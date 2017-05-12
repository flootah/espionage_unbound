package edu.cpp.cs.cs141.prog_final;

public class GameBoard {
    
    public static enum BoardMark {P, A, B}
    
    public static final int BOARD_SIZE = 9;
    /**
     * Method that creates the board.
     */
    public void createBoard() {
        String gameGrid[][] = new String[BOARD_SIZE][BOARD_SIZE];
        for (int i = 0; i < gameGrid.length; i++) {
            for (int j = 0;j < gameGrid.length; j++) {
                System.out.println(gameGrid[i][j]);
            }
        }
    }

}
