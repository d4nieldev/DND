package Business;

public class AbilityResource {
    private int pool;
    private int current;
    private int cost;

    public AbilityResource(int initial, int pool, int cost){
        this.pool = pool;
        this.current = initial;
        this.cost = cost;
    }

    public void addToCurrent(int addition) {
        current += addition;
        if(current > pool)
            current = pool;
    }

    public void consume(){
        if(canCastAbility())
            current -= cost;
    }

    public void setPool(int pool){
        this.pool = pool;
    }

    public boolean canCastAbility(){
        return current >= cost;
    }

    public String toString(){
        return current + "/" + pool;
    }
}
