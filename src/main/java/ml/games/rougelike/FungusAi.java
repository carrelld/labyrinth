package ml.games.rougelike;

public class FungusAi extends CreatureAi {

    private CreatureFactory factory;
    private int spreadcount;
    
    public FungusAi(Creature creature, CreatureFactory factory) {
        super(creature);
        this.factory = factory;
    }
    
    public void onUpdate() {
        if (spreadcount < 5 && World.RANDOM.nextDouble() < 0.02) {
            spread();
        }
    }

    private void spread() {
        int x = creature.x + World.RANDOM.nextInt(4) - 2;
        int y = creature.y + World.RANDOM.nextInt(4) - 2;
        
        if (creature.canEnter(x, y)) {
            Creature child = factory.newFungus();
            child.x = x;
            child.y = y;
            spreadcount++;
        }
    }

}
