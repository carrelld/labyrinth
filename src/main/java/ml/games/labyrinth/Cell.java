package ml.games.labyrinth;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Cell {

    public static final Cell DEFAULT = new Cell(CellType.AIR);
    
    private static final Random random = new Random();
    private static final List<CellType> values= Collections.unmodifiableList(Arrays.asList(CellType.values()));
    private static final int size = values.size();
    
    private CellType cellType;
    
    /**
     * Random Cell constructor
     */
    public Cell() {
        this.cellType = values.get(random.nextInt(size));
    }
    
    /**
     * Specific CellType Cell constructor
     * @param cellType
     */
    public Cell(CellType cellType) {
        this.cellType = cellType;
    }
    
    public CellType getCellType() {
        return cellType;
    }
}
