package com.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

	private Connection databaseConnection;
	
	
	//setter
	public Connection createConnection() {
		
		/*this method creates a connection to the database and returns the connection if
		 * successful, otherwise, it returns null*/
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		
		//setting the location and access privileges for the database handling storage
		//for the user shopVisitor, password is hopVisito
		String url = "jdbc:mysql://localhost:3306/new_product_catalog";
	    String user = "shopVisitor";
	    String password = "hopVisito";
	    
		try {
			databaseConnection =(Connection) DriverManager.getConnection(url, user, password);
			System.out.println("Connected to database");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return databaseConnection;
	}
	
	//getter
	public Connection getConnection() {
		return databaseConnection;
	}
}
