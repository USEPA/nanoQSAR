import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.GeneralSecurityException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class nanoQSAR {

	/**
	 * This is the main method. It calls the LoggerInfo.init and getNanoMaterials methods.
	 * @author Wilson Melendez and Paul Harten
	 * @version 1.0
	 * @param args  Properties file.
	 * @return Nothing.
	 */
	static String propFilename = System.getProperty("user.dir") + "\\nanoQSAR_prop.txt";
	static String outFilename = System.getProperty("user.dir") + "\\nanoQSAR_out.csv";
	
	/* Create an object of type Logger so we can log error or warning messages. */
	private final static Logger lOGGER = Logger.getLogger( LoggerInfo.class.getName() );
	
	public static void main(String[] args) 
	{
		
		if (args == null || args.length == 0)  // Use default properties file.
		{
			propFilename = System.getProperty("user.dir") + "\\nanoQSAR.properties";
			System.out.println("Using default properties file: " + propFilename);
		}
		else  // Use command-line specified properties file.
		{
			propFilename = args[0].trim();
			System.out.println("Using command-line entered properties file: " + args[0]);
		}
		
		try
		{
			/* Initialize log file information. Throw IOException and/or SecurityException 
			 * if creation of file handler was not successful. */
			LoggerInfo.init();  
			
			/* Perform necessary steps to data-mine MySQL database and write data to 
			 * CSV file. */
			getNanoMaterials(propFilename);  
		}
		catch(IOException | SecurityException ex)
		{
		    System.out.println("Creation of file handler for log file failed.");
			ex.printStackTrace();  // This is the only case when the stack trace is sent to the console.
		}
		finally
		{
			LoggerInfo.close();
		}
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
		MySqlQuery sqlNano = new MySqlQuery();
		List<NanoMaterial> nanomaterials;
				
		/* Obtain the header of the CSV file. */
		String[] headerFile = NanoMaterial.getHeaderFile();
		
		String sqlQuery = null;
		
		try
		{				
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
			
			
		}
		catch(SQLException | ClassNotFoundException | IOException | SecurityException | IllegalUnitsException | GeneralSecurityException ex)
		{
			lOGGER.log(Level.SEVERE, "Exception was thrown: ending the execution of the program.");			
		}		
	}

}
