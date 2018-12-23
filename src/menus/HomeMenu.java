package menus;

import java.util.Scanner;

import controller.HotelController;
import exception.NegativeIntException;
import list.RoomList;

public class HomeMenu extends Menu{
	private String menuText = "Hello Customer! What can we do for you today?"
			  + "1. Manage a reservation"
			  + "2. Display current guests"
			  + "3. Display all available rooms"
			  + "4. Process payment"
			  + "5. Exit";
	@Override
	public int HandleMenuOption(Scanner in) throws NegativeIntException {
		int userChoice = in.nextInt();
		switch(userChoice) {
		case 1: ReservationsMenu reservMenu = new ReservationsMenu();
				reservMenu.DisplayMenu(in);
				reservMenu.HandleMenuOption(in);
		}
		return 0;
	}
	
	public int DisplayAvailableRooms(RoomList rooms) {
		for(Object r:rooms.getList()) {
			System.out.println(r.toString());
		}
		return 0;
	}
	

}
