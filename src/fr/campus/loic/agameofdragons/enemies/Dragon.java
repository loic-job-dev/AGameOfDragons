package fr.campus.loic.agameofdragons.enemies;

/**
 * represents a dragon that can be encountered on the board
 */
public class Dragon extends Enemy{

    /**
     * The constructor sets generic values for this enemy
     */
    public Dragon(){
        super();
        this.name = "dragon";
        this.attack = 4;
        this.life = 15;
    }
}