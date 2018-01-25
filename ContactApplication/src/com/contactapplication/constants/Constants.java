package com.contactapplication.constants;

public class Constants {
	public static final String URL = "jdbc:mysql://localhost:3306/jdbcconnectivity";
	public static final String USERNAME = "root";
	public static final String PASSWORD = "zilkeradmin";
	public static final String JDBC_URL = "com.mysql.jdbc.Driver";
	public static final String MAIN_MENU = "1. Get phone number\n 2. Insert data\n 3. Exit\n Enter your choice : ";
	public static final String INVALID_CHOICE = "Invalid choice. Please try again with a valid choice";
	public static final String INVALID_INPUT = "Invalid input. Please try again with valid input";
	public static final String DISPLAY_OPTION = "Display option\n 1. Display the Office Number\n 2. Display the Mobile Number\n "
			+ "3. Display the Home Number\n 4. Display all the available Numbers\n Enter your choice : ";
	public static final String INVALID_NUMBER_CHOICE = "The requested type of number is not available for this user. Do you want "
			+ "to search for other available number of the same contact (y/n)? ";
	public static final String VALID_NUMBER_CHOICE = "Do you want to view other available numbers of the same contact (y/n)? ";
	public static final String GET_NUMBER = "select * from contacttable inner join numbertable where firstname = ? AND lastname = ? AND"
			+ " contacttable.contact_id = numbertable.contact_id;";
	public static final String INSERT_CONTACTTABLE_DATA = "insert into contacttable values(?,?,?,?)";
	public static final String INSERT_NUMBERTABLE_DATA = "insert into numbertable values(?,?,?,?,?,?);";
}
