package pls_analysis;

import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.jblas.DoubleMatrix;
import org.jblas.Singular;
import org.jblas.Solve;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import nanoQSAR.NanoMaterial;
import nanoQSAR.NanoMaterials;

/**
 * @author Wilson Melendez
 *
 */
public class CsvMatrix
{
	private static NanoMaterials nanoMaterials;
	private static int xcolumns;
	private static int ycolumns;
	private static int rowsSize;
	private static int numDataSets = 5;
	private static Integer[] numSetRows = new Integer[numDataSets];
	
	private static List<String[]> rows;
	private static String[] header;
	private static Integer[] jcolX; // Positional indices of X-matrix columns in CSV file. 
	private static Integer[] jcolY; // Positional indices of Y-matrix columns in CSV file.
	private static int[] jcX; // Positional indices of X-matrix columns. 
	private static int[] jcY; // Positional indices of Y-matrix columns.
	private static Double[] minValueX;
	private static Double[] minValueY;
	private static Double[] maxValueX;
	private static Double[] maxValueY;
	private static DoubleMatrix xMatrix;
	private static DoubleMatrix yMatrix;
	private static DoubleMatrix Xmatrix;
	private static DoubleMatrix Ymatrix;
	private DoubleMatrix Umatrix = null;
	private DoubleMatrix Tmatrix = null;
	private DoubleMatrix Wmatrix = null;		
	private DoubleMatrix Cmatrix = null;
	private DoubleMatrix Pmatrix = null;
	private DoubleMatrix Bmatrix = null;
	private DoubleMatrix meanX = null;
	private DoubleMatrix stdX = null;
	private DoubleMatrix meanY = null;
	private DoubleMatrix stdY = null;
	private DoubleMatrix Xtraining = null;
	private DoubleMatrix Ytraining = null;
	private DoubleMatrix Xtesting = null;
	private DoubleMatrix Ytesting = null;
	private double[] q2avg = null;
	private int numOfDeflations = 0;
	private int numDeflationsAvg = 0;
	
	private static DoubleMatrix[] XmatrixSet = new DoubleMatrix[numDataSets];
	private static DoubleMatrix[] YmatrixSet = new DoubleMatrix[numDataSets];
	
	private static double ssx;
	private static double ssy;
	
	private static double EPSILON = 1.0e-12;
	private static double EPSILON_DEFLATION = 1.0e-12;
	
	/* Need this line to allow logging of error messages */
	private final static Logger LOGGER = Logger.getLogger("nanoQSAR");
	static String logFilename = System.getProperty("user.dir") + "\\nanoQSAR.log";
	
	/**
	 * @author Paul Harten
	 * @param filename
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public CsvMatrix() throws Exception {
		super();
	}
	
	public CsvMatrix(NanoMaterials nanoMaterials) throws Exception {
		super();
		
		this.nanoMaterials = nanoMaterials;
		nanoMaterials.selectNumericColumns();
		rowsSize = nanoMaterials.size();
		header = nanoMaterials.getHeader();
		jcX = nanoMaterials.getDescriptorIndex();
		jcY = nanoMaterials.getResultIndex();
		xcolumns = jcX.length;
		ycolumns = jcY.length;

		/* build Matrices from experimental data */
		buildMatrices();
		
