import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * This class is the instantiation of the game and provides the interface between the rest of
 * the model and the front end UI class
 *
 * @author Vincent Moeykens
 */
public class Game {
    // Initialize variables
    private ArrayList<Tile> selectedLetters = new ArrayList<>();
    private ArrayList<String> words = new ArrayList<>();
    private int score;
    private Board board;
    private Dictionary dict;

    /**
     * No-arg constructor that creates a new board and sets the dictionary from as et string
     */
    public Game(){
        // Create a new board
        this.board = new Board();
        // Create a new dictionary
        this.dict = new Dictionary("C:\\Users\\Vincent Moeykens\\Documents\\2018-2019 Semester\\Semester 1\\CS 110\\BoggleProject\\src\\dict.txt");
        // Set the score to zero
        this.score = 0;
    }

    /**
     * Constructor that takes a board and dictionary argument and then sets the score to 0
     * @param board
     * @param dictionary
     */
    public Game(Board board, String dictionary){
        // Set the board
        this.board = board;
        // Create a new dictionary
        this.dict = new Dictionary(dictionary);
        // Set the score to 0
        this.score = 0;
    }


    /**
     * This method takes a row and column number and determines if the row and column selection
     * is a valid move
     *
     * @param row The row value of the selected location
     * @param col The column value of the selected location
     * @return Whether the move is a valid boggle move or not
     */
    public boolean isValidSelection(int row, int col){
        // First we check if the move is within the ranges of the board
        if(row > board.getNumRows() || col > board.getNumCols() || row < 0 || col < 0) return false;
        // then we determine if this is the first letter or not, if it is the first one, return true
        if (selectedLetters.size() == 0) return true;
        // Next we check if the tile has already been played
        for (int i = 0; i < selectedLetters.size(); i++){
            if(row == selectedLetters.get(i).getRowNum() && col == selectedLetters.get(i).getColNum()){
                return false;
            }
        }
        // If there have been letters before it, first we determine which tile it was
        Tile lastTile = selectedLetters.get(selectedLetters.size() - 1);

        // Next we compare the selected location to the previous tile location

        // Finally we compare the selected location to the diagonals
        return((row == (lastTile.getRowNum() - 1) && col == (lastTile.getColNum() - 1))
                || (row == (lastTile.getRowNum() - 1) && col == (lastTile.getColNum() + 1))
                || (row == (lastTile.getRowNum() + 1) && col == (lastTile.getColNum() - 1))
                || (row == (lastTile.getRowNum() + 1) && col == (lastTile.getColNum() + 1))
                || (row == (lastTile.getRowNum() + 1) && col == (lastTile.getColNum()))
                || (row == (lastTile.getRowNum() - 1) && col == (lastTile.getColNum()))
                || (col == (lastTile.getColNum() + 1) && row == (lastTile.getRowNum()))
                || (col == (lastTile.getColNum() - 1) && row == (lastTile.getRowNum())));
    }

    /**
     * Adds a tile to the list of selected letters
     *
     * @param row The row value of the tile
     * @param col The column value of the tile
     */
    public void addToSelected(int row, int col){
        // Add the selected tile to the list of selected letters
        selectedLetters.add(board.getTileAt(row, col));
//        board.getTileAt(row, col).setSelectedByPlayer(true);
    }

    /**
     * This method lets you deselect the last Tile you selected
     *
     * THIS OPERATES UNDER THE ASSUMPTION THAT YOU CAN ONLY DESELECT THE LAST ITEM
     * YOU SELECTED
     *
     * @param row The row value of the item to deselect
     * @param col The column value of the item to deselect
     */
    public void removeFromSelected(int row, int col){
        // First we determine what the last tile to be played was
        Tile lastTile = selectedLetters.get(selectedLetters.size() - 1);

        // Next we determine if the selected tile is the most recent one to be played
        if(row == lastTile.getRowNum() && col == lastTile.getColNum()){
            // If so then remove it from the game
            selectedLetters.remove(selectedLetters.size() - 1);
        }else{
            // If not then print an error
            System.out.println("You can only remove the most recently played tile!");
        }
    }

    /**
     * Gets the currently selected tiles
     *
     * @return The current selected tiles
     */
    public ArrayList<Tile> getSelectedTiles(){
        return this.selectedLetters;
    }

    /**
     * Clears the current list of selected letters
     */
    public void clearSelected(){
        this.selectedLetters = new ArrayList<Tile>();
    }

    /**
     * Determines if the letters form a valid word and if so,
     * adds it to the list of words
     *
     * @throws FileNotFoundException If the dictionary file isn't found
     */
    public boolean testSelected() throws FileNotFoundException {
        // Determine if the word is valid
        if(dict.isValidWord(selectedLetters) && !words.contains(Dictionary.createString(selectedLetters))){
            // Add the word to the list of words
            words.add(Dictionary.createString(selectedLetters));
            // Add to the score
            score += new Word(selectedLetters).getPoints();
            // Clear the board
            clearSelected();
            return true;
        }else if(words.contains(Dictionary.createString(selectedLetters))){
            System.out.println("You already selected the word " + Dictionary.createString(selectedLetters));
            return false;
        } else{
            // Return that the word isn't valid
            System.out.println("The word \"" + Dictionary.createString(selectedLetters) + "\" is not a valid word!");
            return false;
        }
    }

    /**
     * toString method that prints the board, the selected letters, the words, and the score
     *
     * @return A string representation of the game at its current state
     */
    @Override
    public String toString() {
        return board
                + "\nselected: " + selectedLetters
                + "\nwords: " + words
                + "\nscore: " + score + "\n";
    }


    /**
     * Gets board.
     *
     * @return Value of board.
     */
    public Board getBoard() {
        return board;
    }


    /**
     * Gets words.
     *
     * @return Value of words.
     */
    public ArrayList<String> getWords() {
        return words;
    }

    public void resetWords(){
        words = new ArrayList<String>();
    }


    /**
     * Gets score.
     *
     * @return Value of score.
     */
    public int getScore() {
        return score;
    }

    /**
     * Sets new score.
     *
     * @param score New value of score.
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Creates a new board
     */
    public void newBoard(){
        this.board = new Board();
    }
}
