package fr.campus.loic.agameofdragons.tools;

import fr.campus.loic.agameofdragons.enemies.Dragon;
import fr.campus.loic.agameofdragons.enemies.Enemy;
import fr.campus.loic.agameofdragons.enemies.Gobelin;
import fr.campus.loic.agameofdragons.enemies.Wizard;
import fr.campus.loic.agameofdragons.equipment.Potion;
import fr.campus.loic.agameofdragons.equipment.Shield;
import fr.campus.loic.agameofdragons.equipment.Spell;
import fr.campus.loic.agameofdragons.equipment.Weapon;
import fr.campus.loic.agameofdragons.material.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The BoardGenerator class is responsible for generating a random board.
 * The generator fills the board randomly while respecting the following proportions:
 * <ul>
 *     <li>Two-thirds of the tiles are special tiles (bonuses or enemies)</li>
 *     <li>Two-thirds of the special tiles are bonuses</li>
 *     <li>One-third of the special tiles are enemies</li>
 * </ul>
 * Bonuses are distributed evenly across 8 types (4 offensive, 4 defensive),
 * and enemies are distributed across 3 generic types.
 */
public class BoardGenerator {

    private List<Cell> tiles;
    private int numTiles;

    /**
     * Constructs a BoardGenerator for a board with the specified number of tiles.
     *
     * @param numTiles the total number of tiles for the board
     */
    public BoardGenerator(int numTiles) {
        this.numTiles = numTiles;
    }

    /**
     * Generates a random game board containing empty cells, offensive and defensive bonuses, and enemies.
     * Special tiles are never placed at index 0 to avoid occupying the first tile.
     *
     * @return the complete list of tiles for the generated board
     */
    public List<Cell> generate() {
        //Creation of the board
        this.tiles = new ArrayList<Cell>(numTiles + 1);
        //Initialize a board with empty tiles
        for (int i = 0; i < numTiles; i++) {
            tiles.add(new EmptyCell());
        }

        //Definition of a number of non-empty tiles (2/3 of the board)
        int specialTiles = 2 * numTiles / 3;

        //Definition of the proportions of each kind of tile
        int bonusTiles = 2 * specialTiles / 3; //(2/3 of the special tiles)
        int enemyTiles = specialTiles / 3; //(1/3 of the special tiles)

        //Definition of the number of each kind of bonuses and how many tiles for each bonus
        int varietyOfBonuses = 8;
        int numBonusTile = bonusTiles / varietyOfBonuses;

        //Definition of the number of each kind of enemy and how many tiles for each enemy
        int varietyOfEnemies = 3;
        int numEnemyTile = enemyTiles / varietyOfEnemies;

        //Creation of a list of indexes for the placement (from 1 to numTiles-1)
        List<Integer> indices = new ArrayList<>();
        for (int i = 1; i < numTiles; i++) {
            indices.add(i);
        }

        //Random shuffle of the indexes
        Collections.shuffle(indices);

        //Setting the bonuses
        int placed = 0;
        for (int j = 0; j < bonusTiles; j++) {
            int index = indices.get(placed++);
            if (j < numBonusTile) {
                tiles.set(index, new OffensiveBonusCell(new Spell("boule de feu", 7)));
            } else if (j < 2 * numBonusTile) {
                tiles.set(index, new OffensiveBonusCell(new Spell("éclair", 2)));
            } else if (j < 3 * numBonusTile) {
                tiles.set(index, new OffensiveBonusCell(new Weapon("épée longue", 5)));
            } else if (j < 4 * numBonusTile) {
                tiles.set(index, new OffensiveBonusCell(new Weapon("massue", 3)));
            } else if (j < 5 * numBonusTile) {
                tiles.set(index, new DefensiveBonusCell(new Potion("philtre de peau de pierre", 4)));
            } else if (j < 6 * numBonusTile) {
                tiles.set(index, new DefensiveBonusCell(new Potion("philtre de renfort", 2)));
            } else if (j < 7 * numBonusTile) {
                tiles.set(index, new DefensiveBonusCell(new Shield("bouclier en fer", 2)));
            } else if (j < 8 * numBonusTile) {
                tiles.set(index, new DefensiveBonusCell(new Shield("bouclier en tungstène", 6)));
            }
        }
        //Setting the enemies
        placed = bonusTiles-1;
        for (int j = 0; j < enemyTiles; j++) {
            int index = indices.get(placed++);
            if (j<numEnemyTile) {
                tiles.set(index, new EnemyCell(new Gobelin()));
            } else if (j< 2 * numEnemyTile) {
                tiles.set(index, new EnemyCell(new Wizard()));
            } else if (j< 3 * numEnemyTile) {
                tiles.set(index, new EnemyCell(new Dragon()));
            }

        }

        return this.tiles;
    }
}
