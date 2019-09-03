package devoxxPractice;

import java.util.Objects;

//import java.util.function.Predicate;

public class Mypredicate {

	@FunctionalInterface
	interface Predicate<T> {
		boolean test(T t);

		default Predicate<T> negate() {
			return (t -> !this.test(t));
		}

		default Predicate<T> and(Predicate<T> o) {
			Objects.requireNonNull(o,
					"the parameter to the and method must not be null, check around 'and(" + o + ")'");
			return (t -> this.test(t) && o.test(t));
		}
		
		
		default Predicate<T> xor(Predicate<T> o) {
			Objects.requireNonNull(o,
					"the parameter to the and method must not be null, check around 'and(" + o + ")'");
			return (t -> (this.test(t)^o.test(t)));
		}
	}

	public void predicate_1() {// the exercise 1

		Predicate<String> p1 = string -> string.isEmpty();

		Predicate<String> p2 = p1.negate();

		String me = "";

		System.out.println(p1.test(me));
		System.out.println("With an originally empty string i want to see false here, else otherwise");
		System.out.println(p2.test(me));
	}

	public void predicate_2() {// the exercise 2

		Predicate<String> p2 = string -> !string.isEmpty();

		Predicate<String> p1 = Objects::nonNull;// return true if the passed string object is not null

		Predicate<String> p3 = p1.and(p2);

		String me = "bruno";
		System.out.println("Test for null " + p2.test(me));
		System.out.println("Test for empty " + p1.test(me));
		System.out.println("With an originally empty or null string i want to see false here, else otherwise");
		System.out.println(p3.test(me));

		p3.test("");
	}

	// an xOr predicate

	public void predicate_3() {// the exercise 3

		Predicate<String> p2 = string -> !string.isEmpty();

		Predicate<String> p1 = Objects::nonNull;// return true if the passed string object is not null

		Predicate<String> p3 = p1.xor(p2);

		String me = "";
		System.out.println("Test for null " + p2.test(me));
		System.out.println("Test for empty " + p1.test(me));
		System.out.println("With an originally empty or null string i want to see true and false otherwise");
		System.out.println(p3.test(me));

		p3.test("");
	}
	
	
	//test my predicate

	public static void main(String[] args) {
		Mypredicate p = new Mypredicate();
		p.predicate_1();
		p.predicate_2();
		p.predicate_3();
	}
}
