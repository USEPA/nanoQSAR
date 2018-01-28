# This script tests getRecordsWithResults.R script. It verifies that any columns with the only one value are
# removed.
# Created: 01/26/2018 Wilson Melendez
# Revised:

test.RemoveColumnsWithOneRepeatedValue <- function()
{
  # Build data frames.
  a1 <- c(3, 7, 8, 10, 66)
  a2 <- c(11, 11, 11, 11, 11)
  a3 <- c(567, -77, 3.14, 12.5, 2.33)
  a4 <- c(98.9, 2.5, 66.7, 3.8, 46.2)
  df1 <- data.frame(a1,a2,a3,a4)
  dfnew <- data.frame(a1,a3,a4)
  names(df1) <- c("col1","col2","col3","Col4")
  names(dfnew) <- c("col1","col3","Col4")
  
  # Call function that will remove columns with only one value throughout.
  df2 <- removeColumnsWithOneRepeatedValue(df1)
  
  # Obtain number of columns of returned data frame.
  numCols <- ncol(df2)
  
  # Number of columns should be one less than the original data frame's columns.
  checkEquals(numCols, 3)
  
  # Verified that returned data frame is the same as the expected one.
  checkEquals(df2, dfnew)
  
}