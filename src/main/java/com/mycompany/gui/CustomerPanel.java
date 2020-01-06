/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.mycompany.moviemanagementsystem.Constants;
import com.mycompany.moviemanagementsystem.Customer;
import com.mycompany.moviemanagementsystem.Status;
import database.CustomerHandler;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author Vasilis
 */
public class CustomerPanel extends JPanel{
    
    private JPanel customerTablePanel;
    private JTable customerTable;
    private JTableHeader tableHeader;
    private CustomerTableModel customerTableModel;
    private JScrollPane scrollPane;
    
    private JPanel buttonPanel;
    private GridBagConstraints constraints;
    private JButton insertCustomerButton;
    private JButton deleteCustomerButton;
    private JButton deleteAllCustomersButton;
    private JButton detailsCustomerButton;
    private JButton editCustomerButton;
    private JButton searchCustomerButton;
    private JButton dropFiltersButton;
    private ArrayList<JButton> buttonList;
    
    private ArrayList<Customer> customers;
    private ArrayList<Customer> filteredCustomers;
    private CustomerHandler customerHandler;
    
    public CustomerPanel(ArrayList<Customer> customers, CustomerHandler customerHandler){
        initLayout(customers, customerHandler);
        initComponents();
        bindComponents();
    }
    
    public ArrayList<Customer> getCustomers(){
        return customers;
    }
    
    private void initLayout(ArrayList<Customer> customers, CustomerHandler customerHandler){
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.customers = customers;
        this.customerHandler = customerHandler;
        filteredCustomers = new ArrayList<>();
    }
    
    private void initComponents(){
        initPanels();
        initTable();
        initButtons();
        fillTable();
    }
    
    private void bindComponents(){
        bindTable();
        bindListenerToButtons();
        bindButtons();
        bindPanels();
    }
    
    private void initPanels(){
        customerTablePanel = new JPanel();
        GridBagLayout buttonPanelLayout = new GridBagLayout();
        constraints = new GridBagConstraints();
        buttonPanel = new JPanel(buttonPanelLayout);
        buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
    }
    
    private void bindPanels(){
        add(customerTablePanel);
        add(buttonPanel);
    }
    
