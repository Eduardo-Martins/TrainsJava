/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.problem.calculation;

import com.problem.domain.Itinerary;

/**
 *
 * @author Eduardo
 */
public interface RouteStrategy {
    
    /**
     *
     * @param itinerary
     * @return
     * @throws Exception
     */
    long performRouteCalculation(Itinerary itinerary) throws Exception;
}
