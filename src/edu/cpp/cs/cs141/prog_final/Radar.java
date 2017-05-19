/**
 * 
 */
package edu.cpp.cs.cs141.prog_final;

/**
 * @author Corey Perez
 *
 */
public class Radar extends PowerUps{
    
    private int row;
	
	private int column;
	
	private String radarMark = "r";
	
	public Radar()
	{
		row = calculateRow();
		column = calculateColumn();
	}

	public String getRadarMark() 
	{
		return radarMark;
	}

}
