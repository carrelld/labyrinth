package ml.games.labyrinth;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class LabyrinthDesignUtils {

    private static final Random random = new Random();
    
    /**
     * Randomly generate all Cells and return a Map of those cells
     */
    public static Map<Position, Cell> randomize(Labyrinth labyrinth) {
        int w = labyrinth.getWidth();
        int h = labyrinth.getHeight();
        
        Map<Position, Cell> cells = new HashMap<Position, Cell>(); 
        for (int x = 0; x < w; x++) {
            for (int y = 0; y < h; y++) {
                cells.put(new Position(x, y), new Cell());
            }
        }
        return cells;
    }
    
    /**
     * Randomly generate some number of Cells and return a Map of those cells
     */
    public static Map<Position, Cell> randomize(Labyrinth labyrinth, int numCells) {
        int w = labyrinth.getWidth();
        int h = labyrinth.getHeight();
        
        Map<Position, Cell> cells = new HashMap<Position, Cell>(); 
        for (int i = 0; i < numCells; i++) {
            cells.put(new Position(random.nextInt(w), random.nextInt(h)), new Cell());
        }
        return cells;
    }
    
    /**
     * Randomly generate some number of specific Cells and return a Map of those cells
     */
    public static Map<Position, Cell> randomize(Labyrinth labyrinth, int numCells, CellType cellType) {
        int w = labyrinth.getWidth();
        int h = labyrinth.getHeight();
        
        Map<Position, Cell> cells = new HashMap<Position, Cell>(); 
        for (int i = 0; i < numCells; i++) {
            cells.put(new Position(random.nextInt(w), random.nextInt(h)), new Cell(cellType));
        }
        return cells;
    }
    
    /**
     * Make random wall
     */
    public static Map<Position, Cell> wall(Labyrinth labyrinth, Direction wallStart, Direction wallEnd, CellType cellType) {
        Position start = getRandomEdgePosition(labyrinth, wallStart);
        Position end = getRandomEdgePosition(labyrinth, wallEnd);
        List<Position> path = PathStrategy.DEFAULT.findPath(labyrinth, start, end);
        
        Map<Position, Cell> cells = new HashMap<Position, Cell>(); 
        for (Position p : path) {
            cells.put(p, new Cell(cellType));
        }
        return cells;
        
    }

    /**
     * Return a List of contiguous Positions which span from WEST to EAST
     * @param labyrinth
     */
    public static Map<Position, Cell> westToEastPath(Labyrinth l, PathStrategy pathStrategy) {
        Position start = getRandomEdgePosition(l, Direction.WEST);
        Position end = getRandomEdgePosition(l, Direction.EAST);
        List<Position> path = pathStrategy.findPath(l, start, end);
        // convert to Map
        Map<Position, Cell> cells = new HashMap<Position, Cell>();
        for (Position p : path) {
            cells.put(p, new Cell(CellType.DIRT));
        }
        return cells;
    }
    
    /**
     * Give a random position on the given edge
     * @param direction
     * @return
     */
    private static Position getRandomEdgePosition(Labyrinth l, Direction direction) {
        int x = -1;
        int y = -1;
        int w = l.getWidth();
        int h = l.getHeight();
        
        switch (direction) {
        case NORTH:
            y = 0;
            x = random.nextInt(w);
            break;
        case SOUTH:
            y = h - 1;
            x = random.nextInt(w);
            break;
        case EAST:
            y = random.nextInt(h);
            x = w - 1;
            break;
        case WEST:
            y = random.nextInt(h);
            x = 0;
            break;
        }
        
        assert x >= 0 && x < w;
        assert y >= 0 && y < h;
        
        return new Position(x, y);
    }
    
}
