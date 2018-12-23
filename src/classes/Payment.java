package classes;

public class Payment {
	private String date;
	private Guest guest;
	private double paymentAmount;
	private double paymentOutstanding;
	private boolean paymentComplete;
	private int numOccupants;
	public Payment(String date,Guest guest,double payment,
					double outstanding, int occupants) {
		this.date = date;
		this.guest = guest;
		this.paymentAmount = payment;
		this.paymentOutstanding = outstanding;
		this.numOccupants = occupants;
		this.paymentComplete = (this.paymentOutstanding == 0) ? true:false;
	}
	
	public int getNumOccupants() {return this.numOccupants;}
	public double getPaymentOutstanding() {return this.paymentOutstanding;}
	public double getPaymentAmount() {return this.paymentAmount;}
	public Guest getGuest() {return this.guest;}
	public String getDate() {return this.date;}
	public boolean getPaymentComplete() {return this.paymentComplete;}
}
