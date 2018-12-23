package menus;

import java.util.Scanner;

import exception.OutOfRangeException;
import list.ReservationsList;

public class ReservationsMenu extends Menu{
	private String menuText = "Choose an option:\n"
							+ "1. Add new reservation\n"
							+ "2. Cancel a reservation\n"
							+ "3. Display all reservations";
	@Override
	public int HandleMenuOption(Scanner in) throws OutOfRangeException {
		setMenuText(menuText);
		DisplayMenu(in);
		int userChoice = in.nextInt();
		if (userChoice < 1 || userChoice > 3) {
			throw new OutOfRangeException("Number outside of range!");
		}
		return userChoice;
	}

}
