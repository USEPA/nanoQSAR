package nanoQSAR;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.GeneralSecurityException;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.opencsv.CSVWriter;

import datamine.DBUtil;
import datamine.MySqlQuery;

public class NanoMaterials extends Vector<NanoMaterial> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 9210392203321638729L;
	private String[] header = null;
	
	public NanoMaterials() throws Exception {
		super();
	}
	
	public NanoMaterials(MySqlQuery sqlQuery) throws Exception {
		super();
		this.mineNanoMaterials(sqlQuery);
	}
	
	/**
	 * This method performs the main steps needed for reading the desired data from the
	 * database and writing the data to a CSV file.  It sets up the path and the name of the
	 * CSV file; it gets the header file of the CSV file; it calls the getSqlQuery method to
	 * obtain the SQL query statement; it passes the SQL query statement to the 
	 * getNanoMaterials method that will read the data from the database and stores them in a
	 * list of NanoMaterial objects.  This information is passed to the write method of the 
	 * CsvFileWriter class that will generate the CSV file.
	 * @author Wilson Melendez
	 * @param None.
	 * @return Nothing.
	 */
	public void mineNanoMaterials(MySqlQuery sqlQuery) throws Exception {			
	
		try	{				  
			
			/* Read data from remote MySQL server and store them in a list.  */
			this.addAll(sqlQuery.getNanoMaterials());
			
			/* Read header from remote MySQL server and store in a String array.  */
			this.setHeader(sqlQuery.getHeader());
			
			/* Check default units and perform unit conversions if necessary. */
			DefaultUnits.checkUnits(this);
			
		} catch	(Exception ex) {
			
//			lOGGER.log(Level.SEVERE, "Exception was thrown: ending the execution of the program.");
			throw ex;
			
		}
		
	}
	
	public void writeCsvFile(String CsvFileName) throws Exception {

		String[] entries;
		
		try	{
			
			/* create a new FileWriter for the CsvFileName */
			FileWriter file = new FileWriter(CsvFileName); 
			
			/* Create an instance of the CSVWriter class and specify the comma as the 
			 * default separator. Default quote character is double quote. */ 
			CSVWriter csvOutput = new CSVWriter(file, CSVWriter.DEFAULT_SEPARATOR);
			
			/* Write header line to CSV file. */
			csvOutput.writeNext(this.getHeader());   
			
			/* Loop over list of NanoMaterial objects */
			for(NanoMaterial nanoM : this) {
				/* Retrieve data as an array of strings and assign array to entries. 
				 * The array represents one record (row of data). */
				entries = nanoM.storeDataAsStringArray();  
				
				/* Write row of data to output using the writeNext method. */
				csvOutput.writeNext(entries);     
			}
			
			/* Close the writer. */
			csvOutput.close();
			
		} catch(Exception ex)	{
			
//			lOGGER.log(Level.SEVERE, "FileWriter for " + filename + " could not be constructed.", ex);	
			throw ex;
			
		}
		
	}
	
	public void setHeader(String[] header) {
		this.header = header;
	}
	
	public String[] getHeader() {
		return this.header;
	}

}
