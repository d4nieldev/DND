package Business;

public abstract class Enemy extends Unit{
    private int experienceValue;

    public Enemy(char tile, String name, int health_pool, int attack_pts, int defense_pts, int experienceValue) {
        super(tile, name, health_pool, attack_pts, defense_pts);
        this.experienceValue = experienceValue;
    }

    @Override
    public void accept(Unit unit) {
        // TODO: implement this method
    }

    @Override
    public void processStep() {
        // TODO: implement this method
    }

    @Override
    public void onDeath() {
        // TODO: implement this method
    }

    @Override
    public void visit(Player player) {
        // TODO: implement this method
    }

    @Override
    public void visit(Enemy enemy) {
        // TODO: implement this method
    }

    @Override
    public void visit(EmptyTile emptyTile) {
        // TODO: implement this method
    }

    @Override
    public void visit(Wall wall) {
        // TODO: implement this method
    }

    public boolean isPlayer(){
        return false;
    }

    public boolean isEnemy(){
        return true;
    }
}
