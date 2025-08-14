import fr.campus.characters.Character;
import fr.campus.material.Dice;
import fr.campus.material.Board;

public class Game {

    Menu menu;
    Dice dice;
    Board board;

    public Game() {
        this.menu = new Menu();
        this.dice = new Dice(6);
        this.board = new Board(64);
    }

    public void play(){

        menu.displayMessage("Bonjour à toi, nouveau joueur.\n" +
                "Commence par créer ton personnage.\n");

        Character player =  menu.createCharacter();

        menu.displayMessage("Bienvenue sur A Game Of Dragons !\n" +
                "Prêt à souffrir ?\n");

        /*
        int test = 0;
        while (test!=6) {
            test = dice.rollDice();
            System.out.println(test);
        }
        */

        menu.principal(player);

        if (menu.getInGame()){
            startGame(player, board, dice);
        }

    }

    private void startGame(Character character, Board board, Dice dice){
        menu.displayMessage("Début de la partie !");

        while (character.getPosition() != board.getNumTiles()) {
            menu.displayMessage("\nle personnage est en position " + character.getPosition() + ".\n");
            menu.displayMessage("Le dé est lancé...\n");
            int diceNumber = dice.rollDice();
            menu.displayMessage("\nLe dé affiche " + diceNumber + ".\n");
            character.setPosition(character.getPosition()+diceNumber);
            if (character.getPosition() > board.getNumTiles()){
                character.setPosition(board.getNumTiles());
            }
        }
        menu.displayMessage("Tu es arrivé au bout du plateau, félicitations " + character.getName() + " !\n");
    }
}
