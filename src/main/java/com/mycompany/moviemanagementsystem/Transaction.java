/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.moviemanagementsystem;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Vasilis
 */
public class Transaction {
   
    private Date transactionDate;
    private String formattedDate;
    private Customer customer;
    private Movie movie;
    private SimpleDateFormat simpleDateFormat;
    
    public Transaction(Customer customer, Movie movie, Date transactionDate){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        this.transactionDate = transactionDate;
        this.formattedDate = simpleDateFormat.format(transactionDate);
        this.customer = customer;
        this.movie = movie;
        this.simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    }
        
    public Date getDate(){
        return transactionDate;
    }
    
     public String getFormattedDate(){
        return simpleDateFormat.format(this.getDate());
    }
    
    public Customer getCustomer(){
        return customer;
    }
    
    public Movie getMovie(){
        return movie;
    }
    
    public void setDate(Date transactionDate){
        this.transactionDate = transactionDate;
    }
    
    public void setCustomer(Customer customer){
        this.customer = customer;
    }
    
    public void setMovie(Movie movie){
        this.movie = movie;
    }
}
