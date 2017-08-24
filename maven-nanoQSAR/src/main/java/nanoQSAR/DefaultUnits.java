package nanoQSAR;

/**
 * 
 */

import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class checks for default units of fields and converts units and its corresponding
 * values to defaults for entries that have non-default units.
 * handles conversion of units 
 * @author Wilson Melendez
 * 
 */
public class DefaultUnits 
{
	/* Need this line to allow logging of error messages */
	private final static Logger lOGGER = Logger.getLogger("nanoQSAR");
	
	/**
	 * @author Wilson Melendez
	 * @param nanoM
	 * @param i
	 * @throws Exception
	 */
	public static void checkDefaultCoatingAmountUnit(NanoMaterial nanoM, int i) throws Exception
	{
		String strU = String.valueOf(nanoM.getCoatingAmountUnit()).trim();
		String strValue = String.valueOf(nanoM.getCoatingAmount()).trim();
		Double value;
		
		if (strU.equals("ug") || strU.equals("null") || strU.equals("mg"))
		{
			if (strU.equals("mg"))
			{
				nanoM.setCoatingAmountUnit("ug");
				if (!strValue.equals("null"))
				{
					value = nanoM.getCoatingAmount() * 1000.0;
					nanoM.setCoatingAmount(value);
				}
				
			}		
		}
		else
		{
			throw new Exception("Illegal coating amount unit: " + strU + " at row " + i);
		}
	}
	
	/**
	 * @author Wilson Melendez
	 * @param nanoM
	 * @param i
	 * @throws Exception
	 */
	public static void checkDefaultPurityUnit(NanoMaterial nanoM, int i) throws Exception
	{
		String strU = String.valueOf(nanoM.getPurityUnit()).trim();
		String strValue = String.valueOf(nanoM.getPurity()).trim();
		Double value;
		
		if (strU.equals("%") || strU.equals("null") || strU.equals("Mass %") || strU.equals("fraction"))
		{
			if (strU.equals("fraction"))
			{
				nanoM.setPurityUnit("%");
				if (!strValue.equals("null"))
				{
					value = nanoM.getPurity() * 100.0;
					nanoM.setPurity(value);
				}
				
			}		
		}
		else
		{
			throw new Exception("Illegal purity units: " + strU + " at row = " + i);
		}

	}
		
