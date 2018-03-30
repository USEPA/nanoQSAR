# This script extracts the experimental results and builds the Y matrix.
# Created: 01/05/2018 Wilson Melendez
# Revised:

extractYcolumn <- function(dataSet)
{
  Results = c("ViabilityAvg")  # store name of results column.
  
  # Get the desired results using the name of the column in the original data.
  ycolumn <- dataSet[Results[1]]
  
  # Convert the column to a matrix.
  ym <- as.matrix(ycolumn)
  
  # Define new matrix with data converted to numeric.
  ymatrix <- matrix(as.numeric(ym), nrow = nrow(ym))
  
  return(ymatrix)
}