package pls_analysis;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * This class handles properties of the log file.
 * @author Wilson Melendez
 *
 */
public class LoggerInfo 
{
	private static FileHandler fh = null;
	
	/**
	 * @author Wilson Melendez
	 * @throws IOException
	 * @throws SecurityException
	 */
	public static void init() throws IOException, SecurityException
	{
		String logFile = System.getProperty("user.dir") + "\\nanoQSAR.log";
		try
		{
			fh = new FileHandler(logFile);
		}
		catch(SecurityException | IOException ex)
		{
			ex.printStackTrace();
			throw ex;
		}
		
		SimpleFormatter formatter = new SimpleFormatter();
		fh.setFormatter(formatter);
		
		/* Get the logger. */
		Logger logger = PlsrAnalyzer.getLogger(); 
		
		logger.setUseParentHandlers(false);
		logger.addHandler(fh);        
		logger.setLevel(Level.CONFIG);			
	}
	
	/**
	 * @author Wilson Melendez
	 */
	public static void close()
	{
		if (fh != null)
		{
			fh.close();
		}		
	}
}
