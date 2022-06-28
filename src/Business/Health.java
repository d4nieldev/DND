package Business;

public class Health {
    private int pool;
    private int amount;

    public Health(int pool){
        this.pool = pool;
        this.amount = pool;
    }

    public void reduceHealth(int healthToReduce){
        amount -= healthToReduce;
    }

    public void addHealth(int healthToAdd){
        amount += healthToAdd;
        if(amount > pool)
            amount = pool;
    }

    public void fillHealth(){
        addHealth(pool);
    }
    public void addToPool(int poolAddition) {
        this.pool += poolAddition;
    }

    public boolean isDead(){
        return this.amount <= 0;
    }

    public String toString(){
        return String.format("%d/%d", amount, pool);
    }

    public int getPool(){
        return this.pool;
    }

    public int getAmount() {
        return this.amount;
    }
}
