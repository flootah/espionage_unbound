/**
 * 
 */
package edu.cpp.cs.cs141.final_project;

/**
 * @author Corey Perez
 *
 */
public class Radar extends PowerUps
{
	private int row;
	
	private int column;
	
	public Radar()
	{
		row = calculateRow();
		column = calculateColumn();
	}
	
}
