:: This script runs the nanoQSAR application using 3 jar files.

@echo off

echo Beginning execution of batch file.

echo Running batch script from %~dp0
cd %~dp0

echo Beginning execution of the data mining application.
java -jar nanoQSAR.jar
echo Ending execution of the data mining application.

echo Beginning execution of the PLS Regression algorithm.
java -jar nanoQSAR_PLS.jar
echo Ending execution of the PLS Regression application.

echo Beginning execution of the prediction-of-results application.
java -jar nanoQSAR_PRED.jar
echo Ending execution of the prediction-of-results application.

echo Ending execution of batch file.

