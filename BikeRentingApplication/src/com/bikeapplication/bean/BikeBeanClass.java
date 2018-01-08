package com.bikeapplication.bean;

public class BikeBeanClass {
	private int bikeid;
	private String Manufacturer;
	private String bikename;
	private int charge;
	private String regno;
	private String availability;

	public int getBikeid() {
		return bikeid;
	}

	public void setBikeid(int bikeid) {
		this.bikeid = bikeid;
	}

	public String getManufacturer() {
		return Manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		Manufacturer = manufacturer;
	}

	public String getBikename() {
		return bikename;
	}

	public void setBikename(String bikename) {
		this.bikename = bikename;
	}

	public int getCharge() {
		return charge;
	}

	public void setCharge(int charge) {
		this.charge = charge;
	}

	public String getRegno() {
		return regno;
	}

	public void setRegno(String regno) {
		this.regno = regno;
	}

	public String getAvailability() {
		return availability;
	}

	public void setAvailability(String availability) {
		this.availability = availability;
	}

	public String toString() {
		return "Bikeid : " + this.getBikeid() + "\nManufacturer : " + this.getManufacturer() + "\nBike Name : "
				+ this.getBikename() + "\nCharge(per hour) : " + this.getCharge() + "\nRegistration number : "
				+ this.getRegno() + "\nAvailability : " + this.getAvailability();
	}
}
