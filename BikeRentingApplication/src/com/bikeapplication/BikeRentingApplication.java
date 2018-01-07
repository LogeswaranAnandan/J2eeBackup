package com.bikeapplication;

import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

import com.bikeapplication.bean.BikeBeanClass;
import com.bikeapplication.bean.RentBeanClass;
import com.bikeapplication.bean.UserBeanClass;
import com.bikeapplication.constants.Constants;
import com.bikeapplication.dao.BikeApplicationDao;

public class BikeRentingApplication {
	static Logger logger = Logger.getLogger(BikeRentingApplication.class.getName());
	static BikeApplicationDao dao = new BikeApplicationDao();
	static BikeRentingApplication bikeapp = new BikeRentingApplication();
	static Scanner scanner = new Scanner(System.in);

	public void viewAllBikes() {
		List<BikeBeanClass> bikebean = dao.viewAllBike();
		for (BikeBeanClass temp : bikebean) {
			logger.info(temp.toString());
		}
	}

	public void viewAvailableBikes() {
		List<BikeBeanClass> bikebean = dao.viewAvailableBikes();
		for (BikeBeanClass temp : bikebean) {
			logger.info(temp.toString());
		}
		logger.info("Do you want to rent a bike?(y/n)");
		if (scanner.next() == "y") {
			rentBike();
		}

	}

	public void rentBike() {
		RentBeanClass rentbean = new RentBeanClass();
		logger.info("Enter the bike id : ");
		rentbean.setBikeid(scanner.nextInt());
		logger.info("Enter the Duration of renting(in hours) : ");
		rentbean.setDuration(scanner.nextInt());
		logger.info("Enter the advance payment amount : ");
		rentbean.setAdvancepaid(scanner.nextInt());
		dao.rentBike(rentbean);
	}

	public void viewAndRentBike() {
		List<BikeBeanClass> bikebean = dao.viewAvailableBikes();
		for (BikeBeanClass temp : bikebean) {
			logger.info(temp.toString());
		}
		rentBike();
	}

	public void returnBike() {
		int bikeid;
		dao.userRentedBike(Constants.USERID);
		logger.info("Enter the bikeid to be returned : ");
		bikeid = scanner.nextInt();
		dao.returnBike(bikeid);
	}

	public void userMenu() {
		do {
			logger.info(
					"Menu\n 1. View all bikes\n 2. View available bikes\n 3. Rent available bike\n 4. Return a rented bike\n 5. Exit\n"
							+ " Enter your choice : ");
			switch (scanner.nextInt()) {
			case 1:
				bikeapp.viewAllBikes();
				break;
			case 2:
				bikeapp.viewAvailableBikes();
				break;
			case 3:
				bikeapp.viewAndRentBike();
				break;
			case 4:
				bikeapp.returnBike();
				break;
			case 5:
				logger.info("Thank you, " + dao.getUserName(Constants.USERID));
				dao.closeConnection();
				System.exit(0);
				break;
			default:
				logger.warning("Please enter a valid choice");
				break;
			}
		} while (true);

	}

	public void userLogin() {
		String username = "";
		String password = "";
		boolean success = false;
		logger.info("Enter your username or phonenumber");
		username = scanner.nextLine();
		logger.info("Enter the password:");
		password = scanner.nextLine();
		success = dao.userLogin(username, password);
		if (success) {
			logger.info("Welcome, " + dao.getUserName(Constants.getUSERID()));
			bikeapp.userMenu();
		} else {
			userLogin();
		}
	}

	public void userSignup() {
		boolean success = false;
		UserBeanClass userbean = new UserBeanClass();
		logger.info("Enter your name : ");
		userbean.setUsername(scanner.nextLine());
		logger.info("Enter address : ");
		userbean.setAddress(scanner.nextLine());
		logger.info("Enter phone number : ");
		userbean.setPhonenumber(Long.parseLong(scanner.nextLine()));
		logger.info("Enter License number : ");
		userbean.setLicenseno(scanner.nextLine());
		logger.info("Enter user password : ");
		userbean.setUserpassword(scanner.nextLine());
		success = dao.userSignup(userbean);
		if (success) {
			logger.info("User is Registered successfully. \nPlease verify again by logging in.....");
			bikeapp.userLogin();
		} else {
			logger.info("Please try again with valid credentials");
			userSignup();
		}
	}

	///////////////////////////////// ADMIN FUNCTIONS/////////////////

	public void adminMenu() {
		do {
			logger.info(
					"\nMenu\n 1.View all Bikes\n 2.View all rented bikes\n 3.Add a new bike\n 4.Exit\n Enter you choice : ");
			switch (Integer.parseInt(scanner.nextLine())) {
			case 1:
				bikeapp.viewAllBikes();
				break;
			case 2:
				bikeapp.viewRentedBikes();
				break;
			case 3:
				bikeapp.addNewBike();
				break;
			case 4:
				logger.info("Exiting....Done");
				dao.closeConnection();
				System.exit(0);
				break;

			default:
				logger.warning("Please make a valid selection");
				break;
			}
		} while (true);
	}

	public void viewRentedBikes() {
		List<RentBeanClass> rentbeanlist = dao.viewRentedBikes();
		for (RentBeanClass temp : rentbeanlist) {
			logger.info(temp.toString());
		}
	}

	public void addNewBike() {
		boolean success = false;
		BikeBeanClass bikebean = new BikeBeanClass();
		logger.info("Enter the bike id : ");
		bikebean.setBikeid(Integer.parseInt(scanner.nextLine()));
		logger.info("Enter the manufacturer name : ");
		bikebean.setManufacturer(scanner.nextLine());
		logger.info("Enter the bike name : ");
		bikebean.setBikename(scanner.nextLine());
		logger.info("Enter the charge for renting(per hour)");
		bikebean.setCharge(Integer.parseInt(scanner.nextLine()));
		logger.info("Enter the registration number : ");
		bikebean.setRegno(scanner.nextLine());
		success = dao.addNewBike(bikebean);
		if(success) {
			logger.info("New bike is added successfully");
		} else {
			logger.warning("Please enter valid credentials");
		}
	}
	
	public void adminLogin() {
		boolean success;
		String username = "";
		String password = "";
		logger.info("Enter the username : ");
		username = scanner.nextLine();
		logger.info("Enter the password : ");
		password = scanner.nextLine();
		success = dao.adminLogin(username,password);
		if(success) {
			bikeapp.adminMenu();
		} else {
			logger.info("Invalid login. Please try again");
			adminLogin();
		}
		
	}

	public static void main(String[] args) {
		logger.info("Choose your role\n 1.User\n 2.Admin\n Enter your choice : ");
		switch (Integer.parseInt(scanner.nextLine())) {
		case 1:
			logger.info("\n 1.Existing user\n 2.New User\n Enter your choice : ");
			switch (Integer.parseInt(scanner.nextLine())) {
			case 1:
				bikeapp.userLogin();
				break;
			case 2:
				bikeapp.userSignup();
				break;
			default:
				logger.warning("Enter a valid choice");
				break;
			}
			break;
		case 2:
			bikeapp.adminLogin();
			break;
		default:
			logger.warning("Please choose a valid role");
		}
	}
}
