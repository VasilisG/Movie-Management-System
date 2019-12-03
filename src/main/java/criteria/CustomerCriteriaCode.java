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
public class CustomerCriteriaCode implements CustomerCriteria{
    
    private String customerCode;
    
    public CustomerCriteriaCode(String customerCode){
        this.customerCode = customerCode;
    }
    
    public void setCustomerCode(String customerCode){
        this.customerCode = customerCode;
    }
    
    public String getCustomerCode(){
        return customerCode;
    }

    @Override
    public ArrayList<Customer> meetCriteria(ArrayList<Customer> customers) {
        ArrayList<Customer> filteredCustomers = new ArrayList<Customer>();
        for(Customer customer : customers){
            String code = customer.getCode();
            if(code.equals(customerCode)){
                filteredCustomers.add(customer);
            }
        }
        return filteredCustomers;
    }
    
}
