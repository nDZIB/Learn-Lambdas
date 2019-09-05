package cooking;

import java.util.List;
import java.util.stream.Collectors;

public class Tester {

	public static void main(String[] args) {
		
		//testing adding a recipe
		List<String> s =
				Recipe.toPrepare("Cake")
				.get("Flour", "Powder is preferrable.")
				.andAlso("Salt", "buy good")
				.dO("Pour the flour into a bowl. It will remain in the mixture whole time.")
				.next("Add Salt: A pinch, otherwise you would have made a stew, not a cake.")
				.next("Add sugar.")
				.next("Add salt and stir till you have a uniform batter, then bake.")
				.done("concluded")
				//.getIngredients()
				.get();
		
		System.out.println(s.size());
		
		//testing retrieving a recipe
		System.out.println("\n\n========= Getting Recipe=========");
		//A method to return recipes already exists, so what we need to do now is just to pass a a meal to a method
		// then the method returns a list of recipes for the passed meal
		
		
		
		s = Recipe.retrieveProcedure("CAKE").retrieve();
			//System.out.println("\n\tCAKE");
		
		//s already has my recipe for cake, let do some streaming on it
		//first, only using streams, get the number of procedures for cake
		
		System.out.println(
				s.stream()
					  .filter(line -> Character.isDigit(new Character(line.charAt(0))))
					  .collect(Collectors.counting()));
		
		
		
		//next the procedures which start with add
		System.out.println("Procedures which start with add");
				s.stream()
		  			.filter(line -> Character.isDigit(new Character(line.charAt(0))))
					.filter(line -> line.split(" ")[1].equalsIgnoreCase("add"))//use string formatting, in order to remove puntuations
					.collect(Collectors.toList())
					.forEach(System.out::println);	
		//Recipe.getRecipes("Add").retrieve().forEach(System.out::println);
		
	}

}
