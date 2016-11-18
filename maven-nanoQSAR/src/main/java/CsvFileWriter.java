
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.nio.file.*;

import com.opencsv.CSVWriter;

/**
 * This class contains a method for writing data to a CSV file.
 * @author Wilson Melendez
 *
 */
public class CsvFileWriter 
{	
	/**
	 * This method writes the data to a CSV file.  It uses openCSV, which is a CSV
	 * parser library for Java.  It was downloaded via Maven and it can be found under the
	 * Maven dependencies folder. 
	 * @author Wilson Melendez
	 * @param filename  Path and name of CSV file.
	 * @param listNano  List of type NanoMaterial that contains the data to be written to output.
	 * @param headerFile Header of CSV file.
	 * @throws IOException  If file already exists, throw exception.
	 * @return Nothing.
	 */
	public static void writeCsvFile(String filename, List<NanoMaterial> listNano, String[] headerFile) throws IOException
	{
		Path target = Paths.get(filename);
		String[] entries;
		
		try
		{
			if (Files.exists(target)) 
			{
				throw new IOException("File already exists.");
			}
			FileWriter file = new FileWriter(filename);
			
			/* Create an instance of the CSVWriter class and specify the comma as the 
			 * default separator. Default quote character is double quote. */ 
			CSVWriter csvOutput = new CSVWriter(file,CSVWriter.DEFAULT_SEPARATOR);
			
			/* Write header line to CSV file. */
			csvOutput.writeNext(headerFile);   
			
			/* Loop over list of NanoMaterial objects */
			for(NanoMaterial nanoM : listNano)  
			{
				/* Retrieve data as an array of strings and assign array to entries. 
				 * The array represents one record (row of data). */
				entries = nanoM.toStringArray();  
				
				/* Write row of data to output using the writeNext method. */
				csvOutput.writeNext(entries);     
			}
			/* Close the writer. */
			csvOutput.close();   
		}
		finally
		{
			// Nothing to do here.
		}
		
	}

}
