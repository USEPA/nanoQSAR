
import java.util.ArrayList;
import java.util.List;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.jblas.DoubleMatrix;

import static org.junit.Assert.*;

import org.junit.Test;


/**
 * @author Wilson Melendez
 *
 */
public class CsvMatrixTest {
	
	@Test
	public void testMatrices() throws FileNotFoundException, IOException
	{
		
		try
		{
			String filename = System.getProperty("user.dir") + "\\nanoQSAR.csv";
			/* Open and read CSV file. */
			CsvMatrix.readCsvFile(filename);
			/* Build X and Y matrices. */
			CsvMatrix.buildMatrices();
				
			//* Get the X and Y matrices and proceed to perform the PLS regression. */
			DoubleMatrix Xorig = CsvMatrix.getXmatrix();
			DoubleMatrix Yorig = CsvMatrix.getYmatrix();
			DoubleMatrix Yorig1 = new DoubleMatrix(Yorig.rows, 1);
			Yorig1 = Yorig.getColumn(1);  
			DoubleMatrix Bstar = CsvMatrix.performPLSR(Xorig,Yorig1);
			
			DoubleMatrix T = CsvMatrix.getTmatrix();
			DoubleMatrix Imatrix = T.transpose().mmul(T);
			//CsvMatrix.showX(Imatrix);
			double normI = Math.pow(Imatrix.norm2(), 2);
			double nrows = Imatrix.rows;
			assertEquals(nrows, normI, 1.0e-06);
			
			DoubleMatrix W = CsvMatrix.getWmatrix();
			DoubleMatrix Imatrix1 = W.transpose().mmul(W);
			//CsvMatrix.showX(Imatrix1);
			double normI1 = Math.pow(Imatrix1.norm2(), 2);
			double nrows1 = Imatrix1.rows;
			assertEquals(nrows1, normI1, 1.0e-06);
			
		}
		catch(FileNotFoundException ex)
		{
			throw ex;
		}
		catch(IOException ex)
		{
		    throw ex;
		}
	        				
	} 

