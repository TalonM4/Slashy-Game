package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameBackendTest {

    @Test
    public void gameBackendInitializationTest() {
        GameBackend toTest = new GameBackend();
        assertEquals(0,toTest.prestigeLevel);
        assertEquals(1, toTest.weaponLevel);
        assertEquals(0, toTest.balance);
        assertEquals(1, toTest.stage);
        assertEquals(1, toTest.level);
        assertEquals(3, toTest.currentEnemyHealth);
        assertEquals(1, toTest.upgradeCost);
        assertEquals(3, toTest.maxEnemyHealth);
        assertEquals(0, toTest.listOfBosses.listSize());
    }
}
