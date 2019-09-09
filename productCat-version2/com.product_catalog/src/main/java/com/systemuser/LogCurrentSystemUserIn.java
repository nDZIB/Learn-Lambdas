package com.systemuser;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.connection.ConnectionManager;


@WebServlet(urlPatterns = "/login.pcat")
public class LogCurrentSystemUserIn extends HttpServlet{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest requestVariable, HttpServletResponse responseVariable) 
		throws ServletException, IOException {
		
		String userName = requestVariable.getParameter("userName");
		String userPassword = requestVariable.getParameter("userPassword");
		
		CurrentSystemUser currentSystemUser = new CurrentSystemUser(userName, userPassword);
		
		UserAuthenticationService userValidator = new UserAuthenticationService();
		
		boolean userExists = false;//assume the user does not exist
		if(currentSystemUser.isComplete()) {//if the current user has a name and password
			userExists = userValidator.userExists(currentSystemUser);// then verify that the user has signed up
		}
		
		if(userExists) {//if the current user exists, grant them access to the system
			requestVariable.getSession().setAttribute("userID", userValidator.getUserID(currentSystemUser));
			
			responseVariable.sendRedirect("/view-exp-catalog.pcat");
		} else {//if the user does not exist, keep user in view-catalog
			responseVariable.sendRedirect("/view-catalog.pcat");
		}
		
	}
	
	
	@Override
	protected void doGet(HttpServletRequest requestVariable, HttpServletResponse responseVariable) 
		throws ServletException, IOException {
		Connection dbconnection = null; //(Connection)requestVariable.getSession().getAttribute("dbconnection");
		if(dbconnection == null) {
			dbconnection = new ConnectionManager().createConnection();
			requestVariable.getSession().setAttribute("dbconnection", dbconnection);
		}
		String userName = (String)requestVariable.getAttribute("userName");
		String userPassword = (String)requestVariable.getAttribute("userPassword");
		
		CurrentSystemUser currentSystemUser = new CurrentSystemUser(userName, userPassword);
		
		System.out.println(userName);
		System.out.println(userPassword);
		UserAuthenticationService userAuthenticator = new UserAuthenticationService();
		
		boolean userIsSignedUp = false;
		if(currentSystemUser.isComplete()) //if the current user is valid, then verify if they have signed up
			userIsSignedUp = userAuthenticator.userExists(currentSystemUser);
		
		if(userIsSignedUp) {
			requestVariable.getSession().setAttribute("userName", userName);
			responseVariable.sendRedirect("/view-exp-catalog.pcat");
		} else {//if the user has not signed up for an account, keep user in view-catalog
			responseVariable.sendRedirect("/view-catalog.pcat");
		}
		
	}
	
}
