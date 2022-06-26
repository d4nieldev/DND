package Business;

import java.util.List;

public class Warrior extends Player{
    public Warrior(String name, int health_pool, int attack_pts, int defense_pts, int abilityCooldown)
    {
        super(name, health_pool, attack_pts, defense_pts);
        this.ability = new Ability(0, abilityCooldown, abilityCooldown, "Avenger’s Shield", "cooldown");
    }

    @Override
    public String describe(){
        return super.describe() + String.format("\t\tCooldown: %s", ability);
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
