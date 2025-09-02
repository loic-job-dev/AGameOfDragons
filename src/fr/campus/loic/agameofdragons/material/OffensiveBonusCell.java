package fr.campus.loic.agameofdragons.material;

import fr.campus.loic.agameofdragons.Menu;
import fr.campus.loic.agameofdragons.characters.Character;
import fr.campus.loic.agameofdragons.equipment.DefensiveEquipment;
import fr.campus.loic.agameofdragons.equipment.OffensiveEquipment;
import fr.campus.loic.agameofdragons.exceptions.WrongEquipmentException;
import fr.campus.loic.agameofdragons.tools.ConsoleColors;

/**
 * This class represents a tile where an offensive equipment can be found
 */
public class OffensiveBonusCell extends Cell {
    protected OffensiveEquipment offensiveEquipment;

    public OffensiveBonusCell (OffensiveEquipment offensiveEquipment) {
        super();
        this.offensiveEquipment = offensiveEquipment;
    }

    public OffensiveEquipment getContent() {
        return this.offensiveEquipment;
    }
    /**
     * This method allows a character to equip an offensive bonus if possible
     *
     * @param character is the character finding the object
     * @param menu is the menu that displays the information
     */
    @Override
    public void action (Character character, Menu menu) {
        if (!isAlreadyVisited) {
            try {
                menu.displayMessage(ConsoleColors.BOLD_GREEN + "\nTu as trouvé un(e) " + offensiveEquipment.getName() + ".");
                if (character.getOffensiveEquipment().getAttackBuff() <= offensiveEquipment.getAttackBuff()) {
                    character.setOffensiveEquipment(offensiveEquipment);
                    menu.displayMessage(ConsoleColors.BOLD_GREEN + "Tu t'en équipes immédiatement !\n");
                }
                else {
                    menu.displayMessage(ConsoleColors.BLUE + "Ton équipement actuel est meilleur...\n");
                }
            } catch (WrongEquipmentException e) {
                menu.displayMessage(ConsoleColors.YELLOW + e.getMessage());
            }
            isAlreadyVisited = true;
        } else {
            menu.displayMessage(ConsoleColors.BLUE + "Equipement déjà récupéré...\n");
        }
    }
    
    @Override
    public String toString() {
        return ConsoleColors.BOLD_GREEN + "Case bonus offensif qui contient " + this.offensiveEquipment.toString() + " !";
    }
}
