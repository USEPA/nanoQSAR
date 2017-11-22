/**
 * 
 */
package nanoQSAR_test;

import java.io.File;
import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.jblas.DoubleMatrix;

import pls_analysis.CsvMatrix;
import datamine.DBUtil;
import nanoQSAR.NanoToxExps;

/**
 * @author Wmelende
 *
 */
public class nanoQSAR_Test {
	
	/* Default filenames */
	static String originalFilename = System.getProperty("user.dir") + "\\nanoQSAR.csv";
	static String testFilename = System.getProperty("user.dir") + "\\nanoQSAR_TEST.csv";
	static String betaFilename = System.getProperty("user.dir") + "\\nanoQSAR_BPLS.csv";
	static String logFilename = System.getProperty("user.dir") + "\\nanoQSAR_TEST.log";
	

	/* Create an object of type Logger so we can log error or warning messages. */
	protected static Logger LOGGER =  Logger.getLogger("nanoQSAR_TEST", null);
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		/* Initialize log file information. Throw IOException and/or SecurityException 
		 * if creation of file handler was not successful. */
		LOGGER.setLevel(Level.INFO);
		if (!LOGGER.getUseParentHandlers()) {
			try {
				LOGGER.addHandler(new FileHandler(logFilename));
			} catch (SecurityException | IOException e) {
				e.printStackTrace();
			}
			
			LOGGER.addHandler(new ConsoleHandler());
		}
		
		/* Make a copy of nanoQSAR.csv. */
		File source = new File(originalFilename);
		File dest = new File(testFilename);
		
		PredictorsBetaMatrices comp = new PredictorsBetaMatrices();
		try {
			comp.copyFiles(source, dest);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		try 
		{
			/* Read CSV file. */
			NanoToxExps nanoToxExps = new NanoToxExps(testFilename);
			
			
			
			/* Build X and Y matrices from nanoToxExps. */
			nanoToxExps.selectContinuousColumns();
			nanoToxExps.selectResultColumns();
			
			/* build Matrices from experimental data */
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
			prdb.readCsvFile(betaFilename, xcol, ycol);
			
			/* Get header of X-matrix */
			String[] xMatrixHeader = nanoToxExps.getDescriptorHeader();
		
			String[] yMatrixHeadr = nanoToxExps.getResultHeader();
			
			/* Get header of BPLS matrix */
			String[] bplsHeader = prdb.getHeader();			
			
			/* Get a copy of the beta coefficients matrix. */
			DoubleMatrix bMatrix = prdb.getBetaMatrix();
			
			/* Verify that the headers of the X and Beta-Coefficients matrices match. */
			prdb.verifyOrderHeaders(bMatrix, xMatrixHeader, bplsHeader);
			
			/* Get a copy of the X matrix.  */
			DoubleMatrix testXmatrix = prdb.getTestXmatrix();
			
			/* Predict results using the X matrix and beta coefficients. */
			DoubleMatrix Ypred = CsvMatrix.predictResults(testXmatrix, bMatrix);
			
			/* Build test header  */
			prdb.buildTestHeader(xMatrixHeader, yMatrixHeadr);
			
			/* Get copy of the original Y matrix  */
			DoubleMatrix ymatrix = CsvMatrix.getyMatrix();
			
			/* Build results matrix */
			prdb.buildResultsMatrix(testXmatrix, ymatrix, Ypred);
			
			/* Write test results to CSV file. */
			prdb.writeResultsToCsv(testFilename);
			
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		
		
		
	}

}
