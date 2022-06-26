package Business;

import java.util.List;

public abstract class Player extends Unit{
    protected int experience;
    protected int level;
    protected AbilityResource abilityResource;

    public Player(String name, int health_pool, int attack_pts, int defense_pts) {
        super('@', name, health_pool, attack_pts, defense_pts);
        this.experience = 0;
        this.level = 1;
    }

    protected void levelUp() {

        this.experience -= (this.level * 50 );
        this.level++;
        this.health.addToPool(this.level * 10);
        this.health.fillHealth();
        this.attack_pts += this.level * 4;
        this.defense_pts += this.level;

    }

    public void addExperience(int addition){
        this.experience += addition;
        if(this.experience >= this.level * 50)
            levelUp();
    }

    @Override
    public void accept(Unit u) {
        // TODO: implement this method
    }

    @Override
    public void visit(Player p) { }

    @Override
    public void visit(Enemy e) {
        // TODO: implement this method
    }

    @Override
    public void processStep() {
        // TODO: implement this method
    }

    @Override
    public void onDeath() {
        // TODO: implement JUST this method!!!
    }

    public boolean isPlayer(){
        return true;
    }

    public boolean isEnemy(){
        return false;
    }

    public abstract void abilityCast(List<Enemy> enemies);
}
