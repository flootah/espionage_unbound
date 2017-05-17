/**
 * 
 */
package edu.cpp.cs.cs141.final_project;

import java.util.Random;

/**
 * @author Corey Perez
 *
 */
public abstract class PowerUps 
{	
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
}
