package cooking;

public class Ingredient {
	private String ingredientName;
	private String ingredientDescription;
	
	
	//constructor
	public Ingredient(String ingredientName, String ingredientDescription) {
		this.ingredientName = ingredientName;
		this.ingredientDescription = ingredientDescription;
	}


	//getters and setters
	public String getIngredientName() {
		return ingredientName;
	}


	public String getIngredientDescription() {
		return ingredientDescription;
	}


	public void setIngredientName(String ingredientName) {
		this.ingredientName = ingredientName;
	}


	public void setIngredientDescription(String ingredientDescription) {
		this.ingredientDescription = ingredientDescription;
	}
	
	@Override
	public String toString() {
		return this.ingredientName+" ][ "+this.ingredientDescription;
	}
}
