/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.mycompany.moviemanagementsystem.Constants;
import com.mycompany.moviemanagementsystem.Status;
import com.mycompany.moviemanagementsystem.Transaction;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
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
public class TransactionPanel extends JPanel{
    
    private JPanel transactionTablePanel;
    private JTable transactionTable;
    private JTableHeader tableHeader;
    private DefaultTableModel transactionTableModel;
    private JScrollPane scrollPane;
    
    private JPanel buttonPanel;
    private GridBagConstraints constraints;
    private JButton detailsTransactionButton;
    private JButton searchTransactionButton;
    private JButton dropFiltersButton;
    private ArrayList<JButton> buttonList;
    
    private ArrayList<Transaction> transactions;
    private ArrayList<Transaction> filteredTransactions;
    private ButtonListener buttonListener;
    
    public TransactionPanel(ArrayList<Transaction> transactions){
        initLayout();
        initArrays(transactions);
        initComponents();
        bindComponents();
    }
    
    public ArrayList<Transaction> getTransactions(){
        return transactions;
    }
    
    private void initLayout(){
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
    }
    
    private void initArrays(ArrayList<Transaction> transactions){
        this.transactions = transactions;
        filteredTransactions = new ArrayList<>();
    }
    
    private void initComponents(){
        initPanels();
        initTable();
        fillTable();
        initButtons();
    }
    
    private void bindComponents(){
        bindTable();
        bindListenerToButtons();
        bindButtons();
        bindPanels();
    }

    private void initPanels(){
        transactionTablePanel = new JPanel();
        GridBagLayout buttonPanelLayout = new GridBagLayout();
        constraints = new GridBagConstraints();
        buttonPanel = new JPanel(buttonPanelLayout);
        buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
    }
    
    private void bindPanels(){
        add(transactionTablePanel);
        add(buttonPanel);
    }
    
    private void initTable(){
        
        transactionTable = new JTable();
        
        tableHeader = transactionTable.getTableHeader();
        ((DefaultTableCellRenderer)tableHeader.getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
        ((DefaultTableCellRenderer)transactionTable.getDefaultRenderer(String.class)).setHorizontalAlignment(JLabel.CENTER);
                
        int columnCount = transactionTable.getColumnModel().getColumnCount();
        int width = (int) (Toolkit.getDefaultToolkit().getScreenSize().width * 0.8);
        int height = (int) (Toolkit.getDefaultToolkit().getScreenSize().height * 0.8);
        Dimension scrollPaneDimension = new Dimension(width, height);
        
        for(int i=0; i<columnCount; i++){
            transactionTable.getColumnModel().getColumn(i).setMinWidth(width / Constants.TRANSACTION_TABLE_COLUMNS.length);
            transactionTable.getColumnModel().getColumn(i).setMaxWidth(width / Constants.TRANSACTION_TABLE_COLUMNS.length);
            transactionTable.getColumnModel().getColumn(i).setPreferredWidth(width / Constants.TRANSACTION_TABLE_COLUMNS.length);
        }
        
        scrollPane = new JScrollPane(transactionTable);        
        scrollPane.setMinimumSize(scrollPaneDimension);
        scrollPane.setMaximumSize(scrollPaneDimension);
        scrollPane.setPreferredSize(scrollPaneDimension);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        
        transactionTable.setRowHeight(Constants.ROW_HEIGHT);
        transactionTableModel = new DefaultTableModel();
        transactionTableModel.setColumnIdentifiers(Constants.TRANSACTION_TABLE_COLUMNS);
        
        transactionTable.setModel(transactionTableModel);
    }
    
    private void fillTable(){
        for(Transaction transaction : transactions){
            transactionTableModel.addRow(new Object[] {
                transaction.getFormattedDate(),
                transaction.getCustomer().getCode(),
                transaction.getMovie().getCode()
            });
        }
    }
    
    private void bindTable(){
        transactionTablePanel.add(scrollPane);
    }
    
    private void initButtons() {
        detailsTransactionButton = new JButton(Constants.DETAILS);
        searchTransactionButton = new JButton(Constants.SEARCH); 
        dropFiltersButton = new JButton(Constants.DROP_FILTERS);
        
        setSizeToButtons(Constants.BUTTON_WIDTH, Constants.BUTTON_HEIGHT);
        
        buttonList = new ArrayList<>();
        buttonList.add(detailsTransactionButton);
        buttonList.add(searchTransactionButton);
        buttonList.add(dropFiltersButton);
    }
    
    private void setButtonSize(JButton button, int width, int height){
        button.setMinimumSize(new Dimension(width, height));
        button.setMaximumSize(new Dimension(width, height));
        button.setPreferredSize(new Dimension(width, height));
    }
    
    private void setSizeToButtons(int width, int height){
        setButtonSize(detailsTransactionButton, width, height);
        setButtonSize(searchTransactionButton, width, height);
        setButtonSize(dropFiltersButton, width, height);
    }
    
    private void bindListenerToButtons(){
        detailsTransactionButton.addActionListener(new ButtonListener());
        searchTransactionButton.addActionListener(new ButtonListener());
        dropFiltersButton.addActionListener(new ButtonListener());
    }
    
    private void bindButtons() {
        buttonPanel.add(detailsTransactionButton, constraints);
        buttonPanel.add(Box.createRigidArea(new Dimension(Constants.RIGID_AREA_WIDTH, Constants.RIGID_AREA_HEIGHT)));
        buttonPanel.add(searchTransactionButton, constraints);
        buttonPanel.add(Box.createRigidArea(new Dimension(Constants.RIGID_AREA_WIDTH, Constants.RIGID_AREA_HEIGHT)));
        buttonPanel.add(dropFiltersButton, constraints);
    }
    
    private String formattedDate(Date date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String formattedFromDate = simpleDateFormat.format(date);
        return formattedFromDate;
    }
    
    public void addTransactionToTable(Transaction transaction){
        transactionTableModel.addRow(new Object[]{
            false,
            transaction.getFormattedDate(),
            transaction.getCustomer().getCode(),
            transaction.getMovie().getCode()
        });
    }
    
    public void addTransaction(Transaction transaction){
        transactions.add(transaction);
    }
    
    class ButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event) {
            Object source = event.getSource();
            if(source == detailsTransactionButton){
                if(transactions.size() > 0){
                    int index = transactionTable.getSelectionModel().getLeadSelectionIndex();
                    if(index != -1){
                        Transaction transaction = transactions.get(index);
                        new DetailsTransactionFrame(transaction, buttonList);
                    }
                    else Status.showErrorMessage(Constants.NO_RECORD_SELECTED);
                }
            }
            if(source == searchTransactionButton){
                new SearchTransactionFrame(transactions, filteredTransactions, transactionTableModel, buttonList);
            }
            if(source == dropFiltersButton){
                if(transactions.size() <= 0){
                    Status.showErrorMessage(Constants.NO_TRANSACTIONS);
                }
                else{
                   transactionTableModel.setRowCount(0);
                   for(Transaction transaction : transactions){
                        transactionTableModel.addRow(new Object[]{false,
                            formattedDate(transaction.getDate()),
                            transaction.getCustomer().getCode(),
                            transaction.getMovie().getCode(),
                        });
                   } 
                }
            }
            
        } 
    }
}
