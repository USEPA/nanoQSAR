package nanoQSAR;

import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.mysql.jdbc.exceptions.jdbc4.CommunicationsException;

import datamine.ConnectionManager;
import datamine.DBUtil;
import datamine.MySqlQuery;

public class NanoQSAR {

	/**
	 * This is the main method. It calls the LoggerInfo.init and getNanoToxExps methods.
	 * @author Wilson Melendez & Paul Harten
	 * @version 1.0
	 * @param args  Properties file.
	 * @return Nothing.
	 */

	/* Default filenames */
	static String propFilename = System.getProperty("user.dir") + "\\nanoQSAR.properties";
	static String keyFilename = System.getProperty("user.dir") + "\\nanoQSAR.key";
	static String csvFilename = System.getProperty("user.dir") + "\\nanoQSAR.csv";
	static String plsFilename = System.getProperty("user.dir") + "\\nanoQSAR_pls.csv";
	static String logFilename = System.getProperty("user.dir") + "\\nanoQSAR.log";
	
	static String helpString = "User options:\njava -jar nanoQSAR -h\njava -jar nanoQSAR\njava -jar nanoQSAR propFilename\n";
	
	/* Create an object of type Logger so we can log error or warning messages. */
	protected static Logger LOGGER =  Logger.getLogger("nanoQSAR", null);
	
	public static void main(String[] args) 
	{
		try	{
		
			if (args == null || args.length == 0) { // Use default properties file.

				//	System.out.println("Using default properties file: " + filename);

			} else if (args.length == 1 && args[0].trim().matches("-h")) { // respond with user options

//				System.out.println("User options:");
//				System.out.println("java -jar nanoQSAR -h");
//				System.out.println("java -jar nanoQSAR");
//				System.out.println("java -jar nanoQSAR propFilename");
				System.out.print(helpString);
				return;

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
				// LOGGER.addHandler(new ConsoleHandler());
				LOGGER.setUseParentHandlers(false);  // This will prevent LOGGER from printing messages to the console.
			}
//			LoggerInfo.init();

			/* Input database connection information and name of output file. */
			DBUtil.loadProperties(propFilename, keyFilename);	
			
			NanoToxExps nanoToxExps = null;
			
			/* Test for connection to database */			
			if (ConnectionManager.testConnection()) {
				
				/* Data-mine MySQL database */
				nanoToxExps = new NanoToxExps(new MySqlQuery());
				/* write data to CSV file. */
				nanoToxExps.writeCsvFile(DBUtil.getCsvFileName());
				
			} else { /* Connection to database is not avaialable */
				
				/* Get data from CSV file */
				nanoToxExps = new NanoToxExps(DBUtil.getCsvFileName());
			}

		} catch(Exception ex) {

			ex.printStackTrace();  // This is the only case when the stack trace is sent to the console.

		}
		
	}
	
}
