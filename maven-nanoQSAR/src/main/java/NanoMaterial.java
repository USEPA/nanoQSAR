

/**
 * This class is used to handle the data pulled out of the database.
 * @author Wmelende
 *
 */

import java.sql.Date;

public class NanoMaterial 
{	
		
	// Link table fields
	private int materialCharID; 
	private int measurementID;
	
	// Materialchar table fields
	private String ordMaterialID;
	private String dataSource; 
	private String lotNumber; 
	private String coreComp; 
	private String shellComp; 
	private String coatingComp; 
	private Double coatingAmount; 
	private String coatingAmountUnit; 
	private String functionalGroups; 
	private String functionalizationProtocol; 
	private Double purity; 
	private String purityApproxSymbol; 
	private String purityUnit; 
	private String purityMethod; 
	private String purityRefChemical; 
	private String contamUnit; 
	private Double contamAl; 
	private Double contamAs; 
	private Double contamBe; 
	private Double contamCa; 
	private Double contamCo; 
	private Double contamCr; 
	private Double contamFe; 
	private Double contamK; 
	private Double contamMg; 
	private Double contamNa; 
	private Double contamP; 
	private Double contamPb; 
	private Double contamSb; 
	private Double contamSe; 
	private Double contamSiO2; 
	private Double contamSn; 
	private Double contamTl; 
	private Double contamV; 
	private String contamMethod; 
	private String crystalStructure; 
	private String crystalStructureMethod; 
	private String synthesisMethod; 
	private Date synthesisDate; 
	private Double particleOuterDiamAvg; 
	private String particleOuterDiamApproxSymbol; 
	private String particleOuterDiamUnit; 
	private String particleOuterDiamUncertain; 
	private Double particleOuterDiamLow; 
	private Double particleOuterDiamHigh; 
	private String particleOuterDiamMethod; 
	private Double particleInnerDiamAvg; 
	private String particleInnerDiamApproxSymbol; 
	private String particleInnerDiamUnit; 
	private String particleInnerDiamUncertain; 
	private Double particleInnerDiamLow; 
	private Double particleInnerDiamHigh; 
	private String particleInnerDiamMethod; 
	private Double particleLengthAvg; 
	private String particleLengthApproxSymbol; 
	private String particleLengthUnit; 
	private String particleLengthUncertain; 
	private Double particleLengthLow; 
	private Double particleLengthHigh; 
	private String particleLengthMethod; 
	private Double particleThicknessAvg; 
	private String particleThicknessApproxSymbol; 
	private String particleThicknessUnit; 
	private String particleThicknessUncertain; 
	private Double particleThicknessLow; 
	private Double particleThicknessHigh; 
	private String particleThicknessMethod; 
	private String wallNumber; 
	private Double aspectRatio; 
	private String shape; 
	private Double surfaceAreaAvg; 
	private String surfaceAreaApproxSymbol; 
	private String surfaceAreaUnit; 
	private String surfaceAreaUncertain; 
	private Double surfaceAreaLow; 
	private Double surfaceAreaHigh; 
	private String surfaceAreaMethod; 
	private int mc_timeValue; 
	private String mc_timeValueUnit; 
	private Double mc_particleConcentration; 
	private String mc_particleConcentrationUnit; 
	private int dispersionMediumID; 
	private String solubility; 
	private Double mc_pHAvg; 
	private String mc_pHApproxSymbol; 
	private String mc_pHUncertain; 
	private Double mc_pHLow; 
	private Double mc_pHHigh; 
	private Double mc_mediumTemp; 
	private String mc_mediumTempUnit; 
	private Double zetaPotentialAvg; 
	private String zetaPotentialApproxSymbol; 
	private String zetaPotentialUnit; 
	private String zetaPotentialUncertain; 
	private Double zetaPotentialLow; 
	private Double zetaPotentialHigh; 
	private String zetaPotentialMethod; 
	private String sizeDistribType; 
	private String sizeDistribModality; 
	private String sizeDistribMethod; 
	private Double sizeDistribAvg; 
	private String sizeDistribApproxSymbol; 
	private String sizeDistribUnit; 
	private String sizeDistribUncertain; 
	private Double sizeDistribLow; 
	private Double sizeDistribHigh; 
	private Double sizeDistribAvg2; 
	private String sizeDistribApproxSymbol2; 
	private String sizeDistribUnit2; 
	private String sizeDistribUncertain2; 
	private Double sizeDistribLow2; 
	private Double sizeDistribHigh2;
	
	// Assay table fields
	private String assayType; 
	private String assayName; 
	private String sampleName; 
	private String subjectSpecies; 
	private String subjectID;
	private String cellType;
	private String cellSource; 
	private int testMediumID; 
	private Double phAvg;
	private String phApproxSymbol; 
	private String phUncertain;
	private Double phLow;
	private Double phHigh; 
	private Double mediumTemp; 
	private String mediumTempUnit; 
	private Double timeValue;
	private String timeValueUnit; 
	private Double particleConcentration; 
	private String particleConcentrationUnit; 
	private Double particleExposDuration;
	private String particleExposDurationUnit; 
	private Double uvaDose;
	private String uvaDoseUnit; 
	private Double uvaExposDuration; 
	private String uvaExposDurationUnit; 
	private Double viabilityAvg;
	private String viabilityApproxSymbol; 
	private String viabilityUnit;
	private String viabilityUncertain; 
	private Double viabilityLow;
	private Double viabilityHigh; 
	private String viabilityMethod; 
	private Double lc50; 
	private String lc50ApproxSymbol; 
	private String lc50Unit;
	
	// Medium table fields
	private int mediumID;
	private String mediumDescription; 
	private String serumAdditive;
	private Double serumConcentration; 
	private String serumConcentrationUnit; 
	private String antibioticName;
	private Double antibioticConcentration; 
	private String antibioticConcentrationUnit; 
	private String domForm;
	private Double domConcentration; 
	private String domUnit;
	private Double salinityValue; 
	private String salinityUnit;
	
