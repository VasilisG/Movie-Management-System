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
public class CustomerCriteriaFirstName implements CustomerCriteria{
    
    private String firstName;
    
    public CustomerCriteriaFirstName(String firstName){
        this.firstName = firstName;
    }
    
    public void setFirstName(String firstName){
        this.firstName = firstName;
    }
    
    public String getFirstName(){
        return firstName;
    }

    @Override
    public ArrayList<Customer> meetCriteria(ArrayList<Customer> customers) {
        ArrayList<Customer> filteredCustomers = new ArrayList<Customer>();
        for(Customer customer : customers){
            String firstName = customer.getFirstName();
            if(firstName.equals(this.firstName)){
                filteredCustomers.add(customer);
            }
        }
        return filteredCustomers;
    }
    
}
