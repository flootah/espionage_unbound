/**
 * 
 */
package edu.cpp.cs.cs141.prog_final;

/**
 * @author Corey Perez
 *
 */
public class Player 
{
	private final int MAX_LIVES = 3;
	
	private final int INVINCIBLE_TURNS = 5;
	
	private int lives;
	
	private Gun gun;
	
	private int row;
	
	private int column;
	
	private int invincibility;
	
	private String playerMark = "P";
	
	public Player(Gun weapon)
	{
		gun = weapon;
		lives = MAX_LIVES;
		row = 8;
		column = 0;		
	}

	public void shoot(String direction)
	{
		gun.shoot(direction);
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
	
	public void setInvincibility(int invincibility){
	    this.invincibility = invincibility;
	}
	
	/** WORK IN PROGRESS: method that subtracts the invincibility by one each turn.*/
	public void invincibilityDuration(){
	    for (int i = INVINCIBLE_TURNS; i > 0; i--){
	        setInvincibility(i);
	    }
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
		row = 8;
		column = 0;
	}
}
