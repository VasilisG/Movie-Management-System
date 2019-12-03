/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validators;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 *
 * @author Vasilis
 */
public class DateValidator {
        
    public boolean isValidDate(String dateString){
        if(dateString.length() == 0){
            return true;
        }
        DateFormat dateFormat = new SimpleDateFormat(dateString);
        dateFormat.setLenient(false);
        try{
            dateFormat.parse(dateString);
        }catch(ParseException e){
            return false;
        }
        return true;
    }
    
}
