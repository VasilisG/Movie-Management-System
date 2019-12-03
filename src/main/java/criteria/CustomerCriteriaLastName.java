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
public class CustomerCriteriaLastName implements CustomerCriteria{
    
    private String lastName;
    
    public CustomerCriteriaLastName(String lastName){
        this.lastName = lastName;
    }
    
    public void setLastName(String lastName){
        this.lastName = lastName;
    }
    
    public String getLastName(){
        return lastName;
    }

    @Override
    public ArrayList<Customer> meetCriteria(ArrayList<Customer> customers) {
        ArrayList<Customer> filteredCustomers = new ArrayList<Customer>();
        for(Customer customer : customers){
            String lastName = customer.getLastName();
            if(lastName.equals(this.lastName)){
                filteredCustomers.add(customer);
            }
        }
        return filteredCustomers;
    }
    
}
