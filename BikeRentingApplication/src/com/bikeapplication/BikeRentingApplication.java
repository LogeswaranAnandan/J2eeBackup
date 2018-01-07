package com.bikeapplication;

import java.util.Scanner;
import java.util.logging.Logger;

import com.bikeapplication.dao.BikeApplicationDao;

public class BikeRentingApplication {
	static Logger logger = Logger.getLogger(BikeRentingApplication.class.getName());
	static BikeApplicationDao dao = new BikeApplicationDao();
	
	
	public void viewAllBikes() {
		dao.viewAllBike();
	}

	
	public void viewAvailableBikes() {
		dao.viewAvailableBikes();
		//Ask user whether he wants to rent a bike or not.
		//dao.rentBike(); has to be called if user says yes.
	}
	
	
	public void rentBike() {
		
	}
	
	public void returnBike() {
		
	}
	
	public static void main(String[] args) {
		int choice;
		BikeRentingApplication bikeapp = new BikeRentingApplication();
		Scanner scanner = new Scanner(System.in);
		logger.info("Menu\n 1. View all bikes\n 2. View available bikes\n 3. Rent available bike\n 4. Return a rented bike\n"
				+ " Enter your choice : ");
		choice = scanner.nextInt();
		switch(choice) {
		case 1 : bikeapp.viewAllBikes();
				break;
		case 2 : bikeapp.viewAvailableBikes();
				break;
		case 3 : bikeapp.rentBike();
				break;
		case 4 : bikeapp.returnBike();
				break;
		default : logger.warning("Please enter a valid choice");
				break;
		}
		

	}

}
