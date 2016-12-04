import static org.junit.Assert.*;
import org.junit.Assert;
import org.junit.Test;

/**
 * 
 */

/**
 * This class tests the methods of the DefaultUnits class.
 * @author Wilson Melendez
 *
 */
public class DefaultUnitsTest {
	
	private static Double DELTA = 1.0E-15;
	String newUnit1, newUnit2, newUnit3;
	Double newValue1, newValue2, newValue3;
	Double nullValue = null;
	
	
	/**
	 * Test method for {@link DefaultUnits#checkDefaultCoatingAmountUnit(java.lang.String)}.
	 */
	@Test
	public void testCheckDefaultCoatingAmountUnit() {		
		try
		{
			newUnit1 = DefaultUnits.checkDefaultCoatingAmountUnit("ug");
			newUnit2 = DefaultUnits.checkDefaultCoatingAmountUnit("mg");
			newUnit3 = DefaultUnits.checkDefaultCoatingAmountUnit("null");
			
			assertEquals("ug", newUnit1);			
			assertEquals("ug", newUnit2);
			assertEquals("null", newUnit3);
		}
		catch (IllegalUnitsException ex)
		{
			Assert.fail("Exception was thrown: " + ex);	
		}
	}

	/**
	 * Test method for {@link DefaultUnits#checkDefaultCoatingAmount(java.lang.String, java.lang.Double)}.
	 */
	@Test
	public void testCheckDefaultCoatingAmount() {
		
		try
		{
			newValue1 = DefaultUnits.checkDefaultCoatingAmount("ug", 1.0);
			newValue2 = DefaultUnits.checkDefaultCoatingAmount("mg", 1.0);
			newValue3 = DefaultUnits.checkDefaultCoatingAmount("null", nullValue);
			
			assertEquals(1.0, newValue1, DELTA);			
			assertEquals(1000.0, newValue2, DELTA);
			assertEquals(nullValue, newValue3);
		}
		catch (IllegalUnitsException ex)
		{
			Assert.fail("Exception was thrown: " + ex);	
		}
	}

	/**
	 * Test method for {@link DefaultUnits#checkDefaultPurityUnit(java.lang.String)}.
	 */
	@Test
	public void testCheckDefaultPurityUnit() {
		try
		{
			newUnit1 = DefaultUnits.checkDefaultPurityUnit("%");
			newUnit2 = DefaultUnits.checkDefaultPurityUnit("null");
			
			assertEquals("%", newUnit1);			
			assertEquals("null", newUnit2);
		}
		catch (IllegalUnitsException ex)
		{
			Assert.fail("Exception was thrown: " + ex);	
		}
	}

	/**
	 * Test method for {@link DefaultUnits#checkDefaultPurity(java.lang.String, java.lang.Double)}.
	 */
	@Test
	public void testCheckDefaultPurity() {
		try
		{
			newValue1 = DefaultUnits.checkDefaultPurity("%", 89.0);
			newValue2 = DefaultUnits.checkDefaultPurity("null", nullValue);
			
			assertEquals(89.0, newValue1, DELTA);			
			assertEquals(nullValue, newValue2);
		}
		catch (IllegalUnitsException ex)
		{
			Assert.fail("Exception was thrown: " + ex);	
		}
	}

	/**
	 * Test method for {@link DefaultUnits#checkDefaultContaminantUnit(java.lang.String)}.
	 */
	@Test
	public void testCheckDefaultContaminantUnit() {
		try
		{
			newUnit1 = DefaultUnits.checkDefaultContaminantUnit("ppm");
			newUnit2 = DefaultUnits.checkDefaultContaminantUnit("ppt");
			newUnit3 = DefaultUnits.checkDefaultContaminantUnit("null");
			
			assertEquals("ppm", newUnit1);			
			assertEquals("ppm", newUnit2);
			assertEquals("null", newUnit3);
		}
		catch (IllegalUnitsException ex)
		{
			Assert.fail("Exception was thrown: " + ex);	
		}
	}

	/**
	 * Test method for {@link DefaultUnits#checkDefaultContaminant(java.lang.String, java.lang.Double)}.
	 */
	@Test
	public void testCheckDefaultContaminant() {
		try
		{
			newValue1 = DefaultUnits.checkDefaultContaminant("ppm", 15.0);
			newValue2 = DefaultUnits.checkDefaultContaminant("ppt", 20.0);
			newValue3 = DefaultUnits.checkDefaultContaminant("null", nullValue);
			
			assertEquals(15.0, newValue1, DELTA);			
			assertEquals(20000.0, newValue2, DELTA);
			assertEquals(nullValue, newValue3);
		}
		catch (IllegalUnitsException ex)
		{
			Assert.fail("Exception was thrown: " + ex);	
		}
	}

