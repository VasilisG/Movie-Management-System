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
public class Reservation {
    
    private Customer customer;
    private Movie movie;
    private Date startDate;
    private Date endDate;
    private int status;
    private SimpleDateFormat simpleDateFormat;
    
    public Reservation(Customer customer, Movie movie, Date startDate, Date endDate, int status){
        this.customer = customer;
        this.movie = movie;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    }
    
    public Customer getCustomer(){
        return customer;
    }    
    
    public Movie getMovie(){
        return movie;
    }
    
    public Date getStartDate(){
        return startDate;
    }
    
    public String getFormattedStartDate(){
        return simpleDateFormat.format(this.getStartDate());
    }
    
    public Date getEndDate(){
        return endDate;
    }
    
    public String getFormattedEndDate(){
        return simpleDateFormat.format(this.getEndDate());
    }
    
    public int getStatus(){
        return status;
    }
    
    public String getStatusString(){
        if(status == Constants.STATUS_CANCELED) return Constants.CANCELED;
        else if(status == Constants.STATUS_COMPLETED) return Constants.COMPLETED;
        else if(status == Constants.STATUS_DUE) return Constants.DUE;
        else if(status == Constants.STATUS_ONGOING) return Constants.ONGOING;
        else if(status == Constants.STATUS_OVERDUE) return Constants.OVERDUE;
        else return null;
    }
    
    public void setCustomer(Customer customer){
        this.customer = customer;
    }
    
    public void setMovie(Movie movie){
        this.movie = movie;
    }
    
    public void setStartDate(Date startDate){
        this.startDate = startDate;
    }
    
    public void setEndDate(Date endDate){
        this.endDate = endDate;
    }
    
    public void setStatus(int status){
        this.status = status;
    }
    
    public boolean hasExpired(){
       return endDate.before(new Date());
    }
    
    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append("### Reservation info ###\n");
        builder.append("Customer\n");
        builder.append(customer + "\n");
        builder.append("Movie\n");
        builder.append(movie + "\n");
        builder.append("########################");
        
        return builder.toString();
    }
}