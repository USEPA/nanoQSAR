package datamineTests;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.channels.FileChannel;

import org.junit.Test;

import junit.framework.Assert;
import nanoQSAR.NanoQSAR;

public class Datamine_NanoQSARTest {
	
	static String helpString = "User options:\njava -jar nanoQSAR -h\njava -jar nanoQSAR\njava -jar nanoQSAR propFilename\n";

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
		String[] args = null; // null string array
		
		/* Run the application. */
		NanoQSAR nanoQSAR = new NanoQSAR(args);
		
		/* Verify that the CSV file was created and that it's not empty. */	
		File file = new File(System.getProperty("user.dir") + "\\nanoQSAR.csv");
		Assert.assertTrue("CSV file exists.", file.exists());
		Assert.assertTrue("CSV file is not empty.", file.length() > 0);
		file.deleteOnExit();
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
		String[] args = new String[0];  // empty string array
		
		/* Run the application. */
		NanoQSAR nanoQSAR = new NanoQSAR(args);
		
		/* Verify that the CSV file was created and that it's not empty. */		
		File file = new File(System.getProperty("user.dir") + "\\nanoQSAR.csv");
		assertTrue("CSV file exists.", file.exists());
		assertTrue("CSV file is not empty.", file.length() > 0);
		file.deleteOnExit();
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
		String[] args = {System.getProperty("user.dir") + "\\nanoQSAR.properties"};
		
		/* Run the application. */
		NanoQSAR nanoQSAR = new NanoQSAR(args);
		
		/* Verify that the CSV file was created and that it's not empty. */	
		File file = new File(System.getProperty("user.dir") + "\\nanoQSAR.csv");
		assertTrue("CSV file exists.", file.exists());
		assertTrue("CSV file is not empty.", file.length() > 0);
		file.deleteOnExit();
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
		NanoQSAR nanoQSAR = new NanoQSAR(args);
			
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
		/* key file must be in same folder as properties file, but with key extension */
		File file1 = new File(System.getProperty("user.dir") + "\\nanoQSAR.properties");
		File file2 = new File(System.getProperty("java.io.tmpdir"), "\\nanoQSAR.properties");
		File file3 = new File(System.getProperty("user.dir") + "\\nanoQSAR.key");
		File file4 = new File(System.getProperty("java.io.tmpdir"), "\\nanoQSAR.key");
		
		try {
			copyFile(file1, file2);
			copyFile(file3, file4);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		String[] args = {file2.getPath()};
		
		/* Run the application. */
		NanoQSAR nanoQSAR = new NanoQSAR(args);
		
		/* Verify that the CSV file was created and that it's not empty. */	
		File file5 = new File(System.getProperty("user.dir") + "\\nanoQSAR.csv");
		assertTrue("CSV file exists.", file5.exists());
		assertTrue("CSV file is not empty.", file5.length() > 0);
		file5.deleteOnExit();
		
		file2.deleteOnExit();
		file4.deleteOnExit();
	}
	
	private static void copyFile(File sourceFile, File destFile) throws IOException {

	    if(!destFile.exists()) destFile.createNewFile();
	    
	    FileInputStream fileInStream = new FileInputStream(sourceFile);
	    FileOutputStream fileOutStream = new FileOutputStream(destFile);

        FileChannel origin = fileInStream.getChannel();
        FileChannel destination = fileOutStream.getChannel();
        long count = 0;
        long size = origin.size();              
        while((count += destination.transferFrom(origin, count, size-count))<size);
        
        fileInStream.close();
        fileOutStream.close();
	}

}
