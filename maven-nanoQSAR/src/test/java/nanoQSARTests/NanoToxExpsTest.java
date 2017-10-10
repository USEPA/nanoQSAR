/**
 * 
 */
package nanoQSARTests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.contentOf;
import static org.junit.Assert.*;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.TemporaryFolder;

import datamine.DBUtil;
import datamine.MySqlQuery;
import junit.framework.Assert;
import nanoQSAR.NanoToxExp;
import nanoQSAR.NanoToxExps;

/**
 * @author pharten
 *
 */
public class NanoToxExpsTest {
	
	static String csvFilename = System.getProperty("user.dir") + "\\nanoQSAR.csv";

	/**
	 * Test method for {@link nanoQSAR.NanoToxExps#NanoMaterials()}.
	 * @throws Exception 
	 */
	@Test
	public final void testNanoToxExpsConstructor1() throws Exception {
		NanoToxExps nanoToxExps = new NanoToxExps();
		Assert.assertNotNull("NanoToxExps Constructer failed", nanoToxExps);
		Assert.assertTrue("NanoToxExps Constructor is not zero size", nanoToxExps.size()==0);
	}
	/**
	 * Test method for {@link nanoQSAR.NanoToxExps#NanoMaterials(datamine.MySqlQuery)}.
	 * @throws Exception 
	 */
	@Test
	public final void testNanoToxExpsConstructor2() throws Exception {
		NanoToxExps nanoToxExps = new NanoToxExps(csvFilename);
		Assert.assertNotNull("NanoToxExps Constructer failed", nanoToxExps);
		Assert.assertFalse("NanoToxExps Constructor is zero size", nanoToxExps.size()==0);
	}

	/**
	 * Test method for {@link nanoQSAR.NanoToxExps#writeCsvFile(java.lang.String)}.
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
				
		// Create array list of NanoToxExp objects.
		NanoToxExps nanoToxExps = new NanoToxExps();

		nanoToxExps.setHeader(mySqlQuery.getHeader());
	
		NanoToxExp n1 = new NanoToxExp();
				
		n1.setAssayName("cell viability");
	    n1.setCellType("ARPE-19");
		n1.setOrdMaterialID("TiO2-ACROS-21358");
		n1.setSampleName("TiO2-I-142-NONE-0");
		n1.setParticleConcentration(60.0);
		n1.setParticleExposDuration(48.0);
		n1.setLc50(10.0);
				
		nanoToxExps.add(n1);		
				
		// act
		nanoToxExps.writeCsvFile(output.getPath());
		
		// assert some header information
		assertThat(contentOf(output)).contains("ORDMaterialID");
		
		// assert
		assertThat(contentOf(output)).contains("cell viability");
		assertThat(contentOf(output)).contains("TiO2-I-142-NONE-0");
		assertThat(contentOf(output)).contains("ARPE-19");
		assertThat(contentOf(output)).contains("60.0");
		assertThat(contentOf(output)).contains("48.0");
		assertThat(output).hasExtension("txt").hasParent(resolvePath("reports"));		
	}
	
	/**
	 * This method checks whether the contents of a CSV file was read correctly.
	 * Test method for {@link CsvFileReader#readCsvFile(java.lang.String, java.util.List, java.lang.String[])}.
	 * @author Paul Harten
	 * @throws Exception 
	 */
	@Test
	public void testReadCsvFile() throws Exception
	{
				
		// Create NanoToxExps object */.
		NanoToxExps nanoToxExps = new NanoToxExps();

		/* read in nanomaterials from CVS file */
		nanoToxExps.readCsvFile(csvFilename);

		/* write nanoToxExps out to temporary output file */
		String csvOutput  = temporaryFolder.newFolder("reports").toPath().resolve("output.csv").toString();
	    nanoToxExps.writeCsvFile(csvOutput);
	    
		// Create NanoToxExps object */.
		NanoToxExps nanoMaterials2 = new NanoToxExps();
		nanoMaterials2.readCsvFile(csvOutput);
	    
	    Assert.assertTrue("nanoMateials are not the same", nanoToxExps.isSame(nanoMaterials2));
		
	}
	
	private String resolvePath(String folder)
	{
		return temporaryFolder.getRoot().toPath().resolve(folder).toString();
	}
	
	@Test
	public void testSerialize() throws Exception
	{
				
		// Create NanoToxExps object */.
		NanoToxExps nanoToxExps = new NanoToxExps();

		/* read in nanomaterials from CVS file */
		nanoToxExps.readCsvFile(csvFilename);

		/* write nanoToxExps out to temporary output file */
		String serOutput  = temporaryFolder.newFolder("reports").toPath().resolve("nanoToxExps.ser").toString();
		FileOutputStream fileOut = new FileOutputStream(serOutput);
		ObjectOutputStream out = new ObjectOutputStream(fileOut);
		out.writeObject(nanoToxExps);
		out.close();
		fileOut.close();
		
        FileInputStream fileIn = new FileInputStream(serOutput);
        ObjectInputStream in = new ObjectInputStream(fileIn);
        NanoToxExps nanoMaterials2 = (NanoToxExps)in.readObject();
        in.close();
        fileIn.close();
	    
	    Assert.assertTrue("nanoMateials are not the same", nanoToxExps.isSame(nanoMaterials2));
		
	}
	
	@Test
	public final void testClone() throws Exception {
		
		// Create NanoToxExps object */.
		NanoToxExps nanoToxExps = new NanoToxExps();

		/* read in nanomaterials from CVS file */
		nanoToxExps.readCsvFile(csvFilename);
		
		NanoToxExps clone = nanoToxExps.clone();
		
		Assert.assertNotNull("NanoToxExps clone is null", clone);
		Assert.assertNotSame("NanoToxExps clone is same object", (Object)nanoToxExps, (Object)clone);
		
		Assert.assertTrue("NanoToxExps clone isSame as nanoMaterial", nanoToxExps.isSame(clone));
		
	}



}
