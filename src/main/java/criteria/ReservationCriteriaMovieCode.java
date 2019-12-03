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
public class ReservationCriteriaMovieCode implements ReservationCriteria{
    
    private String movieCode;
    
    public ReservationCriteriaMovieCode(String movieCode){
        this.movieCode = movieCode;
    }
    
    public void setMovieCode(String movieCode){
        this.movieCode = movieCode;
    }
    
    public String getMovieCode(){
        return movieCode;
    }

    @Override
    public ArrayList<Reservation> meetCriteria(ArrayList<Reservation> reservations) {
        ArrayList<Reservation> filteredReservations = new ArrayList<Reservation>();
        for(Reservation reservation : reservations){
            String currentMovieCode = reservation.getMovie().getCode();
            if(currentMovieCode.equals(movieCode)){
                filteredReservations.add(reservation);
            }
        }
        return filteredReservations;
    }
    
}
