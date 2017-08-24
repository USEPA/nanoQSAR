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

public class NanoMaterials extends Vector<NanoMaterial> implements Serializable, Cloneable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 9210392203321638729L;
	private String[] header = null;
	private int[] descriptorIndex = null;
	private int[] resultIndex = null;
	
	public NanoMaterials() throws Exception {
		super();
	}
	
//	get nanoMaterials by reading from a CSV file
	public NanoMaterials(String filename) throws Exception {
		super();
		this.readCsvFile(filename);
	}
	
//	get nanoMaterials by querying a database server
	public NanoMaterials(MySqlQuery sqlQuery) throws Exception {
		super();
		/* Read data from remote MySQL server and store them in a list.  */
		this.addAll(sqlQuery.getNanoMaterials());
		this.setHeader(sqlQuery.getHeader());
	}
	
	/**
	 * This method performs the main steps needed for reading the desired data from the
	 * database and writing the data to a CSV file.  It sets up the path and the name of the
	 * CSV file; it gets the header file of the CSV file; it calls the getSqlQuery method to
	 * obtain the SQL query statement; it passes the SQL query statement to the 
	 * getNanoMaterials method that will read the data from the database and stores them in a
	 * list of NanoMaterial objects.  This information is passed to the write method of the 
	 * CsvFileWriter class that will generate the CSV file.
	 * @author Wilson Melendez
	 * @param None.
	 * @return Nothing.
	 */
	public void mineNanoMaterials(MySqlQuery sqlQuery) throws Exception {			
	
		try	{				  
			
			/* Read data from remote MySQL server and store them in a list.  */
			this.addAll(sqlQuery.getNanoMaterials());
			
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
			
			int[] fieldIndex = NanoMaterial.getFieldIndex(getHeader());
			
			String[] line = null;
			/* Loop over lines in the csv file */
			while ((line = csvReader.readNext())!=null) {
				
				/* Read next row of data */
//				String[] line = csvReader.readNext();
				
				/* convert the data into a new NanoMaterial */
				this.add(new NanoMaterial(fieldIndex, line));
	
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
			int[] fieldIndex = NanoMaterial.getFieldIndex(this.getHeader());
			
			/* Loop over list of NanoMaterial objects */
			for(NanoMaterial nanoM : this) {
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

	public NanoMaterials clone() {
		
		NanoMaterials clone = null;
		try {
			clone = new NanoMaterials();
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

	public boolean isSame(NanoMaterials other) throws Exception {
		
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
	 * This method determines the positional indices of the numeric 
	 * data in the CSV file.
	 * @author Wilson Melendez & Paul Harten
	 * @throws Exception 
	 */
	public void selectNumericColumns() throws Exception
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
        String[] desArray = new String[descriptors.size()];
        desArray = descriptors.toArray(desArray);
        setDescriptorIndex(NanoMaterial.getFieldIndex(desArray));        
        
		ArrayList<String> results = new ArrayList<String>();
		
        /* Store indices of current Results columns */
        results.add("ViabilityAvg");        
        results.add("LC50");
        
        /* Create arrays that will store indices, minimum, and 
         * maximum values of the Y columns. */
        String[] resArray = new String[results.size()];
        resArray = results.toArray(resArray);
        setResultIndex(NanoMaterial.getFieldIndex(resArray));        

	}
	
	public void setHeader(String[] header) {
		this.header = header;
	}
	
	public String[] getHeader() {
		return this.header;
	}

	public int[] getDescriptorIndex() {
		return descriptorIndex;
	}

	public void setDescriptorIndex(int[] descriptorIndex) {
		this.descriptorIndex = descriptorIndex;
	}

	public int[] getResultIndex() {
		return resultIndex;
	}

	public void setResultIndex(int[] resultIndex) {
		this.resultIndex = resultIndex;
	}

}
