/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.mycompany.gui.MoviePanel.MovieTableModel;
import com.mycompany.moviemanagementsystem.Constants;
import com.mycompany.moviemanagementsystem.Movie;
import com.mycompany.moviemanagementsystem.MovieFactor;
import com.mycompany.moviemanagementsystem.Status;
import criteria.MovieCriteria;
import criteria.MovieCriteriaActor;
import criteria.MovieCriteriaDirector;
import criteria.MovieCriteriaPrice;
import criteria.MovieCriteriaTitle;
import criteria.MovieCriteriaType;
import criteria.MovieCriteriaYear;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import validators.MovieValidator;

/**
 *
 * @author Vasilis
 */
public class SearchMovieFrame extends javax.swing.JFrame implements WindowListener{

    /**
     * Creates new form SearchMovieFrame
     */
    
    private ArrayList<Movie> movies;
    private ArrayList<Movie> filteredMovies;
    private MovieTableModel movieTableModel;
    private ArrayList<MovieCriteria> criteria;
    private DefaultListModel actorsSearchModel;
    
    private ButtonGroup buttonGroup;
    private ButtonGroup comparisonGroup;
    private SearchMovieListener movieListener;
    private MovieFactorListener actorListener;
    
    private ArrayList<JButton> buttonList;
    
    private MovieValidator validator;
    
    public SearchMovieFrame(ArrayList<Movie> movies, ArrayList<Movie> filteredMovies, MovieTableModel movieTableModel, ArrayList<JButton> buttonList) {
        initComponents();
        initComboBox();
        initButtonGroup();
        initComparisonGroup();
        initValues(movies, movieTableModel, buttonList);
        addWindowListener(this);
        
        setMainPanelMovieButtons(false);
        
        addListeners();
        
        initFrame();
    }
    
    private void initComboBox(){
        typeSearchList.addItem(Constants.SELECT_TYPE);
        typeSearchList.addItem(Constants.ACTION);
        typeSearchList.addItem(Constants.ADVENTURE);
        typeSearchList.addItem(Constants.COMEDY);
        typeSearchList.addItem(Constants.CRIME);
        typeSearchList.addItem(Constants.DRAMA);
        typeSearchList.addItem(Constants.FANTASY);
        typeSearchList.addItem(Constants.HISTORICAL);
        typeSearchList.addItem(Constants.HORROR);
        typeSearchList.addItem(Constants.MAGICAL);
        typeSearchList.addItem(Constants.MYSTERY);
        typeSearchList.addItem(Constants.PARANOID);
        typeSearchList.addItem(Constants.PHILOSOPHICAL);
        typeSearchList.addItem(Constants.POLITICAL);
        typeSearchList.addItem(Constants.ROMANCE);
        typeSearchList.addItem(Constants.SAGA);
        typeSearchList.addItem(Constants.SATIRE);
        typeSearchList.addItem(Constants.SCIENCE);
        typeSearchList.addItem(Constants.SOCIAL);
        typeSearchList.addItem(Constants.SPECULATIVE);
        typeSearchList.addItem(Constants.THRILLER);
        typeSearchList.addItem(Constants.URBAN);
        typeSearchList.addItem(Constants.WESTERN);
        
        typeSearchList.setSelectedIndex(0);
    }
    
    private void initButtonGroup(){
        buttonGroup = new ButtonGroup();
        buttonGroup.add(exactTitleRadioButton);
        buttonGroup.add(likeTitleRadioButton);
    }
    
    private void initComparisonGroup(){
        comparisonGroup = new ButtonGroup();
        comparisonGroup.add(equalRadioButton);
        comparisonGroup.add(lessRadioButton);
        comparisonGroup.add(moreRadioButton);
    }
    
    private void initValues(ArrayList<Movie> movies, MovieTableModel movieTableModel, ArrayList<JButton> buttonList) {
        this.movies = movies;
        this.movieTableModel = movieTableModel;
        this.buttonList = buttonList;
        criteria = new ArrayList<MovieCriteria>();
        actorsSearchModel = new DefaultListModel();
        actorsSearchList.setModel(actorsSearchModel);
        validator = new MovieValidator();
    }
    
