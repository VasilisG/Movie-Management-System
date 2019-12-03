/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.moviemanagementsystem;

/**
 *
 * @author Vasilis
 */
public class MovieFactor {
    
    private String firstName;
    private String lastName;
    
    public MovieFactor(String firstName, String lastName){
        this.firstName = firstName;
        this.lastName = lastName;
    }
    
    public String getFirstName(){
        return firstName;
    }
    
    public String getLastName(){
        return lastName;
    }
    
    public void setFirstName(String firstName){
        this.firstName = firstName;
    }
    
    public void setLastName(String lastName){
        this.lastName = lastName;
    }
    
    public boolean same(MovieFactor actor){
        boolean equalFirstName = this.firstName.toLowerCase().equals(actor.getFirstName().toLowerCase());
        boolean equalLastName = this.lastName.toLowerCase().equals(actor.getLastName().toLowerCase());
        return equalFirstName && equalLastName;
    }
    
    public String toString(){
        return firstName + " " + lastName;
    }
}