	/**
	 * @author Wilson Melendez
	 * @param nanoM
	 * @param i
	 * @throws Exception
	 */
	public static void checkDefaultContaminantUnit(NanoMaterial nanoM, int i) throws Exception
	{
		String strU = String.valueOf(nanoM.getContamUnit()).trim();
		String strValueAl = String.valueOf(nanoM.getContamAl()).trim();
		String strValueAs = String.valueOf(nanoM.getContamAs()).trim();
		String strValueBe = String.valueOf(nanoM.getContamBe()).trim();
		String strValueCa = String.valueOf(nanoM.getContamCa()).trim();
		String strValueCo = String.valueOf(nanoM.getContamCo()).trim();
		String strValueCr = String.valueOf(nanoM.getContamCr()).trim();
		String strValueFe = String.valueOf(nanoM.getContamFe()).trim();
		String strValueK = String.valueOf(nanoM.getContamK()).trim();
		String strValueMg = String.valueOf(nanoM.getContamMg()).trim();
		String strValueNa = String.valueOf(nanoM.getContamNa()).trim();
		String strValueP = String.valueOf(nanoM.getContamP()).trim();
		String strValuePb = String.valueOf(nanoM.getContamPb()).trim();
		String strValueSb = String.valueOf(nanoM.getContamSb()).trim();
		String strValueSe = String.valueOf(nanoM.getContamSe()).trim();
		String strValueSiO2 = String.valueOf(nanoM.getContamSiO2()).trim();
		String strValueSn = String.valueOf(nanoM.getContamSn()).trim();
		String strValueTl = String.valueOf(nanoM.getContamTl()).trim();
		String strValueV = String.valueOf(nanoM.getContamV()).trim();
		
		
		Double value;
		
		if (strU.equals("ppm") || strU.equals("null") || strU.equals("ppt"))
		{
			if (strU.equals("ppt"))
			{
				nanoM.setContamUnit("ppm");
				
				if (!strValueAl.equals("null"))
				{
					value = nanoM.getContamAl() * 1000.0;
					nanoM.setContamAl(value);
				}
				if (!strValueAs.equals("null"))
				{
					value = nanoM.getContamAs() * 1000.0;
					nanoM.setContamAs(value);
				}
				if (!strValueBe.equals("null"))
				{
					value = nanoM.getContamBe() * 1000.0;
					nanoM.setContamBe(value);
				}
				if (!strValueCa.equals("null"))
				{
					value = nanoM.getContamCa() * 1000.0;
					nanoM.setContamCa(value);
				}
				if (!strValueCo.equals("null"))
				{
					value = nanoM.getContamCo() * 1000.0;
					nanoM.setContamCo(value);
				}
				if (!strValueCr.equals("null"))
				{
					value = nanoM.getContamCr()* 1000.0;
					nanoM.setContamCr(value);
				}
				if (!strValueFe.equals("null"))
				{
					value = nanoM.getContamFe()* 1000.0;
					nanoM.setContamFe(value);
				}
				if (!strValueK.equals("null"))
				{
					value = nanoM.getContamK() * 1000.0;
					nanoM.setContamK(value);
				}
				if (!strValueMg.equals("null"))
				{
					value = nanoM.getContamMg() * 1000.0;
					nanoM.setContamMg(value);
				}
				if (!strValueNa.equals("null"))
				{
					value = nanoM.getContamNa() * 1000.0;
					nanoM.setContamNa(value);
				}
				if (!strValueP.equals("null"))
				{
					value = nanoM.getContamP() * 1000.0;
					nanoM.setContamP(value);
				}
				if (!strValuePb.equals("null"))
				{
					value = nanoM.getContamPb()* 1000.0;
					nanoM.setContamPb(value);
				}
				if (!strValueSb.equals("null"))
				{
					value = nanoM.getContamSb()* 1000.0;
					nanoM.setContamSb(value);
				}
				if (!strValueSe.equals("null"))
				{
					value = nanoM.getContamSe()* 1000.0;
					nanoM.setContamSe(value);
				}
				if (!strValueSiO2.equals("null"))
				{
					value = nanoM.getContamSiO2()* 1000.0;
					nanoM.setContamSiO2(value);
				}
				if (!strValueSn.equals("null"))
				{
					value = nanoM.getContamSn()* 1000.0;
					nanoM.setContamSn(value);
				}
				if (!strValueTl.equals("null"))
				{
					value = nanoM.getContamTl()* 1000.0;
					nanoM.setContamTl(value);
				}
				if (!strValueV.equals("null"))
				{
					value = nanoM.getContamV()* 1000.0;
					nanoM.setContamV(value);
				}
			}
		}
		else
		{
			throw new Exception("Illegal contaminant units: " + strU + " at row = " + i);
		}		
	}
	
	
	/**
	 * @author Wilson Melendez
	 * @param nanoM
	 * @param i
	 * @throws Exception
	 */
	public static void checkDefaultParticleOuterDiameterUnit(NanoMaterial nanoM, int i) throws Exception
	{
		String strU = String.valueOf(nanoM.getParticleOuterDiamUnit()).trim();
		String strValueAvg = String.valueOf(nanoM.getParticleOuterDiamAvg()).trim();
		String strValueHigh = String.valueOf(nanoM.getParticleOuterDiamHigh()).trim();
		String strValueLow = String.valueOf(nanoM.getParticleOuterDiamLow()).trim();
		
		Double value;
		
		if (strU.equals("nm") || strU.equals("null") || strU.equals("um"))
		{
			if (strU.equals("um"))
			{
				nanoM.setParticleOuterDiamUnit("nm");
				if (!strValueAvg.equals("null"))
				{
					value = nanoM.getParticleOuterDiamAvg() * 1000.0;
					nanoM.setParticleOuterDiamAvg(value);
				}
				if (!strValueHigh.equals("null"))
				{
					value = nanoM.getParticleOuterDiamHigh() * 1000.0;
					nanoM.setParticleOuterDiamHigh(value);
				}
				if (!strValueLow.equals("null"))
				{
					value = nanoM.getParticleOuterDiamLow() * 1000.0;
					nanoM.setParticleOuterDiamLow(value);
				}
			}
		}
		else
		{
			throw new Exception("Illegal particle outer diameter units: " + strU + " at row = " + i);
		}
	}
	
	
	
	/**
	 * @author Wilson Melendez
	 * @param nanoM
	 * @param i
	 * @throws Exception
	 */
	public static void checkDefaultParticleInnerDiameterUnit(NanoMaterial nanoM, int i) throws Exception
	{
		String strU = String.valueOf(nanoM.getParticleInnerDiamUnit()).trim();
		String strValueAvg = String.valueOf(nanoM.getParticleInnerDiamAvg()).trim();
		String strValueHigh = String.valueOf(nanoM.getParticleInnerDiamHigh()).trim();
		String strValueLow = String.valueOf(nanoM.getParticleInnerDiamLow()).trim();
		
		Double value;
		
		if (strU.equals("nm") || strU.equals("null") || strU.equals("um"))
		{
			if (strU.equals("um"))
			{
				nanoM.setParticleInnerDiamUnit("nm");
				if (!strValueAvg.equals("null"))
				{
					value = nanoM.getParticleInnerDiamAvg() * 1000.0;
					nanoM.setParticleInnerDiamAvg(value);
				}
				if (!strValueHigh.equals("null"))
				{
					value = nanoM.getParticleInnerDiamHigh() * 1000.0;
					nanoM.setParticleInnerDiamHigh(value);
				}
				if (!strValueLow.equals("null"))
				{
					value = nanoM.getParticleInnerDiamLow() * 1000.0;
					nanoM.setParticleInnerDiamLow(value);
				}
			}
		}
		else
		{
			throw new Exception("Illegal particle inner diameter units: " + strU + " at row = " + i);
		}
	}
	
