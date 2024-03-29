/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.mycompany.moviemanagementsystem.Constants;
import com.mycompany.moviemanagementsystem.Main;
import com.mycompany.moviemanagementsystem.Movie;
import com.mycompany.moviemanagementsystem.MovieFactor;
import com.mycompany.moviemanagementsystem.Status;
import database.MovieHandler;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;
import validators.MovieValidator;

/**
 *
 * @author Vasilis
 */
public class InsertMovieFrame extends javax.swing.JFrame implements WindowListener{
    
    private DefaultListModel movieActorsListModel;
    private ButtonListener buttonListener;
    private MovieListener movieListener;
    private MovieValidator validator;
    private MovieHandler movieHandler;
    
    private DefaultTableModel movieTableModel;
    private ArrayList<Movie> movies;
    private ArrayList<JButton> buttonList;

    /**
     * Creates new form InsertCustomerFrame
     */
    public InsertMovieFrame(DefaultTableModel movieTableModel, ArrayList<Movie> movies, ArrayList<JButton> buttonList, MovieHandler movieHandler) {
        initComponents();
        initComboBox();
        bindListeners();
        addWindowListener(this);
        
        movieActorsListModel = new DefaultListModel();
        movieActorsList.setModel(movieActorsListModel);
        
        validator = new MovieValidator();
        
        this.movieTableModel = movieTableModel;
        this.movies = movies;
        this.buttonList = buttonList;
        this.movieHandler = movieHandler;
        
        setMainPanelButtonsEnabled(false);
        
        this.setVisible(true);
        this.setTitle(Constants.INSERT_MOVIE_FRAME_TITLE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        moviePanel = new javax.swing.JPanel();
        movieCodeLabel = new javax.swing.JLabel();
        movieCodeField = new javax.swing.JTextField();
        movieTitleLabel = new javax.swing.JLabel();
        movieTitleField = new javax.swing.JTextField();
        movieYearLabel = new javax.swing.JLabel();
        movieYearField = new javax.swing.JTextField();
        moviePlotLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        moviePlotArea = new javax.swing.JTextArea();
        movieActorsLabel = new javax.swing.JLabel();
        movieActorFirstNameField = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        movieActorsList = new javax.swing.JList<>();
        movieDirector = new javax.swing.JLabel();
        movieDirectorFirstNameField = new javax.swing.JTextField();
        moviePlaytimeLabel = new javax.swing.JLabel();
        moviePlaytimeField = new javax.swing.JTextField();
        moviePriceLabel = new javax.swing.JLabel();
        moviePriceField = new javax.swing.JTextField();
        movieQuantityLabel = new javax.swing.JLabel();
        movieQuantityField = new javax.swing.JTextField();
        cancelButton = new javax.swing.JButton();
        insertButton = new javax.swing.JButton();
        clearFieldsButton = new javax.swing.JButton();
        movieActorLastNameField = new javax.swing.JTextField();
        movieDirectorLastNameField = new javax.swing.JTextField();
        removeActorButton = new javax.swing.JButton();
        removeAllActorsButton = new javax.swing.JButton();
        movieTypeLabel = new javax.swing.JLabel();
        movieTypeComboBox = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        movieCodeLabel.setText("Code: ");

        movieCodeField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                movieCodeFieldActionPerformed(evt);
            }
        });

        movieTitleLabel.setText("Title:");

        movieYearLabel.setText("Year:");

        moviePlotLabel.setText("Plot:");

        moviePlotArea.setColumns(20);
        moviePlotArea.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        moviePlotArea.setRows(5);
        jScrollPane1.setViewportView(moviePlotArea);

        movieActorsLabel.setText("Actors:");

        movieActorsList.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        movieActorsList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(movieActorsList);

        movieDirector.setText("Director: ");

        moviePlaytimeLabel.setText("Playtime (secs):");

        moviePriceLabel.setText("Price:");

        movieQuantityLabel.setText("Quantity:");

        cancelButton.setText("Cancel");

        insertButton.setText("Insert");

        clearFieldsButton.setText("Clear fields");

        removeActorButton.setText("Remove actor");

        removeAllActorsButton.setText("Remove all");

        movieTypeLabel.setText("Type:");

        javax.swing.GroupLayout moviePanelLayout = new javax.swing.GroupLayout(moviePanel);
        moviePanel.setLayout(moviePanelLayout);
        moviePanelLayout.setHorizontalGroup(
            moviePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(moviePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(moviePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(moviePanelLayout.createSequentialGroup()
                        .addGroup(moviePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(moviePlotLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(movieTitleLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(movieCodeLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(moviePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(moviePanelLayout.createSequentialGroup()
                                .addGroup(moviePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(movieCodeField)
                                    .addComponent(movieTitleField, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addComponent(movieTypeLabel)
                                .addGap(18, 18, 18)
                                .addComponent(movieTypeComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(movieYearLabel)
                                .addGap(18, 18, 18)
                                .addComponent(movieYearField, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(moviePanelLayout.createSequentialGroup()
                        .addGroup(moviePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, moviePanelLayout.createSequentialGroup()
                                .addComponent(moviePlaytimeLabel)
                                .addGap(18, 18, 18)
                                .addComponent(moviePlaytimeField, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(moviePriceLabel)
                                .addGap(18, 18, 18)
                                .addComponent(moviePriceField, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(movieQuantityLabel)
                                .addGap(18, 18, 18)
                                .addComponent(movieQuantityField))
                            .addGroup(moviePanelLayout.createSequentialGroup()
                                .addComponent(movieDirector)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(movieDirectorFirstNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(movieDirectorLastNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, moviePanelLayout.createSequentialGroup()
                                .addComponent(movieActorsLabel)
                                .addGap(18, 18, 18)
                                .addGroup(moviePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(moviePanelLayout.createSequentialGroup()
                                        .addComponent(movieActorFirstNameField)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(movieActorLastNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(moviePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(removeActorButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(removeAllActorsButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(53, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, moviePanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(clearFieldsButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(insertButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cancelButton)
                .addContainerGap())
        );
        moviePanelLayout.setVerticalGroup(
            moviePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(moviePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(moviePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(movieCodeLabel)
                    .addComponent(movieCodeField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(moviePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(movieYearLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(moviePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(movieTitleLabel)
                        .addComponent(movieTitleField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(movieYearField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(movieTypeLabel)
                        .addComponent(movieTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(moviePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(moviePlotLabel)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(moviePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(movieActorsLabel)
                    .addComponent(movieActorFirstNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(movieActorLastNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(moviePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(moviePanelLayout.createSequentialGroup()
                        .addComponent(removeActorButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(removeAllActorsButton)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(moviePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(movieDirector)
                    .addComponent(movieDirectorFirstNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(movieDirectorLastNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(moviePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(moviePlaytimeLabel)
                    .addComponent(moviePlaytimeField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(moviePriceLabel)
                    .addComponent(moviePriceField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(movieQuantityLabel)
                    .addComponent(movieQuantityField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addGroup(moviePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelButton)
                    .addComponent(insertButton)
                    .addComponent(clearFieldsButton))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(moviePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(moviePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void movieCodeFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_movieCodeFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_movieCodeFieldActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new InsertMovieFrame().setVisible(true);
//            }
//        });
    }
    
    private void initComboBox(){
        movieTypeComboBox.addItem(Constants.ACTION);
        movieTypeComboBox.addItem(Constants.ADVENTURE);
        movieTypeComboBox.addItem(Constants.COMEDY);
        movieTypeComboBox.addItem(Constants.CRIME);
        movieTypeComboBox.addItem(Constants.DRAMA);
        movieTypeComboBox.addItem(Constants.FANTASY);
        movieTypeComboBox.addItem(Constants.HISTORICAL);
        movieTypeComboBox.addItem(Constants.HORROR);
        movieTypeComboBox.addItem(Constants.MAGICAL);
        movieTypeComboBox.addItem(Constants.MYSTERY);
        movieTypeComboBox.addItem(Constants.PARANOID);
        movieTypeComboBox.addItem(Constants.PHILOSOPHICAL);
        movieTypeComboBox.addItem(Constants.POLITICAL);
        movieTypeComboBox.addItem(Constants.ROMANCE);
        movieTypeComboBox.addItem(Constants.SAGA);
        movieTypeComboBox.addItem(Constants.SATIRE);
        movieTypeComboBox.addItem(Constants.SCIENCE);
        movieTypeComboBox.addItem(Constants.SOCIAL);
        movieTypeComboBox.addItem(Constants.SPECULATIVE);
        movieTypeComboBox.addItem(Constants.THRILLER);
        movieTypeComboBox.addItem(Constants.URBAN);
        movieTypeComboBox.addItem(Constants.WESTERN);
        
        movieTypeComboBox.setSelectedIndex(0);
    }
    
    private void bindListeners(){
        buttonListener = new ButtonListener();
        clearFieldsButton.addActionListener(buttonListener);
        cancelButton.addActionListener(buttonListener);
        insertButton.addActionListener(buttonListener);
        removeActorButton.addActionListener(buttonListener);
        removeAllActorsButton.addActionListener(buttonListener);
        movieTypeComboBox.addActionListener(buttonListener);
        
        movieListener = new MovieListener();
        movieCodeField.addActionListener(movieListener);
        movieTitleField.addActionListener(movieListener);
        movieYearField.addActionListener(movieListener);
        movieActorFirstNameField.addActionListener(movieListener);
        movieActorLastNameField.addActionListener(movieListener);
        movieDirectorFirstNameField.addActionListener(movieListener);
        movieDirectorLastNameField.addActionListener(movieListener);
        moviePlaytimeField.addActionListener(movieListener);
        moviePriceField.addActionListener(movieListener);
        movieQuantityField.addActionListener(movieListener);
    }
    
    private void clearFields(){
        this.movieCodeField.setText("");
        this.movieTitleField.setText("");
        this.movieYearField.setText("");
        this.moviePlotArea.setText("");
        this.movieActorFirstNameField.setText("");
        this.movieActorLastNameField.setText("");
        this.movieActorsListModel.clear();
        this.movieDirectorFirstNameField.setText("");
        this.movieDirectorLastNameField.setText("");
        this.moviePlaytimeField.setText("");
        this.moviePriceField.setText("");
        this.movieQuantityField.setText("");
    }
    
    private void setMainPanelButtonsEnabled(boolean enabled){
        for(JButton button : buttonList){
            button.setEnabled(enabled);
        }
    }
       
    private Movie getMovie(){
        int year = -1;
        int playtime = -1;
        int quantity = -1;
        double price = -1;
        String code = movieCodeField.getText();
        String title = movieTitleField.getText();
        String type = (String)movieTypeComboBox.getSelectedItem();
        try{
           year = Integer.parseInt(movieYearField.getText()); 
           playtime = Integer.parseInt(moviePlaytimeField.getText());
           price = Double.parseDouble(moviePriceField.getText());
           quantity = Integer.parseInt(movieQuantityField.getText());
        }
        catch(NumberFormatException exception){
            return null;
        }
        String plot = moviePlotArea.getText();
        ArrayList<MovieFactor> actors = new ArrayList<MovieFactor>();
        for(int i=0; i<movieActorsListModel.getSize(); i++){
            actors.add((MovieFactor)movieActorsListModel.get(i));
        }
        MovieFactor director = new MovieFactor(movieDirectorFirstNameField.getText(), movieDirectorLastNameField.getText());
        
        Movie movie = new Movie(code,title,type,year,plot,actors,director,playtime,price,quantity);
        return movie;
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
            if(source == removeActorButton){
                int index = movieActorsList.getSelectedIndex();
                if(index != -1){
                    movieActorsListModel.remove(index);
                }
            }
            if(source == removeAllActorsButton){
                movieActorsListModel.removeAllElements();
            }
            if(source == cancelButton){
                setMainPanelButtonsEnabled(true);
                dispose();
            }
            if(source == clearFieldsButton){
                clearFields();
            }
            if(source == insertButton){
                Movie movie = getMovie();
                if(validator.isValidMovie(movie)){
                    if(validator.hasUniqueCode(movie,movies)){
                        NumberFormat priceFormat = new DecimalFormat("#0.00");
                        String price = priceFormat.format(movie.getPrice()) + " €";
                        if(movieHandler != null){
                           movieHandler.insertRecord(movie);
                            System.out.println("Movie added in database"); 
                        }
                        else System.out.println("Movie handler is null for some reason.");
                        movieTableModel.addRow(new Object[]{false, movie.getCode(),movie.getTitle(),price,movie.getQuantity()});
                        movies.add(movie);
                        clearFields();
                        Status.showInfoMessage(Constants.MOVIE_TABLE_UPDATED);
                    }
                    else{
                        Status.showErrorMessage(Constants.DUPLICATE_MOVIE_CODE);
                        clearFields();
                    }
                }
                else{
                    Status.showErrorMessage(Constants.INVALID_MOVIE);
                    clearFields();
                }
            }
        }  
    }
    
    class MovieListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event) {
            
            if(movieCodeField.hasFocus()){
                String code = movieCodeField.getText();
                if(!validator.isValidCode(code)){
                    Status.showErrorMessage(Constants.INVALID_CODE);
                    movieCodeField.setText("");
                }
                else movieTitleField.requestFocus();
            }
            
            if(movieTitleField.hasFocus()){
                String title = movieTitleField.getText();
                if(!validator.isValidTitle(title)){
                    Status.showErrorMessage(Constants.INVALID_TITLE);
                    movieTitleField.setText("");
                }
                else movieYearField.requestFocus();
            }
            
            if(movieYearField.hasFocus()){
                int year = 0;
                try{
                    year = Integer.parseInt(movieYearField.getText());
                    if(!validator.isValidYear(year)){
                        Status.showErrorMessage(Constants.INVALID_YEAR);
                        movieYearField.setText("");
                    }
                    else moviePlotArea.requestFocus();
                }
                catch (NumberFormatException ex){
                    Status.showErrorMessage(Constants.INVALID_DATA);
                    movieYearField.setText("");
                }
            }
            
            if(movieActorFirstNameField.hasFocus()){
                movieActorLastNameField.requestFocus();
            }

            if(movieActorLastNameField.hasFocus()){
                String firstName = movieActorFirstNameField.getText().trim();
                String lastName = movieActorLastNameField.getText().trim();
                MovieFactor actor = new MovieFactor(firstName, lastName);
                
                if(!validator.isValidMovieFactor(actor)){
                    Status.showErrorMessage(Constants.INVALID_NAME);
                    movieActorFirstNameField.setText("");
                    movieActorLastNameField.setText("");
                    movieActorFirstNameField.requestFocus();
                }
                else {
                    movieActorsListModel.addElement(actor);
                    movieActorFirstNameField.setText("");
                    movieActorLastNameField.setText("");
                    movieActorFirstNameField.requestFocus();
                }
            }
            
            if(movieDirectorFirstNameField.hasFocus()){
                movieDirectorLastNameField.requestFocus();
            }
            
            if(movieDirectorLastNameField.hasFocus()){
                String firstName = movieDirectorFirstNameField.getText().trim();
                String lastName = movieDirectorLastNameField.getText().trim();
                MovieFactor director = new MovieFactor(firstName, lastName);
                
                if(!validator.isValidDirector(director)){
                    Status.showErrorMessage(Constants.INVALID_NAME);
                    movieDirectorFirstNameField.setText("");
                    movieDirectorLastNameField.setText("");
                    movieDirectorFirstNameField.requestFocus();
                }
                else moviePlaytimeField.requestFocus();
            }
            
            if(moviePlaytimeField.hasFocus()){
                try{
                    int playtime = Integer.parseInt(moviePlaytimeField.getText());
                    if(!validator.isValidPlayTime(playtime)){
                        Status.showErrorMessage(Constants.INVALID_PLAYTIME);
                        moviePlaytimeField.setText("");
                    }
                    else moviePriceField.requestFocus();
                }
                catch(NumberFormatException ex){
                    Status.showErrorMessage(Constants.INVALID_DATA);
                    moviePlaytimeField.setText("");
                }
            }
            
            if(moviePriceField.hasFocus()){
                try{
                    double price = Double.parseDouble(moviePriceField.getText());
                    if(!validator.isValidPrice(price)){
                        Status.showErrorMessage(Constants.INVALID_PRICE);
                        moviePriceField.setText("");
                    }
                    else movieQuantityField.requestFocus();
                }
                catch(NumberFormatException ex){
                    Status.showErrorMessage(Constants.INVALID_DATA);
                    moviePriceField.setText("");
                }
            }
            
            if(movieQuantityField.hasFocus()){
                try{
                    int quantity = Integer.parseInt(movieQuantityField.getText());
                    if(!validator.isValidQuantity(quantity)){
                        Status.showErrorMessage(Constants.INVALID_QUANTITY);
                        movieQuantityField.setText("");
                    }
                }
                catch(NumberFormatException ex){
                    Status.showErrorMessage(Constants.INVALID_DATA);
                    movieQuantityField.setText("");
                }
            }     
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelButton;
    private javax.swing.JButton clearFieldsButton;
    private javax.swing.JButton insertButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField movieActorFirstNameField;
    private javax.swing.JTextField movieActorLastNameField;
    private javax.swing.JLabel movieActorsLabel;
    private javax.swing.JList<String> movieActorsList;
    private javax.swing.JTextField movieCodeField;
    private javax.swing.JLabel movieCodeLabel;
    private javax.swing.JLabel movieDirector;
    private javax.swing.JTextField movieDirectorFirstNameField;
    private javax.swing.JTextField movieDirectorLastNameField;
    private javax.swing.JPanel moviePanel;
    private javax.swing.JTextField moviePlaytimeField;
    private javax.swing.JLabel moviePlaytimeLabel;
    private javax.swing.JTextArea moviePlotArea;
    private javax.swing.JLabel moviePlotLabel;
    private javax.swing.JTextField moviePriceField;
    private javax.swing.JLabel moviePriceLabel;
    private javax.swing.JTextField movieQuantityField;
    private javax.swing.JLabel movieQuantityLabel;
    private javax.swing.JTextField movieTitleField;
    private javax.swing.JLabel movieTitleLabel;
    private javax.swing.JComboBox<String> movieTypeComboBox;
    private javax.swing.JLabel movieTypeLabel;
    private javax.swing.JTextField movieYearField;
    private javax.swing.JLabel movieYearLabel;
    private javax.swing.JButton removeActorButton;
    private javax.swing.JButton removeAllActorsButton;
    // End of variables declaration//GEN-END:variables
}
