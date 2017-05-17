/**
 * 
 */
package edu.cpp.cs.cs141.final_project;

import java.util.Random;

/**
 * @author Corey Perez
 *
 */
public class GameBoard 
{
	public static final int GRID_SIZE = 9;
	
	private String[][] grid = new String[GRID_SIZE][GRID_SIZE]; 
	
	private String playerMark = "P";
	
	// Change: Put letter mark inside player/ninja/etc. class
	
	private String ninjaMark = "A";
	
	private String briefCaseMark = "B";
	
	private String roomMark = "R";
	
	private String powerUpMark = "S";
	
	private Player player;
	
	private Ninja ninja1;

	// change: Create array of ninjas
	private Ninja ninja2;

	private Ninja ninja3;

	private Ninja ninja4;

	private Ninja ninja5;

	private Ninja ninja6;
	
	private Rooms room1;
	
	//change: create array of rooms
	private Rooms room2;
	
	private Rooms room3;
	
	private Rooms room4;
	
	private Rooms room5;
	
	private Rooms room6;
	
	private Rooms room7;
	
	private Rooms room8;
	
	private Rooms room9;
	
	private Briefcase bCase;
	
	private Bullet bullet;
	
	private Radar radar;
	
	private Invincibility invincible;
	
	public GameBoard(Player play, Ninja enemy1, Ninja enemy2, Ninja enemy3, Ninja enemy4, Ninja enemy5, Ninja enemy6, Briefcase bc,
							Rooms r1, Rooms r2, Rooms r3, Rooms r4, Rooms r5, Rooms r6, Rooms r7, Rooms r8, Rooms r9, Bullet b, Radar r, Invincibility i)
	{
		player = play;
		ninja1 = enemy1;
		ninja2 = enemy2;
		ninja3 = enemy3;
		ninja4 = enemy4;
		ninja5 = enemy5;
		ninja6 = enemy6;
		room1 = r1;
		room2 = r2;
		room3 = r3;
		room4 = r4;
		room5 = r5;
		room6 = r6;
		room7 = r7;
		room8 = r8;
		room9 = r9;
		bCase  = bc;
		bullet = b;
		radar = r;
		invincible = i;
		grid[player.getRow()][player.getColumn()] = getPlayerMark();
		grid[room1.getRoomRow1()][room1.getRoomColumn1()] = getRoomMark();
		grid[room1.getRoomRow1()][room1.getRoomColumn2()] = getRoomMark();
		grid[room1.getRoomRow1()][room1.getRoomColumn3()] = getRoomMark();
		grid[room1.getRoomRow2()][room1.getRoomColumn1()] = getRoomMark();
		grid[room1.getRoomRow2()][room1.getRoomColumn2()] = getRoomMark();
		grid[room1.getRoomRow2()][room1.getRoomColumn3()] = getRoomMark();
		grid[room1.getRoomRow3()][room1.getRoomColumn1()] = getRoomMark();
		grid[room1.getRoomRow3()][room1.getRoomColumn2()] = getRoomMark();
		grid[room1.getRoomRow3()][room1.getRoomColumn3()] = getRoomMark();
		calculateNinjaPositions();
		calculateBriefCasePosition();
		calculatePowerUpPositions();
	}

	public String toString()
	{
		String result = "";
		
		for(String[] row : grid)
		{
			for(String column : row)
			{
				result += column == null ? "[*]" : "[" + getPlayerMark() + "]";	
				
			}
			result += "\n";
		}
		
		return result;
	}

	public void printGrid()
	{
		grid[player.getRow() - 1][player.getColumn()] = " ";
		grid[player.getRow() - 2][player.getColumn()] = " ";
		grid[player.getRow()][player.getColumn() + 1] = " ";
		grid[player.getRow()][player.getColumn() + 2] = " ";
		
		for(int i = 0; i < grid.length; i++)
		{
			for(int j = 0; j < grid[i].length; j++)
			{
				if((grid[i][j] == null) || ((grid[i][j] != null) && (grid[i][j] != getPlayerMark()) && (grid[i][j] != " ")))
				{
					grid[i][j] = "*";
				}
				System.out.print("[" + grid[i][j] + "]");
			}
			System.out.println();
		}
	}
	
	public void printGridDebug()
	{
		for(int i = 0; i < grid.length; i++)
		{
			for(int j = 0; j < grid[i].length; j++)
			{
				if(grid[i][j] == null)
				{
					grid[i][j] = " ";
				}
				System.out.print("[" + grid[i][j] + "]");
			}
			System.out.println();
		}
	}
	
	public void calculateNinjaPositions()
	{
		int counter = 0;
		
		grid[8][1] = getPlayerMark();
		grid[8][2] = getPlayerMark();
		grid[8][3] = getPlayerMark();
		grid[7][0] = getPlayerMark();
		grid[6][0] = getPlayerMark();
		grid[5][0] = getPlayerMark();
		
		while(counter < 6)
		{
			int randRow = ninja1.calculateRow();
			int randColumn = ninja1.calculateColumn();
			
			if(grid[randRow][randColumn] == null)
			{
				grid[randRow][randColumn] = getNinjaMark();
				counter++;
			}
		}
		
		grid[8][1] = null;
		grid[8][2] = null;
		grid[8][3] = null;
		grid[7][0] = null;
		grid[6][0] = null;
		grid[5][0] = null;
	}
	
	public void calculateBriefCasePosition()
	{
		int randNum = new Random().nextInt(9);
		
		switch(randNum)
		{
		case 0:
			grid[room1.getRoomRow1()][room1.getRoomColumn1()] = getBriefCaseMark();
			break;
		case 1:
			grid[room1.getRoomRow1()][room1.getRoomColumn2()] = getBriefCaseMark();
			break;
		case 2:
			grid[room1.getRoomRow1()][room1.getRoomColumn3()] = getBriefCaseMark();
			break;
		case 3:
			grid[room1.getRoomRow2()][room1.getRoomColumn1()] = getBriefCaseMark();
			break;
		case 4:
			grid[room1.getRoomRow2()][room1.getRoomColumn2()] = getBriefCaseMark();
			break;
		case 5:
			grid[room1.getRoomRow2()][room1.getRoomColumn3()] = getBriefCaseMark();
			break;
		case 6:
			grid[room1.getRoomRow3()][room1.getRoomColumn1()] = getBriefCaseMark();
			break;
		case 7:
			grid[room1.getRoomRow3()][room1.getRoomColumn2()] = getBriefCaseMark();
			break;
		case 8:
			grid[room1.getRoomRow3()][room1.getRoomColumn3()] = getBriefCaseMark();
			break;	
		}
	}
	
	public void calculatePowerUpPositions()
	{
		int counter = 0;
		
		while(counter < 3)
		{
			int randRow = bullet.calculateRow();
			int randColumn = bullet.calculateColumn();
			
			if(grid[randRow][randColumn] == null)
			{
				grid[randRow][randColumn] = getPowerUpMark();
				counter++;
			}
		}
	}

	public String getPlayerMark()
	{
		return playerMark;
	}
	
	public String getNinjaMark()
	{
		return ninjaMark;
	}
	
	public String getBriefCaseMark()
	{
		return briefCaseMark;
	}
	
	private String getRoomMark() 
	{
		return roomMark;
	}
	
	private String getPowerUpMark() 
	{
		return powerUpMark;
	}
}
