package ui;

import model.GameBackend;
import persistence.Loader;
import persistence.Save;

import javax.swing.*;
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
    GameBackend toPlay;
    private final Save save = new Save("./data/data.txt");
    private final Loader loader = new Loader("./data/data.txt");
    private final JFrame frame;

    public GUI() {
        this.toPlay = new GameBackend();
        frame = new JFrame();

        this.response = new JTextField();
        response.setBounds(120, 50, 250, 20);


        this.attackButton = new JButton("Attack");
        this.prestigeButton = new JButton("Prestige");
        this.upgradeButton = new JButton("Upgrade");
        this.saveButton = new JButton("Save");
        this.loadButton = new JButton("Load");
        this.nextBossButton = new JButton("Next Boss");
        this.balanceDisplay = new JTextField();
        this.weaponDisplay = new JTextField();
        this.levelDisplay = new JTextField();
        this.prestigeDisplay = new JTextField();


        editAttackButton();
        editUpgradeButton();
        editSaveButton();
        editLoadButton();
        editPrestigeButton();
        editNextBossButton();
        updateTextBoxes();
        initializeFrame();
    }

    public void editAttackButton() {
        attackButton.setBounds(130, 100, 100, 40);
        attackButton.addActionListener(e -> {
            if (toPlay.onAttack()) {
                response.setText("You killed the enemy. You are now on " + toPlay.stage + " - " + toPlay.level);
            } else {
                response.setText("The enemy has " + toPlay.currentEnemyHealth + " health remaining.");
            }
            updateTextBoxes();

        });
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
        frame.setSize(500, 500);//400 width and 500 height
        frame.setLayout(null);
        frame.setVisible(true);
    }

    public void editPrestigeButton() {
        prestigeButton.setBounds(200, 300, 100, 40);
        prestigeButton.addActionListener(e -> {
            if (toPlay.getBalance() >= 100) {
                toPlay.onPrestige();
                response.setText("You have prestiged");
            } else {
                response.setText("You need " + (100 - toPlay.balance) + " gold to prestige.");
            }
            updateTextBoxes();
        });
    }



    public void editUpgradeButton() {
        upgradeButton.setBounds(100, 140, 100, 40);
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
        saveButton.setBounds(200, 200, 100, 40);
        saveButton.addActionListener(e -> {
            try {
                save.openFile();
                save.write(toPlay);
                save.close();
                response.setText("You have sucessfully saved your game");
            } catch (FileNotFoundException u) {
                System.out.println("Game not saved");
            }
        });
    }

    public void editLoadButton() {
        loadButton.setBounds(200, 240, 100, 40);
        loadButton.addActionListener(e -> {
            try {
                loader.read(toPlay);
                response.setText("Game loaded successfully");
            } catch (IOException u) {
                response.setText("Game failed to load");
            }
        });
    }

    public void editNextBossButton() {
        nextBossButton.setBounds(300, 300, 100, 40);
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
        prestigeDisplay.setBounds(174, 0, 70, 20);
        prestigeDisplay.setText("Prestige: " + toPlay.prestigeLevel);
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


