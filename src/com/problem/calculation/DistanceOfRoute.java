/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.problem.calculation;

import com.problem.domain.Itinerary;
import com.problem.domain.Route;
/**
 *
 * @author Eduardo
 */
public class DistanceOfRoute implements RouteStrategy{

    /**
     *
     * @param itinerary
     * @return
     * @throws Exception
     */
    @Override
    public long performRouteCalculation(Itinerary itinerary) throws Exception{
        Itinerary it =(Itinerary)itinerary;
        long sum = 0;
        for(Route route: it.getRoutes()){
            sum+=route.getDistance();
        }
        return sum;
    }
}