    private void fillTable(){
        for(Customer customer : customers){
            customerTableModel.addRow(new Object[]{false,
                customer.getCode(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getAddress(),
                customer.getPhoneNumber(),
                customer.isMemberString()
            });
        }
    }
    
    private void initTable(){
        
        customerTable = new JTable();
        
        tableHeader = customerTable.getTableHeader();
        ((DefaultTableCellRenderer)tableHeader.getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
        ((DefaultTableCellRenderer)customerTable.getDefaultRenderer(String.class)).setHorizontalAlignment(JLabel.CENTER);
                
        int columnCount = customerTable.getColumnModel().getColumnCount();
        int width = (int) (Toolkit.getDefaultToolkit().getScreenSize().width * 0.8);
        int height = (int) (Toolkit.getDefaultToolkit().getScreenSize().height * 0.8);
        Dimension scrollPaneDimension = new Dimension(width, height);
        
        for(int i=0; i<columnCount; i++){
            customerTable.getColumnModel().getColumn(i).setMinWidth(width / Constants.CUSTOMER_TABLE_COLUMNS.length);
            customerTable.getColumnModel().getColumn(i).setMaxWidth(width / Constants.CUSTOMER_TABLE_COLUMNS.length);
            customerTable.getColumnModel().getColumn(i).setPreferredWidth(width / Constants.CUSTOMER_TABLE_COLUMNS.length);
        }
        
        scrollPane = new JScrollPane(customerTable);        
        scrollPane.setMinimumSize(scrollPaneDimension);
        scrollPane.setMaximumSize(scrollPaneDimension);
        scrollPane.setPreferredSize(scrollPaneDimension);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        
        customerTable.setRowHeight(Constants.ROW_HEIGHT);
        customerTableModel = new CustomerTableModel();
        customerTableModel.setColumnIdentifiers(Constants.CUSTOMER_TABLE_COLUMNS);
        
        customerTable.setModel(customerTableModel);
    }
    
    private void bindTable(){
        customerTablePanel.add(scrollPane);
    }
    
    private void initButtons() {
        insertCustomerButton = new JButton(Constants.INSERT);
        deleteCustomerButton = new JButton(Constants.DELETE);
        deleteAllCustomersButton = new JButton(Constants.DELETE_ALL);
        detailsCustomerButton = new JButton(Constants.DETAILS);
        editCustomerButton = new JButton(Constants.EDIT);
        searchCustomerButton = new JButton(Constants.SEARCH); 
        dropFiltersButton = new JButton(Constants.DROP_FILTERS);
        
        setSizeToButtons(Constants.BUTTON_WIDTH, Constants.BUTTON_HEIGHT);
        
        buttonList = new ArrayList<>();
        buttonList.add(insertCustomerButton);
        buttonList.add(deleteCustomerButton);
        buttonList.add(deleteAllCustomersButton);
        buttonList.add(detailsCustomerButton);
        buttonList.add(editCustomerButton);
        buttonList.add(searchCustomerButton);
        buttonList.add(dropFiltersButton);
    }
    
    private void setButtonSize(JButton button, int width, int height){
        button.setMinimumSize(new Dimension(width, height));
        button.setMaximumSize(new Dimension(width, height));
        button.setPreferredSize(new Dimension(width, height));
    }
    
    private void setSizeToButtons(int width, int height){
        setButtonSize(insertCustomerButton, width, height);
        setButtonSize(deleteCustomerButton, width, height);
        setButtonSize(deleteAllCustomersButton, width, height);
        setButtonSize(detailsCustomerButton, width, height);
        setButtonSize(editCustomerButton, width, height);
        setButtonSize(searchCustomerButton, width, height);
        setButtonSize(dropFiltersButton, width, height);
    }
    
    private void bindListenerToButtons(){
        insertCustomerButton.addActionListener(new ButtonListener());
        deleteCustomerButton.addActionListener(new ButtonListener());
        deleteAllCustomersButton.addActionListener(new ButtonListener());
        editCustomerButton.addActionListener(new ButtonListener());
        detailsCustomerButton.addActionListener(new ButtonListener());
        searchCustomerButton.addActionListener(new ButtonListener());
        dropFiltersButton.addActionListener(new ButtonListener());
    }
    
    private void bindButtons() {
        buttonPanel.add(insertCustomerButton, constraints);
        buttonPanel.add(Box.createRigidArea(new Dimension(Constants.RIGID_AREA_WIDTH, Constants.RIGID_AREA_HEIGHT)));
        buttonPanel.add(deleteCustomerButton, constraints);
        buttonPanel.add(Box.createRigidArea(new Dimension(Constants.RIGID_AREA_WIDTH, Constants.RIGID_AREA_HEIGHT)));
        buttonPanel.add(deleteAllCustomersButton, constraints);
        buttonPanel.add(Box.createRigidArea(new Dimension(Constants.RIGID_AREA_WIDTH, Constants.RIGID_AREA_HEIGHT)));
        buttonPanel.add(editCustomerButton, constraints);
        buttonPanel.add(Box.createRigidArea(new Dimension(Constants.RIGID_AREA_WIDTH, Constants.RIGID_AREA_HEIGHT)));
        buttonPanel.add(detailsCustomerButton, constraints);
        buttonPanel.add(Box.createRigidArea(new Dimension(Constants.RIGID_AREA_WIDTH, Constants.RIGID_AREA_HEIGHT)));
        buttonPanel.add(searchCustomerButton, constraints);
        buttonPanel.add(Box.createRigidArea(new Dimension(Constants.RIGID_AREA_WIDTH, Constants.RIGID_AREA_HEIGHT)));
        buttonPanel.add(dropFiltersButton, constraints);
    }
    
    class ButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event) {
            Object source = event.getSource();
            if(source == insertCustomerButton){
                new InsertCustomerFrame(customerTableModel, customers, buttonList, customerHandler);
            }
            if(source == deleteCustomerButton){
               ArrayList<Integer> indices = new ArrayList<Integer>();
               for(int i=0; i<customerTableModel.getRowCount(); i++){
                    boolean selected = (boolean) customerTableModel.getValueAt(i, 0);
                    if(selected){
                        indices.add(i);
                    }
                }
                int rows = indices.size();
                if(rows == 0){
                    int selectedIndex = customerTable.getSelectedRow();
                    if(selectedIndex != -1){
                        int confirmDelete = Status.showConfirmMessage(Constants.CONFIRM_DELETION);
                        if(confirmDelete == JOptionPane.YES_OPTION){
                            Customer customer = customers.get(selectedIndex);
                            customerTableModel.removeRow(selectedIndex);
                            customers.remove(selectedIndex);
                            customerHandler.deleteRecord(customer);
                            Status.showInfoMessage(Constants.RECORD_DELETED);
                        }
                    }
                    else Status.showErrorMessage(Constants.NO_RECORD_SELECTED);
                }   
                else {
                    int confirmDelete = Status.showConfirmMessage(Constants.CONFIRM_DELETION);
                    if(confirmDelete == JOptionPane.YES_OPTION){
                        Collections.reverse(indices);
                        for(Integer currentIndex : indices){
                            customerTableModel.removeRow(currentIndex);
                        }
                        ArrayList<Customer> tempCustomers = new ArrayList<Customer>();
                        for(int i=0; i<customers.size(); i++){
                            if(indices.contains((Integer)i)){
                                continue;
                            }
                            else {
                                Customer customer = customers.get(i);
                                tempCustomers.add(customer);
                                customerHandler.deleteRecord(customer);
                            }
                        }
                        customers = tempCustomers;
                        customerTable.getSelectionModel().setLeadSelectionIndex(-1);
                        Status.showInfoMessage(Constants.DELETED + rows + Constants.RECORDS);
                    }  
                }
            }
            if(source == deleteAllCustomersButton){
               int size = customers.size();
               int modelSize = customerTableModel.getRowCount();
               if(size > 0 && modelSize > 0){
                   int confirmDelete = Status.showConfirmMessage(Constants.CONFIRM_DELETION_ALL);
                   if(confirmDelete == JOptionPane.YES_OPTION){
                       customerTableModel.setRowCount(0);
                       customers.clear();
                       customerHandler.deleteAllRecords();
                       Status.showInfoMessage(Constants.DELETED + modelSize + Constants.RECORDS);
                   }
               }
               else Status.showErrorMessage(Constants.NO_RECORDS_TO_DELETE);
            }
            if(source == editCustomerButton){
               int index = customerTable.getSelectionModel().getLeadSelectionIndex();
               int size = customers.size();
               if(size > 0 && index > -1){
                   new EditCustomerFrame(index, customers, customerTableModel, buttonList, customerHandler);
               }
               else Status.showErrorMessage(Constants.NO_RECORD_SELECTED);
            }
            if(source == detailsCustomerButton){
                int index = customerTable.getSelectionModel().getLeadSelectionIndex();
               int size = customers.size();
               if(size > 0 && index > -1){
                    Customer customer = customers.get(index);
                    new DetailsCustomerFrame(customer, buttonList);
               }
               else Status.showErrorMessage(Constants.NO_RECORD_SELECTED);
            }
            if(source == searchCustomerButton){
                if(customers.size() > 0){
                    new SearchCustomerFrame(customers, filteredCustomers, customerTableModel, buttonList);
                }
                else Status.showErrorMessage(Constants.NO_RECORDS_TO_FILTER);
            }
            if(source == dropFiltersButton){
                customerTableModel.setRowCount(0);
                for(Customer customer : customers){
                    customerTableModel.addRow(new Object[]{false,
                            customer.getCode(),
                            customer.getFirstName(),
                            customer.getLastName(),
                            customer.getAddress(),
                            customer.getPhoneNumber(),
                            customer.isMemberString()
                    });
                }
            }
        } 
    }
    
    class CustomerTableModel extends DefaultTableModel {
        @Override
        public Class<?> getColumnClass(int columnIndex) {
            if (columnIndex == 0)
                return Boolean.class;
            return super.getColumnClass(columnIndex);
        }
        
        @Override
        public boolean isCellEditable(int row, int col) {
            return (col == 0); 
        }
    }
    
}
