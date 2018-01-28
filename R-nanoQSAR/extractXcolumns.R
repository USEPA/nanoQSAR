# This script extracts the X matrix
# Created: 01/06/2018 Wilson Melendez
# Revised:

extractXcolumns <- function(trainingData)
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


  # Get the number of columns
  numCols <- length(numericCols)

  # Loop over the data and store the numeric columns in a separate matrix.  
  for (i in 1: numCols)
  {
    xcolumn <- trainingData[numericCols[i]]       # Extract numeric columns using their names.
    xcolumn[xcolumn == "null"] = NA          # Set any nulls that may be found in the column to NAs.
    if (i == 1)
    {
      xDF <- xcolumn                         # Initialized data frame with the first column.
    }
    else
    {
      xDF <- cbind(xDF, xcolumn)             # Concatenate columns horizontally.
    }
    
  }
  
  
    
  # Convert columns of data frame to numeric.
  for (i in 1:numCols)
  {
    if (class(xDF[,i]) == "character")
    {
      xDF[,i] <- as.numeric(xDF[,i])
    }
  }
  
  return(xDF)
  
}