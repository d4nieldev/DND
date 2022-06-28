package Business;

public class Trap extends Enemy{
    private int visibilityTime;
    private int invisibilityTime;
    private int ticksCount;

    public Trap(char tile, String name, int health_pool, int attack_pts, int defense_pts, int experienceValue, int visibilityTime, int invisibilityTime) {
        super(tile, name, health_pool, attack_pts, defense_pts, experienceValue);
        this.visibilityTime = visibilityTime;
        this.invisibilityTime = invisibilityTime;
        this.ticksCount = 0;
    }

    @Override
    public void processStep(Player player) {
        if(ticksCount == visibilityTime + invisibilityTime)
            ticksCount = 0;
        else
            ticksCount ++;
        
        if(this.position.distance(player.position) < 2)
            battle(player);
    }

    @Override
    public char getTile(){
        if (ticksCount < visibilityTime)
            return super.getTile();
        else
            return '.';
    }
}
