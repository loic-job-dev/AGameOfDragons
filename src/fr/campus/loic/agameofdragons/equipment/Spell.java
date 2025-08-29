package fr.campus.loic.agameofdragons.equipment;

/**
 * This class is the offensive equipment dedicated to the magician
 */
public class Spell extends OffensiveEquipment{
    /**
     * The constructor of the class that sets some parameters
     *
     * @param name is the name of the equipment
     * @param attackBuff represents the value of the bonus it gives to the owner
     */
    public Spell(String name, int attackBuff) {
        super(name);
        this.owner = "magicien";
        this.attackBuff = attackBuff;
    }
}