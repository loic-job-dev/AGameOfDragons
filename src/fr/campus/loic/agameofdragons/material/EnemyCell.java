package fr.campus.loic.agameofdragons.material;

import fr.campus.loic.agameofdragons.tools.ConsoleColors;

/**
 * This class represents a tile where an enemy can be found
 */
public class EnemyCell extends Cell{

    @Override
    public String toString() {
        return ConsoleColors.BOLD_RED + "Case ennemi !";
    }
}