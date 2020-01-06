/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.mycompany.gui.MoviePanel.MovieTableModel;
import com.mycompany.gui.ReservationPanel.ReservationTableModel;
import com.mycompany.moviemanagementsystem.Constants;
import com.mycompany.moviemanagementsystem.Customer;
import com.mycompany.moviemanagementsystem.Movie;
import com.mycompany.moviemanagementsystem.Reservation;
import com.mycompany.moviemanagementsystem.Status;
import database.ReservationHandler;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JFrame;
import validators.CustomerValidator;
import validators.MovieValidator;

/**
 *
 * @author Vasilis
 */
public class InsertReservationFrame extends javax.swing.JFrame implements WindowListener{

    /**
     * Creates new form InsertReservationFrame
     */
    
    private ArrayList<JButton> buttonList;
    private ArrayList<Movie> movies;
    private ArrayList<Customer> customers;
    private ArrayList<Reservation> reservations;
    private ButtonListener buttonListener;
    private ReservationListener reservationListener;
    private MovieValidator movieValidator;
    private CustomerValidator customerValidator;
    private MovieTableModel movieTableModel;
    private ReservationTableModel reservationTableModel;
    private ReservationHandler reservationHandler;
    
    public InsertReservationFrame(ArrayList<JButton> buttonList, 
                                  ArrayList<Movie> movies, 
                                  ArrayList<Customer> customers, 
                                  ArrayList<Reservation> reservations, 
                                  MovieTableModel movieTableModel, 
                                  ReservationTableModel reservationTableModel, 
                                  ReservationHandler reservationHandler) {
        initComponents();
        
        addWindowListener(this);
        
        this.buttonList = buttonList;
        this.movies = movies;
        this.customers = customers;
        this.reservations = reservations;
        this.movieTableModel = movieTableModel;
        this.reservationTableModel = reservationTableModel;
        this.reservationHandler = reservationHandler;
        
        movieValidator = new MovieValidator();
        customerValidator = new CustomerValidator();
        
        setMainPanelButtonsEnabled(false);
        bindListenerToButtons();
        
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setTitle(Constants.INSERT_RESERVATION_FRAME_TITLE);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    
    private void setMainPanelButtonsEnabled(boolean enabled){
        for(JButton button : buttonList){
            button.setEnabled(enabled);
        }
    }
    
    private void bindListenerToButtons(){
        buttonListener = new ButtonListener();
        reservationListener = new ReservationListener();
        
        insertReservationButton.addActionListener(buttonListener);
        cancelButton.addActionListener(buttonListener);
        
        movieCodeField.addActionListener(reservationListener);
        customerCodeField.addActionListener(reservationListener);
        daysToRentField.addActionListener(reservationListener);
    }
    
    private boolean isValidMovieCode(String movieCode){
        if(!movieValidator.isValidCode(movieCode)){
            return false;
        }
        for(Movie movie : movies){
            if(movieCode.equals(movie.getCode())){
                return true;
            }
        }
        return false;
    }
    
    private boolean isValidCustomerCode(String customerCode){
        if(!customerValidator.isValidCode(customerCode)){
            return false;
        }
        for(Customer customer : customers){
            if(customerCode.equals(customer.getCode())){
                return true;
            }
        }
        return false;
    }
    
    private boolean isValidDaysToRent(String daysToRent){
        try{
            int days = Integer.parseInt(daysToRent);
            return days > 0;
        }catch(NumberFormatException exception){
            return false;
        }
    }
    
    private Movie updateMovieQuantity(Movie movie){
        movie.setQuantity(movie.getQuantity()-1);
        return movie;
    }
    
    private void clearFields(){
        movieCodeField.setText("");
        customerCodeField.setText("");
        daysToRentField.setText("");
    }
    
    private Date getEndDate(Date startDate, int days){
        long ltime = startDate.getTime() + days * 24 * 60 * 60 * 1000;
        Date endDate = new Date(ltime);
        return endDate;
    }
    
    private int getMovieByCode(ArrayList<Movie> movies, String code){
        for(int i=0; i<movies.size(); i++){
            String movieCode = movies.get(i).getCode();
            if(movieCode.equals(code)){
                return i;
            }
        }
        return -1;
    }
    
    private Customer getCustomerByCode(ArrayList<Customer> customers, String code){
        for(Customer customer : customers){
            String customerCode = customer.getCode();
            if(customerCode.equals(code)){
                return customer;
            }
        }
        return null;
    }
    
    private void updateMovieRow(int index, MoviePanel.MovieTableModel movieTableModel, Movie movie){
        NumberFormat priceFormat = new DecimalFormat("#0.00");
        String price = priceFormat.format(movie.getPrice()) + " €";
        movieTableModel.setValueAt(movie.getCode(), index, 1);
        movieTableModel.setValueAt(movie.getTitle(), index, 2);
        movieTableModel.setValueAt(price, index, 3);
        movieTableModel.setValueAt(String.valueOf(movie.getQuantity()), index, 4);
    }

    @Override
    public void windowOpened(WindowEvent e) {}

    @Override
    public void windowClosing(WindowEvent e) {
        setMainPanelButtonsEnabled(true);
    }

    @Override
    public void windowClosed(WindowEvent e) {
        setMainPanelButtonsEnabled(true);
    }

    @Override
    public void windowIconified(WindowEvent e) {}

    @Override
    public void windowDeiconified(WindowEvent e) {}

    @Override
    public void windowActivated(WindowEvent e) {}

    @Override
    public void windowDeactivated(WindowEvent e) {}
    
    class ButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event) {
            Object source = event.getSource();
            if(source == insertReservationButton){
                String movieCode = movieCodeField.getText();
                String customerCode = customerCodeField.getText();
                String daysToRentString = daysToRentField.getText();
                
                int daysToRent = Integer.parseInt(daysToRentString);
                Date startDate = new Date();
                Date endDate = getEndDate(startDate, daysToRent);
                    
                Customer customer = getCustomerByCode(customers, customerCode);
                int movieIndex = getMovieByCode(movies, movieCode);
                if(movieIndex != -1 && customer != null){
                    Movie movie = updateMovieQuantity(movies.get(movieIndex));
                    movies.set(movieIndex, movie);
                    updateMovieRow(movieIndex, movieTableModel, movie);
                    Reservation reservation = new Reservation(customer, movie, startDate, endDate, Constants.STATUS_ONGOING);
                    
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                    String formattedFromDate = simpleDateFormat.format(reservation.getStartDate());
                    String formattedToDate = simpleDateFormat.format(reservation.getEndDate());
                    
                    reservations.add(reservation);
                    reservationTableModel.addRow(new Object[]{false, reservation.getCustomer().getCode(),
                                                            reservation.getMovie().getCode(),
                                                            formattedFromDate,
                                                            formattedToDate,
                                                            Constants.ONGOING});
                    reservationHandler.insertRecord(reservation);
                    Status.showInfoMessage(Constants.RESERVATION_ADDED);
                    clearFields();
                }
                else Status.showErrorMessage(Constants.INVALID_DATA);
                
            }
            if(source == cancelButton){
                setMainPanelButtonsEnabled(true);
                dispose();
            }
        } 
    }
    
    class ReservationListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event) {
            if(movieCodeField.hasFocus()){
                String movieCode = movieCodeField.getText();
                if(!movieValidator.isValidCode(movieCode)){
                    Status.showErrorMessage(Constants.INVALID_MOVIE_CODE);
                    movieCodeField.setText("");
                }
                else customerCodeField.requestFocus();
            }
            if(customerCodeField.hasFocus()){
                String customerCode = customerCodeField.getText();
                if(!customerValidator.isValidCode(customerCode)){
                    Status.showErrorMessage(Constants.INVALID_CUSTOMER_CODE);
                    customerCodeField.setText("");
                }
                else daysToRentField.requestFocus();
            }
            if(daysToRentField.hasFocus()){
                String daysToRentString = daysToRentField.getText();
                if(!isValidDaysToRent(daysToRentString)){
                    Status.showErrorMessage(Constants.INVALID_DAYS_TO_RENT);
                    daysToRentField.setText("");
                }
            }
        }
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        insertReservationPanel = new javax.swing.JPanel();
        movieCodeLabel = new javax.swing.JLabel();
        movieCodeField = new javax.swing.JTextField();
        customerCodeLabel = new javax.swing.JLabel();
        customerCodeField = new javax.swing.JTextField();
        daysToRentLabel = new javax.swing.JLabel();
        daysToRentField = new javax.swing.JTextField();
        cancelButton = new javax.swing.JButton();
        insertReservationButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        movieCodeLabel.setText("Movie code:");

        customerCodeLabel.setText("Customer code:");

        daysToRentLabel.setText("Days to rent:");

        cancelButton.setText("Cancel");

        insertReservationButton.setText("Insert");

        javax.swing.GroupLayout insertReservationPanelLayout = new javax.swing.GroupLayout(insertReservationPanel);
        insertReservationPanel.setLayout(insertReservationPanelLayout);
        insertReservationPanelLayout.setHorizontalGroup(
            insertReservationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(insertReservationPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(insertReservationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(daysToRentLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(customerCodeLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                    .addComponent(movieCodeLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(insertReservationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(insertReservationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(movieCodeField)
                        .addComponent(customerCodeField, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE))
                    .addComponent(daysToRentField, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(28, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, insertReservationPanelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(insertReservationButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cancelButton))
        );
        insertReservationPanelLayout.setVerticalGroup(
            insertReservationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(insertReservationPanelLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(insertReservationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(movieCodeLabel)
                    .addComponent(movieCodeField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(insertReservationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(customerCodeLabel)
                    .addComponent(customerCodeField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(insertReservationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(daysToRentLabel)
                    .addComponent(daysToRentField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 93, Short.MAX_VALUE)
                .addGroup(insertReservationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelButton)
                    .addComponent(insertReservationButton)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(insertReservationPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(insertReservationPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(InsertReservationFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(InsertReservationFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(InsertReservationFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(InsertReservationFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new InsertReservationFrame().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelButton;
    private javax.swing.JTextField customerCodeField;
    private javax.swing.JLabel customerCodeLabel;
    private javax.swing.JTextField daysToRentField;
    private javax.swing.JLabel daysToRentLabel;
    private javax.swing.JButton insertReservationButton;
    private javax.swing.JPanel insertReservationPanel;
    private javax.swing.JTextField movieCodeField;
    private javax.swing.JLabel movieCodeLabel;
    // End of variables declaration//GEN-END:variables
}
