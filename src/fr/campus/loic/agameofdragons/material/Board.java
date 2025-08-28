package fr.campus.loic.agameofdragons.material;

import fr.campus.loic.agameofdragons.equipment.Shield;
import fr.campus.loic.agameofdragons.equipment.Spell;

import java.util.ArrayList;

/**
 *This class represents the board where the character moves
 */
public class Board {
    private int numTiles;
    protected ArrayList<Cell> tiles;


    /**
     * The constructor sets a number of tiles
     *
     * @param numTiles is the value of the last tile
     */
    public Board(int numTiles){
        this.numTiles = numTiles;
        this.tiles = new ArrayList<Cell>(numTiles);
        Cell cell1 = new OffensiveBonusCell(new Spell("toucher nécrotique", 4));
        Cell cell2 = new OffensiveBonusCell(new Spell("flamèche", 2));
        Cell cell3 = new EnnemyCell();

        for (int i = 0; i < numTiles; i++) {
            tiles.add(new EmptyCell());
        }

        tiles.add(1, cell1);
        tiles.add(2, cell2);
        tiles.add(3, cell3);
    }

    public int getNumTiles(){
        return this.numTiles;
    }

    public ArrayList<Cell> getTiles() {
        return this.tiles;
    }
}
