package fr.campus.loic.agameofdragons.equipment;

/**
 * This class represent defensive equipment that can be given to a character
 */
public abstract class DefensiveEquipment {
    protected String owner;
    protected String name;
    protected int defenseBuff;

    /**
     * The constructor of the class that sets some parameters
     *
     * @param name is the name of the equipment
     */
    public DefensiveEquipment (String name){
        this.name = name;
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
        return this.name + ", réservée au " + this.owner + ", et qui booste la défense de " + defenseBuff + " points.";
    }
}
