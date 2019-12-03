/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package criteria;

import com.mycompany.moviemanagementsystem.Movie;
import com.mycompany.moviemanagementsystem.MovieFactor;
import java.util.ArrayList;

/**
 *
 * @author Vasilis
 */
public class MovieCriteriaDirector implements MovieCriteria{
    
    private MovieFactor director;
    
    public MovieCriteriaDirector(MovieFactor director){
        this.director = director;
    }

    @Override
    public ArrayList<Movie> meetCriteria(ArrayList<Movie> movies) {
        ArrayList<Movie> filteredMovies = new ArrayList<Movie>();
        for(Movie movie : movies){
            if(director.same(movie.getDirector())){
                filteredMovies.add(movie);
            }
        }
        return filteredMovies;
    }
    
}
