# This script performs the following tasks:
#
# Created: 01/02/2018 Wilson Melendez
# Revised: 

# Store location of script.
working_directory <- "C:/Users/wmelende/RnanoQSAR/R-nanoQSAR"

# Set working directory
setwd(working_directory)

source("runJarFile.R")
source("extractNumericColumns.R")
source("extractXcolumns.R")
source("extractYcolumn.R")
source("getRecordswithResults.R")
source("removeColumnsWithAllNAs.R")
source("removeColumnsWithOneRepeatedValue.R")

# Define string with location of Jar File
jarFolder <- "C:/Users/wmelende/TempFiles"

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
Ymatrix <- extractYcolumn(trainingData)

# Convert Y matrix to numeric 
y = as.numeric(Ymatrix)

# Check whether the X matrix has columns with no values (all NAs), and if so remove those columns.
Xmatrix <- removeColumnsWithAllNAs(XmatrixOrig)

# Check whether the X matrix has columns with only a single value that is repeated throughout the column.
Xmatrix <- removeColumnsWithOneRepeatedValue(Xmatrix)

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
options(java.parameters = "-Xmx2000m")

# Load the bartMachine package
library(bartMachine)

# Allocate number of cores that will be used by the bartMachine
set_bart_machine_num_cores(4)

# Call the bartMachine
bart_machine <- bartMachine(Xmatrix, y, 
                            num_trees = 200,
                            num_burn_in = 250,
                            num_iterations_after_burn_in = 1000,
                            alpha = 0.95, beta = 2, k = 2, q = 0.9, nu = 3,
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

# Print a summary of the results
summary(bart_machine)


# Make predictions on the training data. Note: this is not necessary in this case because the bart_machine
# object does provide predicted values.  Consider this an example on how to use the "predict" function.
y_hat <- predict(bart_machine, Xmatrix)

# Perform k-fold cross validation
bart_machine_cv5fold <- k_fold_cv(Xmatrix, y, 
                                  k_folds = 5,
                                  folds_vec = NULL, 
                                  verbose = FALSE, 
                                  num_trees = 200,
                                  num_burn_in = 250,
                                  num_iterations_after_burn_in = 1000,
                                  alpha = 0.95, beta = 2, k = 2, q = 0.9, nu = 3,
                                  prob_rule_class = 0.5,
                                  mh_prob_steps = c(2.5, 2.5, 4)/9,
                                  use_missing_data = TRUE, 
                                  use_missing_data_dummies_as_covars = TRUE,
                                  serialize = TRUE)

             
# Run a new bartMachine case by reducing beta: this will add more levels to the trees (deeper trees).
bart_machine1 <- bartMachine(Xmatrix, y, 
                            num_trees = 200,
                            num_burn_in = 250,
                            num_iterations_after_burn_in = 1000,
                            alpha = 0.95, beta = 1, k = 2, q = 0.9, nu = 3,
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

summary(bart_machine1)

# Save bartMachine objects to files.  The saveRDS function saves only an object at a time.
# Use readRDS() to load the objects back into R.
saveRDS(bart_machine, file = "bart_machine.rds")
saveRDS(bart_machine_cv5fold, file = "bart_machine_cv5fold.rds")
saveRDS(bart_machine1, file = "bart_machine1.rds")



