package com.bikeapplication.constants;

public class Constants {
	
	public static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	public static final String URL = "jdbc:mysql://localhost:3306/bikerentingapplication";
	public static final String USERNAME = "root";
	public static final String PASSWORD = "zilkeradmin";
	public static final String AVAILABLE_BIKES = "select * from bikedetails where availability = 'Available'";
	public static final String ALL_BIKES = "select * from bikedetails";
	public static final String UNAVAILABLE = "Unavailable";
	public static final String AVAILABLE = "Available";
	public static int USERID;
	
	public static void setUSERID(int USERID) {
		Constants.USERID = USERID;
	}
	
	public static int getUSERID() {
		return Constants.USERID;
	}
}
