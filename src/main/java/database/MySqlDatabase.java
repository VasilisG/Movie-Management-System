/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import com.mycompany.moviemanagementsystem.Constants;
import com.mycompany.moviemanagementsystem.Status;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Vasilis
 */
public class MySqlDatabase {
    
    private Connection connection;
    
    public MySqlDatabase(){
        try {
            connection = DriverManager.getConnection(Constants.DATABASE_URL, Constants.USERNAME, Constants.PASSWORD);
        } catch (SQLException ex) {
            connection = null;
        }
    }
    
    public Connection getConnection(){
        return connection;
    }
        
    public void initDatabase(){
        
        Connection initialConnection = null;
        Statement initialStatement = null;
        Connection connection = null;
        Statement statement = null;
        
        
            
        try {
            System.out.println("Getting class for name...");
            Class.forName(Constants.DRIVER);
        } catch (ClassNotFoundException ex) {
            //Status.showErrorMessage(Constants.SQL_DRIVER_ERROR);
            Status.showErrorMessage(ex.getMessage());
            return;
        }
        
         try {
            System.out.println("Setting initial connection...");
            initialConnection = DriverManager.getConnection(Constants.CONNECTION_URL, Constants.USERNAME, Constants.PASSWORD);        
        } catch (SQLException ex){
            //Status.showErrorMessage(Constants.SQL_CONNECTION_ERROR);
            Status.showErrorMessage(ex.getMessage());
            return;
        }
         
         try {
            System.out.println("Creating initial statement...");
            initialStatement = initialConnection.createStatement();
        } catch (SQLException ex){
            //Status.showErrorMessage(Constants.SQL_STATEMENT_ERROR);
            Status.showErrorMessage(ex.getMessage());
            return;
        }
         
        try {
            System.out.println("Creating database...");
            createDatabase(initialConnection, initialStatement);
        } catch (SQLException ex) {
            //Status.showErrorMessage(Constants.SQL_DATABASE_ERROR);
            Status.showErrorMessage(ex.getMessage());
            return;
        }
        
        try {
            System.out.println("Creating connection...");
            connection = DriverManager.getConnection(Constants.DATABASE_URL, Constants.USERNAME, Constants.PASSWORD);        
        } catch (SQLException ex){
            //Status.showErrorMessage(Constants.SQL_CONNECTION_ERROR);
            Status.showErrorMessage(ex.getMessage());
            return;
        }
        
        try {
            System.out.println("Creating statement...");
            statement = connection.createStatement();
        } catch (SQLException ex){
            //Status.showErrorMessage(Constants.SQL_STATEMENT_ERROR);
            Status.showErrorMessage(ex.getMessage());
            return;
        }
        
        try {
            System.out.println("Creating movie table...");
            createMovieTable(connection, statement);
        } catch (SQLException ex){
            //Status.showErrorMessage(Constants.SQL_MOVIE_TABLE_ERROR);
            Status.showErrorMessage(ex.getMessage());
            return;
        }
        
        try {
            System.out.println("Creating customer table...");
            createCustomerTable(connection, statement);
        } catch (SQLException ex){
            //Status.showErrorMessage(Constants.SQL_CUSTOMER_TABLE_ERROR);
            Status.showErrorMessage(ex.getMessage());
            return;
        }
        
        try {
            System.out.println("Creating reservation table...");
            createReservationTable(connection, statement);
        } catch (SQLException ex){
            //Status.showErrorMessage(Constants.SQL_RESERVATION_TABLE_ERROR);
            Status.showErrorMessage(ex.getMessage());
            return;
        }
        
        try {
            System.out.println("Creating transaction table...");
            createTransactionTable(connection, statement);
        } catch (SQLException ex){
            //Status.showErrorMessage(Constants.SQL_TRANSACTION_TABLE_ERROR);
            Status.showErrorMessage(ex.getMessage());
            return;
        }
    }
    
    private static void createDatabase(Connection connection, Statement statement) throws SQLException{
        String createDatabaseQuery = "CREATE DATABASE IF NOT EXISTS " + Constants.DATABASE_NAME;
        statement = connection.createStatement();
        statement.execute(createDatabaseQuery);
    }
    
    private static void createMovieTable(Connection connection, Statement statement) throws SQLException{
        String movieTableQuery = "CREATE TABLE IF NOT EXISTS movies"
                                 + " (id INT PRIMARY KEY AUTO_INCREMENT,"
                                 + " code VARCHAR(10) NOT NULL,"
                                 + " title VARCHAR(100) NOT NULL,"
                                 + " type VARCHAR(20) NOT NULL,"
                                 + " year INT NOT NULL,"
                                 + " plot VARCHAR(1000),"
                                 + " actors VARCHAR(1000),"
                                 + " director VARCHAR(100),"
                                 + " playtime INT NOT NULL,"
                                 + " price DOUBLE,"
                                 + " quantity INT NOT NULL)";
                
        statement = connection.createStatement();
        statement.execute(movieTableQuery);
    }
    
    private static void createCustomerTable(Connection connection, Statement statement) throws SQLException {
        String customerTableQuery = "CREATE TABLE IF NOT EXISTS customers"
                                    + " (id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,"
                                    + " code VARCHAR(10) NOT NULL,"
                                    + " first_name VARCHAR(100),"
                                    + " last_name VARCHAR(100),"
                                    + " address VARCHAR(100),"
                                    + " email VARCHAR(100),"
                                    + " phone_number VARCHAR(10),"
                                    + " member BOOLEAN)";
        
        statement = connection.createStatement();
        statement.execute(customerTableQuery);
    }
    
    private static void createReservationTable(Connection connection, Statement statement) throws SQLException {
        String reservationTableQuery = "CREATE TABLE IF NOT EXISTS reservations"
                                       + " (id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,"
                                       + " movie_code VARCHAR(10) NOT NULL,"
                                       + " customer_code VARCHAR(10) NOT NULL,"
                                       + " start_date DATE NOT NULL,"
                                       + " end_date DATE NOT NULL,"
                                       + " status INT NOT NULL)";
        
        statement = connection.createStatement();
        statement.execute(reservationTableQuery);                               
    }
    
    private static void createTransactionTable(Connection connection, Statement statement) throws SQLException {
        String transactionTableQuery = "CREATE TABLE IF NOT EXISTS transactions"
                                       + " (id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,"
                                       + " movie_code VARCHAR(10) NOT NULL,"
                                       + " customer_code VARCHAR(10) NOT NULL,"
                                       + " date DATE NOT NULL)";
        
        statement = connection.createStatement();
        statement.execute(transactionTableQuery);
    }
    
    
}
