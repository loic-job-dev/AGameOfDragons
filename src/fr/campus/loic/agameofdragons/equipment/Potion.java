package fr.campus.loic.agameofdragons.equipment;

/**
 * This class is the defensive equipment dedicated to the magician
 */
public class Potion extends DefensiveEquipment {
    /**
     * The constructor of the class that sets some parameters
     *
     * @param name is the name of the equipment
     * @param defenseBuff represents the value of the bonus it gives to the owner
     */
    public Potion(String name, int defenseBuff) {
        super(name);
        this.owner = "magicien";
        this.defenseBuff = defenseBuff;
    }
}