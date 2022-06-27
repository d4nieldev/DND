package Business;

import java.util.List;

public class Hunter extends Player{
    private int range;
    private int ticksCount;

    public Hunter(String name, int health_pool, int attack_pts, int defense_pts, int range) {
        super(name, health_pool, attack_pts, defense_pts);
        this.range = range;
        ticksCount = 0;
        this.ability = new Ability(10, -1, 1, "Shoot", "arrows");
        this.ability.setAbilityCast(this::abilityCastCallback);
    }

    @Override
    public String describe(){
        return super.describe() + String.format("\t\tRange: %s ", range);
    }

    @Override
    protected void levelUp() {
        super.levelUp();
        ability.addToCurrent(10 * this.level);
        int attackBonus = 2 * this.level;
        int defenseBonus = this.level;

        addBonuses(0, attackBonus, defenseBonus);
    }

    @Override
    protected void abilityCastCallback(List<Enemy> enemyList) {
        // find closest enemy
        Enemy enemyToHit = enemyList.get(0);
        for(Enemy enemy : enemyList)
            if (enemy.position.distance(position) < enemyToHit.position.distance(position))
                enemyToHit = enemy;

        if(enemyToHit.position.distance(position) < range) {
            messageCallback.send(String.format("%s fired an arrow at %s", getName(), enemyToHit.getName()));

            int defenseRoll = enemyToHit.defend();

            enemyToHit.dealPureDamage(getName(), attack_pts - defenseRoll, true);

            if (enemyToHit.isDead())
                consumeEnemy(enemyToHit);
        }
        else
            messageCallback.send(String.format("%s tried to shoot an arrow but there were no enemies in range.", getName()));
    }

    @Override
    public void onGameTick() {
        if (ticksCount == 10){
            ability.addToCurrent(this.level);
            ticksCount = 0;
        }
        else
            ticksCount++;
    }
}
