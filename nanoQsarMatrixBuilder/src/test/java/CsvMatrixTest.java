
import java.util.ArrayList;
import java.util.List;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.jblas.DoubleMatrix;
import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Test;


/**
 * @author Wilson Melendez
 *
 */
public class CsvMatrixTest {

	/**
	 * Test method for {@link CsvMatrix#readCsvFile(java.lang.String)}.
	 */
	@Test(expected=FileNotFoundException.class)
	public void testReadCsvFile() throws FileNotFoundException, IOException
	{
		try
		{
			String filename = "FileDoesNotExist.csv";
			CsvMatrix.readCsvFile(filename);
		}
		catch(FileNotFoundException ex)
		{
			throw ex;
		}
		catch(IOException ex)
		{
			throw ex;
		}
	}

	/**
	 * Test method for buildMatricesContainingNulls().
	 */
	@Test
	public void testBuildMatricesContainingNulls() 
	{
		String[] expectedCsvHeader = {
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
	               "LC50ApproxSymbol", "LC50Unit",          
	                };
		CsvMatrix.setHeader(expectedCsvHeader);
		String[] values = new String[173];
		List<String[]> rowsTest = new ArrayList<String[]>();
		DoubleMatrix X = new DoubleMatrix(1,67);
		DoubleMatrix Y = new DoubleMatrix(1,2);
		
		for (int i = 0; i < 173; i++)
		{
			values[i] = "0.2";
		}
		values[7] = "2.3";
		values[11] = "98.4";
		
		for (int i = 0; i < 67; i++)
		{
			X.put(0,i,0.2);
		}
		X.put(0,0,2.3);
		X.put(0,1,98.4);
		Y.put(0,0,0.2);
		Y.put(0,1,0.2);
		
		rowsTest.add(values);
		CsvMatrix.setRows(rowsTest);	
		CsvMatrix.buildMatricesContainingNulls();
		DoubleMatrix Xtest = CsvMatrix.getXmatrix();
		DoubleMatrix Ytest = CsvMatrix.getYmatrix();
		
		DoubleMatrix Xsame, Ysame;
		Xsame = (X.eq(Xtest));  
		Ysame = (Y.eq(Ytest));
		
		for (int i = 0; i < 67; i++)
		{
			assertEquals(1.0,Xsame.get(0,i),1.0e-15);
		}
		
		assertEquals(1.0,Ysame.get(0,0),1.0e-15);
		assertEquals(1.0,Ysame.get(0,1),1.0e-15);
	}
	
	@Test
	public void testBuildMatricesWithoutNulls()
	{
		String[] expectedCsvHeader = {
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
	               "LC50ApproxSymbol", "LC50Unit",          
	                };
		CsvMatrix.setHeader(expectedCsvHeader);
		String[] values = new String[173];
		List<String[]> rowsTest = new ArrayList<String[]>();
		DoubleMatrix X = new DoubleMatrix(1,67);
		DoubleMatrix Y = new DoubleMatrix(1,2);
		
		for (int i = 0; i < 173; i++)
		{
			values[i] = "0.2";
		}
		values[7] = "2.3";
		values[11] = "98.4";
		
		for (int i = 0; i < 67; i++)
		{
			X.put(0,i,0.2);
		}
		X.put(0,0,2.3);
		X.put(0,1,98.4);
		Y.put(0,0,0.2);
		Y.put(0,1,0.2);
		
		rowsTest.add(values);
		CsvMatrix.setRows(rowsTest);	
		CsvMatrix.buildMatricesWithoutNulls();
		DoubleMatrix Xtest = CsvMatrix.getXmatrix();
		DoubleMatrix Ytest = CsvMatrix.getYmatrix();
		
		DoubleMatrix Xsame, Ysame;
		Xsame = (X.eq(Xtest));  
		Ysame = (Y.eq(Ytest));
		
		for (int i = 0; i < 67; i++)
		{
			assertEquals(1.0,Xsame.get(0,i),1.0e-15);
		}
		
		assertEquals(1.0,Ysame.get(0,0),1.0e-15);
		assertEquals(1.0,Ysame.get(0,1),1.0e-15);
	}

	@Test
	public void testGetValue()
	{
		String str = "null";
		Double value = CsvMatrix.getValue(str, 0.0, 100.0);
		assertTrue("Random value between 0 and 100.0", 0.0 <= value && value < 100.0);
		
		str = "54.7";
		value = CsvMatrix.getValue(str, 0.0, 100.0);
		assertEquals(54.7,value,1.0e-15);
	}
}
