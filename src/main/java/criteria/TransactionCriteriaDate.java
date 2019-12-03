/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package criteria;

import com.mycompany.moviemanagementsystem.Constants;
import com.mycompany.moviemanagementsystem.Transaction;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Vasilis
 */
public class TransactionCriteriaDate implements TransactionCriteria{
    
    private Date startDate;
    private Date endDate;
    private int searchType;
    
    public TransactionCriteriaDate(Date startDate, Date endDate, int searchType){
        this.startDate = startDate;
        this.searchType = searchType;
    }
    
    public void setStartDate(Date startDate){
        this.startDate = startDate;
    }
    
    public void setEndDate(Date endDate){
        this.endDate = endDate;
    }
        
    public void setSearchType(int searchType){
        this.searchType = searchType;
    }
    
    public Date getStartDate(){
        return startDate;
    }
    
    public Date getEndDate(){
        return endDate;
    }
    
    public int getSearchType(){
        return searchType;
    }

    @Override
    public ArrayList<Transaction> meetCriteria(ArrayList<Transaction> transactions) {
        ArrayList<Transaction> filteredTransactions = new ArrayList<Transaction>();
        for(Transaction transaction : transactions){
            Date date = transaction.getDate();
            int startComparison,endComparison;
            startComparison = endComparison = 0;
            if(startDate != null){
                startComparison = date.compareTo(startDate);
                if(endDate != null){
                    endComparison = date.compareTo(endDate);
                }
            }
            else{
                if(endDate != null){
                    endComparison = date.compareTo(endDate);
                }
                else return transactions;
            }
            if(searchType == Constants.EQ){
                if(startComparison == 0){
                    filteredTransactions.add(transaction);
                }
            }
            else if(searchType == Constants.LEQ){
                if(endComparison == 0 || endComparison < 0){
                    filteredTransactions.add(transaction);
                }
            }
            else if(searchType == Constants.MEQ){
                if(startComparison == 0 || startComparison > 0){
                    filteredTransactions.add(transaction);
                }
            }
            else if(searchType == Constants.RANGE){
                if(startComparison >= 0 && endComparison <= 0){
                    filteredTransactions.add(transaction);
                }
            }
        }
        return filteredTransactions;
    }
    
}
