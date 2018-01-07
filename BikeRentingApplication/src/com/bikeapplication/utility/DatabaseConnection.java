package com.bikeapplication.utility;

import com.bikeapplication.constants.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

public class DatabaseConnection {
	static Logger logger = Logger.getLogger(DatabaseConnection.class.getName());
	public Connection getConnection() {
		try {
		Class.forName(Constants.JDBC_DRIVER);
		} catch (ClassNotFoundException e) {
			logger.warning("Problem in the jdbc driver");
		}
		try {
		Connection connection = DriverManager.getConnection(Constants.URL, Constants.USERNAME, Constants.PASSWORD);
		return connection;
		} catch (SQLException e) {
			logger.warning("Problem while creating a connection");			
		}
		return null;
	}

}
