package fr.campus.loic.agameofdragons.material;

import fr.campus.loic.agameofdragons.Menu;
import fr.campus.loic.agameofdragons.characters.Character;
import fr.campus.loic.agameofdragons.tools.ConsoleColors;

/**
 * This class represents a tile on the board
 */
public abstract class Cell {

    protected boolean isAlreadyVisited;

    /**
     * The constructor sets a boolean that is usefull to indicate if the tile has been visited
     */
    public Cell () {
        this.isAlreadyVisited = false;
    }

    /**
     * This method describes the consequence of the arrival of the player on this tile
     *
     * @param character is the character of the player
     * @param menu is the menu created to display messages
     */
    public void action (Character character, Menu menu){

    }

    @Override
    public String toString() {
        return ConsoleColors.BLUE + "Case vide !";
    }
}