/**
 * 
 */
package edu.cpp.cs.cs141.prog_final;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Random;

/**
 * @author Corey Perez
 *
 */
public class GameEngine 
{
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

	    private GameBoard gb;

	    private Gun gun;
	    
	    private boolean radarActive;
	    
	    private boolean invincibleActive;
	    
	    private boolean bulletActive;
		
		private int[][] look;
		
		private boolean looking;

		private boolean gameOver;

		private boolean briefcaseFound;

	    public GameEngine() {
	    	gun = new Gun();
	        player = new Player(gun);
	        setNinjas();
	        setRooms();
	        bCase = new Briefcase();
	        bullet = new Bullet();
	        radar = new Radar();
	        invincible = new Invincibility();
	        createBuilding();
	        gb = new GameBoard(this);
	        looking = false;
	        gameOver = false;
	        briefcaseFound = false;
	    }

	    /**
	     * initalizes the building, calculating positions for: the player, the
	     * rooms, the ninjas, the briefcase, and powerups.
	     */
	    public void createBuilding() {
	        calculateRoomPositions();
	        calculateNinjaPositions();
	        calculateBriefCasePosition();
	        calculateBulletPosition();
	        calculateInvinciblePosition();
	        calculateRadarPosition();
	    }

	    public void printGrid() {
			gb.printGrid();
		}
	    
	    public void printDebugGrid() {
	        gb.printDebugGrid();
	    }

	    public void userMoveInput(String choice) {
	        switch (choice.toUpperCase()) {
	        case "W":
	        case "S":
	        case "A":
	        case "D":
	            movePlayer(choice);
	            break;
	        }
	    }

	    public void shootGun(String dir) {
	    	int pRow = player.getRow();
	    	int pCol = player.getColumn();	 
	    	if(gun.getAmmo() == 1)
	    	{
	        switch(dir) {
	        case "up":
	        	if(pRow == 0) {
	        		System.out.println("You shot into a wall...");
	        		break;
	        	}
	        	//loop through each row above the player.
	        	gun.useBullet();
	        	rowloop:
	        	for(int i = pRow; i >= 0; i--) {
	        		//loop through each ninja on board.
	        		for(int n = 0; n < NUM_NINJAS; n++) {
	        			//check if current row and column match current ninja location.
	        			if(ninjas[n].isAlive() && i == ninjas[n].getRow() && pCol == ninjas[n].getColumn()) {
	        				//delete the ninja, and break the loop.
	        				ninjas[n].die();
	        				break rowloop;
	        			} else {
	        				//missing debug messages
	        				/*
	        				System.out.println("i = " + i);
	        				System.out.println("missed ninja " + n);
	        				System.out.println("row: " + ninjas[n].calculateRow());
	        				System.out.println("col: " + ninjas[n].calculateColumn());
	        				*/
	        				//go to next ninja/column
	        			}
	        		}
	        	}
	        	moveNinja();
	        	break;
	        case "left":
	        	if(pCol == 0) {
	        		System.out.println("You shot into a wall...");
	        		break;
	        	}
	        	//loop through each column to the left of the player.
	        	gun.useBullet();
	        	rowloop:
	        	for(int i = pCol; i >= 0; i--) {
	        		//loop through each ninja on board.
	        		for(int n = 0; n < NUM_NINJAS; n++) {
	        			//check if current row and column match current ninja location.
	        			if(ninjas[n].isAlive() && i == ninjas[n].getColumn() && pRow == ninjas[n].getRow()) {
	        				//delete the ninja, and break the loop.
	        				ninjas[n].die();
	        				break rowloop;
	        			} else {
	        				//missing debug messages
	        				/*
	        				System.out.println("i = " + i);
	        				System.out.println("missed ninja " + n);
	        				System.out.println("row: " + ninjas[n].calculateRow());
	        				System.out.println("col: " + ninjas[n].calculateColumn());
	        				*/
	        				//go to next ninja/column
	        			}
	        		}
	        	}
	        	moveNinja();
	        	break;
	        case "right":
	        	if(pCol == 8) {
	        		System.out.println("You shot into a wall...");
	        		break;
	        	}
	        	//loop through each column to the right of the player.
	        	gun.useBullet();
	        	rowloop:
	        	for(int i = pCol; i <= 8; i++) {
	        		//loop through each ninja on board.
	        		for(int n = 0; n < NUM_NINJAS; n++) {
	        			//check if current row and column match current ninja location.
	        			if(ninjas[n].isAlive() && i == ninjas[n].getColumn() && pRow == ninjas[n].getRow()) {
	        				//delete the ninja, and break the loop.
	        				ninjas[n].die();
	        				break rowloop;
	        			} else {
	        				//missing debug messages
	        				/*
	        				System.out.println("i = " + i);
	        				System.out.println("missed ninja " + n);
	        				System.out.println("row: " + ninjas[n].calculateRow());
	        				System.out.println("col: " + ninjas[n].calculateColumn());
	        				*/
	        				//go to next ninja/column
	        			}
	        		}
	        	}
	        	moveNinja();
	        	break;
	        case "down":
	        	if(pRow == 8) {
	        		System.out.println("You shot into a wall...");
	        		break;
	        	}
	        	//loop through each row below the player.
	        	gun.useBullet();
	        	rowloop:
	        	for(int i = pRow; i <= 8; i++) {
	        		//loop through each ninja on board.
	        		for(int n = 0; n < NUM_NINJAS; n++) {
	        			//check if current row and column match current ninja location.
	        			if(ninjas[n].isAlive() && i == ninjas[n].getRow() && pCol == ninjas[n].getColumn()) {
	        				//delete the ninja, and break the loop.
	        				ninjas[n].die();
	        				break rowloop;
	        			} else {
	        				//missing debug messages
	        				/*
	        				System.out.println("i = " + i);
	        				System.out.println("missed ninja " + n);
	        				System.out.println("row: " + ninjas[n].calculateRow());
	        				System.out.println("col: " + ninjas[n].calculateColumn());
	        				*/
	        				//go to next ninja/column
	        			}
	        		}
	        	}
	        	moveNinja();
	        	break;
	        default:
	        	System.out.println("Invalid direction within ge.shootGun()");
	        	break;
	        }
	    	}
	    	else
	    		System.out.println("No Ammo");
	    }

