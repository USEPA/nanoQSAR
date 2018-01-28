/**
 * 
 */
package predictionTests;

import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import org.junit.Test;

import junit.framework.Assert;
import nanoQSAR.NanoQSAR;
import prediction.NanoQSAR_PRED;

/**
 * @author Wmelende
 *
 */
public class NanoQSAR_PredTests {
	
	static String helpString = "User options:\njava -jar nanoQSAR_Test -h\njava -jar nanoQSAR_Test\njava -jar nanoQSAR_Test propFilename\n";

	@Test
	public final void testMain() {
		NanoQSAR_PRED nanoQSAR_pred = new NanoQSAR_PRED();
		Assert.assertNotNull("nanoQSAR_pred was null", nanoQSAR_pred);
	}
	
	/**
	 * This test performs the following steps:
	 * 1) it runs the whole application.
	 * 2) it checks whether the CSV file was created.
	 * 3) it checks whether the CSV file is empty or not.
	 * @author Wilson Melendez
	 */
	@Test
	public void testMainProgam1()
	{
		String csvFile = System.getProperty("user.dir") + "\\nanoQSAR_Predictions.csv";
		
		String[] args = null;
		
		/* Run the application. */
		NanoQSAR_PRED nanoQSAR_pred = new NanoQSAR_PRED(args);
		
		/* Verify that the CSV file was created and that it's not
		 * empty. */
		File file = new File(csvFile);
		Assert.assertTrue("CSV file exists.", file.exists());
		Assert.assertTrue("CSV file is not empty.", file.length() > 0);		
	}

	/**
	 * This test performs the following steps:
	 * 1) it runs the whole application.
	 * 2) it checks whether the CSV file was created.
	 * 3) it checks whether the CSV file is empty or not.
	 * @author Wilson Melendez
	 */
	@Test
	public void testMainProgam2()
	{
		String csvFile = System.getProperty("user.dir") + "\\nanoQSAR_Predictions.csv";
		
		String[] args = new String[0];
		
		/* Run the application. */
		NanoQSAR_PRED nanoQSAR_pred = new NanoQSAR_PRED(args);
		
		/* Verify that the CSV file was created and that it's not
		 * empty. */
		File file = new File(csvFile);
		Assert.assertTrue("CSV file exists.", file.exists());
		Assert.assertTrue("CSV file is not empty.", file.length() > 0);		
	}
	
	/**
	 * This test passes the properties file as an argument to the main program, and it checks
	 * whether the CSV file was created and is non-empty.
	 * @author Wilson Melendez
	 */
	@Test
	public void testMainProgram3()
	{
		String csvFile = System.getProperty("user.dir") + "\\nanoQSAR_Predictions.csv";
		
		String[] args = {System.getProperty("user.dir") + "\\nanoQSAR.properties"};
		
		/* Run the application. */
		NanoQSAR_PRED nanoQSAR_pred = new NanoQSAR_PRED(args);
		
		/* Verify that the CSV file was created and that it's not empty. */	
		File file = new File(csvFile);
		assertTrue("CSV file exists.", file.exists());
		assertTrue("CSV file is not empty.", file.length() > 0);		
	}
	
	/**
	 * This test verifies that the "-h" option passed as args to the main program generates
	 * the helpString message.
	 * @author Wilson Melendez
	 */
	@Test
	public void testMainProgram4()
	{
		String[] args = {"-h"};
		
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
			
		/* Run the application. */
		NanoQSAR_PRED nanoQSAR_pred = new NanoQSAR_PRED(args);
			
		Assert.assertEquals(helpString, outContent.toString());		
	}
	
	/**
	 * This test performs the following steps:
	 * 1) it runs the whole application with a property file named.
	 * 2) it checks whether the CSV file was created.
	 * 3) it checks whether the CSV file is empty or not.
	 * @author Wilson Melendez
	 */
	@Test
	public void testMainProgram5()
	{
		File file1 = new File(System.getProperty("user.dir") + "\\nanoQSAR.properties");
		File file2 = new File(System.getProperty("java.io.tmpdir"), "\\nanoQSAR.properties");
		String csvFile = System.getProperty("user.dir") + "\\nanoQSAR_Predictions.csv";		
		
		try 
		{
			Files.copy(file1.toPath(), file2.toPath(),StandardCopyOption.REPLACE_EXISTING);
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
				
		String[] args = {file2.getPath()};
		
		/* Run the application. */
		NanoQSAR_PRED nanoQSAR_pred = new NanoQSAR_PRED(args);
		
		/* Verify that the CSV file was created and that it's not empty. */
		File file3 = new File(csvFile);
		assertTrue("CSV file exists.", file3.exists());
		assertTrue("CSV file is not empty.", file3.length() > 0);
		
		file2.deleteOnExit();
	}
	
}
