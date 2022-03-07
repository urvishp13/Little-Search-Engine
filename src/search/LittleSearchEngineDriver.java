package search;

import java.io.*;

/**
 * Driver used to use the LittleSearchEngine application
 * 
 * @author Urvish Patel
 *
 */
public class LittleSearchEngineDriver {
	
	static BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
	
	public static void main(String[] args) throws IOException {
		System.out.print("Enter the file name that has all the documents to be read => ");
		String docsFile = keyboard.readLine();
		System.out.print("Enter the noise words file name => ");
		String noiseWordsFile = keyboard.readLine();
		LittleSearchEngine lse = new LittleSearchEngine();
		lse.makeIndex(docsFile, noiseWordsFile);
		while (true) {
			System.out.print("Do you want to find documents 2 words of your choice occur in? [yes/no] => ");
			String answer = keyboard.readLine();
			if (answer.toLowerCase().equals("yes")) {
				System.out.print("Please enter the first word of your choice => ");
				String firstWord = keyboard.readLine();
				System.out.print("Please enter the second word of your choice => ");
				String secondWord = keyboard.readLine();
				System.out.print("top 5 docs for words: " + firstWord + ", " + secondWord + ": ");
				if (lse.top5search(firstWord, secondWord) == null)
					System.out.println("no documents AMONGST these two words!");
				else
					System.out.println(lse.top5search(firstWord, secondWord));
				System.out.println();
			}
			else if (answer.toLowerCase().equals("no")) {
				break;
			}
			else { // any other response
				System.out.println("Invalid response. Please try again.");
			}
		}
	}

}
