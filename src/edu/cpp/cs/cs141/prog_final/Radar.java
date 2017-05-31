package edu.cpp.cs.cs141.prog_final;

/**
 * The radar powerup which shows where the briefcase is to the player.
 * 
 * @author Corey Perez
 * @author Eduardo Saenz
 * @author Lance Dall
 * @author Grant Posner
 * @author Bumjoong Kim
 * @author Jacob Chong
 *
 */
public class Radar extends PowerUps
{
	/**
	 * The mark that represents the {@link #Radar()} on the {@link #GameBoard()}
	 */
	private String radarMark = "r";

	/**
	 * Getter for {@link #radarMark}
	 * @return {@link #radarMark}
	 */
	public String getRadarMark() 
	{
		return radarMark;
	}
	
	/**
	 * Sets {@link #available} to false
	 * When the {@link #Radar()} is used
	 */
	public void used()
	{
		available = false;
	}
	
	/**
	 * Uses the {@link #Radar()}
	 * @return {@link #available}
	 */
	public boolean isUsed()
	{
		return available;
	}
	
}
