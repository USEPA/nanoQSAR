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
//	public static String propFilename = System.getProperty("user.dir") + "\\nanoQSAR.properties";
//	public static String keyFilename = System.getProperty("user.dir") + "\\nanoQSAR.key";
	
	/* Static fields for name of driver, URL of database, username and password. */
	private String driverName;
	private String databaseUrl;
	private String username;
	private String password;
	private String passwordKey;
	private String csvFileName;
	private String trainFileName;
	private String testFileName;
	private String bplsFileName;
	private String PredictionsFileName;
	
	/* Need this line to allow logging of error messages */
	private static Logger LOGGER = Logger.getLogger("nanoQSAR");
	
	/* Need this line to allow logging of error messages */
	private static Logger LOGGER1 = Logger.getLogger("nanoQSAR_TEST");
	
	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	public String getDatabaseUrl() {
		return databaseUrl;
	}

	public void setDatabaseUrl(String databaseUrl) {
		this.databaseUrl = databaseUrl;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPasswordKey() {
		return passwordKey;
	}

	public void setPasswordKey(String passwordKey) {
		this.passwordKey = passwordKey;
	}

	public String getCsvFileName() {
		return csvFileName;
	}

	public void setCsvFileName(String csvFileName) {
		this.csvFileName = csvFileName;
	}
	
	public String getTrainFileName() {
		return trainFileName;
	}

	public void setTrainFileName(String trainFileName) {
		this.trainFileName = trainFileName;
	}

	public String getTestFileName() {
		return testFileName;
	}

	public void setTestFileName(String testFileName) {
		this.testFileName = testFileName;
	}

	public String getBplsFileName() {
		return bplsFileName;
	}

	public void setBplsFileName(String bplsFileName) {
		this.bplsFileName = bplsFileName;
	}

	public String getPredictionsFileName() {
		return PredictionsFileName;
	}

	public void setPredictionsFileName(String predictionsFileName) {
		this.PredictionsFileName = predictionsFileName;
	}
	
	/**
	 * This method opens and reads a property file containing database connection
	 * information and the name of the output file.
	 * @author Wilson Melendez
	 * @throws IOException
	 */
	public void loadProperties(String propFilename, String keyFilename) throws IOException, GeneralSecurityException
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
	 * @throws IOException 
	 * @throws Exception 
	 * @throws IOException
	 */
	public void loadProperties(String propFilename) throws IOException
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
			String trainFileName = prop.getProperty("TrainFileName");
			setTrainFileName(trainFileName.trim());
			setTestFileName(prop.getProperty("TestFileName").trim());
			setBplsFileName(prop.getProperty("BplsFileName").trim());
			setPredictionsFileName(prop.getProperty("PredictionsFileName").trim());

		}
		catch(FileNotFoundException ex)
		{
			LOGGER1.setUseParentHandlers(false);  // Do not print to the console.
			LOGGER1.log(Level.SEVERE, "Properties file, " + propFilename + ", was not found.", ex);
			throw ex;
		}
		catch (IOException ex) {
			// TODO Auto-generated catch block
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