	/**
	 * @author Wilson Melendez
	 * @param nanoM
	 * @param i
	 * @throws Exception
	 */
	public static void checkDefaultParticleLengthUnit(NanoMaterial nanoM, int i) throws Exception
	{
		String strU = String.valueOf(nanoM.getParticleLengthUnit()).trim();
		String strValueAvg = String.valueOf(nanoM.getParticleLengthAvg()).trim();
		String strValueHigh = String.valueOf(nanoM.getParticleLengthHigh()).trim();
		String strValueLow = String.valueOf(nanoM.getParticleLengthLow()).trim();
		Double value;
		
		if (strU.equals("nm") || strU.equals("null") || strU.equals("um"))
		{
			if (strU.equals("um"))
			{
				nanoM.setParticleLengthUnit("nm");
				if (!strValueAvg.equals("null"))
				{
					value = nanoM.getParticleLengthAvg() * 1000.0;
					nanoM.setParticleLengthAvg(value);
				}
				if (!strValueHigh.equals("null"))
				{
					value = nanoM.getParticleLengthHigh() * 1000.0;
					nanoM.setParticleLengthHigh(value);
				}
				if (!strValueLow.equals("null"))
				{
					value = nanoM.getParticleLengthLow() * 1000.0;
					nanoM.setParticleLengthLow(value);
				}
			}
		}
		else
		{
			throw new Exception("Illegal particle length unit: " + strU + " at row " + i);
		}
	}
	
	
	
	/**
	 * @author Wilson Melendez
	 * @param nanoM
	 * @param i
	 * @throws Exception
	 */
	public static void checkDefaultParticleThicknessUnit(NanoMaterial nanoM, int i) throws Exception
	{
		String strU = String.valueOf(nanoM.getParticleThicknessUnit()).trim();
		String strValueAvg = String.valueOf(nanoM.getParticleThicknessAvg()).trim();
		String strValueHigh = String.valueOf(nanoM.getParticleThicknessHigh()).trim();
		String strValueLow = String.valueOf(nanoM.getParticleThicknessLow()).trim();
		Double value;
		
		if (strU.equals("nm") || strU.equals("null") || strU.equals("um"))
		{
			if (strU.equals("um"))
			{
				nanoM.setParticleThicknessUnit("nm");
				if (!strValueAvg.equals("null"))
				{
					value = nanoM.getParticleThicknessAvg() * 1000.0;
					nanoM.setParticleThicknessAvg(value);
				}
				if (!strValueHigh.equals("null"))
				{
					value = nanoM.getParticleThicknessHigh() * 1000.0;
					nanoM.setParticleThicknessHigh(value);
				}
				if (!strValueLow.equals("null"))
				{
					value = nanoM.getParticleThicknessLow() * 1000.0;
					nanoM.setParticleThicknessLow(value);
				}
			}
		}
		else
		{
			throw new Exception("Illegal particle thickness unit: " + strU + " at row " + i);
		}
	}
	
	
	/**
	 * @author Wilson Melendez
	 * @param nanoM
	 * @param i
	 * @throws Exception
	 */
	public static void checkDefaultSurfaceAreaUnit(NanoMaterial nanoM, int i) throws Exception
	{
		String strU = String.valueOf(nanoM.getSurfaceAreaUnit()).trim();
		String strValueAvg = String.valueOf(nanoM.getSurfaceAreaAvg()).trim();
		String strValueHigh = String.valueOf(nanoM.getSurfaceAreaHigh()).trim();
		String strValueLow = String.valueOf(nanoM.getSurfaceAreaLow()).trim();
		Double value;
		
		if (strU.equals("m^2/g") || strU.equals("null") || strU.equals("cm^2/g"))
		{
			if (strU.equals("cm^2/g"))
			{
				nanoM.setSurfaceAreaUnit("m^2/g");
				if (!strValueAvg.equals("null"))
				{
					value = nanoM.getSurfaceAreaAvg() * 1.0E-04;
					nanoM.setSurfaceAreaAvg(value);
				}
				if (!strValueHigh.equals("null"))
				{
					value = nanoM.getSurfaceAreaHigh() * 1.0E-04;
					nanoM.setSurfaceAreaHigh(value);
				}
				if (!strValueLow.equals("null"))
				{
					value = nanoM.getSurfaceAreaLow() * 1.0E-04;
					nanoM.setSurfaceAreaLow(value);
				}
			}
		}
		else
		{
			throw new Exception("Illegal surface area unit: " + strU + " at row " + i);
		}
	}
	
	
	/**
	 * @author Wilson Melendez
	 * @param nanoM
	 * @param i
	 * @throws Exception
	 */
	public static void checkDefaultTimeValueUnit(NanoMaterial nanoM, int i) throws Exception
	{
		String strU = String.valueOf(nanoM.getTimeValueUnit()).trim();
		String strValue = String.valueOf(nanoM.getTimeValue()).trim();
		Double value;
		
		if (strU.equals("hours") || strU.equals("null") || strU.equals("minutes"))
		{
			if (strU.equals("minutes"))
			{
				nanoM.setTimeValueUnit("hours");
				if (!strValue.equals("null"))
				{
					value = nanoM.getTimeValue() / 60.0;
					nanoM.setTimeValue(value);
				}
			}
		}
		else
		{
			throw new Exception("Illegal time value unit: " + strU + " at row " + i);
		}	
	}
	
