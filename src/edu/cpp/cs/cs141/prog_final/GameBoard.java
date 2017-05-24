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
	
	public GameBoard(Player play, Briefcase bc, Bullet b, Radar r, Invincibility i)
	{
		player = play;
		setNinjas();
		setRooms();
		bCase  = bc;
		bullet = b;
		radar = r;
		invincible = i;
		calculatePlayerPosition();
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
	
	public void setNinjas()
	{
		for(int i = 0; i < ninjas.length; i++)
		{
			ninjas[i] = new Ninja();
		}
	}
	
	public void setRooms()
	{
		for(int i = 0; i < rooms.length; i++)
		{
			rooms[i] = new Rooms();
		}
	}
	
	public void calculatePlayerPosition()
	{
		grid[player.getRow()][player.getColumn()] = getPlayerMark();
	}
	
	public void calculateRoomPositions()
	{
		for(int i = 0; i < rooms.length; i++)
		{
			rooms[i].setRow(i);
			rooms[i].setColumn(i);
			grid[rooms[i].getRow()][rooms[i].getColumn()] = getRoomMark();
		}
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
			grid[rooms[0].getRow()][rooms[0].getColumn()] = getBriefCaseMark();
			bCase.setRow(rooms[0].getRow());
			bCase.setColumn(rooms[0].getColumn());
			break;
		case 1:
			grid[rooms[1].getRow()][rooms[1].getColumn()] = getBriefCaseMark();
			bCase.setRow(rooms[1].getRow());
			bCase.setColumn(rooms[1].getColumn());
			break;
		case 2:
			grid[rooms[2].getRow()][rooms[2].getColumn()] = getBriefCaseMark();
			bCase.setRow(rooms[2].getRow());
			bCase.setColumn(rooms[2].getColumn());
			break;
		case 3:
			grid[rooms[3].getRow()][rooms[3].getColumn()] = getBriefCaseMark();
			bCase.setRow(rooms[3].getRow());
			bCase.setColumn(rooms[3].getColumn());
			break;
		case 4:
			grid[rooms[4].getRow()][rooms[4].getColumn()] = getBriefCaseMark();
			bCase.setRow(rooms[4].getRow());
			bCase.setColumn(rooms[4].getColumn());
			break;
		case 5:
			grid[rooms[5].getRow()][rooms[5].getColumn()] = getBriefCaseMark();
			bCase.setRow(rooms[5].getRow());
			bCase.setColumn(rooms[5].getColumn());
			break;
		case 6:
			grid[rooms[6].getRow()][rooms[6].getColumn()] = getBriefCaseMark();
			bCase.setRow(rooms[6].getRow());
			bCase.setColumn(rooms[6].getColumn());
			break;
		case 7:
			grid[rooms[7].getRow()][rooms[7].getColumn()] = getBriefCaseMark();
			bCase.setRow(rooms[7].getRow());
			bCase.setColumn(rooms[7].getColumn());
			break;
		case 8:
			grid[rooms[8].getRow()][rooms[8].getColumn()] = getBriefCaseMark();
			bCase.setRow(rooms[8].getRow());
			bCase.setColumn(rooms[8].getColumn());
			break;	
		}
	}
	
	public void calculateBulletPosition()
	{
		int counter = 0;
		
		while(counter < 1)
		{
			int randRow = bullet.calculateRow();
			int randColumn = bullet.calculateColumn();
		
			if(grid[randRow][randColumn] == null)
			{
				grid[randRow][randColumn] = getBulletMark();
				counter++;
			}
		}
	}
	
	public void calculateRadarPosition()
	{
		int counter = 0;
		
		while(counter < 1)
		{
			int randRow = radar.calculateRow();
			int randColumn = radar.calculateColumn();
		
			if(grid[randRow][randColumn] == null)
			{
				grid[randRow][randColumn] = getRadarMark();
				counter++;
			}
		}
	}
	
	public void calculateInvinciblePosition()
	{
		int counter = 0;
		
		while(counter < 1)
		{
			int randRow = invincible.calculateRow();
			int randColumn = invincible.calculateColumn();
		
			if(grid[randRow][randColumn] == null)
			{
				grid[randRow][randColumn] = getInvincibleMark();
				counter++;
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
	
	public void printNewBoard()
	{		
		for(int i = 0; i < grid.length; i++)
		{
			for(int j = 0; j < grid[i].length; j++)
			{
				if(grid[i][j] == getPlayerMark() || grid[i][j] == getNinjaMark())
				{
					grid[i][j] = " ";
				}
				grid[player.getRow()][player.getColumn()] = getPlayerMark();
				grid[ninjas[0].getRow()][ninjas[0].getColumn()] = getNinjaMark();
				System.out.print("[" + grid[i][j] + "]");
			}
			System.out.println();
		}
	}
	
	public int getPlayerRow()
	{
		return player.getRow();
	}
	
	public int getPlayerColumn()
	{
		return player.getColumn();
	}
	
	public void movePlayerUp()
	{
		player.moveUp();
	}
	
	public void movePlayerDown()
	{
		player.moveDown();
	}
	
	public void movePlayerRight()
	{
		player.moveRight();
	}
	
	public void movePlayerLeft()
	{
		player.moveLeft();
	}
	
	public int getNinjaRow()
	{
		return ninjas[0].getRow();
	}
	
	public int getNinjaColumn()
	{
		return ninjas[0].getColumn();
	}
	
	public void moveNinjaDown()
	{
		ninjas[0].moveDown();
	}
	
	public void moveNinjaUp()
	{
		ninjas[0].moveUp();
	}
	
	public void moveNinjaRight()
	{
		ninjas[0].moveRight();
	}
	
	public void moveNinjaLeft()
	{
		ninjas[0].moveLeft();
	}
}
