package search;

import java.io.*;
import java.util.*;

/**
 * This class encapsulates an occurrence of a keyword in a document. It stores the
 * document name, and the frequency of occurrence in that document. Occurrences are
 * associated with keywords in an index hash table.
 * 
 * @author Sesh Venugopal
 * 
 */
class Occurrence {
	/**
	 * Document in which a keyword occurs.
	 */
	String document;
	
	/**
	 * The frequency (number of times) the keyword occurs in the above document.
	 */
	int frequency;
	
	/**
	 * Initializes this occurrence with the given document,frequency pair.
	 * 
	 * @param doc Document name
	 * @param freq Frequency
	 */
	public Occurrence(String doc, int freq) {
		document = doc;
		frequency = freq;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "(" + document + "," + frequency + ")";
	}
}

/**
 * This class builds an index of keywords. Each keyword maps to a set of documents in
 * which it occurs, with frequency of occurrence in each document. Once the index is built,
 * the documents can searched on for keywords.
 * 
 * @author Urvish Patel
 *
 */
public class LittleSearchEngine {
	
	/**
	 * This is a hash table of all keywords. The key is the actual keyword, and the associated value is
	 * an array list of all occurrences of the keyword in documents. The array list is maintained in descending
	 * order of occurrence frequencies.
	 */
	HashMap<String,ArrayList<Occurrence>> keywordsIndex;
	
	/**
	 * The hash table of all noise words - mapping is from word to itself.
	 */
	HashMap<String,String> noiseWords;
	
	/**
	 * Creates the keyWordsIndex and noiseWords hash tables.
	 */
	public LittleSearchEngine() {
		keywordsIndex = new HashMap<String,ArrayList<Occurrence>>(1000,2.0f);
		noiseWords = new HashMap<String,String>(100,2.0f);
	}
	
	/**
	 * This method indexes all keywords found in all the input documents. When this
	 * method is done, the keywordsIndex hash table will be filled with all keywords,
	 * each of which is associated with an array list of Occurrence objects, arranged
	 * in decreasing frequencies of occurrence.
	 * 
	 * @param docsFile Name of file that has a list of all the document file names, one name per line
	 * @param noiseWordsFile Name of file that has a list of noise words, one noise word per line
	 * @throws FileNotFoundException If there is a problem locating any of the input files on disk
	 */
	public void makeIndex(String docsFile, String noiseWordsFile) 
	throws FileNotFoundException {
		// load noise words to hash table
		Scanner sc = new Scanner(new File(noiseWordsFile));
		while (sc.hasNext()) {
			String word = sc.next();
			noiseWords.put(word,word);
		}
		
		// index all keywords
		sc = new Scanner(new File(docsFile));
		while (sc.hasNext()) {
			String docFile = sc.next();
			HashMap<String,Occurrence> kws = loadKeyWords(docFile);
			mergeKeyWords(kws);
		}
		printKeywordsHashMap();
	}

	/**
	 * Scans a document, and loads all keywords found into a hash table of keyword occurrences
	 * in the document. Uses the getKeyWord method to separate keywords from other words.
	 * 
	 * @param docFile Name of the document file to be scanned and loaded
	 * @return Hash table of keywords in the given document, each associated with an Occurrence object
	 * @throws FileNotFoundException If the document file is not found on disk
	 */
	public HashMap<String,Occurrence> loadKeyWords(String docFile) 
	throws FileNotFoundException {
		// TODO: COMPLETE THIS METHOD
		
		// Make a HashMap for keywords that maps keywords to the number of times they occur in the current document
		// docFile
		HashMap<String,Occurrence> keywords = new HashMap<String,Occurrence>();
		
		Scanner sc = new Scanner(new File(docFile));
		// For each word (token) in the docFile
		while (sc.hasNext()) {
			String word = sc.next();
			// Check if the word is a keyword (use getKeyWord())
			String keyword = getKeyWord(word);
			// If the word is a keyword
			if (keyword != null) {
				//System.out.println("the current keyword is: " + keyword);
				// If the keyword exists in keywords
				if (keywords.containsKey(keyword)) {
					// Find it in the HashMap and increment its frequency by 1
					Occurrence occ = keywords.get(keyword);
					//System.out.println("repeat keyword found. Before updating its frequency: " + occ.frequency);
					occ.frequency++;
					//System.out.println("After updating its frequency: " + occ.frequency);
					//System.out.println(occ);
					keywords.put(keyword, occ);
				}
				// Else, (a new keyword has been found)
				else {
					// Add the keyword to keywords with a new Occurrence of the docFile name with frequency 1
					Occurrence occ = new Occurrence(docFile,1);
					keywords.put(keyword, occ);
					//System.out.println("new keyword found: " + keyword);
				}
			}
		}
		
		return keywords;
	}
	
