# This script performs the following tasks:
#
# Created: 01/02/2018 Wilson Melendez
# Revised: 

# Store location of script.
working_directory <- "C:/Users/wmelende/RnanoQSAR/R-nanoQSAR"

# Set working directory
setwd(working_directory)

source("runJarFile.R")
source("extractNumericColumns.R")
source("extractXcolumns.R")
source("extractYcolumn.R")
source("getRecordswithResults.R")
source("removeColumnsWithAllNAs.R")

# Define string with location of Jar File
jarFolder <- "C:/Users/wmelende/TempFiles"

# Call function that will run Jar file.
runJarFile(jarFolder)

# Read in CSV file.
filename <- paste(jarFolder, "/nanoQSAR.csv", sep="")
nanoQSARdata <- read.csv(filename)

# Extract numeric columns
numericData <- extractNumericColumns(nanoQSARdata)

# Get the records with results
trainingData <- getRecordswithResults(numericData)

# Extract the X matrix.
XmatrixOrig <- extractXcolumns(trainingData)

# Extract results column.
Ymatrix <- extractYcolumn(trainingData)

# Convert Y matrix to numeric 
y = as.numeric(Ymatrix)

# Check whether the X matrix has columns with no values (all NAs), and if so remove those columns.
Xmatrix <- removeColumnsWithAllNAs(XmatrixOrig)

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
options(java.parameters = "-Xmx2000m")

# Load the bartMachine package
library(bartMachine)

# Allocate number of cores that will be used by the bartMachine
set_bart_machine_num_cores(4)

# Call the bartMachine
bartMachine(Xmatrix, y, use_missing_data = TRUE)

