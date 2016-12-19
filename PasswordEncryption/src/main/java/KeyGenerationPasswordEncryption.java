import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * 
 */

/**
 * @author Wilson Melendez
 *
 */
public class KeyGenerationPasswordEncryption 
{
    /* Name of encryption algorithm.  AES stands for Advanced Encryption Standard. */
	public static final String AES = "AES";
	
	/**
	 * This method converts an array of bytes to a string of hexadecimal digits.
	 * @param b
	 * @return String
	 */
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
     * This method converts a string of hexadecimal digits to an array of bytes.
     * @param s
     * @return byte[]
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
    
    public static void generatePasswordKey() throws NoSuchAlgorithmException 
    {
    	KeyGenerator keyGen = null;
    	
		try 
		{
			keyGen = KeyGenerator.getInstance(KeyGenerationPasswordEncryption.AES);
		} 
		catch (NoSuchAlgorithmException e) 
		{
			System.out.println("Attempt to creat keyGen object failed.");
			throw e;
		}
		
		/* Generate 128-bit key */
        keyGen.init(128);  
        SecretKey sk = keyGen.generateKey();
        
        /* Convert key to a string and store it. */
        String key = byteArrayToHexString(sk.getEncoded());
        PropertiesInformation.setKey(key);
        System.out.println("key:" + key);
    }
    
    /**
     * @author Wilson Melendez
     * @throws GeneralSecurityException
     */
    public static void encryptPassword() throws GeneralSecurityException 
    {
    	String password = PropertiesInformation.getPassword();
        String key = PropertiesInformation.getKey();
        byte[] bytekey = hexStringToByteArray(key);        
        SecretKeySpec sks = new SecretKeySpec(bytekey, KeyGenerationPasswordEncryption.AES);
        Cipher cipher = null;
        
		try 
		{
			cipher = Cipher.getInstance(KeyGenerationPasswordEncryption.AES);
		} 
		catch (NoSuchAlgorithmException | NoSuchPaddingException e) 
		{
			System.out.println("Attempt to creat cipher object failed.");
			throw e;
		}
			
        try 
        {
			cipher.init(Cipher.ENCRYPT_MODE, sks, cipher.getParameters());
		} 
        catch (InvalidKeyException | InvalidAlgorithmParameterException e) 
        {
			System.out.println("Attempt to initialize cipher object failed.");
			throw e;
		}
            
        byte[] encrypted = null;
		try 
		{
			encrypted = cipher.doFinal(password.getBytes());
		} 
		catch (IllegalBlockSizeException | BadPaddingException e) 
		{
			System.out.println("Attempt to encrypt password failed.");
			throw e;
		}
			
        String encryptedpwd = byteArrayToHexString(encrypted);           
        PropertiesInformation.setPassword(encryptedpwd);
               
    }
    
    /**
     * @author Wilson Melendez
     * @throws GeneralSecurityException
     */
    public static void decryptPassword() throws GeneralSecurityException
    {
    	String tempkey = PropertiesInformation.getKey();
    	String encryptedpwd = PropertiesInformation.getPassword();
        byte[] bytekey = KeyGenerationPasswordEncryption.hexStringToByteArray(tempkey);
        SecretKeySpec sks = new SecretKeySpec(bytekey, KeyGenerationPasswordEncryption.AES);
        Cipher cipher = null;
        
		try {
			cipher = Cipher.getInstance(KeyGenerationPasswordEncryption.AES);
		} catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
			System.out.println("AES does not exist.");
			throw e;
		}
		
        try {
			cipher.init(Cipher.DECRYPT_MODE, sks);
		} catch (InvalidKeyException e) {
			System.out.println("Initialization of cipher failed.");
			throw e;
		}
        
        byte[] decrypted;
		try {
			decrypted = cipher.doFinal(hexStringToByteArray(encryptedpwd));
		} catch (IllegalBlockSizeException | BadPaddingException e) {
			System.out.println("Decryption of password failed.");
			throw e;
		}
		
        String OriginalPassword = new String(decrypted);
        
        System.out.println("****************  Original Password  ****************");
        System.out.println(OriginalPassword);
        System.out.println("****************  Original Password  ****************");
    }

}
