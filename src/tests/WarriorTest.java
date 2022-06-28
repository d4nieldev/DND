package tests;

import Business.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class WarriorTest {
    Player testWarrior;
    int seed = 7;

    @BeforeEach
    void setUp() {
        testWarrior = new Warrior("Test Warrior", 10000, 10000, 1, 3);
        testWarrior.initialize(new Position(0,0));
        testWarrior.setRandomSeed(seed);
    }

    // Position tests after all kinds of interactions
    @Test
    void testPositionAfterInteractionWithEmpty(){
        Position emptyTileOriginalPosition = new Position(1,0);
        Tile emptyTile = new EmptyTile();
        emptyTile.initialize(emptyTileOriginalPosition);

        testWarrior.interact(emptyTile);
        Position testWarriorPositionAfterInteraction = new Position(testWarrior.getX(), testWarrior.getY());

        Assertions.assertEquals(testWarriorPositionAfterInteraction, emptyTileOriginalPosition);
    }

    @Test
    void testPositionAfterInteractionWithWall(){
        Position testWarriorPositionBeforeInteraction = new Position(testWarrior.getX(), testWarrior.getY());
        Tile wall = new Wall();
        wall.initialize(new Position(1,0));

        testWarrior.interact(wall);
        Position testWarriorPositionAfterInteraction = new Position(testWarrior.getX(), testWarrior.getY());

        Assertions.assertEquals(testWarriorPositionAfterInteraction, testWarriorPositionBeforeInteraction);
    }

    @Test
    void testPositionAfterInteractionWithMonsterAndNotKilling(){
        Position testWarriorPositionBeforeInteraction = new Position(testWarrior.getX(), testWarrior.getY());
        Enemy monster = new Monster('t', "Test Monster", 100000, 0, 0, 0, 0);
        monster.initialize(new Position(1,0));
        monster.setRandomSeed(seed);

        testWarrior.interact(monster);
        Position testWarriorPositionAfterInteraction = new Position(testWarrior.getX(), testWarrior.getY());

        Assertions.assertEquals(testWarriorPositionAfterInteraction, testWarriorPositionBeforeInteraction);
    }

    @Test
    void testPositionAfterInteractionWithTrapAndNotKilling(){
        Position testWarriorPositionBeforeInteraction = new Position(testWarrior.getX(), testWarrior.getY());
        Enemy trap = new Trap('t', "Test Trap", 100000, 0, 0, 0, 1, 10);
        trap.initialize(new Position(1,0));
        trap.setRandomSeed(seed);

        testWarrior.interact(trap);
        Position testWarriorPositionAfterInteraction = new Position(testWarrior.getX(), testWarrior.getY());

        Assertions.assertEquals(testWarriorPositionAfterInteraction, testWarriorPositionBeforeInteraction);
    }

    @Test
    void testPositionAfterInteractionWithMonsterAndKilling(){
        Position monsterPositionBeforeInteraction = new Position(1,0);
        Enemy monster = new Monster('t', "Test Monster", 1, 0, 0, 0, 0);
        monster.initialize(monsterPositionBeforeInteraction);
        monster.setRandomSeed(seed);

        testWarrior.interact(monster);
        Position testWarriorPositionAfterInteraction = new Position(testWarrior.getX(), testWarrior.getY());

        Assertions.assertEquals(testWarriorPositionAfterInteraction, monsterPositionBeforeInteraction);
    }

    @Test
    void testPositionAfterInteractionWithTrapAndKilling(){
        Position trapPositionBeforeInteraction = new Position(1,0);
        Enemy trap = new Trap('t', "Test Trap", 1, 0, 0, 0, 1, 10);
        trap.initialize(trapPositionBeforeInteraction);
        trap.setRandomSeed(seed);

        testWarrior.interact(trap);
        Position testWarriorPositionAfterInteraction = new Position(testWarrior.getX(), testWarrior.getY());

        Assertions.assertEquals(testWarriorPositionAfterInteraction, trapPositionBeforeInteraction);
    }

    @Test
    void testPositionAfterKillingEnemyWithAbility(){
        Position testWarriorPositionBeforeAbilityCast = new Position(testWarrior.getX(), testWarrior.getY());
        Enemy enemyInRange = new Monster('t', "Test Monster", 1, 0, 0, 0, 0);
        enemyInRange.initialize(new Position(1,0));
        enemyInRange.setRandomSeed(seed);

        List<Enemy> enemyList = new ArrayList<>();
        enemyList.add(enemyInRange);

        testWarrior.abilityCast(enemyList);
        Position testWarriorPositionAfterAbilityCast = new Position(testWarrior.getX(), testWarrior.getY());

        Assertions.assertEquals(testWarriorPositionAfterAbilityCast, testWarriorPositionBeforeAbilityCast);
    }
    // Experience gain tests
    @Test
    void testGainExperienceFromKillingMonsterWithRegularAttack(){
        Enemy monster = new Monster('t', "Test Monster", 1, 0, 0, 49, 0);
        monster.initialize(new Position(1, 0));
        monster.setRandomSeed(seed);

        int warriorExperienceBeforeKilling = testWarrior.getExperience();
        testWarrior.interact(monster);
        int warriorExperienceAfterKilling = testWarrior.getExperience();

        Assertions.assertEquals(warriorExperienceAfterKilling, warriorExperienceBeforeKilling + 49);
    }

    @Test
    void testGainExperienceFromKillingMonsterWithAbilityAttack(){
        Enemy monster = new Monster('t', "Test Monster", 1, 0, 0, 49, 0);
        monster.initialize(new Position(1, 0));
        monster.setRandomSeed(seed);

        List<Enemy> enemyList = new ArrayList<>();
        enemyList.add(monster);

        int warriorExperienceBeforeKilling = testWarrior.getExperience();
        testWarrior.abilityCast(enemyList);
        int warriorExperienceAfterKilling = testWarrior.getExperience();

        Assertions.assertEquals(warriorExperienceAfterKilling, warriorExperienceBeforeKilling + 49);
    }

    @Test
    void testNotGainingExperienceFromAttackingMonsterWithRegularAttack(){
        Enemy monster = new Monster('t', "Test Monster", 100000, 0, 0, 49, 0);
        monster.initialize(new Position(1, 0));
        monster.setRandomSeed(seed);

        int warriorExperienceBeforeAttacking = testWarrior.getExperience();
        testWarrior.interact(monster);
        int warriorExperienceAfterAttacking = testWarrior.getExperience();

        Assertions.assertEquals(warriorExperienceAfterAttacking, warriorExperienceBeforeAttacking);
    }

    @Test
    void testNotGainingExperienceFromAttackingMonsterWithAbilityAttack(){
        Enemy monster = new Monster('t', "Test Monster", 100000, 0, 0, 49, 0);
        monster.initialize(new Position(1, 0));
        monster.setRandomSeed(seed);

        List<Enemy> enemyList = new ArrayList<>();
        enemyList.add(monster);

        int warriorExperienceBeforeAttacking = testWarrior.getExperience();
        testWarrior.abilityCast(enemyList);
        int warriorExperienceAfterAttacking = testWarrior.getExperience();

        Assertions.assertEquals(warriorExperienceAfterAttacking, warriorExperienceBeforeAttacking);
    }

    // Ability tests
    @Test
    void testAbilityHittingEnemyInRange(){
        Enemy enemyInRange = new Monster('t', "Test Monster", 1, 0, 0, 49, 0);
        enemyInRange.initialize(new Position(1, 0));
        enemyInRange.setRandomSeed(seed);

        Enemy enemyNotInRange = new Monster('t', "Test Monster", 1, 0, 0, 49, 0);
        enemyNotInRange.initialize(new Position(10, 0));
        enemyNotInRange.setRandomSeed(seed);

        List<Enemy> enemyList = new ArrayList<>();
        enemyList.add(enemyInRange);
        enemyList.add(enemyNotInRange);

        testWarrior.abilityCast(enemyList);

        Assertions.assertTrue(enemyInRange.isDead());
        Assertions.assertFalse(enemyNotInRange.isDead());
    }

    @Test
    void testAbilityHittingEnemyInRangeAndHealing(){
        Enemy enemyInRange = new Monster('t', "Test Monster", 1, 50, 0, 49, 0);
        enemyInRange.initialize(new Position(1, 0));
        enemyInRange.setRandomSeed(seed);

        enemyInRange.interact(testWarrior); // should decrease warrior health by 9

        Enemy enemyNotInRange = new Monster('t', "Test Monster", 1, 0, 0, 49, 0);
        enemyNotInRange.initialize(new Position(10, 0));
        enemyNotInRange.setRandomSeed(seed);

        List<Enemy> enemyList = new ArrayList<>();
        enemyList.add(enemyInRange);
        enemyList.add(enemyNotInRange);

        testWarrior.abilityCast(enemyList);
        int warriorHealthAfterAbilityCast = testWarrior.getHealth().getAmount();

        Assertions.assertEquals(warriorHealthAfterAbilityCast, 10000);
    }

    @Test
    void testAbilityNotHittingEnemyInRangeHealing(){
        Enemy enemyNotInRange = new Monster('t', "Test Monster", 1, 20, 0, 49, 0);
        enemyNotInRange.initialize(new Position(10, 0));
        enemyNotInRange.setRandomSeed(seed);

        enemyNotInRange.interact(testWarrior); // should decrease warrior health by 9

        List<Enemy> enemyList = new ArrayList<>();
        enemyList.add(enemyNotInRange);

        testWarrior.abilityCast(enemyList);
        int warriorHealthAfterAbilityCast = testWarrior.getHealth().getAmount();

        Assertions.assertEquals(warriorHealthAfterAbilityCast, 10000);
    }

    @Test
    void testAbilityCastWithInsufficientResourcesAndDoesNothing(){
        Enemy enemyInRange = new Monster('t', "Test Monster", 100000, 50, 0, 49, 0);
        enemyInRange.initialize(new Position(1, 0));
        enemyInRange.setRandomSeed(seed);

        List<Enemy> enemyList = new ArrayList<>();
        enemyList.add(enemyInRange);

        testWarrior.abilityCast(enemyList); // should hit enemy for 1000 damage and empty cooldown
        testWarrior.onGameTick(); // regenerate 1 cooldown

        int enemyHealthBeforeSecondAbilityCast = enemyInRange.getHealth().getAmount();
        int cooldownBeforeSecondAbilityCast = testWarrior.getAbility().getResourceCurrent();
        testWarrior.abilityCast(enemyList);
        int enemyHealthAfterSecondAbilityCast = enemyInRange.getHealth().getAmount();
        int cooldownAfterSecondAbilityCast = testWarrior.getAbility().getResourceCurrent();

        // cooldown should stay the same
        Assertions.assertEquals(cooldownAfterSecondAbilityCast, cooldownBeforeSecondAbilityCast);

        // enemy health should stay the same
        Assertions.assertEquals(enemyHealthAfterSecondAbilityCast, enemyHealthBeforeSecondAbilityCast);
    }

    // On game tick tests
    @Test
    void test2AbilityCastsWithRegeneratingBetween(){
        Enemy enemyInRange = new Monster('t', "Test Monster", 100000, 50, 0, 49, 0);
        enemyInRange.initialize(new Position(1, 0));
        enemyInRange.setRandomSeed(seed);

        List<Enemy> enemyList = new ArrayList<>();
        enemyList.add(enemyInRange);

        testWarrior.abilityCast(enemyList); // should hit enemy for 1000 damage and empty cooldown
        testWarrior.onGameTick(); // regenerate 1 cooldown
        testWarrior.onGameTick(); // regenerate 1 cooldown
        testWarrior.onGameTick(); // regenerate 1 cooldown

        int enemyHealthBeforeSecondAbilityCast = enemyInRange.getHealth().getAmount();
        testWarrior.abilityCast(enemyList);
        int enemyHealthAfterSecondAbilityCast = enemyInRange.getHealth().getAmount();
        int cooldownAfterSecondAbilityCast = testWarrior.getAbility().getResourceCurrent();

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

        enemyInRange.interact(testWarrior); // damage warrior

        List<Enemy> enemyList = new ArrayList<>();
        enemyList.add(enemyInRange);

        // basic level up
        int experienceBeforeLevelingUp = testWarrior.getExperience();
        int levelBeforeLevelingUp = testWarrior.getLevel();
        int healthPoolBeforeLevelingUp = testWarrior.getHealth().getPool();
        int currentHealthBeforeLevelingUp = testWarrior.getHealth().getAmount();
        int attackPtsBeforeLevelingUp = testWarrior.getAttack_pts();
        int defensePtsBeforeLevelingUp = testWarrior.getDefense_pts();

        testWarrior.abilityCast(enemyList); // should level up from this and empty cooldown

        int experienceAfterLevelingUp = testWarrior.getExperience();
        int levelAfterLevelingUp = testWarrior.getLevel();
        int healthPoolAfterLevelingUp = testWarrior.getHealth().getPool();
        int currentHealthAfterLevelingUp = testWarrior.getHealth().getAmount();
        int attackPtsAfterLevelingUp = testWarrior.getAttack_pts();
        int defensePtsAfterLevelingUp = testWarrior.getDefense_pts();

        // warrior unique bonuses
        int cooldownAfterLevelingUp = testWarrior.getAbility().getResourceCurrent();

        Assertions.assertEquals(levelAfterLevelingUp, 2);
        Assertions.assertEquals(experienceAfterLevelingUp, experienceBeforeLevelingUp + 20);
        Assertions.assertEquals(healthPoolAfterLevelingUp, healthPoolBeforeLevelingUp + 10 * levelAfterLevelingUp + 5 * levelAfterLevelingUp);
        Assertions.assertEquals(currentHealthAfterLevelingUp, healthPoolAfterLevelingUp);
        Assertions.assertEquals(attackPtsAfterLevelingUp, attackPtsBeforeLevelingUp + 4 * levelAfterLevelingUp + 2 * levelAfterLevelingUp);
        Assertions.assertEquals(defensePtsAfterLevelingUp, defensePtsBeforeLevelingUp + levelAfterLevelingUp + levelAfterLevelingUp);
        Assertions.assertEquals(cooldownAfterLevelingUp, 3);
    }

    @Test
    void testLevelUpTwiceStatsAsExpected(){
        Enemy enemyInRange = new Monster('t', "Test Monster", 1, 50, 0, 250, 0);
        enemyInRange.initialize(new Position(1, 0));
        enemyInRange.setRandomSeed(seed);

        enemyInRange.interact(testWarrior); // damage warrior

        List<Enemy> enemyList = new ArrayList<>();
        enemyList.add(enemyInRange);

        // warrior unique bonuses
        int cooldownBeforeLevelingUp = testWarrior.getAbility().getResourceCurrent();

        testWarrior.abilityCast(enemyList); // should level up TWICE from this and empty cooldown

        int experienceAfterLevelingUp = testWarrior.getExperience();
        int levelAfterLevelingUp = testWarrior.getLevel();

        Assertions.assertEquals(levelAfterLevelingUp, 3);
        Assertions.assertEquals(experienceAfterLevelingUp, 100);

    }
}