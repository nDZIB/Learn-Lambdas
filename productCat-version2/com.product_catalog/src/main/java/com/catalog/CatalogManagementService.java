package com.catalog;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.connection.ConnectionManager;
import com.product.Product;

public class CatalogManagementService {
	

	// get all the products
	public List<Product> getProducts() {
		Connection databaseConnection = new ConnectionManager().createConnection();
		List<Product> products = new ArrayList<Product>();// list to hold all available products
		try {
			PreparedStatement dbManipulate = databaseConnection
					.prepareStatement("SELECT " + "productName, productPrice, productView, productDescription, productColor, categoryName, "
							+ "categoryDescription FROM product INNER JOIN category WHERE "
							+ "product.categoryID = category.categoryID");

			// after getting the list of products, put them in request scope and forward to
			// view

			ResultSet setOfProducts = dbManipulate.executeQuery();

			// obtain list of products and add to a list
			while (setOfProducts.next()) {
				String productName = setOfProducts.getString(1);
				int productPrice = setOfProducts.getInt(2);
				byte[] productView = setOfProducts.getBytes(3);
				String productDescription = setOfProducts.getString(4);
				String productColor = setOfProducts.getString(5);
				String categoryName = setOfProducts.getString(6);
				String categoryDescription = setOfProducts.getString(7);
				// instantiate a product object
				Product product = new Product(categoryName, categoryDescription, productName, productDescription,
						productColor, productView, productPrice);

				// add to list
				products.add(product);
			}
			databaseConnection.close();
		} catch (SQLException e) {
			e.printStackTrace();// if getting the products fails print error to console
		}
		return products;
	}
	
	//method to get all the products specific to a user
	public List<Product> getProducts(int userID) {
		Connection databaseConnection = new ConnectionManager().createConnection();
		List<Product> products = new ArrayList<Product>();// list to hold all available products
		try {
			PreparedStatement dbManipulate = databaseConnection
					.prepareStatement("SELECT " + "productName, productPrice, productView, productDescription, productColor, categoryName, "
							+ "categoryDescription FROM product INNER JOIN category WHERE "
							+ "product.categoryID = category.categoryID AND  product.userID = ?");

			// after getting the list of products, put them in request scope and forward to
			// view

			dbManipulate.setInt(1, userID);
			ResultSet setOfProducts = dbManipulate.executeQuery();

			// obtain list of products and add to a list
			while (setOfProducts.next()) {
				String productName = setOfProducts.getString(1);
				int productPrice = setOfProducts.getInt(2);
				byte[] productView = setOfProducts.getBytes(3);
				String productDescription = setOfProducts.getString(4);
				String productColor = setOfProducts.getString(5);
				String categoryName = setOfProducts.getString(6);
				String categoryDescription = setOfProducts.getString(7);
				// instantiate a product object
				Product product = new Product(categoryName, categoryDescription, productName, productDescription,
						productColor, productView, productPrice);

				// add to list
				products.add(product);
			}
			databaseConnection.close();
		} catch (SQLException e) {
			e.printStackTrace();// if getting the products fails print error to console
		}
		return products;
	}
	
	//method to get all the products not added by a given user
		public List<Product> getOtherProducts(int userID) {
			Connection databaseConnection = new ConnectionManager().createConnection();
			List<Product> products = new ArrayList<Product>();// list to hold all available products
			try {
				PreparedStatement dbManipulate = databaseConnection
						.prepareStatement("SELECT " + "productName, productPrice, productView, productDescription, productColor, categoryName, "
								+ "categoryDescription FROM product INNER JOIN category WHERE "
								+ " product.userID != ? AND product.categoryID = category.categoryID");

				// after getting the list of products, put them in request scope and forward to
				// view

				dbManipulate.setInt(1, userID);
				ResultSet setOfProducts = dbManipulate.executeQuery();

				// obtain list of products and add to a list
				while (setOfProducts.next()) {
					String productName = setOfProducts.getString(1);
					int productPrice = setOfProducts.getInt(2);
					byte[] productView = setOfProducts.getBytes(3);
					String productDescription = setOfProducts.getString(4);
					String productColor = setOfProducts.getString(5);
					String categoryName = setOfProducts.getString(6);
					String categoryDescription = setOfProducts.getString(7);
					// instantiate a product object
					Product product = new Product(categoryName, categoryDescription, productName, productDescription,
							productColor, productView, productPrice);

					// add to list
					products.add(product);
				}
				databaseConnection.close();
			} catch (SQLException e) {
				e.printStackTrace();// if getting the products fails print error to console
			}
			return products;
		}
		
	
	public List<Product> getAllCategoryProducts(int catID) {;
		List<Product> products = new ArrayList<Product>();
		Connection databaseConnection = new ConnectionManager().createConnection();
		try {
			System.out.println("Category ID "+catID);
			PreparedStatement dbManipulate = databaseConnection
					.prepareStatement("SELECT " + "productName, productPrice, productView, productDescription, productColor, categoryName, "
							+ "categoryDescription FROM product INNER JOIN category WHERE "
							+ "product.categoryID = category.categoryID AND product.categoryID = ?");

			// after getting the list of products, put them in request scope and forward to
			// view
			dbManipulate.setInt(1, catID);

			ResultSet setOfProducts = dbManipulate.executeQuery();

			// obtain list of products and add to a list
			while (setOfProducts.next()) {
				String productName = setOfProducts.getString(1);
				int productPrice = setOfProducts.getInt(2);
				byte[] productView = setOfProducts.getBytes(3);
				String productDescription = setOfProducts.getString(4);
				String productColor = setOfProducts.getString(5);
				String categoryName = setOfProducts.getString(6);
				String categoryDescription = setOfProducts.getString(7);
				
				// instantiate a product object
				Product product = new Product(categoryName, categoryDescription, productName, productDescription,
						productColor, productView, productPrice);

				// add to list
				products.add(product);
				System.out.println(products.size());
			}
			databaseConnection.close();
		} catch (SQLException e) {
			e.printStackTrace();// if getting the products sfails print error to console
		}
		return products;
	}
	
