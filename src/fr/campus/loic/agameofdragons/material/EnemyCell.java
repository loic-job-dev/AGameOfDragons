package fr.campus.loic.agameofdragons.material;

import fr.campus.loic.agameofdragons.Menu;
import fr.campus.loic.agameofdragons.characters.Character;
import fr.campus.loic.agameofdragons.enemies.Dragon;
import fr.campus.loic.agameofdragons.enemies.Enemy;
import fr.campus.loic.agameofdragons.enemies.Gobelin;
import fr.campus.loic.agameofdragons.enemies.Wizard;
import fr.campus.loic.agameofdragons.interfaces.IDisplay;
import fr.campus.loic.agameofdragons.tools.ConsoleColors;
import fr.campus.loic.agameofdragons.tools.GameLogger;

/**
 * This class represents a tile where an enemy can be found
 */
public class EnemyCell extends Cell {

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
        int fightPosition = character.getPosition();
        while (character.getLife() != 0 && this.enemy.getLife() != 0 && character.getPosition() == fightPosition) {
            character.fight(this.enemy);
            if (this.enemy.getLife() != 0 && character.getPosition() == fightPosition) {
                enemy.fight(character);
            }
        }
    }

    @Override
    public String toString() {
        return ConsoleColors.BOLD_RED + "Case ennemi ! " + this.enemy.toString();
    }
}