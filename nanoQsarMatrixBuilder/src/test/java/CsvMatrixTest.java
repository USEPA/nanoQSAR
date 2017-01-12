
import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Test;


/**
 * @author Wilson Melendez
 *
 */
public class CsvMatrixTest {

	/**
	 * Test method for {@link CsvMatrix#readCsvFile(java.lang.String)}.
	 */
	@Test(expected=FileNotFoundException.class)
	public void testReadCsvFile() throws FileNotFoundException, IOException
	{
		try
		{
			String filename = "test.csv";
			CsvMatrix.readCsvFile(filename);
		}
		catch(FileNotFoundException ex)
		{
			throw ex;
		}
		catch(IOException ex)
		{
			throw ex;
		}
	}

	/**
	 * Test method for {@link CsvMatrix#buildMatrices()}.
	 */
	@Test(expected=NullPointerException.class)
	public void testBuildMatrices() 
	{
		CsvMatrix.buildMatrices();
	}

}
