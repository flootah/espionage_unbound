package edu.cpp.cs.cs141.prog_final;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Random;

/**
 * This class contains all the logic for the game, including collision with room, 
 * Ninjas killing the player, shooting the gun, looking, win conditions, etc.
 * 
 * @author Corey Perez
 * @author Eduardo Saenz
 * @author Lance Dall
 * @author Grant Posner
 * @author Bumjoong Kim
 * @author Jacob Chong
 *
 */
public class GameEngine implements Serializable
{
	/**
	 * The initial grid size
	 */
	public static final int GRID_SIZE = 9;

	/**
	 * Initial number of {@link Ninja()}s
	 */
	public static final int NUM_NINJAS = 6;

	/**
	 * Initial number of {@link Rooms()}
	 */
	public static final int NUM_ROOMS = 9;

	/**
	 * The distance that the player can view
	 */
	public static final int VIEW_DIST = 2;

	/**
	 * Creates the 2-D array to represent the {@link #grid}
	 */
	private String[][] grid = new String[GRID_SIZE][GRID_SIZE];

	/**
	 * Creates the {@link Player()}
	 */
	private Player player;

	/**
	 * Creates an array of {@link Ninja()}s for the amount {@link #NUM_NINJAS}
	 */
	private Ninja[] ninjas = new Ninja[NUM_NINJAS];

	/**
	 * Creates an array of {@link Rooms()} for the amount {@link #NUM_ROOMS}
	 */
	private Rooms[] rooms = new Rooms[NUM_ROOMS];

	/**
	 * Creates a {@link Briefcase()}
	 */
	private Briefcase bCase;

	/**
	 * Creates a {@link Bullet()} powerup for the {@link Player()}'s {@link Gun()}
	 */
	private Bullet bullet;
	
	/**
	 * Creates a {@link Radar()} powerup
	 */
	private Radar radar;

	/**
	 * Create an {@link Invincibility()} powerup
	 */
	private Invincibility invincible;

	/**
	 * Creates a {@link GameBoard()} 
	 */
	private GameBoard gb;

	/**
	 * Creates a {@link Gun()} for the {@link Player()}
	 */
	private Gun gun;
	/*    
	private boolean radarActive;
	    
	private boolean invincibleActive;
	    
	private boolean bulletActive;
		*/
	/**
	 * Boolean to represent whether the player is looking in
	 * a direction or not. Activates with {@link #playerLook(String)}
	 */
	private boolean looking;

	/**
	 * Boolean to represent whether the player is out of lives or not
	 */
	private boolean gameOver;

	/**
	 * Boolean to represent whether the player has found the {@link Briefcase()}. 
	 * When they do, they win
	 */
	private boolean briefcaseFound;

	/**
	 * Boolean to represent whether the player has won. If true, the game ends.
	 */
	private boolean win;

