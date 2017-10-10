package datamine;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import nanoQSAR.NanoToxExp;
import nanoQSAR.NanoToxExps;

/**
* This class gets a connection to the database, submits a SQL query to the database, 
* reads the data and stores them into a list.  
* @author Wilson Melendez
*
*/

public class MySqlQuery 
{
	/* Need this line to allow logging of error messages */
	private final static Logger LOGGER = Logger.getLogger("nanoQSAR");
	
	public MySqlQuery()
	{
		// Empty constructor.
	}
	
	/**
	 * This method declares and returns a string containing a SQL query.
	 * @author Wilson Melendez
	 * @param None.
	 * @return It returns a string containing an SQL statement query.
	 */
	public String getSqlQuery()
	{
		String sqlQuery;		
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
				  
		return sqlQuery;
		
	}
	
	/**
	 * This method returns an array of strings containing the header for the CSV file.
	 * @author Wilson Melendez
	 * @param None.
	 * @return String[]
	 */
	public String[] getHeader()	{
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
		return header;
	}
	
	/**
	 * @author Wilson Melendez
	 * @param sqlQuery
	 * @return  List<NanoToxExp> It returns a list containing objects of type NanoToxExp.
	 * @throws ClassNotFoundException  If no connection driver is available, throw exception.
	 * @throws SQLException  An exception may be thrown if connection to database fails.
	 */
	public NanoToxExps getNanoToxExps() throws Exception
	{		
		NanoToxExps nanoToxExps = new NanoToxExps();
		NanoToxExp nanoToxExp = null;
		Statement stmt = null;
		Connection conn = null;
		ResultSet rs = null;
		
		try
		{
			/* Get the connection to the database. */
			conn = ConnectionManager.getConnection();
			
			/* create the connection statement */
			stmt = conn.createStatement();
//			rs = stmt.executeQuery("SHOW TABLES FROM NanKnowBase"); 
//			rs = stmt.executeQuery("SHOW COLUMNS FROM link FROM NanKnowBase");
			rs = stmt.executeQuery(getSqlQuery());
			
			while (rs.next())  // Loop over the results received from the MySQL database server.
			{	
				/* Instantiate a new nanomaterial object for every iteration of loop. */
				nanoToxExp = new NanoToxExp();
				
				/* Retrieve data by column names. */	
				
				// Retrieve data corresponding to assay table.
				nanoToxExp.setMeasurementID(rs.getInt("MeasurementID"));
				nanoToxExp.setAssayType((String)rs.getObject("AssayType"));
				nanoToxExp.setAssayName((String)rs.getObject("AssayName"));
				nanoToxExp.setSampleName((String)rs.getObject("SampleName"));
				nanoToxExp.setSubjectSpecies((String)rs.getObject("SubjectSpecies"));
				nanoToxExp.setSubjectID((String)rs.getObject("SubjectID"));
				nanoToxExp.setCellType((String)rs.getObject("CellType"));
				nanoToxExp.setCellSource((String)rs.getObject("CellSource"));
				nanoToxExp.setTestMediumID(rs.getInt("TestMediumID"));
				nanoToxExp.setPhAvg((Double)rs.getObject("pHAvg"));
				nanoToxExp.setPhApproxSymbol((String)rs.getObject("pHApproxSymbol"));
			    nanoToxExp.setPhUncertain((String)rs.getObject("pHUncertain"));
				nanoToxExp.setPhLow((Double)rs.getObject("pHLow"));
				nanoToxExp.setPhHigh((Double)rs.getObject("pHHigh"));
				nanoToxExp.setMediumTemp((Double)rs.getObject("MediumTemp"));
				nanoToxExp.setMediumTempUnit((String)rs.getObject("MediumTempUnit"));
				nanoToxExp.setTimeValue((Double)rs.getObject("TimeValue"));
				nanoToxExp.setTimeValueUnit((String)rs.getObject("TimeValueUnit"));
				nanoToxExp.setParticleConcentration((Double)rs.getObject("ParticleConcentration"));
				nanoToxExp.setParticleConcentrationUnit((String)rs.getObject("ParticleConcentrationUnit"));
				nanoToxExp.setParticleExposDuration((Double)rs.getObject("ParticleExposDuration"));
				nanoToxExp.setParticleExposDurationUnit((String)rs.getObject("ParticleExposDurationUnit"));
				nanoToxExp.setUvaDose((Double)rs.getObject("UvaDose"));
				nanoToxExp.setUvaDoseUnit((String)rs.getObject("UvaDoseUnit"));
				nanoToxExp.setUvaExposDuration((Double)rs.getObject("UvaExposDuration"));
				nanoToxExp.setUvaExposDurationUnit((String)rs.getObject("UvaExposDurationUnit"));
				nanoToxExp.setViabilityAvg((Double)rs.getObject("ViabilityAvg"));
				nanoToxExp.setViabilityApproxSymbol((String)rs.getObject("ViabilityApproxSymbol"));
				nanoToxExp.setViabilityUnit((String)rs.getObject("ViabilityUnit"));
				nanoToxExp.setViabilityUncertain((String)rs.getObject("ViabilityUncertain"));
				nanoToxExp.setViabilityLow((Double)rs.getObject("ViabilityLow"));
				nanoToxExp.setViabilityHigh((Double)rs.getObject("ViabilityHigh"));
				nanoToxExp.setViabilityMethod((String)rs.getObject("ViabilityMethod"));
				nanoToxExp.setLc50((Double)rs.getObject("Lc50"));
				nanoToxExp.setLc50ApproxSymbol((String)rs.getObject("Lc50ApproxSymbol"));
				nanoToxExp.setLc50Unit((String)rs.getObject("Lc50Unit"));
				
				// Retrieve data corresponding to materialChar table.
				nanoToxExp.setMaterialCharID(rs.getInt("MaterialCharID"));				
				nanoToxExp.setOrdMaterialID((String)rs.getObject("ORDMaterialID"));
				nanoToxExp.setDataSource((String)rs.getObject("DataSource"));
				nanoToxExp.setLotNumber((String)rs.getObject("LotNumber"));
				nanoToxExp.setCoreComp((String)rs.getObject("CoreComp"));
				nanoToxExp.setShellComp((String)rs.getObject("ShellComp"));
				nanoToxExp.setCoatingComp((String)rs.getObject("CoatingComp"));
				nanoToxExp.setCoatingAmount((Double)rs.getObject("CoatingAmount"));
				nanoToxExp.setCoatingAmountUnit((String)rs.getObject("CoatingAmountUnit"));
			    nanoToxExp.setFunctionalGroups((String)rs.getObject("FunctionalGroups"));
				nanoToxExp.setFunctionalizationProtocol((String)rs.getObject("FunctionalizationProtocol"));
				nanoToxExp.setPurity((Double)rs.getObject("Purity"));
				nanoToxExp.setPurityApproxSymbol((String)rs.getObject("PurityApproxSymbol"));
				nanoToxExp.setPurityUnit((String)rs.getObject("PurityUnit"));
				nanoToxExp.setPurityMethod((String)rs.getObject("PurityMethod"));
				nanoToxExp.setPurityRefChemical((String)rs.getObject("PurityRefChemical"));
				nanoToxExp.setContamUnit((String)rs.getObject("ContamUnit"));
				nanoToxExp.setContamAl((Double)rs.getObject("ContamAl"));
				nanoToxExp.setContamAs((Double)rs.getObject("ContamAs"));
				nanoToxExp.setContamBe((Double)rs.getObject("ContamBe"));
				nanoToxExp.setContamCa((Double)rs.getObject("ContamCa"));
				nanoToxExp.setContamCo((Double)rs.getObject("ContamCo"));
				nanoToxExp.setContamCr((Double)rs.getObject("ContamCr"));
				nanoToxExp.setContamFe((Double)rs.getObject("ContamFe"));
				nanoToxExp.setContamK((Double)rs.getObject("ContamK"));
				nanoToxExp.setContamMg((Double)rs.getObject("ContamMg"));
				nanoToxExp.setContamNa((Double)rs.getObject("ContamNa"));
				nanoToxExp.setContamP((Double)rs.getObject("ContamP"));
				nanoToxExp.setContamPb((Double)rs.getObject("ContamPb"));
				nanoToxExp.setContamSb((Double)rs.getObject("ContamSb"));
				nanoToxExp.setContamSe((Double)rs.getObject("ContamSe"));
				nanoToxExp.setContamSiO2((Double)rs.getObject("ContamSiO2"));
				nanoToxExp.setContamSn((Double)rs.getObject("ContamSn"));
				nanoToxExp.setContamTl((Double)rs.getObject("ContamTl"));
				nanoToxExp.setContamV((Double)rs.getObject("ContamV"));
			    nanoToxExp.setContamMethod((String)rs.getObject("ContamMethod"));
				nanoToxExp.setCrystalStructure((String)rs.getObject("CrystalStructure"));
				nanoToxExp.setCrystalStructureMethod((String)rs.getObject("CrystalStructureMethod"));
				nanoToxExp.setSynthesisMethod((String)rs.getObject("SynthesisMethod"));
				nanoToxExp.setSynthesisDate((Integer)rs.getObject("SynthesisDate"));
				nanoToxExp.setParticleOuterDiamAvg((Double)rs.getObject("ParticleOuterDiamAvg"));
				nanoToxExp.setParticleOuterDiamApproxSymbol((String)rs.getObject("ParticleOuterDiamApproxSymbol"));
				nanoToxExp.setParticleOuterDiamUnit((String)rs.getObject("ParticleOuterDiamUnit"));
				nanoToxExp.setParticleOuterDiamUncertain((String)rs.getObject("ParticleOuterDiamUncertain"));
				nanoToxExp.setParticleOuterDiamLow((Double)rs.getObject("ParticleOuterDiamLow"));
				nanoToxExp.setParticleOuterDiamHigh((Double)rs.getObject("ParticleOuterDiamHigh"));
				nanoToxExp.setParticleOuterDiamMethod((String)rs.getObject("ParticleOuterDiamMethod"));
				nanoToxExp.setParticleInnerDiamAvg((Double)rs.getObject("ParticleInnerDiamAvg"));
				nanoToxExp.setParticleInnerDiamApproxSymbol((String)rs.getObject("ParticleInnerDiamApproxSymbol"));
				nanoToxExp.setParticleInnerDiamUnit((String)rs.getObject("ParticleInnerDiamUnit"));
				nanoToxExp.setParticleInnerDiamUncertain((String)rs.getObject("ParticleInnerDiamUncertain"));
				nanoToxExp.setParticleInnerDiamLow((Double)rs.getObject("ParticleInnerDiamLow"));
				nanoToxExp.setParticleInnerDiamHigh((Double)rs.getObject("ParticleInnerDiamHigh"));
				nanoToxExp.setParticleInnerDiamMethod((String)rs.getObject("ParticleInnerDiamMethod"));
				nanoToxExp.setParticleLengthAvg((Double)rs.getObject("ParticleLengthAvg"));
				nanoToxExp.setParticleLengthApproxSymbol((String)rs.getObject("ParticleLengthApproxSymbol"));
				nanoToxExp.setParticleLengthUnit((String)rs.getObject("ParticleLengthUnit"));
				nanoToxExp.setParticleLengthUncertain((String)rs.getObject("ParticleLengthUncertain"));
				nanoToxExp.setParticleLengthLow((Double)rs.getObject("ParticleLengthLow"));
				nanoToxExp.setParticleLengthHigh((Double)rs.getObject("ParticleLengthHigh"));
			    nanoToxExp.setParticleLengthMethod((String)rs.getObject("ParticleLengthMethod"));
				nanoToxExp.setParticleThicknessAvg((Double)rs.getObject("ParticleThicknessAvg"));
				nanoToxExp.setParticleThicknessApproxSymbol((String)rs.getObject("ParticleThicknessApproxSymbol"));
				nanoToxExp.setParticleThicknessUnit((String)rs.getObject("ParticleThicknessUnit"));
				nanoToxExp.setParticleThicknessUncertain((String)rs.getObject("ParticleThicknessUncertain"));
				nanoToxExp.setParticleThicknessLow((Double)rs.getObject("ParticleThicknessLow"));
				nanoToxExp.setParticleThicknessHigh((Double)rs.getObject("ParticleThicknessHigh"));
				nanoToxExp.setParticleThicknessMethod((String)rs.getObject("ParticleThicknessMethod"));
				nanoToxExp.setWallNumber((String)rs.getObject("WallNumber"));
				nanoToxExp.setAspectRatio((Double)rs.getObject("AspectRatio"));
				nanoToxExp.setShape((String)rs.getObject("Shape"));
				nanoToxExp.setSurfaceAreaAvg((Double)rs.getObject("SurfaceAreaAvg"));
				nanoToxExp.setSurfaceAreaApproxSymbol((String)rs.getObject("SurfaceAreaApproxSymbol"));
				nanoToxExp.setSurfaceAreaUnit((String)rs.getObject("SurfaceAreaUnit"));
				nanoToxExp.setSurfaceAreaUncertain((String)rs.getObject("SurfaceAreaUncertain"));
				nanoToxExp.setSurfaceAreaLow((Double)rs.getObject("SurfaceAreaLow"));
				nanoToxExp.setSurfaceAreaHigh((Double)rs.getObject("SurfaceAreaHigh"));
				nanoToxExp.setSurfaceAreaMethod((String)rs.getObject("SurfaceAreaMethod"));
				nanoToxExp.setMc_timeValue((Double)rs.getObject("Mc_timeValue"));
				nanoToxExp.setMc_timeValueUnit((String)rs.getObject("Mc_timeValueUnit"));
				nanoToxExp.setMc_particleConcentration((Double)rs.getObject("Mc_particleConcentration"));
				nanoToxExp.setMc_particleConcentrationUnit((String)rs.getObject("Mc_particleConcentrationUnit"));
				nanoToxExp.setDispersionMediumID(rs.getInt("DispersionMediumID"));
				nanoToxExp.setSolubility((String)rs.getObject("Solubility"));
				nanoToxExp.setMc_pHAvg((Double)rs.getObject("Mc_pHAvg"));
				nanoToxExp.setMc_pHApproxSymbol((String)rs.getObject("Mc_pHApproxSymbol"));
			    nanoToxExp.setMc_pHUncertain((String)rs.getObject("Mc_pHUncertain"));
				nanoToxExp.setMc_pHLow((Double)rs.getObject("Mc_pHLow"));
				nanoToxExp.setMc_pHHigh((Double)rs.getObject("Mc_pHHigh"));
				nanoToxExp.setMc_mediumTemp((Double)rs.getObject("Mc_mediumTemp"));
				nanoToxExp.setMc_mediumTempUnit((String)rs.getObject("Mc_mediumTempUnit"));
				nanoToxExp.setZetaPotentialAvg((Double)rs.getObject("ZetaPotentialAvg"));
				nanoToxExp.setZetaPotentialApproxSymbol((String)rs.getObject("ZetaPotentialApproxSymbol"));
				nanoToxExp.setZetaPotentialUnit((String)rs.getObject("ZetaPotentialUnit"));
				nanoToxExp.setZetaPotentialUncertain((String)rs.getObject("ZetaPotentialUncertain"));
				nanoToxExp.setZetaPotentialLow((Double)rs.getObject("ZetaPotentialLow"));
				nanoToxExp.setZetaPotentialHigh((Double)rs.getObject("ZetaPotentialHigh"));
				nanoToxExp.setZetaPotentialMethod((String)rs.getObject("ZetaPotentialMethod"));
				nanoToxExp.setSizeDistribType((String)rs.getObject("SizeDistribType"));
				nanoToxExp.setSizeDistribModality((String)rs.getObject("SizeDistribModality"));
				nanoToxExp.setSizeDistribMethod((String)rs.getObject("SizeDistribMethod"));
				nanoToxExp.setSizeDistribAvg((Double)rs.getObject("SizeDistribAvg"));
				nanoToxExp.setSizeDistribApproxSymbol((String)rs.getObject("SizeDistribApproxSymbol"));
				nanoToxExp.setSizeDistribUnit((String)rs.getObject("SizeDistribUnit"));
				nanoToxExp.setSizeDistribUncertain((String)rs.getObject("SizeDistribUncertain"));
				nanoToxExp.setSizeDistribLow((Double)rs.getObject("SizeDistribLow"));
				nanoToxExp.setSizeDistribHigh((Double)rs.getObject("SizeDistribHigh"));
				nanoToxExp.setSizeDistribAvg2((Double)rs.getObject("SizeDistribAvg2"));;
				nanoToxExp.setSizeDistribApproxSymbol2((String)rs.getObject("SizeDistribApproxSymbol2"));
				nanoToxExp.setSizeDistribUnit2((String)rs.getObject("SizeDistribUnit2"));
				nanoToxExp.setSizeDistribUncertain2((String)rs.getObject("SizeDistribUncertain2"));
			    nanoToxExp.setSizeDistribLow2((Double)rs.getObject("SizeDistribLow2"));
				nanoToxExp.setSizeDistribHigh2((Double)rs.getObject("SizeDistribHigh2"));
				
				// Retrieve data corresponding to medium table.
			    nanoToxExp.setMediumID(rs.getInt("mediumID"));				
				nanoToxExp.setMediumDescription((String)rs.getObject("MediumDescription"));
				nanoToxExp.setSerumAdditive((String)rs.getObject("SerumAdditive"));
				nanoToxExp.setSerumConcentration((Double)rs.getObject("SerumConcentration"));
				nanoToxExp.setSerumConcentrationUnit((String)rs.getObject("SerumConcentrationUnit"));
				nanoToxExp.setAntibioticName((String)rs.getObject("AntibioticName"));
				nanoToxExp.setAntibioticConcentration((Double)rs.getObject("AntibioticConcentration"));
				nanoToxExp.setAntibioticConcentrationUnit((String)rs.getObject("AntibioticConcentrationUnit"));
				nanoToxExp.setDomForm((String)rs.getObject("DomForm"));
				nanoToxExp.setDomConcentration((Double)rs.getObject("DomConcentration"));
				nanoToxExp.setDomUnit((String)rs.getObject("DomUnit"));
				nanoToxExp.setSalinityValue((Double)rs.getObject("SalinityValue"));
				nanoToxExp.setSalinityUnit((String)rs.getObject("SalinityUnit"));		
				
				nanoToxExp.setMc_mediumDescription((String)rs.getObject("Mc_MediumDescription"));
				nanoToxExp.setMc_serumAdditive((String)rs.getObject("Mc_SerumAdditive"));
				nanoToxExp.setMc_serumConcentration((Double)rs.getObject("Mc_SerumConcentration"));
				nanoToxExp.setMc_serumConcentrationUnit((String)rs.getObject("Mc_SerumConcentrationUnit"));
				nanoToxExp.setMc_antibioticName((String)rs.getObject("Mc_AntibioticName"));
				nanoToxExp.setMc_antibioticConcentration((Double)rs.getObject("Mc_AntibioticConcentration"));
				nanoToxExp.setMc_antibioticConcentrationUnit((String)rs.getObject("Mc_AntibioticConcentrationUnit"));
				nanoToxExp.setMc_domForm((String)rs.getObject("Mc_DomForm"));
				nanoToxExp.setMc_domConcentration((Double)rs.getObject("Mc_DomConcentration"));
				nanoToxExp.setMc_domUnit((String)rs.getObject("Mc_DomUnit"));
				nanoToxExp.setMc_salinityValue((Double)rs.getObject("Mc_SalinityValue"));
				nanoToxExp.setMc_salinityUnit((String)rs.getObject("Mc_SalinityUnit"));
				
//				/* execute set() on each property of nanomaterial with values taken by SqlQuery */
//				final BeanInfo beanInfo = Introspector.getBeanInfo(nanomaterial.getClass());
//		        final PropertyDescriptor[] properties = beanInfo.getPropertyDescriptors();
//		        for (PropertyDescriptor property : properties) {
//		        	property.getWriteMethod().invoke(nanomaterial, rs.getObject(property.getDisplayName()));
//		        }
				
				nanoToxExps.add(nanoToxExp);  // Add object to list.
				
			}
			
		}
		catch(SQLException ex)
		{
			LOGGER.log(Level.SEVERE, "SQL operation failed.", ex);
			throw ex;
		}
		finally
		{
			/* close everything */
			DBUtil.close(rs);
			DBUtil.close(stmt);
			DBUtil.close(conn);
		}
		
		return nanoToxExps;
	}
	
	

}
