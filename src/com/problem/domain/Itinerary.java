/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.problem.domain;

import com.problem.exception.NoSuchRouteException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
/**
 *
 * @author Eduardo
 */
public class Itinerary {
    
    private String ref;
    
    private List<Route> routes= new ArrayList<Route>();
    
    private Set<Itinerary> itineraries=new HashSet<Itinerary>();
    
    private Set<String> routesSequence=new HashSet<String>();

    /**
     *
     * @return
     */
    public String getRef() {
        return ref;
    }

    /**
     *
     * @param ref
     */
    public void setRef(String ref) {
        this.ref = ref;
    }

    /**
     *
     * @return
     */
    public List<Route> getRoutes() {
        return routes;
    }

    /**
     *
     * @param routes
     */
    public void setRoutes(List<Route> routes) {
        this.routes = routes;
    }

    /**
     *
     * @return
     */
    public Set<Itinerary> getItineraries() {
        return itineraries;
    }

    /**
     *
     * @param itineraries
     */
    public void setItineraries(Set<Itinerary> itineraries) {
        this.itineraries = itineraries;
    }

    /**
     *
     * @return
     */
    public Set<String> getRoutesSequence() {
        return routesSequence;
    }

    /**
     *
     * @param routesSequence
     */
    public void setRoutesSequence(Set<String> routesSequence) {
        this.routesSequence = routesSequence;
    }
       
    /**
     *
     * @param routes_sequence
     * @return
     * @throws Exception
     */
    public Itinerary buildItineraries(String routes_sequence) throws Exception {
        Itinerary it = new Itinerary();

        String[] route_array = routes_sequence.toLowerCase().split("-");

        for (Route r : routes) {
            for (int i = 0; i < route_array.length; i++) {
                StringBuilder sb1 = new StringBuilder();
                StringBuilder sb2 = new StringBuilder();
                String route_origin = r.getTownOrigin().getName().trim().toLowerCase();
                String route_destin = r.getTownDestination().getName().trim().toLowerCase();
                sb1.append(route_origin);
                sb1.append(route_destin);

                String route_origin_crit = route_array[i];
                String route_destin_crit = null;
                int j = i;
                if (j + 1 < route_array.length) {
                    route_destin_crit = route_array[j + 1];
                }
                sb2.append(route_origin_crit);
                sb2.append(route_destin_crit);

                if (sb1.toString().equals(sb2.toString())) {
                    it.getRoutes().add(r);
                }
            }
        }
        Set<String> ro = new HashSet();
        for (Route r : it.getRoutes()) {
            ro.add(r.getTownDestination().getName());
            ro.add(r.getTownOrigin().getName());
        }
        Set<String> roarr = new HashSet();

        for (String f : route_array) {
            roarr.add(f);
        }

        for (String f : route_array) {
            for (String sf : roarr) {
                if (!(roarr.contains(sf) && ro.contains(f))) {
                    throw new NoSuchRouteException();
                }
            }
        }
        return it;

    }

    /**
     *
     * @param start
     * @param end
     * @param cut
     * @param size
     * @throws Exception
     */
    public void formatItineraries(String start, String end, boolean cut,int size) throws Exception{
       
        Map<String, Set<String>> map=planItineraries(start, end, 20);
        for(String st:map.get(start)){
            String correct=null;
            if(cut){
            if(!start.equals(end)){
                correct=st.substring(st.indexOf(start), st.indexOf(end)+1);
            }else{
                List cl=new ArrayList();
                char[] cj=st.toCharArray();
                for(int c=0;c<cj.length;c++){
                   if(Character.toString(cj[c]).equals(start)){
                       cl.add(cj[c]);
                   }
                }
                if(cl.size()>2){
                String[] a=st.split(start);
                correct= start+a[1].toString()+start;
                }
            }
            }else{
               int l=(st.substring(0, size).length()-1);
                if(Character.toString(st.substring(0, size).charAt(l)).equals(end)){
                    correct=st.substring(0, size);
                }
                
            }
            char[] correct_arr=null;
            if(correct!=null){
             correct_arr=correct.toCharArray();
            }
             StringBuilder sb=new StringBuilder();
             if(correct_arr!=null){
               for(int i=0; i<correct_arr.length; i++){
                   
                   sb.append(Character.toString(correct_arr[i]));
                   if(i<(correct_arr.length-1)){
                       sb.append("-");
                   }
               }
                routesSequence.add(sb.toString());
            }
        }
        
    }
    
    /**
     *
     * @param start
     * @param end
     * @param size
     * @return
     * @throws Exception
     */
    public Map<String,Set<String>> planItineraries(String start, String end, int size) throws Exception{
        
        Map<String, Set<Route>> map = new HashMap<String, Set<Route>>();
        Map<String, Set<String>> map_processed = Collections.synchronizedMap(new HashMap<String, Set<String>>());
        Set<String> keys = new HashSet<String>();
       
        if (!routes.isEmpty()) {
            for (Route r : routes) {
                keys.add(r.getTownOrigin().getName());
                keys.add(r.getTownDestination().getName());
                for (String st : keys) {
                    if (r.getTownOrigin().getName().equals(st)) {
                        if (map.get(st) != null) {
                            Set<Route> ls = map.get(st);
                            ls.add(r);
                        } else {
                            Set<Route> ls = new HashSet<Route>();
                            ls.add(r);
                            map.put(st, ls);
                        }
                    }
                }
            }
        }
        for (String st : keys) {
            Set<Route> s1 = map.get(st);
            Set<String> s2 = new HashSet<String>();
            for (Route r : s1) {
                s2.add(r.getTownOrigin().getName() + r.getTownDestination().getName());
                map_processed.put(st, s2);
            }
        }

        Map<String, Set<String>> map_temp = new HashMap<String, Set<String>>();
        Collections.synchronizedMap(map_temp);
        Collections.synchronizedMap(map_processed);
        List<String> ls = new ArrayList<String>();
        ls.addAll(keys);
        while (!map_processed.isEmpty()) {
            for (int i = 0; i < ls.size(); ++i) {
                List<String> ls2 = new ArrayList<String>();
                ls2.addAll(map_processed.get(ls.get(i)));
                for (int j = 0; j < ls2.size(); ++j) {
                    Character c = ls2.get(j).charAt(ls2.get(j).length() - 1);
                    List<Route> ls3 = new ArrayList<Route>();
                    ls3.addAll(map.get(c.toString()));
                    for (int h = 0; h < ls3.size(); h++) {
                        String nova_st = ls2.get(j) + ls3.get(h).getTownDestination().getName();
                        Set<String> ss = map_processed.get(ls.get(i));
                        ss.remove(ls2.get(j));
                        ss.add(nova_st);
                        map_temp.put(ls.get(i), ss);
                    }
                }
            }
            List<String> ls4 = new ArrayList<String>();
            ls4.addAll(map_temp.get(start));
            for (int i = 0; i < ls4.size(); i++) {
                if ((ls4.get(i).length()) == size) {
                    map_processed.clear();
                    break;
                }
            }

        }
        return map_temp;
    }        
}
