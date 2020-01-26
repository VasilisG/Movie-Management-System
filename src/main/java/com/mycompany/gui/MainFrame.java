/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.mycompany.moviemanagementsystem.Constants;
import com.mycompany.moviemanagementsystem.Customer;
import com.mycompany.moviemanagementsystem.Movie;
import com.mycompany.moviemanagementsystem.Reservation;
import com.mycompany.moviemanagementsystem.Transaction;
import database.CustomerHandler;
import database.MovieHandler;
import database.MySqlDatabase;
import database.ReservationHandler;
import database.TransactionHandler;
import java.sql.Connection;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;

/**
 *
 * @author Vasilis
 */
public class MainFrame extends JFrame {
    
    private JTabbedPane mainPane;
    private MoviePanel moviePanel;
    private CustomerPanel customerPanel;
    private ReservationPanel reservationPanel;
    private TransactionPanel transactionPanel;
    
    private ArrayList<Movie> movies;
    private ArrayList<Customer> customers;
    private ArrayList<Reservation> reservations;
    private ArrayList<Transaction> transactions;
    
    private Connection connection;
    private MovieHandler movieHandler;
    private CustomerHandler customerHandler;
    private ReservationHandler reservationHandler;
    private TransactionHandler transactionHandler;
    
    public MainFrame(){
        initDatabase();
        initFrame();
        initComponents();
        bindComponents();
    }
    
    private void initTabbedPane(){
        mainPane = new JTabbedPane();
        mainPane.addTab(Constants.MOVIES_TAB_TITLE, moviePanel);
        mainPane.addTab(Constants.CUSTOMERS_TAB_TITLE, customerPanel);
        mainPane.addTab(Constants.RESERVATIONS_TAB_TITLE, reservationPanel);
        mainPane.addTab(Constants.FINANCIAL_RECORDS_TAB_TITLE, transactionPanel);
    }
    
    private void initMoviePanel(){
        moviePanel = new MoviePanel(movies, reservations ,movieHandler);
        customerPanel = new CustomerPanel(customers, reservations, customerHandler);
        transactionPanel = new TransactionPanel(transactions);
        reservationPanel = new ReservationPanel(reservations, moviePanel, customerPanel, transactionPanel, reservationHandler, transactionHandler);
    }
    
    private void initDatabase(){
        MySqlDatabase db = new MySqlDatabase();
        db.initDatabase();
        
        connection = db.getConnection();
        
        movieHandler = new MovieHandler(connection);
        customerHandler = new CustomerHandler(connection);
        reservationHandler = new ReservationHandler(connection);
        transactionHandler = new TransactionHandler(connection);
        
        movies = movieHandler.getMoviesFromDatabase();
        customers = customerHandler.getCustomersFromDatabase();
        reservations = reservationHandler.getReservationsFromDatabase(movies, customers);
        transactions = transactionHandler.getTransactionsFromDatabase(movies, customers);
    }
    
    private void initFrame(){
        setVisible(true);
        setTitle(Constants.TITLE);
        setLocationRelativeTo(null);
        setSize(Constants.WIDTH, Constants.HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    private void initComponents(){
        initMoviePanel();
        initTabbedPane();
    }
    
    private void bindComponents(){
        add(mainPane);
    }    
}