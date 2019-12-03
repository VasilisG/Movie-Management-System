/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.moviemanagementsystem;

import javax.swing.JOptionPane;

/**
 *
 * @author Vasilis
 */
public final class Status {
    
    public static void showErrorMessage(String errorMessage){
        JOptionPane.showMessageDialog(null, errorMessage, Constants.ERROR, JOptionPane.ERROR_MESSAGE);
    }
    
    public static void showInfoMessage(String infoMessage){
        JOptionPane.showMessageDialog(null, infoMessage, Constants.INFO, JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static int showConfirmMessage(String confirmMessage){
        return JOptionPane.showConfirmDialog(null, confirmMessage, Constants.WARNING, JOptionPane.YES_NO_OPTION);
    }
}