package tests;

import Business.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {
    Board board;

    private Monster generateMonster(List<Enemy> enemyList){
        Monster m = new Monster('m', "m", 1, 1,1 ,1 ,1);
        enemyList.add(m);
        return m;
    }

    private Trap generateTrap(List<Enemy> enemyList){
        Trap t = new Trap('t', "t", 1, 1, 1, 1, 1, 1);
        enemyList.add(t);
        return t;
    }


    @BeforeEach
    void setUp() {
        Player player = new Warrior("Warrior", 1, 1, 1, 1);
        List<Enemy> l = new ArrayList<>();
        Tile[][] tiles = {
                {new Wall(), new Wall(),      new Wall(),         new Wall(),         new Wall(),      new Wall(),         new Wall(),      new Wall()},
                {new Wall(), new EmptyTile(), new EmptyTile(),    new Wall(),         new Wall(),      new Wall(),         generateTrap(l), new Wall()},
                {new Wall(), new EmptyTile(), generateMonster(l), new Wall(),         new Wall(),      new Wall(),         new Wall(),      new Wall()},
                {new Wall(), new EmptyTile(), new Wall(),         new Wall(),         new Wall(),      generateMonster(l), new Wall(),      new Wall()},
                {new Wall(), player,          new EmptyTile(),    new EmptyTile(),    new Wall(),      new Wall(),         new Wall(),      new Wall()},
                {new Wall(), new EmptyTile(), new Wall(),         generateMonster(l), new Wall(),      new Wall(),         new Wall(),      new Wall()},
                {new Wall(), new EmptyTile(), new Wall(),         new Wall(),         new EmptyTile(), new Wall(),         new Wall(),      new Wall()},
                {new Wall(), new Wall(),      new Wall(),         new EmptyTile(),    new Wall(),      new Wall(),         new Wall(),      new Wall()},
        };

        for(int row = 0; row < tiles.length; row++)
            for(int col = 0; col < tiles[0].length; col++)
                tiles[row][col].initialize(new Position(col, row));

        board = new Board(tiles, l);
    }

    @Test
    void testGet() {
        Tile tile = board.get(1,4);

        Assertions.assertTrue(tile instanceof Player);
    }

    @Test
    void remove() {
        Enemy enemyToRemove = (Enemy)board.get(3,5);
        board.remove(enemyToRemove);

        Assertions.assertTrue(board.get(3,5) instanceof EmptyTile);
    }

    @Test
    void testToString() {
        String expectedBoardOutput = """
                ########
                #..###t#
                #.m#####
                #.###m##
                #@..####
                #.#m####
                #.##.###
                ###.####""";

        Assertions.assertEquals(expectedBoardOutput, board.toString());
    }
}