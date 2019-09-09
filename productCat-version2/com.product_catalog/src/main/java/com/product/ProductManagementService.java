package com.product;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.connection.ConnectionManager;

public class ProductManagementService {

	// retrieve the product id
	public int getProductID(Product product) {
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
	}

	// retrieve category id
	public int getCategoryID(Product product) {
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
	}

	// modify/edit a product
	public boolean editProduct(Product newproduct, int newcategoryID, int oldproductID) {
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
	}

	
	//edit an existing product given that an image is supplied
	public boolean editProduct(Product newproduct, int newcategoryID, int oldproductID, InputStream productView) {
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
	}
	
	// remove a product
	public boolean removeProduct(Product product) {

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
	}

	// add a new product without image provided
	public boolean addProduct(Product product, int categoryID, int userID) {
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
	}

	// add a product to products with an image of the product supplied
	public boolean addProduct(Product product, int categoryID, InputStream productView, int userID) {
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
	}
}
