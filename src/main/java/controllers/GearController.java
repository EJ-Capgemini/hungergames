package controllers;

import loot.Food;
import participants.Contestant;

public class GearController {
    public GearController() {
    }

    /*

     */
    public void takeLoot(Contestant contestant){
        int random = (int)(Math.random() * 6);

        switch (random){
            case 0:
                contestant.lootArmour();
                contestant.setHealth(contestant.getHealth() + new Food().eat());
                break;
            case 1:
                contestant.lootWeapon();
                contestant.setHealth(contestant.getHealth() + new Food().eat());
                break;
            case 2:
                contestant.setHealth(contestant.getHealth() + new Food().eat());
                break;
            case 3:
                contestant.setHealth(contestant.getHealth() + new Food().eat());
                contestant.setHealth(contestant.getHealth() + new Food().eat());
                break;
        }
    }
}
