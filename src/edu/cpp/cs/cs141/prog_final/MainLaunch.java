/**
 * 
 */
package edu.cpp.cs.cs141.final_project;

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
		//ui.printBoard();
		//ui.debugBoard();
		ui.mainMenu();
	}

}
