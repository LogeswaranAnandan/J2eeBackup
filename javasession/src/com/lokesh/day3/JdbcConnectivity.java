package com.lokesh.day3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Logger;

public class JdbcConnectivity {
	static Logger logger = Logger.getLogger(JdbcConnectivity.class.getName());
	public static void main(String[] args) {
		final String URL = "jdbc:mysql://localhost:3306/jdbcconnectivity";
		final String username = "root";
		final String password = "zilkeradmin";
		String query = "select * from contacttable";
		try {
		Class.forName("com.mysql.jdbc.Driver");
		Connection connection = DriverManager.getConnection(URL, username, password);
		Statement statement = connection.createStatement();
		ResultSet resultset = statement.executeQuery(query);
		while(resultset.next()) {
			System.out.println("Element is retrieved");
		}
		
		} catch(Exception e) {
			System.out.println("In Exception block");
			e.printStackTrace();
		}
	}
}
