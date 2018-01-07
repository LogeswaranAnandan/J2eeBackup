package com.bikeapplication.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.bikeapplication.bean.BikeBeanClass;
import com.bikeapplication.bean.RentBeanClass;
import com.bikeapplication.bean.UserBeanClass;
import com.bikeapplication.constants.Constants;
import com.bikeapplication.utility.DatabaseConnection;

public class BikeApplicationDao {
	static DatabaseConnection dbconnection = new DatabaseConnection();
	static Logger logger = Logger.getLogger(BikeApplicationDao.class.getName());
	Connection connection = null;
	Statement statement = null;
	ResultSet result = null;
	
	// Executes a SELECT QUERY a returns a ResultSet
	public ResultSet selectQueryExecutor(String query) {
		try {
			connection = dbconnection.getConnection();
			statement = connection.createStatement();
			result = statement.executeQuery(query);
			return result;
			} catch (SQLException e) {
			logger.warning("Problem while accessing the database");
		}
		return result;
	}

	// Executes a INSERT or UPDATE or DELETE QUERY and returns rows affected
	public int queryExecutor(String query) {
		try {
			connection = dbconnection.getConnection();
			statement = connection.createStatement();
			int rowsaffected = statement.executeUpdate(query);
			return rowsaffected;
		} catch (SQLException e) {
			logger.warning("Problem while inserting the value");
		}
		return 0;
	}
	
	//Method to close a connection after user exit
	public void closeConnection() {
		Constants.setUSERID(0);
		dbconnection.closeConnection(result,statement,connection);
	}
	
	//Method to fetch customer name using customer id
	public String getUserName(int userid) {
		String query = "select username from userdetails where userid = " + userid;
		ResultSet result = selectQueryExecutor(query);
		try {
			if(result.next()) {
				return result.getString(1);
			}
		} catch (SQLException e) {
			logger.warning("Problem while retrieving user name");
		}
		return null;
	}
	
	//Method to set availability of the bike
	public void setAvailability(String status, int bikeid) {
		String query = "update bikedetails set availability = '" + status + "' where bikeid = " + bikeid;
		queryExecutor(query);
	}

	// Method to display all bikes in bikedetails table
	public List<BikeBeanClass> viewAllBike() {
		String query = Constants.ALL_BIKES;
		ResultSet result = selectQueryExecutor(query);
		List<BikeBeanClass> bikebeanlist = new ArrayList<>();
		try {
			while (result.next()) {
				BikeBeanClass bikebean = new BikeBeanClass();
				bikebean.setBikeid(result.getInt(1));
				bikebean.setManufacturer(result.getString(2));
				bikebean.setBikename(result.getString(3));
				bikebean.setCharge(result.getInt(4));
				bikebean.setRegno(result.getString(5));
				bikebean.setAvailability(result.getString(6));
				bikebeanlist.add(bikebean);
			}
			return bikebeanlist;
		} catch (SQLException e) {
			logger.warning("Problem while displaying the bikes");
		}
		return null;
	}

	// Method to display only the available bikes in bikedetails table
	public List<BikeBeanClass> viewAvailableBikes() {
		String query = Constants.AVAILABLE_BIKES;
		ResultSet result = selectQueryExecutor(query);
		List<BikeBeanClass> bikebeanlist = new ArrayList<>();
		try {
			while (result.next()) {
				BikeBeanClass bikebean = new BikeBeanClass();
				bikebean.setBikeid(result.getInt(1));
				bikebean.setManufacturer(result.getString(2));
				bikebean.setBikename(result.getString(3));
				bikebean.setCharge(result.getInt(4));
				bikebean.setRegno(result.getString(5));
				bikebean.setAvailability(result.getString(6));
				bikebeanlist.add(bikebean);
			}
		return bikebeanlist;
		} catch (SQLException e) {
			logger.warning("Problem while displaying the bikes");
		}
		return null;
	}
	
	//Method to rent a bike and insert the value into rentdetails table
	public void rentBike(RentBeanClass rentbean) {
		String query = "insert into rentdetails values(" + Constants.USERID + "," + rentbean.getBikeid()
				+ ",curdate(), curtime(), " + rentbean.getDuration() + ","
				+ rentbean.getDuration() + " * (select charge from bikedetails where bikeid = " + rentbean.getBikeid() + ")" + ","
				+ rentbean.getAdvancepaid() + ")";
		int rowsaffected = queryExecutor(query);
		if(rowsaffected > 0) {
			logger.info("Bike is rented successfully");
		} else {
			logger.warning("Please enter valid credentials and try again");
			return;
		}
		setAvailability(Constants.UNAVAILABLE , rentbean.getBikeid());
	}
	
