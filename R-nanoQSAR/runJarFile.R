#  This function runs a Jar file using the system function.
#  Created: 12/21/207 Wilson Melendez
#  Revised:

runJarFile <- function(jarFolder)
{
  # Move to folder where Jar file is located.
  setwd(jarFolder)
 
  # Define string with Java command and options.
  nanoQSAR_JarFile <- "java -jar nanoQSAR.jar"
  
  # Run the Jar File
  system(nanoQSAR_JarFile)
}