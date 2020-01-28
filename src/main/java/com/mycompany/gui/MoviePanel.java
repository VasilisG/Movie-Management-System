/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.mycompany.moviemanagementsystem.Constants;
import com.mycompany.moviemanagementsystem.Movie;
import com.mycompany.moviemanagementsystem.Reservation;
import com.mycompany.moviemanagementsystem.Status;
import database.MovieHandler;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.NumberFormat;
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
public class MoviePanel extends JPanel {
        
    private JPanel movieTablePanel;
    private JTable movieTable;
    private JTableHeader tableHeader;
    private MovieTableModel movieTableModel;
    private JScrollPane scrollPane;
    private ArrayList<Movie> movies;
    private ArrayList<Movie> filteredMovies;
    private ArrayList<Reservation> reservations;
    private MovieHandler movieHandler;
    
    private JPanel buttonPanel;
    private GridBagConstraints constraints;
    private JButton insertMovieButton;
    private JButton deleteMovieButton;
    private JButton deleteAllMoviesButton;
    private JButton detailsMovieButton;
    private JButton editMovieButton;
    private JButton searchMovieButton;
    private JButton dropFiltersButton;
    private ArrayList<JButton> buttonList;
    
    public MoviePanel(ArrayList<Movie> movies, ArrayList<Reservation> reservations, MovieHandler movieHandler){
       initLayout(movies, reservations, movieHandler);
       initComponents();
       bindComponents();
       fillTable();
    }
    
    public ArrayList<Movie> getMovies(){
        return movies;
    }
    
    public MovieTableModel getMovieTableModel(){
        return movieTableModel;
    }
    
    private void initLayout(ArrayList<Movie> movies, ArrayList<Reservation> reservations, MovieHandler movieHandler){
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.movies = movies;
        this.reservations = reservations;
        this.movieHandler = movieHandler;
        filteredMovies = new ArrayList<>();
    }
    
    private void initComponents(){
        initPanels();
        initTable();
        initButtons();
    }
    
    private void bindComponents(){
        bindTable();
        bindListenerToButtons();
        bindButtons();
        bindPanels();
    }
    
    private void fillTable(){
        for(Movie movie : movies){
            NumberFormat priceFormat = new DecimalFormat("#0.00");
            String price = priceFormat.format(movie.getPrice()) + " €";
            movieTableModel.addRow(new Object[]{false, movie.getCode(),movie.getTitle(),price,movie.getQuantity()});
        }
    }
    
    private void initPanels(){
        movieTablePanel = new JPanel();
        GridBagLayout buttonPanelLayout = new GridBagLayout();
        constraints = new GridBagConstraints();
        buttonPanel = new JPanel(buttonPanelLayout);
        buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
    }
    
    private void bindPanels(){
        add(movieTablePanel);
        add(buttonPanel);
    }
    
