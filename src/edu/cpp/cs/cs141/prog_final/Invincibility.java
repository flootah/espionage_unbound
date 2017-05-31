package edu.cpp.cs.cs141.prog_final;

/**
 * The invincibility powerup, which makes the player invincible to
 * being killed by enemies. It has a status for being used or not used.
 * 
 * @author Corey Perez
 * @author Eduardo Saenz
 * @author Lance Dall
 * @author Grant Posner
 * @author Bumjoong Kim
 * @author Jacob Chong
 *
 */
public class Invincibility extends PowerUps 
{
	/**
	 * The mark that the {@link Invincibility()} has on the {@link GameBoard}
	 */
	private String invincibleMark = "i";

	/**
	 * Getter for the {@link #invincibleMark}
	 * @return {@link #invincibleMark}
	 */
	public String getInvincibleMark() 
	{
		return invincibleMark;
	}
	
	/**
	 * Sets {@link #available} to false
	 * Happens when the player steps over the powerup
	 */
	public void used()
	{
		available = false;
	}
	
	/**
	 * Returns whether or not the {@link #Invincibility()} powerup
	 * is used or not
	 * @return {@link #available}
	 */
	public boolean isUsed()
	{
		return available;
	}
	
}
