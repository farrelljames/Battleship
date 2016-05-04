package Battleships;

public class Player {

	private boolean turn;
	public Vessel vessels[];
	private String name;
	private boolean hasWon;
	private int vesselCoords;
	
	public Player(String name, boolean turn, Vessel[] vessels, boolean hasWon) {
		this.name = name;
		this.turn = turn;
		this.vessels = vessels;
		this.hasWon = hasWon;
		
		for(int i = 0; i < vessels.length; i++) {
			vesselCoords += vessels[i].getLength();
		}
	}
	
	public String getName() { return name; };
	public boolean getTurn() { return turn; };
	public void setTurn(boolean turn) { this.turn = turn; };
	public boolean getHasWon() { return hasWon; };
	public void setVesselCoords() { vesselCoords--; };
	public int getVesselCoords() { return vesselCoords; };
	
}
