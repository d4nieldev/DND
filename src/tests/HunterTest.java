package tests;

import Business.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class HunterTest {
    Player testHunter;
    int seed = 7;

    @BeforeEach
    void setUp() {
        testHunter = new Hunter("Test Hunter", 100, 10000, 1, 5);
        testHunter.initialize(new Position(0,0));
        testHunter.setRandomSeed(seed);
    }

    // Position tests after all kinds of interactions
    @Test
    void testPositionAfterInteractionWithEmpty(){
        Position emptyTileOriginalPosition = new Position(1,0);
        Tile emptyTile = new EmptyTile();
        emptyTile.initialize(emptyTileOriginalPosition);

        testHunter.interact(emptyTile);
        Position testWarriorPositionAfterInteraction = new Position(testHunter.getX(), testHunter.getY());

        Assertions.assertEquals(testWarriorPositionAfterInteraction, emptyTileOriginalPosition);
    }

    @Test
    void testPositionAfterInteractionWithWall(){
        Position testWarriorPositionBeforeInteraction = new Position(testHunter.getX(), testHunter.getY());
        Tile wall = new Wall();
        wall.initialize(new Position(1,0));

        testHunter.interact(wall);
        Position testWarriorPositionAfterInteraction = new Position(testHunter.getX(), testHunter.getY());

        Assertions.assertEquals(testWarriorPositionAfterInteraction, testWarriorPositionBeforeInteraction);
    }

    @Test
    void testPositionAfterInteractionWithMonsterAndNotKilling(){
        Position testWarriorPositionBeforeInteraction = new Position(testHunter.getX(), testHunter.getY());
        Enemy monster = new Monster('t', "Test Monster", 100000, 0, 0, 0, 0);
        monster.initialize(new Position(1,0));
        monster.setRandomSeed(seed);

        testHunter.interact(monster);
        Position testWarriorPositionAfterInteraction = new Position(testHunter.getX(), testHunter.getY());

        Assertions.assertEquals(testWarriorPositionAfterInteraction, testWarriorPositionBeforeInteraction);
    }

    @Test
    void testPositionAfterInteractionWithTrapAndNotKilling(){
        Position testWarriorPositionBeforeInteraction = new Position(testHunter.getX(), testHunter.getY());
        Enemy trap = new Trap('t', "Test Trap", 100000, 0, 0, 0, 1, 10);
        trap.initialize(new Position(1,0));
        trap.setRandomSeed(seed);

        testHunter.interact(trap);
        Position testWarriorPositionAfterInteraction = new Position(testHunter.getX(), testHunter.getY());

        Assertions.assertEquals(testWarriorPositionAfterInteraction, testWarriorPositionBeforeInteraction);
    }

    @Test
    void testPositionAfterInteractionWithMonsterAndKilling(){
        Position monsterPositionBeforeInteraction = new Position(1,0);
        Enemy monster = new Monster('t', "Test Monster", 1, 0, 0, 0, 0);
        monster.initialize(monsterPositionBeforeInteraction);
        monster.setRandomSeed(seed);

        testHunter.interact(monster);
        Position testWarriorPositionAfterInteraction = new Position(testHunter.getX(), testHunter.getY());

        Assertions.assertEquals(testWarriorPositionAfterInteraction, monsterPositionBeforeInteraction);
    }

    @Test
    void testPositionAfterInteractionWithTrapAndKilling(){
        Position trapPositionBeforeInteraction = new Position(1,0);
        Enemy trap = new Trap('t', "Test Trap", 1, 0, 0, 0, 1, 10);
        trap.initialize(trapPositionBeforeInteraction);
        trap.setRandomSeed(seed);

        testHunter.interact(trap);
        Position testWarriorPositionAfterInteraction = new Position(testHunter.getX(), testHunter.getY());

        Assertions.assertEquals(testWarriorPositionAfterInteraction, trapPositionBeforeInteraction);
    }

    @Test
    void testPositionAfterKillingEnemyWithAbility(){
        Position testWarriorPositionBeforeAbilityCast = new Position(testHunter.getX(), testHunter.getY());
        Enemy enemyInRange = new Monster('t', "Test Monster", 1, 0, 0, 0, 0);
        enemyInRange.initialize(new Position(1,0));
        enemyInRange.setRandomSeed(seed);

        List<Enemy> enemyList = new ArrayList<>();
        enemyList.add(enemyInRange);

        testHunter.abilityCast(enemyList);
        Position testWarriorPositionAfterAbilityCast = new Position(testHunter.getX(), testHunter.getY());

        Assertions.assertEquals(testWarriorPositionAfterAbilityCast, testWarriorPositionBeforeAbilityCast);
    }
    // Experience gain tests
    @Test
    void testGainExperienceFromKillingMonsterWithRegularAttack(){
        Enemy monster = new Monster('t', "Test Monster", 1, 0, 0, 49, 0);
        monster.initialize(new Position(1, 0));
        monster.setRandomSeed(seed);

        int warriorExperienceBeforeKilling = testHunter.getExperience();
        testHunter.interact(monster);
        int warriorExperienceAfterKilling = testHunter.getExperience();

        Assertions.assertEquals(warriorExperienceAfterKilling, warriorExperienceBeforeKilling + 49);
    }

    @Test
    void testGainExperienceFromKillingMonsterWithAbilityAttack(){
        Enemy monster = new Monster('t', "Test Monster", 1, 0, 0, 49, 0);
        monster.initialize(new Position(1, 0));
        monster.setRandomSeed(seed);

        List<Enemy> enemyList = new ArrayList<>();
        enemyList.add(monster);

        int warriorExperienceBeforeKilling = testHunter.getExperience();
        testHunter.abilityCast(enemyList);
        int warriorExperienceAfterKilling = testHunter.getExperience();

        Assertions.assertEquals(warriorExperienceAfterKilling, warriorExperienceBeforeKilling + 49);
    }

    @Test
    void testNotGainingExperienceFromAttackingMonsterWithRegularAttack(){
        Enemy monster = new Monster('t', "Test Monster", 100000, 0, 0, 49, 0);
        monster.initialize(new Position(1, 0));
        monster.setRandomSeed(seed);

        int warriorExperienceBeforeAttacking = testHunter.getExperience();
        testHunter.interact(monster);
        int warriorExperienceAfterAttacking = testHunter.getExperience();

        Assertions.assertEquals(warriorExperienceAfterAttacking, warriorExperienceBeforeAttacking);
    }

    @Test
    void testNotGainingExperienceFromAttackingMonsterWithAbilityAttack(){
        Enemy monster = new Monster('t', "Test Monster", 100000, 0, 0, 49, 0);
        monster.initialize(new Position(1, 0));
        monster.setRandomSeed(seed);

        List<Enemy> enemyList = new ArrayList<>();
        enemyList.add(monster);

        int warriorExperienceBeforeAttacking = testHunter.getExperience();
        testHunter.abilityCast(enemyList);
        int warriorExperienceAfterAttacking = testHunter.getExperience();

        Assertions.assertEquals(warriorExperienceAfterAttacking, warriorExperienceBeforeAttacking);
    }

    // Ability tests
    @Test
    void testAbilityHittingEnemyInRange(){
        Enemy enemyInRange = new Monster('t', "Test Monster", 1, 0, 0, 49, 0);
        enemyInRange.initialize(new Position(3, 0));
        enemyInRange.setRandomSeed(seed);

        Enemy enemyNotInRange = new Monster('t', "Test Monster", 1, 0, 0, 49, 0);
        enemyNotInRange.initialize(new Position(10, 0));
        enemyNotInRange.setRandomSeed(seed);

        List<Enemy> enemyList = new ArrayList<>();
        enemyList.add(enemyInRange);
        enemyList.add(enemyNotInRange);

        testHunter.abilityCast(enemyList);

        Assertions.assertTrue(enemyInRange.isDead());
        Assertions.assertFalse(enemyNotInRange.isDead());
    }

    @Test
    void testAbilityCastWithInsufficientResourcesAndDoesNothing(){
        Enemy enemyInRange = new Monster('t', "Test Monster", 100000, 50, 0, 49, 0);
        enemyInRange.initialize(new Position(1, 0));
        enemyInRange.setRandomSeed(seed);

        List<Enemy> enemyList = new ArrayList<>();
        enemyList.add(enemyInRange);

        // should hit enemy 10 times and use 10 arrows
        for (int i = 0; i < 10; i++)
            testHunter.abilityCast(enemyList);

        int enemyHealthBeforeSecondAbilityCast = enemyInRange.getHealth().getAmount();
        int cooldownBeforeSecondAbilityCast = testHunter.getAbility().getResourceCurrent();
        testHunter.abilityCast(enemyList);
        int enemyHealthAfterSecondAbilityCast = enemyInRange.getHealth().getAmount();
        int cooldownAfterSecondAbilityCast = testHunter.getAbility().getResourceCurrent();

        // cooldown should stay the same
        Assertions.assertEquals(cooldownAfterSecondAbilityCast, cooldownBeforeSecondAbilityCast);

        // enemy health should stay the same
        Assertions.assertEquals(enemyHealthAfterSecondAbilityCast, enemyHealthBeforeSecondAbilityCast);
    }

    // On game tick tests
    @Test
    void test2AbilityCastsWithRegeneratingBetween(){
        Enemy enemyInRange = new Monster('t', "Test Monster", 500000, 50, 0, 49, 0);
        enemyInRange.initialize(new Position(1, 0));
        enemyInRange.setRandomSeed(seed);

        List<Enemy> enemyList = new ArrayList<>();
        enemyList.add(enemyInRange);

        // should hit enemy 10 times and use 10 arrows
        for (int i = 0; i < 10; i++)
            testHunter.abilityCast(enemyList);

        // regenerate 1 arrow (because level 1)
        for (int i = 0; i <= 10; i++)
            testHunter.onGameTick();

        int enemyHealthBeforeSecondAbilityCast = enemyInRange.getHealth().getAmount();
        testHunter.abilityCast(enemyList);
        int enemyHealthAfterSecondAbilityCast = enemyInRange.getHealth().getAmount();
        int cooldownAfterSecondAbilityCast = testHunter.getAbility().getResourceCurrent();

        // cooldown should be reduced back to 0
        Assertions.assertEquals(cooldownAfterSecondAbilityCast, 0);

        // enemy health should be damaged
        Assertions.assertTrue(enemyHealthAfterSecondAbilityCast < enemyHealthBeforeSecondAbilityCast);
    }

    // Level up tests
    @Test
    void testLevelUpStatsAsExpected(){
        Enemy enemyInRange = new Monster('t', "Test Monster", 1, 50, 0, 70, 0);
        enemyInRange.initialize(new Position(1, 0));
        enemyInRange.setRandomSeed(seed);

        enemyInRange.interact(testHunter); // damage warrior

        List<Enemy> enemyList = new ArrayList<>();
        enemyList.add(enemyInRange);

        // basic level up
        int experienceBeforeLevelingUp = testHunter.getExperience();
        int levelBeforeLevelingUp = testHunter.getLevel();
        int healthPoolBeforeLevelingUp = testHunter.getHealth().getPool();
        int currentHealthBeforeLevelingUp = testHunter.getHealth().getAmount();
        int attackPtsBeforeLevelingUp = testHunter.getAttack_pts();
        int defensePtsBeforeLevelingUp = testHunter.getDefense_pts();

        // hunter specific
        int arrowsBeforeLevelingUp = testHunter.getAbility().getResourceCurrent();

        testHunter.abilityCast(enemyList); // should level up from this and empty cooldown

        int experienceAfterLevelingUp = testHunter.getExperience();
        int levelAfterLevelingUp = testHunter.getLevel();
        int healthPoolAfterLevelingUp = testHunter.getHealth().getPool();
        int currentHealthAfterLevelingUp = testHunter.getHealth().getAmount();
        int attackPtsAfterLevelingUp = testHunter.getAttack_pts();
        int defensePtsAfterLevelingUp = testHunter.getDefense_pts();

        // hunter specific
        int arrowsAfterLevelingUp = testHunter.getAbility().getResourceCurrent();

        Assertions.assertEquals(levelAfterLevelingUp, 2);
        Assertions.assertEquals(experienceAfterLevelingUp, experienceBeforeLevelingUp + 20);
        Assertions.assertEquals(healthPoolAfterLevelingUp, healthPoolBeforeLevelingUp + 10 * levelAfterLevelingUp);
        Assertions.assertEquals(currentHealthAfterLevelingUp, healthPoolAfterLevelingUp);
        Assertions.assertEquals(attackPtsAfterLevelingUp, attackPtsBeforeLevelingUp + 4 * levelAfterLevelingUp + 2 * levelAfterLevelingUp);
        Assertions.assertEquals(defensePtsAfterLevelingUp, defensePtsBeforeLevelingUp + levelAfterLevelingUp + levelAfterLevelingUp);
        Assertions.assertEquals(arrowsAfterLevelingUp, arrowsBeforeLevelingUp - 1 + 10 * levelAfterLevelingUp);
    }

    @Test
    void testLevelUpTwiceStatsAsExpected(){
        Enemy enemyInRange = new Monster('t', "Test Monster", 1, 50, 0, 250, 0);
        enemyInRange.initialize(new Position(1, 0));
        enemyInRange.setRandomSeed(seed);

        enemyInRange.interact(testHunter); // damage warrior

        List<Enemy> enemyList = new ArrayList<>();
        enemyList.add(enemyInRange);

        // warrior unique bonuses
        int cooldownBeforeLevelingUp = testHunter.getAbility().getResourceCurrent();

        testHunter.abilityCast(enemyList); // should level up TWICE from this and empty cooldown

        int experienceAfterLevelingUp = testHunter.getExperience();
        int levelAfterLevelingUp = testHunter.getLevel();

        Assertions.assertEquals(levelAfterLevelingUp, 3);
        Assertions.assertEquals(experienceAfterLevelingUp, 100);

    }
}