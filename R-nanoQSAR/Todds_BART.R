# This script performs the Bayesian Additive Regression Trees analysis on Todd Martin's data.
#
# Created: 01/19/2018 Wilson Melendez
# Revised: 

# Set location of main scripts.  The user has to adjust this pathname based on the location where this and 
# other scripts are located.
working_directory <- "C:/Users/wmelende/RnanoQSAR/R-nanoQSAR"

# Set working directory
setwd(working_directory)

# Set pathname of location of Todd's data. 
toddFolder <- "C:/Users/wmelende/RnanoQSAR/R-nanoQSAR"

# Paste the names of the files to the directory pathanme.
filename1 <- paste(toddFolder, "/LC50_training_set-2d.csv", sep = "")
filename2 <- paste(toddFolder, "/LC50_prediction_set-2d.csv", sep = "")

# Read in CSV files.
Todd_TrainingData <- read.csv(filename1, stringsAsFactors=FALSE)
Todd_TestData <- read.csv(filename2, stringsAsFactors=FALSE)

# Extract the training X and Y matrices 
Xtraining <- Todd_TrainingData
Xtraining$CAS <- NULL
Xtraining$Tox <- NULL
Ytraning <- Todd_TrainingData$Tox

# Convert any non-numeric column of the X matrix to numeric.
numCols = ncol(Xtraining)   # Get the number of columns.
for (i in 1:numCols)
{
  if (class(Xtraining[,i]) != "numeric")
  {
    Xtraining[,i] <- as.numeric(Xtraining[,i])
  }
}

# Extract the X and Y test matrices.
Xtest <- Todd_TestData
Xtest$CAS <- NULL
Xtest$Tox <- NULL
Ytest <- Todd_TestData$Tox

# Convert any non-numeric column of the X test matrix to numeric.
numCols = ncol(Xtest)  # Get the number of columns
for (i in 1:numCols)
{
  if (class(Xtest[,i]) != "numeric")
  {
    Xtest[,i] <- as.numeric(Xtest[,i])
  }
}


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
options(java.parameters = "-Xmx4000m")

# Load the bartMachine package.
library(bartMachine)

# Allocate number of cores that will be used by the bartMachine.
set_bart_machine_num_cores(4)

# Build a BART model using default values.
bart_machine_todd <- bartMachine(Xtraining, Ytraning, 
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
                                 use_missing_data = FALSE, 
                                 covariates_to_permute = NULL,
                                 num_rand_samps_in_library = 10000,
                                 use_missing_data_dummies_as_covars = FALSE,
                                 replace_missing_data_with_x_j_bar = FALSE,
                                 impute_missingness_with_rf_impute = FALSE,
                                 impute_missingness_with_x_j_bar_for_lm = TRUE,
                                 mem_cache_for_speed = TRUE,
                                 serialize = TRUE,
                                 seed = NULL,
                                 verbose = TRUE)
                            

# Print a summary of the results.
summary(bart_machine_todd)

# Make predictions on the training data. Note: this is not necessary in this case because the bart_machine_todd
# object has predicted values stored in it.  Consider this an example on how to use the "predict" function.
y_hat_training <- predict(bart_machine_todd, Xtraining)

# Save bartMachine object in a file.
saveRDS(bart_machine_todd, file = "bart_machine.todd.rds")

# Perform k-fold cross validation using the testing data set.
# bart_machine_toddCV <- k_fold_cv(Xtraining, Ytraning, 
#                                  k_folds = 5,
#                                  folds_vec = NULL, 
#                                  verbose = FALSE, 
#                                  num_trees = 200,
#                                  num_burn_in = 250,
#                                  num_iterations_after_burn_in = 1000,
#                                  alpha = 0.95, beta = 2, k = 2, q = 0.9, nu = 3,
#                                  prob_rule_class = 0.5,
#                                  mh_prob_steps = c(2.5, 2.5, 4)/9,
#                                  use_missing_data = FALSE, 
#                                  use_missing_data_dummies_as_covars = FALSE,
#                                  serialize = TRUE)

# Save bartMachine object in a file.
# saveRDS(bart_machine_toddCV, file = "bart_machine.toddCV.rds")

# Build a BART-CV model by cross-validating over a grid of hyperparameter choices.
# Warning: this can take a long time to run.
# bart_machine_CV <- bartMachineCV(Xmatrix, y,
#                                 num_tree_cvs = c(50, 200), 
#                                 k_cvs = c(2, 3, 5),
#                                 nu_q_cvs = list(c(3, 0.9), c(3, 0.99), c(10, 0.75)), 
#                                 k_folds = 5, verbose = FALSE,
#                                 num_burn_in = 250,
#                                 num_iterations_after_burn_in = 1000,
#                                 alpha = 0.95, beta = 2,
#                                 prob_rule_class = 0.5,
#                                 mh_prob_steps = c(2.5, 2.5, 4)/9,
#                                 use_missing_data = TRUE, 
#                                 use_missing_data_dummies_as_covars = TRUE,
#                                 serialize = TRUE)

# Make predictions on the test data.  
y_hat_test <- predict(bart_machine_todd, Xtest)

# Write predicted values to a file.
saveRDS(y_hat_test, file = "PredictedValues_Todd_TestData.rds")

# We can test model performance on out-of-sample test data for which the outcomes are known.  
oos_perf <- bart_predict_for_test_data(bart_machine_todd, Xtest, Ytest)
print(oos_perf$rmse)  # Print the root-mean-square error

# Calculate Q2 using the Java program's formula.
Q2 = 1.0 - sum((Ytest - y_hat_test)^2) / sum((Ytest - mean(Ytest))^2)
print(Q2)

