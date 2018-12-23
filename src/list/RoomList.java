package list;

import classes.DoubleRoom;
import classes.Room;
import classes.Single;
import classes.Suite;

public class RoomList extends ObjectList {

	public RoomList(int maximum) {
		super(maximum);
	}
	
	public void addRoom(Room room) {
		this.add(room);
	}
	
	public boolean anythingAvailable() {
		for (Object c:list) {
			if(((Room) c).isAvailable()) {
				return true;
			}
		}
		return false;
	}
	public Single getAvailableSingle() {
		for (int i = 9; i <= 14; i++) {
			if(((Room) list.get(i)).isAvailable()) {
				return (Single) list.get(i);
			}
		}
		return null;
	}
	
	public DoubleRoom getAvailableDouble() {
		for (int i = 3; i <= 8; i++) {
			if(((Room) list.get(i)).isAvailable()) {
				return (DoubleRoom) list.get(i);
			}
		}
		return null;
	}
	
	public Suite getAvailableSuite() {
		for (int i = 0; i <= 2; i++) {
			if(((Room) list.get(i)).isAvailable()) {
				return (Suite) list.get(i);
			}
		}
		return null;
	}
}
