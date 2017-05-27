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
	
	public void move()
	{
	     int rng = new Random().nextInt(4);
		    switch (rng) 
		    {
		    case 0:
				if (row > 0) 
				{
					row--;
				} 
				else 
				{
					move();
				}
		        break;
		    case 1:
				if (row < 8) 
				{
					row++;
				} 
				else 
				{
					move();
				}
		        break;
		    case 2:
				if (column > 0) 
				{
					column--;
				} 
				else 
				{
					move();
				}
		        break;
		    case 3:
				if (column < 8) 
				{
					column++;
				} 
				else 
				{
					move();
				}
		        break;
		    }

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
