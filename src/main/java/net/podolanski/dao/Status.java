/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.podolanski.dao;

/**
 *
 * @author maciej
 */

public enum Status {
    CANCELLED, ACCEPTED, DENIED,
    MODIFY, PROCESSING;
    
    private String name;
    
    Status() {
        name = this.toString();
    }
    
    public String getName() {
        return name;
    }
}
