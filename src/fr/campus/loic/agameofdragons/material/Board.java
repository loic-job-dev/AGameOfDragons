package fr.campus.loic.agameofdragons.material;

/**
 *This class represents the board where the character moves
 */
public class Board {
    private int numTiles;

    /**
     * The constructor sets a number of tiles
     *
     * @param numTiles is the value of the last tile
     */
    public Board(int numTiles){
        this.numTiles = numTiles;
    }

    public int getNumTiles(){
        return this.numTiles;
    }
}
