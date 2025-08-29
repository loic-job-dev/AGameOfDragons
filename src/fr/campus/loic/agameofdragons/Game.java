package fr.campus.loic.agameofdragons;

import fr.campus.loic.agameofdragons.characters.Character;
import fr.campus.loic.agameofdragons.exceptions.PersonnageHorsPlateauException;
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
        //numTiles minimal to avoid errors with the random tiles : 14 with 6 weapons and 3 enemies
        this.board = new Board(64);
    }

    /**
     * Initializes the game when no character has been created yet.
     * It creates the player character and then starts the game loop.
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
     * Runs the main game loop, allowing the player to take turns until the game ends.
     *
     * @param character The player character created at the beginning of the game.
     * @param board The game board with a defined number of tiles.
     * @param dice The dice used in the game, with a defined number of faces.
     */
    private void startGame(Character character, Board board, Dice dice) {
        menu.displayMessage(ConsoleColors.RED + "Début de la partie !");

        //Loop where the player plays
        while (character.getPosition() != board.getNumTiles() && !menu.getGameClosed()) {
            try {
                menu.playerTurn(character, board, dice);
                menu.displayMessage(board.getTiles().get(character.getPosition()).toString());
                board.getTiles().get(character.getPosition()).action(character, menu);
            } catch (PersonnageHorsPlateauException e) {
                menu.displayMessage(ConsoleColors.RED + e.getMessage());
                character.setPosition(board.getNumTiles());
            }
        }

        //Victory condition
        if (character.getPosition() == board.getNumTiles()) {
            menu.displayMessage(ConsoleColors.BOLD_GREEN + "Tu es arrivé au bout du plateau, félicitations " + character.getName() + " !\n");
            menu.setGameClosed(true);
        }
    }
}
