# This script checks whether any of the columns of the original X matrix contains all NAs.  If this is the 
# case, that(those) column(s) will be removed.
# Created: 01/07/2018  Wilson Melendez
# Revised:

removeColumnsWithAllNAs <- function(XdataFrame)
{
  numRows = nrow(XdataFrame)
  
  numCols = ncol(XdataFrame)
  
  first <- TRUE
  for (icol in 1:numCols)
  {
    xcol <- XdataFrame[,icol]
    isColNas <- all(is.na(xcol))
    if (isColNas) 
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
