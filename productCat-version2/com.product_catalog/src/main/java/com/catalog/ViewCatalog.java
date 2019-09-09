package com.catalog;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.product.Product;

@WebServlet(urlPatterns = "/view-catalog.pcat")
public class ViewCatalog extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest requestVariable, HttpServletResponse responseVariable)
			throws ServletException, IOException {
		/*
		 * when the user visits the landing page, access is created to the database and
		 * the product catalog is displayed
		 */
		/* the user can view all the products they've created */

		List<Product> products = new ArrayList<Product>();// list to hold all available products

		products = GetProducts.getProducts().get();//get the list of products
		if (products.size() != 0) {
			// put the list in request scope
			requestVariable.setAttribute("products", products);
			// forward the request to the view(view-catalog)
			requestVariable.getRequestDispatcher("/WEB-INF/views/view-catalog.jsp").forward(requestVariable,
					responseVariable);
		} else {// if there are no products to display
			System.out.println("No products to display");
		}
	}
}
