package ml.games.tilemap;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Tilemap implements Iterable<Tile>{

    int x;
    int y;
    private static final Tile DEFAULT_TILE = Tile.AIR;
    Map<Coordinate, Tile> tiles = new HashMap<Coordinate, Tile>();
    
    public Tilemap(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static void transform(Tilemap tilemap, Transformable transformable) {
        transformable.transform(tilemap);
    }

    public int getWidth() {
        return this.x;
    }

    public int getHeight() {
        return this.y;
    }

    public void setTile(Coordinate coordinate, Tile tile) {
        this.tiles.put(coordinate, tile);
    }
    
    public void setTile(Map<Coordinate, Tile> tileLayer) {
        this.tiles.putAll(tileLayer);
    }
    
    public Tile getTile(Coordinate coordinate) {
        if (tiles.containsKey(coordinate)) {
            return tiles.get(coordinate);
        } else if (this.contains(coordinate)) {
            return DEFAULT_TILE;
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    public boolean contains(Coordinate c) {
        boolean validX = c.getX() >= 0 && c.getX() < this.getWidth();
        boolean validY = c.getY() >= 0 && c.getY() < this.getHeight();

        return validX && validY;
    }

    @Override
    public Iterator<Tile> iterator() {
        return new TilemapIterator(this);
    }
    
}

class Coordinate {
    int x;
    int y;
    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public double distanceTo(Coordinate that) {
        double dX = this.x - that.x;
        double dY = this.y - that.y;
        return Math.sqrt(dX * dX + dY * dY);
    }
    public int getX() {
        return this.x;
    }
    public int getY() {
        return this.y;
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
        if (!(obj instanceof Coordinate))
            return false;
        Coordinate other = (Coordinate) obj;
        if (x != other.x)
            return false;
        if (y != other.y)
            return false;
        return true;
    }
    public void moveRelative(int dx, int dy) {
        this.x += dx;
        this.y += dy;
    }
    public void bound(Tilemap tilemap) {
        this.x = Math.max(0, Math.min(tilemap.getWidth() - 1, this.x));
        this.y = Math.max(0, Math.min(tilemap.getHeight() - 1, this.y));
//        
//        if (this.x < 0) {
//            this.x = 0;
//        }
//        if (this.y < 0) {
//            this.y = 0;
//        }
//        if (this.x >= tilemap.getWidth()) {
//            this.x = tilemap.getWidth() - 1;
//        }
//        if (this.y >= tilemap.getHeight()) {
//            this.y = tilemap.getHeight() - 1;
//        }
        
    }
}

class TilemapIterator implements Iterator<Tile>{
    
    int rangeX;
    int rangeY;
    int cursorX = 0;
    int cursorY = 0;
    Tilemap tilemap;
    
    TilemapIterator(Tilemap tilemap) {
        this.tilemap = tilemap;
        rangeX = tilemap.getWidth();
        rangeY = tilemap.getHeight();
    }

    @Override
    public boolean hasNext() {
        return rangeX > cursorX || rangeY > cursorY;
    }

    @Override
    public Tile next() {
        Tile nextTile = tilemap.getTile(new Coordinate(cursorX, cursorY));
        if (cursorX < rangeX) {
            cursorX++;
        } else if (cursorY < rangeY) {
            cursorX = 0;
            cursorY++;
        } else {
            throw new IndexOutOfBoundsException();            
        }
        return nextTile;
    }
}
