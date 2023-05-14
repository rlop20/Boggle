/*
 * Author: Robert Lopez
 * Assignment: BoggleGUI
 * Course: CSC 335, University of Arizona
 * Instructor: Rick Mercer
 * 
 * Description: 
 * This class is used for creating a GUI for the Boggle class. 
 * 
 */

package views;

import java.io.FileNotFoundException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.Boggle;
import model.DiceTray;
import java.util.ArrayList; // import the ArrayList class

public class BoggleGUI extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	ArrayList<String> wordsList = new ArrayList<>();
	String[] wordsArray;
	private Boggle game;
	private DiceTray tray;
// Graphical Nodes
	private TextArea diceTrayArea;
	private TextArea resultArea;
	private TextArea userInputArea;
	private GridPane everything = new GridPane();
	private Button button = new Button("New Game");
	private Button button2 = new Button("End game");
	private Label label1 = new Label("Enter attempts below:");
	private Label label2 = new Label("Results:");

	public void start(@SuppressWarnings("exports") Stage stage) throws FileNotFoundException {

		layoutWindow(); // Set up window with buttons and labels in row one, textareas in row 2
		Scene scene = new Scene(everything, 900, 330);
		Parent root = scene.getRoot();
		root.setStyle("-fx-font: 13 \"Arial\"; ");
		stage.setScene(scene);
		stage.show();
		startNewGame();
	}

	private void startNewGame() throws FileNotFoundException {
		tray = new DiceTray();
		userInputArea.setText("");
		game = new Boggle(tray.getBoard());
		diceTrayArea.setText(tray.printBoard());
		try {
			game.getAllWords();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
	}

	private void layoutWindow() {
// Layout the GUI.
		GridPane buttonPane = new GridPane();
		GridPane textPane = new GridPane();
		everything.setHgap(3);
		everything.setVgap(3);
		buttonPane.setHgap(12);
		textPane.setHgap(12);
		buttonPane.add(button, 1, 1);
		buttonPane.add(button2, 2, 1);
		buttonPane.add(label1, 8, 1);
		buttonPane.add(label2, 11, 1);
		everything.add(buttonPane, 1, 1);
// text areas
		diceTrayArea = new TextArea();
		diceTrayArea.setEditable(false);
		EventHandler<ActionEvent> buttonHandler = new buttonHandler();
		EventHandler<ActionEvent> buttonHandler2 = new buttonHandler2();
		button.setOnAction(buttonHandler);
		button2.setOnAction(buttonHandler2);
		diceTrayArea.setMaxWidth(230);
		diceTrayArea.setFont(new Font("Courier New", 24));
		textPane.add(diceTrayArea, 1, 2);
// Set the font of diceTrayArea to "Courier New", 24
		userInputArea = new TextArea();
		userInputArea.setMaxWidth(150);
		textPane.add(userInputArea, 2, 2);
// Set the border color of diceTrayAre
		userInputArea.setStyle("-fx-border-color: red; -fx-font: 18 \"Arial\";");
// Set wrap text to true 
		userInputArea.setWrapText(true);
// Make userInputArea the least width
		resultArea = new TextArea();
		resultArea.appendText("Click End Game to see results");
// Make resultArea the widest width
		resultArea.setMaxWidth(450);
		resultArea.setWrapText(true);
		textPane.add(resultArea, 3, 2);
		everything.add(textPane, 1, 2);
	}

	private class buttonHandler implements EventHandler<ActionEvent> {
		@Override // Another private inner class, another use of the Java interface
		public void handle(ActionEvent event) {
			wordsArray = new String[100];
			userInputArea.setEditable(true);
			diceTrayArea.clear();
			resultArea.setText("");
			resultArea.appendText("Click End Game to see results");
			try {
				startNewGame();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

	private class buttonHandler2 implements EventHandler<ActionEvent> {
		@Override // Another private inner class, another use of the Java interface
		public void handle(ActionEvent event) {
			userInputArea.setEditable(false); // set editable for input to false until game resets
			wordsList.clear(); // clear wordsList that way previous incorrect words delete
			String words = ""; // use to hold all chars from user input
			// this for loop will add all chars from user input area to words
			for (int i = 0; i < userInputArea.getLength(); i++) {
				words += userInputArea.getText(i, i + 1);
			}
			// split the words by spaces into wordsArray[]
			wordsArray = words.split(" ");
			// this for loop will add all words from the array to wordsList array List
			for (int i = 0; i < wordsArray.length; i++) {
				if (wordsArray[i].equals(" ") == false) {
					wordsList.add(wordsArray[i]);
				}
			}
			// run checkWords to generate all possible words in boggle
			game.checkWords(wordsList);
			// begin to show results on GUI
			resultArea.setText("");
			resultArea.appendText("Your score: " + game.getScore());
			resultArea.appendText("\n");
			resultArea.appendText("\n");
			resultArea.appendText("Words you found: \n");
			for (int i = 0; i < game.getScore(); i++) {
				resultArea.appendText(game.getCorrect().toArray()[i] + " ");
			}
			resultArea.appendText("\n");
			resultArea.appendText("\n");
			resultArea.appendText("Incorrect words:\n");
			for (int i = 0; i < game.getIncorrect().size(); i++) {
				resultArea.appendText(game.getIncorrect().toArray()[i] + " ");
			}
			resultArea.appendText("\n");
			resultArea.appendText("\n");
			resultArea.appendText("You could have found " + game.getAllWordsTotal() + " more words: \n");
			// This for loop prints out all possible words without correct words
			for (int i = 0; i < game.getAllNotFound().size(); i++) {
				resultArea.appendText(game.getAllNotFound().toArray()[i] + " ");
			}
		}
	}
}
