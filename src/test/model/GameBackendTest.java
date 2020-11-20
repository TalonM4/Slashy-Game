package model;

import exceptions.NegativeHealthException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameBackendTest {

    @Test
    public void gameBackendInitializationTest() {
        GameBackend toTest = new GameBackend();
        assertEquals(0, toTest.prestigeLevel);
        assertEquals(1, toTest.weaponLevel);
        assertEquals(0, toTest.balance);
        assertEquals(1, toTest.stage);
        assertEquals(1, toTest.level);
        assertEquals(3, toTest.currentEnemyHealth);
        assertEquals(1, toTest.upgradeCost);
        assertEquals(3, toTest.maxEnemyHealth);
        assertEquals(0, toTest.listOfBosses.listSize());
    }

    @Test
    public void onPrestigeTest() {
        GameBackend toTest = new GameBackend();
        toTest.onPrestige();
        assertEquals(1, toTest.prestigeLevel);
        assertEquals(1, toTest.weaponLevel);
        assertEquals(0, toTest.balance);
        assertEquals(1, toTest.stage);
        assertEquals(1, toTest.level);
        assertEquals(3, toTest.currentEnemyHealth);
        assertEquals(1, toTest.upgradeCost);
        assertEquals(3, toTest.maxEnemyHealth);
        assertEquals(0, toTest.listOfBosses.listSize());
    }


    @Test
    public void attackDamageCalculatorTest() {
        GameBackend toTest = new GameBackend();
        assertEquals(3, toTest.attackDamageCalculator());
        toTest.onPrestige();
        assertEquals(3.15, toTest.attackDamageCalculator());
    }

    @Test
    public void getBalanceTest() {
        GameBackend toTest = new GameBackend();
        assertEquals(0, toTest.getBalance());
        toTest.balance += 100;
        assertEquals(100,toTest.getBalance());
    }

    @Test
    public void increaseStageByOneTest() {
        GameBackend toTest = new GameBackend();
        assertEquals(1, toTest.stage);
        toTest.increaseStageByOne();
        assertEquals(2, toTest.stage);
    }

    @Test
    public void onAttackTest() {
        GameBackend toTest = new GameBackend();
        assertTrue(toTest.onAttack());
        assertTrue(toTest.onAttack());
        assertEquals(4,Math.round(toTest.currentEnemyHealth));
        toTest.currentEnemyHealth = 100;
        assertFalse(toTest.onAttack());
    }

    @Test
    public void onKillTest() {
        GameBackend toTest = new GameBackend();
        toTest.onKill();
        assertEquals(3, Math.round(toTest.maxEnemyHealth));
        assertEquals(3, Math.round(toTest.currentEnemyHealth));
        assertEquals(1, toTest.balance);
        toTest.level = 9;
        toTest.onKill();
        assertEquals(0, toTest.level);
        assertEquals(2, toTest.stage);
    }

    @Test
    public void summonBossTest() {
        try {GameBackend toTest = new GameBackend();
        Boss boss = new Boss("Boss", 100);
        toTest.listOfBosses.addBoss(boss);
        toTest.summonBoss("Boss");
        assertEquals(100, toTest.currentEnemyHealth);
    } catch (NegativeHealthException e) {
            fail("100 is greater than  0");
        }
    }

}
