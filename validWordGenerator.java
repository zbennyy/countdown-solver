package countdown;

import java.io.*;
import java.util.*;

public class validWordGenerator {

	public static boolean containsIllegalCharacter(String s) {
		
		return (s.contains("-") || s.contains("/") || s.contains(".") ||
				s.contains("\'") || s.contains("(") || s.contains(")"));
		
	}
	
	public static boolean containsNumber(String s) {
		
		return (s.contains("1") || s.contains("2") || s.contains("3") || 
				s.contains("4") || s.contains("5") || s.contains("6") ||
				s.contains("7") || s.contains("8") || s.contains("9") ||
				s.contains("0"));
		
	}
	
	public static boolean containsCapital(String s) {
		
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) >= 65 && s.charAt(i) <= 90) return true;
		}
		return false;
		
	}
	
	public static boolean isValidWord(String s) {
		
		return (!(s.length() > 10 || containsIllegalCharacter(s) || containsNumber(s) || containsCapital(s)));
		
	}
	
	public static void main(String[] args) {
		
		// Read in input and output file names
		Scanner in = new Scanner(System.in);
		System.out.print("Enter the name of a text file containing words to validate: ");
		String allWordsFile = in.nextLine();
		System.out.print("Enter the name of a text file to output valid words to: ");
		String validWordsFile = in.nextLine();
		
		// Open output stream
		PrintWriter fileOut;
		try {
			fileOut = new PrintWriter(new BufferedWriter(new FileWriter(validWordsFile)));
		} catch (IOException e) {
			System.err.println("Error opening output file, exiting program");
			in.close();
			return;
		}
		
		// Open input stream 
		BufferedReader fileIn;
		try {
			fileIn = new BufferedReader(new FileReader(allWordsFile));
		} catch (FileNotFoundException e) {
			System.err.println("Error opening input file, file not found");
			in.close();
			fileOut.close();
			return;
		}
		
		ArrayList<String> valid = new ArrayList<>();
		
		// Print all valid words from input to output
		try {
			String s = fileIn.readLine();
			while (s != null) {
				if (isValidWord(s)) {
					valid.add(s);
				}
				s = fileIn.readLine();			
			}
		} catch (IOException e) {
			System.err.println("Error reading from input file " + allWordsFile);
		}
		
		valid.sort(null);
		for (String s : valid) {
			fileOut.println(s);
		}
		
		// Close input/output streams
		in.close();
		fileOut.close();
		try {
			fileIn.close();
		} catch (IOException e) {
			
		}
		
		
	}
	
}
