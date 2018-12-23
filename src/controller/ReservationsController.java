package controller;

import java.io.Serializable;
import java.util.Scanner;

import list.GuestList;
import list.ReservationsList;
import menus.HomeMenu;

public class ReservationsController implements Serializable {
	private ReservationsList reservations;
	
	public ReservationsController(Scanner in) {
		reservations = new ReservationsList(15);
	}
	
	public void handleReservationMenu() {
		int userChoice = in.nextInt();
		switch(userChoice) {
		case 1: 
		case 2:
		case 3: DisplayReservations(in, ); 
		}
		
	}
}
