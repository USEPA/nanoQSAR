# This script runs a test suite of the RUnit tests written for the bartMachine analysis of
# the nanoQSAR's and Todd's data.
# Created: 01/25/2018 Wilson Melendez
# Revised:

# Load the RUnit package
library(RUnit)

# Set main and test directories.
main_dir <- "C:/Users/wmelende/RnanoQSAR/R-nanoQSAR"
test_dir <- "C:/Users/wmelende/RnanoQSAR/R-nanoQSAR/RUnit_test"

# Set working directory
setwd(test_dir)

# Set JAVA_HOME to the location of the JDK in your system.
Sys.setenv("JAVA_HOME"="C:\\Program Files\\Java\\jdk1.8.0_152")

#  Get the location of the JDK
Sys.getenv("JAVA_HOME")

# Load the rJava package -- this is needed by the bartMachine.
library(rJava)

# Allocate memory needed before loading the bartMachine.
# Note that the maximum amount of memory can be set only once at the beginning of the R session (a
# limitation of rJava since only one Java Virtual Machine can be initiated per R session), but the number of
# cores can be respecified at any time.
options(java.parameters = "-Xmx4000m")

# Load the bartMachine package
library(bartMachine)

# Allocate number of cores that will be used by the bartMachine
set_bart_machine_num_cores(4)

# Build the pathnames of the R scripts to be tested.
Rscript1 = paste(main_dir, "/runJarFile.R", sep="")
Rscript2 = paste(main_dir, "/removeColumnsWithAllNAs.R", sep="")
Rscript3 = paste(main_dir, "/extractXColumns.R", sep="")
Rscript4 = paste(main_dir, "/extractNumericColumns.R", sep="")
Rscript5 = paste(main_dir, "/getRecordsWithResults.R", sep="")
Rscript6 = paste(main_dir, "/removeColumnsWithOneRepeatedValue.R", sep="")
Rscript7 = paste(main_dir, "/extractYColumn.R", sep="")

# Load the source codes to be tested.
source(Rscript1)
source(Rscript2)
source(Rscript3)
source(Rscript4)
source(Rscript5)
source(Rscript6)
source(Rscript7)

## Define test suite
myTestSuite <- defineTestSuite("RUnit BartMachine", 
                               file.path(test_dir), 
                               testFileRegexp = "^runit.+\\.[rR]$", 
                               testFuncRegexp = "^test.+", 
                               rngKind = "Marsaglia-Multicarry", 
                               rngNormalKind = "Kinderman-Ramage")

# Run test suite
testResult <- runTestSuite(myTestSuite)

# Print the results of the tests to a text file.  Results will be printed in ASCII format.
printTextProtocol(testResult, fileName = "results.txt", showDetails = TRUE)
