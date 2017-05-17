/**
 * 
 */
package edu.cpp.cs.cs141.prog_final;

/**
 * @author Corey Perez
 *
 */
public class Player extends ActiveAgents {
	
	private int lives;
	
	private Gun gun;
	
	public Player(Gun gun, int lives)
	{
	    this.gun = gun;
	    this.lives = lives;
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
	
	public String toString(){ 
	    String playerInfo = "[P]";
	    return playerInfo;
	}

}
