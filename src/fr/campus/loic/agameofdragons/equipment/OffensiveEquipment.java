package fr.campus.loic.agameofdragons.equipment;

/**
 * This class represent offensive equipment that can be given to a character
 */
public abstract class OffensiveEquipment {
    protected String owner;
    protected String name;
    protected int attackBuff;

    /**
     * The constructor of the class that sets the name of the object
     *
     * @param name is the name of the equipment
     */
    public OffensiveEquipment (String name){
        this.name = name;
    }

    public String getOwner(){
        return this.owner;
    }

    public String getName(){
        return this.name;
    }

    public int getAttackBuff(){
        return this.attackBuff;
    }

    @Override
    public String toString() {
        return this.name + ", réservée au " + this.owner + ", qui booste l'attaque de " + this.attackBuff + " points.";
    }
}