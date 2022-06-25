package Business;

public interface Visitor {
    void visit(Player player);
    void visit(Enemy enemy);
    void visit(EmptyTile emptyTile);
    void visit(Wall wall);
}
