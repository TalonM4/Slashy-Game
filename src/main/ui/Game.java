package ui;

import model.GameBackend;
import java.util.Scanner;

public class Game {
    GameBackend toPlay = new GameBackend();

    public Game() {
        onGameStart();
    }


    public void onGameStart() {
        while (true) {
            Scanner nextLine = new Scanner(System.in);
            System.out.println("You can attack, check balance, upgrade, or prestige(requires 100 coins)");
            String command = nextLine.nextLine();
            nextMove(command);
        }
    }

    public void nextMove(String command) {
        if (command.equalsIgnoreCase("Attack")) {
            onAttack();
        } else if (command.equalsIgnoreCase("Check Balance")) {
            displayBalance();
        } else if (command.equalsIgnoreCase("Upgrade")) {
            toPlay.upgrade();
        } else if (command.equalsIgnoreCase("Prestige")) {
            toPlay.onPrestige();
        } else {
            System.out.println("You selected an invalid command");
        }
    }

    public void displayBalance() {
        System.out.println("You have " + toPlay.getBalance() + " dollars.");;
    }

    public void onAttack() {
        if (toPlay.onAttack()) {
            System.out.println("You have killed the enemy.");
            System.out.println("You are now on " + toPlay.stage + "-at" + toPlay.level + " .");
        } else {
            System.out.println("The enemy has " + toPlay.currentEnemyHealth + " health remaining.");
        }


    }

}

