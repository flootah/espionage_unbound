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
	
	public void setRow(int r)
	{
		row = r;
	}
	
	public void setColumn(int c)
	{
		column = c;
	}
	
	public void move() {
        Random r = new Random();
        int rng = r.nextInt(4);
	    switch (rng) {
	    case 0:
			if (row > 0) {
	        row--;
			} else {
			move();
			}
	        break;
	    case 1:
			if (row < 8) {
	        row++;
			} else {
			move();
			}
	        break;
	    case 2:
			if (column > 0) {
	        column--;
			} else {
			move();
			}
	        break;
	    case 3:
			if (column < 8) {
	        column++;
			} else {
			move();
			}
	        break;
	    }
	}

	public String getNinjaMark() 
	{
		return ninjaMark;
	}
}
