package loot;

public class Food {
    private int healAmount;
    private boolean used = false;

    public Food() {
        int healAmount = (int)(Math.random() * 16) + 10;
        this.healAmount = healAmount;
    }

    public int eat() {
        this.used = true;
        return healAmount;
    }

    public boolean isUsed(){
        return used;
    }
}
