package nanoQSARTests;

import static org.junit.Assert.*;

import java.io.File;

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
	public void testMainProgram()
	{
		String[] args = null;
		/* Run the application. */
		NanoQSAR nanoQSAR = new NanoQSAR();
		nanoQSAR.main(args);
		
		/* Verify that the CSV file was created and that it's not
		 * empty. */
		String csvFile = System.getProperty("user.dir") + "\\nanoQSAR.csv";		
		File file = new File(csvFile);
		assertTrue("CSV file exists.", file.exists());
		assertTrue("CSV file is not empty.", file.length() > 0);		
	}


}