    private void initFrame(){
        this.setTitle(Constants.SEARCH_MOVIE_FRAME_TITLE);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    
    private void addListeners(){
        movieListener = new SearchMovieListener();
        clearFieldsButton.addActionListener(movieListener);
        searchButton.addActionListener(movieListener);
        cancelButton.addActionListener(movieListener);
        
        actorListener = new MovieFactorListener();
        actorFirstNameSearchField.addActionListener(actorListener);
        actorLastNameSearchField.addActionListener(actorListener);
        directorFirstNameSearchField.addActionListener(actorListener);
        directorLastNameSearchField.addActionListener(actorListener);
    }
    
    private void setMainPanelMovieButtons(boolean enabled){
        for(JButton button : buttonList){
            button.setEnabled(enabled);
        }
    }
    
    private void updateCheckBoxes(JCheckBox first, JCheckBox second, JCheckBox third){
        first.setSelected(true);
        second.setSelected(false);
        third.setSelected(false);
    }

    private void updateCheckBoxesState(ActionEvent event, JCheckBox first, JCheckBox second, JCheckBox third){
        Object source = event.getSource();
        if(source == first) updateCheckBoxes(first,second,third);
        if(source == second) updateCheckBoxes(second,first,third);
        if(source == third) updateCheckBoxes(third,first,second);
    }
    
    private void clearFields(){
        searchTitleField.setText("");
        actorFirstNameSearchField.setText("");
        actorLastNameSearchField.setText("");
        movieYearSearchField.setText("");
        actorsSearchModel.clear();
        directorFirstNameSearchField.setText("");
        directorLastNameSearchField.setText("");
        fromPriceSearchField.setText("");
        toPriceSearchField.setText("");
        individualPriceSearchField.setText("");
        typeSearchList.setSelectedIndex(0);
        exactTitleRadioButton.setSelected(false);
        likeTitleRadioButton.setSelected(false);
        equalRadioButton.setSelected(false);
        moreRadioButton.setSelected(false);
        lessRadioButton.setSelected(false);        
    }
    
    private boolean isTitleCriteriaOn(){
        return searchTitleField.getText().trim().length() > 0 &&
               (exactTitleRadioButton.isEnabled() || likeTitleRadioButton.isEnabled());
    }
    
    private boolean isTypeCriteriaOn(){
        return typeSearchList.getSelectedIndex() != 0;
    }
    
    private boolean isYearCriteriaOn(){
        String yearString = movieYearField.getText().trim();
        if(yearString.length()==0){
            return false;
        }
        else{
            try{
                int year = Integer.parseInt(yearString);
                if(validator.isValidYear(year)){
                    if(equalRadioButton.isEnabled() || 
                        lessRadioButton.isEnabled() || 
                        moreRadioButton.isEnabled()){
                        return true;
                    }
                        
                }
            }
            catch(NumberFormatException exception){
                return false;
            }
        }
        return false;
    }
    
    private boolean isActorsCriteriaOn(){
        return actorsSearchModel.size() > 0;
    }
    
    private boolean isDirectorCriteriaOn(){
        String firstName = directorFirstNameSearchField.getText();
        String lastName = directorLastNameSearchField.getText();
        return firstName.length() > 0 && lastName.length() > 0;
    }
    
    private boolean isPriceCriteriaOn(){
        try{
            int individualPrice = Integer.parseInt(individualPriceSearchField.getText().trim());
            int lowerPrice = Integer.parseInt(fromPriceSearchField.getText().trim());
            int upperPrice = Integer.parseInt(toPriceSearchField.getText().trim());
            return true;
        }
        catch(NumberFormatException exception){
            return false;
        }
    }
    
    private boolean isValidPrice(String price){
        return price.matches("-?\\d+(\\.\\d+)?");
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
        jLabel2 = new javax.swing.JLabel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jCheckBox1 = new javax.swing.JCheckBox();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        searchTitleLabel = new javax.swing.JLabel();
        searchTitleField = new javax.swing.JTextField();
        exactTitleRadioButton = new javax.swing.JRadioButton();
        likeTitleRadioButton = new javax.swing.JRadioButton();
        typeSearchLabel = new javax.swing.JLabel();
        typeSearchList = new javax.swing.JComboBox<>();
        actorSearchLabel = new javax.swing.JLabel();
        actorFirstNameSearchField = new javax.swing.JTextField();
        actorLastNameSearchField = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        actorsSearchList = new javax.swing.JList<>();
        removeActorButton = new javax.swing.JButton();
        removeAllButton = new javax.swing.JButton();
        directorSearchLabel = new javax.swing.JLabel();
        directorFirstNameSearchField = new javax.swing.JTextField();
        directorLastNameSearchField = new javax.swing.JTextField();
        priceSearchLabel = new javax.swing.JLabel();
        individualPriceSearchField = new javax.swing.JTextField();
        fromPriceSearchLabel = new javax.swing.JLabel();
        fromPriceSearchField = new javax.swing.JTextField();
        toPriceSearchLabel = new javax.swing.JLabel();
        toPriceSearchField = new javax.swing.JTextField();
        cancelButton = new javax.swing.JButton();
        searchButton = new javax.swing.JButton();
        movieYearField = new javax.swing.JLabel();
        movieYearSearchField = new javax.swing.JTextField();
        equalRadioButton = new javax.swing.JRadioButton();
        lessRadioButton = new javax.swing.JRadioButton();
        moreRadioButton = new javax.swing.JRadioButton();
        clearFieldsButton = new javax.swing.JButton();

        jLabel1.setText("jLabel1");

        jLabel2.setText("jLabel2");

        jRadioButton1.setText("jRadioButton1");

        jCheckBox1.setText("jCheckBox1");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        searchTitleLabel.setText("Title:");

        exactTitleRadioButton.setText("Precise");

        likeTitleRadioButton.setText("Like");

        typeSearchLabel.setText("Type:");

        actorSearchLabel.setText("Actors:");

        jScrollPane1.setViewportView(actorsSearchList);

        removeActorButton.setText("Remove actor");

        removeAllButton.setText("Remove all");

        directorSearchLabel.setText("Director:");

        priceSearchLabel.setText("Price:");

        fromPriceSearchLabel.setText("From:");

        toPriceSearchLabel.setText("To:");

        cancelButton.setText("Cancel");

        searchButton.setText("Search");

        movieYearField.setText("Year:");

        equalRadioButton.setText("=");

        lessRadioButton.setText("<");

        moreRadioButton.setText(">");

        clearFieldsButton.setText("Clear fields");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(typeSearchLabel)
                                    .addComponent(searchTitleLabel))
                                .addGap(29, 29, 29)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(searchTitleField)
                                    .addComponent(typeSearchList, 0, 225, Short.MAX_VALUE))
                                .addGap(90, 90, 90)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(likeTitleRadioButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(exactTitleRadioButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(125, 125, 125))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(movieYearField, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(157, 157, 157))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(priceSearchLabel)
                                .addGap(26, 26, 26)
                                .addComponent(individualPriceSearchField, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(276, 276, 276)
                                .addComponent(toPriceSearchLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(toPriceSearchField, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(movieYearSearchField, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(actorSearchLabel)
                                    .addComponent(directorSearchLabel))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 502, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(actorFirstNameSearchField)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(actorLastNameSearchField, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(directorFirstNameSearchField, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(fromPriceSearchLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(fromPriceSearchField, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(directorLastNameSearchField))))))))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(clearFieldsButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(searchButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cancelButton)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(removeActorButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(removeAllButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(equalRadioButton)
                                .addGap(18, 18, 18)
                                .addComponent(lessRadioButton)
                                .addGap(18, 18, 18)
                                .addComponent(moreRadioButton)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchTitleLabel)
                    .addComponent(searchTitleField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(exactTitleRadioButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(likeTitleRadioButton)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(typeSearchLabel)
                    .addComponent(typeSearchList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(movieYearField)
                    .addComponent(movieYearSearchField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(equalRadioButton)
                    .addComponent(lessRadioButton)
                    .addComponent(moreRadioButton))
                .addGap(58, 58, 58)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(actorSearchLabel)
                    .addComponent(actorFirstNameSearchField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(actorLastNameSearchField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(removeActorButton)
                        .addGap(18, 18, 18)
                        .addComponent(removeAllButton)))
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(directorFirstNameSearchField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(directorLastNameSearchField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(directorSearchLabel))
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(priceSearchLabel)
                    .addComponent(individualPriceSearchField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fromPriceSearchLabel)
                    .addComponent(fromPriceSearchField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(toPriceSearchLabel)
                    .addComponent(toPriceSearchField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchButton)
                    .addComponent(cancelButton)
                    .addComponent(clearFieldsButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SearchMovieFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SearchMovieFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SearchMovieFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SearchMovieFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new SearchMovieFrame().setVisible(true);
//            }
//        });
    }

    @Override
    public void windowOpened(WindowEvent e) {}

    @Override
    public void windowClosing(WindowEvent e) {
        setMainPanelMovieButtons(true);
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
    
    class SearchMovieListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event) {
           Object source = event.getSource();
           
           if(source == clearFieldsButton){
               clearFields();
           }
           
           if(source == searchButton){
               if(isTitleCriteriaOn()){
                   String title = searchTitleField.getText().trim();
                   if(exactTitleRadioButton.isEnabled()){
                       criteria.add(new MovieCriteriaTitle(title,Constants.PRECISE));
                       System.out.println("Title criteria have been added");
                   }
                   else{
                       criteria.add(new MovieCriteriaTitle(title,Constants.LIKE));
                       System.out.println("Title criteria have been added");
                   }
               }
               if(isTypeCriteriaOn()){
                   int index = typeSearchList.getSelectedIndex();
                   if(index != 0){
                       String type = (String)typeSearchList.getSelectedItem();
                       criteria.add(new MovieCriteriaType(type));
                       System.out.println("Type criteria have been added");
                   }
               }
               if(isYearCriteriaOn()){
                   int year = Integer.parseInt(movieYearSearchField.getText());
                   if(equalRadioButton.isEnabled()){
                       criteria.add(new MovieCriteriaYear(year,Constants.EQUAL));
                   }
                   else if(lessRadioButton.isEnabled()){
                       criteria.add(new MovieCriteriaYear(year,Constants.LESS));
                   }
                   else{
                       criteria.add(new MovieCriteriaYear(year,Constants.MORE));
                   }
                   System.out.println("Year criteria have been added");
               }
               if(isActorsCriteriaOn()){
                   ArrayList<MovieFactor> actors = new ArrayList<MovieFactor>();
                   for(int i=0; i<actorsSearchModel.getSize(); i++){
                       actors.add((MovieFactor)actorsSearchModel.get(i));
                   }
                   criteria.add(new MovieCriteriaActor(actors));
                   System.out.println("Actor criteria have been added");
               }
               if(isDirectorCriteriaOn()){
                   String directorFirstName = directorFirstNameSearchField.getText();
                   String directorLastName = directorLastNameSearchField.getText();
                   MovieFactor director = new MovieFactor(directorFirstName, directorLastName);
                   if(validator.isValidDirector(director)){
                       criteria.add(new MovieCriteriaDirector(director));
                       System.out.println("Director criteria have been added");
                   }
               }
               if(isPriceCriteriaOn()){
                   String individualPriceString = individualPriceSearchField.getText().trim();
                   String lowerPriceString = fromPriceSearchField.getText().trim();
                   String upperPriceString = toPriceSearchField.getText().trim();
                   
                   int lowerPrice, upperPrice, individualPrice;
                   
                   if(lowerPriceString.length() > 0 && isValidPrice(lowerPriceString)){
                       lowerPrice = Integer.parseInt(lowerPriceString);
                       if(upperPriceString.length() > 0 && isValidPrice(upperPriceString)){
                           upperPrice = Integer.parseInt(upperPriceString);
                           criteria.add(new MovieCriteriaPrice(-1, lowerPrice, upperPrice, Constants.EQ));
                       }
                       else criteria.add(new MovieCriteriaPrice(lowerPrice,0,0,Constants.MEQ));
                   }
                   else if(upperPriceString.length() > 0 && isValidPrice(upperPriceString)){
                       upperPrice = Integer.parseInt(upperPriceString);
                       if(lowerPriceString.length() > 0 && isValidPrice(lowerPriceString)){
                           lowerPrice = Integer.parseInt(lowerPriceString);
                           criteria.add(new MovieCriteriaPrice(-1, lowerPrice, upperPrice, Constants.EQ));
                       }
                       else criteria.add(new MovieCriteriaPrice(upperPrice,0,0,Constants.LEQ));
                   }
                   else if(individualPriceString.length() > 0 && isValidPrice(individualPriceString)){
                       individualPrice = Integer.parseInt(individualPriceString);
                       criteria.add(new MovieCriteriaPrice(individualPrice,0,0,Constants.EQ));
                   }
                   System.out.println("Price criteria have been added");
               }
               filteredMovies = new ArrayList<>(movies);
               for(MovieCriteria currentCriteria : criteria){
                   filteredMovies = currentCriteria.meetCriteria(filteredMovies);
               }
               movieTableModel.setRowCount(0);
               for(Movie movie : filteredMovies){
                   movieTableModel.addRow(new Object[]{false, movie.getCode(),movie.getTitle(),movie.getPrice(),movie.getQuantity()});
               }
               Status.showInfoMessage(Constants.FILTERED_RESULTS);
               // System.out.println("Movies filtered based on criteria.");
           }
           
           if(source == cancelButton){
               setMainPanelMovieButtons(true);
               dispose();
           }
        }
    }
    
    class MovieFactorListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event) {
            if(actorFirstNameSearchField.hasFocus()){
                actorLastNameSearchField.requestFocus();
            }
            if(actorLastNameSearchField.hasFocus()){
                String firstName = actorFirstNameSearchField.getText().trim();
                String lastName = actorLastNameSearchField.getText().trim();
                MovieFactor actor = new MovieFactor(firstName, lastName);
                
                if(!validator.isValidMovieFactor(actor)){
                    Status.showErrorMessage(Constants.INVALID_NAME);
                    actorFirstNameSearchField.setText("");
                    actorLastNameSearchField.setText("");
                    actorFirstNameSearchField.requestFocus();
                }
                else {
                    actorsSearchModel.addElement(actor);
                    actorFirstNameSearchField.setText("");
                    actorLastNameSearchField.setText("");
                    actorFirstNameSearchField.requestFocus();
                }
            }
            
            if(directorFirstNameSearchField.hasFocus()){
                directorLastNameSearchField.requestFocus();
            }
            if(directorLastNameSearchField.hasFocus()){
                String firstName = directorFirstNameSearchField.getText().trim();
                String lastName = directorLastNameSearchField.getText().trim();
                MovieFactor director = new MovieFactor(firstName, lastName);
                
                if(!validator.isValidMovieFactor(director)){
                    Status.showErrorMessage(Constants.INVALID_NAME);
                    directorFirstNameSearchField.setText("");
                    directorLastNameSearchField.setText("");
                    directorFirstNameSearchField.requestFocus();
                }
            }
        }
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField actorFirstNameSearchField;
    private javax.swing.JTextField actorLastNameSearchField;
    private javax.swing.JLabel actorSearchLabel;
    private javax.swing.JList<String> actorsSearchList;
    private javax.swing.JButton cancelButton;
    private javax.swing.JButton clearFieldsButton;
    private javax.swing.JTextField directorFirstNameSearchField;
    private javax.swing.JTextField directorLastNameSearchField;
    private javax.swing.JLabel directorSearchLabel;
    private javax.swing.JRadioButton equalRadioButton;
    private javax.swing.JRadioButton exactTitleRadioButton;
    private javax.swing.JTextField fromPriceSearchField;
    private javax.swing.JLabel fromPriceSearchLabel;
    private javax.swing.JTextField individualPriceSearchField;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JRadioButton lessRadioButton;
    private javax.swing.JRadioButton likeTitleRadioButton;
    private javax.swing.JRadioButton moreRadioButton;
    private javax.swing.JLabel movieYearField;
    private javax.swing.JTextField movieYearSearchField;
    private javax.swing.JLabel priceSearchLabel;
    private javax.swing.JButton removeActorButton;
    private javax.swing.JButton removeAllButton;
    private javax.swing.JButton searchButton;
    private javax.swing.JTextField searchTitleField;
    private javax.swing.JLabel searchTitleLabel;
    private javax.swing.JTextField toPriceSearchField;
    private javax.swing.JLabel toPriceSearchLabel;
    private javax.swing.JLabel typeSearchLabel;
    private javax.swing.JComboBox<String> typeSearchList;
    // End of variables declaration//GEN-END:variables
}
