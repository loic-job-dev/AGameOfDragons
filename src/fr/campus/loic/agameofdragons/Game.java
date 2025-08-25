package fr.campus.loic.agameofdragons;

import fr.campus.loic.agameofdragons.characters.Character;
import fr.campus.loic.agameofdragons.material.Dice;
import fr.campus.loic.agameofdragons.material.Board;

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

        menu.displayMessage("Bonjour à toi, nouveau joueur.\n" +
                "Commence par créer ton personnage.\n");

        Character player =  menu.createCharacter();

        menu.displayMessage("Bienvenue sur A Game Of Dragons " + player.getName() + " !\n" +
                "Prêt à souffrir ?\n");


        menu.principal(player);

        if (!menu.getGamePaused()){
            startGame(player, board, dice);
        }

    }

    /**
     * This method is the game itself : it allows the player to play his turn until the end of the game.
     *
     * @param character is the Character created by the player at the beginning
     * @param board is the board created, with a defined number of tiles.
     * @param dice is the dice created for the game, with a defined number of faces.
     */
    private void startGame(Character character, Board board, Dice dice){
        menu.displayMessage("Début de la partie !");

        while (character.getPosition() != board.getNumTiles()) {
            menu.playerTurn(character, board, dice);
        }
        menu.displayMessage("Tu es arrivé au bout du plateau, félicitations " + character.getName() + " !\n");
    }
}
