package Business;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class Board {
    private List<Tile> tiles;
    private int maxCol;

    public Board(Tile[][] tiles){
        this.maxCol = tiles[0].length;
        this.tiles = new ArrayList<>();
        for (int y = 0; y < tiles.length; y++) {
            for (int x = 0; x < tiles[0].length; x++) {
                Tile tile = tiles[y][x];
                tile.initialize(new Position(x, y));
                this.tiles.add(tile);
            }
        }
    }

    public Tile get(int x, int y){
        Position toFind = new Position(x, y);
        for(Tile tile : tiles)
            if(tile.position.equals(toFind))
                return tile;

        throw new NoSuchElementException("No such tile with the position (" + x + ", " + y + ")");
    }

    public void remove(Tile e){
        tiles.remove(e);
        Position p = e.position;
        tiles.add(e);
    }

    public String toString(){
        tiles = tiles.stream().sorted().collect(Collectors.toList());
        String output = "";
        for (int i = 0; i < tiles.size(); i++){
            if (i != 0 && i % maxCol == 0)
                output += "\n";
            output += tiles.get(i).getTile();
        }
        return output;
    }
}
