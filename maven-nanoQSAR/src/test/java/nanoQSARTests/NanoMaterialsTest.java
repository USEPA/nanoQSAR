/**
 * 
 */
package nanoQSARTests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.contentOf;
import static org.junit.Assert.*;

import java.io.File;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.TemporaryFolder;

import datamine.MySqlQuery;
import nanoQSAR.NanoMaterial;
import nanoQSAR.NanoMaterials;

/**
 * @author pharten
 *
 */
public class NanoMaterialsTest {

	/**
	 * Test method for {@link nanoQSAR.NanoMaterials#NanoMaterials(datamine.MySqlQuery)}.
	 */
	@Test
	public final void testNanoMaterials() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link nanoQSAR.NanoMaterials#mineNanoMaterials(datamine.MySqlQuery)}.
	 */
	@Test
	public final void testMineNanoMaterials() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link nanoQSAR.NanoMaterials#writeCsvFile(java.lang.String)}.
	 */
	@Rule
	public TemporaryFolder temporaryFolder = new TemporaryFolder();
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();

	/**
	 * This method checks whether the contents of a CSV file was added correctly.
	 * Test method for {@link CsvFileWriter#writeCsvFile(java.lang.String, java.util.List, java.lang.String[])}.
	 * @author Wilson Melendez & Paul Harten
	 * @throws Exception 
	 */
	@Test
	public void testWriteCsvFile() throws Exception
	{
        File output = temporaryFolder.newFolder("reports").toPath().resolve("output.txt").toFile();
		
		// Get header file information
		MySqlQuery mySqlQuery = new MySqlQuery();
				
		// Create array list of NanoMaterial objects.
		NanoMaterials nanoMaterials = new NanoMaterials();
		nanoMaterials.setHeader(mySqlQuery.getHeader());
	
		NanoMaterial n1 = new NanoMaterial();
				
		n1.setAssayName("cell viability");
	    n1.setCellType("ARPE-19");
		n1.setOrdMaterialID("TiO2-ACROS-21358");
		n1.setSampleName("TiO2-I-142-NONE-0");
		n1.setParticleConcentration(60.0);
		n1.setParticleExposDuration(48.0);
		n1.setLc50(null);
				
		nanoMaterials.add(n1);		
				
		// act
		nanoMaterials.writeCsvFile(output.getPath());
		
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
