package persons;

import java.util.ArrayList;
import java.util.List;

import person.Person;

public class Persons {

	private static List<Person> persons = new ArrayList<Person>();
	
	public static List<Person> createPersons() {
		persons.add(new Person("Bruno", "Serkwi", 18, "M", "brunoserkwi@gmail", "671349364", "Bonduma"));
		persons.add(new Person("Bruno", "Serkw", 19, "M", "brunoserkwi@gmail", "671349364", "Bonduma"));
		persons.add(new Person("Bruno", "Serki", 19, "M", "brunoserkwi@gmail", "671349364", "Bonduma"));
		persons.add(new Person("Bruno", "Serwi", 20, "M", "brunoserkwi@gmail", "671349364", "Bonduma"));
		persons.add(new Person("Bruno", "Sekwi", 22, "F", "brunoserkwi@gmail", "671349364", "Bonduma"));
		persons.add(new Person("Bruno", "Srkwi", 60, "M", "brunoserkwi@gmail", "671349364", "Bonduma"));
		persons.add(new Person("Bruno", "erkwi", 64, "M", "brunoserkwi@gmail", "671349364", "Bonduma"));
		persons.add(new Person("Brun", "Serkwi", 26, "F", "brunoserkwi@gmail", "671349364", "Bonduma"));
		persons.add(new Person("Bruo", "Serkwi", 28, "M", "brunoserkwi@gmail", "671349364", "Bonduma"));
		persons.add(new Person("Brno", "Serkwi", 59, "M", "brunoserkwi@gmail", "671349364", "Bonduma"));
		persons.add(new Person("Buo", "Serkwi", 20, "F", "brunoserkwi@gmail", "671349364", "Bonduma"));
		persons.add(new Person("runo", "Srkwi", 20, "M", "brunoserkwi@gmail", "671349364", "Bonduma"));
		persons.add(new Person("Brno", "Serwi", 34, "F", "brunoserkwi@gmail", "671349364", "Bonduma"));
		persons.add(new Person("Bro", "erkwi", 17, "F", "brunoserkwi@gmail", "671349364", "Bonduma"));
		return persons;
	}
}
