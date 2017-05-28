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
	
	public int loselife(){
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
	
	private void move(String userMove)
	{
		switch (userMove) 
		{
		case "W":
		case "w":
			if (row > 0) 
			{
			    moveUp();
			} 
			else 
			{
				System.out.println("It's a wall.");
			}
		    break;
		case "S":
		case "s":
		    if (row < 8) 
		    {
		        moveDown();
			} 
		    else 
		    {
		    	System.out.println("It's a wall.");
			}
		    break;
		case "A":
		case "a":
		    if (column > 0) 
		    {
		        moveLeft();
			} 
		    else 
		    {
		    	System.out.println("It's a wall.");
			}
		    break;
		case "D":
		case "d":
		    if (column < 8) 
		    {
		        moveRight();
			} 
		    else 
		    {
		    	System.out.println("It's a wall.");
			}
		    break;
		default:
		    System.out.println("Invalid move selection");
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
}