	/**
	 * Merges the keywords for a single document into the master keywordsIndex
	 * hash table. For each keyword, its Occurrence in the current document
	 * must be inserted in the correct place (according to descending order of
	 * frequency) in the same keyword's Occurrence list in the master hash table. 
	 * This is done by calling the insertLastOccurrence method.
	 * 
	 * @param kws Keywords hash table for a document
	 */
	public void mergeKeyWords(HashMap<String,Occurrence> kws) {
		// TODO: COMPLETE THIS METHOD
		
		// For each keyword in the current document HashMap kws
		for (String keyword : kws.keySet()) {
			// If the keyword is not found in the master HashMap
			if (!keywordsIndex.containsKey(keyword)) {
				// Add it and its Occurrence to the master HashMap
				ArrayList<Occurrence> keywordOccs = new ArrayList<Occurrence>();
				keywordOccs.add(kws.get(keyword)); // adding keyword's Occ in the current document
				keywordsIndex.put(keyword, keywordOccs);
			}
			// Else, (if the keyword is found in the master HashMap)
			else {
				/* Take its Occurrence and insert it into the correct place into the master HashMap's keyword's		
				 * ArrayList. 
				 */ 
				// First, add the current Occurrence to the END of the ArrayList
				Occurrence keywordOcc = kws.get(keyword);
				keywordsIndex.get(keyword).add(keywordOcc);
				// Then, use insertLastOccurrence() to insert the current Occurrence into the right place.
				ArrayList<Integer> midpoints = insertLastOccurrence(keywordsIndex.get(keyword));
				//System.out.println("midpoints after insertion: " + midpoints);
				//System.out.println("after ordering Occurrences: " + keyword + ": " + keywordsIndex.get(keyword));
			}
		}
		
	}
	
	/**
	 * Utility method
	 */
	private void printKeywordsHashMap() {
		for (String keyword : keywordsIndex.keySet()) {
			System.out.println(keyword + ": " + keywordsIndex.get(keyword));
		}
	}
	
	/**
	 * Given a word, returns it as a keyword if it passes the keyword test,
	 * otherwise returns null. A keyword is any word that, after being stripped of any
	 * TRAILING punctuation, consists only of alphabetic letters, and is not
	 * a noise word. All words are treated in a case-INsensitive manner.
	 * 
	 * Punctuation characters are the following: '.', ',', '?', ':', ';' and '!'
	 * 
	 * @param word Candidate word
	 * @return Keyword (word without trailing punctuation, LOWER CASE)
	 */
	public String getKeyWord(String word) {
		// TODO: COMPLETE THIS METHOD
		
		// Tokenize the word with the punctuations being the delimiters (don't retain the delimiters)
		String delims = ".,?:;!";
		System.out.println("word: " + word);
		word = word.toLowerCase();
		StringTokenizer st = new StringTokenizer(word, delims);
		// Grab the first token i.e. the word stripped of any ending punctuation
		String possibleKeyword;
		try {
			possibleKeyword = st.nextToken();	
		}
		catch (NoSuchElementException e) {
			return null;
		}
		//System.out.println("after try..catch");
		// If there are more tokens (i.e. portions in between the word that are separated by non-letter characters), 
		// return null
		if (st.hasMoreTokens()) {
			//System.out.println(word + ": not a keyword");
			return null;
		}
		// Else, (the word MAY contains all letters)
		else {
			// First check if the word is a noise word
			if (noiseWords.containsKey(possibleKeyword)) {
				// If it is, return null
				//System.out.println("possibleKeyword: " + possibleKeyword + " is a noise word");
				return null;
			}
			/* The word is not a noise word */
			// Go through each character in the word
			for (int i = 0; i < possibleKeyword.length(); i++) {
				// If a non-letter character is reached
				if (!Character.isLetter(possibleKeyword.charAt(i)) ){
					// Return null
					//System.out.println(possibleKeyword + ": not a keyword");
					return null;
				}
			}
			/* The word contains only letters i.e. keyword found */
			// Return the keyword
			String keyword = possibleKeyword;
			//System.out.println("keyword found: " + keyword);
			return keyword;
		}
	}
	
