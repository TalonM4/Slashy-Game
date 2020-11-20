package model;

import exceptions.NegativeHealthException;
import org.json.JSONObject;

// Represents a boss created by the user. Has a name and health points
public class Boss {

    public String name;
    public int health;

    // Name can be any string, health must be a non-zero int
    public Boss(String name, int health) throws NegativeHealthException {
        this.name = name;
        if (health <= 0) {
            throw new NegativeHealthException();
        } else {
            this.health = health;
        }
    }

    //EFFECTS: turns the boss into a jsonobject
    public JSONObject bossToJson() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Name", name);
        jsonObject.put("Health", health);
        return jsonObject;
    }
}
