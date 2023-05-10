import java.util.Scanner;

public class convertTemperature {
	
	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		System.out.print("Enter a Fahrenheit temperature: ");
		
		double fNumInput = input.nextDouble();
		double cConvert = (fNumInput - 32) * 5/9;
		System.out.println(fNumInput + "F is equivalent to: " + cConvert + "C");
		System.out.print("Enter a Celsius temperature: ");
		double cNumInput = input.nextDouble();
		double fConvert = (1.8 * cNumInput) + 32;
		System.out.println(cNumInput + "C is equivalent to: " + fConvert + "F");
		
		input.close();
		
	}
	
}
