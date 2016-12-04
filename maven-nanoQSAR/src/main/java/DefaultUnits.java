/**
 * 
 */

import java.util.List;

/**
 * This class checks for default units of fields and converts units and its corresponding
 * values to defaults for entries that have non-default units.
 * handles conversion of units 
 * @author Wilson Melendez
 * 
 */
public class DefaultUnits 
{
	/**
	 * @author Wilson Melendez
	 * @param strU 
	 * @return String strTemp
	 * @throws IllegalUnitsException
	 */
	public static String checkDefaultCoatingAmountUnit(String strU) throws IllegalUnitsException
	{
		String strTemp = null;
		
		if (strU.trim().equals("ug") || strU.trim().equals("null"))
		{
			strTemp = strU;
		}
		else if (strU.trim().equals("mg"))
		{
			strTemp = "ug";
		}
		else
		{
			throw new IllegalUnitsException("Illegal coating amount units: " + strU);
		}
		return strTemp;
	}
	
	/**
	 * @author Wilson Melendez
	 * @param strU
	 * @param oldValue
	 * @return Double newValue
	 * @throws IllegalUnitsException
	 */
	public static Double checkDefaultCoatingAmount(String strU, Double oldValue) throws IllegalUnitsException
	{
	    Double newValue;
	    
		if (strU.trim().equals("ug") || strU.trim().equals("null"))
		{
			newValue = oldValue;
		}
		else if (strU.trim().equals("mg"))
		{
			newValue = oldValue * 1000.0;
		}
		else
		{
			throw new IllegalUnitsException("Illegal coating amount units: " + strU + " " + oldValue);
		}
			
		return newValue;
	}

	/**
	 * @author Wilson Melendez
	 * @param strU 
	 * @return String strTemp
	 * @throws IllegalUnitsException
	 */
	public static String checkDefaultPurityUnit(String strU) throws IllegalUnitsException
	{
		String strTemp = null;
		
		if (strU.trim().equals("%") || strU.trim().equals("null"))
		{
			strTemp = strU;
		}
		else
		{
			throw new IllegalUnitsException("Illegal purity units: " + strU);
		}
		return strTemp;
	}
	
	/**
	 * @author Wilson Melendez
	 * @param strU
	 * @param oldValue
	 * @return Double newValue
	 * @throws IllegalUnitsException
	 */
	public static Double checkDefaultPurity(String strU, Double oldValue) throws IllegalUnitsException
	{
	    Double newValue;
	    
		if (strU.trim().equals("%") || strU.trim().equals("null"))
		{
			newValue = oldValue;
		}
		else
		{
			throw new IllegalUnitsException("Illegal purity units: " + strU + " " + oldValue);
		}
			
		return newValue;
	}
	
	/**
	 * @author Wilson Melendez
	 * @param strU 
	 * @return String strTemp
	 * @throws IllegalUnitsException
	 */
	public static String checkDefaultContaminantUnit(String strU) throws IllegalUnitsException
	{
		String strTemp = null;
		
		if (strU.trim().equals("ppm") || strU.trim().equals("null"))
		{
			strTemp = strU;
		}
		else if (strU.trim().equals("ppt"))
		{
			strTemp = "ppm";
		}
		else
		{
			throw new IllegalUnitsException("Illegal contaminant units: " + strU);
		}
		return strTemp;
	}
	
	/**
	 * @author Wilson Melendez
	 * @param strU
	 * @param oldValue
	 * @return Double newValue
	 * @throws IllegalUnitsException
	 */
	public static Double checkDefaultContaminant(String strU, Double oldValue) throws IllegalUnitsException
	{
	    Double newValue;
	    
		if (strU.trim().equals("ppm") || strU.trim().equals("null"))
		{
			newValue = oldValue;
		}
		else if (strU.trim().equals("ppt"))
		{
			newValue = oldValue * 1000.0;
		}
		else
		{
			throw new IllegalUnitsException("Illegal contaminant units: " + strU + " " + oldValue);
		}
			
		return newValue;
	}
	
	/**
	 * @author Wilson Melendez
	 * @param strU 
	 * @return String strTemp
	 * @throws IllegalUnitsException
	 */
	public static String checkDefaultParticleOuterDiameterUnit(String strU) throws IllegalUnitsException
	{
		String strTemp = null;
		
		if (strU.trim().equals("nm") || strU.trim().equals("null"))
		{
			strTemp = strU;
		}
		else if (strU.trim().equals("um"))
		{
			strTemp = "nm";
		}
		else
		{
			throw new IllegalUnitsException("Illegal particle outer diameter units: " + strU);
		}
		return strTemp;
	}
	
	/**
	 * @author Wilson Melendez
	 * @param strU
	 * @param oldValue
	 * @return Double newValue
	 * @throws IllegalUnitsException
	 */
	public static Double checkDefaultParticleOuterDiameter(String strU, Double oldValue) throws IllegalUnitsException
	{
	    Double newValue;
	    
		if (strU.trim().equals("nm") || strU.trim().equals("null"))
		{
			newValue = oldValue;
		}
		else if (strU.trim().equals("um"))
		{
			newValue = oldValue * 1000.0;
		}
		else
		{
			throw new IllegalUnitsException("Illegal particle outer diameter units: " + strU + " " + oldValue);
		}
			
		return newValue;
	}
	
	/**
	 * @author Wilson Melendez
	 * @param strU 
	 * @return String strTemp
	 * @throws IllegalUnitsException
	 */
	public static String checkDefaultParticleInnerDiameterUnit(String strU) throws IllegalUnitsException
	{
		String strTemp = null;
		
		if (strU.trim().equals("nm") || strU.trim().equals("null"))
		{
			strTemp = strU;
		}
		else if (strU.trim().equals("um"))
		{
			strTemp = "nm";
		}
		else
		{
			throw new IllegalUnitsException("Illegal particle inner diameter units: " + strU);
		}
		return strTemp;
	}
	
	/**
	 * @author Wilson Melendez
	 * @param strU
	 * @param oldValue
	 * @return Double newValue
	 * @throws IllegalUnitsException
	 */
	public static Double checkDefaultParticleInnerDiameter(String strU, Double oldValue) throws IllegalUnitsException
	{
	    Double newValue;
	    
		if (strU.trim().equals("nm") || strU.trim().equals("null"))
		{
			newValue = oldValue;
		}
		else if (strU.trim().equals("um"))
		{
			newValue = oldValue * 1000.0;
		}
		else
		{
			throw new IllegalUnitsException("Illegal particle inner diameter units: " + strU + " " + oldValue);
		}
			
		return newValue;
	}
	
