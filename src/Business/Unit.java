package Business;

import java.util.Random;

public abstract class Unit extends Tile implements Visitor {
    protected String name;
    protected Health health;
    protected int attack_pts;
    protected int defense_pts;
    protected MessageCallback messageCallback;
    protected MoveCallback moveCallback;
    protected Random generator;

    public Unit(char tile, String name, int health_pool, int attack_pts, int defense_pts)
    {
        super(tile);
        this.name = name;
        this.health = new Health(health_pool);
        this.attack_pts = attack_pts;
        this.defense_pts = defense_pts;
        this.generator = new Random();
        this.messageCallback = message -> {};
    }

    public void setMessageCallback(MessageCallback messageCallback){
        this.messageCallback = messageCallback;
    }

    public void setInteractCallback(MoveCallback moveCallback){
        this.moveCallback = moveCallback;
    }

    public void setRandomSeed(int seed){
        this.generator = new Random(seed);
    }

    public String getName(){
        return this.name;
    }

    public Health getHealth(){
        return this.health;
    }

    protected int attack(){
        int attackRoll = generator.nextInt(attack_pts + 1);
        messageCallback.send(getName() + " rolled " + attackRoll + " attack points.");
        return attackRoll;
    }

    protected int defend(){
        int defenseRoll = generator.nextInt(defense_pts + 1);
        messageCallback.send(getName() + " rolled " + defenseRoll + " defense points.");
        return defenseRoll;
    }

    protected void battle(Unit unit){
        messageCallback.send(getName() + " engaged in combat with " + unit.getName() + ".");
        messageCallback.send(describe());
        messageCallback.send(unit.describe());

        int attackRoll = attack();
        int defenseRoll = unit.defend();

        unit.dealPureDamage(getName(), attackRoll - defenseRoll, false);
    }

    protected void dealPureDamage(String attackerName, int penetration, boolean abilityDamage){
        if(penetration > 0)
            health.reduceHealth(penetration);
        else
            penetration = 0;

        messageCallback.send(String.format("%s dealt %d%s damage to %s.", attackerName, penetration, abilityDamage ? " ability" : "", getName()));
    }

    public void interact(Tile tile){
        tile.accept(this);
    }

    public void interact(int action){
        moveCallback.interact(action);
    }

    public String describe(){
        return String.format("%s\t\tHealth: %s\t\tAttack: %d\t\tDefense: %d", name, health, attack_pts, defense_pts);
    }

    public boolean isDead(){
        return health.isDead();
    }

    public void visit(EmptyTile emptyTile){
        Position temp = position;
        position = emptyTile.position;
        emptyTile.position = temp;
    }

    public void visit(Wall wall) { }

    public abstract void visit(Player player);
    public abstract void visit(Enemy enemy);
    public abstract void accept(Unit unit);
}
