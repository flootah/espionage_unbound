/**
 * 
 */
package edu.cpp.cs.cs141.prog_final;

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

	
	public void shoot(char direction)
	{
		if(ammo > 0)
		{
		    switch (direction) {
		    case 'W':
		    case 'w':
		        shootUp();
		        break;
		    case 'S':
		    case 's':
		        shootDown();
		        break;
		    case 'A':
		    case 'a':
		        shootLeft();
		        break;
		    case 'D':
		    case 'd':
		        shootRight();
		        break;
		    }
			ammo--;
		}
	}
	
	public void shootUp(){
	    //TODO
	}
	
	public void shootDown(){
	    //TODO
	}
	
	public void shootLeft(){
	    //TODO
	}
	
	public void shootRight(){
	    //TODO
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
