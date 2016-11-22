/**
 * 
 */
package com.astro.astroAPI.base;

import swisseph.SwissEph;
import swisseph.SweConst;

/**
 * @author diego
 * 
 * This class builds the body data for the USER/Julianday given
 * 
 */
public class BodyPosition {
	
	// int value to specify the type of calculation
    private int flag = 0; // configuration for apparent body position in geocentric ecliptic polar coordinates(longitude, latitude, and distance) relative to the true equinox of the date
    
    // array of 6 doubles for longitude, latitude, distance, speed in long., speed in lat., and speed in dist.
    private double[] bodyCoordinates = new double[6];

    // character string to return error messages in case of error.
    private static StringBuffer serr = new StringBuffer();

    // Body Distance
    private double distance;
    
    // Body Latitude
    private double latitude;
    
    // Body Longitude
    private double longitude;
    
    // speed Body Distance
    private double speeddistance;
    
    // speed Body Latitude
    private double speedlatitude;
    
    // speed Body Longitude
    private double speedlongitude;

    // Body code number
    private int bodyCode;
    
    // getters and setters for the body values
    public double getLongitude()
    {
        return this.longitude;
    }
    public void setLongitude(double value)
    {
        this.longitude = value;
    }
    
    public double getLatitude()
    {
        return this.latitude;
    }
    public void setLatitude(double value)
    {
        this.latitude = value;
    }
    
    public double getDistance()
    {
        return this.distance;
    }
    public void setDistance(double value)
    {
        this.distance = value;
    }
    
    public double getSpeedLongitude()
    {
        return this.speedlongitude;
    }
    public void setSpeedLongitude(double value)
    {
        this.speedlongitude = value;
    }
    
    public double getSpeedLatitude()
    {
        return this.speedlatitude;
    }
    public void setSpeedLatitude(double value)
    {
        this.speedlatitude = value;
    }
    
    public double getSpeedDistance()
    {
        return this.speeddistance;
    }
    public void setSpeedDistance(double value)
    {
        this.speeddistance = value;
    }
    
    public int getBodyCode()
    {
        return this.bodyCode;
    }
    public void setBodyCode(int value)
    {
    	this.bodyCode = value;
    }
    
    /// <summary>
    /// Method to calculate de coordinates of a planet based on a given julianday in UTC.
    /// </summary>
    /// <param name="julianDay">Datetime for the body position calculation</param>
    /// <param name="bodyNumber">number of the body</param>
    /// 
    /// Body number index:
    /// [0]  = Sun  [1]  = Moon  [2]  = Mercury  [3]  = Venus   [4]  = Mars   [5]  = Jupiter   [6]  = Saturn   [7]  = Uranus
    /// [8]  = Neptune   [9]  = Pluto   [10] = Mean Node   [11] = True Node   [12] = Mean Apogee   [13] = Oscu Apogee   [14] = Earth
    /// <returns></returns>
    public void calc_coordinates(double julianDay, int bodyNumber)
    {
        String path = null;

        SwissEph sweph = new SwissEph(path);

        // set flag to calculate speed
        flag = SweConst.SEFLG_SPEED; // | SweConst.SEFLG_TRUEPOS

        sweph.swe_calc_ut(julianDay, bodyNumber, flag, bodyCoordinates, serr);
        
        sweph.swe_close();
        
        // bodyCoordinates = array of 6 doubles for longitude, latitude, distance, speed in long., speed in lat., and speed in dist.
        longitude      = bodyCoordinates[0];
        latitude       = bodyCoordinates[1];
        distance       = bodyCoordinates[2];
        speedlongitude = bodyCoordinates[3];
        speedlatitude  = bodyCoordinates[4];
        speeddistance  = bodyCoordinates[5];
        bodyCode  = bodyNumber;
    }

}
