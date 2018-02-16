# This script tests the extractNumericColumns.R script.  It verifies that the correct numeric columns were
# extracted.
# Created: 01/26/2018 Wilson Melendez
# Revised: 

test.ExtractNumericColumns <- function()
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
                   "UVAExposDuration", "ViabilityAvg")
  
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
  
  # Get header.
  header <- colnames(numericData)
  
  # Verify that header from file is the same as the one stored in numericCols.
  checkEquals(header, numericCols)
  
}