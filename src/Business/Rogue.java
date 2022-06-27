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
        List<Enemy> enemiesToHit = enemyList.stream().filter(e -> e.position.distance(position) < 2).collect(Collectors.toList());
        messageCallback.send(String.format("%s cast %s", getName(), ability.getName()));
        for (Enemy enemyToHit : enemiesToHit) {
            int defenseRoll = enemyToHit.defend();

            int penetration = attack_pts - defenseRoll;
            if (penetration > 0)
                enemyToHit.health.reduceHealth(penetration);
            else
                penetration = 0;

            messageCallback.send(String.format("%s hit %s for %d ability damage.", getName(), enemyToHit.getName(), penetration));

            if(enemyToHit.isDead())
                consumeEnemy(enemyToHit);
        }
    }

    @Override
    public void onGameTick(){
        ability.addToCurrent(10);
    }

}
