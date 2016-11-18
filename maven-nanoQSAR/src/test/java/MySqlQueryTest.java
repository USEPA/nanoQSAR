import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * This class is used to test the MySqlQuery class.
 * @author Wilson Melendez
 *
 */
public class MySqlQueryTest 
{

	String sqlQuery = null;
	MySqlQuery sqlNano = null;
	List<NanoMaterial> nanomaterials;
	
	@BeforeClass  // Unused
	public static void setUpBeforeClass() throws Exception 
	{
	}

	@AfterClass  // Unused
	public static void tearDownAfterClass() throws Exception 
	{
	}

	@Before
	public void setUp() throws Exception 
	{
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
				 + "INNER JOIN assay "
				 + "ON link.MeasurementID = assay.MeasurementID "
				 + "INNER JOIN medium "
				 + "ON assay.TestMediumID = medium.MediumID "
				 + "ORDER BY link.LinkID";
	}

	@After   // Unused
	public void tearDown() throws Exception 
	{
	}

	/**
	 * This method compares two SQL queries.  They should be identical.
	 * @author Wilson Melendez
	 */
	@Test
	public void getSqlQueryTest() 
	{
		String str = sqlNano.getSqlQuery();
		assertEquals(sqlQuery,str);
	}
	
	/**
	 * This method checks whether selected fields are the same as the expected values.
	 * @author Wilson Melendez
	 */
	@Test
	public void getNanoMaterialsTest()
	{
		nanomaterials = new ArrayList<NanoMaterial>();
		try
		{
			nanomaterials = sqlNano.getNanoMaterials(sqlQuery);
			String strID = nanomaterials.get(0).getOrdMaterialID();
			String strUnits = nanomaterials.get(0).getLc50Unit().trim();
			assertEquals("TiO2-DEGUS-AeroxideP25", strID);
			assertEquals("ug/mL", strUnits);
		}
		catch(ClassNotFoundException ex)
		{
			System.out.println("Exception found: " + ex.getMessage());
		}
		catch (SQLException ex)
		{
			System.out.println("Exception found: " + ex.getMessage());
		}
		
	}

}
