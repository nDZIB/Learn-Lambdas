package Exercises;


/*
 * Introductory concepts of lambdas.
 * A program which requests a user's name, 
 * then does a 5/4 and returns the answer*/

import java.util.Scanner;

public class PracticeOne {
	
	public final String userName;
	public PracticeOne() {
		Scanner keyboard = new Scanner(System.in);
		System.out.println("What is your name?");
		userName = keyboard.nextLine().split(" ")[0];
	}

	public static void main(String args[]) {
		PracticeOne tester = new PracticeOne();

		GreetingService bm = (message) -> System.out.println("Welcome " + message);
		GreetingService wm = (message) -> System.out.println("Good bye " + message);
		MathOperation divide = (a, b) -> a / b;

		wm.sayMessage(tester.userName);
		System.out.println("5/4 = " + tester.operate(5, 4, divide));

		bm.sayMessage(tester.userName);

	}

	interface MathOperation {
		/**
		 * <b>abstract</b> method for a mathematical operator */
		double operation(double a, int b);
	}

	interface GreetingService {
		void sayMessage(String message);
	}

	private double operate(double a, int b, MathOperation mathOperation) {
		/** 
		 * Performs a mathematical operation on two int's <b>a</b> and <b>b</b> and returns a <b>double</b>*/
		return mathOperation.operation(a, b);
	}
}
