package tests;

import Business.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TrapTest {
    Trap testTrap;
    int seed = 7;

    @BeforeEach
    void setUp() {
        testTrap = new Trap('t', "Trap Tile", 1, 50, 0, 100, 2, 5);
        testTrap.initialize(new Position(0, 0));
        testTrap.setRandomSeed(seed);
    }

    // Process step tests
    @Test
    void testPositionAfterProcessStep(){
        Position positionBeforeStep = new Position(testTrap.getX(), testTrap.getY());

        Player testWarrior = new Warrior("Test Warrior", 100, 0, 0, 3);
        testWarrior.initialize(new Position(5,0));
        testWarrior.setRandomSeed(seed);

        testTrap.processStep(testWarrior);

        Position positionAfterStep = new Position(testTrap.getX(), testTrap.getY());

        Assertions.assertEquals(positionBeforeStep, positionAfterStep);
    }

    @Test
    void testAttackPlayerInRangeProcessStep(){
        Player testWarrior = new Warrior("Test Warrior", 100, 0, 0, 3);
        testWarrior.initialize(new Position(1,1)); // in range ~1.41 < 2
        testWarrior.setRandomSeed(seed);

        int playerHealthBeforeDamageTaken = testWarrior.getHealth().getAmount();

        testTrap.processStep(testWarrior);

        int playerHealthAfterDamageTaken = testWarrior.getHealth().getAmount();

        Assertions.assertTrue(playerHealthAfterDamageTaken < playerHealthBeforeDamageTaken);
    }

    @Test
    void testAttackPlayerNotInRangeProcessStep(){
        Player testWarrior = new Warrior("Test Warrior", 100, 0, 0, 3);
        testWarrior.initialize(new Position(5,0));
        testWarrior.setRandomSeed(seed);

        int playerHealthBeforeProcessStep = testWarrior.getHealth().getAmount();

        testTrap.processStep(testWarrior);

        int playerHealthAfterProcessStep = testWarrior.getHealth().getAmount();

        Assertions.assertEquals(playerHealthAfterProcessStep, playerHealthBeforeProcessStep);
    }

    @Test
    void testTurnsInvisibleProcessStep(){
        Player testWarrior = new Warrior("Test Warrior", 100, 0, 0, 3);
        testWarrior.initialize(new Position(5,0));
        testWarrior.setRandomSeed(seed);

        testTrap.processStep(testWarrior);
        testTrap.processStep(testWarrior);
        testTrap.processStep(testWarrior);

        Assertions.assertEquals(testTrap.getTile(), '.');
    }

    @Test
    void testTurnsInVisibleAndThenVisibleProcessStep(){
        Player testWarrior = new Warrior("Test Warrior", 100, 0, 0, 3);
        testWarrior.initialize(new Position(5,0));
        testWarrior.setRandomSeed(seed);

        testTrap.processStep(testWarrior); // V
        testTrap.processStep(testWarrior); // V
        testTrap.processStep(testWarrior); // X
        testTrap.processStep(testWarrior); // X
        testTrap.processStep(testWarrior); // X
        testTrap.processStep(testWarrior); // X
        testTrap.processStep(testWarrior); // X
        testTrap.processStep(testWarrior); // V

        Assertions.assertEquals(testTrap.getTile(), 't');
    }
}