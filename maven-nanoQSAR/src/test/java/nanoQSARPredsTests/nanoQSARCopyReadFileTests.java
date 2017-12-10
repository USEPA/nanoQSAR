/**
 * 
 */
package nanoQSARPredsTests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.contentOf;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.GeneralSecurityException;


import org.jblas.DoubleMatrix;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import junit.framework.Assert;
import nanoQSAR_test.PredictorsBetaMatrices;
import nanoQSAR_test.Utilities;

/**
 * @author Wmelende
 *
 */
public class nanoQSARCopyReadFileTests {
		
	
	/**
	 * This test checks the values of some of the properties in the properties file.  
	 */
	@Test
	public final void testLoadProperties() 
	{
	    /* default file values */
		String CsvFileName  = "nanoQSAR.csv";
		String BplsFileName = "nanoQSAR_BPLS.csv";
		String PredictionsFileName = "nanoQSAR_TEST.csv";
		String propFilename = System.getProperty("user.dir") + "\\nanoQSAR.properties";	
		
		try {
			
			Utilities.loadProperties(propFilename);
			
			Assert.assertEquals(CsvFileName, Utilities.getCsvFileName());
			Assert.assertEquals(BplsFileName, Utilities.getBplsFileName());
			Assert.assertEquals(PredictionsFileName, Utilities.getPredictionsFileName());
			
		} catch (Exception e) {		
			e.printStackTrace();
		} 

	}

	/**
	 * This method throws a file-not-found exception when trying to make a copy of a non-existing file.
	 * @throws Exception
	 */
	@Test(expected=Exception.class)
	public void testCopyFiles() throws Exception
	{
		String originalFilename = System.getProperty("user.dir") + "\\FileDoesNotExist.csv";  // Non-existing file.
		String testFilename = System.getProperty("user.dir") + "\\nanoQSAR_TEST.csv";
		
		/* Make a copy of nanoQSAR.csv. */
		File source = new File(originalFilename);
		File dest = new File(testFilename);
		PredictorsBetaMatrices comp = new PredictorsBetaMatrices();
		try {
			comp.copyFiles(source, dest);
		} catch (IOException ex) {
			throw ex;
		}
	}
	
	/**
	 * This test throws an exception for non-matching headers.
	 * @throws Exception
	 */
	@Test(expected=Exception.class)
    public void testVerifyOrderHeaders() throws Exception
    {
    	String[] xHeader = {"A1", "B1", "C1"};
    	String[] bHeader = {"A0", "A1", "B1", "C2"};
    	
    	PredictorsBetaMatrices prdb = new PredictorsBetaMatrices();
    	try {
    		prdb.verifyOrderHeaders(xHeader, bHeader);
    	} catch (Exception ex) {
    		throw ex;
    	}
    	
    }
	
	/**
	 * This method checks that nulls are replaced by either zeroes or averages.
	 */
	@Test
	public void testReplaceNullsMatrix()
	{
		// Declare matrix.
		DoubleMatrix  x1 = new DoubleMatrix(3, 4);
		
		// Assign values to each cell of the matrix.  Some of the values are nulls.
		x1.put(0, 0, 0.7);
		x1.put(0, 1, 0.0);
		x1.put(0, 2, 1.0);
		x1.put(0, 3, Double.NaN);
		
		x1.put(1, 0, 0.7);
		x1.put(1, 1, 0.0);
		x1.put(1, 2, 5.0);
		x1.put(1, 3, Double.NaN);
		
		x1.put(2, 0, 0.7);
		x1.put(2, 1, 0.0);
		x1.put(2, 2, Double.NaN);
		x1.put(2, 3, Double.NaN);
		
		// Remove nulls from matrix and replace them with zeroes or averages. 
		PredictorsBetaMatrices prdb1 = new PredictorsBetaMatrices();
		prdb1.setTestXmatrix(x1);
		prdb1.replaceNullsXmatrix();
		DoubleMatrix px1 = prdb1.getTestXmatrix();
		
		// assert
		assertEquals(0.0, px1.get(0, 3), 1.0e-12);
		assertEquals(0.0, px1.get(1, 3), 1.0e-12);
		assertEquals(0.0, px1.get(2, 3), 1.0e-12);
		
		assertEquals(3.0, px1.get(2, 2), 1.0e-12);		
		
	}
	
	/**
	 * This test throws a file not found exception if the CSV file is not
	 * found.
	 * @throws Exception 
	 */
	@Test(expected=Exception.class)
	public void testReadCsvFile() throws Exception
	{
		int xcol = 100;
		int ycol = 2;		
		PredictorsBetaMatrices prdb2 = new PredictorsBetaMatrices();
		try 
		{
			String betaFile = "FileDoesNotExist.csv";  // Non-existing CSV file
			prdb2.readCsvFile(betaFile, xcol, ycol);  // Pass non-existing file to method that reads CSV files.
		}
		catch(FileNotFoundException ex)  // Catch exception and throw it again.
		{
			throw ex;
		}
		catch(IOException ex)
		{
			/* catch and throw any other error found */
			throw ex;
		}
		
	}
	
