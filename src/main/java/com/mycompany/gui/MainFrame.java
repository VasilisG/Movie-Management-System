/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.mycompany.moviemanagementsystem.Constants;
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
    
    public MainFrame(){
        init();
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
        moviePanel = new MoviePanel();
        customerPanel = new CustomerPanel();
        transactionPanel = new TransactionPanel();
        reservationPanel = new ReservationPanel(moviePanel, customerPanel, transactionPanel);
    }
    
    private void init(){
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