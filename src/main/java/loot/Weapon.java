package loot;

public class Weapon {
    private WeaponChoice choice;
    private double attack;

    public Weapon(WeaponChoice choice, double attack) {
        this.choice = choice;

        int randomDpsModifier = (int)(Math.random() * 11) - 5;
        this.attack = attack + randomDpsModifier;
    }

    public double getAttack() {
        return attack;
    }
}
