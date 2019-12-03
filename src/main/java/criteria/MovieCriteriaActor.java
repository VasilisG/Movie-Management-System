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
public class MovieCriteriaActor implements MovieCriteria{
    
    private ArrayList<MovieFactor> actors;
    
    public MovieCriteriaActor(ArrayList<MovieFactor> actors){
        this.actors = actors;
    }
    
    public void setActors(ArrayList<MovieFactor> actors){
        this.actors = actors;
    }
    
    public ArrayList<MovieFactor> getActors(){
        return actors;
    }
    
    public boolean actorContained(ArrayList<MovieFactor> movieActors, MovieFactor actor){
        for(MovieFactor movieActor : movieActors){
            if(!actor.same(movieActor)){
                return false;
            }
        }
        return true;
    }
    
    public boolean allActorsContained(ArrayList<MovieFactor> movieActors){
        for(MovieFactor actor : actors){
           if(!actorContained(movieActors, actor)){
               return false;
           }
        }
        return true;
    }

    @Override
    public ArrayList<Movie> meetCriteria(ArrayList<Movie> movies) {
        ArrayList<Movie> filteredMovies = new ArrayList<Movie>();
        for(Movie movie : movies){
           if(allActorsContained(movie.getActors())){
               filteredMovies.add(movie);
           }
        }
        return filteredMovies;
    }
    
}
