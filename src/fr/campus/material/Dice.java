package fr.campus.material;

public class Dice {
    private int faces;

    public Dice (int faces){
        this.faces = faces;
    }

    public int getFaces() {
        return this.faces;
    }

    public int rollDice() {
        return (int)(Math.random() * (this.faces+1));
    }
}
