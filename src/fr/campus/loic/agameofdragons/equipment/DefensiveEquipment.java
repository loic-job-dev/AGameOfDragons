package fr.campus.loic.agameofdragons.equipment;

/**
 * This class represent defensive equipment that can be given to a character
 */
public class DefensiveEquipment {
    private final String type;
    private final String name;
    private final int defenseBuff;

    /**
     * The constructor of the class that sets some parameters
     *
     * @param type is the type of defensive equipment
     * @param name is the name of the equipment
     * @param defenseBuff represents the value of the bonus it gives to the owner
     */
    public DefensiveEquipment (String type, String name, int defenseBuff){
        this.type = type;
        this.name = name;
        this.defenseBuff = defenseBuff;
    }

    public String getType(){
        return this.type;
    }

    public String getName(){
        return this.name;
    }

    public int getDefenseBuff(){
        return this.defenseBuff;
    }

    @Override
    public String toString() {
        return "Cet équipement de type " + type + " nommé " + name + " booste la défense de " + defenseBuff + " points.";
    }
}
