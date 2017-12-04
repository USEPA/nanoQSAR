/**
 * 
 */
package nanoQSAR_test;

import java.io.File;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.jblas.DoubleMatrix;

import pls_analysis.CsvMatrix;
import nanoQSAR.NanoToxExps;

/**
 * @author Wmelende
 *
 */
public class nanoQSAR_Test {
	
	/* Default filenames */
	static String propFilename = System.getProperty("user.dir") + "\\nanoQSAR.properties";
	static String logFilename = System.getProperty("user.dir") + "\\nanoQSAR_TEST.log";
	
	static String helpString = "User options:\njava -jar nanoQSAR_Test -h\njava -jar nanoQSAR_Test\njava -jar nanoQSAR_Test propFilename\n";

	/* Create an object of type Logger so we can log error or warning messages. */
	protected static Logger LOGGER =  Logger.getLogger("nanoQSAR_TEST", null);
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		try	
		{	
			if (args == null || args.length == 0) { // Use default properties file.


			} else if (args.length == 1 && args[0].trim().matches("-h")) { // respond with user options

				System.out.print(helpString);
				return;

			} else if (args.length == 1) { // Using command-line entered properties file

				propFilename = args[0].trim();

			} else { // Something is wrong

				throw new Exception("Invalid options are used");
				
			}

		    /* Initialize log file information. Throw IOException and/or SecurityException 
		     * if creation of file handler was not successful. */
		    LOGGER.setLevel(Level.INFO);
		    if (!LOGGER.getUseParentHandlers()) 
		    {
			   
		        LOGGER.addHandler(new FileHandler(logFilename));		
			    LOGGER.addHandler(new ConsoleHandler());
		    }
		
		    /* Input database connection information and name of output file. */
			Utilities.loadProperties(propFilename);	
			
		    /* Make a copy of nanoQSAR.csv. */
			String originalFilename = Utilities.getCsvFileName();
			String testFilename = Utilities.getPredictionsFileName();
		    File source = new File(originalFilename);
		    File dest = new File(testFilename);
		
		    PredictorsBetaMatrices comp = new PredictorsBetaMatrices();	
		    comp.copyFiles(source, dest);		
		
			/* Read CSV file. */
			NanoToxExps nanoToxExps = new NanoToxExps(testFilename);			
			
			/* Select the columns that will be used to build X and Y matrices from nanoToxExps. */
			nanoToxExps.selectContinuousColumns();
			nanoToxExps.selectResultColumns();
			
			/* Build Matrices from experimental data. */
			CsvMatrix csvm = new CsvMatrix();
			csvm.buildMatrices(nanoToxExps);
			
			/* Get a copy of the X matrix and replace null entries with averages or zeroes. */			
			DoubleMatrix xmatrix = CsvMatrix.getxMatrix();
			PredictorsBetaMatrices prdb = new PredictorsBetaMatrices();
			prdb.setTestXmatrix(xmatrix);
			prdb.replaceNullsXmatrix();
			
			/* Read the beta coefficients from CSV file and store them in a matrix. */
			int xcol = xmatrix.columns;
			int ycol = CsvMatrix.getyMatrix().columns;
			String betaFilename = Utilities.getBplsFileName();
			prdb.readCsvFile(betaFilename, xcol, ycol);
			
			/* Get header of X-matrix */
			String[] xMatrixHeader = nanoToxExps.getDescriptorHeader();
		
			/* Get header of Y-Matrix */
			String[] yMatrixHeadr = nanoToxExps.getResultHeader();
			
			/* Get header of BPLS matrix */
			String[] bplsHeader = prdb.getHeader();			
			
			/* Get a copy of the beta coefficients matrix. */
			DoubleMatrix bMatrix = prdb.getBetaMatrix();
			
			/* Verify that the headers of the X and Beta-Coefficients matrices match. */
			/* If a mismatch in the headers is found, an exception will be thrown. */
			prdb.verifyOrderHeaders(xMatrixHeader, bplsHeader);
			
			/* Get a copy of the processed X matrix.  */
			DoubleMatrix testXmatrix = prdb.getTestXmatrix();
			
			/* Predict results using the X matrix and beta coefficients. */
			DoubleMatrix yPred = CsvMatrix.predictResults(testXmatrix, bMatrix);
			
			/* Build test header  */
			prdb.buildTestHeader(xMatrixHeader, yMatrixHeadr);
			
			/* Get copy of the original Y matrix  */
			DoubleMatrix yMatrix = CsvMatrix.getyMatrix();
			
			
			/* Perform 5-fold cross-validation prediction. */
			// int nfolds = 5;
			// DoubleMatrix Ytilde = csvm.performMultiFoldCrossValidation(nfolds,testXmatrix, yPred);
			// double[] q2Avg = csvm.getQ2avg();
			// int numDeflationsAvg = csvm.getNumDeflationsAvg();
			
			/* Build results matrix */
			prdb.buildResultsMatrix(testXmatrix, yMatrix, yPred);
			
			/* Write test results to CSV file. */
			prdb.writeResultsToCsv(testFilename);
		}
		catch (Exception ex) 
		{
			ex.printStackTrace();
		}
				
		
	}

}