		/* get rid of NaN elements */
		resizeMatrices();
		
	}
	
	public static void setMaxValueX(ArrayList<Double> list)
	{
		CsvMatrix.maxValueX = new Double[list.size()];
		CsvMatrix.maxValueX = list.toArray(CsvMatrix.maxValueX);
	}
	
	public static void setMinValueY(ArrayList<Double> list)
	{
		CsvMatrix.minValueY = new Double[list.size()];
		CsvMatrix.minValueY = list.toArray(CsvMatrix.minValueY);
	}
	
	public static void setMaxValueY(ArrayList<Double> list)
	{
		CsvMatrix.maxValueY = new Double[list.size()];
		CsvMatrix.maxValueY = list.toArray(CsvMatrix.maxValueY);
	}
	
	/**
	 * @return the logger
	 */
	public static Logger getLogger() {
		return LOGGER;
	}

	/**
	 * 
	 * @return
	 * @author Wilson Melendez
	 */
	public List<String[]> getRows() {
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
	public String[] getHeader() {
		return header;
	}

	public static void setHeader(String[] header) {
		CsvMatrix.header = header;
	}

	/**
	 * @return the xmatrix
	 * @author Wilson Melendez
	 */
	public DoubleMatrix getXmatrix() {
		return Xmatrix;
	}

	/**
	 * @return the ymatrix
	 * @author Wilson Melendez
	 */
	public DoubleMatrix getYmatrix() {
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
	public DoubleMatrix getTmatrix() {
		return Tmatrix;
	}

	/**
	 * @return the wmatrix
	 * @author Wilson Melendez
	 */
	public DoubleMatrix getWmatrix() {
		return Wmatrix;
	}

	/**
	 * @return the cmatrix
	 * @author Wilson Melendez
	 */
	public DoubleMatrix getCmatrix() {
		return Cmatrix;
	}

	/**
	 * @return the pmatrix
	 * @author Wilson Melendez
	 */
	public DoubleMatrix getPmatrix() {
		return Pmatrix;
	}
	
	/**
	 * 
	 * @return the umatrix
	 * @author Wilson Melendez
	 */
	public DoubleMatrix getUmatrix(){
		return Umatrix;
	}

	/**
	 * 
	 * @return the Bmatrix
	 * @author Wilson Melendez
	 */
	public DoubleMatrix getBmatrix(){
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
			LOGGER.severe("CSV file was not found.");	
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
			LOGGER.severe("Attempt to close CSV reader failed.");	
			throw ex;
		}
	}
	
	/**
	 * This method determines the positional indices of the numeric 
	 * data in the CSV file.  It also sets maximum and minimum values
	 * for random generated data.
	 * @author Wilson Melendez
	 */
	public static void selectNumericColumns()
	{
		/* Create ArrayLists to store positional indices, minimum, 
		 * and maximum values of the X and Y matrices. */
		ArrayList<Integer> listX = new ArrayList<Integer>();
		ArrayList<Integer> listY = new ArrayList<Integer>();
		ArrayList<Double> listMinX = new ArrayList<Double>();
		ArrayList<Double> listMaxX = new ArrayList<Double>();
		ArrayList<Double> listMinY = new ArrayList<Double>();
		ArrayList<Double> listMaxY = new ArrayList<Double>();
		List<String> listHeader = Arrays.asList(header);
		
		/* Store indices and minimum/maximum random values of X-matrix columns */
		listX.add(listHeader.indexOf("CoatingAmount"));	
		listMinX.add(10.0);  listMaxX.add(100.0);
	    listX.add(listHeader.indexOf("Purity"));
	    listMinX.add(10.0);  listMaxX.add(100.0);
		listX.add(listHeader.indexOf("ContamAl")); 
		listMinX.add(10.0);  listMaxX.add(1500.0); 
		listX.add(listHeader.indexOf("ContamAs")); 
		listMinX.add(10.0);  listMaxX.add(1500.0); 
		listX.add(listHeader.indexOf("ContamBe"));	
		listMinX.add(10.0);  listMaxX.add(1500.0); 
        listX.add(listHeader.indexOf("ContamCa")); 
        listMinX.add(10.0);  listMaxX.add(1500.0); 
        listX.add(listHeader.indexOf("ContamCo")); 
        listMinX.add(10.0);  listMaxX.add(1500.0); 
        listX.add(listHeader.indexOf("ContamCr")); 
        listMinX.add(10.0);  listMaxX.add(1500.0); 
        listX.add(listHeader.indexOf("ContamFe")); 
        listMinX.add(10.0);  listMaxX.add(1500.0); 
        listX.add(listHeader.indexOf("ContamK"));  
        listMinX.add(10.0);  listMaxX.add(1500.0); 
        listX.add(listHeader.indexOf("ContamMg")); 
        listMinX.add(10.0);  listMaxX.add(1500.0); 
        listX.add(listHeader.indexOf("ContamNa")); 
        listMinX.add(10.0);  listMaxX.add(1500.0); 
        listX.add(listHeader.indexOf("ContamP"));  
        listMinX.add(10.0);  listMaxX.add(1500.0); 
        listX.add(listHeader.indexOf("ContamPb")); 
        listMinX.add(10.0);  listMaxX.add(1500.0); 
        listX.add(listHeader.indexOf("ContamSb")); 
        listMinX.add(10.0);  listMaxX.add(1500.0); 
        listX.add(listHeader.indexOf("ContamSe"));  
        listMinX.add(10.0);  listMaxX.add(1500.0); 
        listX.add(listHeader.indexOf("ContamSiO2"));
        listMinX.add(10.0);  listMaxX.add(1500.0); 
        listX.add(listHeader.indexOf("ContamSn")); 
        listMinX.add(10.0);  listMaxX.add(1500.0); 
        listX.add(listHeader.indexOf("ContamTl")); 
        listMinX.add(10.0);  listMaxX.add(1500.0); 
        listX.add(listHeader.indexOf("ContamV"));  
        listMinX.add(10.0);  listMaxX.add(1500.0); 
        listX.add(listHeader.indexOf("ParticleOuterDiamAvg")); 
        listMinX.add(100.0);  listMaxX.add(250.0);
        listX.add(listHeader.indexOf("ParticleOuterDiamLow")); 
        listMinX.add(10.0);  listMaxX.add(100.0); 
        listX.add(listHeader.indexOf("ParticleOuterDiamHigh")); 
        listMinX.add(250.0);  listMaxX.add(400.0);
        listX.add(listHeader.indexOf("ParticleInnerDiamAvg"));
        listMinX.add(50.0);  listMaxX.add(100.0);        
        listX.add(listHeader.indexOf("ParticleInnerDiamLow"));  
        listMinX.add(0.0);  listMaxX.add(50.0);  
        listX.add(listHeader.indexOf("ParticleInnerDiamHigh")); 
        listMinX.add(100.0);  listMaxX.add(150.0); 
        listX.add(listHeader.indexOf("ParticleLengthAvg"));
        listMinX.add(50.0);  listMaxX.add(100.0);       
        listX.add(listHeader.indexOf("ParticleLengthLow")); 
        listMinX.add(10.0);  listMaxX.add(50.0); 
        listX.add(listHeader.indexOf("ParticleLengthHigh")); 
        listMinX.add(100.0);  listMaxX.add(150.0); 
        listX.add(listHeader.indexOf("ParticleThicknessAvg"));
        listMinX.add(50.0);  listMaxX.add(100.0); 
        listX.add(listHeader.indexOf("ParticleThicknessLow")); 
        listMinX.add(10.0);  listMaxX.add(50.0);  
        listX.add(listHeader.indexOf("ParticleThicknessHigh")); 
        listMinX.add(100.0);  listMaxX.add(150.0);  
        listX.add(listHeader.indexOf("SurfaceAreaAvg")); 
        listMinX.add(150.0);  listMaxX.add(300.0); 
        listX.add(listHeader.indexOf("SurfaceAreaLow"));
        listMinX.add(10.0);  listMaxX.add(150.0);  
        listX.add(listHeader.indexOf("SurfaceAreaHigh")); 
        listMinX.add(300.0);  listMaxX.add(450.0);     
        listX.add(listHeader.indexOf("MC_TimeValue")); 
        listMinX.add(10.0);  listMaxX.add(240.0);   
        listX.add(listHeader.indexOf("MC_ParticleConcentration")); 
        listMinX.add(10.0);  listMaxX.add(100.0);     
        listX.add(listHeader.indexOf("MC_SerumConcentration"));   
        listMinX.add(10.0);  listMaxX.add(100.0);     
        listX.add(listHeader.indexOf("MC_AntibioticConcentration"));
        listMinX.add(10.0);  listMaxX.add(100.0);     
        listX.add(listHeader.indexOf("MC_DOMConcentration"));   
        listMinX.add(10.0);  listMaxX.add(20.0); 
        listX.add(listHeader.indexOf("MC_SalinityValue"));
        listMinX.add(10.0);  listMaxX.add(35.0);  
        listX.add(listHeader.indexOf("MC_pHAvg"));
        listMinX.add(4.0);  listMaxX.add(10.0);   
        listX.add(listHeader.indexOf("MC_pHLow"));  
        listMinX.add(0.0);  listMaxX.add(4.0); 
        listX.add(listHeader.indexOf("MC_pHHigh")); 
        listMinX.add(10.0);  listMaxX.add(14.0); 
        listX.add(listHeader.indexOf("MC_MediumTemp")); 
        listMinX.add(10.0);  listMaxX.add(40.0);
        listX.add(listHeader.indexOf("ZetaPotentialAvg"));
        listMinX.add(-30.0);  listMaxX.add(-100.0);
        listX.add(listHeader.indexOf("ZetaPotentialLow"));
        listMinX.add(-10.0);  listMaxX.add(-30.0); 
        listX.add(listHeader.indexOf("ZetaPotentialHigh")); 
        listMinX.add(-100.0);  listMaxX.add(-150.0);    
        listX.add(listHeader.indexOf("SizeDistribAvg"));
        listMinX.add(1000.0);  listMaxX.add(2000.0);     
        listX.add(listHeader.indexOf("SizeDistribLow")); 
        listMinX.add(10.0);  listMaxX.add(1000.0); 
        listX.add(listHeader.indexOf("SizeDistribHigh")); 
        listMinX.add(2000.0);  listMaxX.add(3000.0);   
        listX.add(listHeader.indexOf("SizeDistribAvg2")); 
        listMinX.add(1000.0);  listMaxX.add(2000.0);    
        listX.add(listHeader.indexOf("SizeDistribLow2")); 
        listMinX.add(10.0);  listMaxX.add(1000.0);   
        listX.add(listHeader.indexOf("SizeDistribHigh2"));
        listMinX.add(2000.0);  listMaxX.add(3000.0);   
        listX.add(listHeader.indexOf("SerumConcentration"));
        listMinX.add(10.0);  listMaxX.add(100.0); 
        listX.add(listHeader.indexOf("AntibioticConcentration"));  
        listMinX.add(10.0);  listMaxX.add(100.0);  
        listX.add(listHeader.indexOf("DOMConcentration")); 
        listMinX.add(10.0);  listMaxX.add(20.0);   
        listX.add(listHeader.indexOf("SalinityValue")); 
        listMinX.add(10.0);  listMaxX.add(35.0);     
        listX.add(listHeader.indexOf("pHAvg"));  
        listMinX.add(4.0);  listMaxX.add(10.0);   
        listX.add(listHeader.indexOf("pHLow")); 
        listMinX.add(0.0);  listMaxX.add(4.0);  
        listX.add(listHeader.indexOf("pHHigh")); 
        listMinX.add(10.0);  listMaxX.add(14.0);
        listX.add(listHeader.indexOf("MediumTemp"));
        listMinX.add(10.0);  listMaxX.add(40.0); 
        listX.add(listHeader.indexOf("TimeValue"));   
        listMinX.add(48.0);  listMaxX.add(72.0);   
        listX.add(listHeader.indexOf("ParticleConcentration")); 
        listMinX.add(10.0);  listMaxX.add(100.0); 
        listX.add(listHeader.indexOf("ParticleExposDuration"));
        listMinX.add(48.0);  listMaxX.add(72.0);     
        listX.add(listHeader.indexOf("UVADose")); 
        listMinX.add(0.0);  listMaxX.add(10.0); 
        listX.add(listHeader.indexOf("UVAExposDuration")); 
        listMinX.add(1.0);  listMaxX.add(2.0); 
        
        /* Determine number of X columns. */
        xcolumns = listX.size();
        
        /* Create arrays that will store indices, minimum, and 
         * maximum values of the X columns. */
        jcolX = new Integer[xcolumns];
        minValueX = new Double[xcolumns];
        maxValueX = new Double[xcolumns];
        jcolX = listX.toArray(jcolX); 
        minValueX = listMinX.toArray(minValueX);
        maxValueX = listMaxX.toArray(maxValueX);
        
        /* Store indices and minimum/maximum random values of Y-matrix columns */
        listY.add(listHeader.indexOf("ViabilityAvg"));        
        listMinY.add(10.0);  listMaxY.add(100.0); 
        listY.add(listHeader.indexOf("LC50"));
        listMinY.add(10.0);  listMaxY.add(100.0);
        
        /* Determine number of Y columns. */
        ycolumns = listY.size();
        
        /* Create arrays that will store indices, minimum, and 
         * maximum values of the Y columns. */
        jcolY = new Integer[ycolumns];
        minValueY = new Double[ycolumns];
        maxValueY = new Double[ycolumns];
        jcolY = listY.toArray(jcolY); 
        minValueY = listMinY.toArray(minValueY);
        maxValueY = listMaxY.toArray(maxValueY);
	}
	
	/**
	 * This method builds the X and Y matrices needed by the PLS Regression algorithm.
	 * Null values are converted to NaN values.
	 * @author Paul Harten
	 * @throws Exception 
	 */
	public void buildMatrices() throws Exception
	{		

		xMatrix = new DoubleMatrix(rowsSize, xcolumns);   // full descriptor matrix
		yMatrix = new DoubleMatrix(rowsSize, ycolumns);   // full result matrix
		
		Field[] fields = NanoMaterial.class.getDeclaredFields();
		for (Field field: fields) field.setAccessible(true);
		
		
		/* Check whether data has any null values. */
		for (int i=0; i<nanoMaterials.size(); i++) {
			
			NanoMaterial nanoMaterial = nanoMaterials.get(i);
			
			for (int j=0; j<jcX.length; j++) {
				Field field = fields[jcX[j]];
				Object v1 = field.get(nanoMaterial);
				if (v1!=null) {
					double value = ((Double)v1).doubleValue();
					xMatrix.put(i,j,value);
				} else {
					xMatrix.put(i,j,Double.NaN);
				}
			}
			
			for (int j=0; j<jcY.length; j++) {
				Field field = fields[jcY[j]];
				Object v1 = field.get(nanoMaterial);
				if (v1!=null) {
					double value = ((Double)v1).doubleValue();
					yMatrix.put(i,j,value);
				} else {
					yMatrix.put(i,j,Double.NaN);
				}
			}
			
		}
		
	}
	
	/**
	 * This method resizes the X and Y matrices needed by the PLS Regression algorithm.
	 * Any rows with Ymatrix NaN results is deleted. Xmatrix columns with NaN values are
	 * replaced with descriptor average or 0.0.
	 * @author Paul Harten
	 * @throws Exception 
	 */
	public static void resizeMatrices() throws Exception
	{		

		Xmatrix = new DoubleMatrix(0, xcolumns);   // X matrix
		Ymatrix = new DoubleMatrix(0, ycolumns);   // Y matrix
		
		DoubleMatrix yRow, xColumn;
		Boolean foundNaN;
		double avg, num;
		
		/* if result data has NaN values, leave row out of calculations */
		for (int i=0; i<rowsSize; i++) {
			yRow = yMatrix.getRow(i);
			foundNaN = false;
			for (int j=0; j<ycolumns; j++) {
				if (Double.isNaN(yRow.get(j))) {
					foundNaN = true;
					break;
				}
			}
			if (!foundNaN) {
				Ymatrix = DoubleMatrix.concatVertically(Ymatrix, yRow);
				Xmatrix = DoubleMatrix.concatVertically(Xmatrix, xMatrix.getRow(i)); 
			}
		}
		ycolumns = Ymatrix.columns;
		rowsSize = Ymatrix.rows;
		if (rowsSize==0) throw new Exception("No rows of data remain");
		
		/* if descriptor data has NaN values, replace with avg of that descriptor */
		for (int j=0; j<xcolumns; j++) {
			xColumn = Xmatrix.getColumn(j);
			avg = 0.0;
			num = 0;
			for (int i=0; i<rowsSize; i++) {
				Double value = xColumn.get(i);
				if (!Double.isNaN(value)) {
					num++;
					avg += value;
				}
			}
			if (num>0) {	/* fill the NaN values with avg */
				avg /= num;
				for (int i=0; i<rowsSize; i++) {
					if (Double.isNaN(xColumn.get(i))) {
						xColumn.put(i, avg);
					}
				}
			} else { /* all NaN values, fill the whole column up with 0.0 */
				for (int i=0; i<rowsSize; i++) {
					xColumn.put(i, 0.0);
				}
			}
			Xmatrix.putColumn(j, xColumn);	
		}
		xcolumns = Xmatrix.columns;
		if (xcolumns==0) throw new Exception("No columns of descriptor data remain");
		
//		System.out.println("rowsSize = "+rowsSize+", xcolumns = "+xcolumns+", ycolumns = "+ycolumns);
		
	}
	

	/**
	 * This method returns result column as the log of the result column
	 * @return
	 * @author Paul Harten
	 * @return 
	 * @throws Exception 
	 */
	public static DoubleMatrix log(DoubleMatrix Yorig) {

		DoubleMatrix yLn = new DoubleMatrix(Yorig.rows,1);
		
		for (int i=0; i<yLn.rows; i++) {
			yLn.put(i, Math.log(Yorig.get(i)));
		}
		
		return yLn;
	}
	
	/**
	 * This method returns the result column as the exp of the result column
	 * @return
	 * @author Paul Harten
	 * @return 
	 * @throws Exception 
	 */
	public static DoubleMatrix exp(DoubleMatrix Yorig) {

		DoubleMatrix yExp = new DoubleMatrix(Yorig.rows,1);
		
		for (int i=0; i<yExp.rows; i++) {
			yExp.put(i, Math.exp(Yorig.get(i)));
		}
		
		return yExp;
	}

	/**
	 * This routine builds the X and Y matrices for the case of the original data 
	 * containing null values.
	 * @author Wilson Melendez
	 */
	public static void buildMatricesContainingNulls(DoubleMatrix Xorig, DoubleMatrix Yorig)
	{
		String[] nextLine;
		           
		/* Loop over the rows of the data */
		for (int i = 0; i < Xorig.rows; i++)
		{
			nextLine = rows.get(i);	  // Get the ith row of the data
			for (int k = 0; k < Xorig.columns; k++)   // Loop over the columns
			{
				int jcol = jcolX[k];
				Xorig.put(i,k,getValue(nextLine[jcol], minValueX[k], maxValueX[k]));
			}
			
			for (int k = 0; k < Yorig.columns; k++)
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
           
		/* Loop over the rows of the data */
		for (int i = 0; i < Xorig.rows; i++)
		{
			nextLine = rows.get(i);	  // Get the ith row of the data
			for (int k = 0; k < Xorig.columns; k++)   // Loop over the columns
			{
				int jcol = jcolX[k];
				Xorig.put(i,k,Double.parseDouble(nextLine[jcol]));
//				Xorig.put(i,k,1.0);
			}
			
			for (int k = 0; k < Yorig.columns; k++)
			{
				int jcol = jcolY[k];
				Yorig.put(i,k,Double.parseDouble(nextLine[jcol]));
//				Yorig.put(i,k,1.0);
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
			if (std < Math.abs(mean*EPSILON)) std = 0.0;
			
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
	 * This method performs the PLS regression algorithm one result Column at a time.
	 * It can be done more efficiently because of the multiple normalizations done.
	 * @param Xorig
	 * @param Yorig
	 * @return
	 * @author Paul Harten
	 * @throws IOException 
	 */
	public DoubleMatrix performResultsIndependentPLSR(DoubleMatrix X, DoubleMatrix Y, boolean testForOverfitting)
	{
		DoubleMatrix BplsStar = new DoubleMatrix(X.columns+1, Y.columns);
		for (int j=0; j<Y.columns; j++) {
			BplsStar.putColumn(j, performPLSR(X,Y.getColumn(j), testForOverfitting));
		}
		return BplsStar;
	}
	
	/**
	 * This method performs the PLS regression algorithm.
	 * @param Xorig
	 * @param Yorig
	 * @return
	 * @author Wilson Melendez
	 * @throws IOException 
	 */
	public DoubleMatrix performPLSR(DoubleMatrix Xorig, DoubleMatrix Yorig, boolean testForOverfitting)
	{	
		Pmatrix = null;
		Umatrix = null;
		Cmatrix = null;
		Tmatrix = null;
		Wmatrix = null;
		Bmatrix = null;
		
//		Xmatrix = Xorig;
//		Ymatrix = Yorig;
		
		/* Normalize the Xmatrix by turning each element of the 
		 * matrix into Z-scores. 
		 */
		DoubleMatrix X0 = new DoubleMatrix(Xorig.rows,Xorig.columns);
		X0.copy(Xorig);
		
		meanX = new DoubleMatrix(Xorig.columns);
		stdX = new DoubleMatrix(Xorig.columns);
        normalizeMatrix(X0, meanX, stdX);
        
        /* Normalize the Ymatrix by turning each element of the 
		 * matrix into Z-scores.
		 */        
        DoubleMatrix Y0 = new DoubleMatrix(Yorig.rows,Yorig.columns);
        Y0.copy(Yorig);
        
		meanY = new DoubleMatrix(Yorig.columns);
		stdY = new DoubleMatrix(Yorig.columns);		
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
		bValues = performNIPALS(X0, Y0, testForOverfitting);
		numOfDeflations = bValues.size();
		
		DoubleMatrix BplsStar = determineCoefficients(bValues, Pmatrix, Cmatrix);
			
		return BplsStar;				
	}

	private DoubleMatrix determineCoefficients(List<Double> bValues, DoubleMatrix P, DoubleMatrix C) {
		
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
//		Bmatrix = DoubleMatrix.diag(mB);
		
		/* Calculate the Moore-Penrose pseudo-inverse of the transpose 
		 * of the P matrix. */
		DoubleMatrix ptInv = Solve.pinv(P.transpose());
		
		/* Calculate the PLS regression weights. This is also known as 
		 * the BPLS vector. 
		 */
		Bpls = ptInv.mmul((C.transpose()).mulColumnVector(mB));
//		Bpls = ptInv.mmul(Bmatrix).mmul(Cmatrix.transpose());
        
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
	public static void writeBplsStarToCsv(String[] descriptorHeader, DoubleMatrix Bstar, String filename)
	{
		String[] entries = new String[Bstar.rows];
		
		try
		{	
			FileWriter file = new FileWriter(filename); 
					
			/* Create an instance of the CSVWriter class and specify the comma as the 
			 * default separator. Default quote character is double quote. */ 
			CSVWriter csvOutput = new CSVWriter(file,CSVWriter.DEFAULT_SEPARATOR);
			
			/* first write descriptor header row */
			entries[0] = "B - coeffs:";
			for (int i=0; i<Bstar.rows-1; i++) {
				entries[i+1] = descriptorHeader[i];
			}
			/* Write row of data to output using the writeNext method. */
			csvOutput.writeNext(entries); 
					
			/* allow for multiple columns of Bstar */
			for (int j=0; j<Bstar.columns; j++) {
				
				DoubleMatrix bColumn = Bstar.getColumn(j);
				for (int i=0; i<Bstar.rows; i++) {
					entries[i] = String.valueOf(bColumn.get(i));
				}

				/* Write row of data to output using the writeNext method. */
				csvOutput.writeNext(entries); 
			}
			
			/* Close the writer. */
			   csvOutput.close();   
		}
		catch(IOException ex)
		{
			LOGGER.severe("FileWriter for " + filename + " could not be constructed." + ex);	
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
	public List<Double> performNIPALS(DoubleMatrix X0, DoubleMatrix Y0, Boolean testForOverfitting)
	{
		List<Double> bValues = new ArrayList();
		DoubleMatrix X = new DoubleMatrix();
		DoubleMatrix Y = new DoubleMatrix();
		DoubleMatrix u;
		DoubleMatrix w;		
		DoubleMatrix t;
		DoubleMatrix t0;		
		DoubleMatrix c;		
		DoubleMatrix p;		
		DoubleMatrix tpt, tct;	
		
		double deltaT;
		int numDeflations = 0;
		int maxNumDeflations = X0.rows;
		double press0 = 0;
		double press = 0;
		
		X = X0;
		Y = Y0;
		
		numDeflations = 0;
				
		do {
			
			/* Initialize u with first column of Y. */
			u = Y.getColumn(0);
			
			/* Calculate w = X'u, and normalize the result. */
			w = X.transpose().mmul(u);				
			w = w.div(w.norm2());
			
			/* Calculate X-scores = t, and normalize the result. */
			t0 = X.mmul(w);				
			t0 = t0.div(t0.norm2());
			
			do {

				/* Calculate Y-weights, c, and normalize the result. */
				c = Y.transpose().mmul(t0);
				c = c.div(c.norm2());
				
				/* Calculate an updated set of Y-scores, u */ 
				u = Y.mmul(c);
					
				/* Calculate w and normalize the result. */
				w = X.transpose().mmul(u);				
				w = w.div(w.norm2());
				
				/* Calculate X-scores, t, and normalize the result. */
				t = X.mmul(w);				
				t = t.div(t.norm2());
				
				/* Determine if t is still changing */
				deltaT = (t.sub(t0)).norm2();
				t0 = t;
				
			} while (deltaT > EPSILON);
			
			double b = t.transpose().dot(u);
			
			/* Compute the factor loadings for X. */
			p = X.transpose().mmul(t);
			
			if (testForOverfitting) { // calculate cross-validation effects
				if (numDeflations==0) {
					press0 = sumOfSquares(Ytesting);
				} else {
					List<Double> bValuesTrial = new ArrayList(bValues);
					bValuesTrial.add(b);
					DoubleMatrix pTrial = DoubleMatrix.concatHorizontally(Pmatrix, p);
					DoubleMatrix cTrial = DoubleMatrix.concatHorizontally(Cmatrix, c);
					press = calculatePredictedResidual(bValuesTrial, Xtesting, Ytesting, pTrial, cTrial);
					if (press > press0) break;
					press0 = press;
				}
			}
			
			bValues.add(b);
			
			/* Deflate the X and Y matrices. */
			tpt = t.mmul(p.transpose());
			tct = (t.mmul(c.transpose())).muli(b);
			X = X.sub(tpt);
			Y = Y.sub(tct);
			
			numDeflations = numDeflations + 1;
			
			/* Store t, u, p, c, and w in their corresponding matrices. */
			if (numDeflations==1) {
				Tmatrix = t.dup();
				Umatrix = u.dup();
				Pmatrix = p.dup();
				Cmatrix = c.dup();				
				Wmatrix = w.dup();
			} else {
				/* Make t orthonormal to the rest of its cumulative matrix */
				t = t.sub(Tmatrix.mmul((Tmatrix.transpose()).mmul(t)));
				t = t.div(t.norm2());
				
				Tmatrix = DoubleMatrix.concatHorizontally(Tmatrix,t);
				Umatrix = DoubleMatrix.concatHorizontally(Umatrix,u);
				Pmatrix = DoubleMatrix.concatHorizontally(Pmatrix,p);
				Cmatrix = DoubleMatrix.concatHorizontally(Cmatrix,c);
				Wmatrix = DoubleMatrix.concatHorizontally(Wmatrix,w);				
			}
			
		} while (X.norm2() > EPSILON_DEFLATION && numDeflations < maxNumDeflations);
		
		return bValues;
	
	}

	private double calculatePredictedResidual(List<Double> bValues, DoubleMatrix X, DoubleMatrix Y, DoubleMatrix P, DoubleMatrix C) {
		DoubleMatrix BplsStar = determineCoefficients(bValues, P, C);
		DoubleMatrix Yhat = predictResults(X, BplsStar);
		double press = sumOfSquares(Yhat.sub(Y));
		return press;
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
	 * This method creates a list of set indices and then randomly shuffles.  The indices
	 * to cross-validate the original data using the 5-fold cross-validation method.
	 * @author Paul Harten
	 */
	private List<Integer> createSetIndexListAndShuffle(int nfolds)
	{

		List<Integer> list = new ArrayList<Integer>();
		
		/* Generate list of indices. */
		for (int i = 0; i < Xmatrix.rows; i++)
		{
			list.add(i % nfolds);
		}
		
		/* Randomly shuffle the list of indices. */
		Collections.shuffle(list);
				
		return list;
	}
	
	/**
	 * This method implements the multifold cross-validation algorithm.
	 * @author Wilson Melendez & Paul Harten
	 */
//	public DoubleMatrix performMultiFoldCrossValidation(int nfolds)
//	{
//		DoubleMatrix Ytilde = new DoubleMatrix();
//		
//		for (int ifold = 0; ifold < nfolds; ifold++)
//		{
//			/* Leave one set out and use remaining sets to build model. */
//			Xtraining = new DoubleMatrix(0, XmatrixSet[0].columns);
//			Ytraining = new DoubleMatrix(0, YmatrixSet[0].columns);
//			Xtesting = new DoubleMatrix(0, XmatrixSet[0].columns);
//			Ytesting = new DoubleMatrix(0, YmatrixSet[0].columns);
//			DoubleMatrix Yhat = null;
//			
//			/* Build the X and Y training and testing matrices for the model. */
//			for (int i = 0; i < numDataSets; i++)
//			{
//				if (i==ifold) {
//					Xtesting = XmatrixSet[i];
//					Ytesting = YmatrixSet[i];
//				} else {
//					Xtraining = DoubleMatrix.concatVertically(Xtraining, XmatrixSet[i]);
//					Ytraining = DoubleMatrix.concatVertically(Ytraining, YmatrixSet[i]);
//				}
//			}
//			
//			/* Perform the PLS regression analysis. */
//			DoubleMatrix BplsS = performPLSR(Xtraining, Ytraining, true);
//			
//			/* Predict the Y values that were left out. */
//			Yhat = predictResults(Xtesting, BplsS);			
//			
//			/* Build the predicted Y's into a single matrix. */
//			Ytilde = DoubleMatrix.concatVertically(Ytilde, Yhat);
//
//		}
//		
//		return Ytilde;
//	}
	/**
	 * This method implements the multi-fold cross-validation algorithm.
	 * @author Wilson Melendez & Paul Harten
	 */
	public DoubleMatrix performMultiFoldCrossValidation(int nfolds, DoubleMatrix Xorig, DoubleMatrix Yorig)
	{
		Xmatrix = Xorig;
		Ymatrix = Yorig;
		xcolumns = Xorig.columns;
		ycolumns = Yorig.columns;
		/* Split original data into nfolds cross-validation analysis. */
		List<Integer> list = createSetIndexListAndShuffle(nfolds);
		
		DoubleMatrix Ytilde = new DoubleMatrix(Yorig.rows,Yorig.columns);
		
		numDeflationsAvg = 0;
		q2avg = new double[Yorig.columns];
		for (int j=0; j<q2avg.length; j++) q2avg[j]=0.0;
		
		for (int ifold = 0; ifold < nfolds; ifold++)
		{
			DoubleMatrix Yhat;

			separateTrainingFromTesting(ifold, list);
						
			/* Perform the PLS regression analysis. */
			DoubleMatrix BplsS = performPLSR(Xtraining, Ytraining, true);
			
			numDeflationsAvg += numOfDeflations;
			
			/* Predict the Y values that were left out. */
			Yhat = predictResults(Xtesting, BplsS);	

			/* Use the list to unshuffle Yhat into Ytilde. */
			int index = 0;
			for (int i=0; i<list.size(); i++) {
				if (list.get(i)==ifold) {
					Ytilde.putRow(i, Yhat.getRow(index++));
				}
			}
			
			DoubleMatrix Ycol = null;
			DoubleMatrix Ydiff = null;
			double var = 0;
			double press = 0;
			double q2 = 0;
			
			for (int jcol=0; jcol<Ytesting.columns; jcol++) {
				
				Ycol = Ytesting.getColumn(jcol);
				double ymean = 0.0;
				for (int i = 0; i<Ycol.rows; i++) {
					ymean += Ycol.get(i);
				}
				ymean /= Ycol.rows;
				
				Ydiff = Ycol.sub(ymean);
				var = Ydiff.dot(Ydiff);

				Ydiff = Ycol.sub(Yhat.getColumn(jcol));
				press = Ydiff.dot(Ydiff);

				if (var==0) q2 = 1.0;
				else q2 = 1.0-(press/var);
				
				q2avg[jcol] += q2;
			}
			
		}
		
		for (int j=0;j<q2avg.length;j++) q2avg[j] /= nfolds;
		numDeflationsAvg /= nfolds;
		
		return Ytilde;
	}

	private void separateTrainingFromTesting(int ifold, List<Integer> list) {
		
		/* Leave one set out for testing and use remaining sets for training. */
		Xtraining = new DoubleMatrix(0, xcolumns);
		Ytraining = new DoubleMatrix(0, ycolumns);
		Xtesting = new DoubleMatrix(0, xcolumns);
		Ytesting = new DoubleMatrix(0, ycolumns);
		
		/* Build the X and Y training and testing matrices for the model. */
		for (int i = 0; i < list.size(); i++)
		{
			if (list.get(i)==ifold) {
				Xtesting = DoubleMatrix.concatVertically(Xtesting, Xmatrix.getRow(i));
				Ytesting = DoubleMatrix.concatVertically(Ytesting, Ymatrix.getRow(i));
			} else {
				Xtraining = DoubleMatrix.concatVertically(Xtraining, Xmatrix.getRow(i));
				Ytraining = DoubleMatrix.concatVertically(Ytraining, Ymatrix.getRow(i));
			}
		}
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

	protected void setXtesting(DoubleMatrix xtesting) {
		Xtesting = xtesting;
	}

	protected void setYtesting(DoubleMatrix ytesting) {
		Ytesting = ytesting;
	}

	protected int getNumOfDeflations() {
		return numOfDeflations;
	}
	
	public double[] getQ2avg() {
		return q2avg;
	}

	public int getNumDeflationsAvg() {
		return numDeflationsAvg;
	}
	
}