	public void userRentedBike(int userid) {
		String query = "select * from bikedetails where bikeid in (select bikeid from rentdetails where userid = " + userid + ")";
		ResultSet result = selectQueryExecutor(query);
		try {
			while(result.next()) {
				logger.info("\nBike ID : " + result.getInt(1) + "\nManufacturer : " + result.getString(2)
				+ "\nBike Name : " + result.getString(3));
			}
		} catch (SQLException e) {
			logger.warning("Problem while displaying the bikes rented by this user");
		}
	}
	
	public void returnBike(int bikeid) {
		String query = "Delete from rentdetails where userid = " + Constants.USERID + " AND bikeid = " + bikeid;
		int rowsaffected = queryExecutor(query);
		if(rowsaffected > 0) {
			logger.info("Bike is returned successfully");
		} else {
			logger.warning("Please enter valid credentials and try again");
			return;
		}
		setAvailability(Constants.AVAILABLE, bikeid);
	}
	
	public boolean userLogin(String username, String password) {
		String query = "";
		try {
		long phonenumber = Long.parseLong(username);
		query = "select userid from userdetails where phonenumber = " + phonenumber + " AND userpassword = '" + password + "';";
		} catch(Exception e) {
		query = "select userid from userdetails where username = '" + username + "' AND userpassword = '" + password + "';";
		}
		logger.info(query);
		ResultSet result = selectQueryExecutor(query);
		try {
			if(result.next()) {
				int userid = result.getInt(1);
				Constants.setUSERID(userid);
				return true;
			} else {
				logger.warning("Incorrect username and password. Please try again");
				return false;
			}
		} catch (SQLException e) {
			logger.warning("Please enter a valid username and password");
		}
		return false;
	}
	
	public boolean userSignup(UserBeanClass userbean) {
		String query = "insert into userdetails values(" + (System.currentTimeMillis() % 1000) + ",'" + userbean.getUsername()
					+ "','" + userbean.getAddress() + "'," + userbean.getPhonenumber() + ",'" + userbean.getLicenseno()
					+ "','" + userbean.getUserpassword() + "')";
		int rowsaffected = queryExecutor(query);
		if(rowsaffected > 0) {
			return true;
		}
		return false;
	}
	
	public List<RentBeanClass> viewRentedBikes() {
		String query = "select * from rentdetails";
		ResultSet result = selectQueryExecutor(query);
		List<RentBeanClass> rentbeanlist = new ArrayList<>();
		try {
			while (result.next()) {
				RentBeanClass rentbean = new RentBeanClass();
				rentbean.setUserid(result.getInt(1));
				rentbean.setBikeid(result.getInt(2));
				rentbean.setRented_date(result.getString(3));
				rentbean.setRented_time(result.getString(4));
				rentbean.setDuration(result.getInt(5));
				rentbean.setEstimatedamount(result.getInt(6));
				rentbean.setAdvancepaid(result.getInt(7));
				rentbeanlist.add(rentbean);				
			}
		return rentbeanlist;
		} catch (SQLException e) {
			logger.warning("Problem while displaying the bikes");
		}
		return null;
	}
	
	public boolean addNewBike(BikeBeanClass bikebean) {
		String query = "insert into bikedetails values(" + bikebean.getBikeid() + ",'" + bikebean.getManufacturer() + "','"
				+ bikebean.getBikename() + "'," + bikebean.getCharge() + ",'" + bikebean.getRegno() + "','Available')";
		int rowsaffected = queryExecutor(query);
		if(rowsaffected > 0) {
			return true;
		}
		return false;
	}
	
	public boolean adminLogin(String username , String password) {
		String query = "select * from admintable where adminname = '" + username + "' AND adminpassword = '" + password + "';";
		ResultSet result = selectQueryExecutor(query);
		try {
			if(result.next()) {
				return true;
			}
		} catch (SQLException e) {
			logger.info("Problem while validating the admin");
		}
		return false;
	}
	

}
