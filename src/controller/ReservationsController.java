package controller;

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

public class ReservationsController implements Serializable{
	Scanner in;
	
	public ReservationsController(Scanner in) {
		this.in = in;
	}
	
	public void handleReservationMenu(int userChoice, ReservationsList reservations, GuestList guests, RoomList rooms) {
		switch(userChoice) {
		case 1: addReservation(reservations, guests, rooms);
				break;
		case 2: cancelReservation(reservations, guests, rooms);
				break;
		case 3: displayReservations(in, reservations); 
				break;
		}
		
	}
	
	public int addReservation(ReservationsList reservations, GuestList guests, RoomList rooms) {
		if(!rooms.anythingAvailable()) {
			System.out.println("Hotel fully booked");
			return -1;
		}
		
		int peopleNum = 0;
		boolean loopCondition = true;
		boolean loop2 = true;
		Guest newGuest = addGuest(guests);
		Room roomCheck = null;
		while (loopCondition) {
			try {
	            System.out.println("What type of room are you interested in?\n"
				            		+ "1. Single, 150 euro per night.\n"
				            		+ "2. Double 100 euro per person, per night.\n"
				            		+ "3. Suite 75 euro per person, per night.");
	            int roomType = in.nextInt();
	            roomCheck = bookRoom(newGuest, rooms, roomType);
	            if (roomCheck != null) {loopCondition = false;}
			}catch (InputMismatchException e) {
	            System.out.println("\n" + e + "\nhas happened, make sure to input correct values.\n Click enter to continue...");
	            waitForUser();
			}
		}
		while (loop2) {
			try {
				int maxOccup = roomCheck.getMaxOccupancy();
	            if (!(roomCheck instanceof Single)) {
	            	boolean innerLoopCond = true;
	            	while (innerLoopCond) {
	            		System.out.println("How many people are with you?");
	            		peopleNum = in.nextInt();
	            		if (maxOccup < peopleNum + 1) {
	            			System.out.println("You can't have more than " + maxOccup + ",counting yourself, in your room");
	            		}
	            		else {
	            			newGuest.setNumGuests(peopleNum);
	            			for(int i = 0; i < peopleNum; i ++) {
	            				System.out.println("Provide details for other guests\n");
	            				in.nextLine();
	            				Guest additionalGuest = addGuest(guests);
	            				roomCheck.addGuest(additionalGuest); 
	            				innerLoopCond = false;
	            			}
	            		}
	            	}
	            }
	            loop2 = false;
			 
	        } catch (InputMismatchException e) {
	            System.out.println("\n" + e + "\nhas happened, make sure to input correct values.\n Click enter to continue...");
	        }
		}
		System.out.println("How many days you plan to stay?");
		int stayDays = in.nextInt();
		Reservation finalReservation = new Reservation(newGuest, roomCheck);
		double finalCost = ((roomCheck.getRate() * (peopleNum + 1)) * stayDays) * (1 - newGuest.getDiscount());
		System.out.println("Final cost: " + finalCost);
		finalReservation.setOutStandingPayment(finalCost);
		reservations.add(finalReservation);
		System.out.println(finalReservation.getOutStandingPayment());
		waitForUser();
		return 0;
	}
	
	public Guest addGuest(GuestList guests) {
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
	            	newGuest = (Lecturer) newGuest;
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
	            System.out.println("\n" + e + "\nhas happened, make sure to input correct values.");
	            waitForUser();
	        } 
		}
		guests.add(newGuest);
		return newGuest;
	}
	
	public Room bookRoom(Guest newGuest, RoomList rooms, int roomType) {
		switch(roomType) {
		case 1:
			Single avaSingle = rooms.getAvailableSingle();
			if (avaSingle == null) {
				System.out.println("No more singles available at the moment");
			} else {
				rooms.getAvailableSingle().addGuest(newGuest);
				return avaSingle;
			}
			break;
		case 2:
			DoubleRoom avaDouble = rooms.getAvailableDouble();
			if (avaDouble == null) {
				System.out.println("No more doubles available at the moment");
			} else {
				avaDouble.addGuest(newGuest);
				return avaDouble;
			}
			break;
		case 3:
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
	
	public void cancelReservation(ReservationsList reservations, GuestList guests, RoomList rooms) {
		System.out.println("Input id of your reservation: ");
		int resID = in.nextInt();
		Reservation check = reservations.getReservationByID(resID);
		try {
			if(check != null) {
				Guest tempGuest = check.getGuest();
				int guestIndex = guests.getIndex(check.getGuest());
				for(int i = 0; i <= tempGuest.getNumGuests();i++) {
					guests.remove(guestIndex);
				}
				check.getRoom().makeAvailable();
				reservations.cancelReservation(check);
				
			}else {
				System.out.println("ID not found");
			}
		}catch(InputMismatchException e) {
			System.out.println("\n" + e + "\nhas happened, make sure to input correct values.");
            waitForUser();
		}

	}
	public void displayReservations(Scanner in, ReservationsList reservations) {
		for(Object r:reservations.getList()) {
			System.out.println(r.toString());
		}
		waitForUser();
	}
	
	public void waitForUser(){
		System.out.println("Press ENTER to get back to main menu");
		in.nextLine();
		in.nextLine();
	
	}
}
