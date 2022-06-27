package Business;

public class Monster extends Enemy{
    private int visionRange;

    public Monster(char tile, String name, int health_pool, int attack_pts, int defense_pts, int experienceValue, int visionRange) {
        super(tile, name, health_pool, attack_pts, defense_pts, experienceValue);
        this.visionRange = visionRange;
    }

    @Override
    public void processStep(Player player) {
        int movement; // 0 - left, 1 - right, 2 - up, 3 - down

        if (position.distance(player.position) < visionRange){
            int dx = position.getX() - player.position.getX();
            int dy = position.getY() - player.position.getY();
            if (Math.abs(dx) > Math.abs(dy))
                if(dx > 0)
                    movement = 1;
                else
                    movement = 0;
            else
                if (dy > 0)
                    movement = 2;
                else
                    movement = 3;
        }
        else
            movement = (int)(Math.random() * 4);

        interactCallback.interact(movement);
    }

    @Override
    public void accept(Unit u){
        u.visit(this);
    }
}
