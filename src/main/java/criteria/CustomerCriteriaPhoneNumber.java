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
public class CustomerCriteriaPhoneNumber implements CustomerCriteria{
    
    private String phoneNumber;
    
    public CustomerCriteriaPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }
    
    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }
    
    public String getPhoneNumber(){
        return phoneNumber;
    }

    @Override
    public ArrayList<Customer> meetCriteria(ArrayList<Customer> customers) {
        ArrayList<Customer> filteredCustomers = new ArrayList<Customer>();
        for(Customer customer : customers){
            String phoneNumber = customer.getPhoneNumber();
            if(phoneNumber.equals(this.phoneNumber)){
                filteredCustomers.add(customer);
            }
        }
        return filteredCustomers;
    }
    
}
