package startup;

import controller.HotelController;
import storage.FileStorage;

public class Start {
	public HotelController control;
	protected FileStorage store;
	public void CheckControllerExists() {
		store = new FileStorage();
		try {//load controller object from file and continue from last save
			FileStorage objectIO = new FileStorage();
			HotelController st = (HotelController) objectIO.ReadObjectFromFile("storage.ser");
			st.handleHomeMenu();
	        System.out.println(st);
//			control = (HotelController)store.ReadObjectFromFile("storage.ser"); 
//			control.handleHomeMenu();
		}
		catch(Exception e) {//controller not found. start from scratch
			System.out.println("somethin wrong");
			control = new HotelController();
			control.setupRooms();
		}
		
	}
}