	//get all products in a given category, created by a given user
	public List<Product> getMyCategoryProducts(int catID, int userID) {;
	List<Product> products = new ArrayList<Product>();
	Connection databaseConnection = new ConnectionManager().createConnection();
	try {
		System.out.println("Category ID "+catID);
		PreparedStatement dbManipulate = databaseConnection
				.prepareStatement("SELECT " + "productName, productPrice, productView, productDescription, productColor, categoryName, "
						+ "categoryDescription FROM product INNER JOIN category WHERE "
						+ "product.categoryID = category.categoryID AND product.categoryID = ? AND userID = ?");

		// after getting the list of products, put them in request scope and forward to
		// view
		dbManipulate.setInt(1, catID);
		dbManipulate.setInt(2, userID);

		ResultSet setOfProducts = dbManipulate.executeQuery();

		// obtain list of products and add to a list
		while (setOfProducts.next()) {
			String productName = setOfProducts.getString(1);
			int productPrice = setOfProducts.getInt(2);
			byte[] productView = setOfProducts.getBytes(3);
			String productDescription = setOfProducts.getString(4);
			String productColor = setOfProducts.getString(5);
			String categoryName = setOfProducts.getString(6);
			String categoryDescription = setOfProducts.getString(7);
			
			// instantiate a product object
			Product product = new Product(categoryName, categoryDescription, productName, productDescription,
					productColor, productView, productPrice);

			// add to list
			products.add(product);
			System.out.println(products.size());
		}
		databaseConnection.close();
	} catch (SQLException e) {
		e.printStackTrace();// if getting the products sfails print error to console
	}
	return products;
}
	
	//get all other products in a given category apart from those added by a given user
		public List<Product> getOtherCategoryProducts(int catID, int userID) {;
		List<Product> products = new ArrayList<Product>();
		Connection databaseConnection = new ConnectionManager().createConnection();
		try {
			System.out.println("Category ID "+catID);
			PreparedStatement dbManipulate = databaseConnection
					.prepareStatement("SELECT " + "productName, productPrice, productView, productDescription, productColor, categoryName, "
							+ "categoryDescription FROM product INNER JOIN category WHERE "
							+ "product.categoryID = category.categoryID AND product.categoryID = ? AND userID != ?");

			// after getting the list of products, put them in request scope and forward to
			// view
			dbManipulate.setInt(1, catID);
			dbManipulate.setInt(2, userID);

			ResultSet setOfProducts = dbManipulate.executeQuery();

			// obtain list of products and add to a list
			while (setOfProducts.next()) {
				String productName = setOfProducts.getString(1);
				int productPrice = setOfProducts.getInt(2);
				byte[] productView = setOfProducts.getBytes(3);
				String productDescription = setOfProducts.getString(4);
				String productColor = setOfProducts.getString(5);
				String categoryName = setOfProducts.getString(6);
				String categoryDescription = setOfProducts.getString(7);
				
				// instantiate a product object
				Product product = new Product(categoryName, categoryDescription, productName, productDescription,
						productColor, productView, productPrice);

				// add to list
				products.add(product);
				System.out.println(products.size());
			}
			databaseConnection.close();
		} catch (SQLException e) {
			e.printStackTrace();// if getting the products fails, print error to console
		}
		return products;
	}
}
