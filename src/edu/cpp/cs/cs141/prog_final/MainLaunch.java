/**
 * 
 */
package edu.cpp.cs.cs141.prog_final;

/**
 * @author Corey Perez
 *
 */
public class MainLaunch {

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		UserInterface ui = new UserInterface(new GameEngine());
		ui.mainMenu();
	}

}
