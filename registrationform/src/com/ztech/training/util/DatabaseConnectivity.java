package com.ztech.training.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

import com.ztech.training.constants.Constants;

public class DatabaseConnectivity {
	Logger logger = Logger.getLogger(DatabaseConnectivity.class.getName());

	public Connection getConnection() {
		try {
			Class.forName(Constants.JDBC_URL);
			Connection connection = DriverManager.getConnection(Constants.URL, Constants.USERNAME, Constants.PASSWORD);
			return connection;
		} catch (SQLException | ClassNotFoundException e) {
			logger.warning("Problem in getting connection");
		}
		return null;
	}

	public void closeConnection(ResultSet result, Statement statement, Connection connection) {
		try {
			if (result != null) {
				result.close();
			}
			if (statement != null) {
				statement.close();
			}
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			logger.warning("Error occurred while closing the connection");
		}
	}
}
