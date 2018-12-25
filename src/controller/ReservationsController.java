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

	public ReservationsController() {
		this.in = new Scanner(System.in);
	}

	public void handleReservationMenu(int userChoice, ReservationsList reservations, GuestList guests, RoomList rooms) {
		switch(userChoice) {
		case 1: addReservation(reservations, guests, rooms);
		break;
		case 2: cancelReservation(reservations, guests);
		break;
		case 3: displayReservations(reservations); 
		break;
		}

	}

	public int addReservation(ReservationsList reservations, GuestList guests, RoomList rooms) {
		if(!rooms.anythingAvailable()) {
			System.out.println("Hotel fully booked");
			return -1;
		}
		Guest newGuest = addGuest(guests, false);
		Room roomCheck = chooseTypeRoom(newGuest, rooms);
		int peopleNum = getAdditionalOccupants(roomCheck, newGuest, guests);
		makeReservation(reservations, newGuest, roomCheck, peopleNum);
		return 0;
	}

	public int getAdditionalOccupants(Room roomCheck, Guest newGuest, GuestList guests) {
		int peopleNum = 0;
		boolean loopConditionOccupants = true;
		while (loopConditionOccupants) {
			try {
				int maxOccup = roomCheck.getMaxOccupancy();
				if (!(roomCheck instanceof Single)) {
					boolean innerLoopCond = true;
					while (innerLoopCond) {
						System.out.println("How many people are with you?");
						peopleNum = in.nextInt();
						if (maxOccup < peopleNum + 1) {
							System.out.println("You can't have more than " + maxOccup + ",counting yourself, in your room");
						} else if (peopleNum <= 0) {
							innerLoopCond = false;
						}
						else {
							newGuest.setNumGuests(peopleNum);
							for(int i = 0; i < peopleNum; i ++) {
								System.out.println("Provide details for other guests\n");
								Guest additionalGuest = addGuest(guests, true);
								roomCheck.addGuest(additionalGuest); 
								innerLoopCond = false;
							}
						}
					}
				}
				return peopleNum;
			} catch (InputMismatchException e) {
				System.out.println("\n" + e + "\nhas happened, make sure to input correct values.\n Click enter to continue...");
			}
		}
		return peopleNum;
	}
	public void makeReservation(ReservationsList reservations, Guest newGuest, Room roomCheck, int peopleNum) {
		boolean loopCondition = true;
		int stayDays = 0;
		while(loopCondition) {
			try {
				System.out.println("How many days do you plan to stay?");
				stayDays = in.nextInt();
				loopCondition = false;
			}catch(InputMismatchException error) {
				System.out.println("Input an integer number.");
				resubmitInput();
			}
		}
		Reservation finalReservation = new Reservation(newGuest, roomCheck);
		double finalCost = ((roomCheck.getRate() * (peopleNum + 1)) * stayDays) * (1 - newGuest.getDiscount());
		System.out.println("Final cost: " + finalCost);
		finalReservation.setOutStandingPayment(finalCost);
		reservations.add(finalReservation);
		waitForUser();
		waitForUser();
	}

	public Room chooseTypeRoom(Guest newGuest, RoomList rooms) {

		Room roomCheck = null;
		boolean loopConditionMasterGuest = true;
		while (loopConditionMasterGuest) {
			try {
				System.out.println("What type of room are you interested in?\n"
						+ "1. Single, 150 euro per night.\n"
						+ "2. Double 100 euro per person, per night.\n"
						+ "3. Suite 75 euro per person, per night.");
				int roomType = in.nextInt();
				roomCheck = bookRoom(newGuest, rooms, roomType);
				if (roomCheck != null) {loopConditionMasterGuest = false;}
			}catch (InputMismatchException e) {
				System.out.println("\n" + e + "\nhas happened, make sure to input correct values.\n Click enter to continue...");
				waitForUser();
			}
		}
		return roomCheck;
	}

	public Guest addGuest(GuestList guests, boolean additional) {
		Guest newGuest = null;
		boolean loopCondition = true;
		while(loopCondition) {
			try {
				if (!additional) {
					System.out.println("Are you a student or lecturer?\n"
							+ "1 for student 2 for lecturer");
					int studOrLec = in.nextInt();
					if(studOrLec == 1) {
						newGuest = new Student();
					} else if (studOrLec == 2) {
						newGuest = new Lecturer();
						newGuest = (Lecturer) newGuest;
					} 
					else {
						throw new InputMismatchException();
					}
				}else {
					newGuest = new Guest(.0);
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

	public void cancelReservation(ReservationsList reservations, GuestList guests) {
		boolean loopCondition = true;
		while(loopCondition) {
			try {
				System.out.println("Input id of your reservation: ");
				int resID = in.nextInt();
				Reservation check = reservations.getReservationByID(resID);

				if(check != null) {
					Guest tempGuest = check.getGuest();
					int guestIndex = guests.getIndex(check.getGuest());
					for(int i = 0; i <= tempGuest.getNumGuests();i++) {
						guests.remove(guestIndex);
					}
					check.getRoom().makeAvailable();
					reservations.cancelReservation(check);
					loopCondition = false;

				}else {
					System.out.println("ID not found");
					loopCondition = false;
				}
			}catch(InputMismatchException e) {
				System.out.println("\n" + e + "\nhas happened, make sure to input correct values.");
				waitForUser();
			}
		}

	}
	public void displayReservations(ReservationsList reservations) {
		for(Object r:reservations.getList()) {
			System.out.println(r.toString());
		}
		waitForUser();
	}

	public void waitForUser(){
		System.out.println("Press ENTER to get back to main menu");
		in.nextLine();

	}

	public void resubmitInput(){
		System.out.println("Input correct input");
		in.nextLine();

	}


}
