/**
 * 
 */
package com.astro.astroAPI;

import com.astro.astroAPI.base.UserChart;
import com.astro.astroAPI.base.BodyPosition;
import com.astro.astroAPI.definitinos.DefinitionTemaple;

import com.astro.astroAPI.base.Result;

import com.astro.astroAPI.service.Constants;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.Named;
import com.google.api.server.spi.response.NotFoundException;
import com.google.gson.Gson;


@Api(
	    name        = "forcast",
	    version     = "v1",
	    title       = "Forecast Backend API",
	    description = "Forecasting core services",
	    clientIds   = {Constants.WEB_CLIENT_ID, Constants.API_EXPLORER_CLIENT_ID , Constants.ANDROID_CLIENT_ID, Constants.IOS_CLIENT_ID},
	    audiences   = {Constants.ANDROID_AUDIENCE}
	)

/**
 * @author diegopintog
 *
 */
public class ForecastCalculation {
	/**
	 * @param args
	 * 
	 * testing values: category = testcategory, julindayfirst = 2450029.958333, juliandaysecond = 2446374.105556
	 * 
	 */
	@ApiMethod(httpMethod = "GET", path = "getForecast")
	public Result getForecastApp(@Named("category") String category, @Named("julindayfirst") Double julindayfirst, @Named("juliandaysecond") double juliandaysecond) throws NotFoundException{
		try {  
			  
			// get planet chart for user A
            UserChart UserA = new UserChart();
            UserA.gen_chart(julindayfirst);

            // get planet chart for user B
            UserChart UserB = new UserChart();
            UserB.gen_chart(juliandaysecond);
            
            Result forecast = new Result();
            forecast.setSynastryCategory(category);
        	forecast.setJulianDayFirst(julindayfirst);
        	forecast.setJulianDaySecond(juliandaysecond);
            
            // get synastry Unexpected Events And Adventurousness value
            if (category == "testcategory")
            {
            	forecast.setCalculationValue(DefinitionTemaple.CalculateDefinition(UserA, UserB));
            }
            
            System.out.format("Forecast result for category %s%n are: %s%n" ,forecast.getSynastryCategory() ,forecast.getCalculationValue());            
		    
            return forecast;
            
		  } catch (IndexOutOfBoundsException e) {
		      throw new NotFoundException("No category found with this name: " + category);
		  }    
	  } 

}
