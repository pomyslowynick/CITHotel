package controller;

import java.io.Serializable;
import java.util.Scanner;

import classes.Reservation;
import list.GuestList;
import list.ReservationsList;
import list.RoomList;

public class PaymentController implements Serializable{
	Scanner in;
	
	public PaymentController(Scanner in) {
		this.in = in;
	}
	
	public void handlePaymentMenu(int option, ReservationsList reservations, GuestList guests, RoomList rooms) {
		int userChoice = option;
	
		switch(userChoice) {
		case 1: processPayment();
				break;
		case 2: showOutsandingPayments(reservations);
				break;
		}
	}
	
	public void processPayment() {
	}
	
	public void showOutsandingPayments(ReservationsList reservations) {
		for(Object c:reservations.getList()) {
			Reservation tempC = ((Reservation) c);
			double tempPay = tempC.getOutStandingPayment();
			if(!(tempPay > 0)) {
				System.out.println(tempC.toString() + " " + tempPay);
			}
		}
	}
}
