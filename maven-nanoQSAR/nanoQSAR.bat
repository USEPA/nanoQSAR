:: This script runs the nanoQSAR application using 3 jar files.

@echo off

echo Beginning execution of batch file.

echo Running batch script from %~dp0
cd %~dp0

:: First, the jar file below is run. If no argument is
:: given after the jar file name, a default properties file
:: named nanoQSAR.properties is used. The information needed
:: of all of the jar file runs are in the properties file.

echo Beginning execution of the data mining application.
java -jar nanoQSAR.jar
echo Ending execution of the data mining application.

:: Next, the second jar file is run. By default, data is brought
:: in from the filename nanoQSAR.cvs, and the beta coefficients
:: are output to the filename nanoQSAR_BPLS.csv.

echo Beginning execution of the PLS Regression algorithm.
java -jar nanoQSAR_PLS.jar
echo Ending execution of the PLS Regression application.

:: Finally, the third jar file is run.  By default, data is
:: brought in from the filename nanoQSAR_PLS.cvs where the
:: beta coefficients are stored, and from the nanoQSAR.csv file
:: for the records with no results. Experimental results are
:: predicted for rows in nanoQSAR.csv that contain no results.
:: The predicted results are then output to the filename
:: nanoQSAR_Predictions.csv.

echo Beginning execution of the prediction-of-results application.
java -jar nanoQSAR_PRED.jar
echo Ending execution of the prediction-of-results application.

echo Ending execution of batch file.

