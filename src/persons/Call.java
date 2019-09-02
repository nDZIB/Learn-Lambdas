package persons;


import java.util.List;

import person.Person;

public class Call {

	
	interface Predicate<T> {
		boolean test (T t);
	}
	
	//displayMe d = x -> {System.out.println();};
	
	public void callDrivers() {
		List<Person> persons = Persons.createPersons();
		System.out.println("===========Calling Drivers=============");
		persons.stream().filter(x -> x.getAge()>16).forEach(x -> x.print());
		System.out.println("============Calls Completed==============");
	}
	
	public void callPilots() {
		List<Person> persons = Persons.createPersons();
		System.out.println("===========Calling Pilots=============");
		persons.stream().filter(x -> x.getAge()>23).filter(x -> x.getAge()<65).forEach(x -> x.print());
		System.out.println("============Calls Completed==============");
	}
	
	public void callDraftees() {
		List<Person> persons =Persons.createPersons();
		System.out.println("===========Calling Draftees=============");
		persons.stream().filter(x -> x.getAge()>18).filter(x -> x.getAge()<25).forEach(x -> x.print());
		System.out.println("============Calls Completed==============");
	}
	
	
	public void emailDrivers() {
		List<Person> persons = Persons.createPersons();
		System.out.println("===========Emailing Drivers=============");
		persons.stream().filter(x -> x.getAge()>16).forEach(x -> x.print());
		System.out.println("============Emails sent==============");
	}
	
	public void emailPilots() {
		List<Person> persons = Persons.createPersons();
		System.out.println("===========Emailing Pilots=============");
		persons.stream().filter(x -> x.getAge()>23).filter(x -> x.getAge()<65).forEach(x -> x.print());
		System.out.println("============Emails Completely Sent==============");
	}
	
	public void emailDraftees() {
		List<Person> persons =Persons.createPersons();
		System.out.println("===========Calling Draftees=============");
		persons.stream().filter(x -> x.getAge()>18).filter(x -> x.getAge()<25).forEach(x -> x.print());
		System.out.println("============Calls Completed==============");
	}
	
	
	public static void main(String [] args) {
		Call c = new Call();
		System.out.println("age 23-65");
		c.emailPilots();
	}
}
