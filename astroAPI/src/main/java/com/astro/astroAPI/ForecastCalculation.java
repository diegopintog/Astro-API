/**
 * 
 */
package com.astro.astroAPI;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.Named;
import com.google.api.server.spi.response.NotFoundException;

@Api(
	    name        = "ForecastWebServiceTemaplate",
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
	 */
	@ApiMethod(httpMethod = "GET", path = "getForecast")
	public String getForecastApp(@Named("id") String id) throws NotFoundException{
		try {  
			  
			String test = "";
			    
			return test; 
		    
		  } catch (IndexOutOfBoundsException e) {
		      throw new NotFoundException("forecast not found with an index: " + id);
		  }    
	  } 

}
