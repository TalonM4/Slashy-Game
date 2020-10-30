package persistence;

import model.Boss;
import model.GameBackend;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// this class' read and readfile are based on code from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
public class Loader {

    private String fileName;

    //EFFECTS: creates a Loader
    public Loader(String fileName) {
        this.fileName = fileName;
    }

    //MODIFIES: gb
    //EFFECTS: reads the save file and adjusts the game accordingly
    public void read(GameBackend gb) throws IOException {
        String jsonData = readFile(fileName);
        JSONObject jsonObject = new JSONObject(jsonData);
        loadWeaponLevel(gb, jsonObject);
        loadCurrentEnemyHealth(gb, jsonObject);
        loadStage(gb, jsonObject);
        loadLevel(gb, jsonObject);
        loadUpgradeCost(gb, jsonObject);
        loadMaxEnemyHealth(gb, jsonObject);
        loadPrestigeLevel(gb, jsonObject);
        loadBalance(gb, jsonObject);
        loadBosses(gb, jsonObject);

    }


    // EFFECTS: reads source file as string and returns it
    public String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    //MODIFIES: gb
    //EFFECTS: changes the weapon level to the loaded value
    private void loadWeaponLevel(GameBackend gb, JSONObject jsonObject) {
        int loadWeaponLevel = jsonObject.getInt("Weapon Level");
        gb.weaponLevel = loadWeaponLevel;
    }

    //MODIFIES: gb
    //EFFECTS: changes the current enemy health to the loaded value
    private void loadCurrentEnemyHealth(GameBackend gb, JSONObject jsonObject) {
        int loadCurrentEnemyHealth = jsonObject.getInt("Current Enemy Health");
        gb.currentEnemyHealth = loadCurrentEnemyHealth;
    }

    //MODIFIES: gb
    //EFFECTS: changes the stage to the loaded value
    private void loadStage(GameBackend gb, JSONObject jsonObject) {
        int loadStage = jsonObject.getInt("Stage");
        gb.stage = loadStage;
    }

    //MODIFIES: gb
    //EFFECTS: changes the level to the loaded value
    private void loadLevel(GameBackend gb, JSONObject jsonObject) {
        int loadLevel = jsonObject.getInt("Level");
        gb.level = loadLevel;
    }

    //MODIFIES: gb
    //EFFECTS: changes the upgrade cost to the loaded value
    private void loadUpgradeCost(GameBackend gb, JSONObject jsonObject) {
        int loadUpgradeCost = jsonObject.getInt("Upgrade Cost");
        gb.upgradeCost = loadUpgradeCost;
    }

    //MODIFIES: gb
    //EFFECTS: changes the prestige level to the loaded value
    private void loadPrestigeLevel(GameBackend gb, JSONObject jsonObject) {
        int loadPrestigeLevel = jsonObject.getInt("Upgrade Cost");
        gb.prestigeLevel = loadPrestigeLevel;
    }

    //MODIFIES: gb
    //EFFECTS: changes the balance to the loaded value
    private void loadBalance(GameBackend gb, JSONObject jsonObject) {
        int loadBalance = jsonObject.getInt("Upgrade Cost");
        gb.balance = loadBalance;
    }

    //MODIFIES: gb
    //EFFECTS: changes the max enemy health to the loaded value
    private void loadMaxEnemyHealth(GameBackend gb, JSONObject jsonObject) {
        int loadMaxEnemyHealth = jsonObject.getInt("Upgrade Cost");
        gb.maxEnemyHealth = loadMaxEnemyHealth;
    }

    //EFFECTS: iterates through the list of bosses saved, and adds them into the game
    private void loadBosses(GameBackend gb, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("Bosses");
        for (Object json : jsonArray) {
            JSONObject nextThingy = (JSONObject) json;
            addBoss(gb, nextThingy);
        }
    }

    //MODIFIES: gb
    //EFFECTS: add the bosses from file into the game backend
    private void addBoss(GameBackend gb, JSONObject nextThingy) {
        int health = nextThingy.getInt("Health");
        String name = nextThingy.getString("Name");
        Boss bossToAdd = new Boss(name, health);
        gb.listOfBosses.addBoss(bossToAdd);
    }
}
