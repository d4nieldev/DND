package tests;

import Business.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class MonsterTest {
    Monster testMonster;
    Player playerInRange;
    int seed = 7;

    @BeforeEach
    void setUp() {
        testMonster = new Monster('m', "Monster Tile", 100, 50, 0, 100, 2);
        playerInRange = new Mage("Mage in range", 100, 1, 0, 100, 20, 15, 5, 2);

        testMonster.setRandomSeed(seed);
        playerInRange.setRandomSeed(seed);
    }

    // Process step tests
    @Test
    void testPositionPlayerInRangeAfterProcessStep(){
        Tile[][] tiles = {
                {new Wall(), new Wall(),      new Wall(),      new Wall(),       new Wall()},
                {new Wall(), testMonster,     new EmptyTile(), new EmptyTile(),  new Wall()},
                {new Wall(), new EmptyTile(), playerInRange, new EmptyTile(),  new Wall()},
                {new Wall(), new EmptyTile(), new EmptyTile(), new EmptyTile(),  new Wall()},
                {new Wall(), new Wall(),      new Wall(),      new Wall(),       new Wall()}
        };

        for(int row = 0; row < tiles.length; row++)
            for(int col = 0; col < tiles[0].length; col++)
                tiles[row][col].initialize(new Position(col, row));

        List<Enemy> enemyList = new ArrayList<>();
        enemyList.add(testMonster);

        new Board(tiles, enemyList);
        /*
        board will look like this
        #####
        #m..#
        #...#
        #..@#
        #####
        */

        Position playerPosition = new Position(playerInRange.getX(), playerInRange.getY());
        Position positionBeforeStep = new Position(testMonster.getX(), testMonster.getY());

        testMonster.processStep(playerInRange);

        Position positionAfterStep = new Position(testMonster.getX(), testMonster.getY());

        Assertions.assertTrue(positionBeforeStep.distance(playerPosition) > positionAfterStep.distance(playerPosition));
    }

    @Test
    void testAttackPlayerInRangeProcessStep(){
        Tile[][] tiles = {
                {new Wall(), new Wall(),      new Wall(),      new Wall(),       new Wall()},
                {new Wall(), testMonster,     new EmptyTile(), new EmptyTile(),  new Wall()},
                {new Wall(), playerInRange,   new EmptyTile(), new EmptyTile(),  new Wall()},
                {new Wall(), new EmptyTile(), new EmptyTile(), new EmptyTile(),  new Wall()},
                {new Wall(), new Wall(),      new Wall(),      new Wall(),       new Wall()}
        };

        for(int row = 0; row < tiles.length; row++)
            for(int col = 0; col < tiles[0].length; col++)
                tiles[row][col].initialize(new Position(col, row));

        List<Enemy> enemyList = new ArrayList<>();
        enemyList.add(testMonster);

        new Board(tiles, enemyList);
        /*
        board will look like this
        #####
        #m..#
        #...#
        #..@#
        #####
        */

        int playerHealthBeforeEnemyTurn = playerInRange.getHealth().getAmount();

        testMonster.processStep(playerInRange); // should attack the player

        int playerHealthAfterEnemyTurn = playerInRange.getHealth().getAmount();

        Assertions.assertTrue(playerHealthAfterEnemyTurn < playerHealthBeforeEnemyTurn);
    }
}