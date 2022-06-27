package Business;

import java.util.List;
import java.util.stream.Collectors;

public class Warrior extends Player{
    public Warrior(String name, int health_pool, int attack_pts, int defense_pts, int abilityCooldown)
    {
        super(name, health_pool, attack_pts, defense_pts);
        this.ability = new Ability(3, abilityCooldown, abilityCooldown, "Avenger’s Shield", "cooldown");
    }

    @Override
    protected void abilityCastCallback(List<Enemy> enemyList){
        int healAmount = 10 * defense_pts;
        messageCallback.send(String.format("%s used %s, healing for %d", getName(), ability.getName(), healAmount));
        health.addHealth(healAmount);

        List<Enemy> enemiesInRange = enemyList.stream().filter(e -> e.position.distance(position) < 3).collect(Collectors.toList());
        if(!enemiesInRange.isEmpty()) {
            Enemy enemyToHit = enemiesInRange.get((int) (Math.random() * enemiesInRange.size()));

            int defenseRoll = enemyToHit.defend();

            int attackAmount = (int) (0.1 * health.getPool());
            int penetration = attackAmount - defenseRoll;
            if (penetration > 0)
                enemyToHit.health.reduceHealth(penetration);
            else
                penetration = 0;
            messageCallback.send(String.format("%s hit %s for %d ability damage.", getName(), enemyToHit.getName(), penetration));
        }
    }

    @Override
    public String describe(){
        return super.describe() + String.format("\t\tCooldown: %s", ability);
    }

    @Override
    protected void levelUp() {
        super.levelUp();
        ability.fillCurrent();
        int healthBonus = 5 * this.level;
        int attackBonus = 2 * this.level;
        int defenseBonus = this.level;
        addBonuses(healthBonus, attackBonus, defenseBonus);
    }

    @Override
    public void onGameTick(){
        ability.addToCurrent(1);
    }
}
