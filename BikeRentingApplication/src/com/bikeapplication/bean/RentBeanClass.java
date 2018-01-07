package com.bikeapplication.bean;

public class RentBeanClass {
	private int userid;
	private int bikeid;
	private String rented_date;
	private String rented_time;
	private int duration;
	private int estimatedamount;

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getRented_date() {
		return rented_date;
	}

	public void setRented_date(String rented_date) {
		this.rented_date = rented_date;
	}

	public String getRented_time() {
		return rented_time;
	}

	public void setRented_time(String rented_time) {
		this.rented_time = rented_time;
	}

	public int getEstimatedamount() {
		return estimatedamount;
	}

	public void setEstimatedamount(int estimatedamount) {
		this.estimatedamount = estimatedamount;
	}

	int advancepaid;

	public int getBikeid() {
		return bikeid;
	}

	public void setBikeid(int bikeid) {
		this.bikeid = bikeid;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public int getAdvancepaid() {
		return advancepaid;
	}

	public void setAdvancepaid(int advancepaid) {
		this.advancepaid = advancepaid;
	}
	
	public String toString() {
		return "UserId : " + this.getUserid() + "\nBikeId : " + this.getBikeid() + "\nRented date : " + this.getRented_date()
				+ "\nRented time : " + this.getRented_time() + "\nDuration : " + this.getDuration() + "\nEstimated Payment : " 
				+ this.getEstimatedamount() + "\nAdvance Paid : " + this.getAdvancepaid() + "\nPending amount : " 
				+ (this.getEstimatedamount() - this.getAdvancepaid());
	}

}
