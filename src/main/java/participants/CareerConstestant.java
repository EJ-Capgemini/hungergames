package participants;

import loot.Armour;
import loot.ArmourChoice;
import loot.Weapon;
import loot.WeaponChoice;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class CareerConstestant extends Contestant implements Role{

    public CareerConstestant(int id, String name, Gender gender, double attack, double defence, double health, double luck) {
        super(id, name, gender, attack, defence, health, luck);

        getRoleBonus();
        addStartingGear();
    }

    private void addStartingGear(){
        int randomWeaponOrArmour = (int)(Math.random() * 2);
        Random random = new Random();

        if (randomWeaponOrArmour == 0){
            List<WeaponChoice> choices = Collections.unmodifiableList(Arrays.asList(WeaponChoice.values()));
            WeaponChoice choice = choices.get(random.nextInt(choices.size()));
            Weapon weapon = new Weapon(choice, choice.getDps());
            setWeapon(weapon);
        } else {
            List<ArmourChoice> choices = Collections.unmodifiableList(Arrays.asList(ArmourChoice.values()));
            ArmourChoice choice = choices.get(random.nextInt(choices.size()));
            Armour armour = new Armour(choice, choice.getDefence());
            setArmour(armour);
        }
    }

    public void getRoleBonus(){
        int attackBonus = (int)(Math.random() * 11) + 5;
        this.attack += attackBonus;
    }
}
