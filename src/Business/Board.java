package Business;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class Board {
    private List<Tile> tiles;
    private final int maxCol;
    private List<Enemy> enemyList;

    public Board(Tile[][] tiles, List<Enemy> enemyList){
        this.maxCol = tiles[0].length;
        this.tiles = new ArrayList<>();
        for (Tile[] tile : tiles)
            this.tiles.addAll(Arrays.asList(tile).subList(0, tiles[0].length));

        this.enemyList = enemyList;
        for(Enemy enemy : enemyList) {
            enemy.setDeathCallback(() -> this.tiles.get(remove(enemy)));

            enemy.setInteractCallback(action -> {
                switch (action) {
                    case (0) ->
                            // interact up
                            enemy.interact(get(enemy.getX(), enemy.getY() - 1));
                    case (1) ->
                            // interact down
                            enemy.interact(get(enemy.getX(), enemy.getY() + 1));
                    case (2) ->
                            // interact left
                            enemy.interact(get(enemy.getX() - 1, enemy.getY()));
                    case (3) ->
                            // move right
                            enemy.interact(get(enemy.getX() + 1, enemy.getY()));
                }
            });
        }
    }

    public List<Enemy> getEnemyList(){
        return this.enemyList;
    }

    public Tile get(int x, int y){
        Position toFind = new Position(x, y);
        for(Tile tile : tiles)
            if(tile.position.equals(toFind))
                return tile;

        throw new NoSuchElementException("No such tile with the position (" + x + ", " + y + ")");
    }

    public int remove(Enemy e){
        EmptyTile emptyTile = new EmptyTile();
        emptyTile.initialize(e.position);

        int enemyPosition = tiles.indexOf(e);
        tiles.set(enemyPosition, emptyTile);

        enemyList.remove(e);
        return enemyPosition;
    }

    public String toString(){
        tiles = tiles.stream().sorted().collect(Collectors.toList());
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < tiles.size(); i++){
            if (i != 0 && i % maxCol == 0)
                output.append("\n");
            output.append(tiles.get(i).getTile());
        }
        return output.toString();
    }
}
