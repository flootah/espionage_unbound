/**
 * 
 */
package edu.cpp.cs.cs141.prog_final;
import java.util.Random;

/**
 * @author Corey Perez
 *
 */
public class GameEngine 
{	
	private Player player;
	
	private GameBoard grid;
	
	/**
	 * Instances the building.
	 * Constructs the player character and ninja-assassins.
	 * Constructs the rooms and powerups.
	 */
	public void createBuilding()
	{
		grid = new GameBoard(new Player(new Gun()), new Briefcase(), new Bullet(), new Radar(), new Invincibility());
		grid.calculatePlayerPosition();
		grid.calculateRoomPositions();
		grid.calculateNinjaPositions();
		grid.calculateBriefCasePosition();
		grid.calculateBulletPosition();
		grid.calculateInvinciblePosition();
		grid.calculateRadarPosition();		
	}
	
	/**
	 * Moves an agent.
	 */
	public void movePlayer(String userMove) 
	{
		switch (userMove) 
		{
		case "W":
		case "w":
			if (grid.getPlayerRow() > 0) 
			{
				grid.movePlayerUp();
			} 
			else 
			{
				System.out.println("It's a wall.");
			}
		    break;
		case "S":
		case "s":
		    if (grid.getPlayerRow() < 8) 
		    {
		    	grid.movePlayerDown();
			} 
		    else 
		    {
		    	System.out.println("It's a wall.");
			}
		    break;
		case "A":
		case "a":
		    if (grid.getPlayerColumn() > 0) 
		    {
		    	grid.movePlayerLeft();
			} 
		    else 
		    {
		    	System.out.println("It's a wall.");
			}
		    break;
		case "D":
		case "d":
		    if (grid.getPlayerColumn() < 8) 
		    {
		    	grid.movePlayerRight();
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
	/*
	public void moveNinja()
	{
		int counter = 0;
		
		while(counter < 6)
		{
		 int rng = new Random().nextInt(4);
		    switch (rng) 
		    {
		    case 0:
				if (grid.getNinjaRow(counter) > 0) 
				{
					grid.moveNinjaUp(counter);
					counter++;
				} 
				else
				{
					moveNinja();
				}
		        break;
		    case 1:
				if (grid.getNinjaRow(counter) < 8) 
				{
					grid.moveNinjaDown(counter);
					counter++;
				} 
				else
				{
					moveNinja();
				}
		        break;
		    case 2:
				if (grid.getNinjaColumn(counter) > 0) 
				{
					grid.moveNinjaRight(counter);
					counter++;
				} 
				else
				{
					moveNinja();
				}
		        break;
		    case 3:
				if (grid.getNinjaColumn(counter) < 8) 
				{
					grid.moveNinjaLeft(counter);
					counter++;
				} 
				else
				{
					moveNinja();
				}
		        break;
		    }
		}
	}
	*/
	public void moveNinja()
	{
		int counter = 0;
		
		while(counter < 6)
		{
			int rng = new Random().nextInt(4);
			if(rng == 3 && grid.getNinjaRow(counter) > 0 && grid.getNinjaRow(counter) <= 8)
			{
				grid.moveNinjaUp(counter);
				counter++;
			}
			else
				if(rng == 2 && grid.getNinjaRow(counter) < 8 && grid.getNinjaRow(counter) >= 0)
				{
					grid.moveNinjaDown(counter);
					counter++;
				}
				else
					if(rng == 1 && grid.getNinjaColumn(counter) >= 0 && grid.getNinjaColumn(counter) < 8)
					{
						grid.moveNinjaRight(counter);
						counter++;
					}
					else
						if(rng == 0 && grid.getNinjaColumn(counter) <= 8 && grid.getNinjaColumn(counter) > 0)
						{
							grid.moveNinjaLeft(counter);
							counter++;
						}						
		}
	}
	
	
	public void printNewBoard()
	{
		grid.printNewBoard();
	}
	
	/**
	 * Lets the player "look".
	 */
	public void look() {
		//code
	}
	
	/**
	 * Lets the player "shoot".
	 */
	public void shoot() 
	{
		player.shoot();
	}
	
	/**
	 * Ends the game.
	 * Used for wins or losses.
	 */
	public void endGame() {
		//code
	}
	
	/**
	 * Saves the game's state as a file.
	 */
	public void saveGame() {
		//code
	}
	
	/**
	 * Loads the game's state as a file.
	 */
	public void loadGame() {
		//code
	}
	/**
	 * checks for a game over scenario
	 * returns true if a game over scenario has been reached
	 * returns false otherwise.
	 */
	public boolean gameOver() {
		//code
		return false;
	}
	/**
	 * Moves an entity on the board
	 * 
	 */
	private void moveEntity(int move, int dir) {
		
	}
	
	public void displayBoard()
	{
		grid.printGrid();
	}
	
	public void displayDebugBoard()
	{
		grid.printGridDebug();
	}
	

}
