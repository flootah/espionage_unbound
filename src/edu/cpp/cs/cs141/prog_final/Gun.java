package edu.cpp.cs.cs141.prog_final;

import java.io.Serializable;

/**
 * The gun that the player has. It has {@link #ammo} and can be shot. 
 * Ammo can be replenished by stepping over the {@link Bullet()} powerup
 * 
 * @author Corey Perez
 * @author Eduardo Saenz
 * @author Lance Dall
 * @author Grant Posner
 * @author Bumjoong Kim
 * @author Jacob Chong
 *
 */
public class Gun implements Serializable
{
	/**
	 * The amount of ammunition the player has in their gun
	 */
	private int ammo;
	
	/**
	 * The amount of {@link #ammo} the player could possibly have
	 */
	private final int maxAmmo = 1;
	
	 /**
	  * Constructor for the {@link #Gun()} class
	  * The player starts off with {@link #maxAmmo}
	  */
	public Gun()
	{
		ammo = maxAmmo;
	}
	
	/**
	 * Used when the player walks over a {@link Bullet} powerup.
	 * Sets the current {@link #ammo} to {@link #maxAmmo}
	 */
	public void reload()
	{
		if(ammo == 0)
		{
			ammo = maxAmmo;
		}
	}
	
	/**
	 * Getter for {@link #ammo}
	 * @return {@link #ammo}
	 */
	public int getAmmo()
	{
		return ammo;
	}
	
	/**
	 * Uses the {@link #ammo} in the player's {@link #Gun()}
	 * Reduces the {@link #ammo} by 1
	 */
	public void useBullet()
	{
		ammo--;
	}
	
	/**
	 * Adds 1 {@link #ammo} to the player's {@link #Gun()}
	 * Is used when the player walks over the {@link Bullet()} powerup
	 */
	public void gainBullet() {
		ammo++;
	}

}
