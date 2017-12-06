/**
 * 
 */
package nanoQSARPredsTests;

import org.junit.Test;

import nanoQSAR_test.Utilities;

/**
 * @author Wmelende
 *
 */
public class utilitiesTests {
	
	/**
	 * This test throws a file-not-found exception upon attempting to open a non-existing properties file.
	 * @throws Exception
	 */
	@Test(expected=Exception.class)
	public void testLoadProperties1() throws Exception
	{
		try
		{
			String propFilename = System.getProperty("user.dir") + "\\fileDoesNotExist.properties"; // Non-existing file.
			Utilities.loadProperties(propFilename);			
		}
		catch (Exception ex)
		{
			throw ex;
		}
		
	}
	
	/**
	 * This test checks that an exception will not be thrown when attempting to read a non-existing property in an existing 
	 * properties file.
	 * @throws Exception
	 */
	@Test
	public void testLoadProperties2() throws Exception
	{
		try
		{
			String propFilename = System.getProperty("user.dir") + "\\nanoQSAR.properties2"; // File with several missing properties.
			Utilities.loadProperties(propFilename);			
		}
		catch (Exception ex)
		{
			throw ex;
		}
		
	}

}
