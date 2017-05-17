/**
 * 
 */
package edu.cpp.cs.cs141.prog_final;

/**
 * @author Corey Perez
 *
 */
public class Invincibility extends PowerUps 
{
	private int row;
	
	private int column;

	public Invincibility()
	{
		row = calculateRow();
		column = calculateColumn();
	}
	
}
