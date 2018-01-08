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
import com.bikeapplication.utility.BikeRentCalculator;
import com.bikeapplication.utility.DatabaseConnection;

public class BikeApplicationDao {
	DatabaseConnection dbconnection = new DatabaseConnection();
	Logger logger = Logger.getLogger(BikeApplicationDao.class.getName());
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
			closeConnection(null,statement,connection);
			return rowsaffected;
		} catch (SQLException e) {
			logger.warning("Problem while inserting the values");
		}
		return 0;
	}

	// Method to close a connection after user exit
	public void closeConnection(ResultSet result, Statement statement, Connection connection) {
		dbconnection.closeConnection(result, statement, connection);
	}

	// Method to fetch customer name using customer id
	public String getUserName(int userid) {
		String query = "select username from userdetails where userid = " + userid;
		ResultSet result = selectQueryExecutor(query);
		try {
			if (result.next()) {
				return result.getString(1);
			}
		} catch (SQLException e) {
			logger.warning("Problem while retrieving user name");
		} finally {
			closeConnection(result, statement, connection);
		}
		return null;
	}

	// Method to set availability of the bike
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
		} finally {
			closeConnection(result, statement, connection);
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
		} finally {
			closeConnection(result, statement, connection);
		}
		return null;
	}

	// Method to rent a bike and insert the value into rentdetails table
	public void rentBike(RentBeanClass rentbean) {
		String query = "insert into rentdetails values(" + Constants.USERID + "," + rentbean.getBikeid()
				+ ",current_timestamp(), " + rentbean.getDuration() + "," + rentbean.getDuration()
				+ " * (select charge from bikedetails where bikeid = " + rentbean.getBikeid() + ")" + ","
				+ rentbean.getAdvancepaid() + ")";
		int rowsaffected = queryExecutor(query);
		if (rowsaffected > 0) {
			logger.info("Bike is rented successfully");
		} else {
			logger.warning("Please enter valid credentials and try again");
			return;
		}
		setAvailability(Constants.UNAVAILABLE, rentbean.getBikeid());
	}

	public void userRentedBike(int userid) {
		String query = "select * from bikedetails where bikeid in (select bikeid from rentdetails where userid = "
				+ userid + ")";
		ResultSet result = selectQueryExecutor(query);
		try {
			while (result.next()) {
				logger.info("\nBike ID : " + result.getInt(1) + "\nManufacturer : " + result.getString(2)
						+ "\nBike Name : " + result.getString(3));
			}
		} catch (SQLException e) {
			logger.warning("Problem while displaying the bikes rented by this user");
		} finally {
			closeConnection(result, statement, connection);
		}
	}

	public void returnBike(int bikeid) {
		String query = "Select * from rentdetails where userid = " + Constants.USERID + " AND bikeid = " + bikeid;
		RentBeanClass rentbean = new RentBeanClass();
		BikeRentCalculator rentCalculator = new BikeRentCalculator();
		ResultSet result = selectQueryExecutor(query);
		try {
			if(result.next()) {
				rentbean.setUserid(result.getInt(1));
				rentbean.setBikeid(result.getInt(2));
				rentbean.setRented_datetime(result.getString(3));
				rentbean.setDuration(result.getInt(4));
				rentbean.setEstimatedamount(result.getInt(5));
				rentbean.setAdvancepaid(result.getInt(6));
			}
		} catch (SQLException e) {
			logger.warning("Problem while returning the bike");
		}
		rentCalculator.CalculateAmount(rentbean, getBikeDetails(bikeid).getCharge());
		deleteFromRent(bikeid);
		
	}
	
	public void deleteFromRent(int bikeid) {
		String query = "Delete from rentdetails where userid = " + Constants.USERID + " AND bikeid = " + bikeid;
		int rowsaffected = queryExecutor(query);
		if (rowsaffected > 0) {
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
			query = "select userid from userdetails where phonenumber = " + phonenumber + " AND userpassword = '"
					+ password + "' AND userrole = 'Customer';";
		} catch (Exception e) {
			query = "select userid from userdetails where username = '" + username + "' AND userpassword = '" + password
					+ "' AND userrole = 'Customer';";
		} 
		ResultSet result = selectQueryExecutor(query);
		try {
			if (result.next()) {
				int userid = result.getInt(1);
				Constants.setUSERID(userid);
				return true;
			} else {
				logger.warning("Incorrect username and password. Please try again");
				return false;
			}
		} catch (SQLException e) {
			logger.warning("Please enter a valid username and password");
		} finally {
			closeConnection(result, statement, connection);
		}
		return false;
	}

	public boolean userSignup(UserBeanClass userbean) {
		String query = "insert into userdetails values(" + (System.currentTimeMillis() % 1000000) + ",'"
				+ userbean.getUsername() + "'," + (System.currentTimeMillis() % 100000) + "," + userbean.getPhonenumber() + ",'"
				+ userbean.getLicenseno() + "','" + userbean.getUserpassword() + "','Customer')";
		int rowsaffected = queryExecutor(query);
		if (rowsaffected > 0) {
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
				rentbean.setRented_datetime(result.getString(3));
				rentbean.setDuration(result.getInt(4));
				rentbean.setEstimatedamount(result.getInt(5));
				rentbean.setAdvancepaid(result.getInt(6));
				rentbeanlist.add(rentbean);
			}
			return rentbeanlist;
		} catch (SQLException e) {
			logger.warning("Problem while displaying the bikes");
		} finally {
			closeConnection(result, statement, connection);
		}
		return null;
	}
	
	public BikeBeanClass getBikeDetails(int bikeid) {
		BikeBeanClass bikebean = new BikeBeanClass();
		String query = "select * from bikedetails where bikeid = " + bikeid;
		ResultSet result = selectQueryExecutor(query);
		try {
			if(result.next()) {
				bikebean.setBikeid(result.getInt(1));
				bikebean.setManufacturer(result.getString(2));
				bikebean.setBikename(result.getString(3));
				bikebean.setCharge(result.getInt(4));
				bikebean.setRegno(result.getString(5));
				bikebean.setAvailability(result.getString(6));
				return bikebean;
			}
		} catch (SQLException e) {
			logger.warning("Problem occurred while fetching the bike details");
		} finally {
			closeConnection(result, statement, connection);
		}
		return null;
	}

	public boolean addNewBike(BikeBeanClass bikebean) {
		String query = "insert into bikedetails values(" + bikebean.getBikeid() + ",'" + bikebean.getManufacturer()
				+ "','" + bikebean.getBikename() + "'," + bikebean.getCharge() + ",'" + bikebean.getRegno()
				+ "','Available')";
		int rowsaffected = queryExecutor(query);
		if (rowsaffected > 0) {
			return true;
		}
		return false;
	}

	public boolean adminLogin(String username, String password) {
		String query = "";
		try {
			long phonenumber = Long.parseLong(username);
			query = "select userid from userdetails where phonenumber = " + phonenumber + " AND userpassword = '"
					+ password + "' AND userrole = 'Admin';";
		} catch (Exception e) {
			query = "select userid from userdetails where username = '" + username + "' AND userpassword = '" + password
					+ "' AND userrole = 'Admin';";
		} 
		ResultSet result = selectQueryExecutor(query);
		try {
			if (result.next()) {
				return true;
			}
		} catch (SQLException e) {
			logger.info("Problem while validating the admin");
		} finally {
			closeConnection(result, statement, connection);
		}
		return false;
	}
	
	public List<UserBeanClass> viewAllUsers() {
		String query = Constants.VIEW_USER_DETAILS;
		ResultSet result = selectQueryExecutor(query);
		List<UserBeanClass> userbeanlist = new ArrayList<>();
		try {
			while (result.next()) {
				UserBeanClass userbean = new UserBeanClass();
				userbean.setUserid(result.getInt(1));
				userbean.setUsername(result.getString(2));
				userbean.setAddressid(result.getInt(3));
				userbean.setPhonenumber(result.getLong(4));
				userbean.setLicenseno(result.getString(5));
				userbean.setStreet(result.getString(9));
				userbean.setArea(result.getString(10));
				userbean.setCity(result.getString(11));
				userbean.setPincode(result.getInt(12));
				userbeanlist.add(userbean);
				}
			return userbeanlist;
		} catch (SQLException e) {
			logger.warning("Problem while displaying the bikes");
		} finally {
			closeConnection(result, statement, connection);
		}
		return null;
		
	}

}
