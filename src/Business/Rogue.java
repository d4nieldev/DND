package Business;

import java.util.List;
import java.util.stream.Collectors;

public class Rogue extends Player{
    public Rogue(String name, int health_pool, int attack_pts, int defense_pts, int abilityCost) {
        super(name, health_pool, attack_pts, defense_pts);
        this.ability = new Ability(0, 100, abilityCost, "Fan of Knives", "energy");
        this.ability.setAbilityCast(this::abilityCastCallback);
    }

    @Override
    public String describe(){
        return super.describe() + String.format("\t\tEnergy: %s ", ability);
    }

    @Override
    protected void levelUp() {
        super.levelUp();
        ability.fillCurrent();
        int attackBonus = 3 * this.level;

        addBonuses(0, attackBonus, 0);
    }

    @Override
    public void abilityCastCallback(List<Enemy> enemyList) {
        messageCallback.send(String.format("%s cast %s", getName(), ability.getName()));

        List<Enemy> enemiesInRange = enemyList.stream().filter(e -> e.position.distance(position) < 2).collect(Collectors.toList());
        for (Enemy enemyToHit : enemiesInRange) {
            int defenseRoll = enemyToHit.defend();

            enemyToHit.dealPureDamage(getName(), attack_pts - defenseRoll, true);

            if(enemyToHit.isDead())
                consumeEnemy(enemyToHit);
        }
    }

    @Override
    public void onGameTick(){
        ability.addToCurrent(10);
    }

}
