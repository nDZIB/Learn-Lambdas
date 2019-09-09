package com.category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.connection.ConnectionManager;

@FunctionalInterface
public interface ServiceCategories {
	boolean go();

	static ServiceCategories remove(Category category) {
		return () -> {
			Connection dbconnection = new ConnectionManager().createConnection();
			try {
				PreparedStatement pst = dbconnection
						.prepareStatement("DELETE FROM category WHERE categoryName=? AND categoryDescription=?");
				pst.setString(1, category.getCategoryName());
				pst.setString(2, category.getCategoryDescription());
				pst.execute();

				dbconnection.close();
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
			return true;
		};
	}

	static EditCategory change(Category oldCategory) {
		return newCategory -> {
			Connection dbconnection = new ConnectionManager().createConnection();
			try {
				PreparedStatement pst = dbconnection.prepareStatement("UPDATE category SET categoryName= ?, "
						+ "categoryDescription = ? WHERE categoryName=? AND categoryDescription= ?");

				pst.setString(1, newCategory.getCategoryName());
				pst.setString(2, newCategory.getCategoryDescription());
				pst.setString(3, oldCategory.getCategoryName());
				pst.setString(4, oldCategory.getCategoryDescription());

				pst.execute();
				dbconnection.close();
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
			return true;
		};
	}

	// method which adds a new category

	static ServiceCategories add(Category newCategory) {//add, but do not retrieve the category id
		return () -> {
			Connection dbconnection = new ConnectionManager().createConnection();
			try {
				PreparedStatement pst = dbconnection
						.prepareStatement("INSERT IGNORE INTO category(categoryName, categoryDescription) VALUES(?,?)");

				// set the parameters
				pst.setString(1, newCategory.getCategoryName());
				pst.setString(2, newCategory.getCategoryDescription());
				pst.execute();

				dbconnection.close();
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
			return true;
		};
	}

	static RetrieveCategoryID retrieveIDFor(Category category) {
		return () -> {
			Connection dbconnection = new ConnectionManager().createConnection();
			try {
				PreparedStatement pst = dbconnection.prepareStatement(
						"SELECT categoryID FROM category WHERE categoryName = ? AND " + "categoryDescription = ? ");
				// you want to edit this query to include other fields
				pst.setString(1, category.getCategoryName());
				pst.setString(2, category.getCategoryDescription());

				ResultSet rs = pst.executeQuery();
				if (rs.next()) {
					return (rs.getInt(1));
				}
				dbconnection.close();
			} catch (SQLException e) {
				e.printStackTrace();
				return 0;
			}
			return 0;
		};
	}

	static RetrieveCategoryID addCategory(Category category) {//add a new category id and retrieve its id
		return () -> {
			Connection dbconnection = new ConnectionManager().createConnection();
			try {
				PreparedStatement pst2 = dbconnection.prepareStatement(
						"INSERT IGNORE INTO category(categoryName, categoryDescription) VALUES(?,?)",
						Statement.RETURN_GENERATED_KEYS);
				pst2.setString(1, category.getCategoryName());
				pst2.setString(2, category.getCategoryDescription());

				pst2.executeUpdate();// i now have the id of the new category
				ResultSet rs2 = pst2.getGeneratedKeys();

				while (rs2.next()) {
					return (rs2.getInt(1));
				}
				dbconnection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return 0;
		};
	}
	
	//get all categories
	static RetrieveCategories allCategories() {
		return () -> {
			List<Category> allCategories = new ArrayList<Category>();
			Connection dbconnection = new ConnectionManager().createConnection();
			try {
				PreparedStatement pst = dbconnection.prepareStatement("SELECT categoryName, categoryDescription "
						+ "FROM category");
				
				ResultSet setOfCategories = pst.executeQuery();
				while(setOfCategories.next()) {
					Category newCategory = new Category(setOfCategories.getString(1), setOfCategories.getString(2));
					
					allCategories.add(newCategory);
				}
				dbconnection.close();
			} catch(SQLException ex) {
				ex.printStackTrace();
				System.out.println("Unable to retrieve list of products");
			}
			return allCategories;
		};
	}
	
	
	
	
	//OTHER INTERFACES
	interface EditCategory {
		boolean to(Category category);
	}

	// interface which gets the categoryId
	interface RetrieveCategoryID {
		int retrieve();
	}
	
	interface RetrieveCategories {
		List<Category> get();
	}
}
