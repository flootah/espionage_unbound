/**
 * 
 */
package edu.cpp.cs.cs141.prog_final;

/**
 * @author Corey Perez
 *
 */
public class Player extends ActiveAgents {

    private final int maxLives = 3;
	
	private int lives;
	
	private Gun gun;
	
	/**
	 * 2D int array that is used for knowing the position of the player on the game board.
	 */
	private int[][] position;
	
	public Player(Gun weapon)
	{
		gun = weapon;
		lives = maxLives;
	}
	
	public void shoot()
	{
		gun.shoot();
	}
	
	public void reloadPlayerGun()
	{
		gun.reload();
	}
	
	public int getLives()
	{
		return lives;
	}
	
	/**
	 * Method that retrieves the player's position within the game board.
	 * @return the 2D array that dictates where the player is on the game board.
	 */
	public int[][] getPosition() {
	    return position;
	}

}
