package Business;

public class EmptyTile extends Tile {

    public EmptyTile() {
        super('.');
    }

    @Override
    public void accept(Unit unit) {
        Position temp = unit.position;
        unit.position = this.position;
        this.position = temp;
    }

    @Override
    public boolean isEnemy() {
        return false;
    }

    @Override
    public boolean isPlayer() {
        return false;
    }

    @Override
    public boolean isWall() {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return true;
    }
}
