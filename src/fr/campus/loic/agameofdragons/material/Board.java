package fr.campus.loic.agameofdragons.material;

import fr.campus.loic.agameofdragons.equipment.Potion;
import fr.campus.loic.agameofdragons.equipment.Shield;
import fr.campus.loic.agameofdragons.equipment.Spell;
import fr.campus.loic.agameofdragons.equipment.Weapon;
import fr.campus.loic.agameofdragons.tools.BoardGenerator;

import java.util.ArrayList;
import java.util.List;

/**
 *This class represents the board where the character moves
 */
public class Board {
    private int id;
    private int numTiles;
    private List<Cell> tiles;


    /**
     * The constructor sets a number of tiles, and adds specific tiles (bonuses and enemies).
     *
     * @param numTiles is the value of the last tile
     */
    public Board(int numTiles){
        this.numTiles = numTiles;

        BoardGenerator bg = new BoardGenerator(numTiles);
        this.tiles = bg.generate();
    }

    public int getNumTiles(){
        return this.numTiles;
    }

    public List<Cell> getTiles() {
        return this.tiles;
    }

    //Getter and setter for the Id Database
    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return this.id;
    }
}