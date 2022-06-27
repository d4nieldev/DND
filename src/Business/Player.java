package Business;

import java.util.List;

public abstract class Player extends Unit{
    protected int experience;
    protected int level;
    protected Ability ability;

    public Player(String name, int health_pool, int attack_pts, int defense_pts) {
        super('@', name, health_pool, attack_pts, defense_pts);
        this.experience = 0;
        this.level = 1;
    }

    @Override
    public void interact(Tile tile){
        onGameTick();
        super.interact(tile);
    }

    protected void levelUp() {
        this.experience -= (this.level * 50 );
        this.level++;
    }

    protected void addBonuses(int healthBonus, int attackBonus, int defenseBonus){
        int healthAddition = this.level * 10 + healthBonus;
        this.health.addToPool(healthAddition);
        this.health.fillHealth();

        int attackAddition = this.level * 4 + attackBonus;
        this.attack_pts += attackAddition;

        int defenseAddition =  this.level + defenseBonus;
        this.defense_pts += defenseAddition;

        messageCallback.send(getName() + " reached level " + this.level + ": +" + healthAddition + " Health, +" + attackAddition + " Attack, +" + defenseAddition + " Defense");
    }

    protected void addExperience(int addition){
        this.experience += addition;
        if(this.experience >= this.level * 50)
            levelUp();
    }

    public void visit(Player player){ }

    public void visit(Enemy enemy){
        super.battle(enemy);
        if(enemy.isDead())
            interact(consumeEnemy(enemy));

    }

    protected Tile consumeEnemy(Enemy enemy){
        Tile tile = enemy.onDeath();
        messageCallback.send(String.format("%s died. %s gained %d experience", enemy.getName(), getName(), enemy.getExperienceValue()));
        addExperience(enemy.getExperienceValue());
        return tile;
    }

    public void abilityCast(List<Enemy> enemyList){
        if(!ability.canCastAbility())
            messageCallback.send(String.format("%s tried to cast %s, but there was not enough %s: %s ", getName(), ability.getName(), ability.getResourceName(), ability));
        else
            ability.castAbility(enemyList);

    }

    @Override
    public String describe(){
        return super.describe() + String.format("\t\tLevel: %d\t\tExperience: %d/%d", level, experience, 50 * level);
    }

    @Override
    public void accept(Unit u) {
        u.visit(this);
    }

    protected abstract void abilityCastCallback(List<Enemy> enemyList);
    public abstract void onGameTick();
}
