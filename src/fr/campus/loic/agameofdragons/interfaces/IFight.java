package fr.campus.loic.agameofdragons.interfaces;

/**
 * contains the method to allow a character and an enemy to fight
 *
 * @param <T> is either the Character or the Enemy
 */
public interface IFight<T> {
    public void fight(T target);
}
