
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
* This class gets a connection to the database, submits a SQL query to the database, 
* reads the data and stores them into a list.  
* @author Wilson Melendez
*
*/

public class MySqlQuery 
{
	/* Need this line to allow logging of error messages */
	private final static Logger lOGGER = Logger.getLogger( LoggerInfo.class.getName() );
	
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
	 * @author Wilson Melendez
	 * @param sqlQuery
	 * @return  List<NanoMaterial> It returns a list containing objects of type NanoMaterial.
	 * @throws ClassNotFoundException  If no connection driver is available, throw exception.
	 * @throws SQLException  An exception may be thrown if connection to database fails.
	 */
	public List<NanoMaterial> getNanoMaterials(String sqlQuery) throws ClassNotFoundException, SQLException
	{		
		List<NanoMaterial> list = new ArrayList<NanoMaterial>();
		NanoMaterial nanomaterial = null;
		Statement stmt = null;
		Connection conn = null;
		ResultSet rs = null;
		
		/* Get the connection to the database. */
		conn = ConnectionManager.getConnection();	
		
		try
		{								
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sqlQuery);
			
			while (rs.next())  // Loop over the results received from the MySQL database server.
			{	
				/* Instantiate a new nanomaterial object for every iteration of loop. */
				nanomaterial = new NanoMaterial();
				
				/* Retrieve data by column names. */	
				
				// Retrieve data corresponding to assay table.
				nanomaterial.setMeasurementID(rs.getInt("MeasurementID"));
				nanomaterial.setAssayType((String)rs.getObject("AssayType"));
				nanomaterial.setAssayName((String)rs.getObject("AssayName"));
				nanomaterial.setSampleName((String)rs.getObject("SampleName"));
				nanomaterial.setSubjectSpecies((String)rs.getObject("SubjectSpecies"));
				nanomaterial.setSubjectID((String)rs.getObject("SubjectID"));
				nanomaterial.setCellType((String)rs.getObject("CellType"));
				nanomaterial.setCellSource((String)rs.getObject("CellSource"));
				nanomaterial.setTestMediumID(rs.getInt("TestMediumID"));
				nanomaterial.setPhAvg((Double)rs.getObject("pHAvg"));
				nanomaterial.setPhApproxSymbol((String)rs.getObject("pHApproxSymbol"));
			    nanomaterial.setPhUncertain((String)rs.getObject("pHUncertain"));
				nanomaterial.setPhLow((Double)rs.getObject("pHLow"));
				nanomaterial.setPhHigh((Double)rs.getObject("pHHigh"));
				nanomaterial.setMediumTemp((Double)rs.getObject("MediumTemp"));
				nanomaterial.setMediumTempUnit((String)rs.getObject("MediumTempUnit"));
				nanomaterial.setTimeValue((Double)rs.getObject("TimeValue"));
				nanomaterial.setTimeValueUnit((String)rs.getObject("TimeValueUnit"));
				nanomaterial.setParticleConcentration((Double)rs.getObject("ParticleConcentration"));
				nanomaterial.setParticleConcentrationUnit((String)rs.getObject("ParticleConcentrationUnit"));
				nanomaterial.setParticleExposDuration((Double)rs.getObject("ParticleExposDuration"));
				nanomaterial.setParticleExposDurationUnit((String)rs.getObject("ParticleExposDurationUnit"));
				nanomaterial.setUvaDose((Double)rs.getObject("UvaDose"));
				nanomaterial.setUvaDoseUnit((String)rs.getObject("UvaDoseUnit"));
				nanomaterial.setUvaExposDuration((Double)rs.getObject("UvaExposDuration"));
				nanomaterial.setUvaExposDurationUnit((String)rs.getObject("UvaExposDurationUnit"));
				nanomaterial.setViabilityAvg((Double)rs.getObject("ViabilityAvg"));
				nanomaterial.setViabilityApproxSymbol((String)rs.getObject("ViabilityApproxSymbol"));
				nanomaterial.setViabilityUnit((String)rs.getObject("ViabilityUnit"));
				nanomaterial.setViabilityUncertain((String)rs.getObject("ViabilityUncertain"));
				nanomaterial.setViabilityLow((Double)rs.getObject("ViabilityLow"));
				nanomaterial.setViabilityHigh((Double)rs.getObject("ViabilityHigh"));
				nanomaterial.setViabilityMethod((String)rs.getObject("ViabilityMethod"));
				nanomaterial.setLc50((Double)rs.getObject("Lc50"));
				nanomaterial.setLc50ApproxSymbol((String)rs.getObject("Lc50ApproxSymbol"));
				nanomaterial.setLc50Unit((String)rs.getObject("Lc50Unit"));
				
				// Retrieve data corresponding to materialChar table.
				nanomaterial.setMaterialCharID(rs.getInt("MaterialCharID"));				
				nanomaterial.setOrdMaterialID((String)rs.getObject("ORDMaterialID"));
				nanomaterial.setDataSource((String)rs.getObject("DataSource"));
				nanomaterial.setLotNumber((String)rs.getObject("LotNumber"));
				nanomaterial.setCoreComp((String)rs.getObject("CoreComp"));
				nanomaterial.setShellComp((String)rs.getObject("ShellComp"));
				nanomaterial.setCoatingComp((String)rs.getObject("CoatingComp"));
				nanomaterial.setCoatingAmount((Double)rs.getObject("CoatingAmount"));
				nanomaterial.setCoatingAmountUnit((String)rs.getObject("CoatingAmountUnit"));
			    nanomaterial.setFunctionalGroups((String)rs.getObject("FunctionalGroups"));
				nanomaterial.setFunctionalizationProtocol((String)rs.getObject("FunctionalizationProtocol"));
				nanomaterial.setPurity((Double)rs.getObject("Purity"));
				nanomaterial.setPurityApproxSymbol((String)rs.getObject("PurityApproxSymbol"));
				nanomaterial.setPurityUnit((String)rs.getObject("PurityUnit"));
				nanomaterial.setPurityMethod((String)rs.getObject("PurityMethod"));
				nanomaterial.setPurityRefChemical((String)rs.getObject("PurityRefChemical"));
				nanomaterial.setContamUnit((String)rs.getObject("ContamUnit"));
				nanomaterial.setContamAl((Double)rs.getObject("ContamAl"));
				nanomaterial.setContamAs((Double)rs.getObject("ContamAs"));
				nanomaterial.setContamBe((Double)rs.getObject("ContamBe"));
				nanomaterial.setContamCa((Double)rs.getObject("ContamCa"));
				nanomaterial.setContamCo((Double)rs.getObject("ContamCo"));
				nanomaterial.setContamCr((Double)rs.getObject("ContamCr"));
				nanomaterial.setContamFe((Double)rs.getObject("ContamFe"));
				nanomaterial.setContamK((Double)rs.getObject("ContamK"));
				nanomaterial.setContamMg((Double)rs.getObject("ContamMg"));
				nanomaterial.setContamNa((Double)rs.getObject("ContamNa"));
				nanomaterial.setContamP((Double)rs.getObject("ContamP"));
				nanomaterial.setContamPb((Double)rs.getObject("ContamPb"));
				nanomaterial.setContamSb((Double)rs.getObject("ContamSb"));
				nanomaterial.setContamSe((Double)rs.getObject("ContamSe"));
				nanomaterial.setContamSiO2((Double)rs.getObject("ContamSiO2"));
				nanomaterial.setContamSn((Double)rs.getObject("ContamSn"));
				nanomaterial.setContamTl((Double)rs.getObject("ContamTl"));
				nanomaterial.setContamV((Double)rs.getObject("ContamV"));
			    nanomaterial.setContamMethod((String)rs.getObject("ContamMethod"));
				nanomaterial.setCrystalStructure((String)rs.getObject("CrystalStructure"));
				nanomaterial.setCrystalStructureMethod((String)rs.getObject("CrystalStructureMethod"));
				nanomaterial.setSynthesisMethod((String)rs.getObject("SynthesisMethod"));
				nanomaterial.setSynthesisDate(rs.getDate("SynthesisDate"));
				nanomaterial.setParticleOuterDiamAvg((Double)rs.getObject("ParticleOuterDiamAvg"));
				nanomaterial.setParticleOuterDiamApproxSymbol((String)rs.getObject("ParticleOuterDiamApproxSymbol"));
				nanomaterial.setParticleOuterDiamUnit((String)rs.getObject("ParticleOuterDiamUnit"));
				nanomaterial.setParticleOuterDiamUncertain((String)rs.getObject("ParticleOuterDiamUncertain"));
				nanomaterial.setParticleOuterDiamLow((Double)rs.getObject("ParticleOuterDiamLow"));
				nanomaterial.setParticleOuterDiamHigh((Double)rs.getObject("ParticleOuterDiamHigh"));
				nanomaterial.setParticleOuterDiamMethod((String)rs.getObject("ParticleOuterDiamMethod"));
				nanomaterial.setParticleInnerDiamAvg((Double)rs.getObject("ParticleInnerDiamAvg"));
				nanomaterial.setParticleInnerDiamApproxSymbol((String)rs.getObject("ParticleInnerDiamApproxSymbol"));
				nanomaterial.setParticleInnerDiamUnit((String)rs.getObject("ParticleInnerDiamUnit"));
				nanomaterial.setParticleInnerDiamUncertain((String)rs.getObject("ParticleInnerDiamUncertain"));
				nanomaterial.setParticleInnerDiamLow((Double)rs.getObject("ParticleInnerDiamLow"));
				nanomaterial.setParticleInnerDiamHigh((Double)rs.getObject("ParticleInnerDiamHigh"));
				nanomaterial.setParticleInnerDiamMethod((String)rs.getObject("ParticleInnerDiamMethod"));
				nanomaterial.setParticleLengthAvg((Double)rs.getObject("ParticleLengthAvg"));
				nanomaterial.setParticleLengthApproxSymbol((String)rs.getObject("ParticleLengthApproxSymbol"));
				nanomaterial.setParticleLengthUnit((String)rs.getObject("ParticleLengthUnit"));
				nanomaterial.setParticleLengthUncertain((String)rs.getObject("ParticleLengthUncertain"));
				nanomaterial.setParticleLengthLow((Double)rs.getObject("ParticleLengthLow"));
				nanomaterial.setParticleLengthHigh((Double)rs.getObject("ParticleLengthHigh"));
			    nanomaterial.setParticleLengthMethod((String)rs.getObject("ParticleLengthMethod"));
				nanomaterial.setParticleThicknessAvg((Double)rs.getObject("ParticleThicknessAvg"));
				nanomaterial.setParticleThicknessApproxSymbol((String)rs.getObject("ParticleThicknessApproxSymbol"));
				nanomaterial.setParticleThicknessUnit((String)rs.getObject("ParticleThicknessUnit"));
				nanomaterial.setParticleThicknessUncertain((String)rs.getObject("ParticleThicknessUncertain"));
				nanomaterial.setParticleThicknessLow((Double)rs.getObject("ParticleThicknessLow"));
				nanomaterial.setParticleThicknessHigh((Double)rs.getObject("ParticleThicknessHigh"));
				nanomaterial.setParticleThicknessMethod((String)rs.getObject("ParticleThicknessMethod"));
				nanomaterial.setWallNumber((String)rs.getObject("WallNumber"));
				nanomaterial.setAspectRatio((Double)rs.getObject("AspectRatio"));
				nanomaterial.setShape((String)rs.getObject("Shape"));
				nanomaterial.setSurfaceAreaAvg((Double)rs.getObject("SurfaceAreaAvg"));
				nanomaterial.setSurfaceAreaApproxSymbol((String)rs.getObject("SurfaceAreaApproxSymbol"));
				nanomaterial.setSurfaceAreaUnit((String)rs.getObject("SurfaceAreaUnit"));
				nanomaterial.setSurfaceAreaUncertain((String)rs.getObject("SurfaceAreaUncertain"));
				nanomaterial.setSurfaceAreaLow((Double)rs.getObject("SurfaceAreaLow"));
				nanomaterial.setSurfaceAreaHigh((Double)rs.getObject("SurfaceAreaHigh"));
				nanomaterial.setSurfaceAreaMethod((String)rs.getObject("SurfaceAreaMethod"));
				nanomaterial.setMc_timeValue(rs.getInt("Mc_timeValue"));
				nanomaterial.setMc_timeValueUnit((String)rs.getObject("Mc_timeValueUnit"));
				nanomaterial.setMc_particleConcentration((Double)rs.getObject("Mc_particleConcentration"));
				nanomaterial.setMc_particleConcentrationUnit((String)rs.getObject("Mc_particleConcentrationUnit"));
				nanomaterial.setDispersionMediumID(rs.getInt("DispersionMediumID"));
				nanomaterial.setSolubility((String)rs.getObject("Solubility"));
				nanomaterial.setMc_pHAvg((Double)rs.getObject("Mc_pHAvg"));
				nanomaterial.setMc_pHApproxSymbol((String)rs.getObject("Mc_pHApproxSymbol"));
			    nanomaterial.setMc_pHUncertain((String)rs.getObject("Mc_pHUncertain"));
				nanomaterial.setMc_pHLow((Double)rs.getObject("Mc_pHLow"));
				nanomaterial.setMc_pHHigh((Double)rs.getObject("Mc_pHHigh"));
				nanomaterial.setMc_mediumTemp((Double)rs.getObject("Mc_mediumTemp"));
				nanomaterial.setMc_mediumTempUnit((String)rs.getObject("Mc_mediumTempUnit"));
				nanomaterial.setZetaPotentialAvg((Double)rs.getObject("ZetaPotentialAvg"));
				nanomaterial.setZetaPotentialApproxSymbol((String)rs.getObject("ZetaPotentialApproxSymbol"));
				nanomaterial.setZetaPotentialUnit((String)rs.getObject("ZetaPotentialUnit"));
				nanomaterial.setZetaPotentialUncertain((String)rs.getObject("ZetaPotentialUncertain"));
				nanomaterial.setZetaPotentialLow((Double)rs.getObject("ZetaPotentialLow"));
				nanomaterial.setZetaPotentialHigh((Double)rs.getObject("ZetaPotentialHigh"));
				nanomaterial.setZetaPotentialMethod((String)rs.getObject("ZetaPotentialMethod"));
				nanomaterial.setSizeDistribType((String)rs.getObject("SizeDistribType"));
				nanomaterial.setSizeDistribModality((String)rs.getObject("SizeDistribModality"));
				nanomaterial.setSizeDistribMethod((String)rs.getObject("SizeDistribMethod"));
				nanomaterial.setSizeDistribAvg((Double)rs.getObject("SizeDistribAvg"));
				nanomaterial.setSizeDistribApproxSymbol((String)rs.getObject("SizeDistribApproxSymbol"));
				nanomaterial.setSizeDistribUnit((String)rs.getObject("SizeDistribUnit"));
				nanomaterial.setSizeDistribUncertain((String)rs.getObject("SizeDistribUncertain"));
				nanomaterial.setSizeDistribLow((Double)rs.getObject("SizeDistribLow"));
				nanomaterial.setSizeDistribHigh((Double)rs.getObject("SizeDistribHigh"));
				nanomaterial.setSizeDistribAvg2((Double)rs.getObject("SizeDistribAvg2"));;
				nanomaterial.setSizeDistribApproxSymbol2((String)rs.getObject("SizeDistribApproxSymbol2"));
				nanomaterial.setSizeDistribUnit2((String)rs.getObject("SizeDistribUnit2"));
				nanomaterial.setSizeDistribUncertain2((String)rs.getObject("SizeDistribUncertain2"));
			    nanomaterial.setSizeDistribLow2((Double)rs.getObject("SizeDistribLow2"));
				nanomaterial.setSizeDistribHigh2((Double)rs.getObject("SizeDistribHigh2"));
				
				// Retrieve data corresponding to medium table.
			    nanomaterial.setMediumID(rs.getInt("mediumID"));				
				nanomaterial.setMediumDescription((String)rs.getObject("MediumDescription"));
				nanomaterial.setSerumAdditive((String)rs.getObject("SerumAdditive"));
				nanomaterial.setSerumConcentration((Double)rs.getObject("SerumConcentration"));
				nanomaterial.setSerumConcentrationUnit((String)rs.getObject("SerumConcentrationUnit"));
				nanomaterial.setAntibioticName((String)rs.getObject("AntibioticName"));
				nanomaterial.setAntibioticConcentration((Double)rs.getObject("AntibioticConcentration"));
				nanomaterial.setAntibioticConcentrationUnit((String)rs.getObject("AntibioticConcentrationUnit"));
				nanomaterial.setDomForm((String)rs.getObject("DomForm"));
				nanomaterial.setDomConcentration((Double)rs.getObject("DomConcentration"));
				nanomaterial.setDomUnit((String)rs.getObject("DomUnit"));
				nanomaterial.setSalinityValue((Double)rs.getObject("SalinityValue"));
				nanomaterial.setSalinityUnit((String)rs.getObject("SalinityUnit"));		
				
				nanomaterial.setMc_mediumDescription((String)rs.getObject("Mc_MediumDescription"));
				nanomaterial.setMc_serumAdditive((String)rs.getObject("Mc_SerumAdditive"));
				nanomaterial.setMc_serumConcentration((Double)rs.getObject("Mc_SerumConcentration"));
				nanomaterial.setMc_serumConcentrationUnit((String)rs.getObject("Mc_SerumConcentrationUnit"));
				nanomaterial.setMc_antibioticName((String)rs.getObject("Mc_AntibioticName"));
				nanomaterial.setMc_antibioticConcentration((Double)rs.getObject("Mc_AntibioticConcentration"));
				nanomaterial.setMc_antibioticConcentrationUnit((String)rs.getObject("Mc_AntibioticConcentrationUnit"));
				nanomaterial.setMc_domForm((String)rs.getObject("Mc_DomForm"));
				nanomaterial.setMc_domConcentration((Double)rs.getObject("Mc_DomConcentration"));
				nanomaterial.setMc_domUnit((String)rs.getObject("Mc_DomUnit"));
				nanomaterial.setMc_salinityValue((Double)rs.getObject("Mc_SalinityValue"));
				nanomaterial.setMc_salinityUnit((String)rs.getObject("Mc_SalinityUnit"));		
				
				list.add(nanomaterial);  // Add object to list.
				
			}
			
		}
		catch(SQLException ex)
		{
			lOGGER.log(Level.SEVERE, "Statement or ResultSet failed.", ex);
			throw ex;
		}
		finally  // Close everything.
		{
			DBUtil.close(rs);
			DBUtil.close(stmt);
			DBUtil.close(conn);
		}
		return list;
	}
	
	

}
