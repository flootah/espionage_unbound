/**
 * 
 */
package edu.cpp.cs.cs141.prog_final;

import java.util.Random;

/**
 * @author Corey Perez
 *
 */
public class Ninja extends ActiveAgents {
    
    private int row;
	
	private int column;
	
	private String ninjaMark = "A";
	
	public Ninja()
	{
		row = calculateRow();
		column = calculateColumn();
	}

	public int calculateColumn() 
	{
		int randColumn = new Random().nextInt(9);
		return randColumn;
	}

	public int calculateRow() 
	{
		int randRow = new Random().nextInt(9);
		return randRow;
	}
	
	public void move(int rng) {
	    switch (rng) {
	    case 1:
	        row--;
	        break;
	    case 2:
	        row++;
	        break;
	    case 3:
	        column--;
	        break;
	    case 4:
	        column++;
	        break;
	    }
	}

	public String getNinjaMark() 
	{
		return ninjaMark;
	}
}
