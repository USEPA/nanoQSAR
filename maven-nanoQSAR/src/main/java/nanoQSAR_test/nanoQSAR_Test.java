/**
 * 
 */
package nanoQSAR_test;

import java.io.File;
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
	private static Logger LOGGER =  Logger.getLogger("nanoQSAR_TEST");
	
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
			LOGGER.addHandler(new FileHandler(logFilename));
			LOGGER.setLevel(Level.INFO);
		
		    /* Input filenames stored in properties file. */
			Utilities.loadProperties(propFilename);	
			
		    /* Make a copy of nanoQSAR.csv. */
			String originalFilename = Utilities.getCsvFileName();
			String testFilename = Utilities.getPredictionsFileName();
		    File source = new File(originalFilename);
		    File dest = new File(testFilename);
		
		    PredictorsBetaMatrices prdb = new PredictorsBetaMatrices();	
		    prdb.copyFiles(source, dest);		
		
			/* Read CSV file with data that had been mined from database. */
			NanoToxExps nanoToxExps = new NanoToxExps(testFilename);			
			
			/* Select the columns that will be used to build X and Y matrices from nanoToxExps. */
			nanoToxExps.selectContinuousColumns();
			nanoToxExps.selectResultColumns();
			
			/* Build Matrices from experimental data. */
			CsvMatrix csvm = new CsvMatrix();
			csvm.buildMatrices(nanoToxExps);
			
			/* Get a copy of the X matrix and replace null entries with averages or zeroes. */			
			DoubleMatrix xmatrix = CsvMatrix.getxMatrix();
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
			
			/* Verify that the headers of the X and Beta-Coefficients matrices match. */
			/* If a mismatch in the headers is found, an exception will be thrown. */
			prdb.verifyOrderHeaders(xMatrixHeader, bplsHeader);
			
			/* Get a copy of the beta coefficients matrix. */
			DoubleMatrix bMatrix = prdb.getBetaMatrix();			
			
			/* Get a copy of the processed X matrix (with replaced nulls).  */
			DoubleMatrix testXmatrix = prdb.getTestXmatrix();
			
			/* Predict results using the X matrix and beta coefficients. */
			DoubleMatrix yPred = CsvMatrix.predictResults(testXmatrix, bMatrix);
			
			/* Build test header  */
			prdb.buildTestHeader(xMatrixHeader, yMatrixHeadr);
			
			/* Get copy of the original Y matrix  */
			DoubleMatrix yMatrix = CsvMatrix.getyMatrix();
		
			/* Calculate R2: use the original Y matrix and predicted Y matrix. */
			double[] r2 = prdb.calculateR2(yMatrix, yPred);
			
			/* Store R2 in the logger file. */
			LOGGER.info("R2[0] = " + r2[0]);
			
			/* Build results matrix that will be written to output. */
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
