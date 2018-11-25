package participants;

import loot.Armour;
import loot.Weapon;

public abstract class Contestant {
    int id;
    String name;
    Gender gender;
    double attack;
    double defence;
    double health;
    double luck;
    Weapon weapon;
    Armour armour;

    public Contestant(int id, String name, Gender gender, double attack, double defence, double health, double luck) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.attack = attack;
        this.defence = defence;
        this.health = health;
        this.luck = luck;
    }

    public void setWeapon(Weapon weapon) {
        if (this.weapon == null) {
            this.weapon = weapon;
        } else if (this.weapon.getAttack() < weapon.getAttack()) {
            this.weapon = weapon;
        }
    }

    public void setArmour(Armour armour) {
        if (this.armour == null){
            this.armour = armour;
        } else if (this.armour.getDefence() < armour.getDefence()) {
            this.armour = armour;
        }
    }

    @Override
    public String toString() {
        return "Deelnemer " + id + " met de volgende gegevens. Naam: " + name + " | Gender: " + gender + " | Attack: "
                + attack + " | Defence: " + defence + " | Health: " + health + " | Luck: " + luck;
    }

    public double getAttack() {
        return weapon == null ? attack : attack + weapon.getAttack();
    }

    public double getDefence() {
        return armour == null ? defence : defence + armour.getDefence();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Gender getGender() {
        return gender;
    }

    public double getHealth() {
        return health;
    }

    public double getLuck() {
        return luck;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public Armour getArmour() {
        return armour;
    }
}
