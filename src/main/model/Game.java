package model;

import java.util.Scanner;

public class Game {

    private int balance;
    private int damagePerAttackLevel;
    private int weaponLevel;
    private int baseAttackDamage;
    private int prestigeLevel;

    public Game() {
        this.prestigeLevel = 0;
        this.baseAttackDamage = 5;
        this.weaponLevel = 1;
        this.damagePerAttackLevel = 3;
        this.balance = 0;
        gameStart();
    }

    public void gameStart() {
        while (true) {
            Scanner nextLine = new Scanner(System.in);
            System.out.println("You can attack, check balance, upgrade, or prestige(requires 100 coins)");
            String command = nextLine.nextLine();
            nextMove(command);
        }
    }

    public void nextMove(String command) {
        if (command.equalsIgnoreCase("Attack")) {
            attack();
        } else if (command.equalsIgnoreCase("Check Balance")) {
            displayBalance();
        } else if (command.equalsIgnoreCase("Upgrade")) {
            upgrade();
        } else if (command.equalsIgnoreCase("Prestige")) {
            prestige();
        } else {
            System.out.println("You selected an invalid command");
        }
    }

    private void prestige() {
        this.prestigeLevel += 1;
        this.baseAttackDamage = 5;
        this.balance = 0;
        System.out.println("You have prestiged, you know have gained a 110% attack multiplier.");
    }

    private int getPrestigeLevel() {
        return this.prestigeLevel;
    }

    private void upgrade() {
        System.out.println("upgrade");
    }

    private void displayBalance() {
        System.out.println("Your balance is " + balance);
    }

    private void attack() {
        double damage = baseAttackDamage + damagePerAttackLevel * weaponLevel;
        if (!isPrestigeZero()) {
            damage = damage * (1.1 * prestigeLevel);
        }
        System.out.println("You hit the enemy for " + damage);
    }

    private boolean isPrestigeZero() {
        return this.prestigeLevel == 0;
    }
}
