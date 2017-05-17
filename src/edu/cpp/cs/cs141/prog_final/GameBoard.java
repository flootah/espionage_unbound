package edu.cpp.cs.cs141.prog_final;
import java.util.Random;
public class GameBoard {
    
    /**
     * int that sets the dimensions used for the 2D array / the board.
     */
    public static final int BOARD_SIZE = 9;
    public String gameGrid[][] = new String[BOARD_SIZE][BOARD_SIZE];
    
    /**
     * Method that creates and initializes the board.
     */
    public GameBoard() {
        Gun gun = new Gun();
        Player player = new Player(gun, 3);
        gameGrid[BOARD_SIZE-1][0] = player.toString();
        Ninja ninja1 = new Ninja(6);
        Ninja ninja2 = new Ninja(6);
        Ninja ninja3 = new Ninja(6);
        Ninja ninja4 = new Ninja(6);
        Ninja ninja5 = new Ninja(6);
        Ninja ninja6 = new Ninja(6);
        Bullet bullet = new Bullet(gun);
        Invincibility invincibility = new Invincibility(5);
        Radar radar = new Radar();
        Object[] objects = {ninja1, ninja2, ninja3, ninja4, ninja5, ninja6, bullet, invincibility, radar};
        Random rng = new Random();
        int briefcasePosition = rng.nextInt(9) + 1;
        Briefcase briefcase = new Briefcase(briefcasePosition);
        int count = 1;
        for (int i = 1; i <= 7; i += 3) {
            for (int j = 1; j <= 7; j += 3) {
                if (briefcasePosition == count) {
                    gameGrid[i][j] = briefcase.toString();
                } else {
                    gameGrid[i][j] = "[" + Integer.toString(count) + "]";
                }
                count++;
            }
        }
        count = 0;
        while (count < objects.length) {
            int cellRow = rng.nextInt(9);
            int cellColumn = rng.nextInt(9);
            if (gameGrid[cellRow][cellColumn] == null) {
                gameGrid[cellRow][cellColumn] = objects[count].toString();
                count++;
            }
        }
        for (int i = 0; i < gameGrid.length; i++) {
            for (int j = 0; j < gameGrid.length; j++) {
                if (gameGrid[i][j] == null) {
                    gameGrid[i][j] = "[ ]";
                }
            }
        }
    }
    
    public void printBoard() {
        for (int i = 0; i < gameGrid.length; i++) {
            for (int j = 0; j < gameGrid.length; j++) {
                System.out.print(gameGrid[i][j] + " ");
            }
            System.out.println("");
        }
    }
}
