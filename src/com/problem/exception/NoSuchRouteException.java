/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.problem.exception;

/**
 *
 * @author Eduardo
 */
public class NoSuchRouteException extends Exception{

    /**
     *
     */
    public NoSuchRouteException() {
    }

    /**
     *
     * @param message
     */
    public NoSuchRouteException(String message) {
        super(message);
    }

    /**
     *
     * @param message
     * @param cause
     */
    public NoSuchRouteException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     *
     * @param cause
     */
    public NoSuchRouteException(Throwable cause) {
        super(cause);
    }

    /**
     *
     * @param message
     * @param cause
     * @param enableSuppression
     * @param writableStackTrace
     */
    public NoSuchRouteException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    
}
