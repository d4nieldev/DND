package Business;

public abstract class Enemy extends Unit{
    private int experienceValue;

    public Enemy(char tile, String name, int health_pool, int attack_pts, int defense_pts, int experienceValue) {
        super(tile, name, health_pool, attack_pts, defense_pts);
        this.experienceValue = experienceValue;
    }

    @Override
    public void accept(Unit unit) {
        unit.visit(this);
    }

    @Override
    public void visit(Player player) {
        battle(player);
    }

    @Override
    public void visit(Enemy enemy) { }

    public void onDeath(Player player){
        player.addExperience(experienceValue);
        // TODO: remove enemy from board
        // TODO: replace player position with this position
    }

    public abstract void processStep(Player player);
}
