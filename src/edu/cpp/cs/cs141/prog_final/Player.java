package edu.cpp.cs.cs141.prog_final;

import java.io.Serializable;

/**
 * Represents the player. The player has a certain amount of lives, defined by
 * {@link #MAX_LIVES}, an amount of turn they're invincible for, defined by {@link #INVINCIBLE_TURNS}
 * and a gun which has one bullet. The player can also die and respawn, reinitiallizing their attributes.
 * 
 * @author Corey Perez
 * @author Eduardo Saenz
 * @author Lance Dall
 * @author Grant Posner
 * @author Bumjoong Kim
 * @author Jacob Chong
 *
 */
public class Player implements Serializable
{
	/**
	 * The amount of {@link #lives} the player begins with
	 */
	private final int MAX_LIVES = 3;
	
	/**
	 * The amount of turns that the player is invincible for
	 */
	private final int INVINCIBLE_TURNS = 5;
	
	/**
	 * The current amount of lives the player has.
	 * Initially set to {@link #MAX_LIVES}
	 */
	private int lives;
	
	/**
	 * Creates a {@link Gun()} object for the player
	 */
	private Gun gun;
	
	/**
	 * The row that the player is on
	 * Determines position on {@link GameBoard()}
	 */
	private int row;
	
	/**
	 * The column that the player is on
	 * Determines position on {@link GameBoard()}
	 */
	private int column;
	
	/**
	 * Determines whether the player is invincible or not.
	 * The player is invincible after they pick up the {@link Invincibility()}
	 * powerup for {@link #INVINCIBLE_TURNS} amount of turns
	 */
	private boolean invincible;
	
	/**
	 * Turn counter that increments the amount of turns the {@link Player()}
	 * is invincible for. When it reaches {@link #INVINCIBLE_TURNS}, the player
	 * isn't invincible anymore.
	 */
	private int turnCounter;
	
	/**
	 * The player mark that represents the player on the {@link GameBoard()}
	 */
	private String playerMark = "P";
	
	/**
	 * Constructor for the {@link #Player()}
	 * Sets the Player's position to the bottom left of the {@link GameBoard()}
	 * @param weapon
	 */
	public Player(Gun weapon)
	{
		gun = weapon;
		lives = MAX_LIVES;
		row = 8;
		column = 0;	
		invincible = false;
		turnCounter = 0;
	}
	
	/**
	 * Reloads the {@link #gun} for the player
	 */
	public void reloadPlayerGun()
	{
	    gun.reload();
	}
	
	/**
	 * Getter for the amount of {@link #lives} the player has
	 * @return {@link #lives}
	 */
	public int getLives()
	{
		return lives;
	}
	
	/**
	 * Reduces the amount of {@link #lives} the player has by 1
	 * when the player is killed by a {@link #Ninja()}
	 * @return {@link #lives}
	 */
	public int loseLife(){
	    return --lives;
	}
	
	/**
	 * Gives the player invincibility when they step over the 
	 * {@link Invincibility()} powerup
	 */
	public void gainInvincibility()
	{
		invincible = true;
	}
	
	/**
	 * The player loses invincibility when it expires
	 */
	public void loseInvincibility()
	{
		invincible = false;
	}

	/**
	 * Checks if the player is invincibile
	 * @return {@link #invincible}
	 */
	public boolean isInvincible()
	{
		return invincible;
	}
	
	/**
	 * Getter for the {@link #Player()}'s {@link #row}
	 * @return {@link #row}
	 */
	public int getRow()
	{
		return row;
	}

	/**
	 * Getter for the {@link #Player()}'s {@link #column}
	 * @return {@link #column}
	 */
	public int getColumn() 
	{
		return column;
	}
	
	/**
	 * Getter for the {@link #Player()}'s {@link #playerMark}
	 * @return {@link #playerMark	}
	 */
	public String getPlayerMark()
	{
		return playerMark;
	}
	
	/**
	 * Moves the {@link #player()} left
	 */
	public void moveLeft()
	{
		column--;
	}
	
	/**
	 * Moves the {@link #player()} right
	 */
	public void moveRight()
	{
		column++;
	}
	
	/**
	 * Moves the {@link #player()} up
	 */
	public void moveUp()
	{
		row--;
	}
	
	/**
	 * Moves the {@link #player()} down
	 */
	public void moveDown()
	{
		row++;
	}

	/**
	 * Reinitializes the {@link #Player()} when the player is killed
	 */
	public void respawn() {
		gun.reload();
		row = 8;
		column = 0;
	}
	
	/**
	 * Getter for the {@link #turnCounter} for invincibility
	 * @return {@link #turnCounter}
	 */
	public int getTurnCounter()
	{
		return turnCounter;
	}
	
	/**
	 * Increments the {@link #turnCounter}; is done every
	 * time the player moves
	 */
	public void increaseTurnCounter()
	{
		turnCounter++;
	}
}
