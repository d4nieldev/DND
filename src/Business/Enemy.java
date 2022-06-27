package Business;

public abstract class Enemy extends Unit{
    private int experienceValue;
    private DeathCallback deathCallback;
    protected InteractCallback interactCallback;

    public Enemy(char tile, String name, int health_pool, int attack_pts, int defense_pts, int experienceValue) {
        super(tile, name, health_pool, attack_pts, defense_pts);
        this.experienceValue = experienceValue;
    }

    public void setDeathCallback(DeathCallback deathCallback) {
        this.deathCallback = deathCallback;
    }

    public int getExperienceValue(){
        return this.experienceValue;
    }

    public void onDeath(Player player){
        deathCallback.onDeath(player);
    }

    public void setInteractCallback(InteractCallback interactCallback){
        this.interactCallback = interactCallback;
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

    public abstract void processStep(Player player);
}
