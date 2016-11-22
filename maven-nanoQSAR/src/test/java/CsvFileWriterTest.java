import static org.junit.Assert.*;
import static org.assertj.core.api.Assertions.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class tests the class that handles the creation of the CSV file.
 * @author Wilson Melendez
 *
 */
public class CsvFileWriterTest {
	
	@Rule
	public TemporaryFolder temporaryFolder = new TemporaryFolder();
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();

	/**
	 * This method will throw an exception if the file already exists.  It will verify
	 * that the exception is the right one.
	 * @throws IOException
	 */
	@Test
	public void throwsErrorWhenTargetFileExists() throws IOException
	{
		// Create temporary file
		File output = temporaryFolder.newFile("output.txt");
		
		
		// Set up the exception that we are expecting to get.
		thrown.expect(IOException.class);
		thrown.expectMessage("File already exists.");
		
		// Get header file information
		String[] headerFile = NanoMaterial.getHeaderFile();
		
		// Create array list of NanoMaterial objects.
		List<NanoMaterial> list = new ArrayList<NanoMaterial>();;
		NanoMaterial n1 = new NanoMaterial();
		
		n1.setAssayName("cell viability");
		n1.setCellType("ARPE-19");
		n1.setOrdMaterialID("TiO2-ACROS-21358");
		n1.setSampleName("TiO2-I-142-NONE-0");
		n1.setParticleConcentration(0);
		n1.setParticleExposDuration(48.0);
		n1.setLc50(null);
		
		list.add(n1);		
		
		// Act
		CsvFileWriter.writeCsvFile(output.getPath(), list, headerFile);
	}
	
	/**
	 * This method checks whether the contents of a CSV file was added correctly.
	 * @author Wilson Melendez
	 * @throws IOException
	 */
	@Test
	public void writesContentToFile() throws IOException
	{
		File output = temporaryFolder.newFolder("reports").toPath().resolve("output.txt").toFile();
		
		// Get header file information
		String[] headerFile = NanoMaterial.getHeaderFile();
				
		// Create array list of NanoMaterial objects.
		List<NanoMaterial> list = new ArrayList<NanoMaterial>();;
		NanoMaterial n1 = new NanoMaterial();
				
		n1.setAssayName("cell viability");
	    n1.setCellType("ARPE-19");
		n1.setOrdMaterialID("TiO2-ACROS-21358");
		n1.setSampleName("TiO2-I-142-NONE-0");
		n1.setParticleConcentration(60.0);
		n1.setParticleExposDuration(48.0);
		n1.setLc50(null);
				
		list.add(n1);		
				
		// act
		CsvFileWriter.writeCsvFile(output.getPath(), list, headerFile);
		
		// assert
		assertThat(contentOf(output)).contains("cell viability");
		assertThat(contentOf(output)).contains("TiO2-I-142-NONE-0");
		assertThat(contentOf(output)).contains("ARPE-19");
		assertThat(contentOf(output)).contains("60.0");
		assertThat(contentOf(output)).contains("48.0");
		assertThat(output).hasExtension("txt").hasParent(resolvePath("reports"));		
		
	}

	private String resolvePath(String folder)
	{
		return temporaryFolder.getRoot().toPath().resolve(folder).toString();
	}
	
}
