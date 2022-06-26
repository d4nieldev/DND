package Business;

public class Position implements Comparable<Position> {
    private int x;
    private int y;

    public Position(int x, int y){
        this.x = x;
        this.y = y;
    }

    public double distance(Position pos){
        return Math.sqrt((x - pos.x)*(x - pos.x) + (y - pos.y)*(y- pos.y));
    }

    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if(o instanceof Position)
            return ((Position) o).x == x && ((Position) o).y == y;
        return false;
    }

    @Override
    public int compareTo(Position o) {
        if (this.y != o.y)
            return this.y - o.y;
        return this.x - o.x;
    }

    public String toString(){
        return "(" + x + ", " + y + ")";
    }
}
