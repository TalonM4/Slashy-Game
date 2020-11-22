package model;

import exceptions.NegativeHealthException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class BossTest {

    @Test
    public void bossTest() {
        try {
            Boss boss = new Boss("John", 1);
            assertEquals(1, boss.health);
            assertEquals("John", boss.name);
        } catch (NegativeHealthException e) {
            fail("1 is greater than 0");
        }
    }

    @Test
    public void invalidBossTest() {
        try {
            Boss boss = new Boss("Invalid", -1);
            assertEquals("Invalid", boss.name);
        } catch (NegativeHealthException e) {
            System.out.println("That is indeed negative");
        }
    }

    @Test
    public void invalidBossTest2() {
        try {
            Boss boss = new Boss("Invalid", 0);
            assertEquals("Invalid", boss.name);
        } catch (NegativeHealthException e) {
            System.out.println("That is indeed 0");
        }
    }

    @Test
    public void bossListTest() {
        try {
            Boss boss1 = new Boss("Boss1", 1);
            Boss boss2 = new Boss("Boss2", 2);
            AllBosses bossList = new AllBosses();
            bossList.addBoss(boss1);
            assertEquals(1, bossList.listSize());
            bossList.addBoss(boss2);
            assertEquals(2, bossList.listSize());
    } catch (NegativeHealthException e) {
            fail("Both health are greater than 0");
        }
    }

// NOTE: test is for a deprecated method.

//    @Test
//    public void stringToBossTest() {
//        try {
//            AllBosses bossList = new AllBosses();
//            Boss boss1 = new Boss("Boss1", 100);
//            bossList.addBoss(boss1);
//            assertEquals(boss1, bossList.stringToBoss("Boss1"));
//            assertEquals("Placeholder", bossList.stringToBoss("Jim").name);
//            assertEquals(1, bossList.stringToBoss("Jim").health);
//    } catch (NegativeHealthException e) {
//            fail("All health are greater than 0");
//        }
//    }

    @Test
    public void testGetBoss() {
        try {
            AllBosses bossList = new AllBosses();
            Boss boss1 = new Boss("Boss1", 100);
            bossList.addBoss(boss1);
            Boss boss2 = new Boss("Boss2", 200);
            bossList.addBoss(boss2);
            assertEquals("Boss1", bossList.getBoss(0).name);
            assertEquals("Boss2", bossList.getBoss(1).name);
        } catch (NegativeHealthException e) {
            fail();
        }
    }



}