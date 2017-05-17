/**
 * 
 */
package edu.cpp.cs.cs141.final_project;

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
	
	public Rooms()
	{
		roomRow1 = getRoomRow1();
		roomRow2 = getRoomRow2();
		roomRow3 = getRoomRow3();
		roomColumn1 = getRoomColumn1();
		roomColumn2 = getRoomColumn2();
		roomColumn3 = getRoomColumn3();
	}
	
	public int getRoomRow1()
	{
		return roomRow1;
	}
	
	public int getRoomRow2() 
	{
		return roomRow2;
	}

	public int getRoomRow3() 
	{
		return roomRow3;
	}

	public int getRoomColumn1() 
	{
		return roomColumn1;
	}

	public int getRoomColumn2() 
	{
		return roomColumn2;
	}

	public int getRoomColumn3() 
	{
		return roomColumn3;
	}

}