	/**
	 * @author Wilson Melendez
	 * @param nanoM
	 * @param i
	 * @throws Exception
	 */
	public static void checkDefaultMcTimeValueUnit(NanoMaterial nanoM, int i) throws Exception
	{
		String strU = String.valueOf(nanoM.getMc_timeValueUnit()).trim();
		String strValue = String.valueOf(nanoM.getMc_timeValue()).trim();
		Double value;
		
		if (strU.equals("hours") || strU.equals("null") || strU.equals("minutes"))
		{
			if (strU.equals("minutes"))
			{
				nanoM.setMc_timeValueUnit("hours");
				if (!strValue.equals("null"))
				{
					value = nanoM.getMc_timeValue() / 60.0;
					nanoM.setMc_timeValue(value);
				}
			}
		}
		else
		{
			throw new Exception("Illegal Mc_time_value unit: " + strU + " at row " + i);
		}	
	}
		
	/**
	 * @author Wilson Melendez
	 * @param nanoM
	 * @param i
	 * @throws Exception
	 */
	public static void checkDefaultParticleConcentrationUnit(NanoMaterial nanoM, int i) throws Exception
	{
		String strU = String.valueOf(nanoM.getParticleConcentrationUnit()).trim();
		String strValue = String.valueOf(nanoM.getParticleConcentration()).trim();
		Double value;
		
		if (strU.equals("ug/mL") || strU.equals("null") || strU.equals("mg/mL"))
		{
			if (strU.equals("mg/mL"))
			{
				nanoM.setParticleConcentrationUnit("ug/mL");
				if (!strValue.equals("null"))
				{
					value = nanoM.getParticleConcentration() * 1.0E+03;
					nanoM.setParticleConcentration(value);
				}
			}
		}
		else
		{
			throw new Exception("Illegal particle concentration unit: " + strU + " row = " + i);
		}
	}
	
	
	/**
	 * @author Wilson Melendez
	 * @param nanoM
	 * @param i
	 * @throws Exception
	 */
	public static void checkDefaultMcParticleConcentrationUnit(NanoMaterial nanoM, int i) throws Exception
	{
		String strU = String.valueOf(nanoM.getMc_particleConcentrationUnit()).trim();
		String strValue = String.valueOf(nanoM.getMc_particleConcentration()).trim();
		Double value;
		
		if (strU.equals("mg/L") || strU.equals("null") || strU.equals("ug/L"))
		{
			if (strU.equals("ug/L"))
			{
				nanoM.setMc_particleConcentrationUnit("mg/L");
				if (!strValue.equals("null"))
				{
					value = nanoM.getMc_particleConcentration() / 1000.0;
					nanoM.setMc_particleConcentration(value);
				}
			}
		}
		else
		{
			throw new Exception("Illegal Mc_particle_concentration unit: " + strU + " row = " + i);
		}
	}
	
	
	/**
	 * @author Wilson Melendez
	 * @param nanoM
	 * @param i
	 * @throws Exception
	 */
	public static void checkDefaultMediumTempUnit(NanoMaterial nanoM, int i) throws Exception
	{
		String strU = String.valueOf(nanoM.getMediumTempUnit()).trim();
		String strValue = String.valueOf(nanoM.getMediumTemp()).trim();
		Double value;
		
		if (strU.equals("C") || strU.equals("null") || strU.equals("F") || strU.equals("K"))
		{
			if (strU.equals("F"))
			{
				nanoM.setMediumTempUnit("C");
				if(!strValue.equals("null"))
				{
					value = (nanoM.getMediumTemp() - 32.0) * 5.0 / 9.0;
					nanoM.setMediumTemp(value);
				}				
			}
			else if (strU.equals("K"))
			{
				nanoM.setMediumTempUnit("C");
				if(!strValue.equals("null"))
				{
					value = nanoM.getMediumTemp() - 273.15;
					nanoM.setMediumTemp(value);
				}				
			}
		}
		else
		{
			throw new Exception("Illegal medium temperature unit: " + strU + " at row = " + i);
		}
	}
	
