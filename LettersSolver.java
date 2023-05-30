package countdown;

import java.util.*;
import java.io.*;

public class LettersSolver {

	private static final String VALID_WORDS_FILE = "src/countdown/valid2.txt";
	
	private static final int MAX_WORD_LENGTH = 9;
	
	private static long NANO_TO_SECONDS = 1000000000;
	
	private LettersSolver() {}
	
	public static ArrayList<String> generateTwoCharStrings(ArrayList<String> chars) {
		
		ArrayList<String> result = new ArrayList<>();
		
		for (int i = 0; i < chars.size(); i++) {
			for (int j = 0; j < chars.size(); j++) {
				if (j != i) {
					String s = chars.get(i);
					s = s + chars.get(j);
					result.add(s);
				}
				
			}
		}
		
		return result;
		
	}
	
	public static ArrayList<String> generateThreeCharStrings(ArrayList<String> chars) {
		
		ArrayList<String> result = new ArrayList<>();
		
		/* 
		 * remove each character from the array, generate all possible two character strings
		 * not containing that character, then add that character to the front of each
		 * string in the resulting array 
		 */
		for (int i = 0; i < chars.size(); i++) {
			String c = chars.remove(i);
			ArrayList<String> a = generateTwoCharStrings(chars);
			for (int j = 0; j < a.size(); j++) {
				String temp = a.remove(j);
				a.add(j, c + temp);
			}
			chars.add(i, c);
			result.addAll(a);
		}
		
		return result;
		
	}
	
	public static ArrayList<String> generateFourCharStrings(ArrayList<String> chars) {
		
		ArrayList<String> result = new ArrayList<>();
		
		/* 
		 * remove each character from the array, generate all possible three character strings
		 * not containing that character, then add that character to the front of each
		 * string in the resulting array 
		 */
		for (int i = 0; i < chars.size(); i++) {
			String c = chars.remove(i);
			ArrayList<String> a = generateThreeCharStrings(chars);
			for (int j = 0; j < a.size(); j++) {
				String temp = a.remove(j);
				a.add(j, c + temp);
			}
			chars.add(i, c);
			result.addAll(a);
		}
		
		return result;
		
	}
	
	public static ArrayList<String> generateFiveCharStrings(ArrayList<String> chars) {
		
		ArrayList<String> result = new ArrayList<>();
		
		/* 
		 * remove each character from the array, generate all possible four character strings
		 * not containing that character, then add that character to the front of each
		 * string in the resulting array 
		 */
		for (int i = 0; i < chars.size(); i++) {
			String c = chars.remove(i);
			ArrayList<String> a = generateFourCharStrings(chars);
			for (int j = 0; j < a.size(); j++) {
				String temp = a.remove(j);
				a.add(j, c + temp);
			}
			chars.add(i, c);
			result.addAll(a);
		}
		
		return result;
		
	}
	
	public static ArrayList<String> generateSixCharStrings(ArrayList<String> chars) {
		
		ArrayList<String> result = new ArrayList<>();
		
		/* 
		 * remove each character from the array, generate all possible five character strings
		 * not containing that character, then add that character to the front of each
		 * string in the resulting array 
		 */
		for (int i = 0; i < chars.size(); i++) {
			String c = chars.remove(i);
			ArrayList<String> a = generateFiveCharStrings(chars);
			for (int j = 0; j < a.size(); j++) {
				String temp = a.remove(j);
				a.add(j, c + temp);
			}
			chars.add(i, c);
			result.addAll(a);
		}
		
		return result;
		
	}
	
	public static ArrayList<String> generateSevenCharStrings(ArrayList<String> chars) {
		
		ArrayList<String> result = new ArrayList<>();
		
		/* 
		 * remove each character from the array, generate all possible six character strings
		 * not containing that character, then add that character to the front of each
		 * string in the resulting array 
		 */
		for (int i = 0; i < chars.size(); i++) {
			String c = chars.remove(i);
			ArrayList<String> a = generateSixCharStrings(chars);
			for (int j = 0; j < a.size(); j++) {
				String temp = a.remove(j);
				a.add(j, c + temp);
			}
			chars.add(i, c);
			result.addAll(a);
		}
		
		return result;
		
	}
	
