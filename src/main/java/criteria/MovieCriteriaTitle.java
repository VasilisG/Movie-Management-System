/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package criteria;

import com.mycompany.moviemanagementsystem.Constants;
import com.mycompany.moviemanagementsystem.Movie;
import java.util.ArrayList;

/**
 *
 * @author Vasilis
 */
public class MovieCriteriaTitle implements MovieCriteria{
    
    private String movieTitle;
    private int type; // 1 - Precise, 2 - Like
    
    public MovieCriteriaTitle(String movieTitle, int type){
        this.movieTitle = movieTitle;
        this.type = type;
    }
    
    public void setTitle(String movieTitle){
        this.movieTitle = movieTitle;
    }
    
    public void setType(int type){
        this.type = type;
    }
    
    public String getTitle(){
        return this.movieTitle;
    }
    
    public int getType(){
        return this.type;
    }
    
    private ArrayList<Movie> equalTitle(ArrayList<Movie> movies){
       String filterTitle = getTitle();
       ArrayList<Movie> filteredMovies = new ArrayList<Movie>();
       for(Movie movie : movies){
           String title = movie.getTitle();
           if(title.toLowerCase().equals(filterTitle.toLowerCase())){
               filteredMovies.add(movie);
           }
       }
       return filteredMovies;
    }
    
    private ArrayList<Movie> likeTitle(ArrayList<Movie> movies){
       String filterTitle = getTitle();
       ArrayList<Movie> filteredMovies = new ArrayList<Movie>();
       for(Movie movie : movies){
           String title = movie.getTitle();
           if(filterTitle.toLowerCase().contains(title.toLowerCase())){
               filteredMovies.add(movie);
           }
       }
       return filteredMovies;
    }

    @Override
    public ArrayList<Movie> meetCriteria(ArrayList<Movie> movies) {
       if(type==Constants.PRECISE) return equalTitle(movies);
       else return likeTitle(movies);
    }
    
}
