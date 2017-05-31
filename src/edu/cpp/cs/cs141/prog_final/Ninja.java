package edu.cpp.cs.cs141.prog_final;

import java.io.Serializable;
import java.util.Random;

/**
 * A Ninja, who is the main enemy of the game. Is created and put on a random
 * cell on the board that's not a room. It kills the player if he's on a space next
 * to them. It can die if the player shoots them.
 * 
 * @author Corey Perez
 * @author Eduardo Saenz
 * @author Lance Dall
 * @author Grant Posner
 * @author Bumjoong Kim
 * @author Jacob Chong
 *
 */
public class Ninja implements Serializable
{
	/**
	 * Defines the row of where the {@link #Ninja()} is on the {@link GameBoard}
	 */
	private int row;
	
	/**
	 * Defines the column of where the {@link #Ninja()} is on the {@link GameBoard}
	 */
	private int column;
	
	/**
	 * The mark that the {@link #Ninja()} has on the {@link GameBoard}
	 */
	private String ninjaMark = "A";

	/**
	 * Boolean that determines whether the {@link #Ninja()} is alive or not
	 */
	private boolean alive;
	
	/**
	 * Constructor for the {@link #Ninja()} class
	 * Ninjas start off with a random row, column, and are alive
	 */
	public Ninja()
	{
		row = calculateRow();
		column = calculateColumn();
		alive = true;
	}

	/**
	 * Calculates the {@link #column} randomly for the Ninjas
	 * Doesn't allow the Ninja to spawn on rooms where the {@link Briefcase()} can be
	 * @return {@link #column}
	 */
	public int calculateColumn() 
	{
		Random rng = new Random();
		int randColumn = rng.nextInt(9);
		while(randColumn ==  1 || randColumn == 4 || randColumn == 7) {
			randColumn = rng.nextInt(9);
		}
		return randColumn;
	}

	/**
	 * Calculates the {@link #row} randomly for the Ninjas
	 * Doesn't allow the Ninja to spawn on rooms where the {@link Briefcase()} can be
	 * @return {@link #row}
	 */
	public int calculateRow() 
	{
		int randRow = new Random().nextInt(9);
		while(randRow ==  1 || randRow == 4 || randRow == 7) {
			randRow = new Random().nextInt(9);
		}
		return randRow;
	}

	/**
	 * Getter for the {@link #ninjaMark}
	 * @return {@link #ninjaMark}
	 */
	public String getNinjaMark() 
	{
		return ninjaMark;
	}
	
	/**
	 * Setter for the {@link #row}
	 * @param r
	 */
	public void setRow(int r)
	{
		row = r;
	}
	
	/**
	 * Setter for the {@link #column}
	 * @param c
	 */
	public void setColumn(int c)
	{
		column = c;
	}

	/**
	 * Getter for the {@link #row}
	 * @return {@link #row}
	 */
	public int getRow() 
	{
		return row;
	}

	/**
	 * Getter for the {@link #column}
	 * @return {@link #column}
	 */
	public int getColumn() 
	{
		return column;
	}

	/**
	 * Makes the {@link #Ninja()} move down
	 */
	public void moveDown() 
	{
		row++;
	}

	/**
	 * Makes the {@link #Ninja()} move up
	 */
	public void moveUp() 
	{
		row--;
	}

	/**
	 * Makes the {@link #Ninja()} move right
	 */
	public void moveRight()
	{
		column++;
	}

	/**
	 * Makes the {@link #Ninja()} move left
	 */
	public void moveLeft() 
	{
		column--;
	}

	/**
	 * Sets {@link #alive} to false.
	 * This kills the {@link #Ninja()}
	 */
	public void die() {
		alive = false;
	}
	
	/**
	 * Getter for the status of the {@link #Ninja()}'s life
	 * @return {@link #alive}
	 */
	public boolean isAlive() {
		return alive;
	}
	
	
	
}
