public class DefensiveEquipment {
    private final String type;
    private final String name;
    private final int defenseBuff;

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
}
