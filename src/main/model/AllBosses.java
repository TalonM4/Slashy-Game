package model;

import java.util.LinkedList;
import java.util.List;

//Represents a list of bosses.
public class AllBosses {
    List<Boss> allBosses = new LinkedList<>();

    public AllBosses() {
    }

    //REQUIRES: a valid boss
    //MODIFIES: this
    //EFFECTS: adds the given boss to the list
    public void addBoss(Boss bossToAdd) {
        allBosses.add(bossToAdd);
    }

    //EFFECTS: returns the size of the list
    public int listSize() {
        return allBosses.size();
    }

    //EFFECTS: returns the boss at element i
    public Boss getBoss(int i) {
        return allBosses.get(i);
    }


//NOTE: this method is deprecated, only used in the console interaction which has been disabled.


//    //REQUIRES:The name must have an associated boss
//    //EFFECTS: Takes a boss name and finds it in the list
//    public Boss stringToBoss(String name) throws NegativeHealthException {
//        Boss toReturn = new Boss("Placeholder",1);
//        for (Boss boss: allBosses) {
//            if (boss.name.equals(name)) {
//                toReturn = boss;
//            }
//        }
//        return toReturn;
//    }
}
