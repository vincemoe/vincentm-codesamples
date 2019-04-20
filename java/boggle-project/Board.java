import java.util.ArrayList;
import java.util.Random;

/**
 * This class represents the playing board and the letters showing on the board
 *
 * @author Vincent Moeykens
 */
public class Board {
    // Initialize variables
    private ArrayList<ArrayList<Tile>> board = new ArrayList<ArrayList<Tile>>();
    private ArrayList<Die> dice = new ArrayList<Die>();
    private final int numRows = 4, numCols = 4;

    /**
     * Constructor that takes an arraylist of die
     *
     * @param dice Arraylist of dice
     */
    public Board(ArrayList<Die> dice){
        this.dice = dice;
    }

    public Board(){
        this.dice.add(new Die("R", "I", "F", "O", "B", "X"));
        this.dice.add(new Die("I", "F", "E", "H", "E", "Y"));
        this.dice.add(new Die("D", "E", "N", "O", "W", "S"));
        this.dice.add(new Die("U", "T", "O", "K", "N", "D"));
        this.dice.add(new Die("H", "M", "S", "R", "A", "O"));
        this.dice.add(new Die("L", "U", "P", "E", "T", "S"));
        this.dice.add(new Die("A", "C", "O", "T", "O", "A"));
        this.dice.add(new Die("Y", "L", "G", "K", "U", "E"));
        this.dice.add(new Die("Qu", "B", "M", "J", "O", "A"));
        this.dice.add(new Die("E", "H", "I", "S", "P", "N"));
        this.dice.add(new Die("V", "E", "T", "I", "G", "N"));
        this.dice.add(new Die("B", "A", "L", "I", "Y", "T"));
        this.dice.add(new Die("E", "Z", "A", "V", "N", "D"));
        this.dice.add(new Die("R", "A", "L", "E", "S", "C"));
        this.dice.add(new Die("U", "W", "I", "L", "R", "G"));
        this.dice.add(new Die("P", "A", "C", "E", "M", "D"));

        // Populate the board with values
        populateBoard();
    }

    /**
     * Populates the board by using the die in the list
     */
    private void populateBoard(){
        // Create a new random object
        Random r = new Random();

        // Iterate through the list and add tiles
        for(int i = 0; i < numRows; i++){
            // Create a new arraylist of rows
            ArrayList<Tile> row = new ArrayList<Tile>();

            for (int e = 0; e < numCols; e++){
                // Add random tile to row
                row.add(new Tile(this.dice.get(r.nextInt(this.dice.size())).getRandomSide(), i, e));
            }
            // Add the row to the board
            this.board.add(row);
        }
    }

    /**
     * This method prints out the boggle grid
     *
     * @return A string representing the entire board
     */
    @Override
    public String toString(){
        String outputString = "";
        for(int i = 0; i < board.size(); i++){
            for (int e = 0; e < board.get(i).size(); e++){
                // Determine if the string is Qu, if it is than only add one space to keep everything in line on the board
                if (board.get(i).get(e).toString().equals("Qu")) outputString += (board.get(i).get(e) + " ");
                // If it isn't Qu, then
                else outputString += (board.get(i).get(e) + "  ");
            }
            outputString += "\n";
        }
        return outputString;
    }


    /**
     * Gets numCols.
     *
     * @return Value of numCols.
     */
    public int getNumCols() {
        return numCols;
    }

    /**
     * Gets numRows.
     *
     * @return Value of numRows.
     */
    public int getNumRows() {
        return numRows;
    }

    /**
     * Returns the tile at a set of coordinates
     *
     * @param row The row coordinate
     * @param col The column coordinate
     * @return The tile at that location
     */
    public Tile getTileAt(int row, int col){
        return board.get(row).get(col);
    }
}
