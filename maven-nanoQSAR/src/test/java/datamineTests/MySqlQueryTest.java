package datamineTests;
import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import datamine.DBUtil;
import datamine.MySqlQuery;
import nanoQSAR.NanoMaterial;
import nanoQSAR.NanoMaterials;

import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.sql.SQLException;


/**
 * This class is used to test the MySqlQuery class.
 * @author Wilson Melendez
 *
 */
public class MySqlQueryTest {
	
	String sqlQuery = "SELECT link.LinkID, link.MaterialCharID, link.MeasurementID, "
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
	
	String[] header = {
			"MaterialCharID","ORDMaterialID", "DataSource", "LotNumber", "CoreComp",
			"ShellComp", "CoatingComp", "CoatingAmount", "CoatingAmountUnit", 
			"FunctionalGroups", "FunctionalizationProtocol", "Purity", 
			"PurityApproxSymbol", "PurityUnit", "PurityMethod", 
			"PurityRefChemical", "ContamUnit", "ContamAl", "ContamAs", 
			"ContamBe", "ContamCa", "ContamCo", "ContamCr", "ContamFe", 
			"ContamK", "ContamMg", "ContamNa", "ContamP", "ContamPb", "ContamSb", 
			"ContamSe", "ContamSiO2", "ContamSn", "ContamTl", "ContamV", 
			"ContamMethod", "CrystalStructure", "CrystalStructureMethod", 
			"SynthesisMethod", "SynthesisDate", "ParticleOuterDiamAvg", 
			"ParticleOuterDiamApproxSymbol", "ParticleOuterDiamUnit", 
			"ParticleOuterDiamUncertain", "ParticleOuterDiamLow", 
			"ParticleOuterDiamHigh", "ParticleOuterDiamMethod", 
			"ParticleInnerDiamAvg", "ParticleInnerDiamApproxSymbol", 
			"ParticleInnerDiamUnit", "ParticleInnerDiamUncertain", 
			"ParticleInnerDiamLow", "ParticleInnerDiamHigh", 
			"ParticleInnerDiamMethod", "ParticleLengthAvg", 
			"ParticleLengthApproxSymbol", "ParticleLengthUnit", 
			"ParticleLengthUncertain", "ParticleLengthLow", 
			"ParticleLengthHigh", "ParticleLengthMethod", 
			"ParticleThicknessAvg", "ParticleThicknessApproxSymbol", 
			"ParticleThicknessUnit", "ParticleThicknessUncertain", 
			"ParticleThicknessLow", "ParticleThicknessHigh", 
			"ParticleThicknessMethod", "WallNumber", "AspectRatio", "Shape", 
			"SurfaceAreaAvg", "SurfaceAreaApproxSymbol", "SurfaceAreaUnit", 
			"SurfaceAreaUncertain", "SurfaceAreaLow", "SurfaceAreaHigh", 
			"SurfaceAreaMethod", "MC_TimeValue", "MC_TimeValueUnit", 
			"MC_ParticleConcentration", "MC_ParticleConcentrationUnit", 
			"DispersionMediumID", "MC_MediumDescription", "MC_SerumAdditive", 
			"MC_SerumConcentration", "MC_SerumConcentrationUnit", 
			"MC_AntibioticName", "MC_AntibioticConcentration", 
			"MC_AntibioticConcentrationUnit", "MC_DOMForm", "MC_DOMConcentration", 
			"MC_DOMUnit", "MC_SalinityValue", "MC_SalinityUnit", 			
			"Solubility", "MC_pHAvg", "MC_pHApproxSymbol", 
			"MC_pHUncertain", "MC_pHLow", "MC_pHHigh", "MC_MediumTemp", "MC_MediumTempUnit", 
			"ZetaPotentialAvg", "ZetaPotentialApproxSymbol", 
			"ZetaPotentialUnit", "ZetaPotentialUncertain", "ZetaPotentialLow", 
			"ZetaPotentialHigh", "ZetaPotentialMethod", "SizeDistribType", 
			"SizeDistribModality", "SizeDistribMethod", "SizeDistribAvg", 
			"SizeDistribApproxSymbol", "SizeDistribUnit", 
			"SizeDistribUncertain", "SizeDistribLow", "SizeDistribHigh", 
			"SizeDistribAvg2", "SizeDistribApproxSymbol2", "SizeDistribUnit2", 
			"SizeDistribUncertain2", "SizeDistribLow2", "SizeDistribHigh2",
			"MeasurementID", "AssayType", "AssayName", "SampleName", 
			"SubjectSpecies", "SubjectID", "CellType", "CellSource", 
			"TestMediumID", "MediumDescription", "SerumAdditive", 
			"SerumConcentration", "SerumConcentrationUnit", 
			"AntibioticName", "AntibioticConcentration", 
			"AntibioticConcentrationUnit", "DOMForm", "DOMConcentration", 
			"DOMUnit", "SalinityValue", "SalinityUnit", 
			"pHAvg", "pHApproxSymbol", "pHUncertain",  
			"pHLow", "pHHigh", "MediumTemp", "MediumTempUnit", 
			"TimeValue", "TimeValueUnit", "ParticleConcentration", 
			"ParticleConcentrationUnit", "ParticleExposDuration", 
			"ParticleExposDurationUnit", "UVADose", "UVADoseUnit", 
			"UVAExposDuration", "UVAExposDurationUnit", "ViabilityAvg", 
			"ViabilityApproxSymbol", "ViabilityUnit", "ViabilityUncertain", 
			"ViabilityLow", "ViabilityHigh", "ViabilityMethod", "LC50", 
			"LC50ApproxSymbol", "LC50Unit"
	};
	
