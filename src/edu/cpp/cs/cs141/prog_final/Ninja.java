/**
 * 
 */
package edu.cpp.cs.cs141.prog_final;

/**
 * @author Corey Perez
 *
 */
public class Ninja extends ActiveAgents {
    
    /**
     * 2D int array that is used for knowing the positions of the ninjas on the game board.
     */
    private int[][] position;
    
    /**
     * Method that retrieves the ninjas' positions within the game board.
     * @return the 2D arrays that dictate where the ninjas are on the game board.
     */
    public int[][] getPosition() {
        return position;
    }

}
