package ml.games.labyrinth;

public class View {
    
    public static void display(Labyrinth labyrinth) {
        int h = labyrinth.getHeight();
        int w = labyrinth.getWidth();
        
        header(w);
        for (int y = 0; y < h; y++) {
            prepend();
            for (int x = 0; x < w; x++) {
                Cell cell = labyrinth.getCell(new Position(x, y));
                printCell(cell);
            }
            append();
            nextRow();
        }
        footer(w);
    }

    private static void header(int w) {
        for (int i = 0; i < w + 2; i++) {
            System.out.print("-");            
        }
        System.out.println();
    }

    private static void footer(int w) {
        for (int i = 0; i < w + 2; i++) {
            System.out.print("-");            
        }
        System.out.println();
    }
    
    private static void prepend() {
        System.out.print("|");        
    }
    
    private static void append() {
        System.out.print("|");
    }
    
    private static void printCell(Cell cell) {
        System.out.print(cell.getCellType().getCharacter());
    }
    
    private static void nextRow() {
        System.out.println();
    }
}