	    public void movePlayer(String userMove) {
	        switch (userMove.toUpperCase()) {
	        case "W":
	            if (getPlayerRow() > 0 && !roomCollisionPlayer("up")) {
	                movePlayerUp();
	                setLooking(false);
	            } else {
	                System.out.println("It's a wall.");
	            }
	            break;
	        case "S":
	            if (getPlayerRow() < 8 && !roomCollisionPlayer("down")) {
	                movePlayerDown();
	                setLooking(false);
	            } else {
	                System.out.println("It's a wall.");
	            }
	            break;
	        case "A":
	            if (getPlayerColumn() > 0 && !roomCollisionPlayer("left")) {
	                movePlayerLeft();
	                setLooking(false);
	            } else {
	                System.out.println("It's a wall.");
	            }
	            break;
	        case "D":
	            if (getPlayerColumn() < 8 && !roomCollisionPlayer("right")) {
	                movePlayerRight();
	                setLooking(false);
	            } else {
	                System.out.println("It's a wall.");
	            }
	            break;
	        }
	    }

	    public void moveNinja() {
	    	int counter = 0;
	    	
	        while(counter < 6)
	        {
	            // Below is a debug print to check ninja's coordinates while running
	            // the game.
	            // System.out.println("ninja's " + counter + " processing...");
	            // System.out.println("pre-coordinates: " +
	            // ninja's[counter].getColumn() + " , " + ninja's[counter].getRow());
	            int rng = new Random().nextInt(4);
	            if(ninjas[counter].isAlive())
	            {
				if(rng == 3 && getNinjaRow(counter) > 0 && getNinjaRow(counter) <= 8 && !roomCollisionNinja(counter, "up"))
				{
					moveNinjaUp(counter);
					//System.out.println("postcoordinates: " + ninjas[counter].getColumn() + " , " + ninjas[counter].getRow());
					counter++;
				}
				else
					if(rng == 2 && getNinjaRow(counter) < 8 && getNinjaRow(counter) >= 0 && !roomCollisionNinja(counter, "down"))
					{
						moveNinjaDown(counter);
						//System.out.println("postcoordinates: " + ninjas[counter].getColumn() + " , " + ninjas[counter].getRow());
						counter++;
					}
					else
						if(rng == 1 && getNinjaColumn(counter) >= 0 && getNinjaColumn(counter) < 8 && !roomCollisionNinja(counter, "right"))
						{
							moveNinjaRight(counter);
							//System.out.println("postcoordinates: " + ninjas[counter].getColumn() + " , " + ninjas[counter].getRow());
							counter++;
						}
						else
							if(rng == 0 && getNinjaColumn(counter) <= 8 && getNinjaColumn(counter) > 0 && !roomCollisionNinja(counter, "left"))
							{
								moveNinjaLeft(counter);
								//System.out.println("postcoordinates: " + ninjas[counter].getColumn() + " , " + ninjas[counter].getRow());
								counter++;
							}	
	            } else {
	            	counter++;
	            }
	        }
	    }
	    
