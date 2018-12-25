package classes;

import java.io.Serializable;

public class Reservation implements Serializable{
	private Guest guest;
	private Room room;
	private double outStandingPayment;
	int resID;
	
	public Reservation(Guest guest, Room room) {
		this.guest = guest;
		this.room = room;
	}

	public int getID() {
		return this.resID;
	}
	
	public void setID(int id) {
		this.resID = id;
	}

	public String toString() {
		return "Reservation ID " + resID + " " + room.getRoomType() + 
				" " + guest.getfirstName() + " " + guest.getlastName();
	}

	public Guest getGuest() {
		return guest;
	}

	public Room getRoom() {
		return room;
	}

	public double getOutStandingPayment() {
		return outStandingPayment;
	}

	public void setOutStandingPayment(double outStandingPayment) {
		this.outStandingPayment = outStandingPayment;
	}
}
