/**
 * 
 */
package edu.cpp.cs.cs141.prog_final;

import java.util.Random;

/**
 * @author Corey Perez
 *
 */
public abstract class PowerUps 
{	
	protected int row;
	protected int column;
	
	public int calculateRow()
	{
		int randRow = new Random().nextInt(9);
		return randRow;
	}
	
	public int calculateColumn()
	{
		int randColumn = new Random().nextInt(9);
		return randColumn;
	}
	
	public void setRow(int r) {
		row = r;
	}
	
	public void setColumn(int c) {
		row = c;
	}
	
	public int getRow() {
		return row;
	}
	
	public int getColumn() {
		return column;
	}
}
