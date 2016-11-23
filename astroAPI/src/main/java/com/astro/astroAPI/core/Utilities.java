/**
 * 
 */
package com.astro.astroAPI.core;

import org.joda.time.DateTimeZone;
import org.joda.time.DateTime;

import com.astro.astroAPI.base.BodyPosition;

/**
 * @author diego
 *
 */
public class Utilities {
	private static double[] return_coordinates = new double[2];
    private static double return_result = 0;

    // Method: Given 2 planets and configuration parameters, a value of planetary relation effect will be returned
    public static double DefinitionAnalysisConjunction(BodyPosition firstBody, BodyPosition secondBody, int effectIndex, int angleNeeded, int orbValue)
    {
        // Caculate Angle between bodies
        double angleBetween = AngleBetweenDegrees(firstBody.getLongitude(), firstBody.getLatitude(), secondBody.getLongitude(), secondBody.getLatitude());

        // Evaluate Angle Relation
        return_result = AngleEvaluation(RadsToDegrees(angleBetween), effectIndex, angleNeeded, orbValue);

        /*if (return_result > 0)
        {
            System.out.println("Relation Pass CONJUNCTION");
            System.out.print("firstBody: ");
            System.out.println(firstBody.getBodyCode());
            System.out.print("secondBody: ");
            System.out.println(secondBody.getBodyCode());
            System.out.print("effectIndex: ");
            System.out.println(effectIndex);
            System.out.print("orbValue: ");
            System.out.println(orbValue);
            System.out.print("angleNeeded: ");
            System.out.println(angleNeeded);
            System.out.print("angle found: ");
            System.out.println(RadsToDegrees(angleBetween));
            double anglefix = angleNeeded - RadsToDegrees(angleBetween);
            if (anglefix < 0 )
            {
            	anglefix = anglefix * -1;
            }
            System.out.print("Angle betwen: ");
            System.out.println(anglefix);
            System.out.print("Orbs Found: ");
            System.out.println(anglefix);
            double[] orb = convertDegreesToArc(anglefix, angleNeeded);
            System.out.print("Orbs Found in Arc minutes: ");
            System.out.println(orb[0] + "°" + orb[1] + "'" + orb[2] + "''");
            System.out.print("Points: ");
            System.out.println(AngleEvaluation(RadsToDegrees(angleBetween), effectIndex, angleNeeded, orbValue));
        }*/

        return return_result;
    }

    // Method: Given 2 planets and configuration parameters, a value of planetary relation effect will be returned
    public static double DefinitionAnalysisComposite(BodyPosition firstBodyA, BodyPosition firstBodyB, BodyPosition secondBodyA, BodyPosition secondBodyB, int effectIndex, int angleNeeded, int orbValue)
    {
        // Calculate geographical midpoint between the first body of both users 
    	double[] midpointA = GeographicMidpointBetween(firstBodyA.getLongitude(), firstBodyA.getLatitude(), firstBodyB.getLongitude(), firstBodyB.getLatitude()); // returns double[2] --> [0] = Longitude and [1] = Latitude (degrees)

        // Calculate geographical midpoint between the second body of both users
    	double[] midpointB = GeographicMidpointBetween(secondBodyA.getLongitude(), secondBodyA.getLatitude(), secondBodyB.getLongitude(), secondBodyB.getLatitude()); // returns double[2] --> [0] = Longitude and [1] = Latitude (degrees)

        // Calculate de angle diference between both midpoints
    	double MidpointsAngleBetween = AngleBetweenVectors(midpointA[0], midpointA[1], midpointA[2], midpointB[0], midpointB[1], midpointB[2]);

        // Evaluate Angle Relation
        return_result = AngleEvaluation(RadsToDegrees(MidpointsAngleBetween), effectIndex, angleNeeded, orbValue);

        /*if (return_result > 0)
        {
            System.out.println("Relation Pass COMPOSITE");
            System.out.print("firstBody: ");
            System.out.println(firstBodyA.getBodyCode());
            System.out.print("secondBody: ");
            System.out.println(secondBodyB.getBodyCode());
            System.out.print("effectIndex: ");
            System.out.println(effectIndex);
            System.out.print("orbValue: ");
            System.out.println(orbValue);
            System.out.print("angleNeeded: ");
            System.out.println(angleNeeded);
            System.out.print("MidpointsAngleBetween: ");
            System.out.println(RadsToDegrees(MidpointsAngleBetween));

            System.out.print("angle found: ");
            System.out.println(RadsToDegrees(MidpointsAngleBetween));
            double anglefix = angleNeeded - RadsToDegrees(MidpointsAngleBetween);
            if (anglefix < 0)
            {
            	anglefix = anglefix * -1;
            }
            System.out.print("Angle betwen: ");
            System.out.println(anglefix);
            System.out.print("Orbs Found: ");
            System.out.println(anglefix);
            double[] orb = convertDegreesToArc(anglefix, angleNeeded);
            System.out.print("Orbs Found in Arc minutes: ");
            System.out.println(orb[0] + "°" + orb[1] + "'" + orb[2] + "''");

            System.out.print("Points: ");
            System.out.println(AngleEvaluation(RadsToDegrees(MidpointsAngleBetween), effectIndex, angleNeeded, orbValue));
        }*/
        
        return return_result;
    }

