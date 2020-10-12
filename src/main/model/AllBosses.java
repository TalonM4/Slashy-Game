package model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class AllBosses {
    List<Boss> allBosses = new LinkedList<>();

    public AllBosses() {
    }

    public void addBoss(Boss bossToAdd) {
        allBosses.add(bossToAdd);
    }

    public int listSize() {
        return allBosses.size();
    }

    //REQUIRES:The name must have an associated boss
    //EFFECTS: Takes a boss name and finds it in the list
    public Boss stringToBoss(String name) {
        Boss toReturn = new Boss("Placeholder",0);
        for (Boss boss: allBosses) {
            if (boss.name.equals(name)) {
                toReturn = boss;
            }
        }
        return toReturn;
    }
}
