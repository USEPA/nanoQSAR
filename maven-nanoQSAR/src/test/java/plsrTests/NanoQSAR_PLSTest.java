package plsrTests;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.jblas.DoubleMatrix;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.TemporaryFolder;

import nanoQSAR.NanoMaterials;
import pls_analysis.CsvMatrix;
import pls_analysis.NanoQSAR_PLS;


/**
 * @author Wilson Melendez
 *
 */
public class NanoQSAR_PLSTest {
	
	/* Need this line to allow logging of error messages */
	private static Logger LOGGER = Logger.getLogger("nanoQSAR");
	String logFilename = System.getProperty("user.dir") + "\\nanoQSAR.log";	
	
	@Test
	public void testMatrices() throws Exception
	{
		int num_rows = 250;
	    int num_cols = 67;
        DoubleMatrix Xorig = new DoubleMatrix(num_rows, num_cols);
		DoubleMatrix Yorig = new DoubleMatrix(num_cols, 1);
			
		Xorig = DoubleMatrix.rand(num_rows, num_cols);
		Yorig = DoubleMatrix.rand(num_rows, 1);
		
		/* create an instance of CsvMatrix */
		CsvMatrix csvMatrix = new CsvMatrix();
			
		DoubleMatrix Bstar = csvMatrix.performPLSR(Xorig,Yorig);
			
		DoubleMatrix T = csvMatrix.getTmatrix();
		DoubleMatrix diffMat = T.transpose().mmul(T);
		diffMat.subi(DoubleMatrix.diag(DoubleMatrix.ones(diffMat.rows)));
		double norm = diffMat.norm1()/(diffMat.rows*diffMat.columns);
		assertEquals(0.0, norm, 1.0e-15);
			
		DoubleMatrix W = csvMatrix.getWmatrix();
		diffMat = W.transpose().mmul(W);
		diffMat.subi(DoubleMatrix.diag(DoubleMatrix.ones(diffMat.rows)));
		norm = diffMat.norm1()/(diffMat.rows*diffMat.columns);
		assertEquals(0.0, norm, 1.0e-2);
	        				
	} 

