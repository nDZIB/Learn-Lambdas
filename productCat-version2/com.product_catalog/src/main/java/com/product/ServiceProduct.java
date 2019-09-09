package com.product;

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
	
	
}
