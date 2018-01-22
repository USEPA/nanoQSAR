# This script checks whether any of the columns of the X matrix contains a single value repeated for the .  
# entire column.  If this is the case, that(those) column(s) will be removed.
# Created: 01/17/2018  Wilson Melendez
# Revised:

removeColumnsWithOneRepeatedValue <- function(XdataFrame)
{
  numRows = nrow(XdataFrame)
  
  numCols = ncol(XdataFrame)
  
  first <- TRUE
  
  for (icol in 1:numCols)
  {
    xcol <- XdataFrame[,icol]
    value <- xcol[1]
    if (is.na(value) == TRUE || !all(!is.na(xcol)) == TRUE) next
    
    if (all(xcol == value)) 
    {
      if (first)
      {
        indexCols <- c(icol)
        first <- FALSE
      }
      else
      {
        indexCols <- c(indexCols, icol)
      }
    }
  }
  Xnew <- XdataFrame[,-indexCols]
  return(Xnew)
}