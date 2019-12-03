/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.moviemanagementsystem;

import java.util.ArrayList;

/**
 *
 * @author Vasilis
 */
public class Movie {
    
    private String code;
    private String title;
    private String type;
    private int year;
    private String plot;
    private ArrayList<MovieFactor> actors;
    private MovieFactor director;
    private int playTime;
    private double price;
    private int quantity;
    
    public Movie(String code, String title, String type, int year, String plot, ArrayList<MovieFactor> actors, MovieFactor director, int playTime, double price, int quantity){
        this.code = code;
        this.title = title;
        this.type = type;
        this.year = year;
        this.plot = plot;
        this.actors = actors;
        this.director = director;
        this.playTime = playTime;
        this.price = price;
        this.quantity = quantity;
    }
    
    public String getCode(){
        return code;
    }
    
    public String getTitle(){
        return title;
    }
    
    public String getType(){
        return type;
    }
    
    public int getYear(){
        return year;
    }
    
    public String getPlot(){
        return plot;
    }
    
    public ArrayList<MovieFactor> getActors(){
        return actors;
    }
    
    public MovieFactor getDirector(){
        return director;
    }
    
    public int getPlayTime(){
        return playTime;
    }
    
    public double getPrice(){
        return price;
    }
    
    public int getQuantity(){
        return quantity;
    }
    
    public void setCode(String code){
        this.code = code;
    }
    
    public void setTitle(String title){
        this.title = title;
    }
    
    public void setType(String type){
        this.type = type;
    }
    
    public void setYear(int year){
        this.year = year;
    }
    
    public void setPlot(String plot){
        this.plot = plot;
    }
    
    public void setActors(ArrayList<MovieFactor> actors){
        this.actors = actors;
    }
    
    public void setDirector(MovieFactor director){
        this.director = director;
    }
    
    public void setPlayTime(int playTime){
        this.playTime = playTime;
    }
    
    public void setPrice(double price){
        this.price = price;
    }
    
    public void setQuantity(int quantity){
        this.quantity = quantity;
    }
    
    public String info(){
        return title + " " + year;
    }
    
    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append("--- Movie info ---");
        builder.append("\nTitle: " + title);
        builder.append("\nType: " + type);
        builder.append("\nYear: " + year);
        builder.append("\nPlot: " + plot);
        builder.append("\nActors: ");
        if(actors != null){
            for(MovieFactor actor : actors){
                builder.append("\n" + actor);
            }
        }
        else builder.append("No actors were found.");
        builder.append("\nDirector: ");
        if(director != null){
            builder.append(director);
        }
        else builder.append("\nNo director found.");
        builder.append("\nPlay time: " + playTime + " minutes.");
        builder.append("\nPrice: " + price + "$");
        builder.append("\nQuantity: " + quantity + " pieces");
        builder.append("\n------------------");
        return builder.toString();
    }
}