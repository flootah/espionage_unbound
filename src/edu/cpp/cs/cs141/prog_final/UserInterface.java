package edu.cpp.cs.cs141.prog_final;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Scanner;

import javax.swing.plaf.synth.SynthSeparatorUI;

/**
 * Displays the game to the user, including all of the menu options and 
 * inputs. The player can move, look, shoot and pause the game through these menus.
 * This includes saving and loading a game as well.
 * 
 * @author Corey Perez
 * @author Eduardo Saenz
 * @author Lance Dall
 * @author Grant Posner
 * @author Bumjoong Kim
 * @author Jacob Chong
 *
 */
public class UserInterface 
{
	/**
     * This field represents the Game Engine that will run the game. Only one is
     * created, and used for the duration of the play session.
     */
    private GameEngine ge;
    /**
     * This field represents the Scanner object that will take in user input.
     * Only one is created, and used for the duration of the play session.
     */
    private Scanner sc;
    
    /**
     * This field represents the game's current state. Each number is to
     * represent a different state, ie. MainMenu, ingame, paused, etc. Each game
     * state will run different methods within the UI.
     */    
    private int state;
    
    private int previousState;
    
    /**
     * Represents whether debug mode is active or not.
     */
    private boolean debug;
    
    private int ui;
    
	private boolean paused;
	private boolean saveLoaded;
	private GameEngine loadedSave;

