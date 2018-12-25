package list;

import java.io.Serializable;

import classes.Guest;
import classes.Reservation;

public class ReservationsList extends ObjectList implements Serializable{
	static int ID = 100;
	
	public ReservationsList(int maximum) {
		super(maximum);
	}

	public void addReservation(Reservation reservation) {
		this.add(reservation);
	}

	public void cancelReservation(Reservation reservation) {
		int tempIndex = list.indexOf(reservation);
		this.remove(tempIndex);
	}

	public Reservation getReservationByID(int id) {
		for (Object r:list) {
			if (((Reservation) r).getID() == id) {
				return (Reservation) r;
			}
		}
		return null;
	}

	public void giveID(Reservation reservation) {
		reservation.setID(ID);
		ID ++;
	}
}
