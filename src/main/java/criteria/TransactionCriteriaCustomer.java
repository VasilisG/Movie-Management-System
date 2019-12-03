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
public class TransactionCriteriaCustomer implements TransactionCriteria{
    
    private String customerCode;
    
    public TransactionCriteriaCustomer(String customerCode){
        this.customerCode = customerCode;
    }
        
    @Override
    public ArrayList<Transaction> meetCriteria(ArrayList<Transaction> transactions) {
        ArrayList<Transaction> filteredTransactions = new ArrayList<Transaction>();
        for(Transaction transaction : transactions){
            String customerCode = transaction.getCustomer().getCode();
            if(this.customerCode.equals(customerCode)){
                filteredTransactions.add(transaction);
            }
        }
        return filteredTransactions;
    }
    
}
