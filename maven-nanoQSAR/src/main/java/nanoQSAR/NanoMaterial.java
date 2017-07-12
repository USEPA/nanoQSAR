package nanoQSAR;

import java.io.Serializable;
import java.lang.reflect.Field;

/**
 * This class is used to handle the data pulled out of the database.
 * @author Wmelende
 *
 */

import java.util.ArrayList;

public class NanoMaterial extends Object implements Serializable, Cloneable {
	
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
	private Integer synthesisDate; 
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
	private Integer mc_timeValue; 
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

	private String mc_mediumDescription; 
	private String mc_serumAdditive;
	private Double mc_serumConcentration; 
	private String mc_serumConcentrationUnit; 
	private String mc_antibioticName;
	private Double mc_antibioticConcentration; 
	private String mc_antibioticConcentrationUnit; 
	private String mc_domForm;
	private Double mc_domConcentration; 
	private String mc_domUnit;
	private Double mc_salinityValue; 
	private String mc_salinityUnit;

	public NanoMaterial() throws Exception {
		super();
	}
	
	public NanoMaterial(int[] fieldIndex, String[] values) throws Exception {
		super();
	    
		Field[] fields = NanoMaterial.class.getDeclaredFields();
		
		/* execute set() on each property of NanoMaterial with fieldIndex and values taken from cvs file */
	    for (int i=0; i<values.length; i++) {

	    	String value = values[i];
	    	
	    	Field field = fields[fieldIndex[i]];
	    	if (field.getType()==String.class) {
	    		field.set(this, value);
	    	} else if (field.getType()==Double.class) {
	    		if (value.matches("null")) field.set(this, null);
	    		else field.set(this, Double.valueOf(value));
	    	} else if (field.getType()==int.class) {
	    		if (value!=null) field.setInt(this, Integer.parseInt(value));
	    	} else if (field.getType()==Integer.class) {
	    		if (value.matches("null")) field.set(this, null);
	    		else field.set(this, Integer.valueOf(value));
	    	} else {
	    		throw new Error("Invalid NanoMaterial field class");
	    	}
	    		
	    }
	    
	}
	
