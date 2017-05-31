package edu.cpp.cs.cs141.prog_final;

/**
 * Creates an {@link UserInterface()} object, launching the game
 * 
 * @author Corey Perez
 * @author Eduardo Saenz
 * @author Lance Dall
 * @author Grant Posner
 * @author Bumjoong Kim
 * @author Jacob Chong
 *
 */
public class MainLaunch {

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		UserInterface ui = new UserInterface();
		ui.mainMenu();
	}

}
