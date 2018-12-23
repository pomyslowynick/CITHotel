package classes;

public class Reservation {
	Guest guest;
	int resID;
	static int ID;
	public Reservation(Guest guest) {
		this.guest = guest;
		this.resID = ID;
		ID++;
	}
	
	public int getID() {
		return this.resID;
	}
}
