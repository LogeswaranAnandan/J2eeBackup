package com.bikeapplication.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

import com.bikeapplication.bean.BikeBeanClass;
import com.bikeapplication.constants.Constants;
import com.bikeapplication.utility.DatabaseConnection;

public class BikeApplicationDao {
	static DatabaseConnection dbconnection = new DatabaseConnection();
	static Logger logger = Logger.getLogger(BikeApplicationDao.class.getName());

	// Executes a query a returns a ResultSet
	public ResultSet queryExecutor(String query) {
		try {
			Connection connection = dbconnection.getConnection();
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(query);
			return result;

		} catch (Exception e) {
			logger.warning("Exception in accessing the database");
		}
		return null;
	}

	// Method to display all bikes in bikedetails table
	public void viewAllBike() {
		String query = Constants.ALL_BIKES;
		ResultSet result = queryExecutor(query);
		try {
			while (result.next()) {
				logger.info("\nBike ID : " + result.getInt(1) + "\nManufacturer : " + result.getString(2)
						+ "\nBike Name : " + result.getString(3) + "\nCharge per hour : " + result.getInt(4)
						+ "\nAvailability : " + result.getString(6));
			}
		} catch (SQLException e) {
			logger.warning("Exception in receiving the reslutset method in view all bikes method");
		}
	}

	// method to display only the available bikes in bikedetails table
	public void viewAvailableBikes() {
		String query = Constants.AVAILABLE_BIKES;
		ResultSet result = queryExecutor(query);
		try {
			while (result.next()) {
				logger.info("\nBike ID : " + result.getInt(1) + "\nManufacturer : " + result.getString(2)
						+ "\nBike Name : " + result.getString(3) + "\nCharge per hour : " + result.getInt(4));
			}
		} catch (SQLException e) {
			logger.warning("Exception in receiving the reslutset method in view available bikes method");
		}
	}

}
