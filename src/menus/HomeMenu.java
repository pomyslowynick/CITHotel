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
	Scanner in = new Scanner(System.in);
	@Override
	public int HandleMenuOption() throws OutOfRangeException {
		setMenuText(menuText);
		DisplayMenu();
		int userChoice = in.nextInt();
		if (userChoice < 0 || userChoice > 5) {
			throw new OutOfRangeException("Number outside of range!");
		}
		return userChoice;
	}
	
	public void DisplayAvailableRooms(RoomList rooms) {
		for(Object r:rooms.getList()) {
			System.out.println(((Room) r).getRoomType() + " " + r.toString());
		}
		waitForUser();
	}
	
	public void DisplayGuests(GuestList guests) {
		for(Object r:guests.getList()) {
			System.out.println(r.toString());
		}
		waitForUser();
	}
	
	public void DisplayReservationMenu(ReservationsList reservations, GuestList guests, RoomList rooms) throws OutOfRangeException {
		int option = new ReservationsMenu().HandleMenuOption();
		new ReservationsController().handleReservationMenu(option, reservations, guests, rooms);
	}
	
	public void DisplayPaymentMenu(ReservationsList reservations, GuestList guests, RoomList rooms) throws OutOfRangeException{
		int option = new PaymentMenu().HandleMenuOption();
		new PaymentController().handlePaymentMenu(option, reservations, guests, rooms);
	}
	
	public int DisplayExitMenu() throws OutOfRangeException{
		return new ExitMenu().HandleMenuOption();
	}
}
