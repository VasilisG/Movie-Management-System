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
        builder.append(Constants.CUSTOMER_TABLE_NAME + " ");
        builder.append("VALUES ");
        builder.append("( ");
        builder.append(customer.getCode());
        builder.append(customer.getFirstName());
        builder.append(customer.getLastName());
        builder.append(customer.getAddress());
        builder.append(customer.getEmailAddress());
        builder.append(customer.getPhoneNumber());
        builder.append(customer.isMemberString());
        
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
        builder.append("CODE=" + customer.getCode() + ", ");
        builder.append("FIRST_NAME=" + customer.getFirstName() + ", ");
        builder.append("LAST_NAME=" + customer.getLastName() + ", ");
        builder.append("ADDRESS=" + customer.getAddress());
        builder.append("EMAIL_ADDRESS=" + customer.getEmailAddress());
        builder.append("PHONE_NUMBER=" + customer.getPhoneNumber());
        builder.append("MEMBER=" + customer.isMemberString());
        builder.append("\n");
        builder.append("WHERE ");
        builder.append("CODE=" + customer.getCode() + ";");
        
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
        builder.append("WHERE CODE=" + customer.getCode());
        
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(builder.toString());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("ERROR: Could not execute statement");
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
