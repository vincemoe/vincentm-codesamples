import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class to take a dictionary file and and to have
 * methods to check if words are valid
 *
 * @author Vincent Moeykens
 */
public class Dictionary {
    // Initialize class level variables
    private File dictionaryFile;

    /**
     * Constructor that takes a filename
     *
     * @param fileName String of the filepath
     */
    public Dictionary(String fileName){
        this.dictionaryFile = new File(fileName);
    }

    /**
     * This method takes a arraylist of tiles and determines if they form a valid word
     *
     * @param tiles An arraylist of tile objects
     * @return If the arraylist forms a valid word or not
     * @throws FileNotFoundException If the file is not found
     */
    public boolean isValidWord(ArrayList<Tile> tiles) throws FileNotFoundException {
        // Create a string of words out of the arraylist of tiles
        String word = createString(tiles);
        // Create a scanner object for the file
        Scanner file = new Scanner(dictionaryFile);
        // Loop through dictionary and determine of the word formed is a real word
        while(file.hasNext()){
            // If the next word in the file is equal to the word then return true
            if (file.nextLine().equals(word)){
                return true;
            }
        }
        // If none of them match then return false
        return false;
    }

    /**
     * Creates a string out of an arraylist of tiles
     *
     * @param tiles ArrayList of tiles
     * @return The word the tiles form
     */
    public static String createString(ArrayList<Tile> tiles){
        // Create a string of words out of the arraylist of tiles
        String word = "";
        for (int i = 0; i < tiles.size(); i++){
            word += tiles.get(i);
        }
        return word.toLowerCase();
    }
}
