package edu.cpp.cs.cs141.prog_final;

public class GameEngine {
	
	private Player player;
	private GameBoard grid;

	/**
	 * Instances the building.
	 * Constructs the player character and ninja-assassins.
	 * Constructs the rooms and powerups.
	 */
	public void createBuilding() {
		grid = new GameBoard(new Player(new Gun()), new Ninja(), new Briefcase(), new Rooms(), new Bullet(),
								new Radar(), new Invincibility());
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
	public void move() {
		//code
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