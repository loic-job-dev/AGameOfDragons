import fr.campus.characters.Character;

public class Game {

    public void play(){
        Menu menu = new Menu();

        menu.displayMessage("Bonjour à toi, nouveau joueur.\n" +
                "Commence par créer ton personnage.\n");

        Character player =  menu.createCharacter();

        menu.displayMessage("Bienvenue sur A Game Of Dragons !\n" +
                "Prêt à souffrir ?\n");


        menu.principal(player);

    }
}
