/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.mycompany.gui.MoviePanel.MovieTableModel;
import com.mycompany.moviemanagementsystem.Constants;
import com.mycompany.moviemanagementsystem.Customer;
import com.mycompany.moviemanagementsystem.Movie;
import com.mycompany.moviemanagementsystem.Reservation;
import com.mycompany.moviemanagementsystem.Status;
import com.mycompany.moviemanagementsystem.Transaction;
import database.ReservationHandler;
import database.TransactionHandler;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author Vasilis
 */
public class ReservationPanel extends JPanel{
    
    private JPanel reservationTablePanel;
    private JTable reservationTable;
    private JTableHeader tableHeader;
    private ReservationTableModel reservationTableModel;
    private JScrollPane scrollPane;
    
    private JPanel buttonPanel;
    private GridBagConstraints constraints;
    private JButton insertReservationButton;
    private JButton cancelReservationButton;
    private JButton cancelAllReservationsButton;
    private JButton detailsReservationButton;
    private JButton searchReservationButton;
    private JButton dropFiltersButton;
    private JButton completeReservationButton;
    private JButton completeAllReservationsButton;
    private ArrayList<JButton> buttonList;
    
    private MoviePanel moviePanel;
    private CustomerPanel customerPanel;
    private TransactionPanel transactionPanel;
    
    private ArrayList<Movie> movies;
    private ArrayList<Customer> customers;
    
    private ArrayList<Reservation> reservations;
    private ArrayList<Reservation> filteredReservations;
    private ReservationHandler reservationHandler;
    private TransactionHandler transactionHandler;
    
    public ReservationPanel(ArrayList<Reservation> reservations, 
            MoviePanel moviePanel, 
            CustomerPanel customerPanel, 
            TransactionPanel transactionPanel, 
            ReservationHandler reservationHandler,
            TransactionHandler transactionHandler){
        initLayout(reservations);
        initComponents();
        bindComponents();
        
        this.moviePanel = moviePanel;
        this.customerPanel = customerPanel;
        this.transactionPanel = transactionPanel;
        this.reservationHandler = reservationHandler;
        this.transactionHandler = transactionHandler;
        
        movies = moviePanel.getMovies();
        customers = customerPanel.getCustomers();
    }
    
    private void initLayout(ArrayList<Reservation> reservations){
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.reservations = reservations;
        filteredReservations = new ArrayList<>();
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
        reservationTablePanel = new JPanel();
        GridBagLayout buttonPanelLayout = new GridBagLayout();
        constraints = new GridBagConstraints();
        buttonPanel = new JPanel(buttonPanelLayout);
        buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
    }
    
    private void bindPanels(){
        add(reservationTablePanel);
        add(buttonPanel);
    }
    
    private void fillTable(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String formattedFromDate = null;
        String formattedToDate = null;
        for(Reservation reservation : reservations){
            formattedFromDate = simpleDateFormat.format(reservation.getStartDate());
            formattedToDate = simpleDateFormat.format(reservation.getEndDate());
            reservationTableModel.addRow(new Object[]{false, 
                reservation.getCustomer().getCode(),
                reservation.getMovie().getCode(),
                formattedFromDate,
                formattedToDate,
                reservation.getStatusString()});
        }
    }
    
