# This script tests removeColumnsWithAllNAs.R script.  It verifies that any columns with all NAs are removed.
# Created; 01/26/2018 Wilson Melendez
# Revised:

test.RemoveColumnsWithAllNAs <- function()
{
  # Build data frames
  a1 = c(1, 2, 3, 4, 5)
  a2 = c(NA, NA, NA, NA, NA)
  a3 = c(7, 9, -2, 99, -11)
  a4 = c(0.7, 3.14, 66, 178.5, 10)
  df = data.frame(a1, a2, a3, a4)
  dfExpected = data.frame(a1, a3, a4)
  
  # Call function that will remove all-NAs columns. 
  dfReturned <- removeColumnsWithAllNAs(df)
  
  # Get the columns of the processed data frame
  nc <- ncol(dfReturned)
  
  # Number of columns should be one less than the original data frame's columns.
  checkEquals(nc, 3)
  
  # Checked the returned data frame is the same as the expected one.
  checkEquals(dfReturned, dfExpected)
}


