package plsrTests;

import static org.junit.Assert.*;

import java.io.FileReader;
import java.util.Vector;

import org.jblas.DoubleMatrix;
import org.junit.Test;
import com.opencsv.CSVReader;
import pls_analysis.CsvMatrix;

public class ToddsTEST {

	private String[] header = null;
	Vector<String[]> trainingDataStrings = null;
	Vector<String[]> predictionDataStrings = null;

	@Test
	public void testPlsUsingTESTdata() throws Exception {
				
		trainingDataStrings = readCsvFile("LC50_training_set-2D.csv");
		predictionDataStrings = readCsvFile("LC50_prediction_set-2D.csv");
		
		header = trainingDataStrings.firstElement();
		
		DoubleMatrix xTraining = new DoubleMatrix(trainingDataStrings.size(), trainingDataStrings.get(0).length);;
		DoubleMatrix yTraining = new DoubleMatrix(trainingDataStrings.size(), 1);
		buildMatrices(trainingDataStrings, xTraining, yTraining);
		
		DoubleMatrix xPrediction = new DoubleMatrix(predictionDataStrings.size(), predictionDataStrings.get(0).length);;
		DoubleMatrix yPrediction = new DoubleMatrix(predictionDataStrings.size(), 1);
		buildMatrices(predictionDataStrings, xPrediction, yPrediction);

		/* create CsvMatrix instance */
		CsvMatrix csvMatrix = new CsvMatrix();
		
		csvMatrix.setXtesting(xPrediction);
		csvMatrix.setYtesting(yPrediction);
		
		/* Perform the PLS regression analysis. */
		DoubleMatrix BplsStar = csvMatrix.performPLSR(xTraining, yTraining, true);	
		
		/* Predict the Y values using X and BPLS*. */
		DoubleMatrix yPredicted = CsvMatrix.predictResults(xPrediction, BplsStar);
		
		/* Calculate the residual sum of squares also known as RESS.
		 * The equation is : RESS = ||yPrediction - yPredicted||^2  
		 * where || is the norm of a vector.
		 */
		DoubleMatrix yDiff = yPrediction.sub(yPredicted);
		double ress = yDiff.dot(yDiff);
		
		yDiff = yPrediction.sub(yPrediction.mean());
		double sumYmean = yDiff.dot(yDiff);

		/* Calculate R2 as the following: 
		 * R2 = 1.0 - (||Y- Ypredicted||^2 / ||Y - meanY||^2) 
		 */

		double R2 = 1.0 - (ress / sumYmean);
		assertTrue("R2 = "+R2+", numOfDeflations = "+csvMatrix.getNumOfDeflations(), R2 > 0.6);
		
//		/* Perform a 5-fold cross-validation and compute Q2. */	
//		DoubleMatrix Ytilde = csvMatrix.performMultiFoldCrossValidation(5, X, Y);
//		double sum1 = 0.0;
//		double sum2 = 0.0;
//		DoubleMatrix YdiffMean1 = Y.sub(meanY);
//		DoubleMatrix YdiffTilde = Y.sub(Ytilde);
//		sum1 = Math.pow(YdiffTilde.norm2(), 2.0);
//		sum2 = Math.pow(YdiffMean1.norm2(), 2.0);
//		
//		sumYmean1 = Math.pow(YdiffMean1.getColumn(0).norm2(), 2.0);
//		sumYmean2 = Math.pow(YdiffMean1.getColumn(1).norm2(), 2.0);
//		sumYmean3 = Math.pow(YdiffMean1.getColumn(2).norm2(), 2.0);
//		ress1 = Math.pow(YdiffTilde.getColumn(0).norm2(), 2.0);
//		ress2 = Math.pow(YdiffTilde.getColumn(1).norm2(), 2.0);
//		ress3 = Math.pow(YdiffTilde.getColumn(2).norm2(), 2.0);
//		
//		double[] Q2avg = csvMatrix.getQ2avg();
//		
//		double Q2 = 1.0 - sum1/sum2;
//		assertTrue("Q2 < R2", Q2 < R2);
//		double Q2Col1 = 1.0 - (ress1 / sumYmean1);
//		assertTrue("Q2avg[0] > 0.9", Q2avg[0] > 0.9);
//		double Q2Col2 = 1.0 - (ress2 / sumYmean2);
//		assertTrue("Q2avg[1] > 0.9", Q2avg[1] > 0.9);
//		double Q2Col3 = 1.0 - (ress3 / sumYmean3);
//		assertTrue("Q2avg[2] < 0.0", Q2avg[2] > 0.9);
		
	}

	private void buildMatrices(Vector<String[]> dataStrings, DoubleMatrix x, DoubleMatrix y) {
		
		String elem = null;
		
		for (int j=1; j<dataStrings.size(); j++) {
			elem = dataStrings.get(j)[1];
			y.put(j-1, 0, Double.valueOf(elem).doubleValue());
			for (int i=2; i<header.length; i++) {
				elem = dataStrings.get(j)[i];
				x.put(j-1, i-2, Double.valueOf(elem).doubleValue());
			}
		}
		
	}
	
	public Vector<String[]> readCsvFile(String filename) throws Exception {

		CSVReader csvReader = null;
		Vector<String[]> records = new Vector<String[]>();
		
		try	{
			
			/* create a new CSVReader for the fileName */
			csvReader = new CSVReader(new FileReader(filename));
			
			String[] line = null;
			/* Loop over lines in the csv file */
			while ((line = csvReader.readNext())!=null) {
				
				records.add(line);
				
			}
			
			/* Close the writer. */
			csvReader.close();
			
		} catch(Exception ex)	{
				
			throw ex;
			
		}
		
		return records;

	}
	
}
