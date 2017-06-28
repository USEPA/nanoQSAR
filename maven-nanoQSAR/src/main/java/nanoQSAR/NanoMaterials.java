package nanoQSAR;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.GeneralSecurityException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import datamine.DBUtil;
import datamine.MySqlQuery;

public class NanoMaterials extends Vector<NanoMaterial> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 9210392203321638729L;
	private String[] header = null;
	private int[] propertyIndex = null;
	
	public NanoMaterials() throws Exception {
		super();
	}
	
	public NanoMaterials(String filename) throws Exception {
		super();
		this.readCsvFile(filename);
	}
	
	public NanoMaterials(MySqlQuery sqlQuery) throws Exception {
		super();
		String filename = System.getProperty("user.dir") + "\\nanoQSAR.properties";
		DBUtil.loadProperties(filename);
		/* Read data from remote MySQL server and store them in a list.  */
		this.addAll(sqlQuery.getNanoMaterials());
		this.setHeader(sqlQuery.getHeader());
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
			
			/* Check default units and perform unit conversions if necessary. */
			DefaultUnits.checkUnits(this);
			
		} catch	(Exception ex) {
			
//			lOGGER.log(Level.SEVERE, "Exception was thrown: ending the execution of the program.");
			throw ex;
			
		}
		
	}
	
	public void readCsvFile(String filename) throws Exception {

		String[] entries;
		CSVReader csvReader = null;
		
		try	{
			
			/* create a new CSVReader for the fileName */
			csvReader = new CSVReader(new FileReader(filename));
			
			/* read the headers from the csv file */
			this.setHeader(csvReader.readNext());
			
			int[] fieldIndex = NanoMaterial.getFieldIndex(getHeader());
			
			String[] line = null;
			/* Loop over lines in the csv file */
			while ((line = csvReader.readNext())!=null) {
				
				/* Read next row of data */
//				String[] line = csvReader.readNext();
				
				/* convert the data into a new NanoMaterial */
				this.add(new NanoMaterial(fieldIndex, line));
	
			}
			
			/* Close the writer. */
			csvReader.close();
			
		} catch(Exception ex)	{
			
//			lOGGER.log(Level.SEVERE, "FileWriter for " + filename + " could not be constructed.", ex);	
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
	
	public boolean isSame(NanoMaterials other) throws Exception {
		
		if (this.getHeader().length != other.getHeader().length) return false;
		if (this.size() != other.size()) return false;
		
		String[] thisHeader = this.getHeader();
		String[] otherHeader = other.getHeader();
		for (int i=0; i<thisHeader.length; i++) {
			if (thisHeader[i].matches(otherHeader[i])) continue;
			return false;
		}
		
		if (this.size() != other.size()) return false;
		for (int i=0; i<this.size(); i++) {
			if (this.get(i).isSame(other.get(i))) continue;
			return false;
		}
		
		return true;
	}
	
	public void setHeader(String[] header) {
		this.header = header;
	}
	
	public String[] getHeader() {
		return this.header;
	}

	public int[] getPropertyIndex() {
		return propertyIndex;
	}

	public void setPropertyIndex(int[] propertyIndex) {
		this.propertyIndex = propertyIndex;
	}
	
	public void setPropertyIndexFromHeader() {
		this.propertyIndex = propertyIndex;
	}

}
