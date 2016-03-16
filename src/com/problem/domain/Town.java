/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.problem.domain;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Eduardo
 */
public class Town {
    
    private String name;
    
    private Set<Route> trips=new HashSet<Route>();
    
    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     */
    public Set<Route> getTrips() {
        return trips;
    }

    /**
     *
     * @param trips
     */
    public void setTrips(Set<Route> trips) {
        this.trips = trips;
    }

}
