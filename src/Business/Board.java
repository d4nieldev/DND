package Business;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class Board {
    private List<Tile> tiles;
    private final int maxCol;

    public Board(Tile[][] tiles){
        this.maxCol = tiles[0].length;
        this.tiles = new ArrayList<>();

        for (Tile[] tile : tiles)
            this.tiles.addAll(Arrays.asList(tile).subList(0, tiles[0].length));
    }

    public Tile get(int x, int y){
        Position toFind = new Position(x, y);
        for(Tile tile : tiles)
            if(tile.position.equals(toFind))
                return tile;

        throw new NoSuchElementException("No such tile with the position (" + x + ", " + y + ")");
    }

    public void remove(Tile e){
        EmptyTile emptyTile = new EmptyTile();
        emptyTile.initialize(e.position);
        tiles.set(tiles.indexOf(e), emptyTile);
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
