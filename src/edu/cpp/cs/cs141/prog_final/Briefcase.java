package edu.cpp.cs.cs141.prog_final;

import java.io.Serializable;

/**
 * This class defines the object of a briefcase, which is the end goal
 * of the game. 
 * 
 * @author Corey Perez
 * @author Eduardo Saenz
 * @author Lance Dall
 * @author Grant Posner
 * @author Bumjoong Kim
 * @author Jacob Chong
 *
 */
public class Briefcase implements Serializable
{
	/**
	 * Defines the row of where the {@link Briefcase} is on the {@link GameBoard}
	 */
	private int row;
	
	/**
	 * Defines the column of where the {@link Briefcase} is on the {@link GameBoard}
	 */
	private int column;
	
	/**
	 * The mark that the {@link Briefcase} has on the {@link GameBoard}
	 */
	private String briefCaseMark = "B";
	
	/**
	 * Getter for the {@link #briefCaseMark}
	 * @return {@link #briefCaseMark}
	 */
	public String getBriefCaseMark()
	{
		return briefCaseMark;
	}
	
	/**
	 * Setter for the {@link #row} of the briefcase
	 * @param r, which is the {@link #row} of the briefcase
	 */
	public void setRow(int r)
	{
		row = r;
	}
	
	/**
	 * Setter for the {@link #column} of the briefcase
	 * @param c, which is the {@link #column} of the briefcase
	 */
	public void setColumn(int c)
	{
		column = c;
	}
	
	/**
	 * Getter for the {@link #row} of the briefcase
	 * @return {@link #row}
	 */
	public int getRow() 
	{
		return row;
	}

	/**
	 * Getter for the {@link #column} of the briefcase
	 * @return {@link #column}
	 */
	public int getColumn()
	{
		return column;
	}
	
}
