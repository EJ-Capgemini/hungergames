package participants;

public class DistrictContestant extends Contestant implements Role{

    public DistrictContestant(int id, String name, Gender gender, double attack, double defence, double health, double luck) {
        super(id, name, gender, attack, defence, health, luck);

        getRoleBonus();
    }

    public void getRoleBonus(){
        int defenceBonus = (int)(Math.random() * 11) + 5;
        this.defence += defenceBonus;
    }
}
