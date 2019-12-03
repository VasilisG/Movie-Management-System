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
public class MovieCriteriaPrice implements MovieCriteria{
    
    private double individualPrice;
    private double lowerPrice;
    private double upperPrice;
    private int type;
    
    public MovieCriteriaPrice(double individualPrice, double lowerPrice, double upperPrice, int type){
        this.individualPrice = individualPrice;
        this.lowerPrice = lowerPrice;
        this.upperPrice = upperPrice;
        this.type = type;
    }
    
    public ArrayList<Movie> meetIndividualPrice(ArrayList<Movie> movies){
        ArrayList<Movie> filteredMovies = new ArrayList<Movie>();
        for(Movie movie : movies){
            switch(type){
                case Constants.EQ:
                    if(movie.getPrice() == individualPrice){
                        filteredMovies.add(movie);
                    }
                    break;
                case Constants.LT:
                    if(movie.getPrice() < individualPrice){
                        filteredMovies.add(movie);
                    }
                    break;
                case Constants.MT:
                    if(movie.getPrice() > individualPrice){
                        filteredMovies.add(movie);
                    }
                    break;
                case Constants.LEQ:
                    if(movie.getPrice() <= individualPrice){
                        filteredMovies.add(movie);
                    }
                    break;
                case Constants.MEQ:
                    if(movie.getPrice() >= individualPrice){
                        filteredMovies.add(movie);
                    }
                    break;
            }
        }
        return filteredMovies;
    }
    
    public ArrayList<Movie> meetRangePrice(ArrayList<Movie> movies){
        ArrayList<Movie> filteredMovies = new ArrayList<Movie>();
        for(Movie movie : movies){
            double moviePrice = movie.getPrice();
            if(moviePrice >= lowerPrice && moviePrice <= upperPrice){
                filteredMovies.add(movie);
            }
        }
        return filteredMovies;
    }

    @Override
    public ArrayList<Movie> meetCriteria(ArrayList<Movie> movies) {
        if(individualPrice == -1) return meetRangePrice(movies);
        else return meetIndividualPrice(movies);
    }
}
