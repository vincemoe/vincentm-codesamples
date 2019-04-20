import java.util.ArrayList;

/**
 * This class represents a word entered by a player
 *
 * @author Vincent Moeykens
 */
public class Word {
    // Initialize variables
    private ArrayList<Tile> characters;
    private int points;

    /**
     * Contructor that takes an arraylist of chars
     * @param characters An arraylist of characters that the user has entered
     */
    public Word(ArrayList<Tile> characters){
        this.characters = characters;
        this.points = getPoints();
    }

    /**
     * Calculates the number of points the word is worth
     *
     * @return The amount of
     */
    public int getPoints(){
        if(toString().length() < 3){
            return 0;
        }else if(toString().length() == 3 || toString().length() == 4){
            return 1;
        }else if(toString().length() == 5){
            return 2;
        }else if(toString().length() == 6){
            return 3;
        }else if(this.characters.size() == 7){
            return 5;
        }else{
            return 11;
        }
    }

    /**
     * This method creates a string out of the characters
     *
     * @return The characters as a string
     */
    @Override
    public String toString() {
        String outputString = "";
        for (int i = 0; i < characters.size(); i++){
            outputString += characters.get(i);
        }
        return outputString;
    }
}
