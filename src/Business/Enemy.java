package Business;

public abstract class Enemy extends Unit{
    private final int experienceValue;
    private DeathCallback deathCallback;


    public Enemy(char tile, String name, int health_pool, int attack_pts, int defense_pts, int experienceValue) {
        super(tile, name, health_pool, attack_pts, defense_pts);
        this.experienceValue = experienceValue;
        // default death callback - used for tests. When an enemy is created he is using different bound to board death callback
        this.deathCallback = () -> {
            EmptyTile emptyTile = new EmptyTile();
            emptyTile.initialize(position);
            return emptyTile;
        };
    }

    @Override
    public String describe(){
        return super.describe() + String.format("\t\tExperience Value: %d", experienceValue);
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

    public void setDeathCallback(DeathCallback deathCallback) {
        this.deathCallback = deathCallback;
    }

    public int getExperienceValue(){
        return this.experienceValue;
    }

    protected Tile onDeath(){
        return deathCallback.onDeath();
    }

    public abstract void processStep(Player player);
}
