package fr.campus.loic.agameofdragons.exceptions;

/**
 * This exception prevents a character from equipping an item they are not allowed to.
 */
public class WrongEquipmentException extends RuntimeException {

    public WrongEquipmentException(String message) {
        super(message);
    }
}
