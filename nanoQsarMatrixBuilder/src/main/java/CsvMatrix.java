import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.jblas.DoubleMatrix;

import com.opencsv.CSVReader;


/**
 * @author Wilson Melendez
 *
 */
public class CsvMatrix 
{
	private static int xcolumns = 67;
	private static int ycolumns = 2;
	private static int rowsSize;
	private static List<String[]> rows;
	private static String[] header;
	private static DoubleMatrix Xmatrix;
	private static DoubleMatrix Ymatrix;
	
	/* Need this line to allow logging of error messages */
	private final static Logger lOGGER = Logger.getLogger( LoggerInfo.class.getName() );
	
	public static List<String[]> getRows() {
		return rows;
	}

	public static void setRows(List<String[]> rows) {
		CsvMatrix.rows = rows;
	}

	
	public static String[] getHeader() {
		return header;
	}

	public static void setHeader(String[] header) {
		CsvMatrix.header = header;
	}

	/**
	 * @return the xmatrix
	 */
	public static DoubleMatrix getXmatrix() {
		return Xmatrix;
	}

	/**
	 * @return the ymatrix
	 */
	public static DoubleMatrix getYmatrix() {
		return Ymatrix;
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
			System.out.println("CSV file was not found.");
			lOGGER.log(Level.SEVERE, "CSV file was not found.");	
			throw ex;
		}
		
