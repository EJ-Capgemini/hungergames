package participants;

import loot.*;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public abstract class Contestant {
    int id;
    String name;
    Gender gender;
    double attack;
    double defence;
    double health;
    double maxHealth;
    double luck;
    Weapon weapon;
    Armour armour;

    boolean inBattle = false;

    public Contestant(int id, String name, Gender gender, double attack, double defence, double health, double luck) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.attack = attack;
        this.defence = defence;
        this.health = health;
        this.maxHealth = health;
        this.luck = luck;
    }

    public void lootWeapon(){
        Random random = new Random();
        List<WeaponChoice> choices = Collections.unmodifiableList(Arrays.asList(WeaponChoice.values()));
        WeaponChoice choice = choices.get(random.nextInt(choices.size()));
        Weapon weapon = new Weapon(choice, choice.getDps());
        setWeapon(weapon);
    }

    public void lootArmour(){
        Random random = new Random();
        List<ArmourChoice> choices = Collections.unmodifiableList(Arrays.asList(ArmourChoice.values()));
        ArmourChoice choice = choices.get(random.nextInt(choices.size()));
        Armour armour = new Armour(choice, choice.getDefence());
        setArmour(armour);
    }

    public void setHealth(double health) {
        this.health = health > maxHealth ? maxHealth : health;
    }

    public void setMaxHealth(double maxHealth) {
        this.maxHealth = maxHealth;
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
        return Math.round(weapon == null ? attack : attack + weapon.getAttack());
    }

    public double getDefence() {
        return Math.round(armour == null ? defence : defence + armour.getDefence());
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
        return Math.round(health);
    }

    public double getMaxHealth() {
        return Math.round(maxHealth);
    }

    public double getLuck() {
        return Math.round(luck);
    }

    public boolean isInBattle() {
        return inBattle;
    }
}
