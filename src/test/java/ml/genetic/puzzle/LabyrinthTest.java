package ml.genetic.puzzle;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import ml.games.labyrinth.Cell;
import ml.games.labyrinth.Labyrinth;
import ml.games.labyrinth.Position;

public class LabyrinthTest {

    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    @Test
    public void testLabyrinth() {
        // test labyrinth creation is doing more than just making a blank map
        Labyrinth labyrinth = new Labyrinth(30, 30);
        Map<Position, Cell> map = labyrinth.getCellMap();
        
        assertTrue("cellMap should be non-null", map != null);
        assertTrue("Some cells should be present in the cellMap", map.size() > 0);
        
        boolean nonDefaultCellTypePresent = false;
        for (Position p : map.keySet()) {
            nonDefaultCellTypePresent = !map.get(p).getCellType().equals(Cell.DEFAULT.getCellType());
        }
        assertTrue("Random map should include at least 1 non-default cell type", nonDefaultCellTypePresent);
    }

}
