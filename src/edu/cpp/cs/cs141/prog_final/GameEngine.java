/**
 * 
 */
package edu.cpp.cs.cs141.prog_final;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Random;

/**
 * @author Corey Perez
 *
 */
public class GameEngine {
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

    public void printDebugGrid() {
        gb.printDebugGrid();
    }

    // * THIS WAS HERE BEFORE I MOVED IT TO TH GAME BOARD, IM SCARED TO DELETE
    // IT BUT FEEL FREE TO IF THIS ENDS UP WORKING WELL.
    // Print Debug Grid Method
    /*
     * public void printDebugGriddebug() {
     * System.out.println("running printDebugGriddebug()"); //double for loop,
     * for running thru each of the grid[][]'s coordinates. for(int i = 0; i <
     * grid.length; i++) { for(int j = 0; j < grid[i].length; j++) { //set all
     * cells empty grid[i][j] = " "; /**set rooms Runs through every cell on the
     * board. -loops each cell, and check if it's coordinates matches any of the
     * rooms' coordinates. -if they match, then the object's corresponding mark
     * is placed. All the rest of these setters go through essentially the same
     * process.
     */
    /*
     * for(int n = 0; n < rooms.length; n++) { if(j == rooms[n].getColumn() && i
     * == rooms[n].getRow()) { grid[i][j] = rooms[n].getRoomMark(); } } //set
     * player if((j == player.getColumn() && i == player.getRow())) { grid[i][j]
     * = player.getPlayerMark(); } //set ninjas for(int n = 0; n <
     * ninjas.length; n++) { if(j == rooms[n].getColumn() && i ==
     * ninjas[n].getRow()) { grid[i][j] = ninjas[n].getNinjaMark(); } } //set
     * bCase //Overrides room mark, thus room and briefcase objects can hold
     * same coordinates if(j == bCase.getColumn() && i == bCase.getRow()) {
     * grid[i][j] = bCase.getBriefCaseMark(); } //set bullet //only places if
     * area is empty, thus if a ninja or player is on it, it will be overridden,
     * but still keep its corrdinates. //this applies for the other two powerup
     * objects. if(grid[i][j] == " " && (j == bullet.getColumn() && i ==
     * bullet.getRow())) { grid[i][j] = bullet.getBulletMark(); } //set radar
     * if(grid[i][j] == " " && (j == radar.getColumn() && i == radar.getRow()))
     * { grid[i][j] = radar.getRadarMark(); } //set invincible if(grid[i][j] ==
     * " " && (j == invincible.getColumn() && i == invincible.getRow())) {
     * grid[i][j] = invincible.getInvincibleMark(); }
     * 
     * System.out.print("[" + grid[i][j] + "]"); } System.out.println(); } }
     */

    public void userMoveInput(char choice) {
        switch (choice) {
        case 'W':
        case 'w':
        case 'S':
        case 's':
        case 'A':
        case 'a':
        case 'D':
        case 'd':
            movePlayer(choice);
            moveNinja();
            break;
        }
    }

    public void gunShoot(String direction) {
        gun.shoot(direction);
    }

    public void movePlayer(char userMove) {
        switch (userMove) {
        case 'W':
        case 'w':
            if (getPlayerRow() > 0 && !roomCollisionPlayer("up")) {
                movePlayerUp();
                break;
            } else {
                System.out.println("It's a wall.");
            }
            break;
        case 'S':
        case 's':
            if (getPlayerRow() < 8 && !roomCollisionPlayer("down")) {
                movePlayerDown();
                break;
            } else {
                System.out.println("It's a wall.");
            }
            break;
        case 'A':
        case 'a':
            if (getPlayerColumn() > 0 && !roomCollisionPlayer("left")) {
                movePlayerLeft();
                break;
            } else {
                System.out.println("It's a wall.");
            }
            break;
        case 'D':
        case 'd':
            if (getPlayerColumn() < 8 && !roomCollisionPlayer("right")) {
                movePlayerRight();
                break;
            } else {
                System.out.println("It's a wall.");
            }
            break;
        }
    }

    public void moveNinja() {
        for (int counter = 0; counter < NUM_NINJAS; counter++) {
            // Below is a debug print to check ninja coordinates while running
            // the game.
            // System.out.println("ninja " + counter + " processing...");
            // System.out.println("precoordinates: " +
            // ninjas[counter].getColumn() + " , " + ninjas[counter].getRow());
            int rng = new Random().nextInt(4);
            switch (rng) {
            case 0: // up
                if (getNinjaRow(counter) > 0 && getNinjaRow(counter) <= 8 && !roomCollisionNinja(counter, "up")) {
                    moveNinjaUp(counter);
                }
                break;
            case 1: // down
                if (getNinjaRow(counter) >= 0 && getNinjaRow(counter) < 8 && !roomCollisionNinja(counter, "down")) {
                    moveNinjaDown(counter);
                }
                break;
            case 2: // left
                if (getNinjaColumn(counter) > 0 && getNinjaColumn(counter) <= 8
                        && !roomCollisionNinja(counter, "left")) {
                    moveNinjaLeft(counter);
                }
                break;
            case 3: // right
                if (getNinjaColumn(counter) >= 0 && getNinjaColumn(counter) < 8
                        && !roomCollisionNinja(counter, "right")) {
                    moveNinjaRight(counter);
                }
                break;
            default:
                System.out.println("error in moveNinja() rng");
                break;
            }
            System.out.println("postcoordinates: " + ninjas[counter].getColumn() + " , " + ninjas[counter].getRow());
        }
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

    public void saveGame(String saveName) {
        try {
            FileOutputStream fos = new FileOutputStream(saveName);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(grid);
            oos.close();
        } catch (Exception e) {

        }
    }

    /**
     * Loads the game's state as a file.
     */
    public void loadGame(String loadName) {

        try {
            FileInputStream fis = new FileInputStream(loadName);
            ObjectInputStream ois = new ObjectInputStream(fis);
            grid = (String[][]) ois.readObject();
            ois.close();
        } catch (Exception e) {
            System.out.println("Invalid Name");
        }
    }

    public int getAmmo() {
        // TODO Auto-generated method stub
        // Returns player's current ammo.
        return gun.getAmmo();
    }

    public int getLives() {
        // TODO Auto-generated method stub
        // Returns player's current life count.
        return player.getLives();
    }

    public boolean gameOver() {
        // TODO Auto-generated method stub
        // returns true when a game-ending scenario has been reached.
        return false;
    }

}
