package controller;

import java.io.Serializable;
import java.util.InputMismatchException;
import java.util.Scanner;
import classes.*;
import exception.OutOfRangeException;
import list.GuestList;
import list.ReservationsList;
import list.RoomList;
import menus.*;
import storage.FileStorage;

public class HotelController implements Serializable{
	private RoomList rooms;
	private GuestList guests;
	private ReservationsList reservations;
	static Scanner in;
	FileStorage store;
	
	public HotelController(FileStorage store) {
		this.store = store;
		rooms = new RoomList(15);
		guests = new GuestList(27);
		reservations = new ReservationsList(15);
		in = new Scanner(System.in);
		
	}
	
	public void handleHomeMenu() {
		HomeMenu men = new HomeMenu();
		int userChoice = 0;
		while(userChoice != 5) {
			try {
				userChoice = men.HandleMenuOption();
				switch(userChoice) {
				case 1: men.DisplayReservationMenu(reservations, guests, rooms);
						break;
				case 2: men.DisplayGuests(guests);
						break;
				case 3:	men.DisplayAvailableRooms(rooms);
						break;
				case 4: men.DisplayPaymentMenu(reservations);
						break;
				case 5: if (men.DisplayExitMenu() == 1) {
							saveFile();
						}
				}
			} catch(OutOfRangeException error) {
				System.out.println(error);
				men.waitForUser();
			} catch(InputMismatchException error) {
				System.out.println(error);
				in.nextLine();
				System.out.println("Press anything to continue...");
				in.nextLine();

			}
		}
	}
	
	public void setupRooms() {
		for(int i = 0; i < 3; i++) {			
			rooms.add(new Suite());
		}
		
		for(int i = 0; i < 6; i++) {
			rooms.add(new DoubleRoom());
		}
		
		for(int i = 0; i < 6; i++) {
			rooms.add(new Single());
		}
		this.handleHomeMenu();
	}
	
	public void saveFile() {
		store.writeObject(this, "storage.ser");
	}
	
}