	/**
	 * This test throws a file not found exception if the CSV file is not
	 * found.
	 */
	@Test(expected=FileNotFoundException.class)
	public void testReadCsvFile() throws FileNotFoundException, IOException
	{
		try
		{
			String filename = "FileDoesNotExist.csv";
			CsvMatrix.readCsvFile(filename);
		}
		catch(FileNotFoundException ex)
		{
			throw ex;
		}
		catch(IOException ex)
		{
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
		String[] values = new String[173];
		List<String[]> rowsTest = new ArrayList<String[]>();
		DoubleMatrix X = new DoubleMatrix(1,67);
		DoubleMatrix Y = new DoubleMatrix(1,2);
		
		for (int i = 0; i < 173; i++)
		{
			values[i] = "0.2";
		}
		values[7] = "2.3";
		values[11] = "98.4";
		
		for (int i = 0; i < 67; i++)
		{
			X.put(0,i,0.2);
		}
		X.put(0,0,2.3);
		X.put(0,1,98.4);
		Y.put(0,0,0.2);
		Y.put(0,1,0.2);
		
		rowsTest.add(values);
		CsvMatrix.setRows(rowsTest);
	    DoubleMatrix Xtest = new DoubleMatrix(1,67);
	    DoubleMatrix Ytest = new DoubleMatrix(1,2);
		CsvMatrix.buildMatricesContainingNulls(Xtest, Ytest);
		
		DoubleMatrix Xsame, Ysame;
		Xsame = (X.eq(Xtest));  
		Ysame = (Y.eq(Ytest));
		
		for (int i = 0; i < 67; i++)
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
		String[] values = new String[173];
		List<String[]> rowsTest = new ArrayList<String[]>();
		DoubleMatrix X = new DoubleMatrix(1,67);
		DoubleMatrix Y = new DoubleMatrix(1,2);
		
		for (int i = 0; i < 173; i++)
		{
			values[i] = "0.2";
		}
		values[7] = "2.3";
		values[11] = "98.4";
		
		for (int i = 0; i < 67; i++)
		{
			X.put(0,i,0.2);
		}
		X.put(0,0,2.3);
		X.put(0,1,98.4);
		Y.put(0,0,0.2);
		Y.put(0,1,0.2);
		
		rowsTest.add(values);
		CsvMatrix.setRows(rowsTest);	
		DoubleMatrix Xtest = new DoubleMatrix(1,67);
	    DoubleMatrix Ytest = new DoubleMatrix(1,2);
		CsvMatrix.buildMatricesWithoutNulls(Xtest, Ytest);
		
		DoubleMatrix Xsame, Ysame;
		Xsame = (X.eq(Xtest));  
		Ysame = (Y.eq(Ytest));
		
		for (int i = 0; i < 67; i++)
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
	 */
	@Test
	public void testPLSalgorithm()
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
				             		
		
		DoubleMatrix X1 = new DoubleMatrix(xMatrix);
		DoubleMatrix Y1 = new DoubleMatrix(yMatrix);
		
		DoubleMatrix Bpls1 = CsvMatrix.performPLSR(X1,Y1);
		DoubleMatrix T1 = CsvMatrix.getTmatrix();		
		DoubleMatrix U1 = CsvMatrix.getUmatrix();
		DoubleMatrix W1 = CsvMatrix.getWmatrix();
		CsvMatrix.showX(W1);
		DoubleMatrix Imatrix1 = W1.transpose().mmul(W1);
		CsvMatrix.showX(Imatrix1);
		
		/* Predict the Y values. */
		DoubleMatrix Ypredicted = CsvMatrix.predictResults(X1, Bpls1);
		
		/* Calculate R2. R^2 = || Y - Ypredicted||^2  */
		double r2 = 0.0;
		DoubleMatrix Ydiff = Y1.sub(Ypredicted);
		r2 = Math.pow(Ydiff.norm2(), 2.0);
		
		/* Verify that r2 is equal to 1.25. */
		assertEquals(1.25, r2, 1.0E-06);
	}
	
	/**
	 * This test does the following:
	 * a) Generate a 10*6 matrix X, 10*1 column Y of random numbers; 
	 * b) make column 4 to be a linear combination of columns 1 and 2; 
	 * c) Perform a PLS transformation; 
	 * d) Confirm there are 5 dimensions in the latent space.
	 *    
	 * @author Wilson Melendez
	 */
	@Test 
	public void testPLS_v1()
	{ 
		int nrows = 10;
		int ncols = 6;
		
		double a = 0.2;
		double b = 0.6;
		
		DoubleMatrix X1 = new DoubleMatrix(nrows,ncols);
		DoubleMatrix Y1 = new DoubleMatrix(nrows,1);
		
		X1 = DoubleMatrix.rand(nrows,ncols);
		Y1 = DoubleMatrix.rand(nrows,1);
		
		for (int i = 0; i < nrows; i++)
		{
			double v = a * X1.get(i, 0) + b * X1.get(i, 1);
			X1.put(i, 3, v);
		}
		
		DoubleMatrix Bpls1 = CsvMatrix.performPLSR(X1,Y1);
		DoubleMatrix T1 = CsvMatrix.getTmatrix();
		DoubleMatrix W1 = CsvMatrix.getWmatrix();
		CsvMatrix.showX(W1);
		DoubleMatrix Imatrix1 = W1.transpose().mmul(W1);
		CsvMatrix.showX(Imatrix1);
		assertTrue("Latent space dimensionality is equal to 5.", T1.columns == 5);
	}
	
	/**
	 * This test does the following:
	 * a) Generate a 10*6 matrix X, 10*1 column Y of random numbers; 
	 * b) Make one column of X equal to resultant column Y; 
	 * c) Perform a PLS transformation; 
	 * d) Confirm the latent space has only 1 dimension.
	 * @author Wilson Melendez
	 */
	//@Test 
	public void testPLS_v2()
	{ 
		int nrows = 10;
		int ncols = 6;
		
		DoubleMatrix X2 = new DoubleMatrix(nrows,ncols);
		DoubleMatrix Y2 = new DoubleMatrix(nrows,1);
		
		X2 = DoubleMatrix.rand(nrows,ncols);
		Y2 = DoubleMatrix.rand(nrows,1);
		
		for (int i = 0; i < nrows; i++)
		{
			double v = Y2.get(i, 0);
			X2.put(i, 0, v);
		}
		
		DoubleMatrix Bpls1 = CsvMatrix.performPLSR(X2,Y2);
		DoubleMatrix B2 = CsvMatrix.getBmatrix();
		CsvMatrix.showX(B2);
		
		double ssy = CsvMatrix.getSsy();
		double perCentExplained = (Math.pow(B2.get(0,0), 2.0) / ssy) * 100.0 
				+ (Math.pow(B2.get(1,1), 2.0) / ssy) * 100.0
				+  (Math.pow(B2.get(2,2), 2.0) / ssy) * 100.0;;
		assertTrue("% explained by first component is greater than 70%.", perCentExplained > 70.0);
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
	 */
	@Test
	public void testPLS_v3()
	{
		/* Number of rows */
		int n = 100;
		
		/* Number of columns */
		int m = 20;
		
		/* Vector of Coefficients  */
		DoubleMatrix B = new DoubleMatrix(m + 1);
		
		/* Matrix of results */
		DoubleMatrix Y = new DoubleMatrix(n);
		DoubleMatrix Ypred = new DoubleMatrix(n);
		//DoubleMatrix errorY = new DoubleMatrix(n);
		
		/* Matrix containing the predictors. */
		DoubleMatrix X = new DoubleMatrix(n, m);
		
		/* Augmented Matrix: (1 : X) */
		DoubleMatrix Xb = new DoubleMatrix(n, m + 1);
		
		/* Generate b0, b1, b2, ..., bm coefficients. */
		B = DoubleMatrix.rand(m + 1);
				
		/* Generate random values for X. */
		X = DoubleMatrix.rand(n, m);
		
		/* Augment the X matrix by adding a column of 1's as the
		 * first column. */
		DoubleMatrix Ones = DoubleMatrix.ones(X.rows);
		Xb = DoubleMatrix.concatHorizontally(Ones, X);
		
		/* Calculate the Y matrix using the Xb and B matrices. */
		Y = Xb.mmul(B);
		
		/* Add a small error to Y and store the new values in Ypred. */
		double errorY = Math.random() * 0.02;		
		Ypred = Y.add(errorY);
		
		/* Perform the PLS regression algorithm and return the 
		 * BPLS matrix.
		 */
		DoubleMatrix BPLSS = CsvMatrix.performPLSR(X,Ypred);		
		DoubleMatrix Ypred1 = CsvMatrix.predictResults(X, BPLSS);
		double r2 = 0.0;
		DoubleMatrix Ydiff = Y.sub(Ypred1);
		r2 = Math.pow(Ydiff.norm2(), 2.0);
		double error = Math.pow(errorY, 2.0) * n;
		assertEquals(error, r2, 1.0E-06);
		
		/* Split original data into 5 subsets that will be used for a 5-fold
		 * cross-validation analysis. */
		List<Integer> list = new ArrayList<Integer>();
		CsvMatrix.splitDataIntoSets(X, Ypred, list);
				
		/* Use the list containing the re-shuffled indices to 
		 * obtain the re-shuffled Y vector. */
		DoubleMatrix Yshuffled = new DoubleMatrix(n);
		for (int i = 0; i < n; i++)
		{
			int index = list.get(i);
			Yshuffled.put(i, Y.get(index));
		}
		
		/* Perform a 5-fold cross-validation and compute Q2. */		
		DoubleMatrix Ytilde = CsvMatrix.performFiveFoldCrossValidation();
		double Q2 = 0.0;
		DoubleMatrix Ydiff1 = Yshuffled.sub(Ytilde);
		Q2 = Math.pow(Ydiff1.norm2(), 2.0);
		assertEquals(r2, Q2, 1.0E-06);
		
	}
	
	//@Test
	public void testPlsRegression() throws IOException
	{
		double epsilon = 1.0;
		
		String filename = System.getProperty("user.dir") + "\\nanoQSAR.csv";
		/* Open and read CSV file. */
		try
		{
			CsvMatrix.readCsvFile(filename);
		}
		catch(IOException ex)
		{
			throw ex;
		}
        		
		/* Build X and Y matrices. */
		CsvMatrix.buildMatrices();
		
		/* Get the X and Y matrices and proceed to perform the PLS regression. */
		DoubleMatrix Xorig = CsvMatrix.getXmatrix();
		DoubleMatrix Yorig = CsvMatrix.getYmatrix();
		DoubleMatrix Bpls1 = CsvMatrix.performPLSR(Xorig,Yorig.getColumn(1));
		
		DoubleMatrix Ypred1 = CsvMatrix.predictResults(Xorig, Bpls1);
				
		DoubleMatrix Bpls2 = CsvMatrix.performPLSR(Xorig,Ypred1);
		
		double norm1 = Bpls1.norm2();
		double norm2 = Bpls2.norm2();
		System.out.println("norm1, norm2 = " + norm1 + " " + norm2);
		double diff = Math.abs(norm2-norm1);
		boolean cond = diff < epsilon;
		assertTrue(true);
	} 

}
