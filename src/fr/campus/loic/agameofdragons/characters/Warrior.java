package fr.campus.loic.agameofdragons.characters;

import fr.campus.loic.agameofdragons.tools.ConsoleColors;

public class Warrior extends Character {
    /**
     * The constructor of the class that sets some parameters
     *
     * @param name is the name of the character
     */
    public Warrior(String name) {
        super(name);
        this.type = "guerrier";
        this.attack = 5;
        this.life = 10;
        this.offensiveEquipmentType = "bouclier";
    }
}
