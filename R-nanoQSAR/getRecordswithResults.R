# This script extracts the records with results.
# Created: 01/06/2018 Wilson Melendez
# Revised:

getRecordswithResults <- function(dataSet)
{
  Results = c("ViabilityAvg")
  
  # Get the number of rows
  numRows <- nrow(dataSet)
  
  first <- TRUE
  
  for (i in 1:numRows)
  {
    if (dataSet[i, Results[1]] == "null") next   # Skip records with no results
    if (first)
    {
      Tdata <- dataSet[i,]
      first <- FALSE
    }
    else
    {
      Tdata <- rbind(Tdata, dataSet[i,])
    }
  }
  return(Tdata)
}