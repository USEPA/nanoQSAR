import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
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
	 * @author Wilson Melendez
	 */
	@Test
	public void toStringArraytest() 
	{
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
	
	@Test
	/**
	 * This method verifies the header used in the CSV file.
	 * @author Wilson Melendez
	 */
	public void checkCsvHeaderTest()
	{
		String[] headerCSVExpected = {
	               "MeasurementID", "AssayType", "AssayName", "SampleName", 
                   "SubjectSpecies", "SubjectID", "CellType", "CellSource", 
                   "TestMediumID", "pHAvg", "pHApproxSymbol", "pHUncertain",  
                   "pHLow", "pHHigh", "MediumTemp", "MediumTempUnit", 
                   "TimeValue", "TimeValueUnit", "ParticleConcentration", 
	               "ParticleConcentrationUnit", "ParticleExposDuration", 
	               "ParticleExposDurationUnit", "UVADose", "UVADoseUnit", 
	               "UVAExposDuration", "UVAExposDurationUnit", "ViabilityAvg", 
	               "ViabilityApproxSymbol", "ViabilityUnit", "ViabilityUncertain", 
	               "ViabilityLow", "ViabilityHigh", "ViabilityMethod", "LC50", 
	               "LC50ApproxSymbol", "LC50Unit",
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
                   "DispersionMediumID", "Solubility", "MC_pHAvg", "MC_pHApproxSymbol", 
                   "MC_pHUncertain", "MC_pHLow", "MC_pHHigh", "MC_MediumTemp", "MC_MediumTempUnit", 
                   "ZetaPotentialAvg", "ZetaPotentialApproxSymbol", 
                   "ZetaPotentialUnit", "ZetaPotentialUncertain", "ZetaPotentialLow", 
                   "ZetaPotentialHigh", "ZetaPotentialMethod", "SizeDistribType", 
                   "SizeDistribModality", "SizeDistribMethod", "SizeDistribAvg", 
                   "SizeDistribApproxSymbol", "SizeDistribUnit", 
                   "SizeDistribUncertain", "SizeDistribLow", "SizeDistribHigh", 
                   "SizeDistribAvg2", "SizeDistribApproxSymbol2", "SizeDistribUnit2", 
                   "SizeDistribUncertain2", "SizeDistribLow2", "SizeDistribHigh2",
	               "MediumID", "MediumDescription", "SerumAdditive", 
	               "SerumConcentration", "SerumConcentrationUnit", 
	               "AntibioticName", "AntibioticConcentration", 
	               "AntibioticConcentrationUnit", "DOMForm", "DOMConcentration", 
	               "DOMUnit", "SalinityValue", "SalinityUnit"				               
	                };
		
		String[] headerCSVActual = NanoMaterial.getHeaderFile();  // Actual header 
		
		Assert.assertArrayEquals(headerCSVExpected, headerCSVActual);
	}

}
