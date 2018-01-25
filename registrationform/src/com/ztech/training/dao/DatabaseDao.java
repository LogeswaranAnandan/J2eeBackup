package com.ztech.training.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

import com.ztech.training.beanpackage.BeanClass;
import com.ztech.training.constants.Constants;
import com.ztech.training.util.DatabaseConnectivity;

public class DatabaseDao {
	Logger logger = Logger.getLogger(DatabaseDao.class.getName());
	Connection connection = null;
	PreparedStatement statement = null;
	ResultSet result = null;
	DatabaseConnectivity databaseconnectivity = new DatabaseConnectivity();
	
	public int insertData(BeanClass bean) {
		String query = Constants.INSERT_DATA;
		int rowsAffected = 0;
		try {
		connection = databaseconnectivity.getConnection();
		statement = connection.prepareStatement(query);
		statement.setInt(1, bean.getRollNumber());
		statement.setString(2, bean.getFirstName());
		statement.setString(3, bean.getLastName());
		statement.setString(4, bean.getBranch());
		statement.setString(5, bean.getSection());
		statement.setLong(6, bean.getPhoneNumber());
		rowsAffected = statement.executeUpdate();
		if(rowsAffected > 0) {
			logger.info("data is inserted successfully");
		}
		} catch (SQLException e) {
			logger.warning("Problem occured while inserting the data");
		} finally {
			databaseconnectivity.closeConnection(result, statement, connection);
		}
		return rowsAffected;
		
		
	}
}
