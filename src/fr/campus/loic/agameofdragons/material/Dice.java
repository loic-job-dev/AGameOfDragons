package fr.campus.loic.agameofdragons.material;

/**
 * This class represent a dice that the player can roll to play.
 */
public class Dice {
    private int faces;

    /**
     * The constructor sets a number of faces
     *
     * @param faces is the highest value that can be obtained
     */
    public Dice (int faces){
        this.faces = faces;
    }

    public int getFaces() {
        return this.faces;
    }

    /**
     * This method roll the dice
     *
     * @return an integer between 1 and the maximal number on the dice
     */
    public int rollDice() {
        return (int)(Math.random() * (this.faces)+1);
    }
}
