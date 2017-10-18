package nanoQSAR;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import datamine.DBUtil;
import datamine.MySqlQuery;

public class NanoToxExps extends Vector<NanoToxExp> implements Serializable, Cloneable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 9210392203321638729L;
	private int[] descriptorIndex = null;
	private int[] categoryDescriptorIndex = null;
	private int[] resultIndex = null;
	
	private String[] header = null;
	private String[] descriptorHeader = null;
	private String[] categoryDescriptorHeader = null;
	private String[] resultHeader = null;
	
	public NanoToxExps() throws Exception {
		super();
	}
	
//	get nanoToxExps by reading from a CSV file
	public NanoToxExps(String filename) throws Exception {
		super();
		this.readCsvFile(filename);
	}
	
//	get nanoToxExps by querying a database server
	public NanoToxExps(MySqlQuery sqlQuery) throws Exception {
		super();
		/* Read data from remote MySQL server and store them in a list.  */
		this.addAll(sqlQuery.getNanoToxExps());
		this.setHeader(sqlQuery.getHeader());
	}
	
	/**
	 * This method performs the main steps needed for reading the desired data from the
	 * database and writing the data to a CSV file.  It sets up the path and the name of the
	 * CSV file; it gets the header file of the CSV file; it calls the getSqlQuery method to
	 * obtain the SQL query statement; it passes the SQL query statement to the 
	 * getNanoMaterials method that will read the data from the database and stores them in a
	 * list of NanoToxExp objects.  This information is passed to the write method of the 
	 * CsvFileWriter class that will generate the CSV file.
	 * @author Wilson Melendez
	 * @param None.
	 * @return Nothing.
	 */
	public void mineNanoToxExps(MySqlQuery sqlQuery) throws Exception {			
	
		try	{				  
			
			/* Read data from remote MySQL server and store them in a list.  */
			this.addAll(sqlQuery.getNanoToxExps());
			
			/* Check default units and perform unit conversions if necessary. */
			DefaultUnits.checkUnits(this);
			
		} catch	(Exception ex) {
			
//			lOGGER.log(Level.SEVERE, "Exception was thrown: ending the execution of the program.");
			throw ex;
			
		}
		
	}
	
	public void readCsvFile(String filename) throws Exception {

		CSVReader csvReader = null;
		
		try	{
			
			/* create a new CSVReader for the fileName */
			csvReader = new CSVReader(new FileReader(filename));
			
			/* read the headers from the csv file */
			this.setHeader(csvReader.readNext());
			
			int[] fieldIndex = NanoToxExp.getFieldIndex(getHeader());
			
			String[] line = null;
			/* Loop over lines in the csv file */
			while ((line = csvReader.readNext())!=null) {
				
				/* Read next row of data */
//				String[] line = csvReader.readNext();
				
				/* convert the data into a new NanoToxExp */
				this.add(new NanoToxExp(fieldIndex, line));
	
			}
			
			/* Close the writer. */
			csvReader.close();
			
		} catch(Exception ex)	{
			
//			lOGGER.log(Level.SEVERE, "FileWriter for " + filename + " could not be constructed.", ex);	
			throw ex;
			
		}
		
	}
	
	public void writeCsvFile(String CsvFileName) throws Exception {

		String[] entries;
		
		try	{
			
			/* create a new FileWriter for the CsvFileName */
			FileWriter file = new FileWriter(CsvFileName); 
			
			/* Create an instance of the CSVWriter class and specify the comma as the 
			 * default separator. Default quote character is double quote. */ 
			CSVWriter csvOutput = new CSVWriter(file, CSVWriter.DEFAULT_SEPARATOR);
			
			/* Write header line to CSV file. */
			csvOutput.writeNext(this.getHeader());
			
			/* Get header/fieldIndex translation*/
			int[] fieldIndex = NanoToxExp.getFieldIndex(this.getHeader());
			
			/* Loop over list of NanoToxExp objects */
			for(NanoToxExp nanoM : this) {
				/* Retrieve data as an array of strings and assign array to entries. 
				 * The array represents one record (row of data). */
				entries = nanoM.storeDataAsStringArray(fieldIndex);  
				
				/* Write row of data to output using the writeNext method. */
				csvOutput.writeNext(entries);     
			}
			
			/* Close the writer. */
			csvOutput.close();
			
		} catch(Exception ex)	{
			
//			lOGGER.log(Level.SEVERE, "FileWriter for " + filename + " could not be constructed.", ex);	
			throw ex;
			
		}
		
	}

	public NanoToxExps clone() {
		
		NanoToxExps clone = null;
		try {
			clone = new NanoToxExps();
			clone.setHeader(this.getHeader());
			for (int i=0; i<this.size(); i++) {
				clone.add(this.get(i).clone());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return clone;
	}

	public boolean isSame(NanoToxExps other) throws Exception {
		
		if (this.getHeader().length != other.getHeader().length) return false;
		if (this.size() != other.size()) return false;
		
		if (this.header.equals(other.header)) {
		} else {
			String[] thisHeader = this.getHeader();
			String[] otherHeader = other.getHeader();
			for (int i=0; i<thisHeader.length; i++) {
				if (thisHeader[i].matches(otherHeader[i])) continue;
				return false;
			}
		}
		
		if (this.size() != other.size()) return false;
		for (int i=0; i<this.size(); i++) {
			if (this.get(i).isSame(other.get(i))) continue;
			return false;
		}
		
		return true;
	}
	
	/**
	 * This method determines the positional indices of the continuous 
	 * data in the CSV file.
	 * @author Wilson Melendez & Paul Harten
	 * @throws Exception 
	 */
	public void selectContinuousColumns() throws Exception
	{
		/* Create ArrayLists to store positional indices, minimum, 
		 * and maximum values of the X and Y matrices. */
		ArrayList<String> descriptors = new ArrayList<String>();
		
		/* Store indices of current Descriptor columns */
		descriptors.add("CoatingAmount");	
	    descriptors.add("Purity");
		descriptors.add("ContamAl"); 
		descriptors.add("ContamAs");  
		descriptors.add("ContamBe");	
        descriptors.add("ContamCa"); 
        descriptors.add("ContamCo"); 
        descriptors.add("ContamCr"); 
        descriptors.add("ContamFe"); 
        descriptors.add("ContamK");  
        descriptors.add("ContamMg");  
        descriptors.add("ContamNa"); 
        descriptors.add("ContamP");  
        descriptors.add("ContamPb"); 
        descriptors.add("ContamSb");
        descriptors.add("ContamSe");  
        descriptors.add("ContamSiO2");
        descriptors.add("ContamSn"); 
        descriptors.add("ContamTl"); 
        descriptors.add("ContamV");  
        descriptors.add("ParticleOuterDiamAvg"); 
        descriptors.add("ParticleOuterDiamLow"); 
        descriptors.add("ParticleOuterDiamHigh"); 
        descriptors.add("ParticleInnerDiamAvg");        
        descriptors.add("ParticleInnerDiamLow");   
        descriptors.add("ParticleInnerDiamHigh"); 
        descriptors.add("ParticleLengthAvg");      
        descriptors.add("ParticleLengthLow"); 
        descriptors.add("ParticleLengthHigh");  
        descriptors.add("ParticleThicknessAvg"); 
        descriptors.add("ParticleThicknessLow"); 
        descriptors.add("ParticleThicknessHigh"); 
        descriptors.add("SurfaceAreaAvg"); 
        descriptors.add("SurfaceAreaLow"); 
        descriptors.add("SurfaceAreaHigh");      
        descriptors.add("MC_TimeValue");  
        descriptors.add("MC_ParticleConcentration");      
        descriptors.add("MC_SerumConcentration");        
        descriptors.add("MC_AntibioticConcentration");   
        descriptors.add("MC_DOMConcentration");   
        descriptors.add("MC_SalinityValue"); 
        descriptors.add("MC_pHAvg"); 
        descriptors.add("MC_pHLow");  
        descriptors.add("MC_pHHigh");  
        descriptors.add("MC_MediumTemp"); 
        descriptors.add("ZetaPotentialAvg");
        descriptors.add("ZetaPotentialLow");
        descriptors.add("ZetaPotentialHigh");    
        descriptors.add("SizeDistribAvg");    
        descriptors.add("SizeDistribLow"); 
        descriptors.add("SizeDistribHigh");  
        descriptors.add("SizeDistribAvg2");     
        descriptors.add("SizeDistribLow2");   
        descriptors.add("SizeDistribHigh2");  
        descriptors.add("SerumConcentration");
        descriptors.add("AntibioticConcentration");    
        descriptors.add("DOMConcentration");   
        descriptors.add("SalinityValue");     
        descriptors.add("pHAvg");     
        descriptors.add("pHLow");   
        descriptors.add("pHHigh"); 
        descriptors.add("MediumTemp"); 
        descriptors.add("TimeValue");      
        descriptors.add("ParticleConcentration"); 
        descriptors.add("ParticleExposDuration");    
        descriptors.add("UVADose"); 
        descriptors.add("UVAExposDuration"); 
        
        /* Create arrays that will store indices, minimum, and 
         * maximum values of the X columns. */
        descriptorHeader = new String[descriptors.size()];
        descriptorHeader = descriptors.toArray(descriptorHeader);
        setDescriptorIndex(NanoToxExp.getFieldIndex(descriptorHeader));

	}
	
	/**
	 * This method determines the positional indices of the category 
	 * data in the CSV file.
	 * @author Wilson Melendez & Paul Harten
	 * @throws Exception 
	 */
	public void selectCategoryColumns() throws Exception
	{
		/* Create ArrayLists to store positional indices, minimum, 
		 * and maximum values of the X and Y matrices. */
		ArrayList<String> descriptors = new ArrayList<String>();
		
		/* Store indices of current Descriptor columns */
		descriptors.add("DataSource");	
	    descriptors.add("LotNumber");
		descriptors.add("CoreComp");
		descriptors.add("ShellComp"); 
		descriptors.add("FunctionalGroups");  
		descriptors.add("FunctionalizationProtocol");	
        descriptors.add("PurityMethod"); 
        descriptors.add("PurityRefChemical"); 
        descriptors.add("ContamMethod"); 
        descriptors.add("CrystalStructure"); 
        descriptors.add("CrystalStructureMethod");  
        descriptors.add("SynthesisMethod");  
        descriptors.add("ParticleOuterDiamUncertain"); 
        descriptors.add("ParticleOuterDiamMethod");  
        descriptors.add("ParticleInnerDiamUncertain");        
        descriptors.add("ParticleInnerDiamMethod");    
        descriptors.add("ParticleLengthUncertain");      
        descriptors.add("ParticleLengthMethod");   
        descriptors.add("ParticleThicknessUncertain"); 
        descriptors.add("ParticleThicknessMethod"); 
        descriptors.add("WallNumber");
        descriptors.add("AspectRatio");
        descriptors.add("Shape");
        descriptors.add("SurfaceAreaUncertain"); 
        descriptors.add("SurfaceAreaMethod");      
        
        /* Create arrays that will store indices of the X category columns. */
        categoryDescriptorHeader = new String[descriptors.size()];
        categoryDescriptorHeader = descriptors.toArray(categoryDescriptorHeader);
        setCategoryDescriptorIndex(NanoToxExp.getFieldIndex(categoryDescriptorHeader));

	}
	
	/**
	 * This method determines the positional indices of the numeric 
	 * data in the CSV file.
	 * @author Wilson Melendez & Paul Harten
	 * @throws Exception 
	 */
	public void selectResultColumns() throws Exception
	{
		/* Create ArrayLists to store positional indices of the Y matrix. */
		ArrayList<String> results = new ArrayList<String>();
		
        /* Store indices of current Results columns */
        results.add("ViabilityAvg");        
        results.add("LC50");
        
        /* Create arrays that will store indices, minimum, and 
         * maximum values of the Y columns. */
        resultHeader = new String[results.size()];
        resultHeader = results.toArray(resultHeader);
        setResultIndex(NanoToxExp.getFieldIndex(resultHeader));

	}
	
	public int[] getDescriptorIndex() {
		return descriptorIndex;
	}

	public void setDescriptorIndex(int[] descriptorIndex) {
		this.descriptorIndex = descriptorIndex;
	}
	
	public int[] getCategoryDescriptorIndex() {
		return categoryDescriptorIndex;
	}
	
	public void setCategoryDescriptorIndex(int[] categoryDescriptorIndex) {
		this.categoryDescriptorIndex = categoryDescriptorIndex;
	}

	public int[] getResultIndex() {
		return resultIndex;
	}

	public void setResultIndex(int[] resultIndex) {
		this.resultIndex = resultIndex;
	}
	
	public void setHeader(String[] header) {
		this.header = header;
	}
	
	public String[] getHeader() {
		return this.header;
	}

	public String[] getDescriptorHeader() {
		return descriptorHeader;
	}
	
	public String[] getCategoryDescriptorHeader() {
		return categoryDescriptorHeader;
	}

	protected String[] getResultHeader() {
		return resultHeader;
	}

}
