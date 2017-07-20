package pls_analysis;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.jblas.DoubleMatrix;

import nanoQSAR.NanoMaterial;
import nanoQSAR.NanoMaterials;


/**
 * This program reads the contents of a CSV file, builds the X and Y matrices needed by the
 * PLS regression algorithm, writes the vector with regression weights to a CSV file, and performs
 * a 5-fold cross-validation analysis.
 * @author Wilson Melendez
 *
 */


public class PlsrAnalyzer 
{	
	/* Need this line to allow logging of error messages */
	private static Logger LOGGER = Logger.getLogger("nanoQSAR");
	static String logFilename = System.getProperty("user.dir") + "\\nanoQSAR.log";
	
	/**
	 * This is the main method.
	 * @param args
	 * @author Wilson Melendez
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception 
	{
		String filename = null;
		
		if (args == null || args.length == 0)  // Use default CSV file.
		{
			filename = System.getProperty("user.dir") + "\\nanoQSAR.csv";
		}
		else  // Use command-line specified CSV file.
		{
			filename = args[0].trim();
		}
		
		/* Initialize logger. */
		try
		{
			/* Initialize log file information. Throw IOException and/or SecurityException 
			 * if creation of file handler was not successful. */
			LOGGER.addHandler(new FileHandler(logFilename));
			LOGGER.setLevel(Level.INFO);

		}
		catch(Exception ex)
		{
			LOGGER.severe("Creation of file handler for log file failed.");
			ex.printStackTrace();  // This is the only case when the stack trace is sent to the console.
			System.exit(1);  // End the execution of the program.
		} 
		
		try
		{
			/* create nanoMaterials from filename */
			NanoMaterials nanoMaterials = new NanoMaterials(filename);
			
			/* Build X and Y matrices from nanoMaterials. */
			CsvMatrix csvMatrix = new CsvMatrix(nanoMaterials);
			
			/* Get the X and Y matrices.  Use a single column for 
			 * the Y matrix. */
			DoubleMatrix Xorig = csvMatrix.getXmatrix();
			DoubleMatrix Yorig = csvMatrix.getYmatrix();
			
			DoubleMatrix Yorig1 = Yorig.getColumn(1);	// Use LC50 as the effect variable.
			
			/* Calculate average of observed values. */
			double meanY = Yorig1.mean();
			
			/* Calculate denominator */
			DoubleMatrix Ydiff = Yorig1.sub(meanY);
			double sum2 = Ydiff.dot(Ydiff);
			
			/* Perform PLS regression and return the BPLS* vector. */
			DoubleMatrix BplsS = csvMatrix.performPLSR(Xorig,Yorig1);  
			
			/* Write BPLS* vector to a CSV file. */
			csvMatrix.writeBplsStarToCsv(BplsS);			
			
			/* Predict the Y values. */
			DoubleMatrix Ypredicted = csvMatrix.predictResults(Xorig, BplsS);
//			for (int i=0; i<Yorig1.rows; i++) {
//				System.out.println("i = "+i+": orig = "+Yorig1.get(i)+", pred = "+Ypredicted.get(i));
//			}
			
			/* Calculate R2 = 1.0 - ||Yobs-Ypred||^2 / ||Yobs-Ymean||^2 */
			Ydiff = Yorig1.sub(Ypredicted);
			double sum1 = Ydiff.dot(Ydiff);
			double R2 = 1.0 - (sum1 / sum2);
			
			/* Store R2 in the logger file. */
			LOGGER.info("R2 = " + R2);
			
			/* Perform 5-fold cross-validation prediction. */
			DoubleMatrix Ytilde = csvMatrix.performFiveFoldCrossValidation(Xorig, Yorig1);
//			for (int i=0; i<Ytilde.rows; i++) {
//				System.out.println("i = "+i+": orig = "+Yorig1.get(i)+", pred = "+Ytilde.get(i));
//			}
			
			/* Calculate Q2 = 1.0 - ||Yobs-Ytilde||^2 / ||Yobs-Ymean||^2 */
			Ydiff = Yorig1.sub(Ytilde);
			sum1 = Ydiff.dot(Ydiff);
			double Q2 = 1.0 - (sum1 / sum2);
			
			/* Store Q2 in the logger file. */
			LOGGER.info("Q2 = " + Q2);
			
		}
		catch(FileNotFoundException ex)
		{
			LOGGER.severe("Exception was thrown: ending the execution of the program. \n" + ex);	
		}
		catch(IOException ex)
		{
			LOGGER.severe("Exception was thrown: ending the execution of the program. \n" + ex);	
		}		
		
	}

}
