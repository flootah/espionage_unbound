/**
 * 
 */
package edu.cpp.cs.cs141.prog_final;

/**
 * @author Corey Perez
 *
 */
public class Invincibility extends PowerUps {
    
    private int row;
	
	private int column;
	
	private String invincibleMark = "i";

	public Invincibility()
	{
		row = calculateRow();
		column = calculateColumn();
	}

	public String getInvincibleMark() 
	{
		return invincibleMark;
	}

}
