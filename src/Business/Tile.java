package Business;

public abstract class Tile implements Visited, Comparable<Tile> {
    protected char tile;
    protected Position position;

    public Tile(char tile){
        this.tile = tile;
    }

    @Override
    public int compareTo(Tile o){
        return position.compareTo(o.position);
    }

    public void initialize(Position position) {
        this.position = position;
    }

    public char getTile(){
        return tile;
    }

    public int getX(){
        return position.getX();
    }

    public int getY(){
        return position.getY();
    }

    public abstract void accept(Unit unit);
}
