import java.util.Scanner;

public class numberGuesser {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Welcome to the Number Guesser Application!");
		int guessMin = 1;
		int guessMax = 1000;
		
		int answer = getRandomIntegerInRange(guessMin, guessMax);
		Scanner input = new Scanner(System.in);
		
		int guess = 0;
		int guessTotal = 0;
		
		while (answer != guess) {
			System.out.printf("\nGuess an integer between %d-%d: ", guessMin, guessMax);
			guess = input.nextInt();
			
			if (guess < guessMin || guess > guessMax) {
				System.out.println("\nINTEGER OUT OF RANGE. TRY AGAIN!");
			} else if (guess < answer) {
				guessTotal++;
				System.out.println("\nHIGHER!");
				guessMin = guess + 1;
			} else if (guess > answer) {
				guessTotal++;
				System.out.println("\nLOWER!");
				guessMax = guess - 1;
			} else {
				guessTotal++;
				System.out.println("\nWINNER!");
				System.out.printf("\nNumber of guesses: %d", guessTotal);
			}
		}
		input.close();
	}
	
	public static int getRandomIntegerInRange(int min, int max) {
		int x = (int) (Math.random() * ((max - min) + 1)) + min;
		return x;
	}

}