	    public boolean isNinjaAlive(int n) {
	    	return ninjas[n].isAlive();
	    }

	    public boolean roomCollisionPlayer(String dir) {
	        boolean collision = false;
	        switch (dir) {
	        case "up":
	            for (int r = 0; r < rooms.length; r++) {
	                if ((player.getRow() - 1 == rooms[r].getRow()) && (player.getColumn() == rooms[r].getColumn())) {
	                    collision = true;
	                }
	            }
	            break;
	        case "left":
	            for (int r = 0; r < rooms.length; r++) {
	                if ((player.getColumn() - 1 == rooms[r].getColumn()) && (player.getRow() == rooms[r].getRow())) {
	                    collision = true;
	                }
	            }
	            break;
	        case "right":
	            for (int r = 0; r < rooms.length; r++) {
	                if ((player.getColumn() + 1 == rooms[r].getColumn()) && (player.getRow() == rooms[r].getRow())) {
	                    collision = true;
	                }
	            }
	            break;
	        case "down":
	            for (int r = 0; r < rooms.length; r++) {
	                if ((player.getRow() + 1 == rooms[r].getRow()) && (player.getColumn() == rooms[r].getColumn())) {
	                    collision = true;
	                }
	            }
	            break;
	        }

	        return collision;
	    }

	    public boolean roomCollisionNinja(int counter, String dir) {
	        boolean collision = false;
	        switch (dir) {
	        case "up":
	            for (int r = 0; r < rooms.length; r++) {
	                if ((ninjas[counter].getRow() - 1 == rooms[r].getRow())
	                        && (ninjas[counter].getColumn() == rooms[r].getColumn())) {
	                    collision = true;
	                }
	            }
	            break;
	        case "left":
	            for (int r = 0; r < rooms.length; r++) {
	                if ((ninjas[counter].getColumn() - 1 == rooms[r].getColumn())
	                        && (ninjas[counter].getRow() == rooms[r].getRow())) {
	                    collision = true;
	                }
	            }
	            break;
	        case "right":
	            for (int r = 0; r < rooms.length; r++) {
	                if ((ninjas[counter].getColumn() + 1 == rooms[r].getColumn())
	                        && (ninjas[counter].getRow() == rooms[r].getRow())) {
	                    collision = true;
	                }
	            }
	            break;
	        case "down":
	            for (int r = 0; r < rooms.length; r++) {
	                if ((ninjas[counter].getRow() + 1 == rooms[r].getRow())
	                        && (ninjas[counter].getColumn() == rooms[r].getColumn())) {
	                    collision = true;
	                }
	            }
	            break;
	        }

	        return collision;
	    }

	    public void setNinjas() {
	        for (int i = 0; i < ninjas.length; i++) {
	            ninjas[i] = new Ninja();
	        }
	    }

	    public void setRooms() {
	        for (int i = 0; i < rooms.length; i++) {
	            rooms[i] = new Rooms();
	        }
	    }