	/**
	 * @author Wilson Melendez
	 * @param strU 
	 * @return String strTemp
	 * @throws IllegalUnitsException
	 */
	public static String checkDefaultParticleLengthUnit(String strU) throws IllegalUnitsException
	{
		String strTemp = null;
		
		if (strU.trim().equals("nm") || strU.trim().equals("null"))
		{
			strTemp = strU;
		}
		else if (strU.trim().equals("um"))
		{
			strTemp = "nm";
		}
		else
		{
			throw new IllegalUnitsException("Illegal particle length units: " + strU);
		}
		return strTemp;
	}
	
	/**
	 * @author Wilson Melendez
	 * @param strU
	 * @param oldValue
	 * @return Double newValue
	 * @throws IllegalUnitsException
	 */
	public static Double checkDefaultParticleLength(String strU, Double oldValue) throws IllegalUnitsException
	{
	    Double newValue;
	    
		if (strU.trim().equals("nm") || strU.trim().equals("null"))
		{
			newValue = oldValue;
		}
		else if (strU.trim().equals("um"))
		{
			newValue = oldValue * 1000.0;
		}
		else
		{
			throw new IllegalUnitsException("Illegal particle length units: " + strU + " " + oldValue);
		}
			
		return newValue;
	}
	
	/**
	 * @author Wilson Melendez
	 * @param strU 
	 * @return String strTemp
	 * @throws IllegalUnitsException
	 */
	public static String checkDefaultParticleThicknessUnit(String strU) throws IllegalUnitsException
	{
		String strTemp = null;
		
		if (strU.trim().equals("nm") || strU.trim().equals("null"))
		{
			strTemp = strU;
		}
		else if (strU.trim().equals("um"))
		{
			strTemp = "nm";
		}
		else
		{
			throw new IllegalUnitsException("Illegal particle thickness units: " + strU);
		}
		return strTemp;
	}
	
	/**
	 * @author Wilson Melendez
	 * @param strU
	 * @param oldValue
	 * @return Double newValue
	 * @throws IllegalUnitsException
	 */
	public static Double checkDefaultParticleThickness(String strU, Double oldValue) throws IllegalUnitsException
	{
	    Double newValue;
	    
		if (strU.trim().equals("nm") || strU.trim().equals("null"))
		{
			newValue = oldValue;
		}
		else if (strU.trim().equals("um"))
		{
			newValue = oldValue * 1000.0;
		}
		else
		{
			throw new IllegalUnitsException("Illegal particle thickness units: " + strU + " " + oldValue);
		}
			
		return newValue;
	}
	
	/**
	 * @author Wilson Melendez
	 * @param strU 
	 * @return String strTemp
	 * @throws IllegalUnitsException
	 */
	public static String checkDefaultSurfaceAreaUnit(String strU) throws IllegalUnitsException
	{
		String strTemp = null;
		
		if (strU.trim().equals("m^2/g") || strU.trim().equals("null"))
		{
			strTemp = strU;
		}
		else if (strU.trim().equals("cm^2/g"))
		{
			strTemp = "m^2/g";
		}
		else
		{
			throw new IllegalUnitsException("Illegal surface area units: " + strU);
		}
		return strTemp;
	}
	
	/**
	 * @author Wilson Melendez
	 * @param strU
	 * @param oldValue
	 * @return Double newValue
	 * @throws IllegalUnitsException
	 */
	public static Double checkDefaultSurfaceArea(String strU, Double oldValue) throws IllegalUnitsException
	{
	    Double newValue;
	    
		if (strU.trim().equals("m^2/g") || strU.trim().equals("null"))
		{
			newValue = oldValue;
		}
		else if (strU.trim().equals("cm^2/g"))
		{
			newValue = oldValue * 1.0E-04;
		}
		else
		{
			throw new IllegalUnitsException("Illegal surface area units: " + strU + " " + oldValue);
		}
			
		return newValue;
	}
	
	/**
	 * @author Wilson Melendez
	 * @param strU 
	 * @return String strTemp
	 * @throws IllegalUnitsException
	 */	
	public static String checkDefaultTimeValueUnit(String strU) throws IllegalUnitsException
	{
		String strTemp = null;
		
		if (strU.trim().equals("hours") || strU.trim().equals("null"))
		{
			strTemp = strU;
		}
		else if (strU.trim().equals("minutes"))
		{
			strTemp = "hours";
		}
		else
		{
			throw new IllegalUnitsException("Illegal time value units: " + strU);
		}
		return strTemp;
	}
	
	/**
	 * @author Wilson Melendez
	 * @param strU
	 * @param oldValue
	 * @return Integer newValue
	 * @throws IllegalUnitsException
	 */
	public static Integer checkDefaultTimeValue(String strU, Integer oldValue) throws IllegalUnitsException
	{
	    Integer newValue;
	    
		if (strU.trim().equals("hours") || strU.trim().equals("null"))
		{
			newValue = oldValue;
		}
		else if (strU.trim().equals("minutes"))
		{
			newValue = oldValue / 60;
		}
		else
		{
			throw new IllegalUnitsException("Illegal time value units: " + strU + " " + oldValue);
		}
			
		return newValue;
	}
	
	/**
	 * @author Wilson Melendez
	 * @param strU
	 * @param oldValue
	 * @return Double newValue
	 * @throws IllegalUnitsException
	 */
	public static Double checkDefaultTimeValue(String strU, Double oldValue) throws IllegalUnitsException
	{
	    Double newValue;
	    
		if (strU.trim().equals("hours") || strU.trim().equals("null"))
		{
			newValue = oldValue;
		}
		else if (strU.trim().equals("minutes"))
		{
			newValue = oldValue / 60;
		}
		else
		{
			throw new IllegalUnitsException("Illegal time value units: " + strU + " " + oldValue);
		}
			
		return newValue;
	}
	
	/**
	 * @author Wilson Melendez
	 * @param strU 
	 * @return String strTemp
	 * @throws IllegalUnitsException
	 */
	public static String checkDefaultParticleConcentrationUnit(String strU) throws IllegalUnitsException
	{
		String strTemp = null;
		
		if (strU.trim().equals("ug/mL") || strU.trim().equals("null"))
		{
			strTemp = strU;
		}
		else if (strU.trim().equals("mg/mL"))
		{
			strTemp = "ug/mL";
		}
		else
		{
			throw new IllegalUnitsException("Illegal particle concentration units: " + strU);
		}
		return strTemp;
	}
	
	/**
	 * @author Wilson Melendez
	 * @param strU
	 * @param oldValue
	 * @return Double newValue
	 * @throws IllegalUnitsException
	 */
	public static Double checkDefaultParticleConcentration(String strU, Double oldValue) throws IllegalUnitsException
	{
	    Double newValue;
	    
		if (strU.trim().equals("ug/mL") || strU.trim().equals("null"))
		{
			newValue = oldValue;
		}
		else if (strU.trim().equals("mg/mL"))
		{
			newValue = oldValue * 1.0E+03;
		}
		else
		{
			throw new IllegalUnitsException("Illegal particle concentration units: " + strU + " " + oldValue);
		}
			
		return newValue;
	}
	
