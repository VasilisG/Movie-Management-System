/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import com.mycompany.moviemanagementsystem.Constants;
import com.mycompany.moviemanagementsystem.Customer;
import com.mycompany.moviemanagementsystem.Movie;
import com.mycompany.moviemanagementsystem.Transaction;
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
public class TransactionHandler {
    
    private Connection connection;

    public TransactionHandler(Connection connection) {
        this.connection = connection;
    }

    public void insertRecord(Transaction transaction) {
        StringBuilder builder = new StringBuilder();
        builder.append("INSERT INTO " + Constants.TRANSACTION_TABLE_NAME + " ");
        builder.append("(movie_code, customer_code, date)");
        builder.append(" VALUES (");
        builder.append("\"" + transaction.getMovie().getCode() + "\"" + ", ");
        builder.append("\"" + transaction.getCustomer().getCode() + "\"" + ", ");
        builder.append("\"" + transaction.getFormattedDate() + "\"" + ")");
        
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(builder.toString());
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }

    public void updateRecord(Transaction transaction) {
        StringBuilder builder = new StringBuilder();
        builder.append("UPDATE " + Constants.TRANSACTION_TABLE_NAME + "\n");
        builder.append("SET ");
        builder.append("movie_code=" + "\"" + transaction.getMovie().getCode() + "\"" + ", ");
        builder.append("customer_code=" + "\"" + transaction.getCustomer().getCode() + "\"" + ", ");
        builder.append("date=" + "\"" + transaction.getFormattedDate() + "\"" + ";");
        
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(builder.toString());
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }          
    }
    
    public ArrayList<Transaction> getTransactionsFromDatabase(ArrayList<Movie> movies, ArrayList<Customer> customers){
        try {
            ArrayList<Transaction> transactions = new ArrayList<Transaction>();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(Constants.SQL_TRANSACTION_QUERY);
            while(resultSet.next()){
                String movieCode = resultSet.getString("movie_code");
                String customerCode = resultSet.getString("customer_code");
                Date transactionDate = resultSet.getDate("date");
                
                Customer customer = getCustomerByCode(customers, customerCode);
                Movie movie = getMovieByCode(movies, movieCode);
                Transaction transaction = new Transaction(customer, movie, transactionDate);
                
                transactions.add(transaction);
            }
            return transactions;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }
    
    private Customer getCustomerByCode(ArrayList<Customer> customers, String customerCode){
        for(Customer customer : customers){
            if(customer.getCode().equals(customerCode)){
                return customer;
            }
        }
        return null;
    }
    
    private Movie getMovieByCode(ArrayList<Movie> movies, String movieCode){
        for(Movie movie : movies){
            if(movie.getCode().equals(movieCode)){
                return movie;
            }
        }
        return null;
    }
}