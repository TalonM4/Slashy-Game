package persistence;

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
        }

    }
}