	/**
	 * Test method for {@link DefaultUnits#checkDefaultParticleOuterDiameterUnit(java.lang.String)}.
	 */
	@Test
	public void testCheckDefaultParticleOuterDiameterUnit() {
		try
		{
			newUnit1 = DefaultUnits.checkDefaultParticleOuterDiameterUnit("nm");
			newUnit2 = DefaultUnits.checkDefaultParticleOuterDiameterUnit("um");
			newUnit3 = DefaultUnits.checkDefaultParticleOuterDiameterUnit("null");
			
			assertEquals("nm", newUnit1);			
			assertEquals("nm", newUnit2);
			assertEquals("null", newUnit3);
		}
		catch (IllegalUnitsException ex)
		{
			Assert.fail("Exception was thrown: " + ex);	
		}
	}

	/**
	 * Test method for {@link DefaultUnits#checkDefaultParticleOuterDiameter(java.lang.String, java.lang.Double)}.
	 */
	@Test
	public void testCheckDefaultParticleOuterDiameter() {
		try
		{
			newValue1 = DefaultUnits.checkDefaultParticleOuterDiameter("nm", 35.0);
			newValue2 = DefaultUnits.checkDefaultParticleOuterDiameter("um", 12.0);
			newValue3 = DefaultUnits.checkDefaultParticleOuterDiameter("null", nullValue);
			
			assertEquals(35.0, newValue1, DELTA);			
			assertEquals(12000.0, newValue2, DELTA);
			assertEquals(nullValue, newValue3);
		}
		catch (IllegalUnitsException ex)
		{
			Assert.fail("Exception was thrown: " + ex);	
		}
	}

	/**
	 * Test method for {@link DefaultUnits#checkDefaultParticleInnerDiameterUnit(java.lang.String)}.
	 */
	@Test
	public void testCheckDefaultParticleInnerDiameterUnit() {
		try
		{
			newUnit1 = DefaultUnits.checkDefaultParticleInnerDiameterUnit("nm");
			newUnit2 = DefaultUnits.checkDefaultParticleInnerDiameterUnit("um");
			newUnit3 = DefaultUnits.checkDefaultParticleInnerDiameterUnit("null");
			
			assertEquals("nm", newUnit1);			
			assertEquals("nm", newUnit2);
			assertEquals("null", newUnit3);
		}
		catch (IllegalUnitsException ex)
		{
			Assert.fail("Exception was thrown: " + ex);	
		}
	}

	/**
	 * Test method for {@link DefaultUnits#checkDefaultParticleInnerDiameter(java.lang.String, java.lang.Double)}.
	 */
	@Test
	public void testCheckDefaultParticleInnerDiameter() {
		try
		{
			newValue1 = DefaultUnits.checkDefaultParticleInnerDiameter("nm", 18.0);
			newValue2 = DefaultUnits.checkDefaultParticleInnerDiameter("um", 17.0);
			newValue3 = DefaultUnits.checkDefaultParticleInnerDiameter("null", nullValue);
			
			assertEquals(18.0, newValue1, DELTA);			
			assertEquals(17000.0, newValue2, DELTA);
			assertEquals(nullValue, newValue3);
		}
		catch (IllegalUnitsException ex)
		{
			Assert.fail("Exception was thrown: " + ex);	
		}
	}

	/**
	 * Test method for {@link DefaultUnits#checkDefaultParticleLengthUnit(java.lang.String)}.
	 */
	@Test
	public void testCheckDefaultParticleLengthUnit() {
		try
		{
			newUnit1 = DefaultUnits.checkDefaultParticleLengthUnit("nm");
			newUnit2 = DefaultUnits.checkDefaultParticleLengthUnit("um");
			newUnit3 = DefaultUnits.checkDefaultParticleLengthUnit("null");
			
			assertEquals("nm", newUnit1);			
			assertEquals("nm", newUnit2);
			assertEquals("null", newUnit3);
		}
		catch (IllegalUnitsException ex)
		{
			Assert.fail("Exception was thrown: " + ex);	
		}
	}