	/**
	 * @author Wilson Melendez
	 * @param strU 
	 * @return String strTemp
	 * @throws IllegalUnitsException
	 */
	public static String checkDefaultMediumTempUnit(String strU) throws IllegalUnitsException
	{
		String strTemp = null;
		
		if (strU.trim().equals("C") || strU.trim().equals("null"))
		{
			strTemp = strU;
		}
		else if (strU.trim().equals("F"))
		{
			strTemp = "C";
		}
		else if (strU.trim().equals("K"))
		{
			strTemp = "C";
		}
		else
		{
			throw new IllegalUnitsException("Illegal medium temperature units: " + strU);
		}
		return strTemp;
	}
	
	/**
	 * @author Wilson Melendez
	 * @param strU
	 * @param oldValue
	 * @return Double newValue
	 * @throws IllegalUnitsException
	 */
	public static Double checkDefaultMediumTemp(String strU, Double oldValue) throws IllegalUnitsException
	{
	    Double newValue;
	    
		if (strU.trim().equals("C") || strU.trim().equals("null"))
		{
			newValue = oldValue;
		}
		else if (strU.trim().equals("F"))
		{
			newValue = (oldValue - 32.0) * 5.0 / 9.0;
		}
		else if (strU.trim().equals("K"))
		{
			newValue = oldValue - 273.15;
		}
		else
		{
			throw new IllegalUnitsException("Illegal mediumm temperature units: " + strU + " " + oldValue);
		}
			
		return newValue;
	}
	
	/**
	 * @author Wilson Melendez
	 * @param strU 
	 * @return String strTemp
	 * @throws IllegalUnitsException
	 */
	public static String checkDefaultZetaPotentialUnit(String strU) throws IllegalUnitsException
	{
		String strTemp = null;
		
		if (strU.trim().equals("mV") || strU.trim().equals("null"))
		{
			strTemp = strU;
		}
		else if (strU.trim().equals("uV"))
		{
			strTemp = "mV";
		}
		else
		{
			throw new IllegalUnitsException("Illegal zeta potential units: " + strU);
		}
		return strTemp;
	}
	
	/**
	 * @author Wilson Melendez
	 * @param strU
	 * @param oldValue
	 * @return Double newValue
	 * @throws IllegalUnitsException
	 */
	public static Double checkDefaultZetaPotential(String strU, Double oldValue) throws IllegalUnitsException
	{
	    Double newValue;
	    
		if (strU.trim().equals("mV") || strU.trim().equals("null"))
		{
			newValue = oldValue;
		}
		else if (strU.trim().equals("uV"))
		{
			newValue = oldValue * 1.0E-03;
		}
		else
		{
			throw new IllegalUnitsException("Illegal zeta potential units: " + strU + " " + oldValue);
		}
			
		return newValue;
	}
	
	/**
	 * @author Wilson Melendez
	 * @param strU 
	 * @return String strTemp
	 * @throws IllegalUnitsException
	 */
	public static String checkDefaultSizeDistributionUnit(String strU) throws IllegalUnitsException
	{
		String strTemp = null;
		
		if (strU.trim().equals("nm") || strU.trim().equals("null"))
		{
			strTemp = strU;
		}
		else if (strU.trim().equals("um"))
		{
			strTemp = "nm";
		}
		else
		{
			throw new IllegalUnitsException("Illegal size distribution units: " + strU);
		}
		return strTemp;
	}
	
	/**
	 * @author Wilson Melendez
	 * @param strU
	 * @param oldValue
	 * @return Double newValue
	 * @throws IllegalUnitsException
	 */
	public static Double checkDefaultSizeDistribution(String strU, Double oldValue) throws IllegalUnitsException
	{
	    Double newValue;
	    
		if (strU.trim().equals("nm") || strU.trim().equals("null"))
		{
			newValue = oldValue;
		}
		else if (strU.trim().equals("um"))
		{
			newValue = oldValue * 1.0E+03;
		}
		else
		{
			throw new IllegalUnitsException("Illegal size distribution units: " + strU + " " + oldValue);
		}
			
		return newValue;
	}
	
	/**
	 * @author Wilson Melendez
	 * @param strU 
	 * @return String strTemp
	 * @throws IllegalUnitsException
	 */
	public static String checkDefaultSizeDistributionUnit2(String strU) throws IllegalUnitsException
	{
		String strTemp = null;
		
		if (strU.trim().equals("nm") || strU.trim().equals("null"))
		{
			strTemp = strU;
		}
		else if (strU.trim().equals("um"))
		{
			strTemp = "nm";
		}
		else
		{
			throw new IllegalUnitsException("Illegal size distribution 2 units: " + strU);
		}
		return strTemp;
	}
	
	/**
	 * @author Wilson Melendez
	 * @param strU
	 * @param oldValue
	 * @return Double newValue
	 * @throws IllegalUnitsException
	 */
	public static Double checkDefaultSizeDistribution2(String strU, Double oldValue) throws IllegalUnitsException
	{
	    Double newValue;
	    
		if (strU.trim().equals("nm") || strU.trim().equals("null"))
		{
			newValue = oldValue;
		}
		else if (strU.trim().equals("um"))
		{
			newValue = oldValue * 1.0E+03;
		}
		else
		{
			throw new IllegalUnitsException("Illegal size distribution 2 units: " + strU + " " + oldValue);
		}
			
		return newValue;
	}
	
	/**
	 * @author Wilson Melendez
	 * @param strU 
	 * @return String strTemp
	 * @throws IllegalUnitsException
	 */
	public static String checkDefaultSerumConcentrationUnit(String strU) throws IllegalUnitsException
	{
		String strTemp = null;
		
		if (strU.trim().equals("%") || strU.trim().equals("null"))
		{
			strTemp = strU;
		}
		else
		{
			throw new IllegalUnitsException("Illegal serum concentration units: " + strU);
		}
		return strTemp;
	}
	
	/**
	 * @author Wilson Melendez
	 * @param strU
	 * @param oldValue
	 * @return Double newValue
	 * @throws IllegalUnitsException
	 */
	public static Double checkDefaultSerumConcentration(String strU, Double oldValue) throws IllegalUnitsException
	{
	    Double newValue;
	    
		if (strU.trim().equals("%") || strU.trim().equals("null"))
		{
			newValue = oldValue;
		}
		else
		{
			throw new IllegalUnitsException("Illegal serum concentration units: " + strU + " " + oldValue);
		}
			
		return newValue;
	}
	
