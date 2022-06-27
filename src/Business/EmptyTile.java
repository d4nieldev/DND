package Business;

public class EmptyTile extends Tile {

    public EmptyTile() {
        super('.');
    }

    @Override
    public void accept(Unit unit) {
        unit.visit(this);
    }
}