	/**
	 * Test method for {@link DefaultUnits#checkDefaultParticleLength(java.lang.String, java.lang.Double)}.
	 */
	@Test
	public void testCheckDefaultParticleLength() {
		try
		{
			newValue1 = DefaultUnits.checkDefaultParticleLength("nm", 1.0);
			newValue2 = DefaultUnits.checkDefaultParticleLength("um", 1.0);
			newValue3 = DefaultUnits.checkDefaultParticleLength("null", nullValue);
			
			assertEquals(1.0, newValue1, DELTA);			
			assertEquals(1000.0, newValue2, DELTA);
			assertEquals(nullValue, newValue3);
		}
		catch (IllegalUnitsException ex)
		{
			Assert.fail("Exception was thrown: " + ex);	
		}
	}

	/**
	 * Test method for {@link DefaultUnits#checkDefaultParticleThicknessUnit(java.lang.String)}.
	 */
	@Test
	public void testCheckDefaultParticleThicknessUnit() {
		try
		{
			newUnit1 = DefaultUnits.checkDefaultParticleThicknessUnit("nm");
			newUnit2 = DefaultUnits.checkDefaultParticleThicknessUnit("um");
			newUnit3 = DefaultUnits.checkDefaultParticleThicknessUnit("null");
			
			assertEquals("nm", newUnit1);			
			assertEquals("nm", newUnit2);
			assertEquals("null", newUnit3);
		}
		catch (IllegalUnitsException ex)
		{
			Assert.fail("Exception was thrown: " + ex);	
		}
	}

	/**
	 * Test method for {@link DefaultUnits#checkDefaultParticleThickness(java.lang.String, java.lang.Double)}.
	 */
	@Test
	public void testCheckDefaultParticleThickness() {
		try
		{
			newValue1 = DefaultUnits.checkDefaultParticleThickness("nm", 1.0);
			newValue2 = DefaultUnits.checkDefaultParticleThickness("um", 1.0);
			newValue3 = DefaultUnits.checkDefaultParticleThickness("null", nullValue);
			
			assertEquals(1.0, newValue1, DELTA);			
			assertEquals(1000.0, newValue2, DELTA);
			assertEquals(nullValue, newValue3);
		}
		catch (IllegalUnitsException ex)
		{
			Assert.fail("Exception was thrown: " + ex);	
		}
	}

	/**
	 * Test method for {@link DefaultUnits#checkDefaultSurfaceAreaUnit(java.lang.String)}.
	 */
	@Test
	public void testCheckDefaultSurfaceAreaUnit() {
		try
		{
			newUnit1 = DefaultUnits.checkDefaultSurfaceAreaUnit("m^2/g");
			newUnit2 = DefaultUnits.checkDefaultSurfaceAreaUnit("cm^2/g");
			newUnit3 = DefaultUnits.checkDefaultSurfaceAreaUnit("null");
			
			assertEquals("m^2/g", newUnit1);			
			assertEquals("m^2/g", newUnit2);
			assertEquals("null", newUnit3);
		}
		catch (IllegalUnitsException ex)
		{
			Assert.fail("Exception was thrown: " + ex);	
		}
	}

	/**
	 * Test method for {@link DefaultUnits#checkDefaultSurfaceArea(java.lang.String, java.lang.Double)}.
	 */
	@Test
	public void testCheckDefaultSurfaceArea() {
		try
		{
			newValue1 = DefaultUnits.checkDefaultSurfaceArea("m^2/g", 1.0);
			newValue2 = DefaultUnits.checkDefaultSurfaceArea("cm^2/g", 1000.0);
			newValue3 = DefaultUnits.checkDefaultSurfaceArea("null", nullValue);
			
			assertEquals(1.0, newValue1, DELTA);			
			assertEquals(0.1, newValue2, DELTA);
			assertEquals(nullValue, newValue3);
		}
		catch (IllegalUnitsException ex)
		{
			Assert.fail("Exception was thrown: " + ex);	
		}
	}

	/**
	 * Test method for {@link DefaultUnits#checkDefaultTimeValueUnit(java.lang.String)}.
	 */
	@Test
	public void testCheckDefaultTimeValueUnit() {
		try
		{
			newUnit1 = DefaultUnits.checkDefaultTimeValueUnit("hours");
			newUnit2 = DefaultUnits.checkDefaultTimeValueUnit("minutes");
			newUnit3 = DefaultUnits.checkDefaultTimeValueUnit("null");
			
			assertEquals("hours", newUnit1);			
			assertEquals("hours", newUnit2);
			assertEquals("null", newUnit3);
		}
		catch (IllegalUnitsException ex)
		{
			Assert.fail("Exception was thrown: " + ex);	
		}
	}

