package list;

import classes.Guest;

public class GuestList extends ObjectList{

	public GuestList(int maximum) {
		super(maximum);
	}
	
	public void AddGuest(Guest guest) {
		this.add(guest);
	}

}
