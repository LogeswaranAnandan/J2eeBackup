package com.bikeapplication.constants;

public class Constants {
	
	public static String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	public static String URL = "jdbc:mysql://localhost:3306/bikerentingapplication";
	public static String USERNAME = "root";
	public static String PASSWORD = "zilkeradmin";
	public static String AVAILABLE_BIKES = "select * from bikedetails where availability = 'Available'";
	public static String ALL_BIKES = "select * from bikedetails";

}