    private void initTable(){
        
        movieTable = new JTable();
        
        tableHeader = movieTable.getTableHeader();
        ((DefaultTableCellRenderer)tableHeader.getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
        ((DefaultTableCellRenderer)movieTable.getDefaultRenderer(String.class)).setHorizontalAlignment(JLabel.CENTER);
                
        int columnCount = movieTable.getColumnModel().getColumnCount();
        int width = (int) (Toolkit.getDefaultToolkit().getScreenSize().width * 0.8);
        int height = (int) (Toolkit.getDefaultToolkit().getScreenSize().height * 0.8);
        Dimension scrollPaneDimension = new Dimension(width, height);
        
        for(int i=0; i<columnCount; i++){
            movieTable.getColumnModel().getColumn(i).setMinWidth(width / Constants.MOVIE_TABLE_COLUMNS.length);
            movieTable.getColumnModel().getColumn(i).setMaxWidth(width / Constants.MOVIE_TABLE_COLUMNS.length);
            movieTable.getColumnModel().getColumn(i).setPreferredWidth(width / Constants.MOVIE_TABLE_COLUMNS.length);
        }
        
        scrollPane = new JScrollPane(movieTable);        
        scrollPane.setMinimumSize(scrollPaneDimension);
        scrollPane.setMaximumSize(scrollPaneDimension);
        scrollPane.setPreferredSize(scrollPaneDimension);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        
        movieTable.setRowHeight(Constants.ROW_HEIGHT);
        movieTableModel = new MovieTableModel();
        movieTableModel.setColumnIdentifiers(Constants.MOVIE_TABLE_COLUMNS);
        
        movieTable.setModel(movieTableModel);
    }
    
    private void bindTable(){
        movieTablePanel.add(scrollPane);
    }
    
    private void initButtons() {
        insertMovieButton = new JButton(Constants.INSERT);
        deleteMovieButton = new JButton(Constants.DELETE);
        deleteAllMoviesButton = new JButton(Constants.DELETE_ALL);
        detailsMovieButton = new JButton(Constants.DETAILS);
        editMovieButton = new JButton(Constants.EDIT);
        searchMovieButton = new JButton(Constants.SEARCH); 
        dropFiltersButton = new JButton(Constants.DROP_FILTERS);
        
        setSizeToButtons(Constants.BUTTON_WIDTH, Constants.BUTTON_HEIGHT);
        
        buttonList = new ArrayList<>();
        
        buttonList.add(insertMovieButton);
        buttonList.add(deleteMovieButton);
        buttonList.add(deleteAllMoviesButton);
        buttonList.add(detailsMovieButton);
        buttonList.add(editMovieButton);
        buttonList.add(searchMovieButton);
        buttonList.add(dropFiltersButton);
    }
    
    private void setButtonSize(JButton button, int width, int height){
        button.setMinimumSize(new Dimension(width, height));
        button.setMaximumSize(new Dimension(width, height));
        button.setPreferredSize(new Dimension(width, height));
    }
    
    private void setSizeToButtons(int width, int height){
        setButtonSize(insertMovieButton, width, height);
        setButtonSize(deleteMovieButton, width, height);
        setButtonSize(deleteAllMoviesButton, width, height);
        setButtonSize(detailsMovieButton, width, height);
        setButtonSize(editMovieButton, width, height);
        setButtonSize(searchMovieButton, width, height);
        setButtonSize(dropFiltersButton, width, height);
    }
    
    private void bindListenerToButtons(){
        insertMovieButton.addActionListener(new ButtonListener());
        deleteMovieButton.addActionListener(new ButtonListener());
        deleteAllMoviesButton.addActionListener(new ButtonListener());
        editMovieButton.addActionListener(new ButtonListener());
        detailsMovieButton.addActionListener(new ButtonListener());
        searchMovieButton.addActionListener(new ButtonListener());
        dropFiltersButton.addActionListener(new ButtonListener());
    }
    
    private void bindButtons() {
        buttonPanel.add(insertMovieButton, constraints);
        buttonPanel.add(Box.createRigidArea(new Dimension(Constants.RIGID_AREA_WIDTH, Constants.RIGID_AREA_HEIGHT)));
        buttonPanel.add(deleteMovieButton, constraints);
        buttonPanel.add(Box.createRigidArea(new Dimension(Constants.RIGID_AREA_WIDTH, Constants.RIGID_AREA_HEIGHT)));
        buttonPanel.add(deleteAllMoviesButton, constraints);
        buttonPanel.add(Box.createRigidArea(new Dimension(Constants.RIGID_AREA_WIDTH, Constants.RIGID_AREA_HEIGHT)));
        buttonPanel.add(editMovieButton, constraints);
        buttonPanel.add(Box.createRigidArea(new Dimension(Constants.RIGID_AREA_WIDTH, Constants.RIGID_AREA_HEIGHT)));
        buttonPanel.add(detailsMovieButton, constraints);
        buttonPanel.add(Box.createRigidArea(new Dimension(Constants.RIGID_AREA_WIDTH, Constants.RIGID_AREA_HEIGHT)));
        buttonPanel.add(searchMovieButton, constraints);
        buttonPanel.add(Box.createRigidArea(new Dimension(Constants.RIGID_AREA_WIDTH, Constants.RIGID_AREA_HEIGHT)));
        buttonPanel.add(dropFiltersButton, constraints);
    }
    
    private boolean canDeleteMovie(String movieCode, ArrayList<Reservation> reservations){
        for(Reservation reservation : reservations){
            String reservationCode = reservation.getMovie().getCode();
            int reservationStatus = reservation.getStatus();
            if(movieCode.equals(reservationCode) && (reservationStatus != Constants.STATUS_COMPLETED || reservationStatus != Constants.STATUS_CANCELED)){
                return false;
            }
        }
        return true;
    }
    
    class ButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event) {
           Object source = event.getSource();
           if(source == insertMovieButton){
               new InsertMovieFrame(movieTableModel, movies, buttonList, movieHandler);
           }
           if(source == deleteMovieButton){
               ArrayList<Integer> indices = new ArrayList<Integer>();
               for(int i=0; i<movieTableModel.getRowCount(); i++){
                    boolean selected = (boolean) movieTableModel.getValueAt(i, 0);
                    if(selected){
                        indices.add(i);
                    }
                }
                int rows = indices.size();
                if(rows == 0){
                    int selectedIndex = movieTable.getSelectedRow();
                    if(selectedIndex != -1){
                        int confirmDelete = Status.showConfirmMessage(Constants.CONFIRM_DELETION);
                        if(confirmDelete == JOptionPane.YES_OPTION){
                            Movie movie = movies.get(selectedIndex);
                            String movieCode = movie.getCode();
                            if(canDeleteMovie(movieCode, reservations)){
                                movieHandler.deleteRecord(movie);
                                movieTableModel.removeRow(selectedIndex);
                                movies.remove(selectedIndex);
                                Status.showInfoMessage(Constants.RECORD_DELETED);
                            }
                            else Status.showErrorMessage(Constants.CANNOT_DELETE_MOVIE);
                        }
                    }
                    else Status.showErrorMessage(Constants.NO_RECORD_SELECTED);
                }   
                else {
                    int confirmDelete = Status.showConfirmMessage(Constants.CONFIRM_DELETION);
                    rows = 0;
                    if(confirmDelete == JOptionPane.YES_OPTION){
                        Collections.reverse(indices);
                        for(Integer currentIndex : indices){
                            Movie movie = movies.get(currentIndex);
                            String movieCode = movie.getCode();
                            if(canDeleteMovie(movieCode, reservations)){
                                movieHandler.deleteRecord(movie);
                                movieTableModel.removeRow(currentIndex);
                                rows++;
                            }
                        }
                        ArrayList<Movie> tempMovies = new ArrayList<Movie>();
                        for(int i=0; i<movies.size(); i++){
                            if(indices.contains((Integer)i)){
                                continue;
                            }
                            else tempMovies.add(movies.get(i));
                        }
                        movies = tempMovies;
                        movieTable.getSelectionModel().setLeadSelectionIndex(-1);
                        Status.showInfoMessage(Constants.DELETED + rows + Constants.RECORDS);
                    }  
                } 
           }
           if(source == deleteAllMoviesButton){
               ArrayList<Integer> indices = new ArrayList<Integer>();
               for(int i=0; i<movieTableModel.getRowCount(); i++){
                    boolean selected = (boolean) movieTableModel.getValueAt(i, 0);
                    if(selected){
                        indices.add(i);
                    }
               }
               int rows = indices.size();
               
               int size = movies.size();
               int modelSize = movieTableModel.getRowCount();
               if(size > 0 && modelSize > 0){
                   int confirmDelete = Status.showConfirmMessage(Constants.CONFIRM_DELETION_ALL);
                   if(confirmDelete == JOptionPane.YES_OPTION){
                       Collections.reverse(indices);
                        for(Integer currentIndex : indices){
                            Movie movie = movies.get(currentIndex);
                            String movieCode = movie.getCode();
                            if(canDeleteMovie(movieCode, reservations)){
                                movieHandler.deleteRecord(movie);
                                movieTableModel.removeRow(currentIndex);
                            }
                        }
                        ArrayList<Movie> tempMovies = new ArrayList<Movie>();
                        for(int i=0; i<movies.size(); i++){
                            if(indices.contains((Integer)i)){
                                continue;
                            }
                            else tempMovies.add(movies.get(i));
                        }
                        movies = tempMovies;
                        movieTable.getSelectionModel().setLeadSelectionIndex(-1);
                        Status.showInfoMessage(Constants.DELETED + rows + Constants.RECORDS);
                   }
               }
               else Status.showInfoMessage(Constants.NO_RECORDS_TO_DELETE);
               
           }
           if(source == editMovieButton){
               int index = movieTable.getSelectionModel().getLeadSelectionIndex();
               int size = movies.size();
               if(size > 0 && index > -1){
                   new EditMovieFrame(index, movies, movieTableModel, buttonList, movieHandler);
               }
               else Status.showErrorMessage(Constants.NO_RECORD_SELECTED);
           }
           if(source == detailsMovieButton){
               int index = movieTable.getSelectionModel().getLeadSelectionIndex();
               int size = movies.size();
               if(size > 0 && index > -1){
                    Movie movie = movies.get(index);
                    new DetailsMovieFrame(movie,buttonList);
               }
               else Status.showErrorMessage(Constants.NO_RECORD_SELECTED);
           }
           if(source == searchMovieButton){
               if(movieTableModel.getRowCount() > 0){
                   new SearchMovieFrame(movies, filteredMovies, movieTableModel, buttonList);
               }
               else Status.showInfoMessage(Constants.NO_RECORDS_TO_FILTER);
           }
           if(source == dropFiltersButton){
               movieTableModel.setRowCount(0);
               for(Movie movie : movies){
                   movieTableModel.addRow(new Object[]{false, movie.getCode(),movie.getTitle(),movie.getPrice() + " €",movie.getQuantity()});
               }
           }
        } 
    }
    
    class MovieTableModel extends DefaultTableModel {
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