	static String propFilename = System.getProperty("user.dir") + "\\nanoQSAR.properties";
	
	@BeforeClass
	public static void setUp() throws IOException, GeneralSecurityException
	{
		/* Input database connection information and name of output file. */
		String filename = System.getProperty("user.dir") + "\\nanoQSAR.properties";
		DBUtil.loadProperties(filename);
	}


	/**
	 * Test method for {@link MySqlQuery#getSqlQuery()}.
	 * This method compares two SQL queries.  They should be identical.
	 * @author Wilson Melendez & Paul Harten
	 */
	@Test
	public void testMySqlQuery() {
		MySqlQuery mySqlQuery = new MySqlQuery();
		Assert.assertNotNull(mySqlQuery);
	}
	
	@Test
	public void testGetSqlQuery() {
		MySqlQuery mySqlQuery = new MySqlQuery();
		Assert.assertTrue("SqlQuery strings don't match", sqlQuery.matches(mySqlQuery.getSqlQuery()));
	}

	@Test
	public void testGetHeader() {
		MySqlQuery mySqlQuery = new MySqlQuery();
		String[] remoteHeader = mySqlQuery.getHeader();
		
		for (int i=0; i<header.length; i++) {
			Assert.assertTrue("Header "+i+" doesn't match", header[i].matches(remoteHeader[i]));
		}

	}

	@Test
	public void testGetNanoMaterials() {

		try {
			MySqlQuery mySqlQuery = new MySqlQuery();
			Assert.assertNotNull(mySqlQuery);
			NanoMaterials nanoMaterials = mySqlQuery.getNanoMaterials();
			Assert.assertNotNull(nanoMaterials);
			Assert.assertFalse("Unexpected: nanoMaterials database is empty", nanoMaterials.isEmpty());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testWriteCsvFile() {

		try {
			MySqlQuery mySqlQuery = new MySqlQuery();
			Assert.assertNotNull(mySqlQuery);
			NanoMaterials nanoMaterials = new NanoMaterials(mySqlQuery);
			Assert.assertNotNull(nanoMaterials);
			Assert.assertFalse("Unexpected: nanoMaterials database is empty", nanoMaterials.isEmpty());
			nanoMaterials.writeCsvFile(DBUtil.getCsvFileName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}