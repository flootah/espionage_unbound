package edu.cpp.cs.cs141.prog_final;

import java.io.Serializable;
import java.util.Random;

/**
 * This is an interface used to define the powerups that the player can obtain.
 * All of the powerups have an {@link #row} and {@link #column} that define where
 * it is on the {@link GameBoard}, and {@link #available} that defines if the 
 * powerup has been used or not
 * 
 * @author Corey Perez
 * @author Eduardo Saenz
 * @author Lance Dall
 * @author Grant Posner
 * @author Bumjoong Kim
 * @author Jacob Chong
 *
 */
public abstract class PowerUps implements Serializable
{	
	/**
	 * This defines the row of the object, which is half of what
	 * defines the powerup's position on the grid.
	 */
	protected int row;
	
	/**
	 * This defines the column of the object, which is the other 
	 * half of what defines the powerup's position on the grid.
	 */
	protected int column;
	
	/**
	 * This shows whether or not the powerup is has been used
	 */
	protected boolean available;
	
	/**
	 * The constructor for the PowerUps class
	 */
	public PowerUps() {
		available = true;
	}
	
	/**
	 * Randomly defines the row that the powerup is initialized on
	 * @return The {@link #row} that the powerup starts on
	 */
	public int calculateRow()
	{
		int randRow = new Random().nextInt(9);
		return randRow;
	}
	
	/**
	 * Randomly defines the column that the powerup is initialized on
	 * @return The {@link #column} that the powerup starts on
	 */
	public int calculateColumn()
	{
		int randColumn = new Random().nextInt(9);
		return randColumn;
	}
	
	/**
	 * Setter for the {@link #column} of the powerup
	 * 
	 * Allows for the powerup's initial position to be set. Is used to set
	 * the powerup's position if it's set to the same position as another powerup
	 * @param r, which is the row of the powerup
	 */
	public void setRow(int r) {
		row = r;
	}
	
	/**
	 * Setter for the {@link #column} of the powerup
	 * 
	 * Allows for the powerup's initial position to be set. Is used to set
	 * the powerup's position if it's set to the same position as another powerup
	 * @param c, which is the column of the powerup
	 */
	public void setColumn(int c) {
		column = c;
	}
	
	/**
	 * Getter for the {@link #row} of the powerup
	 * @return {@link #row}
	 */
	public int getRow() {
		return row;
	}
	
	/**
	 * Getter for the {@link #column} of the powerup
	 * @return {@link #column}
	 */
	public int getColumn() {
		return column;
	}
}
