/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import com.mycompany.moviemanagementsystem.Constants;
import com.mycompany.moviemanagementsystem.Customer;
import com.mycompany.moviemanagementsystem.Movie;
import com.mycompany.moviemanagementsystem.Reservation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Vasilis
 */
public class ReservationHandler {
    
    private Connection connection;
    
    public ReservationHandler(Connection connection){
        this.connection = connection;
    }

    public void insertRecord(Reservation reservation) {
       StringBuilder builder = new StringBuilder();
       builder.append("INSERT INTO ");
       builder.append(Constants.RESERVATION_TABLE_NAME + " ");
       builder.append("(movie_code, customer_code, start_date, end_date, status)");
       builder.append(" VALUES (");
       builder.append("\"" + reservation.getMovie().getCode() + "\"");
       builder.append(", ");
       builder.append("\"" + reservation.getCustomer().getCode() + "\"");
       builder.append(", ");
       builder.append("\"" + reservation.getFormattedStartDate() + "\"");
       builder.append(", ");
       builder.append("\"" + reservation.getFormattedEndDate() + "\"");
       builder.append(", ");
       builder.append("\"" + reservation.getStatus() + "\"");
       builder.append(" );");
       
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(builder.toString());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
       
    }

    public void updateRecord(Reservation reservation) {
        StringBuilder builder = new StringBuilder();
        builder.append("UPDATE ");
        builder.append(Constants.RESERVATION_TABLE_NAME);
        builder.append("\n");
        builder.append("SET ");
        builder.append("status=" + reservation.getStatus());
        builder.append(" WHERE ");
        builder.append("movie_code=" + reservation.getMovie().getCode());
        builder.append(" AND ");
        builder.append("customer_code=" + reservation.getCustomer().getCode());
        builder.append(";");
        
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(builder.toString());
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void deleteRecord(Reservation reservation) {
        StringBuilder builder = new StringBuilder();
        builder.append("DELETE FROM " + Constants.RESERVATION_TABLE_NAME + " \n");
        builder.append(" WHERE ");
        builder.append("movie_code=");
        builder.append(reservation.getMovie().getCode());
        builder.append(" AND ");
        builder.append("customer_code=");
        builder.append(reservation.getCustomer().getCode());
        builder.append(";");
        
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(builder.toString());
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void deleteAllRecords(){
        StringBuilder builder = new StringBuilder();
        builder.append("TRUNCATE " + Constants.RESERVATION_TABLE_NAME + ";");
        try{
            Statement statement = connection.createStatement();
            statement.executeUpdate(builder.toString());
        } catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    }
    
    public ArrayList<Reservation> getReservationsFromDatabase(ArrayList<Movie> movies, ArrayList<Customer> customers){
        try {
            ArrayList<Reservation> reservations = new ArrayList<Reservation>();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(Constants.SQL_RESERVATION_QUERY);
            while(resultSet.next()){
                String movieCode = resultSet.getString("movie_code");
                System.out.println("Movie code: " + movieCode);
                String customerCode = resultSet.getString("customer_code");
                System.out.println("Customer code: " + customerCode);
                Date startDate = resultSet.getDate("start_date");
                Date endDate = resultSet.getDate("end_date"); 
                int status = resultSet.getInt("status");
                status = getReservationStatus(endDate, status);
                
                Movie movie = getMovieByCode(movies, movieCode);
                System.out.println(movie);
                Customer customer = getCustomerByCode(customers, customerCode);
                System.out.println(customer);
                
                Reservation reservation = new Reservation(customer, movie, startDate, endDate, status);
                reservations.add(reservation);
            }
            return reservations;
        } catch (SQLException ex) {
            Logger.getLogger(ReservationHandler.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    private int getReservationStatus(Date endDate, int status){
        if(status == Constants.STATUS_COMPLETED || status == Constants.STATUS_CANCELED){
            return status;
        }
        else{
            Date currentDate = new Date();
            int dateCompare = currentDate.compareTo(endDate);
            if(dateCompare < 0){
                return Constants.STATUS_ONGOING;
            }
            else if(dateCompare == 0){
                return Constants.STATUS_DUE;
            }
            else return Constants.STATUS_OVERDUE;
        } 
    }
    
    private Movie getMovieByCode(ArrayList<Movie> movies, String movieCode){
        for(Movie movie : movies){
            System.out.println("Movie code from list: " + movie.getCode());
            if(movie.getCode().equals(movieCode)){
                return movie;
            }
        }
        return null;
    }
    
    private Customer getCustomerByCode(ArrayList<Customer> customers, String customerCode){
        for(Customer customer : customers){
            if(customer.getCode().equals(customerCode)){
                return customer;
            }
        }
        return null;
    }
}