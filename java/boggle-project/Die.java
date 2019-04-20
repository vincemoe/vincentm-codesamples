import java.util.Arrays;
import java.util.Random;

/**
 * Represents a single die on a boggle board
 *
 * @author Vincent Moeykens
 */
public class Die {
    // Initialize variables
    private String[] sides;

    /**
     * Constructor that takes a list of string values for the sides
     * @param sides
     */
    public Die(String ...sides){
        this.sides = sides;
    }

    /**
     * This method gets a random item from the list of sides
     *
     * @return A random side of the die
     */
    public String getRandomSide(){
        // Create random object
        Random r = new Random();
        // Get a random item
        return sides[r.nextInt(sides.length)];
    }

    /**
     * Gets sides.
     *
     * @return Value of sides.
     */
    public String[] getSides() {
        return sides;
    }

    /**
     * toString that prints out the sides
     *
     * @return A string representation of the sides
     */
    @Override
    public String toString() {
        return Arrays.toString(sides);
    }
}