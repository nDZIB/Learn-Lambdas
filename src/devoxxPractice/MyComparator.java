package devoxxPractice;

import java.util.Objects;
import java.util.function.Function;

public class MyComparator {

	class Person {
		public String FirstName;
		public String LastName;
		public int Age;
		
		//constructionpublic String LastName;
		
		public Person(String firstName, String lastName, int age) {
			FirstName = firstName;
			LastName = lastName;
			Age = age;
		}

		public String getFirstName() {
			return FirstName;
		}

		public String getLastName() {
			return LastName;
		}

		public int getAge() {
			return Age;
		}

		public void setFirstName(String firstName) {
			FirstName = firstName;
		}

		public void setLastName(String lastName) {
			LastName = lastName;
		}

		public void setAge(int age) {
			Age = age;
		}
	}
	
	//compare persons by lastname, firstname,then age
	
	
	@FunctionalInterface
	interface Comparator<T> {
		int compare(T o1, T o2);

		static <T, U extends Comparable<? super U>> Comparator<T> comparing(Function<T, U> keyExtractor) {
			Objects.requireNonNull(keyExtractor);
			return (p1, p2) -> {

				U p1LastName = keyExtractor.apply(p1);
				U p2LastName = keyExtractor.apply(p2);
				return p1LastName.compareTo(p2LastName);
			};
		}
		
		default  <U extends Comparable<? super U>> Comparator<T> thenComparing(Function<T, U> keyExtractor) {
			Objects.requireNonNull(keyExtractor);
			return (p1, p2) -> {
				int cmp = this.compare(p1, p2);
				if(cmp == 0) {
					Comparator<T> other = comparing(keyExtractor);
					return other.compare(p1, p2);
				}else {
					return cmp;
				}
			};
		}
	}
	
	public static void main(String [] args) {
		MyComparator c = new MyComparator();
		
		Person me = c.new Person("Serkwi", "Bruno", 20);
		Person you = c.new Person("Serkwi", "Bruno", 20);
		

		Function<Person, String> lastNames = p -> p.getLastName();
		Function<Person, String> firstNames = p -> p.getFirstName();
		Function<Person, Integer> ages = p -> p.getAge();
		
		Comparator<Person> cu = Comparator.comparing(lastNames).thenComparing(firstNames).thenComparing(ages);
		
		System.out.println(cu.compare(me, you));
	}

}
