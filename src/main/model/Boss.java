package model;

// Represents a boss created by the user. Has a name and health points
public class Boss {

    public String name;
    public int health;

    // Name can be any string, health must be a non-zero int
    public Boss(String name, int health) {
        this.name = name;
        this.health = health;
    }
}