	public static int[] getFieldIndex(String[] columnNames) throws Exception {
		
		// all columnNames of the CSV header are fields of this class, but in different order */
		Field[] fields = NanoMaterial.class.getDeclaredFields();
	    int[] fieldIndex = new int[columnNames.length];
	    for (int i=0; i<columnNames.length; i++) {
	    	boolean notFound = true;
	    	for (int j=0; j<fields.length; j++) {
	    		if (fields[j].getName().equalsIgnoreCase(columnNames[i])) {
	    			fieldIndex[i] = j;
	    			notFound = false;
	    			break;
	    		}
	    	}
	    	if (notFound) throw new Error("NanoMatrial field associated with "+columnNames[i]+" not found");
	    }
	    
	    return fieldIndex;
	}
	

	
	/**
	 * This method store the fields into an array of strings and returns the array.
	 * The array of strings is used only when writing data to a CSV file.
	 * @author Wilson Melendez
	 * @param None.
	 * @return String[]
	 */
	public String[] storeDataAsStringArray()
	{			
		ArrayList<String> list = new ArrayList<String>();
		
		// MaterialChar Table		
		list.add(String.valueOf(getMaterialCharID()));		
		list.add(String.valueOf(getOrdMaterialID()));		
		list.add(String.valueOf(getDataSource()));
		list.add(String.valueOf(getLotNumber()));
		list.add(String.valueOf(getCoreComp()));
		list.add(String.valueOf(getShellComp()));
		list.add(String.valueOf(getCoatingComp()));
		list.add(String.valueOf(getCoatingAmount()));
		list.add(String.valueOf(getCoatingAmountUnit()));
	    list.add(String.valueOf(getFunctionalGroups()));
		list.add(String.valueOf(getFunctionalizationProtocol()));
		list.add(String.valueOf(getPurity()));
		list.add(String.valueOf(getPurityApproxSymbol()));
		list.add(String.valueOf(getPurityUnit()));
		list.add(String.valueOf(getPurityMethod()));
		list.add(String.valueOf(getPurityRefChemical()));
		list.add(String.valueOf(getContamUnit()));
		list.add(String.valueOf(getContamAl()));
		list.add(String.valueOf(getContamAs()));
		list.add(String.valueOf(getContamBe()));
		list.add(String.valueOf(getContamCa()));
		list.add(String.valueOf(getContamCo()));
		list.add(String.valueOf(getContamCr()));
		list.add(String.valueOf(getContamFe()));
		list.add(String.valueOf(getContamK()));
		list.add(String.valueOf(getContamMg()));
		list.add(String.valueOf(getContamNa()));
		list.add(String.valueOf(getContamP()));
		list.add(String.valueOf(getContamPb()));
		list.add(String.valueOf(getContamSb()));
		list.add(String.valueOf(getContamSe()));
		list.add(String.valueOf(getContamSiO2()));
		list.add(String.valueOf(getContamSn()));
		list.add(String.valueOf(getContamTl()));
		list.add(String.valueOf(getContamV()));
	    list.add(String.valueOf(getContamMethod()));
		list.add(String.valueOf(getCrystalStructure()));
		list.add(String.valueOf(getCrystalStructureMethod()));
		list.add(String.valueOf(getSynthesisMethod()));
		list.add(String.valueOf(getSynthesisDate()));
		list.add(String.valueOf(getParticleOuterDiamAvg()));
		list.add(String.valueOf(getParticleOuterDiamApproxSymbol()));
		list.add(String.valueOf(getParticleOuterDiamUnit()));
		list.add(String.valueOf(getParticleOuterDiamUncertain()));
		list.add(String.valueOf(getParticleOuterDiamLow()));
		list.add(String.valueOf(getParticleOuterDiamHigh()));
		list.add(String.valueOf(getParticleOuterDiamMethod()));
		list.add(String.valueOf(getParticleInnerDiamAvg()));
		list.add(String.valueOf(getParticleInnerDiamApproxSymbol()));
		list.add(String.valueOf(getParticleInnerDiamUnit()));
		list.add(String.valueOf(getParticleInnerDiamUncertain()));
		list.add(String.valueOf(getParticleInnerDiamLow()));
		list.add(String.valueOf(getParticleInnerDiamHigh()));
		list.add(String.valueOf(getParticleInnerDiamMethod()));
		list.add(String.valueOf(getParticleLengthAvg()));
		list.add(String.valueOf(getParticleLengthApproxSymbol()));
		list.add(String.valueOf(getParticleLengthUnit()));
		list.add(String.valueOf(getParticleLengthUncertain()));
		list.add(String.valueOf(getParticleLengthLow()));
		list.add(String.valueOf(getParticleLengthHigh()));
	    list.add(String.valueOf(getParticleLengthMethod()));
		list.add(String.valueOf(getParticleThicknessAvg()));
		list.add(String.valueOf(getParticleThicknessApproxSymbol()));
		list.add(String.valueOf(getParticleThicknessUnit()));
		list.add(String.valueOf(getParticleThicknessUncertain()));
		list.add(String.valueOf(getParticleThicknessLow()));
		list.add(String.valueOf(getParticleThicknessHigh()));
		list.add(String.valueOf(getParticleThicknessMethod()));
		list.add(String.valueOf(getWallNumber()));
		list.add(String.valueOf(getAspectRatio()));
		list.add(String.valueOf(getShape()));
		list.add(String.valueOf(getSurfaceAreaAvg()));
		list.add(String.valueOf(getSurfaceAreaApproxSymbol()));
		list.add(String.valueOf(getSurfaceAreaUnit()));
		list.add(String.valueOf(getSurfaceAreaUncertain()));
		list.add(String.valueOf(getSurfaceAreaLow()));
		list.add(String.valueOf(getSurfaceAreaHigh()));
		list.add(String.valueOf(getSurfaceAreaMethod()));
		list.add(String.valueOf(getMc_timeValue()));
		list.add(String.valueOf(getMc_timeValueUnit()));
		list.add(String.valueOf(getMc_particleConcentration()));
		list.add(String.valueOf(getMc_particleConcentrationUnit()));
		list.add(String.valueOf(getDispersionMediumID()));
		list.add(String.valueOf(getMc_mediumDescription()));
		list.add(String.valueOf(getMc_serumAdditive()));
		list.add(String.valueOf(getMc_serumConcentration()));
		list.add(String.valueOf(getMc_serumConcentrationUnit()));
		list.add(String.valueOf(getMc_antibioticName()));
		list.add(String.valueOf(getMc_antibioticConcentration()));
		list.add(String.valueOf(getMc_antibioticConcentrationUnit()));
		list.add(String.valueOf(getMc_domForm()));
		list.add(String.valueOf(getMc_domConcentration()));
		list.add(String.valueOf(getMc_domUnit()));
		list.add(String.valueOf(getMc_salinityValue()));
		list.add(String.valueOf(getMc_salinityUnit()));
		list.add(String.valueOf(getSolubility()));
		list.add(String.valueOf(getMc_pHAvg()));
		list.add(String.valueOf(getMc_pHApproxSymbol()));
	    list.add(String.valueOf(getMc_pHUncertain()));
		list.add(String.valueOf(getMc_pHLow()));
		list.add(String.valueOf(getMc_pHHigh()));
		list.add(String.valueOf(getMc_mediumTemp()));
		list.add(String.valueOf(getMc_mediumTempUnit()));
		list.add(String.valueOf(getZetaPotentialAvg()));
		list.add(String.valueOf(getZetaPotentialApproxSymbol()));
		list.add(String.valueOf(getZetaPotentialUnit()));
		list.add(String.valueOf(getZetaPotentialUncertain()));
		list.add(String.valueOf(getZetaPotentialLow()));
		list.add(String.valueOf(getZetaPotentialHigh()));
		list.add(String.valueOf(getZetaPotentialMethod()));
		list.add(String.valueOf(getSizeDistribType()));
		list.add(String.valueOf(getSizeDistribModality()));
		list.add(String.valueOf(getSizeDistribMethod()));
		list.add(String.valueOf(getSizeDistribAvg()));
		list.add(String.valueOf(getSizeDistribApproxSymbol()));
		list.add(String.valueOf(getSizeDistribUnit()));
		list.add(String.valueOf(getSizeDistribUncertain()));
		list.add(String.valueOf(getSizeDistribLow()));
		list.add(String.valueOf(getSizeDistribHigh()));
		list.add(String.valueOf(getSizeDistribAvg2()));
		list.add(String.valueOf(getSizeDistribApproxSymbol2()));
		list.add(String.valueOf(getSizeDistribUnit2()));
		list.add(String.valueOf(getSizeDistribUncertain2()));
	    list.add(String.valueOf(getSizeDistribLow2()));
		list.add(String.valueOf(getSizeDistribHigh2()));
		
		// Assay table
		list.add(String.valueOf(getMeasurementID()));
		list.add(String.valueOf(getAssayType()));
		list.add(String.valueOf(getAssayName()));
		list.add(String.valueOf(getSampleName()));
		list.add(String.valueOf(getSubjectSpecies()));
		list.add(String.valueOf(getSubjectID()));
		list.add(String.valueOf(getCellType()));
		list.add(String.valueOf(getCellSource()));
		list.add(String.valueOf(getTestMediumID()));
		list.add(String.valueOf(getMediumDescription()));
		list.add(String.valueOf(getSerumAdditive()));
		list.add(String.valueOf(getSerumConcentration()));
		list.add(String.valueOf(getSerumConcentrationUnit()));
		list.add(String.valueOf(getAntibioticName()));
		list.add(String.valueOf(getAntibioticConcentration()));
		list.add(String.valueOf(getAntibioticConcentrationUnit()));
		list.add(String.valueOf(getDomForm()));
		list.add(String.valueOf(getDomConcentration()));
		list.add(String.valueOf(getDomUnit()));
		list.add(String.valueOf(getSalinityValue()));
		list.add(String.valueOf(getSalinityUnit()));
		list.add(String.valueOf(getPhAvg()));
		list.add(String.valueOf(getPhApproxSymbol()));
	    list.add(String.valueOf(getPhUncertain()));
		list.add(String.valueOf(getPhLow()));
		list.add(String.valueOf(getPhHigh()));
		list.add(String.valueOf(getMediumTemp()));
		list.add(String.valueOf(getMediumTempUnit()));
		list.add(String.valueOf(getTimeValue()));
		list.add(String.valueOf(getTimeValueUnit()));
		list.add(String.valueOf(getParticleConcentration()));
		list.add(String.valueOf(getParticleConcentrationUnit()));
		list.add(String.valueOf(getParticleExposDuration()));
		list.add(String.valueOf(getParticleExposDurationUnit()));
		list.add(String.valueOf(getUvaDose()));
		list.add(String.valueOf(getUvaDoseUnit()));
		list.add(String.valueOf(getUvaExposDuration()));
		list.add(String.valueOf(getUvaExposDurationUnit()));
		list.add(String.valueOf(getViabilityAvg()));
		list.add(String.valueOf(getViabilityApproxSymbol()));
		list.add(String.valueOf(getViabilityUnit()));
		list.add(String.valueOf(getViabilityUncertain()));
		list.add(String.valueOf(getViabilityLow()));
		list.add(String.valueOf(getViabilityHigh()));
		list.add(String.valueOf(getViabilityMethod()));
		list.add(String.valueOf(getLc50()));
		list.add(String.valueOf(getLc50ApproxSymbol()));
		list.add(String.valueOf(getLc50Unit()));
		
		String[] strNano = new String[list.size()];
		strNano = list.toArray(strNano);
		
		return strNano;
	}
	
