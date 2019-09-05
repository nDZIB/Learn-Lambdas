package cooking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@FunctionalInterface
public interface Recipe {

	List<String> procedure = new ArrayList<String>();
	List<Ingredient> ingredientList = new ArrayList<Ingredient>();
	List<String> retrievedProcedure = new ArrayList<String>();

	abstract String follow();

	// first method specifies the meal for the recipe in question
	static Recipe toPrepare(String meal) {
		return () -> meal;
	}

	// INGREDIENTS
	// inorder to prepare a meal, get the various ingredients needed
	default Recipe get(String ingredientName, String ingredientDescription) {
		return () -> {
			ingredientList.add(new Ingredient(ingredientName, ingredientDescription));// add the ingredient to list of	
									// return its name
			System.out.println(ingredientList);
			return ingredientName;
		};
	}

	// add another ingredient to my list of ingredients
	default Recipe andAlso(String ingredientName, String ingredientDescription) {
		return () -> {
			this.follow();// add the previous element
			return this.get(ingredientName, ingredientDescription).follow();// then add the new one
		};
	}

	// PROCEDURE
	// first activity
	default Recipe dO(String process) {
		return () -> {
			procedure.add(process);
			return process;
		};
	}

	// add another activity
	default Recipe next(String ingredient, String process) {
			return () -> {
				this.follow();
				return this.dO(process).follow();
			};
		}
	
	default Recipe next(String process) {
		return () -> {
			this.follow();
			return this.dO(process).follow();
		};
	}
	
	// add another recipe
	default Recipe thenDo(Recipe r) {
		return () -> {
			this.follow();//add previous activity
			return r.follow();// add a the new recipe then return the value to recently added recipe
		};
	}

	//generic interface to handle the returning of lists
	interface GetList<T> {
		List<T> get();
	}
	
	
	
	//return the list of procedures
	default GetList<String> done(String conclusion) {
		return () -> {
			this.follow();
			procedure.add("CONCLUSION: "+conclusion);
			return procedure;
		};
	}
	
	
	default GetList<Ingredient> getIngredients() {
		return () -> {
			this.follow();
			return ingredientList;
		};
	}
	
	
	interface RetrieveProcedure {
		List<String> retrieve();
	}
	//method to return list of recipes for a certain meal
	static RetrieveProcedure retrieveProcedure(String meal) {//in case of a database, the meal provided will be used within a query
													//here before returning
		
		Objects.requireNonNull(meal,"A meal cannot be null. no appropriate recipe. Check around .retrieveProcedure("+meal+")");
		return () -> {
			//to make it simple, i am not using any query, but a hashmap with keys as the products and values as 
			//lists of strings(procedures)

			Map<String, List<String>> recipe = new HashMap<String, List<String>>();
				List<String> one = new ArrayList<String>();
					one.add("Starting");
					one.add("1. Add");
					one.add("2. Wash the beans");
					one.add("3. Dry them quickly, be careful not to spill them");
					one.add("4. Ensure you have electricity");
					one.add("5. Put in a blender and blend");
					one.add("6. Add may be stupid, but this is the recipe to prepare nothing");
					one.add("end");
				recipe.put("cake", one);//add to the hashmap
			
			//value to return on key not found
			List <String> s = new ArrayList<String>();
			s.add("No elements found");
			
			
			return recipe.getOrDefault(meal.toLowerCase(), s);	
		};
	}
	
	static RetrieveProcedure getRecipes(String x) {
		return () -> Recipe.retrieveProcedure(x).retrieve();
	}
}
