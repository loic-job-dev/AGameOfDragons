package fr.campus.loic.agameofdragons;

import fr.campus.loic.agameofdragons.characters.Character;
import fr.campus.loic.agameofdragons.equipment.DefensiveEquipment;
import fr.campus.loic.agameofdragons.equipment.OffensiveEquipment;
import fr.campus.loic.agameofdragons.equipment.Potion;
import fr.campus.loic.agameofdragons.equipment.Weapon;
import fr.campus.loic.agameofdragons.exceptions.PersonnageHorsPlateauException;
import fr.campus.loic.agameofdragons.exceptions.WrongEquipmentException;
import fr.campus.loic.agameofdragons.material.Dice;
import fr.campus.loic.agameofdragons.material.Board;
import fr.campus.loic.agameofdragons.tools.ConsoleColors;

/**
 * This class implements all the game logic
 */
public class Game {

    Menu menu;
    Dice dice;
    Board board;

    /**
     * The constructor sets a menu, a dice with 6 faces and a board with 64 tiles.
     */
    public Game() {
        this.menu = new Menu();
        this.dice = new Dice(6);
        this.board = new Board(64);
    }

    /**
     * This method is called at the beginning of the game, when the player hasn't a character yet.
     * It calls the method to create a character, and then the method to play the game
     */
    public void launchGame(){

        menu.displayMessage(ConsoleColors.CYAN + "Bonjour à toi, nouveau joueur.\n" +
                ConsoleColors.YELLOW + "Commence par créer ton personnage.\n");

        Character player =  menu.createCharacter();

        menu.displayMessage(ConsoleColors.CYAN + "Bienvenue sur A Game Of Dragons, " + player.getName() + " !\n" +
                ConsoleColors.RED + "Prêt à souffrir ?\n");


        menu.principal(player);

        if (!menu.getGamePaused()){
            startGame(player, board, dice);
        }

        if (menu.getGameClosed()){
            System.exit(0);
        }
    }

    /**
     * This method is the game itself : it allows the player to play his turn until the end of the game.
     *
     * @param character is the Character created by the player at the beginning
     * @param board is the board created, with a defined number of tiles.
     * @param dice is the dice created for the game, with a defined number of faces.
     */
    private void startGame(Character character, Board board, Dice dice) {
        menu.displayMessage(ConsoleColors.RED + "Début de la partie !");

        //Attribution test for a weapon to a warrior :
        Weapon massue = new Weapon("massue", 3);
        findEquipment(character, massue);

        //Attribution test for a potion to a magician :
        Potion philtre = new Potion("Peau de pierre", 3);
        findEquipment(character, philtre);

        //Display the equipment of the character
        menu.displayMessage(character.toString());

        while (character.getPosition() != board.getNumTiles() && !menu.getGameClosed()) {
            try {
                menu.playerTurn(character, board, dice);
            } catch (PersonnageHorsPlateauException e) {
                menu.displayMessage(ConsoleColors.RED + e.getMessage());
                character.setPosition(board.getNumTiles());
            }
        }
        if (character.getPosition() == board.getNumTiles()) {
            menu.displayMessage(ConsoleColors.BOLD_GREEN + "Tu es arrivé au bout du plateau, félicitations " + character.getName() + " !\n");
            menu.setGameClosed(true);
        }
    }

    /**
     * This method allows a character to equip an object
     *
     * @param character is the character finding the object
     * @param defensiveEquipment is the defensive object found
     */
    private void findEquipment (Character character, DefensiveEquipment defensiveEquipment) {
        try {
            character.setDefensiveEquipment(defensiveEquipment);
            menu.displayMessage(ConsoleColors.BOLD_GREEN + "\nTu t'équipes d'un(e) " + defensiveEquipment.getName() + ".\n");
        } catch (WrongEquipmentException e) {
            menu.displayMessage(ConsoleColors.YELLOW + e.getMessage());
        }
    }

    /**
     * This method allows a character to equip an object
     *
     * @param character is the character finding the object
     * @param offensiveEquipment is the offensive object found
     */
    private void findEquipment (Character character, OffensiveEquipment offensiveEquipment) {
        try {
            character.setOffensiveEquipment(offensiveEquipment);
            menu.displayMessage(ConsoleColors.BOLD_GREEN + "\nTu t'équipes d'un(e) " + offensiveEquipment.getName() + ".\n");
        } catch (WrongEquipmentException e) {
            menu.displayMessage(ConsoleColors.YELLOW + e.getMessage());
        }
    }
}
