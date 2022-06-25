package Business;

public class Monster extends Enemy{
    private int visionRange;

    public Monster(char tile, String name, int health_pool, int attack_pts, int defense_pts, int experienceValue, int visionRange) {
        super(tile, name, health_pool, attack_pts, defense_pts, experienceValue);
        this.visionRange = visionRange;
    }
}
