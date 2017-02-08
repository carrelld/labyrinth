package ml.games.labyrinth;

import java.util.HashSet;
import java.util.Set;

/**
 * the coordinates of a cell based on (x, y) == (0,0) being the top left
 * @author carredx
 *
 */
public class Position {


    int x;
    int y;
    
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public Position(Position position) {
        this(position.x, position.y);
    }

    /**
     * Calculate the absolute linear distance from this to that
     */
    public double distanceTo(Position that) {
        int deltaX = that.x - this.x;
        int deltaY = that.y - this.y;
        return Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2));
    }
    
    @Override
    public String toString() {
        return String.format("[%1$3d,%2$3d]", x, y);
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + x;
        result = prime * result + y;
        return result;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Position other = (Position) obj;
        if (x != other.x)
            return false;
        if (y != other.y)
            return false;
        return true;
    }

    /**
     * Create a new Position relative to this Position. Position should be checked for validity before being used
     * 
     * @param deltaX change in X relative to this
     * @param deltaY change in Y relative to this
     * @return
     */
    public Position offset(int deltaX, int deltaY) {
        return new Position(this.x + deltaX, this.y + deltaY);
    }
    
    /**
     * Gather the set of Positions adjacent (using offsets of enum Directions) to this Position
     * @return
     */
    public Set<Position> getSurrounding() {
        Set<Position> surrounding = new HashSet<Position>();
        
        for (Direction d : Direction.values()) {
            Position adjacent = this.offset(d.getDeltaX(), d.getDeltaY());
            surrounding.add(adjacent);
        }
        
        return surrounding;
    }
    
    /**
     * Verify position would be in a grid of the given size with starting at (0,0). Valid positions range from (0,0) up to and including (maxX-1, maxY-1)
     * @param maxX The size of the x component of the grid
     * @param maxY The size of the y component of the grid
     * @return True if this position would fit in a grid of the given size
     */
    public boolean withinBounds(int maxX, int maxY) {
        boolean validX = this.x >= 0 && this.x < maxX;
        boolean validY = this.y >= 0 && this.y < maxY;
        return validX && validY;
    }
}
