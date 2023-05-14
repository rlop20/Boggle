package views;
/* Written by: Robert Lopez

 * Description:
 * A terminal based game for Boggle. 
 */

import java.io.FileNotFoundException;
import java.util.ArrayList; // import the ArrayList class
import java.util.Scanner; // import scanner class

import model.Boggle;
import model.DiceTray;

public class BoggleConsole {

	// print out welcome and board
	public static void main(String[] args) throws FileNotFoundException {
		String words = "";
		String[] wordsSplit = new String[20];
		ArrayList<String> wordsList = new ArrayList<>();
		System.out.println("Play one game of Boggle\n"); // starting prompt
		DiceTray tray = new DiceTray(); // get a random tray of words
		Boggle game = new Boggle(tray.getBoard()); // give boggle object the tray we are using
		tray.printBoard(); // print out the tray we are using
		System.out.println("\nEnter words or ZZ to quit:\n"); // instructions to quit game
		Scanner getWords = new Scanner(System.in); // get user input

		// while user has not typed "ZZ", keep getting input and add it to a list.
		while (words.equals("zz") != true) {
			words = getWords.nextLine().toLowerCase();
			if (words.equals("zz") != true && words.contains(" ") == false) {
				wordsList.add(words);
			} else if (words.contains(" ")) {
				wordsSplit = words.split("\\s+");
				for (int i = 0; i < wordsSplit.length; i++) {
					wordsList.add(wordsSplit[i]);
				}
			}
		}

		// get all possible words that can be found
		game.getAllWords();

		// check if the words the user typed are valid
		game.checkWords(wordsList);

		// print out ending statistics
		System.out.println("\nYour score: " + game.getScore() + "\n");
		System.out.println("Words you found: \n================");
		//game.printWordsFound();
		System.out.println("\n\nIncorrect words: \n================");
		game.getIncorrect();
		System.out.println("\n\nYou could have found " + game.getAllWords() + " more words.");
		System.out.println("The computer found all your words plus these:");
		System.out.println("===============================================");
		//game.printAllWords();

	}
}

///**
//* adds all words from 
//* 
//* @param None
//* @return found, an array list which contains all words found
//*/
//public ArrayList<String> printWordsFound() {
//	for (int i = 0; i < correct.size(); i++) {
//		found.add(correct.get(i));
//	}
//	return found;
//}

//public String printAllWords() throws FileNotFoundException {
//File file = new File("./src/BoggleWords.txt");
//sc = new Scanner(file);
//int counter = 0;
//allWords = 0;
//while (sc.hasNextLine()) {
//	curr = sc.nextLine();
//	if (Tray.found(curr) && correct.contains(curr) == false) {
//		// System.out.print(curr + " ");
//		counter++;
//		allWords++;
//		all.add(curr);
//	}
//	if (counter == 10) {
//		// System.out.println();
//		counter = 0;
//	}
//}
//return all.toString();
//}

//public String getAllWordsFound() {
//return all.toString();
//}

//private ArrayList<String> all = new ArrayList<>();
