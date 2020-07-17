# This script performs the following tasks:
# 1) It separates the nanoQSAR.csv rows that have non-null viabilities into a training set and a 
#    testing set (80%/20%).  
# 2) It then optimizes over small ranges in a set of parameters values (alpha, beta, etc.…) to find out 
#    what set of parameters gives the training set the smallest residual (RMSE).
# 3) It prints out what the parameter values are and what the R2 value is.  
# 4) It builds 200 trees from the training set again using this optimized set of parameters values.
# 5) It makes prediction on the test set.
# 6) It calculates and prints out what the R2 (Q2) value is.  
#
# Created: 01/30/2018  Wilson Melendez and Paul Harten
# Revised:

# Set JAVA_HOME to the location of the JDK in your system.
Sys.setenv("JAVA_HOME"="C:\\Program Files\\Java\\jdk1.8.0_152")

#  Get the location of the JDK
Sys.getenv("JAVA_HOME")

# Load the rJava package -- this is needed by the bartMachine.
library(rJava)

# Allocate memory needed before loading the bartMachine.
# Note that the maximum amount of memory can be set only once at the beginning of the R session (a
# limitation of rJava since only one Java Virtual Machine can be initiated per R session), but the number of
# cores can be respecified at any time.
options(java.parameters = "-Xmx6000m")

# Load the bartMachine package
library(bartMachine)

# Allocate number of cores that will be used by the bartMachine.  Adjust this number to the number of cores
# in your machine.
set_bart_machine_num_cores(4)

# Set the location of scripts.
working_directory <- "C:/Users/wmelende/RnanoQSAR/R-nanoQSAR"

# Set working directory
setwd(working_directory)

# Load external functions into this script.
source("runJarFile.R")
source("extractNumericColumns.R")
source("extractXcolumns.R")
source("extractYcolumn.R")
source("getRecordswithResults.R")
source("removeColumnsWithAllNAs.R")
source("removeColumnsWithOneRepeatedValue.R")

# Define string with location of Jar File
jarFolder <- working_directory

# Call function that will run Jar file.
runJarFile(jarFolder)

# Read in CSV file.
filename <- paste(jarFolder, "/nanoQSAR.csv", sep="")
nanoQSARdata <- read.csv(filename, stringsAsFactors=FALSE)

# Extract numeric columns
numericData <- extractNumericColumns(nanoQSARdata)

# Get the records with results
trainingData <- getRecordswithResults(numericData)

# Extract the X matrix.
XmatrixOrig <- extractXcolumns(trainingData)

# Extract results column.
Ymatrix <- trainingData["ViabilityAvg"]

# Check whether the X matrix has columns with no values (all NAs), and if so remove those columns.
Xmatrix <- removeColumnsWithAllNAs(XmatrixOrig)

# Check whether the X matrix has columns with only a single value that is repeated throughout the column.
Xmatrix <- removeColumnsWithOneRepeatedValue(Xmatrix)

# Get the number of rows
nrows = nrow(Xmatrix)

# Randomize the order of the rows in the data frame structure.  Use the sample R function.
Xrandom <- Xmatrix[sample(1:nrow(Xmatrix)), ]

# Determine the number of rows for the training and test matrices.
nrows_train = ceiling(nrows * 0.80)
nrows_test = nrows - nrows_train

# Obtain the training X matrix.
Xtrain <- Xrandom[1:nrows_train, ]

# Obtain the training Y matrix using the row names of the training X matrix.
rTrainNames <- row.names(Xtrain)
ytrain <- Ymatrix[rTrainNames[], 1]

# Obtain the test X matrix.
np1 <- nrows_train + 1
Xtest <- Xrandom[np1:nrows, ]

# Obtain the test Y matrix using the row names of the test X matrix.
rTestNames <- row.names(Xtest)
ytest <- Ymatrix[rTestNames[], 1]

# Build a BART-CV model by cross-validating over a grid of hyperparameter choices.
# Warning: this can take a long time to run.
alpha_vals <- c(0.80, 0.85, 0.90, 0.95, 0.99)      # Set the  possible choices for the alpha parameter
beta_vals <- c(0.5, 1.0, 1.5, 2.0, 2.5)         # Set the  possible choices for the beta parameter
num_alpha <- length(alpha_vals)  # Number of alpha values
num_beta <- length(beta_vals)    # Number of beta values
first <- TRUE                    # Logical flag
# ij <- 0

