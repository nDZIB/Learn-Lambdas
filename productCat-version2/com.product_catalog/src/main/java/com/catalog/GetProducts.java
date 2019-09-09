package com.catalog;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.connection.ConnectionManager;
import com.product.Product;

public interface GetProducts {

	List<Product> get();//takes no parameter and returns all products
	
	
	
	
	//list all products 
	static GetProducts getProducts() {
		return () -> {
			//get a connection to the database
			Connection databaseConnection = new ConnectionManager().createConnection();
			List<Product> products = new ArrayList<Product>();// list to hold all available products
			try {
				//prepare sql statement
				PreparedStatement dbManipulate = databaseConnection
						.prepareStatement("SELECT " + "productName, productPrice, productView, productDescription, productColor, categoryName, "
								+ "categoryDescription FROM product INNER JOIN category WHERE "
								+ "product.categoryID = category.categoryID");
				ResultSet setOfProducts = dbManipulate.executeQuery();

				// obtain list of products and add to the list
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
		};
		
	}
	
	
	static GetProducts getProductsFor(int userID) {
		return () -> {
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
		};
	}
	
	//get all other products but for those with specified user id
	default GetProducts ExceptProductsFor(int userID) {
		return () -> {
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
		};
	}
	
	
	//get all products under the specified product id
	static GetProducts productsInCategory(int catID) {
		return () -> {
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
		};
	}
	
	
	//get the products for a given user which belong to a given category
	static GetUserProducts myProducts(int userID) {
		return catID -> {
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
		};
	}
	
	
	//get all the products except those added by the current user
	static GetUserProducts otherProducts(int userID) {
		return catID -> {
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
		};
	}
	
	//a sub interface which handles getting products for a specified user
	interface GetUserProducts {
		List<Product> getThoseInCategory(int catID);
	}
}
