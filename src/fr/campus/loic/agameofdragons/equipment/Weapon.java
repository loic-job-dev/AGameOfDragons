package fr.campus.loic.agameofdragons.equipment;

public class Weapon extends OffensiveEquipment {
    /**
     * The constructor of the class that sets some parameters
     *
     * @param name       is the name of the equipment
     * @param attackBuff represents the value of the bonus it gives to the owner
     */
    public Weapon(String name, int attackBuff) {
        super(name);
        this.owner = "guerrier";
        this.attackBuff = attackBuff;
    }
}
