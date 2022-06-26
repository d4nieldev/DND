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

    protected void levelUp() {
        this.experience -= (this.level * 50 );
        this.level++;

        int healthAddition = this.level * 10;
        this.health.addToPool(healthAddition);
        this.health.fillHealth();

        int attackAddition = this.level * 4;
        this.attack_pts += attackAddition;

        int defenseAddition =  this.level;
        this.defense_pts += defenseAddition;

        messageCallback.send(getName() + " reached level " + this.level + ": +" + healthAddition + " Health, +" + attackAddition + " Attack, +" + defenseAddition + " Defense");
    }

    public void addExperience(int addition){
        this.experience += addition;
        if(this.experience >= this.level * 50)
            levelUp();
    }

    @Override
    public void accept(Unit u) {
        u.visit(this);
    }

    protected void battle(Enemy enemy){
        super.battle(enemy);
        if(enemy.isDead())
            enemy.onDeath(this);

    }

    public void visit(Player player){
        // do nothing
    }

    public void visit(Enemy enemy){
        battle(enemy);
    }

    public void abilityCast(List<Enemy> enemies){
        if(!ability.canCastAbility())
            messageCallback.send(String.format("%s tried to cast %s, but there was not enough %s: %s ", getName(), ability.getName(), ability.getResourceName(), ability));
    }
}
