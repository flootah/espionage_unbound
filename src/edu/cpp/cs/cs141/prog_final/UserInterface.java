package edu.cpp.cs.cs141.prog_final;
import java.util.Scanner;

public class UserInterface {
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
		 * Represents whether debug mode is active or not.
		 */
		private boolean debug;
		private int ui;
		/*
		 * Constructor for the class UserInterface.
		 * Takes a game state as an input.
		 * Creates a new Game Engine and Scanner to be used, and fills in default values for the game's state.
		 */
		public UserInterface(GameEngine ge) {
			this.ge = ge;
			sc = new Scanner(System.in);
			state = 1;
			debug = false;
			ui = 0;
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
		public void mainMenu( ){
			while(true) {
				switch(state) {
				case 1:
					firstMenu();
					break;
				case 2:
					aboutMenu();
					break;
				case 3:
					loadMenu();
					break;
				case 4:
					uiMenu();
					break;
				case 5:
					inGame();
					break;
				case 6:
					debugMenu();
					break;
				}
			}
		}
		
		private void debugMenu() {
			System.out.println("Would you like to enter debug mode?");
			System.out.println("   1. Yes");
			System.out.println("   2. No");
			
			int choice = 0;
			if(sc.hasNextInt()) {
				choice = sc.nextInt();
			}
			sc.nextLine();
			
			switch(choice) {
			case 1:
					debug = true;
					changeState(5);
					break;
			case 2:
				debug = false;
					changeState(5);
					break;
			default:
				System.out.println("Invalid option! Try Again.");
				break;
			}
			
		}


		/**
		 * The Text User Interface method.
		 * Used when player is ingame.
		 * Creates a user interface based only on text. Uses TODO x game states.
		 * 
		 */
		private void TUI() {
			boolean gameOver = ge.gameOver();
			int[] stats = playerStats();

			while(!gameOver) {
			if(debug) {
				ge.printDebugGrid();
				} else {
				ge.printDebugGrid();
				}
			
			String choice = "";
			System.out.println("Choose your move: W A S D");
/*TODO*/			System.out.print("Lives: " + stats[0]);
/*TODO*/			System.out.println("          Ammo: " + stats[1]);
			
			choice = sc.nextLine();
			
			ge.movePlayer(choice);
			
			ge.moveNinja();

			}
		}
		/**
		 * The Graphical User Interface method.
		 * Used when player is ingame.
		 * Creates a user interface based on graphical images. Uses TODO x game states.
		 * 
		 */
		private void GUI() {
			//code
		}
		
		/**
		 * A getter for all the player's stats.
		 * Gets all of the player's current stats from the GameEngine.
		 * Organizes them into an array for easier access. But this might not be optimal later on, so subject to change.
		 */
		private int[] playerStats(){
			//code
			int[] playerStats = {ge.getAmmo(), ge.getLives()};
			return playerStats;
		}
		
		/**
		 * Initial menu printing.
		 * Asks the player if they want to start a new game, load a save, display a help page, or exit.
		 * changes game state accordingly, or exit the program.
		 */
		private void firstMenu() {
			System.out.println("Welcome to Espionage Unbound v0.6!");
			System.out.println("");
			System.out.println("Please select an option:");
			System.out.println("   1. New Game");
			System.out.println("   2. Load Game");
			System.out.println("   3. About");
			int option = 0;
			if(sc.hasNextInt()) {
				option = sc.nextInt();
			}
			sc.nextLine();
			
			switch(option) {
			case 1:
				state = 4;
				break;
			case 2:
				state = 3;
				break;
			case 3:
				state = 2;
				break;
			}
			System.out.println(state);
		}
		
		private void changeState(int i) {
			state = i;
		}


		/**
		 * About/help page.
		 * Displays information about how to play, backstory, and returns to firstMenu() if any input is recieved.
		 */
		private void aboutMenu() {
			System.out.println("This is a placeholder about page...");
			System.out.println();
			System.out.println("press ENTER to return");
			sc.nextLine();
			changeState(1);
		}
		/**
		 * Save loading page.
		 * Asks the player to identify their save file, which the UI will pass to the GE to load up.
		 * Note: must first send player to uiMenu() to get the type of UI to use.
		 */
		private void loadMenu() {
			System.out.println("This is a placeholder save loading page...");
			System.out.println();
			System.out.println("press ENTER to return");
			sc.nextLine();
			changeState(1);
		}
		/**
		 * UI choice page.
		 * Asks the player to choose the type of UI they would like to use for the duration of the program.
		 * Sets a global variable accordingly, then send the game state to inGame()
		 */
		private void uiMenu() {
			System.out.println("Please choose a UI type:");
			System.out.println();
			System.out.println("1. Text-Based");
			System.out.println("2. Graphics-Based **UNDER CONSTRUCTION**");
			System.out.println("3. Back");
			int option = 0;
			
			if(sc.hasNextInt()) {
				option = sc.nextInt();
			}
			sc.nextLine();
			
			switch(option) {
			case 1:
				ui = 1;
				changeState(6);
				break;
			case 2:
				System.out.println("Sorry, this UI is under construction!");
				break;
			case 3:
				changeState(1);
				break;
			}
		}
			
		/**
		 * Main ui state, for when the player is ingame.
		 * Displays the map, player stats, and follows prompts from the player.
		 */
		private void inGame() {
			ge.createBuilding();
			if(ui == 1) {
				TUI();
			} else if(ui == 2) {
				GUI();
			} else {
				System.out.println("invalid ui has been chosen!");
			}
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
			ge.printDebugGrid();
		}
		
		 public void printBoard() 
		{
			ge.printDebugGrid();
		}
		 
		 public void saveGameName() {
		     String saveName;
		     System.out.println("Enter name for the save file: ");
		     saveName = sc.next();
		     ge.saveGame(saveName);
		 }
		 
		 public void loadGameName() {
		     String loadName;
		     System.out.println("Enter save file name: ");
		     loadName = sc.next();
		     ge.loadGame(loadName);
		 }
}
