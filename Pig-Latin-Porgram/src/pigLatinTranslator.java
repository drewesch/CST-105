import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class pigLatinTranslator {
	
	public static void main(String[] args) {
		String path = "translateExample.txt";
		fileWrite(path);
		File file = new File(path);
		try {
			Scanner input = new Scanner(file);
			while (input.hasNext()) {
				String phrase = input.nextLine();
				Translate(phrase);
			}
			input.close();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void fileWrite(String path) {
		java.io.File file = new java.io.File(path);
		if (file.exists()) {
			System.out.println("Text file already exists...");
		} else {
			try {
				PrintWriter output = new PrintWriter(path);
				output.print("It was a dark and stormy night");
				output.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void Translate(String phrase) {
		String[] splitPhrase = phrase.trim().split("\\s+");
		int wordCount = splitPhrase.length;
		
		for (int i = 0; i < wordCount; i++) {
			System.out.println();
			String word = splitPhrase[i].toString();
			int wordEnd = word.length();
			char c1 = word.charAt(0);
			char c2;
			char c3;
			
			if (wordEnd == 1) {
				c2 = 'a';
				c3 = 'a';
			} else if (wordEnd == 2) {
				c2 = word.charAt(1);
				c3 = 'a';
			} else {
				c2 = word.charAt(1);
				c3 = word.charAt(2);
			}
				
			if (vowelChecker(c1) == true) {
				String newWord = (word + vowelEnder(word)).toUpperCase();
				System.out.printf(word + "\t" + newWord);
			} else if (wordEnd == 1) {
				String newWord = (word + "way").toUpperCase();
				System.out.printf(word + "\t" + newWord);
			} else if (vowelChecker(c1) == false && vowelChecker(c2) == false && vowelChecker(c3) == false) {
				String newWord = (word.substring(3,(wordEnd)) + c1 + c2 + c3 + "ay ").toUpperCase();
				System.out.printf(word + "\t" + newWord);
			} else if (vowelChecker(c1) == false && vowelChecker(c2) == false && vowelChecker(c3) == true) {
				String newWord = (word.substring(2,(wordEnd)) + c1 + c2 + "ay ").toUpperCase();
				System.out.printf(word + "\t" + newWord);
			} else if (vowelChecker(c1) == false && vowelChecker(c2) == true && vowelChecker(c3) == true) {
				String newWord = (word.substring(1,(wordEnd)) + c1 + "ay ").toUpperCase();
				System.out.printf(word + "\t" + newWord);
			} else if (vowelChecker(c1) == false && vowelChecker(c2) == true && vowelChecker(c3) == false) {
				String newWord = (word.substring(1,(wordEnd)) + c1 + "ay ").toUpperCase();
				System.out.printf(word + "\t" + newWord);
			}
		}
	}
	
	public static String vowelEnder(String word) {
		char end = word.charAt(word.length() - 1);
		String ending = "";
		
		if (end == 't' || end == 's' || end == 'n' || end == 'a' || end == 'e' || end == 'i' || end == 'o' || end == 'u') {
			ending = "way ";
		} else if (end == 'T' || end == 'S' || end == 'N' || end == 'A' || end == 'E' || end == 'I' || end == 'O' || end == 'U') {
			ending = "way ";
		} else {
			ending = "ay ";
		}
		
		return ending;
	}
	
	public static boolean vowelChecker(char letter) {
		if (letter == 'a' || letter == 'e' || letter == 'i' || letter == 'o' || letter == 'u' || letter == 'A' || letter == 'E' || letter == 'I' || letter == 'O' || letter == 'U') {
			boolean vowel = true;
			return vowel;
		} else {
			boolean vowel = false;
			return vowel;
		}
	}
}
