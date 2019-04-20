import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class BoggleText {
    public static void main(String[] args) throws FileNotFoundException {
        // create game
        Game g = new Game();
        // create scanner for user input
        Scanner keyboard = new Scanner(System.in);
        // variables for user input
        int row, col;
        String input;
        // create flag to end game
        boolean stop = false;

        while (!stop) {
            // print board
            System.out.println(g);
            // prompt user for choices
            System.out.print("(s)elect, (d)eselect, (l)ist selected, " +
                    "(c)lear selected, (t)est selected, (e)nd: ");
            // get choice
            input = keyboard.nextLine();
            // select
            if (input.equalsIgnoreCase("s")) {
                // prompt for row & column
                System.out.print("row / column [r c]: ");
                // get row, col from input
                row = keyboard.nextInt();
                col = keyboard.nextInt();
                input = keyboard.nextLine(); // clr new line left in buffer
                // test if the r/c combination is a valid move
                if (g.isValidSelection(row, col)) {
                    // add tile to selected tiles
                    g.addToSelected(row, col);
                } else {
                    System.out.println("Invalid selection! Please select " +
                            "a letter adjacent to the previously " +
                            "selected letter.");
                }
            }
            // deselect
            else if (input.equalsIgnoreCase("d")) {
                // prompt for row & column
                System.out.print("row / column [r c]: ");
                // get row, col from input
                row = keyboard.nextInt();
                col = keyboard.nextInt();
                input = keyboard.nextLine(); // clr new line left in buffer
                // remove tile from selected tiles
                g.removeFromSelected(row, col);
            }
            // list currently selected tiles
            else if (input.equalsIgnoreCase("l")) {
                ArrayList<Tile> selected = g.getSelectedTiles();
                System.out.println(selected);
            }
            // clear currently selected tiles
            else if (input.equalsIgnoreCase("c")) {
                g.clearSelected();
            } else if (input.equalsIgnoreCase("t")) {
                g.testSelected();
            }
            // end game
            else if (input.equalsIgnoreCase("e")) {
                stop = true;
            }
        }
    }
}