	/**
	 * This method store the fields into an array of strings and returns the array.
	 * The array of strings is used only when writing data to a CSV file.
	 * @author Paul Harten
	 * @param None.
	 * @return String[]
	 */
	public String[] storeDataAsStringArray(int[] fieldIndex) throws Exception {
	
		Field[] fields = NanoMaterial.class.getDeclaredFields();
		
		// all columns of the CSV header are fields of this class */
		String[] strNano = new String[fieldIndex.length];

		for (int i=0; i<strNano.length; i++) {
			Field field = fields[fieldIndex[i]];
			strNano[i] = String.valueOf(field.get(this));
		}
		
		return strNano;
	}
	
	@Override
	public NanoMaterial clone() {
		
		NanoMaterial clone = null;
		
		try {
			clone = (NanoMaterial)super.clone();
			
			Field[] fields = NanoMaterial.class.getDeclaredFields();
		    for (int i=0; i<fields.length; i++) {
		    	Field field = fields[i];
		    	field.set(clone, field.get(this));
		    }
		} catch (Exception e) {
			e.printStackTrace();
		}
			
		return clone;
	}
	
	/* Return whether other nanoMaterial is the same as this one */
	public boolean isSame(NanoMaterial other) throws Exception {

		Field[] fields = NanoMaterial.class.getDeclaredFields();
	    for (int i=0; i<fields.length; i++) {
	    	Field field = fields[i];
	    	Object v1 = field.get(this);
	    	Object v2 = field.get(other);
	    	if (v1==v2) continue;  // if both are the same, continue (this includes both null)
	    	if (v1==null || v2==null) return false;  // one is null, the other is not
	    	if (v1.equals(v2)) continue;
	    	if (v1.toString().compareTo(v2.toString())==0) continue;
	    	return false;
	    }
	    
		return true;
	}
	
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
	public Integer getSynthesisDate() {
		return synthesisDate;
	}
	public void setSynthesisDate(Integer synthesisDate) {
		this.synthesisDate = synthesisDate; // millesconds since January 1, 1970 00:00:00.000 GMT
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
	public Integer getMc_timeValue() {
		return mc_timeValue;
	}
	public void setMc_timeValue(Integer mc_timeValue) {
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
	public Double getParticleConcentration() {
		return particleConcentration;
	}
	public void setParticleConcentration(Double particleConcentration) {
		this.particleConcentration = particleConcentration;
	}
	public Double getLc50() {
		return lc50;
	}
	public void setLc50(Double lc50) {
		this.lc50 = lc50;
	}	
	public String getMc_mediumDescription() {
		return mc_mediumDescription;
	}
	public void setMc_mediumDescription(String mc_mediumDescription) {
		this.mc_mediumDescription = mc_mediumDescription;
	}
	public String getMc_serumAdditive() {
		return mc_serumAdditive;
	}
	public void setMc_serumAdditive(String mc_serumAdditive) {
		this.mc_serumAdditive = mc_serumAdditive;
	}
	public Double getMc_serumConcentration() {
		return mc_serumConcentration;
	}
	public void setMc_serumConcentration(Double mc_serumConcentration) {
		this.mc_serumConcentration = mc_serumConcentration;
	}
	public String getMc_serumConcentrationUnit() {
		return mc_serumConcentrationUnit;
	}
	public void setMc_serumConcentrationUnit(String mc_serumConcentrationUnit) {
		this.mc_serumConcentrationUnit = mc_serumConcentrationUnit;
	}
	public String getMc_antibioticName() {
		return mc_antibioticName;
	}
	public void setMc_antibioticName(String mc_antibioticName) {
		this.mc_antibioticName = mc_antibioticName;
	}
	public Double getMc_antibioticConcentration() {
		return mc_antibioticConcentration;
	}
	public void setMc_antibioticConcentration(Double mc_antibioticConcentration) {
		this.mc_antibioticConcentration = mc_antibioticConcentration;
	}
	public String getMc_antibioticConcentrationUnit() {
		return mc_antibioticConcentrationUnit;
	}
	public void setMc_antibioticConcentrationUnit(
			String mc_antibioticConcentrationUnit) {
		this.mc_antibioticConcentrationUnit = mc_antibioticConcentrationUnit;
	}
	public String getMc_domForm() {
		return mc_domForm;
	}
	public void setMc_domForm(String mc_domForm) {
		this.mc_domForm = mc_domForm;
	}
	public Double getMc_domConcentration() {
		return mc_domConcentration;
	}
	public void setMc_domConcentration(Double mc_domConcentration) {
		this.mc_domConcentration = mc_domConcentration;
	}
	public String getMc_domUnit() {
		return mc_domUnit;
	}
	public void setMc_domUnit(String mc_domUnit) {
		this.mc_domUnit = mc_domUnit;
	}
	public Double getMc_salinityValue() {
		return mc_salinityValue;
	}
	public void setMc_salinityValue(Double mc_salinityValue) {
		this.mc_salinityValue = mc_salinityValue;
	}
	public String getMc_salinityUnit() {
		return mc_salinityUnit;
	}
	public void setMc_salinityUnit(String mc_salinityUnit) {
		this.mc_salinityUnit = mc_salinityUnit;
	}

}
