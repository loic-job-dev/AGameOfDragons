package fr.campus.loic.agameofdragons.characters;

import fr.campus.loic.agameofdragons.tools.ConsoleColors;

/**
 * This class represent the character that the player will play with.
 */
public class Character {
    private String name;
    private int attack;
    private int life;
    private int position;
    private final String offensiveEquipmentType;

    //For the beginning :
    private final String type;

    //For the next steps :
    //private Equipment.OffensiveEquipment;

    /**
     * The constructor of the class that sets some parameters
     *
     * @param type is the type of the character
     * @param name is the name of the character
     * @param life represents the lifepoints of the character
     * @param attack represents the value of the attack, the strenght, of the character
     * @param offensiveEquipmentType gives an indication of the equipement that the character can equip, depending on the type.
     */
    public Character(String type, String name, int life, int attack, String offensiveEquipmentType){
        this.type = type;
        this.name = name;
        this.life = life;
        this.attack = attack;
        this.offensiveEquipmentType = offensiveEquipmentType;
        this.position = 0;
    }

    public String getType() {
        return type;
    }

    public String getName(){
        return name;
    }
    public void setName(String newName){
        name = newName;
    }

    public int getLife(){
        return life;
    }
    public void setLife(int newLife){
        life = newLife;
    }

    public int getAttack(){
        return attack;
    }
    public void setAttack(int newAttack){
        attack = newAttack;
    }

    public int getPosition(){
        return position;
    }
    public void setPosition(int newPosition){
        position = newPosition;
    }

    public String getOffensiveEquipmentType(){
        return offensiveEquipmentType;
    }

    @Override
    public String toString() {
        return String.format(ConsoleColors.PURPLE + """
        Le personnage %s est un %s.
        Il possède %d points de vie.
        Il peut s'équiper de %s.
        Sur le plateau, il se trouve à la case %d.
        """, this.name, this.type, this.life, this.offensiveEquipmentType, this.position);
    }

}
