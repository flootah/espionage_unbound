package edu.cpp.cs.cs141.prog_final;

/**
 * This is the bullet powerup, which adds a bullet to the player's gun
 * if they've already used one
 * 
 * @author Corey Perez
 * @author Eduardo Saenz
 * @author Lance Dall
 * @author Grant Posner
 * @author Bumjoong Kim
 * @author Jacob Chong
 *
 */
public class Bullet extends PowerUps 
{
	/**
	 * The mark that the {@link #Bullet()} has on the {@link #GameBoard()}
	 */
	private String bulletMark = "b";
	
	/**
	 * Getter for the {@link #bulletMark}
	 * @return {@link #bulletMark}
	 */
	public String getBulletMark() 
	{
		return bulletMark;
	}
	
	/**
	 * This is called when the player shoots the {@link #Bullet()}
	 */
	public void used()
	{
		available = false;
	}
	
	/**
	 * Returns the status of the whether or not the player has a {@link #Bullet()}
	 * @return {@link #available}
	 */
	public boolean isUsed()
	{
		return available;
	}
}
