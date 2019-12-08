/*R
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.moviemanagementsystem;

import com.mycompany.gui.MainFrame;
import database.CustomerHandler;
import database.MovieHandler;
import database.MySqlDatabase;
import database.ReservationHandler;
import database.TransactionHandler;
import java.awt.Frame;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author Vasilis
 */
public class Main {
    
    public static void main(String[] args){
        
//        try {
//            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (UnsupportedLookAndFeelException ex) {
//            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        MainFrame mainFrame = new MainFrame();
//        mainFrame.setExtendedState(mainFrame.getExtendedState() | JFrame.MAXIMIZED_BOTH);

         MySqlDatabase db = new MySqlDatabase();
         db.initDatabase();
         Connection connection = db.getConnection();
         
         MovieHandler movieHandler = new MovieHandler(connection);
         ArrayList<Movie> movies = movieHandler.getMoviesFromDatabase();
         System.out.println("Movie handler works.");
         
         CustomerHandler customerHandler = new CustomerHandler(connection);
         ArrayList<Customer> customers = customerHandler.getCustomersFromDatabase();
         System.out.println("Customer handler works.");
         
         ReservationHandler reservationHandler = new ReservationHandler(connection);
         ArrayList<Reservation> reservations = reservationHandler.getReservationsFromDatabase(movies, customers);
         System.out.println("Reservation handler works.");
         
         TransactionHandler transactionHandler = new TransactionHandler(connection);
         ArrayList<Transaction> transactions = transactionHandler.getTransactionsFromDatabase(movies, customers);
         System.out.println("Transaction handler works.");
    }
    
}
