package com.systemuser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.connection.ConnectionManager;

public class UserAuthenticationService {

	//method to return a given user's id
	public int getUserID(CurrentSystemUser thisUser)  {
		Connection dbconnection = new ConnectionManager().createConnection();
		try {
			PreparedStatement pst = dbconnection
					.prepareStatement("SELECT userID FROM systemuser " + "WHERE userName = ? AND userPassword = ?");
			pst.setString(1, thisUser.getUserName());
			pst.setString(2, thisUser.getUserPassword());

			ResultSet rs = pst.executeQuery();

			if (rs.next())
				return rs.getInt(1);
			dbconnection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	
	//verify if a given user exists
	public boolean userExists(CurrentSystemUser currentUser) {
		if(this.getUserID(currentUser) == 0)
			return false;
		return true;
	}

	// method to insert a new user
	public boolean signupUser(NewSystemUser systemuser) {
		Connection dbconnection = new ConnectionManager().createConnection();
		// if the current user does not exist, then add them
		try {
			PreparedStatement pst = dbconnection.prepareStatement(
					"INSERT IGNORE INTO systemuser (userRealName, userName, " + "userPassword) VALUES(?,?,?)");

			pst.setString(1, systemuser.getUserRealName());
			pst.setString(2, systemuser.getUserName());
			pst.setString(3, systemuser.getUserPassword());

			pst.executeUpdate();
			dbconnection.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;// return false if new user cannot be signed in
		}
		return true;// return value if a user is successfully signed up
	}
}
