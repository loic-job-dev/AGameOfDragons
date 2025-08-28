package fr.campus.loic.agameofdragons.characters;

import fr.campus.loic.agameofdragons.equipment.DefensiveEquipment;
import fr.campus.loic.agameofdragons.equipment.OffensiveEquipment;
import fr.campus.loic.agameofdragons.equipment.Shield;
import fr.campus.loic.agameofdragons.equipment.Weapon;

public class Warrior extends Character {

    protected Weapon weapon;
    protected Shield shield;

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
        this.offensiveEquipmentType = "armes";
        this.offensiveEquipment = new Weapon("Epée de base", 0);
        this.defensiveEquipmentType = "boucliers";
        this.defensiveEquipment = new Shield("bouclier de base", 0);
    }
}