	/**
	 * @author Wilson Melendez
	 * @param nanoM
	 * @param i
	 * @throws Exception
	 */
	public static void checkDefaultMcMediumTempUnit(NanoMaterial nanoM, int i) throws Exception
	{
		String strU = String.valueOf(nanoM.getMc_mediumTempUnit()).trim();
		String strValue = String.valueOf(nanoM.getMc_mediumTemp()).trim();
		Double value;
		
		if (strU.equals("C") || strU.equals("null") || strU.equals("F") || strU.equals("K"))
		{
			if (strU.equals("F"))
			{
				nanoM.setMc_mediumTempUnit("C");
				if(!strValue.equals("null"))
				{
					value = (nanoM.getMc_mediumTemp() - 32.0) * 5.0 / 9.0;
					nanoM.setMc_mediumTemp(value);
				}				
			}
			else if (strU.equals("K"))
			{
				nanoM.setMc_mediumTempUnit("C");
				if(!strValue.equals("null"))
				{
					value = nanoM.getMc_mediumTemp() - 273.15;
					nanoM.setMc_mediumTemp(value);
				}				
			}
		}
		else
		{
			throw new Exception("Illegal Mc_medium_temperature unit: " + strU + " at row = " + i);
		}
	}
	
	/**
	 * @author Wilson Melendez
	 * @param nanoM
	 * @param i
	 * @throws Exception
	 */
	public static void checkDefaultZetaPotentialUnit(NanoMaterial nanoM, int i) throws Exception
	{
		String strU = String.valueOf(nanoM.getZetaPotentialUnit()).trim();
		String strValueAvg = String.valueOf(nanoM.getZetaPotentialAvg()).trim();
		String strValueHigh = String.valueOf(nanoM.getZetaPotentialHigh()).trim();
		String strValueLow = String.valueOf(nanoM.getZetaPotentialLow()).trim();
		Double value;
		
		if (strU.equals("mV") || strU.equals("null") || strU.equals("uV"))
		{
			if (strU.equals("uV"))
			{
				nanoM.setZetaPotentialUnit("mV");
				if (!strValueAvg.equals("null"))
				{
					value = nanoM.getZetaPotentialAvg() * 1.0E-03;
					nanoM.setZetaPotentialAvg(value);
				}
				if (!strValueHigh.equals("null"))
				{
					value = nanoM.getZetaPotentialHigh() * 1.0E-03;
					nanoM.setZetaPotentialHigh(value);
				}
				if (!strValueLow.equals("null"))
				{
					value = nanoM.getZetaPotentialLow() * 1.0E-03;
					nanoM.setZetaPotentialLow(value);
				}
			}
		}
		else
		{
			throw new Exception("Illegal zeta potential unit: " + strU + " at row " + i);
		}
	}
	
	/**
	 * @author Wilson Melendez
	 * @param nanoM
	 * @param i
	 * @throws Exception
	 */
	public static void checkDefaultSizeDistributionUnit(NanoMaterial nanoM, int i) throws Exception
	{
		String strU = String.valueOf(nanoM.getSizeDistribUnit()).trim();
		String strValueAvg = String.valueOf(nanoM.getSizeDistribAvg()).trim();
		String strValueHigh = String.valueOf(nanoM.getSizeDistribHigh()).trim();
		String strValueLow = String.valueOf(nanoM.getSizeDistribLow()).trim();
		Double value;
		
		if (strU.equals("nm") || strU.equals("null") || strU.equals("um"))
		{
			if (strU.equals("um"))
			{
				nanoM.setSizeDistribUnit("nm");
				if (!strValueAvg.equals("null"))
				{
					value = nanoM.getSizeDistribAvg() * 1.0E+03;
					nanoM.setSizeDistribAvg(value);
				}
				if (!strValueHigh.equals("null"))
				{
					value = nanoM.getSizeDistribHigh() * 1.0E+03;
					nanoM.setSizeDistribHigh(value);
				}
				if (!strValueLow.equals("null"))
				{
					value = nanoM.getSizeDistribLow() * 1.0E+03;
					nanoM.setSizeDistribLow(value);
				}
			}
		}
		else
		{
			throw new Exception("Illegal size distribution unit: " + strU + " at row " + i);
		}
	}
	
	/**
	 * @author Wilson Melendez
	 * @param nanoM
	 * @param i
	 * @throws Exception
	 */
	public static void checkDefaultSizeDistributionUnit2(NanoMaterial nanoM, int i) throws Exception
	{
		String strU = String.valueOf(nanoM.getSizeDistribUnit2()).trim();
		String strValueAvg = String.valueOf(nanoM.getSizeDistribAvg2()).trim();
		String strValueHigh = String.valueOf(nanoM.getSizeDistribHigh2()).trim();
		String strValueLow = String.valueOf(nanoM.getSizeDistribLow2()).trim();
		Double value;
		
		if (strU.equals("nm") || strU.equals("null") || strU.equals("um"))
		{
			if (strU.equals("um"))
			{
				nanoM.setSizeDistribUnit2("nm");
				if (!strValueAvg.equals("null"))
				{
					value = nanoM.getSizeDistribAvg2() * 1.0E+03;
					nanoM.setSizeDistribAvg2(value);
				}
				if (!strValueHigh.equals("null"))
				{
					value = nanoM.getSizeDistribHigh2() * 1.0E+03;
					nanoM.setSizeDistribHigh2(value);
				}
				if (!strValueLow.equals("null"))
				{
					value = nanoM.getSizeDistribLow2() * 1.0E+03;
					nanoM.setSizeDistribLow2(value);
				}
			}
		}
		else
		{
			throw new Exception("Illegal size distribution unit 2: " + strU + " at row " + i);
		}
	}
	
