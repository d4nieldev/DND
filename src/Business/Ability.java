package Business;

public class Ability {
    private String name;
    private String resourceName;
    private int resourcePool;
    private int resourceCurrent;
    private int resourceCost;

    public Ability(int initial, int resourcePool, int resourceCost, String name, String resourceName){
        this.resourcePool = resourcePool;
        this.resourceCurrent = initial;
        this.resourceCost = resourceCost;
        this.name = name;
        this.resourceName = resourceName;
    }

    public void addToCurrent(int addition) {
        resourceCurrent += addition;
        if(resourceCurrent > resourcePool)
            resourceCurrent = resourcePool;
    }

    public String getName(){
        return this.name;
    }

    public String getResourceName() {
        return this.resourceName;
    }

    public void consume(){
        if(canCastAbility())
            resourceCurrent -= resourceCost;
    }

    public void setResourcePool(int resourcePool){
        this.resourcePool = resourcePool;
    }

    public boolean canCastAbility(){
        return resourceCurrent >= resourceCost;
    }
}
