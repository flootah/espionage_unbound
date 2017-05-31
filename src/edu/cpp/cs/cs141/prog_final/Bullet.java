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
	
	public void used()
	{
		available = false;
	}
	
	public boolean isUsed()
	{
		return available;
	}
}
