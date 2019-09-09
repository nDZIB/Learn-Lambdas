package com.product;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.category.Category;
import com.category.ServiceCategories;

@WebServlet(urlPatterns = "/modify-product.pcat")
@MultipartConfig(maxFileSize = 16177215)
public class ModifyProduct extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(HttpServletRequest requestVariable, HttpServletResponse responseVariable)
			throws ServletException, IOException {

		if (requestVariable.getParameter("productPrice") == null)// if no price is provided, call the add new product
																	// view
			requestVariable.getRequestDispatcher("/WEB-INF/views/add-new-product.jsp").forward(requestVariable,
					responseVariable);
	}

	@Override
	public void doPost(HttpServletRequest requestVariable, HttpServletResponse responseVariable)
			throws ServletException, IOException {
		// handles operations to perform after the user does modifications to to a
		// product or wishes to add a new product

		ProductManagementService productMService = new ProductManagementService();

		if (requestVariable.getParameter("editProduct") != null) {// code to execute if user wishes to edit product
			if (!this.editProduct(requestVariable)) {
				System.out.println("Unable to edit the product");
				// may redirect to modify-product
			}

		} else if (requestVariable.getParameter("deleteProduct") != null) {// product deletion code from editing
			Product product = (Product) requestVariable.getSession().getAttribute("product");

			if (!productMService.removeProduct(product)) {
				System.out.println("Unable to delete product");
				// may redirect to delete product
			}
		} else if (requestVariable.getParameter("addNewProduct") != null) {// product addition code
			if (!addNewProduct(requestVariable)) {
				System.out.println("Unable to add the product");
			}
		}

		if (requestVariable.getParameter("modifyFromView") != null) {// code to lead the user to modify a product
			System.out.println("Product is: " + requestVariable.getParameter("productName"));

			// get the neccessary parameters and do the job
			String productName = requestVariable.getParameter("productName");
			String productDescription = requestVariable.getParameter("productDescription");
			String productColor = requestVariable.getParameter("productColor");
			String categoryName = requestVariable.getParameter("categoryName");
			String categoryDescription = requestVariable.getParameter("categoryDescription");
			int productPrice = Integer.parseInt(requestVariable.getParameter("productPrice"));

			Product product = new Product(categoryName, categoryDescription, productName, productDescription,
					productColor, productPrice);

			// set the current product as session variable, this is to enable it to be
			// accessible even after
			// this request
			requestVariable.getSession().setAttribute("product", product);
			requestVariable.getRequestDispatcher("/WEB-INF/views/modify-product.jsp").forward(requestVariable,
					responseVariable);

		} else {//either the user clicked on delete while in the explicit view or they are in the modify product view
			if (requestVariable.getParameter("deleteFromView") != null) {// code to delete a product while in the explicit view
				if (this.deleteFromView(requestVariable)) {
					System.out.println("Successfully deleted from within view");
				}
			}//whether the user was in the explicit view or in the modify product view, redirect them the explicit view
			requestVariable.getSession().removeAttribute("product");
			responseVariable.sendRedirect("/view-exp-catalog.pcat");

		}
	}

	// method to add a product as a new one
	public boolean addNewProduct(HttpServletRequest requestVariable) throws IOException, ServletException {// product
																											// addition
																											// code
		ProductManagementService productMService = new ProductManagementService();

		int foundID;
		String newProductName = requestVariable.getParameter("newProductName");
		String newProductDescription = requestVariable.getParameter("newProductDescription");
		String newProductColor = requestVariable.getParameter("newProductColor");
		String categoryName = requestVariable.getParameter("newCategoryName");
		String categoryDescription = requestVariable.getParameter("newCategoryDescription");
		int productPrice = Integer.parseInt(requestVariable.getParameter("newProductPrice"));

		int userID = (int)requestVariable.getSession().getAttribute("userID");
		// get the image if it exists
		Part part = requestVariable.getPart("productView");

		Category category = new Category(categoryName, categoryDescription);
		Product product = new Product(categoryName, categoryDescription, newProductName, newProductDescription,
				newProductColor, productPrice);
		// System.out.println(product.getProductDescription());

		foundID = ServiceCategories.retrieveIDFor(category).retrieve();//categoryMService.getCategoryID(category);

		// first verify if the product exists
		if (productMService.getProductID(product) == 0) {// if the product does not exist
			if (foundID != 0) {// if the requested product category exists, add the product
				if (this.doAddProduct(part, product, foundID, userID)) {
					System.out.println("Category exists: Product was added");
					return true;
				}
			} else {// otherwise if the category does not exist, add it and proceed
				InputStream productView = null;
				if (part.getSize() != 0)
					productView = part.getInputStream();
				else {
					String pat = System.getProperty("user.dir");
					pat = pat + "\\src\\main\\resources\\def_image.png";
					productView = new FileInputStream(pat);
				}

				// from here, productView is bound not to be null, but for redundancy, still do
				// the test
				if (productView != null) {
					if (this.addProductAndCategory(product, category, productView, userID)) {
						System.out.println("Category does not exist does not exist");
						return true;
					}
				} else {// if the image is invalid (though this code may never be executed) no need for
						// the above if
					if (this.addProductAndCategory(product, category, userID))
						return true;
				}
			}
		}
		return false;
	}

	// method to edit an existing product
	public boolean editProduct(HttpServletRequest requestVariable) throws IOException, ServletException {
		ProductManagementService productMService = new ProductManagementService();

		// inorder to edit product,
		// 1 get a corresponding category id
		// make modifications
		// get the parameters from the page
		int productID = 0;// this will hold the id of the old project
		int categoryID = 0;// will hold the categoryid
		int newcategoryID = 0;// this will hold the id of the new project
		String newProductName = requestVariable.getParameter("newProductName");
		String newProductDescription = requestVariable.getParameter("newProductDescription");
		String newProductColor = requestVariable.getParameter("newProductColor");
		String categoryName = requestVariable.getParameter("newCategoryName");
		String categoryDescription = requestVariable.getParameter("newCategoryDescription");
		int newProductPrice = Integer.parseInt(requestVariable.getParameter("newProductPrice"));
		Part part = requestVariable.getPart("productView");
		InputStream productView = null;

		// instantiate a new product
		Product product = new Product(categoryName, categoryDescription, newProductName, newProductDescription,
				newProductColor, newProductPrice);

		// get the product to be modified
		Product oldProduct = (Product) requestVariable.getSession().getAttribute("product");

		// get categoryID and productID of the old product
		categoryID = productMService.getCategoryID(oldProduct);// get categoryID of old product
		productID = productMService.getProductID(oldProduct);// get productID of old product

		if (productID != 0) {// the provided old product exists
			if (categoryID != 0) {// and the provided category exists

				// instantiate a categor object and get its id
				Category category = new Category(product.getCategoryName(), product.getCategoryDescription());
				newcategoryID = ServiceCategories.retrieveIDFor(category).retrieve();//categoryMService.getCategoryID(category);// get the categoryid of the
																			// new product
				if (newcategoryID != 0) {// if the new product has a category
					if (part.getSize() == 0) {// if the user selects no file
						if (productMService.editProduct(product, newcategoryID, productID)) {
							System.out.println("No image inserted");
							return true;
						}
					} else {// if the user selected an image
						productView = part.getInputStream();
						if (productView != null) {// if there is an image, edit the product, considering the image
							if (productMService.editProduct(product, newcategoryID, productID, productView))
								return true;
						} else {// if the image is not valid, then neglect it
							if (productMService.editProduct(product, newcategoryID, productID)) {
								System.out.println("No image inserted");
								return true;
							}
						}
					}

				} else {// otherwise if the category does not exist, add it and proceed
					int userID = (int)requestVariable.getSession().getAttribute("userID");
					if (part.getSize() != 0) {// if the user selects a file
						productView = part.getInputStream();
						if (productView != null) {// and the file is a valid image file, add the new product with its
													// image
							if (this.addProductAndCategory(oldProduct, category, productView, userID))// if the new product was
																								// added
								return true;
						}
					} else // the file is not a valid image file
					if (this.addProductAndCategory(oldProduct, category, userID))
						return true;
				}
			} else {// if the provided old product's category does not exist/ this most always exist
					// though
				System.out.println("Your category does not exist");
				// redirect the user to modify-product.jsp
			}
		} else {// if the provided old product does not exist/ it must always exist, because the
				// user does not pass it
			System.out.println("Your product does not exist(can't update a non-existent product");
			// redirect the user to modify-product.jsp
		}
		return false;// return value if the new product has an error
	}

	// method to add a category, then a product
	public boolean addProductAndCategory(Product product, Category category, int userID) {
		//CategoryManagementService categoryMService = new CategoryManagementService();
		ProductManagementService productMService = new ProductManagementService();

		int newCategoryID = ServiceCategories.addCategory(category).retrieve();//categoryMService.addCategory(category);
		if (newCategoryID != 0) {// if the category was successfully added
			if (!productMService.addProduct(product, newCategoryID, userID)) {// attempt to add the product
				System.out.println("New product was not added");
				return false;
			}
		} else {// if the category is not successfully added, then the product can't be added
			System.out.println("Could not add the product");
			return false;
			// redirect to modify-product.jsp
		}
		return true;
	}

	// method to add a category and a product with an image provided
	public boolean addProductAndCategory(Product product, Category category, InputStream productView, int userID) {
		//CategoryManagementService categoryMService = new CategoryManagementService();
		ProductManagementService productMService = new ProductManagementService();

		int newCategoryID = ServiceCategories.addCategory(category).retrieve();
		if (newCategoryID != 0) {// if the category was successfully added
			if (!productMService.addProduct(product, newCategoryID, productView, userID))// attempt to add the new
																					// product
				return false;// return false if it fails
		} else {// if the new category was not added
			System.out.println("Could not add the product");
			return false;
			// redirect to modify-product.jsp
		}
		return true;
	}

	// supercode to add a new product(adding the product sets a default image if no
	// image is specified
	public boolean doAddProduct(Part part, Product product, int foundID, int userID) throws IOException {

		ProductManagementService productMService = new ProductManagementService();

		InputStream productView = null;
		if (part.getSize() > 0) {
			productView = part.getInputStream();
			if (productView != null) // if image exists, add the new product
				if (productMService.addProduct(product, foundID, productView, userID))
					return true;
				else {// add new product without image(a default image) this will hardly ever be
						// executed though
					String pat = System.getProperty("user.dir");
					pat = pat + "\\src\\main\\resources\\def_image.png";
					productView = new FileInputStream(pat);
					if (productMService.addProduct(product, foundID, productView, userID)) {
						productView.close();
						return true;
					}
				}
		} else {// if no image was selected
			String pat = System.getProperty("user.dir");
			pat = pat + "\\src\\main\\resources\\def_image.png";
			productView = new FileInputStream(pat);
			if (productMService.addProduct(product, foundID, productView, userID)) {
				productView.close();
				return true;
			}
		}
		return false; // return false if no new product was added
	}

	// method to delete a product from the view, not neccessarily having to pass
	// through the modify-product.jsp
	public boolean deleteFromView(HttpServletRequest requestVariable) {
		String productName = requestVariable.getParameter("productName");
		String productDescription = requestVariable.getParameter("productDescription");
		String productColor = requestVariable.getParameter("productColor");
		String categoryName = requestVariable.getParameter("categoryName");
		String categoryDescription = requestVariable.getParameter("categoryDescription");
		int productPrice = Integer.parseInt(requestVariable.getParameter("productPrice"));
		// String pict = requestVariable.getParameter("productView");

		System.out.println(productDescription);
		Product product = new Product(categoryName, categoryDescription, productName, productDescription, productColor,
				productPrice);

		// set the current product as session variable, this is to enable it to be
		// accessible even after
		// this request

		requestVariable.getSession().setAttribute("product", product);

		if (!new ProductManagementService().removeProduct(product)) {
			return false;
		}
		return true;
	}
}