package fr.campus.characters;
import fr.campus.equipment.*;

public class Character {
    private final String name;
    private int attack;
    private int life;
    private int position;
    private final String offensiveEquipmentType;

    //For the beginning :
    private final String type;

    //For the next steps :
    //private Equipment.OffensiveEquipment;

    public Character(String type, String name, int life, int attack, String offensiveEquipmentType){
        this.type = type;
        this.name = name;
        this.life = life;
        this.attack = attack;
        this.offensiveEquipmentType = offensiveEquipmentType;
    }

    public String getType() {
        return this.type;
    }

    public String getName(){
        return this.name;
    }

    public int getLife(){
        return this.life;
    }
    public void setLife(int newLife){
        this.life = newLife;
    }

    public int getAttack(){
        return this.attack;
    }
    public void setAttack(int newAttack){
        this.attack = newAttack;
    }

    public int getPosition(){
        return this.position;
    }
    public void setPosition(int newPosition){
        this.position = newPosition;
    }

    public String getOffensiveEquipmentType(){
        return this.offensiveEquipmentType;
    }

    @Override
    public String toString() {
        return "Le personnage " + this.name + " est un " + this.type + ".\n" +
                "Il possède " + this.life + " points de vie.";
    }
}
