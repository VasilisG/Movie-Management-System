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
public class TransactionHandler extends EntityHandler{
    
    private static final String tableName = "Transactions";
    private Transaction transaction;

    public TransactionHandler(Connection connection, Transaction transaction) {
        super(connection);
        this.transaction = transaction;
    }
    
    public Transaction getTransaction(){
        return transaction;
    }
    
    public void setTransaction(Transaction transaction){
        this.transaction = transaction;
    }

    @Override
    public void insertRecord() {
        StringBuilder builder = new StringBuilder();
        builder.append("INSERT INTO " + tableName + " ");
        builder.append("VALUES (");
        builder.append(transaction.getDate() + ", ");
        builder.append(transaction.getCustomer().getCode() + ", ");
        builder.append(transaction.getMovie().getCode() + ")");
        
        try {
            preparedStatement = connection.prepareStatement(builder.toString());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("ERROR: Could not execute statement.");
        }
        
    }

    @Override
    public void updateRecord() {
        StringBuilder builder = new StringBuilder();
        builder.append("UPDATE " + tableName + "\n");
        builder.append("SET ");
        builder.append("DATE=" + transaction.getDate() + ", ");
        builder.append("CUSTOMER_CODE=" + transaction.getCustomer().getCode() + ", ");
        builder.append("MOVIE_CODE=" + transaction.getMovie().getCode() + "\n");
        
        try {
            preparedStatement = connection.prepareStatement(builder.toString());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("ERROR: Could not execute statement.");
        }          
    }

    @Override
    public void deleteRecord() {
        StringBuilder builder = new StringBuilder();
        builder.append("DELETE FROM " + tableName + "\n");
        
        try {
            preparedStatement = connection.prepareStatement(builder.toString());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("ERROR: Could not execute statement.");
        }  
    }
    
    public ArrayList<Transaction> getTransactionsFromDatabase(Connection connection, ArrayList<Movie> movies, ArrayList<Customer> customers){
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
            Logger.getLogger(TransactionHandler.class.getName()).log(Level.SEVERE, null, ex);
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