package Battleships;

import java.util.ArrayList;
import java.util.Scanner;

public class Grid {

	private int size;
	//private ArrayList<Vessel> vessels = new ArrayList<>();
	private String grid[][];
	private String gridHits[][];
	private Player player;
	private boolean turn = true;
	
	public Grid(int size, Player player) {
		this.size = size;
		this.grid = new String[size][size];
		this.gridHits = new String[size][size];
		this.player = player;
		
		//initialise empty grid
		for(int i = 0; i < size; i++) {
			for(int k = 0; k < size; k++) {
				grid[i][k] = " 0";
				gridHits[i][k] = " 0";
			}
		}
	}
	
	public Player getPlayer() { return player; };
	public int getSize() { return size; };
	
	public void addCoordinate(Vessel vessel) throws CoordinateException {
		
		//checks if choice is out of bounds
		boolean exc1 = vessel.getX() > size - 1 || vessel.getY() > size - 1;
		//checks to see if space is occupied
		boolean exc2 = grid[vessel.getX()][vessel.getY()] == " X"; 
		
		
		if(exc1 || exc2) { //if input is invalid a custom exception is thrown
		    throw new CoordinateException();
		} else { // sets additional coords on board according to vessel's direction
		    if(vessel.getDirection() == Direction.NORTH) {
                        if(vessel.getX() + 1 > size - 1)
                            throw new CoordinateException();
                        else if(grid[vessel.getX() + 1][vessel.getY()] == " X") 
                            throw new CoordinateException();
                        else {
                            grid[vessel.getX()][vessel.getY()] = " X";
			    grid[vessel.getX() + 1][vessel.getY()] = " X";
                        }
                    } else if(vessel.getDirection() == Direction.EAST) {
                        if(vessel.getY() - 1 < 0)
                            throw new CoordinateException();
                        else if(grid[vessel.getX()][vessel.getY() - 1] == " X") 
                            throw new CoordinateException();
                        else {
                            grid[vessel.getX()][vessel.getY()] = " X";
			    grid[vessel.getX()][vessel.getY() - 1] = " X";
                        }
                    } else if(vessel.getDirection() == Direction.SOUTH) {
                        if(vessel.getX() - 1 < 0)
                            throw new CoordinateException();
                        else if(grid[vessel.getX() - 1][vessel.getY()] == " X")
                            throw new CoordinateException();
                        else {
                            grid[vessel.getX()][vessel.getY()] = " X";
			    grid[vessel.getX() - 1][vessel.getY()] = " X";
                        }
                    } else {
                        if(vessel.getY() + 1 > size - 1)
                            throw new CoordinateException();
                        else if(grid[vessel.getX()][vessel.getY() + 1] == " X") 
                            throw new CoordinateException();
                        else {
                            grid[vessel.getX()][vessel.getY()] = " X";
			    grid[vessel.getX()][vessel.getY() + 1] = " X";
                        }
                    }
		}
		
		//vessels.add(vessel);
		drawGrid();
	}
	
	public void drawGrid() {
		
		/*
		 * Prints multi-dimensional grid array to the console
		 */
		
		for(int i = 0; i < size; i++) {
			for(int k = 0; k < size; k++) {
				System.out.print(grid[i][k]);
			}
			System.out.println();
		}
		
	}
	
	public void drawHitGrid() {
		
		/*
		 * Prints multi-dimensional grid where play has hit
		 */
		System.out.println("H = Hit:  M = Miss");
		for(int i = 0; i < size; i++) {
			for(int k = 0; k < size; k++) {
				System.out.print(gridHits[i][k]);
			}
			System.out.println();
		}
	}
	
	
	public boolean placeVessels(Player player) {
		
		/*
		 * Receives players x and y coordinates for vessel
		 */
		
		Scanner sc = new Scanner(System.in);
		int count = 0;
		
		do {
			System.out.println();
			System.out.println(player.getName());
			System.out.println();
			System.out.println("Type in x coordinate between "
				    + (getSize() -  getSize()) +
				    " and " +  (getSize() - 1));
				
			int p2X = sc.nextInt();
			
			System.out.println("Type in y coordinate between "
				    + (getSize() -  getSize()) +
				    " and " +  (getSize() - 1));
			int p2Y = sc.nextInt();
			
			System.out.println("Select which direction your vessel will face");
	        System.out.println();
	        System.out.println("***********************");
	        System.out.println("Press 1 for North");
	        System.out.println("Press 2 for East");
	        System.out.println("Press 3 for South");
	        System.out.println("Press 4 for West");
	        System.out.println("***********************");
	                                
	        int direction = sc.nextInt();
	        System.out.println(direction);
	                                
	        while(direction != 1 &&
	            direction != 2 && 
	            direction != 3 && 
	            direction != 4) {
	            System.out.println("***********************");
	            System.out.println("Press 1 for North");
	            System.out.println("Press 2 for East");
	            System.out.println("Press 3 for South");
	            System.out.println("Press 4 for West");
	            System.out.println("***********************");
	                                
	            int input = sc.nextInt();
	            direction = input;
	            }
	        
	        if(p2X > size - 1 && p2Y > size - 1)
	        	continue;
	                                
	        player.vessels[0].setDirection(direction);
			
			player.vessels[0].setCoord(p2X, p2Y);
			
			
			try {
				addCoordinate(player.vessels[0]);
				System.out.println();
				count++;
				
			} catch(CoordinateException exc) {
				System.out.println(exc);
				continue;
			}
		} while(count != player.vessels.length);

		player.setTurn(false);
		return player.getTurn();
	}
	
	public void play(Player player, Grid playerGrid) {
		
		/*
		 * Players turn to select a coordinate to bomb
		 */
		Scanner sc = new Scanner(System.in);
		boolean quit = false;
		
		 do {
             System.out.println();
             System.out.println(player.getName() + "'s Board: ");
             System.out.println();
             System.out.println("*************************");
             drawGrid();
             System.out.println("*************************");
             System.out.println();
             System.out.println(player.getName() + "'s Hits Board: ");
             System.out.println();
             System.out.println("*************************");
             drawHitGrid();
             System.out.println("*************************");
             System.out.println();
             System.out.println("Bomb a coordinate");
             System.out.println();
             System.out.println("Type in x coordinate between "
                         + (getSize() -  getSize()) +
                         " and " +  (getSize() - 1));

             int p2X = sc.nextInt();

             System.out.println("Type in y coordinate between "
                         + (getSize() -  getSize()) +
                         " and " +  (getSize() - 1));
             int p2Y = sc.nextInt();

             if(p2X > size - 1 && p2Y > size - 1)
                 continue;
             else
                 bombCoord(p2X, p2Y, playerGrid, player);
                 quit = true;
                 
         } while(!quit);
         
		
	}
	
	
	public void bombCoord(int x, int y, Grid playerGrid, Player player) {
		
		/*
		 * Players selection used to bomb coordinate
		 */
		
		if(x > size - 1 || x < 0 || y > size - 1 || y < 0)
			play(player, playerGrid);
		
		String coord = playerGrid.grid[x][y];
		
		if(coord == " X") {
			playerGrid.grid[x][y] = " S";
			System.out.println("Hit!!!");
			gridHits[x][y] = " H";
			playerGrid.getPlayer().setVesselCoords();
		} else {
			gridHits[x][y] = " M";
			System.out.println("Miss!!!");
		}
		
	}
	
}