	/**
	 * @author Wilson Melendez
	 * @param nanoM
	 * @param i
	 * @throws Exception
	 */
	public static void checkDefaultSerumConcentrationUnit(NanoMaterial nanoM, int i) throws Exception
	{
		String strU = String.valueOf(nanoM.getSerumConcentrationUnit()).trim();
		String strValue = String.valueOf(nanoM.getSerumConcentration()).trim();
		Double value;
		
		if (strU.equals("%") || strU.equals("null") || strU.equals("fraction"))
		{
			if (strU.equals("fraction"))
			{
				nanoM.setSerumConcentrationUnit("%");
				if (!strValue.equals("null"))
				{
					value = nanoM.getSerumConcentration() * 100.0;
					nanoM.setSerumConcentration(value);
				}
			}
		}
		else
		{
			throw new Exception("Illegal serum concentration units: " + strU + " at row = " + i);
		}
	}
	
	/**
	 * @author Wilson Melendez
	 * @param nanoM
	 * @param i
	 * @throws Exception
	 */
	public static void checkDefaultMcSerumConcentrationUnit(NanoMaterial nanoM, int i) throws Exception
	{
		String strU = String.valueOf(nanoM.getMc_serumConcentrationUnit()).trim();
		String strValue = String.valueOf(nanoM.getMc_serumConcentration()).trim();
		Double value;
		
		if (strU.equals("%") || strU.equals("null") || strU.equals("fraction"))
		{
			if (strU.equals("fraction"))
			{
				nanoM.setMc_serumConcentrationUnit("%");
				if (!strValue.equals("null"))
				{
					value = nanoM.getMc_serumConcentration() * 100.0;
					nanoM.setMc_serumConcentration(value);
				}
			}
		}
		else
		{
			throw new Exception("Illegal Mc_serum_concentration units: " + strU + " at row = " + i);
		}
	}
	
	/**
	 * @author Wilson Melendez
	 * @param nanoM
	 * @param i
	 * @throws Exception
	 */
	public static void checkDefaultAntibioticConcentrationUnit(NanoMaterial nanoM, int i) throws Exception
	{
		String strU = String.valueOf(nanoM.getAntibioticConcentrationUnit()).trim();
		String strValue = String.valueOf(nanoM.getAntibioticConcentration()).trim();
		Double value;
		
		if (strU.equals("%") || strU.equals("null") || strU.equals("fraction"))
		{
			if (strU.equals("fraction"))
			{
				nanoM.setAntibioticConcentrationUnit("%");
				if (!strValue.equals("null"))
				{
					value = nanoM.getAntibioticConcentration() * 100.0;
					nanoM.setAntibioticConcentration(value);
				}
			}
		}
		else
		{
			throw new Exception("Illegal serum concentration units: " + strU + " at row = " + i);
		}
	}
	
	/**
	 * @author Wilson Melendez
	 * @param nanoM
	 * @param i
	 * @throws Exception
	 */
	public static void checkDefaultMcAntibioticConcentrationUnit(NanoMaterial nanoM, int i) throws Exception
	{
		String strU = String.valueOf(nanoM.getMc_antibioticConcentrationUnit()).trim();
		String strValue = String.valueOf(nanoM.getMc_antibioticConcentration()).trim();
		Double value;
		
		if (strU.equals("%") || strU.equals("null") || strU.equals("fraction"))
		{
			if (strU.equals("fraction"))
			{
				nanoM.setMc_antibioticConcentrationUnit("%");
				if (!strValue.equals("null"))
				{
					value = nanoM.getMc_antibioticConcentration() * 100.0;
					nanoM.setMc_antibioticConcentration(value);
				}
			}
		}
		else
		{
			throw new Exception("Illegal Mc_serum concentration units: " + strU + " at row = " + i);
		}
	}
	
	/**
	 * @author Wilson Melendez
	 * @param nanoM
	 * @param i
	 * @throws Exception
	 */
	public static void checkDefaultDOMUnit(NanoMaterial nanoM, int i) throws Exception
	{
		String strU = String.valueOf(nanoM.getDomUnit()).trim();
		String strValue = String.valueOf(nanoM.getDomConcentration()).trim();
		Double value;
		
		if (strU.equals("mg/L") || strU.equals("null") || strU.equals("ug/L"))
		{
			if (strU.equals("ug/L"))
			{
				nanoM.setDomUnit("mg/L");
				if (!strValue.equals("null"))
				{
					value = nanoM.getDomConcentration() / 1000.0;
					nanoM.setDomConcentration(value);
				}
			}
		}
		else
		{
			throw new Exception("Illegal DOM concentration unit: " + strU + " row = " + i);
		}
	}
	
