package fr.campus.loic.agameofdragons.equipment;

/**
 * This class represent offensive equipment that can be given to a character
 */
public class OffensiveEquipment {
    private final String type;
    private final String name;
    private final int attackBuff;

    /**
     * The constructor of the class that sets some parameters
     *
     * @param type is the type of offensive equipment
     * @param name is the name of the equipment
     * @param attackBuff represents the value of the bonus it gives to the owner
     */
    public OffensiveEquipment (String type, String name, int attackBuff){
        this.type = type;
        this.name = name;
        this.attackBuff = attackBuff;
    }

    public String getType(){
        return this.type;
    }

    public String getName(){
        return this.name;
    }

    public int getAttackBuff(){
        return this.attackBuff;
    }

    @Override
    public String toString() {
        return "Cet équipement de type " + type + " nommé " + name + " booste l'attaque de " + attackBuff + " points.";
    }
}
