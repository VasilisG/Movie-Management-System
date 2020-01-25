/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.mycompany.moviemanagementsystem.Constants;
import com.mycompany.moviemanagementsystem.Status;
import com.mycompany.moviemanagementsystem.Transaction;
import criteria.ReservationCriteriaDate;
import criteria.TransactionCriteria;
import criteria.TransactionCriteriaCustomer;
import criteria.TransactionCriteriaDate;
import criteria.TransactionCriteriaMovie;
import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import validators.DateValidator;

/**
 *
 * @author Vasilis
 */
public class SearchTransactionFrame extends javax.swing.JFrame implements WindowListener{

    /**
     * Creates new form SearchTransactionFrame
     */
    
    private ArrayList<Transaction> transactions;
    private ArrayList<Transaction> filteredTransactions;
    private DefaultTableModel transactionTableModel;
    private ArrayList<JButton> buttonList;
    private ButtonListener buttonListener;
    private FieldListener fieldListener;
    
    private ArrayList<TransactionCriteria> transactionCriteria;
    
    private DateValidator dateValidator;
    
    public SearchTransactionFrame(ArrayList<Transaction> transactions, ArrayList<Transaction> filteredTransaction,
                                  DefaultTableModel transactionTableModel, ArrayList<JButton> buttonList) {
        initComponents();
        
        this.transactions = transactions;
        this.filteredTransactions = filteredTransactions;
        this.transactionTableModel = transactionTableModel;
        this.buttonList = buttonList;
        
        setMainPanelButtonsEnabled(false);
        bindListenersToButtons();
        
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setTitle(Constants.TRANSACTIONS_SEARCH_FRAME_TITLE);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    
    private void bindListenersToButtons(){
        buttonListener = new ButtonListener();
        fieldListener = new FieldListener();
        
        clearButton.addActionListener(buttonListener);
        cancelButton.addActionListener(buttonListener);
        searchButton.addActionListener(buttonListener);
        
        clearButton.addActionListener(fieldListener);
        cancelButton.addActionListener(fieldListener);
        searchButton.addActionListener(fieldListener);
    }
    
    private void setMainPanelButtonsEnabled(boolean enabled){
        for(JButton button : buttonList){
            button.setEnabled(enabled);
        }
    }
    
    private void clearFields(){
        customerCodeField.setText("");
        movieCodeField.setText("");
        startDateField.setText("");
        endDateField.setText("");
    }
    
    private Date getDateFromString(String dateString){
        Date date;
        try {
            date = new SimpleDateFormat("dd-MM-yyyy").parse(dateString);
            return date;
        } catch (ParseException ex) {
            return null;
        }
    }
    
    private String formattedDate(Date date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String formattedFromDate = simpleDateFormat.format(date);
        return formattedFromDate;
    }

    @Override
    public void windowOpened(WindowEvent e) {}

    @Override
    public void windowClosing(WindowEvent event) {
        setMainPanelButtonsEnabled(true);
    }

    @Override
    public void windowClosed(WindowEvent event) {}

    @Override
    public void windowIconified(WindowEvent event) {}

    @Override
    public void windowDeiconified(WindowEvent event) {}

    @Override
    public void windowActivated(WindowEvent event) {}

    @Override
    public void windowDeactivated(WindowEvent event) {}
    
    
    class ButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event) {
            Object source = event.getSource();
            if(source == clearButton){
                clearFields();
            }
            if(source == searchButton){
                transactionCriteria = new ArrayList<TransactionCriteria>();
                
                String movieCode = movieCodeField.getText();
                String customerCode = customerCodeField.getText();
                String fromDateString = startDateField.getText();
                String toDateString = endDateField.getText();
                
                if(movieCode.length() > 0){
                    transactionCriteria.add(new TransactionCriteriaMovie(movieCode));
                }
                if(customerCode.length() > 0){
                    transactionCriteria.add(new TransactionCriteriaCustomer(customerCode));
                }
                if(dateValidator.isValidDate(fromDateString) && dateValidator.isValidDate(toDateString)){
                    if(fromDateString.length() > 0 && toDateString.length() > 0){
                        Date fromDate = getDateFromString(fromDateString);
                        Date toDate = getDateFromString(toDateString);
                        if(fromDate.compareTo(toDate) == 0){
                            transactionCriteria.add(new TransactionCriteriaDate(fromDate, fromDate, Constants.EQ));
                            System.out.println("Equal date criteria added.");
                        }
                        else {
                            transactionCriteria.add(new TransactionCriteriaDate(fromDate, toDate, Constants.RANGE));
                            System.out.println("Range criteria added.");
                        }
                    }
                    else if(fromDateString.length() > 0 && toDateString.length() == 0){
                        Date fromDate = getDateFromString(fromDateString);
                        transactionCriteria.add(new TransactionCriteriaDate(fromDate,null,Constants.MEQ));
                        System.out.println("Greater or equal criteria added.");
                    }
                    else if(fromDateString.length() == 0 && toDateString.length() > 0){
                        Date toDate = getDateFromString(toDateString);
                        transactionCriteria.add(new TransactionCriteriaDate(null,toDate,Constants.LEQ));
                        System.out.println("Less or equal criteria added.");
                    }
                }
                
                filteredTransactions = new ArrayList<>(transactions);
                if(transactionCriteria.size() > 0){
                  for(TransactionCriteria criteria : transactionCriteria){
                    filteredTransactions = criteria.meetCriteria(filteredTransactions);
                  }  
                }
                
                transactionTableModel.setRowCount(0);
                for(Transaction transaction : filteredTransactions){
                    String finalFromDateString = formattedDate(transaction.getDate());
                    
                    transactionTableModel.addRow(new Object[]{false,
                        finalFromDateString,
                        transaction.getCustomer().getCode(),
                        transaction.getMovie().getCode()
                    });
                }
                Status.showInfoMessage(Constants.FILTERED_RESULTS);
                
            }
            if(source == cancelButton){
                setMainPanelButtonsEnabled(true);
                dispose();
            }
        } 
    }
    
    class FieldListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event) {
            if(movieCodeField.hasFocus()){
                customerCodeField.requestFocus();
            }
            if(customerCodeField.hasFocus()){
                startDateField.requestFocus();
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

        movieCodeLabel = new javax.swing.JLabel();
        movieCodeField = new javax.swing.JTextField();
        customerCodeLabel = new javax.swing.JLabel();
        customerCodeField = new javax.swing.JTextField();
        startDateLabel = new javax.swing.JLabel();
        startDateField = new javax.swing.JTextField();
        endDateLabel = new javax.swing.JLabel();
        endDateField = new javax.swing.JTextField();
        cancelButton = new javax.swing.JButton();
        searchButton = new javax.swing.JButton();
        clearButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        movieCodeLabel.setText("Movie code:");

        customerCodeLabel.setText("Customer code:");

        startDateLabel.setText("Start date:");

        endDateLabel.setText("End date:");

        cancelButton.setText("Cancel");

        searchButton.setText("Search");

        clearButton.setText("Clear");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(endDateLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(startDateLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(customerCodeLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                    .addComponent(movieCodeLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(movieCodeField)
                    .addComponent(customerCodeField)
                    .addComponent(startDateField)
                    .addComponent(endDateField, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(382, Short.MAX_VALUE)
                .addComponent(clearButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(searchButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cancelButton)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(movieCodeLabel)
                    .addComponent(movieCodeField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(customerCodeLabel)
                    .addComponent(customerCodeField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(startDateLabel)
                    .addComponent(startDateField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(endDateLabel)
                    .addComponent(endDateField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelButton)
                    .addComponent(searchButton)
                    .addComponent(clearButton)))
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
//            java.util.logging.Logger.getLogger(SearchTransactionFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(SearchTransactionFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(SearchTransactionFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(SearchTransactionFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new SearchTransactionFrame().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelButton;
    private javax.swing.JButton clearButton;
    private javax.swing.JTextField customerCodeField;
    private javax.swing.JLabel customerCodeLabel;
    private javax.swing.JTextField endDateField;
    private javax.swing.JLabel endDateLabel;
    private javax.swing.JTextField movieCodeField;
    private javax.swing.JLabel movieCodeLabel;
    private javax.swing.JButton searchButton;
    private javax.swing.JTextField startDateField;
    private javax.swing.JLabel startDateLabel;
    // End of variables declaration//GEN-END:variables
}
