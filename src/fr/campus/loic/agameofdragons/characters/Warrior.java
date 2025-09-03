package fr.campus.loic.agameofdragons.characters;

import fr.campus.loic.agameofdragons.equipment.Potion;
import fr.campus.loic.agameofdragons.equipment.Shield;
import fr.campus.loic.agameofdragons.equipment.Spell;
import fr.campus.loic.agameofdragons.equipment.Weapon;

/**
 * This class represents a specific character that the player can choose to play with.
 */
public class Warrior extends Character {

    /**
     * The constructor of the class that sets the correct attributes for a warrior
     *
     * @param name is the name of the character
     */
    public Warrior(String name) {
        super(name, 5, 10, new Weapon("Epée de base", 0), new Shield("bouclier de base", 0), "guerrier", "armes", "boucliers");
    }


}