package ui;

import model.Boss;
import model.GameBackend;
import java.util.Scanner;

public class Game {
    GameBackend toPlay = new GameBackend();
    boolean createBossMode = false;


    public Game() {
        onGameStart();
    }

    //MODIFIES: this
    //EFFECTS:
    public void onGameStart() {
        while (true) {
            if (!createBossMode) {
                Scanner nextLine = new Scanner(System.in);
                System.out.println("You can attack, check balance, upgrade, create boss, summon boss"
                        +
                        "or prestige(requires 100 coins)");
                String command = nextLine.nextLine();
                nextMove(command);
            } else {
                Scanner bossCreator = new Scanner(System.in);
                System.out.println("What is the name of the boss you want to create?");
                String name = bossCreator.nextLine();
                System.out.println("What is the amount of health you want the boss to have?");
                int health = bossCreator.nextByte();
                Boss toAdd = new Boss(name, health);
                toPlay.listOfBosses.addBoss(toAdd);
                createBossMode = false;
            }
        }
    }

    //MODIFIES: this
    //EFFECTS: takes the user input and calls the relevant method
    public void nextMove(String command) {
        if (command.equalsIgnoreCase("Attack") || command.equalsIgnoreCase("A")) {
            onAttack();
        } else if (command.equalsIgnoreCase("Check Balance")) {
            displayBalance();
        } else if (command.equalsIgnoreCase("Upgrade")) {
            upgrade();
        } else if (command.equalsIgnoreCase("Prestige")) {
            prestige();
        } else if (command.equalsIgnoreCase("Create Boss")) {
            createBossMode = true;
        } else if (command.equalsIgnoreCase("Summon Boss")) {
            Scanner bossName = new Scanner(System.in);
            String name = bossName.nextLine();
            toPlay.summonBoss(name);
            System.out.println("You have summoned " + name);
        } else {
            System.out.println("You selected an invalid command");
        }
    }

    //EFFECTS: displays the user's balance in a nice way!
    public void displayBalance() {
        System.out.println("You have " + toPlay.getBalance() + " dollars.");
    }

    //MODIFIES: toPlay
    //EFFECTS: calls toPlay.onAttack to determine if the enemy has died. If it has died, print the kill and level
    //         progression message. If not display how much health is remaining.
    public void onAttack() {
        if (toPlay.onAttack()) {
            System.out.println("You have killed the enemy.");
            System.out.println("You are now on " + toPlay.stage + "-" + toPlay.level + " .");
        } else {
            System.out.println("The enemy has " + toPlay.currentEnemyHealth + " health remaining.");
        }
    }
    //MODIFIES: toPlay
    //EFFECTS: If there is enough money to upgrade, upgrades the weapon. If not tell the user they are poor
    public void upgrade() {
        if (toPlay.getBalance() >= toPlay.upgradeCost) {
            toPlay.balance -= toPlay.upgradeCost;
            toPlay.weaponLevel += 1;
            toPlay.upgradeCost += 2;
            System.out.println("You have successfully upgraded your weapon.");
            System.out.println("Your balance is now " + toPlay.balance + ".");
        } else {
            System.out.println("You don't have enough money to upgrade.");
        }
    }

    //EFFECTS: if the user has enough money to prestige, they will prestige, else tell them they are poor :(
    public void prestige() {
        if (toPlay.getBalance() >= 100) {
            toPlay.onPrestige();
            System.out.println("You have sucessfully prestiged.");
        } else {
            System.out.println("You do not have enough money.");
        }

    }

}

