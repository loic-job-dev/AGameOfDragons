package fr.campus.loic.agameofdragons.characters;

import fr.campus.loic.agameofdragons.equipment.Potion;
import fr.campus.loic.agameofdragons.equipment.Spell;
import fr.campus.loic.agameofdragons.tools.ConsoleColors;

public class Magician extends Character {

    protected Spell spell;
    protected Potion potion;

    /**
     * The constructor of the class that sets some parameters
     *
     * @param name                   is the name of the character
     */
    public Magician(String name) {
        super(name);
        this.type = "magicien";
        this.attack = 8;
        this.life = 6;
        this.offensiveEquipmentType = "philtre";
    }
}
