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
public class RouteCalculation {
    
    private RouteStrategy strategy;

    /**
     *
     * @param strategy
     */
    public RouteCalculation(RouteStrategy strategy) {
        this.strategy = strategy;
    }
    
    /**
     *
     * @param i
     * @return
     * @throws Exception
     */
    public long executeStrategy(Itinerary i)throws Exception{
       return strategy.performRouteCalculation(i);
        
    }
    
}
