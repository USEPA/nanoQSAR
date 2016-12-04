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
		getNanoMaterials();
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
	public static void getNanoMaterials()
	{
		
		MySqlQuery sqlNano = new MySqlQuery();
		List<NanoMaterial> nanomaterials;
				
		/* Obtain the header of the CSV file. */
		String[] headerFile = NanoMaterial.getHeaderFile();
		
		String sqlQuery = null;
		
		try
		{
			/* Input database connection information and name of output file. */
			DBUtil.loadProperties();
			
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
		}
		catch(SQLException ex)
		{
			ex.printStackTrace();
		}
		catch(ClassNotFoundException ex)
		{
			ex.printStackTrace();
		}
		catch(IOException ex)
		{
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}
		catch(IllegalUnitsException ex)
		{
			ex.getMessage();
		}
	}
	
}
