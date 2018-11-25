package loot;

public class Armour {
    private ArmourChoice choice;
    private double defence;

    public Armour(ArmourChoice choice, double defence) {
        this.choice = choice;

        int randomArmourModifier = (int)(Math.random() * 16) - 5;
        this.defence = defence + randomArmourModifier;
    }

    public double getDefence() {
        return defence;
    }
}
