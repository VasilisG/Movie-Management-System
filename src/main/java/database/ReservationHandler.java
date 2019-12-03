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
public class ReservationHandler extends EntityHandler {
    
    private static final String tableName = "Reservations";
    private  Reservation reservation;
    
    public ReservationHandler(Connection connection, Reservation reservation){
        super(connection);
        this.reservation = reservation;
    }
    
    public Reservation getReservation(){
        return reservation;
    }
    
    public void setReservation(Reservation reservation){
        this.reservation = reservation;
    }

    @Override
    public void insertRecord() {
       StringBuilder builder = new StringBuilder();
       builder.append("INSERT INTO ");
       builder.append(tableName + " ");
       builder.append("VALUES (");
       builder.append(reservation.getCustomer().getCode());
       builder.append(", ");
       builder.append(reservation.getMovie().getCode());
       builder.append(", ");
       builder.append(reservation.getStartDate());
       builder.append(", ");
       builder.append(reservation.getEndDate());
       builder.append(" );");
       
        try {
            preparedStatement = connection.prepareStatement(builder.toString());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("ERROR: Could not execute statement");
        }
       
    }

    @Override
    public void updateRecord() {
        StringBuilder builder = new StringBuilder();
        builder.append("UPDATE ");
        builder.append(tableName);
        builder.append("\n");
        builder.append("SET ");
        builder.append("START_DATE=" + reservation.getStartDate() + ", ");
        builder.append("END_DATE=" + reservation.getEndDate() + "\n");
        
        try {
            preparedStatement = connection.prepareStatement(builder.toString());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("ERROR: Could not prepare statement.");
        }
    }

    @Override
    public void deleteRecord() {
        StringBuilder builder = new StringBuilder();
        builder.append("DELETE FROM " + tableName + " \n");
        builder.append(";");
        
        try {
            preparedStatement = connection.prepareStatement(builder.toString());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("ERROR: Could not execute statement.");
        }
    }
    
    public ArrayList<Reservation> getReservationsFromDatabase(Connection connection, ArrayList<Movie> movies, ArrayList<Customer> customers){
        try {
            ArrayList<Reservation> reservations = new ArrayList<Reservation>();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(Constants.SQL_RESERVATION_QUERY);
            while(resultSet.next()){
                String movieCode = resultSet.getString("movie_code");
                String customerCode = resultSet.getString("customer_code");
                Date startDate = resultSet.getDate("start_date");
                Date endDate = resultSet.getDate("end_date"); 
                int status = resultSet.getInt("status");
                status = getReservationStatus(endDate, status);
                
                Movie movie = getMovieByCode(movies, movieCode);
                Customer customer = getCustomerByCode(customers, customerCode);
                
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