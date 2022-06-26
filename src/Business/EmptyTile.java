package Business;

public class EmptyTile extends Tile {

    public EmptyTile() {
        super('.');
    }

    public void accept(Unit unit) {
        unit.visit(this);
    }
}
