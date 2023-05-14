/*
 * Author: Robert Lopez
 * Assignment: Boggle 
 * Course: CSC 335, University of Arizona
 * Instructor: Rick Mercer
 * 
 * Description: 
 * This class is used to simulate the rules of Boggle. 
 * 
 */
package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList; // import the ArrayList class

public class Boggle {

	private static Scanner sc;
	private DiceTray Tray;
	private ArrayList<String> correct = new ArrayList<>();
	private ArrayList<String> incorrect = new ArrayList<>();
	private ArrayList<String> found = new ArrayList<>();
	private String curr;
	private int allWords;
	private int score = 0;

	/**
	 * Construct a file object to read the BoggleWords text file. Scan the file
	 * using a scanner object. Create a dice tray giving it the 2D param tray.
	 * 
	 * @param tray The 2D array of characters used for the game
	 */

	public Boggle(char[][] tray) throws FileNotFoundException {
		Tray = new DiceTray(tray);
	}

	/**
	 * Check if the words from the user are bigger than len 2. Check if this.found
	 * contains any of the words that way the program can increment the score or add
	 * to incorrect list.
	 * 
	 * @param words, an array list of user input words
	 */
	public void checkWords(ArrayList<String> words) {
		if (words.size() > 0) {
			for (int i = 0; i < words.size(); i++) {
				if (found.contains(words.get(i))) {
					correct.add(words.get(i));
					score++;
				} else {
					incorrect.add(words.get(i));
				}
			}
		}
	}

	/**
	 * Get all words found on the current tray from the file object.
	 * 
	 * @param None
	 * @return score, a total of all words found.
	 */
	public int getAllWords() throws FileNotFoundException {
		File file = new File("./src/BoggleWords.txt");
		sc = new Scanner(file);
		while (sc.hasNextLine()) {
			curr = sc.nextLine();
			if (Tray.found(curr) && correct.contains(curr) == false) {
				found.add(curr);
				allWords++;
			}
		}
		return score;
	}

	/**
	 * Remove all elements from found that are in the array correct This will ensure
	 * the program will print out all words the user missed.
	 * 
	 * @param None
	 * @return found, updated.
	 */
	public ArrayList<String> getAllNotFound() {
		for (int i = 0; i < found.size(); i++) {
			if (correct.contains(found.get(i)))
				found.remove(i);
		}
		return found;
	}

	public ArrayList<String> getIncorrect() {
		return incorrect;
	}

	public int getScore() {
		return score;
	}

	public int getAllWordsTotal() {
		return allWords;
	}

	public ArrayList<String> getCorrect() {
		return correct;
	}

}
