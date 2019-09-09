package com.systemuser;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/user-signup.pcat")
public class SignUpNewSystemUser extends HttpServlet{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;



	@Override
	protected void doGet(HttpServletRequest requestVariable, HttpServletResponse responseVariable)
			throws ServletException, IOException {
		requestVariable.getRequestDispatcher("/WEB-INF/views/user-signup.jsp").forward(requestVariable,
				responseVariable);
	}

	@Override
	protected void doPost(HttpServletRequest requestVariable, HttpServletResponse responseVariable)
			throws ServletException, IOException {
		
		String userName = requestVariable.getParameter("userName");
		String userPassword = requestVariable.getParameter("userPassword");
		String userRealName = requestVariable.getParameter("userRealName");

		// after having the necessary information, verify that user does not exist
		// if user does not exist, then allow signup, other wise prompt user to change
		// their name or password
		
		

		UserAuthenticationService userAuthenticator = new UserAuthenticationService();
		NewSystemUser newsystemuser = new NewSystemUser(userRealName, userName, userPassword);

		boolean userISSignedUp = false;
		//check whether or not the user is already Signed up
		
		if(!userAuthenticator.userExists(newsystemuser))//user doesn't not exist
			userISSignedUp = userAuthenticator.signupUser(newsystemuser);//then sign them up
		
		
		if (userISSignedUp) {// if the user is successfully signed up
			System.out.println("signed up");
			boolean userExists = userAuthenticator.userExists(new CurrentSystemUser(userName, userPassword));//then log them in
			if (userExists) {
				requestVariable.getSession().setAttribute("userName", userName);
				responseVariable.sendRedirect("/view-exp-catalog.pcat");
			} else {// if the user does not exist, keep user in view-catalog
				responseVariable.sendRedirect("/view-catalog.pcat");
			}

			System.out.println("Newly signed up user has been signed in");
		} else {// send them back to sign up, if they were not successfully signed up

			responseVariable.sendRedirect("/user-signup.pcat");
		}
	}

}
