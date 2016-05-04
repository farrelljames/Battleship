package Battleships;

import java.util.ArrayList;
import java.util.Scanner;

public class BattleShips {

	public static void main(String args[]) {

		Vessel boat = new Vessel("Boat", 2);
		Vessel boat2 = new Vessel("Boat", 2);
		
		Vessel p1BoatList[] = {boat};
		Vessel p2BoatList[] = {boat2};
		
		Player p1 = new Player("Player 1", true, p1BoatList, false);
		Player p2 = new Player("Player 2", true, p2BoatList, false);
	
		Grid playerGrid1 = new Grid(5, p1);
		Grid playerGrid2 = new Grid(5, p2);
		
		int count = 0;
		
		do {
			playerGrid1.drawGrid();
			System.out.println();
			
			while(p1.getTurn()) {
				playerGrid1.placeVessels(p1);
				count++;
			}
			
			while(p2.getTurn()) {
				playerGrid2.placeVessels(p2);
				count++;
			}
			
		} while(count != p1BoatList.length * 2);
		
		
		do {
			
			playerGrid1.play(p1, playerGrid2);
			
			if(p2.getVesselCoords() <= 0)
				break;
			
			playerGrid2.play(p2, playerGrid1);
			
		} while(p1.getVesselCoords() != 0 && p2.getVesselCoords() != 0);
		
		if(p1.getVesselCoords() == 0)
			System.out.println("Congrats player 2 wins!!!");
		else
			System.out.println("Congrats player 1 wins!!!");
		
	}
}













































