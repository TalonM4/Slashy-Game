package persistence;

import exceptions.NegativeHealthException;
import model.GameBackend;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class LoaderTest {

    @Test
    public void invalidFileTest() {
        Loader loader = new Loader("./data/doesnotexistloader");
        try {
            loader.readFile("./data/doesnotexistloader");
        } catch (IOException e) {
            System.out.println("That file does not exist");
        }
    }

    @Test
    public void validFileTest() {
        GameBackend gb = new GameBackend();
        Loader loader = new Loader("./data/newgamesave.txt");
        try {
            loader.read(gb);
            assertEquals(1, gb.level);
        } catch (IOException e) {
            fail("./data/newgamesave.txt is a valid file name.");
        } catch (NegativeHealthException e) {
            fail("Should not have invalid boss file");
        }
    }

    @Test
    public void prestige100Test() {
        GameBackend gb = new GameBackend();
        Loader loader = new Loader("./data/prestige100.txt");
        try {
            loader.read(gb);
            assertEquals(100, gb.prestigeLevel);
        } catch (IOException e) {
            fail("./data/prestige100.txt is a valid file name.");
        } catch (NegativeHealthException e) {
            fail("Should not have invalid boss file");
        }
    }

    @Test
    public void loadBossTest() {
        GameBackend gb = new GameBackend();
        Loader loader = new Loader("./data/bosssave.txt");
        try {
            loader.read(gb);
        } catch (IOException e) {
            fail("bosssave.txt is a valid file name");
        } catch (NegativeHealthException e) {
            fail("Should not have invalid boss file");
        }
    }
}
