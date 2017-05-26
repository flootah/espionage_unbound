/**
 * 
 */
package edu.cpp.cs.cs141.prog_final;

/**
 * @author Corey Perez
 *
 */
public class Rooms 
{	
	private int roomRow1 = 1;
	
	private int roomRow2 = 4;
	
	private int roomRow3 = 7;

	private int roomColumn1 = 1;
	
	private int roomColumn2 = 4;
	
	private int roomColumn3 = 7;
	
	private int row;
	
	private int column;
	
	private String roomMark = "R";
	
	public Rooms()
	{
		
	}

	public String getRoomMark() 
	{
		return roomMark;
	}

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

	public int getRow() 
	{
		return row;
	}

	public int getColumn() 
	{
		return column;
	}
}
