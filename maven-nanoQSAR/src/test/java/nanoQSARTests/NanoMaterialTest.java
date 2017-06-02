/**
 * 
 */
package nanoQSARTests;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;

import org.junit.Assert;
import org.junit.Test;

import nanoQSAR.NanoMaterial;

/**
 * @author pharten
 *
 */
public class NanoMaterialTest {

	@Test
	public void testNanoMaterial() {
		
		try {

			NanoMaterial nanoMaterial = new NanoMaterial();
			Assert.assertNotNull("NanoMaterial Constructer failed", nanoMaterial);
			
		} catch (AssertionError e) {
			
			Assert.fail("Exception was thrown: " + e);
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	/**
	 * This method checks whether data were entered correctly into string array.
	 * @author Wilson Melendez
	 * @throws Exception 
	 */
	@Test
	public void testStoreDataAsStringArray() {
		String [] entries;
		NanoMaterial n1=null;
		
		try {
			n1 = new NanoMaterial();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		n1.setAssayName("cell viability");
		n1.setCellType("ARPE-19");
		n1.setOrdMaterialID("TiO2-ACROS-21358");
		n1.setSampleName("TiO2-I-142-NONE-0");
		n1.setParticleConcentration(0.0);
		n1.setParticleExposDuration(48.0);
		n1.setLc50(4.5);
		
		entries = n1.storeDataAsStringArray();
		Assert.assertEquals("cell viability",entries[127]);
		Assert.assertEquals("bad","ARPE-19",entries[131]);
		Assert.assertEquals("TiO2-ACROS-21358",entries[1]);
		Assert.assertEquals("0.0",entries[155]);
		Assert.assertEquals("48.0",entries[157]);
		Assert.assertEquals("4.5",entries[170]); 
		
	}
	
	/**
	 * This test checks that each property of nanomaterial and its clone are equal
	 * @author Paul Harten
	 * @throws Exception 
	 */
	@Test
	public final void testClone() throws Exception {
		
		NanoMaterial nanoMaterial = new NanoMaterial();
		nanoMaterial.setAssayName("cell viability");
		nanoMaterial.setCellType("ARPE-19");
		nanoMaterial.setOrdMaterialID("TiO2-ACROS-21358");
		nanoMaterial.setSampleName("TiO2-I-142-NONE-0");
		nanoMaterial.setParticleConcentration(0.0);
		nanoMaterial.setParticleExposDuration(48.0);
		nanoMaterial.setLc50(4.5);
		
		NanoMaterial clone = nanoMaterial.clone();
		
		Assert.assertNotNull("NanoMaterial clone is null", clone);
		Assert.assertNotSame("NanoMaterial clone is same object", nanoMaterial, clone);
		
		/* execute AssertEquals() on each property of nanomaterial and clone */
		final BeanInfo beanInfo = Introspector.getBeanInfo(nanoMaterial.getClass());
        final PropertyDescriptor[] properties = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor property : properties) {
    		Object v1 = property.getReadMethod().invoke(nanoMaterial, (Object[])null);
    		Object v2 = property.getReadMethod().invoke(clone, (Object[])null);
    		Assert.assertEquals(property.getDisplayName()+" not equal: ", v1, v2);
        }
		
	}

	/*
	private void testProperty(@Nonnull final PropertyDescriptor property) throws IllegalAccessException,
	InstantiationException,	InvocationTargetException {
		final Object target = type.newInstance();
		final Object setValue = Defaults.defaultValue(property.getPropertyType());

		final Method getter = property.getReadMethod();
		final Method setter = property.getWriteMethod();

		setter.invoke(target, setValue);
		final Object getValue = getter.invoke(target);

		assertEquals(
				property.getDisplayName() + " getter / setter do not produce the same result.",
				setValue, getValue
				);
	}
	*/

}
