/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.mycompany.gui.CustomerPanel.CustomerTableModel;
import com.mycompany.moviemanagementsystem.Constants;
import com.mycompany.moviemanagementsystem.Customer;
import com.mycompany.moviemanagementsystem.Status;
import database.CustomerHandler;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import validators.CustomerValidator;

/**
 *
 * @author Vasilis
 */
public class EditCustomerFrame extends javax.swing.JFrame implements WindowListener{

    /**
     * Creates new form InsertCustomerFrame
     */
    
    private CustomerTableModel customerTableModel;
    private ArrayList<Customer> customers;
    private int index;
    private ArrayList<JButton> buttonList;
    private CustomerValidator customerValidator;
    private ButtonListener buttonListener;
    private CustomerHandler customerHandler;
    
    public EditCustomerFrame(int index, ArrayList<Customer> customers, CustomerTableModel customerTableModel, ArrayList<JButton> buttonList, CustomerHandler customerHandler) {
        initComponents();
        addWindowListener(this);
        bindListener();
        
        this.index = index;
        this.customers = customers;
        this.buttonList = buttonList;
        this.customerHandler = customerHandler;
        this.customerTableModel = customerTableModel;        
        this.customerValidator = new CustomerValidator();
        
        setMainPanelCustomerButtons(false);
        showCurrentCustomer(customers.get(index));
        
        this.setVisible(true);
        this.setTitle(Constants.EDIT_CUSTOMER_FRAME_TITLE);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    
    private void setMainPanelCustomerButtons(boolean enabled){
        for(JButton button : buttonList){
            button.setEnabled(enabled);
        }
    }
    
    private void bindListener(){
        buttonListener = new ButtonListener();
        editCustomerButton.addActionListener(buttonListener);
        cancelButton.addActionListener(buttonListener);
    }
    
    private void showCurrentCustomer(Customer customer){
        customerCodeField.setText(customer.getCode());
        customerFirstNameField.setText(customer.getFirstName());
        customerLastNameField.setText(customer.getLastName());
        customerEmailAddressField.setText(customer.getEmailAddress());
        customerAddressField.setText(customer.getAddress());
        customerPhoneNumberField.setText(customer.getPhoneNumber());
        isMemberCheckButton.setSelected(customer.isMember());
    }
    
    private Customer getEditedCustomer(){
        String customerCode = customerCodeField.getText();
        String customerFirstName = customerFirstNameField.getText();
        String customerLastName = customerLastNameField.getText();
        String customerAddress = customerAddressField.getText();
        String customerEmailAddress = customerEmailAddressField.getText();
        String customerPhoneNumber = customerPhoneNumberField.getText();
        boolean isMember = isMemberCheckButton.isSelected();
        
        Customer customer = new Customer(customerCode, customerFirstName, customerLastName, customerAddress, customerEmailAddress, customerPhoneNumber, isMember);
        return customer;
    }
    
    private void updateRow(int index, CustomerTableModel customerTableModel, Customer customer){
        customerTableModel.setValueAt(customer.getCode(), index, 1);
        customerTableModel.setValueAt(customer.getFirstName(), index, 2);
        customerTableModel.setValueAt(customer.getLastName(), index, 3);
        customerTableModel.setValueAt(customer.getAddress(),index, 4);
        customerTableModel.setValueAt(customer.getPhoneNumber(), index, 5);
        customerTableModel.setValueAt(customer.isMemberString(), index, 6);
    }
    
    class ButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event) {
            Object source = event.getSource();
            if(source == editCustomerButton){
                Customer customer = getEditedCustomer();
                if(customerValidator.isValidCustomer(customer)){
                    customers.set(index, customer);
                    updateRow(index, customerTableModel, customer);
                    customerHandler.updateRecord(customer);
                    Status.showInfoMessage(Constants.CUSTOMER_TABLE_UPDATED);
                }
                else Status.showErrorMessage(Constants.INVALID_DATA);
            }
            if(source == cancelButton){
                setMainPanelCustomerButtons(true);
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
        customerCodeLabel = new javax.swing.JLabel();
        customerCodeField = new javax.swing.JTextField();
        customerFirstNameLabel = new javax.swing.JLabel();
        customerFirstNameField = new javax.swing.JTextField();
        customerLastNameLabel = new javax.swing.JLabel();
        customerLastNameField = new javax.swing.JTextField();
        customerAddressLabel = new javax.swing.JLabel();
        customerAddressField = new javax.swing.JTextField();
        customerEmailAddressLabel = new javax.swing.JLabel();
        customerEmailAddressField = new javax.swing.JTextField();
        customerPhoneNumberLabel = new javax.swing.JLabel();
        customerPhoneNumberField = new javax.swing.JTextField();
        isMemberCheckButton = new javax.swing.JCheckBox();
        cancelButton = new javax.swing.JButton();
        editCustomerButton = new javax.swing.JButton();

        jLabel1.setText("jLabel1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Insert Customer");
        setMinimumSize(new java.awt.Dimension(600, 350));
        setResizable(false);

        customerCodeLabel.setText("Code:");

        customerCodeField.setEditable(false);

        customerFirstNameLabel.setText("First name:");

        customerLastNameLabel.setText("Last name:");

        customerAddressLabel.setText("Address:");

        customerEmailAddressLabel.setText("Email address:");

        customerPhoneNumberLabel.setText("Phone number:");

        isMemberCheckButton.setText("Is member");

        cancelButton.setText("Cancel");

        editCustomerButton.setText("Edit");
        editCustomerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editCustomerButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(customerPhoneNumberLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                            .addComponent(customerEmailAddressLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(customerAddressLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(customerLastNameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(customerFirstNameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(customerCodeLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(isMemberCheckButton, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                            .addComponent(customerCodeField)
                            .addComponent(customerFirstNameField)
                            .addComponent(customerLastNameField)
                            .addComponent(customerAddressField)
                            .addComponent(customerEmailAddressField)
                            .addComponent(customerPhoneNumberField))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 160, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(editCustomerButton, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(cancelButton)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(customerCodeLabel)
                    .addComponent(customerCodeField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(customerFirstNameLabel)
                    .addComponent(customerFirstNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(customerLastNameLabel)
                    .addComponent(customerLastNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(customerAddressLabel)
                    .addComponent(customerAddressField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(customerEmailAddressLabel)
                    .addComponent(customerEmailAddressField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(customerPhoneNumberLabel)
                    .addComponent(customerPhoneNumberField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(isMemberCheckButton)
                .addContainerGap(52, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelButton)
                    .addComponent(editCustomerButton))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void editCustomerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editCustomerButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_editCustomerButtonActionPerformed

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
//            java.util.logging.Logger.getLogger(EditCustomerFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(EditCustomerFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(EditCustomerFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(EditCustomerFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new EditCustomerFrame().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelButton;
    private javax.swing.JTextField customerAddressField;
    private javax.swing.JLabel customerAddressLabel;
    private javax.swing.JTextField customerCodeField;
    private javax.swing.JLabel customerCodeLabel;
    private javax.swing.JTextField customerEmailAddressField;
    private javax.swing.JLabel customerEmailAddressLabel;
    private javax.swing.JTextField customerFirstNameField;
    private javax.swing.JLabel customerFirstNameLabel;
    private javax.swing.JTextField customerLastNameField;
    private javax.swing.JLabel customerLastNameLabel;
    private javax.swing.JTextField customerPhoneNumberField;
    private javax.swing.JLabel customerPhoneNumberLabel;
    private javax.swing.JButton editCustomerButton;
    private javax.swing.JCheckBox isMemberCheckButton;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables

    @Override
    public void windowOpened(WindowEvent e) {}

    @Override
    public void windowClosing(WindowEvent e) {
        setMainPanelCustomerButtons(true);
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
}
