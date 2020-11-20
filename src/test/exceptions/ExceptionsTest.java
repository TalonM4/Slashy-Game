package exceptions;

import model.Boss;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ExceptionsTest {

    @Test
    public void testInvalid() {
        try {
            Boss boss = new Boss("Jimmy", -1);
        } catch (NegativeHealthException e) {
            System.out.println("It was indeed negative");
        }
    }

    @Test
    public void testValid() {
        try {
            Boss boss = new Boss("Jimmy", 1);
            assertEquals("Jimmy", boss.name);
            assertEquals(1, boss.health);
        } catch (NegativeHealthException e) {
            fail("1 is greater than 0");
        }
    }

}
