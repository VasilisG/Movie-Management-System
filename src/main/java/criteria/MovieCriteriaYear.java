/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package criteria;

import com.mycompany.moviemanagementsystem.Constants;
import com.mycompany.moviemanagementsystem.Movie;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Vasilis
 */
public class MovieCriteriaYear implements MovieCriteria{
    
    private int movieYear;
    private String comparisonType;
    
    public MovieCriteriaYear(int movieYear, String comparisonType){
        this.movieYear = movieYear;
        this.comparisonType = comparisonType;
    }
    
    public void setMovieYear(int movieYear){
        this.movieYear = movieYear;
    }
    
    public int getMovieYear(){
        return this.movieYear;
    }
    
    public void setComparisonType(String comparisonType){
        this.comparisonType = comparisonType;
    }
    
    public String getComparisonType(){
        return this.comparisonType;
    }

    private ArrayList<Movie> equal(ArrayList<Movie> movies){
        ArrayList<Movie> filteredMovies = new ArrayList<Movie>();
        int filteredYear = getMovieYear();
        for(Movie movie : movies){
            int year = movie.getYear();
            if(year == filteredYear){
                filteredMovies.add(movie);
            }
        }
        return filteredMovies;
    }
    
    private ArrayList<Movie> less(ArrayList<Movie> movies){
        ArrayList<Movie> filteredMovies = new ArrayList<Movie>();
        int filteredYear = getMovieYear();
        for(Movie movie : movies){
            int year = movie.getYear();
            if(year < filteredYear){
                filteredMovies.add(movie);
            }
        }
        return filteredMovies;
    }
    
    private ArrayList<Movie> more(ArrayList<Movie> movies){
        ArrayList<Movie> filteredMovies = new ArrayList<Movie>();
        int filteredYear = getMovieYear();
        for(Movie movie : movies){
            int year = movie.getYear();
            if(year > filteredYear){
                filteredMovies.add(movie);
            }
        }
        return filteredMovies;
    }
    
    @Override
    public ArrayList<Movie> meetCriteria(ArrayList<Movie> movies) {
        String comparisonType = getComparisonType();
        if(comparisonType.equals(Constants.EQUAL)) return equal(movies);
        else if(comparisonType.equals(Constants.LESS)) return less(movies);
        else return more(movies);
    }
    
}
