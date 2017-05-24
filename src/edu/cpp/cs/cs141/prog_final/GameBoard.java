package edu.cpp.cs.cs141.prog_final;
import java.util.Random;
public class GameBoard {
    
    public static final int GRID_SIZE = 9;
	
	public static final int NUM_NINJAS = 6;
	
	public static final int NUM_ROOMS = 9;
	
	private String[][] grid = new String[GRID_SIZE][GRID_SIZE]; 
	
	private Player player;
	
	private Ninja[] ninjas = new Ninja[NUM_NINJAS];
	
	private Rooms[] rooms = new Rooms[NUM_ROOMS];
	
	private Briefcase bCase;
	
	private Bullet bullet;
	
	private Radar radar;
	
	private Invincibility invincible;
	
	public GameBoard(Player play, Ninja enemy, Briefcase bc, Rooms room, Bullet b, Radar r, Invincibility i)
	{
		player = play;
		setNinjas(enemy);
		setRooms(room);
		bCase  = bc;
		bullet = b;
		radar = r;
		invincible = i;
		grid[player.getRow()][player.getColumn()] = getPlayerMark();
		calculateRoomPositions();
		calculateNinjaPositions();
		calculateBriefCasePosition();
		calculateBulletPosition();
		calculateInvinciblePosition();
		calculateRadarPosition();
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
				if((grid[i][j] == null) || ((grid[i][j] != getPlayerMark()) && (grid[i][j] != " ")))
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
	
	public void setNinjas(Ninja ninja)
	{
		for(int i = 0; i < ninjas.length; i++)
		{
			ninjas[i] = ninja;
		}
	}
	
	public void setRooms(Rooms room)
	{
		for(int i = 0; i < rooms.length; i++)
		{
			rooms[i] = room;
		}
	}
	
	public void calculateRoomPositions()
	{
		grid[rooms[0].getRoomRow1()][rooms[0].getRoomColumn1()] = getRoomMark();
		grid[rooms[1].getRoomRow1()][rooms[1].getRoomColumn2()] = getRoomMark();
		grid[rooms[2].getRoomRow1()][rooms[2].getRoomColumn3()] = getRoomMark();
		grid[rooms[3].getRoomRow2()][rooms[3].getRoomColumn1()] = getRoomMark();
		grid[rooms[4].getRoomRow2()][rooms[4].getRoomColumn2()] = getRoomMark();
		grid[rooms[5].getRoomRow2()][rooms[5].getRoomColumn3()] = getRoomMark();
		grid[rooms[6].getRoomRow3()][rooms[6].getRoomColumn1()] = getRoomMark();
		grid[rooms[7].getRoomRow3()][rooms[7].getRoomColumn2()] = getRoomMark();
		grid[rooms[8].getRoomRow3()][rooms[8].getRoomColumn3()] = getRoomMark();
	}
	
	public void calculateNinjaPositions()
	{
		int counter = 0;
		
		while(counter < 6)
		{
			int randRow = ninjas[counter].calculateRow();
			int randColumn = ninjas[counter].calculateColumn();
			
			if(randRow < 6 && grid[randRow][randColumn] == null)
			{
				grid[randRow][randColumn] = getNinjaMark();
				ninjas[counter].setRow(randRow);
				ninjas[counter].setColumn(randColumn);
				counter++;
			}
			
			if(randRow >= 6 && randColumn >= 3 && grid[randRow][randColumn] == null)
			{
				grid[randRow][randColumn] = getNinjaMark();
				ninjas[counter].setRow(randRow);
				ninjas[counter].setColumn(randColumn);
				counter++;
			}
		}
	}
	
	public void calculateBriefCasePosition()
	{
		int randNum = new Random().nextInt(9);
		
		switch(randNum)
		{
		case 0:
			grid[rooms[0].getRoomRow1()][rooms[0].getRoomColumn1()] = getBriefCaseMark();
			break;
		case 1:
			grid[rooms[0].getRoomRow1()][rooms[0].getRoomColumn2()] = getBriefCaseMark();
			break;
		case 2:
			grid[rooms[0].getRoomRow1()][rooms[0].getRoomColumn3()] = getBriefCaseMark();
			break;
		case 3:
			grid[rooms[0].getRoomRow2()][rooms[0].getRoomColumn1()] = getBriefCaseMark();
			break;
		case 4:
			grid[rooms[0].getRoomRow2()][rooms[0].getRoomColumn2()] = getBriefCaseMark();
			break;
		case 5:
			grid[rooms[0].getRoomRow2()][rooms[0].getRoomColumn3()] = getBriefCaseMark();
			break;
		case 6:
			grid[rooms[0].getRoomRow3()][rooms[0].getRoomColumn1()] = getBriefCaseMark();
			break;
		case 7:
			grid[rooms[0].getRoomRow3()][rooms[0].getRoomColumn2()] = getBriefCaseMark();
			break;
		case 8:
			grid[rooms[0].getRoomRow3()][rooms[0].getRoomColumn3()] = getBriefCaseMark();
			break;	
		}
	}
	
	public void calculateBulletPosition()
	{
		boolean validCell = false;
		int randRow = bullet.calculateRow();
		int randColumn = bullet.calculateColumn();
		
		while(!validCell)
		{
			if(grid[randRow][randColumn] == null)
			{
				grid[randRow][randColumn] = getBulletMark();
				validCell = true;
			}else{
			    calculateBulletPosition();
			}
		}
	}
	
	public void calculateRadarPosition()
	{
		boolean validCell = false;
		int randRow = radar.calculateRow();
		int randColumn = radar.calculateColumn();
		
		while(!validCell)
		{
			if(grid[randRow][randColumn] == null)
			{
				grid[randRow][randColumn] = getRadarMark();
				validCell = true;
			}else{
			    calculateRadarPosition();
			}
		}
	}
	
	public void calculateInvinciblePosition()
	{
		boolean validCell = false;
		int randRow = invincible.calculateRow();
		int randColumn = invincible.calculateColumn();
		
		while(!validCell)
		{
			if(grid[randRow][randColumn] == null)
			{
				grid[randRow][randColumn] = getInvincibleMark();
				validCell = true;
			}else{
			    calculateInvinciblePosition();
			}
		}
	}	

	public String getPlayerMark()
	{
		return player.getPlayerMark();
	}
	
	public String getNinjaMark()
	{
		return ninjas[0].getNinjaMark();
	}
	
	public String getBriefCaseMark()
	{
		return bCase.getBriefCaseMark();
	}
	
	public String getRoomMark() 
	{
		return rooms[0].getRoomMark();
	}
	
	public String getBulletMark()
	{
		return bullet.getBulletMark();
	}
	
	public String getInvincibleMark()
	{
		return invincible.getInvincibleMark();
	}
	
	public String getRadarMark()
	{
		return radar.getRadarMark();
	}
}
