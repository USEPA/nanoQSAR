package datamine;

import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * @author Wmelende & Paul Harten
 *
 */
import java.nio.file.*;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * This class is used to call static methods that perform general purpose tasks such as
 * closing Java connector related objects, printing out selected items to the screen, and
 * opening and reading a properties file.
 * @author Wilson Melendez & Paul Harten
 * 
 */
public class DBUtil 
{
	public static final String AES = "AES";
	public static final String propFilename = System.getProperty("user.dir") + "\\nanoQSAR.properties";
	public static final String keyFilename = System.getProperty("user.dir") + "\\nanoQSAR.key";
	
	/* Static fields for name of driver, URL of database, username and password. */
	private static String driverName;
	private static String databaseUrl;
	private static String username;
	private static String password;
	private static String csvFileName;
	private static String passwordKey;
	private static String BplsFileName;
	private static String PredictionsFileName;
	
	/* Need this line to allow logging of error messages */
	private static Logger LOGGER = Logger.getLogger("nanoQSAR");
	
	/* Need this line to allow logging of error messages */
	private static Logger LOGGER1 = Logger.getLogger("nanoQSAR_TEST");
	
	public static String getDriverName() {
		return driverName;
	}

	public static void setDriverName(String driverName) {
		DBUtil.driverName = driverName;
	}

	public static String getDatabaseUrl() {
		return databaseUrl;
	}

	public static void setDatabaseUrl(String databaseUrl) {
		DBUtil.databaseUrl = databaseUrl;
	}

	public static String getUsername() {
		return username;
	}

	public static void setUsername(String username) {
		DBUtil.username = username;
	}

	public static String getPassword() {
		return password;
	}

	public static void setPassword(String password) {
		DBUtil.password = password;
	}

	public static String getCsvFileName() {
		return csvFileName;
	}

	public static void setCsvFileName(String csvFileName) {
		DBUtil.csvFileName = csvFileName;
	}

	public static String getPasswordKey() {
		return passwordKey;
	}

	public static void setPasswordKey(String passwordKey) {
		DBUtil.passwordKey = passwordKey;
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
	 * This method opens and reads a property file containing database connection
	 * information and the name of the output file.
	 * @author Wilson Melendez
	 * @throws IOException
	 */
	public static void loadProperties(String propFilename, String keyFilename) throws IOException, GeneralSecurityException
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
			setDatabaseUrl(prop.getProperty("databaseURL").trim());
			setDriverName(prop.getProperty("DriverName").trim());
			setPassword(prop.getProperty("Password").trim());
			setUsername(prop.getProperty("Username").trim());	
			setCsvFileName(prop.getProperty("CsvFileName").trim());
			
			Path p2 = Paths.get(keyFilename);
			File keyFile = new File(p2.toString());
			
			String message = DBUtil.byteArrayToHexString(readKeyFile(keyFile));
			setPasswordKey(message);
			
//			encryptPassword();

			/* Decrypt password using the key. */
//			decrypt(getPassword(), new File(p2.toString()));
		}
		catch(IOException ex)
		{
			LOGGER.log(Level.SEVERE, "Properties file, " + propFilename + ", was not found.", ex);
			throw ex;
		}
//		catch(GeneralSecurityException ex)
//		{
//			LOGGER.log(Level.SEVERE, "Password de-encryption failed.", ex);
//			throw ex;
//		}
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
	
