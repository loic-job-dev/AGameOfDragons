package fr.campus.loic.agameofdragons;

import java.util.InputMismatchException;
import java.util.Scanner;

import fr.campus.loic.agameofdragons.characters.*;
import fr.campus.loic.agameofdragons.characters.Character;
import fr.campus.loic.agameofdragons.db.DatabaseConnector;
import fr.campus.loic.agameofdragons.exceptions.PersonnageHorsPlateauException;
import fr.campus.loic.agameofdragons.material.*;
import fr.campus.loic.agameofdragons.tools.*;

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
    protected void setGamePaused(boolean newBool) {
        this.gamePaused = newBool;
    }

    protected boolean getGameClosed() {
        return this.gameClosed;
    }
    protected void setGameClosed(boolean newBool) {
        this.gameClosed = newBool;
    }

    public Scanner getClavier() {
        return clavier;
    }

    /**
     * Logs a message to the console using {@link GameLogger#LOGGER},
     * with colors reset after the message.
     *
     * @param message The message to display.
     */
    public void displayMessage(String message){
        GameLogger.LOGGER.info(message  + ConsoleColors.RESET);
    }

    /**
     * Creates a character by asking the user to choose a type and a name.
     *
     * @return The created character.
     */
    protected Character createCharacter(DatabaseConnector db){
        String type;
        String name = "error";
        String characterType = "error";
        boolean typeIsValid = false;
        boolean nameIsValid = false;

        //validation of the name
        displayMessage(ConsoleColors.CYAN + "Comment se nommera ton personnage ?");
        while (!nameIsValid) {
            name = clavier.nextLine();
            if (name.trim().isEmpty()) {
                displayMessage(ConsoleColors.BOLD_RED + "\nMerci de renseigner un nom\n");
            }
            else {
                nameIsValid = true;
            }
        }
        displayMessage(ConsoleColors.PURPLE + "\nTon personnage se nommera " + name + ".\n");

        //Validation of the type of character
        displayMessage(ConsoleColors.CYAN + "Quel type de personnage veux-tu incarner ? \nGuerrier ou Mage ?");
        while(!typeIsValid) {
            type = clavier.next();
            if (type.equals("Guerrier") || type.equals("Mage")) {
                typeIsValid = true;
                if (type.equals("Guerrier")) {
                    characterType = "warrior";
                } else {
                    characterType =  "magician";
                }
            } else {
                displayMessage (ConsoleColors.BOLD_RED + "\nMerci saisir \"Guerrier\" ou \"Mage\" uniquement !\n");
            }
        }

        //Creation of the character
        Character character;
        if (characterType.equals("warrior")) {
            character = new Warrior(name);
        } else {
            character = new Magician(name);
        }
        db.createHero(character);
        return character;
    }

    /**
     * Displays the main menu of the game, allowing the player to manage the character
     * and control the game (view information, rename, start, pause, or exit).
     *
     * @param character The player's character.
     */
    protected void principal(Character character, DatabaseConnector db) {

        while (gamePaused && !gameClosed) {
            if (character.getPosition() == 0) {
                displayMessage(ConsoleColors.CYAN + "Que veux-tu faire ?\n" +
                        "1- Voir les statistiques de ton personnage.\n" +
                        "2- Modifier son nom.\n" +
                        ConsoleColors.GREEN + "3- Commencer la partie.\n" +
                        ConsoleColors.RED + "9- Quitter le jeu (lâcheur...)\n" + ConsoleColors.RESET +
                        ConsoleColors.YELLOW + "Tapper le chiffre correspondant au choix");
            } else {
                displayMessage(ConsoleColors.CYAN + "\nQue veux-tu faire ?\n" +
                        "1- Voir les statistiques de ton personnage.\n" +
                        "2- Modifier son nom.\n" +
                        ConsoleColors.GREEN + "3- Reprendre la partie.\n" +
                        ConsoleColors.GREEN + "4- Sauvegarder le héros.\n" +
                        ConsoleColors.RED + "9- Quitter le jeu (lâcheur...)\n" + ConsoleColors.RESET +
                        ConsoleColors.YELLOW + "Tapper le chiffre correspondant au choix");
            }

            try {
                int choice = clavier.nextInt();
                clavier.nextLine();

                if (choice == 1) {
                    displayMessage("\n" + character.toString());
                }

                else if (choice == 2) {
                    boolean nameIsValid = false;
                    String newName = "error";
                    displayMessage(ConsoleColors.CYAN + "\nComment se nomme ton personnage à présent ?");
                    while (!nameIsValid) {
                        newName = clavier.nextLine();
                        if (newName.trim().isEmpty()) {
                            displayMessage(ConsoleColors.BOLD_RED + "\nMerci de renseigner un nom");
                        } else {
                            nameIsValid = true;
                            character.setName(newName);
                            ;
                        }
                    }
                    displayMessage(ConsoleColors.PURPLE + "\nTon personnage se nomme à présent " + character.getName() + ".");
                }

                else if (choice == 3) {
                    this.gamePaused = false;

                }

                else if (character.getPosition() != 0 && choice == 4) {
                    db.editHero(character);
                    displayMessage(ConsoleColors.GREEN + "Héros sauvegardé.\n");
                }

                else if (choice == 9) {
                    displayMessage(ConsoleColors.BLUE + "\nAu revoir " + character.getName() + ", puisse le Valhala t'accueillir dans dans sa fête éternelle...\n");
                    this.gameClosed = true;
                } else {
                    displayMessage(ConsoleColors.BOLD_RED + "\nMerci de saisir un chiffre valide pour indiquer ton choix.\n");
                }
            } catch (InputMismatchException e) {
                displayMessage(ConsoleColors.BOLD_RED + "\nMerci de saisir un chiffre valide pour indiquer ton choix.\n");
                clavier.nextLine();
            };
        }
    }

    /**
     * Executes a player's turn, giving them the option to roll the dice or pause the game.
     *
     * @param character The player's character.
     * @param board The game board with its tiles.
     * @param dice The dice used to determine moves.
     */
    protected void playerTurn(Character character, Board board, Dice dice, DatabaseConnector db) throws PersonnageHorsPlateauException {
        displayMessage(ConsoleColors.CYAN + "le personnage est en position " + character.getPosition() + ".\n");
        displayMessage(ConsoleColors.CYAN + "Que veux-tu faire ?\n" +
                "1- Lancer le dé.\n" +
                ConsoleColors.BLUE + "2- Mettre le jeu en pause.\n" +
                ConsoleColors.YELLOW + "Tapper le chiffre correspondant au choix");

        try {
            // Throws an InputMismatchException if the user doesn't give an integer
            int choice = clavier.nextInt();
            clavier.nextLine();

            if (choice == 1) {
                displayMessage(ConsoleColors.YELLOW + "Le dé est lancé...");
                int diceNumber = dice.rollDice();
                displayMessage(ConsoleColors.PURPLE + "\nLe dé affiche " + diceNumber + ".");
                if (character.getPosition() + diceNumber > board.getNumTiles()) {
                    throw new PersonnageHorsPlateauException(ConsoleColors.BOLD_GREEN + character.getName() + " a dépassé la dernière case du plateau !\n");
                }
                character.setPosition(character.getPosition() + diceNumber);
            }

            else if (choice == 2) {
                this.gamePaused = true;
                this.gameClosed = false;
                principal(character, db);
            }

            else {
                displayMessage(ConsoleColors.BOLD_RED + "\nMerci de saisir un chiffre valide pour indiquer ton choix.\n");
            }

        } catch (InputMismatchException e) {
            displayMessage(ConsoleColors.BOLD_RED + "\nMerci de saisir un chiffre valide pour indiquer ton choix.\n");
            clavier.nextLine();
        };
    }
}
