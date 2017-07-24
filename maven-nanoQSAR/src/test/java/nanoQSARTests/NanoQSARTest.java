package nanoQSARTests;

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

public class NanoQSARTest {
	
	static String helpString = "User options:\nnanoQSAR -h\nnanoQSAR\nnanoQSAR propFilename\n";

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
		
		try {
			copyFile(file1, file2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		String[] args = {file2.getPath()};
		
		/* Run the application. */
		NanoQSAR.main(args);
		
		/* Verify that the CSV file was created and that it's not empty. */
		String csvFile = System.getProperty("user.dir") + "\\nanoQSAR.csv";		
		File file3 = new File(csvFile);
		assertTrue("CSV file exists.", file3.exists());
		assertTrue("CSV file is not empty.", file3.length() > 0);
		
		file2.deleteOnExit();
	}
	
	private static void copyFile(File sourceFile, File destFile) throws IOException {

	    if(!destFile.exists()) destFile.createNewFile();
	    
	    FileChannel origin = null;
	    FileChannel destination = null;

	    try {
	        origin = new FileInputStream(sourceFile).getChannel();
	        destination = new FileOutputStream(destFile).getChannel();
	        long count = 0;
	        long size = origin.size();              
	        while((count += destination.transferFrom(origin, count, size-count))<size);
	    } finally {
            origin.close();
            destination.close();
	    }

	}


}
