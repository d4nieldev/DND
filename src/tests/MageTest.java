package tests;

import Business.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class MageTest {
    Player testMage;
    int seed = 7;

    @BeforeEach
    void setUp() {
        testMage = new Mage("Test Mage", 10000, 10000, 1, 200, 20, 10000, 3, 5);
        testMage.initialize(new Position(0,0));
        testMage.setRandomSeed(seed);
    }

    // Position tests after all kinds of interactions
    @Test
    void testPositionAfterInteractionWithEmpty(){
        Position emptyTileOriginalPosition = new Position(1,0);
        Tile emptyTile = new EmptyTile();
        emptyTile.initialize(emptyTileOriginalPosition);

        testMage.interact(emptyTile);
        Position testWarriorPositionAfterInteraction = new Position(testMage.getX(), testMage.getY());

        Assertions.assertEquals(testWarriorPositionAfterInteraction, emptyTileOriginalPosition);
    }

    @Test
    void testPositionAfterInteractionWithWall(){
        Position testWarriorPositionBeforeInteraction = new Position(testMage.getX(), testMage.getY());
        Tile wall = new Wall();
        wall.initialize(new Position(1,0));

        testMage.interact(wall);
        Position testWarriorPositionAfterInteraction = new Position(testMage.getX(), testMage.getY());

        Assertions.assertEquals(testWarriorPositionAfterInteraction, testWarriorPositionBeforeInteraction);
    }

    @Test
    void testPositionAfterInteractionWithMonsterAndNotKilling(){
        Position testWarriorPositionBeforeInteraction = new Position(testMage.getX(), testMage.getY());
        Enemy monster = new Monster('t', "Test Monster", 100000, 0, 0, 0, 0);
        monster.initialize(new Position(1,0));
        monster.setRandomSeed(seed);

        testMage.interact(monster);
        Position testWarriorPositionAfterInteraction = new Position(testMage.getX(), testMage.getY());

        Assertions.assertEquals(testWarriorPositionAfterInteraction, testWarriorPositionBeforeInteraction);
    }

    @Test
    void testPositionAfterInteractionWithTrapAndNotKilling(){
        Position testWarriorPositionBeforeInteraction = new Position(testMage.getX(), testMage.getY());
        Enemy trap = new Trap('t', "Test Trap", 100000, 0, 0, 0, 1, 10);
        trap.initialize(new Position(1,0));
        trap.setRandomSeed(seed);

        testMage.interact(trap);
        Position testWarriorPositionAfterInteraction = new Position(testMage.getX(), testMage.getY());

        Assertions.assertEquals(testWarriorPositionAfterInteraction, testWarriorPositionBeforeInteraction);
    }

    @Test
    void testPositionAfterInteractionWithMonsterAndKilling(){
        Position monsterPositionBeforeInteraction = new Position(1,0);
        Enemy monster = new Monster('t', "Test Monster", 1, 0, 0, 0, 0);
        monster.initialize(monsterPositionBeforeInteraction);
        monster.setRandomSeed(seed);

        testMage.interact(monster);
        Position testWarriorPositionAfterInteraction = new Position(testMage.getX(), testMage.getY());

        Assertions.assertEquals(testWarriorPositionAfterInteraction, monsterPositionBeforeInteraction);
    }

    @Test
    void testPositionAfterInteractionWithTrapAndKilling(){
        Position trapPositionBeforeInteraction = new Position(1,0);
        Enemy trap = new Trap('t', "Test Trap", 1, 0, 0, 0, 1, 10);
        trap.initialize(trapPositionBeforeInteraction);
        trap.setRandomSeed(seed);

        testMage.interact(trap);
        Position testWarriorPositionAfterInteraction = new Position(testMage.getX(), testMage.getY());

        Assertions.assertEquals(testWarriorPositionAfterInteraction, trapPositionBeforeInteraction);
    }

    @Test
    void testPositionAfterKillingEnemyWithAbility(){
        Position testWarriorPositionBeforeAbilityCast = new Position(testMage.getX(), testMage.getY());
        Enemy enemyInRange = new Monster('t', "Test Monster", 1, 0, 0, 0, 0);
        enemyInRange.initialize(new Position(1,0));
        enemyInRange.setRandomSeed(seed);

        List<Enemy> enemyList = new ArrayList<>();
        enemyList.add(enemyInRange);

        testMage.abilityCast(enemyList);
        Position testWarriorPositionAfterAbilityCast = new Position(testMage.getX(), testMage.getY());

        Assertions.assertEquals(testWarriorPositionAfterAbilityCast, testWarriorPositionBeforeAbilityCast);
    }
    // Experience gain tests
    @Test
    void testGainExperienceFromKillingMonsterWithRegularAttack(){
        Enemy monster = new Monster('t', "Test Monster", 1, 0, 0, 49, 0);
        monster.initialize(new Position(1, 0));
        monster.setRandomSeed(seed);

        int warriorExperienceBeforeKilling = testMage.getExperience();
        testMage.interact(monster);
        int warriorExperienceAfterKilling = testMage.getExperience();

        Assertions.assertEquals(warriorExperienceAfterKilling, warriorExperienceBeforeKilling + 49);
    }

    @Test
    void testGainExperienceFromKillingMonsterWithAbilityAttack(){
        Enemy monster = new Monster('t', "Test Monster", 1, 0, 0, 49, 0);
        monster.initialize(new Position(1, 0));
        monster.setRandomSeed(seed);

        List<Enemy> enemyList = new ArrayList<>();
        enemyList.add(monster);

        int warriorExperienceBeforeKilling = testMage.getExperience();
        testMage.abilityCast(enemyList);
        int warriorExperienceAfterKilling = testMage.getExperience();

        Assertions.assertEquals(warriorExperienceAfterKilling, warriorExperienceBeforeKilling + 49);
    }

    @Test
    void testNotGainingExperienceFromAttackingMonsterWithRegularAttack(){
        Enemy monster = new Monster('t', "Test Monster", 100000, 0, 0, 49, 0);
        monster.initialize(new Position(1, 0));
        monster.setRandomSeed(seed);

        int warriorExperienceBeforeAttacking = testMage.getExperience();
        testMage.interact(monster);
        int warriorExperienceAfterAttacking = testMage.getExperience();

        Assertions.assertEquals(warriorExperienceAfterAttacking, warriorExperienceBeforeAttacking);
    }

    @Test
    void testNotGainingExperienceFromAttackingMonsterWithAbilityAttack(){
        Enemy monster = new Monster('t', "Test Monster", 100000, 0, 0, 49, 0);
        monster.initialize(new Position(1, 0));
        monster.setRandomSeed(seed);

        List<Enemy> enemyList = new ArrayList<>();
        enemyList.add(monster);

        int warriorExperienceBeforeAttacking = testMage.getExperience();
        testMage.abilityCast(enemyList);
        int warriorExperienceAfterAttacking = testMage.getExperience();

        Assertions.assertEquals(warriorExperienceAfterAttacking, warriorExperienceBeforeAttacking);
    }

    // Ability tests
    @Test
    void testAbilityHittingEnemiesInRange(){
        Enemy enemyInRange1 = new Monster('t', "Test Monster", 1, 0, 0, 10, 0);
        enemyInRange1.initialize(new Position(1, 0));
        enemyInRange1.setRandomSeed(seed);

        Enemy enemyInRange2 = new Monster('t', "Test Monster", 1, 0, 0, 10, 0);
        enemyInRange2.initialize(new Position(0, 1));
        enemyInRange2.setRandomSeed(seed);

        Enemy enemyInRange3 = new Monster('t', "Test Monster", 1, 0, 0, 10, 0);
        enemyInRange3.initialize(new Position(2, 0));
        enemyInRange3.setRandomSeed(seed);

        Enemy enemyInRange4 = new Monster('t', "Test Monster", 1, 0, 0, 10, 0);
        enemyInRange4.initialize(new Position(0, 2));
        enemyInRange4.setRandomSeed(seed);

        Enemy enemyNotInRange = new Monster('t', "Test Monster", 1, 0, 0, 10, 0);
        enemyNotInRange.initialize(new Position(10, 0));
        enemyNotInRange.setRandomSeed(seed);

        List<Enemy> enemyList = new ArrayList<>();
        enemyList.add(enemyInRange1);
        enemyList.add(enemyInRange2);
        enemyList.add(enemyInRange3);
        enemyList.add(enemyInRange4);
        enemyList.add(enemyNotInRange);

        testMage.abilityCast(enemyList);

        Assertions.assertEquals(enemyList.stream().filter(e -> !e.isDead()).count(), 2); // 2 enemies left out of 5
        Assertions.assertFalse(enemyNotInRange.isDead());
    }

    @Test
    void testAbilityCastWithInsufficientResourcesAndDoesNothing(){
        Enemy enemyInRange = new Monster('t', "Test Monster", Integer.MAX_VALUE, 50, 0, 49, 0);
        enemyInRange.initialize(new Position(1, 0));
        enemyInRange.setRandomSeed(seed);

        List<Enemy> enemyList = new ArrayList<>();
        enemyList.add(enemyInRange);

        testMage.abilityCast(enemyList); // should hit enemy and use 20 mana
        testMage.abilityCast(enemyList); // should hit enemy and use 20 mana

        // left with 10 mana

        int enemyHealthBeforeThirdAbilityCast = enemyInRange.getHealth().getAmount();
        int manaBeforeThirdAbilityCast = testMage.getAbility().getResourceCurrent();
        testMage.abilityCast(enemyList);
        int enemyHealthAfterThirdAbilityCast = enemyInRange.getHealth().getAmount();
        int manaAfterThirdAbilityCast = testMage.getAbility().getResourceCurrent();

        // energy should stay the same
        Assertions.assertEquals(manaAfterThirdAbilityCast, manaBeforeThirdAbilityCast);

        // enemy health should stay the same
        Assertions.assertEquals(enemyHealthAfterThirdAbilityCast, enemyHealthBeforeThirdAbilityCast);
    }

    // On game tick tests
    @Test
    void test2AbilityCastsWithRegeneratingBetween(){
        Enemy enemyInRange = new Monster('t', "Test Monster", Integer.MAX_VALUE, 50, 0, 49, 0);
        enemyInRange.initialize(new Position(1, 0));
        enemyInRange.setRandomSeed(seed);

        List<Enemy> enemyList = new ArrayList<>();
        enemyList.add(enemyInRange);

        testMage.abilityCast(enemyList); // should hit enemy and use 20 mana
        testMage.abilityCast(enemyList); // should hit enemy and use 20 mana

        // regenerate 10 mana
        for (int i = 0; i < 10; i++)
            testMage.onGameTick();

        int enemyHealthBeforeThirdAbilityCast = enemyInRange.getHealth().getAmount();
        testMage.abilityCast(enemyList);
        int enemyHealthAfterThirdAbilityCast = enemyInRange.getHealth().getAmount();
        int manaAfterThirdAbilityCast = testMage.getAbility().getResourceCurrent();

        // energy should stay the same
        Assertions.assertEquals(manaAfterThirdAbilityCast, 0);

        // enemy health should stay the same
        Assertions.assertTrue(enemyHealthAfterThirdAbilityCast < enemyHealthBeforeThirdAbilityCast);
    }

    // Level up tests
    @Test
    void testLevelUpStatsAsExpected(){
        Enemy enemyInRange = new Monster('t', "Test Monster", 1, 50, 0, 70, 0);
        enemyInRange.initialize(new Position(1, 0));
        enemyInRange.setRandomSeed(seed);

        enemyInRange.interact(testMage); // damage mage

        List<Enemy> enemyList = new ArrayList<>();
        enemyList.add(enemyInRange);

        // basic level up
        int experienceBeforeLevelingUp = testMage.getExperience();
        int levelBeforeLevelingUp = testMage.getLevel();
        int healthPoolBeforeLevelingUp = testMage.getHealth().getPool();
        int currentHealthBeforeLevelingUp = testMage.getHealth().getAmount();
        int attackPtsBeforeLevelingUp = testMage.getAttack_pts();
        int defensePtsBeforeLevelingUp = testMage.getDefense_pts();

        // mage unique bonuses
        int manaBeforeLevelingUp = testMage.getAbility().getResourceCurrent();
        int manaPoolBeforeLevelingUp = testMage.getAbility().getResourcePool();

        testMage.abilityCast(enemyList); // should level up from this and empty cooldown

        int experienceAfterLevelingUp = testMage.getExperience();
        int levelAfterLevelingUp = testMage.getLevel();
        int healthPoolAfterLevelingUp = testMage.getHealth().getPool();
        int currentHealthAfterLevelingUp = testMage.getHealth().getAmount();
        int attackPtsAfterLevelingUp = testMage.getAttack_pts();
        int defensePtsAfterLevelingUp = testMage.getDefense_pts();

        // mage unique bonuses
        int manaAfterLevelingUp = testMage.getAbility().getResourceCurrent();
        int manaPoolAfterLevelingUp = testMage.getAbility().getResourcePool();

        Assertions.assertEquals(levelAfterLevelingUp, 2);
        Assertions.assertEquals(experienceAfterLevelingUp, experienceBeforeLevelingUp + 20);
        Assertions.assertEquals(healthPoolAfterLevelingUp, healthPoolBeforeLevelingUp + 10 * levelAfterLevelingUp);
        Assertions.assertEquals(currentHealthAfterLevelingUp, healthPoolAfterLevelingUp);
        Assertions.assertEquals(attackPtsAfterLevelingUp, attackPtsBeforeLevelingUp + 4 * levelAfterLevelingUp);
        Assertions.assertEquals(defensePtsAfterLevelingUp, defensePtsBeforeLevelingUp + levelAfterLevelingUp);
        Assertions.assertEquals(manaPoolAfterLevelingUp, manaPoolBeforeLevelingUp + 25 * levelAfterLevelingUp);
        Assertions.assertEquals(manaAfterLevelingUp, manaBeforeLevelingUp - 20 + manaPoolAfterLevelingUp / 4);
    }

    @Test
    void testLevelUpTwiceStatsAsExpected(){
        Enemy enemyInRange = new Monster('t', "Test Monster", 1, 50, 0, 250, 0);
        enemyInRange.initialize(new Position(1, 0));
        enemyInRange.setRandomSeed(seed);

        enemyInRange.interact(testMage); // damage mage

        List<Enemy> enemyList = new ArrayList<>();
        enemyList.add(enemyInRange);

        testMage.abilityCast(enemyList); // should level up TWICE from this and reduce mana by 20

        int experienceAfterLevelingUp = testMage.getExperience();
        int levelAfterLevelingUp = testMage.getLevel();

        Assertions.assertEquals(levelAfterLevelingUp, 3);
        Assertions.assertEquals(experienceAfterLevelingUp, 100);

    }
}