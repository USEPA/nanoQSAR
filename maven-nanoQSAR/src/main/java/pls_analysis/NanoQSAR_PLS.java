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

import nanoQSAR.NanoToxExp;
import nanoQSAR.NanoToxExps;


/**
 * This program reads the contents of a CSV file, builds the X and Y matrices needed by the
 * PLS regression algorithm, writes the vector with regression weights to a CSV file, and performs
 * a 5-fold cross-validation analysis.
 * @author Wilson Melendez
 *
 */


public class NanoQSAR_PLS 
{	
	/* Need this line to allow logging of error messages */
	private static Logger LOGGER = Logger.getLogger("nanoQSAR");
	static String logFilename = System.getProperty("user.dir") + "\\nanoQSAR.log";
	static String filename_BPLS = System.getProperty("user.dir") + "\\nanoQSAR_BPLS.csv";
	
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
			LOGGER.setUseParentHandlers(false);  // This will prevent LOGGER from printing messages to the console.
		}
		catch(Exception ex)
		{
			LOGGER.severe("Creation of file handler for log file failed.");
			ex.printStackTrace();  // This is the only case when the stack trace is sent to the console.
			System.exit(1);  // End the execution of the program.
		} 
		
		try
		{
			/* create nanoToxExps from filename */
			NanoToxExps nanoToxExps = new NanoToxExps(filename);
			
			/* Build X and Y matrices from nanoToxExps. */
			CsvMatrix csvMatrix = new CsvMatrix(nanoToxExps);
			
			/* Get the X and Y matrices.  Use a single column for 
			 * the Y matrix. */
			DoubleMatrix Xorig = csvMatrix.getXmatrix();
			DoubleMatrix Yorig = csvMatrix.getYmatrix();
			
			csvMatrix.setXtraining(Xorig);
			csvMatrix.setYtraining(Yorig);
			
//			Yorig = Yorig.getColumn(0);	// UseViability as the effect variable.
//			Yorig = Yorig.getColumn(1);	// Use LC50 as the effect variable.
			
			/* Perform PLS regression and return the BPLS* vector. */
			DoubleMatrix BplsS = csvMatrix.performPLSR(Xorig,Yorig,true); 
			
			/* Get Descriptor Header information */
			String[] descriptorHeader = nanoToxExps.getDescriptorHeader();
			
			/* Get CategoryDescriptor Header information */
			String[] categoryDescriptorHeader = nanoToxExps.getCategoryDescriptorHeader();
			
			if (categoryDescriptorHeader != null) {
				int i1 = descriptorHeader.length;
				int i2 = categoryDescriptorHeader.length;
				String[] header = new String[i1+i2];
				for (int i=0; i<i1; i++) header[i] = descriptorHeader[i];
				for (int i=0; i<i2; i++) header[i+i1] = categoryDescriptorHeader[i];
				descriptorHeader = header;
			}
			
			if (BplsS.rows != descriptorHeader.length + 1) {
				throw new Error("The matrix size is incorrect");
			}
			
			/* Write BPLS* vector to a CSV file. */
			CsvMatrix.writeBplsStarToCsv(descriptorHeader, BplsS, filename_BPLS);			
			
			/* Predict the Y values. */
			DoubleMatrix Ypredicted = CsvMatrix.predictResults(Xorig, BplsS);
			
			DoubleMatrix Ydiff = null;
			double[] r2 = new double[Yorig.columns];
			
			/* Calculate R2 = 1.0 - ||Yobs-Ypred||^2 / ||Yobs-Ymean||^2 for each column */
			
			DoubleMatrix yDiffMean = Yorig.subRowVector(Yorig.columnMeans());
			DoubleMatrix yDiffPred = Yorig.sub(Ypredicted);
			
			for (int jcol=0; jcol<Yorig.columns; jcol++) {
				
				Ydiff = yDiffMean.getColumn(jcol);
				double var = Ydiff.dot(Ydiff);

				Ydiff = yDiffPred.getColumn(jcol);
				double press = Ydiff.dot(Ydiff);

				if (var==0) r2[jcol] = 1.0;
				else r2[jcol] = 1.0-(press/var);
			}
			
//			/* Calculate R2 = 1.0 - ||Yobs-Ypred||^2 / ||Yobs-Ymean||^2 */
//			DoubleMatrix Ydiff = Yorig.subRowVector(Yorig.columnMeans());
//			double sum2 = CsvMatrix.sumOfSquares(Ydiff);
//			Ydiff = Yorig.sub(Ypredicted);
//			double sum1= CsvMatrix.sumOfSquares(Ydiff);
//			double R2 = 1.0 - (sum1 / sum2);
			
			/* Store R2 in the logger file. */
			LOGGER.info("R2[0] = " + r2[0]+", numDeflations = "+csvMatrix.getNumOfDeflations());
//			LOGGER.info("R2[1] = " + r2[1]+", numDeflations = "+csvMatrix.getNumOfDeflations());
			
			/* Perform 5-fold cross-validation prediction. */
			int nfolds = 5;
			DoubleMatrix Ytilde = csvMatrix.performMultiFoldCrossValidation(nfolds, Xorig, Yorig);
			double[] q2Avg = csvMatrix.getQ2avg();
			int numDeflationsAvg = csvMatrix.getNumDeflationsAvg();
			
			/* Store Q2 in the logger file. */
			LOGGER.info("Q2[0]avg = " + q2Avg[0]+", nfolds = "+nfolds+", numDeflationsAvg = "+numDeflationsAvg);
//			LOGGER.info("Q2[1]avg = " + q2Avg[1]+", nfolds = "+nfolds+", numDeflationsAvg = "+numDeflationsAvg);
			
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
