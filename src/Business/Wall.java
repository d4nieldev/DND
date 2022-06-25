package Business;

public class Wall extends Tile{

    public Wall() {
        super('#');
        // TODO: complete this constructor
    }

    @Override
    public void accept(Unit unit) {

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
        return true;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
