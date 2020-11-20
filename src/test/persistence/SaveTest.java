package persistence;

import exceptions.NegativeHealthException;
import model.Boss;
import model.GameBackend;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import static org.junit.jupiter.api.Assertions.fail;

public class SaveTest {

    @Test
    public void saveTestBadFile() {
        Save save = new Save("./data/doesnotexist");
        try {
            save.openFile();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    @Test
    public void saveTestGoodFile() {
        GameBackend gb = new GameBackend();
        Save save = new Save("./data/goodfile.txt");
        try {
            save.openFile();
            save.write(gb);
            save.close();
        } catch (FileNotFoundException e) {
            fail("The file exists");
        }
    }

    @Test
    public void saveBossTest() {
        GameBackend gb = new GameBackend();
        try{
            gb.listOfBosses.addBoss(new Boss("Bob", 100));
        } catch (NegativeHealthException e) {
            fail();

        }
        Save save = new Save("./data/bosssave.txt");
        try {
            save.openFile();
            save.write(gb);
            save.close();
        } catch (FileNotFoundException e) {
            System.out.println("The file exists");
        }
    }
}
