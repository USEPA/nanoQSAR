import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.opencsv.CSVReader;


/**
 * @author Wilson Melendez
 *
 */
public class CsvMatrix 
{
	private static List<String[]> rows;
	private static String[] header;
	
	/* Need this line to allow logging of error messages */
	private final static Logger lOGGER = Logger.getLogger( LoggerInfo.class.getName() );
	
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
		
		/* Create instance of CSVReader. */
		CSVReader csvInput = new CSVReader(fReader);
		
		/* Read contents of CSV file all at once and store it in array list. */
		rows = csvInput.readAll();
		
		/* Read the first row of the Xmatrix and store it in the header string array. */
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
		String[] nextLine;
		int rowsSize = rows.size();  // Number of rows in array list.
		double[][] Xmatrix = new double[rowsSize][67];
		double[][] Ymatrix = new double[rowsSize][2];
		
		for (int i = 0; i < rowsSize; i++)
		{
			nextLine = rows.get(i);
			int j = 0;	
			int jy = 0;
			for (int k = 0; k < nextLine.length-1; k++)
			{		
				switch (header[k].trim())
				{
				case "CoatingAmount":
					Xmatrix[i][j] = !nextLine[k].equals("null") ? Double.parseDouble(nextLine[k]) : Math.random()*100.0;
					j++;
					break;
				case "Purity":
					Xmatrix[i][j] = !nextLine[k].equals("null") ? Double.parseDouble(nextLine[k]) : Math.random()*100.0;
					j++;
					break;	
				case "ContamAl": 
					Xmatrix[i][j] = !nextLine[k].equals("null") ? Double.parseDouble(nextLine[k]) : Math.random()*1500.0;
					j++;
					break;	
				case "ContamAs": 
					Xmatrix[i][j] = !nextLine[k].equals("null") ? Double.parseDouble(nextLine[k]) : Math.random()*1500.0;
					j++;
					break;	
				case "ContamBe":
					Xmatrix[i][j] = !nextLine[k].equals("null") ? Double.parseDouble(nextLine[k]) : Math.random()*1500.0;
					j++;
					break;	
                case "ContamCa":
                	Xmatrix[i][j] = !nextLine[k].equals("null") ? Double.parseDouble(nextLine[k]) : Math.random()*1500.0;
                	j++;
                	break;	
                case "ContamCo":
                	Xmatrix[i][j] = !nextLine[k].equals("null") ? Double.parseDouble(nextLine[k]) : Math.random()*1500.0;
                	j++;
                	break;	
                case "ContamCr":
                	Xmatrix[i][j] = !nextLine[k].equals("null") ? Double.parseDouble(nextLine[k]) : Math.random()*1500.0;
                	j++;
                	break;	
                case "ContamFe":
                	Xmatrix[i][j] = !nextLine[k].equals("null") ? Double.parseDouble(nextLine[k]) : Math.random()*1500.0;
                	j++;
                	break;	
                case "ContamK":
                	Xmatrix[i][j] = !nextLine[k].equals("null") ? Double.parseDouble(nextLine[k]) : Math.random()*1500.0;
                	j++;
                	break;	
                case "ContamMg":
                	Xmatrix[i][j] = !nextLine[k].equals("null") ? Double.parseDouble(nextLine[k]) : Math.random()*1500.0;
                	j++;
                	break;	
                case "ContamNa":
                	Xmatrix[i][j] = !nextLine[k].equals("null") ? Double.parseDouble(nextLine[k]) : Math.random()*1500.0;
                	j++;
                	break;	
                case "ContamP":
                	Xmatrix[i][j] = !nextLine[k].equals("null") ? Double.parseDouble(nextLine[k]) : Math.random()*1500.0;
                	j++;
                	break;	
                case "ContamPb":
                	Xmatrix[i][j] = !nextLine[k].equals("null") ? Double.parseDouble(nextLine[k]) : Math.random()*1500.0;
                	j++;
                	break;	
                case "ContamSb":
                	Xmatrix[i][j] = !nextLine[k].equals("null") ? Double.parseDouble(nextLine[k]) : Math.random()*1500.0;
                	j++;
                	break;	
                case "ContamSe":
                	Xmatrix[i][j] = !nextLine[k].equals("null") ? Double.parseDouble(nextLine[k]) : Math.random()*1500.0;
                	j++;
                	break;	
                case "ContamSiO2":
                	Xmatrix[i][j] = !nextLine[k].equals("null") ? Double.parseDouble(nextLine[k]) : Math.random()*1500.0;
                	j++;
                	break;	
                case "ContamSn":
                	Xmatrix[i][j] = !nextLine[k].equals("null") ? Double.parseDouble(nextLine[k]) : Math.random()*1500.0;
                	j++;
                	break;	
                case "ContamTl":
                	Xmatrix[i][j] = !nextLine[k].equals("null") ? Double.parseDouble(nextLine[k]) : Math.random()*1500.0;
                	j++;
                	break;	
                case "ContamV":
                	Xmatrix[i][j] = !nextLine[k].equals("null") ? Double.parseDouble(nextLine[k]) : Math.random()*1500.0;
                	j++;
                	break;	
                case "ParticleOuterDiamAvg":
                	Xmatrix[i][j] = !nextLine[k].equals("null") ? Double.parseDouble(nextLine[k]) : Math.random()*250.0;
                	j++;
                	break;	
                case "ParticleOuterDiamLow":
                	Xmatrix[i][j] = !nextLine[k].equals("null") ? Double.parseDouble(nextLine[k]) : Math.random()*100.0;
                	j++;
                	break;	
                case "ParticleOuterDiamHigh":
                	Xmatrix[i][j] = !nextLine[k].equals("null") ? Double.parseDouble(nextLine[k]) : Math.random()*300.0;
                	j++;
                	break;	
                case "ParticleInnerDiamAvg":
                	Xmatrix[i][j] = !nextLine[k].equals("null") ? Double.parseDouble(nextLine[k]) : Math.random()*100.0;
                	j++;
                	break;	
                case "ParticleInnerDiamLow":
                	Xmatrix[i][j] = !nextLine[k].equals("null") ? Double.parseDouble(nextLine[k]) : Math.random()*100.0;
                	j++;
                	break;	
                case "ParticleInnerDiamHigh":
                	Xmatrix[i][j] = !nextLine[k].equals("null") ? Double.parseDouble(nextLine[k]) : Math.random()*100.0;
                	j++;
                	break;	
                case "ParticleLengthAvg":
                	Xmatrix[i][j] = !nextLine[k].equals("null") ? Double.parseDouble(nextLine[k]) : Math.random()*100.0;
                	j++;
                	break;	
                case "ParticleLengthLow":
                	Xmatrix[i][j] = !nextLine[k].equals("null") ? Double.parseDouble(nextLine[k]) : Math.random()*100.0;
                	j++;
                	break;	
                case "ParticleLengthHigh":
                	Xmatrix[i][j] = !nextLine[k].equals("null") ? Double.parseDouble(nextLine[k]) : Math.random()*100.0;
                	j++;
                	break;	
                case "ParticleThicknessAvg":
                	Xmatrix[i][j] = !nextLine[k].equals("null") ? Double.parseDouble(nextLine[k]) : Math.random()*100.0;
                	j++;
                	break;	
                case "ParticleThicknessLow":
                	Xmatrix[i][j] = !nextLine[k].equals("null") ? Double.parseDouble(nextLine[k]) : Math.random()*100.0;
                	j++;
                	break;	
                case "ParticleThicknessHigh":
                	Xmatrix[i][j] = !nextLine[k].equals("null") ? Double.parseDouble(nextLine[k]) : Math.random()*100.0;
                	j++;
                	break;	
                case "SurfaceAreaAvg":
                	Xmatrix[i][j] = !nextLine[k].equals("null") ? Double.parseDouble(nextLine[k]) : Math.random()*300.0;
                	j++;
                	break;		
                case "SurfaceAreaLow":
                	Xmatrix[i][j] = !nextLine[k].equals("null") ? Double.parseDouble(nextLine[k]) : Math.random()*300.0;
                	j++;
                	break;		
                case "SurfaceAreaHigh":
                	Xmatrix[i][j] = !nextLine[k].equals("null") ? Double.parseDouble(nextLine[k]) : Math.random()*300.0;
                	j++;
                	break;	
                case "MC_TimeValue":
                	Xmatrix[i][j] = !nextLine[k].equals("null") ? Double.parseDouble(nextLine[k]) : Math.random()*240.0;
                	j++;
                	break;		
                case "MC_ParticleConcentration":
                	Xmatrix[i][j] = !nextLine[k].equals("null") ? Double.parseDouble(nextLine[k]) : Math.random()*100.0;
                	j++;
                	break;	
                case "MC_SerumConcentration":
                	Xmatrix[i][j] = !nextLine[k].equals("null") ? Double.parseDouble(nextLine[k]) : Math.random() * 100.0;
                	j++;
                	break;		
                case "MC_AntibioticConcentration":
                	Xmatrix[i][j] = !nextLine[k].equals("null") ? Double.parseDouble(nextLine[k]) : Math.random() * 100.0;
                	j++;
                	break;	
                case "MC_DOMConcentration":
                	Xmatrix[i][j] = !nextLine[k].equals("null") ? Double.parseDouble(nextLine[k]) : Math.random() * 20.0;
                	j++;
                	break;	
                case "MC_SalinityValue":
                	Xmatrix[i][j] = !nextLine[k].equals("null") ? Double.parseDouble(nextLine[k]) : Math.random() * 35.0;
                	j++;
                	break;	
                case "MC_pHAvg":
                	Xmatrix[i][j] = !nextLine[k].equals("null") ? Double.parseDouble(nextLine[k]) : Math.random() * 14.0;
                	j++;
                	break;	
                case "MC_pHLow":
                	Xmatrix[i][j] = !nextLine[k].equals("null") ? Double.parseDouble(nextLine[k]) : Math.random() * 14.0;
                	j++;
                	break;	
                case "MC_pHHigh":
                	Xmatrix[i][j] = !nextLine[k].equals("null") ? Double.parseDouble(nextLine[k]) : Math.random() * 14.0;
                	j++;
                	break;	
                case "MC_MediumTemp":
                	Xmatrix[i][j] = !nextLine[k].equals("null") ? Double.parseDouble(nextLine[k]) : 30.0 * Math.random() + 10.0;
                	j++;
                	break;	
                case "ZetaPotentialAvg":
                	Xmatrix[i][j] = !nextLine[k].equals("null") ? Double.parseDouble(nextLine[k]) : -Math.random() * 100.0;
                	j++;
                	break;	
                case "ZetaPotentialLow":
                	Xmatrix[i][j] = !nextLine[k].equals("null") ? Double.parseDouble(nextLine[k]) : -Math.random() * 100.0;
                	j++;
                	break;	
                case "ZetaPotentialHigh":
                	Xmatrix[i][j] = !nextLine[k].equals("null") ? Double.parseDouble(nextLine[k]) : -Math.random() * 100.0;
                	j++;
                	break;	
                case "SizeDistribAvg":
                	Xmatrix[i][j] = !nextLine[k].equals("null") ? Double.parseDouble(nextLine[k]) : Math.random() * 3000.0;
                	j++;
                	break;
                case "SizeDistribLow":
                	Xmatrix[i][j] = !nextLine[k].equals("null") ? Double.parseDouble(nextLine[k]) : Math.random() * 3000.0;
                	j++;
                	break;
                case "SizeDistribHigh":
                	Xmatrix[i][j] = !nextLine[k].equals("null") ? Double.parseDouble(nextLine[k]) : Math.random() * 3000.0;
                	j++;
                	break;
                case "SizeDistribAvg2":
                	Xmatrix[i][j] = !nextLine[k].equals("null") ? Double.parseDouble(nextLine[k]) : Math.random() * 3000.0;
                	j++;
                	break;
                case "SizeDistribLow2":
                	Xmatrix[i][j] = !nextLine[k].equals("null") ? Double.parseDouble(nextLine[k]) : Math.random() * 3000.0;
                	j++;
                	break;
                case "SizeDistribHigh2":
                	Xmatrix[i][j] = !nextLine[k].equals("null") ? Double.parseDouble(nextLine[k]) : Math.random() * 3000.0;
                	j++;
                	break;
                case "SerumConcentration":
                	Xmatrix[i][j] = !nextLine[k].equals("null") ? Double.parseDouble(nextLine[k]) : Math.random() * 100.0;
                	j++;
                	break;
                case "AntibioticConcentration":
                	Xmatrix[i][j] = !nextLine[k].equals("null") ? Double.parseDouble(nextLine[k]) : Math.random() * 100.0;
                	j++;
                	break;
                case "DOMConcentration":
                	Xmatrix[i][j] = !nextLine[k].equals("null") ? Double.parseDouble(nextLine[k]) : Math.random() * 20.0;
                	j++;
                	break;
                case "SalinityValue":
                	Xmatrix[i][j] = !nextLine[k].equals("null") ? Double.parseDouble(nextLine[k]) : Math.random() * 35.0;
                	j++;
                	break;	
                case "pHAvg":
                	Xmatrix[i][j] = !nextLine[k].equals("null") ? Double.parseDouble(nextLine[k]) : 4.0 + Math.random() * 6.0;
                	j++;
                	break;	
                case "pHLow":
                	Xmatrix[i][j] = !nextLine[k].equals("null") ? Double.parseDouble(nextLine[k]) : Math.random() * 4.0;
                	j++;
                	break;	
                case "pHHigh":
                	Xmatrix[i][j] = !nextLine[k].equals("null") ? Double.parseDouble(nextLine[k]) : 10.0 + Math.random() * 4.0;
                	j++;
                	break;	
                case "MediumTemp":
                	Xmatrix[i][j] = !nextLine[k].equals("null") ? Double.parseDouble(nextLine[k]) : 30.0 * Math.random() + 10.0;
                	j++;
                	break;
                case "TimeValue":
                	Xmatrix[i][j] = !nextLine[k].equals("null") ? Double.parseDouble(nextLine[k]) : 48.0;
                	j++;
                	break;		
                case "ParticleConcentration":
                	Xmatrix[i][j] = !nextLine[k].equals("null") ? Double.parseDouble(nextLine[k]) : Math.random() * 100.0;
                	j++;
                	break;	
                case "ParticleExposDuration":
                	Xmatrix[i][j] = !nextLine[k].equals("null") ? Double.parseDouble(nextLine[k]) : 48.0;
                	j++;
                	break;	
                case "UVADose":
                	Xmatrix[i][j] = !nextLine[k].equals("null") ? Double.parseDouble(nextLine[k]) : Math.random() * 10.0;
                	j++;
                	break;	
                case "UVAExposDuration":
                	Xmatrix[i][j] = !nextLine[k].equals("null") ? Double.parseDouble(nextLine[k]) : Math.random() * 2.0;
                	j++;
                	break;	
                case "ViabilityAvg":
                	Ymatrix[i][jy] = !nextLine[k].equals("null") ? Double.parseDouble(nextLine[k]) : Math.random()*100.0;
                	jy++;
                	break;	
                case "LC50":
                	Ymatrix[i][jy] = !nextLine[k].equals("null") ? Double.parseDouble(nextLine[k]) : Math.random()*100.0;
                	break;	
				}
			}
		}
		
		// Store values in Matrix class
		Matrix.setDataX(Xmatrix);
		Matrix.setDataY(Ymatrix);
		
	}

}
