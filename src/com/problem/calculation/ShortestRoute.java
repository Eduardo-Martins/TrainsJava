/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.problem.calculation;

import com.problem.domain.Itinerary;
import com.problem.domain.Route;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Eduardo
 */
public class ShortestRoute implements RouteStrategy{

    @Override
    public long performRouteCalculation(Itinerary itinerary) throws Exception {
        
        Set<String> st=itinerary.getRoutesSequence();
        List<Integer> ls= new ArrayList<Integer>();
        long sum=0;
        for(String st1:st){
            sum=0;
            if(!st1.isEmpty()){
            for(Route route: itinerary.buildItineraries(st1).getRoutes()){
                sum+=route.getDistance();
                
            }
           
            ls.add(Integer.valueOf(Long.toString(sum)));
             }
        }
        
        Collections.sort(ls);
        Collections.reverseOrder();
        itinerary.getRoutesSequence().clear();
        return ls.get(0);
    }    
}
