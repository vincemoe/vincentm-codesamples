/**
 * Represents one tile on the boggle board
 *
 * @author Vincent Moeykens
 */
public class Tile {
    // Initializing class level variables
    private String letterShowing;
    private int rowNum, colNum;
    private boolean selectedByPlayer;


    /**
     * Full constructor for the Tile class
     *
     * @param letterShowing The letter that is showing
     * @param rowNum The row location of the tile
     * @param colNum The column location of the tile
     */
    public Tile(String letterShowing, int rowNum, int colNum){
        this.letterShowing = letterShowing;
        this.rowNum = rowNum;
        this.colNum = colNum;
        this.selectedByPlayer = false;
    }


    /**
     * Constructor that takes a character instead of a string as the letter
     * showing and converts it to a string
     *
     * @param letterShowing The letter that is showing
     * @param rowNum The row location of the tile
     * @param colNum The column location of the tile
     */
    public Tile(char letterShowing, int rowNum, int colNum){
        this.letterShowing = Character.toString(letterShowing);
        this.rowNum = rowNum;
        this.colNum = colNum;
    }


    /**
     * Gets colNum.
     *
     * @return Value of colNum.
     */
    public int getColNum() {
        return colNum;
    }

    /**
     * Gets letterShowing.
     *
     * @return Value of letterShowing.
     */
    public String getLetterShowing() {
        return letterShowing;
    }

    /**
     * Gets rowNum.
     *
     * @return Value of rowNum.
     */
    public int getRowNum() {
        return rowNum;
    }


    /**
     * Sets selectedByPlayer.
     *
     * @param selectedByPlayer New value of selectedByPlayer.
     */
    public void setSelectedByPlayer(boolean selectedByPlayer) {
        this.selectedByPlayer = selectedByPlayer;
    }

    /**
     * Gets selectedByPlayer.
     *
     * @return Value of selectedByPlayer.
     */
    public boolean isSelectedByPlayer() {
        return selectedByPlayer;
    }

    /**
     * toString method for the Tile class. This will return just the character
     * so the ArrayList toString can be used to construct full strings
     *
     * @return String representation of the character
     */
    @Override
    public String toString() {
        return letterShowing;
    }
}
