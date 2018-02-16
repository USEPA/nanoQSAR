# This script checks whether any of the columns of the X matrix contains a single value repeated for the .  
# entire column.  If this is the case, that(those) column(s) will be removed.
# Created: 01/17/2018  Wilson Melendez
# Revised:

removeColumnsWithOneRepeatedValue <- function(XdataFrame)
{
  numRows = nrow(XdataFrame)  # Get the number of rows.
  
  numCols = ncol(XdataFrame)  # Get the number of columns.
  
  first <- TRUE   # Logical flag
  
  for (icol in 1:numCols)  # Loop over the columns of the data frame.
  {
    xcol <- XdataFrame[,icol]
    value <- xcol[1]
    if (is.na(value) == TRUE || !all(!is.na(xcol)) == TRUE) next  # Skip columns that have different values.
    
    if (all(xcol == value))   # If column has the same value throughout, proceed to store its index.
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
  Xnew <- XdataFrame[,-indexCols]  # Remove columns with repeated values throughout.
  return(Xnew)                     # Return updated data frame.
}