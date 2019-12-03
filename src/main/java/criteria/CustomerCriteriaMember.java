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
public class CustomerCriteriaMember implements CustomerCriteria{
    
    private boolean member;
    private boolean both;
    
    public CustomerCriteriaMember(boolean member, boolean both){
        this.member = member;
        this.both = both;
    }
    
    public void setMember(boolean member){
        this.member = member;
    }
    
    public void setBoth(boolean both){
        this.both = both;
    }
    
    public boolean getMember(){
        return member;
    }
    
    public boolean getBoth(){
        return both;
    }

    @Override
    public ArrayList<Customer> meetCriteria(ArrayList<Customer> customers) {
        ArrayList<Customer> filteredCustomers = new ArrayList<Customer>();
        if(both){
            filteredCustomers = customers;
        }
        else{
          for(Customer customer : customers){
            boolean member = customer.isMember();
            if(member == this.member){
                filteredCustomers.add(customer);
            }
          }  
        } 
        return filteredCustomers;
    }
    
}
