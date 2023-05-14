/*
 * Author: Robert Lopez and Rick Mercer
 * Assignment: Boggle 
 * Course: CSC 335, University of Arizona
 * Instructor: Rick Mercer
 * 
 * Description: 
 * This class is used to simulate the dice tray of Boggle
 * 
 */
package model;

import java.util.Random;
import java.util.ArrayList;
import java.util.List;

public class DiceTray {

	private char[] boardString;
	private String board;
	private char[][] path;
	private char[][] theBoard;
	public static final char TRIED = '@';
	public static final char PART_OF_WORD = '!';
	private String attempt;
	private int index;
	public static final int SIZE = 4;
	public static final int NUMBER_SIDES = 6;

	/**
	 * Construct a tray of dice using a hard coded 2D array of chars. Use this for
	 * testing
	 * 
	 * @param newBoard The 2D array of characters used in testing
	 */
	public DiceTray(char[][] newBoard) {
		theBoard = newBoard;
	}

	public DiceTray() {
		theBoard = new char[4][4];
		// Instance of random class
		Random rand = new Random();
		// create die
		char[] die0 = { 'L', 'R', 'Y', 'T', 'T', 'E' };
		char[] die1 = { 'V', 'T', 'H', 'R', 'W', 'E' };
		char[] die2 = { 'E', 'G', 'H', 'W', 'N', 'E' };
		char[] die3 = { 'S', 'E', 'O', 'T', 'I', 'S' };
		char[] die4 = { 'A', 'N', 'A', 'E', 'E', 'G' };
		char[] die5 = { 'I', 'D', 'S', 'Y', 'T', 'T' };
		char[] die6 = { 'O', 'A', 'T', 'T', 'O', 'W' };
		char[] die7 = { 'M', 'T', 'O', 'I', 'C', 'U' };
		char[] die8 = { 'A', 'F', 'P', 'K', 'F', 'S' };
		char[] die9 = { 'X', 'L', 'D', 'E', 'R', 'I' };
		char[] die10 = { 'H', 'C', 'P', 'O', 'A', 'S' };
		char[] die11 = { 'E', 'N', 'S', 'I', 'E', 'U' };
		char[] die12 = { 'Y', 'L', 'D', 'E', 'V', 'R' };
		char[] die13 = { 'Z', 'N', 'R', 'N', 'H', 'L' };
		char[] die14 = { 'N', 'M', 'I', 'H', 'U', 'Q' };
		char[] die15 = { 'O', 'B', 'B', 'A', 'O', 'J' };

		List<char[]> placed = new ArrayList<>();

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (placed.contains(die0) == false) {
					theBoard[i][j] = die0[rand.nextInt(6)];
					placed.add(die0);
				} else if (placed.contains(die1) == false) {
					theBoard[i][j] = die1[rand.nextInt(6)];
					placed.add(die1);
				} else if (placed.contains(die2) == false) {
					theBoard[i][j] = die2[rand.nextInt(6)];
					placed.add(die2);
				} else if (placed.contains(die3) == false) {
					theBoard[i][j] = die3[rand.nextInt(6)];
					placed.add(die3);
				} else if (placed.contains(die4) == false) {
					theBoard[i][j] = die4[rand.nextInt(6)];
					placed.add(die4);
				} else if (placed.contains(die5) == false) {
					theBoard[i][j] = die5[rand.nextInt(6)];
					placed.add(die5);
				} else if (placed.contains(die6) == false) {
					theBoard[i][j] = die6[rand.nextInt(6)];
					placed.add(die6);
				} else if (placed.contains(die7) == false) {
					theBoard[i][j] = die7[rand.nextInt(6)];
					placed.add(die7);
				} else if (placed.contains(die8) == false) {
					theBoard[i][j] = die8[rand.nextInt(6)];
					placed.add(die8);
				} else if (placed.contains(die9) == false) {
					theBoard[i][j] = die9[rand.nextInt(6)];
					placed.add(die9);
				} else if (placed.contains(die10) == false) {
					theBoard[i][j] = die8[rand.nextInt(6)];
					placed.add(die10);
				} else if (placed.contains(die11) == false) {
					theBoard[i][j] = die11[rand.nextInt(6)];
					placed.add(die11);
				} else if (placed.contains(die12) == false) {
					theBoard[i][j] = die12[rand.nextInt(6)];
					placed.add(die12);
				} else if (placed.contains(die13) == false) {
					theBoard[i][j] = die13[rand.nextInt(6)];
					placed.add(die13);
				} else if (placed.contains(die14) == false) {
					theBoard[i][j] = die14[rand.nextInt(6)];
					placed.add(die14);
				} else if (placed.contains(die15) == false) {
					theBoard[i][j] = die15[rand.nextInt(6)];
					placed.add(die15);
				}
			}
		}
	}

	public String printBoard() {
		boardString = new char[20];
		board = "";
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				boardString[i] = theBoard[i][j];
				board += "" + theBoard[i][j];
			}
			board += "\n";
		}
		return board;
	}

	public char[][] getBoard() {
		return theBoard;
	}

	/**
	 * Return true if search is word that can found on the board following the rules
	 * of Boggle
	 * 
	 * @param str A word that may be in the board by connecting consecutive letters
	 * @return True if search is found
	 */
	public boolean found(String str) {
		if (str.length() < 3)
			return false;
		attempt = str.toUpperCase().trim();
		boolean found = false;
		for (int r = 0; r < SIZE; r++) {
			for (int c = 0; c < SIZE; c++)
				if (theBoard[r][c] == attempt.charAt(0)) {
					init();
					found = recursiveSearch(r, c);
					if (found) {
						return true;
					}
				}
		}
		return found;
	}

	// Keep a 2nd 2D array to remember the characters that have been tried
	private void init() {
		path = new char[SIZE][SIZE];
		for (int r = 0; r < SIZE; r++)
			for (int c = 0; c < SIZE; c++)
				path[r][c] = '.';
		index = 0;
	}

	// This is like the Obstacle course escape algorithm.
	// Now we are checking 8 possible directions (no wraparound)
	private boolean recursiveSearch(int r, int c) {
		boolean found = false;

		if (valid(r, c)) { // valid returns true if in range AND one letter was found
			path[r][c] = TRIED;
			if (theBoard[r][c] == 'Q')
				index += 2;
			else
				index++;

			// Look in 8 directions for the next character
			if (index >= attempt.length())
				found = true;
			else {
				found = recursiveSearch(r - 1, c - 1);
				if (!found)
					found = recursiveSearch(r - 1, c);
				if (!found)
					found = recursiveSearch(r - 1, c + 1);
				if (!found)
					found = recursiveSearch(r, c - 1);
				if (!found)
					found = recursiveSearch(r, c + 1);
				if (!found)
					found = recursiveSearch(r + 1, c - 1);
				if (!found)
					found = recursiveSearch(r + 1, c);
				if (!found)
					found = recursiveSearch(r + 1, c + 1);
				// If still not found, allow backtracking to use the same letter in a
				// different location later as in looking for "BATTLING" in this board
				//
				// L T T X // Mark leftmost T as untried after it finds a 2nd T but not the L.
				// I X A X
				// N X X B
				// G X X X
				//
				if (!found) {
					path[r][c] = '.'; // Rick used . to mark the 2nd 2D array as TRIED
					index--; // 1 less letter was found. Let algorithm find the right first (col 2)
				}
			} // End recursive case

			if (found) {
				// Mark where the letter was found. Not required, but could be used to
				// show the actual path of the word that was found.
				path[r][c] = theBoard[r][c];
			}
		}
		return found;
	}

	// Determine if a current value of row and columns can or should be tried
	private boolean valid(int r, int c) {
		return r >= 0 && r < SIZE && c >= 0 && c < SIZE && path[r][c] != TRIED
				&& theBoard[r][c] == attempt.charAt(index);
	}

}