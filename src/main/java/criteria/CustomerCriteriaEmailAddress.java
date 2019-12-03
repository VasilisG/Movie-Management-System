/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package criteria;

import com.mycompany.moviemanagementsystem.Customer;
import java.util.ArrayList;

/**
 *
 * @author Vasilis
 */
public class CustomerCriteriaEmailAddress implements CustomerCriteria{
    
    private String emailAddress;
    
    public CustomerCriteriaEmailAddress(String emailAddress){
        this.emailAddress = emailAddress;
    }
    
    public void setEmailAddress(String emailAddress){
        this.emailAddress = emailAddress;
    }
    
    public String getEmailAddress(){
        return emailAddress;
    }

    @Override
    public ArrayList<Customer> meetCriteria(ArrayList<Customer> customers) {
        ArrayList<Customer> filteredCustomers = new ArrayList<Customer>();
        for(Customer customer : customers){
            String emailAddress = customer.getEmailAddress();
            if(emailAddress.equals(this.emailAddress)){
                filteredCustomers.add(customer);
            }
        }
        return filteredCustomers;
        
    }
    
}
