package fr.campus.loic.agameofdragons.characters;

import fr.campus.loic.agameofdragons.Menu;
import fr.campus.loic.agameofdragons.enemies.Enemy;
import fr.campus.loic.agameofdragons.equipment.*;
import fr.campus.loic.agameofdragons.exceptions.WrongEquipmentException;
import fr.campus.loic.agameofdragons.interfaces.IFight;
import fr.campus.loic.agameofdragons.material.Dice;
import fr.campus.loic.agameofdragons.tools.ConsoleColors;

/**
 * This class represent the base of a character that the player will play with.
 */
public abstract class Character implements IFight<Enemy> {
    private int id;
    private String name;
    private int attack;
    private int life;
    private int position;
    private OffensiveEquipment offensiveEquipment;
    private DefensiveEquipment defensiveEquipment;

    //To display the toString method in french :
    private final String type;
    private final String offensiveEquipmentType;
    private final String defensiveEquipmentType;

    private Menu menu = new Menu();
    private Dice dice20 = new Dice(20);

    /**
     * The constructor of the class that sets the name of the character, determinate by the player
     *
     * @param name is the name of the character
     */
    public Character(String name,
                     int attack,
                     int life,
                     OffensiveEquipment offensiveEquipment,
                     DefensiveEquipment defensiveEquipment,
                     String type,
                     String offensiveEquipmentType,
                     String defensiveEquipmentType){
        this.name = name;
        this.attack = attack;
        this.life = life;
        this.position = 0;
        this.offensiveEquipment = offensiveEquipment;
        this.defensiveEquipment = defensiveEquipment;
        this.type = type;
        this.offensiveEquipmentType = offensiveEquipmentType;
        this.defensiveEquipmentType = defensiveEquipmentType;
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

    //Getter and setter for the Id Database
    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return this.id;
    }

    /**
     * allows the Character to fight an enemy. It can set a new value for the life attribute of the Enemy.
     *
     * @param enemy is the Enemy fought
     */
    @Override
    public void fight(Enemy enemy) {
        menu.displayMessage("PV de l'ennemi : " + enemy.getLife());
        int result = dice20.rollDice();
        if (result != 1 && result != 20) {
            enemy.setLife(enemy.getLife() - this.attack);
        } else if (result == 1) {
            menu.displayMessage(ConsoleColors.BOLD_RED + "Echec critique ! " + this.name + " rate complètement son attaque !\n");
        } else if (result == 20) {
            menu.displayMessage(ConsoleColors.BOLD_GREEN + "Coup critique ! " + this.name + " va faire très mal...\n");
            enemy.setLife(enemy.getLife() - (this.attack+5));
        }
        if (enemy.getLife() < 0) {
            enemy.setLife(0);
        }
        if (result != 1) {
            menu.displayMessage(this.name + " frappe le/la " + enemy.getName() + ". Ses PV sont maintenant à " + enemy.getLife() + ".\n");
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