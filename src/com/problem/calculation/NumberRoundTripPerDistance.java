/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.problem.calculation;

import com.problem.domain.Itinerary;
import com.problem.domain.Route;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Eduardo
 */
public class NumberRoundTripPerDistance implements RouteStrategy{
    
    private final String start;
    private final String end;
    private final long distance;

    public NumberRoundTripPerDistance(String start, String end, long distance) {
        this.start = start;
        this.end = end;
        this.distance=distance;
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
        Set<String> ls = new HashSet<String>();
       
        long sum = 0;
        int n = 0;
       
        for (String st1 : st) {

            String novo = st1.substring(0, n);
            n++;
            if (novo.length() == 20) {
                n = 0;
            }
            if (novo.endsWith("-")) {
                novo = novo.substring(0, novo.length() - 1);
            }
            if (novo.endsWith(start.toLowerCase()) && !novo.equals(start.toLowerCase())) {
                sum = 0;
                for (Route route : itinerary.buildItineraries(novo).getRoutes()) {
                    sum += route.getDistance();
                }

                if (sum < distance) {
                    ls.add(novo);
                    sum = 0;
                }
            }
        }

        itinerary.getRoutesSequence().clear();
        return ls.size();
    }
    
    
    
}
