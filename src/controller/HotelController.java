package controller;

import java.io.Serializable;
import java.util.Scanner;

import classes.*;
import exception.NegativeIntException;
import list.RoomList;
import menus.*;
import storage.FileStorage;

public class HotelController implements Serializable{
	private RoomList rooms;
	static Scanner in;
	public HotelController() {
		rooms = new RoomList(15);
		in = new Scanner(System.in);
	}
	
	public void handleHomeMenu() {
		System.out.println("In home menu");
		HomeMenu men = new HomeMenu();

		men.DisplayMenu(in);
		try {
			men.HandleMenuOption(in);
		} catch(NegativeIntException error) {
			
		}
		men.DisplayAvailableRooms(rooms);
	
		new FileStorage().writeObject(this, "storage.ser");
	}
	
	public void setupRooms() {
		System.out.println("Loading rooms");
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
}
