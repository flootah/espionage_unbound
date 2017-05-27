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
	
	private String bulletMark = "b";
	
	public Bullet()
	{
		//row = calculateRow();
		//column = calculateColumn();
	}

	public String getBulletMark() 
	{
		return bulletMark;
	}
	
	public int getRow()
	{
		return row;
	}
	
	public int getColumn()
	{
		return column;
	}
}
