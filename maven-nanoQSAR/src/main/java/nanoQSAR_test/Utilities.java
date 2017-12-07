/**
 * 
 */
package nanoQSAR_test;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.GeneralSecurityException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * @author Wmelende
 *
 */
public class Utilities 
{
	
	/* Static fields for original CSV file, beta coefficients file, and output file. */
	private static String CsvFileName;
	private static String BplsFileName;
	private static String PredictionsFileName;
	
	/* Need this line to allow logging of error messages */
	private static Logger LOGGER = Logger.getLogger("nanoQSAR_TEST");

	
	public static String getCsvFileName() {
		return CsvFileName;
	}

	public static void setCsvFileName(String csvFileName) {
		CsvFileName = csvFileName;
	}

	public static String getBplsFileName() {
		return BplsFileName;
	}

	public static void setBplsFileName(String bplsFileName) {
		BplsFileName = bplsFileName;
	}

	public static String getPredictionsFileName() {
		return PredictionsFileName;
	}

	public static void setPredictionsFileName(String predictionsFileName) {
		PredictionsFileName = predictionsFileName;
	}
	

	/**
	 * This method loads some of the properties in the properties file.
	 * @param propFilename
	 * @throws IOException
	 * @throws GeneralSecurityException
	 */
	public static void loadProperties(String propFilename) throws IOException, GeneralSecurityException
	{
		Properties prop = new Properties();
		FileInputStream propFile = null;
		
		try
		{
			Path p1 = Paths.get(propFilename);
			propFile = new FileInputStream(p1.toString());
			
			// Load properties file
			prop.load(propFile);
			
			// Get the properties and assign them to their respective fields.
			setCsvFileName(prop.getProperty("CsvFileName"));
			setBplsFileName(prop.getProperty("BplsFileName"));
			setPredictionsFileName(prop.getProperty("PredictionsFileName"));
		}
		catch(IOException ex)
		{
			LOGGER.log(Level.SEVERE, "Properties file, " + propFilename + ", was not found.", ex);
			throw ex;
		}
		catch(Exception ex)
		{
			throw ex;
		}
		finally
		{
			if (propFile != null)
			{
				try
				{
					propFile.close();
				}
				catch(IOException ex)
				{
					LOGGER.log(Level.SEVERE, "InputStream variable, input, could not be closed.", ex);
					throw ex;
				}				
			}
		}
	}

}
