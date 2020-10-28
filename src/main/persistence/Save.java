package persistence;

import model.GameBackend;
import org.json.JSONObject;

import java.io.*;

public class Save {

    private final String fileName;
    private PrintWriter fileOpener;

    public Save(String fileName) {
        this.fileName = fileName;
    }

    public void openFile() throws FileNotFoundException {
        fileOpener = new PrintWriter(new File(fileName));
    }

    public void write(GameBackend gb) {
        JSONObject json = gb.convertToJson();
        printToFile(json.toString(4));

    }

    public void close() {
        fileOpener.close();
    }

    private void printToFile(String contents) {
        fileOpener.print(contents);
    }
}
