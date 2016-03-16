/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.problem.calculation;

import com.problem.domain.Itinerary;
import com.problem.domain.Route;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
/**
 *
 * @author Eduardo
 */
public class NumberRoundTrip implements RouteStrategy{
    
    private final int stops;
    private final String criteria;

    /**
     *
     * @param stops
     * @param criteria
     */
    public NumberRoundTrip(int stops, String criteria) {
        this.stops = stops;
        this.criteria=criteria;
    }
    
    /**
     *
     * @param itinerary
     * @return
     * @throws Exception
     */
    @Override
    public long performRouteCalculation(Itinerary itinerary) throws Exception {
        
        Set<String> st = itinerary.getRoutesSequence();
        List<Integer> ls = new ArrayList<Integer>();

        for (String st1 : st) {
            List<String> stunique = new ArrayList<String>();
            for (Route route : itinerary.buildItineraries(st1).getRoutes()) {
                stunique.add(route.getTownDestination().getName());
            }
            if (criteria.equals("l")&&stunique.size() < (stops + 1)) {
                ls.add(stunique.size());
                stunique.clear();
            }
            if(criteria.equals("e")&&stunique.size() == (stops)) {
                ls.add(stunique.size());
                stunique.clear();
            }
        }
        itinerary.getRoutesSequence().clear();
        return ls.size();
    }
       
}
