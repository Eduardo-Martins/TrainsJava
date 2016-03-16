/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.problem.domain;

import java.util.Objects;

/**
 *
 * @author Eduardo
 */
public class Route {
    
    private Long distance;
    
    private String ref;
    
    private Town townDestination;
    
    private Town townOrigin;

    /**
     *
     * @return
     */
    public Long getDistance() {
        return distance;
    }

    /**
     *
     * @param distance
     */
    public void setDistance(Long distance) {
        this.distance = distance;
    }

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
    public Town getTownDestination() {
        return townDestination;
    }

    /**
     *
     * @param townDestination
     */
    public void setTownDestination(Town townDestination) {
        this.townDestination = townDestination;
    }

    /**
     *
     * @return
     */
    public Town getTownOrigin() {
        return townOrigin;
    }

    /**
     *
     * @param townOrigin
     */
    public void setTownOrigin(Town townOrigin) {
        this.townOrigin = townOrigin;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.distance);
        hash = 97 * hash + Objects.hashCode(this.ref);
        hash = 97 * hash + Objects.hashCode(this.townDestination);
        hash = 97 * hash + Objects.hashCode(this.townOrigin);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Route other = (Route) obj;
        if (!Objects.equals(this.distance, other.distance)) {
            return false;
        }
        if (!Objects.equals(this.ref, other.ref)) {
            return false;
        }
        if (!Objects.equals(this.townDestination, other.townDestination)) {
            return false;
        }
        if (!Objects.equals(this.townOrigin, other.townOrigin)) {
            return false;
        }
        return true;
    }
    
    
   
}
