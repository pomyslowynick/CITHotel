package menus;

import java.io.Serializable;
import java.util.Scanner;
import exception.OutOfRangeException;

public class ExitMenu extends Menu implements Serializable{
	private String menuText = "Do you want to save file?\n" 
							+ "1. Yes\n"
							+ "2. No";
	Scanner in = new Scanner(System.in);
	@Override
	public int HandleMenuOption() throws OutOfRangeException {
		setMenuText(menuText);
		DisplayMenu();
		int userChoice = in.nextInt();
		if (userChoice < 1 || userChoice > 2) {
			throw new OutOfRangeException("Number outside of range!");
		}
		switch(userChoice) {
		case 1: return 1;
		case 2: return 0;
		}
		return 1;
	}

}
