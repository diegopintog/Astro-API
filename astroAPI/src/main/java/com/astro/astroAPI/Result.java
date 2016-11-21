/**
 * 
 */
package com.astro.astroAPI;

/**
 * @author diego
 *
 */
public class Result {
	
	private String synastryCategory;
	private double julianDayFirst;
	private double julianDaySecond;
	private double calculationValue;
	
	// getters and setters for the body values
	public String getSynastryCategory(String value)
    {
        return this.synastryCategory;
    }
	public void setSynastryCategory(String value)
    {
        this.synastryCategory = value;
    }
    
	public double getJulianDayFirst(double value)
    {
        return this.julianDayFirst;
    }
    public void setJulianDayFirst(double value)
    {
        this.julianDayFirst = value;
    }
    
    public double getJulianDaySecond(double value)
    {
        return this.julianDaySecond;
    }
    public void setJulianDaySecond(double value)
    {
        this.julianDaySecond = value;
    }
    
    public double getCalculationValue(double value)
    {
        return this.calculationValue;
    }
    public void setCalculationValue(double value)
    {
        this.calculationValue = value;
    }

}
