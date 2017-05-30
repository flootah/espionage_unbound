package edu.cpp.cs.cs141.prog_final;

import java.io.Serializable;

public class GameBoard implements Serializable {
	
	private static int GRID_SIZE;
	private static int NUM_NINJAS;
	private static int NUM_ROOMS;
	private GameEngine ge;
	private String[][] grid;
	
	
	public GameBoard(GameEngine engine) {
		ge = engine;
		GRID_SIZE = GameEngine.GRID_SIZE;
		NUM_NINJAS = GameEngine.NUM_NINJAS;
		NUM_ROOMS = GameEngine.NUM_ROOMS;
		grid = new String[GRID_SIZE][GRID_SIZE];
	}
	
	//Print Debug Grid Method 
		public void printDebugGrid()
		{
			for(int i = 0; i < grid.length; i++) {
				for(int j = 0; j < grid[i].length; j++) {
					//set all cells empty
						grid[i][j] = " ";
					//Set player
					if(grid[i][j] == " " && (j == ge.getPlayerColumn() && i == ge.getPlayerRow())) {
						grid[i][j] = ge.getPlayerMark();
					}
					//set Ninjas
					if(grid[i][j] == " ") {
						for(int n = 0; n < NUM_NINJAS; n++) {
							if(ge.isNinjaAlive(n) && j == ge.getNinjaColumn(n) && i == ge.getNinjaRow(n)) {
								grid[i][j] = ge.getNinjaMark();
							}
						}
					}
					/**set rooms
					 * Runs through every cell on the board.
					 * 	-if the cell is empty, then it will check if it's coordinates matches any of the rooms' coordinates.
					 *  -if they match, then the object's corresponding mark is placed.
					 * All the rest of these setters go through essentially the same process.
					 */
					if(grid[i][j] == " ") {
						for(int n = 0; n < NUM_ROOMS; n++) {
							if(j == ge.getRoomColumn(n) && i == ge.getRoomRow(n)) {
								grid[i][j] = ge.getRoomMark();
							}
						}
					}
					//set bCase
					if(j == ge.getBriefcaseColumn() && i == ge.getBriefcaseRow()) {
						grid[i][j] = ge.getBriefCaseMark();
					}
					//set bullet
					if(ge.isBulletAvailable() && grid[i][j] == " " && (j == ge.getBulletColumn() && i == ge.getBulletRow())) {
						grid[i][j] = ge.getBulletMark();
					}
					//set radar
					if(ge.isRadarAvailable() && grid[i][j] == " " && (j == ge.getRadarColumn() && i == ge.getRadarRow())) {
						grid[i][j] = ge.getRadarMark();
					}
					//set invincible
					if(ge.isInvincibilityAvailable() && grid[i][j] == " " && (j == ge.getInvincibleColumn() && i == ge.getInvincibleRow())) {
						grid[i][j] = ge.getInvincibleMark();
					}
							
					System.out.print("[" + grid[i][j] + "]");
				}
				System.out.println();
			}
		}
		
