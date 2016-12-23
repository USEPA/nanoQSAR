import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import nanoQsarMatrixBuilder.Matrix;

import com.opencsv.CSVReader;


/**
 * @author Wilson Melendez
 *
 */
public class CsvMatrix 
{
	private static List<String[]> rows;
	
	public static void readCsvFile(String filename) throws IOException
	{
		FileReader fReader;
		try
		{
			fReader = new FileReader(filename); 
		}
		catch(FileNotFoundException ex)
		{
			System.out.println("CSV file was not found.");
			throw ex;
		}
		
		CSVReader csvInput = new CSVReader(fReader);
		rows = csvInput.readAll();
		
		try
		{
			csvInput.close();
		}
		catch(IOException ex)
		{
			System.out.println("Attempt to close CSV reader failed.");
			throw ex;
		}
	}
	
	public static void buildMatrix()
	{		
		String[] nextLine;
		String[] header = null;
		int rowsSizem2= rows.size() - 2;
		double[][] data = new double[rowsSizem2][71];
			
		header = rows.get(0);
		
		for (int i = 0; i < rowsSizem2; i++)
		{
			nextLine = rows.get(i+1);
			int j = 0;			
			for (int k = 0; k < nextLine.length-1; k++)
			{		
				switch (header[k].trim())
				{
				case "CoatingAmount":
					data[i][j] = !nextLine[k].equals("null") ? Double.parseDouble(nextLine[k]) : 0.0;
					j++;
					break;
				case "Purity":
					data[i][j] = !nextLine[k].equals("null") ? Double.parseDouble(nextLine[k]) : 0.0;
					j++;
					break;	
				case "ContamAl": 
					data[i][j] = !nextLine[k].equals("null") ? Double.parseDouble(nextLine[k]) : 0.0;
					j++;
					break;	
				case "ContamAs": 
					data[i][j] = !nextLine[k].equals("null") ? Double.parseDouble(nextLine[k]) : 0.0;
					j++;
					break;	
				case "ContamBe":
					data[i][j] = !nextLine[k].equals("null") ? Double.parseDouble(nextLine[k]) : 0.0;
					j++;
					break;	
                case "ContamCa":
                	data[i][j] = !nextLine[k].equals("null") ? Double.parseDouble(nextLine[k]) : 0.0;
                	j++;
                	break;	
                case "ContamCo":
                	data[i][j] = !nextLine[k].equals("null") ? Double.parseDouble(nextLine[k]) : 0.0;
                	j++;
                	break;	
                case "ContamCr":
                	data[i][j] = !nextLine[k].equals("null") ? Double.parseDouble(nextLine[k]) : 0.0;
                	j++;
                	break;	
                case "ContamFe":
                	data[i][j] = !nextLine[k].equals("null") ? Double.parseDouble(nextLine[k]) : 0.0;
                	j++;
                	break;	
                case "ContamK":
                	data[i][j] = !nextLine[k].equals("null") ? Double.parseDouble(nextLine[k]) : 0.0;
                	j++;
                	break;	
                case "ContamMg":
                	data[i][j] = !nextLine[k].equals("null") ? Double.parseDouble(nextLine[k]) : 0.0;
                	j++;
                	break;	
                case "ContamNa":
                	data[i][j] = !nextLine[k].equals("null") ? Double.parseDouble(nextLine[k]) : 0.0;
                	j++;
                	break;	
                case "ContamP":
                	data[i][j] = !nextLine[k].equals("null") ? Double.parseDouble(nextLine[k]) : 0.0;
                	j++;
                	break;	
                case "ContamPb":
                	data[i][j] = !nextLine[k].equals("null") ? Double.parseDouble(nextLine[k]) : 0.0;
                	j++;
                	break;	
                case "ContamSb":
                	data[i][j] = !nextLine[k].equals("null") ? Double.parseDouble(nextLine[k]) : 0.0;
                	j++;
                	break;	
                case "ContamSe":
                	data[i][j] = !nextLine[k].equals("null") ? Double.parseDouble(nextLine[k]) : 0.0;
                	j++;
                	break;	
                case "ContamSiO2":
                	data[i][j] = !nextLine[k].equals("null") ? Double.parseDouble(nextLine[k]) : 0.0;
                	j++;
                	break;	
                case "ContamSn":
                	data[i][j] = !nextLine[k].equals("null") ? Double.parseDouble(nextLine[k]) : 0.0;
                	j++;
                	break;	
                case "ContamTl":
                	data[i][j] = !nextLine[k].equals("null") ? Double.parseDouble(nextLine[k]) : 0.0;
                	j++;
                	break;	
                case "ContamV":
                	data[i][j] = !nextLine[k].equals("null") ? Double.parseDouble(nextLine[k]) : 0.0;
                	j++;
                	break;	
                case "ParticleOuterDiamAvg":
                	data[i][j] = !nextLine[k].equals("null") ? Double.parseDouble(nextLine[k]) : 0.0;
                	j++;
                	break;	
                case "ParticleOuterDiamLow":
                	data[i][j] = !nextLine[k].equals("null") ? Double.parseDouble(nextLine[k]) : 0.0;
                	j++;
                	break;	
                case "ParticleOuterDiamHigh":
                	data[i][j] = !nextLine[k].equals("null") ? Double.parseDouble(nextLine[k]) : 0.0;
                	j++;
                	break;	
                case "ParticleInnerDiamAvg":
                	data[i][j] = !nextLine[k].equals("null") ? Double.parseDouble(nextLine[k]) : 0.0;
                	j++;
                	break;	
                case "ParticleInnerDiamLow":
                	data[i][j] = !nextLine[k].equals("null") ? Double.parseDouble(nextLine[k]) : 0.0;
                	j++;
                	break;	
                case "ParticleInnerDiamHigh":
                	data[i][j] = !nextLine[k].equals("null") ? Double.parseDouble(nextLine[k]) : 0.0;
                	j++;
                	break;	
                case "ParticleLengthAvg":
                	data[i][j] = !nextLine[k].equals("null") ? Double.parseDouble(nextLine[k]) : 0.0;
                	j++;
                	break;	
                case "ParticleLengthLow":
                	data[i][j] = !nextLine[k].equals("null") ? Double.parseDouble(nextLine[k]) : 0.0;
                	j++;
                	break;	
                case "ParticleLengthHigh":
                	data[i][j] = !nextLine[k].equals("null") ? Double.parseDouble(nextLine[k]) : 0.0;
                	j++;
                	break;	
                case "ParticleThicknessAvg":
                	data[i][j] = !nextLine[k].equals("null") ? Double.parseDouble(nextLine[k]) : 0.0;
                	j++;
                	break;	
                case "ParticleThicknessLow":
                	data[i][j] = !nextLine[k].equals("null") ? Double.parseDouble(nextLine[k]) : 0.0;
                	j++;
                	break;	
                case "ParticleThicknessHigh":
                	data[i][j] = !nextLine[k].equals("null") ? Double.parseDouble(nextLine[k]) : 0.0;
                	j++;
                	break;	
                case "SurfaceAreaAvg":
                	data[i][j] = !nextLine[k].equals("null") ? Double.parseDouble(nextLine[k]) : 0.0;
                	j++;
                	break;		
                case "SurfaceAreaLow":
                	data[i][j] = !nextLine[k].equals("null") ? Double.parseDouble(nextLine[k]) : 0.0;
                	j++;
                	break;		
                case "SurfaceAreaHigh":
                	data[i][j] = !nextLine[k].equals("null") ? Double.parseDouble(nextLine[k]) : 0.0;
                	j++;
                	break;	
                case "MC_TimeValue":
                	data[i][j] = !nextLine[k].equals("null") ? Double.parseDouble(nextLine[k]) : 0.0;
                	j++;
                	break;		
                case "MC_ParticleConcentration":
                	data[i][j] = !nextLine[k].equals("null") ? Double.parseDouble(nextLine[k]) : 0.0;
                	j++;
                	break;	
                case "MC_SerumConcentration":
                	data[i][j] = !nextLine[k].equals("null") ? Double.parseDouble(nextLine[k]) : 0.0;
                	j++;
                	break;		
                case "MC_AntibioticConcentration":
                	data[i][j] = !nextLine[k].equals("null") ? Double.parseDouble(nextLine[k]) : 0.0;
                	j++;
                	break;	
                case "MC_DOMConcentration":
                	data[i][j] = !nextLine[k].equals("null") ? Double.parseDouble(nextLine[k]) : 0.0;
                	j++;
                	break;	
                case "MC_SalinityValue":
                	data[i][j] = !nextLine[k].equals("null") ? Double.parseDouble(nextLine[k]) : 0.0;
                	j++;
                	break;	
                case "MC_pHAvg":
                	data[i][j] = !nextLine[k].equals("null") ? Double.parseDouble(nextLine[k]) : 0.0;
                	j++;
                	break;	
                case "MC_pHLow":
                	data[i][j] = !nextLine[k].equals("null") ? Double.parseDouble(nextLine[k]) : 0.0;
                	j++;
                	break;	
                case "MC_pHHigh":
                	data[i][j] = !nextLine[k].equals("null") ? Double.parseDouble(nextLine[k]) : 0.0;
                	j++;
                	break;	
                case "MC_MediumTemp":
                	data[i][j] = !nextLine[k].equals("null") ? Double.parseDouble(nextLine[k]) : 0.0;
                	j++;
                	break;	
                case "ZetaPotentialAvg":
                	data[i][j] = !nextLine[k].equals("null") ? Double.parseDouble(nextLine[k]) : 0.0;
                	j++;
                	break;	
                case "ZetaPotentialLow":
                	data[i][j] = !nextLine[k].equals("null") ? Double.parseDouble(nextLine[k]) : 0.0;
                	j++;
                	break;	
                case "ZetaPotentialHigh":
                	data[i][j] = !nextLine[k].equals("null") ? Double.parseDouble(nextLine[k]) : 0.0;
                	j++;
                	break;	
                case "SizeDistribAvg":
                	data[i][j] = !nextLine[k].equals("null") ? Double.parseDouble(nextLine[k]) : 0.0;
                	j++;
                	break;
                case "SizeDistribLow":
                	data[i][j] = !nextLine[k].equals("null") ? Double.parseDouble(nextLine[k]) : 0.0;
                	j++;
                	break;
                case "SizeDistribHigh":
                	data[i][j] = !nextLine[k].equals("null") ? Double.parseDouble(nextLine[k]) : 0.0;
                	j++;
                	break;
                case "SizeDistribAvg2":
                	data[i][j] = !nextLine[k].equals("null") ? Double.parseDouble(nextLine[k]) : 0.0;
                	j++;
                	break;
                case "SizeDistribLow2":
                	data[i][j] = !nextLine[k].equals("null") ? Double.parseDouble(nextLine[k]) : 0.0;
                	j++;
                	break;
                case "SizeDistribHigh2":
                	data[i][j] = !nextLine[k].equals("null") ? Double.parseDouble(nextLine[k]) : 0.0;
                	j++;
                	break;
                case "SerumConcentration":
                	data[i][j] = !nextLine[k].equals("null") ? Double.parseDouble(nextLine[k]) : 0.0;
                	j++;
                	break;
                case "AntibioticConcentration":
                	data[i][j] = !nextLine[k].equals("null") ? Double.parseDouble(nextLine[k]) : 0.0;
                	j++;
                	break;
                case "DOMConcentration":
                	data[i][j] = !nextLine[k].equals("null") ? Double.parseDouble(nextLine[k]) : 0.0;
                	j++;
                	break;
                case "SalinityValue":
                	data[i][j] = !nextLine[k].equals("null") ? Double.parseDouble(nextLine[k]) : 0.0;
                	j++;
                	break;	
                case "pHAvg":
                	data[i][j] = !nextLine[k].equals("null") ? Double.parseDouble(nextLine[k]) : 0.0;
                	j++;
                	break;	
                case "pHLow":
                	data[i][j] = !nextLine[k].equals("null") ? Double.parseDouble(nextLine[k]) : 0.0;
                	j++;
                	break;	
                case "pHHigh":
                	data[i][j] = !nextLine[k].equals("null") ? Double.parseDouble(nextLine[k]) : 0.0;
                	j++;
                	break;	
                case "MediumTemp":
                	data[i][j] = !nextLine[k].equals("null") ? Double.parseDouble(nextLine[k]) : 0.0;
                	j++;
                	break;
                case "TimeValue":
                	data[i][j] = !nextLine[k].equals("null") ? Double.parseDouble(nextLine[k]) : 0.0;
                	j++;
                	break;		
                case "ParticleConcentration":
                	data[i][j] = !nextLine[k].equals("null") ? Double.parseDouble(nextLine[k]) : 0.0;
                	j++;
                	break;	
                case "ParticleExposDuration":
                	data[i][j] = !nextLine[k].equals("null") ? Double.parseDouble(nextLine[k]) : 0.0;
                	j++;
                	break;	
                case "UVADose":
                	data[i][j] = !nextLine[k].equals("null") ? Double.parseDouble(nextLine[k]) : 0.0;
                	j++;
                	break;	
                case "UVAExposDuration":
                	data[i][j] = !nextLine[k].equals("null") ? Double.parseDouble(nextLine[k]) : 0.0;
                	j++;
                	break;	
                case "ViabilityAvg":
                	data[i][j] = !nextLine[k].equals("null") ? Double.parseDouble(nextLine[k]) : 0.0;
                	j++;
                	break;	
                case "ViabilityLow":
                	data[i][j] = !nextLine[k].equals("null") ? Double.parseDouble(nextLine[k]) : 0.0;
                	j++;
                	break;	
                case "ViabilityHigh":
                	data[i][j] = !nextLine[k].equals("null") ? Double.parseDouble(nextLine[k]) : 0.0;
                	j++;
                	break;	
                case "LC50":
                	data[i][j] = !nextLine[k].equals("null") ? Double.parseDouble(nextLine[k]) : 0.0;
                	//System.out.println("j = " + j);
                	break;	
				}
			}
		}
		
		// Store values in Matrix class
		Matrix D = new Matrix(data);
		
	}

}