	/**
	 * Test method for {@link DefaultUnits#checkDefaultTimeValue(java.lang.String, java.lang.Integer)}.
	 */
	@Test
	public void testCheckDefaultTimeValueStringInteger() {
		Integer newValueInt1, newValueInt2;
		Integer newValueInt3;
		Integer nullValueInt = null;
		try
		{
			newValueInt1 = DefaultUnits.checkDefaultTimeValue("hours", 1);
			newValueInt2 = DefaultUnits.checkDefaultTimeValue("minutes", 60);
			newValueInt3 = DefaultUnits.checkDefaultTimeValue("null", nullValueInt);
			
			assertEquals(1, newValueInt1, DELTA);			
			assertEquals(1, newValueInt2, DELTA);
			assertEquals(nullValueInt, newValueInt3);
		}
		catch (IllegalUnitsException ex)
		{
			Assert.fail("Exception was thrown: " + ex);	
		}
	}

	/**
	 * Test method for {@link DefaultUnits#checkDefaultTimeValue(java.lang.String, java.lang.Double)}.
	 */
	@Test
	public void testCheckDefaultTimeValueStringDouble() {
		try
		{
			newValue1 = DefaultUnits.checkDefaultTimeValue("hours", 1.0);
			newValue2 = DefaultUnits.checkDefaultTimeValue("minutes", 60.0);
			newValue3 = DefaultUnits.checkDefaultTimeValue("null", nullValue);
			
			assertEquals(1.0, newValue1, DELTA);			
			assertEquals(1.0, newValue2, DELTA);
			assertEquals(nullValue, newValue3);
		}
		catch (IllegalUnitsException ex)
		{
			Assert.fail("Exception was thrown: " + ex);	
		}
	}

	/**
	 * Test method for {@link DefaultUnits#checkDefaultParticleConcentrationUnit(java.lang.String)}.
	 */
	@Test
	public void testCheckDefaultParticleConcentrationUnit() {
		try
		{
			newUnit1 = DefaultUnits.checkDefaultParticleConcentrationUnit("ug/mL");
			newUnit2 = DefaultUnits.checkDefaultParticleConcentrationUnit("mg/mL");
			newUnit3 = DefaultUnits.checkDefaultParticleConcentrationUnit("null");
			
			assertEquals("ug/mL", newUnit1);			
			assertEquals("ug/mL", newUnit2);
			assertEquals("null", newUnit3);
		}
		catch (IllegalUnitsException ex)
		{
			Assert.fail("Exception was thrown: " + ex);	
		}
	}

	/**
	 * Test method for {@link DefaultUnits#checkDefaultParticleConcentration(java.lang.String, java.lang.Double)}.
	 */
	@Test
	public void testCheckDefaultParticleConcentration() {
		try
		{
			newValue1 = DefaultUnits.checkDefaultParticleConcentration("ug/mL", 1.0);
			newValue2 = DefaultUnits.checkDefaultParticleConcentration("mg/mL", 1.0);
			newValue3 = DefaultUnits.checkDefaultParticleConcentration("null", nullValue);
			
			assertEquals(1.0, newValue1, DELTA);			
			assertEquals(1000.0, newValue2, DELTA);
			assertEquals(nullValue, newValue3);
		}
		catch (IllegalUnitsException ex)
		{
			Assert.fail("Exception was thrown: " + ex);	
		}
	}

	/**
	 * Test method for {@link DefaultUnits#checkDefaultMediumTempUnit(java.lang.String)}.
	 */
	@Test
	public void testCheckDefaultMediumTempUnit() {
		String newUnit4;
		try
		{
			newUnit1 = DefaultUnits.checkDefaultMediumTempUnit("C");
			newUnit2 = DefaultUnits.checkDefaultMediumTempUnit("F");
			newUnit3 = DefaultUnits.checkDefaultMediumTempUnit("K");
			newUnit4 = DefaultUnits.checkDefaultMediumTempUnit("null");
			
			assertEquals("C", newUnit1);			
			assertEquals("C", newUnit2);
			assertEquals("C", newUnit3);
			assertEquals("null", newUnit4);
		}
		catch (IllegalUnitsException ex)
		{
			Assert.fail("Exception was thrown: " + ex);	
		}
	}

	/**
	 * Test method for {@link DefaultUnits#checkDefaultMediumTemp(java.lang.String, java.lang.Double)}.
	 */
	@Test
	public void testCheckDefaultMediumTemp() {
		Double newValue4 = null;
		try
		{
			newValue1 = DefaultUnits.checkDefaultMediumTemp("C", 10.0);
			newValue2 = DefaultUnits.checkDefaultMediumTemp("F", 59.0);
			newValue3 = DefaultUnits.checkDefaultMediumTemp("K", 273.15);
			newValue4 = DefaultUnits.checkDefaultMediumTemp("null", nullValue);
			
			assertEquals(10.0, newValue1, DELTA);			
			assertEquals(15.0, newValue2, DELTA);
			assertEquals(0.0, newValue3, DELTA);
			assertEquals(nullValue, newValue4);
		}
		catch (IllegalUnitsException ex)
		{
			Assert.fail("Exception was thrown: " + ex);	
		}
	}

