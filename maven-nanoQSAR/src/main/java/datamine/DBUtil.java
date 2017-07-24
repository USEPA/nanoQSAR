package datamine;

/**
 * @author Wmelende & Paul Harten
 *
 */
import java.nio.file.Paths;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.nio.file.*;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
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
	
	/* Static fields for name of driver, URL of database, username and password. */
	private static String driverName;
	private static String databaseUrl;
	private static String username;
	private static String password;
	private static String csvFileName;
	private static String passwordKey;
	
	/* Need this line to allow logging of error messages */
	private static Logger LOGGER = Logger.getLogger("nanoQSAR");
	
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
	
	/**
	 * This method opens and reads a property file containing database connection
	 * information and the name of the output file.
	 * @author Wilson Melendez
	 * @throws IOException
	 */
	public static void loadProperties(String filename) throws IOException, GeneralSecurityException
	{
		Properties prop = new Properties();
		InputStream input = null;
		
		try
		{
			Path p1 = Paths.get(filename);
			input = new FileInputStream(p1.toString());
			
			// Load properties file
			prop.load(input);
			
			// Get the properties and assign them to their respective fields.
			setDatabaseUrl(prop.getProperty("databaseURL").trim());
			setDriverName(prop.getProperty("DriverName").trim());
			setPassword(prop.getProperty("Password").trim());
			setUsername(prop.getProperty("Username").trim());	
			setCsvFileName(prop.getProperty("CsvFileName").trim());
			setPasswordKey(prop.getProperty("Key").trim());
			
//			encryptPassword();

			/* Decrypt password using the key. */
			decryptPassword();
		}
		catch(IOException ex)
		{
			LOGGER.log(Level.SEVERE, "Properties file, " + filename + ", was not found.", ex);
			throw ex;
		}
		catch(GeneralSecurityException ex)
		{
			LOGGER.log(Level.SEVERE, "Password de-encryption failed.", ex);
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
					LOGGER.log(Level.SEVERE, "InputStream variable, input, could not be closed.", ex);
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
	 * This method decrypts the password using a key.
	 * @author Wilson Melendez
	 * @throws GeneralSecurityException
	 */
	public static void decryptPassword() throws GeneralSecurityException
	{
		String tempkey = getPasswordKey();                
        byte[] decrypted;
        String OriginalPassword;
        Cipher cipher = null;
        byte[] bytekey = hexStringToByteArray(tempkey);
        SecretKeySpec sks = new SecretKeySpec(bytekey, DBUtil.AES);
        
        try 
        {
			cipher = Cipher.getInstance(DBUtil.AES);
		} 
        catch (NoSuchAlgorithmException | NoSuchPaddingException ex) 
        {			
			LOGGER.log(Level.SEVERE,"Attempt to create cipher object failed.",ex);
			throw ex;
		}
		
        try 
        {
			cipher.init(Cipher.DECRYPT_MODE, sks);
		} 
        catch (InvalidKeyException ex) 
        {			
			LOGGER.log(Level.SEVERE,"Initialization of cipher failed.",ex);
			throw ex;
		}
        
		try 
		{
			decrypted = cipher.doFinal(hexStringToByteArray(getPassword()));
		} 
		catch (IllegalBlockSizeException | BadPaddingException ex) 
		{			
			LOGGER.log(Level.SEVERE,"Decryption of password failed.",ex);
			throw ex;
		}
		
		OriginalPassword = new String(decrypted);
        setPassword(OriginalPassword);
	}
	
	/**
	 * This method encrypts the password using a key.
	 * @author Paul Harten
	 * @throws GeneralSecurityException
	 */
	public static void encryptPassword() throws GeneralSecurityException
	{
		String tempkey = getPasswordKey();                
        byte[] encrypted;
        String OriginalPassword = "OriginalPassword";
        Cipher cipher = null;
        byte[] bytekey = hexStringToByteArray(tempkey);
        SecretKeySpec sks = new SecretKeySpec(bytekey, DBUtil.AES);
        
        try 
        {
			cipher = Cipher.getInstance(DBUtil.AES);
		} 
        catch (NoSuchAlgorithmException | NoSuchPaddingException ex) 
        {			
			LOGGER.log(Level.SEVERE,"Attempt to create cipher object failed.",ex);
			throw ex;
		}
		
        try 
        {
			cipher.init(Cipher.ENCRYPT_MODE, sks);
		} 
        catch (InvalidKeyException ex) 
        {			
			LOGGER.log(Level.SEVERE,"Initialization of cipher failed.",ex);
			throw ex;
		}
        
		try 
		{
			encrypted = cipher.doFinal(OriginalPassword.getBytes());
		} 
		catch (IllegalBlockSizeException | BadPaddingException ex) 
		{			
			LOGGER.log(Level.SEVERE,"Encryption of password failed.",ex);
			throw ex;
		}
		
		String encryptedPassword = new String(byteArrayToHexString(encrypted));
        setPassword(encryptedPassword);
        
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
