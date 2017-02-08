package ml.games.labyrinth;

import java.util.HashMap;
import java.util.Map;

/**
 * Manages generation of a map, its dimensions, and the positions of cells
 * @author carredx
 *
 */
public class Labyrinth {

    private final int height;
    private final int width;
    private final Map<Position, Cell> cellMap = new HashMap<Position, Cell>();
    private final double maxDistance;
    private final int numPositions;
    
    public Labyrinth(int height, int width) {
        if (height <= 0 || width <= 0) {
            throw new IllegalArgumentException();
        }
        this.height = height;
        this.width = width;
        this.maxDistance = new Position(width, 0).distanceTo(new Position(0, height));
        this.numPositions = height * width;
    }
    
    /**
     * Get the cell at the given position, default cell if cell not present
     */
    public Cell getCell(Position position) {
        if (cellMap.containsKey(position)) {
            return cellMap.get(position);
        } else {
            return Cell.DEFAULT;
        }
    }
    
    public final void addCellMap(Map<Position, Cell> cellMap) {
        this.cellMap.putAll(cellMap);
    }
    
    public final Map<Position, Cell> getCellMap() {
        return this.cellMap;
    }
    
    public final int getHeight() {
        return this.height;
    }
    
    public final int getWidth() {
        return this.width;
    }
    
    public final double getMaxDistance() {
        return this.maxDistance;
    }

    public int getNumPositions() {
        return this.numPositions;
    }
}