	public static ArrayList<String> generateEightCharStrings(ArrayList<String> chars) {
		
		ArrayList<String> result = new ArrayList<>();
		
		/* 
		 * remove each character from the array, generate all possible seven character strings
		 * not containing that character, then add that character to the front of each
		 * string in the resulting array 
		 */
		for (int i = 0; i < chars.size(); i++) {
			String c = chars.remove(i);
			ArrayList<String> a = generateSevenCharStrings(chars);
			for (int j = 0; j < a.size(); j++) {
				String temp = a.remove(j);
				a.add(j, c + temp);
			}
			chars.add(i, c);
			result.addAll(a);
		}
		
		return result;
		
	}
	
	public static ArrayList<String> generateNineCharStrings(ArrayList<String> chars) {
		
		ArrayList<String> result = new ArrayList<>();
		
		/* 
		 * remove each character from the array, generate all possible eight character strings
		 * not containing that character, then add that character to the front of each
		 * string in the resulting array 
		 */
		for (int i = 0; i < chars.size(); i++) {
			String c = chars.remove(i);
			ArrayList<String> a = generateEightCharStrings(chars);
			for (int j = 0; j < a.size(); j++) {
				String temp = a.remove(j);
				a.add(j, c.concat(temp));
			}
			chars.add(i, c);
			result.addAll(a);
		}
		
		return result;
		
	}
	
	private static class AlphaComp implements Comparator<String> {
		
		public int compare(String s1, String s2) {
			return s1.compareToIgnoreCase(s2);
		}
		
	}
	
	public static ArrayList<String> generatePossibleStrings(ArrayList<String> chars) {
		
		ArrayList<String> result = new ArrayList<String>();
		int preSize = 0, postSize;
		long preTime, postTime;
		double elapsedTime;
		
		System.out.println("Generating all possible strings...");
		
		/* 
		 * beginning with length one, add strings of increasing length until all possible
		 * strings have been added
		 */
		System.out.print("Generating single-letter words...");
		preTime = System.nanoTime();
		result.addAll(chars);
		postSize = result.size() - preSize;
		postTime = System.nanoTime();
		elapsedTime = ((double) postTime - preTime) / NANO_TO_SECONDS;
		System.out.println("Done! (" + postSize + " strings added, took " + elapsedTime + " seconds)");
		
		System.out.print("Generating two-letter words...");
		preTime = System.nanoTime();
		preSize = result.size();
		result.addAll(generateTwoCharStrings(chars));
		postSize = result.size() - preSize;
		postTime = System.nanoTime();
		elapsedTime = ((double) postTime - preTime) / NANO_TO_SECONDS;
		System.out.println("Done! (" + postSize + " strings added, took " + elapsedTime + " seconds)");
		
		System.out.print("Generating three-letter words...");
		preTime = System.nanoTime();
		preSize = result.size();
		result.addAll(generateThreeCharStrings(chars));
		postSize = result.size() - preSize;
		postTime = System.nanoTime();
		elapsedTime = ((double) postTime - preTime) / NANO_TO_SECONDS;
		System.out.println("Done! (" + postSize + " strings added, took " + elapsedTime + " seconds)");
		
		System.out.print("Generating four-letter words...");
		preTime = System.nanoTime();
		preSize = result.size();
		result.addAll(generateFourCharStrings(chars));
		postSize = result.size() - preSize;
		postTime = System.nanoTime();
		elapsedTime = ((double) postTime - preTime) / NANO_TO_SECONDS;
		System.out.println("Done! (" + postSize + " strings added, took " + elapsedTime + " seconds)");
		
		System.out.print("Generating five-letter words...");
		preTime = System.nanoTime();
		preSize = result.size();
		result.addAll(generateFiveCharStrings(chars));
		postSize = result.size() - preSize;
		postTime = System.nanoTime();
		elapsedTime = ((double) postTime - preTime) / NANO_TO_SECONDS;
		System.out.println("Done! (" + postSize + " strings added, took " + elapsedTime + " seconds)");
		
		System.out.print("Generating six-letter words...");
		preTime = System.nanoTime();
		preSize = result.size();
		result.addAll(generateSixCharStrings(chars));
		postSize = result.size() - preSize;
		postTime = System.nanoTime();
		elapsedTime = ((double) postTime - preTime) / NANO_TO_SECONDS;
		System.out.println("Done! (" + postSize + " strings added, took " + elapsedTime + " seconds)");
		
		System.out.print("Generating seven-letter words...");
		preTime = System.nanoTime();
		preSize = result.size();
		result.addAll(generateSevenCharStrings(chars));
		postSize = result.size() - preSize;
		postTime = System.nanoTime();
		elapsedTime = ((double) postTime - preTime) / NANO_TO_SECONDS;
		System.out.println("Done! (" + postSize + " strings added, took " + elapsedTime + " seconds)");
		
		System.out.print("Generating eight-letter words...");
		preTime = System.nanoTime();
		preSize = result.size();
		result.addAll(generateEightCharStrings(chars));
		postSize = result.size() - preSize;
		postTime = System.nanoTime();
		elapsedTime = ((double) postTime - preTime) / NANO_TO_SECONDS;
		System.out.println("Done! (" + postSize + " strings added, took " + elapsedTime + " seconds)");
		
		System.out.print("Generating nine-letter words...");
		preTime = System.nanoTime();
		preSize = result.size();
		result.addAll(generateNineCharStrings(chars));
		postSize = result.size() - preSize;
		postTime = System.nanoTime();
		elapsedTime = ((double) postTime - preTime) / NANO_TO_SECONDS;
		System.out.println("Done! (" + postSize + " strings added, took " + elapsedTime + " seconds)");
		
		// sort and return list
		System.out.println("Sorting possible words...");
		result.sort(new AlphaComp());
		
		return result;
		
	}
	
