package nanoQSARTests;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

import org.junit.Test;

import junit.framework.Assert;
import nanoQSAR.NanoQSAR;

public class NanoQSARTest {

	@Test
	public final void testMain() {
		NanoQSAR nanoQSAR = new NanoQSAR();
		Assert.assertNotNull("nanoQSAR was null", nanoQSAR);
	}
	
	/**
	 * This test performs the following steps:
	 * 1) it runs the whole application.
	 * 2) it checks whether the CSV file was created.
	 * 3) it checks whether the CSV file is empty or not.
	 * @author Wilson Melendez
	 */
	@Test
	public void testMainProgram1()
	{
		String csvFile = System.getProperty("user.dir") + "\\nanoQSAR.csv";
		File file = new File(csvFile);
		
		if (file.exists()) Assert.assertTrue(csvFile+" was not able to be deleted.",file.delete());
		
		String[] args = null;
		
		/* Run the application. */
		NanoQSAR.main(args);
		
		/* Verify that the CSV file was created and that it's not
		 * empty. */
		file = new File(csvFile);
		Assert.assertTrue("CSV file exists.", file.exists());
		Assert.assertTrue("CSV file is not empty.", file.length() > 0);		
	}
	
	/**
	 * This test performs the following steps:
	 * 1) it runs the whole application.
	 * 2) it checks whether the CSV file was created.
	 * 3) it checks whether the CSV file is empty or not.
	 * @author Paul Harten
	 */
	@Test
	public void testMainProgram2()
	{
		String csvFile = System.getProperty("user.dir") + "\\nanoQSAR.csv";
		File file = new File(csvFile);
		
		if (file.exists()) Assert.assertTrue(csvFile+" was not able to be deleted.",file.delete());
		
		String[] args = new String[0];
		
		/* Run the application. */
		NanoQSAR.main(args);
		
		/* Verify that the CSV file was created and that it's not
		 * empty. */
		file = new File(csvFile);
		assertTrue("CSV file exists.", file.exists());
		assertTrue("CSV file is not empty.", file.length() > 0);		
	}
	
	/**
	 * This test performs the following steps:
	 * 1) it runs the whole application with a property file named.
	 * 2) it checks whether the CSV file was created.
	 * 3) it checks whether the CSV file is empty or not.
	 * @author Paul Harten
	 */
	@Test
	public void testMainProgram3()
	{
		String csvFile = System.getProperty("user.dir") + "\\nanoQSAR.csv";
		File file = new File(csvFile);
		
		if (file.exists()) Assert.assertTrue(csvFile+" was not able to be deleted.",file.delete());
		
		String[] args = {System.getProperty("user.dir") + "\\nanoQSAR.properties"};
		
		/* Run the application. */
		NanoQSAR.main(args);
		
		/* Verify that the CSV file was created and that it's not
		 * empty. */	
		file = new File(csvFile);
		assertTrue("CSV file exists.", file.exists());
		assertTrue("CSV file is not empty.", file.length() > 0);		
	}
	
	/**
	 * This test performs the following steps:
	 * 1) it runs the whole application with a property file named.
	 * 2) it checks whether the CSV file was created.
	 * 3) it checks whether the CSV file is empty or not.
	 * @author Paul Harten
	 */
	@Test
	public void testMainProgram4()
	{

		String[] args = {"-h"};
		
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
			
		/* Run the application. */
		NanoQSAR.main(args);
			
		Assert.assertEquals(NanoQSAR.getHelpString(), outContent.toString());
		
	}
	
	/**
	 * This test performs the following steps:
	 * 1) it runs the whole application with a property file named.
	 * 2) it checks whether the CSV file was created.
	 * 3) it checks whether the CSV file is empty or not.
	 * @author Paul Harten
	 */
	@Test
	public void testMainProgram5()
	{
		String[] args = {System.getProperty("user.dir") + "\\nanoQSAR.properties"};
		
		/* Run the application. */
		NanoQSAR.main(args);
		
		/* Verify that the CSV file was created and that it's not
		 * empty. */
		String csvFile = System.getProperty("user.dir") + "\\nanoQSAR.csv";		
		File file = new File(csvFile);
		assertTrue("CSV file exists.", file.exists());
		assertTrue("CSV file is not empty.", file.length() > 0);		
	}



}
