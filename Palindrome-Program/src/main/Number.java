// NOTE: See package-info.java for name & statement of work.
package main;

public class Number implements Prime, Palindrome {
	private int input;
	
	// Create a non-default constructor with this.input
	// as the definition for the encapsulated integer "input"
	public Number(int input) {
		super();
		this.input = input;
	}
	
	// Method for checking if the input is a Palindrome
	@Override
	public Boolean isPalindrome() {
		// Initialize all variables beforehand
		Boolean result = true;
		String digits = Integer.toString(input);
		int digitcheck = (digits.length()/2);
		
		// Separate each digit into individual entries in an array
		// This is so the program can perform based on each digit in the number
		int[] list = new int[digits.length()];
		
		// Populate the list with all available digits
		for (int i = 0; i < digits.length(); i++ ) {
			list[i] += digits.charAt(i);
		}
		
		// First, determine if the digit length is 1
		// If so, return true because it always is a palindrome
		// Otherwise, we have to check for a palindrome
		// There are multiple methods for doing this, but my simple solution
		// is to check the corresponding digits forwards in backwards
		// For example, if the first digit and last digit are the same, they are the same either direction
		// Then the second digit and the second to last digit, and so on
		if (digits.length() == 1) {
			return true;
		} else {
			for (int i = 0; i < digitcheck; i++) {
				int start = Integer.valueOf(list[i]);
				int end = Integer.valueOf(list[(digits.length()-1)-i]);
				if (start == end) {
					continue;
				} else {
					result = false;
					break;
				}
			}
		}
		return result;
	}
	
	// Method for checking if the input is a Prime Number
	@Override
	public Boolean isPrime() {
		// First, initialize all variables
		Boolean result = true;
		
		// There are three special cases.
		// 1 is never a prime number, and 2 & 3 are always prime
		// To find the rest, I simplified the process by using the power of modulo.
		// Start by iterating every number until you've reached the input
		// If an iterated number iterated divides the input to zero, then it can be divided
		// Thus, return false if the modulo of any input over any iterated number equals zero
		// Result is true by default, so if nothing is caught, it will return true.
		if (input == 1) {
			result = false;
		} else if (input == 2 || input == 3) {
			result = true;
		} else {
			for (int i = 2; i < input; i++) {
				if ((input % i) == 0) {
					result = false;
					break;
				}
			}
		}
		return result;
	}
}
