package controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.InputMismatchException;
import java.util.Scanner;

import classes.DoubleRoom;
import classes.Guest;
import classes.Lecturer;
import classes.Reservation;
import classes.Room;
import classes.Single;
import classes.Student;
import classes.Suite;
import list.GuestList;
import list.ReservationsList;
import list.RoomList;
import menus.HomeMenu;

public class ReservationsController {
	Scanner in;
	public ReservationsController(Scanner in) {
		this.in = in;
	}
	
	public void handleReservationMenu(int userChoice, ReservationsList reservations, GuestList guests, RoomList rooms) {
		switch(userChoice) {
		case 1: addReservation(reservations, guests, rooms);
				break;
		case 2: ;
		case 3: displayReservations(in, reservations); 
				break;
		}
		
	}
	
	public int addReservation(ReservationsList reservations, GuestList guests, RoomList rooms) {
		if(!rooms.anythingAvailable()) {
			System.out.println("Hotel fully booked");
			return -1;
		}
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
		guests.add(newGuest);
		loopCondition = true;
		while (loopCondition) {
			try {
	            System.out.println("What type of room are you interested in?\n"
	            		+ "1. Single, 150 euro per night.\n"
	            		+ "2. Double 100 euro per person, per night.\n"
	            		+ "3. Suite 75 euro per person, per night.");
	            int roomType = in.nextInt();
	            Room roomCheck = bookRoom(newGuest, rooms, roomType);
	            if (roomCheck != null) {loopCondition = false;}
	            System.out.println("How many people are with you?");
	            int peopleNum = in.nextInt();
	            int maxOccup = roomCheck.getMaxOccupancy();
	            if (maxOccup > peopleNum + 1) {
	            	System.out.println("You can't have more than " + maxOccup + " in your room");
	            }
	            
	            waitForUser();
	            
	        } catch (InputMismatchException e) {
	            System.out.println("\n" + e + "\nhas happened, make sure to input correct values.\n Click enter to continue...");
	            waitForUser();
	        }
		}
		return 0;
	}
	public Room bookRoom(Guest newGuest, RoomList rooms, int roomType) {
		if(roomType == 1) {
			Single avaSingle = rooms.getAvailableSingle();
			if (avaSingle == null) {
				System.out.println("No more singles available at the moment");
			} else {
				rooms.getAvailableSingle().addGuest(newGuest);
				return avaSingle;
			}
		} else if (roomType == 2) {
			DoubleRoom avaDouble = rooms.getAvailableDouble();
			if (avaDouble == null) {
				System.out.println("No more doubles available at the moment");
			} else {
				avaDouble.addGuest(newGuest);
				return avaDouble;
			}
		} else if (roomType == 3) {
			Suite avaSuite = rooms.getAvailableSuite();
			if (avaSuite == null) {
				System.out.println("No more suites available at the moment");
			} else {
				rooms.getAvailableSuite().addGuest(newGuest);
				return avaSuite;
			}
		}
		return null;
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
