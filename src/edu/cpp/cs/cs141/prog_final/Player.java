/**
 * 
 */
package edu.cpp.cs.cs141.prog_final;

/**
 * @author Corey Perez
 *
 */
public class Player extends ActiveAgents {

    private final int maxLives = 3;
	
	private int lives;
	
	private Gun gun;
	
	public Player(Gun weapon)
	{
		gun = weapon;
		lives = maxLives;
	}
	
	public void shoot()
	{
		gun.shoot();
	}
	
	public void reloadPlayerGun()
	{
		gun.reload();
	}
	
	public int getLives()
	{
		return lives;
	}

}
