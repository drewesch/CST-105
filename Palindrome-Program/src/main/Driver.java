// NOTE: See package-info.java for name & statement of work.
package main;

import java.util.ArrayList;

// Main class that runs program, should be simplistic & easy to control
public class Driver {

	public static void main(String[] args) {
		ArrayList<Integer> numList = new ArrayList<Integer>();
		// Feel free to mess with the "amount" variable below
		// Java limits this value to 100000
		int amount = 100000;
		for (int i = 1; i <= amount; i++) {
			// Create new number object for every object
			Number n = new Number(i);
			// Use interfaces to make this simplistic
			// This is an example of abstraction
			// If the number is both a palindrome and a prime, add to the ArrayList
			if ((n.isPalindrome() == true) && (n.isPrime() == true)) {
				numList.add(i);
			}
		}
		// Once complete, print out the ArrayList
		System.out.println("Output:\n");
		System.out.println(numList);
	}
	
	static void isPrime(Prime pr) {
		// Provides abstraction using an interface
		// This method is specifically defined in each object, supposing there could be an infinite amount
		pr.isPrime();
	}
	
	static void isPalindrome(Palindrome pa) {
		// Same idea down here, since you can have multiple interfaces
		pa.isPalindrome();
	}

}
