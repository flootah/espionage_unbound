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
	
	private int row;
	
	private int column;
	
	private String playerMark = "P";
	
	public Player(Gun weapon)
	{
		gun = weapon;
		lives = maxLives;
		row = 8;
		column = 0;		
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
}
