# This script checks whether any of the columns of the original X matrix contains all NAs.  If this is the 
# case, that(those) column(s) will be removed.
# Created: 01/07/2018  Wilson Melendez
# Revised:

removeColumnsWithAllNAs <- function(XdataFrame)
{
  numRows = nrow(XdataFrame)  # Get the number of rows.
  
  numCols = ncol(XdataFrame)  # Get the number of columns.
  
  first <- TRUE  # Logical flag.
  
  for (icol in 1:numCols)  # Loop over columns.
  {
    xcol <- XdataFrame[,icol]  # Select column.
    isColNas <- all(is.na(xcol))  # Check whether columns has only NAs.
    if (isColNas)                 # If true, proceed to store index of column to be removed from data frame.
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
  Xnew <- XdataFrame[,-indexCols]  # Remove columns with all NAs.
  return(Xnew)                    # Return updated data frame.
}
