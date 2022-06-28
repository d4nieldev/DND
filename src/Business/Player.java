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

    public int getExperience(){
        return this.experience;
    }

    public int getLevel(){
        return this.level;
    }

    public Ability getAbility(){
        return this.ability;
    }

    @Override
    public void interact(Tile tile){
        onGameTick();
        super.interact(tile);
    }

    @Override
    public void visit(Player player){ }

    @Override
    public void visit(Enemy enemy){
        super.battle(enemy);
        if(enemy.isDead())
            interact(consumeEnemy(enemy));
    }

    @Override
    public String describe(){
        return super.describe() + String.format("\t\tLevel: %d\t\tExperience: %d/%d", level, experience, 50 * level);
    }

    @Override
    public void accept(Unit u) {
        u.visit(this);
    }

    protected void levelUp() {
        this.experience -= this.level * 50;
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

        messageCallback.send(String.format("%s reached level %d: +%d Health, +%d Attack, +%d Defense", getName(), this.level, healthAddition, attackAddition, defenseAddition));
    }

    protected void addExperience(int addition){
        this.experience += addition;
        while(this.experience >= this.level * 50)
            levelUp();
    }

    protected Tile consumeEnemy(Enemy enemy){
        Tile tile = enemy.onDeath();
        addExperience(enemy.getExperienceValue());

        messageCallback.send(String.format("%s died. %s gained %d experience.", enemy.getName(), getName(), enemy.getExperienceValue()));
        return tile;
    }

    public void abilityCast(List<Enemy> enemyList){
        if(!ability.canCastAbility())
            messageCallback.send(String.format("%s tried to cast %s, but there was not enough %s: %s ", getName(), ability.getName(), ability.getResourceName(), ability));
        else
            ability.castAbility(enemyList);
    }

    protected abstract void abilityCastCallback(List<Enemy> enemyList);
    public abstract void onGameTick();
}
