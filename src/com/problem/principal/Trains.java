/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.problem.principal;

import com.problem.calculation.DistanceOfRoute;
import com.problem.calculation.NumberRoundTrip;
import com.problem.calculation.NumberRoundTripPerDistance;
import com.problem.calculation.RouteCalculation;
import com.problem.calculation.ShortestRoute;
import com.problem.domain.Itinerary;
import com.problem.domain.Route;
import com.problem.domain.Town;
import com.problem.exception.NoSuchRouteException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Eduardo
 */
public class Trains {

  
  
public static void main(String[] args) {
   
        BufferedReader br = null;
        Town t1 = new Town();
        Town t2 = new Town();
        Route r = new Route();
        Itinerary it = new Itinerary();

        try {

            String sCurrentLine;

            br = new BufferedReader(new FileReader("trains.txt"));

            while ((sCurrentLine = br.readLine()) != null) {
                String[] st = sCurrentLine.split(",");
                for (int i = 0; i < st.length; i++) {

                    t1.setName(Character.toString(st[i].charAt(0)).toString().toLowerCase().trim());
                    t2.setName(Character.toString(st[i].charAt(1)).toString().toLowerCase().trim());
                    r.setRef(Character.toString(st[i].charAt(0)).toString().toLowerCase().trim() + Character.toString(st[i].charAt(1)).toString().toLowerCase().trim() + Character.toString(st[i].charAt(2)).toString().toLowerCase().trim());
                    r.setDistance(Long.valueOf(Character.toString(st[i].charAt(2)).toString().toLowerCase().trim()));
                    r.setTownOrigin(t1);
                    r.setTownDestination(t2);
                    it.getRoutes().add(r);
                    t1 = new Town();
                    t2 = new Town();
                    r = new Route();

                }
                try {
                    RouteCalculation strategy = new RouteCalculation(new DistanceOfRoute());
                    Itinerary it2 = it.buildItineraries("a-b-c");
                    long route = strategy.executeStrategy(it2);
                    System.out.println("Output#1:" + route);

                    Itinerary it3 = it.buildItineraries("a-d");
                    long route2 = strategy.executeStrategy(it3);
                    System.out.println("Output#2:" + route2);

                    Itinerary it4 = it.buildItineraries("a-d-c");
                    long route3 = strategy.executeStrategy(it4);
                    System.out.println("Output#3:" + route3);

                    Itinerary it5 = it.buildItineraries("a-e-b-c-d");
                    long route4 = strategy.executeStrategy(it5);
                    System.out.println("Output#4:" + route4);
                    try{
                    Itinerary it6 = it.buildItineraries("a-e-d");
                    long route5 = strategy.executeStrategy(it6);
                    }catch(NoSuchRouteException e){
                        System.out.println("Output#5:NO SUCH ROUTE");
                    }   
                    it.formatItineraries("c", "c", true, 3);
                    RouteCalculation strategyR1 = new RouteCalculation(new NumberRoundTrip(3, "l"));
                    Itinerary it7 = it;
                    long route7 = strategyR1.executeStrategy(it7);
                    System.out.println("Output#6:" + route7);

                    it.formatItineraries("a", "c", false, 5);
                    RouteCalculation strategyR2 = new RouteCalculation(new NumberRoundTrip(4, "e"));
                    Itinerary it8 = it;
                    long route8 = strategyR2.executeStrategy(it8);
                    System.out.println("Output#7:" + route8);

                    it.formatItineraries("a", "c", true, 5);
                    Itinerary it9 = it;
                    RouteCalculation strategy9 = new RouteCalculation(new ShortestRoute());
                    long route9 = strategy9.executeStrategy(it9);
                    System.out.println("Output#8:" + route9);

                    it.formatItineraries("b", "b", true, 5);
                    Itinerary it10 = it;
                    RouteCalculation strategy10 = new RouteCalculation(new ShortestRoute());
                    long route10 = strategy10.executeStrategy(it10);
                    System.out.println("Output#9:" + route10);
                    
                    it.formatItineraries("c", "c", false, 20);
                    Itinerary it11 = it;
                    RouteCalculation strategy11 = new RouteCalculation(new NumberRoundTripPerDistance("c","c",30));
                    long route11 = strategy11.executeStrategy(it11);
                    System.out.println("Output#10:" + route11);

                } catch (NoSuchRouteException en) {
                    System.out.println("Output#5:NO SUCH ROUTE");
                } catch (Exception enn) {
                    enn.printStackTrace();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

    }
   
}
