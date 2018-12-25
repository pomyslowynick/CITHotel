package menus;

import java.io.Serializable;
import java.util.Scanner;

import exception.OutOfRangeException;

public class PaymentMenu extends Menu implements Serializable{
	private String menuText = "Choose an option\n"
			+ "1. Process a payment\n"
			+ "2. Show outstanding payments";
	Scanner in = new Scanner(System.in);
	@Override
	public int HandleMenuOption() throws OutOfRangeException {
		setMenuText(menuText);
		DisplayMenu();
		int userChoice = in.nextInt();
		if (userChoice < 1 || userChoice > 2) {
			throw new OutOfRangeException("Number outside of range!");
		}
		return userChoice;
	}

}