	/**
	 * @author Wilson Melendez
	 * @param strU 
	 * @return String strTemp
	 * @throws IllegalUnitsException
	 */
	public static String checkDefaultAntibioticConcentrationUnit(String strU) throws IllegalUnitsException
	{
		String strTemp = null;
		
		if (strU.trim().equals("%") || strU.trim().equals("null"))
		{
			strTemp = strU;
		}
		else
		{
			throw new IllegalUnitsException("Illegal antibiotic concentration units: " + strU);
		}
		return strTemp;
	}
	
	/**
	 * @author Wilson Melendez
	 * @param strU
	 * @param oldValue
	 * @return Double newValue
	 * @throws IllegalUnitsException
	 */
	public static Double checkDefaultAntibioticConcentration(String strU, Double oldValue) throws IllegalUnitsException
	{
	    Double newValue;
	    
		if (strU.trim().equals("%") || strU.trim().equals("null"))
		{
			newValue = oldValue;
		}
		else
		{
			throw new IllegalUnitsException("Illegal antibiotic concentration units: " + strU + " " + oldValue);
		}
			
		return newValue;
	}
	
	/**
	 * @author Wilson Melendez
	 * @param strU 
	 * @return String strTemp
	 * @throws IllegalUnitsException
	 */
	public static String checkDefaultDOMUnit(String strU) throws IllegalUnitsException
	{
		String strTemp = null;
		
		if (strU.trim().equals("mg/L") || strU.trim().equals("null"))
		{
			strTemp = strU;
		}
		else if (strU.trim().equals("ug/L"))
		{
			strTemp = "mg/L";
		}
		else
		{
			throw new IllegalUnitsException("Illegal DOM units: " + strU);
		}
		return strTemp;
	}
	
	/**
	 * @author Wilson Melendez
	 * @param strU
	 * @param oldValue
	 * @return Double newValue
	 * @throws IllegalUnitsException
	 */
	public static Double checkDefaultDOMConcentration(String strU, Double oldValue) throws IllegalUnitsException
	{
	    Double newValue;
	    
		if (strU.trim().equals("mg/L") || strU.trim().equals("null"))
		{
			newValue = oldValue;
		}
		else if (strU.trim().equals("ug/L"))
		{
			newValue = oldValue / 1000.0;
		}
		else
		{
			throw new IllegalUnitsException("Illegal DOM units: " + strU + " " + oldValue);
		}
			
		return newValue;
	}
	
	/**
	 * @author Wilson Melendez
	 * @param strU 
	 * @return String strTemp
	 * @throws IllegalUnitsException
	 */
	public static String checkDefaultSalinityUnit(String strU) throws IllegalUnitsException
	{
		String strTemp = null;
		
		if (strU.trim().equals("ppt") || strU.trim().equals("psu") || strU.trim().equals("null"))
		{
			strTemp = strU;
		}
		else
		{
			throw new IllegalUnitsException("Illegal salinity units: " + strU);
		}
		return strTemp;
	}
	
	/**
	 * @author Wilson Melendez
	 * @param strU
	 * @param oldValue
	 * @return Double newValue
	 * @throws IllegalUnitsException
	 */
	public static Double checkDefaultSalinityValue(String strU, Double oldValue) throws IllegalUnitsException
	{
	    Double newValue;
	    
		if (strU.trim().equals("ppt") || strU.trim().equals("psu") || strU.trim().equals("g/kg") || strU.trim().equals("null"))
		{
			newValue = oldValue;
		}
		else
		{
			throw new IllegalUnitsException("Illegal salinity units: " + strU + " " + oldValue);
		}
			
		return newValue;
	}
	
	/**
	 * @author Wilson Melendez
	 * @param strU 
	 * @return String strTemp
	 * @throws IllegalUnitsException
	 */
	public static String checkDefaultParticleExposureDurationUnit(String strU) throws IllegalUnitsException
	{
		String strTemp = null;
		
		if (strU.trim().equals("hours") || strU.trim().equals("null"))
		{
			strTemp = strU;
		}
		else if (strU.trim().equals("minutes"))
		{
			strTemp = "hours";
		}
		else
		{
			throw new IllegalUnitsException("Illegal exposoure duration units: " + strU);
		}
		return strTemp;
	}
	
	/**
	 * @author Wilson Melendez
	 * @param strU
	 * @param oldValue
	 * @return Double newValue
	 * @throws IllegalUnitsException
	 */
	public static Double checkDefaultParticleExposureDuration(String strU, Double oldValue) throws IllegalUnitsException
	{
	    Double newValue;
	    
		if (strU.trim().equals("hours") || strU.trim().equals("null"))
		{
			newValue = oldValue;
		}
		else if (strU.trim().equals("minutes"))
		{
			newValue = oldValue / 60.0;
		}
		else
		{
			throw new IllegalUnitsException("Illegal exposure duration units: " + strU + " " + oldValue);
		}
			
		return newValue;
	}
	
	/**
	 * @author Wilson Melendez
	 * @param strU 
	 * @return String strTemp
	 * @throws IllegalUnitsException
	 */
	public static String checkDefaultUVADoseUnit(String strU) throws IllegalUnitsException
	{
		String strTemp = null;
		
		if (strU.trim().equals("J/cm^2") || strU.trim().equals("null"))
		{
			strTemp = strU;
		}
		else if (strU.trim().equals("mJ/cm^2"))
		{
			strTemp = "J/cm^2";
		}
		else
		{
			throw new IllegalUnitsException("Illegal UVA dose units: " + strU);
		}
		return strTemp;
	}
	
	/**
	 * @author Wilson Melendez
	 * @param strU
	 * @param oldValue
	 * @return Double newValue
	 * @throws IllegalUnitsException
	 */
	public static Double checkDefaultUVADose(String strU, Double oldValue) throws IllegalUnitsException
	{
	    Double newValue;
	    
		if (strU.trim().equals("J/cm^2") || strU.trim().equals("null"))
		{
			newValue = oldValue;
		}
		else if (strU.trim().equals("mJ/cm^2"))
		{
			newValue = oldValue / 1000.0;
		}
		else
		{
			throw new IllegalUnitsException("Illegal UVA dose units: " + strU + " " + oldValue);
		}
			
		return newValue;
	}
	
	/**
	 * @author Wilson Melendez
	 * @param strU 
	 * @return String strTemp
	 * @throws IllegalUnitsException
	 */
	public static String checkDefaultUVAExposureDurationUnit(String strU) throws IllegalUnitsException
	{
		String strTemp = null;
		
		if (strU.trim().equals("hours") || strU.trim().equals("null"))
		{
			strTemp = strU;
		}
		else if (strU.trim().equals("minutes"))
		{
			strTemp = "hours";
		}
		else
		{
			throw new IllegalUnitsException("Illegal units: " + strU);
		}
		return strTemp;
	}
	
