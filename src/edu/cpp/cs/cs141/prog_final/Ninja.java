/**
 * 
 */
package edu.cpp.cs.cs141.prog_final;

import java.util.Random;

/**
 * @author Corey Perez
 *
 */
public class Ninja 
{
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
		Random rng = new Random();
		int randColumn = rng.nextInt(9);
		while(randColumn ==  1 || randColumn == 4 || randColumn == 7) {
			randColumn = rng.nextInt(9);
		}
		return randColumn;
	}

	public int calculateRow() 
	{
		int randRow = new Random().nextInt(9);
		while(randRow ==  1 || randRow == 4 || randRow == 7) {
			randRow = new Random().nextInt(9);
		}
		return randRow;
	}

	public String getNinjaMark() 
	{
		return ninjaMark;
	}
	
	public void setRow(int r)
	{
		row = r;
	}
	
	public void setColumn(int c)
	{
		column = c;
	}

	public int getRow() 
	{
		return row;
	}

	public int getColumn() 
	{
		return column;
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
	
	
	
}
