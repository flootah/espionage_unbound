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
	
	
}
