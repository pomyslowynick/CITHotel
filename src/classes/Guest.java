package classes;

import java.io.Serializable;

public class Guest  implements Serializable{
	private String first_name, last_name;
	private int phoneNumber;
	private int numGuests;
	private double discount;
	
	public Guest(double discount) {
		this.discount = discount;
	}
	public int getNumGuests() {
		return this.numGuests;
	}
	public String getFirst_name() {
		return first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public int getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
}
