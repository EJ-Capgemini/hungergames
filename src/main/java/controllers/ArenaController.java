package controllers;

import com.github.javafaker.Faker;
import participants.CareerConstestant;
import participants.Contestant;
import participants.DistrictContestant;
import participants.Gender;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class ArenaController {
    private List<Contestant> contestants;
    private List<Contestant>fightingsContestants;
    private int currentDay = 1;
    private GearController gc;

    public ArenaController() {
        gc = new GearController();
    }

    public void iniatiateSimulation(){
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

        //Dagen spelen.
        continueSimulation();
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

    public void continueSimulation(){
        System.out.println("Complete day " + currentDay + "? Type yes to proceed.");

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        //Voor de ervaring hoofdletters en spaties corrigeren.
        switch(input.toLowerCase().replaceAll("\\s","")){
            case "yes":
            case "y":
                completeNextDay();
                break;
            case "exit":
                break;
            default:
                System.out.println("Alright, no problem. Take your time. Type yes when you're ready or type exit to stop.");
                break;
        }
    }

    public void completeNextDay(){
        fightEachother();

        int remaining = contestants.size();
        if (remaining > 1) {
            System.out.println("Day " + currentDay + " has just completed! There are " + contestants.size() + " contestants remaining!");
            for (Contestant contestant : contestants) {
                gc.takeLoot(contestant);
                System.out.println(contestant.getName() + " alive with " + contestant.getHealth() + " / " + contestant.getMaxHealth() + " health");
            }
            System.out.println("Everyone else DIED! R.I.P.");
            currentDay++;
            continueSimulation();
        } else {
            System.out.println("We have a winner ladies and gentleman! Please make a lot of noise for.. " + contestants.get(0).getName().toUpperCase() + "!!");
        }
    }

    /*
        Aanpak. Er wordt een even aantal random gekozen dat gaat vechten.
     */
    public void fightEachother(){
        int numberFighting = (int)(Math.random() * (contestants.size()) - 1);
        numberFighting = numberFighting + 2; //altijd minstens 1 gevecht. Handig voor testen.
        if (numberFighting % 2 != 0){
            numberFighting--;
        }
        int fights = numberFighting / 2;
        //kopie maken welke gebruikt wordt voor de gevechten.
        fightingsContestants = new ArrayList<>(contestants);
        Collections.shuffle(fightingsContestants);

        for (int i = 0; i < numberFighting; i = i + 2) {
            oneVersusOneBattle(fightingsContestants.get(i), fightingsContestants.get(i + 1));
        }

//        System.out.println(numberFighting + " contestants fought. Remaining: " + contestants.size());
    }

    /*
        Simulatie waarbij spelers elkaar om de beurt aanvallen. c1 begint altijd.
        HOOP DUBELE CODE HIER.. En vast op andere plekken. Gezien de tijd niet opgeruimd.
     */
    private void oneVersusOneBattle(Contestant c1, Contestant c2){
        boolean someoneDied = false;
        System.out.println("Fight between " + c1.getName() + " and " + c2.getName());
        while (!someoneDied){
            //schade is attack - armour. Rekening ermee houden dat het niet onder de 0 kan.
            double damageTaken = (c1.getAttack() - c2.getDefence()) > 0 ? c1.getAttack() - c2.getDefence() : 0;

            //Onafhankelijk van defence speelt het lot ook een rol.
            if ((int)(Math.random() * 101) < c1.getLuck()){
                damageTaken += (int)(Math.random() * 11);
            }
            c2.setHealth(c2.getHealth() - damageTaken);

            if (c2.getHealth() > 0){
                damageTaken = (c2.getAttack() - c1.getDefence()) > 0 ? c2.getAttack() - c1.getDefence() : 0;
                //c2 heeft kans om dubbele schade te doen.
                if ((int)(Math.random() * 101) < c2.getLuck()){
                    damageTaken = damageTaken * 2;
                }
                c1.setHealth(c1.getHealth() - damageTaken);
            } else {
                someoneDied = true;
                contestants.remove(c2);
                System.out.println(c1.getName() + " won the fight with " + c1.getHealth() + " health remaining.");
            }

            if (c1.getHealth() <= 0){
                someoneDied = true;
                contestants.remove(c1);
                System.out.println(c2.getName() + " won the fight with " + c2.getHealth() + " health remaining.");
            }
        }
    }


}
