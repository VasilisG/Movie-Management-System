/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package criteria;

import com.mycompany.moviemanagementsystem.Transaction;
import java.util.ArrayList;

/**
 *
 * @author Vasilis
 */
public interface TransactionCriteria {
    
    public ArrayList<Transaction> meetCriteria(ArrayList<Transaction> transactions);
}