	/**
	 * Test method for {@link DefaultUnits#checkDefaultZetaPotentialUnit(java.lang.String)}.
	 */
	@Test
	public void testCheckDefaultZetaPotentialUnit() {
		try
		{
			newUnit1 = DefaultUnits.checkDefaultZetaPotentialUnit("mV");
			newUnit2 = DefaultUnits.checkDefaultZetaPotentialUnit("uV");
			newUnit3 = DefaultUnits.checkDefaultZetaPotentialUnit("null");
			
			assertEquals("mV", newUnit1);			
			assertEquals("mV", newUnit2);
			assertEquals("null", newUnit3);
		}
		catch (IllegalUnitsException ex)
		{
			Assert.fail("Exception was thrown: " + ex);	
		}
	}

	/**
	 * Test method for {@link DefaultUnits#checkDefaultZetaPotential(java.lang.String, java.lang.Double)}.
	 */
	@Test
	public void testCheckDefaultZetaPotential() {
		try
		{
			newValue1 = DefaultUnits.checkDefaultZetaPotential("mV", 1.0);
			newValue2 = DefaultUnits.checkDefaultZetaPotential("uV", 1.0);
			newValue3 = DefaultUnits.checkDefaultZetaPotential("null", nullValue);
			
			assertEquals(1.0, newValue1, DELTA);			
			assertEquals(0.001, newValue2, DELTA);
			assertEquals(nullValue, newValue3);
		}
		catch (IllegalUnitsException ex)
		{
			Assert.fail("Exception was thrown: " + ex);	
		}
	}

	/**
	 * Test method for {@link DefaultUnits#checkDefaultSizeDistributionUnit(java.lang.String)}.
	 */
	@Test
	public void testCheckDefaultSizeDistributionUnit() {
		try
		{
			newUnit1 = DefaultUnits.checkDefaultSizeDistributionUnit("nm");
			newUnit2 = DefaultUnits.checkDefaultSizeDistributionUnit("um");
			newUnit3 = DefaultUnits.checkDefaultSizeDistributionUnit("null");
			
			assertEquals("nm", newUnit1);			
			assertEquals("nm", newUnit2);
			assertEquals("null", newUnit3);
		}
		catch (IllegalUnitsException ex)
		{
			Assert.fail("Exception was thrown: " + ex);	
		}
	}

	/**
	 * Test method for {@link DefaultUnits#checkDefaultSizeDistribution(java.lang.String, java.lang.Double)}.
	 */
	@Test
	public void testCheckDefaultSizeDistribution() {
		try
		{
			newValue1 = DefaultUnits.checkDefaultSizeDistribution("nm", 1.0);
			newValue2 = DefaultUnits.checkDefaultSizeDistribution("um", 1.0);
			newValue3 = DefaultUnits.checkDefaultSizeDistribution("null", nullValue);
			
			assertEquals(1.0, newValue1, DELTA);			
			assertEquals(1000.0, newValue2, DELTA);
			assertEquals(nullValue, newValue3);
		}
		catch (IllegalUnitsException ex)
		{
			Assert.fail("Exception was thrown: " + ex);	
		}
	}

	/**
	 * Test method for {@link DefaultUnits#checkDefaultSizeDistributionUnit2(java.lang.String)}.
	 */
	@Test
	public void testCheckDefaultSizeDistributionUnit2() {
		try
		{
			newUnit1 = DefaultUnits.checkDefaultSizeDistributionUnit2("nm");
			newUnit2 = DefaultUnits.checkDefaultSizeDistributionUnit2("um");
			newUnit3 = DefaultUnits.checkDefaultSizeDistributionUnit2("null");
			
			assertEquals("nm", newUnit1);			
			assertEquals("nm", newUnit2);
			assertEquals("null", newUnit3);
		}
		catch (IllegalUnitsException ex)
		{
			Assert.fail("Exception was thrown: " + ex);	
		}
	}

