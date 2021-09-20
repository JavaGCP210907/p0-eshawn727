package com.revature.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
	
	public static Connection getConnection() throws SQLException {
		// For compatibility with other frameworks/technologies, well need to register our PostgreSQL driver
		// this process makes the application aware of what driver class is being used
		try {
			Class.forName("org.postgresql.Driver"); // searching for the postgres driver in dependency
		} catch (ClassNotFoundException e) {
			e.printStackTrace(); // gives information in console on errors
		}
		
		/* Need to use database credentials to establish database connection...Hard coded for now which is very bad (not good practice)
		// Will put credentials in Strings, and use them in a method that gets connections
		String url = "jdbc:postgresql://localhost:5432/postgres?currentSchema=projectdb"; //need to provide values for host, port, database //get from Dbeaver
		String username = "postgres";
		String password = "password"; // change db url and add protection with enviroment variables */
		
		String url = System.getenv("URL");
		String username = System.getenv("USERNAME");
		String password = System.getenv("PASSWORD");
		
		//this return statement is what returns the actual database Connection object
		return DriverManager.getConnection(url, username, password);
		
	}

}
