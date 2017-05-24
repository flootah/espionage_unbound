/**
 * 
 */
package edu.cpp.cs.cs141.prog_final;

import java.util.Random;

/**
 * @author Corey Perez
 *
 */
public class Ninja extends ActiveAgents {
    
    private int row;
	
	private int column;
	
	private String ninjaMark = "A";
	
	public Ninja()
	{
		row = calculateRow();
		column = calculateColumn();
	}

	public int calculateColumn() 
	{
		int randColumn = new Random().nextInt(9);
		return randColumn;
	}

	public int calculateRow() 
	{
		int randRow = new Random().nextInt(9);
		return randRow;
	}
	
	public void setRow(int r)
	{
		row = r;
	}
	
	public void setColumn(int c)
	{
		column = c;
	}

	public String getNinjaMark() 
	{
		return ninjaMark;
	}
	
	public void moveDown() 
	{
		row++;
	}

	public void moveUp() 
	{
		row--;
	}

	public void moveRight()
	{
		column++;
	}

	public void moveLeft() 
	{
		column--;
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
