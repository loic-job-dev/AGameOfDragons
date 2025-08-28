package fr.campus.loic.agameofdragons.material;

import fr.campus.loic.agameofdragons.equipment.Shield;
import fr.campus.loic.agameofdragons.equipment.Spell;
import fr.campus.loic.agameofdragons.equipment.Weapon;

import java.util.ArrayList;

/**
 *This class represents the board where the character moves
 */
public class Board {
    private int numTiles;
    protected ArrayList<Cell> tiles;


    /**
     * The constructor sets a number of tiles, and adds specific tiles.
     *
     * @param numTiles is the value of the last tile
     */
    public Board(int numTiles){
        this.numTiles = numTiles;

        //Creation of the board
        this.tiles = new ArrayList<Cell>(numTiles+1);
        //Initialize a board with empty tiles
        for (int i = 0; i < numTiles; i++) {
            tiles.add(new EmptyCell());
        }

        //Addition of j bonuses on the board
        for (int j = 0; j < 25; j++) {
            int index = (int)(Math.random() * (numTiles));
            if (tiles.get(index) instanceof EmptyCell) {
                //Addition of 10 identical spells
                if (j < 10) {
                    tiles.set(index, new OffensiveBonusCell(new Spell("rayon patriotique", 200)));
                }
                //Addition of 10 identical weapons
                if (j >= 10 && j < 20) {
                    tiles.set(index, new OffensiveBonusCell(new Weapon("drapeau démocratique", 200)));
                }
            } else {
                j--;
            }
        }

        /*
        Idée de répartition "équilibrée"
                | Catégorie                     | Nb cases |
                | ----------------------------- | -------- |
                | Armes moyennes guerrier       | 3        |
                | Armes fortes guerrier         | 3        |
                | Protections moyennes guerrier | 3        |
                | Protections fortes guerrier   | 3        |
                | Armes moyennes mage           | 3        |
                | Armes fortes mage             | 3        |
                | Protections moyennes mage     | 3        |
                | Protections fortes mage       | 3        |
                | Ennemi faible                 | 5        |
                | Ennemi moyen                  | 6        |
                | Ennemi fort                   | 5        |
                | Cases vides                   | 18       |

         */

    }

    public int getNumTiles(){
        return this.numTiles;
    }

    public ArrayList<Cell> getTiles() {
        return this.tiles;
    }
}
