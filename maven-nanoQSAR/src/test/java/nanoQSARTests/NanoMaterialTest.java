/**
 * 
 */
package nanoQSARTests;

import static org.junit.Assert.*;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;

import org.junit.Test;

import junit.framework.Assert;
import nanoQSAR.NanoMaterial;

/**
 * @author pharten
 *
 */
public class NanoMaterialTest {

	@Test
	public final void testNanoMaterial() throws Exception {
		NanoMaterial nanoMaterial = new NanoMaterial();
		Assert.assertNotNull("NanoMaterial Constructer failed", nanoMaterial);
	}

	/**
	 * This method checks whether data were entered correctly into string array.
	 * @author Wilson Melendez
	 * @throws Exception 
	 */
	@Test
	public void testStoreDataAsStringArray() throws Exception {
		String [] entries;
		NanoMaterial n1 = new NanoMaterial();
		
		n1.setAssayName("cell viability");
		n1.setCellType("ARPE-19");
		n1.setOrdMaterialID("TiO2-ACROS-21358");
		n1.setSampleName("TiO2-I-142-NONE-0");
		n1.setParticleConcentration(0.0);
		n1.setParticleExposDuration(48.0);
		n1.setLc50(4.5);
		
		entries = n1.storeDataAsStringArray();
		assertEquals("cell viability",entries[127]);
		assertEquals("ARPE-19",entries[131]);
		assertEquals("TiO2-ACROS-21358",entries[1]);
		assertEquals("0.0",entries[155]);
		assertEquals("48.0",entries[157]);
		assertEquals("4.5",entries[170]);
	}
	
