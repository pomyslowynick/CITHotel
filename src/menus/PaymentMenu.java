package menus;

import java.io.Serializable;
import java.util.Scanner;

public class PaymentMenu extends Menu implements Serializable{
	private String menuText = "Choose an option\n"
							+ "1. Process a payment\n"
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
