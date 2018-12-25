package controller;

import java.io.Serializable;
import java.util.InputMismatchException;
import java.util.Scanner;

import classes.Reservation;
import list.ReservationsList;

public class PaymentController implements Serializable{
	Scanner in;

	public PaymentController() {
		this.in =new Scanner(System.in);
	}

	public void handlePaymentMenu(int option, ReservationsList reservations) {
		int userChoice = option;
		switch(userChoice) {

		case 1: processPayment(reservations);
		break;
		case 2: showOutsandingPayments(reservations);
		break;
		}
	}

	public void processPayment(ReservationsList reservations) {
		int userID = 0;
		boolean loopCondition = true;
		while(loopCondition) {
			try {
				System.out.println("Input your reservation ID");
				userID = in.nextInt();
			} catch(InputMismatchException error) {
				System.out.println("Wrong type of input, please provide an integer");
			}
		}
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
		}
		System.out.println("Click anything to continue");
		in.nextLine();
	}
}
