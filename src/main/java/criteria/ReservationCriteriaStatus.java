/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package criteria;

import com.mycompany.moviemanagementsystem.Constants;
import com.mycompany.moviemanagementsystem.Reservation;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Vasilis
 */
public class ReservationCriteriaStatus implements ReservationCriteria{
    
    private String statusString;
    private HashMap<String, Integer> status;
    
    public ReservationCriteriaStatus(String statusString){
        status = new HashMap<String,Integer>();
        status.put(" ", 0);
        status.put(Constants.ONGOING, Constants.STATUS_ONGOING);
        status.put(Constants.DUE, Constants.STATUS_DUE);
        status.put(Constants.OVERDUE, Constants.STATUS_OVERDUE);
        status.put(Constants.CANCELED, Constants.STATUS_CANCELED);
        status.put(Constants.COMPLETED, Constants.STATUS_COMPLETED);
        this.statusString = statusString;
    }
    
    @Override
    public ArrayList<Reservation> meetCriteria(ArrayList<Reservation> reservations) {
        int reservationStatus = status.get(statusString);
        ArrayList<Reservation> filteredReservations = new ArrayList<Reservation>();
        for(Reservation reservation : reservations){
            if(reservation.getStatus() == reservationStatus){
                filteredReservations.add(reservation);
            }
        }
        return filteredReservations;
    }
    
}
