package edu.cpp.cs.cs141.prog_final;

public class GameBoard {
    
    /**
     * int that sets the dimensions used for the 2D array / the board.
     */
    public static final int BOARD_SIZE = 9;
    
    /**
     * Method that creates and initializes the board.
     */
    public void createBoard() {
        String gameGrid[][] = new String[BOARD_SIZE][BOARD_SIZE];
        for (int i = 0; i < gameGrid.length; i++) {
            for (int j = 0;j < gameGrid.length; j++) {
                if (gameGrid[i][j] == null) {
                    gameGrid[i][j] = "[ ]";
                } else if (gameGrid[i][j] == Player.getPosition()) {
                    gameGrid[i][j] = "[P]";
                } else if (gameGrid[i][j] == Ninja.getPosition()) {
                    gameGrid[i][j] = "[A]";
                } else if (gameGrid[i][j] == Briefcase.getPosition()) {
                    gameGrid[i][j] = "[B]";
                }
                System.out.println(gameGrid[i][j]);
            }
        }
    }

}
