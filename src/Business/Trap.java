package Business;

public class Trap extends Enemy{
    private int visibilityTime;
    private int invisibilityTime;
    private int ticksCount;
    private boolean visible;

    public Trap(char tile, String name, int health_pool, int attack_pts, int defense_pts, int experienceValue, int visibilityTime, int invisibilityTime) {
        super(tile, name, health_pool, attack_pts, defense_pts, experienceValue);
        this.visibilityTime = visibilityTime;
        this.invisibilityTime = invisibilityTime;
        this. ticksCount = 0;
        this. visible = true;
    }

    @Override
    public void processStep(Player player) {

    }

    @Override
    public void accept(Unit u){
        u.visit(this);
    }

    @Override
    public void visit(Player player) {

    }

    @Override
    public void visit(Enemy enemy) {

    }

    @Override
    public void visit(EmptyTile emptyTile) {

    }

    @Override
    public void visit(Wall wall) {

    }
}
