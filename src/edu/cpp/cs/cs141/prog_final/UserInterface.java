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

    /*
     * Constructor for the class UserInterface. Takes a game state as an input.
     * Creates a new Game Engine and Scanner to be used, and fills in default
     * values for the game's state.
     */
    public UserInterface(GameEngine ge) {
        this.ge = ge;
        sc = new Scanner(System.in);
        state = 1;
        previousState = 1;
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
            cls();
			switch(state) {
			case 1:
				firstMenu();
				break;
			case 2:
				aboutMenu();
				break;
			case 3:
				loadGameName();
				break;
			case 4:
			    setPreviousState(1);
				uiMenu();
				break;
			case 5:
				inGame();
				break;
			case 6:
				debugMenu();
				break;
			case 7:
				pauseMenu();
				break;
			case 8:
				saveGameName();
				break;
			case 9:
				exitCheck();
				break;
			}
		}
	}
	
	private boolean exitCheck() {
		return false;
	}

    private void debugMenu() {
        System.out.println("Would you like to enter debug mode?");
        System.out.println("\t1. Yes");
        System.out.println("\t2. No");
        System.out.println("\t3. Back");

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
            setPreviousState(4);
			changeState(previousState);
			break;
        default:
            System.out.println("Invalid option! Try Again.");
            break;
        }

    }

    /**
     * The Text User Interface method. Used when player is ingame. Creates a
     * user interface based only on text. Uses TODO x game states.
     * 
     */
    private void TUI() {
        boolean gameOver = ge.gameOver();

        while (true) {
        	
            if (debug) {
                ge.printDebugGrid();
            } else {
                ge.printGrid();
            }
			
            String choice;
            System.out.println("Lives: " + ge.getLives() + "\nAmmo: " + ge.getAmmo());
            System.out.println("Choose your move (W, A, S, D, B, K): ");
            choice = sc.nextLine();
            switch (choice.toUpperCase()) {
            case "B":
                gunDirection();
                break;
            case "K":
                saveGameName();
                break;
            case "W":
            case "A":
            case "S":
            case "D":
                ge.userMoveInput(choice);
                break;
            default:
                System.out.println("Invalid Move");
                break;
            }
        }
    }
    
    private void gunDirection() {
        String gunDirection = null;
        System.out.print("What direction would you like to shoot (W, A, S, D)? ");
        gunDirection = sc.nextLine();
        ge.gunShoot(gunDirection);
        ge.moveNinja();
        }

    /**
     * The Graphical User Interface method. Used when player is ingame. Creates
     * a user interface based on graphical images. Uses TODO x game states.
     * 
     */
    private void GUI() {
        // code
    }


    /**
     * Initial menu printing. Asks the player if they want to start a new game,
     * load a save, display a help page, or exit. changes game state
     * accordingly, or exit the program.
     */
    private void firstMenu() {
        System.out.println("Welcome to Espionage Unbound v0.6!\n");
        System.out.println("Please select an option:");
        System.out.println("\t1. New Game");
        System.out.println("\t2. Load Game");
        System.out.println("\t3. About");
        int option = 0;
        if (sc.hasNextInt()) {
            option = sc.nextInt();
        }
        sc.nextLine();

        switch (option) {
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
    }

    private void changeState(int i) {
        state = i;
    }

    /**
     * About/help page. Displays information about how to play, backstory, and
     * returns to firstMenu() if any input is recieved.
     */
    private void aboutMenu() {
        System.out.println("This is a placeholder about page...");
        System.out.println();
        System.out.println("press ENTER to return");
        sc.nextLine();
        changeState(previousState);
    }

    /**
     * UI choice page. Asks the player to choose the type of UI they would like
     * to use for the duration of the program. Sets a global variable
     * accordingly, then send the game state to inGame()
     */
    private void uiMenu() {
        System.out.println("Please choose a UI type:");
        System.out.println("\t1. Text-Based");
        System.out.println("\t2. Graphics-Based **UNDER CONSTRUCTION**");
        System.out.println("\t3. Back");
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
            changeState(previousState);
            break;
        default:
            System.out.println("Invalid choice.");
        }
    }

    /**
     * Main ui state, for when the player is ingame. Displays the map, player
     * stats, and follows prompts from the player.
     */
    private void inGame() {
        if (ui == 1) {
            TUI();
        } else if (ui == 2) {
            GUI();
        } else {
            System.out.println("Invalid UI has been chosen!");
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
	private void pauseMenu() {
		System.out.println(			"PAUSED");
		System.out.println("Press P to Return to Game");
		System.out.println("     1. Load Game");
		System.out.println("     2. Save Game");
		System.out.println("     3. Controls");
		System.out.println("     4. About");
		System.out.println("     5. Exit to Main Menu");
		System.out.println("     6. Exit to Desktop");
		String choice = "";
		choice = sc.nextLine();
		switch(choice.toUpperCase()) {
		case "P":
			changeState(5);
			break;
		case "1":
			changeState(3);
			break;
		case "2":
			changeState(8);
			break;
		case "3":
			changeState(10);
			break;
		case "4":
			changeState(2);
			break;
		case "5":
			if(exitCheck()) {
				changeState(1);
				break;
			} else {
				break;
			}
		case "6":
			exitCheck();
			System.exit(0);
		}
	}


    /**
     * Game over screen clears screen, printing "GAME OVER" onto system out.
     * then asks the player if they want to exit or return to main menu.
     */
    public void gameOver() {
        // code
    }

    private void saveGameName() {
        String saveName;
        System.out.println("Enter name for the save file or enter 'C' to cancel: ");
        saveName = sc.next();
        if(saveName.equals("C")) {
            changeState(previousState);
        } else {
            ge.saveGame(saveName);
        }
    }

    /**
     * Loading saved game page. Asks the player to identify their save file, which the
     * UI will pass to the GE to load up. Note: must first send player to
     * uiMenu() to get the type of UI to use.
     */
    
    public void loadGameName() {
        String loadName;
        System.out.println("Enter save file name or enter 'C' to cancel: ");
        loadName = sc.next();
        if(loadName.equals("C")) {
            changeState(previousState);
        } else {
        ge.loadGame(loadName);
        }
    }
	 
	
	 /**
	  * prints an arbitrary amount of new line commands
	  * used to simulate screen clearing.
	  */
	private void cls() {
		 System.out.println("");
	}
	
	private void setPreviousState(int state) {
	    this.previousState = state;
	}


}
