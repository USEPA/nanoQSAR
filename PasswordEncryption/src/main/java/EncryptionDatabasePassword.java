/**
 * 
 */
import java.io.IOException;
import java.security.GeneralSecurityException;

import org.apache.commons.configuration.ConfigurationException;


/**
 * @author Wilson Melendez
 *
 */
public class EncryptionDatabasePassword 
{

	/**
	 * This is main method of the program. The program generates a random key, reads a password
	 * from a properties file, uses the key to encrypt the password, and writes the key and the
	 * encrypted password to the same properties file.
	 * @author Wilson Melendez
	 * @param args
	 * 
	 */
	public static void main(String[] args)
	{        
        String filename;
        String outputFile = System.getProperty("user.dir") + "\\nanoQSAR.properties";
        
        if (args == null || args.length == 0)  // Use default properties file.
		{
			filename = System.getProperty("user.dir") + "\\nanoQSAR_Original.properties";
			System.out.println("Using default properties file: " + filename);
		}
		else  // Use command-line specified properties file.
		{
			filename = args[0].trim();
			System.out.println("Using command-line entered properties file: " + args[0]);
		}
        
        try
        {
        	/* Generate key */
        	KeyGenerationPasswordEncryption.generatePasswordKey();  
        	
        	/* Load properties file. */
        	PropertiesInformation.loadProperties(filename);
        	
        	/* Encrypt password */
        	KeyGenerationPasswordEncryption.encryptPassword();
        	
        	/* Write encrypted password and key to properties file.*/
        	PropertiesInformation.writePasswordKey(outputFile);
        	
        }
        catch(IOException | GeneralSecurityException | ConfigurationException ex)
        {
        	ex.printStackTrace();
        }

	}

}
