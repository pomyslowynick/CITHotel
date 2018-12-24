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
		case 1: processPayment(reservations);
				break;
		case 2: showOutsandingPayments(reservations);
				break;
		}
	}
	
	public void processPayment(ReservationsList reservations) {
		System.out.println("Input your reservation ID");
		int userID = in.nextInt();
		Reservation userRes = reservations.getReservationByID(userID);
		double due = userRes.getOutStandingPayment();
		System.out.println("You owe " + due + " euro to pay. Pay now?"
							+ "Input the amount you want to transfer");
		double userTransaction = in.nextDouble();
		userRes.setOutStandingPayment(due - userTransaction);
		
		
	}
	
	public void showOutsandingPayments(ReservationsList reservations) {
		for(Object c:reservations.getList()) {
			Reservation tempC = ((Reservation) c);
			double tempPay = tempC.getOutStandingPayment();
			if(!(tempPay < 0)) {
				System.out.println(tempC.toString() + " due to pay " + tempPay);
			}
			in.nextLine();
			System.out.println("Clicking anything to continue");
			in.nextLine();

		}
	}
}
