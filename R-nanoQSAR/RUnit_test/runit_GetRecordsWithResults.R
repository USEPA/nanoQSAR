# This script tests getRecordsWithResults.R script.  It checks that rows that do not have results are deleted.
# Created: 01/25/2018 Wilson Melendez
# Revised:

test.GetRecordsWithResults <- function()
{
  a1 <- c(3, 7, 8, 10, 66)
  a2 <- c(11, -2, 345, 78, 6.99)
  a3 <- c(567, -77, 3.14, 12.5, 2.33)
  a4 <- c(98.9, "null", 66.7, "null", 46.2)
  
  df <- data.frame(a1,a2,a3,a4)
  names(df) <- c("col1","col2","col3","ViabilityAvg")
  
  df1 <- getRecordswithResults(df)
  
  numRows <- nrow(df1)
  
  checkEquals(numRows, 3)

}