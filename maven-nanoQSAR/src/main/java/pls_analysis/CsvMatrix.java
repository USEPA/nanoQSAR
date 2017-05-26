package pls_analysis;

import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.jblas.DoubleMatrix;
import org.jblas.Singular;
import org.jblas.Solve;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import nanoQSAR.LoggerInfo;


/**
 * @author Wilson Melendez
 *
 */
public class CsvMatrix
{
	private static int xcolumns = 67;
	private static int ycolumns = 2;
	private static int rowsSize;
	private static int numDataSets = 5;
	private static Integer[] numSetRows = new Integer[numDataSets];
	
	private static List<String[]> rows;
	private static String[] header;
	private static DoubleMatrix Xmatrix;
	private static DoubleMatrix Ymatrix;
	private static DoubleMatrix Umatrix;
	private static DoubleMatrix Tmatrix;
	private static DoubleMatrix Wmatrix;		
	private static DoubleMatrix Cmatrix;
	private static DoubleMatrix Pmatrix;
	private static DoubleMatrix Bmatrix;
	
	private static DoubleMatrix[] XmatrixSet = new DoubleMatrix[numDataSets];
	private static DoubleMatrix[] YmatrixSet = new DoubleMatrix[numDataSets];
	
	private static double ssx;
	private static double ssy;
	
	private static double EPSILON = 1.0e-6;
	private static double EPSILON_DEFLATION = 1.0e-6;
	
	/* Need this line to allow logging of error messages */
	private final static Logger lOGGER = Logger.getLogger("nanoQSAR");
	
	/**
	 * @return the logger
	 */
	public static Logger getLogger() {
		return lOGGER;
	}

	/**
	 * 
	 * @return
	 * @author Wilson Melendez
	 */
	public static List<String[]> getRows() {
		return rows;
	}

	/**
	 * 
	 * @param rows
	 * @author Wilson Melendez
	 */
	public static void setRows(List<String[]> rows) {
		CsvMatrix.rows = rows;
	}

	/**
	 * 
	 * @return
	 * @author Wilson Melendez
	 */
	public static String[] getHeader() {
		return header;
	}

	public static void setHeader(String[] header) {
		CsvMatrix.header = header;
	}

	/**
	 * @return the xmatrix
	 * @author Wilson Melendez
	 */
	public static DoubleMatrix getXmatrix() {
		return Xmatrix;
	}

	/**
	 * @return the ymatrix
	 * @author Wilson Melendez
	 */
	public static DoubleMatrix getYmatrix() {
		return Ymatrix;
	}
	
	/**
	 * @return the xcolumns
	 * @author Wilson Melendez
	 */
	public static int getXcolumns() {
		return xcolumns;
	}

	/**
	 * @return the ycolumns
	 * @author Wilson Melendez
	 */
	public static int getYcolumns() {
		return ycolumns;
	}

	/**
	 * @return the rowsSize
	 * @author Wilson Melendez
	 */
	public static int getRowsSize() {
		return rowsSize;
	}

	/**
	 * @return the tmatrix
	 * @author Wilson Melendez
	 */
	public static DoubleMatrix getTmatrix() {
		return Tmatrix;
	}

	/**
	 * @return the wmatrix
	 * @author Wilson Melendez
	 */
	public static DoubleMatrix getWmatrix() {
		return Wmatrix;
	}

	/**
	 * @return the cmatrix
	 * @author Wilson Melendez
	 */
	public static DoubleMatrix getCmatrix() {
		return Cmatrix;
	}

	/**
	 * @return the pmatrix
	 * @author Wilson Melendez
	 */
	public static DoubleMatrix getPmatrix() {
		return Pmatrix;
	}
	
	/**
	 * 
	 * @return the umatrix
	 * @author Wilson Melendez
	 */
	public static DoubleMatrix getUmatrix(){
		return Umatrix;
	}

	/**
	 * 
	 * @return the Bmatrix
	 * @author Wilson Melendez
	 */
	public static DoubleMatrix getBmatrix(){
		return Bmatrix;
	}
	
	/**
	 * 
	 * @return
	 * @author Wilson Melendez
	 */
	public static double getSsy()
	{
		return ssy;
	}
	
	/**
	 * 
	 * @return
	 * @author Wilson Melendez
	 */
	public static double getSsx()
	{
		return ssx;
	}
	
	/**
	 * @author Wilson Melendez
	 * @param filename
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static void readCsvFile(String filename) throws FileNotFoundException, IOException
	{
		FileReader fReader;
		try
		{
			fReader = new FileReader(filename); 
		}
		catch(FileNotFoundException ex)
		{
			lOGGER.severe("CSV file was not found.");	
			throw ex;
		}
		
		/* Create instance of CSVReader. */
		CSVReader csvInput = new CSVReader(fReader);
		
		/* Read contents of CSV file all at once and store it in array list. */
		rows = csvInput.readAll();
		
		/* Read the first row of the data and store it in string array named header. */
		header = rows.get(0);
		
		/* Remove the header line from the array list; header is row 0. */
		rows.remove(0);
		
