/**
 * 
 */
package edu.cpp.cs.cs141.prog_final;

/**
 * @author Corey Perez
 *
 */
public class Bullet extends PowerUps 
{
	private int row;
	
	private int column;
	
	public Bullet()
	{
		row = calculateRow();
		column = calculateColumn();
	}
	
	
}
