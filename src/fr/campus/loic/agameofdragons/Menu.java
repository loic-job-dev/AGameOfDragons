package fr.campus.loic.agameofdragons;

import java.util.InputMismatchException;
import java.util.Scanner;

import fr.campus.loic.agameofdragons.characters.Character;
import fr.campus.loic.agameofdragons.material.Board;
import fr.campus.loic.agameofdragons.material.Dice;

/**
 * This class implements all the methods to allow the player to read instructions and give his choices
 */
public class Menu {

    private final Scanner clavier;
    private boolean gamePaused;
    private boolean gameClosed;

    public Menu (){
        this.clavier =  new Scanner(System.in);
        this.gamePaused = true;
        this.gameClosed = false;
    }

    protected boolean getGamePaused() {
        return this.gamePaused;
    }

    protected boolean getGameClosed() {
        return this.gameClosed;
    }

    protected void displayMessage(String message){
        System.out.println(message);
    }

    /**
     * This method allows the user to set a type of Character and a name for the character he wants to play with.
     *
     * @return It returns the Character
     */
    protected Character createCharacter(){
        String type;
        String name = "error";
        String characterType = "error";
        boolean typeIsValid = false;
        boolean nameIsValid = false;

        //validation of the name
        displayMessage("Comment se nommera ton personnage ?");
        while (!nameIsValid) {
            name = clavier.nextLine();
            if (name.trim().isEmpty()) {
                displayMessage("\nMerci de renseigner un nom\n");
            }
            else {
                nameIsValid = true;
            }
        }
        displayMessage("\nTon personnage se nommera " + name + ".\n");

        //Validation of the type of character
        displayMessage("Quel type de personnage veux-tu incarner ? \nGuerrier ou Mage ?");
        while(!typeIsValid) {
            type = clavier.next();
            if (type.equals("Guerrier") || type.equals("Mage")) {
                typeIsValid = true;
                if (type.equals("Guerrier")) {
                    characterType = "Warrior";
                } else {
                    characterType =  "Magician";
                }
            } else {
                displayMessage ("\nMerci saisir \"Guerrier\" ou \"Mage\" uniquement !\n");
            }
        }
        displayMessage("\nTon personnage sera de la classe " + characterType + ".\n");

        //Creation of the character
        Character character;
        if (characterType.equals("Warrior")) {
            character = new Character("Warrior", name, 10, 5, "Shield");
        } else {
            character = new Character("Magician", name, 6, 8, "Potion");
        }
        return character;
    }

    /**
     * This method is the main menu of the game; it offers the user choices (display information about the character,
     * rename the character, and begin, pause, or leave the game).
     *
     * @param character is the character created at the beginning.
     */
    protected void principal(Character character) {

        while (gamePaused && !gameClosed) {
            if (character.getPosition() == 0) {
                displayMessage("Que veux-tu faire ?\n" +
                        "1- Voir les statistiques de ton personnage.\n" +
                        "2- Modifier son nom.\n" +
                        "3- Commencer la partie.\n" +
                        "9- Quitter le jeu (lâcheur...)\n" +
                        "Tapper le chiffre correspondant au choix");
            } else {
                displayMessage("\nQue veux-tu faire ?\n" +
                        "1- Voir les statistiques de ton personnage.\n" +
                        "2- Modifier son nom.\n" +
                        "3- Reprendre la partie.\n" +
                        "9- Quitter le jeu (lâcheur...)\n" +
                        "Tapper le chiffre correspondant au choix");
            }

            try {
                int choice = clavier.nextInt();
                clavier.nextLine();

                if (choice == 1) {
                    displayMessage("\n" + character.toString());
                }

                if (choice == 2) {
                    boolean nameIsValid = false;
                    String newName = "error";
                    displayMessage("\nComment se nomme ton personnage à présent ?");
                    while (!nameIsValid) {
                        newName = clavier.nextLine();
                        if (newName.trim().isEmpty()) {
                            displayMessage("\nMerci de renseigner un nom");
                        } else {
                            nameIsValid = true;
                            character.setName(newName);
                            ;
                        }
                    }
                    displayMessage("\nTon personnage se nomme à présent " + character.getName() + ".");
                }

                if (choice == 3) {
                    this.gamePaused = false;
                }

                if (choice == 9) {
                    displayMessage("\nAu revoir " + character.getName() + ", puisse le Valhala t'accueillir dans dans sa fête éternelle...\n");
                    this.gameClosed = true;
                }
            } catch (InputMismatchException e) {
                displayMessage("\nMerci de saisir un chiffre pour indiquer ton choix.\n");
                clavier.nextLine();
            };
        }
    }

    /**
     * This method allows the player to make choices during his turn : launch the dice, or set the game in pause.
     *
     * @param character is the Character created by the player at the beginning
     * @param board is the board created, with a defined number of tiles.
     * @param dice is the dice created for the game, with a defined number of faces.
     */
    protected void playerTurn(Character character, Board board, Dice dice) {
        while (!gameClosed) {
            displayMessage("\nle personnage est en position " + character.getPosition() + ".\n");
            displayMessage("Que veux-tu faire ?\n" +
                    "1- Lancer le dé.\n" +
                    "2- Mettre le jeu en pause.\n" +
                    "Tapper le chiffre correspondant au choix");

            try {
                int choice = clavier.nextInt();
                clavier.nextLine();

                if (choice == 1) {
                    displayMessage("Le dé est lancé...");
                    int diceNumber = dice.rollDice();
                    displayMessage("\nLe dé affiche " + diceNumber + ".");
                    character.setPosition(character.getPosition() + diceNumber);
                    if (character.getPosition() > board.getNumTiles()) {
                        character.setPosition(board.getNumTiles());
                    }
                }

                if (choice == 2) {
                    this.gamePaused = true;
                    this.gameClosed = false;
                    principal(character);
                }

            } catch (InputMismatchException e) {
                displayMessage("\nMerci de saisir un chiffre pour indiquer ton choix.\n");
                clavier.nextLine();
            }
            ;
        }
    }
}