	/**
	 * @author Wilson Melendez
	 * @param nanoM
	 * @param i
	 * @throws Exception
	 */
	public static void checkDefaultMcDOMUnit(NanoMaterial nanoM, int i) throws Exception
	{
		String strU = String.valueOf(nanoM.getMc_domUnit()).trim();
		String strValue = String.valueOf(nanoM.getMc_domConcentration()).trim();
		Double value;
		
		if (strU.equals("mg/L") || strU.equals("null") || strU.equals("ug/L"))
		{
			if (strU.equals("ug/L"))
			{
				nanoM.setMc_domUnit("mg/L");
				if (!strValue.equals("null"))
				{
					value = nanoM.getMc_domConcentration() / 1000.0;
					nanoM.setMc_domConcentration(value);
				}
			}
		}
		else
		{
			throw new Exception("Illegal Mc_DOM unit: " + strU + " row = " + i);
		}
	}
	
	/**
	 * @author Wilson Melendez
	 * @param nanoM
	 * @param i
	 * @throws Exception
	 */
	public static void checkDefaultSalinityUnit(NanoMaterial nanoM, int i) throws Exception
	{
		String strU = String.valueOf(nanoM.getSalinityUnit()).trim();
		
		if (strU.equals("ppt") || strU.equals("null") || strU.equals("psu") || strU.equals("g/kg"))
		{
			if (strU.equals("psu") || strU.equals("g/kg"))
			{
				nanoM.setSalinityUnit("ppt");
			}
		}
		else
		{
			throw new Exception("Illegal Salinity concentration unit: " + strU + " row = " + i);
		}
	}
	
	/**
	 * @author Wilson Melendez
	 * @param nanoM
	 * @param i
	 * @throws Exception
	 */
	public static void checkDefaultMcSalinityUnit(NanoMaterial nanoM, int i) throws Exception
	{
		String strU = String.valueOf(nanoM.getMc_salinityUnit()).trim();
		
		if (strU.equals("ppt") || strU.equals("null") || strU.equals("psu") || strU.equals("g/kg"))
		{
			if (strU.equals("psu") || strU.equals("g/kg"))
			{
				nanoM.setMc_salinityUnit("ppt");
			}
		}
		else
		{
			throw new Exception("Illegal Salinity concentration unit: " + strU + " row = " + i);
		}
	}
	
	/**
	 * @author Wilson Melendez
	 * @param nanoM
	 * @param i
	 * @throws Exception
	 */
	public static void checkDefaultParticleExposureDurationUnit(NanoMaterial nanoM, int i) throws Exception
	{
		String strU = String.valueOf(nanoM.getParticleExposDurationUnit()).trim();
		String strValue = String.valueOf(nanoM.getParticleExposDuration()).trim();
		Double value;
		
		if (strU.equals("hours") || strU.equals("null") || strU.equals("minutes"))
		{
			if (strU.equals("minutes"))
			{
				nanoM.setParticleExposDurationUnit("hours");
				if (!strValue.equals("null"))
				{
					value = nanoM.getParticleExposDuration() / 60;
					nanoM.setParticleExposDuration(value);
				}
			}
		}
		else
		{
			throw new Exception("Illegal particle exposure duration unit: " + strU + " at row = " + i);
		}	
	}
	
	/**
	 * @author Wilson Melendez
	 * @param nanoM
	 * @param i
	 * @throws Exception
	 */
	public static void checkDefaultUVADoseUnit(NanoMaterial nanoM, int i) throws Exception
	{
		String strU = String.valueOf(nanoM.getUvaDoseUnit()).trim();
		String strValue = String.valueOf(nanoM.getUvaDose()).trim();
		Double value;
		
		if (strU.equals("J/cm^2") || strU.equals("null") || strU.equals("mJ/cm^2"))
		{
			if (strU.trim().equals("mJ/cm^2"))
			{
				nanoM.setUvaDoseUnit("J/cm^2");
				if (!strValue.equals("null"))
				{
					value = nanoM.getUvaDose() / 1000;
					nanoM.setUvaDose(value);
				}
			}
		}
		else
		{
			throw new Exception("Illegal UVA dose units: " + strU + " at rown = " + i);
		}
	}
	