	/**
	 * Test method for {@link DefaultUnits#checkDefaultSizeDistribution2(java.lang.String, java.lang.Double)}.
	 */
	@Test
	public void testCheckDefaultSizeDistribution2() {
		try
		{
			newValue1 = DefaultUnits.checkDefaultSizeDistribution2("nm", 1.0);
			newValue2 = DefaultUnits.checkDefaultSizeDistribution2("um", 1.0);
			newValue3 = DefaultUnits.checkDefaultSizeDistribution2("null", nullValue);
			
			assertEquals(1.0, newValue1, DELTA);			
			assertEquals(1000.0, newValue2, DELTA);
			assertEquals(nullValue, newValue3);
		}
		catch (IllegalUnitsException ex)
		{
			Assert.fail("Exception was thrown: " + ex);	
		}
	}

	/**
	 * Test method for {@link DefaultUnits#checkDefaultSerumConcentrationUnit(java.lang.String)}.
	 */
	@Test
	public void testCheckDefaultSerumConcentrationUnit() {
		try
		{
			newUnit1 = DefaultUnits.checkDefaultSerumConcentrationUnit("%");
			newUnit2 = DefaultUnits.checkDefaultSerumConcentrationUnit("null");
			
			assertEquals("%", newUnit1);			
			assertEquals("null", newUnit2);
		}
		catch (IllegalUnitsException ex)
		{
			Assert.fail("Exception was thrown: " + ex);	
		}
	}

	/**
	 * Test method for {@link DefaultUnits#checkDefaultSerumConcentration(java.lang.String, java.lang.Double)}.
	 */
	@Test
	public void testCheckDefaultSerumConcentration() {
		try
		{
			newValue1 = DefaultUnits.checkDefaultSerumConcentration("%", 50.0);
			newValue2 = DefaultUnits.checkDefaultSerumConcentration("null", nullValue);
			
			assertEquals(50.0, newValue1, DELTA);			
			assertEquals(nullValue, newValue2);
		}
		catch (IllegalUnitsException ex)
		{
			Assert.fail("Exception was thrown: " + ex);	
		}
	}

	/**
	 * Test method for {@link DefaultUnits#checkDefaultAntibioticConcentrationUnit(java.lang.String)}.
	 */
	@Test
	public void testCheckDefaultAntibioticConcentrationUnit() {
		try
		{
			newUnit1 = DefaultUnits.checkDefaultAntibioticConcentrationUnit("%");
			newUnit2 = DefaultUnits.checkDefaultAntibioticConcentrationUnit("null");
			
			assertEquals("%", newUnit1);			
			assertEquals("null", newUnit2);
		}
		catch (IllegalUnitsException ex)
		{
			Assert.fail("Exception was thrown: " + ex);	
		}
	}

	/**
	 * Test method for {@link DefaultUnits#checkDefaultAntibioticConcentration(java.lang.String, java.lang.Double)}.
	 */
	@Test
	public void testCheckDefaultAntibioticConcentration() {
		try
		{
			newValue1 = DefaultUnits.checkDefaultAntibioticConcentration("%", 60.0);			
			newValue2 = DefaultUnits.checkDefaultAntibioticConcentration("null", nullValue);
			
			assertEquals(60.0, newValue1, DELTA);						
			assertEquals(nullValue, newValue2);
		}
		catch (IllegalUnitsException ex)
		{
			Assert.fail("Exception was thrown: " + ex);	
		}
	}

	/**
	 * Test method for {@link DefaultUnits#checkDefaultDOMUnit(java.lang.String)}.
	 */
	@Test
	public void testCheckDefaultDOMUnit() {
		try
		{
			newUnit1 = DefaultUnits.checkDefaultDOMUnit("mg/L");
			newUnit2 = DefaultUnits.checkDefaultDOMUnit("ug/L");
			newUnit3 = DefaultUnits.checkDefaultDOMUnit("null");
			
			assertEquals("mg/L", newUnit1);			
			assertEquals("mg/L", newUnit2);
			assertEquals("null", newUnit3);
		}
		catch (IllegalUnitsException ex)
		{
			Assert.fail("Exception was thrown: " + ex);	
		}
	}

	/**
	 * Test method for {@link DefaultUnits#checkDefaultDOMConcentration(java.lang.String, java.lang.Double)}.
	 */
	@Test
	public void testCheckDefaultDOMConcentration() {
		try
		{
			newValue1 = DefaultUnits.checkDefaultDOMConcentration("mg/L", 1.0);
			newValue2 = DefaultUnits.checkDefaultDOMConcentration("ug/L", 1000.0);
			newValue3 = DefaultUnits.checkDefaultDOMConcentration("null", nullValue);
			
			assertEquals(1.0, newValue1, DELTA);			
			assertEquals(1.0, newValue2, DELTA);
			assertEquals(nullValue, newValue3);
		}
		catch (IllegalUnitsException ex)
		{
			Assert.fail("Exception was thrown: " + ex);	
		}
	}

