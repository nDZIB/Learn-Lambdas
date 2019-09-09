package com.catalog;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.category.Category;
import com.category.ServiceCategories;
import com.product.Product;

@WebServlet(urlPatterns = "/view-expcategory-product.pcat")
public class ViewExpCategoryProduct extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest requestVariable, HttpServletResponse responseVariable) 
		throws ServletException, IOException {
		
		String categoryName = requestVariable.getParameter("categoryName");
		String categoryDescription = requestVariable.getParameter("categoryDescription");
		
		Category category = new Category(categoryName, categoryDescription);
		int userID = (int)requestVariable.getSession().getAttribute("userID");
		int catID = ServiceCategories.retrieveIDFor(category).retrieve();
		
		List<Product> myProducts = GetProducts.myProducts(userID).getThoseInCategory(catID);
		List<Product> otherProducts = GetProducts.otherProducts(userID).getThoseInCategory(catID);
		System.out.println(otherProducts.size());
		
		requestVariable.setAttribute("category", category);
		requestVariable.setAttribute("myProducts", myProducts);
		requestVariable.setAttribute("otherProducts", otherProducts);
		requestVariable.getRequestDispatcher("/WEB-INF/views/view-expcategory-product.jsp").forward(requestVariable, responseVariable);
	}
}
