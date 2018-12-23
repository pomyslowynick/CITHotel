package menus;

import java.util.Scanner;

public class ExitMenu extends Menu {
	private String menuText = "Do you want to save file?" 
							+ "1. Yes"
							+ "2. No";
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
