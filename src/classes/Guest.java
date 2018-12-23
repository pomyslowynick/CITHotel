package classes;

import java.io.Serializable;

public class Guest  implements Serializable{
	private String firstName, lastName;
	private int phoneNumber;
	private int numGuests;
	private double discount;
	
	public Guest(double discount) {
		this.discount = discount;
	}
	
	public int getNumGuests() {
		return this.numGuests;
	}
	
	public String getfirstName() {
		return firstName;
	}
	
	public String getlastName() {
		return lastName;
	}
	
	public void setfirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public void setlastName(String lastName) {
		this.lastName = lastName;
	}
	
	public int getPhoneNumber() {
		return phoneNumber;
	}
	
	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public String toString() {
		return firstName + " " + lastName;
	}
}
