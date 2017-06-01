package datamineTests;

import static org.junit.Assert.*;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import org.junit.Test;

import datamine.DBUtil;
import junit.framework.Assert;

public class DBUtilTest {
	
	String filename = System.getProperty("user.dir") + "\\nanoQSAR.properties";
	
	/* default file values */
	String csvFileName  = "nanoQSAR.csv";
	String databaseURL = "jdbc:mysql://au.epa.gov:3306/dev_naknowbase_v1";
	String DriverName = "com.mysql.jdbc.Driver";
	String Username = "app_naknowbase";
	String Password = "4324C7A7ADFE01BFD11E516F7A07BC2819437790E4FCDDF7C72444536FFA77AA";
	String OriginalPassword = "WgeV8hN938eDJsZX";
	String Key = "38A4AAABA01D0662F19CF52000852BF9";
	
	@Test
	public final void testLoadProperties() {
		
		try {
			
			DBUtil.loadProperties(filename);
			
			Assert.assertEquals(csvFileName, DBUtil.getCsvFileName());
			Assert.assertEquals(databaseURL, DBUtil.getDatabaseUrl());
			Assert.assertEquals(DriverName, DBUtil.getDriverName());
			Assert.assertEquals(Username, DBUtil.getUsername());
			Assert.assertEquals(OriginalPassword, DBUtil.getPassword());
			Assert.assertEquals(Key, DBUtil.getPasswordKey());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (GeneralSecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	public final void testHexStringToByteArray() {
		
		try {
			String test = "abcdeABCDE123456";
			
			byte[] convert = DBUtil.hexStringToByteArray(test);
			
	        byte[] b = new byte[test.length() / 2];
	        for (int i = 0; i < b.length; i++) 
	        {
	            int index = i * 2;
	            int v = Integer.parseInt(test.substring(index, index + 2), 16);
	            b[i] = (byte) v;
	        }
	        
	        Assert.assertEquals(convert.length, b.length); 
	        for (int i=0; i<b.length; i++) {
	        	Assert.assertEquals(convert[i], b[i]);
	        }    
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}

	}

	@Test
	public final void testDecryptPassword() {
		         
        try {
        	
        	DBUtil.loadProperties(filename);
        	Assert.assertEquals(OriginalPassword, DBUtil.getPassword());

		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
	}

}
