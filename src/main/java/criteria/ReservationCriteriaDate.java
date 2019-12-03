/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package criteria;

import com.mycompany.moviemanagementsystem.Constants;
import com.mycompany.moviemanagementsystem.Reservation;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Vasilis
 */
public class ReservationCriteriaDate implements ReservationCriteria{
    
    private Date startDate;
    private Date endDate;
    private int searchType;
    
    public ReservationCriteriaDate(Date startDate, Date endDate, int searchType){
        this.startDate = startDate;
        this.endDate = endDate;
        this.searchType = searchType;
    }
    
    public void setStartDate(Date startDate){
        this.startDate = startDate;
    }
    
    public void setEndDate(Date endDate){
        this.endDate = endDate;
    }
    
    public void setSearchType(int searchType){
        this.searchType = searchType;
    }
    
    public Date getStartDate(){
        return startDate;
    }
    
    public Date getEndDate(){
        return endDate;
    }
    
    public int getSearchType(){
        return searchType;
    }
    
    @Override
    public ArrayList<Reservation> meetCriteria(ArrayList<Reservation> reservations) {
        ArrayList<Reservation> filteredReservations = new ArrayList<Reservation>();
        for(Reservation reservation : reservations){
            Date date = reservation.getStartDate();
            int startComparison,endComparison;
            startComparison = endComparison = 0;
            if(startDate != null){
                startComparison = date.compareTo(startDate);
                if(endDate != null){
                    endComparison = date.compareTo(endDate);
                }
            }
            else{
                if(endDate != null){
                    endComparison = date.compareTo(endDate);
                }
                else return reservations;
            }
            if(searchType == Constants.EQ){
                if(startComparison == 0){
                    filteredReservations.add(reservation);
                }
            }
            else if(searchType == Constants.LEQ){
                if(endComparison == 0 || endComparison < 0){
                    filteredReservations.add(reservation);
                }
            }
            else if(searchType == Constants.MEQ){
                if(startComparison == 0 || startComparison > 0){
                    filteredReservations.add(reservation);
                }
            }
            else if(searchType == Constants.RANGE){
                if(startComparison >= 0 && endComparison <= 0){
                    filteredReservations.add(reservation);
                }
            }
        }
        return filteredReservations;
    }
    
}