	/**
	 * @author Wilson Melendez
	 * @param nanoM
	 * @param i
	 * @throws Exception
	 */
	public static void checkDefaultUVAExposureDurationUnit(NanoMaterial nanoM, int i) throws Exception
	{
		String strU = String.valueOf(nanoM.getUvaExposDurationUnit()).trim();
		String strValue = String.valueOf(nanoM.getUvaExposDuration()).trim();
		Double value;
		
		if (strU.equals("hours") || strU.equals("null") || strU.equals("minutes"))
		{
			if (strU.equals("minutes"))
			{
				nanoM.setUvaExposDurationUnit("hours");
				if (!strValue.equals("null"))
				{
					value = nanoM.getUvaExposDuration() / 60;
					nanoM.setUvaExposDuration(value);
				}
			}
		}
		else
		{
			throw new Exception("Illegal UVA exposure duration unit: " + strU + " at row = " + i);
		}	
	}
	
	
	/**
	 * @author Wilson Melendez
	 * @param nanoM
	 * @param i
	 * @throws Exception
	 */
	public static void checkDefaultViabilityUnit(NanoMaterial nanoM, int i) throws Exception
	{
		String strU = String.valueOf(nanoM.getViabilityUnit()).trim();
		String strValueAvg = String.valueOf(nanoM.getViabilityAvg()).trim();
		String strValueHigh = String.valueOf(nanoM.getViabilityHigh()).trim();
		String strValueLow = String.valueOf(nanoM.getViabilityLow()).trim();
		Double value;
		
		if (strU.equals("%") || strU.equals("null") || strU.equals("fraction"))
		{
			if (strU.equals("fraction"))
			{
				nanoM.setViabilityUnit("%");
				if (!strValueAvg.equals("null"))
				{
					value = nanoM.getViabilityAvg() * 100.0;
					nanoM.setViabilityAvg(value);
				}
				if (!strValueHigh.equals("null"))
				{
					value = nanoM.getViabilityHigh() * 100.0;
					nanoM.setViabilityHigh(value);
				}
				if (!strValueLow.equals("null"))
				{
					value = nanoM.getViabilityLow() * 100.0;
					nanoM.setViabilityLow(value);
				}
			}
		}
		else
		{
			throw new Exception("Illegal viability unit: " + strU + " at row = " + i);
		}
	}
	
	/**
	 * @author Wilson Melendez
	 * @param nanoM
	 * @param i
	 * @throws Exception
	 */
	public static void checkDefaultLC50Unit(NanoMaterial nanoM, int i) throws Exception
	{
		String strU = String.valueOf(nanoM.getLc50Unit()).trim();
		String strValue = String.valueOf(nanoM.getLc50()).trim();
		Double value;
		
		if (strU.equals("ug/mL") || strU.equals("null") || strU.equals("mg/mL"))
		{
			if (strU.equals("mg/mL"))
			{
				nanoM.setLc50Unit("ug/mL");
				if (!strValue.equals("null"))
				{
					value = nanoM.getLc50() * 1000.0;
					nanoM.setLc50(value);
				}
			}
		}
		else
		{
			throw new Exception("Illegal LC50 unit: " + strU + " at row = " + i);
		}
	}

	
	/**
	 * This method loops over the list array and passes the units and values
	 * to their corresponding methods for inspection of default units and conversion 
	 * of found non-default units, and updates units and values of fields in the list array.
	 * @author Wilson Melendez
	 * @param listNano
	 * @throws Exception
	 */
	public static void checkUnits(Vector<NanoMaterial> nanoMats) throws Exception
	{		
		try 
		{
			for(int i = 0; i < nanoMats.size(); i++)  
			{
				NanoMaterial nanoMaterial = nanoMats.get(i);
				checkDefaultCoatingAmountUnit(nanoMaterial, i);			
				checkDefaultPurityUnit(nanoMaterial, i);
				checkDefaultContaminantUnit(nanoMaterial, i);				
				checkDefaultParticleOuterDiameterUnit(nanoMaterial, i);
				checkDefaultParticleInnerDiameterUnit(nanoMaterial, i);
				checkDefaultParticleLengthUnit(nanoMaterial, i);
				checkDefaultParticleThicknessUnit(nanoMaterial, i);
				checkDefaultSurfaceAreaUnit(nanoMaterial, i);
				checkDefaultTimeValueUnit(nanoMaterial, i);
				checkDefaultMcTimeValueUnit(nanoMaterial, i);
				checkDefaultParticleConcentrationUnit(nanoMaterial, i);
				checkDefaultMcParticleConcentrationUnit(nanoMaterial, i);
				checkDefaultMediumTempUnit(nanoMaterial, i);
				checkDefaultMcMediumTempUnit(nanoMaterial, i);
				checkDefaultZetaPotentialUnit(nanoMaterial, i);
				checkDefaultSizeDistributionUnit(nanoMaterial, i);	
				checkDefaultSizeDistributionUnit2(nanoMaterial, i);
				checkDefaultSerumConcentrationUnit(nanoMaterial, i);
				checkDefaultMcSerumConcentrationUnit(nanoMaterial, i);
				checkDefaultAntibioticConcentrationUnit(nanoMaterial, i);
				checkDefaultMcAntibioticConcentrationUnit(nanoMaterial, i);
				checkDefaultDOMUnit(nanoMaterial, i);	
				checkDefaultMcDOMUnit(nanoMaterial, i);
				checkDefaultSalinityUnit(nanoMaterial, i);	
				checkDefaultMcSalinityUnit(nanoMaterial, i);
				checkDefaultParticleExposureDurationUnit(nanoMaterial, i);				
				checkDefaultUVADoseUnit(nanoMaterial, i);
				checkDefaultUVAExposureDurationUnit(nanoMaterial, i);				
				checkDefaultViabilityUnit(nanoMaterial, i);
				checkDefaultLC50Unit(nanoMaterial, i);														
			}
		}
		catch (Exception ex)
		{
			System.out.println("Unknown/illegal units were found.  Check log file.");
			lOGGER.log(Level.SEVERE, "Illegal units exception found.", ex);
			throw ex;
		}
				
	}

}
