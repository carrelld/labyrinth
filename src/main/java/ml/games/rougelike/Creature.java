package ml.games.rougelike;

import java.awt.Color;

public class Creature {

    private World world;
    
    public int x;
    public int y;
    
    private char glyph;
    private Color color;
    private CreatureAi ai;
    
    public char glyph() { return glyph; }
    public Color color() { return color; }
    
    public Creature(World world, char glyph, Color color) {
        this.world = world;
        this.glyph = glyph;
        this.color = color;
    }
    
    public void setCreatureAi(CreatureAi ai) { this.ai = ai; }
    
    public void dig(int wx, int wy) {
        world.dig(wx, wy);
    }
    
    public void moveBy(int mx, int my) {
        Creature other = world.getCreature(x + mx, y + my);
        
        if (other == null) {
            ai.onEnter(x + mx, y + my, world.getTile(x + mx, y + my));            
        } else {
            attack(other);
        }
    }
    
    private void attack(Creature other) {
        world.remove(other);
    }
    public void update() {
        ai.onUpdate();
    }
    public boolean canEnter(int mx, int my) {
        return world.getTile(mx, my).isGround() && world.getCreature(mx, my) == null;
    }
}
