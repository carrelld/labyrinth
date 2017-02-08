package ml.games.rougelike;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class World {
    
    public static final Random RANDOM = new Random();
    
    private Tile[][] tiles;
    private List<Creature> creatures;
    private int width;
    private int height;
    
    public int width() { return width; }
    public int height() { return height; }
    
    public World(Tile[][] tiles) {
        this.tiles = tiles;
        this.width = tiles.length;
        this.height = tiles[0].length;
        this.creatures = new ArrayList<Creature>();
    }
    
    public Tile getTile(int x, int y) {
        if (x < 0 || x >= width || y < 0 || y >= height) {
            return Tile.BOUNDS;
        } else {
            return tiles[x][y];
        }
    }
    
    public Creature getCreature(int x, int y) {
        for (Creature c : creatures) {
            if (c.x == x && c.y == y) {
                return c;
            }
        }
        return null; //maybe return a "null" creature instead?
    }
    
    public char glyph(int x, int y) {
        return getTile(x, y).glyph();
    }
    
    public Color color(int x, int y) {
        return getTile(x, y).color();
    }
    
    public void dig(int x, int y) {
        if (getTile(x, y).isDiggable()) {
            tiles[x][y] = Tile.FLOOR;
        }
    }
    public void addAtEmptyLocation(Creature creature) {
        int x;
        int y;
        
        do {
            x = RANDOM.nextInt(width);
            y = RANDOM.nextInt(height);
        } while (!getTile(x, y).isGround() || getCreature(x, y) != null);
        
        creature.x = x;
        creature.y = y;
        creatures.add(creature);
    }
    public List<Creature> getCreatures() {
        return creatures;
    }
    public void remove(Creature other) {
        creatures.remove(other);
    }
    
    public void update() {
        List<Creature> currentCreatures = new ArrayList<Creature>(creatures);
        for (Creature creature : currentCreatures) {
            creature.update();
        }
    }
}