	/**
	 * Test method for {@link DefaultUnits#checkDefaultSalinityUnit(java.lang.String)}.
	 */
	@Test
	public void testCheckDefaultSalinityUnit() {
		try
		{
			newUnit1 = DefaultUnits.checkDefaultSalinityUnit("ppt");
			newUnit2 = DefaultUnits.checkDefaultSalinityUnit("psu");
			newUnit3 = DefaultUnits.checkDefaultSalinityUnit("null");
			
			assertEquals("ppt", newUnit1);			
			assertEquals("psu", newUnit2);
			assertEquals("null", newUnit3);
		}
		catch (IllegalUnitsException ex)
		{
			Assert.fail("Exception was thrown: " + ex);	
		}
	}

	/**
	 * Test method for {@link DefaultUnits#checkDefaultSalinityValue(java.lang.String, java.lang.Double)}.
	 */
	@Test
	public void testCheckDefaultSalinityValue() {
		try
		{
			newValue1 = DefaultUnits.checkDefaultSalinityValue("ppt", 35.0);
			newValue2 = DefaultUnits.checkDefaultSalinityValue("psu", 35.0);
			newValue3 = DefaultUnits.checkDefaultSalinityValue("null", nullValue);
			
			assertEquals(35.0, newValue1, DELTA);			
			assertEquals(35.0, newValue2, DELTA);
			assertEquals(nullValue, newValue3);
		}
		catch (IllegalUnitsException ex)
		{
			Assert.fail("Exception was thrown: " + ex);	
		}
	}

	/**
	 * Test method for {@link DefaultUnits#checkDefaultParticleExposureDurationUnit(java.lang.String)}.
	 */
	@Test
	public void testCheckDefaultParticleExposureDurationUnit() {
		try
		{
			newUnit1 = DefaultUnits.checkDefaultParticleExposureDurationUnit("hours");
			newUnit2 = DefaultUnits.checkDefaultParticleExposureDurationUnit("minutes");
			newUnit3 = DefaultUnits.checkDefaultParticleExposureDurationUnit("null");
			
			assertEquals("hours", newUnit1);			
			assertEquals("hours", newUnit2);
			assertEquals("null", newUnit3);
		}
		catch (IllegalUnitsException ex)
		{
			Assert.fail("Exception was thrown: " + ex);	
		}
	}

	/**
	 * Test method for {@link DefaultUnits#checkDefaultParticleExposureDuration(java.lang.String, java.lang.Double)}.
	 */
	@Test
	public void testCheckDefaultParticleExposureDuration() {
		try
		{
			newValue1 = DefaultUnits.checkDefaultParticleExposureDuration("hours", 48.0);
			newValue2 = DefaultUnits.checkDefaultParticleExposureDuration("minutes", 120.0);
			newValue3 = DefaultUnits.checkDefaultParticleExposureDuration("null", nullValue);
			
			assertEquals(48.0, newValue1, DELTA);			
			assertEquals(2.0, newValue2, DELTA);
			assertEquals(nullValue, newValue3);
		}
		catch (IllegalUnitsException ex)
		{
			Assert.fail("Exception was thrown: " + ex);	
		}
	}

	/**
	 * Test method for {@link DefaultUnits#checkDefaultUVADoseUnit(java.lang.String)}.
	 */
	@Test
	public void testCheckDefaultUVADoseUnit() {
		try
		{
			newUnit1 = DefaultUnits.checkDefaultUVADoseUnit("J/cm^2");
			newUnit2 = DefaultUnits.checkDefaultUVADoseUnit("mJ/cm^2");
			newUnit3 = DefaultUnits.checkDefaultUVADoseUnit("null");
			
			assertEquals("J/cm^2", newUnit1);			
			assertEquals("J/cm^2", newUnit2);
			assertEquals("null", newUnit3);
		}
		catch (IllegalUnitsException ex)
		{
			Assert.fail("Exception was thrown: " + ex);	
		}
	}

	/**
	 * Test method for {@link DefaultUnits#checkDefaultUVADose(java.lang.String, java.lang.Double)}.
	 */
	@Test
	public void testCheckDefaultUVADose() {
		try
		{
			newValue1 = DefaultUnits.checkDefaultUVADose("J/cm^2", 67.0);
			newValue2 = DefaultUnits.checkDefaultUVADose("mJ/cm^2", 1000.0);
			newValue3 = DefaultUnits.checkDefaultUVADose("null", nullValue);
			
			assertEquals(67.0, newValue1, DELTA);			
			assertEquals(1.0, newValue2, DELTA);
			assertEquals(nullValue, newValue3);
		}
		catch (IllegalUnitsException ex)
		{
			Assert.fail("Exception was thrown: " + ex);	
		}
	}