		//Print Grid Method 
		public void printGrid()
		{
			for(int i = 0; i < grid.length; i++) {
				for(int j = 0; j < grid[i].length; j++) {
					//set all cells empty
					grid[i][j] = "*";
					
					if(ge.getPlayerRow() > 1 && ge.getPlayerRow() < 7)
					{
						grid[ge.getPlayerRow() - 1][ge.getPlayerColumn()] = " ";
						grid[ge.getPlayerRow() - 2][ge.getPlayerColumn()] = " ";
						grid[ge.getPlayerRow() + 1][ge.getPlayerColumn()] = " ";
						grid[ge.getPlayerRow() + 2][ge.getPlayerColumn()] = " ";
						
					}
					
					if(ge.getPlayerRow() == 7)
					{
						grid[ge.getPlayerRow() - 1][ge.getPlayerColumn()] = " ";
						grid[ge.getPlayerRow() - 2][ge.getPlayerColumn()] = " ";
						grid[ge.getPlayerRow() + 1][ge.getPlayerColumn()] = " ";
					}
					
					if(ge.getPlayerRow() == 8)
					{
						grid[ge.getPlayerRow() - 1][ge.getPlayerColumn()] = " ";
						grid[ge.getPlayerRow() - 2][ge.getPlayerColumn()] = " ";
					}
					
					if(ge.getPlayerRow() == 1)
					{
						grid[ge.getPlayerRow() + 1][ge.getPlayerColumn()] = " ";
						grid[ge.getPlayerRow() + 2][ge.getPlayerColumn()] = " ";
						grid[ge.getPlayerRow() - 1][ge.getPlayerColumn()] = " ";
					}
					
					if(ge.getPlayerRow() == 0)
					{
						grid[ge.getPlayerRow() + 1][ge.getPlayerColumn()] = " ";
						grid[ge.getPlayerRow() + 2][ge.getPlayerColumn()] = " ";
					}
					
					if(ge.getPlayerColumn() > 1 && ge.getPlayerColumn() < 7)
					{
						grid[ge.getPlayerRow()][ge.getPlayerColumn() - 1] = " ";
						grid[ge.getPlayerRow()][ge.getPlayerColumn() - 2] = " ";
						grid[ge.getPlayerRow()][ge.getPlayerColumn() + 1] = " ";
						grid[ge.getPlayerRow()][ge.getPlayerColumn() + 2] = " ";
						
					}
					
					if(ge.getPlayerColumn() == 7)
					{
						grid[ge.getPlayerRow()][ge.getPlayerColumn() - 1] = " ";
						grid[ge.getPlayerRow()][ge.getPlayerColumn() - 2] = " ";
						grid[ge.getPlayerRow()][ge.getPlayerColumn() + 1] = " ";
					}
					
					if(ge.getPlayerColumn() == 8)
					{
						grid[ge.getPlayerRow()][ge.getPlayerColumn() - 1] = " ";
						grid[ge.getPlayerRow()][ge.getPlayerColumn() - 2] = " ";
					}
					
					if(ge.getPlayerColumn() == 1)
					{
						grid[ge.getPlayerRow()][ge.getPlayerColumn() + 1] = " ";
						grid[ge.getPlayerRow()][ge.getPlayerColumn() + 2] = " ";
						grid[ge.getPlayerRow()][ge.getPlayerColumn() - 1] = " ";
					}
					
					if(ge.getPlayerColumn() == 0)
					{
						grid[ge.getPlayerRow()][ge.getPlayerColumn() + 1] = " ";
						grid[ge.getPlayerRow()][ge.getPlayerColumn() + 2] = " ";
					}
					//Set player
					if((j == ge.getPlayerColumn() && i == ge.getPlayerRow())) {
						grid[i][j] = ge.getPlayerMark();
					}
					//set Ninjas if player is looking
					for(int n = 0; n < NUM_NINJAS; n++) {
						if(ge.isNinjaAlive(n) && grid[i][j] == " " && j == ge.getNinjaColumn(n) && i == ge.getNinjaRow(n)) {
							grid[i][j] = ge.getNinjaMark();
						}
					}
					/**set rooms
					 * Runs through every cell on the board.
					 * 	-if the cell is empty, then it will check if it's coordinates matches any of the rooms' coordinates.
					 *  -if they match, then the object's corresponding mark is placed.
					 * All the rest of these setters go through essentially the same process.
					 */
					for(int n = 0; n < NUM_ROOMS; n++) {
						if(j == ge.getRoomColumn(n) && i == ge.getRoomRow(n)) {
							grid[i][j] = ge.getRoomMark();
						}
					}
					//set bCase if radar is active
					if(j == ge.getBriefcaseColumn() && i == ge.getBriefcaseRow() && ge.radarActive()) {
						grid[i][j] = ge.getBriefCaseMark();
					}
					//set bullet if player is looking and ninja isnt there
					if(ge.isBulletAvailable() && grid[i][j] == " " && (j == ge.getBulletColumn() && i == ge.getBulletRow())) {
						grid[i][j] = ge.getBulletMark();
					}
					//set radar if player is looking and ninja isnt there
					if(ge.isRadarAvailable() && grid[i][j] == " " && (j == ge.getRadarColumn() && i == ge.getRadarRow())) {
						grid[i][j] = ge.getRadarMark();
					}
					//set invincible if player is looking and ninja isnt there
					if(ge.isInvincibilityAvailable() && grid[i][j] == " " && (j == ge.getInvincibleColumn() && i == ge.getInvincibleRow())) {
						grid[i][j] = ge.getInvincibleMark();
					}
							
					System.out.print("[" + grid[i][j] + "]");
				}
				System.out.println();
			}
		}
}
