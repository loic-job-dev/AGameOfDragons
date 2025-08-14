import java.util.Scanner;
import fr.campus.characters.*;
import fr.campus.characters.Character;

public class Menu {

    static Scanner clavier = new Scanner(System.in);

    public Menu (){

    }

    protected void displayMessage(String message){
        System.out.println(message);
    }

    protected Character createCharacter(){
        String type;
        String name = "error";
        String characterType = "error";
        boolean typeIsValid = false;
        boolean nameIsValid = false;

        //validation of the name
        displayMessage("Comment se nommera votre personnage ?");
        while (!nameIsValid) {
            name = clavier.nextLine();
            if (name.isEmpty()) {
                displayMessage("Merci de renseigner un nom");
            }
            else {
                nameIsValid = true;
            }
        }
        displayMessage("Votre personnage se nommera " + name);

        //Validation of the type of character
        displayMessage("Quel type de personnage voulez-vous incarner ? \nGuerrier ou Mage ?");
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
                displayMessage ("Veuillez saisir \"Guerrier\" ou \"Mage\" uniquement !");
            }
        }
        displayMessage("Votre personnage sera de la classe " + characterType);

        //Creation of the character
        Character character;
        if (characterType.equals("Warrior")) {
            character = new Character("Warrior", name, 10, 5, "Shield");
        } else {
            character = new Character("Magician", name, 6, 8, "Potion");
        }
        displayMessage(character.toString());
        return character;
    }

    /*
    protected int getPosInt(String message) {

        boolean isValid = false;
        int numberTested = 0;

        System.out.println(message);

        while (!isValid) {
            if (!clavier.hasNextInt()) {
                System.out.println("Merci de saisir un nombre entier positif");
                clavier.next();
            }
            else {
                numberTested = clavier.nextInt();
                if (numberTested < 0) {
                    System.out.println("Merci de saisir un nombre entier positif");
                }
                else {
                    isValid = true;
                }
            }
        }
        return numberTested;
    }

     */
}
