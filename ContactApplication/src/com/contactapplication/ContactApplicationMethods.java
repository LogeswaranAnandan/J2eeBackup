package com.contactapplication;

import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;
import com.contactapplication.bean.ContactBean;
import com.contactapplication.constants.Constants;
import com.contactapplication.dao.ContactApplicationDao;

public class ContactApplicationMethods {
	Logger logger = Logger.getLogger(ContactApplicationMethods.class.getName());
	Scanner scanner = new Scanner(System.in);
	ContactApplicationDao dao = new ContactApplicationDao();

	public void getNumber() {
		ContactBean bean = new ContactBean();
		logger.info("Enter the first name : ");
		bean.setFirstname(scanner.nextLine());
		logger.info("Enter the last name : ");
		bean.setLastname(scanner.nextLine());
		List<ContactBean> beanList = dao.getNumber(bean);
		if (beanList == null) {
			logger.info("Contact is not found");
			return;
		}
		do {
			logger.info(Constants.DISPLAY_OPTION);
			boolean found = false;
			int choice = Integer.parseInt(scanner.nextLine());
			switch (choice) {
			case 1:
				for (ContactBean temp : beanList) {
					if (temp.getPhonetype().equals("office")) {
						logger.info(temp.toString());
						found = true;
					}
				}
				break;
			case 2:
				for (ContactBean temp : beanList) {
					if (temp.getPhonetype().equals("mobile")) {
						logger.info(temp.toString());
						found = true;
					}
				}
				break;
			case 3:
				for (ContactBean temp : beanList) {
					if (temp.getPhonetype().equals("home")) {
						logger.info(temp.toString());
						found = true;
					}
				}
				break;
			case 4:
				for (ContactBean temp : beanList) {
					logger.info(temp.toString());
				}
				found = true;
				break;
			default:
				logger.warning(Constants.INVALID_CHOICE);
			}
			if(choice == 4) {
				break;
			}
			if (found == false) {
				logger.info(Constants.INVALID_NUMBER_CHOICE);
			} else {
				logger.info(Constants.VALID_NUMBER_CHOICE);
			}
		} while (scanner.nextLine().equals("y"));
	}

	public void insertData() {
		ContactBean bean = new ContactBean();
		logger.info("Enter the contact Details : ");
		bean.setContactId(System.currentTimeMillis() % 100000);
		logger.info("\nEnter the first name : ");
		bean.setFirstname(scanner.nextLine());
		logger.info("\nEnter the Last name : ");
		bean.setLastname(scanner.nextLine());
		logger.info("\nEnter the email id : ");
		bean.setEmailId(scanner.nextLine());
		logger.info("\nType of phone number\n 1.office\n 2.mobile \n 3.home\n Enter the type of phone number : ");
		switch (Integer.parseInt(scanner.nextLine())) {
		case 1:
			bean.setPhonetype("office");
			logger.info("\nEnter the extension code : ");
			bean.setExtension(scanner.nextLine());
			break;
		case 2:
			bean.setPhonetype("mobile");
			logger.info("\nEnter the country code : ");
			bean.setCountrycode(scanner.nextLine());
			break;
		case 3:
			bean.setPhonetype("home");
			logger.info("\nEnter the area code : ");
			bean.setAreacode(scanner.nextLine());
			logger.info("\nEnter the country code : ");
			bean.setCountrycode(scanner.nextLine());
			break;
		}
		logger.info("\nEnter the phone number : ");
		bean.setPhonenumber(Long.parseLong(scanner.nextLine()));
		dao.insertData(bean);
	}
}
