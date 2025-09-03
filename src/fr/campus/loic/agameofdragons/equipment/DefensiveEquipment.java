package fr.campus.loic.agameofdragons.equipment;

/**
 * This class represent defensive equipment that can be given to a character
 */
public abstract class DefensiveEquipment {
    private String name;
    private String owner;
    private int defenseBuff;

    /**
     * The constructor of the class that sets the name of the object
     *
     * @param name is the name of the equipment
     */
    public DefensiveEquipment (String name, String owner, int defenseBuff) {
        this.name = name;
        this.owner = owner;
        this.defenseBuff = defenseBuff;
    }

    public String getOwner(){
        return this.owner;
    }

    public String getName(){
        return this.name;
    }

    public int getDefenseBuff(){
        return this.defenseBuff;
    }

    @Override
    public String toString() {
        return this.name + ", réservée au " + this.owner + ", qui booste la défense de " + defenseBuff + " points.";
    }
}