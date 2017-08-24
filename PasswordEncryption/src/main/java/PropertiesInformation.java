import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

import org.apache.commons.configuration.*;

/**
 * 
 */

/**
 * @author Wilson Melendez
 *
 */
public class PropertiesInformation 
{
	/* Static fields for name of driver, URL of database, username, password,
	 * name of CSV file, and key. */
	private static String driverName;
	private static String databaseUrl;
	private static String username;
	private static String password;
	private static String csvFileName;
	private static String key;
	
	/* Getters and Setters of the private static variables.  */
	
	public static String getDriverName() {
		return driverName;
	}
	
	public static void setDriverName(String driverName) {
		PropertiesInformation.driverName = driverName;
	}
	
	public static String getDatabaseUrl() {
		return databaseUrl;
	}
	
	public static void setDatabaseUrl(String databaseUrl) {
		PropertiesInformation.databaseUrl = databaseUrl;
	}
	
	public static String getUsername() {
		return username;
	}
	
	public static void setUsername(String username) {
		PropertiesInformation.username = username;
	}
	
	public static String getPassword() {
		return password;
	}
	
	public static void setPassword(String password) {
		PropertiesInformation.password = password;
	}
	
	public static String getCsvFileName() {
		return csvFileName;
	}
	
	public static void setCsvFileName(String csvFileName) {
		PropertiesInformation.csvFileName = csvFileName;
	}	
	
	public static String getKey() {
		return key;
	}
	
	public static void setKey(String key) {
		PropertiesInformation.key = key;
	}
	
	/**
	 * This method opens and reads a property file containing database connection
	 * information and the name of the output file.
	 * @author Wilson Melendez
	 * @throws IOException
	 */
	public static void loadProperties(String filename) throws IOException
	{
		Properties prop = new Properties();
		InputStream input = null;
		
		try
		{
			Path p1 = Paths.get(filename);
			input = new FileInputStream(p1.toString());
			
			// Load properties file
			prop.load(input);
		
			setCsvFileName(prop.getProperty("CsvFileName").trim());
			setDatabaseUrl(prop.getProperty("databaseURL").trim());
			setDriverName(prop.getProperty("DriverName").trim());
			setUsername(prop.getProperty("Username").trim());
			setPassword(prop.getProperty("Password").trim());
		}
		catch(IOException ex)
		{
			System.out.println("Properties file, " + filename + ", was not found.");
			throw ex;
		}
		finally
		{
			if (input != null)
			{
				try
				{
					input.close();
				}
				catch(IOException ex)
				{
					System.out.println("InputStream variable, input, could not be closed.");
					throw ex;
				}				
			}
		}

	}
	
	/**
	 * This method writes the encrypted password and its associated key to the properties file.
	 * @author Wilson Melendez
	 * @param filename
	 * @throws ConfigurationException
	 */
	public static void writePasswordKey(String filename) throws ConfigurationException
	{
		File outFile = new File(filename);
		if (outFile.exists())
		{
			outFile.delete();
		}
		
		PropertiesConfiguration config = new PropertiesConfiguration(outFile);
		
		try
		{
			config.setProperty("CsvFileName",getCsvFileName());
			config.setProperty("databaseURL",getDatabaseUrl());
			config.setProperty("DriverName",getDriverName());
			config.setProperty("Username",getUsername());
			config.setProperty("Password",getPassword());
			config.setProperty("Key",getKey());
			config.save();
		}
		catch(ConfigurationException ex)
		{
			System.out.println("Attempt to update Password or Key failed.");
			throw ex;
		}		
	}

}
