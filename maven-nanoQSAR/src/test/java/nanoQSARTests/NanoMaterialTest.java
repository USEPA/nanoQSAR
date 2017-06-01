/**
 * 
 */
package nanoQSARTests;

import static org.junit.Assert.*;

import org.junit.Test;

import junit.framework.Assert;
import nanoQSAR.NanoMaterial;

/**
 * @author pharten
 *
 */
public class NanoMaterialTest {

	@Test
	public final void testNanoMaterial() throws Exception {
		NanoMaterial nanoMaterial = new NanoMaterial();
		Assert.assertNotNull("NanoMaterial Constructer failed", nanoMaterial);
	}

	/**
	 * This method checks whether data were entered correctly into string array.
	 * @author Wilson Melendez
	 * @throws Exception 
	 */
	@Test
	public void testStoreDataAsStringArray() throws Exception {
		String [] entries;
		NanoMaterial n1 = new NanoMaterial();
		
		n1.setAssayName("cell viability");
		n1.setCellType("ARPE-19");
		n1.setOrdMaterialID("TiO2-ACROS-21358");
		n1.setSampleName("TiO2-I-142-NONE-0");
		n1.setParticleConcentration(0.0);
		n1.setParticleExposDuration(48.0);
		n1.setLc50(4.5);
		
		entries = n1.storeDataAsStringArray();
		assertEquals("cell viability",entries[127]);
		assertEquals("ARPE-19",entries[131]);
		assertEquals("TiO2-ACROS-21358",entries[1]);
		assertEquals("0.0",entries[155]);
		assertEquals("48.0",entries[157]);
		assertEquals("4.5",entries[170]);
	}

}
