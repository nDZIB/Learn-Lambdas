package com.catalog;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.connection.ConnectionManager;
import com.product.Product;




@WebServlet(urlPatterns = "/view-exp-catalog.pcat")
public class ViewExpCatalog extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest requestVariable, HttpServletResponse responseVariable) 
		throws ServletException, IOException {
		/*when the user visits the landing page, access is created to the database and the
		the product catalog is displayed*/
		
		
		CatalogManagementService catalogMService  = new CatalogManagementService();
		//get connection to the product catalog database
		//Connection databaseConnection = (Connection)requestVariable.getSession().getAttribute("dbconnection");
		
		Connection databaseConnection = new ConnectionManager().createConnection();
		int userID = (int)requestVariable.getSession().getAttribute("userID");
		if(databaseConnection!= null) {
			//if the connection was successful
			//execute a query to get the list of all products
			List<Product> otherProducts = catalogMService.getProducts(userID);
			List <Product> myProducts = catalogMService.getOtherProducts(userID);
			
			System.out.println(myProducts.size());
			if(myProducts.size() != 0) {
				//put the list in request scope
				requestVariable.setAttribute("myProducts", myProducts);
				requestVariable.setAttribute("otheroducts", otherProducts);
				
				//forward the request to the view(view-catalog)
				requestVariable.getRequestDispatcher("/WEB-INF/views/view-catalog.jsp").forward(requestVariable, responseVariable);	
			} else {//if there are no products to display
				System.out.println("No products to display");
			}
		} else {//else if the connection to the database was unsuccessful
			System.out.println("Unable to connect to database");
		}
		
	}
	
	
	@Override
	protected void doGet(HttpServletRequest requestVariable, HttpServletResponse responseVariable) 
		throws ServletException, IOException {
	
			//get the current user's id from session
			int userID = (int)requestVariable.getSession().getAttribute("userID");
			
			List<Product> myProducts = GetProducts.getProductsFor(userID).get();
			List<Product> otherProducts = GetProducts.getProducts().ExceptProductsFor(userID).get();//ViewProducts.getAllUserProductButFor(userID).view();
				//put the lists in request scope
				requestVariable.setAttribute("myProducts", myProducts);
				requestVariable.setAttribute("otherProducts", otherProducts);
				
				//forward the request to the view(view-catalog)
				requestVariable.getRequestDispatcher("/WEB-INF/views/view-exp-catalog.jsp").forward(requestVariable, responseVariable);	
	}
}
