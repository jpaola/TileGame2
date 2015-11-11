
/**
 * A class that models a number tile with 4 randomly generated integer values
 * on each side ranging from 1 to 9.
 * 
 * @author Paola Jiron 
 */

import java.util.Random;

public class NumberTile 
{
    // instance var's
    private final int[] tile;   // a number tile
    private int count;          // number of elements used
    /**
     * Constructor for the number tile.
     */
    public NumberTile()
    {
      Random generator = new Random();  // creates a random generator object
      
      tile = new int[4];    // creates a number tile object of 4 elements
      
      // generates 4 random int's from 1 to 9 for the tile
      
      for ( int i = 0 ; i < 4 ; i++)
      {
          tile[i] = generator.nextInt(9) + 1;
      }
    } 
    /**
     * Rotates the number tile 90 degrees clockwise.
     */
    public void rotate()
    {
        
        // move each value one index location clockwise
        
        int temp = tile[0];
        
        tile[0] = tile[3];
        tile[3] = tile[2];
        tile[2] = tile[1];
        tile[1] = temp;
    }
    /**
     * Returns the left end of the tile.
     * @return The left end value on the tile.
     */
    public int getLeft()
    {
        return tile[0];
    }
    /**
     * Return the right end of the tile.
     * @return The right end value on the tile.
     */
    public int getRight()
    {
        return tile[2];
    }
    /**
     * Return the top of the tile.
     * @return The top value on the tile.
     */
    public int getTop()
    {
        return tile[1];
    }
     /**
     * Return the bottom of the tile.
     * @return The bottom value on the tile.
     */
    public int getBottom()
    {
        return tile[3];
    }
    /**
     * Returns the tile as a String in the form
     *              4
     *          5       7
     *              1
     * @return The tile as a String.
     */
    public String toString()
    {
        return "\n    " + getTop() + "\n"
                + getLeft() + "       " + getRight() + "\n"
                + "    " + getBottom() + "\n";
    }   
}   // end of NumberTile class