	/**
	 * @author Wilson Melendez
	 * @param strU
	 * @param oldValue
	 * @return Double newValue
	 * @throws IllegalUnitsException
	 */
	public static Double checkDefaultUVAExposureDuration(String strU, Double oldValue) throws IllegalUnitsException
	{
	    Double newValue;
	    
		if (strU.trim().equals("hours") || strU.trim().equals("null"))
		{
			newValue = oldValue;
		}
		else if (strU.trim().equals("minutes"))
		{
			newValue = oldValue / 60.0;
		}
		else
		{
			throw new IllegalUnitsException("Illegal units: " + strU + " " + oldValue);
		}
			
		return newValue;
	}
	
	/**
	 * @author Wilson Melendez
	 * @param strU 
	 * @return String strTemp
	 * @throws IllegalUnitsException
	 */
	public static String checkDefaultViabilityUnit(String strU) throws IllegalUnitsException
	{
		String strTemp = null;
		
		if (strU.trim().equals("%") || strU.trim().equals("null"))
		{
			strTemp = strU;
		}
		else
		{
			throw new IllegalUnitsException("Illegal units: " + strU);
		}
		return strTemp;
	}
	
	/**
	 * @author Wilson Melendez
	 * @param strU
	 * @param oldValue
	 * @return Double newValue
	 * @throws IllegalUnitsException
	 */
	public static Double checkDefaultViability(String strU, Double oldValue) throws IllegalUnitsException
	{
	    Double newValue;
	    
		if (strU.trim().equals("%") || strU.trim().equals("null"))
		{
			newValue = oldValue;
		}
		else
		{
			throw new IllegalUnitsException("Illegal units: " + strU + " " + oldValue);
		}
			
		return newValue;
	}
	
	/**
	 * @author Wilson Melendez
	 * @param strU 
	 * @return String strTemp
	 * @throws IllegalUnitsException
	 */
	public static String checkDefaultLC50Unit(String strU) throws IllegalUnitsException
	{
		String strTemp = null;
		
		if (strU.trim().equals("ug/mL") || strU.trim().equals("null"))
		{
			strTemp = strU;
		}
		else if (strU.trim().equals("mg/mL"))
		{
			strTemp = "ug/mL";
		}
		else
		{
			throw new IllegalUnitsException("Illegal units: " + strU);
		}
		return strTemp;
	}
	
	/**
	 * @author Wilson Melendez
	 * @param strU
	 * @param oldValue
	 * @return Double newValue
	 * @throws IllegalUnitsException
	 */
	public static Double checkDefaultLC50(String strU, Double oldValue) throws IllegalUnitsException
	{
	    Double newValue;
	    
		if (strU.trim().equals("ug/mL") || strU.trim().equals("null"))
		{
			newValue = oldValue;
		}
		else if (strU.trim().equals("mg/mL"))
		{
			newValue = oldValue * 1000.0;
		}
		else
		{
			throw new IllegalUnitsException("Illegal units: " + strU + " " + oldValue);
		}
			
		return newValue;
	}
	