for (i in 1: num_alpha)   # Loop over the alpha values
{
  for (j in 1:num_beta)   # Loop over the beta values
  {
    bart_machine_CV <- bartMachineCV(Xtrain, ytrain,
                                     num_tree_cvs = 200, 
                                     k_cvs = c(1, 2, 3, 5),
                                     nu_q_cvs = list(c(3, 0.9), c(3, 0.99), c(10, 0.75)), 
                                     k_folds = 5, verbose = FALSE,
                                     num_burn_in = 250,
                                     num_iterations_after_burn_in = 1000,
                                     alpha = alpha_vals[i], beta = beta_vals[j],
                                     prob_rule_class = 0.5,
                                     mh_prob_steps = c(2.5, 2.5, 4)/9,
                                     use_missing_data = TRUE, 
                                     use_missing_data_dummies_as_covars = TRUE,
                                     serialize = TRUE)
    if (first)
    {
      # bart_machine_arr <- bartMachineArr(bart_machine_CV, R = num_alpha * num_beta)
      bart_machine_opt <- bart_machine_CV
      rmse_opt <- bart_machine_CV$rmse_train
      # ij <- ij + 1
      first <- FALSE
    } else {
      if (bart_machine_CV$rmse_train < rmse_opt) # Check whether new model has smaller RMSE.
      {
        rmse_opt <- bart_machine_CV$rmse_train
        bart_machine_opt <- bart_machine_CV
      }
      # ij <- ij + 1
      # bart_machine_arr[[ij]] <- bart_machine_CV
    }
      
  }
}


# Save optimal bartMachine model to a file.
saveRDS(bart_machine_opt, file = "Optimal_bartMachineCV.rds")

# Print optimized parameters
cat("Optimized alpha = ", bart_machine_opt$alpha, "\n")
cat("Optimized beta = ", bart_machine_opt$beta, "\n")
cat("Optimized k = ", bart_machine_opt$k, "\n")
cat("Optimized nu = ", bart_machine_opt$nu, "\n")
cat("Optimized q = ", bart_machine_opt$q, "\n")

# Build bartMachine model using optimal parameters with 200 trees.
alpha_opt <- bart_machine_opt$alpha
beta_opt <- bart_machine_opt$beta
k_opt <- bart_machine_opt$k
nu_opt <- bart_machine_opt$nu
q_opt <- bart_machine_opt$q

# Build bartMachine model using optmized parameters.  Number of trees is still set to 200.
bart_machine_optimized <- bartMachine(Xtrain, ytrain, 
                                      num_trees = 200,
                                      num_burn_in = 250,
                                      num_iterations_after_burn_in = 1000,
                                      alpha = alpha_opt, beta = beta_opt, k = k_opt, q = q_opt, nu = nu_opt,
                                      prob_rule_class = 0.5,
                                      mh_prob_steps = c(2.5, 2.5, 4)/9,
                                      debug_log = FALSE,
                                      run_in_sample = TRUE,
                                      s_sq_y = "mse",
                                      sig_sq_est = NULL,
                                      cov_prior_vec = NULL,
                                      use_missing_data = TRUE, 
                                      covariates_to_permute = NULL,
                                      num_rand_samps_in_library = 10000,
                                      use_missing_data_dummies_as_covars = TRUE,
                                      replace_missing_data_with_x_j_bar = FALSE,
                                      impute_missingness_with_rf_impute = FALSE,
                                      impute_missingness_with_x_j_bar_for_lm = TRUE,
                                      mem_cache_for_speed = TRUE,
                                      serialize = TRUE,
                                      seed = NULL,
                                      verbose = TRUE)

# Print R2
cat("R2 with optimized parameters = ", bart_machine_optimized$PseudoRsq, "\n")

# Make predictions using optimized bartMachine model on test data.
ytest_hat <- predict(bart_machine_optimized, Xtest)

# Calculate Q2 using the Java program's formula.
Q2 = 1.0 - sum((ytest - ytest_hat)^2) / sum((ytest - mean(ytest))^2)
cat("Q2 = ", Q2, "\n")

# Save optimized bartMachine model to a file.
saveRDS(bart_machine_optimized, file = "bartMachine_OptimizedParameters.rds")
                            
# Save training and test matrices to files.
saveRDS(Xtrain, file = "Xtrain_nanoQSAR.rds")
saveRDS(ytrain, file = "ytrain_nanoQSAR.rds")
saveRDS(Xtest, file = "Xtest__nanoQSAR.rds")
saveRDS(ytest, file = "ytest_nanoQSAR.rds")
saveRDS(y_test_hat, file = "ytest_predicted.rds")

#
# End of script
#
