package com.product;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.connection.ConnectionManager;

public interface ServiceProduct {
	int get();
	
	
	static ServiceProduct getProductIDOf(Product product) {
		return () -> {
			Connection dbconnection = new ConnectionManager().createConnection();
			try {
				PreparedStatement pst = dbconnection
						.prepareStatement("SELECT productID FROM product WHERE productName = ? AND "
								+ "productDescription = ? AND productColor = ? AND productPrice = ?");// you might want to edit this query to
																					// include other fields
				pst.setString(1, product.getProductName());
				pst.setString(2, product.getProductDescription());
				pst.setString(3, product.getProductColor());
				pst.setInt(4, product.getProductPrice());

				ResultSet rs = pst.executeQuery();
				while (rs.next()) {
					return (rs.getInt(1));
				}
				dbconnection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return 0;
		};
	}
	
	//get a product's category id
	static ServiceProduct getCategoryIDOf(Product product) {
		return () -> {
			Connection dbconnection = new ConnectionManager().createConnection();
			
			try {
				PreparedStatement pst = dbconnection
						.prepareStatement("SELECT categoryID FROM product WHERE productName = ? AND "
								+ "productDescription = ? AND productColor = ? AND productPrice = ?");// you might want to edit this query to
																					// include other fields
				pst.setString(1, product.getProductName());
				pst.setString(2, product.getProductDescription());
				pst.setString(3, product.getProductColor());
				pst.setInt(4, product.getProductPrice());

				ResultSet rs = pst.executeQuery();
				while (rs.next()) {
					return (rs.getInt(1));
				}

				dbconnection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			System.out.println("updated product");
			return 0;
		};
	}
	
	//modify a product without picture provided
	static ProductCategory saveProduct(Product newproduct) {
		return newcategoryID -> {
			return oldproductID -> {
				Connection dbconnection = new ConnectionManager().createConnection();
				
				try {
						PreparedStatement pst2 = dbconnection
								.prepareStatement("UPDATE product SET categoryID = ?, productName = ?, productPrice = ?, "
										+ "productDescription = ?, productColor = ? WHERE productID = ?");
						pst2.setInt(1, newcategoryID);
						pst2.setString(2, newproduct.getProductName());
						pst2.setInt(3, newproduct.getProductPrice());
						pst2.setString(4, newproduct.getProductDescription());
						pst2.setString(5, newproduct.getProductColor());
						pst2.setInt(6, oldproductID);

						pst2.executeUpdate();
						dbconnection.close();
				} catch (SQLException ex2) {
					ex2.printStackTrace();
					return false;
				}
				return true;
			};
		};
	}
	
	
	//modify a product, alongside it's image
	static ProductCategory saveProduct(Product newproduct, InputStream productView) {
		return newcategoryID -> {
			return oldproductID -> {
				Connection dbconnection = new ConnectionManager().createConnection();
				
				try {
					PreparedStatement pst2 = dbconnection.prepareStatement("UPDATE product SET categoryID = ?, productName = ?, "
							+ "productPrice = ?, productDescription = ?, productView = ?, productColor = ? WHERE productID = ?");
					
					
					pst2.setInt(1, newcategoryID);
					pst2.setString(2, newproduct.getProductName());
					pst2.setInt(3, newproduct.getProductPrice());
					pst2.setString(4, newproduct.getProductDescription());
					pst2.setBlob(5, productView);
					pst2.setString(6, newproduct.getProductColor());
					pst2.setInt(7, oldproductID);

					pst2.executeUpdate();
					dbconnection.close();
				} catch (SQLException e) {
					e.printStackTrace();
					return false;
				}
				return true;
			};
		};	
	}
	
	//remove a product
	static DeleteProduct select(Product product) {
		return () -> {
			Connection dbconnection = new ConnectionManager().createConnection();

			try {// for better code, you might want to first get the relevant category id before
					// proceeding
				PreparedStatement pst = dbconnection.prepareStatement("DELETE FROM product WHERE productName=? AND "
						+ "productDescription = ? AND productPrice = ? AND productColor = ?");
				pst.setString(1, product.getProductName());
				pst.setString(2, product.getProductDescription());
				pst.setInt(3, product.getProductPrice());
				pst.setString(4, product.getProductColor());
				pst.executeUpdate();

				dbconnection.close();
				System.out.println("Okay, product deleted "+ product.getProductDescription());
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("Nothing deleted");
				return false;
			}
			return true;
		};
	}
	
	//add a new product without providing an image
	static ProductCategory add(Product product) {
		return categoryID -> {
			return userID -> {
				Connection dbconnection = new ConnectionManager().createConnection();
				
				//PreparedStatement pst;
				try {
					PreparedStatement pst = dbconnection.prepareStatement("INSERT IGNORE INTO product(userID, categoryID, productName, productPrice, "
							+ "productDescription, productColor) VALUES(?,?,?,?,?,?)");
					pst.setInt(1, userID);
					pst.setInt(2, categoryID);
					pst.setString(3, product.getProductName());
					pst.setInt(4, product.getProductPrice());
					pst.setString(5, product.getProductDescription());
					pst.setString(6, product.getProductColor());

					// execute the query
					pst.executeUpdate();
					dbconnection.close();
				} catch (SQLException e) {
					e.printStackTrace();
					return false;
				}
				return true;
			};
		};
	}
	
	
	//add a new product given that an image is provided for it
	static ProductCategory add(Product product, InputStream productView) {
		return categoryID -> {
			return userID -> {
				Connection dbconnection = new ConnectionManager().createConnection();
				PreparedStatement pst;
				try {
					pst = dbconnection.prepareStatement(
							"INSERT IGNORE INTO product(userID, categoryID, productName, productPrice, productView, productDescription,"
									+ " productColor) VALUES(?,?,?,?,?,?,?)");
					pst.setInt(1, userID);
					pst.setInt(2, categoryID);
					pst.setString(3, product.getProductName());
					pst.setInt(4, product.getProductPrice());
					pst.setBlob(5, productView);
					pst.setString(6, product.getProductDescription());
					pst.setString(7, product.getProductColor());

					// execute the query
					pst.executeUpdate();
					dbconnection.close();
				} catch (SQLException e) {
					e.printStackTrace();
					return false;
				}
				return true;
			};
		};	
	}
	
	//OTHER INTERFACES
	@FunctionalInterface
	interface ValidateSubject {
		boolean as(int oldProductId);
	}
	
	@FunctionalInterface
	interface ProductCategory {
		ValidateSubject inCategory(int newCategoryID);
	}
	
	@FunctionalInterface 
	interface DeleteProduct{
		boolean remove();
	}
}