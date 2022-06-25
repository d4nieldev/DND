package Business;

import java.util.List;

public class Mage extends Player {
    private int spellPower;
    private int hitsCount;
    private int abilityRange;

    public Mage(String name, int health_pool, int attack_pts, int defense_pts, int manaPool, int manaCost, int spellPower, int hitsCount, int abilityRange)
    {
        super(name, health_pool, attack_pts, defense_pts);
        this.abilityResource = new AbilityResource(manaPool/4, manaPool, manaCost);
        this.spellPower = spellPower;
        this.hitsCount = hitsCount;
        this.abilityRange = abilityRange;
    }

    @Override
    protected void levelUp() {
        // TODO: implement this method
    }

    @Override
    public void abilityCast(List<Enemy> enemies) {
        // TODO: implement this method
    }
}
