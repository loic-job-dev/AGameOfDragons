package fr.campus.loic.agameofdragons.exceptions;

/**
 * This exception handles the case where a character passes the last square of the board on their last die roll
 */
public class PersonnageHorsPlateauException extends Exception{

    public PersonnageHorsPlateauException(String message) {
        super (message);
    }
}
