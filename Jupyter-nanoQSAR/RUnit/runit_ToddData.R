# This script verifies that the BART analysis of Todd's data can be duplicated (for the case of default parameters).
# It stores the bartMachine object in a file, loads the same object back into memory from the file, and verifies 
# that R2 > 0.85.
# Created: 01/25/2018 Wilson Melendez
# Revised:

test.ToddData <- function()
{
  # Define string with location of Todd's data
  mainFolder <- main_dir
  
  # Build full pathname of files to be read in.
  filename1 <- paste(mainFolder, "/LC50_training_set-2d.csv", sep="")
  filename2 <- paste(mainFolder, "/LC50_prediction_set-2d.csv", sep="")
  
  # Read in CSV files.
  Todd_TrainingData <- read.csv(filename1, stringsAsFactors = FALSE)
  Todd_TestData <- read.csv(filename2, stringsAsFactors = FALSE)
  
  # Extract the training X and Y matrices 
  Xtraining <- Todd_TrainingData
  Xtraining$CAS <- NULL
  Xtraining$Tox <- NULL
  Ytraning <- Todd_TrainingData$Tox
  
  # Convert any non-numeric column of the X matrix to numeric.
  numCols = ncol(Xtraining)
  for (i in 1:numCols)
  {
    if (class(Xtraining[,i]) != "numeric")
    {
      Xtraining[,i] <- as.numeric(Xtraining[,i])
    }
  }
  
  # Extract the test X and Y matrices
  Xtest <- Todd_TestData
  Xtest$CAS <- NULL
  Xtest$Tox <- NULL
  Ytest <- Todd_TestData$Tox
  
  # Convert any non-numeric column of the testing X matrix to numeric.
  numCols = ncol(Xtest)
  for (i in 1:numCols)
  {
    if (class(Xtest[,i]) != "numeric")
    {
      Xtest[,i] <- as.numeric(Xtest[,i])
    }
  }
  
  
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
  
  
  # Use the test data to make predictions.
  y_pred_data1 <- predict(bart_machine_todd, Xtest)
  
  # Store bartMachine object in a file.
  outputFolder <- test_dir
  fileRds <- paste(outputFolder, "/bart_machine_todd_testdata.rds", sep="")
  saveRDS(bart_machine_todd, file = fileRds)

  # Verify that file exists.
  checkTrue(file.exists(fileRds), "File Exists.")
  
  # Read rds file
  bm1 <- readRDS(fileRds)
  
  # Use input bartMachine object and test data to make predictions. 
  y_pred_data2 <- predict(bm1, Xtest)
  
  # Verify that both predictions are the same.
  checkEquals(y_pred_data1, y_pred_data2)
  
  # Extract R2 from object
  r2 <- bm1$PseudoRsq
  
  # Verify that R2 is larger than 0.85.
  checkTrue(r2 > 0.85, "Condition is true.")
  
  
}