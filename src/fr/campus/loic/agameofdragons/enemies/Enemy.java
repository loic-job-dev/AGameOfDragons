package fr.campus.loic.agameofdragons.enemies;

/**
 * Represent an enemy that can be encountered on the board
 */
public abstract class Enemy {

    private String name;
    private int attack;
    private int life;

    public Enemy (String name, int attack, int life) {
        this.name = name;
        this.attack = attack;
        this.life = life;
    }

    public String getName(){
        return this.name;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }
    public int getAttack() {
        return this.attack;
    }

    public void setLife(int life) {
        this.life = life;
    }
    public int getLife() {
        return this.life;
    }

    @Override
    public String toString() {
        return "Cet ennemi est un(e) " + this.name + " qui a une attaque à " + this.attack + " et possède " + this.life + " points de vie";
    }
}
