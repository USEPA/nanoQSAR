package nanoQSAR;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.GeneralSecurityException;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import datamine.DBUtil;
import datamine.MySqlQuery;

public class NanoQSAR {

	/**
	 * This is the main method. It calls the LoggerInfo.init and getNanoMaterials methods.
	 * @author Wilson Melendez & Paul Harten
	 * @version 1.0
	 * @param args  Properties file.
	 * @return Nothing.
	 */

	/* Default filenames */
	static String propFilename = System.getProperty("user.dir") + "\\nanoQSAR_prop.txt";
	static String outFilename = System.getProperty("user.dir") + "\\nanoQSAR_out.csv";
	static String plsFilename = System.getProperty("user.dir") + "\\nanoQSAR_pls.csv";
	static String logFilename = System.getProperty("user.dir") + "\\nanoQSAR.log";
	
	/* Create an object of type Logger so we can log error or warning messages. */
	private final static Logger lOGGER = Logger.getLogger("nanoQSAR");
	
	public static void main(String[] args) 
	{
		String filename = null;
		
		if (args == null || args.length == 0)  // Use default properties file.
		{
			filename = System.getProperty("user.dir") + "\\nanoQSAR.properties";
			System.out.println("Using default properties file: " + filename);
		}
		else  // Use command-line specified properties file.
		{
			filename = args[0].trim();
			System.out.println("Using command-line entered properties file: " + args[0]);
		}
		
		try	{
			
			/* Initialize log file information. Throw IOException and/or SecurityException 
			 * if creation of file handler was not successful. */
			LoggerInfo.init();
			
			/* Input database connection information and name of output file. */
			DBUtil.loadProperties(propFilename);  
			
			/* Data-mine MySQL database */
			NanoMaterials nanoMats = new NanoMaterials(new MySqlQuery());
			
			/* write data to CSV file. */
			nanoMats.writeCsvFile(DBUtil.getCsvFileName());
  
		} catch(Exception ex) {
			
		    System.out.println(ex.getMessage());
			ex.printStackTrace();  // This is the only case when the stack trace is sent to the console.
			
		} finally {
			
			LoggerInfo.close();
			
		}
		
	}
	
}
