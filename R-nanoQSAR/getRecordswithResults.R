# This script extracts the records with results.
# Created: 01/06/2018 Wilson Melendez
# Revised:

getRecordswithResults <- function(dataSet)
{
  Results = c("ViabilityAvg")  # Store name of results column.
  
  # Get the number of rows
  numRows <- nrow(dataSet)
  
  first <- TRUE
  
  for (i in 1:numRows)  # Loop over the rows.
  {
    if (dataSet[i, Results[1]] == "null") next   # Skip records with no results
    if (first)
    {
      Tdata <- dataSet[i,]    # Attach first record with results.
      first <- FALSE
    }
    else
    {
      Tdata <- rbind(Tdata, dataSet[i,])  # Concatenate records horizontally.
    }
  }
  return(Tdata)
}