package Business;

import java.util.List;

public class Ability {
    private String name;
    private String resourceName;
    private int resourcePool;
    private int resourceCurrent;
    private int resourceCost;
    private AbilityCastCallback abilityCastCallback;

    public Ability(int initial, int resourcePool, int resourceCost, String name, String resourceName){
        this.resourcePool = resourcePool;
        this.resourceCurrent = initial;
        this.resourceCost = resourceCost;
        this.name = name;
        this.resourceName = resourceName;
    }

    public void setAbilityCast(AbilityCastCallback abilityCastCallback){
        this.abilityCastCallback = abilityCastCallback;
    }

    public void addToCurrent(int addition) {
        resourceCurrent += addition;
        if(resourceCurrent > resourcePool)
            resourceCurrent = resourcePool;
    }

    public void fillCurrent(){
        addToCurrent(resourcePool);
    }

    public String getName(){
        return this.name;
    }

    public String getResourceName() {
        return this.resourceName;
    }


    public void castAbility(List<Enemy> enemyList){
        if(canCastAbility()) {
            resourceCurrent -= resourceCost;
            abilityCastCallback.castAbility(enemyList);
        }
    }

    public int getResourcePool(){
        return this.resourcePool;
    }

    public void setResourcePool(int resourcePool){
        this.resourcePool = resourcePool;
    }

    public boolean canCastAbility(){
        return resourceCurrent >= resourceCost;
    }

    public String toString(){
        return String.format("%d/%d", resourceCurrent, resourcePool);
    }
}
