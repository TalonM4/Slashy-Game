package model;

import org.json.JSONArray;
import org.json.JSONObject;

// Represents the innerworkings of the game. Consists of upgrade cost, balance, weapon level, prestige level
// prestige level, stage, level, current enemy health, max enemy health, and list of Bosses
public class GameBackend {

    public  int upgradeCost;
    public int balance;
    public int weaponLevel;
    public int prestigeLevel;
    public int stage;
    public int level;
    public double currentEnemyHealth;
    public double maxEnemyHealth;
    public AllBosses listOfBosses;

    /*EFFECTS: Prestige is intialized as 0, weapon level is intialized as 1, balance is initialized at 0,
    stage is intialized at 1, level is initialized as 1, current enemy health is intialized as 3, upgrade cost is
    intialized as1, max enemy health is intialized as 3, and list of bosses is a empty Allbosses class.

    */
    public GameBackend() {
        this.prestigeLevel = 0;
        this.weaponLevel = 1;
        this.balance = 0;
        this.stage = 1;
        this.level = 1;
        this.currentEnemyHealth = 3;
        this.upgradeCost = 1;
        this.maxEnemyHealth = 3;
        this.listOfBosses = new AllBosses();
    }

    //MODIFIES: this
    //EFFECTS: essentially resets the backend, except adding a prestigeLevel
    public void onPrestige() {
        this.prestigeLevel += 1;
        stage = 1;
        weaponLevel = 1;
        level = 1;
        currentEnemyHealth = 3;
        upgradeCost = 1;
        maxEnemyHealth = 3;
        balance = 0;
    }

    //MODIFIES: this
    //EFFECTS: returns the amount of damage calculated by the formula 3 x weapon level x (1.05^prestige level)
    public double attackDamageCalculator() {
        return 4.5 * weaponLevel * (Math.pow(1.12, prestigeLevel));
    }


    public int getBalance() {
        return this.balance;
    }

    public void increaseStageByOne() {
        this.stage += 1;
    }

    //MODIFIES: this
    //EFFECTS: if the damage is greater or equal than the current health of the enemy, the enemy will get killed
    //         if not, the enemy loses the damage calculated
    public boolean onAttack() {
        if (Math.round(currentEnemyHealth - attackDamageCalculator()) <= 0) {
            onKill();
            return true;
        } else {
            currentEnemyHealth -= attackDamageCalculator();
            currentEnemyHealth = Math.round(currentEnemyHealth);
            return false;
        }
    }

    //MODIFIES: this
    //EFFECTS: creates the next enemy, by taking the current max health and multiplying it by 1.15 and the stage
    //         and set current health to that number. Increases the level by one. Increases balance by the stage
    public void onKill() {
        maxEnemyHealth *= 1.01 *  (stage / 2 + 0.6);
        currentEnemyHealth = maxEnemyHealth;
        balance += stage + 1;
        if (level + 1 == 10) {
            increaseStageByOne();
            level = 0;
        } else {
            level += 1;
        }
    }


//NOTE: Deprecated method only used in console UI

//    //REQUIRES: Name to have an associated boss
//    //MODIFIES: this
//    //EFFECTS:
//    public void summonBoss(String name) throws NegativeHealthException {
//        Boss currentBoss = listOfBosses.stringToBoss(name);
//        currentEnemyHealth = currentBoss.health;
//    }

    //EFFECT: convert game backend into a jsonobject
    public JSONObject convertToJson() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Upgrade Cost", upgradeCost);
        jsonObject.put("Balance", balance);
        jsonObject.put("Weapon Level", weaponLevel);
        jsonObject.put("Prestige Level", prestigeLevel);
        jsonObject.put("Stage", stage);
        jsonObject.put("Level", level);
        jsonObject.put("Current Enemy Health", currentEnemyHealth);
        jsonObject.put("Max Enemy Health", maxEnemyHealth);
        jsonObject.put("Bosses", bossesToJson());

        return jsonObject;
    }

    //EFFECTS: converts the lists of bosses into a json array
    public JSONArray bossesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Boss b : listOfBosses.allBosses) {
            jsonArray.put(b.bossToJson());
        }
        return jsonArray;
    }

}
