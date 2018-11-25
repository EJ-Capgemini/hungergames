package loot;

public class Food {
    private int healAmount;
    private boolean used = false;

    public Food() {
        int healAmount = (int)(Math.random() * 31) + 10;
        this.healAmount = healAmount;
    }

    public int getHealAmount() {
        return healAmount;
    }

    public void isUsed(){
        this.used = true;
    }
}
