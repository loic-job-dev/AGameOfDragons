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
        this.offensiveEquipmentType = "arme";
        this.defensiveEquipmentType = "bouclier";
    }

    /**
     * This method allows the character to equip the good kind of offensive equipment
     *
     * @param offensiveEquipment is the offensive equipment that will be equipped if allowed
     */
    @Override
    public void setOffensiveEquipment(OffensiveEquipment offensiveEquipment) {
        if (offensiveEquipment.getOwner().equals("guerrier")) {
            super.setOffensiveEquipment(offensiveEquipment);
            this.attack = 6 + offensiveEquipment.getAttackBuff();
        }
    }

    /**
     * This method allows the character to equip the good kind of defensive equipment
     *
     * @param defensiveEquipment is the defensive equipment that will be equipped if allowed
     */
    @Override
    public void setDefensiveEquipment(DefensiveEquipment defensiveEquipment) {
        if (defensiveEquipment.getOwner().equals("guerrier")) {
            super.setDefensiveEquipment(defensiveEquipment);
        }
    }
}

