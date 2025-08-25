package fr.campus.material;

public class Dice {
    private int faces;

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
