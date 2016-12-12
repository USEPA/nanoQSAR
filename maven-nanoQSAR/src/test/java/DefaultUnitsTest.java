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
	NanoMaterial nanoM = new NanoMaterial();
	int i = 0;
	
	
	/**
	 * Test method for {@link DefaultUnits#checkDefaultCoatingAmountUnit(java.lang.String)}.
	 */
	@Test
	public void testCheckDefaultCoatingAmountUnit() {		
		try
		{
			nanoM.setCoatingAmountUnit("ug");
			nanoM.setCoatingAmount(1.0);
			DefaultUnits.checkDefaultCoatingAmountUnit(nanoM, i);
			assertEquals("ug", nanoM.getCoatingAmountUnit());	
			assertEquals(1.0, nanoM.getCoatingAmount(), DELTA);	
			
			nanoM.setCoatingAmountUnit("null");
			DefaultUnits.checkDefaultCoatingAmountUnit(nanoM, i);
			assertEquals("null", nanoM.getCoatingAmountUnit());	
			
			nanoM.setCoatingAmountUnit("mg");
			DefaultUnits.checkDefaultCoatingAmountUnit(nanoM, i);
			assertEquals("ug", nanoM.getCoatingAmountUnit());	
			assertEquals(1000.0, nanoM.getCoatingAmount(), DELTA);	
			
			
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
			nanoM.setPurityUnit("%");
			nanoM.setPurity(89.0);
			DefaultUnits.checkDefaultPurityUnit(nanoM, i);
			assertEquals("%", nanoM.getPurityUnit());	
			assertEquals(89.0, nanoM.getPurity(), DELTA);	
			
			nanoM.setPurityUnit("null");
			DefaultUnits.checkDefaultPurityUnit(nanoM, i);
			assertEquals("null", nanoM.getPurityUnit());	
			
			nanoM.setPurityUnit("fraction");
			nanoM.setPurity(0.89);
			DefaultUnits.checkDefaultPurityUnit(nanoM, i);
			assertEquals("%", nanoM.getPurityUnit());	
			assertEquals(89.0, nanoM.getPurity(), DELTA);
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
			nanoM.setContamUnit("ppm");
			nanoM.setContamAl(15.0);
			DefaultUnits.checkDefaultContaminantUnit(nanoM, i);
			assertEquals("ppm", nanoM.getContamUnit());	
			assertEquals(15.0, nanoM.getContamAl(), DELTA);	
			
			nanoM.setContamUnit("null");
			DefaultUnits.checkDefaultContaminantUnit(nanoM, i);
			assertEquals("null", nanoM.getContamUnit());	
			
			nanoM.setContamUnit("ppt");
			nanoM.setContamAl(20.0);
			DefaultUnits.checkDefaultContaminantUnit(nanoM, i);
			assertEquals("ppm", nanoM.getContamUnit());	
			assertEquals(20000.0, nanoM.getContamAl(), DELTA);
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
			nanoM.setParticleOuterDiamUnit("nm");
			nanoM.setParticleOuterDiamAvg(15.0);
			DefaultUnits.checkDefaultParticleOuterDiameterUnit(nanoM, i);
			assertEquals("nm", nanoM.getParticleOuterDiamUnit());	
			assertEquals(15.0, nanoM.getParticleOuterDiamAvg(), DELTA);	
			
			nanoM.setParticleOuterDiamUnit("null");
			DefaultUnits.checkDefaultParticleOuterDiameterUnit(nanoM, i);
			assertEquals("null", nanoM.getParticleOuterDiamUnit());	
			
			nanoM.setParticleOuterDiamUnit("um");
			nanoM.setParticleOuterDiamAvg(20.0);
			DefaultUnits.checkDefaultParticleOuterDiameterUnit(nanoM, i);
			assertEquals("nm", nanoM.getParticleOuterDiamUnit());	
			assertEquals(20000.0, nanoM.getParticleOuterDiamAvg(), DELTA);
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
			nanoM.setParticleInnerDiamUnit("nm");
			nanoM.setParticleInnerDiamAvg(15.0);
			DefaultUnits.checkDefaultParticleInnerDiameterUnit(nanoM, i);
			assertEquals("nm", nanoM.getParticleInnerDiamUnit());	
			assertEquals(15.0, nanoM.getParticleInnerDiamAvg(), DELTA);	
			
			nanoM.setParticleInnerDiamUnit("null");
			DefaultUnits.checkDefaultParticleInnerDiameterUnit(nanoM, i);
			assertEquals("null", nanoM.getParticleInnerDiamUnit());	
			
			nanoM.setParticleInnerDiamUnit("um");
			nanoM.setParticleInnerDiamAvg(20.0);
			DefaultUnits.checkDefaultParticleInnerDiameterUnit(nanoM, i);
			assertEquals("nm", nanoM.getParticleInnerDiamUnit());	
			assertEquals(20000.0, nanoM.getParticleInnerDiamAvg(), DELTA);
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
			nanoM.setParticleLengthUnit("nm");
			nanoM.setParticleLengthAvg(15.0);
			DefaultUnits.checkDefaultParticleLengthUnit(nanoM, i);
			assertEquals("nm", nanoM.getParticleLengthUnit());	
			assertEquals(15.0, nanoM.getParticleLengthAvg(), DELTA);	
			
			nanoM.setParticleLengthUnit("null");
			DefaultUnits.checkDefaultParticleLengthUnit(nanoM, i);
			assertEquals("null", nanoM.getParticleLengthUnit());	
			
			nanoM.setParticleLengthUnit("um");
			nanoM.setParticleLengthAvg(20.0);
			DefaultUnits.checkDefaultParticleLengthUnit(nanoM, i);
			assertEquals("nm", nanoM.getParticleLengthUnit());	
			assertEquals(20000.0, nanoM.getParticleLengthAvg(), DELTA);
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
			nanoM.setParticleThicknessUnit("nm");
			nanoM.setParticleThicknessAvg(15.0);
			DefaultUnits.checkDefaultParticleThicknessUnit(nanoM, i);
			assertEquals("nm", nanoM.getParticleThicknessUnit());	
			assertEquals(15.0, nanoM.getParticleThicknessAvg(), DELTA);	
			
			nanoM.setParticleThicknessUnit("null");
			DefaultUnits.checkDefaultParticleThicknessUnit(nanoM, i);
			assertEquals("null", nanoM.getParticleThicknessUnit());	
			
			nanoM.setParticleThicknessUnit("um");
			nanoM.setParticleThicknessAvg(20.0);
			DefaultUnits.checkDefaultParticleThicknessUnit(nanoM, i);
			assertEquals("nm", nanoM.getParticleThicknessUnit());	
			assertEquals(20000.0, nanoM.getParticleThicknessAvg(), DELTA);
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
			nanoM.setSurfaceAreaUnit("m^2/g");
			nanoM.setSurfaceAreaAvg(15.0);
			DefaultUnits.checkDefaultSurfaceAreaUnit(nanoM, i);
			assertEquals("m^2/g", nanoM.getSurfaceAreaUnit());	
			assertEquals(15.0, nanoM.getSurfaceAreaAvg(), DELTA);	
			
			nanoM.setSurfaceAreaUnit("null");
			DefaultUnits.checkDefaultSurfaceAreaUnit(nanoM, i);
			assertEquals("null", nanoM.getSurfaceAreaUnit());	
			
			nanoM.setSurfaceAreaUnit("cm^2/g");
			nanoM.setSurfaceAreaAvg(1000.0);
			DefaultUnits.checkDefaultSurfaceAreaUnit(nanoM, i);
			assertEquals("m^2/g", nanoM.getSurfaceAreaUnit());	
			assertEquals(0.1, nanoM.getSurfaceAreaAvg(), DELTA);
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
			nanoM.setTimeValueUnit("hours");
			nanoM.setTimeValue(15.0);
			DefaultUnits.checkDefaultTimeValueUnit(nanoM, i);
			assertEquals("hours", nanoM.getTimeValueUnit());	
			assertEquals(15.0, nanoM.getTimeValue(), DELTA);	
			
			nanoM.setTimeValueUnit("null");
			DefaultUnits.checkDefaultTimeValueUnit(nanoM, i);
			assertEquals("null", nanoM.getTimeValueUnit());	
			
			nanoM.setTimeValueUnit("minutes");
			nanoM.setTimeValue(60.0);
			DefaultUnits.checkDefaultTimeValueUnit(nanoM, i);
			assertEquals("hours", nanoM.getTimeValueUnit());	
			assertEquals(1.0, nanoM.getTimeValue(), DELTA);
		}
		catch (IllegalUnitsException ex)
		{
			Assert.fail("Exception was thrown: " + ex);	
		}
	}

	/**
	 * Test method for CheckDefaultMcTimeValueUnit
	 */
	@Test
	public void testCheckDefaultMcTimeValueUnit() {
		try
		{
			nanoM.setMc_timeValueUnit("hours");
			nanoM.setMc_timeValue(15);
			DefaultUnits.checkDefaultMcTimeValueUnit(nanoM, i);
			assertEquals("hours", nanoM.getMc_timeValueUnit());	
			assertEquals(15, nanoM.getMc_timeValue(), DELTA);	
			
			nanoM.setMc_timeValueUnit("null");
			DefaultUnits.checkDefaultMcTimeValueUnit(nanoM, i);
			assertEquals("null", nanoM.getMc_timeValueUnit());	
			
			nanoM.setMc_timeValueUnit("minutes");
			nanoM.setMc_timeValue(60);
			DefaultUnits.checkDefaultMcTimeValueUnit(nanoM, i);
			assertEquals("hours", nanoM.getMc_timeValueUnit());	
			assertEquals(1, nanoM.getMc_timeValue(), DELTA);
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
			nanoM.setParticleConcentrationUnit("ug/mL");
			nanoM.setParticleConcentration(15.0);
			DefaultUnits.checkDefaultParticleConcentrationUnit(nanoM, i);
			assertEquals("ug/mL", nanoM.getParticleConcentrationUnit());	
			assertEquals(15.0, nanoM.getParticleConcentration(), DELTA);	
			
			nanoM.setParticleConcentrationUnit("null");
			DefaultUnits.checkDefaultParticleConcentrationUnit(nanoM, i);
			assertEquals("null", nanoM.getParticleConcentrationUnit());	
			
			nanoM.setParticleConcentrationUnit("mg/mL");
			nanoM.setParticleConcentration(1.0);
			DefaultUnits.checkDefaultParticleConcentrationUnit(nanoM, i);
			assertEquals("ug/mL", nanoM.getParticleConcentrationUnit());	
			assertEquals(1000.0, nanoM.getParticleConcentration(), DELTA);
		}
		catch (IllegalUnitsException ex)
		{
			Assert.fail("Exception was thrown: " + ex);	
		}
	}

	/**
	 * Test method for CheckDefaultMcParticleConcentrationUnit
	 */
	@Test
	public void testCheckDefaultMcParticleConcentrationUnit() {
		try
		{
			nanoM.setMc_particleConcentrationUnit("ug/mL");
			nanoM.setMc_particleConcentration(15.0);
			DefaultUnits.checkDefaultMcParticleConcentrationUnit(nanoM, i);
			assertEquals("ug/mL", nanoM.getMc_particleConcentrationUnit());	
			assertEquals(15.0, nanoM.getMc_particleConcentration(), DELTA);	
			
			nanoM.setMc_particleConcentrationUnit("null");
			DefaultUnits.checkDefaultMcParticleConcentrationUnit(nanoM, i);
			assertEquals("null", nanoM.getMc_particleConcentrationUnit());	
			
			nanoM.setMc_particleConcentrationUnit("mg/mL");
			nanoM.setMc_particleConcentration(1.0);
			DefaultUnits.checkDefaultMcParticleConcentrationUnit(nanoM, i);
			assertEquals("ug/mL", nanoM.getMc_particleConcentrationUnit());	
			assertEquals(1000.0, nanoM.getMc_particleConcentration(), DELTA);
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
		try
		{
			nanoM.setMediumTempUnit("C");
			nanoM.setMediumTemp(15.0);
			DefaultUnits.checkDefaultMediumTempUnit(nanoM, i);
			assertEquals("C", nanoM.getMediumTempUnit());	
			assertEquals(15.0, nanoM.getMediumTemp(), DELTA);	
			
			nanoM.setMediumTempUnit("null");
			DefaultUnits.checkDefaultMediumTempUnit(nanoM, i);
			assertEquals("null", nanoM.getMediumTempUnit());	
			
			nanoM.setMediumTempUnit("F");
			nanoM.setMediumTemp(50.0);
			DefaultUnits.checkDefaultMediumTempUnit(nanoM, i);
			assertEquals("C", nanoM.getMediumTempUnit());	
			assertEquals(10.0, nanoM.getMediumTemp(), DELTA);
			
			nanoM.setMediumTempUnit("K");
			nanoM.setMediumTemp(273.15);
			DefaultUnits.checkDefaultMediumTempUnit(nanoM, i);
			assertEquals("C", nanoM.getMediumTempUnit());	
			assertEquals(0.0, nanoM.getMediumTemp(), DELTA);
		}
		catch (IllegalUnitsException ex)
		{
			Assert.fail("Exception was thrown: " + ex);	
		}
	}

	@Test
	public void testCheckDefaultMcMediumTempUnit() {
		try
		{
			nanoM.setMc_mediumTempUnit("C");
			nanoM.setMc_mediumTemp(15.0);
			DefaultUnits.checkDefaultMcMediumTempUnit(nanoM, i);
			assertEquals("C", nanoM.getMc_mediumTempUnit());	
			assertEquals(15.0, nanoM.getMc_mediumTemp(), DELTA);	
			
			nanoM.setMc_mediumTempUnit("null");
			DefaultUnits.checkDefaultMcMediumTempUnit(nanoM, i);
			assertEquals("null", nanoM.getMc_mediumTempUnit());	
			
			nanoM.setMc_mediumTempUnit("F");
			nanoM.setMc_mediumTemp(50.0);
			DefaultUnits.checkDefaultMcMediumTempUnit(nanoM, i);
			assertEquals("C", nanoM.getMc_mediumTempUnit());	
			assertEquals(10.0, nanoM.getMc_mediumTemp(), DELTA);
			
			nanoM.setMc_mediumTempUnit("K");
			nanoM.setMc_mediumTemp(273.15);
			DefaultUnits.checkDefaultMcMediumTempUnit(nanoM, i);
			assertEquals("C", nanoM.getMc_mediumTempUnit());	
			assertEquals(0.0, nanoM.getMc_mediumTemp(), DELTA);
		}
		catch (IllegalUnitsException ex)
		{
			Assert.fail("Exception was thrown: " + ex);	
		}
	}

	/**
	 * Test method for CheckDefaultZetaPotentialUnit.
	 */
	@Test
	public void testCheckDefaultZetaPotentialUnit() {
		try
		{
			nanoM.setZetaPotentialUnit("mV");
			nanoM.setZetaPotentialAvg(15.0);
			DefaultUnits.checkDefaultZetaPotentialUnit(nanoM, i);
			assertEquals("mV", nanoM.getZetaPotentialUnit());	
			assertEquals(15.0, nanoM.getZetaPotentialAvg(), DELTA);	
			
			nanoM.setZetaPotentialUnit("null");
			DefaultUnits.checkDefaultZetaPotentialUnit(nanoM, i);
			assertEquals("null", nanoM.getZetaPotentialUnit());	
			
			nanoM.setZetaPotentialUnit("uV");
			nanoM.setZetaPotentialAvg(1.0);
			DefaultUnits.checkDefaultZetaPotentialUnit(nanoM, i);
			assertEquals("mV", nanoM.getZetaPotentialUnit());	
			assertEquals(0.001, nanoM.getZetaPotentialAvg(), DELTA);
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
			nanoM.setSizeDistribUnit("nm");
			nanoM.setSizeDistribAvg(15.0);
			DefaultUnits.checkDefaultSizeDistributionUnit(nanoM, i);
			assertEquals("nm", nanoM.getSizeDistribUnit());	
			assertEquals(15.0, nanoM.getSizeDistribAvg(), DELTA);	
			
			nanoM.setSizeDistribUnit("null");
			DefaultUnits.checkDefaultSizeDistributionUnit(nanoM, i);
			assertEquals("null", nanoM.getSizeDistribUnit());	
			
			nanoM.setSizeDistribUnit("um");
			nanoM.setSizeDistribAvg(20.0);
			DefaultUnits.checkDefaultSizeDistributionUnit(nanoM, i);
			assertEquals("nm", nanoM.getSizeDistribUnit());	
			assertEquals(20000.0, nanoM.getSizeDistribAvg(), DELTA);
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
			nanoM.setSizeDistribUnit2("nm");
			nanoM.setSizeDistribAvg2(15.0);
			DefaultUnits.checkDefaultSizeDistributionUnit2(nanoM, i);
			assertEquals("nm", nanoM.getSizeDistribUnit2());	
			assertEquals(15.0, nanoM.getSizeDistribAvg2(), DELTA);	
			
			nanoM.setSizeDistribUnit2("null");
			DefaultUnits.checkDefaultSizeDistributionUnit2(nanoM, i);
			assertEquals("null", nanoM.getSizeDistribUnit2());	
			
			nanoM.setSizeDistribUnit2("um");
			nanoM.setSizeDistribAvg2(20.0);
			DefaultUnits.checkDefaultSizeDistributionUnit2(nanoM, i);
			assertEquals("nm", nanoM.getSizeDistribUnit2());	
			assertEquals(20000.0, nanoM.getSizeDistribAvg2(), DELTA);
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
			nanoM.setSerumConcentrationUnit("%");
			nanoM.setSerumConcentration(15.0);
			DefaultUnits.checkDefaultSerumConcentrationUnit(nanoM, i);
			assertEquals("%", nanoM.getSerumConcentrationUnit());	
			assertEquals(15.0, nanoM.getSerumConcentration(), DELTA);	
			
			nanoM.setSerumConcentrationUnit("null");
			DefaultUnits.checkDefaultSerumConcentrationUnit(nanoM, i);
			assertEquals("null", nanoM.getSerumConcentrationUnit());	
			
			nanoM.setSerumConcentrationUnit("fraction");
			nanoM.setSerumConcentration(0.20);
			DefaultUnits.checkDefaultSerumConcentrationUnit(nanoM, i);
			assertEquals("%", nanoM.getSerumConcentrationUnit());	
			assertEquals(20.0, nanoM.getSerumConcentration(), DELTA);
		}
		catch (IllegalUnitsException ex)
		{
			Assert.fail("Exception was thrown: " + ex);	
		}
	}

	@Test
	public void testCheckDefaultMcSerumConcentrationUnit() {
		try
		{
			nanoM.setMc_serumConcentrationUnit("%");
			nanoM.setMc_serumConcentration(15.0);
			DefaultUnits.checkDefaultMcSerumConcentrationUnit(nanoM, i);
			assertEquals("%", nanoM.getMc_serumConcentrationUnit());	
			assertEquals(15.0, nanoM.getMc_serumConcentration(), DELTA);	
			
			nanoM.setMc_serumConcentrationUnit("null");
			DefaultUnits.checkDefaultMcSerumConcentrationUnit(nanoM, i);
			assertEquals("null", nanoM.getMc_serumConcentrationUnit());	
			
			nanoM.setMc_serumConcentrationUnit("fraction");
			nanoM.setMc_serumConcentration(0.20);
			DefaultUnits.checkDefaultMcSerumConcentrationUnit(nanoM, i);
			assertEquals("%", nanoM.getMc_serumConcentrationUnit());	
			assertEquals(20.0, nanoM.getMc_serumConcentration(), DELTA);
		}
		catch (IllegalUnitsException ex)
		{
			Assert.fail("Exception was thrown: " + ex);	
		}
	}

	/**
	 * 
	 */
	@Test
	public void testCheckDefaultAntibioticConcentrationUnit() {
		try
		{
			nanoM.setAntibioticConcentrationUnit("%");
			nanoM.setAntibioticConcentration(15.0);
			DefaultUnits.checkDefaultAntibioticConcentrationUnit(nanoM, i);
			assertEquals("%", nanoM.getAntibioticConcentrationUnit());	
			assertEquals(15.0, nanoM.getAntibioticConcentration(), DELTA);	
			
			nanoM.setAntibioticConcentrationUnit("null");
			DefaultUnits.checkDefaultAntibioticConcentrationUnit(nanoM, i);
			assertEquals("null", nanoM.getAntibioticConcentrationUnit());	
			
			nanoM.setAntibioticConcentrationUnit("fraction");
			nanoM.setAntibioticConcentration(0.20);
			DefaultUnits.checkDefaultAntibioticConcentrationUnit(nanoM, i);
			assertEquals("%", nanoM.getAntibioticConcentrationUnit());	
			assertEquals(20.0, nanoM.getAntibioticConcentration(), DELTA);
		}
		catch (IllegalUnitsException ex)
		{
			Assert.fail("Exception was thrown: " + ex);	
		}
	}

	@Test
	public void testCheckDefaultMcAntibioticConcentrationUnit() {
		try
		{
			nanoM.setMc_antibioticConcentrationUnit("%");
			nanoM.setMc_antibioticConcentration(15.0);
			DefaultUnits.checkDefaultMcAntibioticConcentrationUnit(nanoM, i);
			assertEquals("%", nanoM.getMc_antibioticConcentrationUnit());	
			assertEquals(15.0, nanoM.getMc_antibioticConcentration(), DELTA);	
			
			nanoM.setMc_antibioticConcentrationUnit("null");
			DefaultUnits.checkDefaultMcAntibioticConcentrationUnit(nanoM, i);
			assertEquals("null", nanoM.getMc_antibioticConcentrationUnit());	
			
			nanoM.setMc_antibioticConcentrationUnit("fraction");
			nanoM.setMc_antibioticConcentration(0.20);
			DefaultUnits.checkDefaultMcAntibioticConcentrationUnit(nanoM, i);
			assertEquals("%", nanoM.getMc_antibioticConcentrationUnit());	
			assertEquals(20.0, nanoM.getMc_antibioticConcentration(), DELTA);
		}
		catch (IllegalUnitsException ex)
		{
			Assert.fail("Exception was thrown: " + ex);	
		}
	}


	/**
	 * Test method for CheckDefaultDOMUnit
	 */
	@Test
	public void testCheckDefaultDOMUnit() {
		try
		{
			nanoM.setDomUnit("mg/L");
			nanoM.setDomConcentration(15.0);
			DefaultUnits.checkDefaultDOMUnit(nanoM, i);
			assertEquals("mg/L", nanoM.getDomUnit());	
			assertEquals(15.0, nanoM.getDomConcentration(), DELTA);	
			
			nanoM.setDomUnit("null");
			DefaultUnits.checkDefaultDOMUnit(nanoM, i);
			assertEquals("null", nanoM.getDomUnit());	
			
			nanoM.setDomUnit("ug/L");
			nanoM.setDomConcentration(1000.0);
			DefaultUnits.checkDefaultDOMUnit(nanoM, i);
			assertEquals("mg/L", nanoM.getDomUnit());	
			assertEquals(1.0, nanoM.getDomConcentration(), DELTA);
		}
		catch (IllegalUnitsException ex)
		{
			Assert.fail("Exception was thrown: " + ex);	
		}
	}

	/**
	 * 
	 */
	@Test
	public void testCheckDefaultMcDOMUnit() {
		try
		{
			nanoM.setMc_domUnit("mg/L");
			nanoM.setMc_domConcentration(15.0);
			DefaultUnits.checkDefaultMcDOMUnit(nanoM, i);
			assertEquals("mg/L", nanoM.getMc_domUnit());	
			assertEquals(15.0, nanoM.getMc_domConcentration(), DELTA);	
			
			nanoM.setMc_domUnit("null");
			DefaultUnits.checkDefaultMcDOMUnit(nanoM, i);
			assertEquals("null", nanoM.getMc_domUnit());	
			
			nanoM.setMc_domUnit("ug/L");
			nanoM.setMc_domConcentration(1000.0);
			DefaultUnits.checkDefaultMcDOMUnit(nanoM, i);
			assertEquals("mg/L", nanoM.getMc_domUnit());	
			assertEquals(1.0, nanoM.getMc_domConcentration(), DELTA);
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
			nanoM.setSalinityUnit("ppt");
			nanoM.setSalinityValue(15.0);
			DefaultUnits.checkDefaultSalinityUnit(nanoM, i);
			assertEquals("ppt", nanoM.getSalinityUnit());	
			assertEquals(15.0, nanoM.getSalinityValue(), DELTA);	
			
			nanoM.setSalinityUnit("null");
			DefaultUnits.checkDefaultSalinityUnit(nanoM, i);
			assertEquals("null", nanoM.getSalinityUnit());	
			
			nanoM.setSalinityUnit("psu");
			nanoM.setSalinityValue(10.0);
			DefaultUnits.checkDefaultSalinityUnit(nanoM, i);
			assertEquals("ppt", nanoM.getSalinityUnit());	
			assertEquals(10.0, nanoM.getSalinityValue(), DELTA);
		}
		catch (IllegalUnitsException ex)
		{
			Assert.fail("Exception was thrown: " + ex);	
		}
	}

	@Test
	public void testCheckDefaultMcSalinityUnit() {
		try
		{
			nanoM.setMc_salinityUnit("ppt");
			nanoM.setMc_salinityValue(15.0);
			DefaultUnits.checkDefaultMcSalinityUnit(nanoM, i);
			assertEquals("ppt", nanoM.getMc_salinityUnit());	
			assertEquals(15.0, nanoM.getMc_salinityValue(), DELTA);	
			
			nanoM.setMc_salinityUnit("null");
			DefaultUnits.checkDefaultMcSalinityUnit(nanoM, i);
			assertEquals("null", nanoM.getMc_salinityUnit());	
			
			nanoM.setMc_salinityUnit("psu");
			nanoM.setMc_salinityValue(10.0);
			DefaultUnits.checkDefaultMcSalinityUnit(nanoM, i);
			assertEquals("ppt", nanoM.getMc_salinityUnit());	
			assertEquals(10.0, nanoM.getMc_salinityValue(), DELTA);
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
			nanoM.setParticleExposDurationUnit("hours");
			nanoM.setParticleExposDuration(15.0);
			DefaultUnits.checkDefaultParticleExposureDurationUnit(nanoM, i);
			assertEquals("hours", nanoM.getParticleExposDurationUnit());	
			assertEquals(15.0, nanoM.getParticleExposDuration(), DELTA);	
			
			nanoM.setParticleExposDurationUnit("null");
			DefaultUnits.checkDefaultParticleExposureDurationUnit(nanoM, i);
			assertEquals("null", nanoM.getParticleExposDurationUnit());	
			
			nanoM.setParticleExposDurationUnit("minutes");
			nanoM.setParticleExposDuration(60.0);
			DefaultUnits.checkDefaultParticleExposureDurationUnit(nanoM, i);
			assertEquals("hours", nanoM.getParticleExposDurationUnit());	
			assertEquals(1.0, nanoM.getParticleExposDuration(), DELTA);
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
			nanoM.setUvaDoseUnit("J/cm^2");
			nanoM.setUvaDose(15.0);
			DefaultUnits.checkDefaultUVADoseUnit(nanoM, i);
			assertEquals("J/cm^2", nanoM.getUvaDoseUnit());	
			assertEquals(15.0, nanoM.getUvaDose(), DELTA);	
			
			nanoM.setUvaDoseUnit("null");
			DefaultUnits.checkDefaultUVADoseUnit(nanoM, i);
			assertEquals("null", nanoM.getUvaDoseUnit());	
			
			nanoM.setUvaDoseUnit("mJ/cm^2");
			nanoM.setUvaDose(1000.0);
			DefaultUnits.checkDefaultUVADoseUnit(nanoM, i);
			assertEquals("J/cm^2", nanoM.getUvaDoseUnit());	
			assertEquals(1.0, nanoM.getUvaDose(), DELTA);
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
			nanoM.setUvaExposDurationUnit("hours");
			nanoM.setUvaExposDuration(15.0);
			DefaultUnits.checkDefaultUVAExposureDurationUnit(nanoM, i);
			assertEquals("hours", nanoM.getUvaExposDurationUnit());	
			assertEquals(15.0, nanoM.getUvaExposDuration(), DELTA);	
			
			nanoM.setUvaExposDurationUnit("null");
			DefaultUnits.checkDefaultUVAExposureDurationUnit(nanoM, i);
			assertEquals("null", nanoM.getUvaExposDurationUnit());	
			
			nanoM.setUvaExposDurationUnit("minutes");
			nanoM.setUvaExposDuration(60.0);
			DefaultUnits.checkDefaultUVAExposureDurationUnit(nanoM, i);
			assertEquals("hours", nanoM.getUvaExposDurationUnit());	
			assertEquals(1.0, nanoM.getUvaExposDuration(), DELTA);
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
			nanoM.setViabilityUnit("%");
			nanoM.setViabilityAvg(15.0);
			DefaultUnits.checkDefaultViabilityUnit(nanoM, i);
			assertEquals("%", nanoM.getViabilityUnit());	
			assertEquals(15.0, nanoM.getViabilityAvg(), DELTA);	
			
			nanoM.setViabilityUnit("null");
			DefaultUnits.checkDefaultViabilityUnit(nanoM, i);
			assertEquals("null", nanoM.getViabilityUnit());	
			
			nanoM.setViabilityUnit("fraction");
			nanoM.setViabilityAvg(0.20);
			DefaultUnits.checkDefaultViabilityUnit(nanoM, i);
			assertEquals("%", nanoM.getViabilityUnit());	
			assertEquals(20.0, nanoM.getViabilityAvg(), DELTA);;
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
			nanoM.setLc50Unit("ug/mL");
			nanoM.setLc50(11.0);
			DefaultUnits.checkDefaultLC50Unit(nanoM, i);
			assertEquals("ug/mL", nanoM.getLc50Unit());	
			assertEquals(11.0, nanoM.getLc50(), DELTA);	
			
			nanoM.setLc50Unit("null");
			DefaultUnits.checkDefaultLC50Unit(nanoM, i);
			assertEquals("null", nanoM.getLc50Unit());	
			
			nanoM.setLc50Unit("mg/mL");
			nanoM.setLc50(33.0);
			DefaultUnits.checkDefaultLC50Unit(nanoM, i);
			assertEquals("ug/mL", nanoM.getLc50Unit());	
			assertEquals(33000.0, nanoM.getLc50(), DELTA);
		}
		catch (IllegalUnitsException ex)
		{
			Assert.fail("Exception was thrown: " + ex);	
		}
	}

	

}