	/**
	 * This method loops over the list array and passes the units and values
	 * to their corresponding methods for inspection of default units and conversion 
	 * of found non-default units, and updates units and values of fields in the list array.
	 * @author Wilson Melendez
	 * @param listNano
	 * @throws IllegalUnitsException
	 */
	public static void checkUnits(List<NanoMaterial> listNano) throws IllegalUnitsException
	{
		String oldColumnUnits, newColumnUnits;
		Double fieldValue;
		Integer fieldValueInt;
		
		try 
		{
			for(int i = 0; i < listNano.size(); i++)  
			{				
				/* Coating amount on nanoparticle */ 
				oldColumnUnits = String.valueOf(listNano.get(i).getCoatingAmountUnit());
				fieldValue = listNano.get(i).getCoatingAmount();
				newColumnUnits = checkDefaultCoatingAmountUnit(oldColumnUnits);	
				if (!newColumnUnits.equals(oldColumnUnits))
				{
					listNano.get(i).setCoatingAmountUnit(newColumnUnits);				
					listNano.get(i).setCoatingAmount(checkDefaultCoatingAmount(oldColumnUnits, fieldValue));
				}
								
				/* Purity */
				oldColumnUnits = String.valueOf(listNano.get(i).getPurityUnit());
				fieldValue = listNano.get(i).getPurity();
				newColumnUnits = checkDefaultPurityUnit(oldColumnUnits);
				if (!newColumnUnits.equals(oldColumnUnits))
				{
					listNano.get(i).setPurityUnit(newColumnUnits);				
					listNano.get(i).setPurity(checkDefaultPurity(oldColumnUnits, fieldValue));
				}
								
				/* Contaminants  */
				oldColumnUnits = String.valueOf(listNano.get(i).getContamUnit());
				newColumnUnits = checkDefaultContaminantUnit(oldColumnUnits);
				if (!newColumnUnits.equals(oldColumnUnits))
				{
					listNano.get(i).setContamUnit(newColumnUnits);
					
					fieldValue = listNano.get(i).getContamAl();
					listNano.get(i).setContamAl(checkDefaultContaminant(oldColumnUnits, fieldValue));
					
					fieldValue = listNano.get(i).getContamAs();
					listNano.get(i).setContamAs(checkDefaultContaminant(oldColumnUnits, fieldValue));
					
					fieldValue = listNano.get(i).getContamBe();
					listNano.get(i).setContamBe(checkDefaultContaminant(oldColumnUnits, fieldValue));
					
					fieldValue = listNano.get(i).getContamCa();
					listNano.get(i).setContamCa(checkDefaultContaminant(oldColumnUnits, fieldValue));
					
					fieldValue = listNano.get(i).getContamCo();
					listNano.get(i).setContamCo(checkDefaultContaminant(oldColumnUnits, fieldValue));
					
					fieldValue = listNano.get(i).getContamCr();
					listNano.get(i).setContamCr(checkDefaultContaminant(oldColumnUnits, fieldValue));
					
					fieldValue = listNano.get(i).getContamFe();
					listNano.get(i).setContamFe(checkDefaultContaminant(oldColumnUnits, fieldValue));
					
					fieldValue = listNano.get(i).getContamK();
					listNano.get(i).setContamK(checkDefaultContaminant(oldColumnUnits, fieldValue));
					
					fieldValue = listNano.get(i).getContamMg();
					listNano.get(i).setContamMg(checkDefaultContaminant(oldColumnUnits, fieldValue));
					
					fieldValue = listNano.get(i).getContamNa();
					listNano.get(i).setContamNa(checkDefaultContaminant(oldColumnUnits, fieldValue));
					
					fieldValue = listNano.get(i).getContamP();
					listNano.get(i).setContamP(checkDefaultContaminant(oldColumnUnits, fieldValue));
					
					fieldValue = listNano.get(i).getContamPb();
					listNano.get(i).setContamPb(checkDefaultContaminant(oldColumnUnits, fieldValue));
					
					fieldValue = listNano.get(i).getContamSb();
					listNano.get(i).setContamSb(checkDefaultContaminant(oldColumnUnits, fieldValue));
					
					fieldValue = listNano.get(i).getContamSe();
					listNano.get(i).setContamSe(checkDefaultContaminant(oldColumnUnits, fieldValue));
					
					fieldValue = listNano.get(i).getContamSiO2();
					listNano.get(i).setContamSiO2(checkDefaultContaminant(oldColumnUnits, fieldValue));
					
					fieldValue = listNano.get(i).getContamSn();
					listNano.get(i).setContamSn(checkDefaultContaminant(oldColumnUnits, fieldValue));
					
					fieldValue = listNano.get(i).getContamTl();
					listNano.get(i).setContamTl(checkDefaultContaminant(oldColumnUnits, fieldValue));
					
					fieldValue = listNano.get(i).getContamV();
					listNano.get(i).setContamV(checkDefaultContaminant(oldColumnUnits, fieldValue));
				}
							
				/* Particle outer diameter */
				oldColumnUnits = String.valueOf(listNano.get(i).getParticleOuterDiamUnit());	
				newColumnUnits = checkDefaultParticleOuterDiameterUnit(oldColumnUnits);
				if (!newColumnUnits.equals(oldColumnUnits))
				{
					listNano.get(i).setParticleOuterDiamUnit(newColumnUnits);
					
					fieldValue = listNano.get(i).getParticleOuterDiamAvg();
					listNano.get(i).setParticleOuterDiamAvg(checkDefaultParticleOuterDiameter(oldColumnUnits, fieldValue));
					
					fieldValue = listNano.get(i).getParticleOuterDiamLow();
					listNano.get(i).setParticleOuterDiamLow(checkDefaultParticleOuterDiameter(oldColumnUnits, fieldValue));
					
					fieldValue = listNano.get(i).getParticleOuterDiamHigh();
					listNano.get(i).setParticleOuterDiamHigh(checkDefaultParticleOuterDiameter(oldColumnUnits, fieldValue));
				}
				
				
				/* Particle inner diameter */
				oldColumnUnits = String.valueOf(listNano.get(i).getParticleInnerDiamUnit());				
				newColumnUnits = checkDefaultParticleInnerDiameterUnit(oldColumnUnits);
				if (!newColumnUnits.equals(oldColumnUnits))
				{
					listNano.get(i).setParticleInnerDiamUnit(newColumnUnits);
					
					fieldValue = listNano.get(i).getParticleInnerDiamAvg();
					listNano.get(i).setParticleInnerDiamAvg(checkDefaultParticleOuterDiameter(oldColumnUnits, fieldValue));
					
					fieldValue = listNano.get(i).getParticleInnerDiamLow();
					listNano.get(i).setParticleInnerDiamLow(checkDefaultParticleOuterDiameter(oldColumnUnits, fieldValue));
					
					fieldValue = listNano.get(i).getParticleInnerDiamHigh();
					listNano.get(i).setParticleInnerDiamHigh(checkDefaultParticleOuterDiameter(oldColumnUnits, fieldValue));
				}
								
				/* Particle length */
				oldColumnUnits = String.valueOf(listNano.get(i).getParticleLengthUnit());				
				newColumnUnits = checkDefaultParticleLengthUnit(oldColumnUnits);
				if (!newColumnUnits.equals(oldColumnUnits))
				{
					listNano.get(i).setParticleLengthUnit(newColumnUnits);
					
					fieldValue = listNano.get(i).getParticleLengthAvg();
					listNano.get(i).setParticleLengthAvg(checkDefaultParticleLength(oldColumnUnits, fieldValue));
					
					fieldValue = listNano.get(i).getParticleLengthLow();
					listNano.get(i).setParticleLengthLow(checkDefaultParticleLength(oldColumnUnits, fieldValue));
					
					fieldValue = listNano.get(i).getParticleLengthHigh();
					listNano.get(i).setParticleLengthHigh(checkDefaultParticleLength(oldColumnUnits, fieldValue));
				}
								
				/* Particle thickness parameters */
				oldColumnUnits = String.valueOf(listNano.get(i).getParticleThicknessUnit());	
				newColumnUnits = checkDefaultParticleThicknessUnit(oldColumnUnits);
				if (!newColumnUnits.equals(oldColumnUnits))
				{
					listNano.get(i).setParticleThicknessUnit(newColumnUnits);
					
					fieldValue = listNano.get(i).getParticleThicknessAvg();
					listNano.get(i).setParticleThicknessAvg(checkDefaultParticleThickness(oldColumnUnits, fieldValue));
					
					fieldValue = listNano.get(i).getParticleThicknessLow();
					listNano.get(i).setParticleThicknessLow(checkDefaultParticleThickness(oldColumnUnits, fieldValue));
					
					fieldValue = listNano.get(i).getParticleThicknessHigh();
					listNano.get(i).setParticleThicknessHigh(checkDefaultParticleThickness(oldColumnUnits, fieldValue));
				}
								
				/* Surface area parameters */
				oldColumnUnits = String.valueOf(listNano.get(i).getSurfaceAreaUnit());				
				newColumnUnits = checkDefaultSurfaceAreaUnit(oldColumnUnits);
				if (!newColumnUnits.equals(oldColumnUnits))
				{
					listNano.get(i).setSurfaceAreaUnit(newColumnUnits);
					
					fieldValue = listNano.get(i).getSurfaceAreaAvg();
					listNano.get(i).setSurfaceAreaAvg(checkDefaultSurfaceArea(oldColumnUnits, fieldValue));
					
					fieldValue = listNano.get(i).getSurfaceAreaLow();
					listNano.get(i).setSurfaceAreaLow(checkDefaultSurfaceArea(oldColumnUnits, fieldValue));
					
					fieldValue = listNano.get(i).getSurfaceAreaHigh();
					listNano.get(i).setSurfaceAreaHigh(checkDefaultSurfaceArea(oldColumnUnits, fieldValue));
				}
				
				
				/* Time oldValue parameter */
				oldColumnUnits = String.valueOf(listNano.get(i).getTimeValueUnit());				
				newColumnUnits = checkDefaultTimeValueUnit(oldColumnUnits);
				if (!newColumnUnits.equals(oldColumnUnits))
				{
					listNano.get(i).setTimeValueUnit(newColumnUnits);
					
					fieldValue = listNano.get(i).getTimeValue();
					listNano.get(i).setTimeValue(checkDefaultTimeValue(oldColumnUnits, fieldValue));
				}
								
				oldColumnUnits = String.valueOf(listNano.get(i).getMc_timeValueUnit());				
				newColumnUnits = checkDefaultTimeValueUnit(oldColumnUnits);
				if (!newColumnUnits.equals(oldColumnUnits))
				{
					listNano.get(i).setMc_timeValueUnit(newColumnUnits);
					
					fieldValueInt = listNano.get(i).getMc_timeValue();
					listNano.get(i).setMc_timeValue(checkDefaultTimeValue(oldColumnUnits, fieldValueInt));
				}
								
				/* Particle concentration */
				oldColumnUnits = String.valueOf(listNano.get(i).getParticleConcentrationUnit());				
				newColumnUnits = checkDefaultParticleConcentrationUnit(oldColumnUnits);
				if (!newColumnUnits.equals(oldColumnUnits))
				{
					listNano.get(i).setParticleConcentrationUnit(newColumnUnits);
					
					fieldValue = listNano.get(i).getParticleConcentration();
					listNano.get(i).setParticleConcentration(checkDefaultParticleConcentration(oldColumnUnits, fieldValue));
				}
								
				oldColumnUnits = String.valueOf(listNano.get(i).getMc_particleConcentrationUnit());				
				newColumnUnits = checkDefaultParticleConcentrationUnit(oldColumnUnits);
				if (!newColumnUnits.equals(oldColumnUnits))
				{
					listNano.get(i).setMc_particleConcentrationUnit(newColumnUnits);
					
					fieldValue = listNano.get(i).getMc_particleConcentration();
					listNano.get(i).setMc_particleConcentration(checkDefaultParticleConcentration(oldColumnUnits, fieldValue));
				}
				
				
				/* Medium temperature */
				oldColumnUnits = String.valueOf(listNano.get(i).getMediumTempUnit());
				newColumnUnits = checkDefaultMediumTempUnit(oldColumnUnits);
				if (!newColumnUnits.equals(oldColumnUnits))
				{
					listNano.get(i).setMediumTempUnit(newColumnUnits);
					
					fieldValue = listNano.get(i).getMediumTemp();
					listNano.get(i).setMediumTemp(checkDefaultParticleConcentration(oldColumnUnits, fieldValue));
				}
								
				oldColumnUnits = String.valueOf(listNano.get(i).getMc_mediumTempUnit());				
				newColumnUnits = checkDefaultMediumTempUnit(oldColumnUnits);
				if (!newColumnUnits.equals(oldColumnUnits))
				{
					listNano.get(i).setMc_mediumTempUnit(newColumnUnits);
					
					fieldValue = listNano.get(i).getMc_mediumTemp();
					listNano.get(i).setMc_mediumTemp(checkDefaultParticleConcentration(oldColumnUnits, fieldValue));
				}
								
				/* Zeta potential of nanoparticles */
				oldColumnUnits = String.valueOf(listNano.get(i).getZetaPotentialUnit());				
				newColumnUnits = checkDefaultZetaPotentialUnit(oldColumnUnits);
				if (!newColumnUnits.equals(oldColumnUnits))
				{
					listNano.get(i).setZetaPotentialUnit(newColumnUnits);
					
					fieldValue = listNano.get(i).getZetaPotentialAvg();
					listNano.get(i).setZetaPotentialAvg(checkDefaultZetaPotential(oldColumnUnits, fieldValue));
					
					fieldValue = listNano.get(i).getZetaPotentialLow();
					listNano.get(i).setZetaPotentialLow(checkDefaultZetaPotential(oldColumnUnits, fieldValue));
					
					fieldValue = listNano.get(i).getZetaPotentialHigh();
					listNano.get(i).setZetaPotentialHigh(checkDefaultZetaPotential(oldColumnUnits, fieldValue));
				}
								
				/* Size distribution parameters*/
				oldColumnUnits = String.valueOf(listNano.get(i).getSizeDistribUnit());				
				newColumnUnits = checkDefaultSizeDistributionUnit(oldColumnUnits);
				if (!newColumnUnits.equals(oldColumnUnits))
				{
					listNano.get(i).setSizeDistribUnit(newColumnUnits);
					
					fieldValue = listNano.get(i).getSizeDistribAvg();
					listNano.get(i).setSizeDistribAvg(checkDefaultSizeDistribution(oldColumnUnits, fieldValue));
					
					fieldValue = listNano.get(i).getSizeDistribLow();
					listNano.get(i).setSizeDistribLow(checkDefaultSizeDistribution(oldColumnUnits, fieldValue));
					
					fieldValue = listNano.get(i).getSizeDistribHigh();
					listNano.get(i).setSizeDistribHigh(checkDefaultSizeDistribution(oldColumnUnits, fieldValue));
				}
								
				/* Size distribution parameters 2*/
				oldColumnUnits = String.valueOf(listNano.get(i).getSizeDistribUnit2());				
				newColumnUnits = checkDefaultSizeDistributionUnit2(oldColumnUnits);
				if (!newColumnUnits.equals(oldColumnUnits))
				{
					listNano.get(i).setSizeDistribUnit2(newColumnUnits);
					
					fieldValue = listNano.get(i).getSizeDistribAvg2();
					listNano.get(i).setSizeDistribAvg2(checkDefaultSizeDistribution2(oldColumnUnits, fieldValue));
					
					fieldValue = listNano.get(i).getSizeDistribLow2();
					listNano.get(i).setSizeDistribLow2(checkDefaultSizeDistribution2(oldColumnUnits, fieldValue));
					
					fieldValue = listNano.get(i).getSizeDistribHigh2();
					listNano.get(i).setSizeDistribHigh2(checkDefaultSizeDistribution2(oldColumnUnits, fieldValue));
				}
				
			
				/* Serum concentration */
				oldColumnUnits = String.valueOf(listNano.get(i).getSerumConcentrationUnit());				
				newColumnUnits = checkDefaultSerumConcentrationUnit(oldColumnUnits);
				if (!newColumnUnits.equals(oldColumnUnits))
				{
					listNano.get(i).setSerumConcentrationUnit(newColumnUnits);
					
					fieldValue = listNano.get(i).getSerumConcentration();
					listNano.get(i).setSerumConcentration(checkDefaultSerumConcentration(oldColumnUnits, fieldValue));
				}
								
				oldColumnUnits = String.valueOf(listNano.get(i).getMc_serumConcentrationUnit());				
				newColumnUnits = checkDefaultSerumConcentrationUnit(oldColumnUnits);
				if (!newColumnUnits.equals(oldColumnUnits))
				{
					listNano.get(i).setMc_serumConcentrationUnit(newColumnUnits);
					
					fieldValue = listNano.get(i).getMc_serumConcentration();
					listNano.get(i).setMc_serumConcentration(checkDefaultSerumConcentration(oldColumnUnits, fieldValue));
				}
								
				/* Antibiotic concentration */
				oldColumnUnits = String.valueOf(listNano.get(i).getAntibioticConcentrationUnit());
				newColumnUnits = checkDefaultAntibioticConcentrationUnit(oldColumnUnits);
				if (!newColumnUnits.equals(oldColumnUnits))
				{
					listNano.get(i).setAntibioticConcentrationUnit(newColumnUnits);
					
					fieldValue = listNano.get(i).getAntibioticConcentration();
					listNano.get(i).setAntibioticConcentration(checkDefaultAntibioticConcentration(oldColumnUnits, fieldValue));
				}
								
				oldColumnUnits = String.valueOf(listNano.get(i).getMc_antibioticConcentrationUnit());				
				newColumnUnits = checkDefaultAntibioticConcentrationUnit(oldColumnUnits);
				if (!newColumnUnits.equals(oldColumnUnits))
				{
					listNano.get(i).setMc_antibioticConcentrationUnit(newColumnUnits);
					
					fieldValue = listNano.get(i).getMc_antibioticConcentration();
					listNano.get(i).setMc_antibioticConcentration(checkDefaultAntibioticConcentration(oldColumnUnits, fieldValue));
				}
								
				/* DOM (dissolved organic matter) concentration */
				oldColumnUnits = String.valueOf(listNano.get(i).getDomUnit());	
				newColumnUnits = checkDefaultDOMUnit(oldColumnUnits);
				if (!newColumnUnits.equals(oldColumnUnits))
				{
					listNano.get(i).setDomUnit(newColumnUnits);
					
					fieldValue = listNano.get(i).getDomConcentration();
					listNano.get(i).setDomConcentration(checkDefaultDOMConcentration(oldColumnUnits, fieldValue));
				}
				
				oldColumnUnits = String.valueOf(listNano.get(i).getMc_domUnit());				
				newColumnUnits = checkDefaultDOMUnit(oldColumnUnits);
				if (!newColumnUnits.equals(oldColumnUnits))
				{
					listNano.get(i).setMc_domUnit(newColumnUnits);
					
					fieldValue = listNano.get(i).getMc_domConcentration();
					listNano.get(i).setMc_domConcentration(checkDefaultDOMConcentration(oldColumnUnits, fieldValue));
				}
								
				/* Salinity oldValue */
				oldColumnUnits = String.valueOf(listNano.get(i).getSalinityUnit());
				newColumnUnits = checkDefaultSalinityUnit(oldColumnUnits);
				if (!newColumnUnits.equals(oldColumnUnits))
				{
					listNano.get(i).setSalinityUnit(newColumnUnits);
					
					fieldValue = listNano.get(i).getSalinityValue();
					listNano.get(i).setSalinityValue(checkDefaultSalinityValue(oldColumnUnits, fieldValue));
				}
						
				oldColumnUnits = String.valueOf(listNano.get(i).getMc_salinityUnit());				
				newColumnUnits = checkDefaultSalinityUnit(oldColumnUnits);
				if (!newColumnUnits.equals(oldColumnUnits))
				{
					listNano.get(i).setMc_salinityUnit(newColumnUnits);
					
					fieldValue = listNano.get(i).getMc_salinityValue();
					listNano.get(i).setMc_salinityValue(checkDefaultSalinityValue(oldColumnUnits, fieldValue));
				}
								
				/* Particle exposure concentration */
				oldColumnUnits = String.valueOf(listNano.get(i).getParticleExposDurationUnit());				
				newColumnUnits = checkDefaultParticleExposureDurationUnit(oldColumnUnits);
				if (!newColumnUnits.equals(oldColumnUnits))
				{
					listNano.get(i).setParticleExposDurationUnit(newColumnUnits);
					
					fieldValue = listNano.get(i).getParticleExposDuration();
					listNano.get(i).setParticleExposDuration(checkDefaultParticleExposureDuration(oldColumnUnits, fieldValue));
				}
								
				/* UVA dose */
				oldColumnUnits = String.valueOf(listNano.get(i).getUvaDoseUnit());
				newColumnUnits = checkDefaultUVADoseUnit(oldColumnUnits);
				if (!newColumnUnits.equals(oldColumnUnits))
				{
					listNano.get(i).setUvaDoseUnit(newColumnUnits);
					
					fieldValue = listNano.get(i).getUvaDose();
					listNano.get(i).setUvaDose(checkDefaultUVADose(oldColumnUnits, fieldValue));
				}
								
				/* UVA exposure duration */
				oldColumnUnits = String.valueOf(listNano.get(i).getUvaExposDurationUnit());
				newColumnUnits = checkDefaultUVAExposureDurationUnit(oldColumnUnits);
				if (!newColumnUnits.equals(oldColumnUnits))
				{
					listNano.get(i).setUvaExposDurationUnit(newColumnUnits);
					
					fieldValue = listNano.get(i).getUvaExposDuration();
					listNano.get(i).setUvaExposDuration(checkDefaultUVAExposureDuration(oldColumnUnits, fieldValue));
				}
								
				/* Viability average */ 
				oldColumnUnits = String.valueOf(listNano.get(i).getViabilityUnit());	
				newColumnUnits = checkDefaultViabilityUnit(oldColumnUnits);
				if (!newColumnUnits.equals(oldColumnUnits))
				{
					listNano.get(i).setViabilityUnit(newColumnUnits);
					
					fieldValue = listNano.get(i).getViabilityAvg();
					listNano.get(i).setViabilityAvg(checkDefaultViability(oldColumnUnits, fieldValue));
					
					fieldValue = listNano.get(i).getViabilityLow();
					listNano.get(i).setViabilityLow(checkDefaultViability(oldColumnUnits, fieldValue));
					
					fieldValue = listNano.get(i).getViabilityHigh();
					listNano.get(i).setViabilityHigh(checkDefaultViability(oldColumnUnits, fieldValue));
				}
								
				/*  LC50 */
				oldColumnUnits = String.valueOf(listNano.get(i).getLc50Unit());
				newColumnUnits = checkDefaultLC50Unit(oldColumnUnits);
				if (!newColumnUnits.equals(oldColumnUnits))
				{
					listNano.get(i).setLc50Unit(newColumnUnits);					
					fieldValue = listNano.get(i).getLc50();
					listNano.get(i).setLc50(checkDefaultLC50(oldColumnUnits, fieldValue)); 
				}
										
			}
		}
		catch (IllegalUnitsException ex)
		{
			ex.printStackTrace();
			ex.getMessage();
			throw ex;
		}
		
		
	}

}