    private void initTable(){
        
        reservationTable = new JTable();
        
        tableHeader = reservationTable.getTableHeader();
        ((DefaultTableCellRenderer)tableHeader.getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
        ((DefaultTableCellRenderer)reservationTable.getDefaultRenderer(String.class)).setHorizontalAlignment(JLabel.CENTER);
                
        int columnCount = reservationTable.getColumnModel().getColumnCount();
        int width = (int) (Toolkit.getDefaultToolkit().getScreenSize().width * 0.8);
        int height = (int) (Toolkit.getDefaultToolkit().getScreenSize().height * 0.8);
        Dimension scrollPaneDimension = new Dimension(width, height);
        
        for(int i=0; i<columnCount; i++){
            reservationTable.getColumnModel().getColumn(i).setMinWidth(width / Constants.CUSTOMER_TABLE_COLUMNS.length);
            reservationTable.getColumnModel().getColumn(i).setMaxWidth(width / Constants.CUSTOMER_TABLE_COLUMNS.length);
            reservationTable.getColumnModel().getColumn(i).setPreferredWidth(width / Constants.CUSTOMER_TABLE_COLUMNS.length);
        }
        
        scrollPane = new JScrollPane(reservationTable);        
        scrollPane.setMinimumSize(scrollPaneDimension);
        scrollPane.setMaximumSize(scrollPaneDimension);
        scrollPane.setPreferredSize(scrollPaneDimension);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        
        reservationTable.setRowHeight(Constants.ROW_HEIGHT);
        reservationTableModel = new ReservationTableModel();
        reservationTableModel.setColumnIdentifiers(Constants.RESERVATION_TABLE_COLUMNS);
        
        reservationTable.setModel(reservationTableModel);
        reservationTable.getColumnModel().getColumn(5).setCellRenderer(new ReservationStateColumnCellRenderer());
    }
    
    private void bindTable(){
        reservationTablePanel.add(scrollPane);
    }
    
    private void initButtons() {
        insertReservationButton = new JButton(Constants.INSERT);
        cancelReservationButton = new JButton(Constants.CANCEL);
        cancelAllReservationsButton = new JButton(Constants.CANCEL_ALL);
        detailsReservationButton = new JButton(Constants.DETAILS);
        searchReservationButton = new JButton(Constants.SEARCH); 
        dropFiltersButton = new JButton(Constants.DROP_FILTERS);
        completeReservationButton = new JButton(Constants.COMPLETE);
        completeAllReservationsButton = new JButton(Constants.COMPLETE_ALL);
        
        setSizeToButtons(Constants.BUTTON_WIDTH, Constants.BUTTON_HEIGHT);
        
        buttonList = new ArrayList<>();
        buttonList.add(insertReservationButton);
        buttonList.add(cancelReservationButton);
        buttonList.add(cancelAllReservationsButton);
        buttonList.add(detailsReservationButton);
        buttonList.add(searchReservationButton);
        buttonList.add(dropFiltersButton);
        buttonList.add(completeReservationButton);
        buttonList.add(completeAllReservationsButton);
    }
    
    private void setButtonSize(JButton button, int width, int height){
        button.setMinimumSize(new Dimension(width, height));
        button.setMaximumSize(new Dimension(width, height));
        button.setPreferredSize(new Dimension(width, height));
    }
    
    private void setSizeToButtons(int width, int height){
        setButtonSize(insertReservationButton, width, height);
        setButtonSize(cancelReservationButton, width, height);
        setButtonSize(cancelAllReservationsButton, width, height);
        setButtonSize(detailsReservationButton, width, height);
        setButtonSize(searchReservationButton, width, height);
        setButtonSize(dropFiltersButton, width, height);
        setButtonSize(completeReservationButton, width, height);
        setButtonSize(completeAllReservationsButton, width, height);
    }
    
    private void bindListenerToButtons(){
        insertReservationButton.addActionListener(new ButtonListener());
        cancelReservationButton.addActionListener(new ButtonListener());
        cancelAllReservationsButton.addActionListener(new ButtonListener());
        detailsReservationButton.addActionListener(new ButtonListener());
        searchReservationButton.addActionListener(new ButtonListener());
        dropFiltersButton.addActionListener(new ButtonListener());
        completeReservationButton.addActionListener(new ButtonListener());
        completeAllReservationsButton.addActionListener(new ButtonListener());
    }
    
    private void bindButtons() {
        buttonPanel.add(insertReservationButton, constraints);
        buttonPanel.add(Box.createRigidArea(new Dimension(Constants.RIGID_AREA_WIDTH, Constants.RIGID_AREA_HEIGHT)));
        buttonPanel.add(cancelReservationButton, constraints);
        buttonPanel.add(Box.createRigidArea(new Dimension(Constants.RIGID_AREA_WIDTH, Constants.RIGID_AREA_HEIGHT)));
        buttonPanel.add(cancelAllReservationsButton, constraints);
        buttonPanel.add(Box.createRigidArea(new Dimension(Constants.RIGID_AREA_WIDTH, Constants.RIGID_AREA_HEIGHT)));
        buttonPanel.add(detailsReservationButton, constraints);
        buttonPanel.add(Box.createRigidArea(new Dimension(Constants.RIGID_AREA_WIDTH, Constants.RIGID_AREA_HEIGHT)));
        buttonPanel.add(completeReservationButton, constraints);
        buttonPanel.add(Box.createRigidArea(new Dimension(Constants.RIGID_AREA_WIDTH, Constants.RIGID_AREA_HEIGHT)));
        buttonPanel.add(completeAllReservationsButton, constraints);
        buttonPanel.add(Box.createRigidArea(new Dimension(Constants.RIGID_AREA_WIDTH, Constants.RIGID_AREA_HEIGHT)));
        buttonPanel.add(searchReservationButton, constraints);
        buttonPanel.add(Box.createRigidArea(new Dimension(Constants.RIGID_AREA_WIDTH, Constants.RIGID_AREA_HEIGHT)));
        buttonPanel.add(dropFiltersButton, constraints);
    }
    
    private boolean canInsert(ArrayList<Movie> movies, ArrayList<Customer> customers){
        return movies.size() > 0 && customers.size() > 0;
    }
    
    private int getMovieByCode(String movieCode){
        for(int i=0; i<movies.size(); i++){
            Movie movie = movies.get(i);
            if(movie.getCode() == movieCode){
                return i;
            }
        }
        return -1;
    }
    
    private void updateRow(int index, MoviePanel.MovieTableModel movieTableModel, Movie movie){
        movieTableModel.setValueAt(movie.getCode(), index, 1);
        movieTableModel.setValueAt(movie.getTitle(), index, 2);
        movieTableModel.setValueAt(formattedPrice(movie.getPrice()), index, 3);
        movieTableModel.setValueAt(String.valueOf(movie.getQuantity()), index, 4);
    }
    
    private void updateReservationRow(int index, ReservationTableModel reservationTableModel, Reservation reservation, String reservationStatus){
        String formattedStartDate = formattedDate(reservation.getStartDate());
        String formattedEndDate = formattedDate(reservation.getEndDate());
        reservationTableModel.setValueAt(false, index, 0);
        reservationTableModel.setValueAt(reservation.getCustomer().getCode(), index, 1);
        reservationTableModel.setValueAt(reservation.getMovie().getCode(), index, 2);
        reservationTableModel.setValueAt(formattedStartDate, index, 3);
        reservationTableModel.setValueAt(formattedEndDate, index, 4);
        reservationTableModel.setValueAt(reservationStatus, index, 5);
    }
    
    private String formattedDate(Date date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String formattedFromDate = simpleDateFormat.format(date);
        return formattedFromDate;
    }
    
    private String formattedPrice(double price){
        NumberFormat priceFormat = new DecimalFormat("#0.00");
        String formattedPrice = priceFormat.format(price) + " €";
        return formattedPrice;
    }
    
    class ButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event) {
            Object source = event.getSource();
            if(source == insertReservationButton){
                if(canInsert(movies, customers)){
                    new InsertReservationFrame(buttonList, movies, customers, reservations, moviePanel.getMovieTableModel(), reservationTableModel, reservationHandler);
                }
                else Status.showErrorMessage(Constants.CANNOT_INSERT_RESERVATION);
            }
            if(source == cancelReservationButton){
                MovieTableModel movieTableModel = moviePanel.getMovieTableModel();
                if(reservations.size() <= 0){
                    Status.showErrorMessage(Constants.NO_RESERVATIONS);
                }
                else{
                    ArrayList<Integer> indices = new ArrayList<Integer>();
                    for(int i=0; i<reservationTableModel.getRowCount(); i++){
                        boolean selected = (boolean) reservationTableModel.getValueAt(i, 0);
                        if(selected){
                            indices.add(i);
                        }
                    }
                    int rows = indices.size();
                    if(rows == 0){
                        int selectedIndex = reservationTable.getSelectedRow();
                        if(selectedIndex != -1){
                            int confirmCancel = Status.showConfirmMessage(Constants.CANCEL_RESERVATIONS);
                            if(confirmCancel == JOptionPane.YES_OPTION){
                                Reservation reservation = reservations.get(selectedIndex);
                                if(reservation.getStatusString().equals(Constants.COMPLETED)){
                                    Status.showErrorMessage(Constants.RESERVATIONS_NOT_COMPLETED);
                                }
                                else{
                                    String movieCode = reservation.getMovie().getCode();
                                    int movieIndex = getMovieByCode(movieCode);
                                    Movie movie = movies.get(movieIndex);
                                    movie.setQuantity(movie.getQuantity()+1);
                                    updateRow(movieIndex, movieTableModel, movie);
                                    updateReservationRow(selectedIndex, reservationTableModel, reservation, Constants.CANCELED);
                                    reservation.setStatus(Constants.STATUS_CANCELED);
                                    reservationHandler.updateRecord(reservation);
                                    
                                    Status.showInfoMessage(Constants.RESERVATIONS_COMPLETED);
                                }
                                
                            }
                        }
                    }
                    else{
                        int confirmCancel = Status.showConfirmMessage(Constants.CANCEL_RESERVATIONS);
                        int failedToComplete = 0;
                        if(confirmCancel == JOptionPane.YES_OPTION){
                          for(Integer index : indices){
                            Reservation reservation = reservations.get(index);
                            if(reservation.getStatusString().equals(Constants.COMPLETED)){
                                failedToComplete++;
                                continue;
                            }
                            String movieCode = reservation.getMovie().getCode();
                            int movieIndex = getMovieByCode(movieCode);
                            Movie movie = movies.get(movieIndex);
                            movie.setQuantity(movie.getQuantity()+1);
                            updateRow(movieIndex, movieTableModel, movie);
                            updateReservationRow(index, reservationTableModel, reservation, Constants.CANCELED);
                            reservation.setStatus(Constants.STATUS_CANCELED);
                            reservationHandler.updateRecord(reservation);
                          }  
                        }
                        if(failedToComplete > 0){
                            Status.showInfoMessage(Constants.RESERVATIONS_COULD_NOT_BE_CANCELLED + failedToComplete);
                        }
                    }
                }
            }
            if(source == cancelAllReservationsButton){
                int confirmCancel = Status.showConfirmMessage(Constants.CANCEL_ALL_RESERVATIONS);
                if(confirmCancel == JOptionPane.YES_OPTION){
                    MovieTableModel movieTableModel = moviePanel.getMovieTableModel();
                    int currentReservationIndex = 0;
                    int failedToComplete = 0;
                    for(Reservation reservation : reservations){
                        if(reservation.getStatusString().equals(Constants.COMPLETED)){
                            failedToComplete++;
                            continue;
                        }
                        String movieCode = reservation.getMovie().getCode();
                        int movieIndex = getMovieByCode(movieCode);
                        Movie movie = movies.get(movieIndex);
                        movie.setQuantity(movie.getQuantity()+1);
                        updateRow(movieIndex, movieTableModel, movie);
                        updateReservationRow(currentReservationIndex, reservationTableModel, reservation, Constants.CANCELED);
                        reservation.setStatus(Constants.STATUS_CANCELED);
                        reservationHandler.updateRecord(reservation);
                        currentReservationIndex++;
                    }
                    if(failedToComplete > 0){
                        Status.showInfoMessage(Constants.RESERVATIONS_COULD_NOT_BE_CANCELLED + failedToComplete);
                    }
                    else Status.showInfoMessage(Constants.ALL_RESERVATIONS_CANCELED);
                }
            }
            if(source == detailsReservationButton){
                if(reservations.size() > 0){
                    int index = reservationTable.getSelectionModel().getLeadSelectionIndex();
                    if(index != -1){
                        Reservation reservation = reservations.get(index);
                        new DetailsReservationFrame(reservation, buttonList);
                    }
                    else Status.showErrorMessage(Constants.NO_RECORD_SELECTED);
                }
                else Status.showErrorMessage(Constants.NO_RESERVATIONS);
            }
            if(source == completeReservationButton){
                Transaction transaction;
                if(reservations.size() <= 0){
                    Status.showErrorMessage(Constants.NO_RESERVATIONS);
                }
                else{
                    ArrayList<Integer> indices = new ArrayList<Integer>();
                    for(int i=0; i<reservationTableModel.getRowCount(); i++){
                        boolean selected = (boolean) reservationTableModel.getValueAt(i, 0);
                        if(selected){
                            indices.add(i);
                        }
                    }
                    int rows = indices.size();
                    if(rows == 0){
                        int selectedIndex = reservationTable.getSelectedRow();
                        if(selectedIndex != -1){
                            Reservation reservation = reservations.get(selectedIndex);
                            if(reservation.getStatus() == Constants.STATUS_CANCELED){
                                Status.showErrorMessage(Constants.RESERVATION_IS_CANCELED);
                            }
                            else{
                                int confirmComplete = Status.showConfirmMessage(Constants.COMPLETE_RESERVATIONS);
                                if(confirmComplete == JOptionPane.YES_OPTION){
                                    reservations.get(selectedIndex).setStatus(Constants.STATUS_COMPLETED);
                                    updateReservationRow(selectedIndex, reservationTableModel, reservation, Constants.COMPLETED);
                                    reservationHandler.updateRecord(reservation);
                                    Date currentDate = new Date();
                                    transaction = new Transaction(reservation.getCustomer(), reservation.getMovie(), currentDate);
                                    transactionPanel.addTransaction(transaction);
                                    transactionPanel.addTransactionToTable(transaction);
                                } 
                            }
                            
                        }
                    }
                    else{
                        int confirmComplete = Status.showConfirmMessage(Constants.COMPLETE_RESERVATIONS);
                        if(confirmComplete == JOptionPane.YES_OPTION){
                           for(Integer index : indices){
                                Reservation reservation = reservations.get(index);
                                if(reservation.getStatus() == Constants.STATUS_CANCELED){
                                    continue;
                                }
                                else{
                                   reservations.get(index).setStatus(Constants.STATUS_COMPLETED);
                                   updateReservationRow(index, reservationTableModel, reservation, Constants.COMPLETED);
                                   reservationHandler.updateRecord(reservation);
                                   Date currentDate = new Date();
                                   transaction = new Transaction(reservation.getCustomer(), reservation.getMovie(), currentDate);
                                   transactionPanel.addTransaction(transaction);
                                   transactionPanel.addTransactionToTable(transaction);
                                }  
                           }
                        }
                    }
                    Status.showInfoMessage(Constants.RESERVATIONS_COMPLETED);
                }
                
            }
            if(source == completeAllReservationsButton){
                Transaction transaction;
                if(reservations.size() <= 0){
                    Status.showErrorMessage(Constants.NO_RESERVATIONS);
                }
                else{
                    int confirmComplete = Status.showConfirmMessage(Constants.COMPLETE_ALL_RESERVATIONS);
                    if(confirmComplete == JOptionPane.YES_OPTION){
                        int currentReservationIndex = 0;
                        for(Reservation reservation : reservations){
                            if(reservation.getStatus() == Constants.STATUS_CANCELED){
                                continue;
                            }
                            else{
                                reservations.get(currentReservationIndex).setStatus(Constants.STATUS_COMPLETED);
                                updateReservationRow(currentReservationIndex, reservationTableModel, reservation, Constants.COMPLETED);
                                reservationHandler.updateRecord(reservation);
                                Date currentDate = new Date();
                                transaction = new Transaction(reservation.getCustomer(), reservation.getMovie(), currentDate);
                                transactionPanel.addTransaction(transaction);
                                transactionPanel.addTransactionToTable(transaction);
                            }
                            currentReservationIndex++;
                        }
                    } 
                }
                
            }
            if(source == searchReservationButton){
                if(reservations.size() <= 0){
                    Status.showErrorMessage(Constants.NO_RESERVATIONS);
                }
                else{
                    new SearchReservationFrame(buttonList, reservations, filteredReservations, reservationTableModel);
                }
            }
            if(source == dropFiltersButton){
                if(reservations.size() <= 0){
                    Status.showErrorMessage(Constants.NO_RESERVATIONS);
                }
                else{
                   reservationTableModel.setRowCount(0);
                   for(Reservation reservation : reservations){
                        reservationTableModel.addRow(new Object[]{false,
                            reservation.getCustomer().getCode(),
                            reservation.getMovie().getCode(),
                            formattedDate(reservation.getStartDate()),
                            formattedDate(reservation.getEndDate()),
                            reservation.getStatusString()
                        });
                   } 
                }
                
            }
        }
    }
    
    class ReservationTableModel extends DefaultTableModel {
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
    
    class ReservationStateColumnCellRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col) {

            JLabel l = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);
            l.setHorizontalAlignment(SwingConstants.CENTER);

            ReservationTableModel tableModel = (ReservationTableModel) table.getModel();
            String columnValue = (String)tableModel.getValueAt(row, 5);
            if (columnValue == Constants.ONGOING || columnValue == Constants.COMPLETED) {
                l.setForeground(Constants.COLOR_GREEN);
            }
            else if(columnValue == Constants.DUE){
                l.setForeground(Constants.COLOR_YELLOW);
            }
            else{
                l.setForeground(Constants.COLOR_RED);
            }
            return l;
        }
    } 
}
