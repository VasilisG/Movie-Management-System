/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import com.mycompany.moviemanagementsystem.Constants;
import com.mycompany.moviemanagementsystem.Customer;
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
public class CustomerHandler{
    
    private Connection connection;
    
    public CustomerHandler(Connection connection){
        this.connection = connection;
    }

    public void insertRecord(Customer customer) {
        StringBuilder builder = new StringBuilder();
        builder.append("INSERT INTO ");
        builder.append(Constants.CUSTOMER_TABLE_NAME);
        builder.append("(code,first_name,last_name,address,email_address,phone_number,member) ");
        builder.append("VALUES ");
        builder.append("( ");
        builder.append(customer.getCode());
        builder.append(customer.getFirstName());
        builder.append(customer.getLastName());
        builder.append(customer.getAddress());
        builder.append(customer.getEmailAddress());
        builder.append(customer.getPhoneNumber());
        builder.append(customer.isMemberString());
        builder.append(" )");
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(builder.toString());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("ERROR: Could not execute statement.");
        }
        
    }

    public void updateRecord(Customer customer) {
        StringBuilder builder = new StringBuilder();
        builder.append("UPDATE " + Constants.CUSTOMER_TABLE_NAME + "\n");
        builder.append("SET ");
        builder.append("code=" + customer.getCode() + ", ");
        builder.append("first_name=" + customer.getFirstName() + ", ");
        builder.append("last_name=" + customer.getLastName() + ", ");
        builder.append("address=" + customer.getAddress());
        builder.append("email_address=" + customer.getEmailAddress());
        builder.append("phone_number=" + customer.getPhoneNumber());
        builder.append("member=" + customer.isMemberString());
        builder.append("\n");
        builder.append("WHERE ");
        builder.append("code=" + customer.getCode() + ";");
        
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(builder.toString());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("ERROR: Could not execute statement.");
        }
 
    }

    public void deleteRecord(Customer customer) {
        StringBuilder builder = new StringBuilder();
        builder.append("DELETE FROM " + Constants.CUSTOMER_TABLE_NAME + " ");
        builder.append("WHERE code=" + customer.getCode());
        
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(builder.toString());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("ERROR: Could not execute statement.");
        }
    }
    
    public void deleteAllRecords(){
        String query = "TRUNCATE " + Constants.CUSTOMER_TABLE_NAME;
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
        } catch(SQLException Ex){
            System.out.println("Error: Could not execute statement.");
        }
    }
    
    public ArrayList<Customer> getCustomersFromDatabase(){
        try {
            ArrayList<Customer> customers = new ArrayList<Customer>();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(Constants.SQL_CUSTOMER_QUERY);
            while(resultSet.next()){
                String customerCode = resultSet.getString("code");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String address = resultSet.getString("address");
                String email = resultSet.getString("email");
                String phoneNumber = resultSet.getString("phone_number");
                boolean isMember = resultSet.getBoolean("member");
                
                Customer customer = new Customer(customerCode, firstName, lastName, address, email, phoneNumber, isMember);
                customers.add(customer);
            }
            return customers;
        } catch (SQLException ex) {
            Logger.getLogger(CustomerHandler.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
