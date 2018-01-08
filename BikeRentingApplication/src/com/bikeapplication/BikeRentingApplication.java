package com.bikeapplication;

import java.util.Scanner;
import java.util.logging.Logger;

public class BikeRentingApplication {
	public static void main(String[] args) {
		BikeRentingMethods bikerentingmethod = new BikeRentingMethods();
		Scanner scanner = new Scanner(System.in);
		Logger logger = Logger.getLogger(BikeRentingMethods.class.getName());
		logger.info("Choose your role\n 1.User\n 2.Admin\n Enter your choice : ");
		switch (Integer.parseInt(scanner.nextLine())) {
		case 1:
			logger.info("\n 1.Existing user\n 2.New User\n Enter your choice : ");
			switch (Integer.parseInt(scanner.nextLine())) {
			case 1:
				bikerentingmethod.userLogin();
				break;
			case 2:
				bikerentingmethod.userSignup();
				break;
			default:
				logger.warning("Enter a valid choice");
				break;
			}
			break;
		case 2:
			bikerentingmethod.adminLogin();
			break;
		default:
			logger.warning("Please choose a valid role");
		}
		scanner.close();
	}
}
