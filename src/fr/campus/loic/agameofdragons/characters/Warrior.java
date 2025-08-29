package fr.campus.loic.agameofdragons.characters;

import fr.campus.loic.agameofdragons.equipment.Shield;
import fr.campus.loic.agameofdragons.equipment.Weapon;

/**
 * This class represents a specific character that the player can choose to play with.
 */
public class Warrior extends Character {

    protected Weapon weapon;
    protected Shield shield;

    /**
     * The constructor of the class that sets the correct attributes for a warrior
     *
     * @param name is the name of the character
     */
    public Warrior(String name) {
        super(name);
        this.type = "guerrier";
        this.attack = 5;
        this.life = 10;
        this.offensiveEquipmentType = "armes";
        this.offensiveEquipment = new Weapon("Epée de base", 0);
        this.defensiveEquipmentType = "boucliers";
        this.defensiveEquipment = new Shield("bouclier de base", 0);
    }
}