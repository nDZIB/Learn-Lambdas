package com.category;

public class Category {
	private String categoryName;
	private String categoryDescription;
	
	//constructor with parameters
	public Category(String categoryName, String categoryDescription) {
		this.categoryName = categoryName;
		this.categoryDescription = categoryDescription;
	}
	
	//getters
	public String getCategoryName() {
		return categoryName;
	}
	public String getCategoryDescription() {
		return categoryDescription;
	}
	//setters
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public void setCategoryDescription(String categoryDescription) {
		this.categoryDescription = categoryDescription;
	}
	
	
	@Override
	public String toString() {
		return String.format("[Name]: %s [which:] %s", this.categoryName, this.categoryDescription);
	}
	
	public boolean isComplete() {
		if(this.categoryName == null || this.categoryName.isEmpty())
			return false;
		return true;
	}
}
