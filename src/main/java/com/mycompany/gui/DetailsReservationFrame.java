/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.mycompany.gui.ReservationPanel.ReservationTableModel;
import com.mycompany.moviemanagementsystem.Customer;
import com.mycompany.moviemanagementsystem.Movie;
import com.mycompany.moviemanagementsystem.Reservation;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author Vasilis
 */
public class DetailsReservationFrame extends javax.swing.JFrame implements WindowListener{

    /**
     * Creates new form DetailsReservationFrame
     */
    
    private Reservation reservation;
    private ArrayList<JButton> buttonList;
    
    public DetailsReservationFrame(Reservation reservation, ArrayList<JButton> buttonList) {
        initComponents();
        
        addListener();
        addWindowListener(this);
        
        this.reservation = reservation;
        this.buttonList = buttonList;
        
        setMainPanelButtonsEnabled(false);
        
        showReservationDetails();
        
        this.setResizable(false);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    
    private void setMainPanelButtonsEnabled(boolean enabled){
        for(JButton button : buttonList){
            button.setEnabled(enabled);
        }
    }
    
    private void showReservationDetails(){
        Movie movie = this.reservation.getMovie();
        Customer customer = this.reservation.getCustomer();
        
        movieCodeField.setText(movie.getCode());
        movieTitleField.setText(movie.getTitle());
        
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        String price = decimalFormat.format(movie.getPrice()) + " €";
        moviePriceField.setText(price);
        
        customerCodeField.setText(customer.getCode());
        customerFirstNameField.setText(customer.getFirstName());
        customerLastNameField.setText(customer.getLastName());
        
        fromDateField.setText(this.reservation.getStartDate().toString());
        toDateField.setText(this.reservation.getEndDate().toString());
    }
    
    private void addListener(){
        closeButton.addActionListener(new ButtonListener());
    }

    @Override
    public void windowOpened(WindowEvent e) {}

    @Override
    public void windowClosing(WindowEvent e) {
        setMainPanelButtonsEnabled(true);
    }

    @Override
    public void windowClosed(WindowEvent e) {}

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
           if(source == closeButton){
               setMainPanelButtonsEnabled(true);
               dispose();
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

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        movieCodeLabel = new javax.swing.JLabel();
        movieCodeField = new javax.swing.JTextField();
        movieTitleLabel = new javax.swing.JLabel();
        movieTitleField = new javax.swing.JTextField();
        moviePriceLabel = new javax.swing.JLabel();
        moviePriceField = new javax.swing.JTextField();
        customerCodeLabel = new javax.swing.JLabel();
        customerCodeField = new javax.swing.JTextField();
        customerFirstNameLabel = new javax.swing.JLabel();
        customerFirstNameField = new javax.swing.JTextField();
        customerLastNameLabel = new javax.swing.JLabel();
        customerLastNameField = new javax.swing.JTextField();
        fromDateLabel = new javax.swing.JLabel();
        fromDateField = new javax.swing.JTextField();
        toDateLabel = new javax.swing.JLabel();
        toDateField = new javax.swing.JTextField();
        closeButton = new javax.swing.JButton();

        jLabel1.setText("jLabel1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        movieCodeLabel.setText("Movie code:");

        movieCodeField.setEditable(false);

        movieTitleLabel.setText("Movie title:");

        movieTitleField.setEditable(false);

        moviePriceLabel.setText("Movie price:");

        moviePriceField.setEditable(false);

        customerCodeLabel.setText("Customer code:");

        customerCodeField.setEditable(false);

        customerFirstNameLabel.setText("Customer first name:");

        customerFirstNameField.setEditable(false);

        customerLastNameLabel.setText("Customer last name:");

        customerLastNameField.setEditable(false);

        fromDateLabel.setText("From:");

        fromDateField.setEditable(false);

        toDateLabel.setText("To:");

        toDateField.setEditable(false);

        closeButton.setText("Close");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(toDateLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(fromDateLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(customerFirstNameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                    .addComponent(customerCodeLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(moviePriceLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(movieTitleLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(movieCodeLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(customerLastNameLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(movieTitleField)
                    .addComponent(moviePriceField)
                    .addComponent(movieCodeField)
                    .addComponent(customerCodeField)
                    .addComponent(customerFirstNameField)
                    .addComponent(customerLastNameField)
                    .addComponent(fromDateField)
                    .addComponent(toDateField, javax.swing.GroupLayout.DEFAULT_SIZE, 370, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(closeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(movieCodeField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(movieCodeLabel))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(movieTitleLabel)
                    .addComponent(movieTitleField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(moviePriceLabel)
                    .addComponent(moviePriceField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(49, 49, 49)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(customerCodeLabel)
                    .addComponent(customerCodeField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(customerFirstNameLabel)
                    .addComponent(customerFirstNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(customerLastNameLabel)
                    .addComponent(customerLastNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(46, 46, 46)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fromDateLabel)
                    .addComponent(fromDateField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(toDateLabel)
                    .addComponent(toDateField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                .addComponent(closeButton)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
//            java.util.logging.Logger.getLogger(DetailsReservationFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(DetailsReservationFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(DetailsReservationFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(DetailsReservationFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new DetailsReservationFrame().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton closeButton;
    private javax.swing.JTextField customerCodeField;
    private javax.swing.JLabel customerCodeLabel;
    private javax.swing.JTextField customerFirstNameField;
    private javax.swing.JLabel customerFirstNameLabel;
    private javax.swing.JTextField customerLastNameField;
    private javax.swing.JLabel customerLastNameLabel;
    private javax.swing.JTextField fromDateField;
    private javax.swing.JLabel fromDateLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField movieCodeField;
    private javax.swing.JLabel movieCodeLabel;
    private javax.swing.JTextField moviePriceField;
    private javax.swing.JLabel moviePriceLabel;
    private javax.swing.JTextField movieTitleField;
    private javax.swing.JLabel movieTitleLabel;
    private javax.swing.JTextField toDateField;
    private javax.swing.JLabel toDateLabel;
    // End of variables declaration//GEN-END:variables
}
