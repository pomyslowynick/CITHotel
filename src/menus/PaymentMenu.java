package menus;

import java.io.Serializable;
import java.util.Scanner;

import exception.OutOfRangeException;

public class PaymentMenu extends Menu implements Serializable{
	private String menuText = "Choose an option\n"
							+ "1. Process a payment\n"
							+ "2. Show outstanding payments";
	@Override
	public int HandleMenuOption(Scanner in) throws OutOfRangeException {
		setMenuText(menuText);
		DisplayMenu(in);
		int userChoice = in.nextInt();
		if (userChoice < 1 || userChoice > 2) {
			throw new OutOfRangeException("Number outside of range!");
		}
		return userChoice;
	}

}