	/**
	 * This test throws a file not found exception if the CSV file is not
	 * found.
	 * @throws Exception 
	 */
	@Test(expected=FileNotFoundException.class)
	public void testReadCsvFile() throws Exception
	{		
		try
		{		
			/* Pass the name of a non-existing CSV file to the 
			 * readCsvFile method. This will not throw a file-not-found 
			 * exception since that error is expected*/
			String filename = "FileDoesNotExist.csv";
			NanoMaterials nanoMaterials = new NanoMaterials(filename);
		}
		catch(FileNotFoundException ex)
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
	 * Test method for buildMatricesContainingNulls().
	 */

	@Test
	public void testBuildMatricesContainingNulls() 
	{
		String[] expectedCsvHeader = {
				"MaterialCharID","ORDMaterialID", "DataSource", "LotNumber", "CoreComp",
	               "ShellComp", "CoatingComp", "CoatingAmount", "CoatingAmountUnit", 
	               "FunctionalGroups", "FunctionalizationProtocol", "Purity", 
	               "PurityApproxSymbol", "PurityUnit", "PurityMethod", 
	               "PurityRefChemical", "ContamUnit", "ContamAl", "ContamAs", 
	               "ContamBe", "ContamCa", "ContamCo", "ContamCr", "ContamFe", 
	               "ContamK", "ContamMg", "ContamNa", "ContamP", "ContamPb", "ContamSb", 
	               "ContamSe", "ContamSiO2", "ContamSn", "ContamTl", "ContamV", 
	               "ContamMethod", "CrystalStructure", "CrystalStructureMethod", 
	               "SynthesisMethod", "SynthesisDate", "ParticleOuterDiamAvg", 
	               "ParticleOuterDiamApproxSymbol", "ParticleOuterDiamUnit", 
	               "ParticleOuterDiamUncertain", "ParticleOuterDiamLow", 
	               "ParticleOuterDiamHigh", "ParticleOuterDiamMethod", 
	               "ParticleInnerDiamAvg", "ParticleInnerDiamApproxSymbol", 
	               "ParticleInnerDiamUnit", "ParticleInnerDiamUncertain", 
	               "ParticleInnerDiamLow", "ParticleInnerDiamHigh", 
	               "ParticleInnerDiamMethod", "ParticleLengthAvg", 
	               "ParticleLengthApproxSymbol", "ParticleLengthUnit", 
                "ParticleLengthUncertain", "ParticleLengthLow", 
                "ParticleLengthHigh", "ParticleLengthMethod", 
                "ParticleThicknessAvg", "ParticleThicknessApproxSymbol", 
                "ParticleThicknessUnit", "ParticleThicknessUncertain", 
                "ParticleThicknessLow", "ParticleThicknessHigh", 
                "ParticleThicknessMethod", "WallNumber", "AspectRatio", "Shape", 
                "SurfaceAreaAvg", "SurfaceAreaApproxSymbol", "SurfaceAreaUnit", 
                "SurfaceAreaUncertain", "SurfaceAreaLow", "SurfaceAreaHigh", 
                "SurfaceAreaMethod", "MC_TimeValue", "MC_TimeValueUnit", 
                "MC_ParticleConcentration", "MC_ParticleConcentrationUnit", 
                "DispersionMediumID", "MC_MediumDescription", "MC_SerumAdditive", 
	               "MC_SerumConcentration", "MC_SerumConcentrationUnit", 
	               "MC_AntibioticName", "MC_AntibioticConcentration", 
	               "MC_AntibioticConcentrationUnit", "MC_DOMForm", "MC_DOMConcentration", 
	               "MC_DOMUnit", "MC_SalinityValue", "MC_SalinityUnit", 			
                "Solubility", "MC_pHAvg", "MC_pHApproxSymbol", 
                "MC_pHUncertain", "MC_pHLow", "MC_pHHigh", "MC_MediumTemp", "MC_MediumTempUnit", 
                "ZetaPotentialAvg", "ZetaPotentialApproxSymbol", 
                "ZetaPotentialUnit", "ZetaPotentialUncertain", "ZetaPotentialLow", 
                "ZetaPotentialHigh", "ZetaPotentialMethod", "SizeDistribType", 
                "SizeDistribModality", "SizeDistribMethod", "SizeDistribAvg", 
                "SizeDistribApproxSymbol", "SizeDistribUnit", 
                "SizeDistribUncertain", "SizeDistribLow", "SizeDistribHigh", 
                "SizeDistribAvg2", "SizeDistribApproxSymbol2", "SizeDistribUnit2", 
                "SizeDistribUncertain2", "SizeDistribLow2", "SizeDistribHigh2",
	               "MeasurementID", "AssayType", "AssayName", "SampleName", 
                "SubjectSpecies", "SubjectID", "CellType", "CellSource", 
                "TestMediumID", "MediumDescription", "SerumAdditive", 
	               "SerumConcentration", "SerumConcentrationUnit", 
	               "AntibioticName", "AntibioticConcentration", 
	               "AntibioticConcentrationUnit", "DOMForm", "DOMConcentration", 
	               "DOMUnit", "SalinityValue", "SalinityUnit", 
                "pHAvg", "pHApproxSymbol", "pHUncertain",  
                "pHLow", "pHHigh", "MediumTemp", "MediumTempUnit", 
                "TimeValue", "TimeValueUnit", "ParticleConcentration", 
	               "ParticleConcentrationUnit", "ParticleExposDuration", 
	               "ParticleExposDurationUnit", "UVADose", "UVADoseUnit", 
	               "UVAExposDuration", "UVAExposDurationUnit", "ViabilityAvg", 
	               "ViabilityApproxSymbol", "ViabilityUnit", "ViabilityUncertain", 
	               "ViabilityLow", "ViabilityHigh", "ViabilityMethod", "LC50", 
	               "LC50ApproxSymbol", "LC50Unit",          
	                };
		CsvMatrix.setHeader(expectedCsvHeader);
		CsvMatrix.selectNumericColumns();
		int xcols = CsvMatrix.getXcolumns();
		int ycols = CsvMatrix.getYcolumns();
		String[] values = new String[expectedCsvHeader.length];
		List<String[]> rowsTest = new ArrayList<String[]>();
		DoubleMatrix X = new DoubleMatrix(1,xcols);
		DoubleMatrix Y = new DoubleMatrix(1,ycols);
		
		for (int i = 0; i < expectedCsvHeader.length; i++)
		{
			values[i] = "0.2";
		}
		values[7] = "2.3";
		values[11] = "98.4";
		
		for (int i = 0; i < xcols; i++)
		{
			X.put(0,i,0.2);
		}
		X.put(0,0,2.3);
		X.put(0,1,98.4);
		Y.put(0,0,0.2);
		Y.put(0,1,0.2);
		
		rowsTest.add(values);
		CsvMatrix.setRows(rowsTest);
	    DoubleMatrix Xtest = new DoubleMatrix(1,xcols);
	    DoubleMatrix Ytest = new DoubleMatrix(1,ycols);
		CsvMatrix.buildMatricesContainingNulls(Xtest, Ytest);
		
		DoubleMatrix Xsame, Ysame;
		Xsame = (X.eq(Xtest));  
		Ysame = (Y.eq(Ytest));
		
		for (int i = 0; i < xcols; i++)
		{
			assertEquals(1.0,Xsame.get(0,i),1.0e-15);
		}
		
		assertEquals(1.0,Ysame.get(0,0),1.0e-15);
		assertEquals(1.0,Ysame.get(0,1),1.0e-15);
	}
	
	@Test
	public void testBuildMatricesWithoutNulls()
	{
		String[] expectedCsvHeader = {
				"MaterialCharID","ORDMaterialID", "DataSource", "LotNumber", "CoreComp",
	               "ShellComp", "CoatingComp", "CoatingAmount", "CoatingAmountUnit", 
	               "FunctionalGroups", "FunctionalizationProtocol", "Purity", 
	               "PurityApproxSymbol", "PurityUnit", "PurityMethod", 
	               "PurityRefChemical", "ContamUnit", "ContamAl", "ContamAs", 
	               "ContamBe", "ContamCa", "ContamCo", "ContamCr", "ContamFe", 
	               "ContamK", "ContamMg", "ContamNa", "ContamP", "ContamPb", "ContamSb", 
	               "ContamSe", "ContamSiO2", "ContamSn", "ContamTl", "ContamV", 
	               "ContamMethod", "CrystalStructure", "CrystalStructureMethod", 
	               "SynthesisMethod", "SynthesisDate", "ParticleOuterDiamAvg", 
	               "ParticleOuterDiamApproxSymbol", "ParticleOuterDiamUnit", 
	               "ParticleOuterDiamUncertain", "ParticleOuterDiamLow", 
	               "ParticleOuterDiamHigh", "ParticleOuterDiamMethod", 
	               "ParticleInnerDiamAvg", "ParticleInnerDiamApproxSymbol", 
	               "ParticleInnerDiamUnit", "ParticleInnerDiamUncertain", 
	               "ParticleInnerDiamLow", "ParticleInnerDiamHigh", 
	               "ParticleInnerDiamMethod", "ParticleLengthAvg", 
	               "ParticleLengthApproxSymbol", "ParticleLengthUnit", 
                "ParticleLengthUncertain", "ParticleLengthLow", 
                "ParticleLengthHigh", "ParticleLengthMethod", 
                "ParticleThicknessAvg", "ParticleThicknessApproxSymbol", 
                "ParticleThicknessUnit", "ParticleThicknessUncertain", 
                "ParticleThicknessLow", "ParticleThicknessHigh", 
                "ParticleThicknessMethod", "WallNumber", "AspectRatio", "Shape", 
                "SurfaceAreaAvg", "SurfaceAreaApproxSymbol", "SurfaceAreaUnit", 
                "SurfaceAreaUncertain", "SurfaceAreaLow", "SurfaceAreaHigh", 
                "SurfaceAreaMethod", "MC_TimeValue", "MC_TimeValueUnit", 
                "MC_ParticleConcentration", "MC_ParticleConcentrationUnit", 
                "DispersionMediumID", "MC_MediumDescription", "MC_SerumAdditive", 
	               "MC_SerumConcentration", "MC_SerumConcentrationUnit", 
	               "MC_AntibioticName", "MC_AntibioticConcentration", 
	               "MC_AntibioticConcentrationUnit", "MC_DOMForm", "MC_DOMConcentration", 
	               "MC_DOMUnit", "MC_SalinityValue", "MC_SalinityUnit", 			
                "Solubility", "MC_pHAvg", "MC_pHApproxSymbol", 
                "MC_pHUncertain", "MC_pHLow", "MC_pHHigh", "MC_MediumTemp", "MC_MediumTempUnit", 
                "ZetaPotentialAvg", "ZetaPotentialApproxSymbol", 
                "ZetaPotentialUnit", "ZetaPotentialUncertain", "ZetaPotentialLow", 
                "ZetaPotentialHigh", "ZetaPotentialMethod", "SizeDistribType", 
                "SizeDistribModality", "SizeDistribMethod", "SizeDistribAvg", 
                "SizeDistribApproxSymbol", "SizeDistribUnit", 
                "SizeDistribUncertain", "SizeDistribLow", "SizeDistribHigh", 
                "SizeDistribAvg2", "SizeDistribApproxSymbol2", "SizeDistribUnit2", 
                "SizeDistribUncertain2", "SizeDistribLow2", "SizeDistribHigh2",
	               "MeasurementID", "AssayType", "AssayName", "SampleName", 
                "SubjectSpecies", "SubjectID", "CellType", "CellSource", 
                "TestMediumID", "MediumDescription", "SerumAdditive", 
	               "SerumConcentration", "SerumConcentrationUnit", 
	               "AntibioticName", "AntibioticConcentration", 
	               "AntibioticConcentrationUnit", "DOMForm", "DOMConcentration", 
	               "DOMUnit", "SalinityValue", "SalinityUnit", 
                "pHAvg", "pHApproxSymbol", "pHUncertain",  
                "pHLow", "pHHigh", "MediumTemp", "MediumTempUnit", 
                "TimeValue", "TimeValueUnit", "ParticleConcentration", 
	               "ParticleConcentrationUnit", "ParticleExposDuration", 
	               "ParticleExposDurationUnit", "UVADose", "UVADoseUnit", 
	               "UVAExposDuration", "UVAExposDurationUnit", "ViabilityAvg", 
	               "ViabilityApproxSymbol", "ViabilityUnit", "ViabilityUncertain", 
	               "ViabilityLow", "ViabilityHigh", "ViabilityMethod", "LC50", 
	               "LC50ApproxSymbol", "LC50Unit",          
	                };
		CsvMatrix.setHeader(expectedCsvHeader);
		CsvMatrix.selectNumericColumns();
		int xcols = CsvMatrix.getXcolumns();
		int ycols = CsvMatrix.getYcolumns();
		String[] values = new String[expectedCsvHeader.length];
		List<String[]> rowsTest = new ArrayList<String[]>();
		DoubleMatrix X = new DoubleMatrix(1,xcols);
		DoubleMatrix Y = new DoubleMatrix(1,ycols);
		
		for (int i = 0; i < expectedCsvHeader.length; i++)
		{
			values[i] = "0.2";
		}
		values[7] = "2.3";
		values[11] = "98.4";
		
		for (int i = 0; i < xcols; i++)
		{
			X.put(0,i,0.2);
		}
		X.put(0,0,2.3);
		X.put(0,1,98.4);
		Y.put(0,0,0.2);
		Y.put(0,1,0.2);
		
		rowsTest.add(values);
		CsvMatrix.setRows(rowsTest);	
		DoubleMatrix Xtest = new DoubleMatrix(1,xcols);
	    DoubleMatrix Ytest = new DoubleMatrix(1,ycols);
		CsvMatrix.buildMatricesWithoutNulls(Xtest, Ytest);
		
		DoubleMatrix Xsame, Ysame;
		Xsame = (X.eq(Xtest));  
		Ysame = (Y.eq(Ytest));
		
		for (int i = 0; i < xcols; i++)
		{
			assertEquals(1.0,Xsame.get(0,i),1.0e-15);
		}
		
		assertEquals(1.0,Ysame.get(0,0),1.0e-15);
		assertEquals(1.0,Ysame.get(0,1),1.0e-15);
	}

	/**
	 * This tests the getValue method of the CsvMatrix class.
	 * @author Wilson Melendez
	 */
	@Test
	public void testGetValue()
	{
		String str = "null";
		Double value = CsvMatrix.getValue(str, 0.0, 100.0);
		assertTrue("Random value between 0 and 100.0", 0.0 <= value && value < 100.0);
		
		str = "54.7";
		value = CsvMatrix.getValue(str, 0.0, 100.0);
		assertEquals(54.7,value,1.0e-15);
	}
	
	/**
	 * This test verifies some of the results of a numerical example
	 * found in Abdi, H. (2010). Partial least square regression, 
	 * projection on latent structure regression, PLS-Regression. 
	 * <https://www.utdallas.edu/~herve/abdi-wireCS-PLS2010.pdf> 
	 * Wiley Interdisciplinary Reviews: Computational Statistics, 
	 * 2, 97-106.
	 * @author Wilson Melendez
	 * @throws Exception 
	 */
	@Test
	public void testPLSalgorithm() throws Exception
	{
		double[][] yMatrix = {{14.0,7.0,8.0}, 
				              {10.0,7.0,6.0},
				              { 8.0,5.0,5.0},
				              { 2.0,4.0,7.0},
				              { 6.0,2.0,4.0}};
		
		double[][] xMatrix = {{ 7.0,7.0,13.0,7.0},
	                          { 4.0,3.0,14.0,7.0},
	                          {10.0,5.0,12.0,5.0},
	                          {16.0,7.0,11.0,3.0},
	                          {13.0,3.0,10.0,3.0}};
		
		double[][] tMatrix = {{  0.4538, -0.4662,  0.5716},
				              {  0.5399,  0.4940, -0.4631},
				              {  0.0,     0.0,     0.0},
				              { -0.4304, -0.5327, -0.5301},
				              { -0.5633,  0.5049,  0.4217}};
		
		double[][] uMatrix = {{  1.9451, -0.7611,  0.6191},
				              {  0.9347,  0.5305, -0.5388},
				              { -0.2327,  0.6084,  0.0823},
				              { -0.9158, -1.1575, -0.6139},
				              { -1.7313,  0.7797,  0.4513}};
				             		
		
		DoubleMatrix X = new DoubleMatrix(xMatrix);
		DoubleMatrix Y = new DoubleMatrix(yMatrix);
		
		/* create an instance of CsvMatrix */
		CsvMatrix csvMatrix = new CsvMatrix();
		
		/* Perform the PLS regression analysis. */
		DoubleMatrix BplsStar = csvMatrix.performResultsIndependentPLSR(X, Y);	
		
		/* Predict the Y values using X and BPLS*. */
		DoubleMatrix Ypredicted = CsvMatrix.predictResults(X, BplsStar);
		
		/* Calculate the residual sum of squares also known as RESS.
		 * The equation is : RESS = ||Y - Ypredicted||^2  
		 * where || is the norm of a vector.
		 */
		double ress = 0.0;
		DoubleMatrix Ydiff = Y.sub(Ypredicted);
		ress = Math.pow(Ydiff.norm2(), 2.0);
		
		/* Verify that r2 is equal to 1.25. */
		assertEquals(1.25, ress, 1.0E-06);
		
		/* Calculate average of observed values. */
		DescriptiveStatistics stats = new DescriptiveStatistics();
		DoubleMatrix meanY = new DoubleMatrix(Y.rows, Y.columns);
		for (int j = 0; j < Y.columns; j++)
		{
			for (int i = 0; i < Y.rows; i++)
			{
				stats.addValue(Y.get(i, j));
			}
			
			double mean = stats.getMean();
			
			for (int i = 0; i < Y.rows; i++)
			{
				meanY.put(i, j, mean);
			}
			
			stats.clear();
		}
		
		/* Calculate R2 as the following: 
		 * R2 = 1.0 - (||Y- Ypredicted||^2 / ||Y - meanY||^2) 
		 */
		DoubleMatrix YdiffMean = Y.sub(meanY);
		double sumYmean = Math.pow(YdiffMean.norm2(), 2.0);
		double sumYmean1 = Math.pow(YdiffMean.getColumn(0).norm2(), 2.0);
		double sumYmean2 = Math.pow(YdiffMean.getColumn(1).norm2(), 2.0);
		double sumYmean3 = Math.pow(YdiffMean.getColumn(2).norm2(), 2.0);
		double ress1 = Math.pow(Ydiff.getColumn(0).norm2(), 2.0);
		double ress2 = Math.pow(Ydiff.getColumn(1).norm2(), 2.0);
		double ress3 = Math.pow(Ydiff.getColumn(2).norm2(), 2.0);
		double R2 = 1.0 - (ress / sumYmean);
		assertTrue("R2 > 0.9", R2 > 0.9);
		
		double R2Col1 = 1.0 - (ress1 / sumYmean1);
		assertTrue("R2Col1 > 0.9", R2Col1 > 0.9);
		double R2Col2 = 1.0 - (ress2 / sumYmean2);
		assertTrue("R2Col2 > 0.9", R2Col2 > 0.9);
		double R2Col3 = 1.0 - (ress3 / sumYmean3);
		assertTrue("R2Col3 > 0.8", R2Col3 > 0.8);
		
		/* Split original data into 5 subsets that will be used for a 5-fold
		 * cross-validation analysis. 
		 */
//		List<Integer> list = new ArrayList<Integer>();
//		CsvMatrix.splitDataIntoSets(X, Y, list);
//				
//		/* Use the list containing the re-shuffled indices to 
//		 * obtain the re-shuffled Y vector. 
//		 */
//		DoubleMatrix Yshuffled = new DoubleMatrix(5,3);
//		for (int j = 0; j < 3; j++)
//		{
//			for (int i = 0; i < 5; i++)
//			{
//			    int index = list.get(i);
//				Yshuffled.put(i, j, Y.get(index, j));
//			}
//		}
		
		/* Perform a 5-fold cross-validation and compute Q2. */	
		DoubleMatrix Ytilde = csvMatrix.performFiveFoldCrossValidation(X, Y);
		double sum1 = 0.0;
		double sum2 = 0.0;
		DoubleMatrix YdiffMean1 = Y.sub(meanY);
		DoubleMatrix YdiffTilde = Y.sub(Ytilde);
		sum1 = Math.pow(YdiffTilde.norm2(), 2.0);
		sum2 = Math.pow(YdiffMean1.norm2(), 2.0);
		
		sumYmean1 = Math.pow(YdiffMean1.getColumn(0).norm2(), 2.0);
		sumYmean2 = Math.pow(YdiffMean1.getColumn(1).norm2(), 2.0);
		sumYmean3 = Math.pow(YdiffMean1.getColumn(2).norm2(), 2.0);
		ress1 = Math.pow(YdiffTilde.getColumn(0).norm2(), 2.0);
		ress2 = Math.pow(YdiffTilde.getColumn(1).norm2(), 2.0);
		ress3 = Math.pow(YdiffTilde.getColumn(2).norm2(), 2.0);
		
		double Q2 = 1.0 - sum1/sum2;
		assertTrue("Q2 < R2", Q2 < R2);
		double Q2Col1 = 1.0 - (ress1 / sumYmean1);
		assertTrue("Q2Col1 > 0.9", Q2Col1 > 0.9);
		double Q2Col2 = 1.0 - (ress2 / sumYmean2);
		assertTrue("Q2Col2 > 0.9", Q2Col2 > 0.9);
		double Q2Col3 = 1.0 - (ress3 / sumYmean3);
		assertTrue("Q2Col3 < 0.0", Q2Col3 < 0.0);
		
	}
	
	/**
	 * This test does the following:
	 * a) Generate a 10*6 matrix X, 10*1 column Y of random numbers; 
	 * b) make column 4 to be a linear combination of columns 1 and 2; 
	 * c) Perform a PLS transformation; 
	 * d) Confirm there are 5 dimensions in the latent space.
	 *    
	 * @author Wilson Melendez
	 * @throws Exception 
	 */
	@Test 
	public void testPLS_v1() throws Exception
	{ 
		int nrows = 10;
		int ncols = 6;
		
		double a = 0.2;
		double b = 0.6;
		double maxNumber = 10.0;
		
		DoubleMatrix X1 = new DoubleMatrix(nrows,ncols);
		DoubleMatrix Y1 = new DoubleMatrix(nrows,1);
		
		/* Generate random values for the X matrix. */
		for (int j = 0; j < ncols; j++)
		{
			for (int i = 0; i < nrows; i++)
			{
				double rValue = (2.0 * Math.random() - 1.0) * maxNumber;
			    X1.put(i, j, rValue);
			}			
		}
		
		/* Generate random values for the Y matrix. */
		for (int i = 0; i < nrows; i++)
		{
			double rValue = (2.0 * Math.random() - 1.0) * maxNumber;
		    Y1.put(i, 0, rValue);
		}			
		
		/* Make column 4 of the X matrix to be a linear combination 
		 * of columns 1 and 2. 
		 */
		for (int i = 0; i < nrows; i++)
		{
			double v = a * X1.get(i, 0) + b * X1.get(i, 1);
			X1.put(i, 3, v);
		}
		
		/* create an instance of CsvMatrix */
		CsvMatrix csvMatrix = new CsvMatrix();
		
		/* Perform the PLS algorithm.*/
		DoubleMatrix Bpls1 = csvMatrix.performPLSR(X1,Y1);
		
		DoubleMatrix T1 = csvMatrix.getTmatrix();
		
		/* Verify that the dimensionality of the latent space is 5. */
		assertTrue("Latent space dimensionality is wrongly equal to "+T1.columns, T1.columns == 5);
	}
	
	/**
	 * This test does the following:
	 * a) Generate a 10*6 matrix X, 10*1 column Y of random numbers; 
	 * b) Make one column of X equal to resultant column Y; 
	 * c) Perform a PLS transformation; 
	 * d) Confirm the latent space has only 1 dimension.
	 * @author Wilson Melendez
	 * @throws Exception 
	 */
	@Test 
	public void testPLS_v2() throws Exception
	{ 
		int nrows = 10;
		int ncols = 6;
		int colXY = 2;
		double maxNumber = 10.0;
		double delta = 1.0E-06;
		DoubleMatrix X2 = new DoubleMatrix(nrows,ncols);
		DoubleMatrix Y2 = new DoubleMatrix(nrows,1);
		
		/* Generate random values for the X matrix. */
		for (int j = 0; j < ncols; j++)
		{
			for (int i = 0; i < nrows; i++)
			{
				double rValue = (2.0 * Math.random() - 1.0) * maxNumber;
			    X2.put(i, j, rValue);
			}			
		}
		
		/* Generate random values for the Y matrix. */
		for (int i = 0; i < nrows; i++)
		{
			double rValue = (2.0 * Math.random() - 1.0) * maxNumber;
		    Y2.put(i, 0, rValue);
		}			
		
		/* Set one of the X columns equal to Y. */
		for (int i = 0; i < nrows; i++)
		{
			double v = Y2.get(i, 0);
			X2.put(i, colXY, v);
		}
		
		/* create an instance of CsvMatrix */
		CsvMatrix csvMatrix = new CsvMatrix();
		
		/* Perform the PLS algorithm and calculate BPLS* */
		DoubleMatrix Bpls1 = csvMatrix.performPLSR(X2,Y2);
		
		/* Confirm the latent space has only 1 dimension. 
		 * Loop over the rows of BPLS* and verify that only one
		 * element is non-zero and equal to 1.
		 * The element equal to 1 should be in row number colXY + 1.
		 */
		for (int i = 0; i < Bpls1.rows; i++)
		{
			if (i == (colXY + 1))  // This should be the only non-zero element.
			{
				assertEquals(1.0, Bpls1.get(i), delta);
			}
			else  // All other elements should be zero or close to it.
			{
				assertEquals(0.0, Bpls1.get(i), delta);
			}
		}
		
	}
	
	
	/**
	 * This test performs the following steps:
	 * a) Generate b0, b1,...,bm coefficents; 
	 * b) Randomly build matrix n*m matrix X, n large; 
	 * c) calculate Y = Xb; 
	 * d) add a small random error to Y, Ypred = Y + error(rand); 
	 * e) Perform PLS transformation on X and Ypred, calculate and 
	 *    confirm R2; 
	 * f) Perform PLS transformation on all 5 of the 4/5-fold 
	 *    submatrices of X and Ypred, calculate and confirm Q2.
	 *    
	 *   @author Wilson Melendez
	 * @throws Exception 
	 */
	@Test
	public void testPLS_v3() throws Exception
	{
		/* Number of rows */
		int n = 100;
		
		/* Number of columns */
		int m = 20;
		
		/* Maximum values for the data and errors. */
		double maxNumber = 20.0;
		double maxError = 400.0;
		
		/* Vector of Coefficients  */
		DoubleMatrix B = new DoubleMatrix(m + 1);
		
		/* Matrix of results */
		DoubleMatrix Y = new DoubleMatrix(n);
		DoubleMatrix Yobs = new DoubleMatrix(n);
		
		/* Matrix containing the predictors. */
		DoubleMatrix X = new DoubleMatrix(n, m);
		
		/* Augmented Matrix: (1 : X) */
		DoubleMatrix Xb = new DoubleMatrix(n, m + 1);
		
		/* Generate b0, b1, b2, ..., bm coefficients. */
		for (int i = 0; i < m + 1; i++)
		{
			double rValue = (2.0 * Math.random() - 1.0) * maxNumber;
			B.put(i, rValue);
		}
				
		/* Generate random values for X. */
		for (int j = 0; j < m; j++)
		{
			for (int i = 0; i < n; i++)
			{
				double rValue = (2.0 * Math.random() - 1.0) * maxNumber;
			    X.put(i, j, rValue);
			}			
		}
		
		/* Augment the X matrix by adding a column of 1's as the
		 * first column. */
		DoubleMatrix Ones = DoubleMatrix.ones(X.rows);
		Xb = DoubleMatrix.concatHorizontally(Ones, X);
		
		/* Calculate the Y matrix using the Xb and B matrices. */
		Y = Xb.mmul(B);
		
		/* Add a small error to Y and store the new values in Yobs. */
		for (int i = 0; i < Y.rows; i++)
		{
			double rValue = Y.get(i) + (2.0 * Math.random() - 1.0) * maxError;
			Yobs.put(i, rValue);
		}
		
		/* Calculate average of observed values. */
		DescriptiveStatistics stats = new DescriptiveStatistics();
	    for (int i = 0; i < Yobs.rows; i++)
	    {
			stats.addValue(Yobs.get(i));
		}
		double meanY = stats.getMean();
		
		/* create an instance of CsvMatrix */
		CsvMatrix csvMatrix = new CsvMatrix();
		
		/* Perform the PLS regression algorithm and return the 
		 * BPLS matrix.
		 */
		DoubleMatrix BPLSS = csvMatrix.performPLSR(X,Yobs);	
		
		/* Predict the results using X and BPLS*. */
		DoubleMatrix Ypred = CsvMatrix.predictResults(X, BPLSS);
		
		/* Calculate R2. */
		double sum1 = 0.0, sum2 = 0.0;
		for (int i = 0; i < n; i++)
		{
			sum1 = sum1 + (Yobs.get(i) - Ypred.get(i)) * (Yobs.get(i) - Ypred.get(i));
			sum2 = sum2 + (Yobs.get(i) - meanY) * (Yobs.get(i) - meanY);
		}
		
		double R2 = 1.0 - sum1/sum2;
		
		assertTrue("R2 = "+R2+" should be less that 1.0, but not too much", 1.0>=R2 && R2>=0.80);
		
		/* Perform a 5-fold cross-validation and compute Q2. */		
		DoubleMatrix Ytilde = csvMatrix.performFiveFoldCrossValidation(X, Yobs);
		sum1 = 0.0;
		sum2 = 0.0;
		for (int i = 0; i < n; i++)
		{
			sum1 = sum1 + (Yobs.get(i) - Ytilde.get(i)) * (Yobs.get(i) - Ytilde.get(i));
			sum2 = sum2 + (Yobs.get(i) - meanY) * (Yobs.get(i) - meanY);
		}
		
		double Q2 = 1.0 - sum1/sum2;
		assertTrue("Q2 = "+Q2+" should be less than R2 = "+R2+", but not too much", R2>=Q2 && Q2>=R2-0.2);
		
	}
	
	/**
	 * This test performs the following steps:
	 * 1) It runs the whole application.
	 * 2) It checks whether the log file was created.
	 * 3) it checks whether the log file is empty or not.
	 * @author Wilson Melendez
	 * @throws Exception 
	 */
	@Test
	public void testMainProgram() throws Exception
	{
		String[] args = null;
		/* Run the application. */
		NanoQSAR_PLS.main(args);
		
		/* Verify that the log file was created and that it's not
		 * empty. */
	
		File file = new File(logFilename);
		assertTrue("Log file does not exists.", file.exists());
		assertTrue("Log file is empty.", file.length() > 0);
	}
	
	/**
	 * This test checks whether the readCsvFile method successfully
	 * recognizes a CSV file with a name other than the default one. 
	 * If the CSV file cannot be found, an exception will be thrown and
	 * the test will fail.
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @author Wilson Melendez
	 */
	@Test
	public void testNonDefaultCsvFile() throws FileNotFoundException, IOException
	{
		/* Create a temporary file and write data to it. */
		String filename = System.getProperty("user.dir") + "\\nanoQSAR1.csv";
		FileWriter fw = new FileWriter(filename);
		BufferedWriter buffer = new BufferedWriter(fw);
		buffer.write("1,2,3,4,5,\n");  // Write something to the file.
		buffer.close();
		fw.close();
		
		/* Pass the name of the file to the readCsvFile method
		 * for processing. If the file is found, no exception will be
		 * thrown and the test will succeed.  If the file is not found, 
		 * an exception will be thrown and the test will fail. */
		CsvMatrix.readCsvFile(filename);
		
		/* Delete the temporary file. */
		File file = new File(filename);
		file.delete();
	}

}
