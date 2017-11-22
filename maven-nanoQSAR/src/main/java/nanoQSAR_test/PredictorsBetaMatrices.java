/**
 * 
 */
package nanoQSAR_test;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.logging.Logger;

import nanoQSAR.NanoToxExp;

import org.jblas.DoubleMatrix;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

/**
 * @author Wmelende
 *
 */
public class PredictorsBetaMatrices {

	
	private DoubleMatrix testXmatrix;
	private DoubleMatrix betaMatrix;
	private DoubleMatrix resultsMatrix;
	private String[] betaHeader = null;
	private String[] testHeader = null;
	
	/* Need this line to allow logging of error messages */
	private final static Logger LOGGER = Logger.getLogger("nanoQSAR_TEST");
	static String logFilename = System.getProperty("user.dir") + "\\nanoQSAR_TEST.log";
	
	/**
	 * @return the betaMatrix
	 */
	public DoubleMatrix getBetaMatrix() {
		return betaMatrix;
	}

	/**
	 * @return the testHeader
	 */
	public String[] getTestHeader() {
		return testHeader;
	}

	/**
	 * @return the header
	 */
	public String[] getHeader() {
		return betaHeader;
	}

	/**
	 * @param header the header to set
	 */
	public void setHeader(String[] header) {
		this.betaHeader = header;
	}

	/**
	 * @return the xTestMatrix
	 */
	public DoubleMatrix getTestXmatrix() {
		return this.testXmatrix;
	}

	/**
	 * @param xTestMatrix the xTestMatrix to set
	 */
	public void setTestXmatrix(DoubleMatrix xTestMatrix) {
		this.testXmatrix = xTestMatrix;
	}

	public void copyFiles(File source, File dest) throws IOException
	{
		Files.copy(source.toPath(), dest.toPath(),StandardCopyOption.REPLACE_EXISTING);
	}

	public void replaceNullsXmatrix()
	{
		int xcolumns = testXmatrix.columns;
		int rowsSize = testXmatrix.rows;
		DoubleMatrix xColumn;
		double avg, num;
		
		/* if descriptor data has NaN values, replace with avg of that descriptor */
		for (int j = 0; j < xcolumns; j++) {
			xColumn = testXmatrix.getColumn(j);
			avg = 0.0;
			num = 0;
			for (int i = 0; i < rowsSize; i++) {
				Double value = xColumn.get(i);
				if (!Double.isNaN(value)) {
					num++;
					avg += value;
				}
			}
			if (num > 0) {	/* fill the NaN values with avg */
				avg /= num;
				for (int i = 0; i < rowsSize; i++) {
					if (Double.isNaN(xColumn.get(i))) {
						xColumn.put(i, avg);
					}
				}
			} else { /* all NaN values, fill the whole column up with 0.0 */
				for (int i = 0; i < rowsSize; i++) {
					xColumn.put(i, 0.0);
				}
			}
			testXmatrix.putColumn(j, xColumn);	
		}
		
	}
	
	public void readCsvFile(String filename, int xcol, int ycol) throws Exception
	{
        CSVReader csvReader = null;
        betaMatrix = new DoubleMatrix(xcol+1, ycol);
        DoubleMatrix bCol;
		
		try	{
			
			/* create a new CSVReader for the fileName */
			csvReader = new CSVReader(new FileReader(filename));
			
			/* read the headers from the csv file */
			this.setHeader(csvReader.readNext());
			
			String[] line = null;
			int j = 0;
			/* Loop over lines in the csv file */
			while ((line = csvReader.readNext()) != null) {
				
				bCol = new DoubleMatrix(line.length, 1);
				
				for (int i = 0; i < line.length; i++)
				{
					bCol.put(i, 0, Double.valueOf(line[i]));
				}
				
				betaMatrix.putColumn(j, bCol);
				j++;
	
			}
			
			/* Close the writer. */
			csvReader.close();
			
		} catch(Exception ex)	{
				
			throw ex;
			
		}
	}
	
	public void verifyOrderHeaders(DoubleMatrix bMatrix, String[] xHeader, String[] bHeader)
	{
		boolean noMatch = false;
		int xlen = xHeader.length;
		int blen = bHeader.length;
		DoubleMatrix temp = new DoubleMatrix(bMatrix.rows, bMatrix.columns);
		
		for (int i = 0; i < xlen; i++)
		{
			if (!xHeader[i].equalsIgnoreCase(bHeader[i + 1]))
			{
				noMatch = true;
			}
		}
		
		if (noMatch == true)
		{
			for (int j = 0; j < xlen; j++)
			{
				for (int i = 1; i < blen; i++)
				{
					if (xHeader[j].equalsIgnoreCase(bHeader[i]))
					{
						for (int icol = 0; icol < bMatrix.columns; icol++)
						{
							temp.put(j, icol, bMatrix.get(i, icol));
						}
					}
				}
				
			}
			
			bMatrix.copy(temp);
		}
		
	}
	
	
	public void buildTestHeader(String[] xmatrixH, String[] ymatrixH)
	{
		ArrayList<String> list = new ArrayList<String>();
		
		for (int i = 0; i < xmatrixH.length; i++)
		{
			list.add(xmatrixH[i]);
		}
		
		for (int i = 0; i < ymatrixH.length; i++)
		{
			list.add(ymatrixH[i]);
		}
		
		for (int i = 0; i < ymatrixH.length; i++)
		{
			ymatrixH[i] = ymatrixH[i] + "_Predicted";
			list.add(ymatrixH[i]);
		}
		
		this.testHeader = new String[list.size()];
		this.testHeader = list.toArray(testHeader);
	}
	
	public void buildResultsMatrix(DoubleMatrix xMatrix, DoubleMatrix yMatrix, DoubleMatrix predictions)
	{
		DoubleMatrix temp = DoubleMatrix.concatHorizontally(xMatrix, yMatrix);
		this.resultsMatrix = DoubleMatrix.concatHorizontally(temp, predictions);
	}
	
	public void writeResultsToCsv(String filename)
	{
		String[] entries = new String[this.resultsMatrix.columns];
		
		try
		{	
			FileWriter file = new FileWriter(filename); 
					
			/* Create an instance of the CSVWriter class and specify the comma as the 
			 * default separator. Default quote character is double quote. */ 
			CSVWriter csvOutput = new CSVWriter(file, CSVWriter.DEFAULT_SEPARATOR);
			
			/* first write descriptor header row */
			for (int i = 0; i < this.resultsMatrix.columns; i++) {
				entries[i] = this.testHeader[i];
			}
			/* Write row of data to output using the writeNext method. */
			csvOutput.writeNext(entries); 
					
			/* Loop over each row of the rMatrix */
			for (int i = 0; i < this.resultsMatrix.rows; i++) {
				
				DoubleMatrix rRow = this.resultsMatrix.getRow(i);
				
				for (int j = 0; j < this.resultsMatrix.columns; j++) {
					entries[j] = String.valueOf(rRow.get(j));
				}

				/* Write row of data to output using the writeNext method. */
				csvOutput.writeNext(entries); 
			}
			
			/* Close the writer. */
			   csvOutput.close();   
		}
		catch(IOException ex)
		{
			LOGGER.severe("FileWriter for " + filename + " could not be constructed." + ex);	
		}
	}
	
}
