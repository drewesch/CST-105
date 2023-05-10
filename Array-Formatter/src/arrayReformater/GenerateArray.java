package arrayReformater;

public class GenerateArray {
	
	// Fun Note: Play around with these numbers & see what it displays. Any integer will work.
	private static int rowLength = 6;
	private static int columnLength = 7;
	
	public static String Handler(String phrase) {
		// Class to control file
		return Reformater(phraseConverter(phrase));
	}
	
	static char[][] phraseConverter(String phrase) {
		System.out.println("Input:");
		String sArray = "";
		// Fill in and/or restrain phrase
		if (phrase.length() < (rowLength * columnLength)) {
			for (int i = phrase.length(); i < (rowLength * columnLength); i++) {
				phrase += "*";
			}
		} else {
			phrase.substring(0,(rowLength * columnLength));
		}
		// Create Array
		char[][] array2D = new char[rowLength][columnLength];
		// Convert phrase to new array using left-to-right reading method, then display.
		// Note: You could simplify this code and only read it from up-to-down, but it's fun to give visualization :)
		for (int i = 0; i < rowLength; i++) {
			for (int j = 0; j < columnLength; j++) {
					array2D[i][j] = phrase.charAt((i*columnLength)+j);
					sArray += String.valueOf(array2D[i][j]);
				}
			System.out.println(sArray);
			sArray = "";
			}
		return array2D;
		}
	
	static String Reformater(char[][] newArray2D) {
		String output = "\nOutput: ";
		char[] cArray = new char[(rowLength * columnLength)];
		// Convert 6x7 array to 1D char array using up-down reading method. Then convert char array to string.
		int i, j;
		for (j = 0; j < columnLength; j++) {
			for (i = 0; i < rowLength; i++) {
				cArray[(i*columnLength)+j] = newArray2D[i][j];
				output += cArray[(i*columnLength)+j];
			}
		}
		return output;
	}

}