	/**
	 * This test checks whether the output header was built correctly.
	 */
	@Test
	public void testBuildTestHeaders()
	{
		// Declare and initialize expected header.
		String[] expectedHeader = {"CoatingAmount", "Purity", "ContamAl", "ContamAs", "ContamBe", "ContamCa", "ContamCo", 
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
				"UVAExposDuration", "ViabilityAvg", "LC50", "ViabilityAvg_Predicted", "LC50_Predicted"};
		
		// Declare and initialize X matrix header.
		String[] xHeader = {"CoatingAmount", "Purity", "ContamAl", "ContamAs", "ContamBe", "ContamCa", "ContamCo", 
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
				"UVAExposDuration"};
		
		// Declare and initialize Y matrix header.
		String[] yHeader = {"ViabilityAvg", "LC50"};
		
		// Build actual header.
		PredictorsBetaMatrices prdb3 = new PredictorsBetaMatrices();
		prdb3.buildTestHeader(xHeader, yHeader);
		
		// assert
		String[] actualHeader = prdb3.getTestHeader();
		assertArrayEquals(expectedHeader, actualHeader);
		
	}
	
	/**
	 * This test checks that the horizontal concatenation of 3 matrices into a single one is correct.
	 */
	@Test
	public void testBuildResultsMatrix()
	{
		// Declare matrices
		DoubleMatrix matrix1 = new DoubleMatrix(3,2);
		DoubleMatrix matrix2 = new DoubleMatrix(3,2);
		DoubleMatrix matrix3 = new DoubleMatrix(3,2);
		DoubleMatrix expectedMatrix = new DoubleMatrix(3,6);
		
		// Add data to matrices.
		matrix1.addi(2.0);
		matrix2.addi(-1.0);
		matrix3.addi(7.0);
		
		// Add data to the matrix with the expected results.
		expectedMatrix.put(0, 0, 2.0);
		expectedMatrix.put(0, 1, 2.0);
		expectedMatrix.put(1, 0, 2.0);
		expectedMatrix.put(1, 1, 2.0);
		expectedMatrix.put(2, 0, 2.0);
		expectedMatrix.put(2, 1, 2.0);
		
		expectedMatrix.put(0, 2,-1.0);
		expectedMatrix.put(0, 3,-1.0);
		expectedMatrix.put(1, 2,-1.0);
		expectedMatrix.put(1, 3,-1.0);
		expectedMatrix.put(2, 2,-1.0);
		expectedMatrix.put(2, 3,-1.0);
		
		expectedMatrix.put(0, 4, 7.0);
		expectedMatrix.put(0, 5, 7.0);
		expectedMatrix.put(1, 4, 7.0);
		expectedMatrix.put(1, 5, 7.0);
		expectedMatrix.put(2, 4, 7.0);
		expectedMatrix.put(2, 5, 7.0);
		
		// Concatenate the matrix1, matrix2, and matrix3 matrices.
		PredictorsBetaMatrices prdb4 = new PredictorsBetaMatrices();
		prdb4.buildResultsMatrix(matrix1, matrix2, matrix3);
		
		// Get the single matrix containing a1, a2, and a3.
		DoubleMatrix actualMatrix = prdb4.getResultsMatrix();
		
		// assert
		assertEquals(expectedMatrix, actualMatrix);
		
	}
	
	/**
	 * Declare object that will handle temporary folder and file.
	 */
	@Rule
	public TemporaryFolder temporaryFolder = new TemporaryFolder();
	
	/**
	 * This test checks whether data have been written to a CSV file.
	 * @throws Exception
	 */
	@Test
	public void testWriteResultsToCsv() throws Exception
	{
		/* Create temporary file in temporary folder. */
        File output = temporaryFolder.newFolder("reports").toPath().resolve("output.txt").toFile();
		
        /* Declare object that will hold the information to be written to output. */
        PredictorsBetaMatrices prdb5 = new PredictorsBetaMatrices();
        
        /* Declare and initialize header output. */
		String[] testHeader1 = {"ViabilityAvg", "LC50"};	
		
		/* Declare and initialize numeric data. */
		DoubleMatrix results1 = new DoubleMatrix(1,2);
		results1.put(0, 0, 98.5);
		results1.put(0, 1, 12.5);
		
		/* Store header and numeric data in object. */
		prdb5.setTestHeader(testHeader1);
		prdb5.setResultsMatrix(results1);
				
		// Act: proceed to write data to CSV file.
		prdb5.writeResultsToCsv(output.getPath());
		
		// assert some header information
		assertThat(contentOf(output)).contains("ViabilityAvg");
		assertThat(contentOf(output)).contains("LC50");
		
		// assert some numeric information
		assertThat(contentOf(output)).contains("98.5");
		assertThat(contentOf(output)).contains("12.5");
		assertThat(output).hasExtension("txt").hasParent(resolvePath("reports"));				
		
	}
		
	private String resolvePath(String folder)
	{
		return temporaryFolder.getRoot().toPath().resolve(folder).toString();
	}
		
	/**
	 * This test checks the R2 calculation for a case in which the answer is known.
	 */
	@Test
	public void testCalculateR2()
	{
		// Declared object and matrices.
		PredictorsBetaMatrices prdb6 = new PredictorsBetaMatrices();
		DoubleMatrix Yobs = new DoubleMatrix(5, 1);
		DoubleMatrix Ypred = new DoubleMatrix(5, 1);
		
		// Assign values to the Y matrices.
		Yobs.put(0, 0, 1.0);
		Yobs.put(1, 0, 2.0);
		Yobs.put(2, 0, 3.0);
		Yobs.put(3, 0, 4.0);
		Yobs.put(4, 0, 5.0);
		
		Ypred.put(0, 0, 1.1);
		Ypred.put(1, 0, 1.8);
		Ypred.put(2, 0, 2.7);
		Ypred.put(3, 0, 4.2);
		Ypred.put(4, 0, 5.3);
		
		// Calculate R2
		double[] r2 = prdb6.calculateR2(Yobs, Ypred);
		
		// assert
		assertEquals(0.973, r2[0], 1.0E-12);
	}
	
}
