/**
 * 
 */
package edu.cpp.cs.cs141.prog_final;

/**
 * @author Corey Perez
 *
 */

public class Briefcase
{
    private int row;
    
    private int column;

    private String briefCaseMark = "B";
	
	public String getBriefCaseMark()
	{
		return briefCaseMark;
	}
    
    public void setRow(int r)
	{
		row = r;
	}
	
	public void setColumn(int c)
	{
		column = c;
	}

	public int getRow() 
	{
		return row;
	}

	public int getColumn()
	{
		return column;
	}
}