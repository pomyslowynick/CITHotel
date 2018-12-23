package classes;

import java.io.Serializable;
import java.util.ArrayList;

public class Room  implements Serializable{
	private Guest guest;
	private int totalOccupants;
	private double rate;
	private int max_guests;
	private ArrayList<Payment> payments;
	
	public Room() {
		this.payments = new ArrayList<Payment>();
	}
	public double getRate() {
		return this.rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}
	
	public void makeAvailable() {
		this.guest = null;
	}
	public int getMaxOccupancy() {
		return this.max_guests;
	}

	public void setMaxOccupancy(int max) {
		this.max_guests = max;
	}

	public void addGuest(Guest guest) {
		this.totalOccupants = guest.getNumGuests() + 1;
		this.guest = guest;
	}

	public boolean isAvailable() {
		return this.guest == null;
	}

	public void removeGuest() {
		this.totalOccupants = 0;
		this.guest = null;
	}

	public String toString() {
		return "Room Available :" + (this.isAvailable() ? " Yes":" No");
	}
	
	public void setRoomPaid(double amount) {
		double remaining = this.getRate() - amount;
		Payment pay = new Payment("2018-11-30",this.guest,amount,
									remaining,this.totalOccupants);
		this.addPayment(pay);
		this.removeGuest();
	}
	
	private void addPayment(Payment payment) {
		this.payments.add(payment);
	}
	
	public ArrayList<Payment> getPayments(){
		return this.payments;
	}
	
	public String getRoomType() {
		if(this instanceof Single) {
			return "Single";
		}else if (this instanceof DoubleRoom) {
			return "DoubleRoom";
		}else {
			return "Suite";
		}
	}
}
