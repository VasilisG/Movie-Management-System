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
public class CustomerCriteriaAddress implements CustomerCriteria{
    
    private String address;
    
    public CustomerCriteriaAddress(String address){
        this.address = address;
    }
    
    public void setAddress(String address){
        this.address = address;
    }
    
    public String getAddress(){
        return address;
    }

    @Override
    public ArrayList<Customer> meetCriteria(ArrayList<Customer> customers) {
        ArrayList<Customer> filteredCustomers = new ArrayList<Customer>();
        for(Customer customer : customers){
            String address = customer.getAddress();
            if(address.equals(this.address)){
                filteredCustomers.add(customer);
            }
        }
        return filteredCustomers;
    }
    
}
