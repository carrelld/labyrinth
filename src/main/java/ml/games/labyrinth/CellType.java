package ml.games.labyrinth;

public enum CellType{
    AIR (' ', true),
    DIRT ('*', true),
    WALL ('|', false),
    STONE ('O', false),
    LADDER ('H', true);

    private char charRepresentation;
    private boolean permeable;
    
    public char getCharacter() {
        return this.charRepresentation;
    }
    
    public boolean isPermeable() {
        return this.permeable;
    }
    
    private CellType(char c, boolean p) {
        this.charRepresentation = c;
        this.permeable = p;
    }
}