    // Method: Angle relation evaluation
    public static double AngleEvaluation(double angle, int effectIndex, int angleNeeded, int orbValue)
    {
        // compared relation angle with angled needed
        double angleDiference = angle - angleNeeded;
        //System.out.println(angleDiference);

        // to make future relations positive numbers are needed
        if (angleDiference < 0)
        {
            angleDiference = angleDiference * -1;
        }
        // angle separation most be +- orb permision to qualify for relation effect
        if (angleDiference <= orbValue)
        {
            // factor based on how precice the relation is (how close to the perfect value)
            double factor = ((orbValue - angleDiference) / orbValue);

            // effectIndex is the value for a perfect angle match (maximum value), by multiplying it to the factor we mark down this effect value proportionally to how perfect it was.
            return_result = effectIndex * factor;
        }
        else
        {
            // if angle diference is off the orb persmision, the effect is neglected and asign a value of 0
            return_result = 0;
        }

        return return_result;
    }

    // Method: Angle between two bodys Lat/Long coordinates [longitude, Latitude]
    public static double AngleBetweenDegrees(double longitudeFirstBody, double latitudeFirstBody, double longitudeSecondBody, double latitudeSecondBody)
    {
        // converting coordinates to vectors
        double[] vectorFirst  = latLonToVector(longitudeFirstBody, latitudeFirstBody);
        double[] vectorSecond = latLonToVector(longitudeSecondBody, latitudeSecondBody);

        // dot product calculation
        double dotproduct = vectorDotProduct(vectorFirst[0], vectorFirst[1], vectorFirst[2], vectorSecond[0], vectorSecond[1], vectorSecond[2]);

        // absolute values of vectors
        double absoluteFirst  = vectorAbsoluteValue(vectorFirst[0], vectorFirst[1], vectorFirst[2]);
        double absoluteSecond = vectorAbsoluteValue(vectorSecond[0], vectorSecond[1], vectorSecond[2]);

        double angleResult = Math.acos(dotproduct/(absoluteFirst * absoluteSecond));

        return angleResult;
    }
    // Method: Angle between two bodys Vectorial Coordinates [X, Y, Z]
    public static double AngleBetweenVectors(double X1, double Y1, double Z1, double X2, double Y2, double Z2)
    {
        // dot product calculation
        double dotproduct = vectorDotProduct(X1, Y1, Z1, X2, Y2, Z2);

        // absolute values of vectors
        double absoluteFirst  = vectorAbsoluteValue(X1, Y1, Z1);
        double absoluteSecond = vectorAbsoluteValue(X2, Y2, Z2);

        double angleResult = Math.acos(dotproduct / (absoluteFirst * absoluteSecond));

        return angleResult;
    }

    // Method: Midpoint between two bodys coordinates [longitude, Latitude]: Geographic Midpoint
    public static double[] GeographicMidpointBetween(double longitudeFirstBody, double latitudeFirstBody, double longitudeSecondBody, double latitudeSecondBody)
    {
        // converting coordinates to vectors
        double[] vectorFirst  = latLonToVector(longitudeFirstBody,  latitudeFirstBody);
        double[] vectorSecond = latLonToVector(longitudeSecondBody, latitudeSecondBody);

        // Compute weight (by time): w1 = (years1 * 365.25) + (months1 * 30.4375) + days1 | w2 = (years2 * 365.25) + (months2 * 30.4375) + days2
        // Locations are been weighted equal for now, thus, w1 = w2 = 1
        double W1 = 1;
        double W2 = 1;

        // Compute combined total weight
        double totalWeight = W1 + W2;

        // Compute weight averages on X, Y and Z catesian coordinates
        double X = ((vectorFirst[0] * W1) + (vectorSecond[0] * W2)) / totalWeight;
        double Y = ((vectorFirst[1] * W1) + (vectorSecond[1] * W2)) / totalWeight;
        double Z = ((vectorFirst[2] * W1) + (vectorSecond[2] * W2)) / totalWeight;

        double[] coordinates = new double[] { X, Y, Z };

        return coordinates;
    }

