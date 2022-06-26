package Business;

public class Wall extends Tile{

    public Wall() {
        super('#');
        // TODO: complete this constructor
    }

    public void accept(Unit unit) {
        unit.visit(this);
    }
}
