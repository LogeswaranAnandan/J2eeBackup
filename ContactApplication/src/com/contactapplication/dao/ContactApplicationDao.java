package com.contactapplication.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.contactapplication.bean.ContactBean;
import com.contactapplication.constants.Constants;
import com.contactapplication.utility.DatabaseConnection;

public class ContactApplicationDao {
	Logger logger = Logger.getLogger(ContactApplicationDao.class.getName());
	DatabaseConnection dbconnection = new DatabaseConnection();
	Connection connection = null;
	PreparedStatement statement = null;
	ResultSet result = null;

	public PreparedStatement openConnection(String query) throws SQLException {
		connection = dbconnection.getConnection();
		statement = connection.prepareStatement(query);
		return statement;
	}

	public int getNumberId(String phonetype) {
		switch (phonetype) {
		case "office":
			return 1;
		case "mobile":
			return 2;
		case "home":
			return 3;
		}
		return 0;
	}

	public String getPhonetype(int numberId) {
		switch (numberId) {
		case 1:
			return "office";
		case 2:
			return "mobile";
		case 3:
			return "home";
		}
		return null;
	}

	public List<ContactBean> getNumber(ContactBean bean) {
		List<ContactBean> beanList = new ArrayList<>();
		try {
			String query = Constants.GET_NUMBER;
			statement = openConnection(query);
			statement.setString(1, bean.getFirstname());
			statement.setString(2, bean.getLastname());
			logger.info("Statement : " + statement);
			result = statement.executeQuery();
			while (result.next()) {
				ContactBean temp = new ContactBean();
				temp.setContactId(result.getInt(1));
				temp.setFirstname(result.getString(2));
				temp.setLastname(result.getString(3));
				temp.setEmailId(result.getString(4));
				temp.setPhonetype(getPhonetype(result.getInt(6)));
				temp.setExtension(result.getString(7));
				temp.setAreacode(result.getString(8));
				temp.setCountrycode(result.getString(9));
				temp.setPhonenumber(result.getLong(10));
				beanList.add(temp);
			}
			return beanList;
		} catch (SQLException e) {
			logger.warning("Problem while accessing database");
		} finally {
			dbconnection.closeConnection(result, statement, connection);
		}
		return null;
	}

	public void insertData(ContactBean bean) {
		try {
			String query = Constants.INSERT_CONTACTTABLE_DATA;
			statement = openConnection(query);
			statement.setLong(1, bean.getContactId());
			statement.setString(2, bean.getFirstname());
			statement.setString(3, bean.getLastname());
			statement.setString(4, bean.getEmailId());
			statement.executeUpdate();
		} catch (SQLException e) {
			logger.warning("Problem while inserting data in contact table. Please check the data to be inserted.");
		}
		try {
			String query = Constants.INSERT_NUMBERTABLE_DATA;
			statement = openConnection(query);
			statement.setLong(1, bean.getContactId());
			statement.setInt(2, getNumberId(bean.getPhonetype()));
			statement.setString(3, bean.getExtension());
			statement.setString(4, bean.getAreacode());
			statement.setString(5, bean.getCountrycode());
			statement.setLong(6, bean.getPhonenumber());
			int rowsaffected = statement.executeUpdate();
			if (rowsaffected > 0) {
				logger.info("Value is inserted successfully");
			}
		} catch (SQLException e) {
			logger.warning("Problem while inserting data in number table. Please check the data to be inserted.");
		}
	}
}
