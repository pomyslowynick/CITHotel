package controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.InputMismatchException;
import java.util.Scanner;

import classes.Guest;
import classes.Lecturer;
import classes.Reservation;
import classes.Student;
import list.GuestList;
import list.ReservationsList;
import menus.HomeMenu;

public class ReservationsController {
	Scanner in;
	public ReservationsController(Scanner in) {
		this.in = in;
	}
	
	public void handleReservationMenu(int userChoice, ReservationsList reservations) {
		switch(userChoice) {
		case 1: addReservation(reservations);
				break;
		case 2: ;
		case 3: displayReservations(in, reservations); 
				break;
		}
		
	}
	
	public void addReservation(ReservationsList reservations) {
		Guest newGuest = null;
		boolean loopCondition = true;
		while(loopCondition) {
	        try {
	            System.out.println("Are you a student or lecturer?\n"
	            				 + "1 for student 2 for lecturer");
	            int studOrLec = in.nextInt();
	            if(studOrLec == 1) {
	            	newGuest = new Student();
	            } else if (studOrLec == 2) {
	            	newGuest = new Lecturer();
	            } else {
	            	throw new InputMismatchException();
	            }
	            in.nextLine();
	            System.out.println("What's your name?");
	            newGuest.setfirstName(in.nextLine()); 
	            System.out.println("What's your last name?");
	            newGuest.setlastName(in.nextLine());
	            System.out.println("What's your phone number?");
	            newGuest.setPhoneNumber(in.nextInt());
	            loopCondition = false;
	            
	        } catch (InputMismatchException e) {
	            System.out.println("\n" + e + "\nhas happened, make sure to input correct values.\n Click enter to continue...");
	            waitForUser();
	        }
		}
		reservations.add(newGuest);
	}
	
	public void displayReservations(Scanner in, ReservationsList reservations) {
		for(Object r:reservations.getList()) {
			System.out.println(r.toString());
		}
		waitForUser();
	}
	
	public void waitForUser() {
		System.out.println("Press ENTER to get back to main menu");
		try {
			System.in.read();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
