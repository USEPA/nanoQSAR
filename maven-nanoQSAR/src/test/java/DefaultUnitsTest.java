import static org.junit.Assert.*;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.BeforeClass;

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
	private static List<NanoMaterial> nanomaterials = new ArrayList<NanoMaterial>();
	private static MySqlQuery sqlNano = new MySqlQuery();
	private static String sqlQuery = sqlNano.getSqlQuery();
	Double nullValue = null;
	NanoMaterial nanoM = new NanoMaterial();	
	int i = 0;  // This is just a dummy index from the  point of this test.
	
	@BeforeClass
	public static void setUp() throws IOException, GeneralSecurityException
	{
		/* Input database connection information and name of output file. */
		try
		{
			String filename = System.getProperty("user.dir") + "\\nanoQSAR.properties";
			DBUtil.loadProperties(filename);
		}
		catch(IOException | GeneralSecurityException ex)
		{
			throw ex;
		}
	}
	
	/**
	 * This test checks whether the data mined from the MySQL database have valid units.
	 * There are first, second, and null options specified for each parameter with units.
	 * @author Wilson Melendez
	 */
	@Test
	public void testBeforeUnitConversion()
	{
		// nanomaterials = new ArrayList<NanoMaterial>();
		try
		{	
			nanomaterials = sqlNano.getNanoMaterials(sqlQuery);
			String strU;
			
			for (NanoMaterial nanoM : nanomaterials)
			{				
				strU = String.valueOf(nanoM.getCoatingAmountUnit()).trim();
				if (!strU.equals("ug") && !strU.equals("null") && !strU.equals("mg"))
				{
					throw new IllegalUnitsException("Illegal coating amount unit: " + strU);
				}
				
				strU = String.valueOf(nanoM.getPurityUnit()).trim();
				if (!strU.equals("%") && !strU.equals("null") && !strU.equals("Mass %") && !strU.equals("fraction"))
				{
					throw new IllegalUnitsException("Illegal purity unit: " + strU);
				}
				
				strU = String.valueOf(nanoM.getContamUnit()).trim();
				if (!strU.equals("ppm") && !strU.equals("null") && !strU.equals("ppt"))
				{
					throw new IllegalUnitsException("Illegal Contaminant unit: " + strU);
				}
				
				strU = String.valueOf(nanoM.getParticleOuterDiamUnit()).trim();
				if (!strU.equals("nm") && !strU.equals("null") && !strU.equals("um"))
				{
					throw new IllegalUnitsException("Illegal Particle Outer Diameter unit: " + strU);
				}
				
				strU = String.valueOf(nanoM.getParticleInnerDiamUnit()).trim();
				if (!strU.equals("nm") && !strU.equals("null") && !strU.equals("um"))
				{
					throw new IllegalUnitsException("Illegal Particle Inner Diameter unit: " + strU);
				}
				
				strU = String.valueOf(nanoM.getParticleLengthUnit()).trim();
				if (!strU.equals("nm") && !strU.equals("null") && !strU.equals("um"))
				{
					throw new IllegalUnitsException("Illegal Particle Length unit: " + strU);
				}
				
				strU = String.valueOf(nanoM.getParticleThicknessUnit()).trim();
				if (!strU.equals("nm") && !strU.equals("null") && !strU.equals("um"))
				{
					throw new IllegalUnitsException("Illegal Particle Thickness unit: " + strU);
				}
				
				strU = String.valueOf(nanoM.getSurfaceAreaUnit()).trim();
				if (!strU.equals("m^2/g") && !strU.equals("null") && !strU.equals("cm^2/g"))
				{
					throw new IllegalUnitsException("Illegal surface area unit: " + strU);
				}
				
				strU = String.valueOf(nanoM.getTimeValueUnit()).trim();
				if (!strU.equals("hours") && !strU.equals("null") && !strU.equals("minutes"))
				{
					throw new IllegalUnitsException("Illegal time value unit: " + strU);
				}
				
				strU = String.valueOf(nanoM.getMc_timeValueUnit()).trim();
				if (!strU.equals("hours") && !strU.equals("null") && !strU.equals("minutes"))
				{
					throw new IllegalUnitsException("Illegal Mc_time value unit: " + strU);
				}
				
				strU = String.valueOf(nanoM.getParticleConcentrationUnit()).trim();
				if (!strU.equals("ug/mL") && !strU.equals("null") && !strU.equals("mg/mL"))
				{
					throw new IllegalUnitsException("Illegal Particle Concentration unit: " + strU);
				}
				
				strU = String.valueOf(nanoM.getMc_particleConcentrationUnit()).trim();
				if (!strU.equals("mg/L") && !strU.equals("null") && !strU.equals("ug/L"))
				{
					throw new IllegalUnitsException("Illegal Mc_particle Concentration unit: " + strU);
				}
				
				strU = String.valueOf(nanoM.getMediumTempUnit()).trim();
				if (!strU.equals("C") && !strU.equals("null") && !strU.equals("F") && !strU.equals("K"))
				{
					throw new IllegalUnitsException("Illegal medium temperature unit: " + strU);
				}
				
				strU = String.valueOf(nanoM.getMc_mediumTempUnit()).trim();
				if (!strU.equals("C") && !strU.equals("null") && !strU.equals("F") && !strU.equals("K"))
				{
					throw new IllegalUnitsException("Illegal Mc_medium temperature unit: " + strU);
				}
				
				strU = String.valueOf(nanoM.getZetaPotentialUnit()).trim();
				if (!strU.equals("mV") && !strU.equals("null") && !strU.equals("uV"))
				{
					throw new IllegalUnitsException("Illegal zeta potential unit: " + strU);
				}
				
				strU = String.valueOf(nanoM.getSizeDistribUnit()).trim();
				if (!strU.equals("nm") && !strU.equals("null") && !strU.equals("um"))
				{
					throw new IllegalUnitsException("Illegal size distribution unit: " + strU);
				}
				
				strU = String.valueOf(nanoM.getSizeDistribUnit2()).trim();
				if (!strU.equals("nm") && !strU.equals("null") && !strU.equals("um"))
				{
					throw new IllegalUnitsException("Illegal size distribution 2 unit: " + strU);
				}
				
				strU = String.valueOf(nanoM.getSerumConcentrationUnit()).trim();
				if (!strU.equals("%") && !strU.equals("null") && !strU.equals("fraction"))
				{
					throw new IllegalUnitsException("Illegal serum concentration unit: " + strU);
				}
				
				strU = String.valueOf(nanoM.getMc_serumConcentrationUnit()).trim();
				if (!strU.equals("%") && !strU.equals("null") && !strU.equals("fraction"))
				{
					throw new IllegalUnitsException("Illegal Mc_serum concentration unit: " + strU);
				}
				
				strU = String.valueOf(nanoM.getAntibioticConcentrationUnit()).trim();
				if (!strU.equals("%") && !strU.equals("null") && !strU.equals("fraction"))
				{
					throw new IllegalUnitsException("Illegal antibiotic concentration unit: " + strU);
				}
				
				strU = String.valueOf(nanoM.getMc_antibioticConcentrationUnit()).trim();
				if (!strU.equals("%") && !strU.equals("null") && !strU.equals("fraction"))
				{
					throw new IllegalUnitsException("Illegal Mc_antibiotic concentration unit: " + strU);
				}
				
				strU = String.valueOf(nanoM.getDomUnit()).trim();
				if (!strU.equals("mg/L") && !strU.equals("null") && !strU.equals("ug/L"))
				{
					throw new IllegalUnitsException("Illegal DOM unit: " + strU);
				}
				
				strU = String.valueOf(nanoM.getMc_domUnit()).trim();
				if (!strU.equals("mg/L") && !strU.equals("null") && !strU.equals("ug/L"))
				{
					throw new IllegalUnitsException("Illegal Mc_DOM unit: " + strU);
				}
				
				strU = String.valueOf(nanoM.getSalinityUnit()).trim();
				if (!strU.equals("ppt") && !strU.equals("null") && !strU.equals("psu") && !strU.equals("g/kg"))
				{
					throw new IllegalUnitsException("Illegal salinity unit: " + strU);
				}
				
				strU = String.valueOf(nanoM.getMc_salinityUnit()).trim();
				if (!strU.equals("ppt") && !strU.equals("null") && !strU.equals("psu") && !strU.equals("g/kg"))
				{
					throw new IllegalUnitsException("Illegal Mc_salinity unit: " + strU);
				}
				
				strU = String.valueOf(nanoM.getParticleExposDurationUnit()).trim();
				if (!strU.equals("hours") && !strU.equals("null") && !strU.equals("minutes"))
				{
					throw new IllegalUnitsException("Illegal particle exposure duration unit: " + strU);
				}
				
				strU = String.valueOf(nanoM.getUvaDoseUnit()).trim();
				if (!strU.equals("J/cm^2") && !strU.equals("null") && !strU.equals("mJ/cm^2"))
				{
					throw new IllegalUnitsException("Illegal UVA dose unit: " + strU);
				}
				
				strU = String.valueOf(nanoM.getUvaExposDurationUnit()).trim();
				if (!strU.equals("hours") && !strU.equals("null") && !strU.equals("minutes"))
				{
					throw new IllegalUnitsException("Illegal UVA exposure duration unit: " + strU);
				}
				
				strU = String.valueOf(nanoM.getViabilityUnit()).trim();
				if (!strU.equals("%") && !strU.equals("null") && !strU.equals("fraction"))
				{
					throw new IllegalUnitsException("Illegal viability unit: " + strU);
				}
				
				strU = String.valueOf(nanoM.getLc50Unit()).trim();
				if (!strU.equals("ug/mL") && !strU.equals("null") && !strU.equals("mg/mL"))
				{
					throw new IllegalUnitsException("Illegal LC50 unit: " + strU);
				}			
			}			
		}
		catch(IllegalUnitsException ex)
		{
			Assert.fail("Exception was thrown: " + ex);		
		}
		catch(ClassNotFoundException ex)
		{
			Assert.fail("Exception was thrown: " + ex);		
		}
		catch (SQLException ex)
		{
			Assert.fail("Exception was thrown: " + ex);		
		}
	}
	
	/**
	 * This test checks whether the data have default units (first option or null) after calling
	 * the unit conversion method.
	 * @author Wilson Melendez
	 */
	@Test
	public void testAfterUnitConversion()
	{
		try
		{	
			String strU;
			nanomaterials = sqlNano.getNanoMaterials(sqlQuery);
			/* Check default units and perform unit conversions if necessary. */
			DefaultUnits.checkUnits(nanomaterials);
			
			for (NanoMaterial nanoM : nanomaterials)
			{				
				strU = String.valueOf(nanoM.getCoatingAmountUnit()).trim();
				if (!strU.equals("ug") && !strU.equals("null"))
				{
					throw new IllegalUnitsException("Illegal coating amount unit: " + strU);
				}
				
				strU = String.valueOf(nanoM.getPurityUnit()).trim();
				if (!strU.equals("%") && !strU.equals("null"))
				{
					throw new IllegalUnitsException("Illegal purity unit: " + strU);
				}
				
				strU = String.valueOf(nanoM.getContamUnit()).trim();
				if (!strU.equals("ppm") && !strU.equals("null"))
				{
					throw new IllegalUnitsException("Illegal Contaminant unit: " + strU);
				}
				
				strU = String.valueOf(nanoM.getParticleOuterDiamUnit()).trim();
				if (!strU.equals("nm") && !strU.equals("null"))
				{
					throw new IllegalUnitsException("Illegal Particle Outer Diameter unit: " + strU);
				}
				
				strU = String.valueOf(nanoM.getParticleInnerDiamUnit()).trim();
				if (!strU.equals("nm") && !strU.equals("null"))
				{
					throw new IllegalUnitsException("Illegal Particle Inner Diameter unit: " + strU);
				}
				
				strU = String.valueOf(nanoM.getParticleLengthUnit()).trim();
				if (!strU.equals("nm") && !strU.equals("null"))
				{
					throw new IllegalUnitsException("Illegal Particle Length unit: " + strU);
				}
				
				strU = String.valueOf(nanoM.getParticleThicknessUnit()).trim();
				if (!strU.equals("nm") && !strU.equals("null"))
				{
					throw new IllegalUnitsException("Illegal Particle Thickness unit: " + strU);
				}
				
				strU = String.valueOf(nanoM.getSurfaceAreaUnit()).trim();
				if (!strU.equals("m^2/g") && !strU.equals("null"))
				{
					throw new IllegalUnitsException("Illegal surface area unit: " + strU);
				}
				
				strU = String.valueOf(nanoM.getTimeValueUnit()).trim();
				if (!strU.equals("hours") && !strU.equals("null"))
				{
					throw new IllegalUnitsException("Illegal time value unit: " + strU);
				}
				
				strU = String.valueOf(nanoM.getMc_timeValueUnit()).trim();
				if (!strU.equals("hours") && !strU.equals("null"))
				{
					throw new IllegalUnitsException("Illegal Mc_time value unit: " + strU);
				}
				
				strU = String.valueOf(nanoM.getParticleConcentrationUnit()).trim();
				if (!strU.equals("ug/mL") && !strU.equals("null"))
				{
					throw new IllegalUnitsException("Illegal Particle Concentration unit: " + strU);
				}
				
				strU = String.valueOf(nanoM.getMc_particleConcentrationUnit()).trim();
				if (!strU.equals("mg/L") && !strU.equals("null"))
				{
					throw new IllegalUnitsException("Illegal Mc_particle Concentration unit: " + strU);
				}
				
				strU = String.valueOf(nanoM.getMediumTempUnit()).trim();
				if (!strU.equals("C") && !strU.equals("null"))
				{
					throw new IllegalUnitsException("Illegal medium temperature unit: " + strU);
				}
				
				strU = String.valueOf(nanoM.getMc_mediumTempUnit()).trim();
				if (!strU.equals("C") && !strU.equals("null"))
				{
					throw new IllegalUnitsException("Illegal Mc_medium temperature unit: " + strU);
				}
				
				strU = String.valueOf(nanoM.getZetaPotentialUnit()).trim();
				if (!strU.equals("mV") && !strU.equals("null"))
				{
					throw new IllegalUnitsException("Illegal zeta potential unit: " + strU);
				}
				
				strU = String.valueOf(nanoM.getSizeDistribUnit()).trim();
				if (!strU.equals("nm") && !strU.equals("null"))
				{
					throw new IllegalUnitsException("Illegal size distribution unit: " + strU);
				}
				
				strU = String.valueOf(nanoM.getSizeDistribUnit2()).trim();
				if (!strU.equals("nm") && !strU.equals("null"))
				{
					throw new IllegalUnitsException("Illegal size distribution 2 unit: " + strU);
				}
				
				strU = String.valueOf(nanoM.getSerumConcentrationUnit()).trim();
				if (!strU.equals("%") && !strU.equals("null"))
				{
					throw new IllegalUnitsException("Illegal serum concentration unit: " + strU);
				}
				
				strU = String.valueOf(nanoM.getMc_serumConcentrationUnit()).trim();
				if (!strU.equals("%") && !strU.equals("null"))
				{
					throw new IllegalUnitsException("Illegal Mc_serum concentration unit: " + strU);
				}
				
				strU = String.valueOf(nanoM.getAntibioticConcentrationUnit()).trim();
				if (!strU.equals("%") && !strU.equals("null"))
				{
					throw new IllegalUnitsException("Illegal antibiotic concentration unit: " + strU);
				}
				
				strU = String.valueOf(nanoM.getMc_antibioticConcentrationUnit()).trim();
				if (!strU.equals("%") && !strU.equals("null"))
				{
					throw new IllegalUnitsException("Illegal Mc_antibiotic concentration unit: " + strU);
				}
				
				strU = String.valueOf(nanoM.getDomUnit()).trim();
				if (!strU.equals("mg/L") && !strU.equals("null"))
				{
					throw new IllegalUnitsException("Illegal DOM unit: " + strU);
				}
				
				strU = String.valueOf(nanoM.getMc_domUnit()).trim();
				if (!strU.equals("mg/L") && !strU.equals("null"))
				{
					throw new IllegalUnitsException("Illegal Mc_DOM unit: " + strU);
				}
				
				strU = String.valueOf(nanoM.getSalinityUnit()).trim();
				if (!strU.equals("ppt") && !strU.equals("null"))
				{
					throw new IllegalUnitsException("Illegal salinity unit: " + strU);
				}
				
				strU = String.valueOf(nanoM.getMc_salinityUnit()).trim();
				if (!strU.equals("ppt") && !strU.equals("null"))
				{
					throw new IllegalUnitsException("Illegal Mc_salinity unit: " + strU);
				}
				
				strU = String.valueOf(nanoM.getParticleExposDurationUnit()).trim();
				if (!strU.equals("hours") && !strU.equals("null"))
				{
					throw new IllegalUnitsException("Illegal particle exposure duration unit: " + strU);
				}
				
				strU = String.valueOf(nanoM.getUvaDoseUnit()).trim();
				if (!strU.equals("J/cm^2") && !strU.equals("null"))
				{
					throw new IllegalUnitsException("Illegal UVA dose unit: " + strU);
				}
				
				strU = String.valueOf(nanoM.getUvaExposDurationUnit()).trim();
				if (!strU.equals("hours") && !strU.equals("null"))
				{
					throw new IllegalUnitsException("Illegal UVA exposure duration unit: " + strU);
				}
				
				strU = String.valueOf(nanoM.getViabilityUnit()).trim();
				if (!strU.equals("%") && !strU.equals("null"))
				{
					throw new IllegalUnitsException("Illegal viability unit: " + strU);
				}
				
				strU = String.valueOf(nanoM.getLc50Unit()).trim();
				if (!strU.equals("ug/mL") && !strU.equals("null"))
				{
					throw new IllegalUnitsException("Illegal LC50 unit: " + strU);
				}			
			}			
		}
		catch(IllegalUnitsException ex)
		{
			Assert.fail("Exception was thrown: " + ex);		
		}
		catch(ClassNotFoundException ex)
		{
			Assert.fail("Exception was thrown: " + ex);		
		}
		catch (SQLException ex)
		{
			Assert.fail("Exception was thrown: " + ex);		
		}
	}
	
	/**
	 * @ author Wilson Melendez
	 * This is a test method for the checkDefaultCoatingAmountUnit method.
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
			nanoM.setCoatingAmount(1.0);
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
	 * @ author Wilson Melendez
	 * This is a test method for the checkPurityUnit method.
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
	 * @ author Wilson Melendez
	 * This is a test method for the checkDefaultCoatingAmountUnit method.
	 */
	@Test
	public void testCheckDefaultContaminantUnit() {
		try
		{
			nanoM.setContamUnit("ppm");		
			nanoM.setContamAl(15.0);
			nanoM.setContamAs(15.0);
			nanoM.setContamBe(15.0);
			nanoM.setContamCa(15.0);
			nanoM.setContamCo(15.0);
			nanoM.setContamCr(15.0);
			nanoM.setContamFe(15.0);
			nanoM.setContamK(15.0);
			nanoM.setContamMg(15.0);
			nanoM.setContamNa(15.0);
			nanoM.setContamP(15.0);
			nanoM.setContamPb(15.0);
			nanoM.setContamSb(15.0);
			nanoM.setContamSe(15.0);
			nanoM.setContamSiO2(15.0);
			nanoM.setContamSn(15.0);
			nanoM.setContamTl(15.0);
			nanoM.setContamV(15.0);
			
			DefaultUnits.checkDefaultContaminantUnit(nanoM, i);
			assertEquals("ppm", nanoM.getContamUnit());				
			assertEquals(15.0, nanoM.getContamAl(), DELTA);				
			assertEquals(15.0, nanoM.getContamAs(), DELTA);				
			assertEquals(15.0, nanoM.getContamBe(), DELTA);				
			assertEquals(15.0, nanoM.getContamCa(), DELTA);				
			assertEquals(15.0, nanoM.getContamCo(), DELTA);				
			assertEquals(15.0, nanoM.getContamCr(), DELTA);				
			assertEquals(15.0, nanoM.getContamFe(), DELTA);				
			assertEquals(15.0, nanoM.getContamK(), DELTA);				
			assertEquals(15.0, nanoM.getContamMg(), DELTA);				
			assertEquals(15.0, nanoM.getContamNa(), DELTA);				
			assertEquals(15.0, nanoM.getContamP(), DELTA);				
			assertEquals(15.0, nanoM.getContamPb(), DELTA);				
			assertEquals(15.0, nanoM.getContamSb(), DELTA);				
			assertEquals(15.0, nanoM.getContamSe(), DELTA);				
			assertEquals(15.0, nanoM.getContamSiO2(), DELTA);				
			assertEquals(15.0, nanoM.getContamSn(), DELTA);				
			assertEquals(15.0, nanoM.getContamTl(), DELTA);				
			assertEquals(15.0, nanoM.getContamV(), DELTA);	
			
			
			nanoM.setContamUnit("null");
			DefaultUnits.checkDefaultContaminantUnit(nanoM, i);
			assertEquals("null", nanoM.getContamUnit());	
			
			nanoM.setContamUnit("ppt");
			nanoM.setContamAl(20.0);
			nanoM.setContamAs(20.0);
			nanoM.setContamBe(20.0);
			nanoM.setContamCa(20.0);
			nanoM.setContamCo(20.0);
			nanoM.setContamCr(20.0);
			nanoM.setContamFe(20.0);
			nanoM.setContamK(20.0);
			nanoM.setContamMg(20.0);
			nanoM.setContamNa(20.0);
			nanoM.setContamP(20.0);
			nanoM.setContamPb(20.0);
			nanoM.setContamSb(20.0);
			nanoM.setContamSe(20.0);
			nanoM.setContamSiO2(20.0);
			nanoM.setContamSn(20.0);
			nanoM.setContamTl(20.0);
			nanoM.setContamV(20.0);
			DefaultUnits.checkDefaultContaminantUnit(nanoM, i);
			assertEquals("ppm", nanoM.getContamUnit());	
			assertEquals(20000.0, nanoM.getContamAl(), DELTA);
			assertEquals(20000.0, nanoM.getContamAs(), DELTA);				
			assertEquals(20000.0, nanoM.getContamBe(), DELTA);				
			assertEquals(20000.0, nanoM.getContamCa(), DELTA);				
			assertEquals(20000.0, nanoM.getContamCo(), DELTA);				
			assertEquals(20000.0, nanoM.getContamCr(), DELTA);				
			assertEquals(20000.0, nanoM.getContamFe(), DELTA);				
			assertEquals(20000.0, nanoM.getContamK(), DELTA);				
			assertEquals(20000.0, nanoM.getContamMg(), DELTA);				
			assertEquals(20000.0, nanoM.getContamNa(), DELTA);				
			assertEquals(20000.0, nanoM.getContamP(), DELTA);				
			assertEquals(20000.0, nanoM.getContamPb(), DELTA);				
			assertEquals(20000.0, nanoM.getContamSb(), DELTA);				
			assertEquals(20000.0, nanoM.getContamSe(), DELTA);				
			assertEquals(20000.0, nanoM.getContamSiO2(), DELTA);				
			assertEquals(20000.0, nanoM.getContamSn(), DELTA);				
			assertEquals(20000.0, nanoM.getContamTl(), DELTA);				
			assertEquals(20000.0, nanoM.getContamV(), DELTA);	
		}
		catch (IllegalUnitsException ex)
		{
			Assert.fail("Exception was thrown: " + ex);	
		}
	}
	

	/**
	 * @author Wilson Melendez
	 * Test method tests the  checkDefaultParticleOuterDiameterUnit method.
	 */
	@Test
	public void testCheckDefaultParticleOuterDiameterUnit() {
		try
		{
			nanoM.setParticleOuterDiamUnit("nm");
			nanoM.setParticleOuterDiamAvg(15.0);
			nanoM.setParticleOuterDiamHigh(15.0);
			nanoM.setParticleOuterDiamLow(15.0);
			DefaultUnits.checkDefaultParticleOuterDiameterUnit(nanoM, i);
			assertEquals("nm", nanoM.getParticleOuterDiamUnit());	
			assertEquals(15.0, nanoM.getParticleOuterDiamAvg(), DELTA);	
			assertEquals(15.0, nanoM.getParticleOuterDiamHigh(), DELTA);	
			assertEquals(15.0, nanoM.getParticleOuterDiamLow(), DELTA);	
			
			nanoM.setParticleOuterDiamUnit("null");
			DefaultUnits.checkDefaultParticleOuterDiameterUnit(nanoM, i);
			assertEquals("null", nanoM.getParticleOuterDiamUnit());	
			
			nanoM.setParticleOuterDiamUnit("um");
			nanoM.setParticleOuterDiamAvg(20.0);
			nanoM.setParticleOuterDiamHigh(20.0);
			nanoM.setParticleOuterDiamLow(20.0);
			DefaultUnits.checkDefaultParticleOuterDiameterUnit(nanoM, i);
			assertEquals("nm", nanoM.getParticleOuterDiamUnit());	
			assertEquals(20000.0, nanoM.getParticleOuterDiamAvg(), DELTA);
			assertEquals(20000.0, nanoM.getParticleOuterDiamHigh(), DELTA);
			assertEquals(20000.0, nanoM.getParticleOuterDiamLow(), DELTA);
		}
		catch (IllegalUnitsException ex)
		{
			Assert.fail("Exception was thrown: " + ex);	
		}
	}

	/**
	 * @author Wilson Melendez
	 * Test method the checkDefaultParticleInnerDiameterUnit method.
	 */
	@Test
	public void testCheckDefaultParticleInnerDiameterUnit() {
		try
		{
			nanoM.setParticleInnerDiamUnit("nm");
			nanoM.setParticleInnerDiamAvg(15.0);
			nanoM.setParticleInnerDiamHigh(15.0);
			nanoM.setParticleInnerDiamLow(15.0);
			DefaultUnits.checkDefaultParticleInnerDiameterUnit(nanoM, i);
			assertEquals("nm", nanoM.getParticleInnerDiamUnit());	
			assertEquals(15.0, nanoM.getParticleInnerDiamAvg(), DELTA);	
			assertEquals(15.0, nanoM.getParticleInnerDiamHigh(), DELTA);
			assertEquals(15.0, nanoM.getParticleInnerDiamLow(), DELTA);
			
			nanoM.setParticleInnerDiamUnit("null");
			DefaultUnits.checkDefaultParticleInnerDiameterUnit(nanoM, i);
			assertEquals("null", nanoM.getParticleInnerDiamUnit());	
			
			nanoM.setParticleInnerDiamUnit("um");
			nanoM.setParticleInnerDiamAvg(20.0);
			nanoM.setParticleInnerDiamHigh(20.0);
			nanoM.setParticleInnerDiamLow(20.0);
			DefaultUnits.checkDefaultParticleInnerDiameterUnit(nanoM, i);
			assertEquals("nm", nanoM.getParticleInnerDiamUnit());	
			assertEquals(20000.0, nanoM.getParticleInnerDiamAvg(), DELTA);
			assertEquals(20000.0, nanoM.getParticleInnerDiamHigh(), DELTA);
			assertEquals(20000.0, nanoM.getParticleInnerDiamLow(), DELTA);
		}
		catch (IllegalUnitsException ex)
		{
			Assert.fail("Exception was thrown: " + ex);	
		}
	}

	/**
	 * @author Wilson Melendez
	 * Test method tests the DefaultParticleLengthUnit method.
	 */
	@Test
	public void testCheckDefaultParticleLengthUnit() {
		try
		{
			nanoM.setParticleLengthUnit("nm");
			nanoM.setParticleLengthAvg(15.0);
			nanoM.setParticleLengthHigh(15.0);
			nanoM.setParticleLengthLow(15.0);
			DefaultUnits.checkDefaultParticleLengthUnit(nanoM, i);
			assertEquals("nm", nanoM.getParticleLengthUnit());	
			assertEquals(15.0, nanoM.getParticleLengthAvg(), DELTA);
			assertEquals(15.0, nanoM.getParticleLengthHigh(), DELTA);
			assertEquals(15.0, nanoM.getParticleLengthLow(), DELTA);
			
			nanoM.setParticleLengthUnit("null");
			DefaultUnits.checkDefaultParticleLengthUnit(nanoM, i);
			assertEquals("null", nanoM.getParticleLengthUnit());	
			
			nanoM.setParticleLengthUnit("um");
			nanoM.setParticleLengthAvg(20.0);
			nanoM.setParticleLengthHigh(20.0);
			nanoM.setParticleLengthLow(20.0);
			DefaultUnits.checkDefaultParticleLengthUnit(nanoM, i);
			assertEquals("nm", nanoM.getParticleLengthUnit());	
			assertEquals(20000.0, nanoM.getParticleLengthAvg(), DELTA);
			assertEquals(20000.0, nanoM.getParticleLengthHigh(), DELTA);
			assertEquals(20000.0, nanoM.getParticleLengthLow(), DELTA);
		}
		catch (IllegalUnitsException ex)
		{
			Assert.fail("Exception was thrown: " + ex);	
		}
	}

	/**
	 * @author Wilson Melendez
	 * Test method the checkDefaultParticleThicknessUnit method.
	 */
	@Test
	public void testCheckDefaultParticleThicknessUnit() {
		try
		{
			nanoM.setParticleThicknessUnit("nm");
			nanoM.setParticleThicknessAvg(15.0);
			nanoM.setParticleThicknessHigh(15.0);
			nanoM.setParticleThicknessLow(15.0);
			DefaultUnits.checkDefaultParticleThicknessUnit(nanoM, i);
			assertEquals("nm", nanoM.getParticleThicknessUnit());	
			assertEquals(15.0, nanoM.getParticleThicknessAvg(), DELTA);	
			assertEquals(15.0, nanoM.getParticleThicknessHigh(), DELTA);	
			assertEquals(15.0, nanoM.getParticleThicknessLow(), DELTA);	
			
			nanoM.setParticleThicknessUnit("null");
			DefaultUnits.checkDefaultParticleThicknessUnit(nanoM, i);
			assertEquals("null", nanoM.getParticleThicknessUnit());	
			
			nanoM.setParticleThicknessUnit("um");
			nanoM.setParticleThicknessAvg(20.0);
			nanoM.setParticleThicknessHigh(20.0);
			nanoM.setParticleThicknessLow(20.0);
			DefaultUnits.checkDefaultParticleThicknessUnit(nanoM, i);
			assertEquals("nm", nanoM.getParticleThicknessUnit());	
			assertEquals(20000.0, nanoM.getParticleThicknessAvg(), DELTA);
			assertEquals(20000.0, nanoM.getParticleThicknessHigh(), DELTA);
			assertEquals(20000.0, nanoM.getParticleThicknessLow(), DELTA);
		}
		catch (IllegalUnitsException ex)
		{
			Assert.fail("Exception was thrown: " + ex);	
		}
	}

	/**
	 * @author Wilson Melendez
	 * Test method tests the DefaultSurfaceAreaUnit method.
	 */
	@Test
	public void testCheckDefaultSurfaceAreaUnit() {
		try
		{
			nanoM.setSurfaceAreaUnit("m^2/g");
			nanoM.setSurfaceAreaAvg(15.0);
			nanoM.setSurfaceAreaHigh(15.0);
			nanoM.setSurfaceAreaLow(15.0);
			DefaultUnits.checkDefaultSurfaceAreaUnit(nanoM, i);
			assertEquals("m^2/g", nanoM.getSurfaceAreaUnit());	
			assertEquals(15.0, nanoM.getSurfaceAreaAvg(), DELTA);	
			assertEquals(15.0, nanoM.getSurfaceAreaHigh(), DELTA);
			assertEquals(15.0, nanoM.getSurfaceAreaLow(), DELTA);
			
			nanoM.setSurfaceAreaUnit("null");
			DefaultUnits.checkDefaultSurfaceAreaUnit(nanoM, i);
			assertEquals("null", nanoM.getSurfaceAreaUnit());	
			
			nanoM.setSurfaceAreaUnit("cm^2/g");
			nanoM.setSurfaceAreaAvg(1000.0);
			nanoM.setSurfaceAreaHigh(1000.0);
			nanoM.setSurfaceAreaLow(1000.0);
			DefaultUnits.checkDefaultSurfaceAreaUnit(nanoM, i);
			assertEquals("m^2/g", nanoM.getSurfaceAreaUnit());	
			assertEquals(0.1, nanoM.getSurfaceAreaAvg(), DELTA);
			assertEquals(0.1, nanoM.getSurfaceAreaHigh(), DELTA);
			assertEquals(0.1, nanoM.getSurfaceAreaLow(), DELTA);
		}
		catch (IllegalUnitsException ex)
		{
			Assert.fail("Exception was thrown: " + ex);	
		}
	}

	/**
	 * @author Wilson Melendez
	 * Test method tests the DefaultTimeValueUnit method.
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
	 * @author Wilson Melendez
	 * Test method tests the CheckDefaultMcTimeValueUnit method.
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
	 * @author Wilson Melendez
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
	 * @author Wilson Melendez
	 * Test method for CheckDefaultMcParticleConcentrationUnit
	 */
	@Test
	public void testCheckDefaultMcParticleConcentrationUnit() {
		try
		{
			nanoM.setMc_particleConcentrationUnit("mg/L");
			nanoM.setMc_particleConcentration(15.0);
			DefaultUnits.checkDefaultMcParticleConcentrationUnit(nanoM, i);
			assertEquals("mg/L", nanoM.getMc_particleConcentrationUnit());	
			assertEquals(15.0, nanoM.getMc_particleConcentration(), DELTA);	
			
			nanoM.setMc_particleConcentrationUnit("null");
			DefaultUnits.checkDefaultMcParticleConcentrationUnit(nanoM, i);
			assertEquals("null", nanoM.getMc_particleConcentrationUnit());	
			
			nanoM.setMc_particleConcentrationUnit("ug/L");
			nanoM.setMc_particleConcentration(1.0);
			DefaultUnits.checkDefaultMcParticleConcentrationUnit(nanoM, i);
			assertEquals("mg/L", nanoM.getMc_particleConcentrationUnit());	
			assertEquals(0.001, nanoM.getMc_particleConcentration(), DELTA);
		}
		catch (IllegalUnitsException ex)
		{
			Assert.fail("Exception was thrown: " + ex);	
		}
	}

	/**
	 * @author Wilson Melendez
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
	/**
	 * @author Wilson Melendez
	 */
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
	 * @author Wilson Melendez
	 * Test method for CheckDefaultZetaPotentialUnit.
	 */
	@Test
	public void testCheckDefaultZetaPotentialUnit() {
		try
		{
			nanoM.setZetaPotentialUnit("mV");
			nanoM.setZetaPotentialAvg(15.0);
			nanoM.setZetaPotentialHigh(15.0);
			nanoM.setZetaPotentialLow(15.0);
			DefaultUnits.checkDefaultZetaPotentialUnit(nanoM, i);
			assertEquals("mV", nanoM.getZetaPotentialUnit());	
			assertEquals(15.0, nanoM.getZetaPotentialAvg(), DELTA);	
			assertEquals(15.0, nanoM.getZetaPotentialHigh(), DELTA);
			assertEquals(15.0, nanoM.getZetaPotentialLow(), DELTA);
			
			nanoM.setZetaPotentialUnit("null");
			DefaultUnits.checkDefaultZetaPotentialUnit(nanoM, i);
			assertEquals("null", nanoM.getZetaPotentialUnit());	
			
			nanoM.setZetaPotentialUnit("uV");
			nanoM.setZetaPotentialAvg(1.0);
			nanoM.setZetaPotentialHigh(1.0);
			nanoM.setZetaPotentialLow(1.0);
			DefaultUnits.checkDefaultZetaPotentialUnit(nanoM, i);
			assertEquals("mV", nanoM.getZetaPotentialUnit());	
			assertEquals(0.001, nanoM.getZetaPotentialAvg(), DELTA);
			assertEquals(0.001, nanoM.getZetaPotentialHigh(), DELTA);
			assertEquals(0.001, nanoM.getZetaPotentialLow(), DELTA);
		}
		catch (IllegalUnitsException ex)
		{
			Assert.fail("Exception was thrown: " + ex);	
		}
	}

	
	/**
	 * @author Wilson Melendez
	 * Test method for {@link DefaultUnits#checkDefaultSizeDistributionUnit(java.lang.String)}.
	 */
	@Test
	public void testCheckDefaultSizeDistributionUnit() {
		try
		{
			nanoM.setSizeDistribUnit("nm");
			nanoM.setSizeDistribAvg(15.0);
			nanoM.setSizeDistribHigh(15.0);
			nanoM.setSizeDistribLow(15.0);
			DefaultUnits.checkDefaultSizeDistributionUnit(nanoM, i);
			assertEquals("nm", nanoM.getSizeDistribUnit());	
			assertEquals(15.0, nanoM.getSizeDistribAvg(), DELTA);	
			assertEquals(15.0, nanoM.getSizeDistribHigh(), DELTA);
			assertEquals(15.0, nanoM.getSizeDistribLow(), DELTA);
			
			nanoM.setSizeDistribUnit("null");
			DefaultUnits.checkDefaultSizeDistributionUnit(nanoM, i);
			assertEquals("null", nanoM.getSizeDistribUnit());	
			
			nanoM.setSizeDistribUnit("um");
			nanoM.setSizeDistribAvg(20.0);
			nanoM.setSizeDistribHigh(20.0);
			nanoM.setSizeDistribLow(20.0);
			DefaultUnits.checkDefaultSizeDistributionUnit(nanoM, i);
			assertEquals("nm", nanoM.getSizeDistribUnit());	
			assertEquals(20000.0, nanoM.getSizeDistribAvg(), DELTA);
			assertEquals(20000.0, nanoM.getSizeDistribHigh(), DELTA);
			assertEquals(20000.0, nanoM.getSizeDistribLow(), DELTA);
		}
		catch (IllegalUnitsException ex)
		{
			Assert.fail("Exception was thrown: " + ex);	
		}
	}


	/**
	 * @author Wilson Melendez
	 * Test method for {@link DefaultUnits#checkDefaultSizeDistributionUnit2(java.lang.String)}.
	 */
	@Test
	public void testCheckDefaultSizeDistributionUnit2() {
		try
		{
			nanoM.setSizeDistribUnit2("nm");
			nanoM.setSizeDistribAvg2(15.0);
			nanoM.setSizeDistribHigh2(15.0);
			nanoM.setSizeDistribLow2(15.0);
			DefaultUnits.checkDefaultSizeDistributionUnit2(nanoM, i);
			assertEquals("nm", nanoM.getSizeDistribUnit2());	
			assertEquals(15.0, nanoM.getSizeDistribAvg2(), DELTA);
			assertEquals(15.0, nanoM.getSizeDistribHigh2(), DELTA);
			assertEquals(15.0, nanoM.getSizeDistribLow2(), DELTA);
			
			nanoM.setSizeDistribUnit2("null");
			DefaultUnits.checkDefaultSizeDistributionUnit2(nanoM, i);
			assertEquals("null", nanoM.getSizeDistribUnit2());	
			
			nanoM.setSizeDistribUnit2("um");
			nanoM.setSizeDistribAvg2(20.0);
			nanoM.setSizeDistribHigh2(20.0);
			nanoM.setSizeDistribLow2(20.0);
			DefaultUnits.checkDefaultSizeDistributionUnit2(nanoM, i);
			assertEquals("nm", nanoM.getSizeDistribUnit2());	
			assertEquals(20000.0, nanoM.getSizeDistribAvg2(), DELTA);
			assertEquals(20000.0, nanoM.getSizeDistribHigh2(), DELTA);
			assertEquals(20000.0, nanoM.getSizeDistribLow2(), DELTA);
		}
		catch (IllegalUnitsException ex)
		{
			Assert.fail("Exception was thrown: " + ex);	
		}
	}


	/**
	 * @author Wilson Melendez
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

	/**
	 * @author Wilson Melendez
	 */
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
	 * @author Wilson Melendez
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

	/**
	 * @author Wilson Melendez
	 */
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
	 * @author Wilson Melendez
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
	 * @author Wilson Melendez
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
	 * @author Wilson Melendez
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

	/**
	 * @author Wilson Melendez
	 * This method tests for the checkDefaultMcSalinityUnit method.
	 */
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
	 * @author Wilson Melendez
	 * This method tests the checkDefaultParticleExposureDurationUnit method.
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
	 * @author Wilson Melendez
	 * Test method tests the checkDefaultUVADoseUnit method.
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
	 * @author Wilson Melendez
	 * Test method tests the checkDefaultUVAExposureDurationUnit method.
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
	 * @author Wilson Melendez
	 * Test method the checkDefaultViabilityUnit method.
	 */
	@Test
	public void testCheckDefaultViabilityUnit() {
		try
		{
			nanoM.setViabilityUnit("%");
			nanoM.setViabilityAvg(15.0);
			nanoM.setViabilityHigh(15.0);
			nanoM.setViabilityLow(15.0);
			DefaultUnits.checkDefaultViabilityUnit(nanoM, i);
			assertEquals("%", nanoM.getViabilityUnit());	
			assertEquals(15.0, nanoM.getViabilityAvg(), DELTA);
			assertEquals(15.0, nanoM.getViabilityHigh(), DELTA);
			assertEquals(15.0, nanoM.getViabilityLow(), DELTA);
			
			nanoM.setViabilityUnit("null");
			DefaultUnits.checkDefaultViabilityUnit(nanoM, i);
			assertEquals("null", nanoM.getViabilityUnit());	
			
			nanoM.setViabilityUnit("fraction");
			nanoM.setViabilityAvg(0.20);
			nanoM.setViabilityHigh(0.20);
			nanoM.setViabilityLow(0.20);
			DefaultUnits.checkDefaultViabilityUnit(nanoM, i);
			assertEquals("%", nanoM.getViabilityUnit());	
			assertEquals(20.0, nanoM.getViabilityAvg(), DELTA);
			assertEquals(20.0, nanoM.getViabilityHigh(), DELTA);
			assertEquals(20.0, nanoM.getViabilityLow(), DELTA);
		}
		catch (IllegalUnitsException ex)
		{
			Assert.fail("Exception was thrown: " + ex);	
		}
	}

	/**
	 * @author Wilson Melendez
	 * Test method the checkDefaultLC50Unit method.
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
