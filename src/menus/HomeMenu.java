package menus;

import java.util.Scanner;

import controller.HotelController;
import exception.OutOfRangeException;
import list.RoomList;
import list.GuestList;

public class HomeMenu extends Menu{
	private String menuText = "Hello Customer! What can we do for you today?\n"
			  + "1. Manage a reservation\n"
			  + "2. Display current guests\n"
			  + "3. Display all available rooms\n"
			  + "4. Process payment\n"
			  + "5. Exit";
	@Override
	public int HandleMenuOption(Scanner in) throws OutOfRangeException {
		setMenuText(menuText);
		DisplayMenu(in);
		int userChoice = in.nextInt();
		if (userChoice < 0 || userChoice > 5) {
			throw new OutOfRangeException("Number outside of range!");
		}
		
		return userChoice;
	}
	
	public void DisplayAvailableRooms(Scanner in, RoomList rooms) {
		for(Object r:rooms.getList()) {
			System.out.println(r.toString());
		}
		waitForUser();
	}
	
	public void DisplayGuests(Scanner in, GuestList guests) {
		for(Object r:guests.getList()) {
			System.out.println(r.toString());
		}
		waitForUser();
	}
	
	public void DisplayReservationMenu(Scanner in) throws OutOfRangeException {
		new ReservationsMenu().HandleMenuOption(in);
	}
	
	public void DisplayPaymentMenu(Scanner in) throws OutOfRangeException{
		new PaymentMenu().HandleMenuOption(in);
	}
	
	public int DisplayExitMenu(Scanner in) throws OutOfRangeException{
		return new ExitMenu().HandleMenuOption(in);
	}
}
