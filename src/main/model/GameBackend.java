package model;

public class GameBackend {

    public  int upgradeCost;
    public int balance;
    public int weaponLevel;
    private int prestigeLevel;
    public int stage;
    public int level;
    public double currentEnemyHealth;
    public double maxEnemyHealth;

    public GameBackend() {
        this.prestigeLevel = 0;
        this.weaponLevel = 1;
        this.balance = 0;
        this.stage = 1;
        this.level = 1;
        this.currentEnemyHealth = 3;
        this.upgradeCost = 1;
        this.maxEnemyHealth = 3;
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
            return 3 * weaponLevel * 1.1 * prestigeLevel;
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
        maxEnemyHealth *= 1.2 * stage;
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
}
