/**
 * 
 */
package edu.cpp.cs.cs141.final_project;

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
