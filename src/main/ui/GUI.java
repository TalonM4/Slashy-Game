package ui;

import exceptions.NegativeHealthException;
import model.Boss;
import model.GameBackend;
import persistence.Loader;
import persistence.Save;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

// courtesy of http://suavesnippets.blogspot.com/2011/06/add-sound-on-jbutton-click-in-java.html
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioSystem;

public class GUI {
    private final JButton loadButton;
    private final JButton saveButton;
    private final JButton upgradeButton;
    private final JTextField weaponDisplay;
    private final JButton prestigeButton;
    private final JButton attackButton;
    private final JTextField balanceDisplay;
    private final JTextField response;
    private final JTextField levelDisplay;
    private final JTextField prestigeDisplay;
    private final JButton nextBossButton;
    private final JButton newBossButton;
    private final JButton fightBossButton;
    private final JTextField healthDisplay;
    private final JButton saveBossButton;
    GameBackend toPlay;
    private final Save save = new Save("./data/data.txt");
    private final Loader loader = new Loader("./data/data.txt");
    private final JFrame frame;

    public GUI() {
        this.toPlay = new GameBackend();
        frame = new JFrame();

        this.response = new JTextField();
        response.setBounds(70, 50, 250, 20);


        this.attackButton = new JButton("Attack");
        this.prestigeButton = new JButton("Prestige");
        this.upgradeButton = new JButton("Upgrade");
        this.saveButton = new JButton("Save");
        this.loadButton = new JButton("Load");
        this.nextBossButton = new JButton("Next Boss");
        this.newBossButton = new JButton("New Boss");
        this.fightBossButton = new JButton("Fight Boss");
        this.saveBossButton = new JButton("Save Boss");
        this.balanceDisplay = new JTextField();
        this.weaponDisplay = new JTextField();
        this.levelDisplay = new JTextField();
        this.prestigeDisplay = new JTextField();
        this.healthDisplay = new JTextField();


        updateTextBoxes();
        initializeFrame();
    }



    public void initializeFrame() {
        frame.add(attackButton);
        frame.add(prestigeButton);
        frame.add(response);
        frame.add(balanceDisplay);
        frame.add(weaponDisplay);
        frame.add(upgradeButton);
        frame.add(saveButton);
        frame.add(loadButton);
        frame.add(levelDisplay);
        frame.add(prestigeDisplay);
        frame.add(nextBossButton);
        frame.add(newBossButton);
        frame.add(fightBossButton);
        frame.add(healthDisplay);
        frame.add(saveBossButton);

        editButtons();


        frame.setSize(418, 400);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    private void editButtons() {
        editAttackButton();
        editUpgradeButton();
        editSaveButton();
        editLoadButton();
        editPrestigeButton();
        editNextBossButton();
        editFightBossButton();
        editNewBossButton();
        editSaveBossButton();
    }

    public void editAttackButton() {
        attackButton.setBounds(0, 130, 100, 40);
        attackButton.addActionListener(e -> {
            if (toPlay.onAttack()) {
                response.setText("You killed the enemy. You are now on " + toPlay.stage + " - " + toPlay.level);
            } else {
                response.setText("The enemy has " + toPlay.currentEnemyHealth + " health remaining.");
            }
            updateTextBoxes();
        });
    }

    public void editPrestigeButton() {
        prestigeButton.setBounds(200, 130, 100, 40);
        prestigeButton.addActionListener(e -> {
            if (toPlay.getBalance() >= 75) {
                toPlay.onPrestige();
                response.setText("You have prestiged");
            } else {
                response.setText("You need " + (100 - toPlay.balance) + " gold to prestige.");
            }
            updateTextBoxes();
        });
    }

    public void editUpgradeButton() {
        upgradeButton.setBounds(100, 130, 100, 40);
        upgradeButton.addActionListener(e -> {
            if (toPlay.getBalance() >= toPlay.upgradeCost) {
                toPlay.balance -= toPlay.upgradeCost;
                toPlay.weaponLevel += 1;
                toPlay.upgradeCost += 2;
                response.setText("You have upgraded your weapon.");
                playSound("./data/upgrade-sound.wav");
            } else {
                response.setText("You need " + (toPlay.upgradeCost - toPlay.balance) + " gold to upgrade.");
            }
            updateTextBoxes();
        });
    }

    public void editSaveButton() {
        saveButton.setBounds(0, 170, 100, 40);
        saveButton.addActionListener(e -> {
            try {
                save.openFile();
                save.write(toPlay);
                save.close();
                response.setText("You have successfully saved your game");
            } catch (FileNotFoundException u) {
                System.out.println("Game not saved");
            }
        });
    }

    public void editLoadButton() {
        loadButton.setBounds(100, 170, 100, 40);
        loadButton.addActionListener(e -> {
            try {
                loader.read(toPlay);
                response.setText("Game loaded successfully");
            } catch (IOException | NegativeHealthException u) {
                response.setText("Game failed to load");
            }
        });
    }

    public void editNewBossButton() {
        newBossButton.setBounds(200, 170, 100, 40);
        newBossButton.addActionListener(e -> {
            response.setText("What should the name of the boss be?");
            editHealthDisplay();
        });                                                                                

    }

    public void editNextBossButton() {
        nextBossButton.setBounds(300, 130, 100, 40);
    }

    public void editFightBossButton() {
        fightBossButton.setBounds(300, 170, 100, 40);
    }

    public void updateTextBoxes() {
        editWeaponDisplay();
        editBalanceDisplay();
        editLevelDisplay();
        editPrestigeDisplay();
    }

    public void editLevelDisplay() {
        levelDisplay.setBounds(132, 0, 40, 20);
        levelDisplay.setText(toPlay.stage + " - " + toPlay.level);
    }

    public void editBalanceDisplay() {
        balanceDisplay.setBounds(0, 0, 64, 20);
        balanceDisplay.setText("$ " + toPlay.balance);
    }

    public void editWeaponDisplay() {
        weaponDisplay.setBounds(66, 0, 64, 20);
        weaponDisplay.setText("Weapon " + toPlay.weaponLevel);
    }

    public void editPrestigeDisplay() {
        prestigeDisplay.setBounds(174, 0, 100, 20);
        prestigeDisplay.setText("Prestige: " + toPlay.prestigeLevel);
    }

    public void editHealthDisplay() {
        healthDisplay.setBounds(70, 90, 250, 20);
        healthDisplay.setText("How much health should the boss have?");
        healthDisplay.setVisible(true);
    }

    public void editSaveBossButton() {
        saveBossButton.setBounds(0,210, 400, 40);
        saveBossButton.addActionListener(e -> {
            try {
                int health = Integer.parseInt(healthDisplay.getText());
                Boss bossToAdd = new Boss(response.getText(), health);
                toPlay.listOfBosses.addBoss(bossToAdd);
                response.setText("Boss created successfully");
                healthDisplay.setVisible(false);
            } catch (NegativeHealthException c) {
                healthDisplay.setText("Health must be above 0");
            } catch (NumberFormatException w) {
                healthDisplay.setText("Health must be an integer");
            }


        });
    }

    //courtesy of http://suavesnippets.blogspot.com/2011/06/add-sound-on-jbutton-click-in-java.html
    public void playSound(String soundName) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
    }



}


