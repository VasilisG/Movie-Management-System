/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package criteria;

import com.mycompany.moviemanagementsystem.Movie;
import java.util.ArrayList;

/**
 *
 * @author Vasilis
 */
public class MovieCriteriaType implements MovieCriteria{
    
    private String movieType;
    
    public MovieCriteriaType(String movieType){
        this.movieType = movieType;
    }
    
    public void setType(String movieType){
        this.movieType = movieType;
    }
    
    public String getType(){
        return this.movieType;
    }

    @Override
    public ArrayList<Movie> meetCriteria(ArrayList<Movie> movies) {
        String filteredType = getType();
        ArrayList<Movie> filteredMovies = new ArrayList<Movie>();
        for(Movie movie : movies){
            String type = movie.getType();
            if(type.equals(filteredType)){
                filteredMovies.add(movie);
            }
        }
        return filteredMovies;
    }
    
}
