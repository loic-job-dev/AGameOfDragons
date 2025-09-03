package fr.campus.loic.agameofdragons.characters;

import fr.campus.loic.agameofdragons.equipment.Potion;
import fr.campus.loic.agameofdragons.equipment.Spell;

/**
 * This class represents a specific character that the player can choose to play with.
 */
public class Magician extends Character {

    /**
     * The constructor of the class that sets the correct attributes for a magician
     *
     * @param name is the name of the character
     */
    public Magician(String name) {
        super(name, 8, 6, new Spell("sort basique", 0), new Potion("philtre fade", 0), "magicien", "sorts", "philtres");
    }
}