package tests;

import Business.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class RogueTest {
    Player testRogue;
    int seed = 7;

    @BeforeEach
    void setUp() {
        testRogue = new Rogue("Test Rogue", 10000, 10000, 1, 20);
        testRogue.initialize(new Position(0,0));
        testRogue.setRandomSeed(seed);
    }

    // Position tests after all kinds of interactions
    @Test
    void testPositionAfterInteractionWithEmpty(){
        Position emptyTileOriginalPosition = new Position(1,0);
        Tile emptyTile = new EmptyTile();
        emptyTile.initialize(emptyTileOriginalPosition);

        testRogue.interact(emptyTile);
        Position testWarriorPositionAfterInteraction = new Position(testRogue.getX(), testRogue.getY());

        Assertions.assertEquals(testWarriorPositionAfterInteraction, emptyTileOriginalPosition);
    }

    @Test
    void testPositionAfterInteractionWithWall(){
        Position testWarriorPositionBeforeInteraction = new Position(testRogue.getX(), testRogue.getY());
        Tile wall = new Wall();
        wall.initialize(new Position(1,0));

        testRogue.interact(wall);
        Position testWarriorPositionAfterInteraction = new Position(testRogue.getX(), testRogue.getY());

        Assertions.assertEquals(testWarriorPositionAfterInteraction, testWarriorPositionBeforeInteraction);
    }

    @Test
    void testPositionAfterInteractionWithMonsterAndNotKilling(){
        Position testWarriorPositionBeforeInteraction = new Position(testRogue.getX(), testRogue.getY());
        Enemy monster = new Monster('t', "Test Monster", 100000, 0, 0, 0, 0);
        monster.initialize(new Position(1,0));
        monster.setRandomSeed(seed);

        testRogue.interact(monster);
        Position testWarriorPositionAfterInteraction = new Position(testRogue.getX(), testRogue.getY());

        Assertions.assertEquals(testWarriorPositionAfterInteraction, testWarriorPositionBeforeInteraction);
    }

    @Test
    void testPositionAfterInteractionWithTrapAndNotKilling(){
        Position testWarriorPositionBeforeInteraction = new Position(testRogue.getX(), testRogue.getY());
        Enemy trap = new Trap('t', "Test Trap", 100000, 0, 0, 0, 1, 10);
        trap.initialize(new Position(1,0));
        trap.setRandomSeed(seed);

        testRogue.interact(trap);
        Position testWarriorPositionAfterInteraction = new Position(testRogue.getX(), testRogue.getY());

        Assertions.assertEquals(testWarriorPositionAfterInteraction, testWarriorPositionBeforeInteraction);
    }

    @Test
    void testPositionAfterInteractionWithMonsterAndKilling(){
        Position monsterPositionBeforeInteraction = new Position(1,0);
        Enemy monster = new Monster('t', "Test Monster", 1, 0, 0, 0, 0);
        monster.initialize(monsterPositionBeforeInteraction);
        monster.setRandomSeed(seed);

        testRogue.interact(monster);
        Position testWarriorPositionAfterInteraction = new Position(testRogue.getX(), testRogue.getY());

        Assertions.assertEquals(testWarriorPositionAfterInteraction, monsterPositionBeforeInteraction);
    }

    @Test
    void testPositionAfterInteractionWithTrapAndKilling(){
        Position trapPositionBeforeInteraction = new Position(1,0);
        Enemy trap = new Trap('t', "Test Trap", 1, 0, 0, 0, 1, 10);
        trap.initialize(trapPositionBeforeInteraction);
        trap.setRandomSeed(seed);

        testRogue.interact(trap);
        Position testWarriorPositionAfterInteraction = new Position(testRogue.getX(), testRogue.getY());

        Assertions.assertEquals(testWarriorPositionAfterInteraction, trapPositionBeforeInteraction);
    }

    @Test
    void testPositionAfterKillingEnemyWithAbility(){
        Position testWarriorPositionBeforeAbilityCast = new Position(testRogue.getX(), testRogue.getY());
        Enemy enemyInRange = new Monster('t', "Test Monster", 1, 0, 0, 0, 0);
        enemyInRange.initialize(new Position(1,0));
        enemyInRange.setRandomSeed(seed);

        List<Enemy> enemyList = new ArrayList<>();
        enemyList.add(enemyInRange);

        testRogue.abilityCast(enemyList);
        Position testWarriorPositionAfterAbilityCast = new Position(testRogue.getX(), testRogue.getY());

        Assertions.assertEquals(testWarriorPositionAfterAbilityCast, testWarriorPositionBeforeAbilityCast);
    }
    // Experience gain tests
    @Test
    void testGainExperienceFromKillingMonsterWithRegularAttack(){
        Enemy monster = new Monster('t', "Test Monster", 1, 0, 0, 49, 0);
        monster.initialize(new Position(1, 0));
        monster.setRandomSeed(seed);

        int warriorExperienceBeforeKilling = testRogue.getExperience();
        testRogue.interact(monster);
        int warriorExperienceAfterKilling = testRogue.getExperience();

        Assertions.assertEquals(warriorExperienceAfterKilling, warriorExperienceBeforeKilling + 49);
    }

    @Test
    void testGainExperienceFromKillingMonsterWithAbilityAttack(){
        Enemy monster = new Monster('t', "Test Monster", 1, 0, 0, 49, 0);
        monster.initialize(new Position(1, 0));
        monster.setRandomSeed(seed);

        List<Enemy> enemyList = new ArrayList<>();
        enemyList.add(monster);

        int warriorExperienceBeforeKilling = testRogue.getExperience();
        testRogue.abilityCast(enemyList);
        int warriorExperienceAfterKilling = testRogue.getExperience();

        Assertions.assertEquals(warriorExperienceAfterKilling, warriorExperienceBeforeKilling + 49);
    }

    @Test
    void testNotGainingExperienceFromAttackingMonsterWithRegularAttack(){
        Enemy monster = new Monster('t', "Test Monster", 100000, 0, 0, 49, 0);
        monster.initialize(new Position(1, 0));
        monster.setRandomSeed(seed);

        int warriorExperienceBeforeAttacking = testRogue.getExperience();
        testRogue.interact(monster);
        int warriorExperienceAfterAttacking = testRogue.getExperience();

        Assertions.assertEquals(warriorExperienceAfterAttacking, warriorExperienceBeforeAttacking);
    }

    @Test
    void testNotGainingExperienceFromAttackingMonsterWithAbilityAttack(){
        Enemy monster = new Monster('t', "Test Monster", 100000, 0, 0, 49, 0);
        monster.initialize(new Position(1, 0));
        monster.setRandomSeed(seed);

        List<Enemy> enemyList = new ArrayList<>();
        enemyList.add(monster);

        int warriorExperienceBeforeAttacking = testRogue.getExperience();
        testRogue.abilityCast(enemyList);
        int warriorExperienceAfterAttacking = testRogue.getExperience();

        Assertions.assertEquals(warriorExperienceAfterAttacking, warriorExperienceBeforeAttacking);
    }

    // Ability tests
    @Test
    void testAbilityHittingEnemiesInRange(){
        Enemy enemyInRange1 = new Monster('t', "Test Monster", 1, 0, 0, 25, 0);
        enemyInRange1.initialize(new Position(1, 0));
        enemyInRange1.setRandomSeed(seed);

        Enemy enemyInRange2 = new Monster('t', "Test Monster", 1, 0, 0, 24, 0);
        enemyInRange2.initialize(new Position(0, 1));
        enemyInRange2.setRandomSeed(seed);

        Enemy enemyNotInRange = new Monster('t', "Test Monster", 1, 0, 0, 49, 0);
        enemyNotInRange.initialize(new Position(10, 0));
        enemyNotInRange.setRandomSeed(seed);

        List<Enemy> enemyList = new ArrayList<>();
        enemyList.add(enemyInRange1);
        enemyList.add(enemyInRange2);
        enemyList.add(enemyNotInRange);

        testRogue.abilityCast(enemyList);

        Assertions.assertTrue(enemyInRange1.isDead());
        Assertions.assertTrue(enemyInRange2.isDead());
        Assertions.assertFalse(enemyNotInRange.isDead());
    }

    @Test
    void testAbilityCastWithInsufficientResourcesAndDoesNothing(){
        Enemy enemyInRange = new Monster('t', "Test Monster", Integer.MAX_VALUE, 50, 0, 49, 0);
        enemyInRange.initialize(new Position(1, 0));
        enemyInRange.setRandomSeed(seed);

        List<Enemy> enemyList = new ArrayList<>();
        enemyList.add(enemyInRange);

        testRogue.abilityCast(enemyList); // should hit enemy and use 20 energy
        testRogue.abilityCast(enemyList); // should hit enemy and use 20 energy
        testRogue.abilityCast(enemyList); // should hit enemy and use 20 energy
        testRogue.abilityCast(enemyList); // should hit enemy and use 20 energy
        testRogue.abilityCast(enemyList); // should hit enemy and use 20 energy

        testRogue.onGameTick(); // regenerate 10 energy

        int enemyHealthBeforeSecondAbilityCast = enemyInRange.getHealth().getAmount();
        int energyBeforeSecondAbilityCast = testRogue.getAbility().getResourceCurrent();
        testRogue.abilityCast(enemyList);
        int enemyHealthAfterSecondAbilityCast = enemyInRange.getHealth().getAmount();
        int energyAfterSecondAbilityCast = testRogue.getAbility().getResourceCurrent();

        // energy should stay the same
        Assertions.assertEquals(energyAfterSecondAbilityCast, energyBeforeSecondAbilityCast);

        // enemy health should stay the same
        Assertions.assertEquals(enemyHealthAfterSecondAbilityCast, enemyHealthBeforeSecondAbilityCast);
    }

    // On game tick tests
    @Test
    void test2AbilityCastsWithRegeneratingBetween(){
        Enemy enemyInRange = new Monster('t', "Test Monster", Integer.MAX_VALUE, 50, 0, 49, 0);
        enemyInRange.initialize(new Position(1, 0));
        enemyInRange.setRandomSeed(seed);

        List<Enemy> enemyList = new ArrayList<>();
        enemyList.add(enemyInRange);

        testRogue.abilityCast(enemyList); // should hit enemy and use 20 energy
        testRogue.abilityCast(enemyList); // should hit enemy and use 20 energy
        testRogue.abilityCast(enemyList); // should hit enemy and use 20 energy
        testRogue.abilityCast(enemyList); // should hit enemy and use 20 energy
        testRogue.abilityCast(enemyList); // should hit enemy and use 20 energy

        testRogue.onGameTick(); // regenerate 10 energy
        testRogue.onGameTick(); // regenerate 10 energy

        int enemyHealthBeforeSecondAbilityCast = enemyInRange.getHealth().getAmount();
        testRogue.abilityCast(enemyList);
        int enemyHealthAfterSecondAbilityCast = enemyInRange.getHealth().getAmount();
        int energyAfterSecondAbilityCast = testRogue.getAbility().getResourceCurrent();

        // energy should stay the same
        Assertions.assertEquals(energyAfterSecondAbilityCast, 0);

        // enemy health should stay the same
        Assertions.assertTrue(enemyHealthAfterSecondAbilityCast < enemyHealthBeforeSecondAbilityCast);
    }

    // Level up tests
    @Test
    void testLevelUpStatsAsExpected(){
        Enemy enemyInRange = new Monster('t', "Test Monster", 1, 50, 0, 70, 0);
        enemyInRange.initialize(new Position(1, 0));
        enemyInRange.setRandomSeed(seed);

        enemyInRange.interact(testRogue); // damage warrior

        List<Enemy> enemyList = new ArrayList<>();
        enemyList.add(enemyInRange);

        // basic level up
        int experienceBeforeLevelingUp = testRogue.getExperience();
        int levelBeforeLevelingUp = testRogue.getLevel();
        int healthPoolBeforeLevelingUp = testRogue.getHealth().getPool();
        int currentHealthBeforeLevelingUp = testRogue.getHealth().getAmount();
        int attackPtsBeforeLevelingUp = testRogue.getAttack_pts();
        int defensePtsBeforeLevelingUp = testRogue.getDefense_pts();

        testRogue.abilityCast(enemyList); // should level up from this and empty cooldown

        int experienceAfterLevelingUp = testRogue.getExperience();
        int levelAfterLevelingUp = testRogue.getLevel();
        int healthPoolAfterLevelingUp = testRogue.getHealth().getPool();
        int currentHealthAfterLevelingUp = testRogue.getHealth().getAmount();
        int attackPtsAfterLevelingUp = testRogue.getAttack_pts();
        int defensePtsAfterLevelingUp = testRogue.getDefense_pts();

        // warrior unique bonuses
        int energyAfterLevelingUp = testRogue.getAbility().getResourceCurrent();

        Assertions.assertEquals(levelAfterLevelingUp, 2);
        Assertions.assertEquals(experienceAfterLevelingUp, experienceBeforeLevelingUp + 20);
        Assertions.assertEquals(healthPoolAfterLevelingUp, healthPoolBeforeLevelingUp + 10 * levelAfterLevelingUp);
        Assertions.assertEquals(currentHealthAfterLevelingUp, healthPoolAfterLevelingUp);
        Assertions.assertEquals(attackPtsAfterLevelingUp, attackPtsBeforeLevelingUp + 4 * levelAfterLevelingUp + 3 * levelAfterLevelingUp);
        Assertions.assertEquals(defensePtsAfterLevelingUp, defensePtsBeforeLevelingUp + levelAfterLevelingUp);
        Assertions.assertEquals(energyAfterLevelingUp, 100);
    }

    @Test
    void testLevelUpTwiceStatsAsExpected(){
        Enemy enemyInRange = new Monster('t', "Test Monster", 1, 50, 0, 250, 0);
        enemyInRange.initialize(new Position(1, 0));
        enemyInRange.setRandomSeed(seed);

        enemyInRange.interact(testRogue); // damage warrior

        List<Enemy> enemyList = new ArrayList<>();
        enemyList.add(enemyInRange);

        // warrior unique bonuses
        int cooldownBeforeLevelingUp = testRogue.getAbility().getResourceCurrent();

        testRogue.abilityCast(enemyList); // should level up TWICE from this and empty cooldown

        int experienceAfterLevelingUp = testRogue.getExperience();
        int levelAfterLevelingUp = testRogue.getLevel();

        Assertions.assertEquals(levelAfterLevelingUp, 3);
        Assertions.assertEquals(experienceAfterLevelingUp, 100);

    }
}