#
#
#

test.nanoQSAR_Bart <- function()
{
  # Define string with location of Jar File.  
  # This pathname has to be updated on each user's machine.
  jarFolder <- main_dir
  
  # Call function that will run Jar file.
  runJarFile(jarFolder)
  
  # Read in CSV file.
  filename <- paste(jarFolder, "/nanoQSAR.csv", sep="")
  nanoQSARdata <- read.csv(filename, stringsAsFactors=FALSE)
  
  # Set working directory back to test directory.
  setwd(test_dir)
  
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
  
  # Store bartMachine object in a file.
  fileNanoQsarRds <- paste(getwd(), "/bart_machine_nanoQSAR.rds", sep="")
  saveRDS(bart_machine, file = fileNanoQsarRds)
  
  
  # Verify that file exists.
  checkTrue(file.exists(fileNanoQsarRds), "File Exists.")
  
  # Read rds file
  bmNano <- readRDS(fileNanoQsarRds)
  
  # Extract R2 from object
  r2 <- bmNano$PseudoRsq
  
  # Verify that R2 is larger than 0.85.
  checkTrue(r2 > 0.70, "Condition is true.")
  
}