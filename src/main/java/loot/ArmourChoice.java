package loot;

public enum ArmourChoice {
    WEAK(5),
    AVERAGE(10),
    STRONG(15);

    private int defence;

    ArmourChoice(int defence) {
        this.defence = defence;
    }

    public int getDefence() {
        return defence;
    }
}
