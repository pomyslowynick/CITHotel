package menus;

import java.util.Scanner;
import exception.NegativeIntException;

public abstract class Menu {
	public String menuText;
	
	public void DisplayMenu(Scanner in){
		System.out.println(this.menuText);
	}
	
	public abstract int HandleMenuOption(Scanner in) throws NegativeIntException;
		
	
}
