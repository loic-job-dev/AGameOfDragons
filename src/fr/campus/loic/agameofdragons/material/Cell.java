package fr.campus.loic.agameofdragons.material;

import fr.campus.loic.agameofdragons.Menu;
import fr.campus.loic.agameofdragons.characters.Character;
import fr.campus.loic.agameofdragons.equipment.DefensiveEquipment;
import fr.campus.loic.agameofdragons.tools.ConsoleColors;

/**
 * This class represents a tile on the board
 */
public abstract class Cell {

    protected int id;
    protected boolean isAlreadyVisited;

    /**
     * The constructor sets a boolean that is usefull to indicate if the tile has been visited
     */
    public Cell () {
        this.isAlreadyVisited = false;
    }

    public boolean getIsAlreadyVisited() {
        return this.isAlreadyVisited;
    }
    public void setIsAlreadyVisited(boolean isAlreadyVisited) {
        this.isAlreadyVisited = isAlreadyVisited;
    }

    public abstract Object getContent();

    //Getter and setter for the Id Database
    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return this.id;
    }

    /**
     * This method describes the consequence of the arrival of the player on this tile
     *
     * @param character is the character of the player
     * @param menu is the menu created to display messages
     */
    abstract public void action (Character character, Menu menu);

    @Override
    public String toString() {
        return ConsoleColors.BLUE + "Case vide !";
    }


}