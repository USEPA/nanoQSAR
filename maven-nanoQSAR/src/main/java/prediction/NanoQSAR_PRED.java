/**
 * 
 */
package prediction;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.jblas.DoubleMatrix;

import datamine.DBUtil;
import pls_analysis.CsvMatrix;
import nanoQSAR.NanoToxExps;

/**
 * @author Wmelende
 *
 */
public class NanoQSAR_PRED {
	
	/* Default filenames */
	static String propFilename = System.getProperty("user.dir") + "\\nanoQSAR.properties";
	static String logFilename = System.getProperty("user.dir") + "\\nanoQSAR.log";
	
	static String helpString = "User options:\njava -jar nanoQSAR_Test -h\njava -jar nanoQSAR_Test\njava -jar nanoQSAR_Test propFilename\n";

	/* Create an object of type Logger so we can log error or warning messages. */
	private static Logger LOGGER =  Logger.getLogger("nanoQSAR");
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		try	
		{	
			if (testArgs(args)) {

				NanoQSAR_PRED nanoQSAR_PRED = new NanoQSAR_PRED();

			}
		}
		catch (Exception ex) 
		{
			ex.printStackTrace();
		}
				
	}
	
	public static void main() {
		
		try	
		{	
			NanoQSAR_PRED nanoQSAR_PRED = new NanoQSAR_PRED();
		}
		catch (Exception ex) 
		{
			ex.printStackTrace();
		}		
		
	}

	private static boolean testArgs(String[] args) throws Exception {
		
		if (args == null || args.length == 0) { // Use default properties file.


		} else if (args.length == 1 && args[0].trim().matches("-h")) { // respond with user options

			System.out.print(helpString);
			return false;

		} else if (args.length == 1) { // Using command-line entered properties file

			propFilename = args[0].trim();

		} else { // Something is wrong

			throw new Exception("Invalid options are used");
			
		}
		
		return true;
		
	}
	
	public NanoQSAR_PRED() throws Exception
	{

			startLogger();

			predict();
				
	}
	
	private void startLogger() throws IOException {
		
		/* Initialize log file information. Throw IOException and/or SecurityException 
		 * if creation of file handler was not successful. */
		LOGGER.setLevel(Level.INFO);
		if (LOGGER.getUseParentHandlers()) {
			LOGGER.addHandler(new FileHandler(logFilename));
			LOGGER.setUseParentHandlers(false);  // This will prevent LOGGER from printing messages to the console.
		}
		
	}
	
	private void predict() throws Exception {
		
		/* Input filenames stored in properties file. */
		DBUtil dbUtil = new DBUtil();
		dbUtil.loadProperties(propFilename);	

		String testFilename = dbUtil.getTestFileName();
		String predFilename = dbUtil.getPredictionsFileName();

		Predictor predictor = new Predictor();	

		/* Read CSV file with test data that had been mined from database. */
		NanoToxExps nanoToxExps = new NanoToxExps(testFilename);			

		/* Select the columns that will be used to build X and Y matrices from nanoToxExps. */
		nanoToxExps.selectContinuousColumns();
		nanoToxExps.selectResultColumns();

		/* Build Matrices from experimental data. */
		CsvMatrix csvm = new CsvMatrix();
		csvm.buildMatrices(nanoToxExps);

		/* Get a copy of the X matrix and replace null entries with averages or zeroes. */			
		DoubleMatrix xmatrix = csvm.getxMatrix();
		predictor.setTestXmatrix(xmatrix);
		predictor.replaceNullsXmatrix();

		/* Read the beta coefficients from CSV file and store them in a matrix. */
		int xcol = xmatrix.columns;
		int ycol = csvm.getyMatrix().columns;
		String betaFilename = dbUtil.getBplsFileName();
		predictor.readCsvFile(betaFilename, xcol, ycol);

		/* Get header of X-matrix */
		String[] xMatrixHeader = nanoToxExps.getDescriptorHeader();

		/* Get header of BPLS matrix */
		String[] bplsHeader = predictor.getHeader();			

		/* Verify that the headers of the X and Beta-Coefficients matrices match. */
		/* If a mismatch in the headers is found, an exception will be thrown. */
		predictor.verifyOrderHeaders(xMatrixHeader, bplsHeader);

		/* Get a copy of the beta coefficients matrix. */
		DoubleMatrix bMatrix = predictor.getBetaMatrix();			

		/* Get a copy of the processed X matrix (with replaced nulls).  */
		DoubleMatrix testXmatrix = predictor.getTestXmatrix();

		/* Predict results using the X matrix and beta coefficients. */
		DoubleMatrix yPred = CsvMatrix.predictResults(testXmatrix, bMatrix);

		/* Get copy of the original Y matrix  */
		DoubleMatrix yMatrix = csvm.getyMatrix();

		/* Calculate R2: use the original Y matrix and predicted Y matrix. */
		double[] r2 = predictor.calculateR2(yMatrix, yPred);

		/* Store R2 in the logger file. */
		LOGGER.info("R2[0] = " + r2[0] + ", R2[1] = "+r2[1]);
		
		/* Add test results to original CSV file format. */
		nanoToxExps.writeCsvFileWithPredictions(predFilename, yPred);
		
	}

}
