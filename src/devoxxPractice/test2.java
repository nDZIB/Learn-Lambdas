package devoxxPractice;
import java.util.ArrayList;
import java.util.List;

public class test2 {

	interface Consumer<T> {
		void accept(T t);
		

		default Consumer<T> andThen(Consumer<T> o) {
			
			return (t -> {this.accept(t); o.accept(t);});
		}
	}
	
	public static void main(String [] args) {
	
	Consumer<List<String>> c1 = list -> list.add("one");
	Consumer<List<String>> c2 = list -> list.add("two");
	
	Consumer<List<String>> me = c1.andThen(c2);
	
	List<String> strings = new ArrayList<String>();
	
	strings.add("zero");
	
	me.accept(strings);
	System.out.println(strings);
	}
}
