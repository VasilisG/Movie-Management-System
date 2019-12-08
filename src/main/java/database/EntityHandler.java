/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 *
 * @author Vasilis
 */
public abstract class EntityHandler {
    
    protected static final String databaseName = "MovieManagement";
    protected PreparedStatement preparedStatement = null;
    protected Connection connection = null;
    
    public EntityHandler(Connection connection){
        this.connection = connection;
    }
    
    public abstract void insertRecord(Object obj);
    public abstract void updateRecord(Object obj);
    public abstract void deleteRecord(Object obj);
    
}
