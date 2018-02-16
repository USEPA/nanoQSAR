# This script runs a test suite.

# Load the RUnit package
library(RUnit)

# Load the source code to be tested.
source("temperature.R")
source("exception.R")

# Get working directory
working_directory <- getwd()

## Define test suite
myTestSuite <- defineTestSuite("RUnit Example1", 
                               file.path(working_directory), 
                               testFileRegexp = "^runit.+\\.[rR]$", 
                               testFuncRegexp = "^test.+", 
                               rngKind = "Marsaglia-Multicarry", 
                               rngNormalKind = "Kinderman-Ramage")

# Run test suite
testResult <- runTestSuite(myTestSuite)

# Print the results of the tests to a text file.  Results will be printed
# in ASCII format.
printTextProtocol(testResult, fileName = "results.txt", showDetails = TRUE)
