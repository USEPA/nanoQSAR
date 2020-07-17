# Ths script tests the automobile data set that comes with the bartMachine package.
# Created: 01/29/2018 Wilson Melendez
# Revised:

test.Automobile <- function()
{
  # Get data set and build X and Y matrices.  
  data("automobile", package = "bartMachine")
  automobile <- na.omit(automobile)   # Omit missing data.
  y <- automobile$log_price
  X <- automobile
  X$log_price <- NULL  # Remove price column from X matrix.
  
  # Build bartMachine model using default parameters.
  bart_machine_automobile <- bartMachine(X, y)
  
  # Obtain R2 information from built model.
  r2 <- bart_machine_automobile$PseudoRsq
  
  # Verify that R2 is correct.
  delta <- 5.0E-2  # This is a tolorence number for the comparison.
  checkEquals(r2, 0.9798, "Result is 0.9798", delta)
}