/**
 * 
 */
package edu.cpp.cs.cs141.final_project;

/**
 * @author Corey Perez
 *
 */
public class Gun
{
	private int ammo;
	
	private final int maxAmmo = 1;
	
	public Gun()
	{
		ammo = maxAmmo;
	}

	
	public void shoot()
	{
		if(ammo > 0)
		{
			ammo--;
		}
	}
	
	public void reload()
	{
		ammo = maxAmmo;
	}
	
	public int getAmmo()
	{
		return ammo;
	}

}
