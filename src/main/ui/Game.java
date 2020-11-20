//package ui;
//
//import exceptions.NegativeHealthException;
//import persistence.Loader;
//import persistence.Save;
//import model.Boss;
//import model.GameBackend;
//
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.util.Scanner;
//
////Represents the UI of the game, contains the backend.
//public class Game {
//    private static final String DATA_STORAGE = "./data/data.txt";
//    GameBackend toPlay = new GameBackend();
//    boolean createBossMode = false;
//    boolean firstTime = true;
//    private Save save = new Save(DATA_STORAGE);
//    private Loader loader = new Loader("./data/data.txt");
//
//    //EFFECTS: starts the whole thing
//    public Game() throws NegativeHealthException {
//        onGameStart();
//    }
//
//
//    //MODIFIES: this
//    //EFFECTS: gathers user input.
//    public void onGameStart() throws NegativeHealthException {
//        while (true) {
//            if (firstTime) {
//                onHelp();
//                firstTime = false;
//
//            } else if (!createBossMode) {
//                Scanner nextLine = new Scanner(System.in);
//                System.out.println("You can attack, check balance, upgrade, create boss, summon boss, ask for help,"
//                        +
//                        " save game, load game, or prestige(requires 100 coins)");
//                String command = nextLine.nextLine();
//                nextMove(command);
//            } else {
//                createBoss();
//            }
//        }
//    }
//
//    //MODIFIES: this
//    //EFFECTS: uses user input to create a new boss and adds it to the boss list
//    private void createBoss() throws NegativeHealthException {
//        Scanner bossCreator = new Scanner(System.in);
//        System.out.println("What is the name of the boss you want to create?");
//        String name = bossCreator.nextLine();
//        System.out.println("What is the amount of health you want the boss to have?");
//        String shealth = bossCreator.nextLine();
//        int ihealth = Integer.parseInt(shealth);
//        Boss toAdd = new Boss(name, ihealth);
//        toPlay.listOfBosses.addBoss(toAdd);
//        createBossMode = false;
//    }
//
//    //MODIFIES: this
//    //EFFECTS: takes the user input and calls the relevant method
//    public void nextMove(String command) {
//        if (command.equalsIgnoreCase("Attack") || command.equalsIgnoreCase("A")) {
//            onAttack();
//        } else if (command.equalsIgnoreCase("Check Balance")) {
//            displayBalance();
//        } else if (command.equalsIgnoreCase("Upgrade")) {
//            upgrade();
//        } else if (command.equalsIgnoreCase("Prestige")) {
//            prestige();
//        } else if (command.equalsIgnoreCase("Create Boss")) {
//            createBossMode = true;
//        } else if (command.equalsIgnoreCase("Summon Boss")) {
//            onBossSummon();
//        } else if (command.equalsIgnoreCase("help")) {
//            onHelp();
//        } else if (command.equalsIgnoreCase("save")) {
//            onSave();
//        } else if (command.equalsIgnoreCase("load")) {
//            onLoad();
//        } else {
//            System.out.println("You selected an invalid command");
//        }
//    }
//
//    private void onBossSummon() {
//        Scanner bossName = new Scanner(System.in);
//        System.out.println("What is the name of the boss you would like to summon?");
//        String name = bossName.nextLine();
//        toPlay.summonBoss(name);
//        System.out.println("You have summoned " + name);
//    }
//
//    //EFFECTS: displays the user's balance in a nice way!
//    public void displayBalance() {
//        System.out.println("You have " + toPlay.getBalance() + " dollars.");
//    }
//
//    //MODIFIES: toPlay
//    //EFFECTS: calls toPlay.onAttack to determine if the enemy has died. If it has died, print the kill and level
//    //         progression message. If not display how much health is remaining.
//    public void onAttack() {
//        if (toPlay.onAttack()) {
//            System.out.println("You have killed the enemy.");
//            System.out.println("You are now on " + toPlay.stage + "-" + toPlay.level + " .");
//        } else {
//            System.out.println("The enemy has " + toPlay.currentEnemyHealth + " health remaining.");
//        }
//    }
//
//    //MODIFIES: toPlay
//    //EFFECTS: If there is enough money to upgrade, upgrades the weapon. If not tell the user they are poor
//    public void upgrade() {
//        if (toPlay.getBalance() >= toPlay.upgradeCost) {
//            toPlay.balance -= toPlay.upgradeCost;
//            toPlay.weaponLevel += 1;
//            toPlay.upgradeCost += 2;
//            System.out.println("You have successfully upgraded your weapon.");
//            System.out.println("Your balance is now " + toPlay.balance + ".");
//        } else {
//            System.out.println("You don't have enough money to upgrade.");
//        }
//    }
//
//    //EFFECTS: if the user has enough money to prestige, they will prestige, else tell them they are poor :(
//    public void prestige() {
//        if (toPlay.getBalance() >= 100) {
//            toPlay.onPrestige();
//            System.out.println("You have sucessfully prestiged.");
//        } else {
//            System.out.println("You do not have enough money.");
//        }
//    }
//
//
//    //EFFECTS: prints the help message.
//    public void onHelp() {
//        System.out.println("Welcome to Slash Game, traveller, be prepared for a journey of fun times. "
//                        +
//                           "However you're probably wondering how to play this fine game. "
//                        +
//                           "\nYou can attack using the keyword 'attack' or 'a'. You can upgrade your weapon using the "
//                        +
//                           "keyword 'upgrade'. You can prestige using the keyword 'prestige'.\nYou can create a boss "
//                        +
//                           "using the 'create boss' keyword. You can fight the boss using the 'summon boss keyword. "
//                        +
//                           "\n You can save and load a game using the 'save' and 'load' keywords. "
//                        +
//                            "A note, creating a boss with 0 health, or summoning a boss with no associated name breaks"
//                        +
//                           " the game. Please don't do it.");
//    }
//
//    public void onSave() {
//        try {
//            save.openFile();
//            save.write(toPlay);
//            save.close();
//        } catch (FileNotFoundException e) {
//            System.out.println("We can't a file with that name.");
//        }
//    }
//
//    public void onLoad() {
//        try {
//            loader.read(toPlay);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//}
//
