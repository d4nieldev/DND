package Business;

public abstract class Unit extends Tile implements Visitor {
    protected String name;
    protected Health health;
    protected int attack_pts;
    protected int defense_pts;

    public Unit(char tile, String name, int health_pool, int attack_pts, int defense_pts)
    {
        super(tile);
        this.name = name;
        this.health = new Health(health_pool);
        this.attack_pts = attack_pts;
        this.defense_pts = defense_pts;
    }

    protected int attack(){
        // TODO: implement this method
        return -1;
    }

    public int defend(){
        // TODO: implement this method
        return -1;
    }

    public void interact(Tile tile){
        tile.accept(this); // TODO: maybe change?
    }

    /**
     * Combat against another unit
     * @param unit the unit to engage combat into
     */
    protected void battle(Unit unit){
        // TODO: implement this method
    }

    public String describe(){
        return String.format("%s\t\tHealth: %s\t\tAttack: %d\t\tDefense: %d", name, health, attack_pts, defense_pts);
    }

    public boolean isDead(){
        return health.isDead();
    }

    public void visit(EmptyTile e) {
        e.accept(this);
    }

    public void visit(Wall w){
        w.accept(this);
    }

    public boolean isWall(){
        return false;
    }

    public boolean isEmpty(){
        return false;
    }

    public abstract void visit(Player p);
    public abstract void visit(Enemy e);

    public abstract void processStep();
    public abstract void onDeath();
}
