package list;

import classes.Guest;
import classes.Reservation;

public class ReservationsList extends ObjectList{

	public ReservationsList(int maximum) {
		super(maximum);
	}
	
	public void AddReservation(Reservation reservation) {
		this.add(reservation);
	}
	
	public void CancelReservation(Reservation reservation) {
		int tempIndex = list.indexOf(reservation);
		this.remove(tempIndex);
	}

}
