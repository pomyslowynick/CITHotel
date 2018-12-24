package menus;

import java.io.IOException;
import java.util.Scanner;
import exception.OutOfRangeException;

public abstract class Menu {
	public String menuText;
	
	public void DisplayMenu(){
		System.out.println(this.menuText);
	}
	
	public void setMenuText(String menuText) {
		this.menuText = menuText;
	}
	public void waitForUser() {
		System.out.println("Press ENTER to get back to main menu");
		try {
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public abstract int HandleMenuOption() throws OutOfRangeException;
		
	
}
