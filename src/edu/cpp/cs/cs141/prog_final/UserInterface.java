/**
 * 
 */
package edu.cpp.cs.cs141.prog_final;

import java.util.Scanner;

/**
 * @author Corey Perez
 *
 */
public class UserInterface
{
	/**
	 * This field represents the Game Engine that will run the game.
	 * Only one is created, and used for the duration of the play session.
	 */
	private GameEngine ge;
	/**
	 * This field represents the Scanner object that will take in user
	 * input. Only one is created, and used for the duration of the play session.
	 */
	private Scanner sc;
	/**
	 * This field represents the game's current state.
	 * Each number is to represent a different state, ie. MainMenu, ingame, paused, etc.
	 * Each game state will run different methods within the UI.
	*/
	private int state;
	/**
	 * Constructor for the class UserInterface.
	 * Takes a game state as an input.
	 * Creates a new Game Engine and Scanner to be used, and fills in default values for the game's state.
	 */
	public UserInterface(GameEngine ge)
	{
		this.ge = ge;
		sc = new Scanner(System.in);
	}
	
	
	/**
	 * Main UI class.
	 * Will act initially as the main UI for the main menu.
	 * After the player select either a TUI or GUI, then the UI will pass the main UI responsibilities to the respective TUI/GUI method
	 * 
	 * This method takes up four game states:
	 * 1. Select NewGame/LoadGame/Help
	 * 2. About page, showing info about how to play the game, as well as a back story if we feel like it.
	 * 3. Select a Save file page, if the player chooses to load a save.
	 * 4. Page asking the player if they want a TUI or GUI.
	 * 
	 * After page 4, mainMenu ends by calling either TUI or GUI, which will take over main UI responsibilities.
	 */
	
	public void mainMenu()
	{
		printStartScreen();
		boolean exit = false;
		
		while(!exit)
		{
			int choice = startMenu();
		
			switch(choice)
			{
			case 1:
				runGame();
				break;
				
			case 2:
				ge.createBuilding();
				debugBoard();
				break;
				
			case 3:
				exit = true;
				break;
				
			default:
				System.out.println("Invalid choice, please choose again.");					
			}
		}		
	}
	

	
	private int startMenu() 
	{
		int choice = 0;
		
		System.out.println("Select an option: ");
		System.out.println("1. Normal ");
		System.out.println("2. Debug  ");
		System.out.println("3. Exit   ");
		
		choice = sc.nextInt();
		sc.nextLine();
		
		return choice;
	}

	private void runGame()
	{
		ge.createBuilding();
		ge.displayDebugBoard();
		
		while(!gamePlay());
	}

	private boolean gamePlay() 
	{
		String choice = "";
		System.out.println("Choose your move: W A S D");
		
		choice = sc.nextLine();
		
		ge.movePlayer(choice);
		
		ge.moveNinja();
		
		ge.printNewBoard();
		
		
		return false;
	}


	private void printStartScreen() 
	{
		System.out.println("Welcome to Espionage Unbound!");
		
	}
	
	/**
	 * The Text User Interface method.
	 * Used when player is ingame.
	 * Creates a user interface based only on text. Uses TODO x game states.
	 * 
	 */
	private void TUI() {
		//code
	}
	
	/**
	 * A getter for all the player's stats.
	 * Gets all of the player's current stats from the GameEngine.
	 * Organizes them into an array for easier access. But this might not be optimal later on, so subject to change.
	 */
	private int[] playerStats(){
		//code
		int[] playerStats = {};
		return playerStats;
	}
	
	/**
	 * Initial menu printing.
	 * Asks the player if they want to start a new game, load a save, display a help page, or exit.
	 * changes game state accordingly, or exit the program.
	 */
	private void firstMenu() 
	{

	}
	
	/**
	 * About/help page.
	 * Displays information about how to play, backstory, and returns to firstMenu() if any input is recieved.
	 */
	private void aboutMenu() {
		//code
	}
	/**
	 * Save loading page.
	 * Asks the player to identify their save file, which the UI will pass to the GE to load up.
	 * Note: must first send player to uiMenu() to get the type of UI to use.
	 */
	private void loadMenu() {
		//code
	}
	/**
	 * UI choice page.
	 * Asks the player to choose the type of UI they would like to use for the duration of the program.
	 * Sets a global variable accordingly, then send the game state to inGame()
	 */
	private void uiMenu()
	{

	}
	/**
	 * Main ui state, for when the player is ingame.
	 * Displays the map, player stats, and follows prompts from the player.
	 */
	private void inGame() 
	{
		
	}
	/**
	 * Pause menu.
	 * Clears UI, and displays a choice for the player. From this point, they can:
	 * Save
	 * Load
	 * Exit
	 * Print about page
	 * The player's selection will take them to the according to their selection.
	 */
	public void pauseMenu() {
		//code
	}
	/**
	 * Game over screen
	 * clears screen, printing "GAME OVER" onto system out.
	 * then asks the player if they want to exit or return to main menu.
	 */
	public void gameOver() {
		//code
	}
	/**
	 * Method handling debug mode.
	 * Prints the map for the player in a different way, giving full vision to the board.
	 */
	public void debugMode() 
	{
		
	}
	/**
	 * Moves an entity on the board.
	 */


	public void printBoard() 
	{
		//ge.createBuilding();
		ge.displayBoard();
	}
	
	public void debugBoard()
	{
		//ge.createBuilding();
		ge.displayDebugBoard();
	}

}
