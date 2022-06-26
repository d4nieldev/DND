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
            if (Math.abs(dx) > Math.abs(dy)){
                if(dx > 0)
                    movement = 0;
                else
                    movement = 1;
            }
            else{
                if (dy > 0)
                    movement = 2;
                else
                    movement = 3;
            }
        }
        else
            movement = (int)(Math.random() * 4);


        switch (movement){
            case 0:
                // TODO: move left
            case 1:
                // TODO: move right
            case 2:
                // TODO: move up
            case 3:
                // TODO: move down
        }
    }

    @Override
    public void accept(Unit u){
        u.visit(this);
    }
}
