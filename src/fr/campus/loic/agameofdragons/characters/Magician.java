package fr.campus.loic.agameofdragons.characters;

import fr.campus.loic.agameofdragons.equipment.DefensiveEquipment;
import fr.campus.loic.agameofdragons.equipment.OffensiveEquipment;
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
        this.offensiveEquipmentType = "sort";
        this.defensiveEquipmentType = "philtre";
    }

    /**
     * This method allows the character to equip the good kind of offensive equipment
     *
     * @param offensiveEquipment is the offensive equipment that will be equipped if allowed
     */
    @Override
    public void setOffensiveEquipment(OffensiveEquipment offensiveEquipment) {
        if (offensiveEquipment.getOwner().equals("mage")) {
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
        if (defensiveEquipment.getOwner().equals("mage")) {
            super.setDefensiveEquipment(defensiveEquipment);
        }
    }
}