		/* Close connection to CSV file, but throw exception if failure to close. */
		try
		{
			csvInput.close();
			fReader.close();
		}
		catch(IOException ex)
		{
			lOGGER.severe("Attempt to close CSV reader failed.");	
			throw ex;
		}
	}
	
	/**
	 * This method builds the X and Y matrices needed by the PLS Regression algorithm.
	 * Null values are converted to random values using the Math.randow() method.
	 * @author Wilson Melendez
	 */
	public static void buildMatrices()
	{		
		boolean foundNulls = false;
		rowsSize = rows.size();  // Number of rows in array list.
		Xmatrix = new DoubleMatrix(rowsSize,xcolumns);   // X matrix
		Ymatrix = new DoubleMatrix(rowsSize,ycolumns);   // Y matrix
		
		/* Check whether data has any null values. */
		for (String[] str : rows)
		{
			if (Arrays.asList(str).contains("null"))
			{
				foundNulls = true;
				break;
			}
		}
		
		if (foundNulls)		
		{
			buildMatricesContainingNulls(Xmatrix, Ymatrix);  // Handle case with null values.
		}
		else
		{
			buildMatricesWithoutNulls(Xmatrix, Ymatrix);  // Handle case with no null values.
		}		
	}
	
	/**
	 * This routine builds the X and Y matrices for the case of the original data 
	 * containing null values.
	 * @author Wilson Melendez
	 */
	public static void buildMatricesContainingNulls(DoubleMatrix Xorig, DoubleMatrix Yorig)
	{
		String[] nextLine;
		int[] jcolX = new int[xcolumns];  // Positional indices of X-matrix columns in CSV file.
		int[] jcolY = new int[ycolumns];  // Positional indices of Y-matrix columns in CSV file.
		double[] minValueX = new double[xcolumns];  // Minimum random values of columns in X matrix
		double[] maxValueX = new double[xcolumns];  // Maximum random values of columns in X matrix
		double[] minValueY = new double[ycolumns];  // Minimum random values of columns in Y matrix
		double[] maxValueY = new double[ycolumns];  // Maximum random values of columns in Y matrix
				
		int j = 0, jy = 0;		
		List<String> listHeader = Arrays.asList(header);
		
		/* Store indices and minimum/maximum random values of X-matrix columns */
		jcolX[j] = listHeader.indexOf("CoatingAmount");	
		minValueX[j] = 10.0;  maxValueX[j] = 100.0; j++;
	    jcolX[j] = listHeader.indexOf("Purity");	
	    minValueX[j] = 10.0;  maxValueX[j] = 100.0; j++;
		jcolX[j] = listHeader.indexOf("ContamAl"); 
		minValueX[j] = 10.0;  maxValueX[j] = 1500; j++;
		jcolX[j] = listHeader.indexOf("ContamAs"); 
		minValueX[j] = 10.0;  maxValueX[j] = 1500.0; j++;
		jcolX[j] = listHeader.indexOf("ContamBe");	
		minValueX[j] = 10.0;  maxValueX[j] = 1500.0; j++;
        jcolX[j] = listHeader.indexOf("ContamCa"); 
        minValueX[j] = 10.0;  maxValueX[j] = 1500.0; j++;
        jcolX[j] = listHeader.indexOf("ContamCo"); 
        minValueX[j] = 10.0;  maxValueX[j] = 1500.0; j++;
        jcolX[j] = listHeader.indexOf("ContamCr"); 
        minValueX[j] = 10.0;  maxValueX[j] = 1500.0; j++;
        jcolX[j] = listHeader.indexOf("ContamFe"); 
        minValueX[j] = 10.0;  maxValueX[j] = 1500.0; j++;
        jcolX[j] = listHeader.indexOf("ContamK");  
        minValueX[j] = 10.0;  maxValueX[j] = 1500.0; j++;
        jcolX[j] = listHeader.indexOf("ContamMg");     
        minValueX[j] = 10.0;  maxValueX[j] = 1500.0; j++;
        jcolX[j] = listHeader.indexOf("ContamNa"); 
        minValueX[j] = 10.0;  maxValueX[j] = 1500.0; j++;
        jcolX[j] = listHeader.indexOf("ContamP");  
        minValueX[j] = 10.0;  maxValueX[j] = 1500.0; j++;
        jcolX[j] = listHeader.indexOf("ContamPb"); 
        minValueX[j] = 10.0;  maxValueX[j] = 1500.0; j++;
        jcolX[j] = listHeader.indexOf("ContamSb"); 
        minValueX[j] = 10.0;  maxValueX[j] = 1500.0; j++;
        jcolX[j] = listHeader.indexOf("ContamSe"); 
        minValueX[j] = 10.0;  maxValueX[j] = 1500.0; j++;
        jcolX[j] = listHeader.indexOf("ContamSiO2");
        minValueX[j] = 10.0;  maxValueX[j] = 1500.0; j++;
        jcolX[j] = listHeader.indexOf("ContamSn"); 
        minValueX[j] = 10.0;  maxValueX[j] = 1500.0; j++;
        jcolX[j] = listHeader.indexOf("ContamTl"); 
        minValueX[j] = 10.0;  maxValueX[j] = 1500.0; j++;
        jcolX[j] = listHeader.indexOf("ContamV");  
        minValueX[j] = 10.0;  maxValueX[j] = 1500.0; j++;
        jcolX[j] = listHeader.indexOf("ParticleOuterDiamAvg");  
        minValueX[j] = 100.0;  maxValueX[j] = 250; j++;
        jcolX[j] = listHeader.indexOf("ParticleOuterDiamLow"); 
        minValueX[j] = 10.0;  maxValueX[j] = 100.0; j++;
        jcolX[j] = listHeader.indexOf("ParticleOuterDiamHigh"); 
        minValueX[j] = 250.0;  maxValueX[j] = 400; j++;
        jcolX[j] = listHeader.indexOf("ParticleInnerDiamAvg"); 
        minValueX[j] = 50.0;  maxValueX[j] = 100.0; j++;
        jcolX[j] = listHeader.indexOf("ParticleInnerDiamLow");  
        minValueX[j] = 0.0;  maxValueX[j] = 50; j++;
        jcolX[j] = listHeader.indexOf("ParticleInnerDiamHigh"); 
        minValueX[j] = 100.0;  maxValueX[j] = 150; j++;
        jcolX[j] = listHeader.indexOf("ParticleLengthAvg");     
        minValueX[j] = 50.0;  maxValueX[j] = 100.0; j++;
        jcolX[j] = listHeader.indexOf("ParticleLengthLow");    
        minValueX[j] = 10.0;  maxValueX[j] = 50; j++;
        jcolX[j] = listHeader.indexOf("ParticleLengthHigh"); 
        minValueX[j] = 100.0;  maxValueX[j] = 150; j++;
        jcolX[j] = listHeader.indexOf("ParticleThicknessAvg"); 
        minValueX[j] = 50.0;  maxValueX[j] = 100.0; j++;
        jcolX[j] = listHeader.indexOf("ParticleThicknessLow");  
        minValueX[j] = 10.0;  maxValueX[j] = 50; j++;
        jcolX[j] = listHeader.indexOf("ParticleThicknessHigh");  
        minValueX[j] = 100.0;  maxValueX[j] = 150; j++;
        jcolX[j] = listHeader.indexOf("SurfaceAreaAvg");       
        minValueX[j] = 150.0;  maxValueX[j] = 300; j++;
        jcolX[j] = listHeader.indexOf("SurfaceAreaLow");  
        minValueX[j] = 10.0;  maxValueX[j] = 150; j++;
        jcolX[j] = listHeader.indexOf("SurfaceAreaHigh");  
        minValueX[j] = 300.0;  maxValueX[j] = 450; j++;
        jcolX[j] = listHeader.indexOf("MC_TimeValue");    
        minValueX[j] = 10.0;  maxValueX[j] = 240; j++;
        jcolX[j] = listHeader.indexOf("MC_ParticleConcentration"); 
        minValueX[j] = 10.0;  maxValueX[j] = 100.0; j++;
        jcolX[j] = listHeader.indexOf("MC_SerumConcentration");    
        minValueX[j] = 10.0;  maxValueX[j] = 100.0; j++;
        jcolX[j] = listHeader.indexOf("MC_AntibioticConcentration");   
        minValueX[j] = 10.0;  maxValueX[j] = 100.0; j++;
        jcolX[j] = listHeader.indexOf("MC_DOMConcentration");       
        minValueX[j] = 10.0;  maxValueX[j] = 20.0; j++;
        jcolX[j] = listHeader.indexOf("MC_SalinityValue");        	
        minValueX[j] = 10.0;  maxValueX[j] = 35.0; j++;
        jcolX[j] = listHeader.indexOf("MC_pHAvg");        	      
        minValueX[j] = 4.0;  maxValueX[j] = 10.0; j++;
        jcolX[j] = listHeader.indexOf("MC_pHLow");        	       
        minValueX[j] = 0.0;  maxValueX[j] = 4.0; j++;
        jcolX[j] = listHeader.indexOf("MC_pHHigh");        	       
        minValueX[j] = 10.0;  maxValueX[j] = 14.0; j++;
        jcolX[j] = listHeader.indexOf("MC_MediumTemp");        	   
        minValueX[j] = 10.0;  maxValueX[j] = 40.0; j++;
        jcolX[j] = listHeader.indexOf("ZetaPotentialAvg");        	
        minValueX[j] = -30.0;  maxValueX[j] = -100.0; j++;
        jcolX[j] = listHeader.indexOf("ZetaPotentialLow");     
        minValueX[j] = -10.0;  maxValueX[j] = -30.0; j++;
        jcolX[j] = listHeader.indexOf("ZetaPotentialHigh");    
        minValueX[j] = -100.0;  maxValueX[j] = -150; j++;
        jcolX[j] = listHeader.indexOf("SizeDistribAvg");        
        minValueX[j] = 1000.0;  maxValueX[j] = 2000; j++;
        jcolX[j] = listHeader.indexOf("SizeDistribLow");        
        minValueX[j] = 10.0;  maxValueX[j] = 1000; j++;
        jcolX[j] = listHeader.indexOf("SizeDistribHigh");        	
        minValueX[j] = 2000.0;  maxValueX[j] = 3000; j++;
        jcolX[j] = listHeader.indexOf("SizeDistribAvg2");        	
        minValueX[j] = 1000.0;  maxValueX[j] = 2000; j++;
        jcolX[j] = listHeader.indexOf("SizeDistribLow2");        
        minValueX[j] = 10.0;  maxValueX[j] = 1000; j++;
        jcolX[j] = listHeader.indexOf("SizeDistribHigh2");       
        minValueX[j] = 2000.0;  maxValueX[j] = 3000; j++;
        jcolX[j] = listHeader.indexOf("SerumConcentration");      
        minValueX[j] = 10.0;  maxValueX[j] = 100.0; j++;
        jcolX[j] = listHeader.indexOf("AntibioticConcentration");   
        minValueX[j] = 10.0;  maxValueX[j] = 100.0; j++;
        jcolX[j] = listHeader.indexOf("DOMConcentration");        
        minValueX[j] = 10.0;  maxValueX[j] = 20.0; j++;
        jcolX[j] = listHeader.indexOf("SalinityValue");      
        minValueX[j] = 10.0;  maxValueX[j] = 35.0; j++;
        jcolX[j] = listHeader.indexOf("pHAvg");        	      
        minValueX[j] = 4.0;  maxValueX[j] = 10.0; j++;
        jcolX[j] = listHeader.indexOf("pHLow");         	  
        minValueX[j] = 0.0;  maxValueX[j] = 4.0; j++;
        jcolX[j] = listHeader.indexOf("pHHigh");        	   
        minValueX[j] = 10.0;  maxValueX[j] = 14.0; j++;
        jcolX[j] = listHeader.indexOf("MediumTemp");        	 
        minValueX[j] = 10.0;  maxValueX[j] = 40.0; j++;
        jcolX[j] = listHeader.indexOf("TimeValue");        	         
        minValueX[j] = 48.0;  maxValueX[j] = 72.0; j++;
        jcolX[j] = listHeader.indexOf("ParticleConcentration");     
        minValueX[j] = 10.0;  maxValueX[j] = 100.0; j++;
        jcolX[j] = listHeader.indexOf("ParticleExposDuration");       
        minValueX[j] = 48.0;  maxValueX[j] = 72.0; j++;
        jcolX[j] = listHeader.indexOf("UVADose");        	          
        minValueX[j] = 0.0;  maxValueX[j] = 10.0; j++;
        jcolX[j] = listHeader.indexOf("UVAExposDuration");     
        minValueX[j] = 1.0;  maxValueX[j] = 2.0; j++;
        
        /* Store indices and minimum/maximum random values of Y-matrix columns */
        jcolY[jy]   = listHeader.indexOf("ViabilityAvg");        
        minValueY[jy] = 10.0;  maxValueY[jy] = 100.0; jy++;
        jcolY[jy]   = listHeader.indexOf("LC50");
        minValueY[jy] = 10.0;  maxValueY[jy] = 100.0;
           
		/* Loop over the rows of the data */
		for (int i = 0; i < Xorig.rows; i++)
		{
			nextLine = rows.get(i);	  // Get the ith row of the data
			for (int k = 0; k < xcolumns; k++)   // Loop over the columns
			{
				int jcol = jcolX[k];
				Xorig.put(i,k,getValue(nextLine[jcol], minValueX[k], maxValueX[k]));
			}
			
			for (int k = 0; k < ycolumns; k++)
			{
				int jcol = jcolY[k];
				Yorig.put(i,k,getValue(nextLine[jcol], minValueY[k], maxValueY[k]));
			}	
				
		}
	}
	
	/**
	 * This method builds the X and Y matrices for the case of data without null values.
	 * @author Wilson Melendez
	 */
	public static void buildMatricesWithoutNulls(DoubleMatrix Xorig, DoubleMatrix Yorig)
	{
		String[] nextLine;
		int[] jcolX = new int[xcolumns];  // Positional indices of X-matrix columns in CSV file.
		int[] jcolY = new int[ycolumns];  // Positional indices of Y-matrix columns in CSV file.
		
		int j = 0, jy = 0;		
		List<String> listHeader = Arrays.asList(header);
		
		/* Store indices and minimum/maximum random values of X-matrix columns */
		jcolX[j++] = listHeader.indexOf("CoatingAmount");		
	    jcolX[j++] = listHeader.indexOf("Purity");	   
		jcolX[j++] = listHeader.indexOf("ContamAl"); 	
		jcolX[j++] = listHeader.indexOf("ContamAs"); 		
		jcolX[j++] = listHeader.indexOf("ContamBe");			
        jcolX[j++] = listHeader.indexOf("ContamCa");        
        jcolX[j++] = listHeader.indexOf("ContamCo");         
        jcolX[j++] = listHeader.indexOf("ContamCr");        
        jcolX[j++] = listHeader.indexOf("ContamFe");         
        jcolX[j++] = listHeader.indexOf("ContamK");         
        jcolX[j++] = listHeader.indexOf("ContamMg");            
        jcolX[j++] = listHeader.indexOf("ContamNa");         
        jcolX[j++] = listHeader.indexOf("ContamP");          
        jcolX[j++] = listHeader.indexOf("ContamPb");         
        jcolX[j++] = listHeader.indexOf("ContamSb");         
        jcolX[j++] = listHeader.indexOf("ContamSe");         
        jcolX[j++] = listHeader.indexOf("ContamSiO2");        
        jcolX[j++] = listHeader.indexOf("ContamSn");        
        jcolX[j++] = listHeader.indexOf("ContamTl");         
        jcolX[j++] = listHeader.indexOf("ContamV");          
        jcolX[j++] = listHeader.indexOf("ParticleOuterDiamAvg");         
        jcolX[j++] = listHeader.indexOf("ParticleOuterDiamLow");        
        jcolX[j++] = listHeader.indexOf("ParticleOuterDiamHigh");        
        jcolX[j++] = listHeader.indexOf("ParticleInnerDiamAvg");         
        jcolX[j++] = listHeader.indexOf("ParticleInnerDiamLow");         
        jcolX[j++] = listHeader.indexOf("ParticleInnerDiamHigh");         
        jcolX[j++] = listHeader.indexOf("ParticleLengthAvg");            
        jcolX[j++] = listHeader.indexOf("ParticleLengthLow");            
        jcolX[j++] = listHeader.indexOf("ParticleLengthHigh");        
        jcolX[j++] = listHeader.indexOf("ParticleThicknessAvg");         
        jcolX[j++] = listHeader.indexOf("ParticleThicknessLow");         
        jcolX[j++] = listHeader.indexOf("ParticleThicknessHigh");          
        jcolX[j++] = listHeader.indexOf("SurfaceAreaAvg");               
        jcolX[j++] = listHeader.indexOf("SurfaceAreaLow");        
        jcolX[j++] = listHeader.indexOf("SurfaceAreaHigh");       
        jcolX[j++] = listHeader.indexOf("MC_TimeValue");           
        jcolX[j++] = listHeader.indexOf("MC_ParticleConcentration");        
        jcolX[j++] = listHeader.indexOf("MC_SerumConcentration");            
        jcolX[j++] = listHeader.indexOf("MC_AntibioticConcentration");         
        jcolX[j++] = listHeader.indexOf("MC_DOMConcentration");              
        jcolX[j++] = listHeader.indexOf("MC_SalinityValue");        	     
        jcolX[j++] = listHeader.indexOf("MC_pHAvg");        	              
        jcolX[j++] = listHeader.indexOf("MC_pHLow");        	              
        jcolX[j++] = listHeader.indexOf("MC_pHHigh");        	               
        jcolX[j++] = listHeader.indexOf("MC_MediumTemp");        	          
        jcolX[j++] = listHeader.indexOf("ZetaPotentialAvg");        	       
        jcolX[j++] = listHeader.indexOf("ZetaPotentialLow");            
        jcolX[j++] = listHeader.indexOf("ZetaPotentialHigh");           
        jcolX[j++] = listHeader.indexOf("SizeDistribAvg");                
        jcolX[j++] = listHeader.indexOf("SizeDistribLow");               
        jcolX[j++] = listHeader.indexOf("SizeDistribHigh");        	        
        jcolX[j++] = listHeader.indexOf("SizeDistribAvg2");        	       
        jcolX[j++] = listHeader.indexOf("SizeDistribLow2");              
        jcolX[j++] = listHeader.indexOf("SizeDistribHigh2");              
        jcolX[j++] = listHeader.indexOf("SerumConcentration");             
        jcolX[j++] = listHeader.indexOf("AntibioticConcentration");          
        jcolX[j++] = listHeader.indexOf("DOMConcentration");               
        jcolX[j++] = listHeader.indexOf("SalinityValue");            
        jcolX[j++] = listHeader.indexOf("pHAvg");        	             
        jcolX[j++] = listHeader.indexOf("pHLow");         	         
        jcolX[j++] = listHeader.indexOf("pHHigh");        	          
        jcolX[j++] = listHeader.indexOf("MediumTemp");        	        
        jcolX[j++] = listHeader.indexOf("TimeValue");        	               
        jcolX[j++] = listHeader.indexOf("ParticleConcentration");           
        jcolX[j++] = listHeader.indexOf("ParticleExposDuration");             
        jcolX[j++] = listHeader.indexOf("UVADose");        	                
        jcolX[j++] = listHeader.indexOf("UVAExposDuration");     
               
        /* Store indices and minimum/maximum random values of Y-matrix columns */
        jcolY[jy++]   = listHeader.indexOf("ViabilityAvg");                
        jcolY[jy++]   = listHeader.indexOf("LC50");
        
           
		/* Loop over the rows of the data */
		for (int i = 0; i < Xorig.rows; i++)
		{
			nextLine = rows.get(i);	  // Get the ith row of the data
			for (int k = 0; k < xcolumns; k++)   // Loop over the columns
			{
				int jcol = jcolX[k];
				Xorig.put(i,k,Double.parseDouble(nextLine[jcol]));
			}
			
			for (int k = 0; k < ycolumns; k++)
			{
				int jcol = jcolY[k];
				Yorig.put(i,k,Double.parseDouble(nextLine[jcol]));
			}	
				
		}
	}
	
	/**
	 * This method converts null to random numbers and string numerical data to double.
	 * @param strValue
	 * @param minValue
	 * @param maxValue
	 * @return
	 * @author Wilson Melendez
	 */
	public static double getValue(String strValue, double minValue, double maxValue)
	{
		double value;
		
		if (strValue.equals("null"))
		{
			value = minValue + Math.random() * (maxValue - minValue);
		}
		else
		{
			value = Double.parseDouble(strValue);
		}
		
		return value;	
	}

	/**
	 * This method converts the elements of a matrix into Z-scores.
	 * A z-score (also known as standard score) indicates how many 
	 * standard deviations an element is from the mean.  It is 
	 * calculated as Z-score = (Xi - Xavg) / sigma.
	 * Xi is the ith element of a given column; Xavg is the average 
	 * of the elements in a column in the X or Y matrix; sigma is 
	 * the standard deviation of the values in a column in the X or 
	 * Y matrix. Averages and standard deviations are calculated for 
	 * each column of the X and Y matrix.
	 * @author Wilson Melendez
	 * @param A
	 * @return
	 */
	public static void normalizeMatrix(DoubleMatrix A, DoubleMatrix meanA, DoubleMatrix stdA)
	{
		DescriptiveStatistics stats = new DescriptiveStatistics();
		for (int j = 0; j < A.columns; j++)
		{
			for (int i = 0; i < A.rows; i++)
			{
				stats.addValue(A.get(i, j));
			}
			
			double mean = stats.getMean();
			double std = stats.getStandardDeviation();
			meanA.put(j, mean);
			stdA.put(j, std);
			
			for (int i = 0; i < A.rows; i++)
			{
				double zScore = (std > 0.0) ? (A.get(i, j) - mean) / std : 0.0;
				A.put(i, j, zScore);
			}
			
			stats.clear();
		}
	}
	
	/**
	 * This method converts the elements of a matrix into Z-scores.
	 * A z-score (also known as standard score) indicates how many 
	 * standard deviations an element is from the mean.  It is 
	 * calculated as Z-score = (Xi - Xavg) / sigma.
	 * Xi is the ith element of a given column; Xavg is the average 
	 * of the elements in a column in the X or Y matrix; sigma is 
	 * the standard deviation of the values in a column in the X or 
	 * Y matrix. Averages and standard deviations are calculated for 
	 * each column of the X and Y matrix.
	 * @author Wilson Melendez
	 * @param A
	 * @return
	 */
	public static void normalizeVector(DoubleMatrix A, DoubleMatrix meanA, DoubleMatrix stdA)
	{
		DescriptiveStatistics stats = new DescriptiveStatistics();
	    for (int i = 0; i < A.rows; i++)
	    {
			stats.addValue(A.get(i,0));
		}
			
		double mean = stats.getMean();
		double std = stats.getStandardDeviation();
		meanA.put(0, mean);
		stdA.put(0, std);
			
		for (int i = 0; i < A.rows; i++)
		{
			double zScore = (std > 0.0) ? (A.get(i,0) - mean) / std : 0.0;
			A.put(i, 0, zScore);
		}
			
		stats.clear();
	}
	
	/**
	 * This method calculates the sum of the squares of a matrix.
	 * The calculation is performed by squaring each element of the 
	 * matrix and adding all squared values up. 
	 * @param A
	 * @return
	 * @author Wilson Melendez
	 */
	public static double sumOfSquares(DoubleMatrix A)
	{
		double sum2 = 0.0;
		
		for (int j = 0; j < A.columns; j++)
		{
			for (int i = 0; i < A.rows; i++)
			{
				sum2 = sum2 + Math.pow(A.get(i, j), 2);
			}
		}
		
		return sum2;
	}
	
	/**
	 * This method performs the PLS regression algorithm.
	 * @param Xorig
	 * @param Yorig
	 * @return
	 * @author Wilson Melendez
	 * @throws IOException 
	 */
	public static DoubleMatrix performPLSR(DoubleMatrix Xorig, DoubleMatrix Yorig)
	{	
		Pmatrix = null;
		Umatrix = null;
		Cmatrix = null;
		Tmatrix = null;
		Wmatrix = null;
		Bmatrix = null;
		
		/* Normalize the Xmatrix by turning each element of the 
		 * matrix into Z-scores. 
		 */
		DoubleMatrix X0 = new DoubleMatrix(Xorig.rows,Xorig.columns);
		X0.copy(Xorig);
		DoubleMatrix meanX = new DoubleMatrix(Xorig.columns);
		DoubleMatrix stdX = new DoubleMatrix(Xorig.columns);
        normalizeMatrix(X0, meanX, stdX);
        
        /* Normalize the Ymatrix by turning each element of the 
		 * matrix into Z-scores.
		 */        
        DoubleMatrix Y0 = new DoubleMatrix(Yorig.rows,Yorig.columns);
        Y0.copy(Yorig);	
		DoubleMatrix meanY = new DoubleMatrix(Yorig.columns);
		DoubleMatrix stdY = new DoubleMatrix(Yorig.columns);		
	    normalizeMatrix(Y0, meanY, stdY);
        
		/* Calculate the sum of squares for Y0. */
		ssy = sumOfSquares(Y0);
		
		/* Declare a list of double values that will hold the b
		 * coefficients.  The b values are used to predict Y. 
		 */
		List<Double> bValues = new ArrayList<Double>();
				
		/* Perform the NIPALS algorithm. NIPALS is a PLS regression 
		 * algorithm. 
		 */
		performNIPALS(X0, Y0, bValues);
		
		DoubleMatrix Bpls = null;
		DoubleMatrix BplsStar = null;
		
		/* Build the B matrix. B is a diagonal matrix that enters in the 
		 * calculation of the BPLS matrix. The diagonal elements 
		 * represent the regression weights. 
		 */
		DoubleMatrix mB = new DoubleMatrix(bValues.size());
		for (int i = 0; i < mB.length; i++)
		{
			mB.put(i,bValues.get(i));
		}		
		Bmatrix = DoubleMatrix.diag(mB);
		
		/* Calculate the Moore-Penrose pseudo-inverse of the transpose 
		 * of the P matrix. */
		DoubleMatrix ptInv = Solve.pinv(Pmatrix.transpose());
		
		/* Calculate the PLS regression weights. This is also known as 
		 * the BPLS vector. 
		 */
		Bpls = ptInv.mmul(Bmatrix).mmul(Cmatrix.transpose());
        
		/* Re-introduce units into the BPLS vector. */
		BplsStar = new DoubleMatrix(Bpls.rows + 1, Bpls.columns);
		
		for (int j = 0; j < Bpls.columns; j++)
		{
			for (int i = 0; i < Bpls.rows; i++)
			{
				double bUnits = 0.0;
				if (stdX.get(i) > 0.0)
				{
					bUnits = stdY.get(j) * Bpls.get(i,j) / stdX.get(i);
				}
				BplsStar.put(i+1, j, bUnits);
			}
		}
		
		
		/* Calculate intercepts. */
		for (int j = 0; j < Bpls.columns; j++)
		{
			double sum = 0.0;
			for (int i = 0; i < Bpls.rows; i++)
			{
				sum = sum + meanX.get(i) * BplsStar.get(i+1,j);
			}
			double intercept = meanY.get(j) - sum;
			BplsStar.put(0, j, intercept);
		}
			
		return BplsStar;				
	}
	
	/**
	 * This method is used to calculate the predicted Y values.
	 * @param Xorig
	 * @param Bstar
	 * @return
	 * @author Wilson Melendez
	 */
	public static DoubleMatrix predictResults(DoubleMatrix Xorig, DoubleMatrix Bstar)
	{
		/* Augment the Xmatrix by adding a first column of ones. */
		DoubleMatrix Ones = DoubleMatrix.ones(Xorig.rows);
		DoubleMatrix Xaugmented = DoubleMatrix.concatHorizontally(Ones, Xorig);
		
		/* Predict the Y values */
		DoubleMatrix Ypred = Xaugmented.mmul(Bstar);
		return Ypred;
	}
	
	/**
	 * This method writes BPLS* to a CSV file.
	 * @param Bstar
	 * @author Wilson Melendez
	 */
	public static void writeBplsStarToCsv(DoubleMatrix Bstar)
	{
		String[] entries = new String[Bstar.rows];
		String filename = System.getProperty("user.dir") + "\\nanoQSAR_BPLS.csv";
		
		try
		{	
			FileWriter file = new FileWriter(filename); 
					
			/* Create an instance of the CSVWriter class and specify the comma as the 
			 * default separator. Default quote character is double quote. */ 
			CSVWriter csvOutput = new CSVWriter(file,CSVWriter.DEFAULT_SEPARATOR);
					
			/* Write header line to CSV file. */
			//csvOutput.writeNext(headerLine);   
					
			for (int i = 0; i < Bstar.rows; i++)
			{
				entries[i] = String.valueOf(Bstar.get(i));
			}
						
			/* Write row of data to output using the writeNext method. */
			csvOutput.writeNext(entries);   
			
			/* Close the writer. */
			   csvOutput.close();   
		}
		catch(IOException ex)
		{
			lOGGER.severe("FileWriter for " + filename + " could not be constructed." + ex);	
		}
	}
	
	/**
	 * This  method calculates the rank of a matrix using the results
	 * of singular value decomposition.
	 * @param A
	 * @param svdOfA
	 * @return
	 * @author Wilson Melendez
	 */
	public static double rank(DoubleMatrix A, DoubleMatrix svdOfA)
	{
		double maxSizeA = Math.max(A.rows, A.columns);
		double eps = Math.pow(2.0, -52.0);
		double maxS = svdOfA.max();
		double tol = maxSizeA * eps * maxS;
		double r = svdOfA.gt(tol).sum();
		return r;
	}
	
	/**
	 * This method performs a version of the PLSR algorithm known as
	 * NIPALS.
	 * @author Wilson Melendez
	 */
	public static void performNIPALS(DoubleMatrix X0, DoubleMatrix Y0, List<Double> bValues)
	{
		int rowsMatrix = X0.rows;
		int colsMatrix = X0.columns;
		DoubleMatrix X = new DoubleMatrix();
		DoubleMatrix Y = new DoubleMatrix();
		DoubleMatrix u = new DoubleMatrix(rowsMatrix);
		DoubleMatrix u0 = new DoubleMatrix(rowsMatrix);
		DoubleMatrix u1 = new DoubleMatrix(rowsMatrix);
		DoubleMatrix udiff = new DoubleMatrix(rowsMatrix);
		DoubleMatrix w = new DoubleMatrix(colsMatrix);		
		DoubleMatrix t = new DoubleMatrix(rowsMatrix);
		DoubleMatrix t1 = new DoubleMatrix(rowsMatrix);		
		DoubleMatrix c;		
		DoubleMatrix Xtu;
		DoubleMatrix Xw;
		DoubleMatrix Ytt;
		DoubleMatrix tdiff;
		DoubleMatrix p;		
		DoubleMatrix tpt, tct;	
		
		boolean IsFirstDeflation;
		double normX, normY0, normY1, deltaU, deltaY;
		int numDeflations = 0;
		X = X0;
		Y = Y0;
		
		/* Calculate the rank of X */
		DoubleMatrix[] fullSVD = Singular.fullSVD(X0);
		DoubleMatrix singularValuesDM = fullSVD[1];
		double rank = rank(X0, singularValuesDM);
		
		/* Set the maximum number of latent variables/structures 
		 * to the rank of the X matrix.
		 */
		int maxNumLS = (int) rank;
		
		/* Initialize parameters */
		IsFirstDeflation = true;
		normY0 = Y.norm2();
		
		do
		{		
			/* Initialize u with one of the Y columns. */
			u0 = Y.getColumn(0);
						
			numDeflations = numDeflations + 1;
			if (numDeflations > maxNumLS) break; 
			
			do
			{
				/* Calculate w and normalize the result. */
				Xtu = X.transpose().mmul(u0);				
				w = Xtu.div(Xtu.norm2());
				
				/* Calculate X-scores, t, and normalize the result. */
				Xw = X.mmul(w);				
				t1 = Xw.div(Xw.norm2());
				
				/* Calculate Y-weights, c, and normalize the result. */
				Ytt = Y.transpose().mmul(t1);
				c = Ytt.div(Ytt.norm2());
				
				/* Calculate an updated set of Y-scores, u */
				u1 = Y.mmul(c);
					
				/* Calculate the change in u and check whether it is
				 * less than a small number.
				 */
				udiff = u1.sub(u0);
				deltaU = udiff.norm2();
				u0 = u1;
				
			} while (deltaU > EPSILON);
			
			u = u1;
			t = t1;
			
			double b = t.transpose().dot(u);
			bValues.add(b);
			
			/* Compute the factor loadings for X. */
			p = X.transpose().mmul(t);
			
			/* Deflate the X and Y matrices. */
			tpt = t.mmul(p.transpose());
			tct = t.mmul(c.transpose());
			X = X.sub(tpt);
			Y = Y.sub(tct.muli(b));
			
			/* Store t, u, p, c, and w in their corresponding matrices. */
			if (IsFirstDeflation)
			{
				Tmatrix = t;
				Umatrix = u;
				Pmatrix = p;
				Cmatrix = c;				
				Wmatrix = w;
				IsFirstDeflation = false;
			}
			else
			{
				Tmatrix = DoubleMatrix.concatHorizontally(Tmatrix,t);
				Umatrix = DoubleMatrix.concatHorizontally(Umatrix,u);
				Pmatrix = DoubleMatrix.concatHorizontally(Pmatrix,p);
				Cmatrix = DoubleMatrix.concatHorizontally(Cmatrix,c);
				Wmatrix = DoubleMatrix.concatHorizontally(Wmatrix,w);				
			}
			
			/* Calculate the norms of X and Y, and the change in Y. */
			normX = X.norm2();
			normY1 = Y.norm2();
			deltaY = Math.abs(normY1 - normY0);
			normY0 = normY1;
			
		} while (normX > EPSILON_DEFLATION && deltaY > EPSILON);		
	
	}
	
	/**
	 * This method splits the original data into 5 sets.  The sets will be used
	 * to cross-validate the original data using the 5-fold cross-validation method.
	 * @author Wilson Melendez
	 */
	public static void splitDataIntoSets(DoubleMatrix X0, DoubleMatrix Y0, List<Integer> list)
	{
		for (int i = 0; i < numDataSets; i++)
		{
			XmatrixSet[i] = null;
			YmatrixSet[i] = null;
		}
		
		List<List<Integer>> indicesSets = new ArrayList<List<Integer>>();
		
		int numRows = X0.rows;
		int numCols = X0.columns;
		int numYrows = Y0.rows;
		int numYcols = Y0.columns;
		
		/* Generate list of indices. */
		for (int i = 0; i < numRows; i++)
		{
			list.add(i);
		}
		
		/* Randomly shuffle the list of indices. */
		Collections.shuffle(list);
				
		/* Set the number of elements for each fold. */
		for (int i = 0; i < numDataSets; i++)
		{
			numSetRows[i] = numRows / numDataSets;
		}
		
		/* In case we have a non-zero remainder, assign one more element to some of 
		 * the groups. */
		int remainder = numRows % numDataSets;
		if (remainder > 0)
		{
			for (int i = 0; i < remainder; i++)
			{
				numSetRows[i] = numSetRows[i] + 1;
			}
		}
		
		/* Store indices in folds. */
		int jmin = 0;
		int jmax = 0;
		for (int i = 0; i < numDataSets; i++)
		{
			indicesSets.add(new ArrayList<Integer>());
			jmax = jmax + numSetRows[i];
			for (int j = jmin; j < jmax; j++)
			{
				indicesSets.get(i).add(list.get(j));
			}
			jmin = jmin + numSetRows[i];
		}
		
		/* Split the original data in 5 sets and store them in matrices. */
		for (int k = 0; k < numDataSets; k++)
		{
			XmatrixSet[k] = new DoubleMatrix(numSetRows[k], numCols);
			YmatrixSet[k] = new DoubleMatrix(numSetRows[k], numYcols);
			for (int i = 0; i < numSetRows[k]; i++)
			{
				int rowI = indicesSets.get(k).get(i);
				for (int j = 0; j < numCols; j++)
				{
					XmatrixSet[k].put(i, j, X0.get(rowI,j));					
				}
				
				for (int j = 0; j < numYcols; j++)
				{
					YmatrixSet[k].put(i, j, Y0.get(rowI,j));
				}			    												
			}
		}
	}
	
	/**
	 * This method implements the 5-fold cross-validation algorithm.
	 * @author Wilson Melendez
	 */
	public static DoubleMatrix performFiveFoldCrossValidation()
	{
		DoubleMatrix[] Yhat = new DoubleMatrix[numDataSets];
		DoubleMatrix Ytilde = new DoubleMatrix();
		
		for (int ifold = 0; ifold < numDataSets; ifold++)
		{
			/* Leave one set out and use remaining sets to build model. */
			DoubleMatrix Xtraining = null;
			DoubleMatrix Ytraining = null;
			Yhat[ifold] = null;
			Integer[] ind = new Integer[numDataSets-1];
			
			/* Store the indices of the sets that are going to be used for the model and
			 * exclude the one that is going to be predicted. */
			int j = 0;
			for (int i = 0; i < numDataSets; i++)
			{
				if (i != ifold)
				{
					ind[j] = i;	
					j++;
				}				
			}
						
			/* Build the X and Y matrices for the model. */
			Xtraining = DoubleMatrix.concatVertically(XmatrixSet[ind[0]], XmatrixSet[ind[1]]);
			Ytraining = DoubleMatrix.concatVertically(YmatrixSet[ind[0]], YmatrixSet[ind[1]]);
			for (int i = 2; i < ind.length; i++)
			{
				Xtraining = DoubleMatrix.concatVertically(Xtraining, XmatrixSet[ind[i]]);
				Ytraining = DoubleMatrix.concatVertically(Ytraining, YmatrixSet[ind[i]]);
			}
			
			/* Perform the PLS regression analysis. */
			DoubleMatrix BplsS = performPLSR(Xtraining, Ytraining);
			
			/* Predict the Y values that were left out. */
			Yhat[ifold] = predictResults(XmatrixSet[ifold], BplsS);			
			
			/* Build the predicted Y's into a single matrix. */
			if (ifold == 0) 
			{
				Ytilde.copy(Yhat[ifold]);							
			}
			else
			{
				Ytilde = DoubleMatrix.concatVertically(Ytilde, Yhat[ifold]);	
			}
		}
		
		return Ytilde;
	}
	
	/**
	 * This method prints a matrix to standard output.
	 * @param A
	 * @author Wilson Melendez
	 */
	public static void showM(DoubleMatrix A) 
	 {
		 for (int i = 0; i < A.rows; i++) 
		 {
			 for (int j = 0; j < A.columns; j++) 
			 {
				 System.out.printf("%11.6f ", A.get(i,j)); 
			 }
	         System.out.println();
		 }	         
	 }
	  
	 /**
	  * Print single-column matrix to standard output.
	  * @author Wilson Melendez
	  */
	 public static void showV(DoubleMatrix A) 
	 {
		 for (int i = 0; i < A.rows; i++) 
		 {			 
			System.out.printf("%11.6f ", Ymatrix.get(i)); 	
			System.out.println();
		 }
	 }
}
