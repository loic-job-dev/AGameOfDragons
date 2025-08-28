package fr.campus.loic.agameofdragons.material;

import fr.campus.loic.agameofdragons.Menu;
import fr.campus.loic.agameofdragons.characters.Character;

public abstract class Cell {
    protected boolean isAlreadyVisited;

    public Cell () {
        this.isAlreadyVisited = false;
    }

    public void action (Character character, Menu menu){

    }

    @Override
    public String toString() {
        return "Case vide !";
    }
}
