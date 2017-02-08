package ml.games.labyrinth;

public class Main {

    public static void main(String[] args) {
        Labyrinth labyrinth = new Labyrinth(10, 50);
        labyrinth.addCellMap(LabyrinthDesignUtils.randomize(labyrinth, 10));
        labyrinth.addCellMap(LabyrinthDesignUtils.randomize(labyrinth, 10, CellType.STONE));
        labyrinth.addCellMap(LabyrinthDesignUtils.wall(labyrinth, Direction.NORTH, Direction.SOUTH, CellType.STONE));
        labyrinth.addCellMap(LabyrinthDesignUtils.westToEastPath(labyrinth, PathStrategy.ALTERNATE));
        View.display(labyrinth);
    }
}
