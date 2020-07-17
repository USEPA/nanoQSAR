package nonLinearTests;

import static org.junit.Assert.*;

import java.io.FileReader;
import java.util.Vector;

import org.jblas.DoubleMatrix;
import org.junit.Test;
import com.opencsv.CSVReader;
import pls_analysis.CsvMatrix;

public class GaussianClusterTest {

	@Test
	public void testGaussian() throws Exception {
		
		String[] header = null;
		Vector<String[]> trainingDataStrings = null;
		Vector<String[]> predictionDataStrings = null;
		
		trainingDataStrings = readCsvFile("LC50_training_set-2D.csv");
		predictionDataStrings = readCsvFile("LC50_prediction_set-2D.csv");
		
		header = trainingDataStrings.firstElement();
	
		DoubleMatrix xTraining = buildMatrix(trainingDataStrings);
		
//		DoubleMatrix yTraining = xTraining;
//		
//		DoubleMatrix xPrediction = new DoubleMatrix(predictionDataStrings.size(), header.length);
//		DoubleMatrix yPrediction = new DoubleMatrix(predictionDataStrings.size(), 1);
//		buildMatrices(predictionDataStrings, xPrediction, yPrediction);
//
//		/* create CsvMatrix instance */
//		CsvMatrix csvMatrix = new CsvMatrix();
//		
//		csvMatrix.setXtraining(xTraining);
//		csvMatrix.setYtraining(yTraining);
//		
//		/* Perform the PLS regression analysis. */
//		DoubleMatrix BplsStar = csvMatrix.performPLSR(xTraining, yTraining, true);
//		
//		/* write BplsStar to a csv file */
//		String[] descriptorHeader = header;
//		CsvMatrix.writeBplsStarToCsv(descriptorHeader, BplsStar, "LC50_training_set-Bpls.csv");
//		
//		/* Predict the Y values using X and BPLS*. */
//		DoubleMatrix yPredicted = CsvMatrix.predictResults(xTraining, BplsStar);
//		
//		/* Calculate the residual sum of squares also known as RESS.
//		 * The equation is : RESS = ||yTraining - yPredicted||^2  
//		 * where || is the norm of a vector.
//		 */
//		DoubleMatrix yDiff = yTraining.sub(yPredicted);
//		double ress = yDiff.dot(yDiff);
//		
//		yDiff = yTraining.sub(yTraining.mean());
//		double sumYmean = yDiff.dot(yDiff);
//
//		/* Calculate R2 as the following: 
//		 * R2 = 1.0 - (||yTraining- yPredicted||^2 / ||yTraining - meanY||^2) 
//		 */
//
//		double R2 = 1.0 - (ress / sumYmean);
//		assertTrue("R2 = "+R2+", numOfDeflations = "+csvMatrix.getNumOfDeflations(), R2 > 0.30);
//		
//		System.out.println("R2 = "+R2+", numOfDeflations = "+csvMatrix.getNumOfDeflations());
//		
//		/* Predict the Y values using X and BPLS*. */
//		yPredicted = CsvMatrix.predictResults(xPrediction, BplsStar);
//		
//		/* Calculate the predicted residual sum of squares also known as PRESS.
//		 * The equation is : PRESS = ||yPrediction - yPredicted||^2  
//		 * where || is the norm of a vector.
//		 */
//		yDiff = yPrediction.sub(yPredicted);
//		
////		System.out.println("i\t"+"obs\t"+"pred\t\t\t"+"diff");
////		for (int i=0; i<yPredicted.length-1; i++) {
////			System.out.println(i+"\t"+yPrediction.get(i)+"\t"+yPredicted.get(i)+"\t"+yDiff.get(i));
////		}
//		
//		double press = yDiff.dot(yDiff);
//		
//		yDiff = yPrediction.sub(yPrediction.mean());
//		sumYmean = yDiff.dot(yDiff);
//
//		/* Calculate Q2 as the following: 
//		 * Q2 = 1.0 - (||yPrediction - yPredicted||^2 / ||yPrediction - meanY||^2) 
//		 */
//
//		double Q2 = 1.0 - (press / sumYmean);
//		assertTrue("Q2 = "+Q2+", numOfDeflations = "+csvMatrix.getNumOfDeflations(), Q2 > 0.30);
//		
//		System.out.println("Q2 = "+Q2+", numOfDeflations = "+csvMatrix.getNumOfDeflations());
//		
	}
	
	private void buildMatrices(Vector<String[]> dataStrings, DoubleMatrix x, DoubleMatrix y) {
		
		String elem = null;
		String[] row = null;
		
		for (int j=1; j<dataStrings.size(); j++) {
			row = dataStrings.get(j);
			elem = row[1];
			y.put(j-1, 0, Double.valueOf(elem).doubleValue());
			for (int i=2; i<row.length; i++) {
				elem = row[i];
				x.put(j-1, i-2, Double.valueOf(elem).doubleValue());
			}
		}
		
	}
	
	private DoubleMatrix buildMatrix(Vector<String[]> dataStrings) {
		
		String elem = null;
		String[] row = null;
		
		int n = dataStrings.size()-1;
		int m = dataStrings.firstElement().length-1;
		DoubleMatrix matrix = new DoubleMatrix(n,m);
		
		for (int j=0; j<matrix.rows; j++) {
			row = dataStrings.get(j+1);
			for (int i=0; i<matrix.columns; i++) {
				elem = row[i+1];
				matrix.put(j, i, Double.valueOf(elem).doubleValue());
			}
		}
		
		return matrix;
		
	}
	
	private Vector<String[]> readCsvFile(String filename) throws Exception {

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
