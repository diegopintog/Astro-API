/**
 * 
 */
package com.astro.astroAPI.base;

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
	public String getSynastryCategory()
    {
        return this.synastryCategory;
    }
	public void setSynastryCategory(String value)
    {
        this.synastryCategory = value;
    }
    
	public double getJulianDayFirst()
    {
        return this.julianDayFirst;
    }
    public void setJulianDayFirst(double value)
    {
        this.julianDayFirst = value;
    }
    
    public double getJulianDaySecond()
    {
        return this.julianDaySecond;
    }
    public void setJulianDaySecond(double value)
    {
        this.julianDaySecond = value;
    }
    
    public double getCalculationValue()
    {
        return this.calculationValue;
    }
    public void setCalculationValue(double value)
    {
        this.calculationValue = value;
    }

}
