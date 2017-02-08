package ml.games.tilemap;

import java.util.Random;

public interface Transformable {

    public void transform(Tilemap tilemap);
    
}

class MountainRange implements Transformable {

    @Override
    public void transform(Tilemap tilemap) {
        // TODO Auto-generated method stub
        Random random = new Random();
        int x = random.nextInt(tilemap.getWidth()) + 1;
        int y = random.nextInt(tilemap.getHeight()) + 1;
        tilemap.setTile(new Coordinate(x, y), Tile.STONE);
    }
    
}