	/**
	 * Test method for {@link DefaultUnits#checkDefaultUVAExposureDurationUnit(java.lang.String)}.
	 */
	@Test
	public void testCheckDefaultUVAExposureDurationUnit() {
		try
		{
			newUnit1 = DefaultUnits.checkDefaultUVAExposureDurationUnit("hours");
			newUnit2 = DefaultUnits.checkDefaultUVAExposureDurationUnit("minutes");
			newUnit3 = DefaultUnits.checkDefaultUVAExposureDurationUnit("null");
			
			assertEquals("hours", newUnit1);			
			assertEquals("hours", newUnit2);
			assertEquals("null", newUnit3);
		}
		catch (IllegalUnitsException ex)
		{
			Assert.fail("Exception was thrown: " + ex);	
		}
	}

	/**
	 * Test method for {@link DefaultUnits#checkDefaultUVAExposureDuration(java.lang.String, java.lang.Double)}.
	 */
	@Test
	public void testCheckDefaultUVAExposureDuration() {
		try
		{
			newValue1 = DefaultUnits.checkDefaultUVAExposureDuration("hours", 48.0);
			newValue2 = DefaultUnits.checkDefaultUVAExposureDuration("minutes", 120.0);
			newValue3 = DefaultUnits.checkDefaultUVAExposureDuration("null", nullValue);
			
			assertEquals(48.0, newValue1, DELTA);			
			assertEquals(2.0, newValue2, DELTA);
			assertEquals(nullValue, newValue3);
		}
		catch (IllegalUnitsException ex)
		{
			Assert.fail("Exception was thrown: " + ex);	
		}
	}

	/**
	 * Test method for {@link DefaultUnits#checkDefaultViabilityUnit(java.lang.String)}.
	 */
	@Test
	public void testCheckDefaultViabilityUnit() {
		try
		{
			newUnit1 = DefaultUnits.checkDefaultViabilityUnit("%");
			newUnit2 = DefaultUnits.checkDefaultViabilityUnit("null");
			
			assertEquals("%", newUnit1);			
			assertEquals("null", newUnit2);
		}
		catch (IllegalUnitsException ex)
		{
			Assert.fail("Exception was thrown: " + ex);	
		}
	}

	/**
	 * Test method for {@link DefaultUnits#checkDefaultViability(java.lang.String, java.lang.Double)}.
	 */
	@Test
	public void testCheckDefaultViability() {
		try
		{
			newValue1 = DefaultUnits.checkDefaultViability("%", 55.0);
			newValue2 = DefaultUnits.checkDefaultViability("null", nullValue);
			
			assertEquals(55.0, newValue1, DELTA);			
			assertEquals(nullValue, newValue2);
		}
		catch (IllegalUnitsException ex)
		{
			Assert.fail("Exception was thrown: " + ex);	
		}
	}

	/**
	 * Test method for {@link DefaultUnits#checkDefaultLC50Unit(java.lang.String)}.
	 */
	@Test
	public void testCheckDefaultLC50Unit() {
		try
		{
			newUnit1 = DefaultUnits.checkDefaultLC50Unit("ug/mL");
			newUnit2 = DefaultUnits.checkDefaultLC50Unit("mg/mL");
			newUnit3 = DefaultUnits.checkDefaultLC50Unit("null");
			
			assertEquals("ug/mL", newUnit1);			
			assertEquals("ug/mL", newUnit2);
			assertEquals("null", newUnit3);
		}
		catch (IllegalUnitsException ex)
		{
			Assert.fail("Exception was thrown: " + ex);	
		}
	}

	/**
	 * Test method for {@link DefaultUnits#checkDefaultLC50(java.lang.String, java.lang.Double)}.
	 */
	@Test
	public void testCheckDefaultLC50() {
		try
		{
			newValue1 = DefaultUnits.checkDefaultLC50("ug/mL", 11.0);
			newValue2 = DefaultUnits.checkDefaultLC50("mg/mL", 33.0);
			newValue3 = DefaultUnits.checkDefaultLC50("null", nullValue);
			
			assertEquals(11.0, newValue1, DELTA);			
			assertEquals(33000.0, newValue2, DELTA);
			assertEquals(nullValue, newValue3);
		}
		catch (IllegalUnitsException ex)
		{
			Assert.fail("Exception was thrown: " + ex);	
		}
	}

}
