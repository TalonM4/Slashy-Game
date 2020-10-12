package ui;

import model.AllBosses;
import model.Boss;
import model.GameBackend;
import java.util.Scanner;

public class Game {
    GameBackend toPlay = new GameBackend();
    boolean createBossMode = false;
    AllBosses allBosses = new AllBosses();

    public Game() {
        onGameStart();
    }


    public void onGameStart() {
        while (true) {
            if (!createBossMode) {
                Scanner nextLine = new Scanner(System.in);
                System.out.println("You can attack, check balance, upgrade, or prestige(requires 100 coins)");
                String command = nextLine.nextLine();
                nextMove(command);
            } else {
                Scanner bossCreator = new Scanner(System.in);
                System.out.println("What is the name of the boss you want to create?");
                String name = bossCreator.nextLine();
                System.out.println("What is the amount of health you want the boss to have?");
                int health = bossCreator.nextByte();
                Boss toAdd = new Boss(name, health);
                allBosses.addBoss(toAdd);
                createBossMode = false;
            }
        }
    }

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

    public void displayBalance() {
        System.out.println("You have " + toPlay.getBalance() + " dollars.");
    }

    public void onAttack() {
        if (toPlay.onAttack()) {
            System.out.println("You have killed the enemy.");
            System.out.println("You are now on " + toPlay.stage + "-" + toPlay.level + " .");
        } else {
            System.out.println("The enemy has " + toPlay.currentEnemyHealth + " health remaining.");
        }
    }

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

    public void prestige() {
        if (toPlay.getBalance() >= 0) {
            toPlay.onPrestige();
            System.out.println("You have sucessfully prestiged.");
        } else {
            System.out.println("You do not have enough money.");
        }

    }

}

