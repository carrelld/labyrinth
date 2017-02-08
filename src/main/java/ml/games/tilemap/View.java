package ml.games.tilemap;

public class View {

    private Tilemap tilemap;
    
    public View(Tilemap tilemap) {
        this.tilemap = tilemap;
    }
    
    public void display() {
        for (int i = 1; i <= tilemap.getHeight(); i++) {
            for (int j = 1; j <= tilemap.getWidth(); j++) {
                System.out.print(tilemap.getTile(new Coordinate(j, i)).getCharacter());
            }
            System.out.println();
        }
    }
    
}