		// boolean keepCR = false;
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
		}
		catch(IOException ex)
		{
			System.out.println("Attempt to close CSV reader failed.");
			lOGGER.log(Level.SEVERE, "Attempt to close CSV reader failed.");	
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
			buildMatricesContainingNulls();  // Handle case with null values.
		}
		else
		{
			buildMatricesWithoutNulls();  // Handle case with no null values.
		}		
	}
	
	/**
	 * This routine builds the X and Y matrices for the case of the original data 
	 * containing null values.
	 * @author Wilson Melendez
	 */
	public static void buildMatricesContainingNulls()
	{
		String[] nextLine;
		int[] jcolX = new int[xcolumns];  // Positional indices of X-matrix columns in CSV file.
		int[] jcolY = new int[ycolumns];  // Positional indices of Y-matrix columns in CSV file.
		double[] minValueX = new double[xcolumns];  // Minimum random values of columns in X matrix
		double[] maxValueX = new double[xcolumns];  // Maximum random values of columns in X matrix
		double[] minValueY = new double[ycolumns];  // Minimum random values of columns in Y matrix
		double[] maxValueY = new double[ycolumns];  // Maximum random values of columns in Y matrix
		rowsSize = rows.size();  // Number of rows in array list.
		Xmatrix = new DoubleMatrix(rowsSize,xcolumns);   // X matrix
		Ymatrix = new DoubleMatrix(rowsSize,ycolumns);   // Y matrix
		
		int j = 0, jy = 0;		
		List<String> listHeader = Arrays.asList(header);
		
		/* Store indices and minimum/maximum random values of X-matrix columns */
		jcolX[j] = listHeader.indexOf("CoatingAmount");	
		minValueX[j] = 0.0;  maxValueX[j] = 100.0; j++;
	    jcolX[j] = listHeader.indexOf("Purity");	
	    minValueX[j] = 0.0;  maxValueX[j] = 100.0; j++;
		jcolX[j] = listHeader.indexOf("ContamAl"); 
		minValueX[j] = 0.0;  maxValueX[j] = 1500.0; j++;
		jcolX[j] = listHeader.indexOf("ContamAs"); 
		minValueX[j] = 0.0;  maxValueX[j] = 1500.0; j++;
		jcolX[j] = listHeader.indexOf("ContamBe");	
		minValueX[j] = 0.0;  maxValueX[j] = 1500.0; j++;
        jcolX[j] = listHeader.indexOf("ContamCa"); 
        minValueX[j] = 0.0;  maxValueX[j] = 1500.0; j++;
        jcolX[j] = listHeader.indexOf("ContamCo"); 
        minValueX[j] = 0.0;  maxValueX[j] = 1500.0; j++;
        jcolX[j] = listHeader.indexOf("ContamCr"); 
        minValueX[j] = 0.0;  maxValueX[j] = 1500.0; j++;
        jcolX[j] = listHeader.indexOf("ContamFe"); 
        minValueX[j] = 0.0;  maxValueX[j] = 1500.0; j++;
        jcolX[j] = listHeader.indexOf("ContamK");  
        minValueX[j] = 0.0;  maxValueX[j] = 1500.0; j++;
        jcolX[j] = listHeader.indexOf("ContamMg");     
        minValueX[j] = 0.0;  maxValueX[j] = 1500.0; j++;
        jcolX[j] = listHeader.indexOf("ContamNa"); 
        minValueX[j] = 0.0;  maxValueX[j] = 1500.0; j++;
        jcolX[j] = listHeader.indexOf("ContamP");  
        minValueX[j] = 0.0;  maxValueX[j] = 1500.0; j++;
        jcolX[j] = listHeader.indexOf("ContamPb"); 
        minValueX[j] = 0.0;  maxValueX[j] = 1500.0; j++;
        jcolX[j] = listHeader.indexOf("ContamSb"); 
        minValueX[j] = 0.0;  maxValueX[j] = 1500.0; j++;
        jcolX[j] = listHeader.indexOf("ContamSe"); 
        minValueX[j] = 0.0;  maxValueX[j] = 1500.0; j++;
        jcolX[j] = listHeader.indexOf("ContamSiO2");
        minValueX[j] = 0.0;  maxValueX[j] = 1500.0; j++;
        jcolX[j] = listHeader.indexOf("ContamSn"); 
        minValueX[j] = 0.0;  maxValueX[j] = 1500.0; j++;
        jcolX[j] = listHeader.indexOf("ContamTl"); 
        minValueX[j] = 0.0;  maxValueX[j] = 1500.0; j++;
        jcolX[j] = listHeader.indexOf("ContamV");  
        minValueX[j] = 0.0;  maxValueX[j] = 1500.0; j++;
        jcolX[j] = listHeader.indexOf("ParticleOuterDiamAvg");  
        minValueX[j] = 100.0;  maxValueX[j] = 250; j++;
        jcolX[j] = listHeader.indexOf("ParticleOuterDiamLow"); 
        minValueX[j] = 0.0;  maxValueX[j] = 100.0; j++;
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
        minValueX[j] = 0.0;  maxValueX[j] = 50; j++;
        jcolX[j] = listHeader.indexOf("ParticleLengthHigh"); 
        minValueX[j] = 100.0;  maxValueX[j] = 150; j++;
        jcolX[j] = listHeader.indexOf("ParticleThicknessAvg"); 
        minValueX[j] = 50.0;  maxValueX[j] = 100.0; j++;
        jcolX[j] = listHeader.indexOf("ParticleThicknessLow");  
        minValueX[j] = 0.0;  maxValueX[j] = 50; j++;
        jcolX[j] = listHeader.indexOf("ParticleThicknessHigh");  
        minValueX[j] = 100.0;  maxValueX[j] = 150; j++;
        jcolX[j] = listHeader.indexOf("SurfaceAreaAvg");       
        minValueX[j] = 150.0;  maxValueX[j] = 300; j++;
        jcolX[j] = listHeader.indexOf("SurfaceAreaLow");  
        minValueX[j] = 0.0;  maxValueX[j] = 150; j++;
        jcolX[j] = listHeader.indexOf("SurfaceAreaHigh");  
        minValueX[j] = 300.0;  maxValueX[j] = 450; j++;
        jcolX[j] = listHeader.indexOf("MC_TimeValue");    
        minValueX[j] = 0.0;  maxValueX[j] = 240; j++;
        jcolX[j] = listHeader.indexOf("MC_ParticleConcentration"); 
        minValueX[j] = 0.0;  maxValueX[j] = 100.0; j++;
        jcolX[j] = listHeader.indexOf("MC_SerumConcentration");    
        minValueX[j] = 0.0;  maxValueX[j] = 100.0; j++;
        jcolX[j] = listHeader.indexOf("MC_AntibioticConcentration");   
        minValueX[j] = 0.0;  maxValueX[j] = 100.0; j++;
        jcolX[j] = listHeader.indexOf("MC_DOMConcentration");       
        minValueX[j] = 0.0;  maxValueX[j] = 20.0; j++;
        jcolX[j] = listHeader.indexOf("MC_SalinityValue");        	
        minValueX[j] = 0.0;  maxValueX[j] = 35.0; j++;
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
        minValueX[j] = 0.0;  maxValueX[j] = -30.0; j++;
        jcolX[j] = listHeader.indexOf("ZetaPotentialHigh");    
        minValueX[j] = -100.0;  maxValueX[j] = -150; j++;
        jcolX[j] = listHeader.indexOf("SizeDistribAvg");        
        minValueX[j] = 1000.0;  maxValueX[j] = 2000; j++;
        jcolX[j] = listHeader.indexOf("SizeDistribLow");        
        minValueX[j] = 0.0;  maxValueX[j] = 1000; j++;
        jcolX[j] = listHeader.indexOf("SizeDistribHigh");        	
        minValueX[j] = 2000.0;  maxValueX[j] = 3000; j++;
        jcolX[j] = listHeader.indexOf("SizeDistribAvg2");        	
        minValueX[j] = 1000.0;  maxValueX[j] = 2000; j++;
        jcolX[j] = listHeader.indexOf("SizeDistribLow2");        
        minValueX[j] = 0.0;  maxValueX[j] = 1000; j++;
        jcolX[j] = listHeader.indexOf("SizeDistribHigh2");       
        minValueX[j] = 2000.0;  maxValueX[j] = 3000; j++;
        jcolX[j] = listHeader.indexOf("SerumConcentration");      
        minValueX[j] = 0.0;  maxValueX[j] = 100.0; j++;
        jcolX[j] = listHeader.indexOf("AntibioticConcentration");   
        minValueX[j] = 0.0;  maxValueX[j] = 100.0; j++;
        jcolX[j] = listHeader.indexOf("DOMConcentration");        
        minValueX[j] = 0.0;  maxValueX[j] = 20.0; j++;
        jcolX[j] = listHeader.indexOf("SalinityValue");      
        minValueX[j] = 0.0;  maxValueX[j] = 35.0; j++;
        jcolX[j] = listHeader.indexOf("pHAvg");        	      
        minValueX[j] = 4.0;  maxValueX[j] = 10.0; j++;
        jcolX[j] = listHeader.indexOf("pHLow");         	  
        minValueX[j] = 0.0;  maxValueX[j] = 4.0; j++;
        jcolX[j] = listHeader.indexOf("pHHigh");        	   
        minValueX[j] = 10.0;  maxValueX[j] = 14.0; j++;
        jcolX[j] = listHeader.indexOf("MediumTemp");        	 
        minValueX[j] = 10.0;  maxValueX[j] = 40.0; j++;
        jcolX[j] = listHeader.indexOf("TimeValue");        	         
        minValueX[j] = 48.0;  maxValueX[j] = 48.0; j++;
        jcolX[j] = listHeader.indexOf("ParticleConcentration");     
        minValueX[j] = 0.0;  maxValueX[j] = 100.0; j++;
        jcolX[j] = listHeader.indexOf("ParticleExposDuration");       
        minValueX[j] = 48.0;  maxValueX[j] = 48.0; j++;
        jcolX[j] = listHeader.indexOf("UVADose");        	          
        minValueX[j] = 0.0;  maxValueX[j] = 10.0; j++;
        jcolX[j] = listHeader.indexOf("UVAExposDuration");     
        minValueX[j] = 0.0;  maxValueX[j] = 2.0; j++;
        
        /* Store indices and minimum/maximum random values of Y-matrix columns */
        jcolY[jy]   = listHeader.indexOf("ViabilityAvg");        
        minValueY[jy] = 0.0;  maxValueY[jy] = 100.0; jy++;
        jcolY[jy]   = listHeader.indexOf("LC50");
        minValueY[jy] = 0.0;  maxValueY[jy] = 100.0;
           
		/* Loop over the rows of the data */
		for (int i = 0; i < rowsSize; i++)
		{
			nextLine = rows.get(i);	  // Get the ith row of the data
			for (int k = 0; k < xcolumns; k++)   // Loop over the columns
			{
				int jcol = jcolX[k];
				Xmatrix.put(i,k,getValue(nextLine[jcol], minValueX[k], maxValueX[k]));
			}
			
			for (int k = 0; k < ycolumns; k++)
			{
				int jcol = jcolY[k];
				Ymatrix.put(i,k,getValue(nextLine[jcol], minValueY[k], maxValueY[k]));
			}	
				
		}
	}
	
	/**
	 * This method builds the X and Y matrices for the case of data without null values.
	 * @author Wilson Melendez
	 */
	public static void buildMatricesWithoutNulls()
	{
		String[] nextLine;
		int[] jcolX = new int[xcolumns];  // Positional indices of X-matrix columns in CSV file.
		int[] jcolY = new int[ycolumns];  // Positional indices of Y-matrix columns in CSV file.
		rowsSize = rows.size();  // Number of rows in array list.
		Xmatrix = new DoubleMatrix(rowsSize,xcolumns);   // X matrix
		Ymatrix = new DoubleMatrix(rowsSize,ycolumns);   // Y matrix
		
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
		for (int i = 0; i < rowsSize; i++)
		{
			nextLine = rows.get(i);	  // Get the ith row of the data
			for (int k = 0; k < xcolumns; k++)   // Loop over the columns
			{
				int jcol = jcolX[k];
				Xmatrix.put(i,k,Double.parseDouble(nextLine[jcol]));
			}
			
			for (int k = 0; k < ycolumns; k++)
			{
				int jcol = jcolY[k];
				Ymatrix.put(i,k,Double.parseDouble(nextLine[jcol]));
			}	
				
		}
	}
	
	/**
	 * This method converts null to random numbers and string numerical data to double.
	 * @param strValue
	 * @param minValue
	 * @param maxValue
	 * @return
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
	 * Z-score = (Xi - Xavg) / (standard deviation).
	 * @author Wilson Melendez
	 * @param A
	 * @return
	 */
	public static DoubleMatrix standardizeMatrix(DoubleMatrix A)
	{
		DoubleMatrix B = A;
		DescriptiveStatistics stats = new DescriptiveStatistics();
		for (int j = 0; j < B.columns; j++)
		{
			for (int i = 0; i < B.rows; i++)
			{
				stats.addValue(B.get(i, j));
			}
			
			double mean = stats.getMean();
			double std = stats.getStandardDeviation();
			
			for (int i = 0; i < B.rows; i++)
			{
				double zScore = (std > 0.0) ? (B.get(i, j) - mean) / std : 0.0;
				B.put(i, j, zScore);
			}
			
			stats.clear();
		}
		return B;
	}
	
	/**
	 * This method performs the PLSR algorithm.
	 * @author Wilson Melendez
	 */
	public static void performPlsRegression()
	{
		/* Standardize the X and Y matrices */
		DoubleMatrix X = standardizeMatrix(Xmatrix);
		DoubleMatrix Y = standardizeMatrix(Ymatrix);
		
		/* Initialize the u vector */
		DoubleMatrix u = Y.getColumn(0);
		
		/* Calculate w */
		DoubleMatrix Xt = X.transpose();
		DoubleMatrix w = Xt.mmul(u).div(u.dot(u));
		
		/* Calculate X-scores, t */
		DoubleMatrix t = X.mmul(w);
		
		/* Calculated Y-weights, c */
		DoubleMatrix Yt = Y.transpose();
		DoubleMatrix c = Yt.mmul(t).div(t.dot(t));
		
		/* Calculate an updated set of Y-scores, u */
		u = Y.mmul(c).div(c.dot(c));
	}
	
	/**
	  * Print X matrix to standard output.
	  */
	public static void showX() 
	 {
		 for (int i = 0; i < rowsSize; i++) 
		 {
			 for (int j = 0; j < xcolumns; j++) 
			 {
				 System.out.printf("%9.4f ", Xmatrix.get(i,j)); 
			 }
	         System.out.println();
		 }	         
	 }
	  
	 /**
	  * Print Y matrix to standard output.
	  */
	 public static void showY() 
	 {
		 for (int i = 0; i < rowsSize; i++) 
		 {
			 for (int j = 0; j < ycolumns; j++) 
			 {
				 System.out.printf("%9.4f ", Ymatrix.get(i,j)); 
			 }
			 System.out.println();
		 }
	 }
}
