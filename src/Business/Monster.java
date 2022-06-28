package Business;

public class Monster extends Enemy{
    private int visionRange;

    public Monster(char tile, String name, int health_pool, int attack_pts, int defense_pts, int experienceValue, int visionRange) {
        super(tile, name, health_pool, attack_pts, defense_pts, experienceValue);
        this.visionRange = visionRange;
    }

    @Override
    public String describe(){
        return super.describe() + String.format("\t\tVision Range: %d", visionRange);
    }

    @Override
    public void processStep(Player player) {
        int movement; // 0 - up, 1 - down, 2 - left, 3 - right

        if (position.distance(player.position) < visionRange){
            int dx = position.getX() - player.position.getX();
            int dy = position.getY() - player.position.getY();
            if (Math.abs(dx) > Math.abs(dy))
                if(dx > 0)
                    // move left
                    movement = 2;
                else
                    // move right
                    movement = 3;
            else
                if (dy > 0)
                    // move up
                    movement = 0;
                else
                    // move down
                    movement = 1;
        }
        else
            movement = generator.nextInt(4);

        moveCallback.move(movement);
    }
}
