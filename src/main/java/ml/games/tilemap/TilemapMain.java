package ml.games.tilemap;

import java.io.IOException;

public class TilemapMain {

    public static void main(String[] args) {
        Tilemap tilemap = new Tilemap(40, 10);
        Tilemap.transform(tilemap, new MountainRange());
        tilemap.setTile(new Coordinate(1, 1), Tile.STONE);
        View view = new View(tilemap);
        Player p1 = new Player(tilemap, new Coordinate(5, 5));
        view.display();
        try {
            System.in.read();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        p1.move(0, 1);
        view.display();
    }
    
}
