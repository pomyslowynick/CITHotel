package classes;

import java.io.Serializable;

public class Reservation implements Serializable{
	private Guest guest;
	private Room room;
	
	int resID;
	static int ID = 1;
	public Reservation(Guest guest, Room room) {
		this.guest = guest;
		this.resID = ID;
		this.room = room;
		ID++;
	}
	
	public int getID() {
		return this.resID;
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
}