	// Get and Set methods
	public int getMaterialCharID() {
		return materialCharID;
	}
	public void setMaterialCharID(int materialCharID) {
		this.materialCharID = materialCharID;
	}
	public int getMeasurementID() {
		return measurementID;
	}
	public void setMeasurementID(int measurementID) {
		this.measurementID = measurementID;
	}
	public String getDataSource() {
		return dataSource;
	}
	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	}
	public String getLotNumber() {
		return lotNumber;
	}
	public void setLotNumber(String lotNumber) {
		this.lotNumber = lotNumber;
	}
	public String getCoreComp() {
		return coreComp;
	}
	public void setCoreComp(String coreComp) {
		this.coreComp = coreComp;
	}
	public String getShellComp() {
		return shellComp;
	}
	public void setShellComp(String shellComp) {
		this.shellComp = shellComp;
	}
	public String getCoatingComp() {
		return coatingComp;
	}
	public void setCoatingComp(String coatingComp) {
		this.coatingComp = coatingComp;
	}
	public Double getCoatingAmount() {
		return coatingAmount;
	}
	public void setCoatingAmount(Double coatingAmount) {
		this.coatingAmount = coatingAmount;
	}
	public String getCoatingAmountUnit() {
		return coatingAmountUnit;
	}
	public void setCoatingAmountUnit(String coatingAmountUnit) {
		this.coatingAmountUnit = coatingAmountUnit;
	}
	public String getFunctionalGroups() {
		return functionalGroups;
	}
	public void setFunctionalGroups(String functionalGroups) {
		this.functionalGroups = functionalGroups;
	}
	public String getFunctionalizationProtocol() {
		return functionalizationProtocol;
	}
	public void setFunctionalizationProtocol(String functionalizationProtocol) {
		this.functionalizationProtocol = functionalizationProtocol;
	}
	public Double getPurity() {
		return purity;
	}
	public void setPurity(Double purity) {
		this.purity = purity;
	}
	public String getPurityApproxSymbol() {
		return purityApproxSymbol;
	}
	public void setPurityApproxSymbol(String purityApproxSymbol) {
		this.purityApproxSymbol = purityApproxSymbol;
	}
	public String getPurityUnit() {
		return purityUnit;
	}
	public void setPurityUnit(String purityUnit) {
		this.purityUnit = purityUnit;
	}
	public String getPurityMethod() {
		return purityMethod;
	}
	public void setPurityMethod(String purityMethod) {
		this.purityMethod = purityMethod;
	}
	public String getPurityRefChemical() {
		return purityRefChemical;
	}
	public void setPurityRefChemical(String purityRefChemical) {
		this.purityRefChemical = purityRefChemical;
	}
	public String getContamUnit() {
		return contamUnit;
	}
	public void setContamUnit(String contamUnit) {
		this.contamUnit = contamUnit;
	}
	public Double getContamAl() {
		return contamAl;
	}
	public void setContamAl(Double contamAl) {
		this.contamAl = contamAl;
	}
	public Double getContamAs() {
		return contamAs;
	}
	public void setContamAs(Double contamAs) {
		this.contamAs = contamAs;
	}
	public Double getContamBe() {
		return contamBe;
	}
	public void setContamBe(Double contamBe) {
		this.contamBe = contamBe;
	}
	public Double getContamCa() {
		return contamCa;
	}
	public void setContamCa(Double contamCa) {
		this.contamCa = contamCa;
	}
	public Double getContamCo() {
		return contamCo;
	}
	public void setContamCo(Double contamCo) {
		this.contamCo = contamCo;
	}
	public Double getContamCr() {
		return contamCr;
	}
	public void setContamCr(Double contamCr) {
		this.contamCr = contamCr;
	}
	public Double getContamFe() {
		return contamFe;
	}
	public void setContamFe(Double contamFe) {
		this.contamFe = contamFe;
	}
	public Double getContamK() {
		return contamK;
	}
	public void setContamK(Double contamK) {
		this.contamK = contamK;
	}
	public Double getContamMg() {
		return contamMg;
	}
	public void setContamMg(Double contamMg) {
		this.contamMg = contamMg;
	}
	public Double getContamNa() {
		return contamNa;
	}
	public void setContamNa(Double contamNa) {
		this.contamNa = contamNa;
	}
	public Double getContamP() {
		return contamP;
	}
	public void setContamP(Double contamP) {
		this.contamP = contamP;
	}
	public Double getContamPb() {
		return contamPb;
	}
	public void setContamPb(Double contamPb) {
		this.contamPb = contamPb;
	}
	public Double getContamSb() {
		return contamSb;
	}
	public void setContamSb(Double contamSb) {
		this.contamSb = contamSb;
	}
	public Double getContamSe() {
		return contamSe;
	}
	public void setContamSe(Double contamSe) {
		this.contamSe = contamSe;
	}
	public Double getContamSiO2() {
		return contamSiO2;
	}
	public void setContamSiO2(Double contamSiO2) {
		this.contamSiO2 = contamSiO2;
	}
	public Double getContamSn() {
		return contamSn;
	}
	public void setContamSn(Double contamSn) {
		this.contamSn = contamSn;
	}
	public Double getContamTl() {
		return contamTl;
	}
	public void setContamTl(Double contamTl) {
		this.contamTl = contamTl;
	}
	public Double getContamV() {
		return contamV;
	}
	public void setContamV(Double contamV) {
		this.contamV = contamV;
	}
	public String getContamMethod() {
		return contamMethod;
	}
	public void setContamMethod(String contamMethod) {
		this.contamMethod = contamMethod;
	}
	public String getCrystalStructure() {
		return crystalStructure;
	}
	public void setCrystalStructure(String crystalStructure) {
		this.crystalStructure = crystalStructure;
	}
	public String getCrystalStructureMethod() {
		return crystalStructureMethod;
	}
	public void setCrystalStructureMethod(String crystalStructureMethod) {
		this.crystalStructureMethod = crystalStructureMethod;
	}
	public String getSynthesisMethod() {
		return synthesisMethod;
	}
	public void setSynthesisMethod(String synthesisMethod) {
		this.synthesisMethod = synthesisMethod;
	}
	public Date getSynthesisDate() {
		return synthesisDate;
	}
	public void setSynthesisDate(Date synthesisDate) {
		this.synthesisDate = synthesisDate;
	}
	public Double getParticleOuterDiamAvg() {
		return particleOuterDiamAvg;
	}
	public void setParticleOuterDiamAvg(Double particleOuterDiamAvg) {
		this.particleOuterDiamAvg = particleOuterDiamAvg;
	}
	public String getParticleOuterDiamApproxSymbol() {
		return particleOuterDiamApproxSymbol;
	}
	public void setParticleOuterDiamApproxSymbol(
			String particleOuterDiamApproxSymbol) {
		this.particleOuterDiamApproxSymbol = particleOuterDiamApproxSymbol;
	}
	public String getParticleOuterDiamUnit() {
		return particleOuterDiamUnit;
	}
	public void setParticleOuterDiamUnit(String particleOuterDiamUnit) {
		this.particleOuterDiamUnit = particleOuterDiamUnit;
	}
	public String getParticleOuterDiamUncertain() {
		return particleOuterDiamUncertain;
	}
	public void setParticleOuterDiamUncertain(String particleOuterDiamUncertain) {
		this.particleOuterDiamUncertain = particleOuterDiamUncertain;
	}
	public Double getParticleOuterDiamLow() {
		return particleOuterDiamLow;
	}
	public void setParticleOuterDiamLow(Double particleOuterDiamLow) {
		this.particleOuterDiamLow = particleOuterDiamLow;
	}
	public Double getParticleOuterDiamHigh() {
		return particleOuterDiamHigh;
	}
	public void setParticleOuterDiamHigh(Double particleOuterDiamHigh) {
		this.particleOuterDiamHigh = particleOuterDiamHigh;
	}
	public String getParticleOuterDiamMethod() {
		return particleOuterDiamMethod;
	}
	public void setParticleOuterDiamMethod(String particleOuterDiamMethod) {
		this.particleOuterDiamMethod = particleOuterDiamMethod;
	}
	public Double getParticleInnerDiamAvg() {
		return particleInnerDiamAvg;
	}
	public void setParticleInnerDiamAvg(Double particleInnerDiamAvg) {
		this.particleInnerDiamAvg = particleInnerDiamAvg;
	}
	public String getParticleInnerDiamApproxSymbol() {
		return particleInnerDiamApproxSymbol;
	}
	public void setParticleInnerDiamApproxSymbol(
			String particleInnerDiamApproxSymbol) {
		this.particleInnerDiamApproxSymbol = particleInnerDiamApproxSymbol;
	}
	public String getParticleInnerDiamUnit() {
		return particleInnerDiamUnit;
	}
	public void setParticleInnerDiamUnit(String particleInnerDiamUnit) {
		this.particleInnerDiamUnit = particleInnerDiamUnit;
	}
	public String getParticleInnerDiamUncertain() {
		return particleInnerDiamUncertain;
	}
	public void setParticleInnerDiamUncertain(String particleInnerDiamUncertain) {
		this.particleInnerDiamUncertain = particleInnerDiamUncertain;
	}
	public Double getParticleInnerDiamLow() {
		return particleInnerDiamLow;
	}
	public void setParticleInnerDiamLow(Double particleInnerDiamLow) {
		this.particleInnerDiamLow = particleInnerDiamLow;
	}
	public Double getParticleInnerDiamHigh() {
		return particleInnerDiamHigh;
	}
	public void setParticleInnerDiamHigh(Double particleInnerDiamHigh) {
		this.particleInnerDiamHigh = particleInnerDiamHigh;
	}
	public String getParticleInnerDiamMethod() {
		return particleInnerDiamMethod;
	}
	public void setParticleInnerDiamMethod(String particleInnerDiamMethod) {
		this.particleInnerDiamMethod = particleInnerDiamMethod;
	}
	public Double getParticleLengthAvg() {
		return particleLengthAvg;
	}
	public void setParticleLengthAvg(Double particleLengthAvg) {
		this.particleLengthAvg = particleLengthAvg;
	}
	public String getParticleLengthApproxSymbol() {
		return particleLengthApproxSymbol;
	}
	public void setParticleLengthApproxSymbol(String particleLengthApproxSymbol) {
		this.particleLengthApproxSymbol = particleLengthApproxSymbol;
	}
	public String getParticleLengthUnit() {
		return particleLengthUnit;
	}
	public void setParticleLengthUnit(String particleLengthUnit) {
		this.particleLengthUnit = particleLengthUnit;
	}
	public String getParticleLengthUncertain() {
		return particleLengthUncertain;
	}
	public void setParticleLengthUncertain(String particleLengthUncertain) {
		this.particleLengthUncertain = particleLengthUncertain;
	}
	public Double getParticleLengthLow() {
		return particleLengthLow;
	}
	public void setParticleLengthLow(Double particleLengthLow) {
		this.particleLengthLow = particleLengthLow;
	}
	public Double getParticleLengthHigh() {
		return particleLengthHigh;
	}
	public void setParticleLengthHigh(Double particleLengthHigh) {
		this.particleLengthHigh = particleLengthHigh;
	}
	public String getParticleLengthMethod() {
		return particleLengthMethod;
	}
	public void setParticleLengthMethod(String particleLengthMethod) {
		this.particleLengthMethod = particleLengthMethod;
	}
	public Double getParticleThicknessAvg() {
		return particleThicknessAvg;
	}
	public void setParticleThicknessAvg(Double particleThicknessAvg) {
		this.particleThicknessAvg = particleThicknessAvg;
	}
	public String getParticleThicknessApproxSymbol() {
		return particleThicknessApproxSymbol;
	}
	public void setParticleThicknessApproxSymbol(
			String particleThicknessApproxSymbol) {
		this.particleThicknessApproxSymbol = particleThicknessApproxSymbol;
	}
	public String getParticleThicknessUnit() {
		return particleThicknessUnit;
	}
	public void setParticleThicknessUnit(String particleThicknessUnit) {
		this.particleThicknessUnit = particleThicknessUnit;
	}
	public String getParticleThicknessUncertain() {
		return particleThicknessUncertain;
	}
	public void setParticleThicknessUncertain(String particleThicknessUncertain) {
		this.particleThicknessUncertain = particleThicknessUncertain;
	}
	public Double getParticleThicknessLow() {
		return particleThicknessLow;
	}
	public void setParticleThicknessLow(Double particleThicknessLow) {
		this.particleThicknessLow = particleThicknessLow;
	}
	public Double getParticleThicknessHigh() {
		return particleThicknessHigh;
	}
	public void setParticleThicknessHigh(Double particleThicknessHigh) {
		this.particleThicknessHigh = particleThicknessHigh;
	}
	public String getParticleThicknessMethod() {
		return particleThicknessMethod;
	}
	public void setParticleThicknessMethod(String particleThicknessMethod) {
		this.particleThicknessMethod = particleThicknessMethod;
	}
	public String getWallNumber() {
		return wallNumber;
	}
	public void setWallNumber(String wallNumber) {
		this.wallNumber = wallNumber;
	}
	public Double getAspectRatio() {
		return aspectRatio;
	}
	public void setAspectRatio(Double aspectRatio) {
		this.aspectRatio = aspectRatio;
	}
	public String getShape() {
		return shape;
	}
	public void setShape(String shape) {
		this.shape = shape;
	}
	public Double getSurfaceAreaAvg() {
		return surfaceAreaAvg;
	}
	public void setSurfaceAreaAvg(Double surfaceAreaAvg) {
		this.surfaceAreaAvg = surfaceAreaAvg;
	}
	public String getSurfaceAreaApproxSymbol() {
		return surfaceAreaApproxSymbol;
	}
	public void setSurfaceAreaApproxSymbol(String surfaceAreaApproxSymbol) {
		this.surfaceAreaApproxSymbol = surfaceAreaApproxSymbol;
	}
	public String getSurfaceAreaUnit() {
		return surfaceAreaUnit;
	}
	public void setSurfaceAreaUnit(String surfaceAreaUnit) {
		this.surfaceAreaUnit = surfaceAreaUnit;
	}
	public String getSurfaceAreaUncertain() {
		return surfaceAreaUncertain;
	}
	public void setSurfaceAreaUncertain(String surfaceAreaUncertain) {
		this.surfaceAreaUncertain = surfaceAreaUncertain;
	}
	public Double getSurfaceAreaLow() {
		return surfaceAreaLow;
	}
	public void setSurfaceAreaLow(Double surfaceAreaLow) {
		this.surfaceAreaLow = surfaceAreaLow;
	}
	public Double getSurfaceAreaHigh() {
		return surfaceAreaHigh;
	}
	public void setSurfaceAreaHigh(Double surfaceAreaHigh) {
		this.surfaceAreaHigh = surfaceAreaHigh;
	}
	public String getSurfaceAreaMethod() {
		return surfaceAreaMethod;
	}
	public void setSurfaceAreaMethod(String surfaceAreaMethod) {
		this.surfaceAreaMethod = surfaceAreaMethod;
	}
	public int getMc_timeValue() {
		return mc_timeValue;
	}
	public void setMc_timeValue(int mc_timeValue) {
		this.mc_timeValue = mc_timeValue;
	}
	public String getMc_timeValueUnit() {
		return mc_timeValueUnit;
	}
	public void setMc_timeValueUnit(String mc_timeValueUnit) {
		this.mc_timeValueUnit = mc_timeValueUnit;
	}
	public Double getMc_particleConcentration() {
		return mc_particleConcentration;
	}
	public void setMc_particleConcentration(Double mc_particleConcentration) {
		this.mc_particleConcentration = mc_particleConcentration;
	}
	public String getMc_particleConcentrationUnit() {
		return mc_particleConcentrationUnit;
	}
	public void setMc_particleConcentrationUnit(String mc_particleConcentrationUnit) {
		this.mc_particleConcentrationUnit = mc_particleConcentrationUnit;
	}
	public int getDispersionMediumID() {
		return dispersionMediumID;
	}
	public void setDispersionMediumID(int dispersionMediumID) {
		this.dispersionMediumID = dispersionMediumID;
	}
	public String getSolubility() {
		return solubility;
	}
	public void setSolubility(String solubility) {
		this.solubility = solubility;
	}
	public Double getMc_pHAvg() {
		return mc_pHAvg;
	}
	public void setMc_pHAvg(Double mc_pHAvg) {
		this.mc_pHAvg = mc_pHAvg;
	}
	public String getMc_pHApproxSymbol() {
		return mc_pHApproxSymbol;
	}
	public void setMc_pHApproxSymbol(String mc_pHApproxSymbol) {
		this.mc_pHApproxSymbol = mc_pHApproxSymbol;
	}
	public String getMc_pHUncertain() {
		return mc_pHUncertain;
	}
	public void setMc_pHUncertain(String mc_pHUncertain) {
		this.mc_pHUncertain = mc_pHUncertain;
	}
	public Double getMc_pHLow() {
		return mc_pHLow;
	}
	public void setMc_pHLow(Double mc_pHLow) {
		this.mc_pHLow = mc_pHLow;
	}
	public Double getMc_pHHigh() {
		return mc_pHHigh;
	}
	public void setMc_pHHigh(Double mc_pHHigh) {
		this.mc_pHHigh = mc_pHHigh;
	}
	public Double getMc_mediumTemp() {
		return mc_mediumTemp;
	}
	public void setMc_mediumTemp(Double mc_mediumTemp) {
		this.mc_mediumTemp = mc_mediumTemp;
	}
	public String getMc_mediumTempUnit() {
		return mc_mediumTempUnit;
	}
	public void setMc_mediumTempUnit(String mc_mediumTempUnit) {
		this.mc_mediumTempUnit = mc_mediumTempUnit;
	}
	public Double getZetaPotentialAvg() {
		return zetaPotentialAvg;
	}
	public void setZetaPotentialAvg(Double zetaPotentialAvg) {
		this.zetaPotentialAvg = zetaPotentialAvg;
	}
	public String getZetaPotentialApproxSymbol() {
		return zetaPotentialApproxSymbol;
	}
	public void setZetaPotentialApproxSymbol(String zetaPotentialApproxSymbol) {
		this.zetaPotentialApproxSymbol = zetaPotentialApproxSymbol;
	}
	public String getZetaPotentialUnit() {
		return zetaPotentialUnit;
	}
	public void setZetaPotentialUnit(String zetaPotentialUnit) {
		this.zetaPotentialUnit = zetaPotentialUnit;
	}
	public String getZetaPotentialUncertain() {
		return zetaPotentialUncertain;
	}
	public void setZetaPotentialUncertain(String zetaPotentialUncertain) {
		this.zetaPotentialUncertain = zetaPotentialUncertain;
	}
	public Double getZetaPotentialLow() {
		return zetaPotentialLow;
	}
	public void setZetaPotentialLow(Double zetaPotentialLow) {
		this.zetaPotentialLow = zetaPotentialLow;
	}
	public Double getZetaPotentialHigh() {
		return zetaPotentialHigh;
	}
	public void setZetaPotentialHigh(Double zetaPotentialHigh) {
		this.zetaPotentialHigh = zetaPotentialHigh;
	}
	public String getZetaPotentialMethod() {
		return zetaPotentialMethod;
	}
	public void setZetaPotentialMethod(String zetaPotentialMethod) {
		this.zetaPotentialMethod = zetaPotentialMethod;
	}
	public String getSizeDistribType() {
		return sizeDistribType;
	}
	public void setSizeDistribType(String sizeDistribType) {
		this.sizeDistribType = sizeDistribType;
	}
	public String getSizeDistribModality() {
		return sizeDistribModality;
	}
	public void setSizeDistribModality(String sizeDistribModality) {
		this.sizeDistribModality = sizeDistribModality;
	}
	public String getSizeDistribMethod() {
		return sizeDistribMethod;
	}
	public void setSizeDistribMethod(String sizeDistribMethod) {
		this.sizeDistribMethod = sizeDistribMethod;
	}
	public Double getSizeDistribAvg() {
		return sizeDistribAvg;
	}
	public void setSizeDistribAvg(Double sizeDistribAvg) {
		this.sizeDistribAvg = sizeDistribAvg;
	}
	public String getSizeDistribApproxSymbol() {
		return sizeDistribApproxSymbol;
	}
	public void setSizeDistribApproxSymbol(String sizeDistribApproxSymbol) {
		this.sizeDistribApproxSymbol = sizeDistribApproxSymbol;
	}
	public String getSizeDistribUnit() {
		return sizeDistribUnit;
	}
	public void setSizeDistribUnit(String sizeDistribUnit) {
		this.sizeDistribUnit = sizeDistribUnit;
	}
	public String getSizeDistribUncertain() {
		return sizeDistribUncertain;
	}
	public void setSizeDistribUncertain(String sizeDistribUncertain) {
		this.sizeDistribUncertain = sizeDistribUncertain;
	}
	public Double getSizeDistribLow() {
		return sizeDistribLow;
	}
	public void setSizeDistribLow(Double sizeDistribLow) {
		this.sizeDistribLow = sizeDistribLow;
	}
	public Double getSizeDistribHigh() {
		return sizeDistribHigh;
	}
	public void setSizeDistribHigh(Double sizeDistribHigh) {
		this.sizeDistribHigh = sizeDistribHigh;
	}
	public Double getSizeDistribAvg2() {
		return sizeDistribAvg2;
	}
	public void setSizeDistribAvg2(Double sizeDistribAvg2) {
		this.sizeDistribAvg2 = sizeDistribAvg2;
	}
	public String getSizeDistribApproxSymbol2() {
		return sizeDistribApproxSymbol2;
	}
	public void setSizeDistribApproxSymbol2(String sizeDistribApproxSymbol2) {
		this.sizeDistribApproxSymbol2 = sizeDistribApproxSymbol2;
	}
	public String getSizeDistribUnit2() {
		return sizeDistribUnit2;
	}
	public void setSizeDistribUnit2(String sizeDistribUnit2) {
		this.sizeDistribUnit2 = sizeDistribUnit2;
	}
	public String getSizeDistribUncertain2() {
		return sizeDistribUncertain2;
	}
	public void setSizeDistribUncertain2(String sizeDistribUncertain2) {
		this.sizeDistribUncertain2 = sizeDistribUncertain2;
	}
	public Double getSizeDistribLow2() {
		return sizeDistribLow2;
	}
	public void setSizeDistribLow2(Double sizeDistribLow2) {
		this.sizeDistribLow2 = sizeDistribLow2;
	}
	public Double getSizeDistribHigh2() {
		return sizeDistribHigh2;
	}
	public void setSizeDistribHigh2(Double sizeDistribHigh2) {
		this.sizeDistribHigh2 = sizeDistribHigh2;
	}
	public String getAssayType() {
		return assayType;
	}
	public void setAssayType(String assayType) {
		this.assayType = assayType;
	}
	public String getSubjectSpecies() {
		return subjectSpecies;
	}
	public void setSubjectSpecies(String subjectSpecies) {
		this.subjectSpecies = subjectSpecies;
	}
	public String getSubjectID() {
		return subjectID;
	}
	public void setSubjectID(String subjectID) {
		this.subjectID = subjectID;
	}
	public String getCellSource() {
		return cellSource;
	}
	public void setCellSource(String cellSource) {
		this.cellSource = cellSource;
	}
	public int getTestMediumID() {
		return testMediumID;
	}
	public void setTestMediumID(int testMediumID) {
		this.testMediumID = testMediumID;
	}
	public Double getPhAvg() {
		return phAvg;
	}
	public void setPhAvg(Double pHAvg) {
		this.phAvg = pHAvg;
	}
	public String getPhApproxSymbol() {
		return phApproxSymbol;
	}
	public void setPhApproxSymbol(String pHApproxSymbol) {
		this.phApproxSymbol = pHApproxSymbol;
	}
	public String getPhUncertain() {
		return phUncertain;
	}
	public void setPhUncertain(String phUncertain) {
		this.phUncertain = phUncertain;
	}	
	public Double getPhLow() {
		return phLow;
	}
	public void setPhLow(Double pHLow) {
		this.phLow = pHLow;
	}
	public Double getPhHigh() {
		return phHigh;
	}
	public void setPhHigh(Double phHigh) {
		this.phHigh = phHigh;
	}
	public Double getMediumTemp() {
		return mediumTemp;
	}
	public void setMediumTemp(Double mediumTemp) {
		this.mediumTemp = mediumTemp;
	}
	public String getMediumTempUnit() {
		return mediumTempUnit;
	}
	public void setMediumTempUnit(String mediumTempUnit) {
		this.mediumTempUnit = mediumTempUnit;
	}
	public Double getTimeValue() {
		return timeValue;
	}
	public void setTimeValue(Double timeValue) {
		this.timeValue = timeValue;
	}
	public String getTimeValueUnit() {
		return timeValueUnit;
	}
	public void setTimeValueUnit(String timeValueUnit) {
		this.timeValueUnit = timeValueUnit;
	}
	public String getParticleConcentrationUnit() {
		return particleConcentrationUnit;
	}
	public void setParticleConcentrationUnit(String particleConcentrationUnit) {
		this.particleConcentrationUnit = particleConcentrationUnit;
	}
	public Double getParticleExposDuration() {
		return particleExposDuration;
	}
	public void setParticleExposDuration(Double particleExposDuration) {
		this.particleExposDuration = particleExposDuration;
	}
	public String getParticleExposDurationUnit() {
		return particleExposDurationUnit;
	}
	public void setParticleExposDurationUnit(String particleExposDurationUnit) {
		this.particleExposDurationUnit = particleExposDurationUnit;
	}
	public Double getUvaDose() {
		return uvaDose;
	}
	public void setUvaDose(Double uvaDose) {
		this.uvaDose = uvaDose;
	}
	public String getUvaDoseUnit() {
		return uvaDoseUnit;
	}
	public void setUvaDoseUnit(String uvaDoseUnit) {
		this.uvaDoseUnit = uvaDoseUnit;
	}
	public Double getUvaExposDuration() {
		return uvaExposDuration;
	}
	public void setUvaExposDuration(Double uvaExposDuration) {
		this.uvaExposDuration = uvaExposDuration;
	}
	public String getUvaExposDurationUnit() {
		return uvaExposDurationUnit;
	}
	public void setUvaExposDurationUnit(String uvaExposDurationUnit) {
		this.uvaExposDurationUnit = uvaExposDurationUnit;
	}
	public Double getViabilityAvg() {
		return viabilityAvg;
	}
	public void setViabilityAvg(Double viabilityAvg) {
		this.viabilityAvg = viabilityAvg;
	}
	public String getViabilityApproxSymbol() {
		return viabilityApproxSymbol;
	}
	public void setViabilityApproxSymbol(String viabilityApproxSymbol) {
		this.viabilityApproxSymbol = viabilityApproxSymbol;
	}
	public String getViabilityUnit() {
		return viabilityUnit;
	}
	public void setViabilityUnit(String viabilityUnit) {
		this.viabilityUnit = viabilityUnit;
	}
	public String getViabilityUncertain() {
		return viabilityUncertain;
	}
	public void setViabilityUncertain(String viabilityUncertain) {
		this.viabilityUncertain = viabilityUncertain;
	}
	public Double getViabilityLow() {
		return viabilityLow;
	}
	public void setViabilityLow(Double viabilityLow) {
		this.viabilityLow = viabilityLow;
	}
	public Double getViabilityHigh() {
		return viabilityHigh;
	}
	public void setViabilityHigh(Double viabilityHigh) {
		this.viabilityHigh = viabilityHigh;
	}
	public String getViabilityMethod() {
		return viabilityMethod;
	}
	public void setViabilityMethod(String viabilityMethod) {
		this.viabilityMethod = viabilityMethod;
	}
	public String getLc50ApproxSymbol() {
		return lc50ApproxSymbol;
	}
	public void setLc50ApproxSymbol(String lc50ApproxSymbol) {
		this.lc50ApproxSymbol = lc50ApproxSymbol;
	}
	public String getLc50Unit() {
		return lc50Unit;
	}
	public void setLc50Unit(String lc50Unit) {
		this.lc50Unit = lc50Unit;
	}
	
	public int getMediumID() {
		return mediumID;
	}
	public void setMediumID(int mediumID) {
		this.mediumID = mediumID;
	}
	public String getMediumDescription() {
		return mediumDescription;
	}
	public void setMediumDescription(String mediumDescription) {
		this.mediumDescription = mediumDescription;
	}
	public String getSerumAdditive() {
		return serumAdditive;
	}
	public void setSerumAdditive(String serumAdditive) {
		this.serumAdditive = serumAdditive;
	}
	public Double getSerumConcentration() {
		return serumConcentration;
	}
	public void setSerumConcentration(Double serumConcentration) {
		this.serumConcentration = serumConcentration;
	}
	public String getSerumConcentrationUnit() {
		return serumConcentrationUnit;
	}
	public void setSerumConcentrationUnit(String serumConcentrationUnit) {
		this.serumConcentrationUnit = serumConcentrationUnit;
	}
	public String getAntibioticName() {
		return antibioticName;
	}
	public void setAntibioticName(String antibioticName) {
		this.antibioticName = antibioticName;
	}
	public Double getAntibioticConcentration() {
		return antibioticConcentration;
	}
	public void setAntibioticConcentration(Double antibioticConcentration) {
		this.antibioticConcentration = antibioticConcentration;
	}
	public String getAntibioticConcentrationUnit() {
		return antibioticConcentrationUnit;
	}
	public void setAntibioticConcentrationUnit(String antibioticConcentrationUnit) {
		this.antibioticConcentrationUnit = antibioticConcentrationUnit;
	}
	public String getDomForm() {
		return domForm;
	}
	public void setDomForm(String domForm) {
		this.domForm = domForm;
	}
	public Double getDomConcentration() {
		return domConcentration;
	}
	public void setDomConcentration(Double domConcentration) {
		this.domConcentration = domConcentration;
	}
	public String getDomUnit() {
		return domUnit;
	}
	public void setDomUnit(String domUnit) {
		this.domUnit = domUnit;
	}
	public Double getSalinityValue() {
		return salinityValue;
	}
	public void setSalinityValue(Double salinityValue) {
		this.salinityValue = salinityValue;
	}
	public String getSalinityUnit() {
		return salinityUnit;
	}
	public void setSalinityUnit(String salinityUnit) {
		this.salinityUnit = salinityUnit;
	}
	public void setParticleConcentration(Double particleConcentration) {
		this.particleConcentration = particleConcentration;
	}
	public String getOrdMaterialID() {
		return ordMaterialID;
	}
	public void setOrdMaterialID(String ordMaterialID) {
		this.ordMaterialID = ordMaterialID;
	}
	
	public String getAssayName() {
		return assayName;
	}
	public void setAssayName(String assayName) {
		this.assayName = assayName;
	}
	public String getSampleName() {
		return sampleName;
	}
	public void setSampleName(String sampleName) {
		this.sampleName = sampleName;
	}
	public String getCellType() {
		return cellType;
	}
	public void setCellType(String cellType) {
		this.cellType = cellType;
	}
	public double getParticleConcentration() {
		return particleConcentration;
	}
	public void setParticleConcentration(double particleConcentration) {
		this.particleConcentration = particleConcentration;
	}
	
	public Double getLc50() {
		return lc50;
	}
	public void setLc50(Double lc50) {
		this.lc50 = lc50;
	}
	
	
	/**
	 * This method store the fields into an array of strings and returns the array.
	 * The array of strings is used only when writing data to a CSV file.
	 * @author Wilson Melendez
	 * @param None.
	 * @return String[]
	 */
	public String[] toStringArray()
	{
		String[] strNano = new String[162];
		int i = 0;
		
		// Assay table
		strNano[i] = String.valueOf(getMeasurementID());
		i++;
		strNano[i] = String.valueOf(getAssayType());
		i++;
		strNano[i] = String.valueOf(getAssayName());
		i++;
		strNano[i] = String.valueOf(getSampleName());
		i++;
		strNano[i] = String.valueOf(getSubjectSpecies());
		i++;
		strNano[i] = String.valueOf(getSubjectID());
		i++;
		strNano[i] = String.valueOf(getCellType());
		i++;
		strNano[i] = String.valueOf(getCellSource());
		i++;
		strNano[i] = String.valueOf(getTestMediumID());
		i++;
		strNano[i] = String.valueOf(getPhAvg());
		i++;
		strNano[i] = String.valueOf(getPhApproxSymbol());
		i++;
	    strNano[i] = String.valueOf(getPhUncertain());
	    i++;
		strNano[i] = String.valueOf(getPhLow());
		i++;
		strNano[i] = String.valueOf(getPhHigh());
		i++;
		strNano[i] = String.valueOf(getMediumTemp());
		i++;
		strNano[i] = String.valueOf(getMediumTempUnit());
		i++;
		strNano[i] = String.valueOf(getTimeValue());
		i++;
		strNano[i] = String.valueOf(getTimeValueUnit());
		i++;
		strNano[i] = String.valueOf(getParticleConcentration());
		i++;
		strNano[i] = String.valueOf(getParticleConcentrationUnit());
		i++;
		strNano[i] = String.valueOf(getParticleExposDuration());
		i++;
		strNano[i] = String.valueOf(getParticleExposDurationUnit());
		i++;
		strNano[i] = String.valueOf(getUvaDose());
		i++;
		strNano[i] = String.valueOf(getUvaDoseUnit());
		i++;
		strNano[i] = String.valueOf(getUvaExposDuration());
		i++;
		strNano[i] = String.valueOf(getUvaExposDurationUnit());
		i++;
		strNano[i] = String.valueOf(getViabilityAvg());
		i++;
		strNano[i] = String.valueOf(getViabilityApproxSymbol());
		i++;
		strNano[i] = String.valueOf(getViabilityUnit());
		i++;
		strNano[i] = String.valueOf(getViabilityUncertain());
		i++;
		strNano[i] = String.valueOf(getViabilityLow());
		i++;
		strNano[i] = String.valueOf(getViabilityHigh());
		i++;
		strNano[i] = String.valueOf(getViabilityMethod());
		i++;
		strNano[i] = String.valueOf(getLc50());
		i++;
		strNano[i] = String.valueOf(getLc50ApproxSymbol());
		i++;
		strNano[i] = String.valueOf(getLc50Unit());
		i++;
		
		// MaterialChar Table		
		strNano[i] = String.valueOf(getMaterialCharID());
		i++;
		strNano[i] = String.valueOf(getOrdMaterialID());
		i++;
		strNano[i] = String.valueOf(getDataSource());
		i++;
		strNano[i] = String.valueOf(getLotNumber());
		i++;
		strNano[i] = String.valueOf(getCoreComp());
		i++;
		strNano[i] = String.valueOf(getShellComp());
		i++;
		strNano[i] = String.valueOf(getCoatingComp());
		i++;
		strNano[i] = String.valueOf(getCoatingAmount());
		i++;
		strNano[i] = String.valueOf(getCoatingAmountUnit());
		i++;
	    strNano[i] = String.valueOf(getFunctionalGroups());
	    i++;
		strNano[i] = String.valueOf(getFunctionalizationProtocol());
		i++;
		strNano[i] = String.valueOf(getPurity());
		i++;
		strNano[i] = String.valueOf(getPurityApproxSymbol());
		i++;
		strNano[i] = String.valueOf(getPurityUnit());
		i++;
		strNano[i] = String.valueOf(getPurityMethod());
		i++;
		strNano[i] = String.valueOf(getPurityRefChemical());
		i++;
		strNano[i] = String.valueOf(getContamUnit());
		i++;
		strNano[i] = String.valueOf(getContamAl());
		i++;
		strNano[i] = String.valueOf(getContamAs());
		i++;
		strNano[i] = String.valueOf(getContamBe());
		i++;
		strNano[i] = String.valueOf(getContamCa());
		i++;
		strNano[i] = String.valueOf(getContamCo());
		i++;
		strNano[i] = String.valueOf(getContamCr());
		i++;
		strNano[i] = String.valueOf(getContamFe());
		i++;
		strNano[i] = String.valueOf(getContamK());
		i++;
		strNano[i] = String.valueOf(getContamMg());
		i++;
		strNano[i] = String.valueOf(getContamNa());
		i++;
		strNano[i] = String.valueOf(getContamP());
		i++;
		strNano[i] = String.valueOf(getContamPb());
		i++;
		strNano[i] = String.valueOf(getContamSb());
		i++;
		strNano[i] = String.valueOf(getContamSe());
		i++;
		strNano[i] = String.valueOf(getContamSiO2());
		i++;
		strNano[i] = String.valueOf(getContamSn());
		i++;
		strNano[i] = String.valueOf(getContamTl());
		i++;
		strNano[i] = String.valueOf(getContamV());
		i++;
	    strNano[i] = String.valueOf(getContamMethod());
	    i++;
		strNano[i] = String.valueOf(getCrystalStructure());
		i++;
		strNano[i] = String.valueOf(getCrystalStructureMethod());
		i++;
		strNano[i] = String.valueOf(getSynthesisMethod());
		i++;
		strNano[i] = String.valueOf(getSynthesisDate());
		i++;
		strNano[i] = String.valueOf(getParticleOuterDiamAvg());
		i++;
		strNano[i] = String.valueOf(getParticleOuterDiamApproxSymbol());
		i++;
		strNano[i] = String.valueOf(getParticleOuterDiamUnit());
		i++;
		strNano[i] = String.valueOf(getParticleOuterDiamUncertain());
		i++;
		strNano[i] = String.valueOf(getParticleOuterDiamLow());
		i++;
		strNano[i] = String.valueOf(getParticleOuterDiamHigh());
		i++;
		strNano[i] = String.valueOf(getParticleOuterDiamMethod());
		i++;
		strNano[i] = String.valueOf(getParticleInnerDiamAvg());
		i++;
		strNano[i] = String.valueOf(getParticleInnerDiamApproxSymbol());
		i++;
		strNano[i] = String.valueOf(getParticleInnerDiamUnit());
		i++;
		strNano[i] = String.valueOf(getParticleInnerDiamUncertain());
		i++;
		strNano[i] = String.valueOf(getParticleInnerDiamLow());
		i++;
		strNano[i] = String.valueOf(getParticleInnerDiamHigh());
		i++;
		strNano[i] = String.valueOf(getParticleInnerDiamMethod());
		i++;
		strNano[i] = String.valueOf(getParticleLengthAvg());
		i++;
		strNano[i] = String.valueOf(getParticleLengthApproxSymbol());
		i++;
		strNano[i] = String.valueOf(getParticleLengthUnit());
		i++;
		strNano[i] = String.valueOf(getParticleLengthUncertain());
		i++;
		strNano[i] = String.valueOf(getParticleLengthLow());
		i++;
		strNano[i] = String.valueOf(getParticleLengthHigh());
		i++;
	    strNano[i] = String.valueOf(getParticleLengthMethod());
	    i++;
		strNano[i] = String.valueOf(getParticleThicknessAvg());
		i++;
		strNano[i] = String.valueOf(getParticleThicknessApproxSymbol());
		i++;
		strNano[i] = String.valueOf(getParticleThicknessUnit());
		i++;
		strNano[i] = String.valueOf(getParticleThicknessUncertain());
		i++;
		strNano[i] = String.valueOf(getParticleThicknessLow());
		i++;
		strNano[i] = String.valueOf(getParticleThicknessHigh());
		i++;
		strNano[i] = String.valueOf(getParticleThicknessMethod());
		i++;
		strNano[i] = String.valueOf(getWallNumber());
		i++;
		strNano[i] = String.valueOf(getAspectRatio());
		i++;
		strNano[i] = String.valueOf(getShape());
		i++;
		strNano[i] = String.valueOf(getSurfaceAreaAvg());
		i++;
		strNano[i] = String.valueOf(getSurfaceAreaApproxSymbol());
		i++;
		strNano[i] = String.valueOf(getSurfaceAreaUnit());
		i++;
		strNano[i] = String.valueOf(getSurfaceAreaUncertain());
		i++;
		strNano[i] = String.valueOf(getSurfaceAreaLow());
		i++;
		strNano[i] = String.valueOf(getSurfaceAreaHigh());
		i++;
		strNano[i] = String.valueOf(getSurfaceAreaMethod());
		i++;
		strNano[i] = String.valueOf(getMc_timeValue());
		i++;
		strNano[i] = String.valueOf(getMc_timeValueUnit());
		i++;
		strNano[i] = String.valueOf(getMc_particleConcentration());
		i++;
		strNano[i] = String.valueOf(getMc_particleConcentrationUnit());
		i++;
		strNano[i] = String.valueOf(getDispersionMediumID());
		i++;
		strNano[i] = String.valueOf(getSolubility());
		i++;
		strNano[i] = String.valueOf(getMc_pHAvg());
		i++;
		strNano[i] = String.valueOf(getMc_pHApproxSymbol());
		i++;
	    strNano[i] = String.valueOf(getMc_pHUncertain());
	    i++;
		strNano[i] = String.valueOf(getMc_pHLow());
		i++;
		strNano[i] = String.valueOf(getMc_pHHigh());
		i++;
		strNano[i] = String.valueOf(getMc_mediumTemp());
		i++;
		strNano[i] = String.valueOf(getMc_mediumTempUnit());
		i++;
		strNano[i] = String.valueOf(getZetaPotentialAvg());
		i++;
		strNano[i] = String.valueOf(getZetaPotentialApproxSymbol());
		i++;
		strNano[i] = String.valueOf(getZetaPotentialUnit());
		i++;
		strNano[i] = String.valueOf(getZetaPotentialUncertain());
		i++;
		strNano[i] = String.valueOf(getZetaPotentialLow());
		i++;
		strNano[i] = String.valueOf(getZetaPotentialHigh());
		i++;
		strNano[i] = String.valueOf(getZetaPotentialMethod());
		i++;
		strNano[i] = String.valueOf(getSizeDistribType());
		i++;
		strNano[i] = String.valueOf(getSizeDistribModality());
		i++;
		strNano[i] = String.valueOf(getSizeDistribMethod());
		i++;
		strNano[i] = String.valueOf(getSizeDistribAvg());
		i++;
		strNano[i] = String.valueOf(getSizeDistribApproxSymbol());
		i++;
		strNano[i] = String.valueOf(getSizeDistribUnit());
		i++;
		strNano[i] = String.valueOf(getSizeDistribUncertain());
		i++;
		strNano[i] = String.valueOf(getSizeDistribLow());
		i++;
		strNano[i] = String.valueOf(getSizeDistribHigh());
		i++;
		strNano[i] = String.valueOf(getSizeDistribAvg2());
		i++;
		strNano[i] = String.valueOf(getSizeDistribApproxSymbol2());
		i++;
		strNano[i] = String.valueOf(getSizeDistribUnit2());
		i++;
		strNano[i] = String.valueOf(getSizeDistribUncertain2());
		i++;
	    strNano[i] = String.valueOf(getSizeDistribLow2());
	    i++;
		strNano[i] = String.valueOf(getSizeDistribHigh2());
		i++;
		
		// Medium Table
		strNano[i] = String.valueOf(getMediumID());
		i++;
		strNano[i] = String.valueOf(getMediumDescription());
		i++;
		strNano[i] = String.valueOf(getSerumAdditive());
		i++;
		strNano[i] = String.valueOf(getSerumConcentration());
		i++;
		strNano[i] = String.valueOf(getSerumConcentrationUnit());
		i++;
		strNano[i] = String.valueOf(getAntibioticName());
		i++;
		strNano[i] = String.valueOf(getAntibioticConcentration());
		i++;
		strNano[i] = String.valueOf(getAntibioticConcentrationUnit());
		i++;
		strNano[i] = String.valueOf(getDomForm());
		i++;
		strNano[i] = String.valueOf(getDomConcentration());
		i++;
		strNano[i] = String.valueOf(getDomUnit());
		i++;
		strNano[i] = String.valueOf(getSalinityValue());
		i++;
		strNano[i] = String.valueOf(getSalinityUnit());
		
		
		return strNano;
	}
	
	/**
	 * This method returns an array of strings containing the header for the CSV file.
	 * @author Wilson Melendez
	 * @param None.
	 * @return String[]
	 */
	public static String[] getHeaderFile()
	{
		String[] headerFile = {
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
				               "DOMUnit", "SalinityValue", "SalinityUnit",				               
				                };
		return headerFile;
	}
	
}
