package Battleships;

public class Vessel {

	private int length;
    private int xCoord;
    private int yCoord;
    private boolean vertical;
    private String name;
    private Direction direction;
	
    public Vessel(String name, int length) {
        this.name = name;
	this.length = length;
	}
	
    public void setCoord(int x, int y) {
	xCoord = x;
	yCoord = y;
	}
	
    public int getLength() { return length; };
    public void setOrientation(boolean o) { vertical = o; };
    public boolean isVertical() { return vertical; };
    public int getX() { return xCoord; };
    public int getY() { return yCoord; };
    public void setDirection(int direction) {
        switch(direction) {
            case 1:
                this.direction = Direction.NORTH;
                break;
            case 2:
                this.direction = Direction.EAST;
                break;
            case 3:
                this.direction = Direction.SOUTH;
                break;
            case 4:
                this.direction = Direction.WEST;
                break;
            default:
                this.direction = Direction.NORTH;
                break;
        }
    }
    public Direction getDirection() { return direction; };
	
}


