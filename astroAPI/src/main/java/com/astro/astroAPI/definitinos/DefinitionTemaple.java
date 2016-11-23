/**
 * 
 */
package com.astro.astroAPI.definitinos;

import com.astro.astroAPI.base.UserChart;
import com.astro.astroAPI.core.Utilities;

/**
 * @author diego
 *
 */
public class DefinitionTemaple {
	
	public static double CalculateDefinition(UserChart UserA, UserChart UserB){
		
		// Definition set for calculating the astrological effect
        // values are ordered as follow: 
        // double definition_analysis(BodyPosition firstBody, BodyPosition secondBody, int effectIndex, int angleNeeded, int orbValue)
		double definitionResult = 
				Utilities.DefinitionAnalysisConjunction(UserA.Mercury(), UserB.Jupiter(), 10,   0, 120) +
				Utilities.DefinitionAnalysisConjunction(UserA.Venus()  , UserB.Mars()   , 10,  30, 120) +
				Utilities.DefinitionAnalysisConjunction(UserA.Sun()    , UserB.Saturn() ,  8,  90, 120) +
				Utilities.DefinitionAnalysisConjunction(UserA.Venus()  , UserB.Pluto()  ,  8, 180, 120) +
				Utilities.DefinitionAnalysisComposite(UserA.Mercury()  , UserB.Mercury(), UserA.Jupiter(), UserB.Jupiter(), 10, 0, 120);
		
		// Added basic value for testing purposes
		definitionResult = definitionResult + 7;
		
		// Multiplying the result by a standardization value (to normalize results between all forecasts) 
        definitionResult = definitionResult * 10.0;
		
		return definitionResult;
	}

}
