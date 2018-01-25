package nanoQSAR;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.sql.SQLException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import datamine.ConnectionManager;
import datamine.DBUtil;

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
	
	public NanoQSAR(String[] args) 
	{
		try	{
		
			if (testArgs(args)) {

				startLogger();

				generate();
				
			}

		} catch(Exception ex) {

			ex.printStackTrace();  // This is the only case when the stack trace is sent to the console.

		}
		
	}



	public NanoQSAR() 
	{
		try	{

			startLogger();

			generate();

		} catch(Exception ex) {

			ex.printStackTrace();  // This is the only case when the stack trace is sent to the console.

		}
		
	}
	
	private boolean testArgs(String[] args) throws Exception {
		
		if (args == null || args.length == 0) { // Use default properties file.

			//	System.out.println("Using default properties file: " + filename);

		} else if (args.length == 1 && args[0].trim().matches("-h")) { // respond with user options

//				System.out.println("User options:");
//				System.out.println("java -jar nanoQSAR -h");
//				System.out.println("java -jar nanoQSAR");
//				System.out.println("java -jar nanoQSAR propFilename");
			System.out.print(helpString);
			return false;

		} else if (args.length == 1) { // Using command-line entered properties file
			
			/* key file must be in same folder as properties file, but with "key" extension */
			propFilename = args[0].trim();
			keyFilename = propFilename.substring(0,propFilename.lastIndexOf('.')+1)+"key";

		} else { // Something is wrong

			throw new Exception("Invalid options are used");
			
		}
		
		return true;
		
	}
	
	private void startLogger() throws IOException {
		
		/* Initialize log file information. Throw IOException and/or SecurityException 
		 * if creation of file handler was not successful. */
		LOGGER.setLevel(Level.INFO);
		if (!LOGGER.getUseParentHandlers()) {
			LOGGER.addHandler(new FileHandler(logFilename));
			// LOGGER.addHandler(new ConsoleHandler());
			LOGGER.setUseParentHandlers(false);  // This will prevent LOGGER from printing messages to the console.
		}
		//			LoggerInfo.init();
		
	}
	
	private void generate() throws IOException, GeneralSecurityException, SQLException, ClassNotFoundException, Exception {
		
		/* Input database connection information and name of output file. */
		DBUtil dbUtil = new DBUtil();
		dbUtil.loadProperties(propFilename, keyFilename);	

		NanoToxExps nanoToxExps = null;

		/* Test for connection to database */			
		if (ConnectionManager.testConnection(dbUtil, keyFilename)) {

			/* Data-mine MySQL database */
			nanoToxExps = new NanoToxExps(dbUtil, keyFilename);
			/* write data to CSV file. */
			nanoToxExps.writeCsvFile(dbUtil.getCsvFileName());

		} else { /* Connection to database is not available */

			/* Get data from CSV file */
			nanoToxExps = new NanoToxExps(dbUtil.getCsvFileName());
		}
		
	}
	
}
