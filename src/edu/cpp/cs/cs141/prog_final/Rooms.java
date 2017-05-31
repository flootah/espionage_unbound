package edu.cpp.cs.cs141.prog_final;

import java.io.Serializable;

/**
 * Rooms that the {@link Briefcase()} can be in. They have their own
 * positions and collision with both {@link Ninja()}s and {@link Player()}s.
 * 
 * @author Corey Perez
 * @author Eduardo Saenz
 * @author Lance Dall
 * @author Grant Posner
 * @author Bumjoong Kim
 * @author Jacob Chong
 *
 */
public class Rooms implements Serializable
{	
	/**
	 * These next 6 lines set the possible rooms for the {@link Briefcase()}
	 */
	private int roomRow1 = 1;
	
	private int roomRow2 = 4;
	
	private int roomRow3 = 7;

	private int roomColumn1 = 1;
	
	private int roomColumn2 = 4;
	
	private int roomColumn3 = 7;
	
	/**
	 * The possible row that the {@link #Rooms()} can be on
	 */
	private int row;
	
	/**
	 * The possible column the {@link #Rooms()} can be on
	 */
	private int column;
	
	/**
	 * The mark that represents the {@link #Rooms()} on the {@link GameBoard()}
	 */
	private String roomMark = "R";
	
	/**
	 * Getter for the {@link #roomMark}
	 * @return {@link #roomMark}
	 */
	public String getRoomMark() 
	{
		return roomMark;
	}

	/**
	 * Setter for the row that the {@link #Room()} can be on
	 * @param num
	 */
	public void setRow(int num) 
	{
		switch(num)
		{
		case 0:
		case 1:
		case 2:
			row = roomRow1;
			break;
		case 3:
		case 4:
		case 5:
			row = roomRow2;
			break;
		case 6:
		case 7:
		case 8:
			row = roomRow3;
			break;
		}
	}

	/**
	 * Setter for the column that the {@link #Room()} can be on
	 * @param num
	 */
	public void setColumn(int num) 
	{
		switch(num)
		{
		case 0:
		case 3:
		case 6:
			column = roomColumn1;
			break;
		case 1:
		case 4:
		case 7:
			column = roomColumn2;
			break;
		case 2:
		case 5:
		case 8:
			column = roomColumn3;
			break;
		}		
	}

	/**
	 * Getter for the row the {@link #Rooms()} can be on
	 * @return {@link #row}
	 */
	public int getRow() 
	{
		return row;
	}

	/**
	 * Getter for the row the {@link #Rooms()} can be on
	 * @return {@link #column}
	 */
	public int getColumn() 
	{
		return column;
	}
}