    // Method: Convert Lat/Lon degrees coordinates to n-vector
    public static double[] latLonToVector(double longitude, double latitude)
    {
        // convert degrees to radians
        double RadLon = DegreesToRad(longitude);
        double RadLat = DegreesToRad(latitude);

        // convert Lat/lon to Cartesian coordinates
        double X = Math.cos(RadLat) * Math.cos(RadLon);
        double Y = Math.cos(RadLat) * Math.sin(RadLon);
        double Z = Math.sin(RadLat);

        double[] vector = new double[] {X,Y,Z};

        return vector;
    }

    // Method: Convert n-vector to Lat/Lon degrees coordinates
    public static double[] vectorTolatLonDegrees(double X, double Y, double Z)
    {
        // Convert cartesian coordinates back to radians, and radians to degrees
        return_coordinates[0] = RadsToDegrees(Math.atan2(Y, X));               // Longitude
        double finalHypotenuse   = Math.sqrt(X * X + Y * Y);
        return_coordinates[1] = RadsToDegrees(Math.atan2(Z, finalHypotenuse)); // Latitude

        return return_coordinates;
    }

    // Method: vectors dot product
    public static double vectorDotProduct(double X1, double Y1, double Z1, double X2, double Y2, double Z2)
    {
        double dorProduct = X1 * X2 + Y1 * Y2 + Z1 * Z2;

        return dorProduct;
    }

    // Method: Absolute value of vector
    public static double vectorAbsoluteValue(double X, double Y, double Z)
    {
        double absoluteValue = Math.sqrt(Math.pow(X, 2) + Math.pow(Y, 2) + Math.pow(Z, 2));

        return absoluteValue;
    }

    // Method: Tranform Degrees to Radians
    public static double DegreesToRad(double degrees)
    {
        return_result = degrees * (Math.PI / 180);

        return return_result;
    }

    // Method: radians to Degrees conversion
    public static double RadsToDegrees(double radians)
    {
        return_result = radians * (180 / Math.PI);

        return return_result;
    }

    // Method: convert radians to degrees (as bearing: 0...360)
    public static double RadsToBearing(double radians)
    {
        return_result = (RadsToDegrees(radians) + 360) % 360;

        return return_result;
    }

    //  convert military hour to decimal
    public static double getDecimalHour(int hour, int minute, int second)
    {
        double decimaHour = hour + (minute / 60) + (second / 3600);

        return decimaHour;
    }

    // convert decimal degrees to Arc minutes, second
    public static double[] convertDegreesToArc(double decimal_degrees, double angleNeeded)
    {

        double minutes = (decimal_degrees - Math.floor(decimal_degrees)) * 60.0;
        double seconds = (minutes - Math.floor(minutes)) * 60.0;
        double tenths = (seconds - Math.floor(seconds)) * 10.0;

        // get rid of fractional part
        minutes = Math.floor(minutes);
        seconds = Math.floor(seconds);
        tenths  = Math.floor(tenths);

        double[] ArcDegrees = new double[] { Math.floor(decimal_degrees), minutes, seconds};

        return ArcDegrees;
    }

    // Convert Local time to UTC
    public static DateTime LocalToUTC(DateTime local, DateTimeZone sourceTimeZone)
    {  	
        // Convert UTC from Local tima and TimezoneId
    	DateTime utc = new DateTime(sourceTimeZone.convertLocalToUTC(local.getMillis(), false));
    	
        // DateTimeOffset utc = TimeZoneInfo.ConvertTimeToUtc(local, sourceTimeZone);

        /*System.out.format("Local Time:  %s%n, daylight: %s%n", local, sourceTimeZone);
        System.out.format("UTC:         %s%n", utc);
        System.out.println("");*/

        return utc;
    }
}
