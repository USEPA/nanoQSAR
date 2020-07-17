# This script tests the extractXColumns.R script. It checks that the correct columns for the X matrix
# were extracted by comparing the expected header with the obtained one.
# Created: 01/26/208 Wilson Melendez
# Revised: 

test.ExtractXColumns <- function()
{
  numericCols <- c("CoatingAmount", "Purity", "ContamAl", "ContamAs", "ContamBe", "ContamCa", "ContamCo", 
                   "ContamCr", "ContamFe", "ContamK", "ContamMg", "ContamNa", "ContamP", "ContamPb", "ContamSb", "ContamSe", 
                   "ContamSiO2", "ContamSn", "ContamTl", "ContamV", "ParticleOuterDiamAvg", "ParticleOuterDiamLow", 
                   "ParticleOuterDiamHigh", "ParticleInnerDiamAvg", "ParticleInnerDiamLow", "ParticleInnerDiamHigh", 
                   "ParticleLengthAvg", "ParticleLengthLow", "ParticleLengthHigh", "ParticleThicknessAvg", "ParticleThicknessLow", 
                   "ParticleThicknessHigh", "SurfaceAreaAvg", "SurfaceAreaLow", "SurfaceAreaHigh", "MC_TimeValue", 
                   "MC_ParticleConcentration", "MC_SerumConcentration", "MC_AntibioticConcentration", "MC_DOMConcentration", 
                   "MC_SalinityValue", "MC_pHAvg", "MC_pHLow", "MC_pHHigh", "MC_MediumTemp", "ZetaPotentialAvg", "ZetaPotentialLow", 
                   "ZetaPotentialHigh", "SizeDistribAvg", "SizeDistribLow", "SizeDistribHigh", "SizeDistribAvg2", "SizeDistribLow2", 
                   "SizeDistribHigh2", "SerumConcentration", "AntibioticConcentration", "DOMConcentration", "SalinityValue", "pHAvg", 
                   "pHLow", "pHHigh", "MediumTemp", "TimeValue", "ParticleConcentration", "ParticleExposDuration", "UVADose", 
                   "UVAExposDuration")
  
  # Define string with location of Jar File
  jarFolder <- main_dir
  
  # Call function that will run Jar file.
  runJarFile(jarFolder)
  
  # Read in CSV file.
  filename <- paste(jarFolder, "/nanoQSAR.csv", sep="")
  nanoQSARdata <- read.csv(filename, stringsAsFactors=FALSE)
  
  # Set working directory back to test directory.
  setwd(test_dir)
  
  # Extract numeric columns
  numericData <- extractNumericColumns(nanoQSARdata)
  
  # Get the records with results
  trainingData <- getRecordswithResults(numericData)
  
  # Extract the X matrix.
  XmatrixOrig <- extractXcolumns(trainingData)
  
  # Get header of X matrix.
  xheader <- colnames(XmatrixOrig)
  
  # Verify that X matrix's header is the expected one.
  checkEquals(xheader, numericCols)
  
}