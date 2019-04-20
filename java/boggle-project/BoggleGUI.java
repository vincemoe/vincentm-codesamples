import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * Class to run the GUI for the boggle game
 *
 * @author Vincent Moeykens
 */
public class BoggleGUI extends Application {
    // Declare class fields
    final private int HEIGHT = 600, WIDTH = 600;
    private Game g;
    private VBox leftPane, topPane, rightPane, bottomPane;
    private HBox bottomPaneButtons;

    /**
     * The main method that launches the GUI
     *
     * @param args Arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * The method that contains all of the declarations for the GUI
     *
     * @param mainStage The main stage
     * @throws FileNotFoundException If the dictionary file can not be found
     */
    @Override
    public void start(Stage mainStage) throws FileNotFoundException {
        // Create a new game object and set it
        g = new Game();

        // Add controls here
        Label wordLabel = new Label("Words:");
        Label titleLabel = new Label("Boggle!\nBy Vincent Moeykens");
        Label lettersSelected = new Label();
        Label lastLetterTitle = new Label("Last Letter:");
        Label lastLetterLabel = new Label();
        Label scoreLabel = new Label("Score: 0");
        Button resetButton = new Button("Clear");
        Button testWord = new Button("Test");
        Button exit = new Button("Exit");
        Button newGame = new Button("New Game");


        // Set up containers
        CustomTilePane centerPane = new CustomTilePane(g.getBoard(), g, lettersSelected, lastLetterLabel);
        centerPane.setAlignment(Pos.CENTER);

        leftPane = new VBox(wordLabel);
        topPane = new VBox(titleLabel, lettersSelected);
        topPane.setAlignment(Pos.CENTER);
        rightPane = new VBox(lastLetterTitle, lastLetterLabel);
        bottomPaneButtons = new HBox(resetButton, testWord, newGame, exit);
        bottomPane = new VBox(scoreLabel, bottomPaneButtons);
        bottomPane.setAlignment(Pos.CENTER);
        bottomPaneButtons.setAlignment(Pos.CENTER);

        // Set button listeners
        // Reset game
        resetButton.setOnMouseClicked(e -> {
            // Clear selected
            g.clearSelected();
            centerPane.clearLettersSelected(g.getBoard());
            // Reset labels
            lastLetterLabel.setText("");
        });

        // Exit game
        exit.setOnMouseClicked(e -> System.exit(0));

        // Test word
        testWord.setOnMouseClicked(e -> {
            try {
                // If the word is valid
                if (g.testSelected()) {
                    // Add to list and clear pane and update score
                    wordLabel.setText(generateWordLabel(g.getWords()));
                    centerPane.clearLettersSelected(g.getBoard());
                    scoreLabel.setText("Score: " + g.getScore());
                    lastLetterLabel.setText("");
                } else if (g.getWords().contains(Dictionary.createString(g.getSelectedTiles()))) {
                    // Clear selected letters and show error message
                    centerPane.clearLettersSelected(g.getBoard());
                    lettersSelected.setText(Dictionary.createString(g.getSelectedTiles()) + " has already been played!");
                    g.clearSelected();
                    lastLetterLabel.setText("");
                } else {
                    // Clear selected letters and show error message
                    centerPane.clearLettersSelected(g.getBoard());
                    lettersSelected.setText(Dictionary.createString(g.getSelectedTiles()) + " is not a valid word!");
                    g.clearSelected();
                    lastLetterLabel.setText("");
                }
            } catch (FileNotFoundException f) {
                System.out.print(f);
            }
        });

        // New Game
        newGame.setOnMouseClicked(e -> {
            // Clear the selected words
            g.clearSelected();
            // Reset the score
            g.setScore(0);
            // Reset the words
            g.resetWords();
            // Clear labels
            lastLetterLabel.setText("");
            lettersSelected.setText("");
            wordLabel.setText(generateWordLabel(g.getWords()));
            scoreLabel.setText("Score: " + g.getScore());
            // Create a new board and populate
            g.newBoard();
            centerPane.populatePane(g.getBoard(), lastLetterLabel);
        });

        // Set up the main pane for the scene
        BorderPane mainPane = new BorderPane();

        // Add layouts to borderpane regions
        mainPane.setCenter(centerPane.getHolder());
        mainPane.setLeft(leftPane);
        mainPane.setTop(topPane);
        mainPane.setRight(rightPane);
        mainPane.setBottom(bottomPane);

        // Create the scene with the main pane as the root node
        Scene mainScene = new Scene(mainPane, WIDTH, HEIGHT);

        // Add css file to scene
        mainScene.getStylesheets().add("style.css");

        // Add the scene to the main stage
        mainStage.setScene(mainScene);

        // Prevent the window from being resized
        mainStage.setResizable(false);

        // Show the window
        mainStage.show();
    }

    /**
     * Generates a word label based on an arraylist of words
     *
     * @param words The arraylist of words
     * @return A string suitable for javafx labels
     */
    private static String generateWordLabel(ArrayList<String> words) {
        String wordLabel = "Words:\n";
        for (String word : words) {
            wordLabel = wordLabel + word + "\n";
        }
        return wordLabel;
    }
}