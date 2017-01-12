
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * This program reads the contents of a CSV file, builds the X and Y matrices needed by the
 * PLS regression algorithm, and prints the matrices to the console.
 * @author Wilson Melendez
 *
 */


public class Main 
{	
	/* Create an object of type Logger so we can log error or warning messages. */
	private final static Logger lOGGER = Logger.getLogger( Logger.class.getName() );
	
	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		String filename = null;
		
		if (args == null || args.length == 0)  // Use default CSV file.
		{
			filename = System.getProperty("user.dir") + "\\OutputFile.csv";
			System.out.println("Using default CSV file: " + filename);
		}
		else  // Use command-line specified CSV file.
		{
			filename = args[0].trim();
			System.out.println("Using command-line entered CSV file: " + args[0]);
		}
		
		/* Read in contents of CSV file */
		try
		{
			/* Initialize log file information. Throw IOException and/or SecurityException 
			 * if creation of file handler was not successful. */
			LoggerInfo.init(); 			
		}
		catch(IOException | SecurityException ex)
		{
			System.out.println("Creation of file handler for log file failed.");
			LoggerInfo.close();
			ex.printStackTrace();  // This is the only case when the stack trace is sent to the console.
			System.exit(1);  // End the execution of the program.
		}
		
		try
		{		
			/* Open and read CSV file. */
            CsvMatrix.readCsvFile(filename);
			
			/* Build X and Y matrices. */
			CsvMatrix.buildMatrices();
			
			/* Print X and Y matrices to standard output */
			Matrix.showX();
			Matrix.showY();
			
			/* Close logger file. */
			LoggerInfo.close();
		}
		catch(FileNotFoundException ex)
		{
			lOGGER.log(Level.SEVERE, "Exception was thrown: ending the execution of the program.");	
		}
		catch(IOException ex)
		{
			lOGGER.log(Level.SEVERE, "Exception was thrown: ending the execution of the program.");	
		}		
		
	}

}
