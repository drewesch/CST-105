package arrayReformater;
import arrayReformater.FileCompiler;
import arrayReformater.GenerateArray;

public class run {
	// The "control class" for all classes. Makes code neat & separated.
	public static void main(String[] args) {
		String path = "default.txt";
		FileCompiler.fileWrite(path);
		String fText = FileCompiler.readFile(path);
		String output = GenerateArray.Handler(fText);
		System.out.println(output);
	}
	
}
