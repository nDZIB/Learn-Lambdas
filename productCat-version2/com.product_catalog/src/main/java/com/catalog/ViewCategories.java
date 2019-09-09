package com.catalog;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.category.ServiceCategories;

@WebServlet(urlPatterns = "/view-categories.pcat")
public class ViewCategories extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest requestVariable, HttpServletResponse responseVariable)
			throws ServletException, IOException {

		// get all the categories and set them as a request attribute
		requestVariable.setAttribute("category", ServiceCategories.allCategories().get());
		// forward the request to the view
		requestVariable.getRequestDispatcher("/WEB-INF/views/view-categories.jsp").forward(requestVariable,
				responseVariable);
	}

}
