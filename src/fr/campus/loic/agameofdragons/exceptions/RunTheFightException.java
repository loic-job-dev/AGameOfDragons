package fr.campus.loic.agameofdragons.exceptions;

/**
 * This exception allows the player to run from a fight.
 */
public class RunTheFightException extends RuntimeException {
    public RunTheFightException(String message) {
        super(message);
    }
}
