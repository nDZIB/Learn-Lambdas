package com.systemuser;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet (urlPatterns ="/logout.pcat")
public class LogCurrentSystemUserOut extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest requestVariable, HttpServletResponse responseVariable) 
		throws ServletException, IOException {
		
		//invalidate the current session
		requestVariable.getSession().invalidate();
		//redirect
		responseVariable.sendRedirect("/view-catalog.pcat");
	}

}
