package ml.games.tilemap;

public enum Tile {
    AIR ('.'),
    STONE ('O');
    
    private char character;
    
    Tile(char c) {
        this.character = c;
    }
    
    public char getCharacter() {
        return this.character;
    }
}
