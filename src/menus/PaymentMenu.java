package menus;

import java.util.Scanner;

public class PaymentMenu extends Menu{
	private String menuText = "Choose an option"
							+ "1. Process a payment"
							+ "2. Show outstanding payments";
	@Override
	public int HandleMenuOption(Scanner in) {
		System.out.println(menuText);
		int userChoice = in.nextInt();
		switch(userChoice) {
		case 1:  
		case 2:
		}
		return 0;
	}

}
