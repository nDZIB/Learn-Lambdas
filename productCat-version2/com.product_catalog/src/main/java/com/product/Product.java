package com.product;

import java.util.Base64;

import com.category.Category;

public class Product extends Category{
	private String productName;
	private String productDescription;
	private String productColor;
	private byte[] productView;
	private String img;
	private int productPrice;
	
	//first constructor
	public Product(String categoryName, String categoryDescription, String productName, String productDescription,
			String productColor, int productPrice) {
		super(categoryName, categoryDescription);
		this.productName = productName;
		this.productDescription = productDescription;
		this.productColor = productColor;
		this.productView = null;
		this.productPrice = productPrice;
	}

	//constructor with images inclusive
	public Product(String categoryName, String categoryDescription, String productName, String productDescription,
			String productColor, byte[] productVew, int productPrice) {
		super(categoryName, categoryDescription);
		this.productName = productName;
		this.productDescription = productDescription;
		this.productColor = productColor;
		this.productView = productVew;
		this.productPrice = productPrice;
		this.img = Base64.getEncoder().encodeToString(productVew);
	}

	//getters
	public String getProductName() {
		return productName;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public String getProductColor() {
		return productColor;
	}

	public int getProductPrice() {
		return this.productPrice;
	}
	//setters
	public void setProductName(String productName) {
		this.productName = productName;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public void setProductColor(String productColor) {
		this.productColor = productColor;
	}
	
	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
	}
	
	public byte[] getProductView() {
		return productView;
	}
	public String getImg() {
		return img;
	}

	public void setProductView(byte[] productView) {
		this.productView = productView;
	}
	
	@Override
	public boolean isComplete() {
		if(!super.isComplete())
			return false;
		else {
			if(this.productName != null || this.productPrice <= 0 || this.productName.isEmpty())
				return false;
		}
		return true;
	}
}
