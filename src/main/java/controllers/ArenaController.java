package controllers;

import com.github.javafaker.Faker;
import loot.*;
import participants.CareerConstestant;
import participants.Contestant;
import participants.DistrictContestant;
import participants.Gender;

import java.util.ArrayList;
import java.util.List;

public class ArenaController {
    private List<Contestant> contestants;

    public ArenaController() {

    }

    public void playSimulation(){
        //Aanmaken van lijst en weergeven.
        findNewContestants();
        System.out.println("Ladies and gentleman, please welcome our newest victi.. err. contestants for today.");
        String names = "";
        int i = 0;
        for (Contestant contestant : contestants) {
            names += contestant.getName();
            i++;
            if (i == contestants.size()){
                names += ".";
            } else {
                names += ", ";
            }
        }
        System.out.println(names);


    }
    
    private void findNewContestants(){
        Faker faker = new Faker();
        contestants = new ArrayList<Contestant>();

        for (int i = 0; i < 24; i++) {

            String name = faker.name().firstName();
            Gender gender = (i % 2 == 0) ? Gender.MALE : Gender.FEMALE;
            double attack = (Math.random() * 15) + 30;
            double defence = (Math.random() * 10) + 10;
            double health = (Math.random() * 50) + 80;
            double luck = Math.random() * 30;

            int random = (int)(Math.random() * 4); //0, 1, 2 of 3.
            if (random == 0){
                Contestant careerConstestant = new CareerConstestant(i, name, gender, attack, defence, health, luck);
                contestants.add(careerConstestant);
            } else {
                Contestant districtContestant = new DistrictContestant(i, name, gender, attack, defence, health, luck);
                contestants.add(districtContestant);
            }
        }
    }
}
