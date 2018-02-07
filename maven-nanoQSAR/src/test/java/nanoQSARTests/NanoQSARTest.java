package nanoQSARTests;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import org.junit.Test;

import junit.framework.Assert;
import nanoQSAR.NanoQSAR;

public class NanoQSARTest {
	
	static String helpString = "User options:\njava -jar nanoQSAR -h\njava -jar nanoQSAR\njava -jar nanoQSAR propFilename\n";

	@Test
	public final void testMain() {
		NanoQSAR.main();
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
		
		String[] args = null;
		
		/* Run the application. */
		NanoQSAR.main(args);
		
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
	 * @author Paul Harten
	 */
	@Test
	public void testMainProgram2()
	{
		String csvFile = System.getProperty("user.dir") + "\\nanoQSAR.csv";
		
		String[] args = new String[0];
		
		/* Run the application. */
		NanoQSAR.main(args);
		
		/* Verify that the CSV file was created and that it's not
		 * empty. */
		File file = new File(csvFile);
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
		
		String[] args = {System.getProperty("user.dir") + "\\nanoQSAR.properties"};
		
		/* Run the application. */
		NanoQSAR.main(args);
		
		/* Verify that the CSV file was created and that it's not
		 * empty. */	
		File file = new File(csvFile);
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
			
		Assert.assertEquals(helpString, outContent.toString());
		
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
		File file1 = new File(System.getProperty("user.dir") + "\\nanoQSAR.properties");
		File file2 = new File(System.getProperty("java.io.tmpdir"), "\\nanoQSAR.properties");
		File file3 = new File(System.getProperty("user.dir") + "\\nanoQSAR.key");
		File file4 = new File(System.getProperty("java.io.tmpdir"), "\\nanoQSAR.key");
		
		try {
			Files.copy(file1.toPath(), file2.toPath(),StandardCopyOption.REPLACE_EXISTING);
			Files.copy(file3.toPath(), file4.toPath(),StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		String[] args = {file2.getPath()};
		
		/* Run the application. */
		NanoQSAR.main(args);
		
		/* Verify that the CSV file was created and that it's not empty. */
		String csvFile = System.getProperty("user.dir") + "\\nanoQSAR.csv";		
		File file5 = new File(csvFile);
		assertTrue("CSV file exists.", file5.exists());
		assertTrue("CSV file is not empty.", file5.length() > 0);
		
		file2.deleteOnExit();
		file4.deleteOnExit();
	}

}
