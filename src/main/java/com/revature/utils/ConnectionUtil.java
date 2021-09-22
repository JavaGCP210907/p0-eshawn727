package com.revature.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
	
	public static Connection getConnection() throws SQLException {
		
		try {
			Class.forName("org.postgresql.Driver"); // search for the postgres driver in dependency
		} catch (ClassNotFoundException e) {
			e.printStackTrace(); 
		}
		
		/*
		String url = "jdbc:postgresql://localhost:5432/postgres?currentSchema=projectdb";
		String username = "postgres";
		String password = "password";
		*/
	
		// use environment variables to protect sensitive credentials to access database
		String url = System.getenv("URL");
		String username = System.getenv("USERNAME");
		String password = System.getenv("PASSWORD");
	
		
		//this return statement is what returns the actual database Connection object
		return DriverManager.getConnection(url, username, password);		
	}

}
