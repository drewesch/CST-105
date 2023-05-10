import java.util.Scanner;

public class fiveDigitAdder {
	
	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		System.out.print("Enter a 5-digit positive integer: ");
		int fiveDigits = input.nextInt();
		int digit1 = (fiveDigits / 10000);
		int digit2 = (fiveDigits / 1000) % 10;
		int digit3 = (fiveDigits / 100) % 10;
		int digit4 = (fiveDigits / 10) % 10;
		int digit5 = fiveDigits % 10;
		int result = (digit1 + digit2 + digit3 + digit4 + digit5);
		System.out.println("The sum of the five digits is " + digit1 + " + " + digit2 + " + " + digit3 + " + " + digit4 + " + " + digit5 + " = " + result);
		
		input.close();
		
	}
	
}
