package fr.campus.loic.agameofdragons.enemies;

/**
 * represents a gobelin that can be encountered on the board
 */
public class Gobelin extends Enemy{

    /**
     * The constructor sets generic values for this enemy
     */
    public Gobelin(){
        super();
        this.name = "gobelin";
        this.attack = 1;
        this.life = 6;
    }
}