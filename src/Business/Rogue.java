package Business;

import java.util.List;

public class Rogue extends Player{
    public Rogue(String name, int health_pool, int attack_pts, int defense_pts, int abilityCost) {
        super(name, health_pool, attack_pts, defense_pts);
        this.abilityResource = new AbilityResource(0, 100, abilityCost);
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
