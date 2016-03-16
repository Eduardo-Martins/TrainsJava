/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.problem.test;


import com.problem.domain.Route;
import com.problem.domain.Town;
import com.problem.exception.NoSuchRouteException;
import com.problem.calculation.DistanceOfRoute;
import com.problem.domain.Itinerary;
import com.problem.calculation.NumberRoundTrip;
import com.problem.calculation.NumberRoundTripPerDistance;
import com.problem.calculation.RouteCalculation;
import com.problem.calculation.ShortestRoute;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Eduardo
 */
public class TrainsTest {
    
    public TrainsTest() {
    }
    private Itinerary it = null;
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
         it = new Itinerary();
        
        Town a = new Town();
        a.setName("a");
        Town b = new Town();
        b.setName("b");
        Town c = new Town();
        c.setName("c");
        Town d = new Town();
        d.setName("d");
        Town e = new Town();
        e.setName("e");
        
        Route ab5=new Route();
        ab5.setTownOrigin(a);
        ab5.setTownDestination(b);
        ab5.setDistance(5L);
        ab5.setRef("ab5");
        
        Route bc4 = new Route();
        bc4.setTownOrigin(b);
        bc4.setTownDestination(c);
        bc4.setDistance(4L);
        bc4.setRef("bc4");
        
        Route cd8 = new Route();
        cd8.setTownOrigin(c);
        cd8.setTownDestination(d);
        cd8.setDistance(8L);
        cd8.setRef("cd8");
        
        //potencial de conflito
        Route dc8= new Route();
        dc8.setTownOrigin(d);
        dc8.setTownDestination(c);
        dc8.setDistance(8L);
        dc8.setRef("dc8");
        
        Route de6= new Route();
        de6.setTownOrigin(d);
        de6.setTownDestination(e);
        de6.setDistance(6L);
        de6.setRef("de6");
        
        Route ad5 = new Route();
        ad5.setTownOrigin(a);
        ad5.setTownDestination(d);
        ad5.setDistance(5L);
        ad5.setRef("ad5");
        
        Route ce2 = new Route();
        ce2.setTownOrigin(c);
        ce2.setTownDestination(e);
        ce2.setDistance(2L);
        ce2.setRef("ce2");
        
        Route eb3 = new Route();
        eb3.setTownOrigin(e);
        eb3.setTownDestination(b);
        eb3.setDistance(3L);
        eb3.setRef("eb3");
        
        Route ae7 = new Route();
        ae7.setTownOrigin(a);
        ae7.setTownDestination(e);
        ae7.setDistance(7L);
        ae7.setRef("ae7");
        
        it.getRoutes().add(ab5);
        it.getRoutes().add(bc4);
        it.getRoutes().add(cd8);
        it.getRoutes().add(dc8);
        it.getRoutes().add(de6);
        it.getRoutes().add(ad5);
        it.getRoutes().add(ce2);
        it.getRoutes().add(eb3);
        it.getRoutes().add(ae7);
    }
    
    @After
    public void tearDown() {
        it.getRoutes().clear();
    }

     @Test
     public void testMockObjectSize() {
         assertTrue(it.getRoutes().size()==9);
     }
     
     @Test(expected = NoSuchRouteException.class)
     public void testPeformDistanceOfRoute() throws Exception{
        
         RouteCalculation strategy = new RouteCalculation(new DistanceOfRoute());
         Itinerary it2=it.buildItineraries("a-b-c");
         long route=strategy.executeStrategy(it2);
         assertTrue(route==9);
            
         Itinerary it3=it.buildItineraries("a-d");
         long route2=strategy.executeStrategy(it3); 
         assertTrue(route2==5);
         
         Itinerary it4=it.buildItineraries("a-d-c");
         long route3=strategy.executeStrategy(it4); 
         assertTrue(route3==13);
         
         Itinerary it5=it.buildItineraries("a-e-b-c-d");
         long route4=strategy.executeStrategy(it5); 
         assertTrue(route4==22);
         
         Itinerary it6=it.buildItineraries("a-e-d");
         long route5=strategy.executeStrategy(it6);    
    }
    
    @Test
    public void testShortestRoute() throws Exception{
          
        it.formatItineraries("a", "c",true,5);
        Itinerary it1=it;
        RouteCalculation strategy = new RouteCalculation(new ShortestRoute());  
         long route=strategy.executeStrategy(it1);
         assertTrue(route==9);
        
         it.formatItineraries("b", "b",true,5);
          Itinerary it2=it;
         long route2=strategy.executeStrategy(it2);
         assertTrue(route2==9);     
    }
    
    @Test
    public void testNumberRoundTrip() throws Exception{
        
        it.formatItineraries("c", "c", true,3);
        RouteCalculation strategy = new RouteCalculation(new NumberRoundTrip(3,"l"));
        Itinerary it1=it;
         long route=strategy.executeStrategy(it1);
         assertTrue(route==2);
         
         it.formatItineraries("a", "c",false,5);
         RouteCalculation strategy1 = new RouteCalculation(new NumberRoundTrip(4,"e"));
         Itinerary it2=it;
         long route2=strategy1.executeStrategy(it2);
         assertTrue(route2==3);
    }
    
    @Test
    public void testNumberRoundTripPerDistance() throws Exception{
        
        it.formatItineraries("c", "c", false, 20);
        Itinerary it1 = it;
        RouteCalculation strategy = new RouteCalculation(new NumberRoundTripPerDistance("c","c", 30));
        long route = strategy.executeStrategy(it1);
        assertTrue(route == 7);
        
    }
}