	@Test
	public final void testClone() throws Exception {
		
		NanoMaterial nanoMaterial = new NanoMaterial();
		nanoMaterial.setAssayName("cell viability");
		nanoMaterial.setCellType("ARPE-19");
		nanoMaterial.setOrdMaterialID("TiO2-ACROS-21358");
		nanoMaterial.setSampleName("TiO2-I-142-NONE-0");
		nanoMaterial.setParticleConcentration(0.0);
		nanoMaterial.setParticleExposDuration(48.0);
		nanoMaterial.setLc50(4.5);
		
		NanoMaterial clone = nanoMaterial.clone();
		Assert.assertNotNull("NanoMaterial Clone failed", clone);
		
		Assert.assertNotSame(nanoMaterial, clone);
		
		final BeanInfo beanInfo = Introspector.getBeanInfo(nanoMaterial.getClass());
        final PropertyDescriptor[] properties = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor property : properties) {
        	System.out.println(property.getName());
        	if (property.getName().contains("assayName")) assertPropertyEquals(property, nanoMaterial, clone);
        }
/*        
		// Link table fields
		Assert.assertEquals("materialCharID not equal", nanoMaterial.getMaterialCharID(), clone.getMaterialCharID());
		Assert.assertEquals("measurementID not equal", nanoMaterial.getMeasurementID(), clone.getMeasurementID());
		assertPropertyEquals()

				// Materialchar table fields
				clone.ordMaterialID = this.ordMaterialID;
				clone.dataSource = this.dataSource; 
				clone.lotNumber = this.lotNumber; 
				clone.coreComp = this.coreComp; 
				clone.shellComp = this.shellComp; 
				clone.coatingComp = this.coatingComp; 
				clone.coatingAmount = this.coatingAmount; 
				clone.coatingAmountUnit = this.coatingAmountUnit; 
				clone.functionalGroups = this.functionalGroups; 
				clone.functionalizationProtocol = this.functionalizationProtocol; 
				clone.purity = this.purity; 
				clone.purityApproxSymbol = this.purityApproxSymbol; 
				clone.purityUnit = this.purityUnit; 
				clone.purityMethod = this.purityMethod; 
				clone.purityRefChemical = this.purityRefChemical; 
				clone.contamUnit = this.contamUnit; 
				clone.contamAl = this.contamAl; 
				clone.contamAs = this.contamAs; 
				clone.contamBe = this.contamBe; 
				clone.contamCa = this.contamCa; 
				clone.contamCo = this.contamCo; 
				clone.contamCr = this.contamCr; 
				clone.contamFe = this.contamFe; 
				clone.contamK = this.contamK; 
				clone.contamMg = this.contamMg; 
				clone.contamNa = this.contamNa; 
				clone.contamP = this.contamP; 
				clone.contamPb = this.contamPb; 
				clone.contamSb = this.contamSb; 
				clone.contamSe = this.contamSe; 
				clone.contamSiO2 = this.contamSiO2; 
				clone.contamSn = this.contamSn; 
				clone.contamTl = this.contamTl; 
				clone.contamV = this.contamV; 
				clone.contamMethod = this.contamMethod; 
				clone.crystalStructure = this.crystalStructure; 
				clone.crystalStructureMethod = this.crystalStructureMethod; 
				clone.synthesisMethod = this.synthesisMethod; 
				clone.synthesisDate = this.synthesisDate; 
				clone.particleOuterDiamAvg = this.particleOuterDiamAvg; 
				clone.particleOuterDiamApproxSymbol = this.particleOuterDiamApproxSymbol; 
				clone.particleOuterDiamUnit = this.particleOuterDiamUnit; 
				clone.particleOuterDiamUncertain = this.particleOuterDiamUncertain; 
				clone.particleOuterDiamLow = this.particleOuterDiamLow; 
				clone.particleOuterDiamHigh = this.particleOuterDiamHigh; 
				clone.particleOuterDiamMethod = this.particleOuterDiamMethod; 
				clone.particleInnerDiamAvg = this.particleInnerDiamAvg; 
				clone.particleInnerDiamApproxSymbol = this.particleInnerDiamApproxSymbol; 
				clone.particleInnerDiamUnit = this.particleInnerDiamUnit; 
				clone.particleInnerDiamUncertain = this.particleInnerDiamUncertain; 
				clone.particleInnerDiamLow = this.particleInnerDiamLow; 
				clone.particleInnerDiamHigh = this.particleInnerDiamHigh; 
				clone.particleInnerDiamMethod = this.particleInnerDiamMethod; 
				clone.particleLengthAvg = this.particleLengthAvg; 
				clone.particleLengthApproxSymbol = this.particleLengthApproxSymbol; 
				clone.particleLengthUnit = this.particleLengthUnit; 
				clone.particleLengthUncertain = this.particleLengthUncertain; 
				clone.particleLengthLow = this.particleLengthLow; 
				clone.particleLengthHigh = this.particleLengthHigh; 
				clone.particleLengthMethod = this.particleLengthMethod; 
				clone.particleThicknessAvg = this.particleThicknessAvg; 
				clone.particleThicknessApproxSymbol = this.particleThicknessApproxSymbol; 
				clone.particleThicknessUnit = this.particleThicknessUnit; 
				clone.particleThicknessUncertain = this.particleThicknessUncertain; 
				clone.particleThicknessLow = this.particleThicknessLow; 
				clone.particleThicknessHigh = this.particleThicknessHigh; 
				clone.particleThicknessMethod = this.particleThicknessMethod; 
				clone.wallNumber = this.wallNumber; 
				clone.aspectRatio = this.aspectRatio; 
				clone.shape = this.shape; 
				clone.surfaceAreaAvg = this.surfaceAreaAvg; 
				clone.surfaceAreaApproxSymbol = this.surfaceAreaApproxSymbol; 
				clone.surfaceAreaUnit = this.surfaceAreaUnit; 
				clone.surfaceAreaUncertain = this.surfaceAreaUncertain; 
				clone.surfaceAreaLow = this.surfaceAreaLow; 
				clone.surfaceAreaHigh = this.surfaceAreaHigh; 
				clone.surfaceAreaMethod = this.surfaceAreaMethod; 
				clone.mc_timeValue = this.mc_timeValue; 
				clone.mc_timeValueUnit = this.mc_timeValueUnit; 
				clone.mc_particleConcentration = this.mc_particleConcentration; 
				clone.mc_particleConcentrationUnit = this.mc_particleConcentrationUnit; 
				clone.dispersionMediumID = this.dispersionMediumID; 
				clone.solubility = this.solubility; 
				clone.mc_pHAvg = this.mc_pHAvg; 
				clone.mc_pHApproxSymbol = this.mc_pHApproxSymbol; 
				clone.mc_pHUncertain = this.mc_pHUncertain; 
				clone.mc_pHLow = this.mc_pHLow; 
				clone.mc_pHHigh = this.mc_pHHigh; 
				clone.mc_mediumTemp = this.mc_mediumTemp; 
				clone.mc_mediumTempUnit = this.mc_mediumTempUnit; 
				clone.zetaPotentialAvg = this.zetaPotentialAvg; 
				clone.zetaPotentialApproxSymbol = this.zetaPotentialApproxSymbol; 
				clone.zetaPotentialUnit = this.zetaPotentialUnit; 
				clone.zetaPotentialUncertain = this.zetaPotentialUncertain; 
				clone.zetaPotentialLow = this.zetaPotentialLow; 
				clone.zetaPotentialHigh = this.zetaPotentialHigh; 
				clone.zetaPotentialMethod = this.zetaPotentialMethod; 
				clone.sizeDistribType = this.sizeDistribType; 
				clone.sizeDistribModality = this.sizeDistribModality; 
				clone.sizeDistribMethod = this.sizeDistribMethod; 
				clone.sizeDistribAvg = this.sizeDistribAvg; 
				clone.sizeDistribApproxSymbol = this.sizeDistribApproxSymbol; 
				clone.sizeDistribUnit = this.sizeDistribUnit; 
				clone.sizeDistribUncertain = this.sizeDistribUncertain; 
				clone.sizeDistribLow = this.sizeDistribLow; 
				clone.sizeDistribHigh = this.sizeDistribHigh; 
				clone.sizeDistribAvg2 = this.sizeDistribAvg2; 
				clone.sizeDistribApproxSymbol2 = this.sizeDistribApproxSymbol2; 
				clone.sizeDistribUnit2 = this.sizeDistribUnit2; 
				clone.sizeDistribUncertain2 = this.sizeDistribUncertain2; 
				clone.sizeDistribLow2 = this.sizeDistribLow2; 
				clone.sizeDistribHigh2 = this.sizeDistribHigh2;
				
				// Assay table fields
				clone.assayType = this.assayType; 
				clone.assayName = this.assayName; 
				clone.sampleName = this.sampleName; 
				clone.subjectSpecies = this.subjectSpecies; 
				clone.subjectID = this.subjectID;
				clone.cellType = this.cellType;
				clone.cellSource = this.cellSource; 
				clone.testMediumID = this.testMediumID; 
				clone.phAvg = this.phAvg;
				clone.phApproxSymbol = this.phApproxSymbol; 
				clone.phUncertain = this.phUncertain;
				clone.phLow = this.phLow;
				clone.phHigh = this.phHigh; 
				clone.mediumTemp = this.mediumTemp; 
				clone.mediumTempUnit = this.mediumTempUnit; 
				clone.timeValue = this.timeValue;
				clone.timeValueUnit = this.timeValueUnit; 
				clone.particleConcentration = this.particleConcentration; 
				clone.particleConcentrationUnit = this.particleConcentrationUnit; 
				clone.particleExposDuration = this.particleExposDuration;
				clone.particleExposDurationUnit = this.particleExposDurationUnit; 
				clone.uvaDose = this.uvaDose;
				clone.uvaDoseUnit = this.uvaDoseUnit; 
				clone.uvaExposDuration = this.uvaExposDuration; 
				clone.uvaExposDurationUnit = this.uvaExposDurationUnit; 
				clone.viabilityAvg = this.viabilityAvg;
				clone.viabilityApproxSymbol = this.viabilityApproxSymbol; 
				clone.viabilityUnit = this.viabilityUnit;
				clone.viabilityUncertain = this.viabilityUncertain; 
				clone.viabilityLow = this.viabilityLow;
				clone.viabilityHigh = this.viabilityHigh; 
				clone.viabilityMethod = this.viabilityMethod; 
				clone.lc50 = this.lc50; 
				clone.lc50ApproxSymbol = this.lc50ApproxSymbol; 
				clone.lc50Unit = this.lc50Unit;
				
				// Medium table fields
				clone.mediumID = this.mediumID;
				clone.mediumDescription = this.mediumDescription; 
				clone.serumAdditive = this.serumAdditive;
				clone.serumConcentration = this.serumConcentration; 
				clone.serumConcentrationUnit = this.serumConcentrationUnit; 
				clone.antibioticName = this.antibioticName;
				clone.antibioticConcentration = this.antibioticConcentration; 
				clone.antibioticConcentrationUnit = this.antibioticConcentrationUnit; 
				clone.domForm = this.domForm;
				clone.domConcentration = this.domConcentration; 
				clone.domUnit = this.domUnit;
				clone.salinityValue = this.salinityValue; 
				clone.salinityUnit = this.salinityUnit;

				clone.mc_mediumDescription = this.mc_mediumDescription; 
				clone.mc_serumAdditive = this.mc_serumAdditive;
				clone.mc_serumConcentration = this.mc_serumConcentration; 
				clone.mc_serumConcentrationUnit = this.mc_serumConcentrationUnit; 
				clone.mc_antibioticName = this.mc_antibioticName;
				clone.mc_antibioticConcentration = this.mc_antibioticConcentration; 
				clone.mc_antibioticConcentrationUnit = this.mc_antibioticConcentrationUnit; 
				clone.mc_domForm = this.mc_domForm;
				clone.mc_domConcentration = this.mc_domConcentration; 
				clone.mc_domUnit = this.mc_domUnit;
				clone.mc_salinityValue = this.mc_salinityValue;
				clone.mc_salinityUnit = this.mc_salinityUnit;
				*/
		
	}
	
	public final void assertPropertyEquals(final PropertyDescriptor p1, NanoMaterial n1, NanoMaterial n2) throws Exception {
		
		System.out.println(p1.getName()+", "+p1.getReadMethod().getName()+", "+p1.getPropertyType().getName());
		System.out.println(p1.getValue(p1.getName()));
		Object v1 = p1.getReadMethod().invoke(n1, (Object[])null);
		System.out.println(v1);
		
//		Assert.assertEquals(property.getDisplayName()+" not equal", v1, v2);
		
	}

	/*
	private void testProperty(@Nonnull final PropertyDescriptor property) throws IllegalAccessException,
	InstantiationException,	InvocationTargetException {
		final Object target = type.newInstance();
		final Object setValue = Defaults.defaultValue(property.getPropertyType());

		final Method getter = property.getReadMethod();
		final Method setter = property.getWriteMethod();

		setter.invoke(target, setValue);
		final Object getValue = getter.invoke(target);

		assertEquals(
				property.getDisplayName() + " getter / setter do not produce the same result.",
				setValue, getValue
				);
	}

	 */

}