	/**
	 * This method loads some of the properties in the properties file.
	 * @author Wilson Melendez
	 * @param propFilename
	 * @throws Exception 
	 * @throws IOException
	 */
	public static void loadProperties(String propFilename) throws Exception
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
			setCsvFileName(prop.getProperty("CsvFileName").trim());
			setBplsFileName(prop.getProperty("BplsFileName").trim());
			setPredictionsFileName(prop.getProperty("PredictionsFileName").trim());
		}
		catch(FileNotFoundException ex)
		{
			LOGGER1.setUseParentHandlers(false);  // Do not print to the console.
			LOGGER1.log(Level.SEVERE, "Properties file, " + propFilename + ", was not found.", ex);
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
					LOGGER1.setUseParentHandlers(false);  // Do not print to the console.
					LOGGER1.log(Level.SEVERE, "InputStream variable, input, could not be closed.", ex);
					throw ex;
				}				
			}
		}
	}
	
	/**
	 * This method converts a string of hexadecimal digits to an array of bytes.
	 * @param s
	 * @return
	 */
	public static byte[] hexStringToByteArray(String s) 
    {
        byte[] b = new byte[s.length() / 2];
        for (int i = 0; i < b.length; i++) 
        {
            int index = i * 2;
            int v = Integer.parseInt(s.substring(index, index + 2), 16);
            b[i] = (byte) v;
        }
        return b;
    }
	
    public static String byteArrayToHexString(byte[] b) 
    {
        StringBuffer sb = new StringBuffer(b.length * 2);
        for (int i = 0; i < b.length; i++) 
        {
            int v = b[i] & 0xff;
            if (v < 16) 
            {
                sb.append('0');
            }
            sb.append(Integer.toHexString(v));
        }
        return sb.toString().toUpperCase();
    }
	
	/**
	 * This method decrypts the password using a keyFile.
	 * @author Wilson Melendez & Paul Harten
	 * @throws GeneralSecurityException
	 */
	public static String decrypt(String message, File keyFile) throws GeneralSecurityException, IOException
	{
		SecretKeySpec sks = getSecretKeySpec(keyFile);
		Cipher cipher = Cipher.getInstance(DBUtil.AES);
		cipher.init(Cipher.DECRYPT_MODE, sks);                
        byte[] decrypted = cipher.doFinal(hexStringToByteArray(message));
        return new String(decrypted);
	}
	
	/**
	 * This method decrypts the password using a keyFile.
	 * @author Paul Harten
	 * @throws IOException
	 */
	public static SecretKeySpec getSecretKeySpec(File keyFile) throws IOException
	{
		byte[] key = readKeyFile(keyFile);
		SecretKeySpec sks = new SecretKeySpec(key, DBUtil.AES);
		return sks;
	}
	
	/**
	 * This method decrypts the password using a keyFile.
	 * @author Paul Harten
	 * @throws IOException
	 */
	public static byte[] readKeyFile(File keyFile) throws FileNotFoundException
	{
		Scanner scanner = new Scanner(keyFile);
		String keyValue = scanner.next();
		scanner.close();
		return hexStringToByteArray(keyValue);
	}
	
	/**
	 * This method encrypts the password using a key.
	 * @author Paul Harten
	 * @throws GeneralSecurityException
	 */
	public static String encrypt(String value, File keyFile) throws GeneralSecurityException, IOException
	{
		if (!keyFile.exists()) {
			KeyGenerator keyGen = KeyGenerator.getInstance(DBUtil.AES);
			keyGen.init(128);;
			SecretKey sk = keyGen.generateKey();
			FileWriter fw = new FileWriter(keyFile);
			fw.write(byteArrayToHexString(sk.getEncoded()));
			fw.flush();
			fw.close();
		}
		SecretKeySpec sks = getSecretKeySpec(keyFile);
		Cipher cipher = Cipher.getInstance(DBUtil.AES);
		cipher.init(Cipher.ENCRYPT_MODE, sks, cipher.getParameters());
		byte[] encrypted = cipher.doFinal(value.getBytes());
		
		return byteArrayToHexString(encrypted);
	}
	
	/**
	 * @author Wilson Melendez
	 * @param connection It is used to establish connection to remote MySQL server.
	 */
	public static void close(Connection connection) throws SQLException
	{
		if (connection != null)
		{
			try
			{
				connection.close();
			}
			catch(SQLException ex)
			{
				LOGGER.log(Level.SEVERE, "Connection could not be closed.", ex);
				throw ex;
			}
		}
	}

	/**
	 * @author Wilson Melendez
	 * @param statement
	 */
	public static void close(Statement statement) throws SQLException
	{
		if (statement != null)
		{
			try
			{
				statement.close();
			}
			catch(SQLException ex)
			{
				LOGGER.log(Level.SEVERE, "Statement could not be closed.", ex);
				throw ex;
			}
		}
	}
	
	/**
	 * @author Wilson Melendez
	 * @param resultset It is an object used to hold results returned by remote MySQL server.
	 */
	public static void close(ResultSet resultset) throws SQLException
	{
		if (resultset != null)
		{
			try
			{
				resultset.close();
			}
			catch(SQLException ex)
			{
				LOGGER.log(Level.SEVERE, "ResultSet could not be closed.", ex);
				throw ex;
			}
		}
	}
	
}
