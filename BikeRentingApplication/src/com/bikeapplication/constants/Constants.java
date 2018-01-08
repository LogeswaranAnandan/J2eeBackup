package com.bikeapplication.constants;

public class Constants {
	public static int USERID;
	public static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	public static final String URL = "jdbc:mysql://localhost:3306/bikerentingapplication";
	public static final String USERNAME = "root";
	public static final String PASSWORD = "zilkeradmin";
	public static final String AVAILABLE_BIKES = "select * from bikedetails where availability = 'Available'";
	public static final String ALL_BIKES = "select * from bikedetails";
	public static final String UNAVAILABLE = "Unavailable";
	public static final String AVAILABLE = "Available";
	public static final String USERMENU = "Menu\n 1. View all bikes\n 2. View available bikes\n 3. Rent available bike\n "
			+ "4. Return a rented bike\n 5. Exit\n Enter your choice : ";
	public static final String ADMINMENU = "\nMenu\n 1.View all Bikes\n 2.View all rented bikes\n 3.Add a new bike\n"
			+ " 4.View all customers\n 5.Exit\n Enter you choice : ";
	public static final String VIEW_USER_DETAILS = "select * from userdetails inner join addressdetails where "
			+ "userdetails.addressid = addressdetails.addressid AND userdetails.userrole = 'Customer'";

	public static void setUSERID(int USERID) {
		Constants.USERID = USERID;
	}

	public static int getUSERID() {
		return Constants.USERID;
	}
}
