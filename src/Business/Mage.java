package Business;

import java.util.List;
import java.util.stream.Collectors;

public class Mage extends Player {
    private int spellPower;
    private int hitsCount;
    private int abilityRange;

    public Mage(String name, int health_pool, int attack_pts, int defense_pts, int manaPool, int manaCost, int spellPower, int hitsCount, int abilityRange)
    {
        super(name, health_pool, attack_pts, defense_pts);
        this.ability = new Ability(manaPool/4, manaPool, manaCost, "Blizzard", "mana");
        this.ability.setAbilityCast(this::abilityCastCallback);
        this.spellPower = spellPower;
        this.hitsCount = hitsCount;
        this.abilityRange = abilityRange;
    }

    @Override
    public String describe(){
        return super.describe() + String.format("\t\tMana: %s\t\tSpell Power: %d", ability, spellPower);
    }

    @Override
    protected void abilityCastCallback(List<Enemy> enemyList) {
        messageCallback.send(String.format("%s cast %s", getName(), ability.getName()));
        int hits = 0;
        List<Enemy> enemiesInRange = enemyList.stream().filter(e -> e.position.distance(position) < abilityRange).collect(Collectors.toList());
        while (hits < hitsCount && enemiesInRange.size() > 0){
            Enemy enemyToHit = enemiesInRange.get((int) (Math.random() * enemiesInRange.size()));

            int defenseRoll = enemyToHit.defend();

            enemyToHit.dealPureDamage(getName(), spellPower - defenseRoll, true);

            if(enemyToHit.isDead())
                consumeEnemy(enemyToHit);

            hits++;
        }
    }

    @Override
    public void onGameTick() {
        ability.addToCurrent(this.level);
    }

    @Override
    protected void levelUp() {
        super.levelUp();
        addBonuses(0, 0, 0);

        int manaPoolAddition = 25 * this.level;
        ability.setResourcePool(ability.getResourcePool() + manaPoolAddition);
        ability.addToCurrent(ability.getResourcePool() / 4);

        int spellPowerAddition = 10 * this.level;
        spellPower += spellPowerAddition;

        messageCallback.send(String.format("\t\t\t\t+%d maximum mana, +%d spell power", manaPoolAddition, spellPowerAddition));
    }
}
