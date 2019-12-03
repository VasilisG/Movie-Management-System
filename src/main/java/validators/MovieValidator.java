/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validators;

import com.mycompany.moviemanagementsystem.Constants;
import com.mycompany.moviemanagementsystem.Movie;
import com.mycompany.moviemanagementsystem.MovieFactor;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Vasilis
 */
public class MovieValidator {
    
    
    
    public String getPrefix(){
        return Constants.CODE_PREFIX;
    }
    
    public boolean isValidCode(String code){
        return code.length() == Constants.SUFFIX_LENGTH && code.matches("[0-9]+");
    }
    
    public boolean isValidTitle(String title){
        return title.length() > Constants.MIN_LENGTH && title.length() < Constants.MAX_LENGTH;
    }
    
    public boolean isValidYear(int year){
        return year >= Constants.YEAR_THRESHOLD && year <= Calendar.getInstance().get(Calendar.YEAR);
    } 
    
    public boolean isValidPlot(String plot){
        return plot.length() > 0 && plot.length() <= Constants.MAX_PLOT_LENGTH;
    }
    
    public boolean isValidMovieFactor(MovieFactor factor){
        String firstName = factor.getFirstName().trim();
        String lastName = factor.getLastName().trim();
    
        boolean firstNameOK = firstName.length() >= Constants.MIN_NAME_LENGTH && firstName.length() < Constants.MAX_NAME_LENGTH && firstName.matches("[a-zA-Z ]+");
        boolean lastNameOK = lastName.length() >= Constants.MIN_NAME_LENGTH && lastName.length() < Constants.MAX_NAME_LENGTH  && lastName.matches("[a-zA-Z ]+");
        
        return firstNameOK && lastNameOK;
    }
    
    public boolean areValidActors(ArrayList<MovieFactor> actors){
        for(MovieFactor actor : actors){
            if(!isValidMovieFactor(actor)){
                return false;
            }
        }
        return true;
    }
    
    public boolean isValidDirector(MovieFactor director){
        return isValidMovieFactor(director);
    }
    
    public boolean isValidPlayTime(int playtime){
        return playtime > 0;
    }
    
    public boolean isValidPrice(double price){
        return price > 0;
    }
    
    public boolean isValidQuantity(int quantity){
        return quantity > 0;
    }
    
    public boolean hasUniqueCode(Movie movie, ArrayList<Movie> movies){
        for(Movie tempMovie : movies){
            if(movie.getCode().equals(tempMovie.getCode())){
                return false;
            }
        }
        return true;
    }
    
    public boolean isValidMovie(Movie movie){
        return !(movie == null) && isValidCode(movie.getCode()) && isValidTitle(movie.getTitle()) &&
               isValidYear(movie.getYear()) && isValidPlot(movie.getPlot()) &&
               areValidActors(movie.getActors()) && isValidMovieFactor(movie.getDirector()) &&
               isValidPlayTime(movie.getPlayTime()) && isValidPrice(movie.getPrice()) &&
               isValidQuantity(movie.getQuantity());
    }
}
