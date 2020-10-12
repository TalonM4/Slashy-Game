package model;

import java.util.List;

public class GameBackend {

    public double storedMaxHealth;
    public  int upgradeCost;
    public int balance;
    public int weaponLevel;
    private double prestigeLevel;
    public int stage;
    public int level;
    public double currentEnemyHealth;
    public double maxEnemyHealth;
    public AllBosses listOfBosses;

    public GameBackend() {
        this.prestigeLevel = 0;
        this.weaponLevel = 1;
        this.balance = 0;
        this.stage = 1;
        this.level = 1;
        this.currentEnemyHealth = 3;
        this.upgradeCost = 1;
        this.maxEnemyHealth = 3;
        this.storedMaxHealth = 0;
        this.listOfBosses = new AllBosses();
    }

    public void onPrestige() {
        this.prestigeLevel += 1;
        this.balance -= 1000;
    }

    private boolean isNotZero() {
        return this.prestigeLevel != 0;
    }

    public double attackDamageCalculator() {
        if (isNotZero()) {
            return 3 * weaponLevel * Math.pow(1.05,prestigeLevel);
        } else {
            return 3 * weaponLevel;
        }
    }

    public int getBalance() {
        return this.balance;
    }

    public void increaseStageByOne() {
        this.stage += 1;
    }

    public boolean onAttack() {
        if (currentEnemyHealth - attackDamageCalculator() <= 0) {
            onKill();
            return true;
        } else {
            currentEnemyHealth -= attackDamageCalculator();
            currentEnemyHealth = Math.round(currentEnemyHealth);
            return false;
        }
    }

    public void onKill() {
        maxEnemyHealth *= 1.15 * stage;
        currentEnemyHealth = maxEnemyHealth;
        if (level + 1 == 10) {
            increaseStageByOne();
            setLevelToZero();

        } else {
            level += 1;
        }
    }

    public void setLevelToZero() {
        level = 0;
    }

    //REQUIRES: Name to have an associated boss
    //MODIFIES: this
    //EFFECTS:
    public void summonBoss(String name) {
        Boss currentBoss = listOfBosses.stringToBoss(name);
        currentEnemyHealth = currentBoss.health;
        }


    public void onBossKill() {
    }


}
