package list;

import classes.Room;

public class RoomList extends ObjectList {

	public RoomList(int maximum) {
		super(maximum);
	}
	
	public void addRoom(Room room) {
		this.add(room);
	}
}
