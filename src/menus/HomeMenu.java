package menus;

import java.io.Serializable;
import java.util.Scanner;

import classes.Room;
import controller.PaymentController;
import controller.ReservationsController;
import exception.OutOfRangeException;
import list.RoomList;
import list.GuestList;
import list.ReservationsList;

public class HomeMenu extends Menu implements Serializable{
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
			System.out.println(((Room) r).getRoomType() + " " + r.toString());
		}
		waitForUser();
	}
	
	public void DisplayGuests(Scanner in, GuestList guests) {
		for(Object r:guests.getList()) {
			System.out.println(r.toString());
		}
		waitForUser();
	}
	
	public void DisplayReservationMenu(Scanner in, ReservationsList reservations, GuestList guests, RoomList rooms) throws OutOfRangeException {
		int option = new ReservationsMenu().HandleMenuOption(in);
		new ReservationsController(in).handleReservationMenu(option, reservations, guests, rooms);
	}
	
	public void DisplayPaymentMenu(Scanner in, ReservationsList reservations, GuestList guests, RoomList rooms) throws OutOfRangeException{
		int option = new PaymentMenu().HandleMenuOption(in);
		new PaymentController(in).handlePaymentMenu(option, reservations, guests, rooms);
	}
	
	public int DisplayExitMenu(Scanner in) throws OutOfRangeException{
		return new ExitMenu().HandleMenuOption(in);
	}
}
