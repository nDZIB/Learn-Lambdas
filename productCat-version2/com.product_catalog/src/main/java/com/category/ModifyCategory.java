package com.category;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/modify-category.pcat")
public class ModifyCategory extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(HttpServletRequest requestVariable, HttpServletResponse responseVariable)
			throws ServletException, IOException {
		// first, get the parameters
		String categoryName = requestVariable.getParameter("categoryName");
		String categoryDescription = requestVariable.getParameter("categoryDescription");
		if (categoryName == null || categoryName.isEmpty() || categoryDescription.isEmpty()
				|| categoryDescription == null)
			requestVariable.getRequestDispatcher("/WEB-INF/views/add-new-category.jsp").forward(requestVariable,
					responseVariable);
		else {
			Category category = new Category(categoryName, categoryDescription);

			if (Integer.parseInt(requestVariable.getParameter("id")) != 0) {//if the id identifying the button clicked is 0, implies delete
				requestVariable.getSession().setAttribute("category", category);// put the current category to be
																				// modified
																				// in
																				// session
				requestVariable.getRequestDispatcher("/WEB-INF/views/modify-category.jsp").forward(requestVariable,
						responseVariable);
			} else {//code to delete the category(if the passed id is 0)
				ServiceCategories.remove(category);
				
				responseVariable.sendRedirect("/view-exp-categories.pcat");
			}
		}
	}

	@Override
	public void doPost(HttpServletRequest requestVariable, HttpServletResponse responseVariable)
			throws ServletException, IOException {
		/* Do post handles deletion and modification of a category/categories */

		if (requestVariable.getParameter("deleteCategory") != null) {// if user wishes to delete a category
			Category category = (Category) requestVariable.getSession().getAttribute("category");

			ServiceCategories.remove(category);
		} else if (requestVariable.getParameter("editCategory") != null) {// if a user wishes to edit a category
			String newCategoryDescription = requestVariable.getParameter("newCategoryDescription").toString();
			String newCategoryName = requestVariable.getParameter("newCategoryName").toString();

			Category newCategory = new Category(newCategoryName, newCategoryDescription);
			Category oldcategory = (Category) requestVariable.getSession().getAttribute("category");

			if (newCategory.isComplete()) {
				if (ServiceCategories.change(oldcategory).to(newCategory))
					System.out.println("Category updated");
				else
					System.out.println("Modify Category: Unable to update the category");
			} else {
				System.out.println(
						"ModifyCategory: Category was not modified, it seems all information was not provided");
			}

		} else if (requestVariable.getParameter("addNewCategory") != null) {
			// execute query to add a category, then return to the view-exp-catalog jsp
			// first get category, then add it
			String newCategoryDescription = requestVariable.getParameter("newCategoryDescription").toString();
			String newCategoryName = requestVariable.getParameter("newCategoryName").toString();

			Category newCategory = new Category(newCategoryName, newCategoryDescription);

			if (newCategory.isComplete()) {
				int catID = ServiceCategories.retrieveIDFor(newCategory).retrieve();//manageCategory.getCategoryID(newCategory);
				System.out.println("category id = " + catID);

				if (catID <= 0) {// if no such category exists
					if (ServiceCategories.add(newCategory).go())
						System.out.println("Modify Category: Category was added");
				} else {
					System.out.println("Modify Category: Category was added");
				}
			} else {
				System.out.println("Modify Category: Category was not added");
			}
		}
		requestVariable.getSession().removeAttribute("category");// remove the old category from session after
																	// performing relevant operation
		responseVariable.sendRedirect("/view-exp-categories.pcat");// and return the user to be able to explicitly view
																	// products
	}
}
