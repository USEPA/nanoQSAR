
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.jblas.DoubleMatrix;


/**
 * This program reads the contents of a CSV file, builds the X and Y matrices needed by the
 * PLS regression algorithm, writes the vector with regression weights to a CSV file, and performs
 * a 5-fold cross-validation analysis.
 * @author Wilson Melendez
 *
 */


public class Main 
{	

	/**
	 * This is the main method.
	 * @param args
	 * @author Wilson Melendez
	 */
	public static void main(String[] args) 
	{
		String filename = null;
		
		if (args == null || args.length == 0)  // Use default CSV file.
		{
			filename = System.getProperty("user.dir") + "\\nanoQSAR.csv";
			System.out.println("Using default CSV file: " + filename);
		}
		else  // Use command-line specified CSV file.
		{
			filename = args[0].trim();
			System.out.println("Using command-line entered CSV file: " + args[0]);
		}
		
		/* Initialize logger. */
		try
		{
			/* Initialize log file information. Throw IOException and/or SecurityException 
			 * if creation of file handler was not successful. */
			LoggerInfo.init(); 
		}
		catch(IOException | SecurityException ex)
		{
			System.out.println("Creation of file handler for log file failed.");
			LoggerInfo.close();
			ex.printStackTrace();  // This is the only case when the stack trace is sent to the console.
			System.exit(1);  // End the execution of the program.
		}
		
		/* Get the logger. */
		Logger logger = CsvMatrix.getLogger(); 
		
		try
		{		
			/* Open and read CSV file. */
            CsvMatrix.readCsvFile(filename);
            
            /* Select columns with numeric data in CSV file. */
            CsvMatrix.selectNumericColumns();
			
			/* Build X and Y matrices. */
			CsvMatrix.buildMatrices();
			
			/* Get the X and Y matrices.  Use a single column for 
			 * the Y matrix. */
			DoubleMatrix Xorig = CsvMatrix.getXmatrix();
			DoubleMatrix Yorig = CsvMatrix.getYmatrix();
			DoubleMatrix Yorig1 = new DoubleMatrix(Yorig.rows, 1);
			Yorig1 = Yorig.getColumn(1);  // Use LC50 as the effect variable.
			
			/* Perform PLS regression and return the BPLS* vector. */
			DoubleMatrix BplsS = CsvMatrix.performPLSR(Xorig,Yorig1);  
			
			/* Write BPLS* vector to a CSV file. */
			CsvMatrix.writeBplsStarToCsv(BplsS);
			
			/* Predict the Y values. */
			DoubleMatrix Ypredicted = CsvMatrix.predictResults(Xorig, BplsS);
			
			/* Calculate average of observed values. */
			DescriptiveStatistics stats = new DescriptiveStatistics();
		    for (int i = 0; i < Yorig1.rows; i++)
		    {
				stats.addValue(Yorig1.get(i));
			}
			double meanY = stats.getMean();
			
			
			
			/* Calculate R2. R^2 = || Y - Ypredicted||^2  */
			double R2 = 0.0;
			DoubleMatrix Ydiff = Yorig1.sub(Ypredicted);
			double sum1 = Math.pow(Ydiff.norm2(), 2.0);
			double sum2 = Math.pow(Yorig1.sub(meanY).norm2(), 2.0);
			R2 = 1.0 - (sum1 / sum2);
			
			/* Store R2 in the logger file. */
			logger.info("R2 = " + R2);
			
			/* Split original data into 5 subsets that will be used for a 5-fold
			 * cross-validation analysis. */
			List<Integer> list = new ArrayList<Integer>();
			CsvMatrix.splitDataIntoSets(Xorig, Yorig1, list);
			
			/* Use the list containing the re-shuffled indices to 
			 * obtain the re-shuffled Y vector. */
			DoubleMatrix Yshuffled = new DoubleMatrix(Yorig1.rows);
			for (int i = 0; i < Yorig1.rows; i++)
			{
				int index = list.get(i);
				Yshuffled.put(i, Yorig1.get(index));
			}
			
			/* Perform a 5-fold cross-validation and compute Q2. */
			DoubleMatrix Ytilde = CsvMatrix.performFiveFoldCrossValidation();
			double Q2 = 0.0;
			DoubleMatrix Ydiff1 = Yshuffled.sub(Ytilde);
			sum1 = Math.pow(Ydiff1.norm2(), 2.0);
			Q2 = 1.0 - (sum1 / sum2);
			
			/* Store Q2 in the logger file. */
			logger.info("Q2 = " + Q2);
			
			/* Close logger file. */
			LoggerInfo.close();
		}
		catch(FileNotFoundException ex)
		{
			logger.severe("Exception was thrown: ending the execution of the program. \n" + ex);	
		}
		catch(IOException ex)
		{
			logger.severe("Exception was thrown: ending the execution of the program. \n" + ex);	
		}		
		
	}

}