	    // calculators for positions of objects
	    public void calculateRoomPositions() {
	        for (int i = 0; i < rooms.length; i++) {
	            rooms[i].setRow(i);
	            rooms[i].setColumn(i);
	            grid[rooms[i].getRow()][rooms[i].getColumn()] = getRoomMark();
	        }
	    }

	    public void calculateNinjaPositions() {
	        int counter = 0;

	        while (counter < 6) {
	            int randRow = ninjas[counter].calculateRow();
	            int randColumn = ninjas[counter].calculateColumn();

	            if (randRow < 6 && grid[randRow][randColumn] == null) {
	                grid[randRow][randColumn] = getNinjaMark();
	                ninjas[counter].setRow(randRow);
	                ninjas[counter].setColumn(randColumn);
	                counter++;
	            }

	            if (randRow >= 6 && randColumn >= 3 && grid[randRow][randColumn] == null) {
	                grid[randRow][randColumn] = getNinjaMark();
	                ninjas[counter].setRow(randRow);
	                ninjas[counter].setColumn(randColumn);
	                counter++;
	            }
	        }
	    }

	    public void calculateBriefCasePosition() {
	        int randNum = new Random().nextInt(9);

	        switch (randNum) {
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

	    public void calculateBulletPosition() {
	        int counter = 0;

	        while (counter < 1) {
	            int randRow = bullet.calculateRow();
	            int randColumn = bullet.calculateColumn();

	            if (grid[randRow][randColumn] == null) {
	                bullet.setRow(randRow);
	                bullet.setColumn(randColumn);
	                counter++;
	            }
	        }
	    }

	    public void calculateRadarPosition() {
	        int counter = 0;

	        while (counter < 1) {
	            int randRow = radar.calculateRow();
	            int randColumn = radar.calculateColumn();

	            if (grid[randRow][randColumn] == null) {
	                radar.setRow(randRow);
	                radar.setColumn(randColumn);
	                counter++;
	            }
	        }
	    }

	    public void calculateInvinciblePosition() {
	        int counter = 0;

	        while (counter < 1) {
	            int randRow = invincible.calculateRow();
	            int randColumn = invincible.calculateColumn();

	            if (grid[randRow][randColumn] == null) {
	                invincible.setRow(randRow);
	                invincible.setColumn(randColumn);
	                counter++;
	            }
	        }
	    }

	    // several getters for Marks of objects.
	    public String getPlayerMark() {
	        return player.getPlayerMark();
	    }

	    public String getNinjaMark() {
	        return ninjas[0].getNinjaMark();
	    }

	    public String getBriefCaseMark() {
	        return bCase.getBriefCaseMark();
	    }

	    public String getRoomMark() {
	        return rooms[0].getRoomMark();
	    }

	    public String getBulletMark() {
	        return bullet.getBulletMark();
	    }

	    public String getInvincibleMark() {
	        return invincible.getInvincibleMark();
	    }

	    public String getRadarMark() {
	        return radar.getRadarMark();
	    }

	    // getters for player location
	    public int getPlayerRow() {
	        return player.getRow();
	    }

	    public int getPlayerColumn() {
	        return player.getColumn();
	    }

	    // movers for the player's location
	    public void movePlayerUp() {
	        player.moveUp();
	    }

	    public void movePlayerDown() {
	        player.moveDown();
	    }

	    public void movePlayerRight() {
	        player.moveRight();
	    }

	    public void movePlayerLeft() {
	        player.moveLeft();
	    }

	    // getter for ninja location
	    public int getNinjaRow(int num) {
	        if (num == 0) {
	            return ninjas[0].getRow();
	        } else if (num == 1) {
	            return ninjas[1].getRow();
	        } else if (num == 2) {
	            return ninjas[2].getRow();
	        } else if (num == 3) {
	            return ninjas[3].getRow();
	        } else if (num == 4) {
	            return ninjas[4].getRow();
	        } else
	            return ninjas[5].getRow();
	    }

	    public int getNinjaColumn(int num) {
	        if (num == 0) {
	            return ninjas[0].getColumn();
	        } else if (num == 1) {
	            return ninjas[1].getColumn();
	        } else if (num == 2) {
	            return ninjas[2].getColumn();
	        } else if (num == 3) {
	            return ninjas[3].getColumn();
	        } else if (num == 4) {
	            return ninjas[4].getColumn();
	        } else
	            return ninjas[5].getColumn();
	    }

	    // movers for ninjas.
	    public void moveNinjaDown(int num) {
	        ninjas[num].moveDown();
	    }

	    public void moveNinjaUp(int num) {
	        ninjas[num].moveUp();
	    }

	    public void moveNinjaRight(int num) {
	        ninjas[num].moveRight();
	    }

	    public void moveNinjaLeft(int num) {
	        ninjas[num].moveLeft();
	    }

	    // getters for other objects
	    public int getRoomColumn(int num) {
	        switch (num) {
	        case 0:
	            return rooms[0].getColumn();
	        case 1:
	            return rooms[1].getColumn();
	        case 2:
	            return rooms[2].getColumn();
	        case 3:
	            return rooms[3].getColumn();
	        case 4:
	            return rooms[4].getColumn();
	        case 5:
	            return rooms[5].getColumn();
	        case 6:
	            return rooms[6].getColumn();
	        case 7:
	            return rooms[7].getColumn();
	        case 8:
	            return rooms[8].getColumn();
	        default:
	            return 0;
	        }
	    }

	    public int getRoomRow(int num) {
	        switch (num) {
	        case 0:
	            return rooms[0].getRow();
	        case 1:
	            return rooms[1].getRow();
	        case 2:
	            return rooms[2].getRow();
	        case 3:
	            return rooms[3].getRow();
	        case 4:
	            return rooms[4].getRow();
	        case 5:
	            return rooms[5].getRow();
	        case 6:
	            return rooms[6].getRow();
	        case 7:
	            return rooms[7].getRow();
	        case 8:
	            return rooms[8].getRow();
	        default:
	            return 0;
	        }
	    }

	    public int getBriefcaseColumn() {
	        return bCase.getColumn();
	    }

	    public int getBriefcaseRow() {
	        return bCase.getRow();
	    }

	    public int getBulletRow() {
	        return bullet.getRow();
	    }

	    public int getBulletColumn() {
	        return bullet.getColumn();
	    }

	    public int getRadarColumn() {
	        return radar.getColumn();
	    }

	    public int getRadarRow() {
	        return radar.getRow();
	    }

	    public int getInvincibleColumn() {
	        return invincible.getColumn();
	    }

	    public int getInvincibleRow() {
	        return invincible.getRow();
	    }

	    //TODO removed static from method, was causing errors.
	    public void saveGame(String saveName) {
	        SaveState save = new SaveState();
	        System.out.println(save.toString());
	        
            FileOutputStream fos;	       
            
	        try {
	            fos = new FileOutputStream(saveName);
	            ObjectOutputStream oos = new ObjectOutputStream(fos);
	            oos.writeObject(grid);
	            oos.flush();
	            oos.close();
	        } catch (IOException e) {
	            e.printStackTrace();

	        }
	    }

	    /**
	     * Loads the game's state as a file.
	     */
	    public Object loadGame(String loadName) {

	        try {
	            FileInputStream fis = new FileInputStream(loadName);
	            ObjectInputStream ois = new ObjectInputStream(fis);
	            grid = (String[][]) ois.readObject();
	            ois.close();
	        } catch (Exception e) {
	            System.out.println("Invalid Name");
	        }return grid;
	    }

	    public int getAmmo() {
	        // Returns player's current ammo.
	        return gun.getAmmo();
	    }

	    public int getLives() {
	        // Returns player's current life count.
	        return player.getLives();
	    }

	    public boolean gameOver() {
	       return gameOver;
	    }
	   
	    public void checkForGameOver() {
	    	if(player.getLives() < 0 || briefcaseFound) {
	    		gameOver = true;
	    	}
	    }
	    
		public void playerLook(String dir)
	    {	    
	    	boolean checkNinja = false;
	    	
	    	switch(dir)
	    	{
	    	case "up":
	    		for(int j = 0; j < ninjas.length; j++)
	    		{
	    			if(player.getColumn() == getNinjaColumn(j) && player.getRow() > getNinjaRow(j))
	    			{
	    				checkNinja = true;
	    			}
	    		}
	    		if(checkNinja)
	    		{
	    			System.out.println("Ninja Ahead!");
	    		}
	    		else
	    			System.out.println("Clear");
	    		
	    	case "down":
	    		for(int j = 0; j < ninjas.length; j++)
	    		{
	    			if(player.getColumn() == getNinjaColumn(j) && player.getRow() < getNinjaRow(j))
	    			{
	    				checkNinja = true;
	    			}
	    		}
	    		if(checkNinja)
	    		{
	    			System.out.println("Ninja Ahead!");
	    		}
	    		else
	    			System.out.println("Clear");
	    		
	    	case "left":
	    		for(int j = 0; j < ninjas.length; j++)
	    		{
	    			if(player.getRow() == getNinjaRow(j) && player.getColumn() > getNinjaColumn(j))
	    			{
	    				checkNinja = true;
	    			}
	    		}
	    		if(checkNinja)
	    		{
	    			System.out.println("Ninja Ahead!");
	    		}
	    		else
	    			System.out.println("Clear");
	    		
	    	case "right":
	    		for(int j = 0; j < ninjas.length; j++)
	    		{
	    			if(player.getRow() == getNinjaRow(j) && player.getColumn() < getNinjaColumn(j))
	    			{
	    				checkNinja = true;
	    			}
	    		}
	    		if(checkNinja)
	    		{
	    			System.out.println("Ninja Ahead!");
	    		}
	    		else
	    			System.out.println("Clear");
	    	}
	    }
		
		/*
		 * method to process radar powerup collection.
		 * checks if the player is on the powerup, if so the radar object is removed, and radarActive is set to true;
		 */
		public void radar() {
			if(player.getColumn() == radar.getColumn() && player.getRow() == radar.getRow()) {
				radarActive = true;
				radar = null;
			}
		}
		
		/*
		 * returns radarActive for printing use.
		 */
		public boolean radarActive() {
			return radarActive;
		}
		
		public void invincibility() {
		    if(player.getColumn() == invincible.getColumn() && player.getRow() == invincible.getRow()) {
		        invincibleActive = true;
		        invincible = null;
		    }
		}
		
		public boolean invincibilityActive() {
		    return invincibleActive;
		}
		
		public void bullet() {
		    if (player.getColumn() == bullet.getColumn() && player.getRow() == bullet.getRow()) {
		        if (gun.getAmmo() == 0) {
		                player.reloadPlayerGun();
		                bulletActive = true;
		                bullet = null;
		        }
		    }
		}
		
		public boolean bulletActive() {
			return bulletActive;
		}

		public boolean getLooking() {
			return looking;
		}
		public void setLooking(boolean x) {
			looking = x;
		}

		public void resetGrid() {
			gb = new GameBoard(this);
		}
		
		public void checkForSpy() {
			for(int n = 0; n < NUM_NINJAS; n++){
				if(	ninjas[n].isAlive() && 																			//ninja is alive AND
					(
					(ninjas[n].getColumn() + 1 == player.getColumn() && ninjas[n].getRow() == player.getRow()) ||	//player is to the right of the ninja OR
					(ninjas[n].getColumn() - 1 == player.getColumn() && ninjas[n].getRow() == player.getRow()) ||	//player is to the left of the ninja OR
					(ninjas[n].getRow() + 1 == player.getRow() && ninjas[n].getColumn() == player.getColumn()) ||	//player is below the ninja OR
					(ninjas[n].getRow() - 1 == player.getRow() && ninjas[n].getColumn() == player.getColumn())		//player is above the ninja
					)
				  ) 
				{
					stabSpy();
				}
			}
		}
		
		public void stabSpy() {
			player.loseLife();
			System.out.println("You were stabbed!");
			player.respawn();
		}
}
