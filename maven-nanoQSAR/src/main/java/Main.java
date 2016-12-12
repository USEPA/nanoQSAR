/**
 * 
 */

/**
 * @author Wmelende
 *
 */

import java.sql.SQLException;
import java.io.IOException;
import java.util.List;
import java.nio.file.*;
import java.util.logging.*;

import javax.annotation.processing.FilerException;

import org.assertj.core.internal.InputStreamsException;

public class Main {

	/**
	 * This is the main method. It calls the getNanoMaterials method.
	 * @author Wilson Melendez
	 * @version 1.0
	 * @param args  Unused.
	 * @return Nothing.
	 */
	public static void main(String[] args) 
	{
		String filename = null;
		
		if (args == null || args.length == 0)  // Use default properties file.
		{
			filename = System.getProperty("user.dir") + "\\properties.txt";
		}
		else  // Use command-line specified properties file.
		{
			filename = args[0].trim();
			System.out.println("Argument passed to the program: " + args[0]);
		}
		
		getNanoMaterials(filename);
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
	public static void getNanoMaterials(String filename)
	{
		Logger logger = Logger.getLogger("getNanoMaterials.class");
		String logFile = System.getProperty("user.dir") + "\\nanoQSAR.log";
		FileHandler fh;
				
		MySqlQuery sqlNano = new MySqlQuery();
		List<NanoMaterial> nanomaterials;
				
		/* Obtain the header of the CSV file. */
		String[] headerFile = NanoMaterial.getHeaderFile();
		
		String sqlQuery = null;
		
		try
		{
			fh = new FileHandler(logFile);
			logger.setUseParentHandlers(false);
			logger.addHandler(fh);
			logger.setLevel(Level.INFO);
			
			SimpleFormatter formatter = new SimpleFormatter();
			fh.setFormatter(formatter);
						
			/* Input database connection information and name of output file. */
			DBUtil.loadProperties(filename);
			
			/* Set up the path and name of the CSV file. */
			Path p1 = Paths.get(DBUtil.getCsvFileName());  
			
			/* Obtain the SQL query statement. */
			sqlQuery = sqlNano.getSqlQuery();
			
			/* Read data from remote MySQL server and store them in a list.  */
			nanomaterials = sqlNano.getNanoMaterials(sqlQuery);
			
			/* Check default units and perform unit conversions if necessary. */
			DefaultUnits.checkUnits(nanomaterials);
			
			/* Write data to CSV file. */
			CsvFileWriter.writeCsvFile(p1.toString(), nanomaterials, headerFile);
			
			/*  Print out selected data columns: this has been commented out because
			 * is is not really needed for the CSV file. */
			/* DBUtil.displayMetaData();
			for (NanoMaterial nanomaterial : nanomaterials)
			{
				DBUtil.displayNanoMaterial(nanomaterial);
			}
			*/
			fh.close();
		}
		catch(SQLException ex)
		{
			logger.log(Level.SEVERE, "SQL exception found.", ex);
			// ex.printStackTrace();
		}
		catch(ClassNotFoundException ex)
		{
			logger.log(Level.SEVERE, "Class not found exception found.", ex);
			// ex.printStackTrace();
		}
		catch(IOException ex)
		{
			logger.log(Level.SEVERE, "File not found exception found.", ex);
			// ex.printStackTrace();
		}
		catch(NullPointerException ex)
		{
			logger.log(Level.SEVERE, "Null pointer exception found.", ex);
		}
		catch(SecurityException ex)
		{
			logger.log(Level.SEVERE, "Security exception found.", ex);
		}
		catch(IllegalUnitsException ex)
		{
			logger.log(Level.SEVERE, "Class not found exception found.", ex.getMessage());
		}
		
	}
	
}