	/**
	 * Constructor for the {@link #GameEngine()}. Initializes the {@link Player()}
	 * with their {@link #gun}, all the {@link PowerUps()} and {@link GameBoard()}.
	 */
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
	     * Initalizes the building, calculating positions for: {@link Player()}, {@link Rooms()},
	     * {@link Ninja()}, {@link Briefcase(), and {@link PowerUps()}.
	     */
	    public void createBuilding() {
	        calculateRoomPositions();
	        calculateNinjaPositions();
	        calculateBriefCasePosition();
	        calculateBulletPosition();
	        calculateInvinciblePosition();
	        calculateRadarPosition();
	    }

	    /**
	     * Prints a {@link #grid} without debug mode
	     */
	    public void printGrid() {
			gb.printGrid();
		}
	    
	    /**
	     * Prints a {@link #grid} with debug mode
	     */
	    public void printDebugGrid() {
	        gb.printDebugGrid();
	    }

	    /**
	     * Takes in user's movement choice and runs {@link #movePlayer(String)}
	     * to actually move the {@link #player}
	     * @param choice
	     */
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

	    /**
	     * Shoots a gun in a direction, checking each cell in the row or
	     * column the {@link #player()} specifies, killing a {@link Ninja()} if there
	     * is one. 
	     * 
	     * This also subtracts the amount of ammo the player's {@link Gun()} has by 1
	     * @param dir
	     */
	    public void shootGun(String dir) {
	    	int pRow = player.getRow();
	    	int pCol = player.getColumn();	 
	    	if(gun.getAmmo() == 1)
	    	{
	        switch(dir) {
	        case "up":
	        	if(pRow == 0) {
	        		System.out.println("You shot into a wall...");
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
				checkForSpy();
	        	moveNinja();
	        	break;
	        case "left":
	        	if(pCol == 0) {
	        		System.out.println("You shot into a wall...");
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
				checkForSpy();
	        	moveNinja();
	        	break;
	        case "right":
	        	if(pCol == 8) {
	        		System.out.println("You shot into a wall...");
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
				checkForSpy();
	        	moveNinja();
	        	break;
	        case "down":
	        	if(pRow == 8) {
	        		System.out.println("You shot into a wall...");
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
	        				//debug message to check if missed.
	        				
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
				checkForSpy();
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

	    /**
	     * Moves the player in the direction specified in {@link #userMoveInput(String)}
	     * @param userMove
	     */
	    public void movePlayer(String userMove) {
	        switch (userMove.toUpperCase()) {
	        case "W":
	            if (getPlayerRow() > 0 && !roomCollisionPlayer("up")) {
	                movePlayerUp();
	                setLooking(false);
	            } else {
	                System.out.println("It's a wall.");
	            }
				if(player.isInvincible())
	            {
	            	player.increaseTurnCounter();
	            }
	            break;
	        case "S":
	            if (getPlayerRow() < 8 && !roomCollisionPlayer("down")) {
	                movePlayerDown();
	                setLooking(false);
	            } else {
	            	if(playerAboveRoom()) {
	            		System.out.println("Checking room...");
	            		checkRoom();
	            	} else {
		                System.out.println("It's a wall.");
	            	}
	            }
				if(player.isInvincible())
	            {
	            	player.increaseTurnCounter();
	            }
	            break;
	        case "A":
	            if (getPlayerColumn() > 0 && !roomCollisionPlayer("left")) {
	                movePlayerLeft();
	                setLooking(false);
	            } else {
	            	if(playerAboveRoom()) {
	            		
	            	} else {
	                System.out.println("It's a wall.");
	            	}
	            }
				if(player.isInvincible())
	            {
	            	player.increaseTurnCounter();
	            }
	            break;
	        case "D":
	            if (getPlayerColumn() < 8 && !roomCollisionPlayer("right")) {
	                movePlayerRight();
	                setLooking(false);
	            } else {
	                System.out.println("It's a wall.");
	            }
				if(player.isInvincible())
	            {
	            	player.increaseTurnCounter();
	            }
	            break;
	        }
	    }

	    /**
	     * Checks if the player is above a room or not. If they are
	     * above a room, 
	     * @return isAbove
	     */
	    private boolean playerAboveRoom() {
	    	boolean isAbove = false;
	    	//for all rooms
	    	for(int r = 0; r < NUM_ROOMS; r++) {
				//if player is above a room
				if(player.getColumn() == rooms[r].getColumn() && player.getRow() + 1 == rooms[r].getRow()) {
					isAbove = true;
					break;
				} else {
					isAbove = false;
				}
			}
	    	
	    	return isAbove;
		}

	    /**
	     * Moves each {@link Ninja()} in a random direction every turn the player makes
	     */
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
	    
		/**
		 * Determines if a {@link Ninja()} is alive from the array of ninjas created
		 * @param n
		 */
	    public boolean isNinjaAlive(int n) {
	    	return ninjas[n].isAlive();
	    }

	    /**
	     * Checks whether or not the {@link Player()} can move in a direction. Collision is true
	     * when the player is about to collide into a {@link #Rooms()}.
	     * @param dir
	     */
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

	    /**
	     * Returns true if the direction the {@link Ninja()} is moving is a {@link Rooms()}
	     * @param counter: the counter of the ninja being checked for collision
	     * @param dir
	     */
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

	    /**
	     * Calculates the position of the {@link Rooms()} on the grid
	     */
	    public void calculateRoomPositions() {
	        for (int i = 0; i < rooms.length; i++) {
	            rooms[i].setRow(i);
	            rooms[i].setColumn(i);
	            grid[rooms[i].getRow()][rooms[i].getColumn()] = getRoomMark();
	        }
	    }

	    /**
	     * Calculates the initial positions of the {@link Ninja()}s on the {@link #grid}
	     */
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

	    /**
	     * Calculates the position of the {@link Briefcase()}, which is randomly
	     * set to one of the {@link Rooms()}
	     */
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

	    /**
	     * Calculates the initial position of the {@link Bullet()} powerup
	     */
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

	    /**
	     * Calculates the initial position of the {@link Radar()} powerup
	     */
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
	    
	    /**
	     * Calculates the initial position of the {@link Invincibility()} powerup
	     */
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

	    /**
	     * Getter for the {@link Player()}'s mark on the {@link #grid}
	     */
	    public String getPlayerMark() {
	        return player.getPlayerMark();
	    }

	    /**
	     * Getter for the {@link Ninja()}'s mark on the {@link #grid}
	     */
	    public String getNinjaMark() {
	        return ninjas[0].getNinjaMark();
	    }

	    /**
	     * Getter for the {@link Briefcase()}'s mark on the {@link #grid}
	     */
	    public String getBriefCaseMark() {
	        return bCase.getBriefCaseMark();
	    }

	    /**
	     * Getter for the {@link Rooms()}'s mark on the {@link #grid}
	     */
	    public String getRoomMark() {
	        return rooms[0].getRoomMark();
	    }

	    /**
	     * Getter for the {@link Bullet()}'s mark on the {@link #grid}
	     */
	    public String getBulletMark() {
	        return bullet.getBulletMark();
	    }

	    /**
	     * Getter for the {@link Invincibility()}'s mark on the {@link #grid}
	     */
	    public String getInvincibleMark() {
	        return invincible.getInvincibleMark();
	    }

	    /**
	     * Getter for the {@link Radar()}'s mark on the {@link #grid}
	     */
	    public String getRadarMark() {
	        return radar.getRadarMark();
	    }

	    /**
	     * Getter for the {@link Player()}'s position - specifically the row - on the {@link #grid}
	     */
	    public int getPlayerRow() {
	        return player.getRow();
	    }

	    /**
	     * Getter for the {@link Player()}'s position - specifically the column - on the {@link #grid}
	     */
	    public int getPlayerColumn() {
	        return player.getColumn();
	    }

	    /**
	     * Moves the {@link Player()} up
	     */
	    public void movePlayerUp() {
	        player.moveUp();
	    }

	    /**
	     * Moves the {@link Player()} down
	     */
	    public void movePlayerDown() {
	        player.moveDown();
	    }

	    /**
	     * Moves the {@link Player()} right
	     */
	    public void movePlayerRight() {
	        player.moveRight();
	    }

	    /**
	     * Moves the {@link Player()} left
	     */
	    public void movePlayerLeft() {
	        player.moveLeft();
	    }

	    /**
	     * Getter for the {@link Ninja()}'s position - Specifically the row
	     * @param num
	     */
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

	    /**
	     * Getter for the {@link Ninja()}'s position - Specifically the column
	     * @param num
	     */
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

	    /**
	     * Moves a specific {@link Ninja()} down
	     * @param num
	     */
	    public void moveNinjaDown(int num) {
	        ninjas[num].moveDown();
	    }

	    /**
	     * Moves a specific {@link Ninja()} up
	     * @param num
	     */
	    public void moveNinjaUp(int num) {
	        ninjas[num].moveUp();
	    }

	    /**
	     * Moves a specific {@link Ninja()} right
	     * @param num
	     */
	    public void moveNinjaRight(int num) {
	        ninjas[num].moveRight();
	    }

	    /**
	     * Moves a specific {@link Ninja()} left
	     * @param num
	     */
	    public void moveNinjaLeft(int num) {
	        ninjas[num].moveLeft();
	    }

	    /**
	     * Getter for the {@link Rooms()}' position - specifically the column
	     * @param num
	     */
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

	    /**
	     * Getter for the {@link Rooms()}' position - specifically the row
	     * @param num
	     */
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

	    /**
	     * Getter for the {@link Briefcase()}'s position - specifically the column
	     */
	    public int getBriefcaseColumn() {
	        return bCase.getColumn();
	    }
	    
	    /**
	     * Getter for the {@link Briefcase()}'s position - specifically the row
	     */
	    public int getBriefcaseRow() {
	        return bCase.getRow();
	    }

	    /**
	     * Getter for the {@link Bullet()}'s position - specifically the row
	     */
	    public int getBulletRow() {
	        return bullet.getRow();
	    }

	    /**
	     * Getter for the {@link Bullet()}'s position - specifically the column
	     */
	    public int getBulletColumn() {
	        return bullet.getColumn();
	    }

	    /**
	     * Getter for the {@link Radar()}'s position - specifically the column
	     */
	    public int getRadarColumn() {
	        return radar.getColumn();
	    }

	    /**
	     * Getter for the {@link Radar()}'s position - specifically the row
	     */
	    public int getRadarRow() {
	        return radar.getRow();
	    }

	    /**
	     * Getter for the {@link Invincibility()}'s position - specifically the column
	     */
	    public int getInvincibleColumn() {
	        return invincible.getColumn();
	    }

	    /**
	     * Getter for the {@link Invincibility()}'s position - specifically the row
	     */
	    public int getInvincibleRow() {
	        return invincible.getRow();
	    }

	    /**
	     * Saves the game with the FileName specified by the player
	     * @param saveName
	     */
	    public void saveGame(String saveName) {
            FileOutputStream fos;	       
	        try {
	            fos = new FileOutputStream(saveName);
	            ObjectOutputStream oos = new ObjectOutputStream(fos);
	            oos.writeObject(this);
	            oos.flush();
	            oos.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

	    /**
	     * Getter for the player's current ammo
	     */
	    public int getAmmo() {
	        return gun.getAmmo();
	    }

	    /**
	     * Getter for the player's current amount of lives
	     */
	    public int getLives() {
	        // Returns player's current life count.
	        return player.getLives();
	    }

	    /**
	     * Sets the {@link #gameOver} status to true or false
	     */
	    public boolean gameOver() {
	       return gameOver;
	    }
	   
	    /**
	     * Checks the {@link #gameOver} status to see if the game is over.
	     * The game is over if the player's lives reach 0, or the {@link Briefcase()} is found
	     */
	    public void checkForGameOver() {
	    	if(player.getLives() < 0) {
	    		gameOver = true;
	    		win = false;
	    	}
	    	if(briefcaseFound) {
	    		gameOver = true;
	    		win = true;
	    	}
	    }
	    
	    /**
	     * Returns whether the player wins or not
	     */
	    public boolean winCondition() {
	    	return win;
	    }
	    
	    /**
	     * Lets the player look in a specific direction and prints whether
	     * a {@link Ninja()} is in a direction
	     * @param dir
	     */
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
				break;
	    		
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
	    		break;
				
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
	    		break;
				
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
				break;
	    	}
	    }

		/**
		 * returns whether the player is looking or not
		 * @return
		 */
		public boolean getLooking() {
			return looking;
		}
		
		/**
		 * Sets the player to looking or not looking
		 * @param x
		 */
		public void setLooking(boolean x) {
			looking = x;
		}

		/**
		 * Resets the grid. This is done every time time player dies
		 */
		public void resetGrid() {
			gb = new GameBoard(this);
		}
		
		/**
		 * Done for every {@link Ninja()} for every turn. Checks whether or not the 
		 * player is on a space next to the ninja. Kills the player if that's the case
		 */
		public void checkForSpy() {
			for(int n = 0; n < NUM_NINJAS; n++){
				if(	ninjas[n].isAlive() && 																			//ninja is alive AND
					(
					(ninjas[n].getColumn() + 1 == player.getColumn() && ninjas[n].getRow() == player.getRow()) ||	//player is to the right of the ninja OR
					(ninjas[n].getColumn() - 1 == player.getColumn() && ninjas[n].getRow() == player.getRow()) ||	//player is to the left of the ninja OR
					(ninjas[n].getRow() + 1 == player.getRow() && ninjas[n].getColumn() == player.getColumn()) ||	//player is below the ninja OR
					(ninjas[n].getRow() - 1 == player.getRow() && ninjas[n].getColumn() == player.getColumn()) ||	//player is above the ninja OR
					(ninjas[n].getRow() == player.getRow() && ninjas[n].getColumn() == player.getColumn())			//player is ON the ninja.
					) && !gameOver()																				//AND the game is not over.
				  ) 
				{
					stabSpy();
				}
			}
		}
		
		/**
		 * Checks if the player is invincible. If they aren't they lose a life and respawn.
		 */
		public void stabSpy() {
			if(!player.isInvincible())
			{
				player.loseLife();
				System.out.println("You were stabbed!");
				player.respawn();
			}
		}

		/**
		 * Checks the room that the player is above. If the room contains the
		 * {@link Briefcase()}, the player wins.
		 */
		public void checkRoom() {
			//for all rooms
			for(int r = 0; r < NUM_ROOMS; r++) {
				//if player is above a room
				if(player.getColumn() == rooms[r].getColumn() && player.getRow() + 1 == rooms[r].getRow()) {
					//and the briefcase is in that room
					if(rooms[r].getColumn() == bCase.getColumn() && rooms[r].getRow() == bCase.getRow()) {
						System.out.println("The Briefcase is here!");
						gameOver = true;
						win = true;
					} else {
						System.out.println("Nothing but junk.");
					}
				}
			}
		}
		
		/**
		 * Checks if the player has the {@link Bullet()} powerup
		 */
		public boolean isBulletAvailable()
	    {
	    	return bullet.isUsed();
	    }

		/**
		 * Checks if the player has the {@link Radar()} powerup
		 */
	    public boolean isRadarAvailable()
	    {
	    	return radar.isUsed();
	    }

		/**
		 * Checks if the player has the {@link Invincibility()} powerup
		 */
	    public boolean isInvincibilityAvailable()
	    {
	    	return invincible.isUsed();
	    }

		/**
		 * 	Picks up {@link PowerUps()} if the player steps on top of them
		 */
	    public void pickUpPowerUp()
	    {
	    	if(isBulletAvailable() && getPlayerRow() == getBulletRow() && getPlayerColumn() == getBulletColumn())
	    	{
	    		bullet.used();
	    		player.reloadPlayerGun();
	    	}
	    	
	    	if(isRadarAvailable() && getPlayerRow() == getRadarRow() && getPlayerColumn() == getRadarColumn())
	    	{
	    		radar.used();
	    	}
	    	
	    	if(isInvincibilityAvailable() && getPlayerRow() == getInvincibleRow() && getPlayerColumn() == getInvincibleColumn())
	    	{
	    		invincible.used();
	    		player.gainInvincibility();	    			
	    	}
	    }
	    
	    /**
	     * Checks if the player's turn counter is above 5 to see if 
	     * they've lost their invincibility.
	     */
	    public void checkInvincible()
	    {
	    	if(player.getTurnCounter() >= 5)
	    	{
	    		player.loseInvincibility();
	    	}
	    }
}
