package fr.campus.loic.agameofdragons.material;

import fr.campus.loic.agameofdragons.Menu;
import fr.campus.loic.agameofdragons.characters.Character;
import fr.campus.loic.agameofdragons.enemies.Dragon;
import fr.campus.loic.agameofdragons.enemies.Enemy;
import fr.campus.loic.agameofdragons.enemies.Gobelin;
import fr.campus.loic.agameofdragons.enemies.Wizard;
import fr.campus.loic.agameofdragons.tools.ConsoleColors;

/**
 * This class represents a tile where an enemy can be found
 */
public class EnemyCell extends Cell{

    private Enemy enemy;

    public EnemyCell (Enemy enemy) {
        super();
        this.enemy = enemy;
    }

    @Override
    public Enemy getContent() {
        return this.enemy;
    }

    @Override
    public void action(Character character, Menu menu) {

    }

    @Override
    public String toString() {
        return ConsoleColors.BOLD_RED + "Case ennemi ! " + this.enemy.toString();
    }
}