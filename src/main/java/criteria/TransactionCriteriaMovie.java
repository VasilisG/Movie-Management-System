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
public class TransactionCriteriaMovie implements TransactionCriteria{
    
    private String movieCode;
    
    public TransactionCriteriaMovie(String movieCode){
        this.movieCode = movieCode;
    }

    @Override
    public ArrayList<Transaction> meetCriteria(ArrayList<Transaction> transactions) {
        ArrayList<Transaction> filteredTransactions = new ArrayList<Transaction>();
        for(Transaction transaction : transactions){
            String movieCode = transaction.getMovie().getCode();
            if(this.movieCode.equals(movieCode)){
                filteredTransactions.add(transaction);
            }
        }
        return filteredTransactions;
    }
    
}