    /**
     * Constructor for the class UserInterface. Takes a game state as an input.
     * Creates a new Game Engine and Scanner to be used, and fills in default
     * values for the game's state.
     */
    public UserInterface() {
        sc = new Scanner(System.in);
        state = 1;
        previousState = 1;
        debug = false;
        ui = 0;
        paused = false;
        saveLoaded = false;
        ge = null;
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
				cls();
				firstMenu();
				break;
			case 2:
				cls();
				aboutMenu();
				break;
			case 3:
				loadGameMenu();
				break;
			case 4:
				cls();
				uiMenu();
				break;
			case 5:
				cls();
				initGame();
				break;
			case 6:
				cls();
				debugMenu();
				break;
			case 7:
				cls();
				pauseMenu();
				break;
			case 8:
				cls();
				saveGameMenu();
				break;
			case 9:
				cls();
				exitCheck();
				break;
			case 10:
				cls();
				gameOver();
				break;
			}
		}
	}
	
	/**
	 * Initial menu printing. Asks the player if they want to start a new game,
	 * load a save, display a help page, or exit. changes game state
	 * accordingly, or exit the program.
	 */
	private void firstMenu() {
	    System.out.println("Welcome to Espionage Unbound v0.6!");
	    System.out.println("");
	    System.out.println("Please select an option:");
	    System.out.println("   1. New Game");
	    System.out.println("   2. Load Game");
	    System.out.println("   3. About & Controls");
	    ge = null;
	    saveLoaded = false;
	    int option = 0;
	    if (sc.hasNextInt()) {
	        option = sc.nextInt();
	    }
	    sc.nextLine();
	
	    switch (option) {
	    case 1:
	        changeState(4);
	        break;
	    case 2:
	        changeState(3);
	        break;
	    case 3:
	        changeState(2);
	        break;
	    }
	}

	/**
	 * Allows the player to launch the game in debug mode
	 */
	private void debugMenu() {
        System.out.println("Would you like to enter debug mode?");
        System.out.println("   1. Yes");
        System.out.println("   2. No");
        System.out.println("   3. Back");

        int choice = 0;
        if (sc.hasNextInt()) {
            choice = sc.nextInt();
        }
        sc.nextLine();

        switch (choice) {
        case 1:
            debug = true;
            changeState(5);
            break;
        case 2:
            debug = false;
            changeState(5);
            break;
        case 3:
			changeState(previousState);
			break;
        default:
            System.out.println("Invalid option! Try Again.");
            break;
        }

    }

	/**
	 * Takes in the direction the player wants to move in and moves them
	 * in that direction.
	 */
    private void lookMenu() {
    	if(!ge.getLooking()) {
	    	System.out.println("What direction would you like to look? (W, A, S, D)");
	    	System.out.println("Press C to cancel.");
	    	boolean exit = false;
		    	while(!exit) {
		    	String lookDirection = sc.nextLine();
		    	switch(lookDirection.toUpperCase()) {
		    	case "C":
		    		exit = true;
		    		break;
		    	case "W":
		    		ge.playerLook("up");
		    		ge.setLooking(true);
		    		exit = true;
		    		break;
		    	case "A":
		    		ge.playerLook("left");
		    		ge.setLooking(true);
		    		exit = true;
		    		break;
		    	case "S":
		    		ge.playerLook("down");
		    		ge.setLooking(true);
		    		exit = true;
		    		break;
		    	case "D":
		    		ge.playerLook("right");
		    		ge.setLooking(true);
		    		exit = true;
		    		break;
		    	default:
		    		System.out.println("You can't look that way!");
		    	}
	    	}
    	} else {
    		System.out.println("You've already looked this turn!");
    	}
	}

	private void shootMenu() {
        String gunDirection = null;
        System.out.print("What direction would you like to shoot (W, A, S, D)? ");
        System.out.println("Press C to cancel.");
        boolean exit = false;
        while(!exit) {
	        gunDirection = sc.nextLine();
	        switch(gunDirection.toUpperCase()) {
	        case "C":
	        	exit = true;
	        	break;
	        	//cancel
	        case "W":
	        	ge.shootGun("up");
	        	exit = true;
	        	break;
	        case "A":
	        	ge.shootGun("left");
	        	exit = true;
	        	break;
	        case "S":
	        	ge.shootGun("down");
	        	exit = true;
	        	break;
	        case "D":
	        	ge.shootGun("right");
	        	exit = true;
	        	break;
	        default:
	        	System.out.println("You cant shoot that way!");
	        }
        }
	}

    /**
	 * UI choice page. Asks the player to choose the type of UI they would like
	 * to use for the duration of the program. Sets a global variable
	 * accordingly, then send the game state to inGame()
	 */
	private void uiMenu() {
	    System.out.println("Please choose a UI type:");
	    System.out.println();
	    System.out.println("1. Text-Based");
	    System.out.println("2. Graphics-Based **UNDER CONSTRUCTION**");
	    System.out.println("3. Back");
	    int option = 0;
	
	    if (sc.hasNextInt()) {
	        option = sc.nextInt();
	    }
	    sc.nextLine();
	
	    switch (option) {
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
	 * About/help page. Displays information about how to play, backstory, and
	 * returns to firstMenu() if any input is recieved.
	 */
	private void aboutMenu() {
	    System.out.println("Welcome to Espionage Unbound!  You are trapped in a building where six ninja assassins are trying to kill you!\n"
	            + "The goal of the game is to find a briefcase with important documents inside.  The briefcase can spawn in one of nine rooms.\n"
	            + "If you are in an adjacent tile to a ninja, you will be stabbed and lose a life.  You are equipped with\n"
	            + "a gun that has only one bullet.  You can shoot ninjas to kill them, but they must be horizontal or vertical to your position.\n"
	            + "(which displays the room the suitcase is in), and an invincibility potion (which makes you immune to ninja stabbings for five\n"
	            + "turns.  Good luck finding the briefcase!\n");
	    System.out.println("W = Moves player up one cell.  Can also shoot bullet up.\n"
	            + "A = Moves player left one cell.  Can also shoot bullet left.\n"
	            + "S = Moves player down one cell.  Can also shoot bullet down.\n"
	            + "D = Moves player right one cell.  Can also shoot bullet right.\n"
	            + "B = Gives the player the option to choose what direction to shoot the bullet.\n"
	            + "L = Allows the player to check a direction to see if there are any ninjas in the row or column the player is currently in.\n"
	            + "P = Opens a pause menu, where the player can load, save, open about menu, open control menu, exit to main menu, or exit to desktop.\n");
	    System.out.println("press ENTER to return");
	    sc.nextLine();
	    changeState(previousState);
	}

	/**
	 * Main ui state, for when the player is ingame. Displays the map, player
	 * stats, and follows prompts from the player.
	 */
	private void initGame() {
		if(saveLoaded) {
			ge = loadedSave;
		} else {
			ge = new GameEngine();
		}
	    if (ui == 1) {
	        TUI();
	    } else if (ui == 2) {
	        GUI();
	    } else {
	        System.out.println("invalid ui has been chosen!");
	    }
	}

	/**
	 * The Text User Interface method. Used when player is ingame. Creates a
	 * user interface based only on text.
	 * 
	 */
	private void TUI() {
	    boolean gameOver = ge.gameOver();

	    while (!gameOver && !paused) {
	    	ge.checkForGameOver();
		    gameOver = ge.gameOver();
		    if(gameOver) {
		    	changeState(10);
		    	break;
		    }
	    	
	        if (debug) {
	            ge.printDebugGrid();
	        } else {
	            ge.printGrid();
	        }
			
	        String choice;
	        System.out.println("Choose your move: W, A, S, D, B, L, P");
	        System.out.println("Lives: " + ge.getLives() + "\nAmmo: " + ge.getAmmo());
	        choice = sc.nextLine();
	        switch (choice.toUpperCase()) {
	        case "B":
	            shootMenu();
	            break;
	        case "P":
	        	paused = true;
	        	changeState(7);
	        	break;
	        case "L":
	        	lookMenu();
	        	break;
	        case "W":
	        case "A":
	        case "S":
	        case "D":
	            ge.userMoveInput(choice);
	            ge.checkForSpy();
	            ge.moveNinja();
				ge.pickUpPowerUp();
				ge.checkInvincible();
	            break;
	        default:
	            System.out.println("Invalid Move");
	            break;
	        }
	    }
	    
	}

	/**
     * The Graphical User Interface method. Used when player is ingame. Creates
     * a user interface based on graphical images.
     * 
     */
    private void GUI() {
        // code
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
	private void pauseMenu() {
		System.out.println("	  PAUSED");
		System.out.println("     1. Load Game");
		System.out.println("     2. Save Game");
		System.out.println("     3. About & Controls");
		System.out.println("     4. Exit to Main Menu");
		System.out.println("     5. Exit to Desktop");
		System.out.println("Enter your choice or press P to return to game.");
		String choice = "";
		boolean exit = false;
		while(!exit) {
			choice = sc.next();
			sc.nextLine();
			switch(choice.toUpperCase()) {
			case "P":
				paused = false;
				exit = true;
				changeState(5);
				break;
			case "1":
				changeState(3);
				exit = true;
				break;
			case "2":
				changeState(8);
				exit = true;
				break;
			case "3":
				changeState(2);
				exit = true;
				break;
			case "4":
				if(exitCheck()) {
					paused = false;
					ge.resetGrid();
					changeState(1); 
					exit = true;
					break;
				}
				break;
			case "5":
				if(exitCheck()) {
					System.exit(0);
				}
				break;
			default:
				System.out.println("Invalid Selection!");
				break;
				}
		}
		
	}

	/**
	 * Checks if the player wants to exit the game
	 */
	private boolean exitCheck() {
		System.out.println("Are you sure you wish to exit?");
		System.out.println("All unsaved progress will be lost!");
		System.out.println("(y/n)");
		boolean exit = false;
		while(!exit) {
			String choice = sc.nextLine();
			switch(choice.toLowerCase()) {
			case "y":
				return true;
			case "n":
				return false;
			default:
				//lalala
			}
		}
		return false;
	}

	/**
     * Game over screen. clears screen, printing "GAME OVER" onto system out.
     * then asks the player if they want to exit or return to main menu.
     */
    private void gameOver() {
         if(ge.winCondition()) {
        	 System.out.println("You win! Congratulations!");
         } else {
        	 System.out.println("Game Over!");
         }
         
         System.out.println();
         System.out.println();
         
         System.out.println("Play Again?");
         System.out.println("(y/n)");
         boolean exit = false;
         while(!exit) {
	         String choice = sc.nextLine();
	         switch(choice.toUpperCase()) {
	         case "Y":
	        	 exit = true;
	        	 changeState(1);
	        	 break;
	         case "N":
	        	 System.exit(0);
	         default:
	        	 System.out.println("Invalid selection.");
	         }
         }
    }
         

    /**
     * Menu for the save game
     */
    private void saveGameMenu() {
	     String saveName;
	     System.out.println("Press C to Cancel");
	     System.out.println("Enter save file name");
	     System.out.println("Save Files must be more than 3 characters");
	     while(true) {
		     saveName = sc.next();
		     if(saveName.equalsIgnoreCase("c")) {
		    	 changeState(previousState);
		    	 break;
		     } else if(saveName.length() <= 3) {
		    	 System.out.println("Invalid Name!");
		     } else {
		     ge.saveGame(saveName);
		     System.out.println("Game Saved.");
		     changeState(previousState);
		     break;
		     }
	     }
	}
	 
    /**
     * Changes the state of the game save
     * @param i
     */
	private void changeState(int i) {
		previousState = state;
	    state = i;
	}

	/**
	 * Menu for loading the game
	 */
	private void loadGameMenu() {
	     System.out.println("Press C to Cancel");
	     System.out.println("Enter the save file's name");
	     System.out.println("Save Files are always more than than 3 characters");
	     boolean exit = false;
	     while(!exit) {
	    String loadName;
	    loadName = sc.nextLine();
		     if(loadName.equalsIgnoreCase("c")) {
		    	 changeState(previousState);
		    	 exit = true;
		    	 break;
		     } else if(loadName.length() <= 3) {
		    	 System.out.println("Invalid Name! Too Short.");
		     } else {
		     GameEngine saveState = loadGame(loadName);
		     if(saveState == null) {
		    	 System.out.println("Save could not be loaded...");
		     } else {
		    	 loadedSave = saveState;
		    	 saveLoaded = true;
		    	 exit = true;
		    	 if(previousState == 1) {
		    		 changeState(4);
		    	 } else {
		    		 changeState(previousState);
		    	 }
			     break;
		     }
		     }
	     }
	}
	
	/**
	 * Loads the game based on the save game given by the player
	 * @param loadName
	 */
    private GameEngine loadGame(String loadName) {
        try {
            FileInputStream fis = new FileInputStream(loadName);
            ObjectInputStream ois = new ObjectInputStream(fis);
            ge = (GameEngine) ois.readObject();
            ois.close();
        } catch (FileNotFoundException e) {
        	System.out.println("File not found!");
        } catch (IOException e) {
        	System.out.println("IOExeption Error");
        } catch (ClassNotFoundException e) {
        	System.out.println("ClassNotFound Error");
		} 
        return ge;
    }
	
	
	 /**
	  * prints an arbitrary amount of new line commands
	  * used to simulate screen clearing.
	  */
	private void cls() {
		 System.out.println("\n");
	}

	
}
