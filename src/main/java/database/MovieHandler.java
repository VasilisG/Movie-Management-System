/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import com.mycompany.moviemanagementsystem.Constants;
import com.mycompany.moviemanagementsystem.Movie;
import com.mycompany.moviemanagementsystem.MovieFactor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Vasilis
 */
public class MovieHandler {
    
    private Connection connection;
        
    public MovieHandler(Connection connection){
        this.connection = connection;
    }
    
    public void insertRecord(Movie movie){
        StringBuilder builder = new StringBuilder();
        builder.append("INSERT INTO ");
        builder.append(Constants.MOVIE_TABLE_NAME);
        builder.append(" VALUES ");
        builder.append("( ");
        builder.append(movie.getCode());
        builder.append(" , ");
        builder.append(movie.getTitle());
        builder.append(" , ");
        builder.append(movie.getYear());
        builder.append(" , ");
        builder.append(movie.getPlot());
        builder.append(" , ");
        builder.append(stringifyActors(movie));
        builder.append(" , ");
        builder.append(stringifyDirector(movie));
        builder.append(" , ");
        builder.append(movie.getPlayTime());
        builder.append(" , ");
        builder.append(movie.getPrice());
        builder.append(" , ");
        builder.append(movie.getQuantity()); 
        builder.append(";");
        
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(builder.toString());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("ERROR: Could not execute statement.");
            //Logger.getLogger(MovieHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void deleteRecord(Movie movie){
        StringBuilder builder = new StringBuilder();
        builder.append("DELETE FROM TABLE ");
        builder.append(Constants.MOVIE_TABLE_NAME);
        builder.append(" WHERE ");
        builder.append("TITLE=");
        builder.append(movie.getTitle());
        builder.append(" AND ");
        builder.append("YEAR=");
        builder.append(movie.getYear());
        builder.append(",");
        
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(builder.toString());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("ERROR: Could not execute statement.");
        }
    }
    
    public void updateRecord(Movie movie){
        StringBuilder builder = new StringBuilder();
        builder.append("UPDATE " + Constants.MOVIE_TABLE_NAME + "\n");
        builder.append(" SET ");
        builder.append(" CODE=" + movie.getCode() + ", ");
        builder.append(" TITLE=" + movie.getTitle() + ", ");
        builder.append(" YEAR=" + movie.getYear() + ", ");
        builder.append(" PLOT=" + movie.getPlot() + ", ");
        builder.append(" ACTORS=" + stringifyActors(movie) + ", ");
        builder.append(" DIRECTOR=" + stringifyDirector(movie) + ", ");
        builder.append(" PLAYTIME=" + movie.getPlayTime() + ", ");
        builder.append(" PRICE=" + movie.getPrice() + ", ");
        builder.append(" QUANTITY=" + movie.getQuantity() + ", ");
        builder.append(" WHERE ");
        builder.append("CODE=");
        builder.append(movie.getTitle());
        builder.append(";");
        
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(builder.toString());
            preparedStatement.executeUpdate();
        } catch (SQLException ex){
            System.out.println("ERROR: Could not execute statement.");
        }
    }
    
    private String stringifyActors(Movie movie){
        ArrayList<MovieFactor> actors = movie.getActors();
        StringBuilder actorsBuilder = new StringBuilder();
        actors.stream().map((actor) -> {
            StringBuilder builder = new StringBuilder();
            builder.append(actor.getFirstName());
            builder.append(",");
            builder.append(actor.getLastName());
            return builder;            
        }).map((builder) -> {
            actorsBuilder.append(builder.toString());
            return builder;
        }).forEach((_item) -> {
            actorsBuilder.append(":");
        });
        return actorsBuilder.toString();
    }
    
    private String stringifyDirector(Movie movie){
        StringBuilder builder = new StringBuilder();
        MovieFactor director = movie.getDirector();
        builder.append(director.getFirstName());
        builder.append(",");
        builder.append(director.getLastName());
        
        return builder.toString();
    }
    
    public ArrayList<Movie> getMoviesFromDatabase(){
        try {
            ArrayList<Movie> movies = new ArrayList<Movie>();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(Constants.SQL_MOVIE_QUERY);
            while(resultSet.next()){
                String movieCode = resultSet.getString("code");
                String title = resultSet.getString("title");
                String type = resultSet.getString("type");
                int year = resultSet.getInt("year");
                String plot = resultSet.getString("plot");
                
                String actorsString = resultSet.getString("actors");
                ArrayList<MovieFactor> actors = deserializeActors(actorsString);
                
                String directorString = resultSet.getString("director");
                MovieFactor director = deserializeDirector(directorString);
                
                int playTime = resultSet.getInt("playtime");
                double price = resultSet.getDouble("price");
                int quantity = resultSet.getInt("quantity");
                
                Movie movie = new Movie(movieCode, title, type, year, plot, actors, director, playTime, price, quantity);
                movies.add(movie);
            }
            return movies;
        } catch (SQLException ex) {
            Logger.getLogger(MovieHandler.class.getName()).log(Level.SEVERE, null, "Could not retrieve movie records from database.");
            return null;
        }
    }
    
    private ArrayList<MovieFactor> deserializeActors(String actorsString){
        String[] splitActors = actorsString.split(":");
        ArrayList<MovieFactor> actors = new ArrayList<MovieFactor>();
        for(String splitActor : splitActors){
            String[] fullName = splitActor.split(",");
            String firstName = fullName[0];
            String lastName = fullName[1];
            MovieFactor actor = new MovieFactor(firstName, lastName);
            actors.add(actor);
        }
        return actors;
    }
    
    private MovieFactor deserializeDirector(String directorString){
        String[] splitDirector = directorString.split(",");
        String firstName = splitDirector[0];
        String lastName = splitDirector[1];
        MovieFactor movieFactor = new MovieFactor(firstName, lastName);
        return movieFactor;
    }
}
