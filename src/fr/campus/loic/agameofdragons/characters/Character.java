package fr.campus.loic.agameofdragons.characters;

import fr.campus.loic.agameofdragons.equipment.*;
import fr.campus.loic.agameofdragons.exceptions.WrongEquipmentException;
import fr.campus.loic.agameofdragons.tools.ConsoleColors;

/**
 * This class represent the base of a character that the player will play with.
 */
public abstract class Character {
    protected String name;
    protected int attack;
    protected int life;
    protected int position;
    protected OffensiveEquipment offensiveEquipment;
    protected DefensiveEquipment defensiveEquipment;

    //To display the toString method in french :
    protected String type;
    protected String offensiveEquipmentType;
    protected String defensiveEquipmentType;


    /**
     * The constructor of the class that sets the name of the character, determinate by the player
     *
     * @param name is the name of the character
     */
    public Character(String name){
        this.name = name;
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
    public OffensiveEquipment getOffensiveEquipment() {
        return this.offensiveEquipment;
    }

    /**
     * This method checks if a character is allowed to equip an offensive item, and assigns it if it is better than the current equipment
     *
      * @param offensiveEquipment is the equipment found by the player
     * @throws WrongEquipmentException is the exception thrown if the character can't equip this kind of item
     */
    public void setOffensiveEquipment(OffensiveEquipment offensiveEquipment) throws WrongEquipmentException {
        if (!this.type.equals(offensiveEquipment.getOwner())) {
            throw new WrongEquipmentException("\nTu as trouvé un(e) " + offensiveEquipment.getName() + ", mais cet équipement ne correspond pas à ton personnage, dommage !");
        }
        //Equipment if better bonus
        int newAttack = this.attack + offensiveEquipment.getAttackBuff();
        if (newAttack > this.attack) {
            this.offensiveEquipment = offensiveEquipment;
            this.attack = newAttack;
        }
    }

    public String getDefensiveEquipmentType(){
        return defensiveEquipmentType;
    }
    public DefensiveEquipment getDefensiveEquipment(){
        return this.defensiveEquipment;
    }

    /**
     * This method checks if a character is allowed to equip a defensive item, and assigns it if it is better than the current equipment
     *
     * @param defensiveEquipment is the equipment found by the player
     * @throws WrongEquipmentException is the exception thrown if the character can't equip this kind of item
     */
    public void setDefensiveEquipment(DefensiveEquipment defensiveEquipment) throws WrongEquipmentException {
        if (!this.type.equals(defensiveEquipment.getOwner())) {
            throw new WrongEquipmentException("\nTu as trouvé un(e) " + defensiveEquipment.getName() + ", mais cet équipement ne correspond pas à ton personnage, dommage !\n");
        }
        //Equipment if better bonus
        if (defensiveEquipment != null) {
            this.defensiveEquipment = defensiveEquipment;
        }
        else if (defensiveEquipment.getDefenseBuff() > this.defensiveEquipment.getDefenseBuff()) {
            this.defensiveEquipment = defensiveEquipment;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format(ConsoleColors.PURPLE + """
        \nLe personnage %s est un %s.
        Il possède %d points de vie.
        Il peut s'équiper de : "%s" et de : "%s".
        Son attaque actuelle est de %d
        Sur le plateau, il se trouve à la case %d.
        """, this.name, this.type, this.life, this.offensiveEquipmentType, this.defensiveEquipmentType, this.attack, this.position));

        if (this.offensiveEquipment != null) {
            sb.append("Il est équipé de : ").append(this.offensiveEquipment.toString()).append("\n");
        }

        if (this.defensiveEquipment != null) {
            sb.append("Il se protège avec : ").append(this.defensiveEquipment.toString()).append("\n");
        }

        return sb.toString();
    }
}