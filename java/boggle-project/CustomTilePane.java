import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

/**
 * This class is a custom representation of the center tile board
 */
public class CustomTilePane{
    // Base gridpane
    private GridPane holder;
    private Game g;
    private Label lettersSelected, lastLetter;

    /**
     * Constructor
     *
     * @param board Board object
     * @param g Game object
     * @param lettersSelected Label for current letters selected
     * @param lastLetter Label for last letter
     */
    public CustomTilePane(Board board, Game g, Label lettersSelected, Label lastLetter){
        this.holder = new GridPane();
        // Populate the gridpane and set the current holder to it
        populatePane(board, lastLetter);

        // Style the grid holder
        this.holder.setHgap(10);
        this.holder.setVgap(10);
        this.holder.setGridLinesVisible(false);

        // Set the game
        this.g = g;

        // Set the label for the letters selected
        this.lettersSelected = lettersSelected;
        this.lastLetter = lastLetter;
    }

    /**
     * Populates the visual board
     *
     * @param board Board object
     * @param lastLetter Last letter label
     */
    public void populatePane(Board board, Label lastLetter){
        for(int row = 0; row < board.getNumRows(); row ++){
            for(int col = 0; col < board.getNumCols(); col++){
                addGridCell(board, col, row, lastLetter);
            }
        }
    }

    /**
     * Adds actual cells to grid and gives each tile on click listeners
     *
     * @param board Board object
     * @param col Current column for cell
     * @param row Current row for cell
     * @param lastLetter Last letter label
     */
    private void addGridCell(Board board, int col, int row, Label lastLetter){
        Label label = new Label(board.getTileAt(row, col).getLetterShowing());
        label.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        label.setId("cell_label");

        label.setOnMouseClicked(e -> {
            if(g.getBoard().getTileAt(row, col).getLetterShowing().equals(lastLetter.getText())) {
                g.removeFromSelected(row, col);
                label.setId("cell_label");
                lettersSelected.setText(g.getSelectedTiles().toString());
                lastLetter.setText("");
            }else if(g.isValidSelection(row, col)){
                label.setId("cell_label_selected");
                lastLetter.setText(label.getText());
                g.addToSelected(row, col);
                lettersSelected.setText(g.getSelectedTiles().toString());
            }
        });

        holder.add(label, col, row);
    }

    /**
     * Clears the entire board of selected letters
     * @param board Board object
     */
    public void clearLettersSelected(Board board){
        lettersSelected.setText("");
        populatePane(board, lastLetter);
    }

    /**
     * Sets the alignment of the board
     * @param position Alignment
     */
    public void setAlignment(Pos position){
        this.holder.setAlignment(position);
    }

    /**
     * Gets holder.
     *
     * @return The gridpane holder.
     */
    public GridPane getHolder() {
        return holder;
    }
}
