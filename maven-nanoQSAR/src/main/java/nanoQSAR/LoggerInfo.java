package nanoQSAR;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.io.IOException;

/**
 * 
 */

/**
 * This class sets up the log file that will be used to write error messages to.
 * @author Wilson Melendez
 *
 */
public class LoggerInfo 
{
	private final static Logger lOGGER = Logger.getLogger( LoggerInfo.class.getName() );
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
		lOGGER.setUseParentHandlers(false);
		lOGGER.addHandler(fh);        
		lOGGER.setLevel(Level.CONFIG);		
		
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
