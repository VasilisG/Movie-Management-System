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
import criteria.CustomerCriteria;
import criteria.CustomerCriteriaAddress;
import criteria.CustomerCriteriaCode;
import criteria.CustomerCriteriaEmailAddress;
import criteria.CustomerCriteriaFirstName;
import criteria.CustomerCriteriaLastName;
import criteria.CustomerCriteriaMember;
import criteria.CustomerCriteriaPhoneNumber;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import validators.CustomerValidator;

/**
 *
 * @author Vasilis
 */
public class SearchCustomerFrame extends javax.swing.JFrame implements WindowListener{

    /**
     * Creates new form TestFrame
     */
    private static final int FRAME_WIDTH = 560;
    private static final int FRAME_HEIGHT = 360;
    
    private ButtonListener buttonListener;
    private CustomerValidator customerValidator;
    private ArrayList<CustomerCriteria> criteria;
    
    private ArrayList<Customer> customers;
    private ArrayList<Customer> filteredCustomers;
    private CustomerTableModel customerTableModel;
    private ArrayList<JButton> buttonList;

    /**
     * Creates new form SearchCustomerFrame
     */
    public SearchCustomerFrame(ArrayList<Customer> customers, ArrayList<Customer> filteredCustomers, CustomerTableModel customerTableModel, ArrayList<JButton> buttonList){
        initComponents();
        setFrameSize(FRAME_WIDTH,FRAME_HEIGHT);
        bindListenerToButtons();
        addWindowListener(this);
        
        customerValidator = new CustomerValidator();
        
        this.customers = customers;
        this.filteredCustomers = filteredCustomers;
        this.customerTableModel = customerTableModel;
        this.buttonList = buttonList;
        
        criteria = new ArrayList<CustomerCriteria>();
        
        setMainPanelCustomerButtons(false);
        
        this.setTitle(Constants.SEARCH_CUSTOMER_FRAME_TITLE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
    }
    
    private void setFrameSize(int width, int height){
        setMinimumSize(new Dimension(width, height));
        setMaximumSize(new Dimension(width, height));
        setPreferredSize(new Dimension(width, height));
    }
    
    private void bindListenerToButtons(){
        buttonListener = new ButtonListener();
        clearFieldsButton.addActionListener(buttonListener);
        searchButton.addActionListener(buttonListener);
        cancelButton.addActionListener(buttonListener);
    }
    
    private void setMainPanelCustomerButtons(boolean enabled){
        for(JButton button : buttonList){
            button.setEnabled(enabled);
        }
    }
    
    private void clearFields(){
        customerCodeField.setText("");
        customerFirstNameField.setText("");
        customerLastNameField.setText("");
        customerAddressField.setText("");
        customerEmailAddressField.setText("");
        customerPhoneNumberField.setText("");
        customerIsMemberCheckBox.setSelected(false);
        isNotMemberCheckBox.setSelected(false);
    }
    
    private boolean isCodeCriteriaOn(){
        String code = customerCodeField.getText();
        if(code.length() > 0 && customerValidator.isValidCode(code)){
            return true;
        }
        return false;
    }
    
    private boolean isFirstNameCriteriaOn(){
        String firstName = customerFirstNameField.getText();
        if(firstName.length() > 0 && customerValidator.isValidName(firstName)){
            return true;
        }
        return false;
    }
    
    private boolean isLastNameCriteriaOn(){
        String lastName = customerLastNameField.getText();
        if(lastName.length() > 0 && customerValidator.isValidName(lastName)){
            return true;
        }
        return false;
    }
    
    private boolean isValidAddressCriteriaOn(){
        String address = customerAddressField.getText();
        return address.length() > 0;
    }
    
    private boolean isValidEmailAddressCriteriaOn(){
        String emailAddress = customerEmailAddressField.getText();
        if(emailAddress.length() > 0 && customerValidator.isValidEmailAddress(emailAddress)){
            return true;
        }
        return false;
    }
    
    private boolean isValidPhoneNumberCriteriaOn(){
        String phoneNumber = customerPhoneNumberField.getText();
        if(phoneNumber.length() > 0 && customerValidator.isValidPhoneNumber(phoneNumber)){
            return true;
        }
        return false;
    }
    
    private boolean isMemberCriteriaOn(){
        return customerIsMemberCheckBox.isSelected() || isNotMemberCheckBox.isSelected();
    }

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
    
    class ButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent event) {
            Object source = event.getSource();
            if(source == clearFieldsButton){
                clearFields();
            }
            if(source == searchButton){
                if(isCodeCriteriaOn()){
                    String code = customerCodeField.getText();
                    criteria.add(new CustomerCriteriaCode(code));
                    System.out.println("Code criteria added.");
                }
                if(isFirstNameCriteriaOn()){
                    String firstName = customerFirstNameField.getText();
                    criteria.add(new CustomerCriteriaFirstName(firstName));
                    System.out.println("First name criteria added.");
                }
                if(isLastNameCriteriaOn()){
                    String lastName = customerLastNameField.getText();
                    criteria.add(new CustomerCriteriaLastName(lastName));
                    System.out.println("Last name criteria added.");
                }
                if(isValidAddressCriteriaOn()){
                    String address = customerAddressField.getText();
                    criteria.add(new CustomerCriteriaAddress(address));
                    System.out.println("Address criteria added.");
                }
                if(isValidEmailAddressCriteriaOn()){
                    String emailAddress = customerEmailAddressField.getText();
                    criteria.add(new CustomerCriteriaEmailAddress(emailAddress));
                    System.out.println("Email address criteria added.");
                }
                if(isValidPhoneNumberCriteriaOn()){
                    String phoneNumber = customerPhoneNumberField.getText();
                    criteria.add(new CustomerCriteriaPhoneNumber(phoneNumber));
                    System.out.println("Phone number criteria added.");
                }
                if(isMemberCriteriaOn()){
                    boolean isMember = customerIsMemberCheckBox.isSelected();
                    boolean isNotMember = isNotMemberCheckBox.isSelected();
                    if(isMember && isNotMember){
                        criteria.add(new CustomerCriteriaMember(isMember, true));
                        System.out.println("Member criteria added.");
                    }
                    else{
                        if(isMember){
                            criteria.add(new CustomerCriteriaMember(isMember, false));
                            System.out.println("Member criteria added.");
                        }
                        if(isNotMember){
                            criteria.add(new CustomerCriteriaMember(!isNotMember,false));
                            System.out.println("Member criteria added.");
                        }
                    }
                    
                }
                filteredCustomers = new ArrayList<>(customers);
                for(CustomerCriteria currentCriteria : criteria){
                   filteredCustomers = currentCriteria.meetCriteria(filteredCustomers);
                }
                System.out.println("Filtered customers size: " + filteredCustomers.size());
                customerTableModel.setRowCount(0);
                for(Customer customer : filteredCustomers){
                   customerTableModel.addRow(new Object[]{false,
                            customer.getCode(),
                            customer.getFirstName(),
                            customer.getLastName(),
                            customer.getAddress(),
                            customer.getPhoneNumber(),
                            customer.isMemberString()
                        });
                }
                Status.showInfoMessage(Constants.FILTERED_RESULTS);
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

        jPanel1 = new javax.swing.JPanel();
        customerCodeLabel = new javax.swing.JLabel();
        custoemerFirstNameLabel = new javax.swing.JLabel();
        customerLastNameLabel = new javax.swing.JLabel();
        customerAddressLabel = new javax.swing.JLabel();
        customerEmailAddressLabel = new javax.swing.JLabel();
        customerPhoneNumberLabel = new javax.swing.JLabel();
        customerCodeField = new javax.swing.JTextField();
        customerFirstNameField = new javax.swing.JTextField();
        customerLastNameField = new javax.swing.JTextField();
        customerAddressField = new javax.swing.JTextField();
        customerEmailAddressField = new javax.swing.JTextField();
        customerPhoneNumberField = new javax.swing.JTextField();
        customerIsMemberCheckBox = new javax.swing.JCheckBox();
        cancelButton = new javax.swing.JButton();
        searchButton = new javax.swing.JButton();
        clearFieldsButton = new javax.swing.JButton();
        isNotMemberCheckBox = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setMaximumSize(new java.awt.Dimension(560, 360));
        jPanel1.setMinimumSize(new java.awt.Dimension(560, 360));

        customerCodeLabel.setText("Code:");

        custoemerFirstNameLabel.setText("First name:");

        customerLastNameLabel.setText("Last name:");

        customerAddressLabel.setText("Address:");

        customerEmailAddressLabel.setText("Email address:");

        customerPhoneNumberLabel.setText("Phone number:");

        customerIsMemberCheckBox.setText("Is member");

        cancelButton.setText("Cancel");

        searchButton.setText("Search");

        clearFieldsButton.setText("Clear Fields");

        isNotMemberCheckBox.setText("Is not member");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(customerCodeLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(custoemerFirstNameLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(customerLastNameLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(customerAddressLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(customerEmailAddressLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(customerEmailAddressField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(customerLastNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(customerFirstNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(customerCodeField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(customerAddressField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(customerPhoneNumberLabel)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(isNotMemberCheckBox)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(customerIsMemberCheckBox)
                                .addComponent(customerPhoneNumberField)))))
                .addContainerGap(250, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(clearFieldsButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(searchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cancelButton)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(customerCodeLabel)
                    .addComponent(customerCodeField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(custoemerFirstNameLabel)
                    .addComponent(customerFirstNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(customerLastNameLabel)
                    .addComponent(customerLastNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(customerAddressLabel)
                    .addComponent(customerAddressField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(customerEmailAddressLabel)
                    .addComponent(customerEmailAddressField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(customerPhoneNumberLabel)
                    .addComponent(customerPhoneNumberField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(customerIsMemberCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(isNotMemberCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelButton)
                    .addComponent(searchButton)
                    .addComponent(clearFieldsButton))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
//            java.util.logging.Logger.getLogger(TestFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(TestFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(TestFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(TestFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new TestFrame().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelButton;
    private javax.swing.JButton clearFieldsButton;
    private javax.swing.JLabel custoemerFirstNameLabel;
    private javax.swing.JTextField customerAddressField;
    private javax.swing.JLabel customerAddressLabel;
    private javax.swing.JTextField customerCodeField;
    private javax.swing.JLabel customerCodeLabel;
    private javax.swing.JTextField customerEmailAddressField;
    private javax.swing.JLabel customerEmailAddressLabel;
    private javax.swing.JTextField customerFirstNameField;
    private javax.swing.JCheckBox customerIsMemberCheckBox;
    private javax.swing.JTextField customerLastNameField;
    private javax.swing.JLabel customerLastNameLabel;
    private javax.swing.JTextField customerPhoneNumberField;
    private javax.swing.JLabel customerPhoneNumberLabel;
    private javax.swing.JCheckBox isNotMemberCheckBox;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton searchButton;
    // End of variables declaration//GEN-END:variables
}
