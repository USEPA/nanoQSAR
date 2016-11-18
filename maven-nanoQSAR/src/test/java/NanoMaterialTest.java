import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


/**
 * Junit test for NanoMaterial class.
 * @author Wmelende
 *
 */
public class NanoMaterialTest {

	/**
	 * This method checks whether data was entered correctly in string array.
	 */
	@Test
	public void toStringArraytest() {
		String [] entries;
		NanoMaterial n1 = new NanoMaterial();
		
		n1.setAssayName("cell viability");
		n1.setCellType("ARPE-19");
		n1.setOrdMaterialID("TiO2-ACROS-21358");
		n1.setSampleName("TiO2-I-142-NONE-0");
		n1.setParticleConcentration(0.0);
		n1.setParticleExposDuration(48.0);
		n1.setLc50(4.5);
		
		entries = n1.toStringArray();
		assertEquals("cell viability",entries[2]);
		assertEquals("ARPE-19",entries[6]);
		assertEquals("TiO2-ACROS-21358",entries[37]);
		assertEquals("0.0",entries[18]);
		assertEquals("48.0",entries[20]);
		assertEquals("4.5",entries[33]);
	}

}
