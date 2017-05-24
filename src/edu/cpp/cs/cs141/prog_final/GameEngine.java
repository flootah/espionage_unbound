package edu.cpp.cs.cs141.prog_final;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class GameEngine {
	
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
	}
	
	/**
	 * Instances an enemy.
	 * To be used by instanceBuilding.
	 */
	public void instanceEnemy() {
		//code
	}
	
	/**
	 * Instances a powerup.
	 * To be used by instanceBuilding.
	 */
	public void instancePowerup() {
		//code
	}
	
	/**
	 * Instances a room.
	 * To be used by instanceBuilding.
	 */
	public void instanceRoom() {
		//code
	}
	
	/**
	 * Moves an agent.
	 */
	public void move(String userMove) 
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
	public void shoot() {
		//code
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
	public void saveGame(String saveName) {
	    try {
	        FileOutputStream fos = new FileOutputStream(saveName);
	        ObjectOutputStream oos = new ObjectOutputStream(fos);
	        oos.writeObject(grid);
	        oos.close();
	    } catch (Exception e) {

	    }
	}
	
	/**
	 * Loads the game's state as a file.
	 */
	public void loadGame(String loadName) {

        try {
            FileInputStream fis = new FileInputStream(loadName);
            ObjectInputStream ois = new ObjectInputStream(fis);
            grid = (GameBoard) ois.readObject();
            ois.close();
        }catch (Exception e) {
            System.out.println("Invalid Name");    
        }
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