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
public class Customer {
    
    private String code;
    private String firstName;
    private String lastName;
    private String address;
    private String emailAddress;
    private String phoneNumber;
    private boolean member;
    
    public Customer(String code, String firstName, String lastName, String address, String emailAddress, String phoneNumber, boolean member){
        this.code = code;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
        this.member = member;
    }
    
    public String getCode(){
        return code;
    }
    
    public String getFirstName(){
        return firstName;
    }
    
    public String getLastName(){
        return lastName;
    }
    
    public String getAddress(){
        return address;
    }
    
    public String getEmailAddress(){
        return emailAddress;
    }
    
    public String getPhoneNumber(){
        return phoneNumber;
    }
    
    public boolean isMember(){
        return member;
    }
    
    public void setCode(String code){
        this.code = code;
    }
    
    public void setFirstName(String firstName){
        this.firstName = firstName;
    }
    
    public void setLastName(String lastName){
        this.lastName = lastName;
    }
    
    public void setAddress(String address){
        this.address = address;
    }
    
    public void setEmailAddress(String emailAddress){
        this.emailAddress = emailAddress;
    }
    
    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }
    
    public void setMember(boolean member){
        this.member = member;
    }
    
    public String isMemberString(){
        return member?"Yes":"No";
    }
    
    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append("--- Customer info ---");
        builder.append("\nCode: " + code);
        builder.append("\nFirst name: " + firstName);
        builder.append("\nLast name: " + lastName);
        builder.append("\nAddress: " + address);
        builder.append("\nEmail address: " + emailAddress);
        builder.append("\nPhone number: " + phoneNumber);
        builder.append("\nMember: " + isMemberString());
        builder.append("\n---------------------");
        
        return builder.toString();
    }
}
