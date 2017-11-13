package datamineTests;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Properties;
import java.util.logging.Level;

import org.junit.Test;

import datamine.DBUtil;
import junit.framework.Assert;

public class DBUtilTest {
	
	String propFilename = System.getProperty("user.dir") + "\\nanoQSAR.properties";
	String keyFilename = System.getProperty("user.dir") + "\\nanoQSAR.key";
	String propFilename2 = System.getProperty("user.dir") + "\\nanoQSAR.properties2";
	String keyFilename2 = System.getProperty("user.dir") + "\\nanoQSAR.key2";
	
	/* default file values */
	String CsvFileName  = "nanoQSAR.csv";
	String databaseURL = "jdbc:mysql://mysql-res1.epa.gov:3306/dev_naknowbase_v1";
	String DriverName = "com.mysql.jdbc.Driver";
	String Username = "app_naknowbase";
	String Password = "AABE19899036AE8D8C349434FC44B0AB814019C1BBEE8C8D5BAFA84A4C8FAC54";
	String OriginalPassword = "OriginalPassword";
	String Key = "9D9AAB428E9FAD22D9DBEF323F7AACD0";
	
	@Test
	public final void testStoreAndLoadProperties() {
		
		try {

			Properties p1 = new Properties();
			p1.put("CsvFileName", CsvFileName);
			p1.put("databaseURL", databaseURL);
			p1.put("DriverName", DriverName);
			p1.put("Username", Username);
			String encryptedPassword = DBUtil.encrypt(OriginalPassword, new File(keyFilename2));
			p1.put("Password", encryptedPassword);
			p1.store(new FileWriter(propFilename2), "");
			
			Properties p2 = new Properties();
			
			p2.load(new FileReader(propFilename2));
			encryptedPassword = p2.getProperty("Password");
			Assert.assertEquals(OriginalPassword, DBUtil.decrypt(encryptedPassword,  new File(keyFilename2)));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (GeneralSecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	@Test
	public final void testLoadProperties() {
		
		try {
			
			DBUtil.loadProperties(propFilename, keyFilename);
			
			Assert.assertEquals(CsvFileName, DBUtil.getCsvFileName());
			Assert.assertEquals(databaseURL, DBUtil.getDatabaseUrl());
			Assert.assertEquals(DriverName, DBUtil.getDriverName());
			Assert.assertEquals(Username, DBUtil.getUsername());
			String message = DBUtil.getPassword();
			Assert.assertEquals(Password, message);
			message = DBUtil.getPasswordKey();
			Assert.assertEquals(Key, message);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (GeneralSecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	public final void testHexStringToByteArrayandBack() {
		
		try {
			String test = "abcdeABCDE123456";
			
			byte[] b = DBUtil.hexStringToByteArray(test);
			
	        String test2 = DBUtil.byteArrayToHexString(b);
	        
	        Assert.assertEquals(test.length(), test2.length());
	        
	        Assert.assertEquals(test.toUpperCase(), test2); 

		} catch (Exception e) {
			
			e.printStackTrace();
			
		}

	}

	@Test
	public final void testDecryptPassword() {
		         
        try {
        	
        	DBUtil.loadProperties(propFilename2, keyFilename2);
        	String decrypted = DBUtil.decrypt(DBUtil.getPassword(), new File(keyFilename2));
        	Assert.assertEquals(OriginalPassword, decrypted);

		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
	}

}
