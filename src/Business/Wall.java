package Business;

public class Wall extends Tile{

    public Wall() {
        super('#');
    }

    @Override
    public void accept(Unit unit) {
        unit.visit(this);
    }
}
