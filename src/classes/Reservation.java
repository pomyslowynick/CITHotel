package classes;

public class Reservation {
	Guest guest;
	Room room;
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
}
