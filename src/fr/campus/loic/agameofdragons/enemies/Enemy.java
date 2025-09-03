package fr.campus.loic.agameofdragons.enemies;

import fr.campus.loic.agameofdragons.Menu;
import fr.campus.loic.agameofdragons.characters.Character;
import fr.campus.loic.agameofdragons.interfaces.IDisplay;
import fr.campus.loic.agameofdragons.interfaces.IFight;
import fr.campus.loic.agameofdragons.material.Dice;
import fr.campus.loic.agameofdragons.tools.ConsoleColors;
import fr.campus.loic.agameofdragons.tools.GameLogger;

/**
 * Represent an enemy that can be encountered on the board
 */
public abstract class Enemy implements IFight<fr.campus.loic.agameofdragons.characters.Character>, IDisplay {

    private String name;
    private int attack;
    private int life;
    private Dice dice20 = new Dice(20);

    public Enemy (String name, int attack, int life) {
        this.name = name;
        this.attack = attack;
        this.life = life;
    }

    public String getName(){
        return this.name;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }
    public int getAttack() {
        return this.attack;
    }

    public void setLife(int life) {
        this.life = life;
    }
    public int getLife() {
        return this.life;
    }

    /**
     * displays a message on the console
     *
     * @param message is the message displayed
     */
    @Override
    public void displayMessage(String message) {
        GameLogger.LOGGER.info(message  + ConsoleColors.RESET);
    }

    /**
     * allows the Enemy to fight the character. It can set a new value for the life attribute of the Character.
     *
     * @param character is the Character fought
     */
    @Override
    public void fight(Character character) {
        displayMessage("PV du héros : " + character.getLife());
        int result = dice20.rollDice();
        if (result != 1 && result != 20) {
            character.setLife(character.getLife() - this.attack);
        } else if (result == 1) {
            displayMessage(ConsoleColors.BOLD_GREEN + "Echec critique ! " + this.name + " rate complètement son attaque !\n");
        } else if (result == 20) {
            displayMessage(ConsoleColors.BOLD_RED + "Coup critique ! " + this.name + " va te faire très mal...\n");
            character.setLife(character.getLife() - (this.attack+5));
        }
        if (character.getLife() < 0) {
            character.setLife(0);
        }
        if (result != 1) {
            displayMessage("Le/la " + this.name + " frappe " + character.getName() + ". Ses PV sont maintenant à " + character.getLife() + ".\n");
        }
    }

    @Override
    public String toString() {
        return "Cet ennemi est un(e) " + this.name + " qui a une attaque à " + this.attack + " et possède " + this.life + " points de vie";
    }
}
