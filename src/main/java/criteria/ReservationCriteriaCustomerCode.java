/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package criteria;

import com.mycompany.moviemanagementsystem.Reservation;
import java.util.ArrayList;

/**
 *
 * @author Vasilis
 */
public class ReservationCriteriaCustomerCode implements ReservationCriteria{

    private String customerCode;
    
    public ReservationCriteriaCustomerCode(String customerCode){
        this.customerCode = customerCode;
    }
    
    public void setCustomerCode(String movieCode){
        this.customerCode = customerCode;
    }
    
    public String getCustomerCode(){
        return customerCode;
    }

    @Override
    public ArrayList<Reservation> meetCriteria(ArrayList<Reservation> reservations) {
        ArrayList<Reservation> filteredReservations = new ArrayList<Reservation>();
        for(Reservation reservation : reservations){
            String currentCustomerCode = reservation.getCustomer().getCode();
            if(currentCustomerCode.equals(customerCode)){
                filteredReservations.add(reservation);
            }
        }
        return filteredReservations;
    }
    
}
