package persistence;

import model.GameBackend;
import org.junit.jupiter.api.Test;
import ui.Game;

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
        Save save = new Save("./data.goodfile.txt");
        try {
            save.openFile();
            save.write(gb);
            save.close();
        } catch (FileNotFoundException e) {
            fail("The file exists");
        }
    }
}
