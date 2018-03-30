# This script tests the extractYColumn.R script.  It checks that the correct column for the Y matrix was extracted.
# Created: 01/25/2018 Wilson Melendez
# Revised:

test.ExtractYColumn <- function()
{
  # Build data frame
  a1 <- c(3, 7, 8, 10, 66)
  a2 <- c(11, 11, 11, 11, 11)
  a3 <- c(567, -77, 3.14, 12.5, 2.33)
  a4 <- c(98.9, 2.5, 66.7, 3.8, 46.2)
  df <- data.frame(a1,a2,a3,a4)
  
  # Name the columns of the data frame.
  names(df) <- c("col1","col2","col3","ViabilityAvg")
  
  df1 <- as.matrix(a4)  # Expected value.
  
  # Call the function that will extract the Y column from the data frame.
  df2 <- extractYcolumn(df)
  
  # Verify that the extracted column is the expected one.
  checkEquals(df2, df1)
  
}