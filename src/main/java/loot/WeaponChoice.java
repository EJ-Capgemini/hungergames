package loot;

public enum WeaponChoice {
    SWORD (30),
    SPEAR (25),
    AXE (15),
    BOW (25);

    int dps; //Damage per second default

    WeaponChoice(int dps){
        this.dps = dps;
    }

    public int getDps() {
        return dps;
    }
}
