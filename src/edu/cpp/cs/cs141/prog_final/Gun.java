/**
 * 
 */
package edu.cpp.cs.cs141.prog_final;

import java.io.Serializable;

/**
 * @author Corey Perez
 *
 */
public class Gun implements Serializable
{
	private int ammo;
	
	private final int maxAmmo = 1;
	
	public Gun()
	{
		ammo = maxAmmo;
	}
	
	public void reload()
	{
		if(ammo == 0)
		{
			ammo = maxAmmo;
		}
	}
	
	public int getAmmo()
	{
		return ammo;
	}
	
	public void useBullet()
	{
		ammo--;
	}
	public void gainBullet() {
		ammo++;
	}

}
