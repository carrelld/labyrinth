package ml.games.tilemap;

public class Player{

    private Coordinate position;
    private Tilemap tilemap;

    public Player( Tilemap tilemap, Coordinate position) {
        this.tilemap = tilemap;
        this.position = position;
    }

    public void move(int dx, int dy) {
        this.position.moveRelative(dx, dy);
        this.position.bound(tilemap);
    }

    

}
