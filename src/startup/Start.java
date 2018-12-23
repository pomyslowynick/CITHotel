package startup;

import controller.HotelController;
import storage.FileStorage;

public class Start {
	HotelController control;
	FileStorage store;
	public void CheckControllerExists() {
		store = new FileStorage();
		try {//load controller object from file and continue from last save
			control = (HotelController)store.readObject("storage.ser"); 
			control.handleHomeMenu();
		}
		catch(Exception e) {//controller not found. start from scratch
			control = new HotelController();
			control.setupRooms();
		}
		
	}
}
