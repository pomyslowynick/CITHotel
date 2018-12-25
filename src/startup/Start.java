package startup;

import java.io.Serializable;

import controller.HotelController;
import storage.FileStorage;

public class Start implements Serializable {
	public HotelController control;
	protected FileStorage store;
	public void CheckControllerExists() {
		store = new FileStorage();
		try {//load controller object from file and continue from last save
			control = (HotelController)store.readObject("storage.ser"); 
			control.handleHomeMenu();
			System.out.println(control);
		}
		catch(Exception e) {//controller not found. start from scratch
			control = new HotelController(store);
			control.setupRooms();
		}

	}
}
