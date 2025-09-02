package fr.campus.loic.agameofdragons.material;

import fr.campus.loic.agameofdragons.Menu;
import fr.campus.loic.agameofdragons.characters.Character;
import fr.campus.loic.agameofdragons.enemies.Enemy;
import fr.campus.loic.agameofdragons.tools.ConsoleColors;

/**
 * This class represents a tile where an enemy can be found
 */
public class EnemyCell extends Cell{

    Enemy enemy = new Enemy();

    @Override
    public Enemy getContent() {
        return this.enemy;
    }

    @Override
    public void action(Character character, Menu menu) {

    }

    @Override
    public String toString() {
        return ConsoleColors.BOLD_RED + "Case ennemi !";
    }
}