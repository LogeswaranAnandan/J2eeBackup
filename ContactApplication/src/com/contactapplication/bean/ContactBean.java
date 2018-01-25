package com.contactapplication.bean;

import java.util.Scanner;
import java.util.logging.Logger;
import com.contactapplication.utility.DataValidation;

public class ContactBean {
	Logger logger = Logger.getLogger(ContactBean.class.getName());
	Scanner scanner = new Scanner(System.in);
	DataValidation validation = new DataValidation();
	private long contactId;
	private String firstname = "";
	private String lastname = "";
	private String emailId = "";
	private String extension = null;
	private String areacode = null;
	private String countrycode = null;
	private long phonenumber;
	private String phonetype = "";

	public long getContactId() {
		return contactId;
	}

	public void setContactId(long contactId) {
		this.contactId = contactId;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}
	
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		if (validation.validateEmailId(emailId)) {
			this.emailId = emailId;
		} else {
			logger.info("Invalid email id. Please try again");
			logger.info("Enter a valid email Id : ");
			this.setEmailId(scanner.nextLine());
		}
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public String getAreacode() {
		return areacode;
	}

	public void setAreacode(String areacode) {
		this.areacode = areacode;
	}

	public String getCountrycode() {
		return countrycode;
	}

	public void setCountrycode(String countrycode) {
		this.countrycode = countrycode;
	}

	public long getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(long phonenumber) {
		this.phonenumber = phonenumber;
	}

	public String getPhonetype() {
		return phonetype;
	}

	public void setPhonetype(String phonetype) {
		this.phonetype = phonetype;
	}

	public String toString() {
		String temp = "\nFirst Name : " + this.getFirstname() + "\nLast Name : " + this.getLastname() + "\nE-mail Id : "
				+ this.getEmailId() + "\nType of phone number : " + this.getPhonetype();
		switch (this.getPhonetype()) {
		case "office":
			temp += "\nExtension code : " + this.getExtension();
			break;
		case "mobile":
			temp += "\nCountry code : " + this.getCountrycode();
			break;
		case "home":
			temp += "\nArea code : " + this.getAreacode() + "\nCountry code : " + this.getCountrycode();
			break;
		}
		temp += "\nPhone number : " + this.getPhonenumber();
		return temp;
	}

}
