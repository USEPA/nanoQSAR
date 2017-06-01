package nanoQSAR;

import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
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
	static String propFilename = System.getProperty("user.dir") + "\\nanoQSAR.properties";
	static String outFilename = System.getProperty("user.dir") + "\\nanoQSAR_out.csv";
	static String plsFilename = System.getProperty("user.dir") + "\\nanoQSAR_pls.csv";
	static String logFilename = System.getProperty("user.dir") + "\\nanoQSAR.log";
	
	/* Create an object of type Logger so we can log error or warning messages. */
	protected static Logger LOGGER =  Logger.getLogger("nanoQSAR", null);
	
	public static void main(String[] args) 
	{
		try	{
		
			if (args == null || args.length == 0) { // Use default properties file.

				//	System.out.println("Using default properties file: " + filename);

			} else if (args.length == 1 && args[0].trim().matches("-h")) { // respond with user options

				System.out.println("User options:");
				System.out.println("nanoQSAR -h");
				System.out.println("nanoQSAR");
				System.out.println("nanoQSAR propFilename");

			} else if (args.length == 1) { // Using command-line entered properties file

				propFilename = args[0].trim();

			} else { // Something is wrong

				throw new Exception("Invalid options are used");
				
			}

			/* Initialize log file information. Throw IOException and/or SecurityException 
			 * if creation of file handler was not successful. */
			LOGGER.setLevel(Level.INFO);
			if (!LOGGER.getUseParentHandlers()) {
				LOGGER.addHandler(new FileHandler(logFilename));
				LOGGER.addHandler(new ConsoleHandler());
			}
//			LoggerInfo.init();

			/* Input database connection information and name of output file. */
			DBUtil.loadProperties(propFilename);  

			/* Data-mine MySQL database */
			NanoMaterials nanoMaterial = new NanoMaterials(new MySqlQuery());

			/* write data to CSV file. */
//			nanoMats.writeCsvFile(DBUtil.getCsvFileName());

		} catch(Exception ex) {

			System.out.println(ex.getMessage());
			ex.printStackTrace();  // This is the only case when the stack trace is sent to the console.

		} finally {

			LoggerInfo.close();

		}
		
	}
	
}
