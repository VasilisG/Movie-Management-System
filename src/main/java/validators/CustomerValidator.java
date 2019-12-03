/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validators;

import com.mycompany.moviemanagementsystem.Customer;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Vasilis
 */
public class CustomerValidator {
    
    private static final String CODE_PREFIX = "CUST-";
    private static final int SUFFIX_LENGTH = 10;
    private static final int PHONE_NUMBER_LENGTH = 10;
    
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX = 
    Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    
    public boolean hasUniqueCode(String code, ArrayList<Customer> customers){
        for(Customer customer : customers){
            if(customer.getCode().equals(code)){
                return false;
            }
        }
        return true;
    }
    
    public boolean isValidCode(String code){
        if(code.length() == SUFFIX_LENGTH && code.matches("[0-9]+")){
            return true;
        }
        else return false;
    }
    
    public boolean isValidName(String name){
        return name.length() > 0 && name.matches("[a-zA-Z ]+");
    }
    
    public boolean isValidAddress(String address){
        return address.length() > 0;
    }
    
    public boolean isValidEmailAddress(String emailAddress){
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(emailAddress);
        return matcher.find();
    }
    
    public boolean isValidPhoneNumber(String phoneNumber){
        return phoneNumber.length() == PHONE_NUMBER_LENGTH && phoneNumber.matches("[0-9]+");
    } 
    
    public boolean isValidCustomer(Customer customer){
        boolean validCustomer = true;
        
        validCustomer &= isValidCode(customer.getCode());
        validCustomer &= isValidName(customer.getFirstName());
        validCustomer &= isValidName(customer.getLastName());
        validCustomer &= isValidAddress(customer.getAddress());
        validCustomer &= isValidEmailAddress(customer.getEmailAddress());
        validCustomer &= isValidPhoneNumber(customer.getPhoneNumber());
        
        return validCustomer;
    }
}
