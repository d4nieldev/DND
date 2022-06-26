package Business;

import java.util.List;

public class Rogue extends Player{
    public Rogue(String name, int health_pool, int attack_pts, int defense_pts, int abilityCost) {
        super(name, health_pool, attack_pts, defense_pts);
        this.ability = new Ability(0, 100, abilityCost, "Fan of Knives", "energy");
    }

    @Override
    protected void levelUp() {
        // TODO: implement this method
    }
    @Override
    public String describe(){
        return super.describe() + String.format("\t\tEnergy: %s ", ability);
    }


    @Override
    public void abilityCast(List<Enemy> enemies) {
        // TODO: implement this method
    }
}
