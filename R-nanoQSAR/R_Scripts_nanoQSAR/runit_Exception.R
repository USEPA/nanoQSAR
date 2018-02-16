# This script tests the RUnit package.
# Created: 12/13/0217 W. Melendez
# Modified:

# Check that an exception will be thrown.
test.Exception <- function()
{
   checkException(fun(TRUE), silent=TRUE)  
}