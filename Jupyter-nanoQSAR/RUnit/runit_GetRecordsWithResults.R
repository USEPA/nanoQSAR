# This script tests getRecordsWithResults.R script.  It checks that rows with no results are deleted.
# Created: 01/25/2018 Wilson Melendez
# Revised:

test.GetRecordsWithResults <- function()
{
  # Build data frame.
  a1 <- c(3, 7, 8, 10, 66)
  a2 <- c(11, -2, 345, 78, 6.99)
  a3 <- c(567, -77, 3.14, 12.5, 2.33)
  a4 <- c(98.9, "null", 66.7, "null", 46.2)
  
  dfOrig <- data.frame(a1, a2, a3, a4)
  names(dfOrig) <- c("col1","col2","col3","ViabilityAvg")
  
  # Build expected data frame.
  b1 <- c(3, 8, 66)
  b2 <- c(11, 345, 6.99)
  b3 <- c(567, 3.14, 2.33)
  b4 <- c(98.9, 66.7, 46.2)
  dfExpected <- data.frame(b1, b2, b3, b4)
  names(dfExpected) <- c("col1","col2","col3","ViabilityAvg")
  row.names(dfExpected) <- as.integer(c(1, 3, 5))  # Need to specify row names as an integer vector.
  
  # Process data frame.
  dfReturned <- getRecordswithResults(dfOrig)
  dfReturned[,4] <- as.numeric(as.character(dfReturned[,4]))  # Need to convert fourth column to numeric.
  
  # Obtain number of rows of returned data frame.
  numRows <- nrow(dfReturned)
  
  # Check the number of rows is two less than the original number of rows.
  checkEquals(numRows, 3)

  # Check that returned and expected data frames are the same.
  checkEquals(dfReturned, dfExpected)
  
}