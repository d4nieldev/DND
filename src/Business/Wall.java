package Business;

public class Wall extends Tile{

    public Wall() {
        super('#');
    }

    public void accept(Unit unit) {
        unit.visit(this);
    }
}
