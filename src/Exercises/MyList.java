package Exercises;

/*
 * This keeps a track of names of registered persons in a certain organization
 * And the state of the list (whether odd or even-membered)*/
import java.util.ArrayList;

public class MyList<E> extends ArrayList<E> {
	public static String state;

	public MyList() {
		super();
		state = "even";
	}

	@Override
	public boolean add(E e) {
		if (super.add(e)) {
			if(this.size()%2 == 0)
				state = "even";
			else
				state = "odd";
			return true;
		}
		return false;
	}
	
	@Override
	public E remove(int index) {
		super.remove(index);
		if(this.size()%2 == 0)
			state = "even";
		else
			state = "odd";
		return (E)this;
	}

	public static void main(String [] args) {
		MyList<String> l = new MyList<String>();
		
		//add some elements to my new list of strings
		l.add("Bruno");
		l.add("Ndzi");
		l.add("Nyugab");
		
		//using a lambda
		System.out.println("Displaying "+l.size()+" items, with status: "+l.state);
		l.forEach(System.out::println);//USE OF LAMBDA
	}
}
