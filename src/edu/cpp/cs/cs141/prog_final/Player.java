/**
 * 
 */
package edu.cpp.cs.cs141.prog_final;

import java.io.Serializable;

/**
 * @author Corey Perez
 *
 */
public class Player implements Serializable
{
	private final int MAX_LIVES = 3;
	
	private final int INVINCIBLE_TURNS = 5;
	
	private int lives;
	
	private Gun gun;
	
	private int row;
	
	private int column;
	
	private boolean invincible;
	
	private int turnCounter;
	
	private String playerMark = "P";
	
	public Player(Gun weapon)
	{
		gun = weapon;
		lives = MAX_LIVES;
		row = 8;
		column = 0;	
		invincible = false;
		turnCounter = 0;
	}
	
	public void reloadPlayerGun()
	{
	    gun.reload();
	}
	
	public int getLives()
	{
		return lives;
	}
	
	public int loseLife(){
	    return --lives;
	}
	
	public void gainInvincibility()
	{
		invincible = true;
	}
	
	public void loseInvincibility()
	{
		invincible = false;
	}

	public boolean isInvincible()
	{
		return invincible;
	}
	
	public int getRow()
	{
		return row;
	}

	public int getColumn() 
	{
		return column;
	}
	
	public String getPlayerMark()
	{
		return playerMark;
	}
	
	public void moveLeft()
	{
		column--;
	}
	
	public void moveRight()
	{
		column++;
	}
	
	public void moveUp()
	{
		row--;
	}
	
	public void moveDown()
	{
		row++;
	}

	public void respawn() {
		gun.reload();
		row = 8;
		column = 0;
	}
	
	public int getTurnCounter()
	{
		return turnCounter;
	}
	
	public void increaseTurnCounter()
	{
		turnCounter++;
	}
}
