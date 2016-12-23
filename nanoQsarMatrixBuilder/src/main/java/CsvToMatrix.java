
import java.io.IOException;

/**
 * 
 */

/**
 * @author Wilson Melendez
 *
 */
public class CsvToMatrix {

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		String filename = null;
		
		if (args == null || args.length == 0)  // Use default CSV file.
		{
			filename = System.getProperty("user.dir") + "\\OutputFile.csv";
			System.out.println("Using default CSV file: " + filename);
		}
		else  // Use command-line specified CSV file.
		{
			filename = args[0].trim();
			System.out.println("Using command-line entered CSV file: " + args[0]);
		}
		
		/* Read in contents of CSV file */
		try
		{
			CsvMatrix.readCsvFile(filename);
		}
		catch(IOException ex)
		{
			ex.printStackTrace();
		}
		
		/* Build matrix */
		CsvMatrix.buildMatrix();
		
		/* Print matrix to standard output */
		Matrix.show();
		

	}

}
