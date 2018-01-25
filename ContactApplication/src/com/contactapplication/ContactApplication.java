package com.contactapplication;

import java.util.Scanner;
import java.util.logging.Logger;

import com.contactapplication.constants.Constants;

public class ContactApplication {
	public static void main(String[] args) {
		Logger logger = Logger.getLogger(ContactApplication.class.getName());
		Scanner scanner = new Scanner(System.in);
		ContactApplicationMethods contact = new ContactApplicationMethods();
		try {
		do {
			logger.info(Constants.MAIN_MENU);
			int choice = Integer.parseInt(scanner.nextLine());
			switch (choice) {
			case 1:
				contact.getNumber();
				break;
			case 2:
				contact.insertData();
				break;
			case 3:
				logger.info("Exiting.....Done");
				scanner.close();
				System.exit(0);
				break;
			default:
				logger.warning(Constants.INVALID_CHOICE);
			}
		} while (true);
		} catch(Exception e) {
			logger.warning(Constants.INVALID_INPUT);
		}
	}
}
