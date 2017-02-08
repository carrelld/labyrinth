package ml.games.labyrinth;

public enum Direction {
    NORTH (0, -1),
    SOUTH (0, 1),
    EAST (1, 0),
    WEST (-1, 0);
    
    private int deltaX;
    private int deltaY;
    
    private Direction(int deltaX, int deltaY) {
        this.deltaX = deltaX;
        this.deltaY = deltaY;
    }
    
    public int getDeltaX() {
        return this.deltaX;                
    }
    public int getDeltaY() {
        return this.deltaY;                
    }
}