	/**
	 * Inserts the last occurrence in the parameter list in the correct position in the
	 * same list, based on ordering occurrences on descending frequencies. The elements
	 * 0..n-2 in the list are already in the correct order. Insertion of the last element
	 * (the one at index n-1) is done by first finding the correct spot using binary search, 
	 * then inserting at that spot.
	 * 
	 * @param occs List of Occurrences
	 * @return Sequence of mid point indexes in the input list checked by the binary search process,
	 *         null if the size of the input list is 1. This returned array list is only used to test
	 *         your code - it is not used elsewhere in the program.
	 */
	public ArrayList<Integer> insertLastOccurrence(ArrayList<Occurrence> occs) {
		// TODO: COMPLETE THIS METHOD
		
		// Remove the last Occurrence in the ArrayList i.e. the Occurrence recently added that has to be placed into
		//		the right sorted place
		//System.out.println("before removal of lastOcc: " + occs);
		Occurrence lastOcc = occs.remove(occs.size() - 1);
		int lastOccFreq = lastOcc.frequency;
		
		/* Implement Binary Search */
		ArrayList<Integer> midpoints = new ArrayList<Integer>();
		int low = 0;
		int high = occs.size()-1; // the last index in the List
		
		int mid;
		while (low <= high) {
			mid = (low + high)/2;
			midpoints.add(mid);
			
			if (lastOccFreq == occs.get(mid).frequency) {
				low = mid + 1; // done to make sure ANY occurrence of a duplicate frequency is listed last WITH RESPECT TO
							   // the portion of the List that involves duplicates of ITS frequency
			}
			else if (lastOccFreq > occs.get(mid).frequency) {
				high = mid - 1;
			}
			else { // lastOccFreq < mid Occ
				low = mid + 1;
			}
		}
		
		int lastMidpoint = midpoints.get(midpoints.size() - 1);
		// If the lastOccFreq > the freq at the last midpoint location
		if (lastOccFreq > occs.get(lastMidpoint).frequency) {
			// Add the lastOcc before it
			occs.add(lastMidpoint, lastOcc);
		}
		// Else, (if the lastOccFreq < the freq at the last midpoint location or == to the freq at the last midpoint
		//		location)
		else {
			// Add the lastOcc after it
			occs.add(lastMidpoint+1, lastOcc);
		}
		
		// Return the list of MIDPOINTS
		return midpoints;
	}
	
	/**
	 * Search result for "kw1 or kw2". A document is in the result set if kw1 or kw2 occurs in that
	 * document. Result set is arranged in descending order of occurrence frequencies. (Note that a
	 * matching document will only appear once in the result.) Ties in frequency values are broken
	 * in favor of the first keyword. (That is, if kw1 is in doc1 with frequency f1, and kw2 is in doc2
	 * also with the same frequency f1, then doc1 will appear before doc2 in the result. 
	 * The result set is limited to 5 entries. If there are no matching documents, the result is null.
	 * 
	 * @param kw1 First keyword
	 * @param kw1 Second keyword
	 * @return List of NAMES of documents in which either kw1 or kw2 occurs, arranged in descending order of
	 *         frequencies. The result size is limited to 5 documents. If there are no matching documents,
	 *         the result is null.
	 */
	public ArrayList<String> top5search(String kw1, String kw2) {
		// TODO: COMPLETE THIS METHOD
		
		// Get the List for kw1 from keywordsIndex
		ArrayList<Occurrence> kw1Occs = keywordsIndex.get(kw1);
		// Get the List for kw2 from keywordsIndex
		ArrayList<Occurrence> kw2Occs = keywordsIndex.get(kw2);
		// Create a top5SearchResultOccs ArrayList
		ArrayList<Occurrence> top5SearchResultOccs = new ArrayList<Occurrence>();
		ArrayList<String> top5SearchResultDocs = new ArrayList<String>();
		
		/* Start comparing frequencies of kw1 and kw2 and add their docs to top5SearchResultDocs using
		 * insertLastOccurrence 
		 * 
		 * Note: Adding Occurrences for troubleshooting purposes and extracting docs later
		 */
		// Add all the Occurrences from kw1's Occurrence List to the Occs result List, don't need to use
		//		insertLastOccurrence because the Occurrences are already in order
		top5SearchResultOccs = kw1Occs;
		System.out.println("after adding kw1's occs to search results: " + top5SearchResultOccs);
		// Add all the Occurrences from kw2's Occurrence List (if it exists) to the Occs result List
		for (int i = 0; kw2Occs != null && i < kw2Occs.size(); i++) {
			Occurrence occ = kw2Occs.get(i);
			top5SearchResultOccs.add(occ);
			// checking to see if, after passing same reference of List, add values gets added to the new reference's List
			System.out.println("after adding ONE of kw2's occs to search results: " + top5SearchResultOccs);
			insertLastOccurrence(top5SearchResultOccs); // used for putting the recently added Occurrence in the right spot
			System.out.println("occs sorted: " + top5SearchResultOccs);
			
		}
		
		/* Start extracting document names from the Occs result List and add them to the Docs result List*/
		// If there are no Occurrences in the search result, return null
		if (top5SearchResultOccs == null) {
			return null;
		}
		// while the size of the Docs result List doesn't equal 5 AND all the Occurrences in Occs haven't been sifted
		//		through, keep adding documents
		int i = 0;
		while (top5SearchResultDocs.size() != 5 && i != top5SearchResultOccs.size()) {
			// If the document isn't in the List, add it
			String currentOccDoc = top5SearchResultOccs.get(i).document;
			if (!top5SearchResultDocs.contains(currentOccDoc)){
				top5SearchResultDocs.add(currentOccDoc);
			}
			// Go on to the next Occurrence
			i++;
		}
		
		// Return the Docs result List
		return top5SearchResultDocs;
	}
}
