import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.sql.SQLException;


/**
 * This class is used to test the MySqlQuery class.
 * @author Wilson Melendez
 *
 */
public class MySqlQueryTest {
	
	String sqlQuery = null;
	MySqlQuery sqlNano = null;
	List<NanoMaterial> nanomaterials;

	/**
	 * @throws java.lang.Exception
	 * @author Wilson Melendez
	 */
	@Before
	public void setUp() throws Exception {
		sqlNano = new MySqlQuery();	
		sqlQuery = "SELECT link.LinkID, link.MaterialCharID, link.MeasurementID, "
				 + "materialchar.ORDMaterialID, materialchar.DataSource, "
				 + "materialchar.LotNumber, materialchar.CoreComp, materialchar.ShellComp, "
				 + "materialchar.CoatingComp, materialchar.CoatingAmount, "
				 + "materialchar.CoatingAmountUnit, materialchar.FunctionalGroups, "
				 + "materialchar.FunctionalizationProtocol, materialchar.Purity, "
				 + "materialchar.PurityApproxSymbol, materialchar.PurityUnit, "
				 + "materialchar.PurityMethod, materialchar.PurityRefChemical, "
				 + "materialchar.ContamUnit, materialchar.ContamAl, materialchar.ContamAs, "
				 + "materialchar.ContamBe, materialchar.ContamCa, materialchar.ContamCo, "
				 + "materialchar.ContamCr, materialchar.ContamFe, materialchar.ContamK, "
				 + "materialchar.ContamMg, materialchar.ContamNa, materialchar.ContamP, "
				 + "materialchar.ContamPb, materialchar.ContamSb, materialchar.ContamSe, "
				 + "materialchar.ContamSiO2, materialchar.ContamSn, materialchar.ContamTl, "
				 + "materialchar.ContamV, materialchar.ContamMethod, "
				 + "materialchar.CrystalStructure, materialchar.CrystalStructureMethod, "
				 + "materialchar.SynthesisMethod, materialchar.SynthesisDate, "
				 + "materialchar.ParticleOuterDiamAvg, materialchar.ParticleOuterDiamApproxSymbol, "
				 + "materialchar.ParticleOuterDiamUnit, materialchar.ParticleOuterDiamUncertain, "
				 + "materialchar.ParticleOuterDiamLow, materialchar.ParticleOuterDiamHigh, "
				 + "materialchar.ParticleOuterDiamMethod, materialchar.ParticleInnerDiamAvg, "
				 + "materialchar.ParticleInnerDiamApproxSymbol, "
				 + "materialchar.ParticleInnerDiamUnit, materialchar.ParticleInnerDiamUncertain, "
				 + "materialchar.ParticleInnerDiamLow, materialchar.ParticleInnerDiamHigh, "
				 + "materialchar.ParticleInnerDiamMethod, materialchar.ParticleLengthAvg, "
				 + "materialchar.ParticleLengthApproxSymbol, materialchar.ParticleLengthUnit, "
				 + "materialchar.ParticleLengthUncertain, materialchar.ParticleLengthLow, "
				 + "materialchar.ParticleLengthHigh, materialchar.ParticleLengthMethod, "
				 + "materialchar.ParticleThicknessAvg, "
				 + "materialchar.ParticleThicknessApproxSymbol, "
				 + "materialchar.ParticleThicknessUnit, materialchar.ParticleThicknessUncertain, "
				 + "materialchar.ParticleThicknessLow, materialchar.ParticleThicknessHigh, "
				 + "materialchar.ParticleThicknessMethod, materialchar.WallNumber, "
				 + "materialchar.AspectRatio, materialchar.Shape, "
				 + "materialchar.SurfaceAreaAvg, materialchar.SurfaceAreaApproxSymbol, "
				 + "materialchar.SurfaceAreaUnit, materialchar.SurfaceAreaUncertain, "
				 + "materialchar.SurfaceAreaLow, materialchar.SurfaceAreaHigh, "
				 + "materialchar.SurfaceAreaMethod, "
				 + "materialchar.TimeValue AS 'Mc_timeValue', "
				 + "materialchar.TimeValueUnit AS 'Mc_timeValueUnit', "
				 + "materialchar.ParticleConcentration AS 'Mc_particleConcentration', "
				 + "materialchar.ParticleConcentrationUnit AS 'Mc_particleConcentrationUnit', "
				 + "materialchar.DispersionMediumID, "				 
				 + "materialchar.Solubility, "
				 + "materialchar.pHAvg AS 'Mc_pHAvg', "
				 + "materialchar.pHApproxSymbol AS 'Mc_pHApproxSymbol', "
				 + "materialchar.pHUncertain AS 'Mc_pHUncertain', "
				 + "materialchar.pHLow AS 'Mc_pHLow', "
				 + "materialchar.pHHigh AS 'Mc_pHHigh', "
				 + "materialchar.MediumTemp AS 'Mc_mediumTemp', "
				 + "materialchar.MediumTempUnit AS 'Mc_mediumTempUnit', "
				 + "materialchar.ZetaPotentialAvg, materialchar.ZetaPotentialApproxSymbol, "
				 + "materialchar.ZetaPotentialUnit, materialchar.ZetaPotentialUncertain, "
				 + "materialchar.ZetaPotentialLow, materialchar.ZetaPotentialHigh, "
				 + "materialchar.ZetaPotentialMethod, materialchar.SizeDistribType, "
				 + "materialchar.SizeDistribModality, materialchar.SizeDistribMethod, "
				 + "materialchar.SizeDistribAvg, materialchar.SizeDistribApproxSymbol, "
				 + "materialchar.SizeDistribUnit, materialchar.SizeDistribUncertain, "
				 + "materialchar.SizeDistribLow, materialchar.SizeDistribHigh, "
				 + "materialchar.SizeDistribAvg2, materialchar.SizeDistribApproxSymbol2, "
				 + "materialchar.SizeDistribUnit2, materialchar.SizeDistribUncertain2, "
				 + "materialchar.SizeDistribLow2, materialchar.SizeDistribHigh2, "
				 + "Mc_medium.MediumDescription AS Mc_MediumDescription, "
                 + "Mc_medium.SerumAdditive AS Mc_SerumAdditive, "
				 + "Mc_medium.SerumConcentration AS Mc_SerumConcentration, "
                 + "Mc_medium.SerumConcentrationUnit AS Mc_SerumConcentrationUnit, "
				 + "Mc_medium.AntibioticName AS Mc_AntibioticName, "
                 + "Mc_medium.AntibioticConcentration AS Mc_AntibioticConcentration, "
				 + "Mc_medium.AntibioticConcentrationUnit AS Mc_AntibioticConcentrationUnit, " 
                 + "Mc_medium.DOMForm AS Mc_DOMForm, " 
                 + "Mc_medium.DOMConcentration AS Mc_DOMConcentration, "
				 + "Mc_medium.DOMUnit AS Mc_DOMUnit, "
                 + "Mc_medium.SalinityValue AS Mc_SalinityValue, " 
                 + "Mc_medium.SalinityUnit AS Mc_SalinityUnit, "
				 + "assay.AssayType, assay.AssayName, assay.SampleName, assay.SubjectSpecies, "
				 + "assay.SubjectID, assay.CellType, assay.CellSource, assay.TestMediumID, "
				 + "assay.pHAvg, assay.pHApproxSymbol, assay.pHUncertain, assay.pHLow, "
				 + "assay.pHHigh, assay.MediumTemp, assay.MediumTempUnit, assay.TimeValue, "
				 + "assay.TimeValueUnit, assay.ParticleConcentration, "
				 + "assay.ParticleConcentrationUnit, assay.ParticleExposDuration, "
				 + "assay.ParticleExposDurationUnit, assay.UVADose, assay.UVADoseUnit, "
				 + "assay.UVAExposDuration, assay.UVAExposDurationUnit, assay.ViabilityAvg, "
				 + "assay.ViabilityApproxSymbol, assay.ViabilityUnit, assay.ViabilityUncertain, "
				 + "assay.ViabilityLow, assay.ViabilityHigh, assay.ViabilityMethod, assay.LC50, "
				 + "assay.LC50ApproxSymbol, assay.LC50Unit, "
				 + "medium.MediumID, medium.MediumDescription, medium.SerumAdditive, "
				 + "medium.SerumConcentration, medium.SerumConcentrationUnit, "
				 + "medium.AntibioticName, medium.AntibioticConcentration, "
				 + "medium.AntibioticConcentrationUnit, medium.DOMForm, medium.DOMConcentration, "
				 + "medium.DOMUnit, medium.SalinityValue, medium.SalinityUnit "
				 + "FROM link "
				 + "INNER JOIN materialchar "
				 + "ON link.MaterialCharID = materialchar.MaterialCharID "
				 + "INNER JOIN medium AS Mc_medium "
                 + "ON materialchar.DispersionMediumID = Mc_medium.MediumID "
				 + "INNER JOIN assay "
				 + "ON link.MeasurementID = assay.MeasurementID "
				 + "INNER JOIN medium "
				 + "ON assay.TestMediumID = medium.MediumID "
				 + "ORDER BY link.LinkID";
	}

