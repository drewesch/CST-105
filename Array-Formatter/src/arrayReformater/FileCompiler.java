package arrayReformater;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class FileCompiler {
	
	public static void fileWrite(String path) {
		// Write file if non-existent, appears unorganized at first level of project.
		java.io.File file = new java.io.File(path);
		if (file.exists()) {
		} else {
			try {
				PrintWriter output = new PrintWriter(path);
				output.print("The quick brown fox jumps over the lazy dog");
				output.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static String readFile(String path) {
		// From the file we created, read it and return combined phrases.
		File file = new File(path);
		String fText = "";
		try {
			Scanner input = new Scanner(file);
			while (input.hasNext()) {
				// Separate each line by a space for clarity
				String phrase = input.nextLine() + " ";
				fText += phrase;
			}
			input.close();
			// Now catch for numbers and symbols. Move on with error, as this is fine.
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fText;
	}

}
