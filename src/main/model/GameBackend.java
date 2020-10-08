package model;

public class GameBackend {

    private int balance;
    private int weaponLevel;
    public String name;
    private int prestigeLevel;
    public int stage;
    public int level;
    public double currentEnemyHealth;

    public GameBackend() {
        this.prestigeLevel = 0;
        this.weaponLevel = 0;
        this.balance = 0;
        this.stage = 1;
        this.level = 1;
        this.currentEnemyHealth = 3;
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

    public void upgrade() {
        System.out.println("upgrade");
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
            return false;
        }
    }

    public void onKill() {
        if (level + 1 == 10) {
            increaseStageByOne();
        } else {
            level += 1;
        }
    }
}