	private static class StringLengthComp implements Comparator<String> {
		
		public int compare(String s1, String s2) {
			return s1.length() - s2.length();
		}
		
	}
	
	public static ArrayList<String> findValidWords(ArrayList<String> candidates) {
		
		System.out.println("Identifying valid words...");
		
		// Open valid words file
		BufferedReader fileIn;
		try {
			fileIn = new BufferedReader(new FileReader(VALID_WORDS_FILE));
		} catch (IOException e) {
			System.err.println("(findValidWords) Error opening vaild words file " + VALID_WORDS_FILE + ". Returning null pointer.");
			return null;
		}
		
		// Create array list of valid candidate words, and populate
		ArrayList<String> result = new ArrayList<>();
		try {
			int i = 0;
			String s = fileIn.readLine();
			while (i < candidates.size() && s != null) {
				String c = candidates.get(i);
				
				// Candidate string matches valid word in text file, so candidate is a valid word
				if (c.compareTo(s) == 0) {
					result.add(c);
					s = fileIn.readLine();
					i++;
					
				// Candidate string comes alphabetically before word in text file, so go on to next candidate
				} else if (c.compareTo(s) < 0) {
					i++;
					
				// Candidate string comes alphabetically after word in text file, so go on to next word in file
				} else {
					s = fileIn.readLine();
					
				}
			}
		} catch (IOException e) {
			System.err.println("(findValidWords) Error reading from valid words file " + VALID_WORDS_FILE + ". Returning empty array.");
		}
		
		
		// Close input stream
		try {
			fileIn.close();
		} catch (IOException e) {
			System.err.println("(findValidWords) Error closing valid words file " + VALID_WORDS_FILE + ".");
		}
		
		result.sort(new StringLengthComp());
		return result;
		
	}
	
	public static void reportValidWords(ArrayList<String> words) {
		
		if (words.size() == 0) {
			System.out.println("There are no valid words in this selection.");
			return;
		} else {
			int maxLength = words.get(words.size() - 1).length();
			if (maxLength == MAX_WORD_LENGTH) {
				System.out.println("There's a " + MAX_WORD_LENGTH + "!");
			} else {
				System.out.println("The longest possible word was " + maxLength + " letters.");
			}
			
			System.out.println();
			int i = 0;
			while (i < words.size()) {
				System.out.println(words.get(i).length() + " letters:");
				while (i < words.size() - 1 && words.get(i + 1).length() == words.get(i).length()) {
					System.out.print(words.get(i) + ", ");
					i++;
				}
				System.out.print(words.get(i));
				System.out.println();
				System.out.println();
				i++;
			}
		}
		
	}
	
	public static void main(String[] args) {
		
		ArrayList<String> chars = new ArrayList<>();
		
		Scanner in = new Scanner(System.in);
		System.out.print("Enter the nine characters, separated by spaces: ");
		String[] charsArr = in.nextLine().split(" ");
		for (String c : charsArr) chars.add(c);
		
		ArrayList<String> possibleWords = generatePossibleStrings(chars);
		ArrayList<String> validWords = findValidWords(possibleWords);
		reportValidWords(validWords);
		
		in.close();
		
	}
}
