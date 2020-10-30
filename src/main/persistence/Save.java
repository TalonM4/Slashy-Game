package persistence;

import model.GameBackend;
import org.json.JSONObject;

import java.io.*;

// This code is adapted from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
public class Save {

    private final String fileName;
    private PrintWriter fileOpener;

    //creates a Save
    public Save(String fileName) {
        this.fileName = fileName;
    }

    // MODIFIES: this
    // EFFECTS: opens fileOpener; throws FileNotFoundException if destination file cannot
    // be opened for writing
    public void openFile() throws FileNotFoundException {
        fileOpener = new PrintWriter(new File(fileName));
    }

    //MODIFIES: fileOpener
    //EFFECTS: writes JSON backend to file
    public void write(GameBackend gb) {
        JSONObject json = gb.convertToJson();
        printToFile(json.toString(4));
    }

    //MODIFIES: fileOpener
    //EFFECTS: closes fileOpener
    public void close() {
        fileOpener.close();
    }

    //MODIFIES: fileOpener
    //EFFECTS: writes string to file
    private void printToFile(String contents) {
        fileOpener.print(contents);
    }
}