	/**
	 * Test method for {@link MySqlQuery#getSqlQuery()}.
	 * This method compares two SQL queries.  They should be identical.
	 * @author Wilson Melendez
	 */
	@Test
	public void testGetSqlQuery() {
		String str = sqlNano.getSqlQuery();
		assertEquals(sqlQuery,str);
	}

	/**
	 * Test method for {@link MySqlQuery#getNanoMaterials(java.lang.String)}.
	 * This method checks whether selected fields are the same as the expected values.
	 * @author Wilson Melendez
	 */
	@Test
	public void testGetNanoMaterials() {
		nanomaterials = new ArrayList<NanoMaterial>();
		try
		{
			/* Input database connection information and name of output file. */
			String filename = "C:\\Users\\wmelende\\git\\maven-nanoQSAR\\properties.txt";
			DBUtil.loadProperties(filename);
			
			nanomaterials = sqlNano.getNanoMaterials(sqlQuery);
			for (NanoMaterial nanoM : nanomaterials)
			{
				String strU;
				
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
				if (!strU.equals("ug/mL") && !strU.equals("null") && !strU.equals("mg/mL"))
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
			
			// String strID = nanomaterials.get(0).getOrdMaterialID();
			// String strUnits = nanomaterials.get(0).getLc50Unit().trim();
			// assertEquals("TiO2-DEGUS-AeroxideP25", strID);
			// assertEquals("ug/mL", strUnits);
		}
		catch(IllegalUnitsException ex)
		{
			System.out.println("Exception found: " + ex.getMessage());
		}
		catch(ClassNotFoundException ex)
		{
			System.out.println("Exception found: " + ex.getMessage());
		}
		catch (SQLException ex)
		{
			System.out.println("Exception found: " + ex.getMessage());
		}
		catch (IOException ex)
		{
			System.out.println("Exception found: " + ex.getMessage());
		}
		
	}

}
